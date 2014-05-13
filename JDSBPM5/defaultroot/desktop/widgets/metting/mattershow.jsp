<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/usm/common/taglibs.jsp"%>

 <%
String contextpath = request.getContextPath() + "/";
String oid = request.getParameter("orgid");
%>
<html>
<head>
 <script type="text/javascript">
   	       var orgid="<%=oid%>"; //"${param.orgid}";
   	             
</script>

    <link rel="stylesheet" type="text/css" href="<%=contextpath%>usm/resources/css/file-upload.css"/>
     <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script type="text/javascript">
	var context="/";
</script>
<script type="text/javascript" src="/js/extAll.js"></script>
    <script type="text/javascript" src="/desktop/js/CreateGrid.js"></script>
	<script  src="/js/JDS.js" type="text/javascript"></script>
    <script type="text/javascript" src="/usm/js/FileUploadField.js" ></script>
    <script type="text/javascript" src="/usm/js/mettingAction.js" defer="defer"></script>
	<script type="text/javascript">
	Ext.onReady(function(){
		newceshi();
	});
	</script>
</head>
<script type="text/javascript" src="<%=contextpath%>usm/resources/examples.js"></script>

</html>
