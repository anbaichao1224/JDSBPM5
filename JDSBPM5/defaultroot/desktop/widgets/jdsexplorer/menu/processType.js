Ext.apply(JDS.exp.rightMenu.groups, {
    processType: ["open", "startProc","monitorProc","editProc","attrProc"],
    processType_b: ["open", "startProc","createProc","monitorProc","editProc","attrProc","upload"]
})
Ext.apply(JDS.exp.mainMenu.items, {
    
	createProc: {
        id: "createProc",
        text: "新建",
        cmdName: "createProc"
    },
    startProc: {
        id: "startProc",
        text: "启动流程",
        cmdName: "startProc",
		disabled:true
    },
	monitorProc: {
        id: "monitorProc",
        text: "监控流程",
        cmdName: "monitorProc",
		disabled:true
    },
	editProc: {
        id: "editProc",
        text: "编辑流程",
        cmdName: "editProc",
		disabled:true
    },
	attrProc: {
        id: "attrProc",
        text: "流程属性",
        cmdName: "attrProc",
		disabled:true
    }
});



