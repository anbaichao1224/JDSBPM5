<%@page contentType="text/html;charset=UTF-8"%>
<%@ page import="net.itjds.bpm.client.ActivityInst"%>
<%@ page import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack,com.opensymphony.xwork2.ActionContext,net.itjds.fdt.define.designer.metadata.bean.DocumentBean"%>
<%@ page import="java.util.Map,com.opensymphony.xwork2.ActionContext,net.itjds.common.org.base.Person"%>
<%@include file="taglibs.jsp"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>

<%
String actionurl = request.getParameter("action");
String contextpath = request.getContextPath() + "/";
String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ contextpath;
DocumentBean docbean = (DocumentBean) ActionContext.getContext().getValueStack().findValue("$docInject.getDocBean()");
Person person1 = (Person)ActionContext.getContext().getValueStack().findValue("$currPerson");

%>
 
<html>
<head>
<title>文件正文</title>
<script type="text/javascript">
var context = '<%=contextpath %>';
</script>
<script type="text/javascript" src="<%=contextpath %>js/extAll.js"></script>
<script LANGUAGE="JavaScript" type="text/javascript">
Ext.onReady(function(){
	var docid = '<ww:property value="docid"/>';
		if (docid == "") {
            alert("url地址为空");
            return;
        }
        var ret = pdf.OpenRemotePdf('http://localhost:82/doctopdf_writeResponse.action?docid=<ww:property value="docid"/>');
        if (ret != 1) {
            alert("打开文件错误");
        }
        
      
});

</script>


</head>

<body>
<div>
<object id="pdf" classid="clsid:3542AF22-FBBF-4799-9EE7-DB428ED59DE8" width="907" height="615" title="测试">
</object>
</div>
</body>	   