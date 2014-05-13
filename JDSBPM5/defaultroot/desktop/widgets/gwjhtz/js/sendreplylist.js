
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
			},{
				header : '回执人',
				dataIndex : 'replyperson',
				width : 100,
				sortable : true
			}, {
				header : '回执人单位',
				dataIndex : 'replydept',
				width : 100,
				sortable : true
			}, {
				header : '回执时间',
				dataIndex : 'replytime',
				width : 100,
				sortable : true
			}, {
				header : '查看回执内容',
				dataIndex : 'replycontent',
				width : 100,
				renderer:function(value){
				 return String.format("<input type=\"button\" value=\"查看\" onclick=\"showreplycontent('{0}')\" />",value);
				}
			}]);

	var proxy = new Ext.data.HttpProxy({
		url : 'gwjhAction_findsendReplylist.action?uuid='+sendid
	});

	// 链接
	var store = new Ext.data.Store({
				proxy : proxy,
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalProperty',
							root : 'root'
						}, [    {
									name : 'uuid'
								},{
									name : 'replyperson'
								}, {
									name:'replydept'
								}, {
									name:'replytime'
								}, {
									name:'replycontent'
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
		title : '回执列表',
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
					handler : function(){
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
  
  function showreplycontent(uuid){
        var _width = 900;
		var _height = Ext.getBody().getHeight()-40;
		var addwin = new Ext.Window({
					title : '回执信息',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					y:0,
					html:"<iframe id='djiframe' name='djiframe' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='gwjhAction_findsendreplyContent.action?uuid="+uuid+"'></iframe>"
				
			});
			addwin.show();
  
  }

	