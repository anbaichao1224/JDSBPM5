<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://www.opensymphony.com/oscache" prefix="cache" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
String contextpath = request.getContextPath() + "/";
%>
<html>
<head>
<script type="text/javascript">
  var orgid ='<s:property value="depart.orgid"/>';
  var parentorgid='<s:property value="depart.parentorgid"/>';
  var cnname='<s:property value="depart.cnname"/>';
  var enname='<s:property value="depart.enname"/>';
  var memo='<s:property value="depart.memo"/>';
  if(memo==null)
  {
	 memo="";
  }
  var orglevel='<s:property value="depart.orglevel"/>';
  if(orglevel==null)
  {
	 orglevel="";
  }
  var status='<s:property value="depart.status"/>';
  if(status=='0')
  {
	 status='正常';
  }			 		
  if(status=='1')
  {
	 status='禁用';
  }
  if(status=='2')
  {
     status='删除';
  } 
		
  var orgtype='<s:property value="depart.orgtype"/>';
  if(orgtype=='0')
  {
	  orgtype='普通部门';
  }
		
  if(orgtype=='1')
  {
	  orgtype='实体部门';
  }
  var intel='<s:property value="depart.intel"/>';
  if(intel==null)
  {
	 intel='';
  }
  var outtel='<s:property value="depart.outtel"/>';
   if(outtel==null)
  {
	 outtel='';
  }
  var nighttel='<s:property value="depart.nighttel"/>';
  if(nighttel==null)
  {
	 nighttel='';
  }
  var fax='<s:property value="depart.fax"/>';
  if(fax==null)
  {
	 fax='';
  }
  var serialindex='<s:property value="depart.serialindex"/>';
  if(serialindex==null)
  {
	 serialindex='';
  }
</script>

    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src='<%=contextpath%>js/ext/ext-all.js'></script>
    <script type="text/javascript" src="<%=contextpath%>usm/js/states.js"></script>
    <script type="text/javascript" src="<%=contextpath%>usm/depart/js/departmodify.js"  defer="defer"></script>

    
</head>
<body>
<script type="text/javascript" src="<%=contextpath%>usm/resources/examples.js"></script><!-- EXAMPLES -->

</body>
</html>
