<%@ include file="/usm/common/taglibs.jsp"%>
<%
String contextpath = request.getContextPath() + "/";
%>
<html>
<head>
     <title></title>
     <link rel="stylesheet" type="text/css" href="<%=contextpath%>usm/resources/css/file-upload.css"/>
     <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
     <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
     <script src='<%=contextpath%>js/ext/ext-all.js'></script>
    <script type="text/javascript" src="<%=contextpath%>usm/resources/redresourcesInfo.js" defer="defer"></script>
    <script type="text/javascript" src="<%=contextpath%>usm/js/FileUploadField.js"></script>
</head>
   <script type='text/javascript'>	
	var moduleid='<s:property value="module.moduleid"/>';
	var cnname='<s:property value="module.cnname"/>';
	var enname='<s:property value="module.enname"/>';
	var memo='<s:property value="module.memo"/>';
	
	var funcurl='<s:property value="module.funcurl" escape="false"/>';
	var navurl='<s:property value="module.navurl" escape="false"/>';
	
	var icon='<s:property value="module.icon"/>';
	var checked1=<s:property value="module.leftframeenabled>0?true:false"/>;
	var checked2=<s:property value="module.enabled>0?true:false"/>;
	var checked3=<s:property value="module.needright>0?true:false"/>;
	var checked4=<s:property value="module.adminflag>0?true:false"/>;
	
	
	var uuid='<s:property value="moduleproperties.uuid"/>';
	var propertiesfastheight='<s:property value="moduleproperties.propertiesfastheight"/>';
	var propertiesfastwidth='<s:property value="moduleproperties.propertiesfastwidth"/>';
	var propertieswindowheight='<s:property value="moduleproperties.propertieswindowheight"/>';
	var propertieswindowwidth='<s:property value="moduleproperties.propertieswindowwidth"/>';
	var propertieswindowx='<s:property value="moduleproperties.propertieswindowx"/>';
	var propertieswindowy='<s:property value="moduleproperties.propertieswindowy"/>';

	var propertieswindowmax=<s:property value="moduleproperties.propertieswindowmax=='on'?true:false"/>;
	var propertieswindowmin=<s:property value="moduleproperties.propertieswindowmin=='on'?true:false"/>;
	var propertieswindowclose=<s:property value="moduleproperties.propertieswindowclose=='on'?true:false"/>;
	
	</script>
<body>
</body>
</html>
