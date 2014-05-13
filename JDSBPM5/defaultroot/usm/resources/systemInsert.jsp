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
	<script type="text/javascript" src="<%=contextpath%>usm/js/FileUploadField.js"></script>
    <script type="text/javascript" src="<%=contextpath%>usm/js/states.js"></script>
   
    <!-- Common Styles for the examples -->
    <link rel="stylesheet" type="text/css" href="examples.css"/>
</head>
<center>
<body>
<script type="text/javascript" src="<%=contextpath%>usm/resources/examples.js"></script>
 <script type="text/javascript" src="systemInsert.js" defer="defer"></script>
</body></center>
</html>
