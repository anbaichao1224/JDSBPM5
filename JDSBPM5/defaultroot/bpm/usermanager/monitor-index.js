Ext.BLANK_IMAGE_URL='/usm/resources/images/default/s.gif';
Ext.onReady(function(){

    Ext.QuickTips.init();
    var tree=new Ext.tree.TreePanel({
        id:'index-tree',
        region:'west',
        split:true,
        width: 200,
        height:800,
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
              text: '网络服务器'
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
			tree.body.mask('加载中...','x-mask-loading');
			tree.getRootNode().loaded = false;///因数据源改变，设置loaded属性为false，从而让树的内容更新
			setTimeout(function(){
			   tree.body.unmask();
			   tree.root.expand(false,true);
			},100);
		 Ext.get("rolling7").dom.src="/bpm/usermanager/allcomputer.jsp";
		}else{
		 Ext.get("rolling7").dom.src=/bpm/usermanager/computerInfo.jsp?nodeid="+node.id;
		}
	});  
	function handleActivate(tab){
	
	}
	
	var pan = new Ext.Panel({
	        	region:'center',
	            html:'<iframe name="rolling7" id="rolling7" scrolling="yes" frameborder="0" src="<%=contextpath%>bpm/usermanager/allcomputer.jsp" height=100% width=100%></iframe>'
	});
	var nav = new Ext.Panel({
	    title       : '网络服务器',
	    region      : 'west',
	    split       : true,
	    width       : 200,
	    collapsible : true,
	    margins     : '3 0 3 3',
	    cmargins    : '3 3 3 3',
	    items:[
	    	tree
	    ]
	});

	function getcomments (){
	var commentslist={
	    closable : true,
	    width    : 600,
	    height   : 350,
	    border : true,
	    plain    : true,
	    layout   : 'border',
	    items    : [nav,pan]
	
	};
	return commentslist;
	
	}
	function EVAL.getPanel(){
	
	return getcomments();
	
	}
   
});

