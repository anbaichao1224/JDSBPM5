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
      uuid:"<ww:property value="rollid"/>",
      title:"<ww:if test="filetype==1"><a href=javascript:void(0) onclick=opentype('/roll_findById.action?rollbean.uuid=<ww:property value="rollid"/>&&rollbean.ispepodom=1','1000','800')></ww:if><ww:elseif test="filetype==2"><a href=javascript:void(0) onclick=window.top.openUrlWin('/data_findById.action?dataId=<ww:property value="rollid"/>','档案信息')></ww:elseif><ww:elseif test="filetype==3"><a href=\"javascript:window.top.openpdfgd('<ww:property value="rollid"/>')\"></ww:elseif> <ww:property value="rollname"/></a>",
      creator:"<ww:property value="starttime"/>",  
      createdate:"<ww:property value="endtime"/>" 
      }  
       <ww:if test ="#rowstatus.count < datalist.size">
      , 
      </ww:if>
   </ww:iterator>     
 ]
}

 