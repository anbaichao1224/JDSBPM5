<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    
    <title>My JSP 'windowPromptGrid.jsp' starting page</title>
	
	<script type="text/javascript">
	var context="/";
	</script>
	<script type="text/javascript" src="/js/extAll.js"></script>
	<script type="text/javascript">
	
	Ext.onReady(function(){
	
	var sm = new Ext.grid.CheckboxSelectionModel({
		dataIndex:'messageid' 
	});
	
	//列名称
	var cm = new Ext.grid.ColumnModel([
		
		{header:'待办事项名称',dataIndex: 'mkname',width:150,sortable:true},
	    {header:'件数',dataIndex: 'sl',width:80,sortable:true}
	     
	]);
	
	var urlstr = 'tixing_list.action';
	var proxy = new Ext.data.HttpProxy({url:urlstr});
	//链接
	var store = new Ext.data.Store({
		proxy: proxy,
		reader:new Ext.data.JsonReader({
			totalProperty:'totalCount',
			root:'root'
		},[
			{name:'mkname'},
			{name:'sl'}
		])
	});

	//面板
	var grid= new Ext.grid.GridPanel({
		title:'',
		id:'windowprompt-list',
        bodyStyle:'width:100%;height:100%', 
		loadMask:true,
		renderTo: Ext.getBody(),
		store:store,
		hideHeaders:true,
		cm:cm,
		sm:sm
		
	});
	store.load();
	 
});

	
	</script>
  </head>
  
  <body>
    
  </body>
</html>
