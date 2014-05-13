<%@ page language="java" errorPage="/error.jsp" pageEncoding="utf-8"%>
<%
String contextpath = request.getContextPath() + "/";
String syid = request.getParameter("sysid");
String typ = request.getParameter("type");
String orglev = request.getParameter("orglevel");

%>
<html>
<head> 
    <title>部门管理中心</title>
    <script type="text/javascript">

   	    var sysid="<%=syid%>";//子系统id
   	       var type="<%=typ%>"; //角色管理参数
		var orglevel = "<%=orglev%>";
			
			   	      
   	</script> 
     <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
     <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
     <script src="<%=contextpath%>js/ext/ext-all.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>usm/css/menus.css" />
   <script type="text/javascript" src="<%=contextpath%>desktop/baosongbiaodan/bs_departtree.js"></script>
    <script type="text/javascript" src="js/displayAction.js" defer="defer"></script>
    <link rel="stylesheet" type="text/css" href="css/forum.css" />
</head>
	<body>
		<div id="header">
            <div style="float:left;margin:5px;" class="x-small-editor">
			<div id="container">
		    </div>	
		    </div>
		</div>
	</body>
</html>