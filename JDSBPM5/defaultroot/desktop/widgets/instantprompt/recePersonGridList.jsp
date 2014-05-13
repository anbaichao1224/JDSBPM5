<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%   String contextpath = request.getContextPath() ; response.setContentType("text/html;charset=utf-8");

%>
{
totalCount:<ww:property value="totalCount"/>, 
root:[
<ww:iterator value="pplist" status="rowstatus">
    {
      uuid:"<ww:property value="uuid"/>",
      name:"<ww:property value="personname"/>",
      begindate:"<ww:property value="begindate"/>",
      iscancel:"<ww:if test="iscancel==0">正常</ww:if><ww:else>已取消</ww:else>"     
      }  
       <ww:if test ="#rowstatus.count < pplist.size">
      , 
      </ww:if>
   </ww:iterator>     
 ]
}

 