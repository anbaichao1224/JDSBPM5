<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%   String contextpath = request.getContextPath()+ "/" ; %>
 

{
totalCount:<ww:property value="totalCount"/>,
datas:[
   <ww:iterator value="list" status="historystatus">
    {
      index:"<ww:property value="#historystatus.index"/>",
      instId:"<ww:property value="instId"/>",
      performPerson:"<ww:property value="person"/>",
      name:"<ww:property value="name"/>",
      arriveTime:"<ww:property value="arriveTime"/>",
      startTime:"<ww:property value="startTime"/>",
      status:"<ww:property value="status"/>",
      historyId : "<ww:property value="historyId"/>"
          }  
      <ww:if test ="(#historystatus.count < list.size)">
      , 
      </ww:if>
      </ww:iterator>   
 ]
}



 
