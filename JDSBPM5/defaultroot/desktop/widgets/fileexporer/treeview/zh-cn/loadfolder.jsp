<?xml version="1.0" encoding="GB2312"?>
<%@ page contentType="text/html;charset=gb2312" language="java"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="java.io.*,java.util.*,webedit.*"%>
<%@include file="../shared/inc/App_Variable.jsp"%>
<%
//清除缓存
response.addHeader("Cache-Control", "no-cache");
response.addHeader("Expires", "Thu,  01 Jan   1970 00:00:01 GMT");

//变量定义
String TREE_BEGIN="";
String TREE_END="</Tree>";
String TREENODE_BEGIN="";
String TREENODE_END="></TreeNode>";
TREE_BEGIN+="<Tree TreeId=\"treeviewlib\" ";
TREE_BEGIN+="NodeXmlSrc=\""+SYS_BASE_PATH+"treeview/zh-cn/loadfolder.jsp\">";

//取得当前路径（相对于APP_ROOT_PATH）
try{

	APP_CUR_PATH = request.getParameter("CurPath");
	APP_CUR_PATH = new String(APP_CUR_PATH.getBytes("ISO8859_1"),"GBK");
	
}catch(Exception e){
	APP_CUR_PATH = "";
}
if(APP_CUR_PATH!=null){
	APP_CUR_PATH=APP_CUR_PATH.trim();
}else{
	APP_CUR_PATH="";
}

String BootPath="";
BootPath+=APP_BASE_PATH;

//判断是否是根路径
String sroot="";
boolean root=false;
sroot = request.getParameter("root");
if(sroot!=null&&sroot.equals("1")){
	root=true;
}

if(root){
	out.println(TREE_BEGIN);
	TREENODE_BEGIN ="<TreeNode NodeId=\"treeviewlib_\" Title=\""+BootPath+"\" ";
	TREENODE_BEGIN+="Href=\""+SYS_BASE_PATH+"treeview/zh-cn/loadfile.jsp\"";
	TREENODE_BEGIN+=" NodeXmlSrc=\""+SYS_BASE_PATH+"treeview/zh-cn/loadfolder.jsp\"";
	out.println(TREENODE_BEGIN+TREENODE_END);
	out.println(TREE_END);
	return;
}

String CurPath=APP_REAL_PATH+APP_BASE_PATH+APP_CUR_PATH;

//定义
String folderName="";
boolean existChildFolder=false;
java.util.Date date=new java.util.Date();
long folderCount=0;
//实例化
Folder folder=new Folder();
folder.setCurPath(CurPath);
folder.init();
folderCount=folder.getFolderCount();
if(folderCount<1){
	return;
}

out.println(TREE_BEGIN);
int i=0;
for(i=0;i<folderCount;i++){
	folderName=folder.getName(i);
	existChildFolder=folder.existChildFolder(i);
	//date=folder.lastModified(i);
	TREENODE_BEGIN ="<TreeNode NodeId=\"treeviewlib_\" Title=\""+folderName+"\" ";
	TREENODE_BEGIN+="Href=\""+SYS_BASE_PATH+"treeview/zh-cn/loadfile.jsp?CurPath="+APP_CUR_PATH+folderName+"/\"";
	if(existChildFolder){
		TREENODE_BEGIN+=" NodeXmlSrc=\""+SYS_BASE_PATH+"treeview/zh-cn/loadfolder.jsp?CurPath="+APP_CUR_PATH+folderName+"/\"";
	}
	out.println(TREENODE_BEGIN+TREENODE_END);
}
out.println(TREE_END);
%><%!

%>