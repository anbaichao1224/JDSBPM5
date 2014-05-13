<%
//------------------------
String SYS_NAME="JDS";
String SYS_VER="DESK3.0";
//------------------------

Boolean SYS_IS_INIT=new Boolean(false);
String SYS_BASE_PATH="";			//系统基本路径(绝对路径)
String SYS_BG_COLOR="";		//系统背景颜色
String SYS_BORDER_COLOR="";	//系统边框颜色

String APP_BASE_PATH="";	//当前用户的基础路径
String APP_REAL_PATH="";	//基础路径的物理路径


SYS_IS_INIT=(Boolean)application.getAttribute("SYS_IS_INIT");
if(SYS_IS_INIT==null||SYS_IS_INIT.booleanValue()!=true){
	out.print("系统没有被初始化！");
	return;
}else{
	SYS_BASE_PATH=(String)application.getAttribute("SYS_BASE_PATH");
	SYS_BG_COLOR=(String)application.getAttribute("SYS_BG_COLOR");
	SYS_BORDER_COLOR=(String)application.getAttribute("SYS_BORDER_COLOR");
	APP_BASE_PATH=(String)application.getAttribute("APP_BASE_PATH");
	APP_REAL_PATH=(String)application.getAttribute("APP_REAL_PATH");
}

String APP_CUR_PATH="";		//当前用户的操作路径
%>