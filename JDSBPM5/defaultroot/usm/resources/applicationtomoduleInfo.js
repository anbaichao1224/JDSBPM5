
Ext.onReady(function(){

    Ext.QuickTips.init();

    // turn on validation errors beside the field globally
    Ext.form.Field.prototype.msgTarget = 'side';

    var bd = Ext.getBody();

    /*
     * ================  Simple form  =======================
     */ 

    var simple = new Ext.FormPanel({
        labelWidth: 75, // label settings here cascade unless overridden
        frame:true,
        fileUpload: true,
        title: '添加模块注册信息',
        bodyStyle:'padding:5px 5px 0',
        width: 350,
        defaults: {width: 230},
        defaultType: 'textfield',

        items: [{
                fieldLabel: '中文名称',
                    name: 'module.cnname',
                    allowBlank:true,
                    value:cnname
                },{fieldLabel: '英文名称',
                    name: 'module.enname',
                    value:enname
                },{
                    fieldLabel: '功能URL',
                    name: 'module.funcurl',
                    value:funcurl
                },{
                    fieldLabel: '导航链接',
                    name: 'module.navurl',
                    value:navurl
                },
               {
	            xtype: 'fileuploadfield',
	            id: 'form-file',
	            emptyText: '请选择图片',
	            fieldLabel: '显示图片',
	            name: 'file',
	            buttonCfg: {
	                text: '<img src="'+icon+'" width=40 height=19 border=0/>',
	                iconCls: 'upload-icon'
	            }
        		},{
                    fieldLabel: '排序',
                    name: 'order'
                },{
                	xtype:'textarea',
                    fieldLabel: '表达式代码',
                    name: 'company'
                },{
	                xtype:'textarea',
                    fieldLabel: '描述',
                    name: 'memo'
                },new Ext.form.Hidden({
                	name:'module.uuid',
                	value:uuid
                }),new Ext.form.Hidden({
                	name:'txtCheckValue',
                	value:'application'
                }),
          		 new Ext.form.Checkbox({fieldLabel:'导航条',   
	                name:'leftframeenabled',   
	                checked:checked1,   
	                boxLabel:'左导航条可用设置'}),
                new Ext.form.Checkbox({fieldLabel:'模块',   
	                name:'enabled',   
	                checked:checked2,   
	                boxLabel:'模块是否可用'}),
                new Ext.form.Checkbox({fieldLabel:'权限控制',   
	                name:'needright',   
	                checked:checked3,   
	                boxLabel:'是否需要权限控制'}),
                new Ext.form.Checkbox({fieldLabel:'管理',   
	                name:'adminflag',   
	                checked:checked4,   
	                boxLabel:'是否可管理'
	                })	
               ]
		/*
        buttons: [{
            text: '删除',
            handler:function(){ 
             Ext.MessageBox.confirm('消息', '确认删除吗？',moduleRemove);
			} 
			}]
			*/
    });

	 simple.render(document.body);
	 
	 function moduleRemove(btn){
	 if(btn=='yes'){
	 simple.getForm().submit({url:'/usm/appcatalogmoduleRemove.do',method:'POST',waitMsg:'正在保存数据...',success:function(form,action){ 
				Ext.Msg.alert('操作','数据删除成功');
				
			}, failure:function(form,action){ 
				Ext.MessageBox.alert('错误', '删除资源失败!'); 
			}
            }); 
            }
	 }
 //   simple.render(document.getElementById("one"));
//   document.getElementById("two").style.display = "none";

});