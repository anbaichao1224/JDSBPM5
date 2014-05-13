Ext.apply(JDS.exp.rightMenu.groups, {
    activityFormNodeType: ["activityForm_edit","activityForm_binding","processForm_main","filedownload","fileDelete"],
    activityFormNodeType_b: ["activityForm_edit", "activityForm_binding"]
})
Ext.apply(JDS.exp.mainMenu.items, {
    activityForm_edit: {
        id: "activityForm_edit",
        text: "编辑表单",
        cmdName: "activityFormNodeType_open"
    },
	activityForm_binding: {
        id: "activityForm_binding",
        text: "绑定表单",
        cmdName: "activityForm_binding"
    }	
})

Ext.apply(JDS.exp.actions.fns, {
	
    activityFormNodeType_open: function(){
		JDS.ajax.exp.Connection.getFileName(this[0].data.path,'edit');
    },
    activityForm_binding: function(){
		JDS.ajax.exp.Connection.getFileName(this[0].data.path,'binding');
    }
});