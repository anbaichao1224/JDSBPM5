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
<script type="text/javascript" src="<%=contextpath %>desktop/widgets/columntreemetting/js/mettingAll.js" defer="defer"></script>
<script type="text/javascript" src="<%=contextpath %>desktop/widgets/columntreemetting/js/mettingcheck.js" defer="defer"></script>
<script  src="/js/JDS.js" type="text/javascript"></script>
<script type="text/javascript">
function zj(){
  	var rid = document.getElementById("rollid").value;
  	if(rid==''){
  		alert("请选择案卷");
  		return;
  	}
  	var ids = '<ww:property value="dataIds"/>';
	Ext.Ajax.request({
		url:'/data_updateStatus.action',
		method:'post',
		params:{dataIds:ids,'dbean.rollid':rid},
		success:function(o){
			alert("添加成功");
			parent.Ext.getCmp('data-list').getStore().reload();
			parent.Ext.getCmp('selectRoll').close();
		}
	});
}
</script>
</HEAD>
<BODY>

<center>
<form name="addRoll" id="addRoll" method="post" action="data_save.action">
<table width="100%" height="100" align="center" bgcolor="#dfe8f6" style="border:1px solid #99bbe8;line-height:30px;">
  <tr>
    <td width="100" align="right">选择案卷：</td>
    <td colspan="2">
    <select name="rollid" id="rollid">
    	<ww:iterator value="rolllist" status="rowstatus">
    		<option value='<ww:property value="uuid"/>'><ww:property value="rollname"/></option>
    	</ww:iterator>
    </select></td>
    <td align="center"><input type="button" name="savematter" value="确定" onclick="zj()" />&nbsp;&nbsp;</td>
  </tr>
</table>
</form>
</center>
</BODY>
 
</HTML>