
Ext.onReady(function(){
	var sm = new Ext.grid.CheckboxSelectionModel({
		dataIndex:'messageid' 
	});
	//列名称
	var cm = new Ext.grid.ColumnModel([
		sm,
		{header:'uuid',dataIndex: 'uuid',width:100,sortable:true,hidden:true},
	    {header:'文种名称',dataIndex: 'namemc',width:400,sortable:true},
	    {header:'创建人',dataIndex: 'creator',width:100,sortable:true},
	    {header:'创建时间',dataIndex: 'createdate',width:100,sortable:true}
	    //{header:'操作',dataIndex: 'cz',width:100,sortable:true}
	     
	]);
	var urlstr = '/wzlistAction.action';
	var proxy = new Ext.data.HttpProxy({url:urlstr});
	
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
			//{name:'cz'}
			
		])
	});

	
	var ttbar = new Ext.Toolbar(
			{
				items : [
				         {
				        	 text : '新增',
				        	 id : 'newmodelbtn',
				        	 cls : "x-btn-text-icon",
				        	 icon: '/desktop/resources/images/default/icons/grid2-2.gif',
				        	 handler : function() {
				        	 	addWenzhong();
				        	}
				         },'-', {
				        	 text : '删除',
				        	 id : 'delbtn',
				        	 cls : "x-btn-text-icon",
				        	 icon: '/desktop/widgets/electronicfile/images/delete_01.png',
				        	 handler : function() {
				        	 deleteFile(grid);
				        	
				        	
				         }
				         } ]
			});
	var pgsize = 30;
	store.load({params:{start:0,limit:pgsize}});
	//面板
	var grid= new Ext.grid.GridPanel({
		title:'文种列表',
		id:'wz-list',
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
				//title:Ext.get('title').dom.value
				
		};
		
	});
	 store.load({params:{start:0,limit:pgsize}});
	
});

function wenzhongList(wenzhongId,uuid){
	var _width = 900;
	var _height = Ext.getBody().getHeight()-40;
	var addwin = new Ext.Window({
	    url : '/zhchaxunAction.action?uuid="+uuid+"',
		title : '字号列表',
		layout : 'fit',
		width : _width,
		minWidth : 350,
		height : _height,
		minHeight : 200,
		y:0,
		html:"<iframe id='ckframe' name='ckframe' width='" + (_width-10)  + "' height='" + (_height - 8) + "' src='/desktop/widgets/ttmanager/xianshiList.jsp?wenzhongId="+wenzhongId+"'></iframe>"
	});
	addwin.show();
}

function addWenzhong(uid,wzname,wzename){
	
	var form = new Ext.FormPanel({
		frame:true,
     	width:300,
     	url:'/wzsaveAction.action',
     	items:[{
     		xtype:'textfield',
     		name:'uuid',
     		id:'uuid',
     		hidden:true,
     		hideLabel:true,
     		value:uid
     	},{
     		xtype:'textfield',
     		name:'wzname',
     		id:'wzname',
     		fieldLabel:'文种名称',
     		allowBlank:false,
     		emptyText:"不能为空",
     		msgTarget:'side',
     		value:wzname,
     		anchor:'90%'
     	},{
     		xtype:'textfield',
     		name:'wzename',
     		id:'wzename',
     		allowBlank:false,
     		emptyText:"不能为空",
     		msgTarget:'side',
     		fieldLabel:'名称全拼',
     		value:wzename,
     		anchor:'90%'
     	}],
     	buttons:[{
     		text:'确定',
     		handler:function(){
     			
     			if(Ext.getCmp('wzname').getValue==''){
     				alert("请填写文种名称");
     				return;
     			}
     			if(Ext.getCmp('wzename').getValue==''){
     				alert('请填写文种全拼');
     				return;
     			}
     			form.getForm().submit({
					success:function(){
						alert('操作成功');
						Ext.getCmp('wz-list').getStore().reload();
						Ext.getCmp('addwenzhong').close();
					}
				});
     		}
     	}]
	});
	
	var win = new Ext.Window({
			id:'addwenzhong',
			title:'新增文种',
			width:'310',
			height:'200',
			items:form
	});
		win.show();
}

function deleteFile(grid){
	var selections=grid.getSelections();
	   if (selections.length==0){
	          alert('请选择需要删除的文种');
	   		return;
	   }else{
	   		if(!confirm("确定删除？")){
	   			return;
	   		}
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
		  url:'/wzdeleteAction.action',
		  params:{ids:fileList},
		  method:'post',
		  success:function(o){
			  alert(o.responseText);
			  grid.getStore().reload();
		  }
	  });
		  
}