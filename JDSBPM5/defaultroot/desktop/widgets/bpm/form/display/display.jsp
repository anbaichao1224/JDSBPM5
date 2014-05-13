<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="/struts-tags" prefix="ww"%>
 <%@ page
	import="java.util.Map,
	com.opensymphony.xwork2.ActionContext,
	java.util.List,net.itjds.bpm.data.FormClassBean,
	com.opensymphony.xwork2.util.OgnlValueStack,net.itjds.common.org.base.Person,net.itjds.common.org.base.PersonRole"
	%>
 <%
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
 String contextpath = request.getContextPath() + "/";
OgnlValueStack stack = (OgnlValueStack) request.getAttribute("struts.valueStack");
String personId = ""; 
if(request.getSession().getAttribute("personId")!=null){
	 personId = request.getSession().getAttribute("personId").toString();
 }
Person person = (Person) ActionContext.getContext().getValueStack().findValue("$currPerson");
String orgName=person.getOrgList().get(0).getName();
List<PersonRole> prlist = person.getRoleList();
boolean flag = false;
if(prlist!=null&&prlist.size()>0){
	for(PersonRole pr:prlist){
	if(pr!=null&&pr.getName()!=null&&pr.getName().equals("文档管理员")){
		flag = true;
		break;
	}
}
}

%>
	  
{
   activityInstId:'<ww:property value="activityInstId"/>',
    processInstId:'<ww:property value="processInstId"/>',
   sendMenu:function(){
        var sendMenu= [
        
  	   	<ww:iterator value="#activityInstId=activityInstId,activityInst.nextRoutes.{? !#this.toEnd}" status="routstest">	   
	     <ww:if test="!toEnd">	     
	     	{
            text: '<ww:property value="name"/>',
            handler: function(){ 
                   <ww:if test="isNoNeedSelect(toActivityDefId)">
                    	this.routeTo('<ww:property value="toActivityDefId"/>','<ww:property value="isContinuous(toActivityDefId)"/>','<ww:property value="name"/>');
	               </ww:if>
	                <ww:if test="!isNoNeedSelect(toActivityDefId)">
	           			<ww:if test="name=='多人承办'">
	           				this.showSelectPersonPanelDrcb('<ww:property value="routeDefId"/>');
	           			</ww:if>
	           			<ww:else>
	           				this.showSelectPersonPanel('<ww:property value="routeDefId"/>','<ww:property value="name"/>');
	           			</ww:else>
	               		
	               </ww:if>
               },
            tooltip: '<b>操作提示</b><br/><ww:property value="(description==null || description.length==0)? name:description"/>',
            iconCls: 'sendMenu<ww:property value="#routstest.index"/>',
            scope:this
            }
    		</ww:if>
			<ww:if test="!#routstest.last">,</ww:if>
			 <ww:if test="name=='办理完毕'">
	           				,this.tzfawen()
	           			</ww:if>
	           			<ww:if test="name=='校对'">
	           				,this.ReadAction()
	           			</ww:if>
		</ww:iterator>
					<ww:if test="#str=='4AED1BE'">
		,this.abortProAction()
		</ww:if>
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
	 
                  name: '<ww:property value="#activityDef=activityInst.activityDef,$visitableForm.getCheckForm().{? #this.name==#activityDef.MainFormDef.name}.size()>0 ? #activityDef.MainFormDef.name:$visitableForm.getCheckForm()[0].name"/>',
                  formId:'<ww:property value="#activityDef=activityInst.activityDef,$visitableForm.getCheckForm().{? #this.name==#activityDef.MainFormDef.name}.size()>0 ? #activityDef.MainFormDef.id:$visitableForm.getCheckForm()[0].id"/>'   
 	   },
  
	   forms:[
	              <ww:iterator value="$visitableForm.getCheckForm()" status="routs">
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
        <ww:if test="#activityInst.activityDef.name=='草拟'">
           this.saveAction(),'-',
        </ww:if>  
        
        
        
           <ww:if test="#activityInst.activityDef.name=='办公室编号' && activityInst.canPerform">
           this.bianHaoAction(),'-',
        </ww:if>  
		<ww:if test="#activityInst.activityDef.name=='拟稿人'">
      //  this.endAction(),'-',
        //   this.viewProcessAction(),'-',
        </ww:if>  
        
        
        
            <ww:if test="activityInst.CanSignReceive">
           this.signAction(),'-',
           </ww:if> 
          <ww:if test="#activityInst.activityDef.name=='公文交换跳转'">
           this.fromFWtoGwjhAction(),'-',
           this.fromFWtoTZGGAction(),'-',
           
 		  </ww:if>
 		  <ww:if test="#activityInst.activityDef.name=='公文交换'">
           this.fromSWtoGwjhAction(),'-',
 		  </ww:if>
         <ww:if test="activityInst.canRouteBack">
            routeBackAction,'-',
          </ww:if>
           <ww:if test="activityInst.canEndRead">
             this.endReadAction(),'-',
          </ww:if>
          
       //   this.reSend(), '-',
           <ww:if test="activityInst.canTakeBack">
     //        this.tackBackAction(),'-',
//            <ww:iterator value="routeDefList">      
//	               		this.resendAction('<ww:property value="name"/>','<ww:property value="routeDefId"/>'),'-',
//            </ww:iterator>
          </ww:if>
//             this.refAction(),
//activityInst.canPerform  已办环节
			<ww:if test="activityInst.canPerform &&#activityInst.activityDef.name=='拟稿人'">
             this.abortProAction(),'-',
              this.fromFWtoTZGGAction(),'-',
             </ww:if>
             <ww:if test="#activityInst.processInst.processDef.name=='发文办理' && #activityInst.canPerform &&#activityInst.activityDef.name=='拟稿人'">
              this.huiqianAction(), '-',
             </ww:if>
               <ww:if test="#activityInst.processInst.processDef.name=='收文办理' && #activityInst.canPerform &&#activityInst.activityDef.name=='拟稿人'">
              this.swhuiqianAction(), '-',
             </ww:if>
               <ww:if test="#activityInst.processInst.processDef.name=='发文办理'&&#activityInst.activityDef.name=='拟稿人'">
           //    this.huiqianAction(), '-',
         //    this.zhuangtaiAction(),'-',
          
              </ww:if>
            <ww:if test="#activityInst.processInst.processDef.name=='收文办理'&&#activityInst.activityDef.name=='拟稿人'">
          //    this.swhuiqianAction(), '-',
          //   this.zhuangtaiAction(),'-',
           
            </ww:if>
               
             //    this.printAction()
             this.printwordAction(),
             this.viewRouteLogAction()
           //  <!-- this.NbyjAction() 拟办意见修改痕迹  hcl 关闭-->
             
 //              this.shudaoAction('<ww:property value="activityInst.activityInstId"/>')
 //             this.dubugAction()
        ]
        <ww:if test="activityInst.activityDef.processDef.name in {'消息推送'}">
         return null;
	 	</ww:if>
	 
	 	 return jdsmenu;
	 }
      
}


   