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
<script type="text/javascript" src="<%=contextpath %>desktop/widgets/electronicfile/rollmanager/js/formCheck.js" defer="defer"></script>
<script  src="/js/JDS.js" type="text/javascript"></script>
<script type="text/javascript">
Ext.onReady(function(){
var date = '<ww:property value="rollbean.starttime"/>';
var enddate = '<ww:property value="rollbean.endtime"/>';

	var sxksdate = new Ext.form.DateField({
		applyTo:'starttime',
		fieldLabel:'日期',
		format:'Y-m-d',
		value:'<ww:property value="rollbean.starttime"/>'
	});
	
	var sxjsdate = new Ext.form.DateField({
		applyTo:'endtime',
		fieldLabel:'日期',
		format:'Y-m-d',
		value:'<ww:property value="rollbean.endtime"/>'
	});
});
function saveRoll(){
	var stime = document.getElementById("starttime").value;
	var etime = document.getElementById("endtime").value;
	
	if(checkForm('addRoll',stime,etime)){
		Ext.Ajax.request({
			url:'roll_save.action',
			method:'post',
			form:'addRoll',
			success:function(o){
				alert("添加成功");
				parent.Ext.getCmp('roll-list').getStore().reload();
				parent.Ext.getCmp('addRoll').close();
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
<form name="addRoll" id="addRoll" method="post" action="treemetting_addSession.action">
<table width="753" height="227" align="center" bgcolor="#dfe8f6" style="border:1px solid #99bbe8;line-height:30px;">
<input type="hidden" name="rollbean.uuid" id="uuid" value='<ww:property value="rollbean.uuid"/>'/>
<input type="hidden" name="rollbean.createdate" id="uuid" value='<ww:property value="rollbean.createdate"/>'/>
     <tr>
       <td align="center" colspan="4">案卷信息</td>
  </tr>
    <tr>
    <td width="144" align="right">案卷标题：</td>
    <td width="184"><input name="rollbean.rollname" type="text" id="rollname" value='<ww:property value="rollbean.rollname"/>'/><span style="color:red;">*</span></td>
    <td align="right">案卷号：</td>
    <td><input name="rollbean.rollnum" type="text" id="rolldirectnum" value='<ww:property value="rollbean.rollnum"/>'/><span style="color:red;">*</span></td>
  </tr>
  <tr>
    <td width="144" align="right">保管期限：</td>
    <td width="184"><input name="rollbean.timelimit" type="text" id="timelimit" value='<ww:property value="rollbean.timelimit"/>'/><span style="color:red;">*</span></td>
    <td align="right">密级：</td>
    <td width="272">
    <select name="rollbean.miji" value='<ww:property value="rollbean.miji"/>'>
    	<option value="秘密">秘密</option>
    	<option value="机密">机密</option>
    	<option value="绝密">绝密</option>
    </select><span style="color:red;">*</span></td>
  </tr>
  <tr>
    <td align="right">开始日期：</td>
    <td><div style="float:left"><input type="text" name="rollbean.starttime" id="starttime"  onkeydown="return false;" value='<ww:property value="rollbean.starttime"/>'/></div><div style="float:left;"><span style="color:red;">*</span></div></td>
    <td align="right">终止日期：</td>
    <td><div style="float:left"><input type="text" name="rollbean.endtime" id="endtime"  onkeydown="return false;" value='<ww:property value="rollbean.endtime"/>'/></div><div style="float:left"><span style="color:red;">*</span></div></td>
  </tr>
  <tr>
    <td align="right">页数：</td>
    <td><input name="rollbean.pagenum" type="text" id="pagenum" value='<ww:property value="rollbean.pagenum"/>'/><span style="color:red;">*</span></td>
    <td align="right">存放位置：</td>
    <td><input name="rollbean.savedirection" type="text" id="savedirection" value='<ww:property value="rollbean.savedirection"/>'/><span style="color:red;">*</span></td>
  </tr>
  <tr id="processtr">
  	<td align="right">年度：</td>
  	<td><input name="rollbean.yearnum" type="text" id="yearnum" value='<ww:property value="rollbean.yearnum"/>'/><span style="color:red;">*</span></td>
  	<td align="right"></td>
  	<td></td>
  	</tr>
</table>
</form>
</center>
</BODY>
 
</HTML>