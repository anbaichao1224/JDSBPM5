<%@ page language="java" errorPage="/error.jsp" pageEncoding="utf8" %>
<%
String contextpath = request.getContextPath() + "/";
String porgid = request.getParameter("parentorgid");
String orglev = request.getParameter("orglevel");
%>
<html>

<head>
	<SCRIPT LANGUAGE="JavaScript" type="text/javascript">
		var context='<%=contextpath%>';
	</SCRIPT>
	<script type="text/javascript">
   	       var orglevel="<%=orglev%>"; //"${param.orglevel}";
   	       var parentorgid='<%=porgid%>'; 
   	       //alert("parentorgid is "+parentorgid);//"${param.parentorgid}"; 
	</script> 

    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src='<%=contextpath%>js/ext/ext-all.js'></script>
<!--     <script type="text/javascript" src="js/RowExpander.js"></script>
-->    <script type="text/javascript" src="<%=contextpath%>usm/depart/js/childrendepartListgrid.js" defer="defer"></script>
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>usm/css/grid-examples.css" />
    <style type="text/css">
        .icon-grid {
            background-image:url(../img/grid.png) !important;
        }
        #button-grid .x-panel-body {
            border:1px solid #99bbe8;
            border-top:0 none;
        }
        .add {
            background-image:url(../img/add.gif) !important;
        }
        .option {
            background-image:url(../img/plugin.gif) !important;
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
