<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://www.opensymphony.com/oscache" prefix="cache" %>

<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String contextpath = request.getContextPath() + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>子系统管理</title>
   	<script type="text/javascript">
   	var sysid='<s:property value="sysid"/>';//子系统id
   	
   	function create(){
 		
 		
 		var toolbars={text:'角色管理',iconCls:'role',menu:[
 		<s:if test="perposition">{id:'position',text: '岗位管理',iconCls:'position',handler:onItemClick},</s:if>
 		<s:if test="perlevel">{id:'dutylevel',text: '职级管理',handler:onItemClick},</s:if>
 		<s:if test="perduty">{id:'duty',text: '职务管理',iconCls:'duty',handler:onItemClick},</s:if>
 		<s:if test="perrole">{id:'role',text: '角色管理',iconCls:'role',handler:onItemClick},</s:if>
 		<s:if test="perusergroup">{id:'usergroup',text: '用户组管理',iconCls:'usergroup',handler:onItemClick}</s:if>
 		<s:else>"-"</s:else>
 		]};
 		var application={id:'application',iconCls: 'application',text: '应用管理',handler:onItemClick};
   		var obj={text:'权限管理',iconCls: 'quanxian',menu: new Ext.menu.Menu({id: 'mainMenu3',items: [{id:'moduleright',iconCls:'module',text: '模块授权',handler:onItemClick},{id:'personright',text: '人员授权',iconCls:'persons',handler:onItemClick},{text: '角色授权',iconCls:'duty',menu:[
   		<s:if test="perposition">{id:'positionright',text: '岗位',iconCls:'position',handler:onItemClick},</s:if>
 		<s:if test="perlevel">{id:'levelright',text: '职级',handler:onItemClick},</s:if>
 		<s:if test="perduty">{id:'dutyright',text: '职务',iconCls:'duty',handler:onItemClick},</s:if>
 		<s:if test="perrole">{id:'roleright',text: '角色',iconCls:'role',handler:onItemClick},</s:if>
 		<s:if test="perusergroup">{id:'usergroupright',text: '用户组',iconCls:'usergroup',handler:onItemClick},</s:if>
 		{id:'orgright',text: '部门',iconCls:'depart',handler:onItemClick}
   		]}]})};
   		
   		var tb = new Ext.Toolbar();
		tb.render('toolbar');
		tb.add('-',toolbars,'-',application,'-',obj,'-',{id:'login',iconCls: 'login', icon:"/usm/img/041.gif",text: '重新登录',handler:onItemClick});
		tb.add({
		   id:'login',
		  // icon:"/usm/img/plugin.gif",
		   text: '提交',
		   handler:function(){
		   
			   	var evalJs = function(o){
			   //	 Ext.Msg.hide();
			   	   Ext.Msg.updateText('提交成功请刷新页面！');
			   	    
			       //window.location.reload();
			    }
			    
			
			    Ext.Msg.show({
				   title:'是否提交',
				   msg: '提交数据将终端当前系统数据连接确认提交吗？',
				   buttons: Ext.Msg.YESNOCANCEL,
				   fn: function(){
				      Ext.Msg.alert('提交修改', '正在提交请稍候。。。');
			   	    JDS.ajax.Connection.LoadJsonFromUrl( '/usm/commitCache.action', evalJs, 'subSystemId=<s:property value="sysenname"/>');
				   },
				 
				   icon: Ext.MessageBox.QUESTION
				});
			    
			   
			    
		     
		   }}
		 );
		
		}
		
		
		 function onItemClick(item){
	      if(item.id=="position"){
	       	Ext.get("directory-views").dom.src ="/usm/position-index.jsp?sysid="+sysid+"&type=manager";
	      }else if(item.id=="dutylevel"){
	      	Ext.get("directory-views").dom.src ="/usm/dutylevel-index.jsp?sysid="+sysid+"&type=manager";
	      }else if(item.id=="duty"){
	      	Ext.get("directory-views").dom.src ="/usm/duty-index.jsp?sysid="+sysid+"&type=manager";
	      }else if(item.id=="role"){
	      	Ext.get("directory-views").dom.src ="/usm/role-index.jsp?sysid="+sysid+"&type=manager";
	      }else if(item.id=="usergroup"){
	      	Ext.get("directory-views").dom.src ="/usm/usergroup-index.jsp?sysid="+sysid+"&type=manager";
	      }else if(item.id=="login"){
	      	location ="/usmlogin.jsp";
	      }else if(item.id=="application"){
	      	Ext.get("directory-views").dom.src="resources/applicationManager.jsp?sysid="+sysid+"";
	      }else if(item.id=="positionright"){
	      	Ext.get("directory-views").dom.src ="/usm/position-index.jsp?sysid="+sysid+"&type=right";
	      }else if(item.id=="roleright"){
	      	Ext.get("directory-views").dom.src ="/usm/role-index.jsp?sysid="+sysid+"&type=right";
	      }else if(item.id=="dutylevelright"){
	      	Ext.get("directory-views").dom.src ="/usm/dutylevel-index.jsp?sysid="+sysid+"&type=right";
	      }else if(item.id=="dutyright"){
	      	Ext.get("directory-views").dom.src ="/usm/duty-index.jsp?sysid="+sysid+"&type=right";
	      }else if(item.id=="usergroupright"){
	      	Ext.get("directory-views").dom.src ="/usm/usergroup-index.jsp?sysid="+sysid+"&type=right";
	      }else if(item.id=="moduleright"){
	      	Ext.get("directory-views").dom.src ="/usm/moduleright-index.jsp?sysid="+sysid;
	      }else if(item.id=="personright"){
	      	Ext.get("directory-views").dom.src ="/usm/personright-index.jsp?sysid="+sysid;
	      }else if(item.id=="orgright"){
	      	Ext.get("directory-views").dom.src ="/usm/departright-index.jsp?sysid="+sysid;
	      }else{
	      	alert(item.text);	
	      }
  		 }
   	</script>
     <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
   
     
	   <script src='<%=contextpath%>js/prototype.js'></script>
	<script src='<%=contextpath%>js/ext/adapter/yui/yui-utilities.js'></script>
	<script src='<%=contextpath%>js/ext/adapter/yui/ext-yui-adapter.js'></script>
	<script src='<%=contextpath%>js/ext/adapter/ext/ext-base.js'></script>

     <script src='<%=contextpath%>js/ext/ext-all.js'></script>
     <script src='<%=contextpath%>js/JDS.js'></script>
	<link rel="stylesheet" type="text/css" href="<%=contextpath%>usm/css/menus.css" />
    <script type="text/javascript" src="js/directory-index.js" defer="defer"></script>
    
</head>

<body onload="create()"> 
<div id="header"><div style="float:left;margin:5px;" class="x-small-editor">
	<div id="container">
    <div id="toolbar"></div>
</div>	
</div>
</div>
</body>
</html>
