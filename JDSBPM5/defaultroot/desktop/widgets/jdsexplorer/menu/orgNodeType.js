Ext.apply(JDS.exp.rightMenu.groups, {
    orgNodeType: ["open","org_list","org_listPerson", "org_modify"],
    orgNodeType_b: ["org_list","org_listPerson","person_add"]
})
Ext.apply(JDS.exp.mainMenu.items, {
    
    org_list:{
    	id: "org_list",
    	text: "子部门列表",
    	cmdName: "org_list"
    },
    org_listPerson:{
    	id: "org_listPerson",
    	text: "部门人员列表",
    	cmdName: "org_listPerson"
    },
    org_modify:{
    	id: "org_modify",
    	text: "详细信息",
    	cmdName: "org_modify"
    },
    person_add:{
    	id: "person_add",
    	text: "添加人员",
    	cmdName: "person_add"
    }	
});
Ext.apply(JDS.exp.actions.fns , {
	org_list:function(){
		if(!this[0].data == "") {
			params = {
        		orglevel:0,
        		width:780,
        		height:600,
        		parentorgid:this[0].data.uuid
        	} 
		}else{
			params = {
				orglevel:0,
				width:780,
				height:600,
        		parentorgid:this[0].node.attributes.uuid
			}
		}		           
        openUrlWin('/usm/depart/childrendepartListgrid.jsp','子部门列表',params);
	},
	org_listPerson:function(){
		if(!this[0].data == "") {
			params = {
				width:780,
				height:600,
        		orgid:this[0].data.uuid
        	} 
		}else{
			params = {
				width:780,
				height:600,
        		orgid:this[0].node.attributes.uuid
			}
		}           
        openUrlWin('/usm/person/childrenPersonListgrid.jsp','部门人员列表',params);
	},
	org_modify:function(){
		if(!this[0].data == "") {
			params = {
				width:760,
				height:320,
        		orgid:this[0].data.uuid
        	} 
		}else{
			params = {
				width:760,
				height:320,
        		orgid:this[0].node.attributes.uuid
			}
		}           
        openUrlWin('/usm/getDepartDetailInfo.do','部门详细信息',params);
	},
	person_add:function(){
		params = {
			width:770,
        	orgid:this[0].node.attributes.uuid
        }                   
        openUrlWin('/usm/person/personInsertwindow.jsp','添加人员',params);
	}		
});


