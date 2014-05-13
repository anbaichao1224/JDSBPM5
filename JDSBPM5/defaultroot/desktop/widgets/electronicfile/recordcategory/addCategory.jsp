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
.check{color:#FF0000}
-->
		</style>
<script type="text/javascript">
	var context="/";
</script>
 
<script type="text/javascript" src="/js/extAll.js"></script>
<script type="text/javascript">
Ext.onReady(function(){
	if('<%=request.getParameter("pid")%>'!='null'){
		//alert("a");
		document.getElementById("parentid").value='<%=request.getParameter("pid")%>';
	}
	if('<ww:property value="rbean.uuid"/>'!=''){
		document.getElementById("creatortr").style.display="block";
		document.getElementById("createdatetr").style.display="block";
	}
});
function saveCategory(){
	Ext.Ajax.request({
		url:'/category_save.action',
		method:'post',
		form:'saveRoom',
		success:function(o){
			alert("操作成功请手动刷新");
			parent.Ext.getCmp('categoryInfo').close();
		}
	})
}
</script>
</HEAD>
<BODY>

<center>
<form name="saveRoom" id="saveRoom" action="/category_save.action">
<table width="100%" height="100%" align="left" bgcolor="#dfe8f6" style="border:1px solid #99bbe8;line-height:20px;">
<input type="hidden" name="rbean.uuid" id="uuid" value='<ww:property value="rbean.uuid"/>'/>
<input type="hidden" name="rbean.parentid" id="parentid" value='<ww:property value="rbean.parentid"/>'/>
<input type="hidden" name="rbean.creatdate" id="parentid" value='<ww:property value="rbean.createdate"/>'/>
	<tr>
	<td colspan="4" align="center">
		<font size="5">目录信息</font>	
	</td></tr>
  <tr>
    <td align="right">目录名称：</td>
    <td colspan="3"><input name="rbean.categoryname" type="text" id="manme" size="30" value='<ww:property value="rbean.categoryname"/>' /><span class="check">*</span></td>
  </tr>
  <tr id="creatortr" style="display:none;">
    <td align="right">创建人：</td>
    <td colspan="3"><input name="rbean.creator" type="text" onkeydown="return false;"  id="creator" size="30" value='<ww:property value="rbean.creator"/>' /><span class="check">*</span></td>
  </tr>
  <tr id="createdatetr" style="display:none;">
    <td align="right">创建时间：</td>
    <td colspan="3"><input name="rbean.createdate" type="text" onkeydown="return false;" id="createdate" size="30" value='<ww:property value="rbean.createdate"/>' /><span class="check">*</span></td>
  </tr>
  <tr>
    <td colspan="4" align="center"><input type="button" name="savematter" value="保存" onclick="saveCategory()" />&nbsp;&nbsp;
  	</td>
  </tr>
</table>
</form>
</center>
</BODY>
 
</HTML>