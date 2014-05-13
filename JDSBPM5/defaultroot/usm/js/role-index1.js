Ext.BLANK_IMAGE_URL='/usm/resources/images/default/s.gif';
Ext.onReady(function(){

    Ext.QuickTips.init();	
    var viewport = new Ext.Viewport({
        layout:'border',
        items:[
           
            new Ext.tree.TreePanel({
                id:'forum-tree',
                region:'west',
                title:'角色管理中心',
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
								url: '/usm/roletreeJson.do?sysid='+sysid+''
		}),
                rootVisible:true,
                lines:true,
                autoScroll:true,
                root: new Ext.tree.AsyncTreeNode({
                					id:'root',
                					draggable:false,
                          text: '所有角色'
                      })
            }),
          
                      new Ext.Panel({
                            region:'center',
                            id:'topic-grid',
                            html:'<iframe id="views" frameborder="0"  height=100% width=100%></iframe>'  
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
	 
	   		//Ext.get('views').dom.src= 'role/rolequeryListgrid.jsp?sysid='+sysid+'&type='+type+'' ;
	   }else{
	   	
	   	if(type=="manager"){
            nodeid=node.id;     
	        Ext.get('views').dom.src= '/usm/roleInfo.do?sysid='+sysid+'&type='+type+'&roleid='+node.id+'';
		  }else{
		  	Ext.get('views').dom.src='/usm/rightTemplate.do?template=role&sysid='+sysid+'&nodeid='+node.id;
		  }
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


//加载异步树   
function tree_refreshSystem(sysid) {   
    var tree = Ext.getCmp("forum-tree");   
     tree.getLoader().url="/usm/systemtreeJson.do?id="+sysid;
	tree.body.mask('加载中...','x-mask-loading');
     tree.getRootNode().loaded = false;///因数据源改变，设置loaded属性为false，从而让树的内容更新
     setTimeout(function(){
	   tree.body.unmask();
	   tree.root.expand(false,true);
	},100);
       
}   

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