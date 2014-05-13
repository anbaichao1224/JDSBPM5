
<%@ include file="/usm/common/taglibs.jsp"%>
<%
String contextpath = request.getContextPath() + "/";
%>
<html>


<head>
<script type="text/javascript">
	var levelid='<s:property value="level.levelid"/>';
	var cnname='<s:property value="level.cnname"/>';
	var leveldesc='<s:property value="level.leveldesc"/>';
</script>

    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src='<%=contextpath%>js/ext/ext-all.js'></script>
  <script type="text/javascript" src="<%=contextpath%>usm/dutylevel/js/personDutyLevelquerygrid.js" defer="defer"></script>
       
    <link rel="stylesheet" type="text/css" href="../css/grid-examples.css" />
    <style type="text/css">
        body .x-panel {
            margin-bottom:20px;
        }
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
