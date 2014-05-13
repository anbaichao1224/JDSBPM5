
<%@ include file="/usm/common/taglibs.jsp"%>
<%
String contextpath = request.getContextPath() + "/";
%>
<html>

<head>
<script type="text/javascript">
   	     
   	       var levelid='<s:property value="level.levelid"/>';
   	       var cnname='<s:property value="level.cnname"/>';
   	       var adminflag='<s:property value="level.adminflag"/>';
   	       var createtime='<s:property value="level.createtime"/>';
   	       var leveldesc='<s:property value="level.leveldesc"/>';
</script> 
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src='<%=contextpath%>js/ext/ext-all.js'></script>
    <script type="text/javascript" src="<%=contextpath%>usm/js/states.js"></script>
    <script type="text/javascript" src="<%=contextpath%>usm/dutylevel/js/dutylevelmodify.js" defer="defer"></script>

    
</head>
<body>


</body>
</html>
