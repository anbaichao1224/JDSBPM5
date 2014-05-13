<%@page  contentType="text/html; charset=UTF-8" language="java" %>
<%
		String json=request.getAttribute("json").toString();
		out.print("[");
		out.println(json);
		out.print("]");
%>
