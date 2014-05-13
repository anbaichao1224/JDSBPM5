<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>客户端下载</title>
<link rel="stylesheet" type="text/css" href="style.css" >
<link rel="stylesheet" type="text/css" href="platformtree.css">
<style>
<!--
.inputtext {
  border-bottom-color: White;
  border-left-style: none;
  border-right-style: none;
  border-top-style: none;
  border-bottom-style: solid;
  background-color: #989898;
  border-bottom-width: 1px;
  text-align: center;
  }
.gray {
  font-size: 9pt;
  cursor: default
  }
.blue {
  font-size: 9pt;
  color: #FFFFFF;
  cursor: hand}
-->
</style>
<script language="JavaScript" type="text/JavaScript">
<!--
function MM_reloadPage(init) {  //reloads the window if Nav4 resized
  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
}
MM_reloadPage(true);
//-->
</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<div id="Layer1" style="position:absolute; left:196px; top:267px; width:587px; height:154px; z-index:1"> 
  <p>各位系统用户您好:</p>
  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;新版OA系统采用客户端登录方式，请下载系</p>
  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;统登录客户端安装后直接登录。<u><strong></p>
  <p align="right"><font color="#0000FF"><strong><a style='font-weight: bold;color: blue' href="/downloadclient.action">系统登录客户端下载<img src="<%=request.getContextPath() %>/download/icon_download.gif" width="16" height="16"></a></strong></font></p>
  
  <%--<p align="right"><strong><font color="#0000FF">新系统无客户端登录<img src="<%=request.getContextPath() %>/download/icon_download.gif" width="16" height="16"></font></strong></p>--%>
</div>
<form name="loginForm" id="loginForm" action="logon.jsp" method="post" >
	<table width="100%" height="100%"  style="background-image:URL(<%=request.getContextPath() %>/download/loginFrameBgImg.jpg)">
		<tr>
			
      <td width="55%" height="406"></td>
			<td width="45%">
				<table width="731"  border="0" cellspacing="0" cellpadding="5" style="font:Menu">
					<tr>
						<td width="721" height="300" colspan="3">&nbsp;</td>
					</tr>
					
					<tr>
						<td height="30" colspan="3">&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

</form>
</body>
</html>
