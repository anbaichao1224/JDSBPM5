<%@ page language="java" errorPage="/error.jsp" pageEncoding="utf8" %>
<%String helloStr = request.getParameter("helloStr");  %>
<html>
  <head><title>Simple jsp page</title></head>
  <body>
    <%=helloStr %>
  </body>
</html>