<%@page  contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="ww" %>	
<%
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
String contextpath = request.getContextPath() + "/";
%> 
<ww:property value="treeJson" escape="false"/>