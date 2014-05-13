
Ext.onReady(function(){
	var sm = new Ext.grid.CheckboxSelectionModel({
		dataIndex:'messageid' 
	});
	　function formatDate(value) {
　　　　　　//return value?value.dateFormat('Y-m-d') : '';
		return Ext.util.Format.date(value, 'Y-m-d');// 用于时间控件返回值
　　　　};

	var cdate = new Ext.form.DateField({
		width : 100,
		id:'applicantdate',
		name : 'applicantdate',
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
	

	//列名称
	var cm = new Ext.grid.ColumnModel([
		sm,
		{header:'uuid',dataIndex: 'uuid',width:100,sortable:true,hidden:true},
	    {header:'标题',dataIndex: 'namemc',width:300,sortable:true},
	    {header:'申请人',dataIndex: 'applicant',width:100,sortable:true},
	    {header:'申请时间',dataIndex: 'applicantdate',width:100,sortable:true},
	    {header:'状态',dataIndex: 'status',width:100,sortable:true},
	    {header:'审核人',dataIndex: 'verifier',width:100,sortable:true},
	    {header:'审核时间',dataIndex: 'verifierdate',width:100,sortable:true},
	    {header:'审核类型',dataIndex: 'filetype',width:100,sortable:true},
		{header:'开始时间',dataIndex: 'starttime',width:100,sortable:true,renderer:formatDate,editor:new Ext.grid.GridEditor(new Ext.form.DateField({format:'Y-m-d',allowBlank:false}))},
		{header:'结束时间',dataIndex: 'endtime',width:100,sortable:true,renderer:formatDate,editor:new Ext.grid.GridEditor(new Ext.form.DateField({format:'Y-m-d',allowBlank:false}))}
	     
	]);
	var urlstr = '/pepodom_applicantrecord.action?liststatus=2';//&rollid='+rollid;
	var proxy = new Ext.data.HttpProxy({url:urlstr});
	var ttbar = new Ext.Toolbar(
			{
				items : ['标题：',tquery ,'状态：',status,'申请时间：',cdate,{// 查询按钮
					id : 'newModelButton',
					text : '查  询',
					cls : "x-btn-text-icon",
					icon: '/desktop/resources/images/search.jpg',
					handler : queryDj
			},
						{
							text:'通过',
							cls : "x-btn-text-icon",
							icon: '/desktop/widgets/electronicfile/images/pass.png',
							handler:function(){
								updateispass(Ext.getCmp('applicant-list'),2);
								
							}
						},'-',{
							text:'不通过',
							cls : "x-btn-text-icon",
							icon: '/desktop/widgets/electronicfile/images/notpass.png',
							handler:function(){
								updateispass(Ext.getCmp('applicant-list'),3);
								//store.reload(); 
							}								
						},'-',
				         {
				        	 text : '保存',
				        	 cls : "x-btn-text-icon",
				        	 icon: '/desktop/widgets/electronicfile/images/save_01.png',
				        	 id : 'newmodelbtn',
				        	 handler : function() {
				        	 	var m = store.modified.slice(0);   
    
              					 var datastr = "[";  
               
              					 Ext.each(m, function(item) {   
                   					//data.push(item.data);  
                   					datastr += "{\"uuid\":\""+item.data["uuid"]+"\",\"starttime\":\""+formatDate(item.data["starttime"])+"\",\"endtime\":\""+formatDate(item.data["endtime"])+"\"},"    
              		
               					}); 
               					datastr = datastr.substring(0,datastr.lastIndexOf(","));   
              					datastr+="]";
               					//alert(Ext.encode(datastr));   
               					Ext.Ajax.request({    
               		url:'pepodom_update.action',  
                   	method:'POST',    
                   	params:{jsonstr:datastr},    
                   success: function(response){      
                       Ext.Msg.alert('信息', "修改成功", function(){     
                           store.reload();    
                       }); 
                    }   
                   });    
                  
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
			{name:'applicant'},
			{name:'applicantdate'},
			{name:'status'},
			{name:'verifier'},
			{name:'verifierdate'},
			{name:'filetype'},
			{name:'starttime',type:'date',dateFormat:'Y-m-d'},
			{name:'endtime',type:'date',dateFormat:'Y-m-d'}
		])
	});

	
	
	var pgsize = 30;
	//store.load({params:{start:0,limit:pgsize}});
	//面板
	var grid= new Ext.grid.EditorGridPanel({
		title:'规则列表',
		id:'applicant-list',
		//autoHeight: true,
//		autoWidth: true,
//	    width:800,
		height:Ext.getBody().getHeight()-50,
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
				starttime : Ext.get('applicantdate').dom.value,
				ispass:Ext.get('astatus').dom.value
		};
		
	});
	store.load({params:{start:0,limit:pgsize,title:Ext.get('title').dom.value,starttime: Ext.get('applicantdate').dom.value,ispass: Ext.get('astatus').dom.value}});

	function queryDj() {
		store.load({
			params : {
			start : 0,
			limit : pgsize,
			tite : tquery,
			starttime : cdate,
			ispass:status
		}
		});
	}
	 
});
function updateispass(grid,ispass){
	var selections=grid.getSelections();
	   if (selections.length==0){
	          alert('请先选择数据');
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
	  Ext.Ajax.request({
		  url:'pepodom_updateIspass.action',
		  params:{pids:fileList,ispass:ispass},
		  method:'post',
		  success:function(){
			  alert("操作成功");
			  grid.getStore().reload();
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
	     width:Ext.getBody().getWidth()-100,
	     height:Ext.getBody().getHeight()-150,
	     title:'案卷信息',
	     closable:true,
		 collapsible:true,
	     autoScroll:true,
	     bodyStyle:'padding:5px 5px',
	     items:new Ext.Panel({
		        title: '',        
			  	height:Ext.getBody().getHeight()-200,
			  	bodySyle:'width:100%',
		        html: '<iframe frameborder="0" src="'+url+'" width="100%" height="100%"></iframe>'
	     })
	 });
	 win.show();
}