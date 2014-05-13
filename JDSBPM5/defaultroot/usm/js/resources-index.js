
var nodeid;
var system="";
var Forum = {};
Ext.BLANK_IMAGE_URL='/usm/resources/images/default/s.gif';
Ext.onReady(function(){
    Ext.QuickTips.init();
    var viewport = new Ext.Viewport({
    	id:'viewport',
        layout:'border',
        items:[
            new Ext.BoxComponent({ // raw
                region:'north',
                el: 'header',
                height:32
            }),
            new Ext.tree.TreePanel({
                id:'forum-tree',
                bodyStyle:'background:url(/usm/images/left.jpg) repeat;', 
                region:'west',
                title:'资源管理中心',
                split:true,
                width: 225,
                minSize: 175,
                maxSize: 400,
                collapsible: true,
                margins:'0 0 0 5',
				      animCollapse:false,
				      animate: true,
				      collapseMode:'mini',
				      
                loader: new Ext.tree.TreeLoader({
								url: '   '	
		        }),
                rootVisible:false,
                lines:false,
                autoScroll:true,
                root: new Ext.tree.AsyncTreeNode({
                					id:'root',
                					draggable:false,
                                    text: '功能操作'
                })
            }),
             new Ext.Panel({
                            region:'center',
                            id:'topic-grid',
                            html:'<iframe name="views" id="views" scrolling="yes" frameborder="0" src="/usm/info.jsp" height=100% width=100%></iframe>'
                           
             })
         ]
    });


    var tree = Ext.getCmp('forum-tree');
    var sm = tree.getSelectionModel();
    sm.on('beforeselect', function(sm, node){
          return node.isLeaf();
    });
   	sm.on('selectionchange', function(sm, node){
    if(node!=null){
    	  Ext.get('views').dom.src= node.attributes.url;
    }
    });
});

//刷新树
function left_tree_refresh(){
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
function tree_refreshSystem(url) {   

    var tree = Ext.getCmp("forum-tree"); 
    
    tree.getLoader().url=url;
	tree.body.mask('加载中...','x-mask-loading');
     tree.getRootNode().loaded = false;///因数据源改变，设置loaded属性为false，从而让树的内容更新
     setTimeout(function(){
	   tree.body.unmask();
	   tree.root.expand(false,true);
	},100);
}   

  //创建 Store
    var store = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({   
            url: "/usm/moduleGrid.do"   //从此页获取数据
        }),
        reader: new Ext.data.JsonReader({
            root: 'data',
            totalProperty: 'totalCount',
            id: 'moduleid',
            fields: [
            {name: 'moduleid', type: 'string'},
            {name: 'cnname', type: 'string'},
            {name: 'enname', type: 'string'},
             {name: 'memo', type: 'string'}
            ]
        })

    });
    store.setDefaultSort('moduleid', 'desc');
    var sm = new Ext.grid.CheckboxSelectionModel(); // add checkbox column
    var cm = new Ext.grid.ColumnModel([sm,{
           id: 'moduleid', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
           header: "编号",
           dataIndex: 'moduleid',
           align:'center',
           hidden:true,
           width: 60
        },{
           header: "中文名称",
           dataIndex: 'cnname',
           align:'center',
           width: 100
        },{
           header: "英文名称",
           dataIndex: 'enname',
           align:'center',
           width: 60
        },{
           header: "描述",
           dataIndex: 'memo',
           align:'center',
           width: 60
        }
        ]);
     cm.defaultSortable = true;
	 
	 
	 
    
function competenceCreate(){
	var txtCheckValue;
 	var win;
        if(!win){
            win = new Ext.Window({
                id:'moduleWindows',
                title:'所有模块',
                width:500,
		        height:350,
                x:330,
                y:150,
                shim:false,
                animCollapse:false,
                constrainHeader:true,
                layout: 'fit',
              	items: new Ext.grid.GridPanel({
		     	id:'resgrid',
		        width:500,
		        height:250,
		        title:'所有的模块',
		        store: store,
		        cm: cm,
		        sm: sm,
		        trackMouseOver:false,
		        loadMask: true,
		        autoShow : true,
		        viewConfig: {
		            forceFit:true,
		            enableRowBody:true,
		            showPreview:true
		        },
		        bbar: new Ext.PagingToolbar({
		            pageSize: 10,
		            store: store,
		            displayInfo: true,
		            displayMsg: '当前显示{0} - {1}  条记录 /共 {2}条记录',
		            emptyMsg: "无显示数据"
		         
		        })
		        
		        }),
            buttons: [{ 
			text: '保存', 
			handler: function() { 
				var row=Ext.getCmp("resgrid").getSelections();
	            var txtCheckValue = "";
	            for(var i=0,len=row.length;i<len;i++){
	                var ss = row[i].get("moduleid");
	                txtCheckValue = txtCheckValue + ss + ",";
	            }
	            
					Ext.Ajax.request({ 
					method:'get', 
					url:"/usm/sysmoduleSave.do?n="+Math.random()+"&txtCheckValue="+txtCheckValue+"&sysid="+nodeid, 
					success : function(result, action)
								{ 
								Ext.MessageBox.alert('消息', '操作成功');
							  	}, 
				    failure : function(form, action) 
				    { Ext.MessageBox.alert('消息', '系统初始化失败!');} 
				}); 
				left_tree_refresh();
				win.close();
				
			} 
			},{ 
			text: '取消', 
			handler: function(){ 
			win.close();
			;} 
			
			}] 
            
            });
        }
        win.show();    
        store.load({params:{start:0, limit:10,sysid:nodeid}});
          
    }
    
    window.onbeforeunload =function RunOnBeforeUnload() {
			 Ext.Ajax.request({
			 	url:'/usm/commitCache.action',
			 	method:'post',
			 	waitMsg:'正在处理，请稍候...'
			 })
 }
    
//子系统的调序
//创建 Store
    var Systemstore = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({   
            url: "/usm/systemGrid.do"   //从此页获取数据
        }),
        reader: new Ext.data.JsonReader({
            root: 'data',
            totalProperty: 'totalCount',
            id: 'sysid',
            fields: [
            {name: 'sysid', type: 'string'},
            {name: 'sysname', type: 'string'},
            {name: 'serialindex'},
             {name: 'sysdesc', type: 'string'}
            ]
        })
    });
    Systemstore.setDefaultSort('serialindex', 'asc');

     var Systemsm = new Ext.grid.CheckboxSelectionModel(); // add checkbox column
    var Systemcm = new Ext.grid.ColumnModel([Systemsm,{
           id: 'sysid', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
           header: "编号",
           dataIndex: 'sysid',
           align:'center',
           hidden:true,
           width: 60
        },{
           header: "中文名称",
           dataIndex: 'sysname',
           align:'center',
           width: 100
        },{
           header: "序号",
           dataIndex: 'serialindex',
           align:'center',
           hidden:true,
           width: 60
        },{
           header: "系统描述",
           dataIndex: 'sysdesc',
           align:'center',
           width: 60
        }
        ]);
     Systemcm.defaultSortable = true;
function SystemSort(){
 	var win;
        if(!win){
            win = new Ext.Window({
                id:'systemwindows',
                title:'所有模块',
                width:500,
		        height:350,
                x:330,
                y:150,
                shim:false,
                animCollapse:false,
                constrainHeader:true,
                layout: 'fit',
              	items: new Ext.grid.GridPanel({
		     	id:'systemgrid',
		        width:500,
		        height:250,
		        title:'所有的模块',
		        store: Systemstore,
		        cm: Systemcm,
		        sm: Systemsm,
		        trackMouseOver:true,
		        loadMask: true,
		        autoShow : true,
		        enableDragDrop: true,
		        viewConfig: {
		            forceFit:true,
		            enableRowBody:true,
		            showPreview:true
		        }
		        
		        }),
            buttons: [{ 
			text: '保存', 
			handler: function() {
				Systemsm.selectAll();
				var row=Ext.getCmp("systemgrid").getSelections();
	            var txtCheckValue = "";
	            for(var i=0,len=row.length;i<len;i++){
	                var ss = row[i].get("sysid");
	                txtCheckValue = txtCheckValue + ss + ",";
	            }
	            	Ext.Ajax.request({ 
					method:'get', 
					url:"/usm/systemSort.do?n="+Math.random()+"&txtCheckValue="+txtCheckValue, 
					success : function(result, action)
								{ 
								Ext.MessageBox.alert('消息', '操作成功');
							  	}, 
				    failure : function(form, action) 
				    { Ext.MessageBox.alert('消息', '系统初始化失败!');} 
				}); 
				
				win.close();
				left_tree_refresh();
				
			} 
			},{ 
			text: '取消', 
			handler: function(){ 
			win.close();
			;} 
			
			}] 
            
            });
        }
        win.show();
        Systemstore.load();

     var ddrowSystem = new Ext.dd.DropTarget(Ext.getCmp("systemgrid").container, {
        ddGroup : 'GridDD',
        copy    : false,
        notifyDrop : function(dd, e, data) {
            // 选中了多少行
            var rows = data.selections;
            // 拖动到第几行
            var index = dd.getDragData(e).rowIndex;
            if (typeof(index) == "undefined") {
                return;
            }
            // 修改ds
            for(i = 0; i < rows.length; i++) {
                var rowData = rows[i];
                if(!this.copy) Systemstore.remove(rowData);
                Systemstore.insert(index, rowData);
            }
        }
    }); 
    }
 