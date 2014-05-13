<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>


<%
String contextpath = request.getContextPath() + "/";
%>
<html>
<head> 
	<script type="text/javascript">
		var sysid="<s:property value='sysid'/>";
		var nodeid="<s:property value='nodeid'/>";
		var template="<s:property value='template'/>";
	</script>
    <title>ExtJS.com Forums</title>
     <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
     <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
     <script src='<%=contextpath%>js/ext/ext-all.js'></script>
    <link rel="stylesheet" type="text/css" href="usm/css/menus.css" />
    <script type="text/javascript" src="js/persontree.js" defer="defer"></script>
    <link rel="stylesheet" type="text/css" href="forum.css" />
</head>
	<body>
		<script type="text/javascript">
     var bool;
     var maxCols = 4;
 	 var strarr2=[
		<s:iterator id="li" value="personlistright" status="rowstatus">
			 {boxLabel: '<s:property value="#li.name"/>',name:'checkboxs',checked:true,inputValue: '<s:property value="#li.ID"/>'
	     	 }
			  <s:if test ="#rowstatus.count < personlistright.size">
		      , 
		      </s:if>
		</s:iterator>
 	];
    var cnt2 = '<s:property value="personlistright.size"/>';
 	var strarr=[
		<s:iterator id="li" value="personlistrightadd" status="rowstatus">
			 {boxLabel: '<s:property value="#li.name"/>',name:'txtCheckValue',checked:true,inputValue: '<s:property value="#li.ID"/>'}
			  <s:if test ="#rowstatus.count < personlistrightadd.size">
		      , 
		      </s:if>
		</s:iterator>
 	];
 	var cnt = '<s:property value="personlistrightadd.size"/>';
 	if (cnt>maxCols){
 	  cnt = maxCols;
 	}
 	if (cnt2>maxCols){
 	  cnt2 = maxCols;
 	}
 	if(strarr=="" && strarr2==""){
 	bool=true;
 	var str=[new Ext.form.Hidden({name:'sysid',value:'<s:property value="sysid"/>'}),new Ext.form.Hidden({name:'nodeid',value:'<s:property value="nodeid"/>'}),new Ext.form.Hidden({name:'template',value:'<s:property value="template"/>'})];
 	}else{
 	bool=false;
 	if(strarr==""){
 		var str=[{xtype: 'checkboxgroup',columns:cnt2,fieldLabel: '该模块人员权限列表',items:strarr2},new Ext.form.Hidden({name:'sysid',value:'<s:property value="sysid"/>'}),new Ext.form.Hidden({name:'nodeid',value:'<s:property value="nodeid"/>'}),new Ext.form.Hidden({name:'template',value:'<s:property value="template"/>'})];
 	}else if(strarr2==""){
 		var str=[{xtype: 'checkboxgroup',columns:cnt,fieldLabel: '添加后的人员列表',items:strarr},new Ext.form.Hidden({name:'sysid',value:'<s:property value="sysid"/>'}),new Ext.form.Hidden({name:'nodeid',value:'<s:property value="nodeid"/>'}),new Ext.form.Hidden({name:'template',value:'<s:property value="template"/>'})];
 	}else{
 		var str=[{xtype: 'checkboxgroup',columns:cnt2,fieldLabel: '该模块人员权限列表',items:strarr2},{xtype: 'checkboxgroup',columns:cnt,fieldLabel: '添加的人员列表',items:strarr},new Ext.form.Hidden({name:'sysid',value:'<s:property value="sysid"/>'}),new Ext.form.Hidden({name:'nodeid',value:'<s:property value="nodeid"/>'}),new Ext.form.Hidden({name:'template',value:'<s:property value="template"/>'})];
 	}
 	}
      
      
 
</script>

		<div id="form-ct"></div> 
		
	</body>
</html>
