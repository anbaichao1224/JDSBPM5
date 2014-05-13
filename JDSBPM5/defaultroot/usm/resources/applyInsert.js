/*
 * Ext JS Library 2.0.1
 * Copyright(c) 2006-2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */

Ext.onReady(function(){

    Ext.QuickTips.init();

    // turn on validation errors beside the field globally
    Ext.form.Field.prototype.msgTarget = 'side';

    var bd = Ext.getBody();

    /*
     * ================  Simple form  =======================
     */ 


    var simple = new Ext.FormPanel({
        labelWidth: 80, // label settings here cascade unless overridden
        url:'save-form.php',
        fileUpload: true,
        frame: true,
        title: '应用目录注册信息',
        bodyStyle:'padding:5px 5px 0',
        width: 350,
        defaults: {width: 230},
        defaultType: 'textfield',

        items: [{
                fieldLabel: '中文名称',
                    name: 'application.cnname',
                    allowBlank:false,
		            blankText:'名称不能为空'  
                },{fieldLabel: '英文名称',
                    name: 'application.enname'
                },

	            {
                    fieldLabel: '链接',
                    name: 'application.navurl',
                    value:'/usm/systemInfo.do',
                    allowBlank:false,
		            blankText:'名称不能为空'  
                },
              {
	            xtype: 'fileuploadfield',
	            id: 'form-file',
	            emptyText: '请选择图片',
	            fieldLabel: '子系统图片',
	            name: 'file',
	            buttonCfg: {
	                text: '打开',
	                iconCls: 'upload-icon'
	            }
        	  }
                ,
                new  Ext.form.TextArea({    //TextArea   
                fieldLabel:'描述',   
                hideParent:true,   
                preventScrollbars:true,   
                name:'application.memo' 
            	}),
       
          		  new Ext.form.Hidden({   //hidden   
                	name:'application.sysid',
                	value:sysid
           		}),
           		new Ext.form.Hidden({   //hidden   
                	name:'application.parentappcatalogid',
                	value:parentappcatalogid
           		}),
           		 new Ext.form.Checkbox({fieldLabel:'可用',   
	                name:'enabled',
	                checked:false,   
	                boxLabel:'是否可用'}),
	             new Ext.form.Checkbox({fieldLabel:'目录',   
	                name:'haschild',   
	                checked:false,   
	                boxLabel:'是否有子目录'
	                })
               ],

        buttons: [{
            text: '确定',
            handler: function(){
            simple.getForm().submit({url:'/usm/appSave.do',method:'POST',waitMsg:'Saving Data...',success:function(form,action){ 
			Ext.Msg.alert('操作','应用添加成功');
			}, failure:function(form,action){ 
			Ext.Msg.alert('操作','应用添加失败');
			}
            });
        }
        },{
            text: '重置',
            handler:function(){ 
			simple.getForm().reset(); 
			} 
	 }]
    });

    simple.render(document.body);

});