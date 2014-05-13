
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
	var status = new Ext.form.ComboBox({
		 mode : 'local',
		 id:'status',
		 name:'status',
		 triggerAction:"all",
		 hiddenName:'astatus',
    	 displayField: 'cname', 
         valueField: 'auditstatus', 
         store: new Ext.data.SimpleStore({ 
             fields: ['auditstatus', 'cname'] 
                 , data: [ 
                      ['4','全部'],['1', '待审核'] , ['2', '通过'], ['3', '不通过'] 
                 ] 
         })
	});
	
	var ttbar = new Ext.Toolbar(
			['标题：',tquery ,'开始时间：',cdate,'结束时间：',edate,{// 查询按钮
					id : 'newModelButton',
					text : '查  询',
					cls : "x-btn-text-icon",
					icon: '/desktop/resources/images/search.jpg',
					handler : queryDj
			}
			]);
	//列名称
	function createButton(valueid){
		
			return "<input type=button value='文件目录' width='150' onclick=\"location.href='/desktop/widgets/electronicfile/pepodom/applicantData.jsp?rollid="+valueid+"'\"/>";
	}
	var cm = new Ext.grid.ColumnModel([
		 new Ext.grid.RowNumberer({header:"序号",dataIndex:'id',width:40}),
		{header:'uuid',dataIndex: 'uuid',width:40,sortable:true,hidden:true},
	    {header:'档案标题',dataIndex: 'namemc',width:300,sortable:true},
	    {header:'申请时间',dataIndex: 'applicantdate',width:100,sortable:true},
	    {header:'审核人',dataIndex: 'verifier',width:100,sortable:true},
	    {header:'审核时间',dataIndex: 'verifierdate',width:100,sortable:true},
		{header:'开始时间',dataIndex: 'starttime',width:100,sortable:true},
		{header:'结束时间',dataIndex: 'endtime',width:100,sortable:true},
	    {header:'查看',dataIndex: 'uuid',width:100,renderer:createButton} 
	]);
	var urlstr = '/pepodom_applicantpass.action';
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
			{name:'applicantdate'},
			{name:'verifier'},
			{name:'verifierdate'},
			{name:'starttime'},
			{name:'endtime'}
		])
	});

	
	
	var pgsize = 30;
	//store.load({params:{start:0,limit:pgsize,ispass:}});
	//面板
	var grid= new Ext.grid.GridPanel({
		title:'规则列表',
		id:'room-list',
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