Ext.apply(JDS.exp.rightMenu.groups, {
    personNodeType: ["person_detail"],
    personNodeType_b: ["open"]
})
Ext.apply(JDS.exp.mainMenu.items, {
    person_detail: {
    	id: "person_detail",
    	text: "详细信息",
    	cmdName: "personNodeType_open"
    }
	
});

Ext.apply(JDS.exp.actions.fns, {
	personNodeType_open:function(){
		params = {
        	width:640,
        	height:420,
        	personid:this[0].data.uuid        	
        }        
        openUrlWin('/usm/getPersonDetail.do','人员详细信息',params);
	}		
});
