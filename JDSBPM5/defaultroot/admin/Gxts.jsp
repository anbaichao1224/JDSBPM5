<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String fileList = request.getParameter("fileList");
%>
<html>
	<head>
		<base href="<%=basePath%>"></base>
		<script>var context='<%=path%>';</script>
		<style type="text/css">
<!--
body {
	font-family: "宋体", "Arial";
	font-size: 9pt;
	background-color: #dfe8f6
}

td {
	font-family: "宋体", "Arial";
	font-size: 9pt
}

.check {
	color: #FF0000
}

-->
<!--
.STYLE1 {
	font-size: 24px
}

.STYLE2 {
	font-size: 18px
}

-->
.tableClass {
	border-collapse: collapse;
}

.tdClass {
	border: 1px solid #000000;
}
</style>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />

		<script type="text/javascript" src="<%=path%>js/extAll.js"></script>
		<script type="text/javascript" src="<%=path%>/js/ext/TabCloseMenu.js"></script>
		<script type="text/javascript"
			src="/admin/departtree.js" defer="defer"></script>
		<script type="text/javascript" src="<%=path%>desktop/js/CreateGrid.js"></script>
		<script src="/js/JDS.js" type="text/javascript"></script>
		<script type="text/javascript">
			 var fileList='<%=fileList%>';
		</script>
		<script type="text/javascript">
			function checknum(s){
				var patrn=^[1-9]*[1-9][0-9]*$;
				if(patrn.test(s)){
					return true;
				}else{
					return false
				}
			}
		
		</script>
	</head>
	<body>
		<form id="gxts" name="gxts" action="Chaxun_gxts.action" method="post">
			<table width="481" height="420" border="1" align="center"
				cellpadding="0" cellspacing="0">
				<input type="hidden" name="fileList" id="fileList"
					value='<%=fileList%>' />
				<tr>
					<td height="64" colspan="4">
						<div align="center" class="STYLE1">
							系 统 提 醒
						</div>
					</td>
				</tr>
				<tr>
					<td width="106" height="30" align="center">
						<span class="STYLE2">内 容 </span>
					</td>
					<td width="200" height="35" colspan="3">
						<span class="STYLE2"> <textarea id="msm" name="msm"
								cols="42" rows="17"></textarea> </span>
					</td>
				</tr>

				<tr>
					<td width="106" height="15" align="center">
						<span class="STYLE2">单 位 </span>
					</td>
					<td colspan="3">

						<textarea size="85" class="required" name="deptnames" type="text"
							id="deptnames" onkeydown="return false;" cols="35" rows="4"></textarea>
						<input type="hidden" name="id" id="id" />
						<input name="choice" size="70" type="button" id="choice"
							value="选择" onclick="createPersonPositionWindow('5015','')" />
					</td>
				</tr>


			</table>
		</form>
	</body>
</html>