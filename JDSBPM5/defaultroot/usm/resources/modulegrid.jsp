<%@ include file="/usm/common/taglibs.jsp"%>
<%
String contextpath = request.getContextPath() + "/";
String syid = request.getParameter("sysid");
String aid = request.getParameter("appcatalogid");
%>
<html>
<head>
	<script type="text/javascript">
	 var sysid="<%=syid%>";
	// var sysid="${param.sysid}";
	 var appcatalogid="<%=aid%>";//"${param.appcatalogid}";
	</script>
    <title>Grid</title>
     <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
     <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
     <script src='<%=contextpath%>js/ext/ext-all.js'></script>
    <script type="text/javascript" src="<%=contextpath%>usm/js/modulegrid.js" defer="defer"></script>
    <script type="text/javascript">
        Ext.BLANK_IMAGE_URL = 'img/s.gif';
    </script>
    <script type="text/javascript">
    	setTimeout("shan()",100);
    	
    	function shan(){
    		
    	}
    </script>
    <style type="text/css">
    .search {
        background-image: url(usm/images/plugin.gif) !important;
     }
     .remove {
        background-image: url(usm/images/delete.gif) !important;
     }
     .edit {
        background-image: url(usm/images/edit.gif) !important;
     }
     .add {
        background-image: url(usm/images/add.gif) !important;
     }
     .preview {
        background-image: url(usm/images/preview.png) !important;
     }
    </style>
</head>
<body>
    <div id="topic-grid"></div>  
   <div id="topic-win" class="x-hidden"></div>
</body>
</html>
