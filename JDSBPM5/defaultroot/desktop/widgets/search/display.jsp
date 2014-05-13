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
	
	 
	 
 function EVAL.getPanel(){
    function querydepartMessage(){
		   	 var googleKey = Ext.get('googleKey').dom.value;
		  
		   	 var googletype="shan";
		   	 var startTime=Ext.get('startTime').dom.value;
		   	 var endTime=Ext.get('endTime').dom.value;
	    	 store.load({params:{start:0, limit:10,googleKey:googleKey,googletype:googletype,startTime:startTime,endTime:endTime}}); 
	}
     
     var search = new Ext.Action({
        text: '开始查询',
        tooltip: '开始查询',
        handler: function(){      
                JDS.bpm.Form.Action.serarch();
            
        },
        iconCls: 'send'
    });
   
     var refAction = new Ext.Action({
        text: '刷新',
        tooltip: '刷新',
        handler: function(){    
        var tab=formTab.getActiveTab();
      tab.load({url: '<%=contextpath%>BPMClientFormDisplayAction.action', params: 'formID='+tab.formId+'&activityInstId='+tab.activityInstId, callback:JDS.bpm.Form.FormCallFn.callFormFn});
        },
        iconCls: 'viewRouteButton'
    });
   
    var forms=[
    		{
                title: '<ww:property value="activityInst.activityDef.allDataFormDef.size>0 ? activityInst.activityDef.MainFormDef.name :  '没有发现定义表单' "/>',
                listeners: {activate: handleActivate},
                activityInstId:'<ww:property value="activityInstId"/>',
                formId:'<ww:property value="activityInst.activityDef.MainFormDef.id"/>',
                autoWidth : true,        
                iconCls: 'tabs',
				autoScroll :true,
				<ww:if test="activityInst.activityDef.allDataFormDef.size>0">
                autoLoad: {url: '<%=contextpath%>BPMClientFormDisplayAction.action', params: 'formID=<ww:property value="activityInst.activityDef.MainFormDef.id"/>&activityInstId=<ww:property value="activityInstId"/>', callback:JDS.bpm.Form.FormCallFn.callFormFn}
                </ww:if>
                <ww:if test="activityInst.activityDef.allDataFormDef.size==0">
                   autoLoad: {url: '/fdt/blank.jsp'}
                 </ww:if>
            }
            
           <ww:if test="#forms=($visitableForm.getCheckForm().size >0 ? $visitableForm.getCheckForm(): activityInst.activityDef.allDataFormDef),#forms.size()>1">,
             <ww:iterator value="#forms=($visitableForm.getCheckForm().size >0 ? $visitableForm.getCheckForm(): activityInst.activityDef.allDataFormDef), #activityInstId=activityInstId,#mainId=activityInst.activityDef.MainFormDef.id, #forms.{? #this.id!=#mainId}" status="routs">
			{
                title: '<ww:property value="name"/>',
                listeners: {activate: handleActivate},
                formId:'<ww:property value="id"/>',
                activityInstId:'<ww:property value="activityInstId"/>',  
                autoHeight : true,   
                autoWidth : true,    
                iconCls: 'tabs',
				autoScroll :true,
                autoLoad: {url: '<%=contextpath%>BPMClientFormDisplayAction.action', params: 'formID=<ww:property value="id"/>&activityInstId=<ww:property value="#activityInstId"/>', callback:JDS.bpm.Form.FormCallFn.callFormFn}
            }
         <ww:if test="!#routs.last">,</ww:if>
         </ww:iterator>
         </ww:if>
          
        ];
      
       tbar= [
           
           '标题:', new Ext.form.TextField({   
                width:175, 
                name: 'fileTitle',
                allowBlank:true 
            }),'开始日期:',{
        				xtype:'datefield',
        				width:80,
        				format: 'Y-m-d',
                name: 'startTime'
            },'结束日期:',{
        				xtype:'datefield',
        				width:80,
        				format: 'Y-m-d',
                name: 'endTime'
            },'-',{  
                    id : 'newModelButton',    
                    icon : context+"desktop/resources/images/search.jpg",   
                    cls : "x-btn-text-icon",   
                    handler :function(){JDS.bpm.Form.Action.search('<ww:property value="activityInstId"/>'); }  
            },'-',refAction
       ]
      
      
      formTab = new Ext.TabPanel({
        renderTo: Ext.getBody(),
        activeTab: 0,  
        autoDestroy:true,
        id:Ext.id(),
        plain:true,   
      defaults:{autoScroll: true},
        items:forms
    });
  
    function handleActivate(tab){
        tab.isLoad=true;
      currActivateTabId=tab.id
    }
 var panel= {
            title:'打开流程',
		    autoScroll:true,
      		id:'<%=winId%>',
      		width:835,
			tbar:tbar,
      		height:500,     		
      		items:[formTab]     		
     };  
    return panel;
};



   