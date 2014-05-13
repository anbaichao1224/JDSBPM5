
<%@ include file="/usm/common/taglibs.jsp"%>
<%
String contextpath = request.getContextPath() + "/";
%>
<html>
<head>
<script type="text/javascript">
	var parentorgid ='<s:property value="parentorgid" />';
	var orglevel='<s:property value="orglevel" />';
	var orgid='<s:property value="orgid" />';
	var cnname='<s:property value="cnname" />';
	var stringArray = cnname.split(" ");
	cnname="";
	for(var i=1;i<stringArray.length;i++)
		cnname+=String.fromCharCode(stringArray[i]);
</script>
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src="<%=contextpath%>js/ext/ext-all.js"></script> 
    <script type="text/javascript" src="<%=contextpath%>usm/depart/js/departdetailinfo.js" defer="defer"></script>  
</head>
<body>
</body>
</html>
