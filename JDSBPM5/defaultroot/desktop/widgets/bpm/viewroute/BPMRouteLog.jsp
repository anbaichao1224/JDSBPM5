<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%   String contextpath = request.getContextPath()+ "/" ; %>
 

{

   <ww:iterator value="performers" status="performersstatus">
                   <tr>
                     <td width="33%" style="BORDER-RIGHT: #0E9CE3 1px solid;">&nbsp;<ww:property value="name"/></td>
                     <td width="66%">&nbsp;			
                        <ww:property value="orgs[0].name"/>
                     </td>					    
                   </tr>
				    </ww:iterator>
totalCount:<ww:property value="totalCount"/>,
datas:[
   <ww:iterator value="historyWrpList" status="historystatus">
    {
      index:"<ww:property value="#historystatus.index"/>",
      processInstId:"<ww:property value="processInstId,"/>",
      performPerson:" <ww:iterator value="performers" status="performersstatus"><ww:property value="name"/> / <ww:property value="orgs[0].name"/></ww:iterator>",
      activityInstName:"<ww:property value="activityDef.name"/>",
      startName:"<ww:property value="arrivedTime"/>",
      state:"<ww:property value="Status"/>",
      endTime:"<ww:property value="endTime"/>",
      action: "<input type='button' style='border:1 ;height:15' name='action' value=' 查 看 '  onClick=\"openWin('<%=contextpath %>demodisplay.action?ActivityInstId=<ww:property value="activityInstHistory.activityInstId"/>', '<ww:property value="activityInstHistory.activityInstId"/>')\">"       
          }  
      <ww:if test ="(#historystatus.count < historyWrpList.size) || (currentWrpList.size >0)">
      , 
      </ww:if>
      </ww:iterator>   
     
     <ww:iterator value="currentWrpList" status="rowstatus"> 
      {
      index:"<ww:property value="#rowstatus.index"/>",
      processInstId:"<ww:property value="processInstId"/>",
      performPerson:" <ww:iterator value="performers" status="performersstatus"><ww:property value="name"/> / <ww:property value="orgs[0].name"/></ww:iterator>",
      activityInstName:"<ww:property value="activityDef.name"/>",
      startName:"<ww:property value="arrivedTime"/>",
      state:"<ww:property value="Status"/>",
      endTime:"<ww:property value="Status"/>",
      action: "<input type='button' style='border:1 ;height:15' name='action' value=' 查 看 '  onClick=\"openWin('<%=contextpath %>demodisplay.action?ActivityInstId=<ww:property value="activityInstId"/>', '<ww:property value="activityInstId"/>')\">"         
          }  
      <ww:if test ="#rowstatus.count < currentWrpList.size">
      , 
      </ww:if>
        
       </ww:iterator>   
 ]
}



 
