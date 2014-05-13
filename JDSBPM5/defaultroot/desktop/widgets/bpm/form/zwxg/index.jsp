<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
String path = request.getContextPath();
String contextpath = request.getContextPath() + "/";
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<script type="text/javascript" src="<%=contextpath%>js/formdisplay/displayAction.js"></script>
	<head>

		
	</head>
	<body>
		<div id="Upload-dlg"></div>
		<script type="text/javascript">
	//	showAccachWin();
		</script>
	</body>
</html>
