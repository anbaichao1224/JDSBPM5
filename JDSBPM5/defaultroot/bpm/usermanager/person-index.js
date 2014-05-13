
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
	    loader: new Tree.TreeLoader({dataUrl:'/usm/orgtreeJson.do?name=person'}),
	    lines: true,
	    selModel: new Ext.tree.MultiSelectionModel(),
	    containerScroll: true,
	    autoHeight:true,
	    rootVisible:false
	    });
	    
	    var treePanel= new Ext.tree.TreePanel({
                id:'forum-tree',
                region:'west',
                title:'身份管理中心',
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
            new Ext.BoxComponent({ // raw
                region:'north',
                el: 'header',
                height:0
            }),
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
    tree.on('beforeload', function(node){
	   tree.loader.url = '/usm/orgtreeJson.do?name=person&childOrgId='+node.id+'';
	});
   
   tree.on('click', function(node){
	   if(node.id=='toproot' || node.id=='root'){
	        nodeid=node.id;
	   		tree_refresh();
	   		Ext.get('views').dom.src= 'person/personqueryListgrid.jsp?childOrgId='+nodeid+'';
	   }else{
	      if(node.attributes.uid=="person"+node.id ){
	          nodeid=node.id;
		      Ext.get('views').dom.src= 'getPersonDetail.do?personid='+node.id;	    
	      }else{
	      	 nodeid=node.id;
		     var title=node.attributes['cnName'];
			   	 	var personname = title;
					var charAt ='' ;
					for(var i = 0 ; i < personname.length ; i++)
					{
						var substr = personname.substring(i,i+1);
						var k = ' ';
						charAt = charAt+k+substr.charCodeAt();	
					}
					var ppname=encodeURIComponent(charAt);
					title=ppname;
		     Ext.get('views').dom.src='/usm/departInfo.do?cnname='+title+'&orgid='+node.attributes['id']+'&orglevel='+node.attributes['orglevel']+'&parentorgid='+node.id;
	      }
      }	
      
      if(node.attributes.checked==true)
      {
          nodeid=node.id;
	     // Ext.getCmp('main-view').setTitle(node.text);
	      Ext.get('views').dom.src= 'person/personDetailInfo.do?personid='+node.id;
      
      }
       
    });
    
    
     tree.on('checkchange', function(node, checked) {  
      if(checked){
          nodeid=node.id;
          Ext.get('views').dom.src= 'person/personDetailInfo.do?personid='+node.id;
      }
       
    }); 
});

