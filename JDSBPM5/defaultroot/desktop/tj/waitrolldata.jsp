<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*,kzxd.documentmodel.entity.KZXDDocumentModel" %>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<html>
<head>
<script type="text/javascript">
	var context="/";
</script>
<script type="text/javascript" src="/js/extAll.js"></script>
<script type="text/javascript" src="/desktop/js/CreateGrid.js"></script>
<script  src="/js/JDS.js" type="text/javascript"></script>  
<script type="text/javascript">
Ext.onReady(function(){
	var sm = new Ext.grid.CheckboxSelectionModel({
		dataIndex:'messageid' 
	});

	//列名称
	var gwt = '公文类型';
	var cm = new Ext.grid.ColumnModel([
		sm,
	    {header:'紧急程度',dataIndex: 'impend',width:100,sortable:true},
	    {header:'状态',dataIndex: 'status',width:100,sortable:true},
	    {header:'时限',dataIndex: 'timeLimit',width:100,sortable:true}, 
	    {header:'标题',dataIndex: 'fileTitle',width:400,sortable:true},
	    {header:'当前办理人',dataIndex: 'currPerson',width:100,sortable:true},
	    {header:'拟稿人',dataIndex: 'startPerson',width:100,sortable:true},
	    {header:'拟稿时间',dataIndex: 'startTime',width:100,sortable:true},
	    {header:'流程名称',dataIndex: 'processName',width:100,sortable:true},
	    {header:'操作',dataIndex: 'view',width:100,sortable:true}
	]);
	
	
	var deptproxy = new Ext.data.HttpProxy({url:'findChildDept.action'});  
	var record = new Ext.data.Record.create([  
    	{name:'deptid',type:'string',mapping:'id'},  
    	{name:'name',type:'string',mapping:'name'}  
	]); 
	var deptstore = new Ext.data.Store({ 
             proxy:deptproxy,
             reader:new Ext.data.JsonReader({},record)
         });
	var depttype = new Ext.form.ComboBox({
			mode:'remote', 
		 id:'querytype1',
		 name:'querytype1',
		 triggerAction:"all",
		 hiddenName:'deptId',
         store: deptstore,
          displayField: 'name', 
         valueField: 'deptid',
         listeners:{
                       "select":function(deptstore,record,index){
                            var codeId = deptstore.getValue();
                            alert(codeId);
                   			personcom.clearValue();
                            //personstore.load({params:{deptId:codeId}}); //childTypeStroe是子级菜单的Stroe，将所选父级菜单项的ID作为参数load子级菜单；
                   			personstore.proxy = new Ext.data.HttpProxy({url:'findPersonByDept.action?deptId='+codeId});
                   			personstore.load();
                   /*var sd = formTest.findById("personcom") 从查询表单获取对象            
                    sd.setRawValue("");   //设置对象的当前值为空*/
                                     
                     }                               
                               }
         
	});
	
	var personproxy = new Ext.data.HttpProxy({url:'findPersonByDept.action'});  
	var personrecord = new Ext.data.Record.create([  
    	{name:'id',type:'string',mapping:'personid'},  
    	{name:'name',type:'string',mapping:'personname'}  
	]); 
	var personstore =  new Ext.data.Store({ 
             proxy:personproxy,
             reader:new Ext.data.JsonReader({},personrecord)
         });
	var personcom = new Ext.form.ComboBox({
		 mode:'remote',
		 id:'personcom',
		 name:'personcom',
		 width:100,
		 emptyText:'选择人员',
		 readOnly:true,
		 triggerAction:"all",
		 hiddenName:'personid',
         store:personstore,
         displayField: 'name', 
         valueField: 'id'
	});
	
	
	var gwtypeproxy = new Ext.data.HttpProxy({url:'findGwType.action'});  
	var gwtyperecord = new Ext.data.Record.create([  
    	{name:'defId',type:'string',mapping:'defId'},  
    	{name:'defName',type:'string',mapping:'defName'}  
	]); 
	var gwtypestore = new Ext.data.Store({ 
             proxy:gwtypeproxy,
             reader:new Ext.data.JsonReader({},gwtyperecord)
         });
	var gwtype = new Ext.form.ComboBox({
		 mode : 'remote',
		 id:'gwtype',
		 name:'status',
		 width:100,
		 emptyText:'选择人员',
		 readOnly:true,
		 triggerAction:"all",
		 hiddenName:'defId',
    	 displayField: 'defName', 
         valueField: 'defId', 
         store:gwtypestore
	});
	
	var title= new Ext.form.TextField({
		 width : 150,
		 id:'title',
		name : 'title'
	});
	
	var status = new Ext.form.ComboBox({
		 mode : 'local',
		 id:'status',
		 name:'status',
		 width:60,
		 triggerAction:"all",
		 hiddenName:'blstatus',
    	 displayField: 'cname', 
         valueField: 'auditstatus', 
         store: new Ext.data.SimpleStore({ 
             fields: ['auditstatus', 'cname'] 
                 , data: [ 
                      ['0','待办'],['1', '已办']
                 ] 
         })
	});
	
	var starttime = new Ext.form.DateField({
		width : 100,
		id:'starttime',
		name : 'starttime',
		format:'Y-m-d',
		allowBlank : true
	});
	var endtime = new Ext.form.DateField({
		width : 100,
		id:'endtime',
		name : 'endtime',
		format:'Y-m-d',
		allowBlank : true
	});
	var urlstr = '/gwtjAction.action';
	var proxy = new Ext.data.HttpProxy({url:urlstr});
	var ttbar = new Ext.Toolbar(['部门：',depttype,'人员：',personcom,'公文类型：',gwtype,'标题：',title,'状态：',status,'开始时间:',starttime,'结束时间:',endtime,{
								text : '查 询',
				        	 	id : 'querybtn',
				        	 	cls : "x-btn-text-icon",
							 	icon: '/desktop/widgets/electronicfile/images/fj_01.png',
				        	 	handler :queryDj
	}]);
	
	//链接
	var store = new Ext.data.Store({
		//proxy: new Ext.data.MemoryProxy(data),
		proxy: proxy,
		reader:new Ext.data.JsonReader({
			totalProperty:'totalCount',
			root:'root'
		},[
			{name:'uuid'},
			{name:'impend'},
			{name:'status'},
			{name:'timeLimit'},
			{name:'fileTitle'},
			{name:'currPerson'},
			{name:'startPerson'},
			{name:'startTime'},
			{name:'processName'},
			{name:'view'}
		])
	});

	
	
	var pgsize = 30;
	
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
				deptId : Ext.get('deptId').dom.value,
				blstatus:Ext.get('blstatus').dom.value,
				defId:Ext.get('defId').dom.value,
				personid:Ext.get('personid').dom.value,
				title:Ext.get('title').dom.value,
				starttime : Ext.get('starttime').dom.value,
				endtime:Ext.get('endtime').dom.value
		};
		
	});
	store.load({params:{start:0,limit:pgsize,deptId:Ext.get('deptId').dom.value,blstatus:Ext.get('blstatus').dom.value,personid:Ext.get('personid').dom.value,gwtpe:Ext.get('gwtype').dom.value,title:Ext.get('title').dom.value,starttime: Ext.get('starttime').dom.value,endtime: Ext.get('endtime').dom.value}});

	function queryDj() {
		
		store.load({
			params : {
			start : 0,
			limit : pgsize,
			deptId : depttype,
			blstatus:status,
			personid:personcom,
			gwtpe:gwtype,
			title:title,
			starttime : starttime,
			endtime:endtime
		}
		});
	}
	
	
});
</script>
</head>
<body>
<div id="modelshow" style="width:100%"></div>
</body>
</html>