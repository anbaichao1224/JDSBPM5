<%
//------------------------
String SYS_NAME="JDS";
String SYS_VER="DESK3.0";
//------------------------

Boolean SYS_IS_INIT=new Boolean(false);
String SYS_BASE_PATH="";			//ϵͳ����·��(����·��)
String SYS_BG_COLOR="";		//ϵͳ������ɫ
String SYS_BORDER_COLOR="";	//ϵͳ�߿���ɫ

String APP_BASE_PATH="";	//��ǰ�û��Ļ���·��
String APP_REAL_PATH="";	//����·��������·��


SYS_IS_INIT=(Boolean)application.getAttribute("SYS_IS_INIT");
if(SYS_IS_INIT==null||SYS_IS_INIT.booleanValue()!=true){
	out.print("ϵͳû�б���ʼ����");
	return;
}else{
	SYS_BASE_PATH=(String)application.getAttribute("SYS_BASE_PATH");
	SYS_BG_COLOR=(String)application.getAttribute("SYS_BG_COLOR");
	SYS_BORDER_COLOR=(String)application.getAttribute("SYS_BORDER_COLOR");
	APP_BASE_PATH=(String)application.getAttribute("APP_BASE_PATH");
	APP_REAL_PATH=(String)application.getAttribute("APP_REAL_PATH");
}

String APP_CUR_PATH="";		//��ǰ�û��Ĳ���·��
%>