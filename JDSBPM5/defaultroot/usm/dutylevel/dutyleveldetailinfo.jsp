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
   	       var levelid='<s:property value="level.levelid"/>';
   	       var cnname='<s:property value="level.cnname"/>';
</script> 
     <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src='<%=contextpath%>js/ext/ext-all.js'></script>
  
    <script type="text/javascript" src="<%=contextpath%>usm/dutylevel/js/dutyeveldetailinfo.js" defer="defer"></script>
    <link rel="stylesheet" type="text/css" href="../css/grid-examples.css" />
  
</head>
<body>

</body>
</html>
