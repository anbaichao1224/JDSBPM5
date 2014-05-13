<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%   String contextpath = request.getContextPath() ; response.setContentType("text/html;charset=utf-8");

%>
{
totalCount:<ww:property value="totalCount"/>, 
root:[
<ww:iterator value="tjlist" status="rowstatus">
    {
      uuid:"<ww:property value="uuid"/>",
      status:"<ww:property value="getPorcessInst().startTime"/></a>"  
      }  
       <ww:if test ="#rowstatus.count < tjlist.size">
      , 
      </ww:if>
   </ww:iterator>     
 ]
}

 