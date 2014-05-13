
Ext.onReady(function(){
	var sm = new Ext.grid.CheckboxSelectionModel({
		dataIndex:'messageid' 
	});

	//列名称
	var cm = new Ext.grid.ColumnModel([
		sm,
		{header:'uuid',dataIndex: 'uuid',width:100,sortable:true,hidden:true},
	    {header:'会议类型名称',dataIndex: 'namemc',width:400,sortable:true},
	    {header:'创建人',dataIndex: 'creator',width:100,sortable:true},
	    {header:'创建时间',dataIndex: 'createdate',width:100,sortable:true}
	     
	]);
	var urlstr = 'mtype_list.action';
	var proxy = new Ext.data.HttpProxy({url:urlstr});
	var ttbar = new Ext.Toolbar(
			{
				items : [
				         {
				        	 text : '添加会议类型',
				        	 id : 'newmodelbtn',
				        	 handler : function() {
				        	 	opentype('/desktop/widgets/columntreemetting/admin/addtype.jsp');
				        	}
				         }, {
				        	 text : '删除',
				        	 id : 'delbtn',
				        	 handler : function() {
				        	 deleteFile(Ext.getCmp('hylx-list'));
				        	 // setTimeout("refFileGridById()",500);
				        	 alert("删除成功");
				        	 store.reload();
				         }
				         } ]
			});
	
	//链接
	var store = new Ext.data.Store({
		//proxy: new Ext.data.MemoryProxy(data),
		proxy: proxy,
		reader:new Ext.data.JsonReader({
			totalProperty:'totalCount',
			root:'root'
		},[
			{name:'uuid'},
			{name:'namemc'},
			{name:'creator'},
			{name:'createdate'}
		])
	});

	
	
	var pgsize = 30;
	store.load({params:{start:0,limit:pgsize}});
	//面板
	var grid= new Ext.grid.GridPanel({
		title:'规则列表',
		id:'hylx-list',
		autoHeight: true,
//		autoWidth: true,
//	    width:800,
        bodyStyle:'width:100%',   
		loadMask:true,
		renderTo: Ext.getBody(),
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
		   alert("error");
	   }
	  };
	   var fileList=delAllList.join();
	    var str="mtypeids="+fileList;
	  //JDS.ajax.Connection.LoadJsonFromUrl('mtype_deltype.action',null,str);
	  Ext.Ajax.request({
		  url:'mtype_deltype.action',
		  params:{mtypeids:fileList},
		  method:'post',
		  success:function(){
			  alert("删除成功");
		  }
	  });
		  
}
function savetype(){
	Ext.Ajax.request({
	url: "mtype_save.action",
	form:'addtype',
	method: "post",
	success: function(o){
		alert("添加成功");
		var win =parent.Ext.getCmp('addtype');
		parent.Ext.getCmp('hylx-list').load();
			win.close();
		
	}
}); 
	
}
function opentype(url){
	var win=new Ext.Window({
		 id:'addtype',
	     width:800,
	     height:450,
	     title:'会议类型',
	     closable:true,
		 collapsible:true,
	     autoScroll:true,
	     bodyStyle:'padding:0 5px',
	     items:new Ext.Panel({
		        title: '',        
			  	height:400,
			  	bodySyle:'width:100%',
		        html: '<iframe frameborder="0" src="'+url+'" width="100%" height="100%"></iframe>'
	     })
	 });
	 win.show();
}