<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>"></base>
		<script>var context='<%=path%>';</script>
<style type="text/css">
<!--
.STYLE3 {font-size: 36px}
.STYLE4 {font-size: 20px}
-->
</style>

		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />

		<script type="text/javascript" src="<%=path%>js/extAll.js"></script>
		<script type="text/javascript" src="<%=path%>/js/ext/TabCloseMenu.js"></script>
		<script type="text/javascript"
			src="<%=path%>desktop/js/CreateGrid.js"></script>
		<script src="/js/JDS.js" type="text/javascript"></script>
		<script type="text/javascript">
			Ext.onReady(function(){
			  var kssj = new Ext.form.DateField({
				applyTo:'djlwrq',
				fieldLabel:'日期',
				emptyText:'请选择',
				format:'Y-m-d',
				value:'<ww:property value="$CurrTime.currDate"/>'	
			  });
			});
		</script>
	</head>
	<body>
		<form id="addDj" action="/SwdjAction_addDj.action" method="post">
		<input type="hidden" name="tid" id="tid" value='<ww:property value="sxxxid"></ww:property>' />
		<input type="hidden" name="xmlmodel" id="xmlmodel" value='<ww:property value="xmlmodel"></ww:property>' />
		<input type="hidden" name="UUID" id="UUID" value='<ww:property value="sxxxid"></ww:property>' />
			<table align="center" width="722" height="491" border="1"
				cellpadding="0" cellspacing="0">
				<tr>
					<td height="104" colspan="6">
						<div align="center" class="STYLE3">
							收文信息登记表
						</div>
					</td>
				</tr>
				<tr>
					<td width="58" height="46">
						<div align="center">
							<span class="STYLE4">编号</span>
						</div>
					</td>
					<td width="249" align="center">
						<input name="djbh" type="text"></input>
					</td>
					<td width="68">
						<div align="center">
							<span class="STYLE4">密级</span>
						</div>
					</td>
					<td width="99" align="center">
						<select id="djmj" name="djmj">
							<option value="普通">普通</option>
							<option value="机密">机密</option>
							<option value="绝密">绝密</option>
						</select>
					</td>
					<td width="87">
						<div align="center">
							<span class="STYLE4">紧急程度</span>
						</div>
					</td>
					<td width="147" align="center">
						<select id="djjjcd" name="djjjcd">
							<option value="普通">普通</option>
							<option value="紧急">紧急</option>
							<option value="特急">特急</option>
						</select>
					</td>
				</tr>
				<tr>
					<td height="57">
						<div align="center">
							<span class="STYLE4">来文标题</span>
						</div>
					</td>
					<td colspan="5" align="center">
						<textarea name="djlwbt" cols="75" rows="2"></textarea>
					</td>
				</tr>
				<tr>
					<td height="53">
						<div align="center">
							<span class="STYLE4">来文单位</span>
						</div>
					</td>
					<td colspan="2" align="center">
						<textarea name="djlwdw" cols="33" rows="2"></textarea>
					</td>
					<td>
						<div align="center">
							<span class="STYLE4">来文日期</span>
						</div>
					</td>
					<td colspan="2" align="center">
						<input id="djlwrq" type="text" name="djlwrq"></input>
					</td>
				</tr>
				<tr>
					<td height="229" align="center">
						<div align="center">
							<p class="STYLE4">
								附
							</p>
							<p class="STYLE4">
								件
							</p>
						</div>
					</td>
					<td colspan="5" style="width:50px">
						
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>