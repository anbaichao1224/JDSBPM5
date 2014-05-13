<?xml version="1.0" encoding="GB2312"?>
<%@ page contentType="text/html;charset=gb2312" language="java"%>
<%@include file="../shared/inc/App_Variable.jsp"%>
<deeptreeconfig sLeftFrameHeight="84">
  <TopXMLSrc><%=SYS_BASE_PATH%>treeview/zh-cn/loadfolder.jsp?root=1</TopXMLSrc>
  <TreeLabel>Welcome to the MSDN Library</TreeLabel>
  <StartPage><%=SYS_BASE_PATH%>treeview/zh-cn/loadfile.jsp</StartPage>
  <ErrorPage><%=SYS_BASE_PATH%>error.htm</ErrorPage>
  <ContentTarget>fraContent</ContentTarget>
  <TreeId>treeviewlib</TreeId>
  <TreeRootPath><%=SYS_BASE_PATH%>treeview/zh-cn/loadfile.jsp</TreeRootPath>
  <Locale>zh-cn</Locale>
  <LocaleTextDirection>LTR</LocaleTextDirection>
</deeptreeconfig>