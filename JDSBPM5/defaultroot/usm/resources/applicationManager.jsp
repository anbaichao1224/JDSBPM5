
<%@ page language="java" errorPage="/error.jsp" pageEncoding="utf-8" %>
<%
String contextpath = request.getContextPath() + "/";
String sysid = request.getParameter("sysid");
%>
<html>
<head>
<script type="text/javascript">
 var sysid='<%=sysid%>';
 
</script>
    <title>应用管理</title>

    <link rel="stylesheet" type="text/css" href="<%=contextpath%>usm/resources/css/file-upload.css"/>
     <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
     <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
     <script src='<%=contextpath%>js/ext/ext-all.js'></script>
 	<script type="text/javascript" src="<%=contextpath%>usm/js/FileUploadField.js"></script>
    <script type="text/javascript" src="<%=contextpath%>usm/resources/applicationManager.js" defer="defer"></script>
</head>

<body> 
<div id="header"><div style="float:left;margin:5px;" class="x-small-editor">
	<div id="container">
    <div id="toolbar"></div>
</div>	
</div>
</div>
</body>
</html>
