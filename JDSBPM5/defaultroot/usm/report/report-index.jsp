<%@ include file="/usm/common/taglibs.jsp" %>
<%
String contextpath = request.getContextPath() + "/";
%>
<html>
<head>

    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src="<%=contextpath%>js/ext/ext-all.js"></script>
    <script type="text/javascript" src="/usm/js/FileUploadField.js" ></script>
    <script type="text/javascript" src="<%=contextpath%>usm/js/reportgrid.js" defer="defer"></script>
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>usm/css/grid-examples.css" />
    <link rel="stylesheet" type="text/css" href="/usm/resources/css/file-upload.css"/>
</head>
<body>
<input type='hidden' id='memo' name='memo' >
<input type='hidden' id='cnname' name='cnname' >
</body>
</html>
