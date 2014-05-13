<%@ include file="/usm/common/taglibs.jsp"%>
<jsp:directive.page import="net.itjds.usm.persistence.model.Usergroup"/>
<%
String contextpath = request.getContextPath() + "/";
String sysid = request.getParameter("sysid");
String type =  request.getParameter("type");
%>
<html>
<head> 
    <title>部门管理中心</title>
    <script type="text/javascript">

   	       var sysid="<%=sysid%>";//子系统id
   	       var type="<%=type%>"; 
   	   var grpid='<s:property value="usergroup.grpid"/>';
	   var grpname='<s:property value="usergroup.grpname"/>';
	   var grpflag='<s:property value="usergroup.grpflag"/>'; 
	   var createtime='<s:property value="usergroup.createtime"/>'; 
	   var grpdesc='<s:property value="usergroup.grpdesc"/>';
</script>

    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src='<%=contextpath%>js/ext/ext-all.js'></script>
    <script type="text/javascript" src="<%=contextpath%>usm/js/states.js"></script>
    <script type="text/javascript" src="<%=contextpath %>usm/usergroup/js/usergroupmodify.js" defer="defer"></script>

    
</head>
<body>
<script type="text/javascript" src="<%=contextpath%>usm/resources/examples.js"></script>

</body>
</html>
