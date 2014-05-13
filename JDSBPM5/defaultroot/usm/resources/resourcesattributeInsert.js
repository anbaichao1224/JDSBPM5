/*
 * Ext JS Library 2.0.1
 * Copyright(c) 2006-2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */

Ext.onReady(function(){

    Ext.QuickTips.init();

	 var simple = new Ext.FormPanel({
        labelWidth: 110, // label settings here cascade unless overridden
        frame:true,
        title: '',
        bodyStyle:'padding:5px',
        width: 400,
        defaults: {width: 250},
        defaultType: 'textfield',

        items: [{
	            xtype: 'fileuploadfield',
	            id: 'form-file',
	            emptyText: '请选择图片',
	            fieldLabel: '桌面快捷图片',
	            name: 'file',
	            buttonCfg: {
	                text: '打开',
	                iconCls: 'upload-icon'
	            }
        		},{
                    fieldLabel: '桌面快捷键高度',
                    name: 'moduleproperties.propertiesfastheight'
                },{
                    fieldLabel: '桌面快捷键宽度',
                    name: 'moduleproperties.propertiesfastwidth'
                },{
                    fieldLabel: '桌面快捷键排序',
                    name: 'moduleproperties.propertiesfastsort'
                },{
	            xtype: 'fileuploadfield',
	            id: 'form-file2',
	            emptyText: '请选择图片',
	            fieldLabel: '桌面窗口图片(小)',
	            name: 'file2',
	            buttonCfg: {
	                text: '打开',
	                iconCls: 'upload-icon'
	            }
        		},{
	            xtype: 'fileuploadfield',
	            id: 'form-file3',
	            emptyText: '请选择图片',
	            fieldLabel: '缩略图',
	            name: 'file3',
	            buttonCfg: {
	                text: '打开',
	                iconCls: 'upload-icon'
	            }
        		},{
                    fieldLabel: '窗口高度',
                    name: 'moduleproperties.propertieswindowheight'
                },{
                    fieldLabel: '窗口宽度',
                    name: 'moduleproperties.propertieswindowwidth'
                },{
                    fieldLabel: '窗口位置X',
                    name: 'moduleproperties.propertieswindowx'
                },{
                    fieldLabel: '窗口位置Y',
                    name: 'moduleproperties.propertieswindowy'
                },{
                    fieldLabel: '窗口样式',
                    name: 'moduleproperties.propertieswindowstyle'
                },{
                    fieldLabel: '窗口显示方式',
                    name: 'moduleproperties.propertieswindowtype'
                },
                new Ext.form.Checkbox({
                 fieldLabel:'桌面快捷键显示',   
	                name:'moduleproperties.propertiesfastshow',   
	                checked:true
	              }),
	                  new Ext.form.Checkbox({fieldLabel:'桌面窗口显示',   
	                name:'moduleproperties.propertieswindowshow',   
	                checked:true
	                }),
                new Ext.form.Checkbox({fieldLabel:'窗口最大化',   
	                name:'moduleproperties.propertieswindowmax',   
	                checked:true}),
	            new Ext.form.Checkbox({fieldLabel:'窗口最小化',   
	                name:'moduleproperties.propertieswindowmin',   
	                checked:true}),
	           	new Ext.form.Checkbox({fieldLabel:'窗口关闭',   
	                name:'moduleproperties.propertieswindowclose',   
	                checked:true}),
                new Ext.form.Checkbox({fieldLabel:'用户删除桌面窗口',   
	                name:'PROPERTIESWINDOWUPDATE',   
	                checked:true}),
	            new Ext.form.Checkbox({fieldLabel:'用户删除此快捷键',   
	                name:'PROPERTIESFASTUPDATE',   
	                checked:true})
          
               ],
                 buttons: [{
	            text: '确定',
	            handler: function(){
	            simple.getForm().submit({url:'/usm/modulepropertiesSave.do',method:'GET',waitMsg:'正在保存数据...',success:function(form,action){ 
				alert("success"); 
				}, failure:function(form,action){ 
				alert("failure"); 
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