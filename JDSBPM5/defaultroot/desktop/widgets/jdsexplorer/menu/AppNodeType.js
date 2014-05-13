Ext.apply(JDS.exp.rightMenu.groups, {
    AppNodeType: ["open", "app_detail","app_modify"],
    AppNodeType_b: ["app_detail","app_modify","module_add"]
});
Ext.apply(JDS.exp.mainMenu.items, {
   
	app_detail: {
        id: "app_detail",
        text: "应用菜单信息",
        cmdName: "app_detail"
    },
    app_modify: {
    	id: "app_modify",
    	text: "应用菜单编辑",
    	cmdName: "app_modify"
    },
     app_sort: {
    	id: "app_sort",
    	text: "应用菜单排序",
    	cmdName: "app_sort"
    },
    module_add:{
    	id: "module_add",
    	text: "添加模块",
    	cmdName: "module_add"
    }
});
Ext.apply(JDS.exp.actions.fns , {
	app_detail:function(){ 
		if(!this[0].data == "") {
			params = {
				template:'application',
        		sysid:this[0].data.uuid
        	}
		}else{
			params = {
				template:'application',
				sysid:this[0].node.attributes.uuid
			}
		}
		openUrlWin('usm/resources/applicationTemplate.jsp','菜单模块列表',params);		
	},
	app_modify:function(){
		if(!this[0].data == "") {
			params = {
				width:360,
				height:370,
        		appcatalogid:this[0].data.uuid
        	}
		}else{
			params = {
				width:360,
				height:370,
				appcatalogid:this[0].node.attributes.uuid
			}
		}
		openUrlWin('usm/applicationInfo.do','菜单编辑',params);
	},
	app_sort:function(){
		openUrlWin('usm/applicationSort.do','菜单排序','');
	},
	module_add:function(){
		openUrlWin('usm/resources/resourcesInsert.jsp','添加模块','');
	}		
});

