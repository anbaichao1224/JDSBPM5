
Ext.BLANK_IMAGE_URL='/usm/resources/images/default/s.gif';  
Ext.onReady(function(){
    Ext.QuickTips.init();

	var root = new Ext.tree.AsyncTreeNode({
	    text: '组织机构',
	    leaf : false,
	    cls : 'folder',
	    draggable:false,
	    id:'toproot'
	    });
	    
	    
	     var Tree = Ext.tree;
	    var treePanel = new Tree.TreePanel({
	  
	    animate:true,
	    enableDD:false,
	    loader: new Tree.TreeLoader({dataUrl:'/usm/orgtreeJson.do'}),
	    lines: true,
	    selModel: new Ext.tree.MultiSelectionModel(),
	    containerScroll: true,
	    autoHeight:true,
	    rootVisible:false
	    });
	    
	    var treePanel= new Ext.tree.TreePanel({
                id:'forum-tree',
                region:'west',
                title:'人员管理中心',
                split:true,
                width: 225,
                minSize: 175,
                maxSize: 400,
                collapsible: true,
                margins:'0 0 5 5',
				      animCollapse:false,
				      animate: true,
				      collapseMode:'mini',
				       rootVisible:false,
                loader: new Ext.tree.TreeLoader({
								url: '/usm/orgtreeJson.do'
		}),
                rootVisible:true,
                lines:true,
                autoScroll:true,
                root:root
            });
     var viewport = new Ext.Viewport({
        layout:'border',
        items:[
          
           treePanel,
             new Ext.Panel({
             
                            region:'center',
                            id:'topic-grid',
                            html:'<iframe id="views" frameborder="0" src="person/personqueryListgrid.jsp" height=100% width=100%></iframe>'
                           
             })
         ]
    });	 
    var tree =treePanel;
    tree.setRootNode(root);
	tree.render();
    var sm = tree.getSelectionModel();
	tree.on('click', function(node){
	   if(node.leaf){
	   
       Ext.get('views').dom.src='/usm/rightTemplate.do?template=personright&sysid='+sysid+'&nodeid='+node.id;
	   }
     });
    tree.on('beforeload', function(node){
	   tree.loader.url = '/usm/orgtreeJson.do?childOrgId='+node.id+'';
	});

   
});


//刷新树
function tree_refresh(){
var tree = Ext.getCmp('forum-tree');
tree.body.mask('加载中...','x-mask-loading');
tree.root.reload();
tree.root.collapse(true,true);
setTimeout(function(){
   tree.body.unmask();
   tree.root.expand(false,false);
},100);
};

