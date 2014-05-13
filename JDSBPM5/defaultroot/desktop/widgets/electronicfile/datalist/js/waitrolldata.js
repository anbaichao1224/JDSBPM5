
Ext.onReady(function(){
	var sm = new Ext.grid.CheckboxSelectionModel({
		dataIndex:'messageid' 
	});

	//列名称
	var cm = new Ext.grid.ColumnModel([
		sm,
		{header:'uuid',dataIndex: 'uuid',width:100,sortable:true,hidden:true},
	    {header:'标题',dataIndex: 'namemc',width:400,sortable:true},
	    {header:'发送人',dataIndex: 'creator',width:100,sortable:true},
	    {header:'发送时间',dataIndex: 'createdate',width:100,sortable:true},
	    {header:'公文类型',dataIndex: 'processName',width:100,sortable:true}
	     
	]);
	var urlstr = '/data_list.action';
	var proxy = new Ext.data.HttpProxy({url:urlstr});
	var title = new Ext.form.TextField({
		id:'title',
		name:'title',
		width:120
	});
	var ttbar = new Ext.Toolbar(
			{
				items : ['案卷名称：',title,{// 查询按钮
					id : 'newModelButton',
					text : '查  询',
					cls : "x-btn-text-icon",
					icon: '/desktop/resources/images/search.jpg',
					handler : queryDj
			},'-',
				         {
				        	 text : '组卷',
				        	 id : 'newmodelbtn',
				        	 cls : "x-btn-text-icon",
							 icon: '/desktop/widgets/electronicfile/images/fj_01.png',
				        	 handler : function() {
				        	 	 selectdata(Ext.getCmp('data-list'));
				        	 	//openWin('/roll_listByStatus.action?rollbean.status=0&dataIds='+ids,'400','200');
				        	}
				         },'-',{
				        	 text : '新增档案',
				        	 id : 'createbtn',
				        	 cls : "x-btn-text-icon",
							 icon: '/usm/img/add.gif',
				        	 handler : function() {
				        	 	window.top.showelecData(null,null,null,null,grid);
				        	 	
				        	}
				         }]
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
			{name:'createdate'},
			{name:'processName'}
		])
	});

	
	
	var pgsize = 30;
	store.load({params:{start:0,limit:pgsize}});
	//面板
	var grid= new Ext.grid.GridPanel({
		title:'规则列表',
		id:'data-list',
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
	store.on('beforeload', function() {
		this.baseParams = {
				title:Ext.get('title').dom.value
				
		};
		
	});
	 store.load({params:{start:0,limit:pgsize,title:Ext.get('title').dom.value}});
	function queryDj() {
		store.load({
			params : {
			start : 0,
			limit : pgsize,
			title:title
		}
		});
	}
});
function selectdata(grid){
	var selections=grid.getSelections();
	   if (selections.length==0){
	          alert('请选择需要组卷的文件');
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
	   openWin('/roll_listByStatus.action?rollbean.status=0&dataIds='+fileList,'500','200');
	  // return fileList;
		  
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
		 id:'rollInfo',
	     width:800,
	     height:Ext.getBody().getHeight()-50,
	     title:'案卷信息',
	     closable:true,
		 collapsible:true,
	     autoScroll:true,
	     bodyStyle:'padding:0 5px',
	     items:new Ext.Panel({
		        title: '',        
			  	height:Ext.getBody().getHeight()-100,
			  	bodySyle:'width:100%',
		        html: '<iframe frameborder="0" src="'+url+'" width="100%" height="100%"></iframe>'
	     })
	 });
	 win.show();
}
//小页面
function openWin(url,width,height){
	var win=new Ext.Window({
		 id:'selectRoll',
	     width:width,
	     height:height,
	     title:'案卷信息',
	     closable:true,
		 collapsible:true,
	     autoScroll:true,
	     bodyStyle:'padding:0 5px',
	     items:new Ext.Panel({
		        title: '',        
			  	height:height-50,
			  	bodySyle:'width:100%',
		        html: '<iframe frameborder="0" src="'+url+'" width="100%" height="100%"></iframe>'
	     })
	 });
	 win.show();
}