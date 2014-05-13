
/*
 * 
 * 应用菜单管理
 * 
 */
Ext.BLANK_IMAGE_URL='/usm/resources/images/default/s.gif';
Ext.onReady(function(){
    Ext.QuickTips.init();
    var viewport = new Ext.Viewport({
        layout:'border',
        items:[
         
            new Ext.tree.TreePanel({
                id:'forum-tree',
                region:'west',
                title:'右键添加菜单-管理中心',
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
								url: '/usm/applicationtreeJson.do?sysid='+sysid+''
		        }),
                rootVisible:true,
                lines:true,
                autoScroll:true,
                root: new Ext.tree.AsyncTreeNode({
                		  id:'root',
                		  draggable:false,
                          text: '所有应用系统'
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
    tree.on('beforeload',function(node){
	     if(node.attributes.treetype=='module'){
	    	 tree.loader.dataUrl = '/usm/applicationtreeJson.do?sysid='+sysid+'&fid='+node.id;
	     }  
    });  
  	tree.on("contextmenu",treeCtxMenu);//右键菜单
    tree.on('click', function(node){

	   if(node.id=='root'){
	   		left_tree_refresh();
	   }else{
	      if(node.attributes.treetype=='fathernodes'){
	
	           Ext.get("views").dom.src="/usm/resources/applicationTemplate.jsp?template=system&sysid="+node.id;
		 
	      }else if(node.attributes.type=='modulenode'){
	
	      	  Ext.get("views").dom.src="/usm/resources/applicationTemplate.jsp?template=module&sysid="+node.id;
	      }else{
	      	  Ext.get("views").dom.src="/usm/resources/applicationTemplate.jsp?template=application&sysid="+node.id;
		
	      }
      }	
       
    });
   
});

//刷新树
function left_tree_refresh(){
	var tree = Ext.getCmp('forum-tree');
	tree.body.mask('加载中...','x-mask-loading');
	tree.loader.dataUrl = '/usm/applicationtreeJson.do?sysid='+sysid
	tree.root.reload();
	tree.root.collapse(true,true);
	setTimeout(function(){
	   tree.body.unmask();
	   tree.root.expand(false,false);
	},100);
};

//生成节点的右键菜单
function treeCtxMenu(node,e){

  var menu = new Ext.menu.Menu();
  var addApp={
            id:"aMenu",
            text:"添加菜单",
            node:node,
            handler:function(item){
             createApplication(sysid,node.id);
            }
        };
  var editApp={
            id:"eMenu",
            text:"编辑菜单",
            node:node,
            handler:function(item){
             editApplication(node.id);
            }
        };
  var addModule={
            id:"mMenu",
            text:"添加模块",
            node:node,
            handler:function(item){
             showModules(node.id);
            }
  };
   var sort={
            id:"mSort",
            text:"菜单排序",
            node:node,
            handler:function(item){
             applicationSort(node.id);
            }
  };
  
  var modulesort={
            id:"moduleSort",
            text:"模块排序",
            node:node,
            handler:function(item){
             moduleSort(node.id);
            }
  };
  var rModule={
            id:"rMenu",
            text:"删除",
            node:node,
            handler:function(item){
            	 Ext.Ajax.request({ 
					method:'get', 
					url:"/usm/appcatalogmoduleRemove.do?n="+Math.random()+"&uuid="+node.id+"&txtCheckValue=application", 
					success : function(result, action)
								{ 
								Ext.MessageBox.alert('消息', '成功!')
							  	}, 
				    failure : function(form, action) 
				    { Ext.MessageBox.alert('消息', '系统初始化失败!');} 
				}); 
				//left_tree_refresh();
            }
  };

  var expandAll={
            id:"expMenu",
            text:"展开所有",
            node:node,
            handler:function(item){
              item.node.expand(true);
            }
          };
  var collapseAll={
            id:"collMenu",
            text:"折叠所有",
            node:node,
            handler:function(item){
              item.node.collapse(true);
            }
          };

  if(node.attributes.type=='modulenode'){
  		menu.add(rModule);
  }else if(node.attributes.treetype=='fathernodes'){
  	 	menu.add(addApp);
  	 	menu.add(sort);
  }else{
  	menu.add(addApp);
  	menu.add(editApp);
  	menu.add(addModule);
  	menu.add(sort);
  	menu.add(modulesort);
  }

  if(!node.isLeaf()){
    if(node.childNodes&&node.childNodes.length>0){
      if(menu.items.length>0){
        menu.add("-");
      }
      menu.add(expandAll);
      menu.add(collapseAll);
    }
  }
  if(menu.items.getCount()>0){
    menu.showAt(e.getXY());
  }
  return ;
}
function getvalue(name){

    var str=window.location.search; 
    if (str.indexOf(name)!=-1){           
        var pos_start=str.indexOf(name)+name.length+1;
        var pos_end=str.indexOf("&",pos_start);
        if (pos_end==-1){
            return str.substring(pos_start);
        }else{
            alert("对不起这个值不存在！");
        }
    }
}

//添加应用目录
function createApplication(sysid,parentappcatalogid){
var win;
        if(!win){
        win = new Ext.Window({
        title: '应用目录注册信息',
        collapsible:true,
        width:360,
        height:310,
        id:'applicationInsert',
        shim:false,
        animCollapse:false,
        constrainHeader:true, 
        maximizable: true,       
        items: new Ext.FormPanel({
        labelWidth: 80, // label settings here cascade unless overridden
        id:'forms',
        fileUpload: true,
        frame: true,
        title: '',
        bodyStyle:'padding:5px 5px 0',
        width: 350,
        defaults: {width: 230},
        defaultType: 'textfield',

        items: [{
                fieldLabel: '中文名称',
                    name: 'application.cnname',
                    allowBlank:false,
		            blankText:'名称不能为空'  
                },{fieldLabel: '英文名称',
                    name: 'application.enname'
                },

	            {
                    fieldLabel: '链接',
                    name: 'application.navurl',
                    value:'/usm/systemInfo.do',
                    allowBlank:false,
		            blankText:'名称不能为空'  
                },
              {
	            xtype: 'fileuploadfield',
	            id: 'form-file',
	            emptyText: '请选择图片',
	            fieldLabel: '子系统图片',
	            name: 'file',
	            buttonCfg: {
	                text: '打开',
	                iconCls: 'upload-icon'
	            }
        	  }
                ,
                new  Ext.form.TextArea({    //TextArea   
                fieldLabel:'描述',   
                hideParent:true,   
                preventScrollbars:true,   
                name:'application.memo' 
            	}),
       
          		  new Ext.form.Hidden({   //hidden   
                	name:'application.sysid',
                	value:sysid
           		}),
           		new Ext.form.Hidden({   //hidden   
                	name:'application.parentappcatalogid',
                	value:parentappcatalogid
           		}),
           		 new Ext.form.Checkbox({fieldLabel:'可用',   
	                name:'enabled',
	                checked:false,   
	                boxLabel:'是否可用'})
               ],

        buttons: [{
            text: '确  定',
            handler: function(){
            Ext.getCmp("forms").getForm().submit({url:'/usm/appSave.do',method:'POST',waitMsg:'Saving Data...',success:function(form,action){
            	//left_tree_refresh();
            	win.close();
			    Ext.Msg.alert('操作','应用添加成功');
			    
			}, failure:function(form,action){ 
			Ext.Msg.alert('操作','应用添加失败');
			}
            });
        }
        },{
            text: '取  消',
            handler:function(){ 
			Ext.getCmp("forms").getForm().reset(); 
			} 
	 }]
    })
    });
		 win.show();
}
}

//编辑应用目录
function editApplication(parentappcatalogid){
var app;
        if(!app){
        app = new Ext.Window({
        title: '应用目录注册信息',
        collapsible:true,
        width:375,
        height:400,
        id:'applicationEdit',
        shim:false,
        animCollapse:false,
        constrainHeader:true, 
        maximizable: true,       
        html:'<iframe id="editapp" scrolling="yes" frameborder="0" src="/usm/applicationInfo.do?appcatalogid='+parentappcatalogid+'" height=100% width=100%></iframe>'
    });
		 app.show();
}
}



//应用排序
	 //创建 Store
    var applicationstore = new Ext.data.Store({

        proxy: new Ext.data.HttpProxy({   
            url: '/usm/applicationSort.do'   //从此页获取数据
        }),
        
        reader: new Ext.data.JsonReader({
            root: 'data',
            totalProperty: 'totalCount',
            id: 'uuid',
            fields: [
            {name: 'uuid', type: 'string'},
            {name: 'appcatalogid', type: 'string'},
            {name: 'cnname', type: 'string'},
            {name:'serialinde'},
            {name: 'memo', type: 'string'}
            ]
        })
        
        // turn on remote sorting
     //   remoteSort: true
    });
    applicationstore.setDefaultSort('serialinde', 'asc');
    var applicationsm = new Ext.grid.CheckboxSelectionModel(); // add checkbox column
    var applicationcm = new Ext.grid.ColumnModel([applicationsm,{
           id: 'uuid', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
           header: "编 号",
           dataIndex: 'uuid',
            hidden:true,
           align:'center',
           width: 10
        },{
           id: 'appcatalogid', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
           header: "编 号",
           dataIndex: 'appcatalogid',
           align:'center',
            hidden:true,
           width: 40
        },{
           header: "名 称",
           dataIndex: 'cnname',
           align:'center',
           width: 60
        },{
           header: "描 述",
           dataIndex: 'memo',
           align:'center',
           width: 60
        },{
           header: "序号",
           dataIndex: 'serialinde',
           align:'center',
          
           width: 60
        }
        ]); 
     applicationcm.defaultSortable = true;
     
  
function applicationSort(parentappcatalogid){
	var win;
        if(!win){
            win = new Ext.Window({
                id:'applicationwindows',
                title:'应用列表',
                width:500,
		        height:400,
                shim:false,
                animCollapse:false,
                constrainHeader:true,
                layout: 'fit',
              	items: new Ext.grid.GridPanel({
		     	id:'applicationgrid',
		        width:500,
		        height:380,
		        title:'',
		        store: applicationstore,
		        cm: applicationcm,
		        sm: applicationsm,
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
			text: '保  存', 
			handler: function() {
				applicationsm.selectAll();
				var row=Ext.getCmp("applicationgrid").getSelections();
	            var txtCheckValue = "";
	            for(var i=0,len=row.length;i<len;i++){
	                var ss = row[i].get("appcatalogid");
	                txtCheckValue = txtCheckValue + ss + ",";
	            }
	            Ext.Ajax.request({ 
					url:"/usm/applicationSaveSort.do", 
					params:{txtCheckValue:txtCheckValue},
					success : function(result, action)
					{ 
						Ext.MessageBox.alert('消息', '操作成功');
					}, 
				    failure : function(form, action) 
				    { 
						Ext.MessageBox.alert('消息', '系统初始化失败!');
					} 
				}); 
				
				win.close();
				//left_tree_refresh();
				
			} 
			},{ 
			text: '取  消', 
			handler: function(){ 
			win.close();
			;} 
			
			}] 
            
            });
        }
     win.show();
     applicationstore.load({params:{parentappcatalogid:parentappcatalogid}});
     var ddrow = new Ext.dd.DropTarget(Ext.getCmp("applicationgrid").container, {
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
                if(!this.copy) applicationstore.remove(rowData);
                applicationstore.insert(index, rowData);
            }
        }
    }); 
}

//模块配置
   //创建 Store
    var store2 = new Ext.data.Store({
      proxy: new Ext.data.HttpProxy({   
            url: '/usm/sysmoduleGrid.do'   //从此页获取数据
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
    store2.setDefaultSort('moduleid', 'desc');
    var sm2 = new Ext.grid.CheckboxSelectionModel(); // add checkbox column
    var cm2 = new Ext.grid.ColumnModel([sm2,{
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
           hidden:true,
           width: 60
        },{
           header: "描述",
           dataIndex: 'memo',
           align:'center',
           width: 160
        }
        ]);
     cm2.defaultSortable = true;
     
    function showModules(appcatalogid){
	var txtChkValue;
    var cnname = new Ext.form.TextField({   
        width:175, 
        name: 'cnname',
        allowBlank:true 
    }); 
    var memo = new Ext.form.TextField({   
        width:175, 
        name: 'memo',
        allowBlank:true 
    });
 	    var win;
        if(!win){
           win = new Ext.Window({
                id:'win1',
       			width: 700,
                height:400,
                x:200,
                y:100,
                shim:false,
                animCollapse:false,
                constrainHeader:true,
                layout: 'fit',
            items: 
            new Ext.grid.GridPanel({
				id:'modulegrid',
		        width: 500,
	           	height:250,
	            x:140,
	            y:50,
		        store: store2,
		        cm: cm2,
		        sm: sm2,
		        trackMouseOver:false,
		        loadMask: true,
		        autoShow : true,
		        viewConfig: {
	            forceFit:true,
	            enableRowBody:true,
	            showPreview:true
       		}, tbar:['模块名称：',cnname,'模块描述：',memo, {// 查询按钮   
                id : 'searchModelButton',    
                icon : "/usm/img/search.png",
                text: '查 询',
                cls : "x-btn-text-icon",   
                handler :queryModuleMessage   
            }],
       		 bbar: new Ext.PagingToolbar({
       			    pageSize: 1000,
		            store: store2,
		            displayInfo: true,
		            displayMsg: '当前显示{0} - {1}  条记录 /共 {2}条记录',
		            emptyMsg: "无显示数据"
		         
		        })
    		}),
            buttons: [{ 
			text: '保  存', 
				handler: function() { 
				
					var row=Ext.getCmp("modulegrid").getSelections();
		            var txtCheckValue = "";
		            for(var i=0,len=row.length;i<len;i++){
		                var ss = row[i].get("moduleid");
		                txtCheckValue = txtCheckValue + ss + ",";
		            }
	
					Ext.Ajax.request({ 
						method:'get', 
						url:"/usm/appcatalogmoduleSave.do?n="+Math.random()+"&appcatalogid="+appcatalogid+"&txtCheckValue="+txtCheckValue, 
						success : function(result, action)
									{ 
									Ext.MessageBox.alert('消息', '成功!')
								  	}, 
					    failure : function(form, action) 
					    { Ext.MessageBox.alert('消息', '系统初始化失败!');} 
					}); 
					left_tree_refresh();
					win.close();
				} 
			},{ 
			text: '取  消', 
				handler: function(){ 
				win.close(); 
				} 
			
			}] 
            });
        }
        
        win.show();
        //store2.load({params:{start:0, limit:20,sysid:sysid,nodeid:appcatalogid,cnname:cnname,memo:memo}})
        function queryModuleMessage()
        { 
        
        	var cnname = Ext.get('cnname').dom.value;
        	var memo = Ext.get('memo').dom.value;
            store2.load({params:{start:0, limit:1000,sysid:sysid,nodeid:appcatalogid,cnname:cnname,memo:memo}});    
        }
        //store2.load({params:{start:0, limit:50,sysid:sysid,nodeid:appcatalogid}});
    }
    
    
//模块排序
   var modulestore = new Ext.data.Store({

        proxy: new Ext.data.HttpProxy({   
            url: '/usm/appcatalogmoduleGrid.do'   //从此页获取数据
        }),
        
        reader: new Ext.data.JsonReader({
            root: 'data',
            totalProperty: 'totalCount',
            id: 'uuid',
            fields: [
            {name: 'uuid', type: 'string'},
            {name: 'cnname', type: 'string'},
            {name:'serialindex'},
            {name: 'displayname', type: 'string'}

            ]
        })
    });
    modulestore.setDefaultSort('serialindex', 'asc');
   
     
    var modulesm = new Ext.grid.CheckboxSelectionModel(); // add checkbox column
    var modulecm = new Ext.grid.ColumnModel([modulesm,{
           id: 'uuid', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
           header: "编号",
           dataIndex: 'uuid',
            hidden:true,
           align:'center',
           width: 60
        },{
           id: 'cnname', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
           header: "名称",
           dataIndex: 'cnname',
           align:'center',
           width: 60
        },{
           header: "描述",
           dataIndex: 'displayname',
           align:'center',
           width: 100
        },{
           header: "序号",
           dataIndex: 'serialindex',
           align:'center',
           
           width: 100
        }
        ]);
     modulecm.defaultSortable = true; 

function moduleSort(appcatalogid){
	var win;
        if(!win){
            win = new Ext.Window({
                id:'modulewindows',
                title:'模块列表',
                width:500,
		        height:300,
                x:330,
                y:150,
                shim:false,
                animCollapse:false,
                constrainHeader:true,
                layout: 'fit',
              	items: new Ext.grid.GridPanel({
		     	id:'modulegrid',
		        width:500,
		        height:250,
		        title:'',
		        store: modulestore,
		        cm: modulecm,
		        sm: modulesm,
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
			text: '保  存', 
			handler: function() {
				modulesm.selectAll();
				var row=Ext.getCmp("modulegrid").getSelections();
	            var txtCheckValue = "";
	            for(var i=0,len=row.length;i<len;i++){
	                var ss = row[i].get("uuid");
	                txtCheckValue = txtCheckValue + ss + ",";
	            }
	            	Ext.Ajax.request({ 
					method:'get', 
					url:"/usm/appcatalogmoduleSaveSort.do?txtCheckValue="+txtCheckValue, 
					success : function(result, action)
								{ 
								Ext.MessageBox.alert('消息', '操作成功');
							  	}, 
				    failure : function(form, action) 
				    { Ext.MessageBox.alert('消息', '系统初始化失败!');} 
				}); 
				//left_tree_refresh();
				win.close();
				
			} 
			},{ 
			text: '取  消', 
			handler: function(){ 
			win.close();
			;} 
			
			}] 
            
            });
        }
        win.show();
     modulestore.load({params:{start:0, limit:50,appcatalogid:appcatalogid}});
     var ddrow = new Ext.dd.DropTarget(Ext.getCmp("modulegrid").container, {
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
                if(!this.copy) modulestore.remove(rowData);
                modulestore.insert(index, rowData);
            }
        }
    }); 
        
  
}
     