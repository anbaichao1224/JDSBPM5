<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
String contextpath = request.getContextPath() + "/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>JDS EXPLORER</title>

<script >
	var context='<%=contextpath%>';

</script>
	<script type="text/javascript" src="<%=contextpath%>js/extAll.js"></script>

	
</head>
<body scroll="no">
<script  src="<%=contextpath%>js/JDS.js" type="text/javascript"></script>
	<script type="text/javascript" src="<%=contextpath%>js/extAll.js"></script>
	    <script  src="<%=contextpath%>js/JDS.js" type="text/javascript"></script>
	<script src="expjs/menuAction.js"></script>		
<link rel='STYLESHEET' type='text/css' href='/desktop/resources/themes/xtheme-vista/css/xtheme-vista.css'>
<link rel='STYLESHEET' type='text/css' href='/desktop/resources/css/desktop.css'>

	<div id="north_area">
		<div class="menuEl" id="topmenu"></div>
		<div class="toolbarEl" id="topmenu"></div>
		<table id="query_makeup" style="width:100%; align:left;height:20px; border:0px; background:#ececec url(../images/default/toolbar/bg.gif) repeat-y top left;">
			<tr calss="iebar_tr"  style="height:20px;">
				<td class="left_buttonsEl" style="width:100px;align:left;height:20px;"></td>
				<td class="uribarEl"  style="width:500px;align:left;height:20px;" ></td>
				<td class="right_buttonsEl" align="left"></td>
			</tr>
		</table>
	</div>
	
	<div id="itemsArea"></div>
	<div id="loading_mask">&#160;</div>
</body>
</html>

