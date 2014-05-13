Ext.apply(JDS.exp.rightMenu.groups, {
    SystemModuleNodeType: ["sysModle_openOredit"],
    SystemModuleNodeType_b: ["sysModle_openOredit"]
})
Ext.apply(JDS.exp.mainMenu.items, {
    sysModle_openOredit: {
        id: "sysModle_openOredit",
        text: "打开或配置",
        cmdName: "sysModle_openOredit"
    },
    sysModle_att: {
        id: "sysModle_att",
        text: "系统模块属性",
        cmdName: "sysModle_att"
    }

})
var sysModle_checkEditor=function(url){
		if(url.indexOf('indexmanager.action') != -1)
			return true;
		return false;
}
Ext.apply(JDS.exp.actions.fns, {
	
    sysModle_openOredit: function(){
		var url = this[0].data.url;
		if(sysModle_checkEditor(url)){
			var text = this[0].data.name;
			openFormWin(url,text);			
		}else{
			openUrlWin(url,this[0].data.text,'')
		}
        
    },
    sysModle_att: function(){
    
    
    }
});
