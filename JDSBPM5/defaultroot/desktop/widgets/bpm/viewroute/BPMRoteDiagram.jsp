<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="ww" %>	
<%
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
String contextpath = request.getContextPath() + "/";
%>
{
 <ww:iterator value="pdvList" status="rows">
 {
 	id:		<ww:property value="activityDefId"/>,
 	value:		<ww:property value="name"/>
 }
 	<ww:if test="#rows.count < pdvList.size">
	 ,
	 </ww:if>
 </ww:iterator>
}
