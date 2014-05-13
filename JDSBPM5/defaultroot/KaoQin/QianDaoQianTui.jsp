<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    
    <title></title>
	<style type="text/css">
		<!--
		.STYLE3 {font-size: 36px}
		.STYLE4 {font-size: 20px}
		-->
		.tableClass{
		 border-collapse: collapse; 
		} 
		.tdClass{
		    border:1px solid #000000;
		}	
	</style>
	<script type="text/javascript">var context='/';</script>
	<script type="text/javascript" src="/js/extAll.js"></script>
	<script type="text/javascript">
	
		function fun_1(){   
			document.getElementById("my_form").action="KaoQinSave1.action";   
			document.getElementById("my_form").submit();   
		}
		
		function fun_2(){   
			document.getElementById("my_form").action="KaoQinSave2.action";   
			document.getElementById("my_form").submit();   
		}
		
		function fun_3(){   
			document.getElementById("my_form").action="KaoQinSave3.action";   
			document.getElementById("my_form").submit();   
		}
		
		function fun_4(){   
			document.getElementById("my_form").action="KaoQinSave4.action";   
			document.getElementById("my_form").submit();   
		}
		
	</script>
  </head>
  
  <body>
	<form id="my_form" method="post">
		<div align="center" class="STYLE3">
			签到签退页面
		</div>
		<table align="center" width="550" class="tableClass">
			<tr>
				<td width="200" height="50" class="tdClass">
					<div align="center">
						<span class="STYLE4">上午</span>
					</div>
				</td>
				<td width="350" align="center" class="tdClass">
					<input type="button" value="签到" onclick="fun_1()" />
				</td>
			</tr>
			<tr>
				<td width="200" height="50" class="tdClass">
					<div align="center">
						<span class="STYLE4">上午</span>
					</div>
				</td>
				<td width="350" align="center" class="tdClass">
					<input type="button" value="签退" onclick="fun_2()" />
				</td>
			</tr>
			<tr>
				<td width="200" height="50" class="tdClass">
					<div align="center">
						<span class="STYLE4">下午</span>
					</div>
				</td>
				<td width="350" align="center" class="tdClass">
					<input type="button" value="签到" onclick="fun_3()" />
				</td>
			</tr>
			<tr>
				<td width="200" height="50" class="tdClass">
					<div align="center">
						<span class="STYLE4">下午</span>
					</div>
				</td>
				<td width="350" align="center" class="tdClass">
					<input type="button" value="签退" onclick="fun_4()" />
				</td>
			</tr>
		</table>
	</form>
  </body>
</html>
