<%@ page contentType="text/html;charset=gb2312" language="java"%>
<%@include file="../../shared/inc/App_Variable.jsp"%>
<%
response.addHeader("Cache-Control", "no-cache");
response.addHeader("Expires", "Thu,  01 Jan   1970 00:00:01 GMT");
%>
<HTML>
<HEAD>
<TITLE>TOOLBAR</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=GB2312"/>
<META NAME="Description" CONTENT="JWebEdit - TOOLBAR"/>
<META NAME="Keywords" CONTENT="JWebEdit"/>
<META NAME="Yoinn.LOCALE" CONTENT="zh-cn"/>

<!-- no cache headers -->
<META content=no-cache http-equiv=Pragma>
<META http-equiv=no-cache>
<META content=-1 http-equiv=Expires>
<META content=no-cache http-equiv=Cache-Control>
<!-- end no cache headers -->

<!--- TOOLBAR_EXEMPT --->
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
<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
<SCRIPT LANGUAGE="JavaScript">
<!--
//鼠标移动到图片上
function over(the,imgsrc){
	the.className="img_over";
	the.src='images/'+imgsrc+'_over.gif';
}
//鼠标移出图片上
function out(the,imgsrc){
	the.className="img";
	the.src='images/'+imgsrc+'.gif';
}
//鼠标在图片上按下
function down(the,imgsrc){
	the.className="img_down";
	//the.src='images/'+imgsrc+'_down.gif';
}
function over_(the){the.className="img_over";}
function out_(the){the.className="img";}
function down_(the){the.className="img_down";}

window.top.content = top.frames.fraRightFrame.frames.fraContent;

//---------------------
function _prev(){
	window.history.go(-1);
}
function _next(){
	window.history.go(1);
}
function _toup(){
	window.top.content.goParent();
}
//---------------------
function _stop(){
	//WebBrowser.stop();
}
function _refresh(){
	window.top.location.reload();
	//WebBrowser.ExecWB(22,1);
}
function _home(){
	window.top.content.location="../loadfile.jsp";
}
//---------------------
function _cut(){
	window.top.content.doit("cut");
}
function _copy(){
	window.top.content.doit("copy");
}
function _paste(){
	window.top.content.paste();
}
function _delete(){
	if(window.top.content.chkSelect()==false) return;
	if(!confirm("真的要删除选中的文件与目录吗？")) return;
	window.top.content.doit("delete");
}
//---------------------
function _moveto(){
	alert("Building...");
}
function _copyto(){
	alert("Building...");
}
//---------------------
function _print(){
	WebBrowser.ExecWB(7,1);
}
function _edit(){
	window.top.content.editfile();
}
//---------------------
//---------------------
function _email(){
	window.open("mailto:yoinn@163.com","_blank","");
}
function _homepage(){
	window.open("http://www.3may.net/zl/2003/jwebedit/","_blank","");
}
function _help(){
	alert("Building...");
	alert("注意：\n1.不能复制空目录.\n2.可以使用剪切来移动目录.");
}
function _about(){
	window.showModalDialog("<%=SYS_BASE_PATH%>treeview/about/about.htm","","dialogWidth:266px;dialogHeight:306px;scroll:no;status:no;help:no");
}
//---------------------
//---------------------
function _exit(){
	WebBrowser.ExecWB(45,1);
}
//---------------------
//-->
</SCRIPT>	
</HEAD>

<BODY TOPMARGIN="0"  LEFTMARGIN="0" MARGINHEIGHT="0" MARGINWIDTH="0" TEXT="#000000">
<div id="TBContainer" style="height:0px;">
<TABLE WIDTH="100%" CELLPADDING="0" CELLSPACING="0" BORDER="1" bordercolor=buttonface bordercolorlight="#808080" bordercolordark="#FFFFFF">
  <form name=theForm>
  <TR height=22>
	<TD>
	<!--# ToolBar the First Line Start-->
	<TABLE CELLPADDING="0" CELLSPACING="0" BORDER="0">
	  <TR>
		<TD width=2></TD>
		<TD width=3><img src="images/toolbar.gif"></TD>
		<TD width=4></TD>
		<TD width=4><nobr>天诚永信桌面3.0 文件管理器 </TD>
		<TD width=6 align=center><img src="images/tiao.gif"></TD>		
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
		<TD width=3><img src="images/toolbar.gif"></TD>
		<TD width=4></TD>
		<TD width=50><img src="images/prev.gif" class="img" onmousedown="down(this,'prev');" onmouseout="out(this,'prev');" onmouseover="over(this,'prev');" onmouseup="over(this,'prev');" border=1 onclick="_prev()" alt="向前"></TD>
		<TD width=22><img src="images/next.gif" class="img" onmousedown="down(this,'next');" onmouseout="out(this,'next');" onmouseover="over(this,'next');" onmouseup="over(this,'next');" border=1 onclick="_next()" alt="向后"></TD>
		<TD width=22><img src="images/toup.gif" class="img" onmousedown="down(this,'toup');" onmouseout="out(this,'toup');" onmouseover="over(this,'toup');" onmouseup="over(this,'toup');" border=1 alt="向上" onclick="_toup()"></TD>
		<TD width=6 align=center><img src="images/tiao.gif"></TD>
		<TD width=22><img src="images/stop.gif" class="img" onmousedown="down(this,'stop');" onmouseout="out(this,'stop');" onmouseover="over(this,'stop');" onmouseup="over(this,'stop');" border=1 onclick="_stop()"></TD>
		<TD width=22><img src="images/refresh.gif" class="img" onmousedown="down(this,'refresh');" onmouseout="out(this,'refresh');" onmouseover="over(this,'refresh');" onmouseup="over(this,'refresh');" border=1 onclick="_refresh()" alt="刷新"></TD>
		<TD width=22><img src="images/home.gif" class="img" onmousedown="down(this,'home');" onmouseout="out(this,'home');" onmouseover="over(this,'home');" onmouseup="over(this,'home');" border=1 onclick="_home()" alt="home"></TD>		
		<TD width=6 align=center><img src="images/tiao.gif"></TD>
		<TD width=20><img src="images/cut.gif" class="img" onmousedown="down_(this);" onmouseout="out_(this);" onmouseover="over_(this);" onmouseup="over_(this);" border=1 onclick="_cut()" alt="剪切选中的文件与目录到剪贴板"></TD>
		<TD width=20><img src="images/copy.gif" class="img" onmousedown="down_(this);" onmouseout="out_(this);" onmouseover="over_(this);" onmouseup="over_(this);" border=1 onclick="_copy()" alt="复制选中的文件与目录到剪贴板"></TD>
		<TD width=20><img src="images/paste.gif" class="img" onmousedown="down_(this);" onmouseout="out_(this);" onmouseover="over_(this);" onmouseup="over_(this);" border=1 onclick="_paste()" alt="粘贴剪贴板中的文件与目录"></TD>
		<!--TD width=20><img src="images/delete.gif" class="img" onmousedown="down_(this);" onmouseout="out_(this);" onmouseover="over_(this);" onmouseup="over_(this);" border=1 onclick="_delete()" alt="删除选中的文件与目录"></TD-->
		<TD width=6 align=center><img src="images/tiao.gif"></TD>
		<TD width=22><img src="images/moveto.gif" class="img" onmousedown="down(this,'moveto');" onmouseout="out(this,'moveto');" onmouseover="over(this,'moveto');" onmouseup="over(this,'moveto');" border=1 alt="移至" onclick="_moveto()"></TD>
		<TD width=22><img src="images/copyto.gif" class="img" onmousedown="down(this,'copyto');" onmouseout="out(this,'copyto');" onmouseover="over(this,'copyto');" onmouseup="over(this,'copyto');" border=1 alt="复制到" onclick="_copyto()"></TD>
		<TD width=22><img src="images/del.gif" class="img" onmousedown="down(this,'del');" onmouseout="out(this,'del');" onmouseover="over(this,'del');" onmouseup="over(this,'del');" border=1 onclick="_delete()" alt="删除"></TD>
		<TD width=22><img src="images/ce.gif" class="img" onmousedown="down(this,'ce');" onmouseout="out(this,'ce');" onmouseover="over(this,'ce');" onmouseup="over(this,'ce');" border=1 alt="撤销"></TD>
		<TD width=6 align=center><img src="images/tiao.gif"></TD>
		<TD width=22><img src="images/print.gif" class="img" onmousedown="down(this,'print');" onmouseout="out(this,'print');" onmouseover="over(this,'print');" onmouseup="over(this,'print');" border=1 onclick="_print()"></TD>
		<TD width=22><img src="images/edittool.gif" class="img" onmousedown="down(this,'edittool');" onmouseout="out(this,'edittool');" onmouseover="over(this,'edittool');" onmouseup="over(this,'edittool');" border=1 onclick="_edit()"></TD>
		<TD width=6 align=center><img src="images/tiao.gif"></TD>
				<TD>&nbsp;</TD>
		<TD width=20></TD>
		<TD width=22><img src="images/exit.gif" class="img" onmousedown="down(this,'exit');" onmouseout="out(this,'exit');" onmouseover="over(this,'exit');" onmouseup="over(this,'exit');" border=1 onclick="_exit()"></TD>
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
		<TD width=3><img src="images/toolbar.gif"></TD>
		<TD width=4></TD>
		<TD width=46><nobr>地址(<u>D</u>)</TD>
		<TD width=2></TD>
		<TD>
		  <div style="border:2 inset white;width:100%; height:22px;background-color:#FFFFFF">
		  <TABLE CELLPADDING="1" CELLSPACING="0" BORDER="0" width="100%" height="100%">
		  <TR>
		  	<TD width=16><img src="images/ie.gif"></TD>
		  	<TD id=URL>http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=APP_BASE_PATH%><%=APP_CUR_PATH%></TD>
		  </TR>
		  </TABLE>
		  </div>
		</TD>
		<TD width=2></TD>
		<TD width=49><img src="images/goto.gif" class="img" onmousedown="down(this,'goto');" onmouseout="out(this,'goto');" onmouseover="over(this,'goto');" onmouseup="over(this,'goto');" border=1></TD>
		<TD width=4></TD>
	  </TR>
	</TABLE>

	</TD>
  </TR>
  </form>
</TABLE>
<div>
</BODY>
</HTML>