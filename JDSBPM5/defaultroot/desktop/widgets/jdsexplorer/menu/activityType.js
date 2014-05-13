Ext.apply(JDS.exp.rightMenu.groups, {
    activityType: ["open", "createActivity","attrActivity"],
    activityType_b: ["open", "attrActivity","upload"]
})
Ext.apply(JDS.exp.mainMenu.items, {
	createActivity: {
        id: "createProc",
        text: "新建",
        cmdName: "createProc",
		disabled:true
    },
	attrActivity: {
        id: "attrProc",
        text: "活动属性",
        cmdName: "attrProc",
		disabled:true
    }
});

