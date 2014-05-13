<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%   String contextpath = request.getContextPath() ; response.setContentType("text/html;charset=utf-8");

%>
{
totalCount:<ww:property value="totalCount"/>, 
root:[
<ww:iterator value="modellist" status="rowstatus">
    {
      uuid:"<ww:property value="tid"/>",
      //namemc:"<a href=javascript:void(0) onclick=window.top.opentype('/desktop/widgets/columntreemetting/addMetting.jsp?mettinguuid=<ww:property value="tid"/>&liststatus=10','830','800')> <ww:property value="name"/></a>",
      namemc:"<a href=javascript:void(0) onclick=window.top.opentype('treemetting_querymodel.action?mettinguuid=<ww:property value="tid"/>&liststatus=10','1150','800')> <ww:property value="name"/></a>",
      creator:"<ww:property value="creator"/>",
      createdate:"<ww:property value="createdate"/>"     
      }  
       <ww:if test ="#rowstatus.count < modellist.size">
      , 
      </ww:if>
   </ww:iterator>     
 ]
}

 