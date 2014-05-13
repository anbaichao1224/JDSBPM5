var store;
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
				header : '未收单位',
				dataIndex : 'unInceptdept',
				width : 150,
				sortable : true
			}, {
				header : '取消发送',
				dataIndex : 'cancel',
				width : 150,
				renderer:function(value){
				return String.format("<input type=\"button\" value=\"取消\" onclick=\"cancelSend('{0}')\" />",value);
				}
			}]);

	var proxy = new Ext.data.HttpProxy({
		url : 'gwjhAction_findsendUnreceive.action?uuid='+sendid
	});

	// 链接
	store = new Ext.data.Store({
				proxy : proxy,
				reader : new Ext.data.JsonReader(
				        {
							totalProperty : 'totalProperty',
							root : 'root'
						  }, [
						        {
									name : 'uuid'
								},
						        {
									name : 'unInceptor'
								}, {
									name:'unInceptdept'
							    }, {
									name:'cancel'
							    }
							 ])
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
		title : '未收公文',
		id : 'grid1',
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



function successload() {
	store.load({
				params : {
					start : 0,
					limit : 10
				}
			});
	}
 function cancelSend(uuid){
   Ext.MessageBox.confirm('','确定是否取消',function(btn){
	       if(btn=='yes'){
	          Ext.Ajax.request({
	           url:'gwjhAction_updateCancelFlag.action',
	           method:'post',
	           params:{
	           'uuid':uuid
	           },
	           success:function(){
	             alert("取消成功");
	             store.reload();
	             
	           },failure:function(){
	             alert("退取消失败");
	           }
	          });
	       }else{
	         winClose();
	         return;
	         
	       }
	   
	   })
   }
   
   });
	