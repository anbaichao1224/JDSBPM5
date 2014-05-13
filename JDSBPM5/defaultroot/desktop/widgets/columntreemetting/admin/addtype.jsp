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
<script  src="/js/JDS.js" type="text/javascript"></script>
<script type="text/javascript">
Ext.onReady(function(){
var cdate = new Ext.form.DateField({
		applyTo:'createdate',
		value:new Date(),
		fieldLabel:'日期',
		emptyText:'请选择',
		format:'Y-m-d'
	});
});

</script>
</HEAD>
<BODY>

<center>
<form name="addtype" id="addtype" action="mtype_save.action" method="post">
<input type="hidden" name="uuid" id="uuid" value='<ww:property value="mtbean.uuid"/>'/>
<table width="753" align="center" bgcolor="#dfe8f6" style="border:1px solid #99bbe8;line-height:30px;">
    <tr>
    <td width="144" align="right">起草人：</td>
    <td width="184"><input name="creator" type="text" id="creator"  onkeydown="return false;"  theoremexpression="$currPerson.name" value='<ww:property value="$currPerson.name"/>' /></td>
    <td align="right">起草时间：</td>
    <td width="272"><input name="createdate" id="createdate" type="text" id="createdate"  onkeydown="return false;"  value='<ww:property value="mtbean.createdate"/>'/></td>
  </tr>
  <tr>
    <td align="right">类型名称：</td>
    <td colspan="3"><input name="lxmc" type="text" id="lxmc" value='<ww:property value="mtbean.lxmc"/>' size="75" /></td>
  </tr>
  <tr>
    <td valign="top" align="right">类型描述：</td>
    <td colspan="3"><textarea name="description" cols="75" rows="10" id="description"><ww:property value="mtbean.description"/></textarea></td>
  </tr>
    <td colspan="4" align="center"><input type="button" name="savematter" value="保存" onclick="savetype()" />&nbsp;&nbsp;
    </td>
  </tr>
</table>
</form>
</center>
</BODY>
 
</HTML>