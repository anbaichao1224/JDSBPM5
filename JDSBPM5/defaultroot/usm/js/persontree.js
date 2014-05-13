Ext.onReady(function(){

    Ext.QuickTips.init();

	var fp = new Ext.FormPanel({
        frame: true,
        title:'列表',
        split:true,
        collapsible: true,
        labelWidth: 110,
        width: 765,
        renderTo:'form-ct',
        bodyStyle: 'padding:0 10px 0;',
        items :str,
        buttons: [{
        	disabled:bool,
            text: '保存',
            handler: function(){
              fp.getForm().submit({url:'/usm/rightTemplateSave.do',method:'POST',waitMsg:'正在保存数据...',success:function(form,action){ 
					Ext.Msg.alert('操作','操作成功');
				
				}, failure:function(form,action){ 
					Ext.MessageBox.alert('错误', '操作失败!'); 
				}
	            });
	            
            }
        },{
            text: '添加人员',
            handler: function(){

            	createWindow(nodeid,sysid);

            }
        }]

    });
  
   
});

function createWindow(nodeid,sysid){
	var root = new Ext.tree.AsyncTreeNode({
	    text: '组织机构',
	    leaf : false,
	    cls : 'folder',
	    draggable:false,
	    id:'toproot'
    });
    var Tree = Ext.tree;
    var treePanel= new Ext.tree.TreePanel({
               id:'forum-tree',
               region:'west',
               split:true,
               width: 225,
               height: 500,
               minSize: 175,
               maxSize: 400,
               collapsible: true,
               margins:'0 0 0 5',
			animCollapse:false,
			animate: true,
			rootVisible:false,
            loader: new Ext.tree.TreeLoader({
				url: '/usm/orgtreeJson.do'
	        }),
               rootVisible:true,
               lines:true,
               autoScroll:true,
               root:root
           
       });
	var win;
       if(!win){
           win = new Ext.Window({
       title: '人员中心',
       collapsible:true,
       width:240,
       height:500,
       id:'tabExtPersonformwin',
       shim:false,
       animCollapse:false,
       constrainHeader:true, 
       maximizable: true,       
       items: treePanel,
       buttonAlign:'left',
        buttons: [{
                   text: '保存',
                   handler: function(){
                   var txtCheckValue=Ext.getCmp("forum-tree").getChecked('id');
                   Ext.Ajax.request({ 
				method:'get', 
				url:"/usm/rightTemplateSave.do?n="+Math.random()+"&template=moduleaddright&nodeid="+nodeid+"&txtCheckValue="+txtCheckValue+"&sysid="+sysid, 
				success : function(result, action)
							{ 
							Ext.MessageBox.alert('消息', '操作成功');
							//location.reload();
						  	}, 
			    failure : function(form, action) 
			    { Ext.MessageBox.alert('消息', '系统初始化失败!');} 
				}); 
                   	
                   }
                   
               },{
                   text: '关闭',
                   handler: function(){
                   win.close();
                   
                   }
                   
               }]      
    });
    win.show();
    
	var tree=Ext.getCmp("forum-tree");
    tree.on('beforeload', function(node){
	    Ext.getCmp("forum-tree").loader.url = '/usm/orgtreeJson.do?name=person&choose=true&childOrgId='+node.id+'';
	});
	}
  
 }