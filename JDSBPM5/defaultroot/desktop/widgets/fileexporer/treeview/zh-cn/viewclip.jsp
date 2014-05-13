<%@ page contentType="text/html;charset=gb2312" language="java"%>
<%@page import="java.util.*"%>
<%
response.addHeader("Cache-Control", "no-cache");
response.addHeader("Expires", "Thu,  01 Jan   1970 00:00:01 GMT");

String act="";
String clip=null;			//剪切板为空
Vector buffer=new Vector();	//剪切板数据
String bufferType="";
String bufferPath="";


buffer=(Vector)session.getAttribute("buffer");
if(buffer!=null&&buffer.size()==4){
	clip="clip";
	bufferType=(String)buffer.elementAt(0);
	if(bufferType!=null&&bufferType.equals("cut")){
		bufferType="剪切";
	}else{
		bufferType="复制";
	}
	bufferPath=(String)buffer.elementAt(1);
}else{
	buffer=null;
	session.setAttribute("buffer",buffer);
}

act=request.getParameter("act");
if(clip!=null&&act!=null&&act.equals("clear")){
	buffer=null;
	session.setAttribute("buffer",buffer);
	clip=null;
}
%>
<html>
<head>
<title>查看剪切板</title>
<meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=gb2312">
<!-- no cache headers -->
<META content=no-cache http-equiv=Pragma>
<META http-equiv=no-cache>
<META content=-1 http-equiv=Expires>
<META content=no-cache http-equiv=Cache-Control>
<!-- end no cache headers -->
<style>
input {font-size:12px;height:16px;}
.bt { 
	border-color: #FFFFFF #808080 #808080 #FFFFFF;
	cursor: hand;
	border-style: solid;
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px
}
</style>
</head>

<SCRIPT LANGUAGE="JavaScript">
<!--
function resize(){
	var W = 400;//screen.width;
	var H = 300;//screen.height;

	var windowW = W;
	var windowH = H;
	var windowX = -1;
	var windowY = -1;
	
	windowX = Math.ceil( (window.screen.width  - windowW) / 2 );
	windowY = Math.ceil( (window.screen.height - windowH) / 2 );

	resizeTo( Math.ceil( W )       , Math.ceil( H ) );
	moveTo  ( Math.ceil( windowX ) , Math.ceil( windowY ) );
}
//-->
</SCRIPT>

<body bgcolor="#F1F1F1" onload="resize()">
<p>查看剪贴板</p>
<%if(clip!=null){%>
	<span style="font:9 pt">操作方式：<font color=#0000ff><%=bufferType%></font><br>
	<br>
	<%
	String folders[]=(String[])buffer.elementAt(2);
	String files[]=(String[])buffer.elementAt(3);
	%>
	<font color=#0000ff>选中的目录：</font><br>
	<%
	if(folders!=null){
		for(int i=0;i<folders.length;i++){
			out.println(bufferPath+folders[i]+"<br>");
		}
	}
	%>
	<br>
	<font color=#0000ff>选中的文件：</font><br>
	<%
	if(files!=null){
		for(int i=0;i<files.length;i++){
			out.println(bufferPath+files[i]+"<br>");
		}
	}
	%>
	</span><br>
	<br>
	<span style="font:9 pt"> 
	<input type="button" name="submit" value="清空剪贴板" onclick="clearit();" class="bt">
	<input type="button" name="submit2" value="关闭" onClick="window.close();" class="bt">
	<script language=javascript>
	function clearit()
	{
	 location.href='<%=request.getServletPath()%>?act=clear';
	}
	</script>
	</span>
<%}else{%>
	<span style="font:9 pt"> 
	<script language=javascript>
	window.opener.refresh();
	</script>
	<br>
	<br>
	<p align=center>无数据 [<a href="javascript:window.close();">关闭</a>]</p>
	</span>
<%}%>
</body>
</html>