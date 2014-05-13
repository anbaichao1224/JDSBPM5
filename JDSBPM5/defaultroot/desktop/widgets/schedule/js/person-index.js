
Ext.BLANK_IMAGE_URL='/usm/resources/images/default/s.gif';
Ext.onReady(function(){
    Ext.QuickTips.init();
	    var root = new Ext.tree.AsyncTreeNode({
	    text: orgname,
	    leaf : false,
	    cls : 'folder',
	    draggable:false,
	    id:orgid
	    });
	    
	    
	    var Tree = Ext.tree;
	    var treePanel = new Tree.TreePanel({
	    animate:true,
	    enableDD:false,
	    loader: new Tree.TreeLoader({dataUrl:'/usm/rccx.do'}),
	    lines: true,
	    selModel: new Ext.tree.MultiSelectionModel(),
	    containerScroll: true,
	    autoHeight:true,
	    rootVisible:false
	    });
	    
	    var treePanel= new Ext.tree.TreePanel({
                id:'forum-tree',
                region:'west',
                title:'日程查询',
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
				url: '/usm/rccx.do'
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
                            html:'<iframe id="views" frameborder="0" src="/desktop/widgets/schedule/personqueryListgrid.jsp" height=100% width=100%></iframe>'
                           
             })
         ]
    });
	 
	 
    var tree =treePanel;
    tree.setRootNode(root);
	tree.render();
    tree.on('beforeload', function(node){
	   tree.loader.url = '/usm/rccx.do?name=person&childOrgId='+node.id+'';
	});
   
   tree.on('click', function(node){
	   if(node.id=='toproot' || node.id=='root'){
	        nodeid=node.id;
	   		tree_refresh();
	   		//Ext.get('views').dom.src= 'person/personqueryListgrid.jsp?childOrgId='+nodeid+'';
	   		Ext.get('views').dom.src= '';
	   }else{
	      if(node.attributes.uid=="person"+node.id ){
	          nodeid=node.id;
	        	
		      //Ext.get('views').dom.src= 'getPersonDetail.do?personid='+node.id;
		      Ext.get('views').dom.src= '/desktop/widgets/schedule/adminrcap.jsp?personid='+node.id;		    
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
		     //Ext.get('views').dom.src='/usm/departInfo.do?cnname='+title+'&orgid='+node.attributes['id']+'&orglevel='+node.attributes['orglevel']+'&parentorgid='+node.id;
		       Ext.get('views').dom.src='';
		     
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
function competenceCreate(){
		 var win;
        if(!win){
            win = new Ext.Window({
                id:'win1',
                title:'权限',
                width: 250,
                height:450,
                x:760,
                y:95,
                shim:false,
                animCollapse:false,
                constrainHeader:true,
                layout: 'fit',
                items: new Ext.tree.TreePanel({
                id:'forum-tree2',
                region:'west',
                title:'所有部门人员',
                split:true,
                width: 225,
                minSize: 175,
                maxSize: 400,
                collapsible: true,
                margins:'0 0 5 5',
				animCollapse:false,
				animate: true,
				collapseMode:'mini',
                loader: new Ext.tree.TreeLoader({
								url: 'person-tree.js'
		}),
                rootVisible:true,
                lines:true,
                autoScroll:true,
                root: new Ext.tree.AsyncTreeNode({
                					id:'root',
                					draggable:false,
                          text: '所有部门人员'
                      }),
                      collapseFirst:false
            })
            });
        }
        win.show();    
}
