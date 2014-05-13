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
		<div id="grid111" style="width:100%;height:100%"></div>
		<div id="form"></div>
	</body>
	<base href="<%=basePath%>">
		<script>var context='<%=path%>/';</script>


		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
       
		<script type="text/javascript" src="<%=basePath%>js/extAll.js"></script>
		 <style type="text/css">
			 .x-grid3-row td,.x-grid3-summary-row td{ 
				 line-height:10px;//控制GRID单元格高度 
				 vertical-align:middle;//单元格垂直居中 
				 border-right: 1px solid #eceff6 !important;//控制表格列线 
				 border-top: 1px solid #eceff6 !important;//控制表格行线 
			}

		</style>
		<script type="text/javascript">
		var store;
		Ext.onReady(function() {
       
	// 列名称
	var cm = new Ext.grid.ColumnModel([{
				header : 'uuid',
				dataIndex : 'uuid',
				width : 100,
				hidden:true
				
			}, {
	            
				header : '标题',
				dataIndex : 'BT',
				width : 370,
				sortable : true
			}, {
			    header : '编号',
				dataIndex : 'BH',
				width : 150,
				sortable : true
			}, {
			
				header : '等级',
				dataIndex : 'DJ',
				width : 50,
				sortable : true
			
			},{
				header : '发送单位',
				dataIndex : 'NGDW',
				width : 120,
				sortable : true
			},{
				header : '发送时间',
				dataIndex : 'NGSJ',
				width : 160,
				sortable : true
			}, {
				header : '发送单位数',
				dataIndex : 'FSDWS',
				width : 80,
				sortable : true
			}, {
				header : '未收单位',
				dataIndex : 'WSDW',
				width : 80
				
			}, {
				header : '已收单位',
				dataIndex : 'YSDW',
				width : 80
			}, {
				header : '办结单位',
				dataIndex : 'YBJ',
				width : 80
				
			}, {
				header : '退回单位',
				dataIndex : 'TH',
				width : 80
			}, {
				header : '短信催收',
				dataIndex : 'dxcs',
				width : 80
				
			}]);

	var proxy = new Ext.data.HttpProxy({
		url : '/HqYewuAction_bjhqlb.action'
	});

	// 链接
	 store = new Ext.data.Store({
				// proxy: new Ext.data.MemoryProxy(data),
				proxy : proxy,
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalProperty',
							root : 'root'
						}, [{
						        name : 'uuid'
								},{
						        name : 'BT'
								}, {
									name : 'BH'
								}, {
								    name : 'DJ'
								}, {
									name : 'NGDW'
								}, {
								    name : 'NGSJ'
								}, {
									name : 'FSDWS'
								}, {
									name : 'WSDW'
								}, {
									name : 'YSDW'
								}, {
									name : 'YBJ'
								}, {
									name:'TH'
								}, {
									name:'dxcs'
								}])
			});

   
	var pgsize = 10;

	// 面板
	var grid = new Ext.grid.GridPanel({
		title : '已发会签列表',
		id : 'grid111',
		layout : 'fit',
		bodyStyle : 'width:100%',
		width : '1383',
		autoHeight : true,
		loadMask : true,
		renderTo : 'grid111',
		store : store,
	    cm : cm,
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
				} 
          ])


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

	
    // 刷新
	function refresh() {
		store.load();
	}

		</script>
</html>