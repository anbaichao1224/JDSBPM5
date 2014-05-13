<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" %>

<%
String contextpath = request.getContextPath() + "/";
String sysid = request.getParameter("sysid");
String template = request.getParameter("template");

%>
<html>
<head>
    <script type="text/javascript">   
    var sysid='<%=sysid%>';
    var p= '<%=template%>';    
    </script>
    <title>应用模块</title>

<link rel="stylesheet" type="text/css" href="<%=contextpath%>usm/resources/css/gridview.css"/>
        <link rel="stylesheet" type="text/css" href="<%=contextpath%>usm/resources/css/file-upload.css"/>
     <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
     <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
     <script src='<%=contextpath%>js/ext/ext-all.js'></script>
     <script type="text/javascript" src="<%=contextpath%>usm/resources/applicationTemplate.js" defer="defer"></script>
    <script type="text/javascript" src="<%=contextpath%>usm/js/FileUploadField.js"></script>
	<script type="text/javascript" src="<%=contextpath%>usm/resources/examples.js"></script>
<link rel="stylesheet" type="text/css" href="<%=contextpath%>usm/resources/examples.css"/>

</head>
<body>
</body>
</html>
