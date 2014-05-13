<%@ page language="java" errorPage="/error.jsp" pageEncoding="utf8" %>
<%
String contextpath = request.getContextPath() + "/";
%>
<html>
<head>

	<SCRIPT LANGUAGE="JavaScript" type="text/javascript">
		var context='<%=contextpath%>';
	</SCRIPT>
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src='<%=contextpath%>js/ext/ext-all.js'></script>
    <script type="text/javascript" src="js/departquerygrid.js" defer="defer"></script>
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>usm/css/grid-examples.css" />
    <style type="text/css">
        body .x-panel {
            
        }
        .icon-grid {
            background-image:url(<%=contextpath%>usm/img/grid.png) !important;
        }
        #button-grid .x-panel-body {
            border:1px solid #99bbe8;
            border-top:0 none;
        }
        .add {
            background-image:url(<%=contextpath%>usm/img/add.gif) !important;
        }
        .option {
            background-image:url(<%=contextpath%>usm/img/plugin.gif) !important;
        }
        .remove {
            background-image:url(<%=contextpath%>usm/img/delete.gif) !important;
        }
        .save {
            background-image:url(<%=contextpath%>usm/img/save.gif) !important;
        }
    </style>
</head>
<body>
<script type="text/javascript" src="<%=contextpath%>usm/resources/examples.js"></script><!-- EXAMPLES -->
</body>
</html>
