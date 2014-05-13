<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="ww" %>	
<%
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
String contextpath = request.getContextPath() + "/";
%> 	

doOk();
porcess=0;
openWin('<%=contextpath%>demodisplay.action?activityInstId=<ww:property value="nextActivityInstId" escape="false"/>','<ww:property value="nextActivityInstId" escape="false"/>');

