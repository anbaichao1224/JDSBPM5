<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"%>
<%
String contextpath = request.getContextPath() + "/";
String syid = request.getParameter("sysid");
String typ = request.getParameter("type");
%>
<html>
<head> 
    <title>ExtJS.com Forums</title>
    <script type="text/javascript">
   	       var sysid="<%=syid%>";
   	       var type="<%=typ%>"; 
   	</script> 
     <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
     <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
     <script src="<%=contextpath%>js/ext/ext-all.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>usm/css/menus.css" />
    <script type="text/javascript" src="js/departright-index.js" defer="defer"></script>
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