Ext.apply(JDS.exp.rightMenu.groups, {
    officeType: ["filedownload"],
    officeType_b: ["filedownload"]
})
Ext.apply(JDS.exp.mainMenu.items, {
   
	officeType_download: {
        id: "officeType_download",
        text: "下载",
        cmdName: "officeType_download"
    },
	officeType_delete:{
		id: "officeType_delete",
        text: "删除",
        cmdName: "officeType_delete"
	}
	
})

Ext.apply(JDS.exp.actions.fns, {
	
    officeType_download: function(){
		alert();
    }
	,
	officeType_delete:function(){
		alert();
	}
});
