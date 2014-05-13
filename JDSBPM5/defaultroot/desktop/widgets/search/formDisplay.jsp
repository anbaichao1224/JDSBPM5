<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,
	com.opensymphony.xwork2.ActionContext,
	java.util.List,net.itjds.bpm.data.FormClassBean,
	com.opensymphony.xwork2.util.OgnlValueStack"
	%>
<%@ taglib uri="/struts-tags" prefix="ww"%>

        <%
		OgnlValueStack stack = (OgnlValueStack) request.getAttribute("struts.valueStack");
		FormClassBean currForm=(FormClassBean)stack.findValue("currForm");		
	    String winId=(String)stack.findValue("activityInstId");
	    winId=net.itjds.j2ee.util.StringUtil.replace(winId, "-", "");
	    String jspRunTimeUrl = (String) stack
			.findValue("currForm.jspUrl" );
		%>
				
        <form id="<%=winId%><%=currForm.getId()%>form"  name="<%=winId%><%=currForm.getId()%>form"   method="post" action="demoupdate.action">											
		<input type="hidden" id="<%=winId%><%=currForm.getId()%>formActivityInstId" name="<%=winId%><%=currForm.getId()%>formAcivityInstId" value="<ww:property value="activityInstId"/>">
		<jsp:include page='<%=jspRunTimeUrl%>' flush="" />	
	
		<ww:if test="activityInst.activityDef.MainFormDef.id==currForm.id">
		<input type="hidden" name="bpmUserClientUtil.BPM_UpdateType"  id="<%=winId%>BPM_UpdateType">
		<input type="hidden" name="activityInstId"  id="activityInstId" value="<ww:property value="activityInstId"/>">
		<input type="hidden" name="bpmUserClientUtil.BPM_PerformTypes" id="<%=winId%>BPM_PerformTypes">
		<input type="hidden" name="bpmUserClientUtil.BPM_Admin" id="<%=winId%>BPM_Admin" >
		<input type="hidden" name="bpmUserClientUtil.BPM_BackToActivityInstHistoryId" id="<%=winId%>BPM_BackToActivityInstHistoryId">
		<input type="hidden" name="bpmUserClientUtil.BPM_RouteToActivityDefIds" id="<%=winId%>BPM_RouteToActivityDefIds">
		<input type="hidden" name="bpmUserClientUtil.BPM_Performers" id="<%=winId%>BPM_Performers">	
		<input type="hidden" name="bpmUserClientUtil.BPM_Readers" id="<%=winId%>BPM_Readers">	
	</ww:if>
		</form>		

   