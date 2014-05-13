<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%   String contextpath = request.getContextPath() ; response.setContentType("text/html;charset=utf-8");

%>
{
totalCount:<ww:property value="totalCount"/>, 
root:[
<ww:iterator value="rolllist" status="rowstatus">
    {
      uuid:"<ww:property value="dataId"/>",
      namemc:"<ww:property value="title"/>",
      creator:"<ww:property value="personname"/>",
      createdate:"<ww:property value="createdate"/>"     
      }  
       <ww:if test ="#rowstatus.count < rolllist.size">
      , 
      </ww:if>
   </ww:iterator>     
 ]
}

 