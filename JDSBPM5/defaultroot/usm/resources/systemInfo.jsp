<%@ include file="/usm/common/taglibs.jsp"%>
<%
String contextpath = request.getContextPath() + "/";
%>
<html>
<head>

 
    <title>Forms</title>
      <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
     <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
     <script src='<%=contextpath%>js/ext/ext-all.js'></script>
     <link rel="stylesheet" type="text/css" href="<%=contextpath%>usm/resources/css/file-upload.css"/>
	<script type="text/javascript" src="<%=contextpath%>usm/resources/examples.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>usm/resources/examples.css"/>
	 <script type="text/javascript" src="<%=contextpath%>usm/js/FileUploadField.js"></script>
    <script type="text/javascript" src="<%=contextpath%>usm/resources/systemInfo.js" defer="defer"></script>

    <script type='text/javascript'>
	var uuid='<s:property value="system.uuid"/>';
	var sysid='<s:property value="system.sysid"/>';
	var syscnname='<s:property value="system.syscnname"/>';
	var sysdesc='<s:property value="system.sysdesc"/>';
	var url='<s:property value="system.url"/>';
	var icon='<s:property value="system.icon"/>';
	var check=<s:property value="system.guestenable>0?true:false"/>;
	</script>
</head>
<body>
</body>
</html>
