
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,
	com.opensymphony.xwork2.ActionContext,
	java.util.List,net.itjds.bpm.data.FormClassBean,net.kzxd.dj.bean.FdtOaDj,net.kzxd.dj.bean.FdtOaDjBean,
	com.opensymphony.xwork2.util.OgnlValueStack"
	
	%>
<%@ taglib uri="/struts-tags" prefix="ww"%>




        <%
		OgnlValueStack stack = (OgnlValueStack) request.getAttribute("struts.valueStack");
	   FormClassBean currForm=(FormClassBean)stack.findValue("currForm");
	    String jspRunTimeUrl = currForm.getJspUrl();
			%>
	    <input type="hidden" name="" value="y" id="ldyj<ww:property value="activityInstId"/>"/>
	    <!-- ldyjtemb 文本框为了区分一个表单有两个意见框 只填写其中一个即可发送 -->
	    <input type="hidden" name="" value="n" id="ldyjtemb"/>
		<input type="hidden" name="" value="<ww:property value="yibanli"/>" id="yibanli<ww:property value="activityInstId"/>">
        <form path="<%=java.net.URLEncoder.encode(currForm.getPath())%>"  formname="<%=currForm.getName()%>" formId="<%=currForm.getId()%>" >											
		<jsp:include page='<%=jspRunTimeUrl%>' flush="" />	
		</form>	