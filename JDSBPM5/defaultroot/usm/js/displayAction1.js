function createSystemWindow(sysid) {
	var win;
	if (!win) {
		win = new Ext.Window(
				{
					title :'系统详细信息',
					collapsible :true,
					width :900,
					height :500,
				id :Ext.id(),
					shim :false,
					animCollapse :false,
					constrainHeader :true,
					maximizable :true,
					items :new Ext.TabPanel(
							{
								autoTabs :true,
								region :'center',
								margins :'3 3 3 0',
								defaults : {
									autoScroll :true
								},
								items : [
									/*	{
											id :Ext.id(),
											height :500,
											title :'基本信息',
											html :'<iframe name="personall" id="personall" scrolling="yes" frameborder="0" src="/usm/systemInfo.do?sysid=' + sysid + '" height=100% width=100%></iframe>'
										},
										{
										id :Ext.id(),
											height :500,
											title :'模块列表',
											html :'<iframe name="personall" id="personall" scrolling="yes" frameborder="0" src="/usm/sysmoduleAllGrid.do?sysid=' + sysid + '" height=100% width=100%></iframe>'
										},*/
										{
										id :Ext.id(),
											title :'部门列表',
											height :500,
											html :'<iframe name="personall" id="personall" scrolling="yes" frameborder="0" src="/usm/resources/departgrid.jsp?sysid=' + sysid + '" height=100% width=100%></iframe>'
										},
										{
										id :Ext.id(),
											title :'系统管理员',
											height :500,
											html :'<iframe name="administrator" id="administrator" scrolling="yes" frameborder="0" src="/usm/resources/administratorgrid.jsp?sysid=' + sysid + '" height=100% width=100%></iframe>'
										},{
											id:Ext.id(),
											title : '安全监控管理员',
											height : 500,
											html : '<iframe name="administrator" id="administrator" scrolling="yes" frameborder="0" src="/usm/resources/secureistratorgrid.jsp?sysid=' + sysid + '" height=100% width=100%></iframe>'
										} ],
								activeTab :0,
								deferredRender :true,
								border :false
							})
				});
		win.show();

	}
}

function createModuleWindow(moduleid) {
	var win;
	
	
	if (!win) {
		win = new Ext.Window(
				{
					title :'模块详细信息',
					collapsible :true,
					width :700,
					height :565,
					id :Ext.id(),
					constrainHeader :true,
					html :'<iframe name="'+Ext.id()+'" id="'+Ext.id()+'" scrolling="yes" frameborder="0" src="/usm/moduleInfo.do?moduleid=' + moduleid + '" height=100% width=100%></iframe>'
					
				});
	}
	win.show();
}

function createOrgWindow(orglevel, parentorgid) {
	var win = new Ext.Window(
			{
				title :"添加部门基本信息",
				width :600,
				height :260,
				maximizable :true,
				closeAction :'close',
				closable :true,
				collapsible :true,
				autoScroll :false,
				items :new Ext.Panel(
						{
							title :'',
							height :300,
							width :600,
							html :'<iframe scrolling="yes" frameborder="0" src="/usm/depart/departaddwindow.jsp?orglevel='
									+ orglevel
									+ '&parentorgid='
									+ parentorgid
									+ '" height=100% width=100%></iframe>'
						})

			});
	win.show();
}

function createPersonWindow(orgid) {
	var win = new Ext.Window(
			{
				title :"添加人员基本信息",
				width :720,
				height :470,
				closable :true,
				collapsible :true,
				autoScroll :true,
				items :new Ext.Panel(
						{
							title :'',
							height :435,
							width :700,
							html :'<iframe scrolling="yes" frameborder="0" src="/usm/person/personInsertwindow.jsp?orgid=' + orgid + '" height=100% width=100%></iframe>'
						})
			});
	win.show();
}

//岗位人员窗体
function createPersonPositionWindow(positionid, positionname) {
	var txtCheckValue;
	var root = new Ext.tree.AsyncTreeNode( {
		text :'组织机构',
		leaf :false,
		cls :'folder',
		draggable :false,
		id :'toproot'
	});

	var win = new Ext.Window(
			{
				id :'positionWin',
				title :'人员列表',
				width :220,
				height :520,
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
					height :510,

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
									Ext.Ajax
											.request( {
												//请求地址
												method :'POST',
												url :'/usm/addPositionPersonSave.do?positionid='
														+ positionid
														+ '&personIdJsonData='
														+ jsonData,
												//成功时回调      
												success : function(response,
														options) {
													Ext.MessageBox.alert('消息',
															'添加成功');
													Ext.getCmp("positionWin")
															.close();
													//  Ext.get('views').dom.src= 'position/positionInfo.do?sysid='+sysid+'&type=manager&positionid='+positionid;
												},
												//失败时回调      
												failure : function(response,
														options) {
													Ext.MessageBox
															.show( {
																title :'消息',
																msg :'添加失败！',
																buttons :Ext.Msg.OK,
																icon :Ext.MessageBox.ERROR
															});
												}
											});

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
	tree.render();

	tree
			.on(
					'beforeload',
					function(node) {
						tree.loader.url = '/usm/orgtreeJson.do?name=person&choose=true&childOrgId=' + node.id + '';
					});
/*	tree.on('click',function(node){
		
	})*/
	tree.on('checkchange', function(node, checked) {
		node.expand();
		node.attributes.checked = checked;
		var parentNode = node.parentNode;

		if (checked) {
			if (parentNode != null && parentNode.id != 'root') {
				//如果是选中,把父节点保持选中状态   
			parentNode.ui.toggleCheck(true);
			txtCheckValue = tree.getChecked('id');
		}
	} else {
		//如果所有子节点都没选中，取消根节点选中状态   
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
		txtCheckValue = tree.getChecked('id');

	}, tree);


}

//职务人员窗体
function createPersonDutyWindow(dutyid) {
	var txtCheckValue;
	var root = new Ext.tree.AsyncTreeNode( {
		text :'组织机构',
		leaf :false,
		cls :'folder',
		draggable :false,
		id :'toproot'
	});

	var win = new Ext.Window(
			{
				id :'dutyWin',
				title :'人员列表',
				width :220,
				height :520,
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
					height :510,

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
											'请在以下人员中选择您要添加到此角色人员 。');
								} else {
									Ext.Ajax
											.request( {
												//请求地址
												method :'POST',
												url :'/usm/addPersonDutySave.do?dutyid='
														+ dutyid
														+ '&dutyidjsonData='
														+ jsonData,
												//成功时回调      
												success : function(response,
														options) {
													Ext.MessageBox.alert('消息',
															'添加成功');
													Ext.getCmp("dutyWin")
															.close();
												},
												//夫败时回调      
												failure : function(response,
														options) {
													Ext.MessageBox
															.show( {
																title :'消息',
																msg :'添加失败！',
																buttons :Ext.Msg.OK,
																icon :Ext.MessageBox.ERROR
															});
												}
											});

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
	tree.render();

	tree
			.on(
					'beforeload',
					function(node) {
						tree.loader.url = '/usm/orgtreeJson.do?name=person&choose=true&childOrgId=' + node.id + '';
					});

	tree.on('checkchange', function(node, checked) {
		node.expand();
		node.attributes.checked = checked;
		var parentNode = node.parentNode;

		if (checked) {
			if (parentNode != null && parentNode.id != 'root') {
				//如果是选中,把父节点保持选中状态   
			parentNode.ui.toggleCheck(true);
			txtCheckValue = tree.getChecked('id');
		}
	} else {
		//如果所有子节点都没选中，取消根节点选中状态   
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
		txtCheckValue = tree.getChecked('id');

	}, tree);

}

//人员用户组窗体
function createPersonUserGroupWindow(grpid) {
	var txtCheckValue;
	var root = new Ext.tree.AsyncTreeNode( {
		text :'组织机构',
		leaf :false,
		cls :'folder',
		draggable :false,
		id :'toproot'
	});

	var win = new Ext.Window( {
		id :'usergroupWin',
		title :'人员列表',
		width :220,
		height :400,
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
			height :380,
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
							Ext.MessageBox
									.alert('消息', '请在以下人员中选择您要添加到此用户组人员 。');
						} else {
							Ext.Ajax.request( {
								//请求地址
								method :'POST',
								url :'/usm/addPersonUserGroupSave.do?grpid='
										+ grpid + '&grpidjsonData=' + jsonData,
								//成功时回调      
								success : function(response, options) {
									Ext.MessageBox.alert('消息', '添加成功');
									Ext.getCmp("usergroupWin").close();
								},
								//夫败时回调      
								failure : function(response, options) {
									Ext.MessageBox.show( {
										title :'消息',
										msg :'添加失败！',
										buttons :Ext.Msg.OK,
										icon :Ext.MessageBox.ERROR
									});
								}
							});
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
	tree.render();

	tree
			.on(
					'beforeload',
					function(node) {
						tree.loader.url = '/usm/orgtreeJson.do?name=person&choose=true&childOrgId=' + node.id + '';
					});

	tree.on('checkchange', function(node, checked) {
		node.expand();
		node.attributes.checked = checked;
		var parentNode = node.parentNode;

		if (checked) {
			if (parentNode != null && parentNode.id != 'root') {
				//如果是选中,把父节点保持选中状态   
			parentNode.ui.toggleCheck(true);
			txtCheckValue = tree.getChecked('id');
		}
	} else {
		//如果所有子节点都没选中，取消根节点选中状态   
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
		txtCheckValue = tree.getChecked('id');

	}, tree);

}
//---------------------------------------------
//               发送消息人员窗体                                     
//---------------------------------------------
function createMessagePersonWindow(messageInput,toPersonIds) {
	var txtCheckValue;
	var txtCheckName;
	var root = new Ext.tree.AsyncTreeNode( {
		text :'通讯录',
		leaf :false,
		cls :'folder',
		draggable :false,
		id :'toproot'
	});
	var win = new Ext.Window({
		id :'txlWin',
		title :'人员列表',
		width :312,
		height :445,
		closeAction :'close',
		closable :true,
		collapsible :true,
		autoScroll :false,
		items :new Ext.tree.TreePanel( {
			id :'message-tree',
			region :'west',
			title :'',
			split :true,
			width :280,
			height :370,
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
		buttons : [{
						text :' 确  定  ',
						handler : function() {
							var jsonData = "";
							jsonData = txtCheckValue;
							if (jsonData.length == 0) {
								Ext.MessageBox.alert('消息', '请在以下人员中选择您要发送消息的人员 。');
							}else{
								messageInput.setValue(txtCheckName);
								toPersonIds.setValue(txtCheckValue);
								//返回信息到主窗口
								win.close();
							}
						}
					}, {
						text :' 取  消  ',
						handler : function() {
							win.close();
						}
					}]
	});
	win.show();
	
	var tree = Ext.getCmp('message-tree');
	tree.setRootNode(root);
	tree.render();
	tree.on('beforeload',function(node) {
		
		tree.loader.url = '/usm/orgtreeJson.do?name=person&choose=true&childOrgId=' + node.id + '';
		
	});
	tree.on('checkchange', function(node, checked) {
		node.expand();
		node.attributes.checked = checked;
		var parentNode = node.parentNode;
		if (checked) {
			if (parentNode != null && parentNode.id != 'root') {
				//如果是选中,把父节点保持选中状态   
			parentNode.ui.toggleCheck(true);
			txtCheckValue = tree.getChecked('id');
			txtCheckName = tree.getChecked('text');
		}
	} else {
		//如果所有子节点都没选中，取消根节点选中状态   
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
		txtCheckValue = tree.getChecked('id');
		txtCheckName = tree.getChecked('text');
	}, tree);

}

//部门用户组窗体
var store2 = new Ext.data.Store( {
	proxy :new Ext.data.HttpProxy( {
		url :'/usm/getUserGroupNotDepartInfo.do' //从此页获取部门信息
		}),
	reader :new Ext.data.JsonReader( {
		root :'data',
		totalProperty :'totalCount',
		id :'orgid',
		fields : [ {
			name :'orgid',
			type :'string'
		}, {
			name :'cnname',
			type :'string'
		}, {
			name :'enname',
			type :'string'
		}, {
			name :'status',
			type :'string'
		}, {
			name :'orgtype',
			type :'string'
		}, {
			name :'intel',
			type :'string'
		}, {
			name :'memo',
			type :'string'
		}, {
			name :'orglevel',
			type :'string'
		}, {
			name :'outtel',
			type :'string'
		}, {
			name :'nighttel',
			type :'string'
		}, {
			name :'fax',
			type :'string'
		}, {
			name :'serialindex',
			type :'string'
		} ]
	})
});
store2.setDefaultSort('orgid', 'desc');
var sm2 = new Ext.grid.CheckboxSelectionModel(); // add checkbox column
var cm2 = new Ext.grid.ColumnModel( [ sm2, new Ext.grid.RowNumberer( {
	header :'序号',
	width :32,
	align :'center'
}), {
	id :'orgid', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
	header :"部门编号",
	dataIndex :'orgid',
	align :'center',
	hidden :true,
	width :60
}, {
	header :"部门名称",
	dataIndex :'cnname',
	align :'center',
	allowBlank :false,
	blankText :'部门名称不能为空',
	width :100
}, {
	header :"英文名称",
	dataIndex :'enname',
	align :'center',
	allowBlank :false,
	blankText :'英文名称不能为空',
	width :100
}, {
	header :"状态",
	dataIndex :'status',
	hidden :true,
	align :'center',
	width :100
}, {
	header :"部门类型",
	dataIndex :'orgtype',
	align :'center',
	hidden :true,
	width :100
}, {
	header :"内线电话",
	dataIndex :'intel',
	align :'center',
	width :100
}, {
	header :"备注",
	dataIndex :'memo',
	align :'center',
	hidden :true,
	width :100
}, {
	header :"部门级别",
	dataIndex :'orglevel',
	align :'center',
	hidden :true,
	width :100
}, {
	header :"外线电话",
	dataIndex :'outtel',
	align :'center',
	hidden :true,
	width :100
}, {
	header :"夜间值班电话",
	dataIndex :'nighttel',
	align :'center',
	hidden :true,
	width :100
}, {
	header :"传真",
	dataIndex :'fax',
	align :'center',
	hidden :true,
	width :100
}, {
	header :"排序",
	dataIndex :'serialindex',
	align :'center',
	hidden :true,
	width :100
} ]);
cm2.defaultSortable = true;

function querynameMessage() {
	// var name = Ext.getCmp('name').getValue();
	var name = Ext.get('name').dom.value;

	store.load( {
		params : {
			start :0,
			limit :17,
			name :name
		}
	});

}

function querydnameMessage() {
	// var name = Ext.getCmp('name').getValue();

	var name = Ext.get('dname').dom.value;
	store2.load( {
		params : {
			start :0,
			limit :17,
			name :name
		}
	});

}
//persongrid2
var dname = new Ext.form.TextField( {
	width :175,
	name :'dname',
	allowBlank :true
});

function createOrgUserGroupWindow(grpid) {
	var txtCheckValue;
	var win = new Ext.Window(
			{
				id :'orgusergroupWin',
				width :600,
				height :450,
				shim :true,
				animCollapse :true,
				constrainHeader :true,
				layout :'fit',
				items :new Ext.grid.GridPanel( {
					id :'orgusergroupGid',
					width :500,
					height :250,
					title :'',
					store :store2,
					cm :cm2,
					sm :sm2,
					trackMouseOver :false,
					loadMask :true,
					autoShow :true,
					viewConfig : {
						forceFit :true,
						enableRowBody :true,
						showPreview :true
					},
					bbar :new Ext.PagingToolbar( {
						pageSize :17,
						store :store2,
						displayInfo :true,
						displayMsg :'当前显示{0} - {1}  条记录 /共 {2}条记录',
						emptyMsg :"无显示数据"

					})

				}),
				buttons : [
						{
							text :'确定',
							handler : function() {
								var row = Ext.getCmp("orgusergroupGid")
										.getSelections();
								var jsonData = "";
								for ( var i = 0, len = row.length; i < len; i++) {
									var ss = row[i].get("orgid");
									jsonData = jsonData + ss + ",";
								}
								if (jsonData.length == 0) {
									Ext.MessageBox.alert('消息',
											'请在以下部门选择您要添加的用户组 。');
								} else {
									Ext.Ajax
											.request( {
												//请求地址
												method :'POST',
												url :'/usm/addOrgUserGroupSave.do?grpid='
														+ grpid
														+ '&grpidjsonData='
														+ jsonData,
												//成功时回调      
												success : function(response,
														options) {
													Ext.MessageBox.alert('消息',
															'添加成功');
													win.close();
												},
												//夫败时回调      
												failure : function(response,
														options) {
													Ext.MessageBox
															.show( {
																title :'消息',
																msg :'添加失败！',
																buttons :Ext.Msg.OK,
																icon :Ext.MessageBox.ERROR
															});
												}
											});

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
	store2.load( {
		params : {
			start :0,
			limit :17
		}
	});
}

function createPersonRoleWindow(roleid) {
	var txtCheckValue;
	var root = new Ext.tree.AsyncTreeNode( {
		text :'组织机构',
		leaf :false,
		cls :'folder',
		draggable :false,
		id :'toproot'
	});

	var win = new Ext.Window(
			{
				id :'roleWin',
				title :'人员列表',
				width :300,
				height :400,
				//  maximizable:true,
				closeAction :'close',
				closable :true,
				collapsible :true,
				autoScroll :false,
				items :new Ext.tree.TreePanel( {
					id :'forum-tree',
					region :'west',
					title :'',
					split :true,
					width :300,
					height :380,
					minSize :175,
					maxSize :400,
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
											'请在以下人员中选择您要添加到此角色人员 。');
								} else {
									Ext.Ajax
											.request( {
												//请求地址
												method :'POST',
												url :'/usm/addPersonRoleSave.do?roleid='
														+ roleid
														+ '&roleidjsonData='
														+ jsonData,
												//成功时回调      
												success : function(response,
														options) {
													Ext.MessageBox.alert('消息',
															'添加成功');
													Ext.getCmp("roleWin")
															.close();
												},
												//夫败时回调      
												failure : function(response,
														options) {
													Ext.MessageBox
															.show( {
																title :'消息',
																msg :'添加失败！',
																buttons :Ext.Msg.OK,
																icon :Ext.MessageBox.ERROR
															});
												}
											});

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
	tree.render();
	tree.on('beforeload',function(node) {
		tree.loader.url = '/usm/orgtreeJson.do?choose=true&name=person&childOrgId=' + node.id + '';
	});
	tree.on('checkchange', function(node, checked) {
		node.expand();
		node.attributes.checked = checked;
		var parentNode = node.parentNode;
		if (checked) {
			if (parentNode != null && parentNode.id != 'root') {
				//如果是选中,把父节点保持选中状态   
			parentNode.ui.toggleCheck(true);
			txtCheckValue = tree.getChecked('id');
		}
	} else {
		//如果所有子节点都没选中，取消根节点选中状态   
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
		txtCheckValue = tree.getChecked('id');

	}, tree);
}

var storeRole = new Ext.data.Store( {
	proxy :new Ext.data.HttpProxy( {
		url :'/usm/getRoleNotDepartInfo.do' //从此页获取部门信息
		}),
	reader :new Ext.data.JsonReader( {
		root :'data',
		totalProperty :'totalCount',
		id :'orgid',
		fields : [ {
			name :'orgid',
			type :'string'
		}, {
			name :'cnname',
			type :'string'
		}, {
			name :'enname',
			type :'string'
		}, {
			name :'status',
			type :'string'
		}, {
			name :'orgtype',
			type :'string'
		}, {
			name :'intel',
			type :'string'
		}, {
			name :'memo',
			type :'string'
		}, {
			name :'orglevel',
			type :'string'
		}, {
			name :'outtel',
			type :'string'
		}, {
			name :'nighttel',
			type :'string'
		}, {
			name :'fax',
			type :'string'
		}, {
			name :'serialindex',
			type :'string'
		} ]
	})
});
storeRole.setDefaultSort('orgid', 'desc');

var smRole = new Ext.grid.CheckboxSelectionModel(); // add checkbox column
var cmRole = new Ext.grid.ColumnModel( [ smRole, new Ext.grid.RowNumberer( {
	header :'序号',
	width :32,
	align :'center'
}), {
	id :'orgid', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
	header :"部门编号",
	dataIndex :'orgid',
	align :'center',
	hidden :true,
	width :60
}, {
	header :"部门名称",
	dataIndex :'cnname',
	align :'center',
	allowBlank :false,
	blankText :'部门名称不能为空',
	width :100
}, {
	header :"英文名称",
	dataIndex :'enname',
	align :'center',
	allowBlank :false,
	blankText :'英文名称不能为空',
	width :100
}, {
	header :"状态",
	dataIndex :'status',
	hidden :true,
	align :'center',
	width :100
}, {
	header :"部门类型",
	dataIndex :'orgtype',
	align :'center',
	hidden :true,
	width :100
}, {
	header :"内线电话",
	dataIndex :'intel',
	align :'center',
	width :100
}, {
	header :"备注",
	dataIndex :'memo',
	align :'center',
	hidden :true,
	width :100
}, {
	header :"部门级别",
	dataIndex :'orglevel',
	align :'center',
	hidden :true,
	width :100
}, {
	header :"外线电话",
	dataIndex :'outtel',
	align :'center',
	hidden :true,
	width :100
}, {
	header :"夜间值班电话",
	dataIndex :'nighttel',
	align :'center',
	hidden :true,
	width :100
}, {
	header :"传真",
	dataIndex :'fax',
	align :'center',
	hidden :true,
	width :100
}, {
	header :"排序",
	dataIndex :'serialindex',
	align :'center',
	hidden :true,
	width :100
} ]);
cmRole.defaultSortable = true;

function createOrgRoleWindow(roleid) {
	var txtCheckValue;
	var win = new Ext.Window( {
		id :'roleWin',
		title :'部门列表',
		width :600,
		height :450,
		shim :true,
		animCollapse :true,
		constrainHeader :true,
		layout :'fit',
		items :new Ext.grid.GridPanel( {
			id :'rolegid',
			width :500,
			height :250,
			title :'',
			store :storeRole,
			cm :cmRole,
			sm :smRole,
			trackMouseOver :false,
			loadMask :true,
			autoShow :true,
			viewConfig : {
				forceFit :true,
				enableRowBody :true,
				showPreview :true
			},
			bbar :new Ext.PagingToolbar( {
				pageSize :17,
				store :storeRole,
				displayInfo :true,
				displayMsg :'当前显示{0} - {1}  条记录 /共 {2}条记录',
				emptyMsg :"无显示数据"

			})

		}),
		buttons : [
				{
					text :'确  定',
					handler : function() {
						var row = Ext.getCmp("rolegid").getSelections();
						var jsonData = "";
						for ( var i = 0, len = row.length; i < len; i++) {
							var ss = row[i].get("orgid");
							jsonData = jsonData + ss + ",";
						}
						if (jsonData.length == 0) {
							Ext.MessageBox
									.alert('消息', '请在以下人员中选择您要添加的角色部门人员 。');
						} else {
							Ext.Ajax.request( {
								//请求地址
								method :'POST',
								url :'/usm/addOrgRoleSave.do?roleid=' + roleid
										+ '&roleidjsonData=' + jsonData,
								//成功时回调      
								success : function(response, options) {
									Ext.MessageBox.alert('消息', '添加成功');
									win.close();
								},
								//夫败时回调      
								failure : function(response, options) {
									Ext.MessageBox.show( {
										title :'消息',
										msg :'添加失败！',
										buttons :Ext.Msg.OK,
										icon :Ext.MessageBox.ERROR
									});
								}
							});

						}
					}
				}, {
					text :'取  消',
					handler : function() {
						win.close();
					}
				} ]
	});

	win.show();
	storeRole.load( {
		params : {
			start :0,
			limit :10
		}
	});
}
var storePosition = new Ext.data.Store( {
	proxy :new Ext.data.HttpProxy( {
		url :'/usm/positionNotRoleQery.do' //从此页获取选择人员数据
		}),
	reader :new Ext.data.JsonReader( {
		root :'data',
		totalProperty :'totalCount',
		id :'positionid',
		fields : [ {
			name :'positionid',
			type :'string'
		}, {
			name :'positionname',
			type :'string'
		}, {
			name :'positiondesc',
			type :'string'
		} ]
	})
});
storePosition.setDefaultSort('positionid', 'desc');

var smPosition = new Ext.grid.CheckboxSelectionModel(); // add checkbox column
var cmPosition = new Ext.grid.ColumnModel( [ smPosition,
		new Ext.grid.RowNumberer( {
			header :'序号',
			width :32,
			align :'center'
		}), {
			id :'positionid', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
			header :"岗位编号",
			dataIndex :'positionid',
			hidden:true,
			align :'center',
			width :60
		}, {
			header :"岗位名称",
			dataIndex :'positionname',
			align :'center',
			width :100
		}, {
			header :"显示顺序",
			dataIndex :'positiondesc',
			align :'center',
			hidden :'true',
			width :60
		} ]);
cmPosition.defaultSortable = true;
function createPositionRoleWindow(roleid) {
	var txtCheckValue;
	var win = new Ext.Window(
			{
				id :'positionWin',
				title :'岗位列表',
				width :600,
				height :450,
				shim :true,
				animCollapse :true,
				constrainHeader :true,
				layout :'fit',
				items :new Ext.grid.GridPanel(
						{
							id :'positiongid',
							width :500,
							height :250,
							title :'',
							store :storePosition,
							cm :cmPosition,
							sm :smPosition,
							trackMouseOver :false,
							loadMask :true,
							autoShow :true,
							viewConfig : {
								forceFit :true,
								enableRowBody :true,
								showPreview :true
							},
							bbar :new Ext.PagingToolbar( {
								pageSize :17,
								store :storePosition,
								displayInfo :true,
								displayMsg :'当前显示{0} - {1}  条记录 /共 {2}条记录',
								emptyMsg :"无显示数据"

							}),
							buttons : [
									{
										text :'确定',
										handler : function() {
											var row = Ext.getCmp("positiongid")
													.getSelections();
											var jsonData = "";
											for ( var i = 0, len = row.length; i < len; i++) {
												var ss = row[i]
														.get("positionid");
												jsonData = jsonData + ss + ",";
											}
											if (jsonData.length == 0) {
												Ext.MessageBox.alert('消息',
														'请在以下人员中选择您要添加的角色人员 。');
											} else {
												Ext.Ajax
														.request( {
															//请求地址
															method :'POST',
															url :'/usm/addPositionRoleSave.do?roleid='
																	+ roleid
																	+ '&roleidjsonData='
																	+ jsonData,
															//成功时回调      
															success : function(
																	response,
																	options) {
																Ext.MessageBox
																		.alert(
																				'消息',
																				'添加成功');
																// store.reload();
																win.close();
															},
															//夫败时回调      
															failure : function(
																	response,
																	options) {
																Ext.MessageBox
																		.show( {
																			title :'消息',
																			msg :'添加失败！',
																			buttons :Ext.Msg.OK,
																			icon :Ext.MessageBox.ERROR
																		});
															}
														});

											}
										}
									}, {
										text :'取消',
										handler : function() {
											win.close();
										}
									} ]
						})
			});
	win.show();
	storePosition.load( {
		params : {
			start :0,
			limit :10
		}
	});
}

var storeUserGroup = new Ext.data.Store( {
	proxy :new Ext.data.HttpProxy( {
		url :'/usm/getUserGroupNotRoleInfo.do' //从此页获取选择用户组数据
		}),
	reader :new Ext.data.JsonReader( {
		root :'data',
		totalProperty :'totalCount',
		id :'grpid',
		fields : [ {
			name :'grpid',
			type :'string'
		}, {
			name :'grpname',
			type :'string'
		}, {
			name :'grpflag',
			type :'string'
		}, {
			name :'grpdesc',
			type :'string'
		}, {
			name :'createtime',
			type :'string'
		}
		//{name: 'createtime', type: 'string'}   			
		]
	})
});
storeUserGroup.setDefaultSort('grpid', 'desc');

var smUserGroup = new Ext.grid.CheckboxSelectionModel(); // add checkbox column
var cmUserGroup = new Ext.grid.ColumnModel( [ smUserGroup,
		new Ext.grid.RowNumberer( {
			header :'序号',
			width :32,
			align :'center'
		}), {
			id :'grpid', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
			header :"用户组编号",
			dataIndex :'grpid',
			align :'center',
			hidden :true,
			width :60
		}, {
			header :"用户组名称",
			dataIndex :'grpname',
			align :'center',
			width :100
		}, {
			header :"标 识",
			dataIndex :'grpflag',
			align :'center',
			hidden :true,
			width :100
		}, {
			header :"创建时间",
			dataIndex :'createtime',
			format :'Y-m-d h:i:s',
			align :'center',
			width :100
		}, {
			header :"排序",
			dataIndex :'grpdesc',
			align :'center',
			hidden :true,
			width :100
		} ]);
cmUserGroup.defaultSortable = true;
function createUserGroupRoleWindow(roleid) {
	var txtCheckValue;
	var win = new Ext.Window( {
		id :'usergroupWin',
		title :'用户组列表',
		width :600,
		height :450,
		shim :true,
		animCollapse :true,
		constrainHeader :true,
		layout :'fit',
		items :new Ext.grid.GridPanel( {
			id :'usergroupgid',
			width :500,
			height :250,
			title :'',
			store :storeUserGroup,
			cm :cmUserGroup,
			sm :smUserGroup,
			trackMouseOver :false,
			loadMask :true,
			autoShow :true,
			viewConfig : {
				forceFit :true,
				enableRowBody :true,
				showPreview :true
			},
			bbar :new Ext.PagingToolbar( {
				pageSize :17,
				store :storeUserGroup,
				displayInfo :true,
				displayMsg :'当前显示{0} - {1}  条记录 /共 {2}条记录',
				emptyMsg :"无显示数据"

			}),
			buttons : [
					{
						text :'确  定',
						handler : function() {
							var row = Ext.getCmp("usergroupgid")
									.getSelections();
							var jsonData = "";
							for ( var i = 0, len = row.length; i < len; i++) {
								var ss = row[i].get("grpid");
								jsonData = jsonData + ss + ",";
							}
							if (jsonData.length == 0) {
								Ext.MessageBox.alert('消息',
										'请在以下人员中选择您要添加的角色人员 。');
							} else {
								Ext.Ajax.request( {
									//请求地址
									method :'POST',
									url :'/usm/addUserGroupRoleSave.do?roleid='
											+ roleid + '&roleidjsonData='
											+ jsonData,
									//成功时回调      
									success : function(response, options) {
										Ext.MessageBox.alert('消息', '添加成功');
										win.close();
									},
									//失败时回调      
									failure : function(response, options) {
										Ext.MessageBox.show( {
											title :'消息',
											msg :'添加失败！',
											buttons :Ext.Msg.OK,
											icon :Ext.MessageBox.ERROR
										});
									}
								});

							}
						}
					}, {
						text :'取  消',
						handler : function() {
							win.close();
						}
					} ]
		})
	});
	win.show();
	storeUserGroup.load( {
		params : {
			start :0,
			limit :10
		}
	});
}