<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Map,com.opensymphony.xwork2.ActionContext,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
String contextpath = request.getContextPath() + "/";
 %>
<HTML>
<HEAD>

<TITLE>新增会议</TITLE>

<style type="text/css">
<!--
body{width:800px;background-color:#dfe8f6;height:100%}
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
<script type="text/javascript" src="<%=contextpath%>desktop/widgets/metting/js/mettingAll.js" defer="defer"></script>

	
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

	
});
	function addsx(){
		var   frame   =   document.getElementById("matterframe"); 
		frame.onreadystatechange = null;
		document.getElementById("matterframe").src="addmatter.jsp";
		document.getElementById("addmatter").style.display="block";
		document.getElementById("isadd").value="addma";
	}
</script>
</HEAD>
<BODY>
<center>
<form name="createMetting" id="createMetting"  method="post" action="savemetting.action" onsubmit="return form_validate('','createMetting');"  enctype="multipart/form-data" >
		<input type="hidden" name="isadd" id="isadd"/>
		<input type="hidden" name="matterids" id="matterids"/>
		<table width="100%" height="30%" bgcolor="#dfe8f6" style="border:1px solid #99bbe8;line-height:30px;">
			<tr>
				<td colspan="4" align="center" style="line-height:40pxs"><font size="5">会议信息</font></td>
			</tr>
			<tr class="trf">
				<td width="14%" align="right">起草人：</td>
				<td class="tdv" width="36%" ><input type="text" name="creator" id="creator" theoremexpression="$currPerson.name" value='<ww:property value="$currPerson.name"/>'/><span class="formisnull">*</span></td>
				<td width="14%" align="right">起草时间：</td>
				<td class="tdv" width="36%">
					<input type="date" id="createdate" name="createdate"  custype="DateType" value='<ww:date format="yyyy-MM-dd" name="$CurrTime.currDate"/>'/>						
				</td>
			</tr>
			<tr class="trf">
				<td class="tdl" width="14%" align="right">会议开始时间：</td>
				<td class="tdv" width="36%" ><input type="text" name="hykssj" id="hykssj" value=''/></td>
				<td width="14%" align="right">会议结束时间：</td>
				<td class="tdv" width="36%" ><input type="text" name="hyjssj" id="hyjssj" value="" /></td>
			</tr>
	        <tr class="trf">
				<td class="tdl" width="14%" align="right">会议名称：</td>
				<td colspan="3" class="tdv" >
						<input name="hymc" type="text" id="hymc" size="100"/><span class="formisnull">*</span></td>
			</tr>
			<tr class="trf">
				<td width="14%" align="right">会议类型：</td>
				<td colspan="3" class="tdv" >
								<select name="hylx" id="hylx" onchange="showmatter()">
					  <option value="">请选择</option>
					  <option value="市委会议">市委会议</option>
					  <option value="常委会议">常委会议</option>
					  <option value="市全体会议">市全体会议</option>
					  </select>					<span class="formisnull">*</span></td>
			</tr>
				<tr class="trf">
					<td width="14%" rowspan="2" valign="top" align="right">事项列表：</td>
					<td class="tdv" colspan="3" ><div id="cymatter" style="overflow:hidden;height:auto;">
					<table>
						
					</table></div><input type="button" class="button" value="增 加" onClick="addmatter()" />
						</td>
				</tr>
			<tr>
					 <td class="tdv" colspan="4" align="center"><input type="submit" value="提交"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onclick="ceshi()" value="重置"/></td> 
					
				</tr>	
		</table>

	<!-- 意见输入 -->
</form>
</center>
</BODY>
</HTML>