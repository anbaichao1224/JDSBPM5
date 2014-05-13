Ext.BLANK_IMAGE_URL='/usm/resources/images/default/s.gif';
Ext.onReady(function(){

    Ext.QuickTips.init();

	
    var viewport = new Ext.Viewport({
        layout:'border',
        items:[
           
            new Ext.tree.TreePanel({
                id:'forum-tree',
                region:'west',
                title:'职级管理中心',
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
								url: '/usm/dutyLeveltreeJson.do?sysid='+sysid+''
		}),
                rootVisible:true,
                lines:true,
                autoScroll:true,
                root: new Ext.tree.AsyncTreeNode({
                					id:'root',
                					draggable:false,
                          text: '组织机构'
                      })
            }),
            new Ext.TabPanel({
                id:'main-tabs',
                activeTab:0,
                region:'center',
                margins:'0 5 5 0',
                resizeTabs:true,
                tabWidth:150,
                items: {
                    id:'main-view',
                    layout:'border',
                    title:'职级管理',
                    items:[
                        new Ext.Panel({
                            region:'center',
                            id:'topic-grid',
                            html:'<iframe id="views" frameborder="0" src="dutylevel/dutylevelqueryListgrid.jsp?sysid='+sysid+'&type='+type+'"  height=100% width=100%></iframe>'
                           
                        })
                     ]
                 }
              })
         ]
    });


    var tree = Ext.getCmp('forum-tree');
   /* tree.on('append', function(tree, p, node){
       if(node.id == 11){
           node.select.defer(100, node);
       }
    });
    */
    var sm = tree.getSelectionModel();
    sm.on('beforeselect', function(sm, node){
          return node.isLeaf();
    });
  
   /*	 sm.on('selectionchange', function(sm, node){
      //  ds.loadForum(node.id);
      nodeid=node.id;
       
      var url="/usm/systemInfo.do?id="+node.id;
  	//	var url="/usm/resources/systemInfo.jsp?id="+node.id;
        Ext.getCmp('main-view').setTitle(node.text);
        Ext.getCmp('createResources').enable();
       	Ext.get('views').dom.src= url;
   //   	parent.reload();
      	
       
    });
     */
   tree.on('click', function(node){
	   if(node.id=='root'){
	        nodeid=node.id;
	   		tree_refresh();
	   		
	   		Ext.get('views').dom.src= 'dutylevel/dutylevelqueryListgrid.jsp?sysid='+sysid+'&type='+type+'' ;
	   }else{
		 if(type=="manager"){ 
	        Ext.get('views').dom.src= 'dutylevel/dutyLevelInfo.do?sysid='+sysid+'&type='+type+'&levelid='+node.id;
		  }else{
		  	Ext.get('views').dom.src='/usm/rightTemplate.do?template=level&sysid='+sysid+'&nodeid='+node.id;
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
      
   
//具体的把一本树展现成树形的结构,可以在枝，叶上做修改   
/*    var data = new Ext.tree.TreeLoader({   
        url : 'js/resources-tree.js'  
    });   
    var root = new Ext.tree.AsyncTreeNode({   
        text : '根',   
        draggable : false,   
        rootFlag : true  
    });   
    var tree = new Ext.tree.TreePanel({   
        id : 'sectionStructure1',   
        renderTo : 'forum-tree',   
        width : 1000,   
        autoScroll : true,   
        useArrows : true,   
        animate : true,   
        containerScroll : true,   
        rootVisible : false,   
        root : root,   
        loader : data   
    });   
  
   // tree.on('dblclick', itemManage);   
  
   // tree.on('contextmenu', outItemTreeContextMenu);   
  */
  //  root.expand(true, true);   
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
				    //    animate:true, 
			     //   enableDD:true,
			     //   containerScroll: true,
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
/*
Forum.TreeLoader = function(){
	alert('load1');
	
    Forum.TreeLoader.superclass.constructor.call(this);
    this.proxy = new Ext.data.ScriptTagProxy({
   // this.proxy = new Ext.data.HttpProxy({
        url : this.dataUrl
    });
    alert(this.dataUrl);
};
Ext.extend(Forum.TreeLoader, Ext.tree.TreeLoader, {
  //  dataUrl: 'http://localhost:8081/a.html',
   dataUrl: 'http://extjs.com/forum/forums-remote.php',
    requestData : function(node, cb){
        this.proxy.load({}, {
            readRecords : function(o){
                return o;
            }
        }, this.addNodes, this, {node:node, cb:cb});
    },
   
    addNodes : function(o, arg){
        var node = arg.node;
        for(var i = 0, len = o.length; i < len; i++){
            var n = this.createNode(o[i]);
            if(n){
                node.appendChild(n);
            }
        }
        arg.cb(this, node);
    }
});
*/