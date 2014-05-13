<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%   String contextpath = request.getContextPath()+ "/" ; %>
	<link rel="stylesheet" type="text/css"
			href="<%=contextpath%>bpm/oa/desktop/resources/css/ext-all.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=contextpath%>bpm/oa/desktop/examples.css" />
		<link rel="STYLESHEET" type="text/css"
			href="<%=contextpath%>css/dhtmlXTree.css">


		<link rel="stylesheet" type="text/css"
			href="<%=contextpath%>bpm/oa/desktop/resources/css/menus.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=contextpath%>bpm/oa/desktop/css/desktop.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=contextpath%>bpm/oa/desktop/resources/css/ext-all.css" />
		<SCRIPT LANGUAGE="JavaScript" type="text/javascript">
	_js_prefix='<%=contextpath%>js/';
	context='<%=contextpath%>';
	</SCRIPT>

		<script type="text/javascript"
			src="<%=contextpath%>js/report/prototype.js"></script>
		<script type="text/javascript"
			src="<%=contextpath%>js/dhtmlxTree/dhtmlXCommon.js"></script>
		<script type="text/javascript"
			src="<%=contextpath%>js/dhtmlxTree/dhtmlXTree.js"></script>
		<script type="text/javascript"
			src="<%=contextpath%>js/dhtmlxTree/dhtmlXTree_start.js"></script>
		<script type="text/javascript"
			src="<%=contextpath%>bpm/oa/desktop/adapter/ext/ext-base.js"></script>
		<script type="text/javascript"
			src="<%=contextpath%>bpm/oa/desktop/adapter/adapter/ext-prototype-adapter.js"></script>
		<script type="text/javascript"
			src="<%=contextpath%>bpm/oa/desktop/ext-all.js"></script>
		<script type="text/javascript"
			src="<%=contextpath%>js/dhtmlxTree/dhtmlXCommon.js"></script>
		<script type="text/javascript"
			src="<%=contextpath%>bpm/oa/desktop/locale/ext-lang-zh_CN.js"></script>
		<script type="text/javascript"
			src="<%=contextpath%>bpm/oa/desktop/message-box/msg-box.js"></script>
		<script type="text/javascript"
			src="<%=contextpath%>bpm/oa/desktop/examples.js"></script>
		<script type="text/javascript"
			src="<%=contextpath%>js/formdisplay/displayAction.js"></script>
		<script type="text/javascript"
			src="<%=contextpath%>js/formdisplay/form2ext.js"></script>
		<script language="javascript"
			src="<%=contextpath%>bpm/oa/desktop/createGrid.js"></script>
		<!-- DESKTOP -->
		<script type="text/javascript"
			src="<%=contextpath%>bpm/oa/desktop/js/StartMenu.js"></script>
		<script type="text/javascript"
			src="<%=contextpath%>bpm/oa/desktop/js/TaskBar.js"></script>
		<script type="text/javascript"
			src="<%=contextpath%>bpm/oa/desktop/js/Desktop.js"></script>
		<script type="text/javascript"
			src="<%=contextpath%>bpm/oa/desktop/js/App.js"></script>
		<script type="text/javascript"
			src="<%=contextpath%>bpm/oa/desktop/js/Module.js"></script>
		<script type="text/javascript"
			src="<%=contextpath%>bpm/oa/desktop/newMyDesktop.js"></script>
		<script type="text/javascript"
			src="<%=contextpath%>bpm/oa/desktop/startMenudata.js"></script>


		<script language="javascript">
		<script>
{

 var store = new Ext.data.SimpleStore({
        fields: [
         <ww:iterator value="demoListProcessInst" status="rowstatus">
    {
      index:"<ww:property value="#rowstatus.index"/>",
      rowdblclick:"<ww:property value="(#presonformList=demoListActivity.{? (#this.canSignReceive || #this.canPerform )} ,#presonformList.size>0 ) ? \"proformWork('\"+#presonformList[0].activityInst.activityInstId+\"')\" : \"\"" />",
      processInstId:"<ww:property value="processInst.processInstId"/>",
      signList:"<ww:iterator value="#signListActivity=demoListActivity.{? #this.canSignReceive}" status="activityInsts"><ww:property value="activityInst.activityInstId"/><ww:if test ="#activityInsts.count < #signListActivity.size">;</ww:if></ww:iterator>",
      performList:"<ww:iterator value="#performListActivity=demoListActivity.{? #this.canPerform}" status="activityInsts"><ww:property value="activityInst.activityInstId"/><ww:if test ="#activityInsts.count < #performListActivity.size">;</ww:if></ww:iterator>",
      fileTitle:"<ww:property value="processInst.name"/>",
      priprocessDefNamece:"<ww:property value="processInst.processDef.name"/>",
      startName:"<ww:property value="startName"/>",
      startTime:"<ww:property value="startTime" />",       
      process:"<table style='border:1'><ww:iterator value="demoListActivity" status="activitys"><tr  style='height:20'><td style='border:2; solid #000000;vertical-align: middle;height:20px'><a ><ww:property value="activityInst.activityDef.name"/></a></td><ww:if test="canSignReceive"><td><input type='button' class='button'  name='signReceive' value='签收公文' style='border:1 ;height:15'  onClick=\"openWin('<%=contextpath %>demosignfeceive.action?ActivityInstId=<ww:property value="activityInst.activityInstId"/>','<ww:property value="activityInst.activityInstId"/>')\"></td></ww:if><ww:if test="canTakeBack &&!canSignReceive" ><td ><input type='button' style='border:1 ;height:15' name='signReceive' value='收回重办' onClick=\"openWin('<%=contextpath %>demotakeback.action?ActivityInstId=<ww:property value="activityInst.activityInstId"/>','<ww:property value="activityInst.activityInstId"/>')\"></td></ww:if><ww:if test="canPerform"><td ><input type='button' style='border:1 ;height:15' name='startProcess2' value='继续办理' onClick=\"openWin('<%=contextpath %>demodisplay.action?ActivityInstId=<ww:property value="activityInst.activityInstId"/>','<ww:property value="activityInst.activityInstId"/>')\"></td></ww:if><ww:if test="!(canPerform || canSignReceive ||  canTakeBack)"><td ><input type='button' name='startProcess2' style='border:1 ;height:15' value='查看公文'  onClick=\"openWin('<%=contextpath %>demodisplay.action?ActivityInstId=<ww:property value="activityInst.activityInstId"/>','<ww:property value="activityInst.activityInstId"/>')\"></td></ww:if> <td><input type='button' name='startProcess2' value='调试' style='border:1 ;height:15' onClick=\"document.location='<%=contextpath %>BPMDebugDisplay.action?ActivityInstId=<ww:property value="activityInst.activityInstId"/>'\"></td></tr></ww:iterator></table>"
      }  
      <ww:if test ="#rowstatus.count < demoListProcessInst.size">
      , 
      </ww:if>
     </ww:iterator>    
        ]
    });



 var grid = new Ext.grid.GridPanel({
          store: store,
        columns: [
           {text:"index",name: 'index',isDisplay:'false'},
        {text:"rowdblclick",name: 'rowdblclick',isDisplay:'false'},      
       {text:"processInstId",name: 'processInstId',isDisplay:'false'},
       {text:"signList",name: 'signList',isDisplay:'false'},    
       {text:"performList",name: 'performList',isDisplay:'false'},
       {text:"公文标题",name: 'fileTitle',sortable:true,width:120},
       {text:"流程名称",name: 'priprocessDefNamece'},
       {text:"拟稿人",name: 'startName'},
       {text:"拟稿时间",name: 'startTime' ,width:120},
       {text:"当前办理步骤     可执行操作   调试",name: 'process',width:240}   
        ],
          width:800,
    id:"processDefList"
        title:'Array Grid'
    });
 
 
};


  </script>


 
