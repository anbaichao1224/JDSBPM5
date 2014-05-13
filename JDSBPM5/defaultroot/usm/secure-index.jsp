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
			src="<%=contextpath%>usm/resources/examples.js"></script>
		<script type="text/javascript"
			src="<%=contextpath%>usm/js/secure-index.js" defer="defer"></script>

	</head>
	<body>
		<div id="header">
			<div style="float: left; margin: 5px;" class="x-small-editor">
				<div id="container">
					<div id="toolbar">
						<div id="c"
							style="text-align: left; margin: 0px; float: left; display: inline; left: 0; top: 0;">

							<img src="<%=contextpath%>usm/images/index2.png" alt="应用资源管理"
								width=260 height=69 />
							<a href="/usmlogin.jsp"> <img
									src="<%=contextpath%>usm/images/09.png" alt="重新登入"
									onmouseover="this.style.cursor='pointer'"
									onmouseout="this.style.cursor='normal'" border="0" />
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
