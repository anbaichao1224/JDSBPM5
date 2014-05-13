<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ page import="cwzx.action.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
SynOrgs so = new SynOrgs();
		so.SynOrg();
		
		SynPersons sp = new SynPersons(); 
		sp.SynPerson();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>人员和部门同步</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    同步前原系统部门<%= so.getRiseLi().size() %> 个 <br>
    	提案管理系统部门<%= so.getJdsLi().size() %> 个<br>
    	<br>
     同步前原系统接收单位<%= sp.getRiseLi().size() %> 个<br>
    	提案管理接收单位<%= sp.getJdsLi().size() %> 个<br>	
    	<br>
    	
    	同步完成
  </body>
</html>
