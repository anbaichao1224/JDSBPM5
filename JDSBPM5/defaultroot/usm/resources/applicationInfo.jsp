<%@ include file="/usm/common/taglibs.jsp"%>
<%
String contextpath = request.getContextPath() + "/";
%>
<html>
<head>
    <title>Forms</title>

    <link rel="stylesheet" type="text/css" href="<%=contextpath%>usm/resources/css/file-upload.css"/>
     <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
     <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
     <script src='<%=contextpath%>js/ext/ext-all.js'></script>
    <script type="text/javascript" src="<%=contextpath%>usm/resources/states.js"></script>
	<script type="text/javascript" src="<%=contextpath%>usm/resources/examples.js"></script>
	 <script type="text/javascript" src="<%=contextpath%>usm/js/FileUploadField.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>usm/resources/examples.css"/>

    <script type="text/javascript" src="<%=contextpath%>usm/resources/applicationInfo.js" defer="defer"></script>
    <script type='text/javascript'>
	var sysid='<s:property value="application.sysid"/>';
	var cnname='<s:property value="application.cnname"/>';
	var enname='<s:property value="application.enname"/>';
	var appcatalogid='<s:property value="application.appcatalogid"/>';
	var memo='<s:property value="application.demo"/>';
	var enabled=<s:property value="application.enabled>0?true:false"/>;
	var haschild=<s:property value="application.haschild>0?true:false"/>;
	</script>
</head>
<body>
</body>
</html>
