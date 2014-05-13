<%@ page language="java" errorPage="/error.jsp" pageEncoding="utf8" %>
<%
String contextpath = request.getContextPath() + "/";
String sysid = request.getParameter("sysid");
String type =  request.getParameter("type");
%>
<html>
<head> 

    <script type="text/javascript">

   	       var sysid="<%=sysid%>";//子系统id
   	       var type="<%=type%>"; ;
</script>
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src='<%=contextpath%>js/ext/ext-all.js'></script>
    <script type="text/javascript" src="<%=contextpath%>usm/js/states.js"></script>
    <script type="text/javascript" src="js/usergroupadd.js" defer="defer"></script>
</head>
<script type="text/javascript" src="<%=contextpath%>usm/resources/examples.js"></script>
</html>
