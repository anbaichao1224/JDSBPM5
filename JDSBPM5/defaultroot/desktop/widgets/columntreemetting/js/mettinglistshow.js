
function mettinglistshow(liststatus){
	var sm = new Ext.grid.CheckboxSelectionModel({
		dataIndex:'messageid' 
	});

	//列名称
	var cm = new Ext.grid.ColumnModel([
		sm,
		{header:'uuid',dataIndex: 'uuid',width:100,sortable:true,hidden:true},
	    {header:'会议名称',dataIndex: 'namemc',width:100,sortable:true},
	    {header:'开始时间',dataIndex: 'kssj',width:100,sortable:true},
	    {header:'结束时间',dataIndex: 'jssj',width:100,sortable:true},
	    {header:'创建人',dataIndex: 'creator',width:100,sortable:true},
	    {header:'创建时间',dataIndex: 'createdate',width:100,sortable:true}
	     
	]);
	var urlstr = 'mlistshow_mtreelist.action?liststatus='+liststatus;
	var proxy = new Ext.data.HttpProxy({url:urlstr});
	var btnstr;
	if(liststatus==7){
		var ttbar = new Ext.Toolbar(
				{
					items : [
					          {
					        	 text : '删除',
					        	 id : 'delbtn',
					        	 handler : function() {
					        	 deleteFile(Ext.getCmp('metting-list'));
					        	 // setTimeout("refFileGridById()",500);
					        	 alert("删除成功");
					        	 // refFileGridById();
					        	 store.reload();
					         }
					         } ]
				});
	}
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
			{name:'kssj'},
			{name:'jssj'},
			{name:'creator'},
			{name:'createdate'}
		])
	});

	
	
	var pgsize = 30;
	store.load({params:{start:0,limit:pgsize}});
	//面板
	var grid= new Ext.grid.GridPanel({
		title:'会议列表',
		id:'metting-list',
		autoHeight: true,
//		autoWidth: true,
//	    width:800,
        bodyStyle:'width:100%',   
		loadMask:true,
		renderTo: Ext.getBody(),
		store:store,
		cm:cm,
		sm:sm,
		tbar:ttbar,
		bbar: new Ext.PagingToolbar({
			pageSize:pgsize,
			store:store,
			displayInfo:true,
			displayMsg:'显示第{0}条到{1}条记录,一共{2}条',
			emptyMsg:"没有记录"
		})
		
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
		    var str="optionnum=1&mettingids="+fileList;
		 //JDS.ajax.Connection.LoadJsonFromUrl('modeldel.action',null,str);
		    Ext.Ajax.request({
				  url:'modeldel.action',
				  params:{mettingids:fileList,optionnum:'1'},
				  method:'post',
				  success:function(){
					  alert("删除成功");
				  }
			  });
			  
	}
}