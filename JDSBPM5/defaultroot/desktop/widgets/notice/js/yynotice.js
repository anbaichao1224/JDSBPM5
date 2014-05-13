Ext.onReady(function(){
        var cm;
		var store;
		var urlstr = 'noticeAction_findReadNotice.action?noticeid='+nid;
		var proxy = new Ext.data.HttpProxy({url:urlstr});
			var ttbar = new Ext.Toolbar(
								{
									items : [
									         {
									        	 text : '返回',
									        	 id : 'newmodelbtn',
									        	 handler : function() {
									        
                                              window.location.href="/desktop/widgets/notice/yfnoticelist.jsp";

									         }
									         }, {
												icon : '/usm/img/depart.gif',
												text : '刷新',
												cls : "x-btn-text-icon",
												handler : refresh
											}
																         
									         
									         
									         ]
								});
                               cm = new Ext.grid.ColumnModel( [ {
								header : 'noticeid',
								dataIndex : 'noticeid',
								width : 100,
								sortable : true,
								hidden:true
								
							}, {
								header : '已阅人',
								dataIndex : 'reader',
								width : 200
								
							}, {
								header : '已阅人单位',
								dataIndex : 'readerdept',
								width : 200
								
							},{
								header : '接收时间',
								dataIndex : 'receivetime',
								width : 200
								
							},{
							   header : '查看回执',
								dataIndex : 'searchReply',
								width : 200
							
							}
							
							]);
							var pgsize = 30;
							store = new Ext.data.Store( {
								
								proxy : proxy,
								reader : new Ext.data.JsonReader( {
									totalProperty : 'totalProperty',
									root : 'root'
								}, [ {
									name : 'noticeid'
								},{
								    name : 'reader'	
								},{
								    name : 'readerdept'	
								},{
								    name : 'receivetime'	
								},{
								    name : 'searchReply'	
								}
								 ])
							});
							store.load({params:{start:0,limit:pgsize}});
						//面板
						
						var grid= new Ext.grid.GridPanel({
							title:'通知列表',
							id:'gridbd',
							autoHeight: true,
			                bodyStyle:'width:100%',   
							loadMask:true,
							renderTo: 'modelshow',
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


