<%@ page contentType="text/html; charset=UTF-8"%>
<%
String contextpath = request.getContextPath() + "/";
String orgname = request.getAttribute("orgname").toString();
String orgid = request.getAttribute("orgid").toString();
 %>
<HTML>
<HEAD>

<TITLE>日程查询</TITLE>
<script type="text/javascript">
	var context="/";
	var orgname = '<%=orgname%>';
	var orgid = '<%=orgid%>';
</script>
<script type="text/javascript" src="<%=contextpath%>js/extAll.js"></script>
<script type="text/javascript" src="<%=contextpath%>desktop/widgets/schedule/js/person-index.js" defer="defer"></script>
</HEAD>
<BODY>
	<div id="header"></div>
</BODY>
</HTML>