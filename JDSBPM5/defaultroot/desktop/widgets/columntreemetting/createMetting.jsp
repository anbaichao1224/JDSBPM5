<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Map,com.opensymphony.xwork2.ActionContext,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%@page import="net.itjds.j2ee.util.UUID" %>
<%
String contextpath = request.getContextPath() + "/";
String uuid = (new UUID()).toString();
 %>
<HTML>
<HEAD>

<TITLE>新增会议</TITLE>

<style type="text/css">
<!--
body{width:800px;background-color:#dfe8f6;height:50%}
a:link {   
 COLOR:#000000;TEXT-DECORATION: none   
}   
a:visited {   
  COLOR: #969696; TEXT-DECORATION: none   
}   
a:active {   
  COLOR:#000000;TEXT-DECORATION: none   
}   
a:hover {   
COLOR: #ff6102; TEXT-DECORATION: none   
}

.formisnull{color:#FF0000}
-->
</style>
<SCRIPT LANGUAGE="JavaScript" type="text/javascript">
	var context='<%=contextpath%>';
	
</SCRIPT>
<script type="text/javascript" src="<%=contextpath%>js/extAll.js"></script>
<script type="text/javascript" src="<%=contextpath %>desktop/widgets/columntreemetting/js/mettingAll.js" defer="defer"></script>
<script type="text/javascript" src="<%=contextpath%>desktop/widgets/columntreemetting/js/mettingcheck.js" defer="defer"></script>

	
<script type="text/javascript">
	var obj;
	Ext.onReady(function(){
	Ext.QuickTips.init();
	var createdate = new Ext.form.DateField({
		applyTo:'createDate',
		value:new Date(),
		fieldLabel:'日期',
		emptyText:'请选择',
		format:'Y-m-d'
	});
	
	var hykssj = new Ext.form.DateField({
		applyTo:'hykssj',
		fieldLabel:'日期',
		emptyText:'请选择',
		allowBlank:false,
		format:'Y-m-d'
	});
	
	var hyjssj = new Ext.form.DateField({
		applyTo:'hyjssj',
		fieldLabel:'日期',
		emptyText:'请选择',
		format:'Y-m-d'
	});
	document.getElementById("hylxid").value='<ww:property value="mettingbean.hylxid"/>';
	
	var muuid = '<%=request.getParameter("mettinguuid")%>';
	if('<ww:property value="mettinguuid"/>'==''){
		document.getElementById("mettinguuid").value=muuid;
	}
	if(document.getElementById("liststatus").value==''){
		document.getElementById("optionnum").value="2";
		document.getElementById("isopen").disabled="disabled";
	}
	if('<ww:property value="mettingbean.tid"/>'!=""){
		document.getElementById("tid").value='<ww:property value="mettingbean.tid"/>';
		document.getElementById("createdate").value='<ww:property value="mettingbean.createdate"/>';
		document.getElementById("adddirection").value='<ww:property value="adddirection"/>';
		document.getElementById("liststatus").value='<ww:property value="liststatus"/>';
		//alert('<ww:property value="mettingbean.isopen"/>');
		document.getElementById("isopen").value='<ww:property value="mettingbean.isopen"/>';
		
	}
	if(document.getElementById("adddirection").value=="1"){
			document.getElementById("hylxid").disabled="disabled";
			document.getElementById("hylxtr").style.display="none";
			document.getElementById("isopen").disabled="disabled";
			document.getElementById("isopentr").style.display="none";
		}
});

	function savemetting(){
		if(form_validate('','createMetting')){
		Ext.Ajax.request({
			url:'treemetting_savesinglmetting.action',
			method:'post',
			form:'createMetting',
			success:function(o){
					//alert("su");
					
					parent.Ext.getCmp('form-columntree').root.reload();
					parent.Ext.getCmp('updateMatterInfo').close();
					
					
			}
		});
		}else{
			return false;
		}
	}
</script>
</HEAD>
<BODY>
<center>
<form name="createMetting" id="createMetting"  method="post" action="treemetting_addSession.action" onsubmit="return form_validate('','createMetting');"  enctype="multipart/form-data" >
		<input type="hidden" name="mettinguuid" id="mettinguuid" value='<ww:property value="mettinguuid"/>'/>
		<input type="hidden" name="adddirection" id="adddirection" value='<%=request.getParameter("adddirection")%>'/>
		<input type="hidden" name="tid" id="tid" value='<%=uuid %>'/>
		<input type="hidden" name="optionnum" id="optionnum" value='1'/>
		<input type="hidden" name="liststatus" id="liststatus" value='<%=request.getParameter("liststatus")%>'/>
		<table width="100%" height="30%" bgcolor="#dfe8f6" style="border:1px solid #99bbe8;line-height:30px;">
			<tr>
				<td colspan="4" align="center" style="line-height:40pxs"><font size="5">会议信息</font></td>
			</tr>
			<tr class="trf">
				<td width="14%" align="right">起草人：</td>
				<td class="tdv" width="36%" ><input type="text" name="creator"  onkeydown="return false;"  id="creator" theoremexpression="$currPerson.name" value='<ww:property value="$currPerson.name"/>'/><span class="formisnull">*</span></td>
				<td width="14%" align="right">起草时间：</td>
				<td class="tdv" width="36%">
					<input type="date" id="createdate" name="createdate"  onkeydown="return false;"   custype="DateType" value='<ww:date format="yyyy-MM-dd" name="$CurrTime.currDate"/>'/>						
				</td>
			</tr>
			<tr class="trf">
				<td class="tdl" width="14%" align="right">开始时间：</td>
				<td class="tdv" width="36%" ><input type="text" name="kssj"  onkeydown="return false;"  id="hykssj" value='<ww:property value="mettingbean.kssj"/>'/></td>
				<td width="14%" align="right">结束时间：</td>
				<td class="tdv" width="36%" ><input type="text" name="jssj"  onkeydown="return false;"  id="hyjssj" value='<ww:property value="mettingbean.jssj"/>' /></td>
			</tr>
	        <tr class="trf">
				<td class="tdl" width="14%" align="right">信息名称：</td>
				<td colspan="3" class="tdv" >
						<input name="xxname" type="text" id="xxname" size="100" value='<ww:property value="mettingbean.name"/>'/><span class="formisnull">*</span></td>
			</tr>
			<tr class="trf" id="hylxtr">
				<td width="14%" align="right">会议类型：</td>
				<td colspan="3" class="tdv" >
								<select name="hylxid" id="hylxid">
					  <option value="">请选择</option>
					  <ww:iterator value="mtbeanlist" status="rowstatus">
					  	
					  		<option value='<ww:property value="lxmc"/>'><ww:property value="lxmc"/></option>
					  	
					  </ww:iterator>
					  </select>					<span class="formisnull">*</span></td>
			</tr>
			<tr class="trf" id="isopentr">
				<td width="14%" align="right">是否开始：</td>
				<td colspan="3" class="tdv" >
								<select name="isopen" id="isopen">
					  <option value="否">否</option>
					 <option value="是">是</option>
					  </select>					<span class="formisnull">*</span></td>
			</tr>
			<tr>
					 <td class="tdv" colspan="4" align="center"><input type="button" onclick="savemetting('createMetting')" value="提交"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onclick="ceshi()" value="重置"/></td> 
					
				</tr>	
		</table>

	<!-- 意见输入 -->
</form>
</center>
</BODY>
</HTML>