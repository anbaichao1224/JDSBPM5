<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
String contextpath = request.getContextPath() + "/";
%> 	
 
  <iframe name="quwei" id="quwei" scrolling="yes" frameborder="0" src="<%=contextpath %>bpm/userclient/samples.jsp"  height=550  width=100%></iframe>
 

