<%
/**
 *    $RCSfile: newProcessDefPerson.jsp,v $
 *    $Author: liyang $
 *    $Revision: 1.2 $
 *    $Date: 2011/08/03 09:39:18 $
 */
%>

<%@ page contentType="text/html;charset=gb2312"%>
<%@ include file="../include/jsp/global.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="net.itjds.bpm.engine.WorkflowServer" %>
<%@ page import="net.itjds.common.org.base.OrgManager" %>
<%@ page import="net.itjds.common.org.base.OrgManagerFactory" %>
<%@ page import="net.itjds.common.org.base.Person" %>

<%
	String contextpath = request.getContextPath()+"/";
	try {
        OrgManager orgManager = OrgManagerFactory.getInstance().getOrgManager();
		Person[] persons = orgManager.getPersons();
		Iterator appsIte = WorkflowServer.getApplications().keySet().iterator();
%>

<HTML>
<HEAD>
<TITLE> BPM Engine Admin </TITLE>
<link href="<%=contextpath %>bpm/admin/include/css/style.css" rel="stylesheet" type="text/css">
<SCRIPT LANGUAGE="JavaScript" src="<%=contextpath %>bpm/admin/include/js/jcommon.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
<!--
function _check() {
	return true;
}
//-->
</SCRIPT>
</HEAD>

<BODY   LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0> 
<form name="frm" method="post" action="newProcessDefPerson_do.jsp" onSubmit="return _check();">

<TABLE width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF"> 
  <tr> 
    <td height="8" bgcolor="E3E8F8"> </td> 
  </tr> 
  <tr> 
    <td height="500" align="center" valign="top"> <TABLE width="100%" border=0 align=center cellspacing="3">
      <tr height="21" valign="bottom">
        <td width="500" class="lin3"><font color="FB4303">■ 当前位置 》新建流程定义人员</font></td>
      </tr>
      <tr>
        <td height="8"> </td>
      </tr>
        <tr>
          <td>
            <table width="100%" border="0" align="center" cellpadding="2" cellspacing="0" class="tr">
            <tr >
              <td height=21 colspan=2 bgcolor=#E3E8F8 class=td>■<font color=#ffffff>&nbsp;</font>新建信息</td>
            </tr>
            <tr>
              <td width="100%" height="23" align="center" class="td">
                <table width="100%" border="0" cellspacing="0" cellpadding="3">
                    <tr>
                      <td width="24%" height="23" align="right">人员：</td>
                      <td width="76%" height="23">
					  	<select name="PERSON_ID" style="width: 150" class="inputtxt">
						<%
							for(int i=0; i < persons.length; i++) {
								Person person = persons[i];
						%>
							<option value="<%=person.getID() %>"><%=person.getName()%></option>
						<%
							}
						%>
						</select>
                      </td>
                    </tr>
					<tr>
                      <td width="24%" height="23" align="right">应用：</td>
                      <td width="76%" height="23">
					  	<select name="APPNAME" style="width: 150" class="inputtxt">
						<%
							while(appsIte.hasNext()) {
								String app = (String)appsIte.next();
						%>
							<option value="<%=app %>"><%=app %></option>
						<%
							}
						%>
						</select>
                      </td>
                    </tr>
					<tr>
                      <td width="24%" height="23" align="right">人员选择公式：</td>
                      <td width="76%" height="23"><textarea name="PERSON_SELECT_FORMULA" class="inputtxt" rows="7" cols="50"></textarea></td>
                    </tr>
    </table></td>
  </tr>
  </table>
  <br>
  <table width="191" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr> 
    <td height="25" align="center"> 
      <input name="save" type="submit" class="inputbutton" style="cursor:hand;" value="保 存">
	  <input name="cancle" type="button" class="inputbutton" onClick="window.location='processDefPersonList.jsp'" style="cursor:hand;" value="返 回">
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