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
       getTbar:function (){
    
	 	 var jdsmenu= [
	 	  <ww:if test="activityInst.canPerform && (activityInst.nextRoutes.{? !#this.toEnd}.size>0) && activityInst.activityDef.split!=@net.itjds.bpm.client.ActivityDef@SPLIT_AND" >  
          this.sendMenu(),'-',
         </ww:if> 
	            this.viewRouteLogAction(),
//	             this.printAction(),
	             this.printwordAction(), 
                this.dubugAction()
        ]
         return jdsmenu;
	 	
	 }
      
}



   