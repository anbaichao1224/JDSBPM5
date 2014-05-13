
<jsp:directive.page import="net.itjds.usm.persistence.model.Role"/>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="utf8" %>
<%
String contextpath = request.getContextPath() + "/";
%>
<html>
<% 		
		Role role=(Role)request.getAttribute("role");
		out.println("<script type='text/javascript'>");
			out.println("var roleid='"+role.getRoleid()+"'");
			out.println("var cnname='"+role.getCnname()+"'");
			out.println("var adminflag='"+role.getAdminflag()+"'");
			out.println("var createtime='"+role.getCreatetime()+"'");
			out.println("var roledesc='"+role.getRoledesc()+"'");
			
		out.println("</script>");
		out.flush();
%>
<head>

    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src='<%=contextpath%>js/ext/ext-all.js'></script>
    <script type="text/javascript" src="<%=contextpath%>usm/js/states.js"></script>
    <script type="text/javascript" src="<%=contextpath%>usm/role/js/rolemodify.js"  defer="defer"></script>

    

</head>
<script type="text/javascript" src="<%=contextpath%>usm/resources/examples.js"></script>
</html>
