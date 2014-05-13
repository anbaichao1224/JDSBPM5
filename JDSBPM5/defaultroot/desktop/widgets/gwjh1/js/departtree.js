
// 人员窗体
function createPersonPositionWindow(positionid, positionname) {
	var txtCheckValue;
	var txtCheckName;
	var ids = document.getElementById("id").value;
    var disabledIds='';
	var _width = 300;
	var _height = Ext.getBody().getHeight()-40;
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
	var win = new Ext.Window(
			{
				id :'positionWin',
				title :'人员列表',
				//width :220,
				closeAction :'close',
				closable :true,
				collapsible :true,
				autoScroll :false,
				layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					y:0,
				/*items :new Ext.tree.TreePanel( {
					id :'dept-tree',
					region :'west',
					title :'',
					split :true,
					width :200,
					height :Ext.getBody().getHeight()-30,
					checkModel:'cascade',
					collapsible :true,
					margins :'0 0 5 5',
					animCollapse :false,
					animate :true,
					collapseMode :'mini',
					rootVisible :false,
					loader :new Ext.tree.TreeLoader( {
						url :'deptAction_findAllDepts.action'
					}),
					rootVisible :true,
					lines :true,
					autoScroll :true,
					root :root

				}),*/
                html:"<iframe id='orgtreeiframe' name='orgtreeiframe' width='" + (_width-10)  + "' height='" +
                 (_height - 50) + "' src='deptAction_addtree.action?checked="+ids+"&disabled="+disabledIds+"'></iframe>",
				buttons : [
						{
							text :'确定',
							handler : function() {
							var retValue = document.getElementById('orgtreeiframe').contentWindow.getCheckedNames();
	                        document.getElementById("deptnames").value=retValue.names;
							document.getElementById("id").value=retValue.ids;
								/*var jsonData = "";

								jsonData = txtCheckValue;
							
								if (jsonData.length == 0) {
									Ext.MessageBox.alert('消息',
											'请选择要发送的部门 。');
								} else {
									// alert(positionid+positionname);
									
									
									var cname = txtCheckName;
									document.getElementById("deptnames").value=cname;
									document.getElementById("id").value=txtCheckValue;
									
									
								}*/
							Ext.getCmp("positionWin")
									.close();
							}
						}, {
							text :'取消',
							handler : function() {
								win.close();
							}
						} ]

			});

	win.show();

	/*var tree = Ext.getCmp('dept-tree');
	tree.setRootNode(root);
	tree.expandAll();
	tree.render();
	tree.on('checkchange', function(node, checked) {
		node.expand();
		// alert("schice:"+node.id);
		node.attributes.checked = checked;
		var parentNode = node.parentNode;
		if (checked) {
			if (parentNode != null && parentNode.id != 'root') {
				// 如果是选中,把父节点保持选中状态
			parentNode.ui.toggleCheck(true);
			if(node.isLeaf()){
			  txtCheckValue = tree.getChecked('id');
			  txtCheckName = tree.getChecked('text');
			}
		}
	} else {
		// 如果所有子节点都没选中，取消根节点选中状态
			if (parentNode != null && parentNode.id != 'root') {
				var chk = false;
				parentNode.eachChild( function(child) {
					if (child.attributes.checked)
						chk = true;
				});
				parentNode.ui.toggleCheck(chk);
				//parentNode.attributes.checked = chk;
			}
		}
		node.eachChild( function(child) {
			child.ui.toggleCheck(checked);
			child.attributes.checked = checked;
			child.fireEvent('checkchange', child, checked);
		});
		if(this.checkModel=='single'){    
            // var nodes =this.getNodes(this);
            var startNode = this.getRootNode();    
            var nodes = [];    
            var f = function(){    
            	nodes.push(this);    
            };    
       
            startNode.cascade(f);       
            if(nodes && nodes.length>0){    
                for(var i=0,len=nodes.length;i<len;i++){    
                    if(nodes[i].id!=node.id){    
                        if(nodes[i].getUI().checkbox){    
                            nodes[i].getUI().checkbox.checked = false;    
                                nodes[i].attributes.checked=false;    
                        }     

                 
   }    
                }    
            }    
        }    
		
		txtCheckValue = tree.getChecked('id');
		txtCheckName = tree.getChecked('text');
	}, tree);
	getNodes:function(treePanel){    
        
	}*/
}
