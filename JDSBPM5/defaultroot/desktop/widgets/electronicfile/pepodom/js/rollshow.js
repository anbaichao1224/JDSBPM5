
Ext.onReady(function(){
	var sm = new Ext.grid.CheckboxSelectionModel({
		dataIndex:'messageid' 
	});
	
var cdate = new Ext.form.DateField({
		width : 100,
		id:'createdate',
		name : 'createdate',
		format:'Y',
		allowBlank : true
	});
	
	var edate = new Ext.form.DateField({
		width : 100,
		id:'enddate',
		name : 'enddate',
		format:'Y',
		allowBlank : true
	});
	
	var tquery = new Ext.form.TextField({
		 width : 100,
		 id:'title',
		name : 'title'
	});
	
	function createButton(valueid){
		
			return "<input type=button value='申请查看' width='150' onclick=\"applicant('"+valueid+"')\"/>";
	}
	

	var ttbar = new Ext.Toolbar(
			{
				items : ['标题：',tquery ,'开始时间：',cdate,'结束时间：',edate,{// 查询按钮
					id : 'newModelButton',
					text : '查  询',
					cls : "x-btn-text-icon",
					icon: '/desktop/resources/images/search.jpg',
					handler : queryDj
			}]}
			);
	//列名称
	var cm = new Ext.grid.ColumnModel([
		 new Ext.grid.RowNumberer({header:"序号",dataIndex:'id',width:40}),
		{header:'uuid',dataIndex: 'uuid',width:100,sortable:true,hidden:true},
	    {header:'档案标题',dataIndex: 'namemc',width:400,sortable:true},
	    {header:'发送人',dataIndex: 'creator',width:100,sortable:true},
	    {header:'归档时间',dataIndex: 'createdate',width:100,sortable:true},
	   /* {header:"操作",dataIndex:"uuid",renderer:function(valueid){   
   													var btnId = Ext.id();//获得Ext创建的唯一id   
   													createGridButton.defer(1, this, [btnId]);   
   													function createGridButton(){   
     											 		return new Ext.Button({text:'申请查看',handler:function(){
     											 		
     											 			var form = new Ext.FormPanel({
     											 				frame:true,
     											 				width:300,
     											 				url:'/pepodom_save.action',
     											 				items:[
     											 					{
     											 						xtype:'datefield',
     											 						fieldLabel:'开始日期',
     											 						name:'starttime',
     											 						format:'Y-m-d',
     											 						anchor:'90%'
     											 					},
     											 					{
     											 						xtype:'datefield',
     											 						fieldLabel:'结束日期',
     											 						name:'endtime',
     											 						format:'Y-m-d',
     											 						anchor:'90%'
     											 					},
     											 					new Ext.form.Hidden({
     											 						name:'rollid',
     											 						fieldLabel:'案卷id',
     											 						value:valueid
     											 					})
     											 				],
     											 				buttons:[{text:"确定",handler:login,formBind:true},{text:"取消",handler:reset}]	 
													 		});
													 		function login(){
																form.getForm().submit({
																	success:function(){
																		//alert("1");
																		store.reload();
																		Ext.getCmp('addPepodom').close();
																	}
																});
																store.reload();
															}
															function reset(){
																form.form.reset();
															}
													 		var win = new Ext.Window({
													 			id:'addPepodom',
													 			title:'申请信息',
													 			width:'310',
													 			height:'200',
													 			items:form
													 		});
													 		win.show();
													 		
     											 			/*Ext.Ajax.request({
     											 				url:'pepodom_save.action',
     											 				method:'post',
     											 				params:{rollid:value},
     											 				success:function(){
     											 					alert("请求发送成功，请等待审核");
     											 				}
     											 			});*/
     											 	/*	}}).render(document.body, btnId);   
   													}   
   													return "<div id="+btnId+"></div>";   
   													}
	}*/
	      {header:'申请',dataIndex: 'uuid',width:200,renderer:createButton}
	]);
	var urlstr = '/pepodom_list.action';
	var proxy = new Ext.data.HttpProxy({url:urlstr});
	
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
	
		//面板
	var grid= new Ext.grid.GridPanel({
		title:'规则列表',
		id:'room-list',
		autoHeight: true,
//		autoWidth: true,
//	    width:800,
        bodyStyle:'width:100%;height:100%',   
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
				title : Ext.get('title').dom.value,
				starttime : Ext.get('createdate').dom.value,
				endtime:Ext.get('enddate').dom.value
		};
		
	});
	store.load({params:{start:0,limit:pgsize,title:Ext.get('title').dom.value,starttime: Ext.get('createdate').dom.value,endtime: Ext.get('enddate').dom.value}});

	
	function queryDj() {
		store.load({
			params : {
			start : 0,
			limit : pgsize,
			tite : tquery,
			starttime : cdate,
			endtime:edate
		}
		});
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
		   alert("error");
	   }
	  };
	   var fileList=delAllList.join();
	    var str="mtypeids="+fileList;
	  //JDS.ajax.Connection.LoadJsonFromUrl('mtype_deltype.action',null,str);
	  Ext.Ajax.request({
		  url:'mroom_delroom.action',
		  params:{roomids:fileList},
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

	function applicant(valueid){
			var form = new Ext.FormPanel({
     											 				frame:true,
     											 				width:300,
     											 				url:'/pepodom_save.action',
     											 				items:[
     											 					{
     											 						xtype:'datefield',
     											 						fieldLabel:'开始日期',
     											 						id:'starttime',
     											 						name:'starttime',
     											 						format:'Y-m-d',
     											 						allowBlank:false,
     											 						blankText:'不能为空',
     											 						anchor:'90%'
     											 					},
     											 					{
     											 						xtype:'datefield',
     											 						fieldLabel:'结束日期',
     											 						id:'endtime'
     											 						name:'endtime',
     											 						format:'Y-m-d',
     											 						allowBlank:false,
     											 						blankText:'不能为空',
     											 						anchor:'90%'
     											 					},
     											 					new Ext.form.Hidden({
     											 						name:'rollid',
     											 						fieldLabel:'案卷id',
     											 						value:valueid
     											 					})
     											 				],
     											 				buttons:[{text:"确定",handler:login,formBind:true},{text:"取消",handler:reset}]	 
													 		});
													 		function login(){
													 			
																form.getForm().submit({
																	success:function(){
																		//alert("1");
																		//store.reload();
																		
																		//Ext.getCmp('room-list').getStore().reload();
																		Ext.getCmp('addPepodom').close();
																	}
																});
																//store.reload();
																Ext.getCmp('room-list').getStore().reload();
															}
															function reset(){
																form.form.reset();
															}
													 		var win = new Ext.Window({
													 			id:'addPepodom',
													 			title:'申请信息',
													 			width:'310',
													 			height:'200',
													 			items:form
													 		});
													 		win.show();
	}


function opentype(url){
	var win=new Ext.Window({
		 id:'addRoom',
	     width:800,
	     height:Ext.getBody().getHeight()-50,
	     title:'案卷信息',
	     closable:true,
		 collapsible:true,
	     autoScroll:true,
	     bodyStyle:'padding:5px 5px',
	     items:new Ext.Panel({
		        title: '',        
			  	height:Ext.getBody().getHeight()-100,
			  	bodySyle:'width:100%',
		        html: '<iframe frameborder="0" src="'+url+'" width="100%" height="100%"></iframe>'
	     })
	 });
	 win.show();
}