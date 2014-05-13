<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%   String contextpath = request.getContextPath()+ "/" ; %>
 

{

totalCount:<ww:property value="historyWrpList.size+currentWrpList.size"/>,
datas:[
   <ww:iterator value="historyWrpList" status="historystatus">
    {
      performPerson:" <table><ww:iterator value="performers" status="performersstatus"><tr><td><ww:property value="name"/></td><td><ww:property value="orgs[0].name"/></td></tr></ww:iterator></table>",
      activityInstName:"<ww:property value="activityDef.name"/>",
       startName:"<ww:property value="arrivedTime"/>",
       endTime:"<ww:property value="endTime"/>",
       // action: "<input type='button' style='border:1 ;height:15' name='action' value=' 查 看 '  onClick=\"openWin('<%=contextpath %>demodisplay.action?ActivityInstId=<ww:property value="activityInstHistory.activityInstId"/>', '<ww:property value="activityInstHistory.activityInstId"/>view')\">"       
          }  
      <ww:if test ="(#historystatus.count < historyWrpList.size) || (currentWrpList.size >0)">
      , 
      </ww:if>
      </ww:iterator>   
     
     <ww:iterator value="currentWrpList" status="rowstatus"> 
    
      {
      index:"<ww:property value="#rowstatus.index"/>",
      processInstId:"<ww:property value="processInstId"/>",
      performPerson:" <table><ww:iterator value="performers" status="performersstatus"><tr><td><ww:property value="name"/></td><td><ww:property value="orgs[0].name"/></td></tr></ww:iterator></table>",
      activityInstName:"<ww:property value="activityDef.name"/>",
       startName:"<ww:property value="arrivedTime"/>",
       endTime:"<ww:property value="'正在办理'"/>",
     action: "<input type='button' style='border:1 ;height:15' name='action' value=' 查 看 '  onClick=\"openWin('<%=contextpath %>demodisplay.action?ActivityInstId=<ww:property value="activityInstId"/>', '<ww:property value="activityInstId"/>view')\">"         
          }  
      <ww:if test ="#rowstatus.count < currentWrpList.size">
      , 
      </ww:if>
      
       
       </ww:iterator>   
 ]
}



 
