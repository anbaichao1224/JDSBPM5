<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%  String contextpath = request.getContextPath()+ "/" ; %>

{images: [
<ww:iterator value="wallpapers" status="rowstatus">
    {
      id:'<ww:property value="id"/>',
      name:'<ww:property value="name"/>',
      pathtothumbnail:'<ww:property value="pathtothumbnail"/>',
      pathtofile:'<ww:property value="pathtofile"/>'
               }  
      <ww:if test ="#rowstatus.count < wallpapers.size">
      , 
      </ww:if>
     </ww:iterator>    

]}





 
