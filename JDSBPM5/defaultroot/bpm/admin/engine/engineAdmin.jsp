<%
/**
 *    $RCSfile: engineAdmin.jsp,v $
 *    $Author: liyang $
 *    $Revision: 1.2 $
 *    $Date: 2011/08/03 09:39:21 $
 */
%>

<%@ page contentType="text/html;charset=gb2312"%>
<%@ include file="../include/jsp/global.jsp" %>
<%@ page import="net.itjds.bpm.engine.WorkflowServer" %>

<%
	String currentStatus = WorkflowServer.status();
	String contextpath = request.getContextPath()+"/";
%>

<HTML>
<HEAD>
<TITLE> BPM Engine Admin </TITLE>
<meta http-equiv="refresh" content="<%=REFRESHINTERVAL %>">
<link href="<%= contextpath%>bpm/admin/include/css/style.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.style1 {font-size: 16px}
-->
</style>
</HEAD>

<BODY   LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0> 
<TABLE width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF"> 
  <tr> 
    <td height="8" bgcolor="E3E8F8"> </td> 
  </tr> 
  <tr> 
    <td height="500" align="center" valign="top"> <TABLE width="100%" height="184" border=0 align=center cellspacing="3">
      <tr height="21" valign="bottom">
        <td width="500" height="17" class="lin3"><font color="FB4303">■ 当前位置 》系统管理 》流程服务管理</font></td>
      </tr>
      
      <tr>
        <td height="86" align="left" valign="top"> <br>&nbsp;&nbsp;服务器当前状态: <%=currentStatus %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <%
				if("Running".equals(currentStatus)) {
		  %>
          <input type="button" name="stop" onClick="window.location='engineAdmin_do.jsp?action=stop'" value="停止" class="inputbutton">
          &nbsp;&nbsp;
          <input type="button" name="restart" onClick="window.location='engineAdmin_do.jsp?action=restart'" value="重新启动" class="inputbutton">
          <%
				}
				else if("Stopped".equals(currentStatus)) {
			%>
          <input type="button" name="start" onClick="window.location='engineAdmin_do.jsp?action=start'" value="启动" class="inputbutton">
          <%
				}
			%></td>
      </tr> 
</table> 
</td> </tr>
</table>
</BODY>
</HTML>
