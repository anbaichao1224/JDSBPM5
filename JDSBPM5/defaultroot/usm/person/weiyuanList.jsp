<%@ include file="/usm/common/taglibs.jsp" %>

<%
String contextpath = request.getContextPath() + "/";
String poid = request.getParameter("parentorgid");
String oid = request.getParameter("orgid");
%>
 
<html>
<head>
 <script type="text/javascript">
   	       var parentorgid="<%=poid%>";
   	       var orgid="<%=oid%>";	      
</script>


    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src='<%=contextpath%>js/ext/ext-all.js'></script>
    <script type="text/javascript" src="<%=contextpath%>usm/person/js/RowExpander.js"></script>
    <script type="text/javascript" src="js/weiyuanList.js" defer="defer"></script>
    <script type="text/javascript" src="/usm/js/FileUploadField.js" ></script>
    <link rel="stylesheet" type="text/css" href="/usm/resources/css/file-upload.css"/> 
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>usm/css/grid-examples.css" />
    
    <style type="text/css">
        body .x-panel {
           
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
<script>

</script>
<body>


</body>
</html>
