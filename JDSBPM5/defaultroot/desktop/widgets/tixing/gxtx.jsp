<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String contextpath = request.getContextPath() + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>系统提醒列表</title>

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
				header : 'uuid',
				dataIndex : 'uuid',
				width : 2000,
				hidden:true
				
			},{
				header : 'personid',
				dataIndex : 'personid',
				width : 100,
				hidden:true
			},{
				header : '消息',
				dataIndex : 'msm',
				fixed:true,
				width : 1200,
				renderer : function(value, metadata, record)
				 {
				  metadata.attr = 'style="white-space:normal;"';
				  return value;
				   } 
			},{
				header : '提醒时间',
				dataIndex : 'createtime',
				width : 180,
				sortable : true
			}]);

	var proxy = new Ext.data.HttpProxy({
		url : 'GxTx_gxtx.action'
	});

	// 链接
	var store = new Ext.data.Store({
				proxy : proxy,
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalProperty',
							root : 'root'
						}, [    
						        {
									name : 'uuid'
								},{
									name : 'personid'
								},{
									name : 'msm'
									
								},{
									name : 'createtime'
									
								}])
			});


	var pgsize = 2;
	// 面板
	var grid = new Ext.grid.GridPanel({
		title : '系统提醒列表',
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
		tbar : new Ext.Toolbar(
				 {
					icon : '/usm/img/depart.gif',
					text : '刷新',
					cls : "x-btn-text-icon",
					handler : refresh
				})
				
	});

	store.on('beforeload', function() {
				this.baseParams = {
					
				};
			});

	store.load({
				params : {
					start : 0,
					limit : pgsize
				}
			});

	// 登记查询
	function queryDj() {
		store.load({
					params : {
						start : 0,
						limit : pgsize
					}
				});
	}
	
	// 刷新
	function refresh() {
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
