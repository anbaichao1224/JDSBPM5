Ext.BLANK_IMAGE_URL='/usm/resources/images/default/s.gif';
Ext.onReady(function(){
	
	var tree = new Ext.tree.TreePanel({
		title : '<center>承办、批办菜单</center>',
		layout : 'fit',
		border : false,
		rootVisible : false,
		root : new Ext.tree.AsyncTreeNode({
			children : [{
					text : '在办承办',
					url : 'cpswbl/model/zbcb.jsp',
					leaf :	true
				},{
					text : '办结承办',
					url : 'cpswbl/model/bjcb.jsp',
					leaf : true
				},{
					text : '在办批办',
					url : 'cpswbl/model/zbpb.jsp',
					leaf :	true
				},{
					text : '办结批办',
					url : 'cpswbl/model/bjpb.jsp',
					leaf : true
				}]
			})
	});
	
	var tsm = tree.getSelectionModel();
   	tsm.on('selectionchange', function(sm, node){
    	if(node!=null){
    		 Ext.get('cpswbl').dom.src= node.attributes.url;
    	}
    });
	
	var panel = new Ext.Panel({
		title : '管理员操作菜单',
		region : 'west',
		split : true,
		collapsible : true,
		width : 200,
		layout : 'accordion',
		items : [tree]	
	});
	
	var cpanel = new Ext.Panel({
		region : 'center',
		layout : 'fit',
		border : false,
		html : '<iframe frameborder="no" id="cpswbl" style="border:1px marginwidth="0"  marginheight="0" id="view" src="cpswbl/model/zbcb.jsp" width="100%" height="100%"></iframe>'
	})
	
	
	var viewport = new Ext.Viewport({
		layout : 'border',
		border : false,
		items : [panel,cpanel]
	});
});