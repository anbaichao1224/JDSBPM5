<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

  <head>
    
    <title>各窗口咨询上报统计列表</title>
    <script type="text/javascript">var context='/';</script>
	<script type="text/javascript" src="/js/extAll.js"></script>
	<script type="text/javascript">
		Ext.onReady(function(){
			// 复选框
			var sm = new Ext.grid.CheckboxSelectionModel({
				dataIndex : 'index'
			});

			// 列名称
			var cm = new Ext.grid.ColumnModel([sm, {
					header : '咨询单位',
					dataIndex : 'sbdw',
					width : 200,
					sortable : true
				}, {
					header : '咨询事项',
					dataIndex : 'zxsx',
					width : 200,
					sortable : true
				}, {
					header : 'UUID',
					dataIndex : 'uuid',
					width : 100,
					sortable : true,
					hidden : true
				}, {
					header : '咨询件数',
					dataIndex : 'zxjs',
					width : 75,
					sortable : true
				}, {
					header : '上报时间',
					dataIndex : 'sbsj',
					width : 100,
					sortable : true,
					type : 'date',
					renderer : Ext.util.Format.dateRenderer('Y-m-d')
				}]);
			
			var proxy = new Ext.data.HttpProxy({
				url : 'ziXunShangBaoList.action'
			});

			// 链接
			var store = new Ext.data.Store({
//				proxy: new Ext.data.MemoryProxy(data),
				proxy : proxy,
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalProperty',
							root : 'root'
						}, [{
									name : 'sbdw'
								}, {
									name:'zxsx'
								}, {
									name:'uuid'
								}, {
									name : 'zxjs'
								}, {
									name : 'sbsj',
									type : 'date',
									dateFormat : 'Y-m-d H:i:s.u'
								}])
			});
				
			//面板
			var grid = new Ext.grid.GridPanel({
				title : '统计列表',
				id : 'grid',
				layout : 'fit',
				bodyStyle : 'width:100%',
				autoHeight : true,
				loadMask : true,
				renderTo : 'grid',
				store : store,
				cm : cm,
				sm : sm,
				bbar : new Ext.PagingToolbar({
							pageSize : 10,
							store : store,
							displayInfo : true,
							displayMsg : '显示第{0}条到{1}条记录,一共{2}条',
							emptyMsg : "没有记录"
				})
			});		
			
			store.load({
				params : {
					start : 0,
					limit : 10
				}
			});
		
		})
	</script>
  </head>
  
  <body>
		<div id="grid"></div>
  </body>
</html>
