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
	var rollid = '<%=request.getParameter("rollid")%>';
	var cid = '<%=request.getParameter("cid")%>';
	if(rollid=='null'){
		
	}
</script>
		
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
    <script type="text/javascript" src="<%=contextpath%>js/ext3/examples/ux/CheckColumn.js"></script>
    <script type="text/javascript" src="<%=contextpath%>desktop/widgets/electronicfile/datalist/js/column-tree.js"></script>
	
	</HEAD>
	<BODY>
		<div id="rolllist"></div>
	</BODY>
</HTML>