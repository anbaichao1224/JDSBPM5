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
var processid = '<ww:property value="mettingbean.processdefid"/>';
var bsta = '<ww:property value="mettingbean.blstatus"/>';
var id = '<ww:property value="mettingbean.tid"/>';  
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
		applyTo:'kssj',
		fieldLabel:'日期',
		format:'Y-m-d'
	});
	
	var sxjsdate = new Ext.form.DateField({
		applyTo:'jssj',
		fieldLabel:'日期',
		format:'Y-m-d'
	});
	if('<ww:property value="matterInfoListBean.uuid"/>'!=""){
		document.getElementById("savesm").disabled="disabled";
	}
	if(document.getElementById("liststatus").value==''){
		document.getElementById("optionnum").value="2";
	}
	if('<ww:property value="mettingbean.tid"/>'!=""){
		document.getElementById("parentid").value='<ww:property value="parentid"/>';
		document.getElementById("tid").value='<ww:property value="mettingbean.tid"/>';
		document.getElementById("createdate").value='<ww:property value="mettingbean.createdate"/>';
	}
	var sxlx = document.getElementById("sxlx").value;
		if(sxlx=="1"){
			document.getElementById("processdefid").disabled=false;
			document.getElementById("processtr").style.display="block";
			document.getElementById("sxfj").style.display="none";
			queryProcess();
		}
});

</script>
</HEAD>
<BODY>

<center>
<form name="addmatter" id="addmatter" method="post" action="treemetting_addSession.action">
<table width="753" height="227" align="center" bgcolor="#dfe8f6" style="border:1px solid #99bbe8;line-height:30px;">
<input type="hidden" name="parentid" id="parentid" value='<%=request.getParameter("parentid") %>'/>
<input type="hidden" name="tid" id="tid" value='<%=uuid %>'/><input type="hidden" name="optionnum" id="optionnum" value='1'/>
<input type="hidden" name="mettinguuid" id="mettinguuid" value='<%=request.getParameter("mettinguuid") %>'/>
<input type="hidden" name="adddirection" id="adddirection" value='2'/>
<input type="hidden" name="liststatus" id="liststatus" value='<%=request.getParameter("liststatus")%>'/>
    <tr>
    <td width="144" align="right">起草人：</td>
    <td width="184"><input name="creator" type="text" id="creator" theoremexpression="$currPerson.name" value='<ww:property value="$currPerson.name"/>' /></td>
    <td align="right">起草时间：</td>
    <td width="272"><input name="createdate" type="text" id="createdate" value='<ww:property value="mettingbean.createdate"/>'/></td>
  </tr>
  <tr>
    <td align="right">事项类型：</td>
    <td colspan="3"><select name="sxlx" id="sxlx" onchange="sxlxchange()">
      <option value="0">普通</option>
      <option value="1" >走流程</option>
    </select>    
     <script type="text/javascript">
    	var sxlx = document.getElementById("sxlx");
		var datasxlx = '<ww:property value="mettingbean.sxlx"/>';
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
    <td><input type="text" name="kssj" id="kssj"  onkeydown="return false;"  value='<ww:property value="mettingbean.kssj"/>' /></td>
    <td align="right">结束时间：</td>
    <td><input type="text" name="jssj" id="jssj"  onkeydown="return false;"  value='<ww:property value="mettingbean.jssj"/>' /></td>
  </tr>
  <tr>
    <td align="right">事项名称：</td>
    <td colspan="3"><input name="xxname" type="text" id="xxname" value='<ww:property value="mettingbean.name"/>' size="75" /></td>
  </tr>
  <tr>
    <td align="right">办理人：</td>
    <td colspan="3"><input name="personname" type="text" id="personname" onkeydown="return false;"  size="50" value='<ww:property value="mettingbean.blrmc"/>' /><input type="hidden" name="personid" id="personid" value='<ww:property value="mettingbean.blrid"/>'/>
    <input name="choice" type="button" id="choice" value="选择" onclick="createPersonPositionWindow('5015','')" /></td>
  </tr>
  <tr id="processtr" style="display:none">
  	<td align="right">流程列表:</td>
  	<td><select name="processdefid" id="processdefid" disabled="disabled">
  	</select></td>
  </tr>
  <tr>
    <td valign="top" align="right">事项内容：</td>
    <td colspan="3"><textarea name="sxnr" cols="75" rows="10" id="sxnr"><ww:property value="mettingbean.sxnr"/></textarea></td>
  </tr>
	<tr id="sxfj">
                    <td valign="top" align="right">附&nbsp;&nbsp;&nbsp;&nbsp;件</td>
                    <td colspan="3">
                    	<ww:action name="mfileinclude" executeResult="true"></ww:action>
                    </td>
                </tr>
  <tr>
    <td colspan="4" align="center"><input type="button" name="savematter" value="保存" onclick="savemetting('addmatter')" />&nbsp;&nbsp;
    <input type="button" name="tc" value="退出" onclick="winclose()" /></td>
  </tr>
</table>
</form>
</center>
</BODY>
 
</HTML>