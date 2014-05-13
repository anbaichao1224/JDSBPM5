<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://www.opensymphony.com/oscache" prefix="cache"%>
<!--     
  content:登陆后首页    
-->

<%
	String contextpath = request.getContextPath() + "/";
%>
<html>
	<head>
		<SCRIPT LANGUAGE="JavaScript" type="text/javascript">
		var context='<%=contextpath%>';
	    var username = '<s:property value="username"/>';
	</SCRIPT>
		<title>USM管理中心</title>
		<link rel="stylesheet" type="text/css"
			href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=contextpath%>usm/css/menus.css" />
		<script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
		<script src='<%=contextpath%>js/ext/ext-all.js'></script>
		<script type="text/javascript"
			src="<%=contextpath%>usm/js/model/roleyb.js"></script>
	</head>
	<body>
	</body>
</html>
