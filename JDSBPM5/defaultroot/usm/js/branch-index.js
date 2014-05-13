
Ext.BLANK_IMAGE_URL='/usm/resources/images/default/s.gif';
Ext.onReady(function(){

    Ext.QuickTips.init();

		//tree start
		 var menu = new Ext.menu.Menu({
        id: 'mainMenu',
        items: [
            {
            		id:'person',
                text: '��Ա����',
                handler:onItemClick
            },
            {
            		id:'branch',
                text: '���Ź���',
                handler:onItemClick
            }
        ]
    });
    var menu2 = new Ext.menu.Menu({
        id: 'mainMenu2',
        items: [
            {
                text: '��λ',
                handler:onItemClick
            },
            {
                text: 'ְ��',
                handler:onItemClick
            },
            {
                text: 'ְ��',
                handler:onItemClick
            },
            {
                text: '��ɫ',
                handler:onItemClick
            },
            {
                text: '�û���',
                handler:onItemClick
            }
        ]
    });
	
		 var menu3 = new Ext.menu.Menu({
        id: 'mainMenu3',
        items: [
            {
                text: 'ϵͳע��',
                handler:onItemClick
            },
            {
            		id:'resources',
                text: '��Դ����',
                handler:onItemClick
            },
            {
                text: 'ϵͳ��־',
                handler:onItemClick
            }
        ]
    });

    var tb = new Ext.Toolbar();
    tb.render('toolbar');

    tb.add({
            text:'��֯����',
            iconCls: 'bmenu',  // <-- icon
            menu: menu  // assign menu by instance
        },'-',{
       		  text:'��֯����ģ��',
            iconCls: 'bmenu',  // <-- icon
            menu: menu2  // assign menu by instance
    },'-',{
       		  text:'��Դ',
            iconCls: 'bmenu',  // <-- icon
            menu: menu3  // assign menu by instance
    }
    		);
/*
    menu.addSeparator();
    // Menus have a rich api for
    // adding and removing elements dynamically
    var item = menu.add({
        text: 'Dynamically added Item'
    });
    // items support full Observable API
    item.on('click', onItemClick);

  */ 


    function onItemClick(item){
    if(item.id=="person"){
       	location ="person-index.html";
      }else if(item.id=="branch"){
      	location ="branch-index.html";
      }else if(item.id=="resources"){
      	location ="resources-index.html";
      }else{
      	alert(item.text);	
      }
    }
		//tree end 

/*
    var ds = new Forum.TopicStore();

    var cm = new Ext.grid.ColumnModel([{
           id: 'topic',
           header: "Topic",
           dataIndex: 'title',
           width: 420,
           renderer: Forum.Renderers.topic
        },{
           header: "Author",
           dataIndex: 'author',
           width: 100,
           hidden: true
        },{
           header: "Replies",
           dataIndex: 'replycount',
           width: 70,
           align: 'right'
        },{
           id: 'last',
           header: "Last Post",
           dataIndex: 'lastpost',
           width: 150,
           renderer: Forum.Renderers.lastPost
        }]);

    cm.defaultSortable = true;
*/
    var viewport = new Ext.Viewport({
        layout:'border',
        items:[
            new Ext.BoxComponent({ // raw
                region:'north',
                el: 'header',
                height:32
            }),
            new Ext.tree.TreePanel({
                id:'forum-tree',
                region:'west',
                title:'���Ź�������',
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
				  //    waitMsg:"���ڼ������ݣ��Ե�......",
				      collapseMode:'mini',
                loader: new Ext.tree.TreeLoader({
								url: 'js/branch-tree.js'
		}),
                rootVisible:true,
                lines:true,
                autoScroll:true,
                root: new Ext.tree.AsyncTreeNode({
                					id:'root',
                					draggable:false,
                          text: '���в���'
                      }),
                      collapseFirst:false
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
                    title:'������...',
                    items:[
                        new Ext.Panel({
                            region:'center',
                            id:'topic-grid',
                            
                     //       title:'sdfsdfsdf',
                         
                       //     trackMouseOver:false,
                      //      loadMask: {msg:'Loading Topics...'},
                            html:'<iframe id="views" scrolling="no" frameborder="0" src="http://www.google.cn" height=100% width=100%></iframe>',
                            tbar:[
                                {
                                    text:'��������',
                                    iconCls: 'new-topic',
                                    handler:function(){Ext.get('views').dom.src= "branch/branchInsert.html";}
                                },
                                '-',
                                {
                                    text:'��ѯ����',
                                    iconCls: 'new-topic',
                                    handler:function(){Ext.get('views').dom.src= "branch/branchQuery.html";}
                                },
                                '-',
                                {
                                    text:'Ȩ������',
                                    iconCls: 'new-topic',
                                    handler:function(){competenceCreate();}
                                },
                                '-',
                                {
                                    text:'Ȩ�޸���',
                                    iconCls: 'new-topic',
                                    handler:function(){competenceCreate();}
                                },
                                '-',
                                {
                                    text:'Ȩ�޼̳�',
                                    iconCls: 'new-topic',
                                    handler:function(){competenceCreate();}
                                },
                                '-',
                                {
                                    text:'Ȩ�޶Ա�',
                                    iconCls: 'new-topic',
                                    handler:function(){competenceCreate();}
                                }
                            ]
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
    sm.on('selectionchange', function(sm, node){
      //  ds.loadForum(node.id);
      var url="branch/branchFunInform.html";
        Ext.getCmp('main-view').setTitle(node.text);
       	Ext.get('views').dom.src= url;
       
    });
});

function competenceCreate(){
		 var win;
        if(!win){
            win = new Ext.Window({
                id:'win1',
                title:'Ȩ��',
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
                title:'����Ӧ��ϵͳ',
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
								url: 'resources-tree.js'
		}),
                rootVisible:true,
                lines:true,
                autoScroll:true,
                root: new Ext.tree.AsyncTreeNode({
                					id:'root',
                					draggable:false,
                          text: '����Ӧ��ϵͳ'
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