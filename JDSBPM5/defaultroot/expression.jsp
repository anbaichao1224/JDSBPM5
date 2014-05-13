<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww" %>
<%
String expression=(String) request.getParameter("expression");
%>
<ww:property value="<%=expression%>" escape="false"/>