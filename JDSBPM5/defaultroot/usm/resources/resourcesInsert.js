
Ext
		.onReady( function() {

			Ext.QuickTips.init();
			Ext.form.Field.prototype.msgTarget = 'side';
			var bd = Ext.getBody();
			var textfieldWidth = 500;
		    var store = new Ext.data.Store({
		        
		        proxy: new Ext.data.HttpProxy({   
		            url: '/usm/getSsoInfo.do?limit=100&start=0'   // 从此页获取数据
		        }),
		         reader: new Ext.data.JsonReader({
		            root: 'data',
		            id: 'ssoid',
		            fields: ['uuid','sso_name']
		        })
		    });
		    store.load()
		    var combo = new Ext.form.ComboBox({
		    	   width:300,
		    	   height:50,
		    	   hiddenName:'sso_uuid',
		    	   editable: false,
		    	   triggerAction:'all',
		    	   emptyText:'请选择SSO系统..',
		    	   handleHeight:5,
		    	   listWidth :270,
		    	   maxHeight:100,
		    	   fieldLabel: '所属系统',
		    	   readOnly:true,
		    	   mode: 'local',
		    	   valueField:'uuid',
		    	   displayField:'sso_name',
		    	   store:store
		    });
			/*
			 * ================  Simple form  =======================
			 */
			var simple = new Ext.FormPanel(
					{
						labelWidth :75, 
						fileUpload :true,
						frame :true,
						title :'添加模块注册信息',
						bodyStyle :'padding:0px 0px 0',
						width :760,
						height :540,
						align:'center',
						defaults : {
							width :230
						},
						defaultType :'textfield',
						items : [
								{
									fieldLabel :'中文名称',
									name :'module.cnname',
									allowBlank :false,
									width:textfieldWidth,
									blankText :'名称不能为空'
								},
								{
									fieldLabel :'英文名称',
									id :'enname',
									name :'module.enname',
									allowBlank :false,
									width:textfieldWidth,
									blankText :'名称不能为空',
									listeners : {
										'change' : function() {
											var enname = document
													.getElementById("enname").value;
											Ext.Ajax
													.request( {
														method :'get',
														url :"/usm/moduleisEnname.do?n="
																+ Math.random()
																+ "&enname="
																+ enname,
														success : function(
																response,
																options) {
															var responseArray = Ext.util.JSON
																	.decode(response.responseText);
								
														}

													});

										}
									}
								}, {
									fieldLabel :'功能链接',
									name :'module.funcurl',
									allowBlank :false,
									width:textfieldWidth,
									blankText :'名称不能为空'
								}, {
									fieldLabel :'导航链接',
									name :'module.navurl',
									allowBlank :false,
									width:textfieldWidth,
									blankText :'名称不能为空'
								}, {
									xtype :'fileuploadfield',
									id :'form-file',
									width:textfieldWidth,
									emptyText :'请选择图片',
									fieldLabel :'显示图片',
									name :'file',
									buttonCfg : {
										text :'打开',
										iconCls :'upload-icon'
									}
								}
//								, {
//									fieldLabel :'排序',
//									width:textfieldWidth,
//									name :'order'
//								}
								, {
									xtype :'textarea',
									fieldLabel :'表达式代码',
									width:textfieldWidth,
									name :'company'
								}, {
									xtype :'textarea',
									fieldLabel :'描               述',
									width:textfieldWidth,
									name :'module.memo'
								}
//								, new Ext.form.Checkbox({
//									fieldLabel :'导航条',
//									name :'leftframeenabled',
//									checked :false,
//									boxLabel :'左导航条可用设置'
//								})
								, new Ext.form.Checkbox( {
									fieldLabel :'禁用模块',
									name :'enabled',
									checked :false,
									boxLabel :'勾选将导致子系统所有用户（包括管理员）无法使用该模块'
								}), new Ext.form.Checkbox( {
									fieldLabel :'权限控制',
									name :'needright',
									checked :false,
									boxLabel :'是否需要权限控制'
								})
//								, new Ext.form.Checkbox( {
//									fieldLabel :'管理',
//									name :'adminflag',
//									checked :false,
//									boxLabel :'是否可管理'
//								})
								,{
							        
							          xtype:"fieldset",
							          checkboxName:"otherIfno",
							          title:"代理登录",
							          collapsed: true,
							          width:660,
							          collapsible: true,
							          checkboxToggle:false,//关键参数，其他和以前的一样            
							          autoHeight:true,           
							          border:true,
							          labelSeparator:'：',
							          items:[
							             new Ext.form.Checkbox( {
											fieldLabel :'代理登录',
											name :'ifsso',
											checked :false,
											boxLabel :'是否需要代理登录'
										}),combo]
								}
						],
						buttonAlign :'center',
						buttons : [ {
							text :'保   存',
							handler : function() {
								simple.getForm().submit( {
									url :'/usm/moduleSave.do',
									method :'POST',
									waitMsg :'正在保存数据...',
									success : function(form, action) {
										Ext.Msg.alert('操作', '模块保存成功');

									},
									failure : function(form, action) {
										Ext.Msg.alert('操作', '模块添加失败');
									}
								});
							}
						}, {
							text :'取  消',
							handler : function() {
								simple.getForm().reset();
							}
						} ]
					});
			simple.render(document.body);

		});