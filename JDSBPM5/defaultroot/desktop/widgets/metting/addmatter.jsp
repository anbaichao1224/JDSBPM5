<%@ page contentType="text/html; charset=UTF-8"%>
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
body {font-family: "宋体", "Arial"; font-size: 9pt;background-color:#dfe8f6}
td {font-family: "宋体", "Arial"; font-size: 9pt} 
-->
		</style>
<script type="text/javascript">
	var context="/";
</script>
 
<script type="text/javascript" src="/js/extAll.js"></script>
<script type="text/javascript" src="<%=contextpath %>desktop/js/CreateGrid.js"></script>
<script  src="/js/JDS.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=contextpath %>desktop/widgets/metting/js/mettingAll.js" defer="defer"></script>
<script type="text/javascript" src="<%=contextpath %>desktop/widgets/metting/js/matterAll.js" defer="defer"></script>
<script type="text/javascript">
var processid = '<ww:property value="matterInfoListBean.processdefid"/>';
var bsta = '<ww:property value="matterInfoListBean.blstatus"/>';
var id = '<ww:property value="matterInfoListBean.uuid"/>';  
var newid = '<%=uuid%>';
Ext.onReady(function(){
	var cdate = new Ext.form.DateField({
		applyTo:'createdate',
		value:new Date(),
		fieldLabel:'日期',
		emptyText:'请选择',
		format:'Y-m-d'
	});
	
	var sxksdate = new Ext.form.DateField({
		applyTo:'sxkssj',
		fieldLabel:'日期',
		format:'Y-m-d'
	});
	
	var sxjsdate = new Ext.form.DateField({
		applyTo:'sxjssj',
		fieldLabel:'日期',
		format:'Y-m-d'
	});
	document.getElementById("sxssjd").value='<ww:property value="matterInfoListBean.sxssjd"/>';
	document.getElementById("sxsslx").value=window.parent.document.getElementById("hylx").value;
	if('<ww:property value="matterInfoListBean.uuid"/>'!=""){
		document.getElementById("savesm").disabled="disabled";
	}
	var sxlx = document.getElementById("sxlx").value;
		if(sxlx=="1"){
			document.getElementById("processtr").style.display="block";
			document.getElementById("sxfj").style.display="none";
			queryProcess();
		}
});

//表单验证


</script>
</HEAD>
<BODY>

<center>
<form name="addmatter" id="addmatter" action="savematter.action">
<input type="hidden" name="mstatus" id="mstatus"/>
<input type="hidden" name="sxsslx" id="sxsslx"/>
<table width="753" height="227" align="center" bgcolor="#dfe8f6" style="border:1px solid #99bbe8;line-height:30px;">
<input type="hidden" name="sxxxid" id="sxxxid"/>
<input type="hidden" name="uuid" id="uuid" value='<ww:property value="matterInfoListBean.uuid"/>'/>
    <tr>
    <td width="144" align="right">起草人：</td>
    <td width="184"><input name="creator" type="text" id="creator" theoremexpression="$currPerson.name" value='<ww:property value="$currPerson.name"/>' /></td>
    <td align="right">起草时间：</td>
    <td width="272"><input name="createdate" type="text" id="createdate" value='<ww:property value="matterInfoListBean.createdate"/>'/></td>
  </tr>
  <script type="text/javascript">
  	var bsta = '<ww:property value="matterInfoListBean.blstatus"/>';
  	
	if(bsta==""){
		document.getElementById("sxxxid").value='<%=uuid%>';
		document.getElementById("uuid").value="";
	}else{
		document.getElementById("sxxxid").value='<ww:property value="matterInfoListBean.uuid"/>';
		document.getElementById("creator").value='<ww:property value="matterInfoListBean.creator"/>';
	}
  </script>
  <tr>
    <td align="right">所属阶段：</td>
    <td><select name="sxssjd" id="sxssjd">
      <option value="">请选择</option>
      <option value="会前协调工作">会前协调工作</option>
      <option value="会前准备工作">会前准备工作</option>
      <option value="会议期间工作">会议期间工作</option>
      <option value="会后工作">会后工作</option>
    </select></td>
    <td align="right">事项类型：</td>
    <td><select name="sxlx" id="sxlx" onchange="sxlxchange()">
      <option value="0">普通</option>
      <option value="1" >走流程</option>
    </select>    
     <script type="text/javascript">
    	var sxlx = document.getElementById("sxlx");
		var datasxlx = '<ww:property value="matterInfoListBean.sxlx"/>';
		for(var i=0;i<sxlx.options.length;i++){
			if(sxlx.options[i].value==datasxlx){
				sxlx.options[i].selected='selected';
				break;
			}
		}
    </script>
    </td>
  </tr>
  <tr>
    <td align="right">开始时间：</td>
    <td><input type="text" name="sxkssj" id="sxkssj" value='<ww:property value="matterInfoListBean.sxkssj"/>' /></td>
    <td align="right">结束时间：</td>
    <td><input type="text" name="sxjssj" id="sxjssj" value='<ww:property value="matterInfoListBean.sxjssj"/>' /></td>
  </tr>
  <tr>
    <td align="right">事项名称：</td>
    <td colspan="3"><input name="sxmc" type="text" id="sxmc" value='<ww:property value="matterInfoListBean.sxmc"/>' size="75" /></td>
  </tr>
  <tr>
    <td align="right">办理人：</td>
    <td colspan="3"><input name="personname" type="text" id="personname" size="50" value='<ww:property value="matterInfoListBean.personname"/>' /><input type="hidden" name="personid" id="personid" value='<ww:property value="matterInfoListBean.personid"/>'/>
    <input name="choice" type="button" id="choice" value="选择" onclick="createPersonPositionWindow('5015','')" /></td>
  </tr>
  <tr id="processtr" style="display:none">
  	<td align="right">流程列表:</td>
  	<td><select name="processdefid" id="processdefid">
  	</select></td>
  </tr>
  <tr>
    <td valign="top" align="right">事项内容：</td>
    <td colspan="3"><textarea name="sxnr" cols="75" rows="10" id="sxnr"><ww:property value="matterInfoListBean.sxnr"/></textarea></td>
  </tr>
	<tr id="sxfj">
                    <td valign="top" align="right">附&nbsp;&nbsp;&nbsp;&nbsp;件</td>
                    <td colspan="3">
                    	<!-- <script type="text/javascript">
                    		window.top.proceshi()
                    	</script>
                    	-->
                    	<ww:action name="mfileinclude" executeResult="true"></ww:action>
                    </td>
                </tr>
  <tr>
    <td colspan="4" align="center"><input type="button" name="savematter" value="保存" onclick="mattersave('save')" />&nbsp;&nbsp;
    <input type="button" name="saveasm" id="savesm" value="保存到常用事项" onclick="mattersave('saveas')" />&nbsp;&nbsp;
    <input type="button" name="tc" value="退出" onclick="winclose()" /></td>
  </tr>
</table>
</form>
</center>
</BODY>
 
</HTML>