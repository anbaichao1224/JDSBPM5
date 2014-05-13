Ext
		.onReady( function() {

			Ext.QuickTips.init();
			var imgPanel = {
				xtype :'panel',
				html :"<img id='img' border='0' width='30' height='30' src='/upload/no.jpg'/>",
				border :false
			};



			var tabs = new Ext.FormPanel( {
				labelWidth :75,
				fileUpload :true,
				frame :true,
				width :'100%',
				height :'100%',
				layout :'form',
				defaults : {
					width :'95%'
				},
				defaultType :'textfield',
				items : [ {
					fieldLabel :'子系统名称',
					name :'system.syscnname',
					allowBlank :true,
					value :syscnname

				}, {
					fieldLabel :'子系统说明',
					name :'system.sysdesc',
					value :sysdesc

				}, {
					fieldLabel :'子系统链接',
					name :'system.url',
					value :url

				}, {
					xtype :'fileuploadfield',
					id :'form-file',
					emptyText :'请选择图片',
					fieldLabel :'子系统图片',
					name :'file',
					buttonCfg : {
						text :'打开',
						iconCls :'upload-icon'
					}
				}, new Ext.form.Checkbox( {
					fieldLabel :'子系统用户',
					name :'guestenable',
					checked :check,
					boxLabel :'是否允许Guest用户'
				})

				, imgPanel, new Ext.form.Hidden( { //hidden   
							name :'system.uuid',
							value :uuid
						}), new Ext.form.Hidden( { //hidden   
							name :'system.sysid',
							value :sysid
						}) ],

				buttons : [ {
					text :'修改',
					handler : function() {
						tabs.getForm().submit( {
							url :'/usm/systemUpdate.do',
							method :'POST',
							waitMsg :'正在保存数据...',
							success : function(form, action) {
								Ext.Msg.alert('操作', '修改子系统信息成功');

							},
							failure : function(form, action) {
								Ext.MessageBox.alert('错误', '修改子系统失败!');
							}
						});
					}
				}, {
					text :'删除',
					handler : function() {

						Ext.MessageBox.confirm('消息', '确认删除吗？', systemRemove);

					}
				} ]
			});

			tabs.render(document.body);
			document.getElementById("img").src = icon;
			function systemRemove(btn) {
				if (btn == 'yes') {
					tabs.getForm().submit( {
						url :'/usm/systemRemove.do',
						method :'POST',
						waitMsg :'正在保存数据...',
						success : function(form, action) {
							Ext.Msg.alert('操作', '删除子系统成功');

						},
						failure : function(form, action) {
							Ext.MessageBox.alert('错误', '删除子系统失败!');
						}
					});
				}
			}
			;

		});
