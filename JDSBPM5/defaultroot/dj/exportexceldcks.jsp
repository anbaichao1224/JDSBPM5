<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.kzxd.rcap.action.CommonExportExcel" %>

<%
response.reset();
response.setContentType("application/vnd.ms-excel");
CommonExportExcel comm = new CommonExportExcel();
comm.export(response.getOutputStream(),"dj-zyl");
%>