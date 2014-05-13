<%@ page contentType="text/html;charset=gb2312" language="java"%>
<%@include file="../../shared/inc/App_Variable.jsp"%>

<html>
<head>
<title>TreeView</title>
<META NAME="Robots" CONTENT="noindex">

<!-- no cache headers -->
<META content=no-cache http-equiv=Pragma>
<META http-equiv=no-cache>
<META content=-1 http-equiv=Expires>
<META content=no-cache http-equiv=Cache-Control>
<!-- end no cache headers -->

<link rel="stylesheet" type="text/css" href="<%=SYS_BASE_PATH%>treeview/shared/css/ie.css">
<link rel="stylesheet" type="text/css" href="<%=SYS_BASE_PATH%>treeview/shared/css/treeview.jsp">
</head>

<script>

  function selectstart()
    {
		window.event.cancelBubble = true;
		window.event.returnValue = false;
		return false;
    }

</script>

<body onselectstart="selectstart();" topmargin="0" leftmargin="0" marginheight="0" marginwidth="0" bgcolor="#F1F1F1" text="#000000">
<nobr>
<div id="deeptree" class="deeptree" CfgXMLSrc="<%=SYS_BASE_PATH%>treeview/zh-cn/config.jsp">Loading...</div>
</nobr>
</body>
</html>
