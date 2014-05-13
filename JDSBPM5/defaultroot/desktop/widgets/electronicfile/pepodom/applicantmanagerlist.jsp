<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%   String contextpath = request.getContextPath() ; response.setContentType("text/html;charset=utf-8");
String basePath = request.getScheme() + "://"
+ request.getServerName() + ":" + request.getServerPort()
+ contextpath;
%>
{
totalCount:<ww:property value="totalCount"/>, 
root:[
<ww:iterator value="rolllist" status="rowstatus">
    {
      uuid:"<ww:property value="uuid"/>",
      namemc:"<ww:if test="filetype==1"><a href=javascript:void(0) onclick=opentype('/roll_findById.action?rollbean.uuid=<ww:property value="rollid"/>&&rollbean.ispepodom=1','1150','800')></ww:if><ww:elseif test="filetype==2"><a href=javascript:void(0) onclick=window.top.openUrlWin('/data_findById.action?dataId=<ww:property value="rollid"/>','档案信息')></ww:elseif><ww:elseif test="filetype==3"><a href=\"javascript:window.top.openpdfgd('<ww:property value="rollid"/>','<%=basePath %>')\"></ww:elseif> <ww:property value="rollname"/></a>",
      applicant:"<ww:property value="applicant"/>",
      applicantdate:"<ww:property value="applicantdate"/>",
      status:"<ww:if test="ispass==1"><font color='blue'>待审核</font></ww:if><ww:elseif test="ispass==2">通过</ww:elseif><ww:else><font color='red'>未通过</font></ww:else>",
      verifier:"<ww:property value="verifier"/>",
      verifierdate:"<ww:property value="verifierdate"/>", 
       filetype:"<ww:if test="filetype==1">案卷</ww:if><ww:elseif test="filetype==2">文件</ww:elseif><ww:elseif test="filetype==3">附件下载</ww:elseif>",
      starttime:"<ww:property value="starttime"/>",
      endtime:"<ww:property value="endtime"/>"   
      }  
       <ww:if test ="#rowstatus.count < rolllist.size">
      , 
      </ww:if>
   </ww:iterator>     
 ]
}

 