
Ext.onReady(function(){
						
						
						//复选框
						var sm = new Ext.grid.CheckboxSelectionModel({
							dataIndex:'messageid' 
						});
				
						//列名称
						var cm;
						var store;
						
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
						var urlstr = 'noticeAction_findbypersonid.action';
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
									} , {
										icon : '/usm/img/depart.gif',
										text : '刷新',
										cls : "x-btn-text-icon",
										handler : refresh
									  }
									 ]
								});
						
	

						
	             var pgsize = 15;
	             cm = new Ext.grid.ColumnModel( [ sm, {
								header : '序号',
								dataIndex : 'noticeid',
								width : 100,
								sortable : true,
								hidden : true
							},{
								header : 'uuid',
								dataIndex : 'uuid',
								width : 100,
								sortable : true,
								hidden : true
							},{
								header : '标题',
								dataIndex : 'title',
								width : 300,
								sortable : true
							}, {
								header : '发送时间',
								dataIndex : 'sendtime',
								width : 200,
								sortable : true
							},{
								header:'未阅人数',
								dataIndex:'unread',
								width:100
							
							},{
								header:'已阅人数',
								dataIndex:'read',
								width:100
								
							
							},{
								header :'delete',
								dataIndex : 'delete',
								width : 100,
								hidden:true,
								sortable : true
							}, {
								header :'取消情况',
								dataIndex : 'qxflag',
								width : 100,
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
									name : 'unread'
								}, {
									name : 'read'
								}, {
									name : 'qxflag'
								} ])
							});
						//store.load({params:{start:0,limit:pgsize}});
						//面板
						var grid= new Ext.grid.GridPanel({
							title:'已发通知列表',
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

	store.load({
				params : {
					start : 0,
					limit : pgsize,
					title : Ext.get('title').dom.value,
                    from : Ext.get('from').dom.value,
                    to : Ext.get('to').dom.value
				}
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
			
			
      function updatePage(){
      var records = Ext.getCmp('gridbd').getSelectionModel().getSelections();
		if (records.length == 0) {
			alert("请选择需要修改的记录");
			return;
		} else if (records.length > 1) {
			alert("请进行单项选择");
			return;
		}
		var _record = Ext.getCmp('gridbd').getSelectionModel().getSelected();
		showupdatewin(_record.get("uuid"),_record.get("noticeid"));	
		}
	
	function showupdatewin(uuid,noticeid){
        var _width = 900;
		var _height = Ext.getBody().getHeight()-40;
		var addwin = new Ext.Window({
					title : '修改信息',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					y:0,
					html:"<iframe id='djiframe' name='djiframe' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='noticeAction_updatePage.action?uuid="+uuid+"&noticeid="+noticeid+"'></iframe>",
					tbar:[
						{
							id : 'djsave',
							icon : "/usm/img/save.gif",
							text : '保存',
							cls : "x-btn-text-icon",
							handler : function(){
								var fn = Ext.get('djiframe').dom.contentWindow.document.getElementById("updateNotice");
								fn.submit();
								alert("保存成功");
								addwin.close();
							}
						},{
							id : 'djreset',
							icon : "/usm/img/depart.gif",
							text : '重  置',
							cls : "x-btn-text-icon",
							handler : function(){
								var fn = Ext.get('djiframe').dom.contentWindow.document.getElementById("updateNotice");
								fn.reset();
							}
						}
					]
			});
			addwin.show();
  
  
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


function deleteFile(grid){
	var selections=grid.getSelections();
	   if (selections.length==0){
	          alert('请选择需要删除的文件');
	   return;
	   }
 
	var delAllList=new Array();
	  for(var i=0;i<selections.length;i++){
	   if (selections[i].get('uuid').length>0 ){     
	    delAllList[delAllList.length]= selections[i].get('uuid');    
	   }else{
	    //Ext.getCmp(gridId).getSelectionModel().deselectRow(selections[i].get('index'));   
		   alert("error");
	   }
	  };
	   var fileList=delAllList.join();
	    var str="optionnum=2&mettingids="+fileList;
	  //JDS.ajax.Connection.LoadJsonFromUrl('modeldel.action',null,str);
	    Ext.Ajax.request({
			  url:'noticeAction_delSentNotice.action',
			  params:{rollids:fileList},
			  method:'post',
			  success:function(){
			    alert(o.responseText);
			   grid.getStore().reload();
				  alert("删除成功");
			  }
		  });
		  
}
