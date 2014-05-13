
		Ext.onReady(function() {

	// 复选框
	var sm = new Ext.grid.CheckboxSelectionModel({
				dataIndex : 'index'
			});

	// 列名称
	var cm = new Ext.grid.ColumnModel([sm,
	        {
	
				header : 'uuid',
				dataIndex : 'uuid',
				width : 100,
				hidden:true
			}, {
				header : '被取消单位',
				dataIndex : 'canceledDept',
				width : 100
				
			}]);

	var proxy = new Ext.data.HttpProxy({
		url : 'gwjhAction_findCancelCountlist.action?uuid='+sendid
	});

	// 链接
	var store = new Ext.data.Store({
				proxy : proxy,
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalProperty',
							root : 'root'
						}, [   {
									name : 'uuid'
								},  {
									name : 'canceledDept'
								}])
			});
			
		
	
	var pgsize = 10;

	store.load({
			params : {
				start : 0,
				limit : pgsize
				
			}
		});
	// 面板
	var grid = new Ext.grid.GridPanel({
		title : '被取消人列表',
		id : 'grid',
		layout : 'fit',
		bodyStyle : 'width:100%',
		autoHeight : true,
		loadMask : true,
		renderTo : 'grid',
		store : store,
		cm : cm,
		sm : sm,
		tbar : new Ext.Toolbar([
				{
					id : 'newModelButton',
					icon : "/usm/img/search.png",
					text : '返回',
					cls : "x-btn-text-icon",
					handler :function(){
					 window.location.href="/desktop/widgets/gwjh/cancellist.jsp";
					}
				}
				]),
		     bbar : new Ext.PagingToolbar({
					pageSize : pgsize,
					store : store,
					displayInfo : true,
					displayMsg : '显示第{0}条到{1}条记录,一共{2}条',
					emptyMsg : "没有记录"
				})
				
				
	});

});

	