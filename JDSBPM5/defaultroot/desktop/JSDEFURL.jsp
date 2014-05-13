<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page  import="com.opensymphony.xwork2.util.OgnlValueStack" %>
<%@ taglib uri="/struts-tags" prefix="ww"%>



<%   
	OgnlValueStack stack = (OgnlValueStack) request.getAttribute("struts.valueStack");
	String path = (String)stack.findValue("JSDEFURL");
	%>

<jsp:include page="<%=path%>" />	

 




 
