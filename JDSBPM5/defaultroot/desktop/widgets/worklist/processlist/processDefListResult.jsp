<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%  String contextpath = request.getContextPath()+ "/" ; %>
 


{
totalCount:<ww:property value="totalCount"/>,
datas:[
 <ww:iterator value="processDefList" status="rowstatus">
    {
      rowdblclick:"newProcess('<ww:property value="processDefId"/>')",
      index:"<ww:property value="#rowstatus.index"/>",
      processDefId:"<ww:property value="processDefId"/>",
      processDefName:"<ww:property value="processDefName"/>",
      description:"<ww:property value="processDefName"/>",
       classification:"<ww:property value="classification"/>",
       limit:"<ww:property value="limit"/>",
       action: "<input type='button' style='border:1 ;height:15' name='action' value=' 启 动 '  onClick=\"newProcess('<ww:property value="processDefId"/>')\">"       
          }  
      <ww:if test ="#rowstatus.count < processDefList.size">
      , 
      </ww:if>
     </ww:iterator>    
 ]
}



 
