<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%   String contextpath = request.getContextPath() ; response.setContentType("text/html;charset=utf-8");

%>
{
totalCount:<ww:property value="totalCount"/>, 
root:[
<ww:iterator value="wzlist" status="rowstatus">
    {
      uuid:"<ww:property value="uuid"/>",
     
      namemc:"<a href=javascript:void(0) onclick=addWenzhong('<ww:property value="uuid"/>','<ww:property value="wzname"/>','<ww:property value="wzename"/>')> <ww:property value="wzname"/></a>",
      creator:"<ww:property value="creator"/>",
      createdate:"<ww:property value="createdate"/>",
       cz:"<a href=javascript:void(0) onclick=wenzhongList('<ww:property value="uuid"/>')>查看</a>"
      
      }  
       <ww:if test ="#rowstatus.count < wzlist.size">
      , 
      </ww:if>
   </ww:iterator>     
 ]
}

 