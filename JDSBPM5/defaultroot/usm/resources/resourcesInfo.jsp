<%@ include file="/usm/common/taglibs.jsp"%>
<%
String contextpath = request.getContextPath() + "/";
%>
<html>
<head>
    <title>Module Info</title>
<style>
#div1{
	position:absolute;
	z-index:1;
	left: 0px;
	top: 0px;
}
#div2{
	position:absolute;
	z-index:1;
	left: 350px;
	top: 0px;
}
</style>
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>usm/resources/css/file-upload.css"/>
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src='<%=contextpath%>js/ext/ext-all.js'></script>
    <script type="text/javascript" src="<%=contextpath%>usm/js/FileUploadField.js"></script>
     <script type="text/javascript" src="<%=contextpath%>usm/resources/examples.js"></script>
    <script type="text/javascript" src="<%=contextpath%>usm/resources/resourcesInfo.js"></script>
   
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>usm/resources/examples.css"/>
</head>
   <script type='text/javascript'>	
	var moduleid='<s:property value="module.moduleid"/>';
	var cnname='<s:property value="module.cnname"/>';
	var enname='<s:property value="module.enname"/>';
	var memo='<s:property value="module.memo"/>';
	
	var funcurl="<s:property value="@net.itjds.userclient.common.util.StringUtil@replace(module.funcurl,'\\\"', '##')" escape="false"/>";
	var navurl="<s:property value="@net.itjds.userclient.common.util.StringUtil@replace(module.navurl,'\\\"', '##')" escape="false"/>";
	var serialindex = '<s:property value="module.serialindex" escape="false"/>';
	
	var icon='<s:property value="module.icon"/>';
	var checked1=<s:property value="module.leftframeenabled>0?true:false"/>;
	var checked2=<s:property value="module.enabled>0?true:false"/>;
	var checked3=<s:property value="module.needright>0?true:false"/>;
	var checked4=<s:property value="module.adminflag>0?true:false"/>;
	var checked5=<s:property value="module.ifsso>0?true:false"/>;
	var sso_uuid='<s:property value="module.sso_uuid"/>';
	var uuid='<s:property value="moduleproperties.uuid"/>';
	var propertiesfastheight='<s:property value="moduleproperties.propertiesfastheight"/>';
	var propertiesfastwidth='<s:property value="moduleproperties.propertiesfastwidth"/>';
	var propertieswindowheight='<s:property value="moduleproperties.propertieswindowheight"/>';
	var propertieswindowwidth='<s:property value="moduleproperties.propertieswindowwidth"/>';
	var propertieswindowx='<s:property value="moduleproperties.propertieswindowx"/>';
	var propertieswindowy='<s:property value="moduleproperties.propertieswindowy"/>';
	var propertieswindowmax=<s:property value="moduleproperties.propertieswindowmax==\"on\"?true:false"/>;
	var propertieswindowmin=<s:property value="moduleproperties.propertieswindowmin==\"on\"?true:false"/>;
	var propertieswindowclose=<s:property value="moduleproperties.propertieswindowclose==\"on\"?true:false"/>;
	</script>
<body>

</body>
</html>
