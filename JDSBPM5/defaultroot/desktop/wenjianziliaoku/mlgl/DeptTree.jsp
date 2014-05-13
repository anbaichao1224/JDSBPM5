<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String contextpath = request.getContextPath() + "/";


%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>目录管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src='<%=contextpath%>js/ext/ext-all.js'></script>
    <script src='<%=contextpath%>desktop/widgets/notice/js/notice.js'></script>
	 <!-- <script src="/desktop/widgets/gwjh/js/cancellist.js"></script>  -->
    <script text="text/javascript">
    
    Ext.onReady(function() {
    
	    var tree = new Ext.tree.TreePanel({
	    	id:'treePanel',
			region : 'west',
			title : '文件资料库',
			width : 300,
			split : true,
			loader : new Ext.tree.TreeLoader({dataUrl:'wjzlRootCatalogMangerAction_findAllDepts.do'})
		});
		
		var root = new Ext.tree.AsyncTreeNode({
			id : '0',
			text : '请选择部门名称'
			
		}); 
		
		 tree.setRootNode(root);
	  	root.expand(true,true);
	  	
	  	var viewport = new Ext.Viewport({
			layout : 'border',
			items : [tree]
		});
	})
	   
	
    </script>

  </head>
  
  <body>
   <div id="grid"> 
       </div>
  </body>
</html>
