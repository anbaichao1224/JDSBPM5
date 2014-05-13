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
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src='<%=contextpath%>js/ext/ext-all.js'></script>
    <script type="text/javascript" src="<%=contextpath%>usm/resources/states.js"></script>
    <script type="text/javascript" src="/usm/js/FileUploadField.js" ></script>
    <script type="text/javascript" src="js/personInsertwindow.js" defer="defer"></script>

</head>
<script type="text/javascript" src="<%=contextpath%>usm/resources/examples.js"></script>
</html>
