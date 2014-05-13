Ext.apply(JDS.exp.rightMenu.groups, {
    TemplateFormNodeType: ["tempForm_edit", "tempForm_binding","filedownload"],
    TemplateFormNodeType_b: ["open", "attrActivity"]
})
Ext.apply(JDS.exp.mainMenu.items, {
    tempForm_edit: {
        id: "tempForm_edit",
        text: "编辑表单",
        cmdName: "tempForm_edit"
    },
	tempForm_binding: {
        id: "tempForm_binding",
        text: "绑定表单",
        cmdName: "tempForm_binding"
    }
	
})

Ext.apply(JDS.exp.actions.fns, {
	
    tempForm_edit: function(){
		JDS.ajax.exp.Connection.getFileName(this[0].data.path,'edit');
    },
    tempForm_binding: function(){
		JDS.ajax.exp.Connection.getFileName(this[0].data.path,'binding');
    }
});

