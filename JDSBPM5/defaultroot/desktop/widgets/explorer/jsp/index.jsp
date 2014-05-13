<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="ww" %>

<%

String contextpath = request.getContextPath() + "/";

%>

<html>
	<head>
	<script>
	var context='<%=contextpath%>';
	var personid='<ww:property value="person.ID"/>';
	var currUserName='<ww:property value="person.name"/>';
	




	
	
	
	</script>
	
  	<link rel='stylesheet' type='text/css' href='<%=contextpath%>explorer/codebase/skins/dhtmlXMenu_xp.css'></link>
       <link rel='stylesheet' type='text/css' href='<%=contextpath%>desktop/widgets/explorer/css/leftPanel.css'></link>
        <link rel="STYLESHEET" type="text/css" href="<%=contextpath%>explorer/codebase/skins/dhtmlXToolbar.css">
     <script type="text/javascript" src="<%=contextpath%>js/extAll.js"></script>
	 <script language="javascript" src="<%=contextpath%>js/dhtmlXAll.js"></script>
	<script  id="script_desktop" language="javascript" src="<%=contextpath%>desktop/js/deskTopAll.js"></script>			
	<script id="script_Jame" src="<%=contextpath%>desktop/widgets/jame/js/ext-jame/JameAll.js" type="text/javascript"></script>
	<script  src="<%=contextpath%>js/JDS.js" type="text/javascript"></script>
	<script  src="<%=contextpath%>desktop/js/desktop.js" type="text/javascript"></script>
	</head>
	<body>	
	<div id="expressionToolMenu"></div>
	<div id="tableTool"></div>
	<div id=JDSExplorer></div>
	</body>
	</html>