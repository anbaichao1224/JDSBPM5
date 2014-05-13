<%@ page contentType="text/html; charset=UTF-8"%>
<jsp:directive.page import="net.itjds.userclient.usm.ws.IndexManager"/>
<jsp:directive.page import="net.itjds.userclient.usm.ws.server.ServerBean"/>
<%
String contextpath = request.getContextPath() + "/";
String nodeid=request.getParameter("nodeid");
String action=request.getParameter("action");
IndexManager obj=new IndexManager();
ServerBean bean=new ServerBean();
bean=obj.serverbeanlistById(nodeid);
if(action!=null){
bean.setStatus(action);
}
%>
<html>
<title>Forms</title>  
<head>
<script type="text/javascript">
var nodeid='<%=nodeid%>';
var name='<%=bean.getName()%>';
var type='<%=bean.getType()%>';
var maxconnection='<%=bean.getMaxconnection()%>';
var minconnection='<%=bean.getMinconnection()%>';
var timeout='<%=bean.getTimeout()%>';
var url='<%=bean.getUrl()%>';
var status='<%=bean.getStatus()%>';
//var userexpression='<%=bean.getUserexpression()%>';
var userexpression='true';
var desc='<%=bean.getDesc()%>';
</script>
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src="<%=contextpath%>js/ext/ext-all.js"></script>
    <script type="text/javascript" src="computerInfo.js"></script>
</head>
<body>
</body>
</html>
