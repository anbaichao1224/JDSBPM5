<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
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
    <title>事项添加</title>

  </head>
  
  <body>
	<ww:form action="cksxmlAdd.action" id="my_form" method="post">
		<div align="center" class="STYLE3">
			行政服务窗口事项添加页面
		</div>
		<p></p>
		<table align="center" width="700" class="tableClass">
			<tr>
				<td width="200" height="50" class="tdClass">
					<div align="center">
						<span class="STYLE4">服务窗口</span>
					</div>
				</td>
				<td width="500" align="center" class="tdClass">
					<input name="fwck" id="fwck" type="text" readonly value='<ww:property value="$currPerson.name"/>'></input>
				</td>
			</tr>
			<tr>
				<td width="200" height="50" class="tdClass">
					<div align="center">
						<span class="STYLE4">窗口事项</span>
					</div>
				</td>
				<td width="500" align="center" class="tdClass">
					<input name="cksx" id="cksx" type="text" ></input>
				</td>
			</tr>
			<tr>
				<td width="200" height="50" class="tdClass">
					<div align="center">
						<span class="STYLE4">是否年审</span>
					</div>
				</td>
				<td width="500" align="center" class="tdClass">
					<select id="sfns" name="sfns">
							<option value="是">是</option>
							<option value="否">否</option>
					</select>
				</td>
			</tr>
		</table>
	</ww:form>
  </body>
</html>
