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
    
    <title>��Ա�Ͳ���ͬ��</title>
    
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
    ͬ��ǰԭϵͳ����<%= so.getRiseLi().size() %> �� <br>
    	�᰸����ϵͳ����<%= so.getJdsLi().size() %> ��<br>
    	<br>
     ͬ��ǰԭϵͳ���յ�λ<%= sp.getRiseLi().size() %> ��<br>
    	�᰸������յ�λ<%= sp.getJdsLi().size() %> ��<br>	
    	<br>
    	
    	ͬ�����
  </body>
</html>
