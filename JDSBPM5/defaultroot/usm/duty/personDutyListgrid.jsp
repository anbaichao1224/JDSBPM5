
<%@ include file="/usm/common/taglibs.jsp"%>
<jsp:directive.page import="net.itjds.usm.persistence.model.Duty"/>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="utf8" %>
<%
String contextpath = request.getContextPath() + "/";
%>
<html>


<head>

<script type="text/javascript">
   	       var dutyid='<s:property value="duty.dutyid"/>';
   	       var cnname='<s:property value="duty.cnname"/>';
   	       var dutydesc='<s:property value="duty.dutydesc"/>';       
</script> 


    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src='<%=contextpath%>js/ext/ext-all.js'></script>
  <script type="text/javascript" src="<%=contextpath%>usm/duty/js/personDutyquerygrid.js"  defer="defer"></script>
   
    
    
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
