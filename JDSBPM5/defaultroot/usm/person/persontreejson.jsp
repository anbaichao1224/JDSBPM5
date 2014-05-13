<%@page  contentType="text/html; charset=GB2312" language="java" %>
<%
		String json=request.getAttribute("json").toString();
		out.print("[");
		out.println(json);
		out.print("]");
%>