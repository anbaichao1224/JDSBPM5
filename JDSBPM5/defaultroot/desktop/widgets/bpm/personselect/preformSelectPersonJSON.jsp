<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="ww" %>	
<%
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
String contextpath = request.getContextPath() + "/";
%> 	

<ww:property value="treeType=='PERFORM' ? performersExtStr:readExtStr" escape="false"/>