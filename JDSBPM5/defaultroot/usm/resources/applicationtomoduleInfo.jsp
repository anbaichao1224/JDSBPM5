<%@ include file="/usm/common/taglibs.jsp"%>
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
    <script type="text/javascript" src="<%=contextpath%>usm/js/FileUploadField.js"></script>
	<script type="text/javascript" src="<%=contextpath%>usm/resources/examples.js"></script>
    <script type="text/javascript" src="<%=contextpath%>usm/resources/applicationtomoduleInfo.js" defer="defer"></script>
 
<link rel="stylesheet" type="text/css" href="<%=contextpath%>usm/resources/examples.css"/>
</head>
    <script type='text/javascript'>
	var moduleid='<s:property value="module.moduleid"/>';
	var cnname='<s:property value="module.cnname"/>';
	var enname='<s:property value="module.enname"/>';
	var funcurl='<s:property value="module.funcurl"/>';
	var navurl='<s:property value="module.navurl"/>';
	var icon='<s:property value="module.icon"/>';
	var check1=<s:property value="module.leftframeenabled>0?true:false"/>;
	var check2=<s:property value="module.enabled>0?true:false"/>;
	var check3=<s:property value="module.needright>0?true:false"/>;
	var check4=<s:property value="module.adminflag>0?true:false"/>;
	</script>
<body>

</body>
</html>
