
// 人员窗体
//function createPersonPositionWindow(positionid, positionname) {
Ext.BLANK_IMAGE_URL = '/js/ext/resources/images/default/s.gif';
Ext.onReady(function(){
	var txtCheckValue;
	var txtCheckName;
	var cmodel="cascade";
	var root = new Ext.tree.AsyncTreeNode( {
		text :'组织机构',
		leaf :false,
		cls :'folder',
		draggable :false,
		id :'toproot',
		expanded:function(){
		
			alert("expand");
		}
	});
	
				var treePanel = new Ext.tree.TreePanel( {
					id :'forum-tree',
					region :'west',
					title :'',
					split :true,
					width :200,
					height :510,
					checkModel:cmodel,
					collapsible :true,
					margins :'0 0 5 5',
					animCollapse :false,
					animate :true,
					collapseMode :'mini',
					rootVisible :false,
					loader :new Ext.tree.TreeLoader( {
						url :'/category_getTree.do'
					}),
					rootVisible :true,
					lines :true,
					autoScroll :true,
					root :root

				})

	var viewport = new Ext.Viewport({
        layout:'border',
        items:[
            new Ext.BoxComponent({ // raw
                region:'north',
                el: 'treediv',
                height:0
            }),
           treePanel,
             new Ext.Panel({
             
                            region:'center',
                            id:'topic-grid',
                            html:'<iframe id="views" frameborder="0" src="/desktop/widgets/electronicfile/recordcategory/electroniclist.jsp" height=100% width=100%></iframe>'
                           
             })
         ]
    });
	var tree = treePanel;
	tree.setRootNode(root);
	tree.expandAll();
	tree.render();
	tree
			.on(
					'beforeload',
					function(node) {
						tree.loader.url = '/category_getTree.do?treename=person&choose=true&categoryId=' + node.id + '';
					});
	tree.on('click',function(node){
		if(node.leaf){
			Ext.get('views').dom.src= '/desktop/widgets/electronicfile/datalist/rolltree.jsp?rollid='+node.id;
		}else{
			//Ext.get('views').dom.src= '/desktop/widgets/electronicfile/recordcategory/categoryDetail.jsp?uuid='+node.id;
			Ext.get('views').dom.src= '/desktop/widgets/electronicfile/datalist/rolltree.jsp?cid='+node.id;
		}
	});
});

// 会议窗体
function createMettingWindow() {
		
		var root = new Ext.tree.AsyncTreeNode({
		    text: '会议列表',
		    leaf : false,
		    cls : 'folder',
		    draggable:false,
		    id:'toproot'
		    });
		    
		    
		     var Tree = Ext.tree;
		    var treePanel = new Tree.TreePanel({
		  
		    animate:true,
		    enableDD:false,
		    loader: new Tree.TreeLoader({dataUrl:''}),
		    lines: true,
		    selModel: new Ext.tree.MultiSelectionModel(),
		    containerScroll: true,
		    autoHeight:true,
		    rootVisible:false
		    });
		    
		    var treePanel= new Ext.tree.TreePanel({
	                id:'forum-tree',
	                region:'west',
	                title:'会议列表',
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
									url: '/metting_mettingTreeSuccess.action'
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
	                            html:'<iframe id="views" frameborder="0" src="/usm/person/personqueryListgrid.jsp" height=100% width=100%></iframe>'
	                           
	             })
	         ]
	    });	 
	    var tree =treePanel;
	    tree.setRootNode(root);
		tree.render();
	    var sm = tree.getSelectionModel();
		tree.on('click', function(node){
		   if(node.leaf){
		   
	       Ext.get('views').dom.src='/bpmmatter_matterInfoById.do?uuid='+node.id;
		   }
	     });
	    tree.on('beforeload', function(node){
	    	
		  tree.loader.url = 'metting_mettingTreeSuccess.do?childOrgId='+node.id+'';
		});

	   
}



// 待办列表

function createMettingLbWindow(sxstatus) {
	
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
	    loader: new Tree.TreeLoader({dataUrl:'/mettingLbTree.action?sxstatus='+sxstatus}),
	    lines: true,
	    selModel: new Ext.tree.MultiSelectionModel(),
	    containerScroll: true,
	    autoHeight:true,
	    rootVisible:false
	    });
	    
	    var treePanel= new Ext.tree.TreePanel({
                id:'forum-tree',
                region:'west',
                title:'会议列表',
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
								url: '/mettingLbTree.action?sxstatus='+sxstatus
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
                            html:'<iframe id="views" frameborder="0" src="/desktop/widgets/metting/mettingListgrid.jsp" height=100% width=100%></iframe>'
                           
             })
         ]
    });	 
    var tree =treePanel;
    tree.setRootNode(root);
	tree.render();
    var sm = tree.getSelectionModel();
    tree.on('contextmenu',rightMenu);
	tree.on('click', function(node){
		Ext.getCmp('topic-grid').show();
	   if(node.leaf){
	   
       Ext.get('views').dom.src='/bpmmatter_matterInfoById.do?uuid='+node.id+'&sxstatus='+sxstatus;
	   }
	  // alert("nodeid:"+node.parentNode.id);
	   if(node.parentNode!=null&&node.parentNode.id=='toproot'){
		   Ext.get('views').dom.src='/metting_getById.do?childOrgId='+node.id;
	   }
     });
    tree.on('beforeload', function(node){
	  tree.loader.url = 'mettingLbTree.do?childOrgId='+node.id+'&sxstatus='+sxstatus;
	});

   
}




// 生成节点的右键菜单
function rightMenu(node,event,sxstatus){
	var menu = new Ext.menu.Menu({
		  id:'righclick',
		  items:[{
	            id:"rMenu",
	            text:"删除",
	            handler:function(item){
	            	 Ext.Ajax.request({ 
						method:'get', 
						url:"matterinfo_matterDelete.action?delstatus=Y&uuid="+node.id, 
						success : function(result, action)
									{ 
										Ext.getCmp('forum-tree').root.reload();
									Ext.MessageBox.alert('消息', '成功!')
								  	}, 
					    failure : function(form, action) 
					    { Ext.MessageBox.alert('消息', '系统初始化失败!');} 
					}); 
					// left_tree_refresh();
	            }
	  }]
	  });
	
	if(node.leaf&&(sxstatus!='3'&&sxstatus!='5')){
		event.preventDefault();
		menu.showAt(event.getXY());
	}
}
 

function newceshi(){
	
	var fsf = new Ext.FormPanel({
		 	labelWidth: 90, 
			labelAlign: 'right',  
			fileUpload: true,  
			title: '',    
			buttonAlign:'center',    
			bodyStyle:'padding:0px 0px 0',   
			anchor:'100%',  
			frame:true,    
		    items: [{
		        collapsible: true,
		        checkboxToggle:false,
		        collapsed: true,
		        xtype:'fieldset',
		        title:'基本信息',
		        autoHeight:true,
		        collapsed:false,
		        width:810,
		        height:670,
		        checkboxName:'jiben',
		        layout:'column',
		        border:true,
		        labelSeparator:'：',
		        fileUpload:true,
		        html:'<iframe frameborder="0" src="/desktop/widgets/metting/addmatter.jsp" width="754" height="630"></iframe>'
		    }]
	});
	fsf.render(document.body);
}

function updateMatter(url){
	
	var win=new Ext.Window({
		 id:'updateMatterInfo',
	     width:790,
	     height:700,
	     title:'事项信息',
	     closable:true,
		 collapsible:true,
	     autoScroll:true,
	     bodyStyle:'padding:0 5px',
	     items:new Ext.Panel({
			        title: '',        
				  	height:620,
				  	width:755,
			        html: '<iframe frameborder="0" src="'+url+'" width="754" height="620"></iframe>'
	     })
	 });
	 win.show();
}
