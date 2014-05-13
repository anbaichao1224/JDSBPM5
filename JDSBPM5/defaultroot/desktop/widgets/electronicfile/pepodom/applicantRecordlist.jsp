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
      uuid:"<ww:property value="uuid"/>",
      namemc:"<ww:property value="rollname"/>",
      applicantdate:"<ww:property value="applicantdate"/>",
      status:"<ww:if test="ispass==1"><font color='blue'>待审核</font></ww:if><ww:elseif test="ispass==2">通过</ww:elseif><ww:else><font color='red'>未通过</font></ww:else>",
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

 