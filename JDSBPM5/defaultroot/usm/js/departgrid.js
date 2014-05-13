Ext.onReady( function() {
	Ext.QuickTips.init();
	var addForm;
	var newwin;
	var eventID;
	var number = 0;

	//创建 Store
		var store = new Ext.data.Store( {
			proxy :new Ext.data.HttpProxy( {
				url :"/usm/sysorgGrid.do?n=" + Math.random() + "&sysid="
						+ sysid + "" //从此页获取数据
			}),
			reader :new Ext.data.JsonReader( {
				root :'data',
				totalProperty :'totalCount',
				id :'uuid',
				fields : [ {
					name :'uuid',
					type :'string'
				}, {
					name :'sysid',
					type :'string'
				}, {
					name :'orgid',
					type :'string'
				}, {
					name :'flag',
					type :'string'
				} ]
			})
				});

		store.setDefaultSort('uuid', 'desc');

		var store2 = new Ext.data.Store( {
			proxy :new Ext.data.HttpProxy( {
				url :"/usm/orgGrid.do" //从此页获取数据
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
					name :'desc',
					type :'string'
				} ]
			})

				});
		store2.setDefaultSort('orgid', 'desc');
		function renderDateTime(value) {
			var tmpValue = value.replace(/@/g, "");
			if (tmpValue * 1 < 0)
				return "";
			var tmpDate = new Date(tmpValue * 1);
			return String.format('{0}', Ext.util.Format.date(tmpDate, 'Y/m/d'));
		}
		var sm = new Ext.grid.CheckboxSelectionModel(); // add checkbox column
		var cm = new Ext.grid.ColumnModel( [ sm, {
			id :'uuid', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
			header :"编号",
			dataIndex :'uuid',
			hidden :true,
			align :'center',
			width :60
		}, {
			header :"系统ID",
			dataIndex :'sysid',
			align :'center',
			hidden :true,
			width :100
		}, {
			header :"部门名称",
			dataIndex :'orgid',
			align :'center',
			width :60
		}, {
			header :"标志",
			dataIndex :'flag',
			align :'center',
			width :60
		} ]);
		cm.defaultSortable = true;
		var sm2 = new Ext.grid.CheckboxSelectionModel(); // add checkbox column
		var cm2 = new Ext.grid.ColumnModel( [ sm2, {
			header :"部门ID",
			dataIndex :'orgid',
			align :'center',
			hidden:true,
			width :60
		}, {
			header :"部门名称",
			dataIndex :'cnname',
			align :'center',
			width :100
		}, {
			header :"部门描述",
			dataIndex :'desc',
			align :'center',
			width :60
		} ]);
		cm2.defaultSortable = true;
		var cnname = new Ext.form.TextField( {
			width :175,
			id :'cnname',
			name :'cnname',
			allowBlank :true
		});
		// by default columns are sortable
		var grid = new Ext.grid.GridPanel( {
			el :'topic-grid',
			width :850,
			height :440,
			store :store,
			cm :cm,
			sm :sm,
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
				store :store,
				displayInfo :true,
				displayMsg :'当前显示{0} - {1}  条记录 /共 {2}条记录',
				emptyMsg :"无显示数据"
			}),
			tbar : [ '部门名称：', cnname, {// 查询按钮   
						id :'queryButton',
						icon :"/usm/img/search.png",
						text :"查 询",
						cls :"x-btn-text-icon",
						handler : function() {
							var cnname = Ext.getCmp('cnname').getValue();
							store.load( {
								params : {
									start :0,
									limit :25,
									cnname :cnname
								}
							});

					   }
					}, {
						text :'选择部门',
						icon :"/usm/img/add.gif",
						tooltip :'选择部门',
						cls :"x-btn-text-icon",
						handler : function() {
							showAddPanel();
							eventID = "add";
						}
					}, '-', {
						text :'删除部门',
						icon :"/usm/img/delete.gif",
						tooltip :'删除',
						cls :"x-btn-text-icon",
						handler :showDelMess
					} ]
		});

		grid.render();

		grid.on("rowdblclick", function(grid) {
			loadFormData(grid);
		});
		var loadFormData = function(grid) {
			var _record = grid.getSelectionModel().getSelected();
			if (!_record) {
				Ext.MessageBox.alert('提示', '请选择一笔记录');
			} else {

				showAddPanel();

			}
		}

		// trigger the data store load
		store.load( {
			params : {
				start :0,
				limit :17
			}
		});

		function toggleDetails(btn, pressed) {
			var view = grid.getView();
			view.showPreview = pressed;
			view.refresh();
		}

		var myFormWin = function() {
			showAddPanel();
		}
		function showAddPanel() {
			var txtCheckValue;
			var win;
			if (!win) {

				win = new Ext.Window(
						{
							id :'win1',
							title :'选择部门',
							width :500,
							height :250,
							x :140,
							y :50,
							shim :true,
							animCollapse :true,
							constrainHeader :true,
							layout :'fit',
							items :new Ext.grid.GridPanel( {
								id :'grid2',
								width :500,
								height :250,
								//title :'所有部门',
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
										text :'保存',
										handler : function() {
											var row = Ext.getCmp("grid2")
													.getSelections();
											var txtCheckValue = "";
											for ( var i = 0, len = row.length; i < len; i++) {
												var ss = row[i].get("orgid");
												txtCheckValue = txtCheckValue
														+ ss + ",";
											}
											Ext.Ajax
													.request( {
														method :'get',
														url :"/usm/sysorgSave.do?n="
																+ Math.random()
																+ "&sysid="
																+ sysid
																+ "&txtCheckValue="
																+ txtCheckValue,
														success : function(
																result, action) {
															Ext.MessageBox
																	.alert(
																			'消息',
																			'成功!')

														},
														failure : function(
																form, action) {
															Ext.MessageBox
																	.alert(
																			'消息',
																			'系统初始化失败!');
														}
													});
											store.reload();
											win.close();
										}
									}, {
										text :'取消',
										handler : function() {
											win.close();
											;
										}

									} ]

						});
			}
			win.show();
			store2.load( {
				params : {
					start :0,
					limit :17
				}
			});
		}
		function doSave() {
			var evt = eventID;

			Ext.MessageBox.show( {
				msg :'保存中...',
				progressText :'保存中...',
				width :200,
				wait :true,
				waitConfig : {
					interval :200
				},
				icon :'ext-mb-save',
				nimEl :'btnSave'
			});
			addForm.getForm().submit( {
				url :'Project.aspx?event=' + evt,
				method :'POST',
				success : function(form, action) {
					Ext.MessageBox.hide();
					Ext.MessageBox.alert('信息', '保存成功！');
					newwin.hide();
					//store.load({params:{start:0, limit:25}});
				store.reload();
			},
			failure : function(form, action) {
				Ext.MessageBox.hide();
				Ext.MessageBox.show( {
					title :'消息',
					msg :'保存失败！',
					buttons :Ext.Msg.OK,
					icon :Ext.MessageBox.ERROR
				});
			}
			});
		}

		function orgQuery() {

		}

		function showDelMess() {
			Ext.MessageBox.confirm('消息', '确认删除？', doDel);
		}
		function doDel(btn) {
			if (btn == 'yes') {
				var row = grid.getSelections();
				var jsonData = "";
				for ( var i = 0, len = row.length; i < len; i++) {
					var ss = row[i].get("uuid");
					jsonData = jsonData + ss + ",";
				}
				if (jsonData.length == 0) {
					Ext.MessageBox.alert('消息', '请选择要删除的数据。');
				} else {

					Ext.Ajax.request( {
						method :'get',
						url :"/usm/sysorgRemove.do?txtCheckValue=" + jsonData,
						success : function(result, action) {
							Ext.MessageBox.alert('消息', '成功!');
							store.reload();
						},
						failure : function(form, action) {
							Ext.MessageBox.alert('消息', '系统初始化失败!');
						}
					});

				}
			}

		}
	});
