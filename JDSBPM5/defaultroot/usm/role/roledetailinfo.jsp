
<jsp:directive.page import="net.itjds.usm.persistence.model.Org"/>
<jsp:directive.page import="net.itjds.usm.persistence.model.Usergroup"/>
<jsp:directive.page import="net.itjds.usm.persistence.model.Role"/>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="utf8" %>
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
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src='<%=contextpath%>js/ext/ext-all.js'></script>
  
    <script type="text/javascript" src="<%=contextpath%>usm/role/js/roledetailinfo.js"  defer="defer"></script>
    <link rel="stylesheet" type="text/css" href="<%=contextpath %>usm/css/grid-examples.css" />
  
</head>
<body>
<% 		
		Role role=(Role)request.getAttribute("role");
		out.println("<script type='text/javascript'>");
			out.println("var roleid='"+role.getRoleid()+"'");
			out.println("var cnname='"+role.getCnname()+"'");
		out.println("</script>");
		out.flush();
%>

</body>
</html>
