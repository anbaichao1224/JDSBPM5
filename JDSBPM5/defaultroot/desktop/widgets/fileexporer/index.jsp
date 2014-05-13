<%@ page contentType="text/html;charset=gb2312" language="java"%>
<%
Boolean SYS_IS_INIT=new Boolean(false);
String SYS_BASE_PATH="";	//系统基本路径(绝对路径)
String SYS_BG_COLOR="";		//系统背景颜色
String SYS_BORDER_COLOR="";	//系统边框颜色

String APP_BASE_PATH="";	//当前用户的基础路径
String APP_REAL_PATH="";	//基础路径的物理路径

String SessionID=session.getId();
SessionID=SessionID.toUpperCase();
%>
<event:application_OnStart>
<%
	String path=request.getServletPath();	
	SYS_BASE_PATH=path.substring(0,path.lastIndexOf("/")+1);
	SYS_BG_COLOR="#6699CC";
	SYS_BORDER_COLOR="#6699CC";
	APP_BASE_PATH="/";
	APP_REAL_PATH=request.getRealPath(APP_BASE_PATH);
	SYS_IS_INIT=new Boolean(true);
	application.setAttribute("SYS_BASE_PATH",SYS_BASE_PATH);
	application.setAttribute("SYS_BG_COLOR",SYS_BG_COLOR);
	application.setAttribute("SYS_BORDER_COLOR",SYS_BORDER_COLOR);
	application.setAttribute("APP_BASE_PATH",APP_BASE_PATH);
	application.setAttribute("APP_REAL_PATH",APP_REAL_PATH);
	application.setAttribute("SYS_IS_INIT",SYS_IS_INIT);
%>
</event:application_OnStart>
<HTML>
<HEAD>
<TITLE>JWebEdit v1.0</TITLE>
<META content="text/html;charset=GB2312" http-equiv="Content-Type">

<!-- no cache headers -->
<META content=no-cache http-equiv=Pragma>
<META http-equiv=no-cache>
<META content=-1 http-equiv=Expires>
<META content=no-cache http-equiv=Cache-Control>
<!-- end no cache headers -->
<style>
body {
	font-family:宋体,verdana;
	margin:0;
	/*border:0;*/
	background-color:#F1F1F1;
	font-size:12px;
}
td {font-size:12px}
input {font-size:12px;}

/*================================================*/
/*  设定超连的属性 */
/*================================================*/
A  {
	font-size:12px;
	font-family:"宋体", "Verdana", "Arial";
	text-decoration : none;
	COLOR: #223344;
}
A:active
{
    font-size:12px;
	font-family:"宋体", "Verdana", "Arial";
	text-decoration : none;
	COLOR: red;
}
A:link
{
    font-size:12px;
	font-family:"宋体", "Verdana", "Arial";
	text-decoration : none;
	COLOR: #223344;
}
A:visited
{
    font-size:12px;
	font-family:"宋体", "Verdana", "Arial";
	text-decoration : none;
	COLOR: #223344;
}
A:hover
{
    font-size:12px;
	font-family:"宋体", "Verdana", "Arial";
	text-decoration : none;
	COLOR: red;
}

.bt { 
	border-color: #FFFFFF #808080 #808080 #FFFFFF;
	cursor: hand;
	border-style: solid;
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	height:17px;
}
.tx {
	font-size: 9pt;
	border-style: groove;
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
}
</style>
<SCRIPT LANGUAGE="JavaScript">

// this page should never load inside of another frame
if (top.location != self.location)
{
	top.location = self.location;
}

</SCRIPT>
</HEAD>

<SCRIPT LANGUAGE="JavaScript">
<!--
function OPEN_JWEBEDIT(){
	var winPopUp = null;
	var winName = "JWebEdit_<%=SessionID%>";
	var href="JWebEdit.jsp";
	var W = screen.width*0.9;
	var H = screen.height*0.9;
	if(W<640) W=640;
	if(H<480) H=480;

	var windowW = W;
	var windowH = H;
	var windowX = -1;
	var windowY = -1;
	
	windowX = Math.ceil( (window.screen.width  - windowW) / 2 );
	windowY = Math.ceil( (window.screen.height - windowH) / 2 );

	winPopUp = window.open(href ,winName,"toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=0,resizable=1");
	winPopUp.resizeTo( Math.ceil( W )       , Math.ceil( H ) );
	winPopUp.moveTo  ( Math.ceil( windowX ) , Math.ceil( windowY ) );
}
//-->
</SCRIPT>
<body scroll=no>

<TABLE width="100%" height="100%" align=center>
<TR height="20%">
	<TD valign="bottom">
	<TABLE align=center border=1 bordercolorlight="#808080" bordercolordark="#FFFFFF" CELLPADDING="5" CELLSPACING="0" width="466">
	<TR>
		<TD>
		JWebEdit v1.0b Build030110<br>
		<a href="http://www.3may.net/zl"><img src="treeview/shared/graphics/Yoinn_logo.gif" border=0></a>
		</TD>
	</TR>
	</TABLE>
	</TD>
</TR>
<TR height="30">
	<TD align=center>
	点击下面按钮进入<br>
	如无法进入，请<a href="javascript:window.location=window.location;">刷新</a>本页<br>
	<br>
	<input type="button" name=open value="JWebEdit v1.0b Build 030110" onclick="OPEN_JWEBEDIT()" class=bt>
	</TD>
</TR>
<TR height="25%">
	<TD>
	<TABLE align=center border=1 bordercolorlight="#808080" bordercolordark="#FFFFFF" CELLPADDING="5" CELLSPACING="0" width="466">
	<TR>
		<TD>
		版 权<br>
		-----------------------------------------------<br>
		本程序版权归 Yoinn 所有，在不对版权进行修改的情况下，可以任意传播本程序。<br>
		不得用于商业目的。<br>
		<br>
		测试环境<br>
		-----------------------------------------------<br>
		Server: Win2000+J2SDK1.4.1+Resin2.1.6<br>
		Client: IE 6.0.2600.0000<br>
		</TD>
	</TR>
	</TABLE>	
	</TD>
</TR>
<TR height="25%">
	<TD align=center>
	<hr width="80%" size=1 color="#223344">
	&copy; 2002-2003 Copy By <a href="mailto:Yoinn@163.com">Yoinn</a> All Rights Reserved.<br>
	HomePage: <a href="http://www.3may.net/zl/2003/JWebEdit/">http://www.3may.net/zl/2003/JWebEdit</a>
	</TD>
</TR>
</TABLE>

</body>

</HTML>