
Ext.onReady(function(){
						
						
						//复选框
						var sm = new Ext.grid.CheckboxSelectionModel({
							dataIndex:'messageid' 
						});
				     var docname = new Ext.form.TextField({
							width : 100,
							name : 'title',
							allowBlank : true
						});
						var rdata = new Ext.form.DateField({
									width : 100,
									name : 'from',
									format : 'Y-m-d H:i:s',
									cls : "x-btn-text-icon"
								});
								
						var tdata = new Ext.form.DateField({
									width : 100,
									name:'to',
									format : 'Y-m-d H:i:s',
									cls : "x-btn-text-icon"
						});
						//列名称
						var cm;
						var store;
						var urlstr = 'noticeAction_findbypersonid1.action';
						var proxy = new Ext.data.HttpProxy({url:urlstr});
						var ttbar = new Ext.Toolbar(
								{
									items : ['标题：', docname, '从 开始日期：',
									rdata, '至 结束日期:', tdata,
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
									, {
										icon : '/usm/img/depart.gif',
										text : '刷新',
										cls : "x-btn-text-icon",
										handler : refresh
									}
									
									
									]
								});
						
						//链接
						
						
						var pgsize = 30;
						cm = new Ext.grid.ColumnModel( [ sm, {
								header : '序号',
								dataIndex : 'noticeid',
								width : 100,
								sortable : true,
								hidden : true
							}, {
								header : 'uuid',
								dataIndex : 'uuid',
								width : 100,
								sortable : true,
								hidden : true
							},{
								header : '标题',
								dataIndex : 'title',
								width : 300
								
							},{
								header:'发送人',
								dataIndex:'sendperson',
								width:200
							
							},{
							    header:'发送人单位',
								dataIndex:'sendpersondept',
								width:200
							
							}, {
								header :'发送时间',
								dataIndex : 'sendtime',
								width : 200,
								sortable : true
							}
							
							]);
							store = new Ext.data.Store({
								proxy : proxy,
								reader : new Ext.data.JsonReader( {
									totalProperty : 'totalProperty',
									root : 'root'
								}, [ {
									name : 'noticeid'
								}, {
									name : 'uuid'
								}, {
									name : 'title'
								}, {
									name : 'sendtime'
								}, {
									name : 'sendperson'
								}, {
									name : 'sendpersondept'
								} ])
							});
						//store.load({params:{start:0,limit:pgsize}});
						//面板
						var grid= new Ext.grid.GridPanel({
							title:'未读列表',
							id:'gridbd',
							autoHeight: true,
			                bodyStyle:'width:100%',   
							loadMask:true,
							renderTo: 'modelshow',
							store:store,
							cm:cm,
							sm:sm,
							
							tbar: ttbar,
							bbar: new Ext.PagingToolbar({
								pageSize:pgsize,
								store:store,
								displayInfo:true,
								displayMsg:'显示第{0}条到{1}条记录,一共{2}条',
								emptyMsg:"没有记录"
							})
							
						});
			store.on('beforeload', function() {
				this.baseParams = {
					title : Ext.get('title').dom.value,
                     from : Ext.get('from').dom.value,
                      to : Ext.get('to').dom.value
					
				};
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


