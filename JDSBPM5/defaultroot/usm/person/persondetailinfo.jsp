<%@ include file="/usm/common/taglibs.jsp"%>

<%
String contextpath = request.getContextPath() + "/";
%>
<html>
<head>
 
 <script language="JavaScript">
   	       
	       var personid='<s:property value="personinfo.personid"/>';
	       var userid='<s:property value="personaccount.userid"/>';
	       var cnname='<s:property value="personinfo.cnname"/>';
	       var enname='<s:property value="personinfo.enname"/>';
	       var officetel='<s:property value="personinfo.officetel"/>';
	       var mobile='<s:property value="personinfo.mobile"/>';
	       var lastloginip='<s:property value="personaccount.lastloginip"/>';
	         
</script>

   
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src='<%=contextpath%>js/ext/ext-all.js'></script>
    <script type="text/javascript" src="<%=contextpath%>usm/person/js/persondetailinfo.js" defer="defer"></script>
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>usm/css/grid-examples.css" />
    
</head>
<body>

</body>
 
</html>
