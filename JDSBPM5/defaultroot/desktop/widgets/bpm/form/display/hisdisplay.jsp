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
	activityInstId:'<ww:property value="activityInstHistory.activityInstId"/>',
	processInstId:'<ww:property value="processInstId"/>',
    activityInstHistoryId:'<ww:property value="activityInstHistoryId"/>',

	mainForm:{
		name: '<ww:property value="$visitableForm.getCheckForm().{? #this.id==activityDef.MainFormDef.id}.size()>0 ? activityDef.MainFormDef.name:$visitableForm.getCheckForm()[0].name"/>',
		formId:'<ww:property value="$visitableForm.getCheckForm().{? #this.id==activityDef.MainFormDef.id}.size()>0 ? activityDef.MainFormDef.id:$visitableForm.getCheckForm()[0].id"/>'   
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
	
	//在办结列表里面打开表单 起tbar中显示的按钮
    getTbar:function (){
		var jdsmenu= [
			

			this.printwordAction(),'-',
			//<ww:property value="activityInstHistory.processInst.state"/>   
			//<ww:property value="activityInstHistory.processInst.processInstId"/>   
			
			
			
			<ww:if test="activityInstHistory.processInst.state=='completed'">
			//从流程往文件资料库里面跳转
			this.tzWjzlAction(),'-',
			 </ww:if>
			 
			 
			 
			//is can take back;
			
			<ww:if test="isCanTakeBack()">
             this.viewProcessAction(),'-',
            </ww:if>
            <ww:if test="isCanResend()">
			this.reSend(), '-',
			  </ww:if>
			  
			  this.viewRouteLogAction()
		]
      
        <ww:if test="activityInstHistory.activityDef.processDef.name in {'信息发布'}">
        	return null;
	 	</ww:if>
	 	
        return jdsmenu;
	}
}



   