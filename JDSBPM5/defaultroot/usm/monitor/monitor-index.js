
Ext.BLANK_IMAGE_URL='/usm/resources/images/default/s.gif';
Ext.onReady(function(){
    Ext.QuickTips.init();
	    var tree=new Ext.tree.TreePanel({
	        id:'index-tree',
	        region:'west',
	        split:true,
	        width: 200,
	        height:700,
	        minSize: 175,
	        maxSize: 400,
	        collapsible: true,
	        margins:'0 0 0 5',
			animCollapse:true,
			animate: true,
			collapseMode:'mini', 
	        loader: new Ext.tree.TreeLoader({
			      url: 'indexmanagertree.action'
			}),
	        rootVisible:true,
	        lines:true,
	        autoScroll:true,
	        root: new Ext.tree.AsyncTreeNode({
	        	  id:'root',
	        	  draggable:false,
	        	  icon:'/usm/img/module.gif',
	              text: '应用服务'
	        })
	    });
		var sm = tree.getSelectionModel();
		sm.on('beforeselect', function(sm, node){
		  return node.isLeaf();
		});
		tree.on('click', function(node){
			if(node.id=='root'){
				var tree = Ext.getCmp("index-tree");   
				tree.getLoader().url="indexmanagertree.action";
				tree.getRootNode().loaded = false;///因数据源改变，设置loaded属性为false，从而让树的内容更新
				setTimeout(function(){
				   tree.body.unmask();
				   tree.root.expand(false,true);
				},100);
			    Ext.get("appMonitor").dom.src="allcomputer.jsp";
			}else{
			    Ext.get("appMonitor").dom.src="computerInfo.jsp?nodeid="+node.id;
			}
		});
     var viewport = new Ext.Viewport({
        layout:'border',
        items:[
           new Ext.BoxComponent({ 
                region:'north',
                el: 'header',
                height:0
            }),
           tree,
           new Ext.Panel({
		        id:'content',
	        	region:'center',
	            html:'<iframe name="appMonitor" id="appMonitor" scrolling="yes" frameborder="0" src="allcomputer.jsp" height=100% width=100%></iframe>'
	       })
         ]
    });
	 
});

