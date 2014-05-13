
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts-menu.sf.net/tag-el" prefix="menu" %>
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
 var personid='<s:property value="personinfo.personid"/>';
 var cnname='<s:property value="personinfo.cnname"/>';
 var enname='<s:property value="personinfo.enname"/>';
 var password='<s:property value="personaccount.password"/>';
 var househao='<s:property value="personinfo.househao"/>';
 if(househao==null)
 {
	househao='';		
 }
 var accountttl='<s:property value="personaccount.accountttl"/>';
 if(accountttl==null)
 {
	accountttl='';		
 }
 var accountstat='<s:property value="personaccount.accountstat"/>';
	  
 if(accountstat=='0')
 {
	accountstat = '临时账号';
 }
 if(accountstat=='1')
 {
	accountstat = '普通账号(连续120天不登陆，即失效（被禁止）)';
 }
 if(accountstat=='2')
 { 
	accountstat = '永久';
 }

 var passquestion='<s:property value="personaccount.passquestion"/>';
 if(passquestion==null)
 {
	 passquestion='';
 }	  
 
 var passanswer='<s:property value="personaccount.passanswer"/>';
 if(passanswer==null)
 {
	      passanswer = '';
 }
 var sex='<s:property value="personinfo.sex"/>';
 if(sex=='0')
 {
	sex = '保密';
 }
 if(sex=='1')
 {
	sex = '男';
 }
 if(sex=='2')
 {
	sex = '女';
 }
 var photo='<s:property value="personinfo.photo"/>';
 if(photo==null)
 {
	photo = '';
 }
 var birthday='<s:property value="personinfo.birthday"/>';
 if(birthday==null)
 {
	birthday = '';
 }
 var job='<s:property value="personinfo.job"/>';
 if(job==null)
 {
	job = '';
 }
 var duty='<s:property value="personinfo.duty"/>';
 if(duty==null)
 {
	duty = '';
 }
 var marry='<s:property value="personinfo.marry"/>';
 if(marry=='0')
 {
   marry = '保密';
 }
 if(marry=='1')
 {
   marry = '已婚';
 }
 if(marry=='2')
 {
	marry = '未婚';
 }
 if(marry=='3')
 {
	marry = '离异';
 }
 var politicalstat='<s:property value="personinfo.politicalstat"/>';
  if(politicalstat=='0')
  {
		 politicalstat = '群众';
  }
  if(politicalstat=='1')
  {
		 politicalstat = '团员';
  }
  if(politicalstat=='2')
  {
		 politicalstat = '党员';
  }
  if(politicalstat=='3')
  {
		 politicalstat = '其它';
  }
  var hometel='<s:property value="personinfo.hometel"/>';
  if(hometel==null)
  {
 		hometel = '';
  }
  var homefax='<s:property value="personinfo.homefax"/>';
  if(homefax==null)
  {
 		homefax = '';
  }
  var officetel='<s:property value="personinfo.officetel"/>';
  if(officetel==null)
  {
 		officetel ='';
  }
  var mobile='<s:property value="personinfo.mobile"/>';
  if(mobile==null)
  {
         mobile = '';
  }
  var email1='<s:property value="personinfo.email1"/>'
  if(email1==null)
  {
 		email1 = '';
  }
  var nativeplace='<s:property value="personinfo.nativeplace"/>'
  if(nativeplace == null)
  {
 		nativeplace = '';
  }
  var nation='<s:property value="personinfo.nation"/>'
  if(nation==null)
  {
 		nation = '';	
  }
  var country='<s:property value="personinfo.country"/>'
  if(country == null)
  {
 		country = '';
  }
  
  var city='<s:property value="personinfo.city"/>'
  if(city==null)
  {
 		city='';
  }
  
  var zip='<s:property value="personinfo.zip"/>'
  if(zip == null)
  {
 		zip='';
  }
	  
  var lastedulevel='<s:property value="personinfo.lastedulevel"/>'
  if(lastedulevel==null)
   {
   		lastedulevel = '';
   }
  else 
  {
	  if(lastedulevel=='0')
	  {
		    lastedulevel = '无';
	  }
	  if(lastedulevel=='1')
	  {
		    lastedulevel = '小学';
	  }
	  if(lastedulevel=='2')
	  {
			lastedulevel = '初中';
	  }
	  if(lastedulevel=='3')
	  { 
			lastedulevel = '高中(中专、技校)';
	  }
	  if(lastedulevel=='4')
	  {
			lastedulevel = '大专';
	  }
	  if(lastedulevel=='5')
	  {
			lastedulevel = '本科';
	  }
	  if(lastedulevel=='6')
	  {
			lastedulevel = '研究生';
	  }
	  if(lastedulevel=='7')
	  {
			lastedulevel = '博士';
	  }
}
	 var lastdegree='<s:property value="personinfo.lastdegree"/>'
	if(lastdegree==null)
	{
	     lastdegree = '';
	}else
	{ 
		if(lastdegree=='0')
		{
			lastdegree = '无学位';
		}
			if(lastdegree=='1')
		{
			lastdegree = '学士';
		}
		if(lastdegree=='2')
		{
			lastdegree = '硕士';
		}
		if(lastdegree=='3')
		{
			lastdegree = '博士(后)';
		}
     }
     
     var homeadd='<s:property value="personinfo.homeadd"/>'
	  if(homeadd==null)
	  {
	 		homeadd = '';
	  }
	  var connectaddr='<s:property value="personinfo.connectaddr"/>'
	  if(connectaddr==null)
	  {
	  		connectaddr='';
	  }
	  var officeadd='<s:property value="personinfo.officeadd"/>'
	  if(officeadd==null)
	  {
	  		officeadd='';
	  }
	  var school='<s:property value="personinfo.school"/>'
	  if(school==null)
	  {
	 		school = '';
	  }
	 
	  var lastloginip='<s:property value="personaccount.lastloginip"/>';
	  if(lastloginip==null)
	 {
	 		lastloginip='';
	 }
	 var officefax='<s:property value="personinfo.officefax"/>';
	  if(officefax==null)
	 {
	 		officefax='';
	 }
	 
	  var icon='<s:property value="personinfo.photo"/>';
	 
	
 
</script>
  

   
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src='<%=contextpath%>js/ext/ext-all.js'></script>
    <script type="text/javascript" src="/usm/js/FileUploadField.js"></script>
    <link rel="stylesheet" type="text/css" href="/usm/resources/css/file-upload.css"/> 
    <script type="text/javascript" src="/usm/js/FileUploadField.js" ></script>
    <script type="text/javascript" src="<%=contextpath%>usm/person/js/gerenxinxi.js" defer="defer"> </script>
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>usm/css/grid-examples.css" />
  
</head>


<body>

</body>
</html>