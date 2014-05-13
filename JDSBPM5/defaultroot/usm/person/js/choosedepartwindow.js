
    var viewport = new Ext.Viewport({
        layout:'fit',
        items:[
           
            new Ext.tree.TreePanel({
                id:'forum-tree',
                region:'west',
                title:'人员管理中心',
                split:true,
                width: 150,
                minSize: 175,
                maxSize: 200,
                animCollapse:false,  
				animate: true,
				collapseMode:'mini',
                loader: new Ext.tree.TreeLoader({
							url: '/usm/orgtreeJson.do'
		}),
                rootVisible:true,
                lines:true,
                autoScroll:true,
                root: new Ext.tree.AsyncTreeNode({
                					id:'root',
                					draggable:false,
                          text: '组织机构'
                      })
                      
            })
         ]
    });


 	
	 var tree = Ext.getCmp('forum-tree');

    var sm = tree.getSelectionModel();
    sm.on('beforeselect', function(sm, node){
          return node.isLeaf();
    });
  
 
   tree.on('click', function(node){
	   if(node.id=='root'){
	        nodeid=node.id;
	   		tree_refresh();
	   		Ext.getCmp('personmessage').enable();
	   		Ext.get('views').dom.src= 'person-index.jsp';
	   }else{
      if(node.attributes.treetype=='fathernodes'){
          nodeid=node.id;
	      Ext.getCmp('main-view').setTitle(node.text);
	      Ext.get('views').dom.src= '/usm/departInfo.do?parentorgid='+node.id;
	      Ext.getCmp('personmessage').disable();
	    //parent.reload();
	    
      }else{
      	  nodeid=node.id;
      	  //alert(nodeid);
	      Ext.getCmp('main-view').setTitle(node.text);
	      Ext.get('views').dom.src= '/usm/departInfo.do?parentorgid='+node.id;
	      Ext.getCmp('personmessage').disable();
		

      }
      	  

      }	
       
    });
   






