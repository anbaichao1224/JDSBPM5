<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%   String contextpath = request.getContextPath() ; response.setContentType("text/html;charset=utf-8");

%>
{
totalCount:<ww:property value="totalCount"/>, 
root:[
<ww:iterator value="mypromptlist" status="rowstatus">
    {
      uuid:"<ww:property value="uuid"/>",
      title:"<a href=javascript:void(0) onclick=openPrompt('<ww:property value="uuid"/>','<ww:property value="title"/>','<ww:property value="content"/>','<ww:property value="begindate"/>')> <ww:property value="title"/></a>",
      senddate:"<ww:property value="createdate"/>"     
      }  
       <ww:if test ="#rowstatus.count < mypromptlist.size">
      , 
      </ww:if>
   </ww:iterator>     
 ]
}

 