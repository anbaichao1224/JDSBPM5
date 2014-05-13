<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%@page import="net.itjds.j2ee.util.UUID"%>
<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";		
			
	String catalogUuid = (String)request.getAttribute("catalogUuid");
	String uuid = (new UUID()).toString();
%>
<html>
	<head>
		<base href="<%=basePath%>"></base>
		<script>var context='<%=path%>';</script>
		
		<style type="text/css">
		<!--
		.bt {
			font-size: 32px;
			font-weight: bold;
		}
		.zt {
			font-size: 18px;
		}
		.ys {
			color: #FF0000;
		}
		.STYLE2 {font-family: "宋体"}
		-->
		</style>
		
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />

		<script type="text/javascript" src="<%=path%>js/extAll.js"></script>
		<script type="text/javascript" src="<%=path%>/js/ext/TabCloseMenu.js"></script>
		<script type="text/javascript" src="<%=path%>desktop/js/CreateGrid.js"></script>
		<script type="text/javascript" src="/desktop/wenjianziliaoku/wjzlym/wjzlTree.js" defer="defer"></script>
		<script src="/js/JDS.js" type="text/javascript"></script>
	
	</head>
	<body >
		<form id="add" name="add" action="wjzlMeteriaAction_add.action?catalogId=<ww:property value="catalogUuid" />"  method="post">
			<input type="hidden" name="tid" id="tid" value='<%=uuid %>' />
			<input type="hidden" name="UUID" id="UUID" value='<ww:property value="sxxxid"></ww:property>' />
			<table width="783" height="824" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#000000">
			  <tr>
			    <td height="88" colspan="4"  class="bt" align="center">文 件 资 料 登 记</td>
			  </tr>
			  <tr>
			    <td height="70" class="zt" align="center">登记人</td>
			    <td width="200">&nbsp;<input type="text" onkeydown="return false;" id="wjzl.creatorName" name="wjzl.creatorName" value="<ww:property value="$currPerson.name"/>" ></input></td>
			    <td width="110" class="zt" align="center">来文时间</td>
			    <td><input type="text" name="wjzl.meterialDate" id="wjzl.meterialDate"></input></td>
			  </tr>
			  <tr>
			    <td height="70" class="zt" align="center">文号</td>
			    <td>&nbsp;<input type="text" id ="wjzl.meterialWenhao" name="wjzl.meterialWenhao" size="50" /></td> 
			    <td class="zt" align="center">等级</td>
			    <td>
			    	&nbsp;<select id="wjzl.meterialDengji" style="width:100px; font-size:18px; " name="wjzl.meterialDengji">
						<option value="平急">平急</option>
						<option value="加急">加急</option>
						<option value="特急">特急</option>
						<option value="特提">特提</option>
					</select>
			    </td>
			  </tr>
			  <tr>
			    <td height="70" class="zt" align="center">编号</td>
			    <td>&nbsp;<input type="text" id="wjzl.meterialBianhao" name="wjzl.meterialBianhao"  size="50" /></td>
			    <td class="zt" align="center">公开范围</td>
			    <td colspan="2">
			    	&nbsp;<select id="wjzl.openType" name="wjzl.openType" onchange="updateAnNiu()" style="width:100px; font-size:18px; ">
						<option value="0">不公开</option>
						<option value="1">部分公开</option>
						<option value="2">公开</option>
					</select>	
			    </td>
			  </tr>
			  <tr>
			    <td height="85" class="zt" align="center">公开人</td>
			    <td colspan="2">&nbsp;<textarea rows="4" cols="50" name='sendrange' id="sendrange" onkeydown="return false;" ></textarea></td>
			    <ww:hidden  name="personid" id="personid"></ww:hidden>
			    <td>&nbsp;<input type="button" id="fsr" style="width:100px; font-size:18px;" disabled="disabled" value="选 择" onClick="createPersonPositionWindow('5015','')" /> </td>
			  </tr>
			  <tr>
			    <td height="85" class="zt" align="center">资料目录</td>
			    <td colspan="2">&nbsp;<textarea rows="4" onkeydown="return false;" cols="50"><ww:property value="buMenName"  /></textarea>
				    <ww:hidden  id="wjzl.catalogUuid" name="wjzl.catalogUuid" value="<%=catalogUuid %>" ></ww:hidden>
			    </td>
			    <td class="zt">&nbsp;明文</td>
			  </tr>
			  <tr>
			    <td width="110" height="85" class="zt" align="center">标题<span class="ys" >*</span></td>
			    <td colspan="3">&nbsp;<textarea id="wjzl.meterialTitle" name="wjzl.meterialTitle" cols="78" rows="4"></textarea></td>
			  </tr>
			  <tr>
			    <td height="85" class="zt" align="center">副标题</td>
			    <td colspan="3">&nbsp;<textarea id="wjzl.meterialFubiaoti" name="wjzl.meterialFubiaoti" cols="78" rows="4"></textarea></td>
			  </tr>
			  <tr>
			    <td height="85" class="zt" align="center">主题词</td>
			    <td colspan="3">&nbsp;<textarea  id="wjzl.meterialZhutici" name="wjzl.meterialZhutici" cols="78" rows="4"></textarea></td>
			  </tr>
			  <tr>
			    <td height="100" class="zt" align="center">附件<span class="ys" >*</span></td>
			    <td colspan="3">&nbsp;<ww:action name="docexfileinclude" executeResult="true"></ww:action></td>
			  </tr>
			</table>
		</form>
	</body>
</html>


	<script type="text/javascript">
			Ext.onReady(function(){
			  var kssj = new Ext.form.DateField({
				applyTo:'wjzl.meterialDate',
				fieldLabel:'日期',
				emptyText:'请选择',
				format:'Y-m-d',
				value:'<ww:property value="$CurrTime.currDate"/>'	
			  });
			  
			});
			
			function updateAnNiu(){
				var value = document.getElementById("wjzl.openType").value;
				if("1" == value){
					//alert(value);
					document.getElementById("fsr").disabled=false;
				}else{
					document.getElementById("fsr").disabled=true;
					document.getElementById("personid").value="";
					document.getElementById("sendrange").value="";
				}
			}
			
			//获得上传附件的数量
			function getCount(){
				var obj = Ext.getCmp(getActiveFormId());
				return obj.getStore().getCount();
			}
			
		</script>
		