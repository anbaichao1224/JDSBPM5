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
		<!-- background-color:#dfe8f6-->
body {font-family: "宋体", "Arial"; font-size: 9pt}
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
	if('<ww:property value="dbean.dataId"/>'==''&&'<ww:property value="processInstId"/>'!=''){
		var tt = window.top.gettitle();
		document.getElementById("recordtitle").value=tt;
		var lwdw = window.top.getFileValue('ngdw');
		if(lwdw==null||lwdw==''){
			lwdw = window.top.getFileValue('lwdw');
		}
		alert(lwdw);
	}else if('<ww:property value="dbean.dataId"/>'!=''){
		document.getElementById("clj").style.display="none";
		document.getElementById("btntr").style.display="none";
	}else{
		document.getElementById("clj").style.display="none";
	}
	
})
function saveDataInfo(){
	if(checkForm('addData')){
	window.top.saveData();
	Ext.Msg.wait('处理中，请稍后...', '提示'); 
	Ext.Ajax.request({
		timeout: 6000000,
		async : false,
		url:'/data_save.action',
		method:'post',
		form:'addData',
		waitMsg:'正在保存数据,请等待...',
		success:function(o){
		    Ext.Msg.hide();
			alert("添加成功!"+o.responseText);
			//parent.parent.Ext.getCmp('forum-tree').reload();
			location.href="/data_returnaddData.action";
		}
	})
	}else{
		return false;
	}
}


function openWord(){
	//window.top.openUrlWin('/desktop/widgets/electronicfile/datalist/createDocument.jsp');
	var activityInstId = '<ww:property value="activityInstId"/>';
	var personId = '<ww:property value="personId"/>';
	var formId = '<ww:property value="formId"/>';
	var dataId = '<ww:property value="dataId"/>';
	var wname = window.top.getWordname();
	window.top.openUrlWin('/data_getBookMark.action?activityInstId='+activityInstId+'&personId='+personId+'&wordname='+wname+'&dataId='+dataId,'在线编辑');
	/*
	var win = new Ext.Window({
		id:'openWord',
		tilte:'在线编辑',
		width:Ext.getBody().getWidth()-30,
		height:Ext.getBody().getHeight()-30,
		html:'<iframe frameborder="0" src="data_getBookMark.action?activityInstId='+activityInstId+'&personId='+personId+'&formId='+personId+'" width="100%" height="100%"></iframe>'
	});
	win.show();*/
}

function winclose(){
	
	
}
</script>
</HEAD>
<BODY>

<center>
<form name="addData" id="addData" method="post" action="data_save.action">
<table width="753" height="227" bgcolor="#dfe8f6" style="border:1px solid #99bbe8;line-height:30px;">
<input type="hidden" name="dataId" id="dataId" value='<ww:property value="dataId"/>'/>
<input type="hidden" name="dbean.createdate" id="createdate" value='<ww:property value="dbean.createdate"/>'/>
<input type="hidden" name="dbean.activityInstId" id="activityInstId" value='<ww:property value="activityInstId"/>'/>
<input  type="hidden" name="wordhand" value="$Fdtoafwbl.fdtOaFwblDAO.">
     <tr>
       <td align="center" colspan="4"><font size="5">档案信息</font></td>
  </tr>
    <tr>
    <td width="144" align="right">档案标题：</td>
    <td colspan="3"><input name="dbean.title" type="text" id="recordtitle" size="70" value='<ww:property value="dbean.title"/>'/><span style="color:red;">*</span></td>
    
  </tr>
  <tr>
    <td width="144" align="right">主题词：</td>
    <td colspan="3"><input name="dbean.dkeyword" type="text" id="dkeyword" size="70" value='<ww:property value="dbean.dkeyword"/>'/></td>
    
  </tr>
  <tr>
    <td width="144" align="right">正文：</td>
    <td colspan="3"><textarea name="dbean.content" cols="70" rows="10"><ww:property value="dbean.content"/></textarea></td>
  </tr>
  <tr id="clj">
    <td width="144" align="right">生成处理笺：</td>
    <td colspan="3"><input type="button" value="生成处理笺" name="adddata.addbtn" onclick="openWord()" style="width:150px"/></td>
  </tr>
  <tr>
    <td width="144" align="right">附件：</td>
    <td colspan="3"><ww:action name="rfileinclude" executeResult="true"></ww:action></td>
  </tr>
  <tr id="btntr">
    <td colspan="4" align="center"><input type="button" name="savematter" value="保存" onclick="saveDataInfo()" />&nbsp;&nbsp;
    <input type="reset" name="tc" value="重置" /></td>
  </tr>
</table>
</form>
</center>
</BODY>
 
</HTML>