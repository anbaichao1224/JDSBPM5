<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="/struts-tags" prefix="ww"%>
 <%@ page
	import="java.util.Map,
	com.opensymphony.xwork2.ActionContext,
	java.util.List,net.itjds.bpm.data.FormClassBean,
	com.opensymphony.xwork2.util.OgnlValueStack"
	%>
 <%
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
 String contextpath = request.getContextPath() + "/";
		OgnlValueStack stack = (OgnlValueStack) request.getAttribute("struts.valueStack");
	    String winId=(String)stack.findValue("activityInstId");
	    winId=net.itjds.j2ee.util.StringUtil.replace(winId, "-", "");
 %>
      
 [
      	<ww:iterator value="#activityInst=activityInst,activityInst.nextRoutes" status="routstest">	   
         <ww:if test="toEnd && #activityInst.canPerform">
             endAction,'-',
         </ww:if>    
         </ww:iterator>   
           
          <ww:if test="activityInst.canPerform && (activityInst.nextRoutes.{? !#this.toEnd}.size>1)&& activityInst.activityDef.split==@net.itjds.bpm.client.ActivityDef@SPLIT_AND" >     	     
           sendAction,'-',
        </ww:if> 
         <ww:if test="activityInst.canPerform && (activityInst.nextRoutes.{? !#this.toEnd}.size>0) && activityInst.activityDef.split!=@net.itjds.bpm.client.ActivityDef@SPLIT_AND" >  
         [
 	   	<ww:iterator value="activityInst.nextRoutes.{? !#this.toEnd}" status="routstest">	   
	     <ww:if test="!toEnd">	     
	     	{
            text: '<ww:property value="name"/>',
            handler: function(){    
                  JDS.bpm.Form.Action.BPM_routeTo('<ww:property value="routeDefId"/>','<ww:property value="name"/>')
               },
            tooltip: '<b>操作提示</b><br/><ww:property value="(description==null || description.length==0)? name:description"/>',
            
            iconCls: 'sendMenu<ww:property value="#routstest.index"/>'
            
            }
    		</ww:if>
			<ww:if test="!#routstest.last">,</ww:if>
		</ww:iterator>
		
			]	,'-',
         </ww:if> 
        <ww:if test="activityInst.canPerform ">
             saveAction,'-',
           </ww:if>  
            <ww:if test="activityInst.CanSignReceive">
              signAction,'-',
           </ww:if> 
         
         <ww:if test="activityInst.canRouteBack">
             {                 
                text: '退回',
                menu: [
			 	   	<ww:iterator value="#activityInstId=activityInstId,activityInst.routeBackActivityHistoryInstList" status="routstest">	   
				     	{
			            text: '<ww:property value="activityDef.name"/>',
			            handler: function(){                   
			              BPM_routeBack('<ww:property value="#activityInstId"/>','<ww:property value="activityHistoryId"/>')
			              },
			            tooltip: '<b>操作提示</b><br/><ww:property value="(description==null || description.length==0)? name:description"/>',
			            iconCls: 'sendMenu<ww:property value="#routstest.index"/>'
			            }
						<ww:if test="!#routstest.last">,</ww:if>
					</ww:iterator>
						]	        
             },'-',
          </ww:if>
           <ww:if test="activityInst.canEndRead">
             endReadAction,'-',
          </ww:if>
          <ww:if test="!activityInst.canPerform && !activityInst.CanSignReceive">
               sendReadAction,'-',
           </ww:if>
           <ww:if test="activityInst.canTakeBack">
             tackBackAction,'-',
          </ww:if>
               // printAction,    
               //  dubugAction,  
              printwordAction,'-',
               viewRouteLogAction
               
        ]
      
    


   