<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%@ page import="net.itjds.j2ee.util.UUID"%>
<%@ page
		import= "java.util.Date"
		import ="java.text.SimpleDateFormat"
%>
<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";		
			
	Date date1=(Date)request.getAttribute("from");
	String lwDate="";
	if(date1!=null){
	   SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
	   lwDate = sfd.format(date1);
	   
	}
	
	//将创建时间接过来  不允许修改其格式（带有时分秒）
	String createDate = "";
	SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
	createDate = sfd.format((Date)request.getAttribute("wjzl.createDate"));
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
		<form id="update" name="update" action="wjzlMeteriaAction_update.action" method="post">
			<input type="hidden" name="tid" id="tid" value='<ww:property value="wjzl.uuid"/>' />
			<input type="hidden" name="wjzl.uuid" id="wjzl.uuid" value='<ww:property value="wjzl.uuid"/>' />
			<input type="hidden" name="wjzl.creatorUuid" id="wjzl.creatorUuid" value='<ww:property value="wjzl.creatorUuid"/>' />
			<input type="hidden" name="wjzl.createDate" id="wjzl.createDate" value='<%=createDate %>' />
			
			<table width="783" height="824" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#000000">
			  <tr>
			    <td height="88" colspan="4"  class="bt" align="center">文 件 资 料 修 改</td>
			  </tr>
			  <tr>
			    <td height="70" class="zt" align="center">登记人</td>
			    <td width="200">&nbsp;<input type="text" readonly="readonly" id="wjzl.creatorName" name="wjzl.creatorName" value="<ww:property value="$currPerson.name"/>" ></input></td>
			    <td width="110" class="zt" align="center">来文时间</td>
			    <td>
			    	<input type="text" name="wjzl.meterialDate" id="wjzl.meterialDate" />
			    </td>
			  </tr>
			  <tr>
			    <td height="70" class="zt" align="center">文号</td>
			    <td>&nbsp;<input type="text" value='<ww:property value="wjzl.meterialWenhao"/>' id ="wjzl.meterialWenhao" name="wjzl.meterialWenhao" size="50" /></td> 
			    <td class="zt" align="center">等级</td>
			    <td>
			    	<ww:select cssStyle="width:100px; font-size:18px;"  list="{'平急','加急','特急','特提'}" name="wjzl.meterialDengji" ></ww:select>
			    </td>
			  </tr>
			  <tr>
			    <td height="70" class="zt" align="center">编号</td>
			    <td>&nbsp;<input type="text" id="wjzl.meterialBianhao" value='<ww:property value="wjzl.meterialBianhao"/>' name="wjzl.meterialBianhao"  size="50" /></td>
			    <td class="zt" align="center">公开范围</td>
			    <td>
			    	<ww:select onchange="updateAnNiu()" cssStyle="width:100px; font-size:18px;"  list="#{'0':'不公开','1':'部分公开','2':'公开'}" name="wjzl.openType" id="wjzl.openType" ></ww:select>
			    </td>
			  </tr>
			  <tr>
			    <td height="85" class="zt" align="center">公开人</td>
			    <td colspan="2">&nbsp;<textarea rows="4" cols="50" name="sendrange" id="sendrange" onkeydown="return false;" ><ww:property value="sendrange"/></textarea></td>
			    <input type="hidden" name="personid" id="personid"  value='<ww:property value="personid"/>'  />
			    <td>
			    	 
			    	<ww:if test="wjzl.openType == 1">
			    		<input type="button" id="fsr" style="width:100px; font-size:18px;" value="选 择" onClick="createPersonPositionWindow('5015','')" /> 
			    	</ww:if>
			    	<ww:if test="wjzl.openType != 1">
			    		<input type="button" id="fsr" style="width:100px; font-size:18px;" disabled="disabled" value="选 择" onClick="createPersonPositionWindow('5015','')" /> 
			    	</ww:if> 
			    </td>
			  </tr>
			  <tr>
			    <td height="85" class="zt" align="center">资料目录</td>
			    <td colspan="2">&nbsp;<textarea name="buMenName" id="buMenName" rows="4" onkeydown="return false;" cols="50"><ww:property value="buMenName"  /></textarea>
					<input type="hidden" name="wjzl.catalogUuid" id="wjzl.catalogUuid" value='<ww:property value="wjzl.catalogUuid"/>' />
			    </td>
			    <td class="zt">&nbsp;明文</td>
			  </tr>
			  <tr>
			    <td width="110" height="85" class="zt" align="center">标题</td>
			    <td colspan="3">&nbsp;<textarea id="wjzl.meterialTitle" name="wjzl.meterialTitle" cols="78" rows="4"><ww:property value="wjzl.meterialTitle"/></textarea></td>
			  </tr>
			  <tr>
			    <td height="85" class="zt" align="center">副标题</td>
			    <td colspan="3">&nbsp;<textarea id="wjzl.meterialFubiaoti" name="wjzl.meterialFubiaoti" cols="78" rows="4"><ww:property value="wjzl.meterialFubiaoti"/></textarea></td>
			  </tr>
			  <tr>
			    <td height="85" class="zt" align="center">主题词</td>
			    <td colspan="3">&nbsp;<textarea  id="wjzl.meterialZhutici" name="wjzl.meterialZhutici" cols="78" rows="4"><ww:property value="wjzl.meterialZhutici"/></textarea></td>
			  </tr>
			  <tr>
			    <td height="100" class="zt" align="center">附件</td>
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
				value:'<%=lwDate%>'
				
			  });
			  
			 /* if(<ww:property value="wjzl.openType"/> == 1){
			  	document.getElementById("fsr").disabled=false;
			  }*/
			  
			});
			
			function updateAnNiu(){
				var value = document.getElementById("wjzl.openType").value;
				if("1" == value){
					document.getElementById("fsr").disabled=false;
				}else{
					document.getElementById("fsr").disabled=true;
					document.getElementById("personid").value="";
					document.getElementById("sendrange").value="";
				}
			}
</script>