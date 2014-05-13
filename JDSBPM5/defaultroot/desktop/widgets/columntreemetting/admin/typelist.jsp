<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%   String contextpath = request.getContextPath() ; response.setContentType("text/html;charset=utf-8");

%>
{
totalCount:<ww:property value="totalCount"/>, 
root:[
<ww:iterator value="mtbeanlist" status="rowstatus">
    {
      uuid:"<ww:property value="uuid"/>",
      namemc:"<a href=javascript:void(0) onclick=opentype('mtype_getById.action?uuid=<ww:property value="uuid"/>')> <ww:property value="lxmc"/></a>",
      creator:"<ww:property value="creator"/>",
      createdate:"<ww:property value="createdate"/>"     
      }  
       <ww:if test ="#rowstatus.count < mtbeanlist.size">
      , 
      </ww:if>
   </ww:iterator>     
 ]
}

 