<%@ include file="/usm/common/taglibs.jsp"%>
<%
String contextpath = request.getContextPath() + "/";
String syid = request.getParameter("sysid");
String paid = request.getParameter("parentappcatalogid");
%>
<html>
<head>
	<script type="text/javascript">
	 var sysid="<%=syid%>";//"${param.sysid}";
	 var parentappcatalogid="<%=paid%>";//"${param.parentappcatalogid}";
	</script>
    <title>Forms</title>

	<link rel="stylesheet" type="text/css" href="<%=contextpath%>usm/resources/css/file-upload.css"/>
     <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
     <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
     <script src='<%=contextpath%>js/ext/ext-all.js'></script>
	<script type="text/javascript" src="<%=contextpath%>usm/js/FileUploadField.js"></script>
    <script type="text/javascript" src="<%=contextpath%>usm/js/states.js"></script>
    <script type="text/javascript" src="applyInsert.js" defer="defer"></script>

    <!-- Common Styles for the examples -->
    <link rel="stylesheet" type="text/css" href="examples.css"/>
</head>
<body>
<script type="text/javascript" src="<%= contextpath%>/usm/resources/examples.js"></script>
</body>
</html>
