<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%   String contextpath = request.getContextPath() ; response.setContentType("text/html;charset=utf-8");

%>
{
totalCount:<ww:property value="totalCount"/>, 
root:[
<ww:iterator value="mlist" status="rowstatus">
    {
      uuid:"<ww:property value="tid"/>",
      namemc:"<a href=javascript:void(0) onclick=window.top.opentype('/desktop/widgets/columntreemetting/treemetting_querymodel.action?liststatus=<ww:property value="liststatus"/>&mettinguuid=<ww:property value="tid"/>','1100','800')> <ww:property value="name"/></a>",
      kssj:"<ww:property value="kssj"/>",
      jssj:"<ww:property value="jssj"/>",
      creator:"<ww:property value="creator"/>",
      createdate:"<ww:property value="createdate"/>"     
      }  
       <ww:if test ="#rowstatus.count < mlist.size">
      , 
      </ww:if>
   </ww:iterator>     
 ]
}

 