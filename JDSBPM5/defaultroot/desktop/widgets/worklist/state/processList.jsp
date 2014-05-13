<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%   String contextpath = request.getContextPath()+ "/" ; %>
{

totalCount:<ww:property value="totalCount"/>,
datas:[
 <ww:iterator value="processList" status="rowstatus">
    {
      index:"<ww:property value="#rowstatus.index"/>",
     // rowdblclick:"<ww:property value=" \"openWin('demodisplay.action?activityInstId=\"+activityInstId+\"')\"" />",
      processInstId:"<ww:property value="processInstId"/>",
      signList:"<ww:property value="signList"/>",
      performList:"<ww:property value="performList"/>",
      urgencyDegree:"<ww:if test="urgencyDegree==1"><font color='red'>急!</font></ww:if><ww:else>普通</ww:else>",
      readState:"<ww:property value="readState"/>",
      timeLimit:"<ww:property value="timeLimit"/>",
      fileTitle:"<a href=javascript:openWin('<ww:property value="'demodisplay.action?activityInstId='+activityInstId"/>') style='text-decoration: none'><ww:property value="fileTitle"/></a>",
      priprocessDefNamece:"<ww:property value="priprocessDefNamece"/>",
      startName:"<ww:property value="startName"/>",
      startTime:"<ww:property value="startTime" />",  
      activityDefName:"<ww:property value="activityDefName"/>",    
      lookStat:"<a href=javascript:winPop('<ww:property value="'routelogView.action?activityInstId='+activityInstId"/>','aaaa') style='text-decoration: none'/>查看办理请况</a>" ,
      process:"<ww:if test="canSignReceive"><input type='button' class='button'  name='signReceive' value='签收公文' style='border:1 ;height:15'  onClick=\"openWin('<%=contextpath %>demodisplay.action?activityInstId=<ww:property value="activityInstId"/>','<ww:property value="activityInstId"/>')\"></ww:if><ww:if test="canTakeBack &&!canSignReceive" ><input type='button' style='border:1 ;height:15' name='signReceive' value='收回重办' onClick=\"openWin('<%=contextpath %>demotakeback.action?activityInstId=<ww:property value="activityInstId"/>','<ww:property value="activityInstId"/>')\"></ww:if><ww:if test="canPerform"><input type='button' style='border:1 ;height:15' name='startProcess2' value='继续办理' onClick=\"openWin('<%=contextpath %>demodisplay.action?activityInstId=<ww:property value="activityInstId"/>','<ww:property value="activityInstId"/>')\"></ww:if><ww:if test="!(canPerform || canSignReceive ||  canTakeBack)"><input type='button' name='startProcess2' style='border:1 ;height:15' value='查看公文'  onClick=\"openWin('<%=contextpath %>demodisplay.action?activityInstId=<ww:property value="activityInstId"/>','<ww:property value="activityInstId"/>')\"></ww:if>"
      }  
      <ww:if test ="#rowstatus.count < processList.size">
      , 
      </ww:if>
     </ww:iterator>    
 ]
}
