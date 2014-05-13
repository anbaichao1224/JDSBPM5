
// 人员窗体
function createPersonPositionWindow(positionid, positionname) {
	var txtCheckValue;
	var txtCheckName;
	var nid = document.getElementById("personid").value;
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
				width :220,
				
				closeAction :'close',
				closable :true,
				collapsible :true,
				autoScroll :false,
				items :new Ext.tree.TreePanel( {
					id :'forum-tree',
					region :'west',
					title :'',
					split :true,
					width :200,
					height :Ext.getBody().getHeight()-30,
					checkModel:'single',
					collapsible :true,
					margins :'0 0 5 5',
					animCollapse :false,
					animate :true,
					collapseMode :'mini',
					rootVisible :false,
					loader :new Ext.tree.TreeLoader( {
						url :'/usm/orgtreeJson.do'
					}),
					rootVisible :true,
					lines :true,
					autoScroll :true,
					root :root

				}),

				buttons : [
						{
							text :'确定',
							handler : function() {
								var jsonData = "";

								jsonData = txtCheckValue;
							
								if (jsonData.length == 0) {
									Ext.MessageBox.alert('消息',
											'请在以下人员中选择您要添加到此岗位人员 。');
								} else {
									// alert(positionid+positionname);
									
									Ext.getCmp("positionWin")
									.close();
									var cname = txtCheckName;
									document.getElementById("sendrange").value=cname;
									document.getElementById("personid").value=txtCheckValue;
								}
							}
						}, {
							text :'取消',
							handler : function() {
								win.close();
							}
						} ]

			});

	win.show();

	var tree = Ext.getCmp('forum-tree');
	tree.setRootNode(root);
	tree.expandAll();
	tree.render();
	tree
			.on(
					'beforeload',
					function(node) {
						tree.loader.url = '/usm/orgtreeJson.do?name=person&choose=true&childOrgId=' + node.id + '';
					});

	tree.on('checkchange', function(node, checked) {
		node.expand();
		// alert("schice:"+node.id);
		node.attributes.checked = checked;
		var parentNode = node.parentNode;
		if (checked) {
			if (parentNode != null && parentNode.id != 'root') {
				// 如果是选中,把父节点保持选中状态
			parentNode.ui.toggleCheck(true);
			txtCheckValue = tree.getChecked('id');
			txtCheckName = tree.getChecked('text');
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
				parentNode.attributes.checked = chk;
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
        
	}
}
