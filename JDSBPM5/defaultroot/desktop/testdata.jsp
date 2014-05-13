<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%   String contextpath = request.getContextPath() ; response.setContentType("text/html;charset=utf-8");

%>
{totalCount:<ww:property value="totalCount"/>,
root:[
<ww:iterator value="gzListBean" status="rowstatus">
    {
      uuid:"<ww:property value="uuid"/>",
      namemc:"<ww:property value="name"/>",
      fenshu:"<ww:property value="fenshu"/>",
      kaishishijian:"<ww:property value="kaishishijian"/>",
      jieshushijian:"<ww:property value="jieshushijian"/>",
      banbenhao:"<ww:property value="banbenhao"/>",
      biaohao:"<ww:property value="biaohao"/>",
      xiangxi:"<a href='javascript:void(0)' onclick=window.top.testahref('<ww:property value="uuid"/>','<ww:property value="name"/>','<ww:property value="fenshu"/>','<ww:property value="kaishishijian"/>','<ww:property value="jieshushijian"/>','<ww:property value="banbenhao"/>','<ww:property value="biaohao"/>')>详细</a>"
      }  
      <ww:if test ="#rowstatus.count < gzListBean.size">
      , 
      </ww:if>
   </ww:iterator>     
 ]
}