
Ext.onReady(function(){
						
						
						//复选框
						var sm = new Ext.grid.CheckboxSelectionModel({
							dataIndex:'messageid' 
						});
				
						//列名称
						var cm;
						var store;
						var urlstr = 'mnotice_list.action?liststatus='+liststatus;
						var proxy = new Ext.data.HttpProxy({url:urlstr});
						var ttbar = new Ext.Toolbar(
								{
									items : [
									         {
									        	 text : '新增会议通知',
									        	 id : 'newmodelbtn',
									        	 handler : function() {
									        	
									        	 if (adddirection == 0) {
									        		 
									        		 window.top.opentype('/desktop/widgets/columntreemetting/treemetting_querymodel.action?liststatus=10','1150', '800');
									        	 } else {
									        		 window.top.opentype('/desktop/widgets/columntreemetting/treemetting_querymodel.action','1150', '800');
									        	 }
									         }
									         }, {
									        	 text : '删除',
									        	 id : 'delbtn',
									        	 handler : function() {
									        	 deleteFile(Ext.getCmp('gridbd'));
									        	 // setTimeout("refFileGridById()",500);
									        	 alert("删除成功");
									        	 // refFileGridById();
									        	 store.reload();
									         }
									         } ]
								});
						
						//链接
						
						
						var pgsize = 24;
						if (liststatus == 2) {
							cm = new Ext.grid.ColumnModel( [ sm, {
								header : 'uuid',
								dataIndex : 'uuid',
								width : 100,
								sortable : true,
								hidden : true
							}, {
								header : '会议名称',
								dataIndex : 'namemc',
								width : 400,
								sortable : true
							}, {
								header : '创建人',
								dataIndex : 'creator',
								width : 100,
								sortable : true
							}, {
								header : '创建时间',
								dataIndex : 'createdate',
								width : 100,
								sortable : true
							}
							
							]);
							store = new Ext.data.Store( {
								// proxy: new Ext.data.MemoryProxy(data),
								proxy : proxy,
								reader : new Ext.data.JsonReader( {
									totalProperty : 'totalCount',
									root : 'root'
								}, [ {
									name : 'uuid'
								}, {
									name : 'namemc'
								}, {
									name : 'creator'
								}, {
									name : 'createdate'
								} ])
							});
						} else {
							cm = new Ext.grid.ColumnModel( [ sm, {
								header : 'uuid',
								dataIndex : 'uuid',
								width : 100,
								sortable : true,
								hidden : true
							}, {
								header : '会议名称',
								dataIndex : 'namemc',
								width : 400,
								sortable : true
							}, {
								header : '接收部门名称',
								dataIndex : 'departmentname',
								width : 400,
								sortable : true
							}, {
								header : '创建人',
								dataIndex : 'creator',
								width : 100,
								sortable : true
							}, {
								header : '创建时间',
								dataIndex : 'createdate',
								width : 100,
								sortable : true
							}
							
							]);
							
							store = new Ext.data.Store( {
								// proxy: new Ext.data.MemoryProxy(data),
								proxy : proxy,
								reader : new Ext.data.JsonReader( {
									totalProperty : 'totalCount',
									root : 'root'
								}, [ {
									name : 'uuid'
								}, {
									name : 'namemc'
								}, {
									name : 'departmentname'
								}, {
									name : 'creator'
								}, {
									name : 'createdate'
								} ])
							});
						}
						store.load({params:{start:0,limit:pgsize}});
						//面板
						var grid= new Ext.grid.GridPanel({
							title:'规则列表',
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
	     width:width,
	     height:height,
	     title:'会议类型',
	     closable:true,
		 collapsible:true,
	     autoScroll:true,
	     bodyStyle:'padding:0 5px',
	     items:new Ext.Panel({
		        title: '',        
			  	height:height-50,
			  	width:width-50,
		        html: '<iframe frameborder="0" src="'+url+'" width="754" height="620"></iframe>'
	     })
	 });
	 win.show();
}