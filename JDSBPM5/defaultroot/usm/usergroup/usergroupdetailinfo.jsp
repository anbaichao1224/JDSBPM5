<%@ include file="/usm/common/taglibs.jsp"%>
<%
String  contextpath = request.getContextPath() + "/";
String sysid = request.getParameter("sysid");
String type =  request.getParameter("type");
%>
<html>
<head> 
    <title></title>
    <script type="text/javascript">

   	       var sysid="<%=sysid%>";//子系统id
   	       var type="<%=type%>"; 
   	        var grpid='<s:property value="usergroup.grpid"/>';
	       var grpname='<s:property value="usergroup.grpname"/>';
	       var grpdesc='<s:property value="usergroup.grpdesc"/>';
</script>


    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src='<%=contextpath%>js/ext/ext-all.js'></script>
  
    <script type="text/javascript" src="<%=contextpath %>usm/usergroup/js/usergroupdetailinfo.js" defer="defer"></script>
    <link rel="stylesheet" type="text/css" href="<%=contextpath %>usm/css/grid-examples.css" />
  
</head>
<body>


</body>
</html>
