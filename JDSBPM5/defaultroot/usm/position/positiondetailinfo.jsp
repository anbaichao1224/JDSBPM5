<%@ include file="/usm/common/taglibs.jsp"%>

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
	       var positionid='<s:property value="position.positionid"/>';
	       var positionname='<s:property value="position.positionname"/>';
</script>
   	
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src='<%=contextpath%>js/ext/ext-all.js'></script>
  
    <script type="text/javascript" src="/usm/position/js/positiondetailinfo.js" defer="defer"></script>
  
  
</head>
<body>


</body>
</html>
