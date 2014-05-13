<%@ page contentType="text/html; charset=UTF-8"%>
<%
String contextpath = request.getContextPath() + "/";
 %>
<HTML>
<HEAD>

<TITLE>新增会议</TITLE>
<script type="text/javascript">
	var context="/";
</script>
<script type="text/javascript" src="<%=contextpath%>js/extAll.js"></script>
<script type="text/javascript" src="<%=contextpath%>usm/js/mettingAction.js" defer="defer"></script>
</HEAD>
<BODY onload="createMettingLbWindow('0')">
</BODY>
</HTML>