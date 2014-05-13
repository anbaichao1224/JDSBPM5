<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%@page import="net.itjds.j2ee.util.UUID" %>
<%
String contextpath = request.getContextPath() + "/";
String uuid = (new UUID()).toString();
 %>
<HTML>
	<HEAD>

		<TITLE>新增会议</TITLE>
		<script type="text/javascript">
	var context="/";
</script>
 
<script type="text/javascript" src="/js/extAll.js"></script>
<script type="text/javascript" src="<%=contextpath %>desktop/js/CreateGrid.js"></script>
<script  src="/js/JDS.js" type="text/javascript"></script>  
<script  src="/desktop/widgets/columntreemetting/js/mettinglistshow.js" type="text/javascript"></script>  
<script type="text/javascript">
	Ext.onReady(function(){
	
	mettinglistshow('<%=request.getParameter("liststatus")%>');
	});
</script>
	</HEAD>
	<BODY>
	</BODY>
</HTML>