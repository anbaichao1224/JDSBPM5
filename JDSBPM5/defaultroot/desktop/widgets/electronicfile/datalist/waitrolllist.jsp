<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%   String contextpath = request.getContextPath() ; response.setContentType("text/html;charset=utf-8");

%>
{
totalCount:<ww:property value="totalCount"/>, 
root:[
<ww:iterator value="datalist" status="rowstatus">
    {
      uuid:"<ww:property value="dataId"/>",
      //namemc:"<a href=javascript:void(0) onclick=opentype('/data_findById.action?dataId=<ww:property value="dataId"/>')> <ww:property value="title"/></a>",
     namemc:"<a href=javascript:void(0) onclick=window.top.openUrlWin('/data_findById.action?dataId=<ww:property value="dataId"/>','档案信息')> <ww:property value="title"/></a>",
      creator:"<ww:property value="personname"/>",
      createdate:"<ww:property value="createdate"/>",
      processName:"<ww:property value="processDefId"/>"    
      }  
       <ww:if test ="#rowstatus.count < datalist.size">
      , 
      </ww:if>
   </ww:iterator>     
 ]
}

 