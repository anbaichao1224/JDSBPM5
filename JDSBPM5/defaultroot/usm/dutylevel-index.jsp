<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"%>

<%
String contextpath = request.getContextPath() + "/";
String sysid = request.getParameter("sysid");
String type = request.getParameter("type");
%>
<html>
<head>

<script type="text/javascript">
   	       var sysid="<%=sysid%>";
   	       var type="<%=type%>";
   	</script>
    <title>ExtJS.com Forums</title>
         <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
     <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
     <script src='<%=contextpath%>js/ext/ext-all.js'></script>
<!--     <link rel="stylesheet" type="text/css" href="usm/css/menus.css" />
-->    <script type="text/javascript" src="js/dutylevel-index.js" defer="defer"></script>
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>usm/css/forum.css" />
</head>
	<body>
	
		<div id="header"><div style="float:left;margin:5px;" class="x-small-editor">
			<div id="container">
		    <div id="toolbar"></div>
		</div>	
		</div>
		</div>
	</body>
</html>