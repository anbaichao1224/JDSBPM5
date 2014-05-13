<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
	<head>
		<base href="<%=basePath%>">
		<script>var context='<%=path%>';</script>
		
			<style type="text/css">
			.x-date-middle { padding-top:2px;padding-bottom:2px; width:130px;} 
			.update2 {background-image:url(<%=path%>/js/ext/images/icons/update2.gif ) !important;}
			.edit {background-image:url(<%=path%>/js/ext/images/edit.gif ) !important;}
			.zoom {background-image:url(<%=path%>/js/ext/images/zoom.png ) !important;}
			.add {background-image:url(<%=path%>/js/ext/images/add.gif) !important; }
			.remove { background-image:url(<%=path%>/js/ext/images/delete.gif) !important; }
			.serialindex{background-image:url(<%=path%>/js/ext/images/save.gif) !important;}
			.mobility{background-image:url(<%=path%>/js/ext/images/plugin.gif) !important;}
			.importPerson{background-image:url(<%=path%>/js/ext/images/plugin.gif) !important;}
		</style>
	</head>

	<body>
	</body>
	
		<title><ww:property value="$R(\"title\")" escape="false"/></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
	
        <script type="text/javascript" src="<%=path%>js/extAll.js"></script>
		<script type="text/javascript" src="<%=path%>/js/ext/TabCloseMenu.js"></script>
		<script type="text/javascript" src="<%=path%>/js/ext/PageSizePlugin.js"></script>
		<script type="text/javascript" src="<%=path%>js/JDS.js"></script>
		<script type="text/javascript" src="<%=path%>/usm2/js/usmall.js"></script>
	
		<script>
				Ext.BLANK_IMAGE_URL = "<%=path%>/js/ext/resources/images/default/s.gif";
			<ww:property value="$usmMain" escape="false"/>
		
			createViewport();	
			
		</script>
	
</html>
