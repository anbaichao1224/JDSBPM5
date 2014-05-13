
Ext.onReady(function(){
				
						//列名称
						var cm;
						var store;
						var urlstr = 'noticeAction_findbysnid.action';
						var proxy = new Ext.data.HttpProxy({url:urlstr});
						var ttbar = new Ext.Toolbar(
								{
									items : [
									         {
									        	 text : '阅毕',
									        	 id : 'newmodelbtn',
									        	 handler : function() {
									        	
									        	 if (adddirection == 0) {
									        		 
									        		// window.top.opentype('/desktop/widgets/columntreemetting/treemetting_querymodel.action?status=10','1150', '800');
									        	 } else {
									        		// window.top.opentype('/desktop/widgets/columntreemetting/treemetting_querymodel.action','1150', '800');
									        	 }
									         }
									         }]
								});
						
						//链接
						var pgsize = 24;
						if (status == 0) {
							cm = new Ext.grid.ColumnModel( [ {
								header : 'noticeid',
								dataIndex : 'noticeid',
								width : 100,
								sortable : true,
								hidden : true
							}, {
								header : '未阅人',
								dataIndex : 'unreader',
								width : 100
								
							}, {
								header : '未阅人单位',
								dataIndex : 'receivetime',
								width : 100
								
							}
							
							]);
							store = new Ext.data.Store( {
								
								proxy : proxy,
								reader : new Ext.data.JsonReader( {
									totalProperty : 'totalCount',
									root : 'root'
								}, [ {
									name : 'noticeid'
								},{
								    name : 'unreader'	
								},{
								    name : 'receivetime'	
								}
								 ])
							});
						} else {
							cm = new Ext.grid.ColumnModel( [{
								header : 'noticeid',
								dataIndex : 'noticeid',
								width : 100,
								sortable : true,
								hidden : true
							}, {
								header : '已阅人',
								dataIndex : 'reader',
								width : 100
								
							}, {
								header : '已阅人单位',
								dataIndex : 'unreaderdept',
								width : 100
								
							    }, {
								header : '接收时间',
								dataIndex : 'receivetime',
								width : 100
								
							    }
							
							]);
							
							store = new Ext.data.Store( {
								// proxy: new Ext.data.MemoryProxy(data),
								proxy : proxy,
								reader : new Ext.data.JsonReader( {
									totalProperty : 'totalCount',
									root : 'root'
								}, [ {
									name : 'noticeid'
								}, {
									name : 'reader'
								} ,{
									name : 'receivetime'
								}])
							});
						}
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
			  url:'modeldel.action',
			  params:{mettingids:fileList,optionnum:'2'},
			  method:'post',
			  success:function(){
				  alert("删除成功");
			  }
		  });
		  
}
function opentype(url,width,height){
	var win=new Ext.Window({
		 id:'addtype',
	     width:Ext.getBody().getWidth()-50,
	     height:Ext.getBody().getHeight()-50,
	     title:'查看通知信息',
	     closable:true,
		 collapsible:true,
	     autoScroll:true,
	     bodyStyle:'padding:0 5px',
	     items:new Ext.Panel({
		        title: '',        
			  	height:Ext.getBody().getHeight()-100,
			  	width:Ext.getBody().getWidth()-100,
		        html: '<iframe frameborder="0" src="'+url+'" width="100%" height="100%"></iframe>'
	     })
	 });
	 win.show();
}