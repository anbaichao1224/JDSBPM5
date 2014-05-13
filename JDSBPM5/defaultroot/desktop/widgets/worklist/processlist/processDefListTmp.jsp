<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%  String contextpath = request.getContextPath()+ "/" ; %>




{images: [
<ww:iterator value="processDefList" status="rowstatus">
    {
      id:'<ww:property value="processDefId"/>',
      name:'<ww:property value="processDefName"/>',
      pathtothumbnail:'<ww:property value="'fdt/designer/imgs/defaultform.png'"/>'
               }  
      <ww:if test ="#rowstatus.count < processDefList.size">
      , 
      </ww:if>
     </ww:iterator>    

]}


 
