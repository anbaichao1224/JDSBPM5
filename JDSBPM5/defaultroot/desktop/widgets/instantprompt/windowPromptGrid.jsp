<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'windowPromptGrid.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	var context="/";
	</script>
	<script type="text/javascript" src="/js/extAll.js"></script>
	<script type="text/javascript">
	
		Ext.onReady(function(){
		//alert('1');
	var sm = new Ext.grid.CheckboxSelectionModel({
		dataIndex:'messageid' 
	});
	
	
	function createButton(valueid){
		
			return "<a href=javascript:void(0) onclick=\"cancelPrompt('"+valueid+"')\">取消提醒</a>";
	}
	//列名称
	var cm = new Ext.grid.ColumnModel([
		
		{header:'uuid',dataIndex: 'uuid',width:50,sortable:true,hidden:true},
	    {header:'信息标题',dataIndex: 'title',width:180,sortable:true},
	    {header:'操作',dataIndex: 'uuid',width:70,renderer:createButton}
	     
	]);
	
	var urlstr = '/prompt/prompt_getPromList.action';
	var proxy = new Ext.data.HttpProxy({url:urlstr});
	//链接
	var store = new Ext.data.Store({
		proxy: proxy,
		reader:new Ext.data.JsonReader({
			totalProperty:'totalCount',
			root:'root'
		},[
			{name:'uuid'},
			{name:'title'}
		])
	});

	
	
	var pgsize = 30;
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
	store.load({params:{start:0,limit:pgsize}});
	 
});
function cancelPrompt(uid){
		
		if(confirm("确定取消提醒？")){
										
									Ext.Ajax.request({
										url:'/prompt/promptperson_cancel.action',
										params:{promIds:uid},
										method:'post',
										success:function(){
											Ext.getCmp('windowprompt-list').getStore().reload();
										}
									});
									}
	}
	
	</script>
  </head>
  
  <body>
    
  </body>
</html>
