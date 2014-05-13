Ext.onReady(function() {

	// 复选框
	var sm = new Ext.grid.CheckboxSelectionModel({
				dataIndex : 'index'
			});

	// 列名称
	var cm = new Ext.grid.ColumnModel([ {
				header : '标题',
				dataIndex : 'title',
				width : 180,
				sortable : true
			}, {
				header : '发送人',
				dataIndex : 'sendor',
				width : 180,
				sortable : true
			}, {
				header : '发送单位',
				dataIndex : 'senddept',
				width : 180,
				sortable : true
			}, {
				header : '发送时间',
				dataIndex : 'sendtime',
				width : 180,
				sortable : true
			}, {
				header : '密级',
				dataIndex : 'mj',
				width : 180,
				sortable : true
			}, {
				header : '紧急程度',
				dataIndex : 'jjcd',
				width : 180,
				sortable : true
			}, {
				header : '份数',
				dataIndex : 'copy',
				width : 180,
				sortable : true
			}]);

	var proxy = new Ext.data.HttpProxy({
		url : 'gwjhAction_findUnReceivelist.action'
	});

	// 链接
	var store = new Ext.data.Store({
				proxy : proxy,
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalProperty',
							root : 'root'
						}, [{
									name : 'title'
								}, {
									name:'sendor'
								}, {
									name : 'senddept'
								}, {
									name : 'sendtime'
								}, {
									name : 'mj'
								}, {
									name : 'jjcd'
								}, {
									name : 'copy'
								}])
			});

	var docname = new Ext.form.TextField({
				width : 100,
				name : 'title',
				allowBlank : true
			});

	var rdata = new Ext.form.DateField({
				width : 100,
				name : 'from',
				format : 'Y-m-d',
				cls : "x-btn-text-icon"
			});
			
	var tdata = new Ext.form.DateField({
				width : 100,
				name:'to',
				format : 'Y-m-d',
				cls : "x-btn-text-icon"
	});

	

	

	var pgsize = 10;

	// 面板
	var grid = new Ext.grid.GridPanel({
		title : '未收公文列表',
		id : 'grid',
		layout : 'fit',
		bodyStyle : 'width:100%',
		autoHeight : true,
		loadMask : true,
		renderTo : 'grid',
		store : store,
		cm : cm,
		bbar : new Ext.PagingToolbar({
					pageSize : pgsize,
					store : store,
					displayInfo : true,
					displayMsg : '显示第{0}条到{1}条记录,一共{2}条',
					emptyMsg : "没有记录"
				}),
		tbar : new Ext.Toolbar(['标题：', docname, '从 开始日期：',
				rdata, '至 结束时间:', tdata,
				{
					id : 'newModelButton',
					icon : "/usm/img/search.png",
					text : '查  询',
					cls : "x-btn-text-icon",
					handler : queryDj
				}, {
					icon : '/usm/img/depart.gif',
					text : '重  置',
					cls : "x-btn-text-icon",
					handler : requery
				}
				])
				
	});

	store.on('beforeload', function() {
				this.baseParams = {
					title : Ext.get('title').dom.value,
                     from : Ext.get('from').dom.value,
                      to : Ext.get('to').dom.value
					
				};
			});

	store.load({
				params : {
					start : 0,
					limit : pgsize,
					title : Ext.get('title').dom.value,
                    from : Ext.get('from').dom.value,
                    to : Ext.get('to').dom.value
				}
			});
			
  function renderUnreceive(value){
      
  
  
  }

	// 登记查询
	function queryDj() {
		store.load({
					params : {
						start : 0,
						limit : pgsize,
						title:Ext.get('title').dom.value,
                        from:Ext.get('from').dom.value,
                        to:Ext.get('to').dom.value
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

	// 登记条件重置
	function requery() {
		Ext.get('title').dom.value = '';
		Ext.get('from').dom.value = '';
		Ext.get('to').dom.value = ''
		
	}
});
	

		