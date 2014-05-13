Ext.BLANK_IMAGE_URL='/usm/resources/images/default/s.gif';
Ext.onReady(function(){
	Ext.QuickTips.init();
	var tree = new Ext.tree.TreePanel({
		title : '<center>权限申请</center>',
		layout : 'fit',
		border : false,
		rootVisible : false,
		root : new Ext.tree.AsyncTreeNode({
			children : [{
					text : '待办申请授权列表',
					url : 'model/roledb.jsp',
					leaf :	true
				},{
					text : '已办申请授权列表',
					url : 'model/roleyb.jsp',
					leaf : true
				}]
			})
	});
	
	var tsm = tree.getSelectionModel();
   	tsm.on('selectionchange', function(sm, node){
    	if(node!=null){
    		 Ext.get('secure').dom.src= node.attributes.url;
    	}
    });
	
	var panel = new Ext.Panel({
		title : '权限申请操作菜单',
		region : 'west',
		split : true,
		collapsible : true,
		width : 200,
		layout : 'accordion',
		items : [tree]	
	});
	
	
	var cpanel = new Ext.Panel({
		region: 'center',
		layout : 'fit',
		border : false,
		html : '<iframe frameborder="no" id="secure" style="border:1px marginwidth="0"  marginheight="0" id="view" src="model/roledb.jsp" width="100%" height="100%"></iframe>'
	});
	
	var viewport = new Ext.Viewport({
		id:'viewport',
        layout:'border',
        margin : '5 0 5 0',
        items:[
            new Ext.BoxComponent({ // raw
                region:'north',
                el: 'header',
                height:32
            }),panel,cpanel
         ]
	});
});