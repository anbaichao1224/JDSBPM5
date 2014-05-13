<%
/**
 *    $RCSfile: error.jsp,v $
 *    $Author: liyang $
 *    $Revision: 1.2 $
 *    $Date: 2011/08/03 09:39:04 $
 */
%>

<%@ page contentType="text/html;charset=gb2312"%>
<%@ include file="include/jsp/global.jsp" %>

<%
    Throwable t = (Throwable) request.getAttribute("Throwable");
    String errorMsg = request.getParameter("errorMsg");
    if(errorMsg == null) {
        errorMsg = (String)request.getAttribute("errorMsg");
    }
    if(errorMsg == null) {
        errorMsg = "系统忙或发生数据库错误，请稍后重试！ ";
    }
    
    String returnURI = request.getParameter("returnURI");
    if(returnURI == null) {
        returnURI = (String)request.getAttribute("returnURI");
    }
    if(returnURI == null) {
        returnURI = "javascript:window.history.back();";
    }
	
	String returnText = request.getParameter("returnText");
    if(returnText == null) {
        returnText = (String)request.getAttribute("returnText");
    }
    if(returnText == null) {
        returnText = "返回";
    }
%>

<HTML>
<HEAD>
<TITLE> BPM Engine Admin </TITLE>
<link href="include/css/style.css" rel="stylesheet" type="text/css">
<SCRIPT LANGUAGE="JavaScript" src="include/js/jcommon.js"></SCRIPT>
<style type="text/css">
<!--
.style1 {font-size: 16px}
-->
</style>
</HEAD>

<BODY    background="images/home_background.gif" LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0> 
<%@ include file="include/jsp/top.jsp" %>
<TABLE width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF"> 
  <tr> 
    <td height="8" bgcolor="E3E8F8"> </td> 
  </tr> 
  <tr> 
    <td height="500" align="center" valign="top"> <TABLE width="100%" height="201" border=0 align=center cellspacing="3">
      <tr height="21" valign="bottom">
        <td width="500" height="17" class="lin3"><font color="FB4303">■ 当前位置 》系统错误</font></td>
      </tr>
      <tr>
        <td height="175" align="center"><span class="style1"><%=errorMsg %></span><br><br><a href="<%=returnURI %>" class="A_1"><%=returnText %></a></td>
      </tr> 
</table> 
</td> </tr>
</table>
<%@ include file="include/jsp/bottom.jsp" %>
</BODY>
</HTML>
