<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="ww" %>	
<%
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
String contextpath = request.getContextPath() + "/";
%> 	
{
   
   performTrees:[
            <ww:iterator value="routeDefs" status="routs">
						{
						title:'<ww:property value="name"/>',
						routeDefId:'<ww:property value="routeDefId"/>',
						toActivityDefId:'<ww:property value="ToActivityDefId"/>',
						perfromType:'<ww:property value="getPerformType(routeDefId)"/>'
						}	
						<ww:if test="!#routs.last">,</ww:if>
		     </ww:iterator>
  ]
}
