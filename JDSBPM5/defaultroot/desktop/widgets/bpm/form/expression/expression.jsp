<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/struts-tags" prefix="ww" %>

{
   updateFields:[
    <ww:iterator value="updataList" status="routs">
   {
   
     name:'<ww:property value="key"/>',
     value:"<ww:property value="value" escape="false"/>"
	
	 }
	    <ww:if test="!#routs.last">,</ww:if>
	     </ww:iterator>
   ]
    
}