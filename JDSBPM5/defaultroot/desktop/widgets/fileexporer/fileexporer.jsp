<%@ page contentType="text/html;charset=gb2312" language="java"%>
<%@ page import="net.itjds.common.CommonConfig" %>
<jsp:directive.page import="java.io.File"/>

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
	//APP_REAL_PATH=request.getRealPath(APP_BASE_PATH)+File.separator+"downloadfolder";
	APP_REAL_PATH = CommonConfig.getValue("bpm.filePath")+File.separator+"downloadfolder";
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
<TITLE>JDS 文件管理器</TITLE>
<META content="text/html;charset=GB2312" http-equiv="Content-Type">

<!-- no cache headers -->
<META content=no-cache http-equiv=Pragma>
<META http-equiv=no-cache>
<META content=-1 http-equiv=Expires>
<META content=no-cache http-equiv=Cache-Control>
<!-- end no cache headers -->

<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
<SCRIPT LANGUAGE="JavaScript">
<!--

//-->
</SCRIPT>

<style>
FRAMESET
{
	font-family:verdana,arial,helvetica;
	background-color:buttonface;
	margin:0;
	border:0;
}
</style>

<SCRIPT LANGUAGE="JavaScript">

// this page should never load inside of another frame

function resize(){
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

	resizeTo( Math.ceil( W )       , Math.ceil( H ) );
	moveTo  ( Math.ceil( windowX ) , Math.ceil( windowY ) );
}
//window.onload=resize();

function InitSync()
{
	if("object"==typeof(top.deeptree)&&"unknown"==typeof(top.deeptree.Sync))
	{
		top.deeptree.Sync();
	}
}
</SCRIPT>
</HEAD>

<FRAMESET onload="InitSync();" rows="0,*" border="1" FRAMESPACING="0" TOPMARGIN="0" LEFTMARGIN="0" MARGINHEIGHT="0" MARGINWIDTH="0">
   <FRAME name="fraToolbar"  scrolling="no" border="0" frameborder="no" noresize TOPMARGIN="0" LEFTMARGIN="0" MARGINHEIGHT="0" MARGINWIDTH="0"></FRAME>
    <FRAMESET name="fstMain" cols="180,*" border="1" frameborder="1" FRAMESPACING="6" TOPMARGIN="0" LEFTMARGIN="0" MARGINHEIGHT="0" MARGINWIDTH="0">
      <FRAME name="fraLeftFrame" src="<%=SYS_BASE_PATH%>treeview/zh-cn/jsp/leftframe.jsp" TOPMARGIN="0" LEFTMARGIN="0" MARGINHEIGHT="0" MARGINWIDTH="0" FRAMEBORDER="1" BORDER="1"></FRAME>
      <FRAME name="fraRightFrame" src="<%=SYS_BASE_PATH%>treeview/zh-cn/jsp/rightframe.jsp" FRAMEBORDER="no" BORDER="0" BORDERCOLOR="<%=SYS_BORDER_COLOR%>"></FRAME>
    </FRAMESET>
   
</HTML>