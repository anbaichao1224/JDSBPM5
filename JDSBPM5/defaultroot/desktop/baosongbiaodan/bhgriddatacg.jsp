<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%   String contextpath = request.getContextPath() ; response.setContentType("text/html;charset=utf-8");

%>
{totalCount:<ww:property value="totalCount"/>,
root:[
<ww:iterator value="bdListBean" status="rowstatus">
    {
      uuid:"<ww:property value="uuid"/>",
      bt:"<ww:property value="bt"/>",
      sbsj:"<ww:property value="sbsj"/>",
      djr:"<ww:property value="djr"/>",
      zt:"<ww:property value="zt"/>",
      dw:"<ww:property value="dw"/>",
      xiangxi:"<a href='javascript:void(0)' onclick=window.top.showbhbd('<ww:property value="uuid"/>')>详细</a>"
      }  
      <ww:if test ="#rowstatus.count < bdListBean.size">
      , 
      </ww:if>
   </ww:iterator>     
 ]
}

 