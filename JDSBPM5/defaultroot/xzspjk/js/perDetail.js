Ext.onReady(function(){
	
	//列名称
	var proxy = new Ext.data.HttpProxy({url:'XZSPAction_findPermissionByPerUuid.action?permissionid='+permissionid});

	var cm = new Ext.grid.ColumnModel([{
			header : 'uuid',
			dataIndex : 'uuid',
			width : 100,
			sortable : true,
			hidden : true
		},{
			header : 'applicationid',
			dataIndex : 'applicationid',
			width : 300,
			sortable : true,
			hidden : true
		}, {
			header : '事项名称',
			dataIndex : 'sxmc',
			width : 200,
			sortable : true
		}, {
			header : '业务编号',
			dataIndex : 'ywbh',
			width : 200,
			sortable : true
		},{
			header:'项目名称',
			dataIndex:'xmmc',
			width:200
		
		},{
			header:'部门组织机构代码',
			dataIndex:'department',
			width:200
		}, {
			header :'申请批示状态',
			dataIndex : 'status',
			width : 200,
			sortable : true
		}
		
		]);
		
	
	var store = new Ext.data.Store({
		proxy : proxy,
		reader : new Ext.data.JsonReader( {
			totalProperty : 'totalProperty',
			root : 'root'
		}, [ {
			name : 'uuid'
		}, {
			name : 'sxmc'
		}, {
			name : 'ywbh'
		}, {
			name : 'xmmc'
		}, {
			name : 'department'
		}, {
			name : 'status'
		},{
			name : 'applicationid'
		}])
	});
	store.load();
	
	//面板
	var grid= new Ext.grid.GridPanel({
		title:'审批数据详细信息列表显示',
		id:'gridbd',
		autoHeight: true,
        bodyStyle:'width:100%',   
		loadMask:true,
		renderTo: 'permissionDetail',
		store:store,
		cm:cm
	});
	
	
	
	//列名称
	var hproxy = new Ext.data.HttpProxy({url:'XZSPAction_findHandlenodeByPerid.action?permissionid='+permissionid});

	var hcm = new Ext.grid.ColumnModel([{
			header : 'uuid',
			dataIndex : 'uuid',
			width : 100,
			sortable : true,
			hidden : true
		},{
			header : 'permissionid',
			dataIndex : 'permissionid',
			width : 300,
			sortable : true,
			hidden : true
		}, {
			header : '特别程序结果',
			dataIndex : 'specialresult',
			width : 200,
			sortable : true
		}, {
			header : '结果产生时间',
			dataIndex : 'specialresultdate',
			width : 200,
			sortable : true
		},{
			header:'结束时间',
			dataIndex:'specialenddate',
			width:200
		
		},{
			header:'收费金额',
			dataIndex:'specialpay',
			width:200
		}, {
			header :'部门组织机构代码',
			dataIndex : 'department',
			width : 300,
			sortable : true
		}
		
	]);
		
	
	var hstore = new Ext.data.Store({
		proxy : hproxy,
		reader : new Ext.data.JsonReader( {
			totalProperty : 'totalProperty',
			root : 'root'
		}, [ {
			name : 'uuid'
		}, {
			name : 'specialresult'
		}, {
			name : 'specialresultdate'
		}, {
			name : 'specialenddate'
		}, {
			name : 'specialpay'
		}, {
			name : 'department'
		},{
			name : 'permissionid'
		}])
	});
	hstore.load();
	
	//面板
	var hgrid= new Ext.grid.GridPanel({
		title:'特别程序处理环节详细信息列表显示',
		id:'hgridbd',
		autoHeight: true,
        bodyStyle:'width:100%',   
		loadMask:true,
		renderTo: 'handlenodeDetail',
		store:hstore,
		cm:hcm
	});
	
	//列名称
	var aproxy = new Ext.data.HttpProxy({url:'XZSPAction_findApplynodeByPerid.action?permissionid='+permissionid});

	var acm = new Ext.grid.ColumnModel([{
			header : 'uuid',
			dataIndex : 'uuid',
			width : 100,
			sortable : true,
			hidden : true
		},{
			header : 'permissionid',
			dataIndex : 'permissionid',
			width : 300,
			sortable : true,
			hidden : true
		},{
			header : '特别程序类型',
			dataIndex : 'specialtype',
			width : 150,
			sortable : true
		}, {
			header : '名称',
			dataIndex : 'specialname',
			width : 150,
			sortable : true
		}, {
			header : '开始时间',
			dataIndex : 'specialstartdate',
			width : 150,
			sortable : true
		},{
			header:'批准人',
			dataIndex:'specialuser',
			width:100,
			sortable : true		
		},{
			header:'批准人电话号码',
			dataIndex:'specialusertel',
			width:150
		}, {
			header :'批准人手机',
			dataIndex : 'specialuserphone',
			width : 150,
			sortable : true
		}, {
			header :'启动原因',
			dataIndex : 'specialidea',
			width : 100,
			sortable : true
		}, {
			header :'申请内容',
			dataIndex : 'specialcontent',
			width : 200,
			sortable : true
		}, {
			header :'时限',
			dataIndex : 'speciallimit',
			width : 70,
			sortable : true
		}, {
			header :'时限单位',
			dataIndex : 'specialunit',
			width : 100,
			sortable : true
		}, {
			header :'部门组织机构代码',
			dataIndex : 'department',
			width : 200,
			sortable : true
		}
		
		]);
		
	
	var astore = new Ext.data.Store({
		proxy : aproxy,
		reader : new Ext.data.JsonReader( {
			totalProperty : 'totalProperty',
			root : 'root'
		}, [ {
			name : 'uuid'
		}, {
			name : 'specialtype'
		}, {
			name : 'specialname'
		}, {
			name : 'specialstartdate'
		}, {
			name : 'specialuser'
		}, {
			name : 'specialusertel'
		}, {
			name : 'specialuserphone'
		}, {
			name : 'specialidea'
		}, {
			name : 'specialcontent'
		}, {
			name : 'speciallimit'
		}, {
			name : 'specialunit'
		},{
			name : 'department'
		},{
			name : 'permissionid'
		}])
	});
	astore.load();
	
	//面板
	var agrid= new Ext.grid.GridPanel({
		title:'特别程序申请环节详细信息列表显示',
		id:'agridbd',
		autoHeight: true,
        bodyStyle:'width:100%',   
		loadMask:true,
		renderTo: 'applynodeDetail',
		store:astore,
		cm:acm
	});
	
	//列名称
	var acproxy = new Ext.data.HttpProxy({url:'XZSPAction_findAcceptnodeByPerid.action?permissionid='+permissionid});

	var accm = new Ext.grid.ColumnModel([{
			header : 'uuid',
			dataIndex : 'uuid',
			width : 100,
			sortable : true,
			hidden : true
		},{
			header : 'permissionid',
			dataIndex : 'permissionid',
			width : 300,
			sortable : true,
			hidden : true
		},{
			header : '补交受理人',
			dataIndex : 'handler',
			width : 150,
			sortable : true
		},{
			header : '补交受理时间',
			dataIndex : 'handlerdate',
			width : 150,
			sortable : true
		},{
			header : '补交受理地点',
			dataIndex : 'handleraddr',
			width : 150,
			sortable : true
		},{
			header:'补交受理清单',
			dataIndex:'handlerlist',
			width:100,
			sortable : true
		},{
			header :'部门组织机构代码',
			dataIndex : 'department',
			width : 200,
			sortable : true
		}
	]);
		
	
	var acstore = new Ext.data.Store({
		proxy : acproxy,
		reader : new Ext.data.JsonReader( {
			totalProperty : 'totalProperty',
			root : 'root'
		}, [ {
			name : 'uuid'
		}, {
			name : 'handler'
		}, {
			name : 'handlerdate'
		}, {
			name : 'handleraddr'
		}, {
			name : 'handlerlist'
		}, {
			name : 'department'
		},{
			name : 'permissionid'
		}])
	});
	acstore.load();
	
	//面板
	var acgrid= new Ext.grid.GridPanel({
		title:'补交受理环节详细信息列表显示',
		id:'acgridbd',
		autoHeight: true,
        bodyStyle:'width:100%',   
		loadMask:true,
		renderTo: 'acceptnodeDetail',
		store:acstore,
		cm:accm
	});
	
	//列名称
	var noproxy = new Ext.data.HttpProxy({url:'XZSPAction_findNotifynodeByPerid.action?permissionid='+permissionid});

	var nocm = new Ext.grid.ColumnModel([{
			header : 'uuid',
			dataIndex : 'uuid',
			width : 100,
			sortable : true,
			hidden : true
		},{
			header : 'permissionid',
			dataIndex : 'permissionid',
			width : 300,
			sortable : true,
			hidden : true
		},{
			header : '补交告知发出人',
			dataIndex : 'nodeactor',
			width : 150,
			sortable : true
		},{
			header : '补交告知发出时间',
			dataIndex : 'complementdate',
			width : 150,
			sortable : true
		},{
			header : '补交告知原因',
			dataIndex : 'complementidea',
			width : 150,
			sortable : true
		},{
			header:'补交告知清单',
			dataIndex:'complementlist',
			width:100,
			sortable : true
		},{
			header :'部门组织机构代码',
			dataIndex : 'department',
			width : 200,
			sortable : true
		}
	]);
		
	
	var nostore = new Ext.data.Store({
		proxy : noproxy,
		reader : new Ext.data.JsonReader( {
			totalProperty : 'totalProperty',
			root : 'root'
		}, [ {
			name : 'uuid'
		}, {
			name : 'nodeactor'
		}, {
			name : 'complementdate'
		}, {
			name : 'complementidea'
		}, {
			name : 'complementlist'
		}, {
			name : 'department'
		},{
			name : 'permissionid'
		}])
	});
	nostore.load();
	
	//面板
	var nogrid= new Ext.grid.GridPanel({
		title:'补交告知环节详细信息列表显示',
		id:'nogridbd',
		autoHeight: true,
        bodyStyle:'width:100%',   
		loadMask:true,
		renderTo: 'notifynodeDetail',
		store:nostore,
		cm:nocm
	});
	
	//列名称
	var nnproxy = new Ext.data.HttpProxy({url:'XZSPAction_findNodeByPerid.action?permissionid='+permissionid});

	var nncm = new Ext.grid.ColumnModel([{
			header : 'uuid',
			dataIndex : 'uuid',
			width : 100,
			sortable : true,
			hidden : true
		},{
			header : 'permissionid',
			dataIndex : 'permissionid',
			width : 300,
			sortable : true,
			hidden : true
		},{
			header : '环节ID',
			dataIndex : 'nodeid',
			width : 100,
			sortable : true
		},{
			header : '环节名称',
			dataIndex : 'nodename',
			width : 100,
			sortable : true
		},{
			header : '环节处理人',
			dataIndex : 'nodeactor',
			width : 120,
			sortable : true
		},{
			header:'受理单位办理人工号',
			dataIndex:'nodeactorgh',
			width:100,
			sortable : true
		},{
			header : '环节处理人职务名称',
			dataIndex : 'nodeactorzwmc',
			width : 150,
			sortable : true
		},{
			header:'环节处理人职务代码',
			dataIndex:'nodeactorzwdm',
			width:100,
			sortable : true
		},{
			header : '处理时间',
			dataIndex : 'handlerdate',
			width : 150,
			sortable : true
		},{
			header : '处理意见',
			dataIndex : 'handleridea',
			width : 150,
			sortable : true
		},{
			header :'部门组织机构代码',
			dataIndex : 'department',
			width : 200,
			sortable : true
		}
	]);
		
	
	var nnstore = new Ext.data.Store({
		proxy : nnproxy,
		reader : new Ext.data.JsonReader( {
			totalProperty : 'totalProperty',
			root : 'root'
		}, [ {
			name : 'uuid'
		}, {
			name : 'nodeid'
		}, {
			name : 'nodename'
		}, {
			name : 'nodeactor'
		}, {
			name : 'nodeactorgh'
		},{
			name : 'nodeactorzwmc'
		}, {
			name : 'nodeactorzwdm'
		}, {
			name : 'handlerdate'
		}, {
			name : 'handleridea'
		}, {
			name : 'department'
		},{
			name : 'permissionid'
		}])
	});
	nnstore.load();
	
	//面板
	var nngrid= new Ext.grid.GridPanel({
		title:'审批材料详细信息列表显示',
		id:'nngridbd',
		autoHeight: true,
        bodyStyle:'width:100%',   
		loadMask:true,
		renderTo: 'nodeDetail',
		store:nnstore,
		cm:nncm
	});
	
	//列名称
	var mproxy = new Ext.data.HttpProxy({url:'XZSPAction_findMaterialByPerid.action?permissionid='+permissionid});

	var mcm = new Ext.grid.ColumnModel([{
			header : 'uuid',
			dataIndex : 'uuid',
			width : 100,
			sortable : true,
			hidden : true
		},{
			header : 'permissionid',
			dataIndex : 'permissionid',
			width : 300,
			sortable : true,
			hidden : true
		},{
			header : '材料实例ID',
			dataIndex : 'id',
			width : 100,
			sortable : true
		},{
			header : '材料ID',
			dataIndex : 'mlid',
			width : 100,
			sortable : true
		},{
			header : '材料名称',
			dataIndex : 'mlname',
			width : 100,
			sortable : true
		},{
			header : '电子化要求',
			dataIndex : 'selecttype',
			width : 100,
			sortable : true
		},{
			header : '上传文件返回ID',
			dataIndex : 'fid',
			width : 100,
			sortable : true
		},{
			header : '文件存储路径',
			dataIndex : 'fpath',
			width : 100,
			sortable : true
		},{
			header : '上传材料名称',
			dataIndex : 'fname',
			width : 120,
			sortable : true
		},{
			header:'材料状态',
			dataIndex:'status',
			width:100,
			sortable : true
		},{
			header : '原件份数',
			dataIndex : 'orinum',
			width : 150,
			sortable : true
		},{
			header:'复印件份数',
			dataIndex:'copynum',
			width:100,
			sortable : true
		},{
			header : '是否必要',
			dataIndex : 'isneed',
			width : 150,
			sortable : true
		},{
			header : '是否是基本信息填报表单',
			dataIndex : 'baseinfo',
			width : 150,
			sortable : true
		},{
			header :'补交意见',
			dataIndex : 'adjustment',
			width : 200,
			sortable : true
		}
	]);
	
	var mstore = new Ext.data.Store({
		proxy : mproxy,
		reader : new Ext.data.JsonReader( {
			totalProperty : 'totalProperty',
			root : 'root'
		}, [ {
			name : 'uuid'
		}, {
			name : 'id'
		}, {
			name : 'mlid'
		}, {
			name : 'mlname'
		}, {
			name : 'selecttype'
		},{
			name : 'fid'
		}, {
			name : 'fpath'
		}, {
			name : 'fname'
		}, {
			name : 'status'
		}, {
			name : 'orinum'
		},{
			name : 'copynum'
		}, {
			name : 'isneed'
		}, {
			name : 'baseinfo'
		}, {
			name : 'adjustment'
		},{
			name : 'permissionid'
		}])
	});
	mstore.load();
	
	//面板
	var mgrid= new Ext.grid.GridPanel({
		title:'审批环节详细信息列表显示',
		id:'mgridbd',
		autoHeight: true,
        bodyStyle:'width:100%',   
		loadMask:true,
		renderTo: 'materialDetail',
		store:mstore,
		cm:mcm
	});	

});