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
     uuid:"<ww:property value="rollid"/>",
      namemc:"<a href=javascript:void(0) onclick=opentype('/roll_findById.action?rollbean.uuid=<ww:property value="rollid"/>','1150','800')> <ww:property value="rollname"/></a>",
      applicantdate:"<ww:property value="applicantdate"/>",
      verifier:"<ww:property value="verifier"/>",
      verifierdate:"<ww:property value="verifierdate"/>",  
      starttime:"<ww:property value="starttime"/>",
      endtime:"<ww:property value="endtime"/>"     
      }  
       <ww:if test ="#rowstatus.count < rolllist.size">
      , 
      </ww:if>
   </ww:iterator>     
 ]
}

 