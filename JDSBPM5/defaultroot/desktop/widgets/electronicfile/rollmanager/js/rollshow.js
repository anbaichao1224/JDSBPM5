
Ext.onReady(function(){
	var sm = new Ext.grid.CheckboxSelectionModel({
		dataIndex:'messageid' 
	});

	var cdate = new Ext.form.DateField({
		width : 100,
		name : 'createdate',
		format:'Y',
		allowBlank : true
	});
	
	var yearchoice = new Ext.form.ComboBox({
		 mode : 'local',
		 id:'yearnum',
		 width:60,
		 name:'yearnum',
		 triggerAction:"all",
		 hiddenName:'ayearnum',
		 displayField: 'svalue', 
        valueField: 'cname', 
        store: new Ext.data.SimpleStore({ 
            fields: ['svalue','cname'] 
                , data: [ 
                     ['2011','2011'],['2012','2012'] , ['2013','2013'], ['2014','2014'],['2015','2015'],['2016','2016'] , ['2017','2017'], ['2018','2018']
                ] 
        })
	});
	var title = new Ext.form.TextField({
		id:'rollname',
		name:'rollname',
		width:100
	});
	var rollnum = new Ext.form.TextField({
		id:'rollnum',
		name:'rollnum',
		width:80
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
                     ['','全部'],['1', '已封卷'] , ['0', '未封卷'] 
                ] 
        })
	});
	
	
	function createButton(valueid){
		
			return "<input type=button value='查看申请人列表' width='150' onclick='opentype(\"/desktop/widgets/electronicfile/pepodom/applicantmanager.jsp?rollid="+valueid+"\")'/>";
	}
	//列名称
	var cm = new Ext.grid.ColumnModel([
		sm,
		{header:'uuid',dataIndex: 'uuid',width:100,sortable:true,hidden:true},
	    {header:'案卷名称',dataIndex: 'namemc',width:400,sortable:true},
	    {header:'案卷号',dataIndex: 'rollnum',width:100,sortable:true},
	    {header:'状态',dataIndex: 'rollstatus',width:100,sortable:true},
	    {header:'创建人',dataIndex: 'creator',width:100,sortable:true},
	    {header:'创建时间',dataIndex: 'createdate',width:100,sortable:true}
	    //{header:'查看',dataIndex: 'uuid',width:200,renderer:createButton}
	     
	]);
	
	var urlstr = '/roll_list.action';
	var proxy = new Ext.data.HttpProxy({url:urlstr});
	var ttbar = new Ext.Toolbar(
			{
				items : ['案卷名称：',title,'年度：',yearchoice ,'案卷号：',rollnum,'状态：',status,{// 查询按钮
					id : 'newModelButton',
					text : '查  询',
					cls : "x-btn-text-icon",
					icon: '/desktop/resources/images/search.jpg',
					handler : queryDj
			},'-',
				         {
				        	 text : '新增案卷',
				        	 id : 'newmodelbtn',
				        	 cls : "x-btn-text-icon",
				        	 icon: '/desktop/resources/images/default/icons/grid2-2.gif',
				        	 handler : function() {
				        	 	opentype('/desktop/widgets/electronicfile/rollmanager/addRoll.jsp');
				        	}
				         },'-', {
				        	 text : '删除',
				        	 id : 'delbtn',
				        	 cls : "x-btn-text-icon",
				        	 icon: '/desktop/widgets/electronicfile/images/delete_01.png',
				        	 handler : function() {
				        	 deleteFile(Ext.getCmp('roll-list'));
				        	 // setTimeout("refFileGridById()",500);
				        	
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
			{name:'rollnum'},
			{name:'rollstatus'},
			{name:'creator'},
			{name:'createdate'}
		])
	});

	
	
	var pgsize = 30;
	//面板
	var grid= new Ext.grid.GridPanel({
		title:'规则列表',
		id:'roll-list',
//		autoWidth: true,
//	    width:800,
        bodyStyle:'width:100%', 
        height:Ext.getBody().getHeight()-50,
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
				rollname:Ext.get('rollname').dom.value,
				yearnum : Ext.get('ayearnum').dom.value,
				status : Ext.get('astatus').dom.value,
				rollnum:Ext.get('rollnum').dom.value
		};
		
	});
	store.load({params:{start:0,limit:pgsize,rollname:Ext.get('rollname').dom.value,yearnum : Ext.get('ayearnum').dom.value,status : Ext.get('astatus').dom.value,rollnum:Ext.get('rollnum').dom.value}});
	function queryDj() {
		store.load({
			params : {
			start : 0,
			limit : pgsize,
			rollname:title,
			yearnum : yearchoice,
			status : status,
			rollnum:rollnum
		}
		});
	}
	 
});
function deleteFile(grid){
	var selections=grid.getSelections();
	   if (selections.length==0){
	          alert('请选择需要删除的案卷');
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
		  url:'/roll_delRoll.action',
		  params:{rollids:fileList},
		  method:'post',
		  success:function(o){
			  alert(o.responseText);
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
		 id:'addRoll',
	     width:800,
	     height:Ext.getBody().getHeight()-100,
	     title:'案卷信息',
	     closable:true,
		 collapsible:true,
	     autoScroll:true,
	     bodyStyle:'padding:5px 5px',
	     items:new Ext.Panel({
		        title: '',        
			  	height:Ext.getBody().getHeight()-150,
			  	bodySyle:'width:100%',
		        html: '<iframe frameborder="0" src="'+url+'" width="100%" height="100%"></iframe>'
	     })
	 });
	 win.show();
}