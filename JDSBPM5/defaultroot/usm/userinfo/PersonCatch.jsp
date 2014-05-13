<%@ page language="java"  pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'PersonCatch.jsp' starting page</title>
    
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
    <form action="personal.action" method="post">
    	<input type="text" name="personid" id="personid"></input>
    	<input type="submit" value="查看委员"></input>
    </form>
    <form action="passwordshow.action" method="post">
    	<input type="text" name="personid" id="personid"></input>
    	<input type="submit" value="修改密码"></input>
    </form>
    <form action="chiefM.action" method="post">
    	<input type="text" name="personId" id="personId"></input>
    	<input type="submit" value="区长管理"></input>
    </form>
    <form action="statC.action" method="post">
    	<input type="text" name="personId" id="personId"></input>
    	<input type="hidden" name="bound" id="bound" value="12"></input>
    	<input type="hidden" name="order" id="order" value="1"></input>
    	<input type="submit" value="区长查看"></input>
    </form>
    <form action="querybounder.action" method="post">
    	<input type="submit" value="生成"></input>
    </form>
    <form action="allchief.action" method="post">
    	<input type="submit" value="全部区长"></input>
    </form>
  </body>
</html>
