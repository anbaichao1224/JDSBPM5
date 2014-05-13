<%@ include file="/usm/common/taglibs.jsp"%>

<%
String contextpath = request.getContextPath() + "/";
%>
<html>
<head>
<script type="text/javascript">
	var sysid='<s:property value="sysid"/>';
   	var uuid='<s:property value="position.uuid"/>';
	var positionid='<s:property value="position.positionid"/>';
	var positionname='<s:property value="position.positionname"/>';
	var positiondesc='<s:property value="position.positiondesc"/>';
</script>
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src='<%=contextpath%>js/ext/ext-all.js'></script>
    <script type="text/javascript" src="<%=contextpath%>usm/position/js/personpositionquerygrid.js"  defer="defer"></script>
       
    
    <link rel="stylesheet" type="text/css" href="../css/grid-examples.css" />
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

</body>
</html>
