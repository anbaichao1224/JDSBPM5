<%@ page contentType="text/html; charset=gb2312" %>


<%@ page import="java.util.*" %>
<%@ page import="net.itjds.bpm.engine.config.CApplication"%>
<%@ page import="net.itjds.bpm.client.*" %>
<%@ page import="net.itjds.bpm.engine.*" %>
<%@ page import="net.itjds.bpm.admin.Constants" %>
<%@ page import="net.itjds.bpm.admin.AdminConfig" %>
<%@ page import="net.itjds.bpm.admin.AdminRight" %>
<%@ page import="net.itjds.bpm.org.base.*" %>

<%
    boolean _userErr = false;

    String username = request.getParameter("username");
    String password = request.getParameter("password");
    try{
      if( username != null && password != null) {
          if(username.equals(AdminConfig.getValue("SuperAdmin.username"))) {            
              if(password.equals(AdminConfig.getValue("SuperAdmin.password"))) {
                  session.removeAttribute(Constants.FDT_ROLE_KEY);
                  session.removeAttribute(Constants.BPD_ROLE_KEY);
                  session.removeAttribute(Constants.BPM_LOGIN_PERSON_KEY);
                  session.setAttribute(Constants.ADMIN_ROLE_KEY, Constants.ADMIN_ROLE_SUPERADMIN);
                  response.sendRedirect("home.jsp");
                  return;
              }
              else {
                  _userErr = true;
              }
          }else{
              OrgManager orgManager = OrgManagerFactory.getOrgManager();
              Person person = orgManager.getPersonByAccount(username);
              if(orgManager.verifyPerson(person.getAccount(), password)){
                session.removeAttribute(Constants.ADMIN_ROLE_KEY);
                String personId = person.getID();
                if(AdminRight.checkPersonRight(personId, Constants.FDT_ROLE_KEY))
                    session.setAttribute(Constants.FDT_ROLE_KEY, Constants.ADMIN_ROLE_FDT);  
                if(AdminRight.checkPersonRight(personId, Constants.BPD_ROLE_KEY))
                    session.setAttribute(Constants.BPD_ROLE_KEY, Constants.ADMIN_ROLE_BPD);  
                session.setAttribute(Constants.BPM_LOGIN_PERSON_KEY, person);
 
				BPMSessionFactory sessionFactory = new BPMSessionFactory();
				BPMSessionHandle sessionHandle = BPMSessionFactory.getSessionHandle(request);
				Map map = WorkflowServer.getApplications();
				for(Iterator it=map.values().iterator(); it.hasNext();) {
					CApplication app = (CApplication) it.next();
					String systemCode = app.getCode();
					WorkflowClientService service = sessionFactory.newClientService(sessionHandle, systemCode);
					service.connect(new ConnectInfo(person.getID(), username, password));
				}
				session.setAttribute("sessionHandle", sessionHandle);
				response.sendRedirect("home.jsp");
                return;
              }else{
                _userErr = true;
              }
          }
      }
    } catch (PersonNotFoundException pnfe) {
        _userErr = true;
    }
%>


<SCRIPT LANGUAGE="JavaScript" src="/bpm/oa/include/js/jcommon.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
<!--
function _check() {
    if( !validfield("frmLogin.username", "NotNull", "Please enter username!")) return false;
	if( !validfield("frmLogin.password", "NotNull", "Please enter password!")) return false;
        
	return true;
}
//-->
</SCRIPT>
<style type="text/css">
<!--
.style1 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 18px;
	font-weight: bold;
	font-style: italic;
	color: #FFFFFF;
}
-->
</style>
<form id="frmLogin" method="post" action="" onSubmit="return _check();">
<%
    if(_userErr) {
%>
    <center>
      <font color="red" size="2">登陆失败,登陆名或口令错误，请重试！</font>
    </center>
<%
    }    
%>     

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=gb2312">
<title>交通委电子政务网上审批系统</title>
<link href="css/default.css" rel="stylesheet" type="text/css">
</head>

<body bgcolor=#ffffff background="images/login_bg.jpg" topmargin=0 marginheight=0 leftmargin=0 marginwidth=0>

<table width="100%" height="100%" cellpadding=0 cellspacing=0 border=0>
  <tr>
    <td height="431" align=center valign=middle>
	<input type="hidden" name="wdl" value="wdl">
<input type="hidden" name="sysId" value="10">
        <table cellpadding=0 cellspacing=0 border=0 background="/images/login_face.jpg" width=609 height=359>
        <tr>
          <td height=177></td>
        </tr>
        <tr>
          <td height=23 align=center>用户名： 
            <input name="username" type="text" class="rilo-input" size="14" tabindex="8" value="<%=username==null?"":username%>"></td>
        </tr>
        <tr>
          <td height=10></td>
        </tr>
        <tr>
          <td height=23 align=center>密　码： 
            <input name="password" type="password" class="rilo-input" size="14" tabindex="9"></td>
        </tr>
        <tr>
          <td height=23></td>
        </tr>
        <tr>
          <td height=21 align=center><input name="提交" type=submit class="loginButton"  value="登 录">
              &nbsp;&nbsp;<input type=button value="重 写" class="loginButton"></td>
        </tr>
        <tr>
          <td height=31></td>
        </tr>
        <tr>
          <td height=26 align=center>北京思普科科技开发有限公司 版权所有</td>
        </tr>
        <tr>
          <td height=21 align=center>Copyright &copyright;2000 Beijing Sparkle Technology & Development Co.,Ltd</td>
        </tr>
        <tr>
          <td height=4></td>
        </tr>
      </table></form> 
	  <script language="JavaScript">
    window.frmLogin.loginname.focus();
</script>
    </td>
  </tr>
</table>

</body>

</html>