Ext.apply(JDS.exp.rightMenu.groups, {
    processFormNodeType: ["processForm_edit", "processForm_binding","processForm_main","filedownload","fileDelete"],
    processFormNodeType_b: ["open", "attrActivity"]
})
Ext.apply(JDS.exp.mainMenu.items, {
    processForm_edit: {
        id: "processForm_edit",
        text: "编辑表单",
        cmdName: "processFormNodeType_open"
    },
	processForm_binding: {
        id: "processForm_binding",
        text: "绑定表单",
        cmdName: "processForm_binding"
    },
	processForm_main: {
        id: "processForm_main",
        text: "设为主表单",
        cmdName: "processForm_main"
    },
	filedownload:{
		id: "filedownload",
        text: "下载",
        cmdName: "filedownload"
	}
})

Ext.apply(JDS.exp.actions.fns, {
	
    processFormNodeType_open: function(){
		JDS.ajax.exp.Connection.getFileName(this[0].data.path,'edit');
    },
    processForm_binding: function(){
		JDS.ajax.exp.Connection.getFileName(this[0].data.path,'binding');
    },
	processForm_main: function(){
		JDS.ajax.exp.Connection.setMainForm(this[0].data,this[0].data.name);
    },
	filedownload:function(){
		JDS.ajax.exp.Connection.downloadFile(this[0].data.path);
	}
});

