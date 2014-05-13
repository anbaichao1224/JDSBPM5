<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String contextpath = request.getContextPath() + "/";
	String personid = request.getParameter("personid");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String flag = request.getParameter("flag");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>共性办公应用系统监控</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link rel="stylesheet" type="text/css"
			href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
		<script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
		<script src='<%=contextpath%>js/ext/ext-all.js'></script>
		<script text="text/javascript">
    var personid='<%=personid%>';
     var username='<%=username%>';
    var password='<%=password%>';
     var flag='<%=flag%>';
    </script>
	</head>

	<body>
		<div id="grid">
		</div>
	</body>
	<script text="text/javascript">
   Ext.onReady(function() {

	// 复选框
	var sm = new Ext.grid.CheckboxSelectionModel({
				dataIndex : 'index'
			});

	// 列名称
	var cm = new Ext.grid.ColumnModel([ sm,
			 {
				header : 'delid',
				dataIndex : 'delid',
				width : 100,
				hidden:true
				
			},{
				header : '序号',
				dataIndex : 'id',
				width : 40,
				sortable : true
			},{
				header : '模块名称',
				dataIndex : 'mkname',
				width : 100,
				sortable : true
			},{
				header : 'personid',
				dataIndex : 'personid',
				width : 330,
				hidden:true
			},{
				header : '标题',
				dataIndex : 'title',
				width : 1030,
				sortable : true
			},{
				header : '时间',
				dataIndex : 'time',
				width : 150,
				sortable : true
			},{
				header : '删除提醒',
				dataIndex : 'del',
				width : 80,
				sortable : true
			}]);

	var proxy = new Ext.data.HttpProxy({
		url : 'Chaxun_PersonTx.action?personid='+personid +'&username='+username+'&password='+password+'&flag='+flag
	});

	// 链接
	var store = new Ext.data.Store({
				proxy : proxy,
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalProperty',
							root : 'root'
						}, [    
						        {
									name : 'id'
								},{
									name : 'personid'
								},{
									name : 'delid'
									
								}, {
									name:'mkname'
								}, {
									name:'title'
								}, {
									name:'time'
								}, {
									name:'del'
								}])
			});

	var pgsize = 23;
	// 面板
	var grid = new Ext.grid.GridPanel({
		title : '提醒列表',
		id : 'grid',
		layout : 'fit',
		bodyStyle : 'width:100%',
		autoHeight : true,
		loadMask : true,
		renderTo : 'grid',
		store : store,
		cm : cm,
		sm:sm,
		bbar : new Ext.PagingToolbar({
					pageSize : pgsize,
					store : store,
					displayInfo : true,
					displayMsg : '显示第{0}条到{1}条记录,一共{2}条',
					emptyMsg : "没有记录"
				}),
		tbar : new Ext.Toolbar([
				{
					id : 'newModelButton',
					icon : "/usm/img/search.png",
					text : '主页',
					cls : "x-btn-text-icon",
					handler : function(){
					 window.location.href="/admin/index.jsp";
					}
				},{
					id : 'newModelButton',
					icon : "/usm/img/search.png",
					text : '返回',
					cls : "x-btn-text-icon",
					handler : function(){
					 window.location.href="/admin/ckry.jsp?username="+username+"&password="+password+"&flag="+flag;
					}
				}, {
					icon : '/usm/img/depart.gif',
					text : '刷新',
					cls : "x-btn-text-icon",
					handler : refresh
				}
				
				
				
				])
				
	});


	store.load({
				params : {
					start : 0,
					limit : pgsize
				}
			});

	
	// 刷新
	function refresh() {
		store.load({
					params : {
						start : 0,
						limit : pgsize
			
					}
				});
	}
	

	function successload() {
		store.load({
					params : {
						start : 0,
						limit : pgsize
					}
				});
	}

});

		
    </script>
</html>
