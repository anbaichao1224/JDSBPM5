Ext.onReady(function(){
	
	var sm = new Ext.grid.CheckboxSelectionModel();
	
    var cm = new Ext.grid.ColumnModel([
    	new Ext.grid.RowNumberer(),
    	sm,
        {header:'申请授权模块名称',dataIndex:'modulename',width:300,sortable:true},
        {header:'授权角色名称',dataIndex:'rolename',width:300,sortable:true},
        //{header:'申请管理员',dataIndex:'adminname', width : 200,sortable:true},
        {header:'申请时间',dataIndex:'createtime', width : 200,sortable:true},
        {header:'授权角色类型', dataIndex : 'roleclass', widht : 200, sortable:true}
    ]);

	var proxy = new Ext.data.HttpProxy({
		url : '/roledata.action'
	});

    var store = new Ext.data.GroupingStore({
    	id : 'store',
        proxy: proxy,
        reader: new Ext.data.JsonReader({
        	totalProperty : 'totalProperty',
        	root : 'root'
        },[
        	{name : 'uuid'},
        	{name : 'typeid'},
        	{name : 'modulename'},
        	{name : 'rolename'},
        	{name : 'createtime'},
        	{name : 'roleclass'}
        ]),
        groupField : 'roleclass',
        sortInfo : {field : 'createtime', direction : 'asc'}
    });
    store.load();

    var grid = new Ext.grid.GridPanel({
    	id : 'grid',
        title : '授权申请待办列表',
        region: 'center',
        margin : '5 0 5 0',
        store: store,
        sm : sm,
        cm: cm,
        view : new Ext.grid.GroupingView({
        	columnsText : '显示的列',
        	scrollOffset : 20,
        	sortAscText : '升序',
        	sortDescText : '降序',
        	groupByText : '按此字段分组',
        	showGroupsText : '隐藏/显示分组',
        	groupOnSort : true,
         	groupField : 'adminname',
         	stripeRows : true,
         	groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "申请记录" : "申请记录"]})',
        	forceFit : true
        }),
        tbar : [{
        	text : '授权通过',
        	icon : "/usm/img/add.gif",
        	cls : "x-btn-text-icon",
        	handler : function(){
        		acct(grid,'M');
        	}
        },{
        	text : '授权拒绝',
        	icon : "/usm/img/delete.gif",
        	cls : "x-btn-text-icon",
        	handler : function(){
        		acct(grid,'F');
        	}
        }]
    });
    
    function acct(grid,type){
		var selections = grid.getSelectionModel()
				.getSelections();
		if (selections.length == 0) {
			alert('请选择需要操作的记录');
			return;
		}
		var ruuid = new Array();
		var rtypeid = new Array();
		var AllList = new Array();
		var typeList = new Array();
		for (var i = 0; i < selections.length; i++) {
			if (selections[i].get('uuid').length > 0) {
				AllList[AllList.length] = selections[i]
						.get('uuid');
			} else {
				grid
						.getSelectionModel()
						.deselectRow(selections[i].get('index'));
			}
			if (selections[i].get('typeid').length > 0) {
				typeList[typeList.length] = selections[i]
						.get('typeid');
			} else {
				grid
						.getSelectionModel()
						.deselectRow(selections[i].get('index'));
			}
		}
		ruuid = AllList.join();
		rtypeid = typeList.join();
		var url = '';
		if(type == 'M'){
			url = '/rolesave.action'
		}else {
			url = '/roleupdate.action'
		}
		Ext.Ajax.request({
			url : url,
			params : {
				ruuid : ruuid,
				type : type,
				rtypeid : rtypeid
			},
			success : function(resp, opts) {
				Ext.Msg.alert('信息', '授权操作成功!',function(){
					parent.Ext.get('secure').dom.src= 'model/roledb.jsp';					
				});
			},
			failure : function(resp, opts) {
				Ext.Msg.alert('信息', '授权操纵失败!');
			}
		});
    }
	var viewport = new Ext.Viewport({
		id:'viewport',
        layout:'border',
        items:[grid]
	});
});