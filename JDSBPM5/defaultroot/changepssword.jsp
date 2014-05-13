<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<head>
	<base href="<%=basePath%>">

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	
	-->
	<script language="javascript">
		 
		//验证新密码的长度以及新密码中不能含有非法字符
		function yzPassFF(){
			var newpssword = document.getElementById("newpssword").value; 
			var zzbds="^[0-9a-zA-Z_]{6,12}$"; 
			var yz=new RegExp(zzbds);
			if (!yz.test(newpssword))
			{
				document.getElementById("yzPass").innerHTML = '密码必须由字母,数字或<br>是下划线组成,且只能6-12位';
				return false;
			}else{
				document.getElementById("yzPass").innerHTML = '';
				return true;
			}
		}
		
	   //验证两次密码是否一致
	  	function yzMimaYZ(){	
			var newPassword = document.getElementById("newPassword").value;
			var xinmima = document.getElementById("xinmima").value;
			if(newPassword != xinmima){
				document.getElementById('yzmima').innerHTML ="请两次输入相同的密码";
				return false;
			}else{
				document.getElementById("yzmima").innerHTML ='';
				return true;
			}
		} 
		
		//总验证
		function tijiao(){
			if(!yzPassFF()) return;
			else if(!yzMimaYZ()) return;
			xgmm.submit();
		}
	
</script>	
</head>
<body>
	<center>
		<form name="xgmm" method="post"	action="/ChangePsswordAction_passwordModify.action">
		
			<table border="1" width="600" height="300" cellspacing="0" cellpadding="0" >
				<tr>
					<td width='120' height="100">请输入新密码：</td>
					<td  width='200'><input type="password" name="newPassword" id="newPssword" onBlur="yzPassFF()" /> </td>
					<td><span id="yzPass" style="color:#FF0000"></span></td>
				</tr>
				<tr>
					<td>请确认新密码：</td>
					<td><input type="password" name="xinmima" id="xinmima" onBlur="yzMimaYZ()" /> </td>
					<td><span id="yzmima" style="color:#FF0000"></span></td>
				</tr>
				<tr>
					<td colspan="3" align="center"><input type="button" value="修改" onclick="tijiao()"  /> </td>
				</tr>
			</table>
			
		</form>
	</center>

</body>
</html>
