<%
String contextpath = request.getContextPath() + "/";
%>
<%@ page language="java"  pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>USM应用资源管理系统</title>
<style type="text/css">
</style>
    <script type="text/javascript">
    	function logon(){
    		form1.submit();
    	}
    </script>
</head>
<body topmargin="0" leftmargin="0" background="/usm/images/bj3.jpg">
<form id="frmLogin" method="post" action="<%=contextpath%>usm/logon.action">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" align="left">
  <tr>
    <td height="20"></td>
  </tr>
  <tr>
    <td>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="400"></td>
		<td width="400" height="240" background="/usm/images/bj4.png" >
		<table border="0" cellspacing="0" cellpadding="0" align="right">
            <tr>
              <td>
			  <table height="60" border="0" cellspacing="0" cellpadding="0">
                <td width="70">用户名</td>
                  <td width="130">
                  <input type="text" name="username" onmouseover="this.style.borderColor='#000000';this.style.backgroundColor='#ccffff'" style="width:120px;height:20px;border-width:0px;border-color=#ffffff" onmouseout="this.style.borderColor='#ffffff';this.style.backgroundColor='#ffffff'" />                  </td>
                <tr>
                  <td width="70">密&nbsp;&nbsp;码</td>
                  <td><input type="password" name="password" onmouseover="this.style.borderColor='#000000';this.style.backgroundColor='#ccffff'" style="width:120px; height:20px;border-width:0px;border-color=#ffffff" onmouseout="this.style.borderColor='#ffffff';this.style.backgroundColor='#ffffff'" /></td>
                </tr>
              </table></td>
              <td width="48" valign="center">
			  <input name="image" type="image" id="img" onmouseover="this.src='/usm/images/dl_13.png'" onmouseout="this.src='/usm/images/dl_03.png'" src="/usm/images/dl_03.png"/></td>
              <td width="35">&nbsp;</td>
            </tr>
        </table></td>
        <td width="329"></td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
<%
if(request.getAttribute("message")=="login"){
	out.print("<script language='javascript'>");
	out.print("alert('用户名或密码错误！');");
    out.print("</script>");
}
 %>
</body>
</html>
