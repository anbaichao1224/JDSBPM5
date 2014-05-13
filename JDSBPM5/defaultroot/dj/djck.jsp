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
				height:100,
				width:200,
				emptyText:'请选择',
				format:'Y-m-d',
				value:'<ww:property value="rbean.lwddate"/>'	
			  });
			});
		</script>
	</head>
	<body>
		<form id="updateDj" action="SwdjAction_updateDj.action" method="post">
		<input type="hidden" name="tid" id="tid" value='<ww:property value="rbean.uuid"></ww:property>' />
		<input type="hidden" name="xmlmodel" id="xmlmodel" value='<ww:property value="xmlmodel"></ww:property>' />
		<input type="hidden" name="uuid" id="uuid" value='<ww:property value="rbean.uuid"></ww:property>' />
			<table align="center" width="807" height="696" border="1"
				cellpadding="0" cellspacing="0">
		  <tr>
					<td height="104" colspan="6">
						<div align="center" class="STYLE3">
							收文登记信息表						</div>					</td>
				</tr>
				<tr>
					<td width="110" height="65" bgcolor="#d2dff0">
						<div align="center">
							<span class="STYLE4">收文<br>编号</span>						</div>				  </td>
					<td width="248" align="left">
						<input name="djbh" type="text" class="STYLE4" value='<ww:property value="rbean.sn"></ww:property>' size="20">
						</input>				  </td>
					
					<td width="108" bgcolor="#d2dff0">
						<div align="center">
							<span class="STYLE4">等级</span>						</div>				  </td>
					<td width="214" align="left">
						<select name="djjjcd" class="STYLE4" id="djjjcd">
			
							<option value="平急">平急</option>
							<option value="加急">加急</option>
							<option value="特急">特急</option>
							<option value="特提">特提</option>
						</select>
						<script type="text/javascript">
							
							document.getElementById('djjjcd').value = '<ww:property value="rbean.emergency"></ww:property>';
						</script>				
						
									  </td>
				</tr>
				
					<tr>
					<td height="65" bgcolor="#d2dff0" class="tdClass" >
						<div align="center">
							<span class="STYLE4">原文<br>
							编号</span>						</div>					</td>
					<td align="left" class="tdClass"><label>
					  <input name="lwbh" type="text" class="STYLE4" id="lwbh" value='<ww:property value="rbean.lwbh"/>' size="20">
					</label></td>
					
			  <td bgcolor="#d2dff0" class="tdClass">
						<div align="center">
							<span class="STYLE4">登记人</span>						</div>					</td>
					<td colspan="2" align="left" class="tdClass">
						<input name="djr" type="text" class="STYLE4" id="djr" value='<ww:property value="$currPerson.name"/>'></input>					</td>
				</tr>
				<tr>
					<td height="72" bgcolor="#d2dff0">
						<div align="center">
							<span class="STYLE4">来电<br>
					  编号</span>						</div>					</td>
					<td align="left"><input name="ldbh" type="text" class="STYLE4" id="ldbh" value='<ww:property value="rbean.ldbh"/>' size="20"></td>
					<td align="center" bgcolor="#d2dff0"><span class="STYLE4">来文<br>
				    日期</span></td>
					<td colspan="3" align="left"><input name="djlwrq" type="text" class="STYLE4" id="djlwrq">						
				    </input>					</td>
				</tr>
				<tr>
					<td height="88" bgcolor="#d2dff0">
					  <div align="center">
				  <span class="STYLE4">密级<br></span>						</div>					</td>
				  <td align="center"><div align="left"> <input name="mj" type="text" readOnly=true class="STYLE4" id="mj" value="明文"> </div></td>
			  <td height="77" bgcolor="#d2dff0">
						<div align="center">
							<span class="STYLE4">发文<br>
					  单位</span>						</div>					</td>
				    <td colspan="3" align="left"><input name="djlwdw"  class="STYLE4" value='<ww:property value="rbean.department"/>'/></td>
			    </tr>
               <tr>
					<td height="70" class="tdClass"  bgcolor="#d2dff0">
						<div align="center">
							<span class="STYLE4">来文<br>标题</span>						
						</div>					</td>
					<td colspan="7" align="left" class="tdClass"><textarea name="djlwbt" cols="50" rows="2" class="STYLE4"><ww:property value="rbean.docbt"></ww:property></textarea>
									
					</td>
				</tr>

                
		  <tr>
					<td height="150" align="center" class="tdClass"  bgcolor="#d2dff0">
						<div align="center">
							<p class="STYLE4">
								附							</p>
							<p class="STYLE4">
								件							</p>
						</div>					</td>
					<td colspan="7" style="width:50px" class="tdClass">
						
						<ww:action name="sdocexchangefileinclude" executeResult="true"></ww:action>			
					</td>
				</tr>

		  </table>
	</form>
	</body>
</html>