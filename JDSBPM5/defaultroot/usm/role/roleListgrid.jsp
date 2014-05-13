<%@ page language="java" errorPage="/error.jsp" pageEncoding="utf8" %>
<html>
<head>
<%
String contextpath = request.getContextPath() + "/";
%>

    <link rel="stylesheet" type="text/css" href="../resources/css/ext-all.css" />

    <!-- GC -->
 	<!-- LIBS -->
 	<script type="text/javascript" src="../js/ext-base.js"></script>
 	<!-- ENDLIBS -->
    <script type="text/javascript" src="../js/ext-all.js"></script>
    <script type="text/javascript" src="<%=contextpath%>usm/role/js/RowExpander.js"></script>
    <script type="text/javascript" src="<%=contextpath%>usm/role/js/rolegrid.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=contextpath %>usm/css/grid-examples.css" />
    <style type="text/css">
        body .x-panel {
            margin-bottom:20px;
        }
        .icon-grid {
            background-image:url(<%=contextpath%>usm/img/grid.png) !important;
        }
        #button-grid .x-panel-body {
            border:1px solid #99bbe8;
            border-top:0 none;
        }
        .add {
            background-image:url(<%=contextpath %>usm/img/add.gif) !important;
        }
        .option {
            background-image:url(<%=contextpath %>usm/img/plugin.gif) !important;
        }
        .remove {
            background-image:url(../img/delete.gif) !important;
        }
        .save {
            background-image:url(../img/save.gif) !important;
        }
    </style>
</head>
<body>
<script type="text/javascript" src="<%=contextpath%>usm/resources/examples.js"></script><!-- EXAMPLES -->

</body>
</html>
