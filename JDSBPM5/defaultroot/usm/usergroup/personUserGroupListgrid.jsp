<%@ include file="/usm/common/taglibs.jsp"%>
<jsp:directive.page import="net.itjds.usm.persistence.model.Usergroup"/>
<%
String contextpath = request.getContextPath() + "/";
%>
<html>

<%
		Usergroup usergroup=(Usergroup)request.getAttribute("usergroup");
	    out.println("<script type='text/javascript'>");
			out.println("var grpid='"+usergroup.getGrpid().trim()+"'");
			out.println("var grpname='"+usergroup.getGrpname()+"'");
			out.println("var grpdesc='"+usergroup.getGrpdesc()+"'");
		out.println("</script>");
		out.flush();
%>
<head>
<script type="text/javascript">
	       var grpid='<s:property value="usergroup.grpid"/>';
	       var grpname='<s:property value="usergroup.grpname"/>';
	       var grpdesc='<s:property value="usergroup.grpdesc"/>';
</script>

    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src='<%=contextpath%>js/ext/ext-all.js'></script>
    <script type="text/javascript" src="<%=contextpath%>usm/usergroup/js/RowExpander.js"></script>
    <script type="text/javascript" src="<%=contextpath%>usm/usergroup/js/personUserGroupquerygrid.js" defer="defer"></script>
    <script type="text/javascript" src="<%=contextpath%>usm/resources/examples.js"></script>
    
    
    <link rel="stylesheet" type="text/css" href="../css/grid-examples.css" />
    <style type="text/css">
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

</body>
</html>
