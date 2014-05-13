<%@ page contentType="text/html;charset=gb2312" language="java"%>
<%@include file="../shared/inc/App_Variable.jsp"%>
<%
//取得当前路径（相对于APP_ROOT_PATH）
try{
	APP_CUR_PATH = request.getParameter("CurPath");
}catch(Exception e){
	APP_CUR_PATH = "";
}
if(APP_CUR_PATH!=null){
	APP_CUR_PATH=APP_CUR_PATH.trim();
}else{
	APP_CUR_PATH = "";
}
APP_CUR_PATH = new String(APP_CUR_PATH.getBytes("ISO8859_1"),"GBK");
%>
<html>
<head>
<title>文件上传</title>
<meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=gb2312">

<!-- no cache headers -->
<META content=no-cache http-equiv=Pragma>
<META http-equiv=no-cache>
<META content=-1 http-equiv=Expires>
<META content=no-cache http-equiv=Cache-Control>
<!-- end no cache headers -->

<LINK REL="stylesheet" TYPE="text/css" HREF="<%=SYS_BASE_PATH%>treeview/shared/css/ie4.css" />
<LINK REL="stylesheet" TYPE="text/css" HREF="<%=SYS_BASE_PATH%>treeview/shared/css/ie5.css" />
<LINK REL="stylesheet" TYPE="text/css" HREF="<%=SYS_BASE_PATH%>treeview/shared/css/ie.css" />
<style>
BODY
{
	font-family:verdana,arial,helvetica;
	background-color:buttonface;
	margin:0;
	border:0;
}
input {height:17px;font-size:12px}
.tx1 { height: 18px; font-size: 9pt; border: 1px solid; border-color: black black #000000; color: #0000FF}
-->
</style>
</head>
<SCRIPT LANGUAGE="JavaScript">
<!--
//鼠标移动到图片上
function over(the,imgsrc){
	the.className="img_over";
	the.src='toolbar/images/'+imgsrc+'_over.gif';
}
//鼠标移出图片上
function out(the,imgsrc){
	the.className="img";
	the.src='toolbar/images/'+imgsrc+'.gif';
}
//鼠标在图片上按下
function down(the,imgsrc){
	the.className="img_down";
	//the.src='toolbar/images/'+imgsrc+'_down.gif';
}

function check_form(){
	var obj=document.forms[0];
	var error=true;
	if(typeof(obj)=="object"){
		first_file_index=-1;
		for(i=0;i<obj.length;i++){
			if(obj.elements[i].type=="file"){
				if(first_file_index==-1) first_file_index=i;
				if(obj.elements[i].value!="") {
					return;
				}
			}
		}
		alert("请选择要上传的文件");
		if(typeof(obj.elements[first_file_index])=="object"){
			obj.elements[first_file_index].focus();
		}
	}else{
		alert("JavaScript ERROR!!\n or This page is ERROR!");
	}
	if(error) return false;
}
//-->
</SCRIPT>
<body scroll="no">
<table align=center WIDTH="100%" CELLPADDING="0" CELLSPACING="0" BORDER="1" bordercolor=buttonface bordercolorlight="#808080" bordercolordark="#FFFFFF">
  <TR height=22>
	<TD>
	<!--# ToolBar the First Line Start-->
	<TABLE CELLPADDING="0" CELLSPACING="0" BORDER="0">
	  <TR>
		<TD width=2></TD>
		<TD width=3><img src="toolbar/images/toolbar.gif"></TD>
		<TD width=4></TD>
		<TD width=4><nobr><%=SYS_NAME%> <%=SYS_VER%> </TD>
		<TD width=6 align=center><img src="toolbar/images/tiao.gif"></TD>		
	  </TR>
	</TABLE>
	<!--# ToolBar the First Line End-->
	</TD>
  </TR>
 <TR height=22>
 <TR>
	<TD>

	<TABLE CELLPADDING="0" CELLSPACING="0" BORDER="0" width="100%">
	  <TR valign="middle" height=22>
		<TD width=2></TD>
		<TD width=3><img src="toolbar/images/toolbar.gif"></TD>
		<TD width=22><img src="toolbar/images/refresh.gif" class="img" onmousedown="down(this,'refresh');" onmouseout="out(this,'refresh');" onmouseover="over(this,'refresh');" onmouseup="over(this,'refresh');" border=1 onclick="window.location=window.location" alt="刷新"></TD>
		<TD width=6 align=center><img src="toolbar/images/tiao.gif"></TD>
		<TD width=4></TD>
		<TD width=46><nobr>上传到:</TD>
		<TD width=2></TD>
		<TD>
		  <div style="border:2 inset white;width:100%; height:22px;background-color:#FFFFFF">
		  <TABLE CELLPADDING="0" CELLSPACING="0" BORDER="0" width="100%" height="100%">
		  <TR>
		  	<TD width=1></TD>
			<TD width=18><img src="toolbar/images/ie.gif"></TD>
		  	<TD id=URL valign="bottom"><input type="text" value="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=APP_BASE_PATH+APP_CUR_PATH%>" style="border:0;width:400px;height:16px;font-size:9pt"></TD>
		  </TR>
		  </TABLE>
		  </div>
		</TD>
		<TD width=1></TD>
	  </TR>
	</TABLE>

	</TD>
  </TR>
<tr>
 <td bgcolor="#F1F0F1" height=342 valign="top">
  <form method='post' action='upload.jsp' enctype='multipart/form-data' onsubmit="return check_form();">
	  <input type="hidden" name="filepath" class="tx" style="width:350" value="<%=APP_BASE_PATH+APP_CUR_PATH%>" readonly><br>
	  <p>
		<%for(int i=0;i<10;i++){%>
		&nbsp;&nbsp;File<%=i%>: <input type='file' name='file<%=i%>' class=tx1 style="width:400"><br>
		<%}%>
		<br>
		&nbsp;&nbsp;数据总量限于1M以内<br>
		&nbsp;&nbsp;<input type='submit' name='submit' value=' 上 传 ' class=bt>
		&nbsp;<input type='reset' value=' 重 置 ' class=bt>
	  </p>
  </form>
  <hr size=2 width="100%">
  <div align=center>
    &copy; 2001-2003 COPY BY <a href="mailto:Yoinn@163.com">YOINN</a> ALL RIGHTS RESERVE<br>
	HOMEPAGE: <a href="http://www.3may.net/zl/" target="_blank">http://www.3may.net/zl/</a>
	&nbsp;&nbsp;EMAIL: <a href="mailto:Yoinn@21cn.com">@21cn</a> or <a href="mailto:Yoinn@163.com">@163</a>
  </div>
 </td>
</tr>
</table>
</body>
</html>