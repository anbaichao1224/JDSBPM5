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
<style type="text/css">
		<!--
body {font-family: "宋体", "Arial"; font-size: 9pt;background-color:#dfe8f6}
td {font-family: "宋体", "Arial"; font-size: 9pt} 
-->
		</style>
<script type="text/javascript">
	var context="/";
</script>
 
<script type="text/javascript" src="/js/extAll.js"></script>
<script type="text/javascript" src="<%=contextpath %>desktop/js/CreateGrid.js"></script>
<script type="text/javascript" src="<%=contextpath %>desktop/widgets/electronicfile/recordcategory/js/categorytree.js" defer="defer"></script>
<script  src="/js/JDS.js" type="text/javascript"></script>
<script type="text/javascript">
</script>
</HEAD>
<BODY>
	<div id="treediv"></div>
</BODY>
 
</HTML>