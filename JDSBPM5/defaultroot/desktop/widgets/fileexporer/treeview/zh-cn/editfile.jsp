<%@ page contentType="text/html;charset=gb2312" language="java"%>
<%@page import="java.io.*"%>
<%@ page import="java.io.*,java.util.*,webedit.*"%>
<%@include file="../shared/inc/App_Variable.jsp"%>
<%
response.addHeader("Cache-Control", "no-cache");
response.addHeader("Expires", "Thu,  01 Jan   1970 00:00:01 GMT");

String filepath="";
String filefullpath="";
String filecontent="";

//�õ��ļ�·��
filepath = request.getParameter("filepath");
filepath = new String(filepath.getBytes("ISO8859_1"),"GBK");
if(filepath==null||filepath.trim().length()<4){
	out.println("�������ݴ���");
	return;
}else{
	filepath=filepath.trim();
}
//�õ��ļ�����������·��
filefullpath=APP_REAL_PATH+filepath;

//����ļ��Ƿ����
if(isExist(filefullpath)==false){
	out.println("<li> "+filepath);
	out.println("<li> �������ݴ����ļ������ڣ���");
	return;
}

EditFile editfile=new EditFile(filefullpath);

String act = request.getParameter("act");
if(act!=null&&act.equals("edit")){
	filecontent=(String)request.getParameter("filecontent");
	if(filecontent==null){
		out.print("<li> �������ݴ���");
		return;
	}
	editfile.setFileContent(filefullpath,filecontent);
}else{
	filecontent=editfile.getFileContent();
}
%>
<html>
<head>
<title>�ļ��༭ -> <%=filepath%></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
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
</style>
</head>

<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
<SCRIPT LANGUAGE="JavaScript">
<!--

function resize(){
	var W = 640;//screen.width*0.9;
	var H = 480;//screen.height*0.9;

	var windowW = W;
	var windowH = H;
	var windowX = -1;
	var windowY = -1;

	windowX = Math.ceil( (window.screen.width  - windowW) / 2 );
	windowY = Math.ceil( (window.screen.height - windowH) / 2 );

	resizeTo( Math.ceil( W )       , Math.ceil( H ) );
	moveTo  ( Math.ceil( windowX ) , Math.ceil( windowY ) );
}
window.onload=resize();

function reload_(){
	window.location="<%=request.getServletPath()%>?filepath="+theForm.filepath.value;
}

//-----------------------------------------------
//����ƶ���ͼƬ��
function over(the,imgsrc){
	the.className="img_over";
	the.src='toolbar/images/'+imgsrc+'_over.gif';
}
//����Ƴ�ͼƬ��
function out(the,imgsrc){
	the.className="img";
	the.src='toolbar/images/'+imgsrc+'.gif';
}
//�����ͼƬ�ϰ���
function down(the,imgsrc){
	the.className="img_down";
	//the.src='toolbar/images/'+imgsrc+'_down.gif';
}
function over_(the){the.className="img_over";}
function out_(the){the.className="img";}
function down_(the){the.className="img_down";}
//-----------------------------------------------

//---------------------
function _save(){
	if(confirm("���Ҫ������")==false) return false;
	document.forms[0].submit();
}
function _refresh(){
	reload_();
}
//---------------------
function _cut(){
	WebBrowser.ExecWB(11,1);
}
function _copy(){
	WebBrowser.ExecWB(12,1);
}
function _paste(){
	WebBrowser.ExecWB(13,1);
}
function _delete(){
	WebBrowser.ExecWB(33,1);
}
//---------------------
function _Undo(){
	WebBrowser.ExecWB(15,1);
}
function _Redo(){
	WebBrowser.ExecWB(16,1);
}
//---------------------
function _Find(){
	alert("Building...");
}
//---------------------
function _print(){
	WebBrowser.ExecWB(7,1);
}
//---------------------
function _email(){
	window.open("mailto:yoinn@163.com","_blank","");
}
function _homepage(){
	window.open("http://www.3may.net/zl/jwebedit/","_blank","");
}
function _help(){
	alert("Building...");
}
function _about(){
	window.showModalDialog("<%=SYS_BASE_PATH%>about/about.htm","","dialogWidth:266px;dialogHeight:306px;scroll:no;status:no;help:no");
}
//---------------------
function _exit(){
	WebBrowser.ExecWB(45,1);
}
//---------------------
//-->
</SCRIPT>
<body scroll="no">
<TABLE WIDTH="100%" CELLPADDING="0" CELLSPACING="0" BORDER="1" bordercolor=buttonface bordercolorlight="#808080" bordercolordark="#FFFFFF">
  <form name="theForm" method="post">
  <input type="hidden" name="act" value="edit">
  <input type="hidden" name="filepath" size="43" value="<%=filepath%>">
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
	<TD>
	<!--# ToolBar the Second Line Start-->
	<TABLE CELLPADDING="0" CELLSPACING="0" BORDER="0" width="100%">
	  <TR>
		<TD width=2></TD>
		<TD width=3><img src="toolbar/images/toolbar.gif"></TD>
		<TD width=4></TD>
		<TD width=20><img src="toolbar/images/save.gif" class="img" onmousedown="down_(this);" onmouseout="out_(this);" onmouseover="over_(this);" onmouseup="over_(this);" border=1 onclick="_save()" alt="����"></TD>
		<TD width=22><img src="toolbar/images/refresh.gif" class="img" onmousedown="down(this,'refresh');" onmouseout="out(this,'refresh');" onmouseover="over(this,'refresh');" onmouseup="over(this,'refresh');" border=1 onclick="_refresh()" alt="ˢ��"></TD>
		<!--TD width=6 align=center><img src="toolbar/images/tiao.gif"></TD>
		<TD width=20><img src="toolbar/images/cut.gif" class="img" onmousedown="down_(this);" onmouseout="out_(this);" onmouseover="over_(this);" onmouseup="over_(this);" border=1 onclick="_cut()" alt="����ѡ�е����ݵ�������"></TD>
		<TD width=20><img src="toolbar/images/copy.gif" class="img" onmousedown="down_(this);" onmouseout="out_(this);" onmouseover="over_(this);" onmouseup="over_(this);" border=1 onclick="_copy()" alt="����ѡ�е����ݵ�������"></TD>
		<TD width=20><img src="toolbar/images/paste.gif" class="img" onmousedown="down_(this);" onmouseout="out_(this);" onmouseover="over_(this);" onmouseup="over_(this);" border=1 onclick="_paste()" alt="ճ���������е�����"></TD>
		<TD width=20><img src="toolbar/images/delete.gif" class="img" onmousedown="down_(this);" onmouseout="out_(this);" onmouseover="over_(this);" onmouseup="over_(this);" border=1 onclick="_delete()" alt="ɾ��ѡ�е�����"></TD-->
		<TD width=22><img src="toolbar/images/del.gif" class="img" onmousedown="down(this,'del');" onmouseout="out(this,'del');" onmouseover="over(this,'del');" onmouseup="over(this,'del');" border=1 onclick="_delete()" alt="ɾ��ѡ�������"></TD>
		<TD width=6 align=center><img src="toolbar/images/tiao.gif"></TD>
		<TD width=22><img src="toolbar/images/undo.gif" class="img" onmousedown="down_(this);" onmouseout="out_(this);" onmouseover="over_(this);" onmouseup="over_(this);" border=1 onclick="_Undo()" alt="Undo(Ctrl+Z)"></TD>
		<TD width=22><img src="toolbar/images/redo.gif" class="img" onmousedown="down_(this);" onmouseout="out_(this);" onmouseover="over_(this);" onmouseup="over_(this);" border=1 onclick="_Redo()" alt="Redo(Ctrl+Y)"></TD>
		<TD width=6 align=center><img src="toolbar/images/tiao.gif"></TD>
		<TD width=22><img src="toolbar/images/find.gif" class="img" onmousedown="down_(this);" onmouseout="out_(this);" onmouseover="over_(this);" onmouseup="over_(this);" border=1 onclick="_Find()" alt="Find and Replace"></TD>
		<TD width=1></TD>
		<TD width=22><input type=text name=findStr size=10 class=tx onkeypress="return false;"></TD>
		<TD width=2></TD>
		<TD width=6 align=center><img src="toolbar/images/tiao.gif"></TD>
		<TD width=22><img src="toolbar/images/print.gif" class="img" onmousedown="down(this,'print');" onmouseout="out(this,'print');" onmouseover="over(this,'print');" onmouseup="over(this,'print');" border=1 onclick="_print()"></TD>
		<TD width=22><img src="toolbar/images/email.gif" class="img" onmousedown="down(this,'email');" onmouseout="out(this,'email');" onmouseover="over(this,'email');" onmouseup="over(this,'email');" border=1 onclick="_email()" alt="Email to me"></TD>
		<TD width=22><img src="toolbar/images/home.gif" class="img" onmousedown="down(this,'home');" onmouseout="out(this,'home');" onmouseover="over(this,'home');" onmouseup="over(this,'home');" border=1 onclick="_homepage()" alt="JWebEdit's HomePage"></TD>
		<TD width=22><img src="toolbar/images/help.gif" class="img" onmousedown="down_(this);" onmouseout="out_(this);" onmouseover="over_(this);" onmouseup="over_(this);" border=1 onclick="_help()" alt="JWebEdit ����"></TD>
		<TD width=22><img src="toolbar/images/about.gif" class="img" onmousedown="down_(this);" onmouseout="out_(this);" onmouseover="over_(this);" onmouseup="over_(this);" border=1 onclick="_about()" alt="About JWebEdit"></TD>
		<TD>&nbsp;</TD>
		<TD width=20></TD>
		<TD width=22><img src="toolbar/images/exit.gif" class="img" onmousedown="down(this,'exit');" onmouseout="out(this,'exit');" onmouseover="over(this,'exit');" onmouseup="over(this,'exit');" border=1 onclick="_exit()"></TD>
	  </TR>
	</TABLE>
	<!--# ToolBar the Second Line End-->
	</TD>
  </TR>
  <TR>
	<TD>

	<TABLE CELLPADDING="0" CELLSPACING="0" BORDER="0" width="100%">
	  <TR valign="middle" height=22>
		<TD width=2></TD>
		<TD width=3><img src="toolbar/images/toolbar.gif"></TD>
		<TD width=4></TD>
		<TD width=46><nobr>��ַ(<u>D</u>)</TD>
		<TD width=2></TD>
		<TD>
		  <div style="border:2 inset white;width:100%; height:22px;background-color:#FFFFFF">
		  <TABLE CELLPADDING="1" CELLSPACING="0" BORDER="0" width="100%" height="100%">
		  <TR>
		  	<TD width=16><img src="toolbar/images/ie.gif"></TD>
		  	<TD id=URL>http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=filepath%></TD>
		  </TR>
		  </TABLE>
		  </div>
		</TD>
		<TD width=2></TD>
		<TD width=49><img src="toolbar/images/goto.gif" class="img" onmousedown="down(this,'goto');" onmouseout="out(this,'goto');" onmouseover="over(this,'goto');" onmouseup="over(this,'goto');" border=1 onclick="reload_()"></TD>
		<TD width=4></TD>
	  </TR>
	</TABLE>

    </TD>
  </TR>
  <TR>
	<TD><TEXTAREA NAME="filecontent" ROWS="6" COLS="90" wrap="OFF" class=tx style="width:630px;height:378px"><%=filecontent%></TEXTAREA></TD>
  </TR>
  </form>
</TABLE>
</body>
</html>
<%!
//import java.io.*;

public boolean isExist(String fullFileName){
	try{
		File file=new File(fullFileName);
		return file.exists();
	}catch(Exception e){
		return false;
	}
}
%>