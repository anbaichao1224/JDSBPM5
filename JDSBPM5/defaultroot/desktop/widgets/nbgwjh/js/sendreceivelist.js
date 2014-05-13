
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
				header : '接收人',
				dataIndex : 'Inceptor',
				width : 100
				
			}, {
				header : '接收单位',
				dataIndex : 'Inceptdept',
				width : 100
				
			}, {
				header : '接收时间',
				dataIndex : 'Incepttime',
				width : 100,
				sortable : true
			},{
			    header : '查看回执',
				dataIndex : 'reply',
				width : 100
			
			
			}]);

	var proxy = new Ext.data.HttpProxy({
		url : 'gwjhAction_findsendreceive.action?uuid='+sendid
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
									name : 'Inceptor'
								}, {
									name:'Inceptdept'
								}, {
									name : 'Incepttime'
								}, {
									name : 'reply'
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
		title : '已收公文',
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
					 window.location.href="/desktop/widgets/gwjh/hassentlist.jsp";
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

	