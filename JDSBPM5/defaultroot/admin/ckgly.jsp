<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String contextpath = request.getContextPath() + "/";
	String username = request.getParameter("username");
	String password = request.getParameter("password");
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
		<!-- <script src="/desktop/widgets/gwjh/js/hassentlist.js"></script> -->
		<script text="text/javascript">
    var username='<%=username%>';
    var password='<%=password%>';
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
				header : '序号',
				dataIndex : 'id',
				width : 50,
				sortable : true
			},{
				header : '用户名',
				dataIndex : 'user',
				width : 120,
				sortable : true
			},{
				header : '账号',
				dataIndex : 'username',
				width : 120,
				sortable : true
			},{
				header : '密码',
				dataIndex : 'password',
				width : 120,
				sortable : true
			},{
				header : '操作',
				dataIndex : 'cz',
				width : 100,
				sortable : true
			}]);

	var proxy = new Ext.data.HttpProxy({
		url : 'Chaxun_GPersonList.action'
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
									name : 'user'
								},{
									name : 'password'
								},{
									name : 'username'
									
								},{
									name : 'cz'
									
								}])
			});


	var pgsize = 23;
	// 面板
	var grid = new Ext.grid.GridPanel({
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
					icon : '/usm/img/depart.gif',
					text : '刷新',
					cls : "x-btn-text-icon",
					handler : refresh
				},'-', {
						text : '添加',
						icon : '/usm/img/add.gif',
						cls : "x-btn-text-icon",
						handler : function(){
							var _width = 510;
							var _height = Ext.getBody().getHeight()-85;
							var addwin = new Ext.Window({
									title : '查看管理员',
									layout : 'fit',
									width : _width,
									minWidth : 0,
									height : _height,
									minHeight : 500,
									y:0,
									html:"<iframe id='addiframe' name='addiframe' width='" + (_width-10)  + "' height='" + (_height - 10) + "' src='Chaxun_AddPerson.action?'></iframe>",
									tbar:[{
											id : 'rjsave',
											icon : "/usm/img/save.gif",
											text : '保存',
											cls : "x-btn-text-icon",
											handler : function(){
												var fn = Ext.get('addiframe').dom.contentWindow.document.getElementById("addPer");
												//判断排序序号不能为空
											
											var username = Ext.get('addiframe').dom.contentWindow.document.getElementById("username");
											if(username.value == ""){
											//alert(biaoti.value);
											alert("管理员账号不允许为空，请填写账号");
											return;
											}
											var password = Ext.get('addiframe').dom.contentWindow.document.getElementById("password");
											if(password.value == ""){
											alert("管理员密码不允许为空，请填写密码");
											return;
											}
												fn.submit();
												alert(fn.submit());
												alert('保存成功');
												store.reload();
												addwin.close();
											}
										},{
											id : 'rjreset',
											icon : "/usm/img/depart.gif",
											text : '重  置',
											cls : "x-btn-text-icon",
											handler : function(){
												var fn = Ext.get('addiframe').dom.contentWindow.document.getElementById("addPer");
												fn.reset();
											}
										}
									]
							});
							
							addwin.show();
									
						}
				
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
