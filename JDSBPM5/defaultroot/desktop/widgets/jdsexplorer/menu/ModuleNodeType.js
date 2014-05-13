Ext.apply(JDS.exp.rightMenu.groups, {
    ModuleNodeType: ["module_detail"],
    ModuleNodeType_b: ["module_detail"]
});
Ext.apply(JDS.exp.mainMenu.items, {   
	module_detail: {
        id: "module_detail",
        text: "模块信息",
        cmdName: "ModuleNodeType_open"
    }	
});
Ext.apply(JDS.exp.actions.fns , {
	ModuleNodeType_open:function(){
		params = {
			width:380,     
			height:560,
        	moduleid:this[0].data.uuid
        };            
        openUrlWin('/usm/moduleInfo.do','模块详细信息',params);
	}		
});



