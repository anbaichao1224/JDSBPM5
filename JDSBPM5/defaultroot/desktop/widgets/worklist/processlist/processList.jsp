<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%   String contextpath = request.getContextPath()+ "/" ; %>

{

totalCount:<ww:property value="totalCount"/>,
datas:[
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
      lookStat:"<a href=javascript:openWin('<ww:property value="'/routelogView.action?activityInstId='+activityInstId"/>') style='text-decoration: none'>查看办理情况</a>",
      process:"<table style='border:1'><ww:iterator value="demoListActivity" status="activitys"><tr  style='height:20'><td style='border:2; solid #000000;vertical-align: middle;height:20px'><a ><ww:property value="activityInst.activityDef.name"/></a></td><ww:if test="canSignReceive"><td><input type='button' class='button'  name='signReceive' value='签收公文' style='border:1 ;height:15'  onClick=\"openWin('<%=contextpath %>demodisplay.action?activityInstId=<ww:property value="activityInst.activityInstId"/>','<ww:property value="activityInst.activityInstId"/>')\"></td></ww:if><ww:if test="canTakeBack &&!canSignReceive" ><td ><input type='button' style='border:1 ;height:15' name='signReceive' value='收回重办' onClick=\"openWin('<%=contextpath %>demotakeback.action?activityInstId=<ww:property value="activityInst.activityInstId"/>','<ww:property value="activityInst.activityInstId"/>')\"></td></ww:if><ww:if test="canPerform"><td ><input type='button' style='border:1 ;height:15' name='startProcess2' value='继续办理' onClick=\"openWin('<%=contextpath %>demodisplay.action?activityInstId=<ww:property value="activityInst.activityInstId"/>','<ww:property value="activityInst.activityInstId"/>')\"></td></ww:if><ww:if test="!(canPerform || canSignReceive ||  canTakeBack)"><td ><input type='button' name='startProcess2' style='border:1 ;height:15' value='查看公文'  onClick=\"openWin('<%=contextpath %>demodisplay.action?activityInstId=<ww:property value="activityInst.activityInstId"/>','<ww:property value="activityInst.activityInstId"/>')\"></td></ww:if> </tr></ww:iterator></table>"
      }  
      <ww:if test ="#rowstatus.count < demoListProcessInst.size">
      , 
      </ww:if>
     </ww:iterator>    
 ]
}



 
