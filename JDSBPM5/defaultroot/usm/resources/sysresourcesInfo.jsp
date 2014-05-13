<%@ page language="java" errorPage="/error.jsp" pageEncoding="utf8" %>
<jsp:directive.page import="net.itjds.usm.persistence.model.Module"/>
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
 
    <script type="text/javascript" src="<%=contextpath%>usm/resources/sysresourcesInfo.js" defer="defer"></script>
    <script type="text/javascript" src="<%=contextpath%>usm/js/FileUploadField.js"></script>
	<script type="text/javascript" src="<%=contextpath%>usm/resources/examples.js"></script>
<link rel="stylesheet" type="text/css" href="<%=contextpath%>usm/resources/examples.css"/>
</head>
    <script type="text/javascript">
    	setTimeout("shan()",100);
    	
    	function shan(){
    		
    	}
    </script>
<body>

</body>
</html>
