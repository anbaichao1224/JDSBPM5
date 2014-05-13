<?xml version="1.0" encoding="GB2312"?>
<%@ page contentType="text/html;charset=gb2312" language="java"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="java.io.*,java.util.*,webedit.*"%>
<%@include file="../shared/inc/App_Variable.jsp"%>
<%
response.addHeader("Cache-Control", "no-cache");
response.addHeader("Expires", "Thu,  01 Jan   1970 00:00:01 GMT");

String TREE_BEGIN="";
String TREE_END="</Tree>";
String TREENODE_BEGIN="";
String TREENODE_END="></TreeNode>";

//取得当前路径（相对于APP_BASE_PATH）
APP_CUR_PATH = request.getParameter("CurPath");

if(APP_CUR_PATH!=null){
	APP_CUR_PATH=APP_CUR_PATH.trim();
}else{
	APP_CUR_PATH = "";
}
APP_CUR_PATH = new String(APP_CUR_PATH.getBytes("ISO8859_1"),"GBK");
//当前物理路径
//APP_REAL_PATH = CommonConfig.getValue("bpm.filePath")+File.separator+"downloadfolder";;
String CurPath=APP_REAL_PATH+APP_BASE_PATH+APP_CUR_PATH;

//得到动作类型（cut、copy、paste......）
String act=request.getParameter("act");

String actMsg="";

String clip=null;			//剪切板为空
Vector buffer=new Vector();	//剪切板数据
buffer=(Vector)session.getAttribute("buffer");
if(act!=null&&act.trim().length()>1){
	String bufferFolders[]=request.getParameterValues("folder");
	String bufferFiles[]=request.getParameterValues("file");
	if(act.equals("cut")||act.equals("copy")){
		//剪切和复制
		buffer=new Vector();
		buffer.addElement(act);
		buffer.addElement(APP_BASE_PATH+APP_CUR_PATH);
		buffer.addElement(bufferFolders);
		buffer.addElement(bufferFiles);
		session.setAttribute("buffer",buffer);
	}else if(act.equals("paste")){
		//粘贴
		if(buffer!=null&&buffer.size()==4){
			JWebEdit jwebedit=new JWebEdit();
			String from = APP_REAL_PATH+(String)buffer.elementAt(1);
			
			String to   = CurPath;
			if(jwebedit.checkPaste(buffer,from,to)){
				//粘贴
				jwebedit.paste(buffer,from,to);
				//清空剪切板数据
				buffer=new Vector();
				session.setAttribute("buffer",buffer);
			}else{
				actMsg="Error!!!不能粘贴到当前目录下！\\n可能的原因：\\n1.源目录与目标目录相同！\\n2.目标目录是源目录的子目录！";
			}	
		}
	}else if(act.equals("mkdir")){
		//创建目录
		String to=request.getParameter("to");
		to = new String(to.getBytes("ISO8859_1"),"GBK");
		if(to!=null&&to.trim().length()>0){
			to=CurPath+to.trim();
			JWebEdit jwebedit=new JWebEdit();
			if(!jwebedit.mkdir(to)){
				actMsg="Error!!!不能粘贴到当前目录下！\\n可能的原因：\\n1.源目录与目标目录相同！\\n2.目标目录是源目录的子目录！";
			}
		}
	}else if(act.equals("delete")){
		//删除文件或目录
		if(bufferFolders!=null||bufferFiles!=null){
			JWebEdit jwebedit=new JWebEdit();
			jwebedit.del(CurPath,bufferFolders,bufferFiles);
		}
	}else if(act.equals("rename")){
		//文件或目录改名
		String from=request.getParameter("from");
		
		
		String to=request.getParameter("to");
		from = new String(from.getBytes("ISO8859_1"),"GBK");
		to = new String(to.getBytes("ISO8859_1"),"GBK");
		if(from!=null&&from.trim().length()>0&&to!=null&&to.trim().length()>0){
			from=CurPath+from.trim();
			to=CurPath+to.trim();
			JWebEdit jwebedit=new JWebEdit();
			if(!jwebedit.rename(from,to)){
				actMsg="Error!!!！\\n：\\n1.！\\n2.！";
			}
		}
	}
}
if(buffer!=null&&buffer.size()==4) clip="clip";

//目录定义
String FolderName="";
java.util.Date fdate=new java.util.Date();
long FolderCount=0;
//实例化
Folder folder=new Folder();
folder.setCurPath(CurPath);
folder.init();
FolderCount=folder.getFolderCount();

//文件定义
String FileName="";
String FileType="";
long FileSize=0;
java.util.Date date=new java.util.Date();
long FileCount=0;
//实例化
FileList filelist=new FileList();
filelist.setCurPath(CurPath);
filelist.init();
FileCount=filelist.getFileCount();

if(actMsg!=null&&actMsg.trim().length()>1){
	out.println("<SCRIPT>alert('"+actMsg+"');</SCRIPT>");
}%>
<html>
<head>
<title>LIST</title>
<meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=gb2312">
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
	border:0;
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
</head>

<SCRIPT LANGUAGE="JavaScript">
<!--
var SERVER_NAME="<%=request.getServerName()%>"
var SERVER_PORT="<%=request.getServerPort()%>"
var BASE_PATH="/downloadfolder<%=APP_BASE_PATH%><%=APP_CUR_PATH%>"
var BSAE_URL="<%=request.getServletPath()%>?CurPath=<%=APP_CUR_PATH%>"

function paste(){
	<%if(clip==null){%>
	 alert('剪贴板中没有数据!');
	<%}else{%>
	var filepath="<%=(String)buffer.elementAt(1)%>";
	if( BASE_PATH==filepath ){
		alert("Error!!!不能粘贴到当前目录下！\n可能的原因：\n1.源目录与目标目录相同！\n2.目标目录是源目录的子目录！");
		return;
	}
	if(!confirm('真要将文件粘贴到此目录吗?'))return;
	var obj=document.forms[0];
	obj.action=BSAE_URL+'&act=paste';
	obj.submit();
	<%}%>
}

function goParent(){
	var parentpath="<%=getCurFolderName(APP_CUR_PATH)%>";
	window.location="<%=request.getServletPath()%>?CurPath="+parentpath;
}
//浏览文件
function openit(str){
url = "download.jsp?filename="+BASE_PATH+str;
//alert(url);
window.open(url,'_blank');
//window.open(BASE_PATH+str,'_blank');
}
//打开目录
function openFolder(str)
{
	var url=BSAE_URL+str;
	window.location=url;
}

//设置地址（ToolBar）
function setURL(){
	window.top.toolbar=top.frames.fraToolbar;	
	if(typeof(eval(window.top.toolbar.URL))=="object"){
		URL=window.top.toolbar.URL;
		URL.innerHTML="http://"+SERVER_NAME+":"+SERVER_PORT+BASE_PATH;
	}
}
setURL();

//全选
function selectALL(){
	isSel=0;
	var obj=document.forms[0];
	for(i=0;i<obj.length;i++){
		if(obj.elements[i].type=="checkbox"){
			obj.elements[i].checked=true;
		}
	}
}
//反选
function selectALL_(){
	isSel=0;
	var obj=document.forms[0];
	for(i=0;i<obj.length;i++){
		if(obj.elements[i].type=="checkbox"){
			if(obj.elements[i].checked==true){
				obj.elements[i].checked=false;
			}else{
				obj.elements[i].checked=true;
			}
		}
	}
}
//取消选择
function selectCancel(){
	isSel=0;
	var obj=document.forms[0];
	for(i=0;i<obj.length;i++){
		if(obj.elements[i].type=="checkbox"){
			obj.elements[i].checked=false;
		}
	}
}
//检测是否有目录或文件被选择
function chkSelect(){
	isSel=false;
	var obj=document.forms[0];
	for(i=0;i<obj.length;i++){
		if(obj.elements[i].type=="checkbox"&&obj.elements[i].checked==true){
			isSel=true;
			break;
		}
	}
	if(!isSel){alert('请先选择文件或目录。');}
	return isSel;
}

//传递一(act)参数
function doit(act)
{
	var obj=document.forms[0];
	if(chkSelect()){
		obj.action=BSAE_URL+'&act='+act;
		obj.submit();
	}
}

//刷新本页
function refresh(){window.location=BSAE_URL;}

//判断名为str的目录或文件是否存在
function doesit(str){
	var obj=document.forms[0];
	for(i=0;i<obj.length;i++){
		if(obj.elements[i].type=="checkbox"){
			if(obj.elements[i].value==str) {
				return true;
			}
		}
	}
	return false;
}

//创建目录
function mkdir()
{
	str=prompt('新建目录:','');
	if(!str)return;
	var obj=document.forms[0];
	if(doesit(str)){
		alert("目录已经存在！");
		return;
	}
	obj.action=BSAE_URL+'&act=mkdir&to='+str;
	obj.submit();
}

//改名
function rename(oldname)
{
	str=prompt('将 '+oldname+' 改名为:',oldname);
	if(!str||str==oldname)return;
	var obj=document.forms[0];
	if(doesit(str)){
		alert("无法重命名：指定的目录/文件存在！");
		return;
	}

	obj.action=BSAE_URL+'&act=rename&from='+oldname+'&to='+str;
	obj.submit();
}

//删除目录或文件
function del(name,type){
	var obj=document.forms[0];
	if(type==0){
		if(confirm("真的要删除目录：<"+name+">  ")==false) return;
		obj.action=BSAE_URL+'&act=delete&folder='+name;
	}else{
		if(confirm("真的要删除文件："+name+"  ")==false) return;
		obj.action=BSAE_URL+'&act=delete&file='+name;
	}
	obj.submit();
}

function winPop(w,h,href,name){
	var winPopUp = null;
	var W = w;
	var H = h;

	var windowW = W;
	var windowH = H;
	var windowX = -1;
	var windowY = -1;
	
	windowX = Math.ceil( (window.screen.width  - windowW) / 2 );
	windowY = Math.ceil( (window.screen.height - windowH) / 2 );
	winPopUp = window.open(href ,name,"toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=0,resizable=0");
	winPopUp.resizeTo( Math.ceil( W )       , Math.ceil( H ) );
	winPopUp.moveTo  ( Math.ceil( windowX ) , Math.ceil( windowY ) );
}

//上传文件
function upfile(path)
{ 
	var href="upfile.jsp?CurPath="+path;
	var W = 512;
	var H = 416;
	winPop(W,H,href,"UpFile");
}

//编辑文件
function edit(filename)
{ 
	var href="editfile.jsp?filepath=<%=APP_BASE_PATH%><%=APP_CUR_PATH%>"+filename;
	var W = 640;
	var H = 480;
	winPop(W,H,href,"EditFile");
}
//编辑文件
function editfile()
{ 
	var obj=document.forms[0];
	for(i=0;i<obj.length;i++){
		if(obj.elements[i].type=="checkbox"&&obj.elements[i].name=="file"&&obj.elements[i].checked==true){
			edit(obj.elements[i].value);
			return;
		}
	}
	alert('请先选择文件。');	
}
//关于
function about(){
	window.showModalDialog("images/about.htm","","dialogWidth:280px;dialogHeight:150px;scroll:no;status:no;help:no");
}
//-->
</SCRIPT>

<body onload="">
<TABLE width="100%" cellspacing="1" cellpadding="1" border=1 bordercolor="#F1F1F1">
 <form name="theForm" action="" method="post">
 <input type="hidden" name="form" /> <input type="hidden" name="to" /> <input type="hidden" name="rename" /><input type="hidden" name="file" /> <input type="hidden" name="folder" /> 
 <TR height="30">
	<TD colspan=9>
	<TABLE border=0 cellspacing="0" cellpadding="0" width="100%">
	  <TR>
		<TD align="left">
		 <input type="button" name="ref" value=" 刷新 " class=bt onclick="refresh()">
		</TD>
		<TD align="right">
		 <%if(clip!=null){%>
		 <SCRIPT LANGUAGE="JavaScript">
		 <!--
		 function ViewClip()
		 {
			window.open('viewclip.jsp' ,"viewclip","width=400,height=300,toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=1,resizable=0");
		 }
		 //-->
		 </SCRIPT>
		 <input type="button" name="viewclip" value="查看剪切板" class=bt onclick="ViewClip()">
		 <%}%>
	 <!--	 <input type="button" name="mk" value="创建目录" class=bt onclick="mkdir()">
		 <input type="button" name="sel1" value="全选" class=bt onclick="selectALL()">
		 <input type="button" name="sel2" value="反选" class=bt onclick="selectALL_()">
		 <input type="button" name="cancel" value="取消" class=bt onclick="selectCancel()">
		 <input type="button" name="up" value="上传" class=bt onclick="upfile('<%=APP_CUR_PATH%>')">
		  //-->
		</TD>
	  </TR>
	</TABLE>	
	</TD>
 </TR>
 <TR height="3" bgcolor="#223344">
	<TD colspan=9> </TD>
 </TR>
 <TR height="5">
	<TD width=1></TD>
	<TD width=1></TD>
	<TD width="99%"></TD>
	<TD width="60"></TD>
	<TD width="60"></TD>
	<TD width="100"></TD>
	<TD width="30"></TD>
	<TD width="30"></TD>
	<TD width="30"></TD>
 </TR>
 <TR height="20">
    <TD colspan=1><img src="../shared/graphics/up.gif" alt="↑向上" border=0 onclick="goParent()" style="cursor: hand;" onmouseover="this.src='../shared/graphics/up_over.gif'" onmouseout="this.src='../shared/graphics/up.gif'"></TD>
	<TD colspan=8 valign="bottom">当前位置：<%=(APP_BASE_PATH+APP_CUR_PATH)%></TD>
 </TR>
 <TR height="18" valign="bottom" bgcolor="buttonface" bordercolorlight="#808080" bordercolordark="#FFFFFF" style="line-height:12px">
	<TD colspan=3>&nbsp;<nobr>名称&nbsp;</TD>
	<TD width="60">&nbsp;<nobr>大小&nbsp;</TD>
	<TD width="60">&nbsp;<nobr>类型&nbsp;</TD>
	<TD width="100">&nbsp;<nobr>修改时间&nbsp;</TD>	
<!--  	<TD colspan=3>&nbsp;<nobr>操作&nbsp;</TD> -->
  </TR>
<%
if(FolderCount<1&&FileCount<1){
  %>
  <TR height="22" bgcolor="#DDDDDD">
	<TD colspan=9>&nbsp;当前共 <b>0</b> 个文件夹，<b>0</b> 个文件。</TD>
  </TR>
  <%
}else{
  //如果存在文件夹，则显示说有；
  for(int i=0;i<FolderCount;i++){
	FolderName=folder.getName(i);
	fdate=folder.lastModified(i);
  %>
  <TR>
	<TD><input type=checkbox name="folder" value="<%=FolderName%>"></TD>
	<TD><img src="../shared/graphics/filetype/folder.gif" height="13"></TD>
	<TD><nobr><a href="javascript:openFolder('<%=FolderName%>/')"><%=FolderName%></a></TD>
	<TD><nobr></TD>
	<TD>&nbsp;<nobr>文件夹&nbsp;</TD>
	<TD>&nbsp;<nobr><%=fdate.toLocaleString()%>&nbsp;</TD>
	<TD><nobr></TD>
<!-- 	<TD><nobr><a href="javascript:rename('<%=FolderName%>')"><img src="../shared/graphics/rename.gif" border=0 alt="改名"></a></TD>
	<TD><nobr><a href="javascript:del('<%=FolderName%>',0)"><img src="../shared/graphics/delete.gif" border=0 alt="删除" width=10></a></TD> -->
  </TR>
  <%}//end for
  
  //显示所有文件并计算文件大小的和
  long TotalFileSize=0;
  for(int j=0;j<FileCount;j++){
	FileName=filelist.getFileName(j);
	FileName=filelist.getFileName(j);
	FileSize=filelist.getFileSize(j);
	date=filelist.getFileLastModified(j);
	TotalFileSize+=FileSize;
  %><TR>
	<TD><input type=checkbox name="file" value="<%=FileName%>"></TD>
	<TD><img src="../shared/graphics/filetype/<%=getFileTypeImg(FileName)%>" width="16" height="16"></TD>
	<TD><nobr><a href="javascript:openit('<%=FileName%>')"><%=FileName%></a></TD>
	<TD align=right>&nbsp;<nobr><%=(FileSize+999)/1000%>KB&nbsp;</TD>
	<TD>&nbsp;<nobr><%=getFileType(FileName)%>文件&nbsp;</TD>
	<TD>&nbsp;<nobr><%=date.toLocaleString()%>&nbsp;</TD>
<!-- 	<TD><nobr><a href="javascript:edit('<%=FileName%>')"><img src="../shared/graphics/edit.gif" border=0 alt="编辑"></a></TD>
	<TD><nobr><a href="javascript:rename('<%=FileName%>')"><img src="../shared/graphics/rename.gif" border=0 alt="改名"></a></TD>
	<TD><nobr><a href="javascript:del('<%=FileName%>',1)"><img src="../shared/graphics/delete.gif" border=0 alt="删除" width=10></a></TD> -->
  </TR>
  <%}%>
<TR height="5">
	<TD colspan=9></TD>
</TR>
<TR height="22" bgcolor="#DDDDDD">
	<TD colspan=9>&nbsp;当前共 <b><%=FileCount%></b> 个文件，共 <b><%=(TotalFileSize)/1000%>.<%=(TotalFileSize%1000)/100%><%=(TotalFileSize%1000%100)/10%><%=(TotalFileSize%1000%100)%10%></b> K。</TD>
</TR>
<%}%>
</form>
</TABLE>
</body>
</html>
<%!
public String getCurFolderName(String curpath){
	String foldername="";
	if(curpath!=null&&curpath.lastIndexOf("/")>0){
		curpath=curpath.substring(0,curpath.lastIndexOf("/"));
		if(curpath.lastIndexOf("/")>0){
			foldername=curpath.substring(0,curpath.lastIndexOf("/")+1);
		}
	}
	
	return foldername;
}
public String getFileType(String filename){
	String filetype="";
	if(filename!=null&&filename.lastIndexOf(".")>-1){
		filetype=filename.substring(filename.lastIndexOf(".")+1,filename.length());
	}
	return filetype.toUpperCase();
}
public String getFileTypeImg(String filename){
	String str="00.gif";
	if(filename==null||filename.trim().length()<5){
		str="00.gif";
	}else{
		filename=filename.trim();
		filename=filename.substring(filename.length()-4,filename.length());
		filename=filename.toLowerCase();
		
		if(filename.equals(".txt")){str="txt.gif";}
		else if(filename.equals(".xml")){str="xml.gif";}
		else if(filename.equals(".xsl")||filename.equals("xslt")){str="xsl.gif";}
		else if(filename.equals(".doc")){str="doc.gif";}
		else if(filename.equals(".css")){str="css.gif";}
		else if(filename.equals(".htm")||filename.equals("html")){str="htm.gif";}
		else if(filename.equals(".gif")){str="gif.gif";}
		else if(filename.equals(".jpg")||filename.equals("jpeg")){str="jpg.gif";}
		else if(filename.equals(".psd")){str="psd.gif";}
		else if(filename.equals(".mid")){str="mid.gif";}
		else if(filename.equals(".wav")){str="wav.gif";}
		else if(filename.equals(".avi")){str="avi.gif";}
		else if(filename.equals(".rar")){str="rar.gif";}
		else if(filename.equals(".zip")){str="zip.gif";}
		else{str="00.gif";}
		filename=filename.substring(filename.length()-3,filename.length());
		if(filename.equals(".js")){str="js.gif";}
	}
	return str;
}




%>