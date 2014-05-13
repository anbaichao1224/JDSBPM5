<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%   String contextpath = request.getContextPath() ; response.setContentType("text/html;charset=utf-8");

%>
{
totalCount:<ww:property value="totalCount"/>, 
root:[
<ww:iterator value="nlist" status="rowstatus">
    {
      uuid:"<ww:property value="uuid"/>",
      niticetitle:"<a href=javascript:void(0) onclick=window.top.opentype('mnotice_findById.action?uuid=<ww:property value="uuid"/>','1175','800')> <ww:property value="niticetitle"/></a>",
      issuerdate:"<ww:property value="issuerdate"/>",
      createdept:"<ww:property value="createdept"/>"     
      }  
       <ww:if test ="#rowstatus.count < nlist.size">
      , 
      </ww:if>
   </ww:iterator>     
 ]
}

 