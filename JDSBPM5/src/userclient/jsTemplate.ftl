function EVAL.getPanel(){
var sm = new Ext.grid.CheckboxSelectionModel();
var rn = new Ext.grid.RowNumberer();
var colM=new Ext.grid.ColumnModel([
       
			${column}
		]);
var data = Ext.data.Record.create([
	
			${data}
		]);
var myReader = new Ext.data.JsonReader({
		    totalProperty: "totalCount",             
		    root: "datas",
		    id:"id"                       
		}, data);
var store = new Ext.data.Store({
			proxy : new Ext.data.HttpProxy({
			url:'/${url}',
			timeout:90000
			}),
			 remoteSort:false,
			reader:myReader
});
	
var customAction = function query(){
		var parms='extid='+Ext.id() ;
<#list searchColums as item>

	<#if item.fieldType != 'none'>
		<#if item.fieldType == 'ComboBox'>
		var ${item.filed} = Ext.get('${id}${item.filed}');
		
		if(${item.filed}.getValue()&&${item.filed}.getValue()!='' && ${item.filed}.getValue()!='所有')
		{
		parms +="&${item.expression}="+${item.filed}.getValue();
		}
		<#else>
		var ${item.filed} = Ext.get('${id}${item.filed}').dom.value;
		parms +="&${item.expression}="+${item.filed};
		</#if>
	</#if>
</#list>
		var proxyStore = Ext.getCmp("${id}").getStore();
		proxyStore.on("beforeload", function (store, options){
			store.proxy.getConnection().extraParams=Ext.urlDecode(parms);
		});
		store.on("load", afterload);
    	function afterload(store, options){
			store.proxy.getConnection().extraParams='';
			Ext.getCmp("${id}").getEl().unmask()
		}  
		proxyStore.load({params:{start:0,limit:bbar.pageSize}});
};
var customQuery  =  new Ext.Toolbar ({
		items:[
<#list searchColums as item>
	<#if item.fieldType != 'none'>
		<#if item.fieldType == 'DateField'>
			'${item.name}:',{
				xtype:'datefield',
				format: 'Y-m-d',
				width:${item.width},
                name: '${id}${item.filed}',
                id: '${id}${item.filed}'
            },
		<#elseif item.fieldType == 'TextField'>
			'${item.name}:',
			new Ext.form.TextField({   
                name: '${id}${item.filed}',
                 id: '${id}${item.filed}', 
                width:${item.width},
                allowBlank:true
            }),
        <#elseif item.fieldType == 'ComboBox'>
			'${item.name}:',
			new Ext.form.ComboBox({
	                    name: '${id}${item.filed}', 
	                    id: '${id}${item.filed}', 
	                    store: new Ext.data.SimpleStore({
	                        fields: ['value','text'],
	                        data: [${item.initField}]
	                    }),
	                    hiddenName: '${item.filed}',
	                    valueField:'value',               
	                    displayField:'text',
	                    forceSelection: true,
	                    mode: 'local',   
	                    triggerAction:'all',
	                    selectOnFocus:true,
	                    width:${item.width}
	                   }),
		</#if>
	</#if>
</#list>
			new Ext.Button({
				id : 'newModelButton',
				text : '查询',
				handler:customAction
				})
			]
	});

var commonAction = function commonQuery(){
		var title	=	Ext.get('${id}title').dom.value;
		var startTime= Ext.get('${id}startTime').dom.value;
		var endTime =  Ext.get('${id}endTime').dom.value;
		var status =  Ext.get('${id}status ').dom.value;
		var proxyStore = Ext.getCmp("${id}").getStore();
	
		proxyStore.load({params:{start:0,limit:bbar.pageSize,fileTitle:title,status :status ,startTime:startTime,endTime:endTime}});
};

var refreshAction=function refreshQuerty(){

		var proxyStore = Ext.getCmp("${id}").getStore();
	
		proxyStore.load({params:{start:0,limit:bbar.pageSize}});

};

		var status = new Ext.form.ComboBox({
		 mode : 'local',
		 id:'${id}status ',
		 name:'status ',
		 width:85,
		 triggerAction:"all",
		 hiddenName:'blstatus',
    		 displayField: 'cname', 
		 emptyText:'选择方式',
      	         valueField: 'auditstatus', 
        	 store: new Ext.data.SimpleStore({ 
             fields: ['auditstatus', 'cname'] 
                 , data: [ 
                      ['文件标题','文件标题'],['文件编号', '文件编号'],['原文编号', '原文编号'],['收文编号', '收文编号'],['发方总号', '发方总号'],['来文单位', '来文单位'],['文件类型', '文件类型'],['文件等级', '文件等级']
                 ] 
         })
	});
	


var commonQuery  =  new Ext.Toolbar ({
		items:[
			'查询方式:',status ,'查询条件:',
			new Ext.form.TextField({   
                name: 'title',
                width: 300,
emptyText:'请输入查询条件',
                id:'${id}title',
                allowBlank:true 
            }),'开始时间:',
				{xtype:'datefield',
				format: 'Y-m-d',
				 width: 120,
				 id: '${id}startTime',
                name: 'startTime'
            },'结束时间:',{
				xtype:'datefield',
				format: 'Y-m-d',
				 width: 120,
				id:'${id}endTime',
                name: 'endTime'
            },
            new Ext.Button({
               id : 'newModelButton',
               text : '查询',
               handler:commonAction
            }),
            new Ext.Button({
               id : 'newButton',
               text : '刷新',
               handler:refreshAction
            })
            ]
});


var none=new Ext.Toolbar({});
//删除操作
var deleteButton = {
	text:'批量删除',
	handler: function(){
	deleteAll(grid);
	}
};
//中止操作
var abortButton = {
	text:'批量中止',
	handler:function(){
      abortAll(grid);
	}
};

//导出EXCEL操作
var exportExcelButton = {
	text:'导出EXCEL',
	handler:function(){
      exportExcel(grid);
	}
};
//重发操作
var resendButton = {
	text:'重发'
};
var endButton = {
	text:'批量归档',
	handler:function(){
      completeAll(grid);
	}
};
//新建流程
var newProcess = {
	text:'新建流程',
	handler:function(){
      alert('newProcess');
	}
};
	var bbar= new Ext.PagingToolbar({
			${pageSize},
            store: store,
            displayInfo: true,
             displayMsg: '共 {2} 条 当前列表中显示 {0} - {1} 条',
            emptyMsg: "没有工作"
        });
	var grid = new Ext.grid.GridPanel({
				sm:sm,
				${frame}
				cm:colM,
				store:store,
				
				loadMask:{msg:"正在读取数据，请稍候...."},
				frame:true,
				iconCls:'icon-grid',
				bbar:bbar
				}); 
	
    store.on("load", afterload);
    function afterload(store, options){
    	Ext.getCmp("${id}").getEl().unmask();
		}  		
			 
	store.load({params:{start:0,limit:bbar.pageSize}});
	var ${id}={
	  type:'grid',
	  height:grid.height,
	  x:grid.x,
	  y:grid.y,
	  width:grid.width,
      items:[
     		 grid
      ]
    };
    
    if('${actionExt.refreshType}' == 'immediately'){
    	grid.on("cellclick",cellclickGrid);
    	function cellclickGrid(g, r, c, e){
    		var fieldName = g.getColumnModel().getDataIndex(c); 
    		if(fieldName == 'fileTitle' || fieldName == 'view'){
    			g.getStore().reload();
    		}
    	}
    }
	return ${id};
};
function EVAL.callBack(dispwin,module){
     Ext.getCmp("${id}").getEl().mask('正在读取数据，请稍候....');
}
