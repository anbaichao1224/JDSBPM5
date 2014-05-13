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

    var forms=[
    			{
                title: '<ww:property value="activityInst.activityDef.MainFormDef.name"/>',
                listeners: {activate: handleActivate},
                id:'<ww:property value="activityInst.activityDef.MainFormDef.id"/>',   
               autoWidth : true,  
               height:400,          
               iconCls: 'tabs',
			   autoScroll :true,
               autoLoad: {url: '<%=contextpath%>BPMClientFormDisplayAction.action', params: 'formID=<ww:property value="activityInst.activityDef.MainFormDef.id"/>&activityInstId=<ww:property value="activityInstId"/>', callback:JDS.bpm.Form.FormCallFn.callFormFn}
                
            }
            
           <ww:if test="#forms=($visitableForm.getCheckForm().size >0 ? $visitableForm.getCheckForm(): activityInst.activityDef.allDataFormDef),#forms.size()>1">,
             <ww:iterator value="#forms=($visitableForm.getCheckForm().size >0 ? $visitableForm.getCheckForm(): activityInst.activityDef.allDataFormDef), #activityInstId=activityInstId,#mainId=activityInst.activityDef.MainFormDef.id, #forms.{? #this.id!=#mainId}" status="routs">
					{
                title: '<ww:property value="name"/>',
                listeners: {activate: handleActivate},
                id:'<ww:property value="id"/>',    
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
      
   