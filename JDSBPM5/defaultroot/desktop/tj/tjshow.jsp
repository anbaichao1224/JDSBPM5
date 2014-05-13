<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>客户端下载</title>
<link rel="stylesheet" type="text/css" href="style.css" >
<link rel="stylesheet" type="text/css" href="platformtree.css">
<script language="JavaScript" type="text/JavaScript">

</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" background="images/comp01_right2.gif">

<form name="loginForm" id="loginForm" action="/gwtjAction.action" method="post" >
	<table>
		<tr>
			<td width="40%">查询类型：<select name="querytype" id=""querytype"">
				<option value="0">子部门</option>
				<option value="1">本部门</option>
			</select>办理状态：<select name="blstatus" id="blstatus">
				<option value="0">未办理</option>
				<option value="1">已办理</option>
			</select><input type="submit" value="按部门查询"></td>
      		
		</tr>
	</table>
	<table width="80%" align="center" border="1">
		<tr>
			<td width="30%">部门名称</td>
      		<td width="20%">发文数量</td>
      		<td width="20%">收文数量</td>
      		<td width="20%">阅文数量</td>
		</tr>
		<ww:iterator value="tjlist" status="rowstatus">
			<tr>
				
				<td><ww:property value="name"/></td>
				<ww:iterator value="childList" status="rowstatus">
					<td><ww:property value="count"/></td>
				</ww:iterator>
			</tr>
		</ww:iterator>
	</table>
	<table width="80%" align="center" border="1">
		<tr>
			<td width="40%">公文类型</td>
      		<td width="30%">已办理</td>
      		<td width="30%">待办理</td>
		</tr>
		<ww:iterator value="tjlist" status="rowstatus">
			<tr>
				
				<td><ww:property value="gwtype"/></td>
				<td><ww:property value="completeCount"/></td>
				<td><ww:property value="waitCount"/></td>
			</tr>
		</ww:iterator>
	</table>

</form>
</body>
</html>
