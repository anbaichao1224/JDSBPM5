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
.tableClass{
 border-collapse: collapse; 
} 
.tdClass{
    border:1px solid #000000;
}

.STYLE6 {font-size: 20px; font-weight: bold; }
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
	<form id="addZDj" name="addZDj" action="/SwdjAction_addDj.action"  method="post">
		<input type="hidden" name="tid" id="tid" value='<ww:property value="senddao.uuid"></ww:property>' />
		<input type="hidden" name="xmlmodel" id="xmlmodel" value="dj-zyl" />
		<input type="hidden" name="inceptid" id="inceptid" value='<ww:property value="inceptid"></ww:property>' />
		<input type="hidden" name="UUID" id="UUID" value='<ww:property value="sxxxid"></ww:property>' />
			<table align="center" width="771" height="654" class="tableClass">
<tr>
					<td height="104" colspan="8" class="tdClass">
						<div align="center" class="STYLE3">
							收文信息登记表	</div></td>
				</tr>
				<tr>
					<td width="102" height="60" bgcolor="#d2dff0" class="tdClass">
						<div align="center">
							<span class="STYLE4">收文<br>编号</span></div></td>
					<td align="left" valign="middle" class="tdClass">
				  		<input class="STYLE4" name="djbh" type="text"></input>	</td>
				   <td width="99" bgcolor="#d2dff0" class="tdClass">
				  		<div align="center" class="STYLE4">
				  		等级	</div></td>
					  <td width="313" align="left" class="tdClass">
				   <input class="STYLE4" id="djjjcd" name="djjjcd" value="<ww:property value="senddao.sendJjcd"/>"></input></td>
				</tr>
                <tr>
					<td height="70" bgcolor="#d2dff0" class="tdClass">
						<div align="center">
							<span class="STYLE4">原文<br>
					  编号</span></div></td>
					<td align="left" class="tdClass"><label> <input class="STYLE4" type="text" name="lwbh" id="lwbh" value="<ww:property value="senddao.sendWh"/>"></label></td>
					<td bgcolor="#d2dff0" class="tdClass">
							<div align="center">
								<span class="STYLE4">登记人</span></div></td>
					<td colspan="2" align="left" class="tdClass">
						<input class="STYLE4" id="djr" type="text" name="djr" 
						value='<ww:property value="$currPerson.name"/>'></input></td></tr>
						<tr>
					<td height="79" bgcolor="#d2dff0" class="tdClass">
					  <div align="center">
					  <span class="STYLE4">来电<br>
			             编号</span></div>	</td>
						<td align="left" class="tdClass"><label>
					    <input name="ldbh" type="text" class="STYLE4" id="ldbh" value="<ww:property value="senddao.sendType"/>">
					</label></td>
					<td bgcolor="#d2dff0" class="tdClass"><div align="center">
					<span class="STYLE4">来文<br>
						  日期</span></div></td>
					<td colspan="4" align="left" class="tdClass"><input  class="STYLE4" id="djlwrq" type="text" name="djlwrq"  value='<ww:property value="inceptdao.inceptTime"/>'></td>
				</tr>
					<tr>
					<td height="88" bgcolor="#d2dff0" class="tdClass"><div align="center">
				  <span class="STYLE4">密级<br></span></div></td>
				  <td align="center" class="tdClass"><div align="left"> <input name="mj" type="text" readOnly=true class="STYLE4" id="mj" value="明文"> </div></td>
				    <td height="77" bgcolor="#d2dff0" class="tdClass"><div align="center">
					<span class="STYLE4">发文<br>
					  单位</span></div></td>
				    <td colspan="7" align="left" class="tdClass"><input name="djlwdw"  class="STYLE4" value='<ww:property value="senddao.senddept"/>'/></td>
			    </tr>
				 <tr>
					<td height="70" class="tdClass"  bgcolor="#d2dff0">
						<div align="center">
							<span class="STYLE4">来文<br>标题</span></div></td>
						<td colspan="5" align="left" class="tdClass"><textarea class="STYLE4" name="djlwbt" cols="50" rows="2"><ww:property value="senddao.sendTitle"/></textarea></td>
				</tr>
		<tr>
					<td height="150" align="center" bgcolor="#d2dff0" class="tdClass">
						<div align="center">
							<p class="STYLE4">
								附							</p>
							<p class="STYLE4">
								件							</p>
						</div>					</td>
					<td colspan="6" style="width:50px" class="tdClass">
						
						<ww:action name="docexchangefileinclude" executeResult="true"></ww:action></td>
				</tr>

		  </table>
	</form>
	</body>
</html>