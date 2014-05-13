
Ext.onReady(function(){
	var sm = new Ext.grid.CheckboxSelectionModel({
		dataIndex:'messageid' 
	});
	
	
	function createButton(valueid){
		
			return "<a href=javascript:void(0) onclick=\"\">取消提醒</a>";
	}
	//列名称
	var cm = new Ext.grid.ColumnModel([
		sm,
		 new Ext.grid.RowNumberer({header:"序号",dataIndex:'id',width:40}),
		{header:'uuid',dataIndex: 'uuid',width:100,sortable:true,hidden:true},
	    {header:'接收人姓名',dataIndex: 'name',width:100,sortable:true},
	    {header:'开始提醒时间',dataIndex: 'begindate',width:100,sortable:true},
	    {header:'是否取消',dataIndex: 'iscancel',width:100,sortable:true}
	     
	]);
	
	var urlstr = '/prompt/promptperson_getPersonByPid.action?promIds='+promId;
	var proxy = new Ext.data.HttpProxy({url:urlstr});
	var ttbar = new Ext.Toolbar(
			{
				items : [ {
				        	 text : '新增人员',
				        	 id : 'addbtn',
				        	 cls : "x-btn-text-icon",
				        	  icon: '/desktop/resources/images/default/icons/grid2-2.gif',
				        	 handler : function() {
				        	 	var txtCheckValue;
				var txtCheckName;
				
				var dateTree = new Ext.tree.TreePanel({
						id : 'datetree',
						rootVisible : false,
						columnWidth : .5,
						//width:200,
						//root : new Ext.tree.TreeLoader(),
						tbar : [{
									id : 'blr',
									text : '办理人数(0)',
									border : false
								}],
						border : false,
						autoScroll :false
					});

			var root1 = new Ext.tree.AsyncTreeNode({});

			dateTree.setRootNode(root1);
	 			var root = new Ext.tree.AsyncTreeNode( {
					text :'根节点',
					//leaf :false,
					//cls :'folder',
					//draggable :false,
					id :'toproot'
			});
		
	 			var treeWin = new Ext.Window({
	 				title:'选择人员',
	 				width:480,
	 				height:360,
	 				id:'persontreewin',
	 				closeAction :'close',
					closable :true,
					collapsible :true,
					autoScroll :false,
	 				items:[{layout:'column',
	 					items:[
	 				new Ext.tree.TreePanel( {
					id :'forum-tree',
					columnWidth:.5,
					title :'',
					split :true,
					//width :230,
					height:285,
					checkModel:'cascade',//'multiple',
					collapsible :true,
					margins :'0 0 5 5',
					animCollapse :false,
					animate :true,
					autoScroll:true,
					root:root,
					
					loader :new Ext.tree.TreeLoader( {
						url :'/usm/orgtreeJson.do'
					}),
					
					containerScroll : true,
					autoScroll :true,
					rootVisible :false
					}),dateTree]
					}],
					buttons:[{
						text:'确定',
						handler:function(){
							Ext.Ajax.request({
								url:'/prompt/promptperson_saves.action',
								params:{personIds:txtCheckValue,promIds:promId},
								method:'post',
								success:function(){
									alert('添加成功');
									grid.getStore().reload();
									treeWin.close();
								}
							})
							
						}
					},{
						text:'关闭',
						handler:function(){
							treeWin.close();
						}
					}]
	 			});
	 			treeWin.show();
				var tree = Ext.getCmp('forum-tree');
				tree.expandAll();
	 			tree.render();
	 			
	 			tree.on('beforeload',function(node) {
						tree.loader.url = '/usm/orgtreeJson.do?name=person&choose=true&childOrgId=' + node.id + '';
					});
					var count = 0;
				tree.on('checkchange', function(node, checked) {
					
					var t = Ext.getCmp('datetree');
					var troot = t.root;
					if (node.leaf == true) {
						var node1 = new Ext.tree.TreeNode({
									text : node.text
								});
						if (checked == true) {
							// t.root.expand(t.getNodeById(node.id));
							troot.appendChild(new Ext.tree.TreeNode({
										id : node.id,
										text : node.text
									}));
							count++;
							Ext.getCmp('blr').setText('办理人数(' + count + ')');
						} else {
							var rn = t.getNodeById(node.id);
							if (rn != null) {
								troot.removeChild(rn);
							}
							count--;
							Ext.getCmp('blr').setText('办理人数(' + count + ')');
						}
					} else {
						if (checked == true) {
							node.eachChild(function(child) {
										troot
												.appendChild(new Ext.tree.TreeNode(
														{
															id : child.id,
															text : child.text
														}));
										count++;
									});
							Ext.getCmp('blr').setText('办理人数(' + count + ')');
						} else {
							node.eachChild(function(child) {
										var rn = t.getNodeById(child.id);
										if (rn != null) {
											troot.removeChild(rn);
											count--;
										}
									});
							Ext.getCmp('blr').setText('办理人数(' + count + ')');
						}
					}
					txtCheckValue = tree.getChecked('id');
					txtCheckName = tree.getChecked('text');
				
					},tree);	 	
				         }
				         },{
				         	text:'删除',
				         	 id:'delPersonbtn',
				         	 cls : "x-btn-text-icon",
				         	 icon: '/desktop/widgets/electronicfile/images/delete_01.png',
				         	 handler:function(){
				         	 	cancelFile(grid);
				         	 }
				         }]
			});
	
	//链接
	var store = new Ext.data.Store({
		//proxy: new Ext.data.MemoryProxy(data),
		proxy: proxy,
		reader:new Ext.data.JsonReader({
			totalProperty:'totalCount',
			root:'root'
		},[
			{name:'uuid'},
			{name:'name'},
			{name:'begindate'},
			{name:'iscancel'}
		])
	});

	
	
	var pgsize = 30;
	//面板
	var grid= new Ext.grid.GridPanel({
		title:'',
		id:'promptperson-list',
       width:Ext.getBody().getWidth(),
       height:Ext.getBody().getHeight(),
		loadMask:true,
		renderTo: Ext.getBody(),
		store:store,
		cm:cm,
		sm:sm,
		tbar: ttbar,
		bbar: new Ext.PagingToolbar({
			pageSize:pgsize,
			store:store,
			displayInfo:true,
			displayMsg:'显示第{0}条到{1}条记录,一共{2}条',
			emptyMsg:"没有记录"
		})
		
	});
	
	store.load({params:{start:0,limit:pgsize}});
	 
});
function cancelFile(grid){
	var selections=grid.getSelections();
	   if (selections.length==0){
	          alert('请选择需要删除的人员');
	   		return;
	   }else{
	   		if(!confirm("确定删除？")){
	   			return;
	   		}
	   }
 
	var delAllList=new Array();
	  for(var i=0;i<selections.length;i++){
	   if (selections[i].get('uuid').length>0 ){     
	    delAllList[delAllList.length]= selections[i].get('uuid');    
	   }else{
		   alert("error");
	   }
	  };
	   var fileList=delAllList.join();
	  //JDS.ajax.Connection.LoadJsonFromUrl('mtype_deltype.action',null,str);
	  Ext.Ajax.request({
		  url:'/prompt/promptperson_deletes.action',
		  params:{promIds:fileList},
		  method:'post',
		  success:function(o){
			   alert('操作成功');
			  grid.getStore().reload();
		  }
	  });
		  
}

function cancelPrompt(uid){
	if(!confirm("确定取消提醒？")){
	   			return;
	  }
	 Ext.Ajax.request({
		  url:'/prompt/promptperson_cancel.action',
		  params:{promIds:uid},
		  method:'post',
		  success:function(o){
			  alert('操作成功');
			  Ext.getCmp('prompt-list').getStore().reload();
		  }
	  });
}
function savetype(){
	Ext.Ajax.request({
	url: "mtype_save.action",
	form:'addtype',
	method: "post",
	success: function(o){
		alert("添加成功");
		var win =parent.Ext.getCmp('addtype');
		parent.Ext.getCmp('hylx-list').load();
			win.close();
		
	}
}); 
	
}
function addPrompt(){
	var form = new Ext.FormPanel({
	 	frame:true,
	 	width:600,
	 	labelWidth:100,
	 	labelAlign:'right',
	 	url:'/prompt/prompt_save.action',
	 	items:[{
	 		xtype:'textfield',
	 		name:'prombean.uuid',
	 		hidden:true,
	 		hideLabel:true
	 		//value:''
	 	},{
	 		xtype:'textfield',
	 		name:'personIds',
	 		id:'personIds',
	 		hidden:true,
	 		hideLabel:true
	 	},{
	 		xtype:'textfield',
	 		fieldLabel:'标题',
	 		name:'prombean.title',
	 		id:'title',
	 		anchor:'90%'
	 	},{
	 		xtype:'textarea',
	 		fieldLabel:'内容',
	 		name:'prombean.content',
	 		id:'content',
	 		anchor:'90%'
	 	},{
	 		xtype:'datefield',
	 		fieldLabel:'开始提醒时间',
	 		format:'Y-m-d',
	 		name:'prombean.begindate',
	 		id:'begindate',
	 		anchor:'50%'
	 	}],
	 	buttons:[{
	 		text:'发送',
	 		handler:function(){
	 			var txtCheckValue;
				var txtCheckName;
				
				var dateTree = new Ext.tree.TreePanel({
						id : 'datetree',
						rootVisible : false,
						columnWidth : .5,
						//width:200,
						//root : new Ext.tree.TreeLoader(),
						tbar : [{
									id : 'blr',
									text : '办理人数(0)',
									border : false
								}],
						border : false,
						autoScroll :false
					});

			var root1 = new Ext.tree.AsyncTreeNode({});

			dateTree.setRootNode(root1);
	 			var root = new Ext.tree.AsyncTreeNode( {
					text :'根节点',
					leaf :false,
					cls :'folder',
					draggable :false,
					id :'toproot'
			});
		
	 			var treeWin = new Ext.Window({
	 				title:'选择人员',
	 				width:480,
	 				height:Ext.getBody().getHeight()-40,
	 				id:'persontreewin',
	 				closeAction :'close',
					closable :true,
					collapsible :true,
					autoScroll :false,
	 				items:[{layout:'column',
	 					items:[
	 				new Ext.tree.TreePanel( {
					id :'forum-tree',
					columnWidth:.5,
					title :'',
					split :true,
					//width :230,
					//height:600,
					checkModel:'cascade',//'multiple',
					collapsible :true,
					margins :'0 0 5 5',
					animCollapse :false,
					animate :true,
					//rootVisible :false,
					loader :new Ext.tree.TreeLoader( {
						url :'/usm/orgtreeJson.do'
					}),
					
					containerScroll : true,
					autoScroll :true,
					root :root,
					rootVisible :true
					}),dateTree]
					}],
					buttons:[{
						text:'确定',
						handler:function(){
							//alert(txtCheckName);
							Ext.getCmp('personIds').setValue(txtCheckValue);
							if(form.getForm().isValid()){
            					form.getForm().submit({
            						success:function(){
            							alert('添加成功');
            							Ext.getCmp('persontreewin').close();
            							Ext.getCmp('addPrompt').close();
            						}
            					});
            				}
						}
					},{
						text:'关闭',
						handler:function(){
							win.close();
						}
					}]
	 			});
	 			treeWin.show();
	 			var tree = Ext.getCmp('forum-tree');
	 			tree.setRootNode(root);
				tree.expandAll();
	 			tree.render();
	 			
	 			tree.on('beforeload',function(node) {
						tree.loader.url = '/usm/orgtreeJson.do?name=person&choose=true&childOrgId=' + node.id + '';
					});
					var count = 0;
				tree.on('checkchange', function(node, checked) {
					
					var t = Ext.getCmp('datetree');
					var troot = t.root;
					if (node.leaf == true) {
						var node1 = new Ext.tree.TreeNode({
									text : node.text
								});
						if (checked == true) {
							// t.root.expand(t.getNodeById(node.id));
							troot.appendChild(new Ext.tree.TreeNode({
										id : node.id,
										text : node.text
									}));
							count++;
							Ext.getCmp('blr').setText('办理人数(' + count + ')');
						} else {
							var rn = t.getNodeById(node.id);
							if (rn != null) {
								troot.removeChild(rn);
							}
							count--;
							Ext.getCmp('blr').setText('办理人数(' + count + ')');
						}
					} else {
						if (checked == true) {
							node.eachChild(function(child) {
										troot
												.appendChild(new Ext.tree.TreeNode(
														{
															id : child.id,
															text : child.text
														}));
										count++;
									});
							Ext.getCmp('blr').setText('办理人数(' + count + ')');
						} else {
							node.eachChild(function(child) {
										var rn = t.getNodeById(child.id);
										if (rn != null) {
											troot.removeChild(rn);
											count--;
										}
									});
							Ext.getCmp('blr').setText('办理人数(' + count + ')');
						}
					}
					txtCheckValue = tree.getChecked('id');
					txtCheckName = tree.getChecked('text');
				
				},tree);
	 		}
	 	},{
	 		text:'取消',
	 		handler:function(){
	 			
	 		}
	 	}]
	 });
	var win=new Ext.Window({
		 id:'addPrompt',
	     width:600,
	     height:Ext.getBody().getHeight()-100,
	     title:'新增提醒',
	     closable:true,
		 collapsible:true,
	     autoScroll:true,
	     bodyStyle:'padding:5px 5px',
	     items:form/*new Ext.Panel({
		        title: '',        
			  	height:Ext.getBody().getHeight()-150,
			  	bodySyle:'width:100%',
		        html: '<iframe frameborder="0" src="'+url+'" width="100%" height="100%"></iframe>'
	     })*/
	 });
	 
	 
	 win.show();
	 
	 
}