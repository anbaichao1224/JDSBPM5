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
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src='<%=contextpath%>js/ext/ext-all.js'></script>

  </head>
  
  <body>
   <div id="grid"></div>
  </body>
  
  <script text="text/javascript">
    
	var root = new Ext.tree.AsyncTreeNode({
		id : '0',
		text : '文件资料库'
		
	}); 
	
	//使目录树重新加载并其所有子目录全部展开
    function treeCjz(){
    	//Ext.getCmp('subcatelogtree').getLoader().load(root);
    	root.reload();
    	//Ext.getCmp('subcatelogtree').expandAll();
    	root.expand(true, true);
    	//root.expandChildNodes(true);
    }
  
    Ext.onReady(function() {
    
	    var tree = new Ext.tree.TreePanel({
	    	id:'subcatelogtree',
			region : 'west',
			title : '子目录管理',
			width : 300,
			split : true,
			loader : new Ext.tree.TreeLoader({dataUrl:'wjzlCatalogAction_findCatalogByPersonId.do'}),
			listeners : {
				Click : function(node){
					var uuid=node.id;
					//alert(uuid);
					document.getElementById("views").src="/desktop/wenjianziliaoku/zmlgl/zmlglList.jsp?catalogUuid="+uuid;
				}
			}
		});
		
	    tree.setRootNode(root);
	    
	  	root.expand(true,true);
		var grid = new Ext.grid.GridPanel({
			title : '文件列表',
			region :'center',
		 	width : 500,
			html:'显示'
		});
		
		/**
		 * 布局
		 */		
		var viewport = new Ext.Viewport({
			layout : 'border',
			items : [tree,{region :'center',
			 	html:'<iframe id="views" frameborder="0" src="/desktop/wenjianziliaoku/zmlgl/zmlglList.jsp" height=100% width=100%></iframe>'
			 }]
		});
	})
	
    </script>
</html>
