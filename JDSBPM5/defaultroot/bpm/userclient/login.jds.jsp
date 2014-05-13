
<%@ page contentType="text/html; charset=UTF-8"%>
<%
String contextpath = request.getContextPath() + "/";
%>
<TITLE> 系统登录 </TITLE>


<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css"
			href="<%=contextpath%>js/ext/resources/css/ext-all.css" />

		<link rel="STYLESHEET" type="text/css"
			href="<%=contextpath%>css/dhtmlXTree.css">
		
<link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/menus.css" />		
  <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/css/desktop.css" />
  <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
		<SCRIPT LANGUAGE="JavaScript" type="text/javascript">
	_js_prefix='<%=contextpath%>js/';
	context='<%=contextpath%>';
	</SCRIPT> 		
	
	   		   
		<script type="text/javascript"  src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>					
	
		<script type="text/javascript"  src="<%=contextpath%>js/ext/ext-all.js"></script>
		
		 
		<script type="text/javascript"  src="<%=contextpath%>js/ext/message-box/msg-box.js"></script>
		<script type="text/javascript"  src="<%=contextpath%>js/showpic.js"></script>
	
    
<style type="text/css">
.style1 {font-size: 12px; color:#205484; face:宋体}
.style11 {font-size: 12px; color:#205484; face:宋体}
.style111 {font-size: 12px; color:#205484; face:宋体}
.style112 {font-size: 12px; color:#205484; face:宋体}
.style1111 {font-size: 12px; color:#205484; face:宋体}
.style11111 {font-size: 12px; color:#205484; face:宋体}
.style1121 {font-size: 12px; color:#205484; face:宋体}
.style111111 {font-size: 12px; color:#205484; face:宋体}
.style11211 {font-size: 12px; color:#205484; face:宋体}
.style1111111 {font-size: 12px; color:#205484; face:宋体}
.style112111 {font-size: 12px; color:#205484; face:宋体}
.style11111111 {font-size: 12px; color:#205484; face:宋体}
.style1121111 {font-size: 12px; color:#205484; face:宋体}

    #loading-mask{
        position:absolute;
        left:0;
        top:0;
        width:100%;
        height:100%;
        z-index:20000;
        background-color:white;
    }
    #loading{
        position:absolute;
        left:45%;
        top:40%;
        padding:2px;
        z-index:20001;
        height:auto;
    }
    #loading a {
        color:#225588;
    }
    #loading .loading-indicator{
        background:white;
        color:#444;
        font:bold 13px tahoma,arial,helvetica;
        padding:10px;
        margin:0;
        height:auto;
    }
    #loading-msg {
        font: normal 10px arial,tahoma,sans-serif;
    }
</style>

<HTML>
<BODY bgproperties="fixed" BGCOLOR=#FFFFFF LEFTMARGIN=0 TOPMARGIN=150 MARGINWIDTH=0 MARGINHEIGHT=0>
<div id="loading-mask" style=""></div>
<div id="loading">
    <div class="loading-indicator"><img src="<%=contextpath%>imgs/desktop/loading.gif" width="32" height="32" style="margin-right:8px;float:left;vertical-align:top;"/>登入页面<br /><span id="loading-msg">Loading ......</span></div>
</div>
<form id="frmLogin" method="post" action="<%=contextpath%>login.action">
<TABLE WIDTH=860 height="388" BORDER=0 align="center" CELLPADDING=0 CELLSPACING=0>
  <TR> 
    <TD ROWSPAN=2 width="48"> 　</TD>
    <TD height="290" width="792"> 　</TD>
    <TD ROWSPAN=2 width="20"> 　</TD>
  </TR>
  <TR> 
    <TD height="84"  width="792" align="center">
    <table width="202" border="0" cellpadding="3" cellspacing="0">
      <tr align="center">
        <td width="80">
              <DIV style="FONT-SIZE: 11pt; FONT-FAMILY: 宋体">用户名：</DIV>
        </td>
        <td width="77">
              <input  name="username" type="text" size="10"  
				style="font-size: 14px;border-top:none; border-left:none; border-right:none; border-bottom: 1px solid #558FC1; background:none;" tabindex="8" />
        </td>
        <td width="50" rowspan="2">
           <input name="image" type="image" id="img" tabindex="10" src="<%=contextpath%>desktop/resources/images/default/icons/im48x48.gif" width="47" height="44"  border="0" />
        </td>
      </tr>
      <tr align="center">
        <td height="24">
        <DIV style="FONT-SIZE: 11pt; FONT-FAMILY: 宋体">密码：</DIV></td>
        <td><input type="password"  name="password" size="11"
				style="font-size: 14px;border-top:none; border-left:none; border-right:none; border-bottom: 1px solid #558FC1; background:none;" tabindex="9" /></td>
      </tr>
    </table>
</TD>
  </TR>
  <TR> 
    <TD height="12" COLSPAN=2> </TD>
    <TD width="20"> 　</TD>
  </TR>
</TABLE>
</form>
<SCRIPT LANGUAGE="JavaScript">
 // function msg (){
 //  messageBoxprogress("正在登录系统请稍后...","正在登录系统请稍后...","正在登录系统请稍后...",'8','');
 //  }
 
 	Ext.EventManager.on(window, 'load', function(){
    setTimeout(function(){
        Ext.get('loading').remove();
        Ext.get('loading-mask').fadeOut({remove:true});
    }, 1000);
	});
<!--
    frmLogin.username.focus();
-->
</SCRIPT>

<%
if(request.getAttribute("message")=="login"){
	out.print("<script language='javascript'>");
	out.print("alert('用户名或密码错误！');");
    out.print("</script>");
}
 %>
</BODY>
</HTML>