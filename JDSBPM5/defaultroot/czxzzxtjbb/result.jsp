<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    
    <title>添加成功返回页面</title>

  </head>
  
  <body>
	添加成功!<a href="finAllByBuMen.action">返回添加页面</a>
  </body>
</html>
