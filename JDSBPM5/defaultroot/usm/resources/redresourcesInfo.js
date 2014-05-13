/*
 * 资源信息
 * 
 */

Ext.onReady(function(){

    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';
    var textfieldWidth = 360;
    var bd = Ext.getBody();
    
    var simple = new Ext.FormPanel({
        labelWidth: 75, // label settings here cascade unless overridden
        frame:true,
        fileUpload: true,
        //title: '添加模块注册信息',
        bodyStyle:'padding:5px 5px 0',
        width:500,
        defaults: {width: 230},
        defaultType: 'textfield',

        items: [{
                fieldLabel: '中文名称',
                    name: 'module.cnname',
                    allowBlank:true,
                    width:textfieldWidth,
                    value:cnname
                },{fieldLabel: '英文名称',
                    name: 'module.enname',
                    width:textfieldWidth,
                    value:enname
                },{
                    fieldLabel: '功能URL',
                    width:textfieldWidth,
                    name: 'module.funcurl',
                    value:funcurl
                },{
                    fieldLabel: '导航链接',
                    width:textfieldWidth,
                    name: 'module.navurl',
                    value:navurl
                },{
		            xtype: 'fileuploadfield',
		            id: 'form-file',
		            emptyText: '请选择图片',
		            fieldLabel: '显示图片',
		            width:textfieldWidth,
		            name: 'file',
		            buttonCfg: {
		                text: '<img src="'+icon+'" width=16 height=16 border=0/>',
		                iconCls: 'upload-icon'
		            }
        		}
//                ,{
//                    fieldLabel: '排序',
//                    width:textfieldWidth,
//                    name: 'order'
//                }
                ,{
                	xtype:'textarea',
                    fieldLabel: '表达式代码',
                    width:textfieldWidth,
                    name: 'company'
                },{
	                xtype:'textarea',
                    fieldLabel: '描述',
                    width:textfieldWidth,
                    name: 'module.memo',
                    value:memo
                },new Ext.form.Hidden({
                	name:'module.moduleid',
                	value:moduleid
                }),new Ext.form.Checkbox({fieldLabel:'禁用模块',   
	                name:'enabled',   
	                checked:checked2,   
	                boxLabel:'勾选将导致子系统所有用户（包括管理员）无法使用该模块'
	            }),new Ext.form.Checkbox({fieldLabel:'权限控制',   
	                name:'needright',   
	                checked:checked3,   
	                boxLabel:'是否需要权限控制'
	            })],
         buttons: [{
	             text :'修  改',
							handler : function() {
								simple.getForm().submit( {
									url :'/usm/moduleUpdate.do',
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
			}]
               
    	});
	 simple.render(document.body);
});