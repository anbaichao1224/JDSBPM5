<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>�ͻ�������</title>
<link rel="stylesheet" type="text/css" href="style.css" >
<link rel="stylesheet" type="text/css" href="platformtree.css">
<script language="JavaScript" type="text/JavaScript">

</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" background="images/comp01_right2.gif">

<form name="loginForm" id="loginForm" action="/gwtjAction.action" method="post" >
	<table width="80%" align="center" border="1" style="display:none">
		<tr>
			<td width="30%">��������</td>
      		<td width="20%">��������</td>
      		<td width="20%">��������</td>
      		<td width="20%">��������</td>
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
			<td width="40%">��������</td>
      		<td width="30%">�Ѱ���</td>
      		<td width="30%">������</td>
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
