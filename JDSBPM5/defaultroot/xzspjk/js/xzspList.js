
Ext.onReady(function(){

	//复选框
	var sm = new Ext.grid.CheckboxSelectionModel({
		dataIndex:'messageid' 
	});
				
	//列名称
	var proxy = new Ext.data.HttpProxy({url:'XZSPAction_findAllXZSP.action'});
	
	var ttbar = new Ext.Toolbar({
			items : ['-']
	});
	
	var cm = new Ext.grid.ColumnModel( [ sm, {
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
			width : 300,
			sortable : true
		}, {
			header : '业务编号',
			dataIndex : 'ywbh',
			width : 200,
			sortable : true
		},{
			header:'申请人',
			dataIndex:'sqr',
			width:100
		
		},{
			header:'申请单位',
			dataIndex:'sqdw',
			width:100
		}, {
			header :'操作',
			dataIndex : 'cz',
			width : 200,
			sortable : true,
			renderer:function(value){
				return String.format("<a href=\"javascript:void(0)\" onclick=\"window.top.xzspstartProcess('{0}')\">启动流程</a>|<a href=\"/xzspjk/jsp/perDetail.jsp?permissionid='{1}'\")>查看</a>",value,value);
			}
		}
		
	]);
		
	var pgsize = 20;
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
			name : 'sqr'
		}, {
			name : 'sqdw'
		}, {
			name : 'cz'
		},{
			name: 'applicationid'
		}])
	});
	store.load({params:{start:0,limit:pgsize}});
	
	//面板
	var grid= new Ext.grid.GridPanel({
		title:'行政审批列表',
		id:'gridbd',
		autoHeight: true,
        bodyStyle:'width:100%',   
		loadMask:true,
		renderTo: 'xzspList',
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
});


function deleteFile(grid){
	var selections=grid.getSelections();//获得所有被选中的组件集合
	if (selections.length==0){
      	alert('请选择需要删除的文件');
		return;
	}

	//根据permissionid删除
	var delAllList=new Array();//数组
	for(var i=0;i<selections.length;i++){
		if (selections[i].get('applicationid').length>0 ){     
			delAllList[delAllList.length]= selections[i].get('applicationid');    
		}else{
			alert("error");
		}
	}
	var fileList=delAllList.join();//数组转换成jason字符串
    Ext.Ajax.request({
		url:'XZSPAction_deleteByUuid.action',
		params:{applicationidList:fileList},
		method:'post',
		success:function(){
			alert(o.responseText);
			grid.getStore().reload();
			alert("删除成功");
		}
	});
}


