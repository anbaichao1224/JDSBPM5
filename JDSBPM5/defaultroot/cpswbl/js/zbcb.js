Ext.onReady(function(){
    var pgsize = 25;
    
	var sm = new Ext.grid.CheckboxSelectionModel();
	
	var cm = new Ext.grid.ColumnModel([
    	new Ext.grid.RowNumberer(),
    	sm,
    	{header:'紧急程度',dataIndex:'jjcd',width:100,sortable:true},
    	//{header:'状态',dataIndex:'state',width:100,sortable:true},
    	{header:'时限',dataIndex:'timex',width:50,sortable:true},
        {header:'标题',dataIndex:'title',width:300,sortable:true},
        {header:'接收时间',dataIndex:'stime',width:300,sortable:true},
        {header:'流程名称',dataIndex:'pname', width : 100,sortable:true},
        {header:'发送人', dataIndex : 'sendperson', widht : 100, sortable:true},
        {header:'操作', dataIndex : 'option', widht : 100, sortable:true}
    ]);
    
    var proxy = new Ext.data.HttpProxy({
		url : '/zbchDatas.action'
	});
	
	var store = new Ext.data.Store({
    	id : 'store',
        proxy: proxy,
        reader: new Ext.data.JsonReader({
        	totalProperty : 'totalProperty',
        	root : 'root'
        },[
        	{name : 'uuid'},
        	{name : 'jjcd'},
        	//{name : 'state'},
        	{name : 'timex'},
        	{name : 'title'},
        	{name : 'stime'},
        	{name : 'pname'},
        	{name : 'sendperson'},
        	{name : 'option'}
        ])
    });
    
    var ttitle = new Ext.form.TextField({
				width : 100,
				id : 'ttitle',
				name : 'ttitle',
				allowBlank : true
			});
    
    var startdate = new Ext.form.DateField({
				width : 100,
				id : 'startdate',
				name : 'startdate',
				format : 'Y-m-d',
				cls : "x-btn-text-icon"
			});
			
    var enddate = new Ext.form.DateField({
			width : 100,
			id : 'enddate',
			name : 'enddate',
			format : 'Y-m-d',
			cls : "x-btn-text-icon"
		});
   
    var tb = new Ext.Toolbar({
    	autoHeight : true,
    	items : ['标题:',ttitle,'开始时间:',startdate,'结束时间:',enddate,{
			icon : "/usm/img/search.png",
			text : '查  询',
			cls : "x-btn-text-icon",
			handler : function(){
				    store.load({
						params : {
							start : 0,
							limit : pgsize
						}
					});
			}
    	}]
    });
    
    var grid = new Ext.grid.GridPanel({
    	id : 'grid',
        title : '在办承办信息列表',
		region : 'center',      
        store: store,
        layout : 'fit',
        sm : sm,
        cm: cm,
        tbar : tb,
	    bbar : new Ext.PagingToolbar({
			pageSize : pgsize,
			store : store,
			displayInfo : true,
			displayMsg : '显示第{0}条到{1}条记录,一共{2}条',
			emptyMsg : "没有记录"
		}),
        viewConfig : {
   		    columnsText : '显示的列',
        	scrollOffset : 20,
        	sortAscText : '升序',
        	sortDescText : '降序',
        	forceFit : true
        }
    });
    
    var viewport = new Ext.Viewport({
		id:'viewport',
        layout:'border',
        items:[grid]
	});
    
    store.on('beforeload', function() {
			this.baseParams = {
				ttitle : Ext.get('ttitle').dom.value,
				startdate : Ext.get('startdate').dom.value,
				enddate : Ext.get('enddate').dom.value
			};
		});
		
    store.load({
		params : {
			start : 0,
			limit : pgsize
		}
	});
    
    var openActivityInstWin = function(){}
});
function openActivityInstWin(aid){
	window.top.openActivityInstWin(aid);
}
