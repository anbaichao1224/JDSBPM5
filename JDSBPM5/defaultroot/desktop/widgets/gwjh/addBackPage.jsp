<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%@page import="net.itjds.j2ee.util.UUID"%>
<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String uuid = (new UUID()).toString();
	String inceptid = request.getParameter("uuid");
	String sendid = request.getParameter("id");
%>
<html>
	<head>
		<base href="<%=basePath%>"></base>
		<script>var context='<%=path%>';</script>
		<style type="text/css">
<!--
body {
	font-family: "宋体", "Arial";
	background-color: #dfe8f6
}

.STYLE3 {
	font-size: 24px
}

.STYLE4 {
	font-size: 20px
}
-->
</style>

		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />

		<script type="text/javascript" src="<%=path%>js/extAll.js"></script>
		<script type="text/javascript" src="<%=path%>/js/ext/TabCloseMenu.js"></script>
		<script type="text/javascript" src="<%=path%>desktop/js/CreateGrid.js"></script>
		<script src="/js/JDS.js" type="text/javascript"></script>
		<script type="text/javascript">
			
		</script>
	</head>
	<body>
		<form id="addBack" action="gwjhAction_addBack.action" method="post">
			<input type="hidden" name="parentid" id="parentid"
				value='<%=request.getParameter("parentid")%>' />
			<input type="hidden" name="tid" id="tid" value='<%=uuid%>' />
			<input type="hidden" name="optionnum" id="optionnum" value='1' />
			<input type="hidden" name="mettinguuid" id="mettinguuid"
				value='<%=request.getParameter("mettinguuid")%>' />
			<input type="hidden" name="adddirection" id="adddirection" value='2' />
			<input type="hidden" name="liststatus" id="liststatus"
				value='<%=request.getParameter("liststatus")%>' />
			<input type="hidden" name="uuid" id="uuid" value='<%=inceptid%>' />
			<input type="hidden" name="id" id="id" value='<%=sendid%>' />
			<table align="center" width="750" height="491" border="1"
				cellpadding="0" cellspacing="0">
				<tr>
					<td height="20" colspan="6">
						<div align="center" class="STYLE3">
							 拒签信息
						</div>
					</td>
				</tr>
				<tr>
					<td width="65" height="30">
						<div align="center">
							<span class="STYLE4">拒签人</span>
						</div>
					</td>
					<td width="249" align="center">
						<input name="backPerson" type="text"
							value='<ww:property value="$currPerson.name"/>'></input>
					</td>
					<td width="80">
						<div align="center">
							<span class="STYLE4">拒签单位</span>
						</div>
					</td>
					<td width="99" align="center">
						<input name="backdept" type="text"
							value='<ww:property value="deptName"/>'></input>
					</td>

				</tr>
				<tr>
					<td height="80">
						<div align="center">
							<span class="STYLE4">拒签理由</span>
						</div>
					</td>
					<td colspan="4" align="center">
						<textarea name="backReason" cols="75" rows="15"></textarea>
					</td>
				</tr>
			
			</table>
		</form>
	</body>
</html>