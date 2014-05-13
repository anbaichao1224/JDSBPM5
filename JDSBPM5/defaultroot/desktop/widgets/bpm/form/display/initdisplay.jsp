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

%>
	
	 
	 
{
   activityInstId:'<ww:property value="activityInstId"/>',
   sendMenu:function(){
        var sendMenu= [
  	   	<ww:iterator value="#activityInstId=activityInstId,activityInst.nextRoutes.{? !#this.toEnd}" status="routstest">	   
	     <ww:if test="!toEnd">	     
	     	{
            text: '<ww:property value="name"/>',
            handler: function(){  
                    <ww:if test="isNoNeedSelect(toActivityDefId)">
                    this.routeTo('<ww:property value="toActivityDefId"/>',<ww:property value="isContinuous(toActivityDefId)"/>);
                   
	               </ww:if>
	                <ww:if test="!isNoNeedSelect(toActivityDefId)">
	               this.showSelectPersonPanel('<ww:property value="routeDefId"/>')
	               </ww:if>
               },
            tooltip: '<b>操作提示</b><br/><ww:property value="(description==null || description.length==0)? name:description"/>',
            iconCls: 'sendMenu<ww:property value="#routstest.index"/>',
            scope:this
            }
    		</ww:if>
			<ww:if test="!#routstest.last">,</ww:if>
		</ww:iterator>
			]
			
			return sendMenu;
			},	
	
   
    routeBackMenu:function(){
       var routeBackMenu=[
 	   	<ww:iterator value="#activityInstId=activityInstId,activityInst.routeBackActivityHistoryInstList" status="routstest">	   
	     	{
            text: '<ww:property value="activityDef.name"/>',
            handler: function(){                   
              JDS.bpm.Form.Action.BPM_routeBack('<ww:property value="#activityInstId"/>','<ww:property value="activityHistoryId"/>')
              },
            tooltip: '<b>操作提示</b><br/><ww:property value="(description==null || description.length==0)? name:description"/>',
            iconCls: 'sendMenu<ww:property value="#routstest.index"/>',
            scope: this
            }
			<ww:if test="!#routstest.last">,</ww:if>
		</ww:iterator>
			];
			return routeBackMenu;
			}
			
			,
			
	   mainForm:{
                  name: '<ww:property value="activityInst.activityDef.MainFormDef.name"/>',
                  formId:'<ww:property value="activityInst.activityDef.MainFormDef.id"/>'   
 	   },
  
	   forms:[
	              <ww:iterator value="#forms=activityInst.activityDef.allDataFormDef" status="routs">
				{
	                  name: '<ww:property value="name"/>',
	                  formId:'<ww:property value="id"/>'   
	            }
	         <ww:if test="!#routs.last">,</ww:if>
	         </ww:iterator>
	    ],
	    
	    routeBackAction:function(){
	    
	    var  routeBackAction= {                 
                text: '退回',
                menu: routeBackMenu()        
             }
             return routeBackAction;
	    },
	    
	  
  
       getTbar:function (){
    
	 	 var jdsmenu= [
      	<ww:iterator value="#activityInst=activityInst,activityInst.nextRoutes" status="routstest">	   
         <ww:if test="toEnd && #activityInst.canPerform">
            this.endAction(),'-',
         </ww:if>    
         </ww:iterator>   
          <ww:if test="activityInst.canPerform && (activityInst.nextRoutes.{? !#this.toEnd}.size>1)&& activityInst.activityDef.split==@net.itjds.bpm.client.ActivityDef@SPLIT_AND" >     	     
           this.sendAction(),'-',
        </ww:if> 
         <ww:if test="activityInst.canPerform && (activityInst.nextRoutes.{? !#this.toEnd}.size>0) && activityInst.activityDef.split!=@net.itjds.bpm.client.ActivityDef@SPLIT_AND" >  
          this.sendMenu(),'-',
         </ww:if> 
        <ww:if test="activityInst.canPerform ">
           this.saveAction(),'-',
           </ww:if>  
            <ww:if test="activityInst.CanSignReceive">
           this.signAction(),'-',
           </ww:if> 
         
         <ww:if test="activityInst.canRouteBack">
            routeBackAction,'-',
          </ww:if>
           <ww:if test="activityInst.canEndRead">
             this.endReadAction(),'-',
          </ww:if>
           <ww:if test="activityInst.canTakeBack">
             this.tackBackAction(),'-',
          </ww:if>
			   this.printwordAction()
//               this.refAction()
               
              
         //        this.printAction()
               
        ]
       
	 	 return jdsmenu;
	 }
      
}



   