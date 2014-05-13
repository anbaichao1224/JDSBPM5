<%@ page contentType="text/html;charset=gb2312" %>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="com.opensymphony.xwork2.ActionInvocation" %>
<%
	String path = request.getContextPath();
	String filename=request.getParameter("filename");
   
%>
<HTML>
<HEAD>
<meta http-equiv="content-type" content="text/html;charset=gb2312">
<SCRIPT LANGUAGE="JavaScript" src="document.js"></SCRIPT>


<TITLE>正文编辑</TITLE>
</HEAD>

<BODY onLoad='javascript:TANGER_OCX_OpenDoc("<%=filename %>");'onbeforeunload='javascript:winClose()' bgcolor="#CCCCCC">
<center>
<FORM id="myForm" METHOD="POST" ENCTYPE="multipart/form-data" ACTION="uploadedit.jsp">
<style>
BODY {
	MARGIN: 5px 5px 0px; BACKGROUND-COLOR: #CCCCCC
}
button.op{
width:90px;
background-color:#9DC2DB;
border:1px #EEEEEE solid;
cursor: hand;
}
button.op1{
background-color:#CCCCCC;
border:1px #EEEEEE solid;
cursor: hand;
}
.opinfo{
color:red;
}
</style>
<TABLE BORDER=0 width = 500 bordercolor="#999999" >	
	<tr >
		<td align="center"><INPUT type=BUTTON class="op1" VALUE="保存文档" onclick="TANGER_OCX_SaveEditToServer();"></td>
		<td align="center"><INPUT type=BUTTON VALUE="显示痕迹" onclick="TANGER_OCX_ShowRevisions(true);"></td>
		<td align="center"><INPUT type=BUTTON VALUE="隐藏痕迹" onclick="TANGER_OCX_ShowRevisions(false);"></td>
		<td align="center"><INPUT type=BUTTON VALUE="手写批注" onclick="DoHandDraw();"></td>
		<%--<td align="center"><INPUT type=BUTTON VALUE="手写签名" onclick="DoHandSign();"></td>--%>
		<td align="center"><INPUT type=BUTTON VALUE="打印文件" onclick="TANGER_OCX_PrintDoc();"></td>
		<td align="center"><INPUT type=BUTTON VALUE="关闭文件" onclick="self.close()"></td>
		<%--<td >附件：<input type="file"  size=20 name="attach1"></td>DoHandSign()
	--%></tr>
	<input type="hidden" id="filename" name="filename"  value="<%=filename%>">
	<input type="hidden" id="username" name="username"  value="1">
	<input type="hidden" id="activityInstId" name="activityInstId"  value="2">
	<input type="hidden" id="processInstId" name="processInstId"  value="3">
</TABLE>

<table width=100% height=700 border=1 cellpadding=0 cellspacing=0 style="border:1px #9dc2db solid">

<tr width=100%>
<td width=100%>	
<script type="text/javascript">
	TANGER_OCX_Load();
	
	</script>
	<script language="JScript" for=TANGER_OCX event="OnDocumentClosed()">
		
		TANGER_OCX_OnDocumentClosed();
	</script>
	<script language="JScript" for=TANGER_OCX event="OnDocumentOpened(TANGER_OCX_str,TANGER_OCX_obj)">
		TANGER_OCX_OnDocumentOpened(TANGER_OCX_str,TANGER_OCX_obj);
	</script>
</td>
</tr></table>	
</FORM>
</center>
</BODY>
</HTML>