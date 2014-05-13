<%
/**
 *    $RCSfile: login.jsp,v $
 *    $Author: liyang $
 *    $Revision: 1.2 $
 *    $Date: 2011/08/03 09:39:04 $
 */
%>

<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="java.util.*" %>
<%@ page import="net.itjds.bpm.engine.config.CApplication"%>
<%@ page import="net.itjds.bpm.client.*" %>
<%@ page import="net.itjds.bpm.engine.*" %>
<%@ page import="net.itjds.bpm.admin.Constants" %>
<%@ page import="net.itjds.bpm.admin.AdminConfig" %>
<%@ page import="net.itjds.bpm.admin.AdminRight" %>
<%@ page import="net.itjds.common.org.base.*" %>

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
				session.setAttribute("personId", person.getID());
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

<HTML>
<HEAD>
<TITLE> BPM System Admin Login </TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=gb2312">
<link href="/bpm/oa/include/css/style.css" rel="stylesheet" type="text/css">
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
</HEAD>
<BODY BGCOLOR=#FFFFFF background="images/comp01_right2.gif" LEFTMARGIN=0 TOPMARGIN=150 MARGINWIDTH=0 MARGINHEIGHT=0>
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
<TABLE WIDTH=672 height="284" BORDER=0 align="center" CELLPADDING=0 CELLSPACING=0>
  <TR> 
      <TD width="407" height="68" background="images/enter_4.gif">&nbsp;<span class="style1"> 
        itjds BPM系统后台管理</span></TD>
    <TD width="253"> <IMG SRC="images/enter_4.gif" WIDTH=253 HEIGHT=68 ALT=""></TD>
    <TD width="12"> <IMG SRC="images/enter_5.gif" WIDTH=12 HEIGHT=68 ALT=""></TD>
  </TR>
  <TR> 
    <TD ROWSPAN=2> <IMG SRC="images/enter_7.gif" WIDTH=407 HEIGHT=171 ALT=""></TD>
    <TD height="87"> <IMG SRC="images/enter_8.gif" WIDTH=253 HEIGHT=87 ALT=""></TD>
    <TD ROWSPAN=2> <IMG SRC="images/enter_9.gif" WIDTH=12 HEIGHT=171 ALT=""></TD>
  </TR>
  <TR> 
    <TD height="84" bgcolor="6FBEFF"><table width="253" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="1" bgcolor="#FFFFFF"> </td>
          <td width="252" height="84" align="center"><table width="230" border="0" cellpadding="3" cellspacing="0">
              <tr align="center"> 
                  <td width="69" align="right">登陆名:</td>
                  <td width="87"> 
                  <input name="username" type="text" class="rilo-input" size="14" tabindex="8" value="<%=username==null?"":username%>"></td>
                <td width="56" rowspan="2"><input type="image" border="0" src="images/login.gif" width="47" height="44" tabindex="10"></td>
              </tr>
              <tr align="center" align="right"> 
                <td>口令:</td>
                <td> <input name="password" type="password" class="rilo-input" size="14" tabindex="9"></td>
              </tr>
            </table></td>
        </tr>
      </table> </TD>
  </TR>
  <TR> 
      <TD height="33" COLSPAN=2 align="center" background="images/enter_11.gif"><font color="#FFFFFF">1998-2008  
        BPM System Admin </font></TD>
    <TD> <IMG SRC="images/enter_12.gif" WIDTH=12 HEIGHT=33 ALT=""></TD>
  </TR>
  <TR> 
    <TD height="12" COLSPAN=2> <IMG SRC="images/enter_13.gif" WIDTH=660 HEIGHT=12 ALT=""></TD>
    <TD> <IMG SRC="images/enter_14.gif" WIDTH=12 HEIGHT=12 ALT=""></TD>
  </TR>
</TABLE>
</form>
<SCRIPT LANGUAGE="JavaScript">
<!--
    frmLogin.username.focus();
//-->
</SCRIPT>
</BODY>
</HTML>