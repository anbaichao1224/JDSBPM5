<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
	
	</head>
	<body>
		<div id="grid11"></div>
		<div id="form"></div>
	</body>
		<base href="<%=basePath%>">
		<script>var context='<%=path%>/';</script>


		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
       
		<script type="text/javascript" src="<%=basePath%>js/extAll.js"></script>
		<script type="text/javascript">
		var store;
		Ext.onReady(function() {
      


	// 列名称
	var cm = new Ext.grid.ColumnModel([ {
	            header:'状态',
				dataIndex : 'zt',
				width : 500,
				sortable : true
		
			}]);

	var proxy = new Ext.data.HttpProxy({
		url : 'listckztAction.action?deptid='+ '<ww:property value="deptid"></ww:property>&hq='+ '<ww:property value="hq"></ww:property>'
		 
	});

	// 链接
	 store = new Ext.data.Store({
				// proxy: new Ext.data.MemoryProxy(data),
				proxy : proxy,
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalProperty',
							root : 'root'
						}, [{
						        
								     name : 'zt'
								
								}])
			});


	var pgsize = 10;

	// 面板
	var grid = new Ext.grid.GridPanel({
		title : '详细状态',
		id : 'grid11',
		layout : 'fit',
		bodyStyle : 'width:100%',
		autoHeight : true,
		loadMask : true,
		renderTo : 'grid11',
		store : store,
		cm : cm,
		bbar : new Ext.PagingToolbar({
					pageSize : pgsize,
					store : store,
					displayInfo : true,
					displayMsg : '显示第{0}条到{1}条记录,一共{2}条',
					emptyMsg : "没有记录"
				})
	});



	store.load({
				params : {
					start : 0,
					limit : pgsize
					
				}
			});

	
	


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