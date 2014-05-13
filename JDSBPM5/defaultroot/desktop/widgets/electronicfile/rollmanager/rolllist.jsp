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
      uuid:"<ww:property value="uuid"/>",
      namemc:"<a href=javascript:void(0) onclick=opentype('/roll_findById.action?rollbean.uuid=<ww:property value="uuid"/>','1150','800')> <ww:property value="rollname"/></a>",
      rollnum:"<ww:property value="rollnum"/>",
      rollstatus:"<ww:if test="status==0">未封卷</ww:if><ww:else>已封卷</ww:else>",
      creator:"<ww:property value="creator"/>",
      createdate:"<ww:property value="createdate"/>"     
      }  
       <ww:if test ="#rowstatus.count < rolllist.size">
      , 
      </ww:if>
   </ww:iterator>     
 ]
}

 