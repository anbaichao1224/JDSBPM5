/*
 * 
 * 应用菜单管理
 * 
 */

Ext.onReady(function(){

    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';
    var bd = Ext.getBody();
    var tabs = new Ext.FormPanel({
        labelWidth: 80,
        fileUpload: true,
        frame: true,
        border:true,
        width: 330,
        height:320,
        title:'',
        layout:'form',
        defaults: {width: 230},
        defaultType: 'textfield',
        items: [{
                fieldLabel: '中文名称',
                    name: 'application.cnname',
                    allowBlank:true,
                    value:cnname
                },{fieldLabel: '英文名称',
                    name: 'application.enname',
                    value:enname
                },
	            {
                    fieldLabel: '链接',
                    name: 'application.navurl',
                    value:'/usm/systemInfo.do'
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
                value:memo,   
                name:'application.memo' 
            	})
               	,
                new Ext.form.Checkbox({fieldLabel:'可用',   
	                name:'enabled',
	                checked:enabled,   
	                boxLabel:'是否可用'}),
	                
          		  new Ext.form.Hidden({   //hidden   
                	name:'application.appcatalogid',
                	value:appcatalogid
                	
           		})],
        buttonAlign:'center',
        buttons: [{
            text: '修  改',
            handler:function(){ 
			tabs.getForm().submit({url:'/usm/appupdate.do',method:'POST',waitMsg:'正在保存数据...',success:function(form,action){ 
				Ext.Msg.alert('操作','数据修改成功');
				
			}, failure:function(form,action){ 
				Ext.MessageBox.alert('错误', '修改子系统失败!'); 
			}
            });
			} 
        },{
            text: '删  除',
            handler:function(){ 
                Ext.MessageBox.confirm('消息', '确认删除吗？',applicationRemove);
			} 
        }]
    });

    tabs.render(document.body);
    function applicationRemove(btn){
	if(btn=='yes'){
			tabs.getForm().submit({url:'/usm/appremove.do',method:'POST',waitMsg:'正在保存数据...',success:function(form,action){ 
				Ext.Msg.alert('操作','数据删除成功');
				
			}, failure:function(form,action){ 
				Ext.MessageBox.alert('错误', '删除子系统失败!'); 
			}
            });
            }
	};

});