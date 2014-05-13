<%
/**
 *    $RCSfile: modFdtPerson.jsp,v $
 *    $Author: liyang $
 *    $Revision: 1.2 $
 *    $Date: 2011/08/03 09:39:04 $
 */
%>

<%@ page contentType="text/html;charset=gb2312"%>
<%@ include file="../include/jsp/global.jsp" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.*" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="net.itjds.common.org.base.OrgManager" %>
<%@ page import="net.itjds.common.org.base.OrgManagerFactory" %>
<%@ page import="net.itjds.common.org.base.Person" %>
<%@ page import="net.itjds.common.database.DBAgent" %>
<%@ page import="net.itjds.bpm.engine.BPMConstants" %>

<%
	try {
	    String PERSON_ID = request.getParameter("PERSON_ID");
        String APPNAME = request.getParameter("APPNAME");
        DBAgent dba = new DBAgent(BPMConstants.CONFIG_KEY);
        String querySQL = "SELECT PERSON_SELECT_FORMULA FROM ADMIN_BPMSYS_PERSON WHERE PERSON_ID='"+PERSON_ID+"' AND APPNAME='"+APPNAME+"' AND MODULE_NAME='FDT'";
        int state = dba.execute(querySQL);
        if(state == -1) {
            response.sendRedirect("error.jsp?errorMsg="+URLEncoder.encode("指定的数据库记录不存在。")+"&returnURI=fdtPersonList.jsp");
			dba.close();
            return;
        }
        ResultSet rs = dba.getQueryResult();
        String formula = "";
        if(rs.next())
            formula = rs.getString(1);
		dba.close();
        OrgManager orgManager = OrgManagerFactory.getInstance().getOrgManager();
		Person person = orgManager.getPersonByID(PERSON_ID);
%>

<HTML>
<HEAD>
<TITLE> BPM Engine Admin </TITLE>
<link href="../include/css/style.css" rel="stylesheet" type="text/css">
<SCRIPT LANGUAGE="JavaScript" src="../include/js/jcommon.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
<!--
function _check() {
	return true;
}
//-->
</SCRIPT>
</HEAD>

<BODY   LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0> 
<form name="frm" method="post" action="modFdtPerson_do.jsp" onSubmit="return _check();">
<input type="hidden" name="PERSON_ID" value="<%=PERSON_ID%>">
<input type="hidden" name="APPNAME" value="<%=APPNAME%>">

<TABLE width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF"> 
  <tr> 
    <td height="8" bgcolor="E3E8F8"> </td> 
  </tr> 
  <tr> 
    <td height="500" align="center" valign="top"> <TABLE width="100%" border=0 align=center cellspacing="3">
      <tr height="21" valign="bottom">
        <td width="500" class="lin3"><font color="FB4303">■ 当前位置 》编辑表单设计人员</font></td>
      </tr>
      <tr>
        <td height="8"> </td>
      </tr>
        <tr>
          <td>
            <table width="100%" border="0" align="center" cellpadding="2" cellspacing="0" class="tr">
            <tr >
              <td height=21 colspan=2 bgcolor=#E3E8F8 class=td>■<font color=#ffffff>&nbsp;</font>详细信息</td>
            </tr>
            <tr>
              <td width="100%" height="23" align="center" class="td">
                <table width="100%" border="0" cellspacing="0" cellpadding="3">
                    <tr>
                      <td width="24%" height="23" align="right">人员：</td>
                      <td width="76%" height="23"><%=person==null?"无":person.getName() %></td>
                    </tr>
					<tr>
                      <td width="24%" height="23" align="right">应用：</td>
                      <td width="76%" height="23"><%=APPNAME %></td>
                    </tr>
					<tr>
                      <td width="24%" height="23" align="right">人员选择公式：</td>
                      <td width="76%" height="23"><textarea name="PERSON_SELECT_FORMULA" class="inputtxt" rows="7" cols="50"><%=formula==null?"":formula %></textarea></td>
                    </tr>
    </table></td>
  </tr>
  </table>
  <br>
  <table width="191" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr> 
    <td height="25" align="center"> 
      <input name="save" type="submit" class="inputbutton" style="cursor:hand;" value="保 存">
	  <input name="cancle" type="button" class="inputbutton" onClick="window.location='fdtPersonList.jsp'" style="cursor:hand;" value="返 回">
    </td>
  </tr>
</table></td> 
  </tr> 
</table> 
</td> </tr>
</table>
<%@ include file="../include/jsp/bottom.jsp" %>

</BODY>
</HTML>
<%
	}
	catch(Exception e) {
		logger.error("", e);
		response.sendRedirect("../error.jsp");
	}
%>