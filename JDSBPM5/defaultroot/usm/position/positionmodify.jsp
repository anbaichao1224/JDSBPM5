<%@ include file="/usm/common/taglibs.jsp"%>
<%
String contextpath = request.getContextPath() + "/";
String sysid = request.getParameter("sysid");

%>
<html>
<head>

<script type="text/javascript">
   	       var sysid="<%=sysid%>";
   	       
    if(sysid=="null")
    {
    		sysid = '';
    }
   	var type="${param.type}";
	var positionid='<s:property value="position.positionid"/>';
	var positionname='<s:property value="position.positionname"/>';
	var positiondesc='<s:property value="position.positiondesc"/>';
	
</script>

    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src='<%=contextpath%>js/ext/ext-all.js'></script>
    <script type="text/javascript" src="<%=contextpath%>usm/js/states.js"></script>
    <script type="text/javascript" src="<%=contextpath%>usm/position/js/positionmodify.js"  defer="defer"></script>
    <script type="text/javascript" src="<%= contextpath%>usm/resources/examples.js"></script>
    

</head>

</html>
