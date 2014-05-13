
<jsp:directive.page import="net.itjds.usm.persistence.model.Duty"/>
<%@ include file="/usm/common/taglibs.jsp"%>
<%
String contextpath = request.getContextPath() + "/";
String sysid = request.getParameter("sysid");

%>
<html>
<head>

<script type="text/javascript">
   	       var sysid="<%=sysid%>";

   	       var dutyid='<s:property value="duty.dutyid"/>';
   	       var cnname='<s:property value="duty.cnname"/>';
   	       var adminflag='<s:property value="duty.adminflag"/>';
   	       var createtime='<s:property value="duty.createtime"/>';
   	       var dutydesc='<s:property value="duty.dutydesc"/>';
   	       var sysid='<s:property value="duty.sysid"/>';
   	       
   	</script> 

    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src='<%=contextpath%>js/ext/ext-all.js'></script>
    <script type="text/javascript" src="<%=contextpath%>usm/js/states.js"></script>
    <script type="text/javascript" src="<%=contextpath%>usm/duty/js/dutymodify.js" defer="defer"></script>
  
</head>
<body>

</body>
</html>
