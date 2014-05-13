Ext.apply(JDS.exp.rightMenu.groups, {
    SystemAppNodeType: ["open","syn"],
    SystemAppNodeType_b: ["open","syn"]
})
Ext.apply(JDS.exp.mainMenu.items, {
    open: {
        id: "open",
        text: "打开",
        cmdName: "open"
    },
	syn:{
		id: "syn",
        text: "同步",
        cmdName: "syn"
	}
	
})

Ext.apply(JDS.exp.actions.fns, {
	syn:function(){
		var id = '';
		if(this[0].data)
			id = this[0].data.path;
		else{
			id = this[0].node.id;
		}
		if(id.indexOf('workflow') != -1){
			JDS.ajax.exp.Connection.updateProcess('workflow',id);
		}
	}		
});