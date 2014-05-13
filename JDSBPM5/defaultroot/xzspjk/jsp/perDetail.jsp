<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="ss" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String contextpath = request.getContextPath() + "/";
	String permissionid = (String)request.getParameter("permissionid");
	//System.out.println(permissionid+"页面jsp中打印");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>xzsplist</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src='<%=contextpath%>js/ext/ext-all.js'></script>
    <script text="text/javascript">
	 	permissionid = <%=permissionid %>;
    </script>
    <script src="/xzspjk/js/perDetail.js"></script>
  </head>
  
  <body>
    <div id="permissionDetail" ></div>
    
    <div id="handlenodeDetail" ></div> 
       
    <div id="applynodeDetail" ></div>
    
    <div id="acceptnodeDetail" ></div>
    
    <div id="notifynodeDetail" ></div>
    
    <div id="nodeDetail" ></div>
    
    <div id="materialDetail" ></div>
  </body>
</html>
