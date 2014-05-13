<%@ page language="java" errorPage="/error.jsp" pageEncoding="utf8" %>
<%
String contextpath = request.getContextPath() + "/";
String orglev = request.getParameter("orglevel");
String poid = request.getParameter("parentorgid");
%>
<html>
<head>
<script type="text/javascript">
   	       var orglevel="<%=orglev%>"; 
   	       var parentorgid="<%=poid%>"; //"${param.parentorgid}";
</script>
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src='<%=contextpath%>js/ext/ext-all.js'></script>
    <script type="text/javascript" src="<%=contextpath%>usm/js/states.js"></script>
    <script type="text/javascript" src="<%=contextpath%>usm/depart/js/chengbanbumenaddwindow.js" defer="defer"></script>

    

</head>
<script type="text/javascript" src="<%=contextpath%>usm/resources/examples.js"></script><!-- EXAMPLES -->

</html>
