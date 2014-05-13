
Ext.onReady(function(){
						
						
						//复选框
						var sm = new Ext.grid.CheckboxSelectionModel({
							dataIndex:'messageid' 
						});
					var ttbar = new Ext.Toolbar(
								{
									items : [
									         {
									        	 text : '返回',
									        	 id : 'newmodelbtn',
									        	 handler : function() {
									        	  var nid=nnid;
									        	 window.location.href="/desktop/widgets/notice/yynotice.jsp?noticeid="+nid;
									        	}
									         }, {
													icon : '/usm/img/depart.gif',
													text : '刷新',
													cls : "x-btn-text-icon",
													handler : refresh
												}
									         ]
								});
						//列名称
						var cm;
						var store;
						var urlstr = 'reply_findyyHZlist.action?readid='+rid;
						var proxy = new Ext.data.HttpProxy({url:urlstr});
						
						//链接
						var pgsize = 10;
						cm = new Ext.grid.ColumnModel( [ sm, {
								header : '序号',
								dataIndex : 'noticeid',
								width : 200,
								sortable : true,
								hidden : true
							}, {
								header : '反馈人',
								dataIndex : 'personname',
								width : 200
								
							}, {
								header : '反馈时间',
								dataIndex :'replydate',
								width : 200,
								sortable : true
							},{
								header:'反馈内容',
								dataIndex:'content',
								width:400
							
							}
							
							]);
							store = new Ext.data.Store({
								proxy : proxy,
								reader : new Ext.data.JsonReader( {
									totalProperty : 'totalCount',
									root : 'root'
								}, [ {
									name : 'noticeid'
								}, {
									name : 'personname'
								}, {
									name : 'replydate'
								}, {
									name : 'content'
								} ])
							});
						store.load({params:{start:0,limit:pgsize}});
						//面板
						var grid= new Ext.grid.GridPanel({
							title:'反馈列表',
							id:'gridbd',
							autoHeight: true,
			                bodyStyle:'width:100%',   
							loadMask:true,
							renderTo:'modelshow',
							store:store,
					     	cm:cm,
							tbar: ttbar,
							bbar: new Ext.PagingToolbar({
								pageSize:pgsize,
								store:store,
								displayInfo:true,
								displayMsg:'显示第{0}条到{1}条记录,一共{2}条',
								emptyMsg:"没有记录"
							})
						
							
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
						
					

	 
});


