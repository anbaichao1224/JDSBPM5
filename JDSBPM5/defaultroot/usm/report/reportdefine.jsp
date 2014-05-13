<%@ page import="net.itjds.j2ee.util.UUID" %>
<%@ page import="java.io.*" %>
<%@ include file="/usm/common/taglibs.jsp" %>
<%
String contextpath = request.getContextPath() + "/";
String realpath = request.getRealPath(contextpath).replaceAll("\\\\","/");

String uuid = request.getParameter("uuid");
String isOpenTemp = request.getParameter("isOpenTemp");

if(isOpenTemp==null || "".equals(isOpenTemp)||"null".equals(isOpenTemp)){
	isOpenTemp= "false";
}

if(uuid==null || "".equals(uuid)||"null".equals(uuid)){
	uuid= new UUID().toString();
}
//out.println(uuid);
String sep = "/";

String tempPath = realpath+"report"+sep+"_report"+sep+"_templates"
					+sep+uuid+".jsp";

String runtimePath = realpath+"report"+sep+"_report"+sep+"_runtime"
					+sep+uuid+".jsp";

String tempDir = realpath+"report"+sep+"_report"+sep+"_templates";

String urlContext=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextpath;
//out.println(urlContext);
//String reportUrl = urlContext+"report"+sep+"_report"+sep+"_runtime"+sep+uuid+".jsp";
	String reportUrl = urlContext+"report/display/display.jsp?reportUuid="+uuid;
//out.println(reportUrl);
%>
<html>
<head>
<script>
	
   var uuid ='<%=uuid%>';
   <%
   	if("false".equalsIgnoreCase(isOpenTemp)){
   %>
   		var memo = "";
   		var cnname = "";
   <%	
   	}else{
   %>
   		var memo = window.opener.document.getElementById("memo").value;
   		var cnname = window.opener.document.getElementById("cnname").value;
   	<%}%>
   
   
   var tempPath = '<%=tempPath%>';
   var runtimePath = '<%=runtimePath%>';
   var tempDir = '<%=tempDir%>';
   var isOpenTemp = '<%=isOpenTemp%>';
   var reportUrl = '<%=reportUrl%>';
   
</script>
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src="<%=contextpath%>js/ext/ext-all.js"></script>
    <script type="text/javascript" src="<%=contextpath%>usm/js/reportdefine.js" ></script>
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>usm/css/grid-examples.css" />
</head>
<body>
</body>
</html>
