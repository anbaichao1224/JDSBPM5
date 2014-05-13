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
	var mid = '<%=request.getParameter("mettinguuid")%>';
	var liststatus = '<%=request.getParameter("liststatus")%>';
</script>
		
		
		
		
		<!-- <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/columntree/css/ColumnNodeUI.css" />
		<link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/columntree/css/column-tree.css" />
		<script type="text/javascript" src="<%=contextpath%>js/ext/columntree/ColumnNodeUI.js"></script>
		<script type="text/javascript" src="<%=contextpath%>js/ext/columntree/column-tree.js"></script>
		<script type="text/javascript" src="<%=contextpath%>js/ext/columntree/code-display.js"></script> -->
		<!-- <link rel="STYLESHEET" type="text/css" href="codebase/dhtmlxgrid.css">
		
		<script src="<%=contextpath%>js/report/dhtmlXCommon.js"></script>
		<script src="<%=contextpath%>js/report/dhtmlXGrid.js"></script>
		<script src="<%=contextpath%>js/report/dhtmlXTreeGrid.js"></script>
		<script src="<%=contextpath%>js/report/dhtmlXGridCell.js"></script>-->
		<link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext3/resources/css/ext-all.css" />
    <!-- Common Styles for the examples -->
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext3/examples/shared/examples.css" />
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext3/examples/ux/css/ColumnNodeUI.css" />
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext3/examples/tree/column-tree.css" />
    <!-- GC -->
    <!-- LIBS -->
    <script type="text/javascript" src="<%=contextpath%>js/ext3/adapter/ext/ext-base.js"></script>
    <!-- ENDLIBS -->
    <script type="text/javascript" src="<%=contextpath%>js/ext3/ext-all.js"></script>
    <script type="text/javascript" src="<%=contextpath%>js/ext3/examples/code-display.js"></script>

    <script type="text/javascript" src="<%=contextpath%>js/ext3/examples/ux/ColumnNodeUI.js"></script>
    <script type="text/javascript" src="<%=contextpath%>js/ext/columntree/column-tree.js"></script>
	
	
</script>
	</HEAD>
	<BODY>
		<!-- onload="createMettingLbWindow('')">-->
		<div id="mettingdetail"></div>
		<input type="text" name="mettinguuid" id="mettinguuid" value='<%=request.getParameter("mettinguuid")%>'/>
		
	</BODY>
</HTML>