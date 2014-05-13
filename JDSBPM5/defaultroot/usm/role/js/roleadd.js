Ext.onReady(function(){

    Ext.QuickTips.init();

    // turn on validation errors beside the field globally
    Ext.form.Field.prototype.msgTarget = 'side';

    

    var fsf = new Ext.FormPanel({
		labelAlign: 'right',    
		title: '新增角色信息',    
		buttonAlign:'right',    
		bodyStyle:'padding:8px',    
		anchor:'100%',  
		frame:true,    
        items: [{               
		               xtype:'textfield',                
		               fieldLabel: '角色名称',                
		               name: 'CnName',                
		               anchor:'50%'
	                }, 
	                {
	
	                    xtype:'textfield',	
	                    fieldLabel: '角色说明',	
	                    name: 'RoleDesc',
		                anchor:'50%'
	
	                }, 
	                 {
	
	                    xtype:'textfield',	
	                    fieldLabel: '是否管理用',	
	                    name: 'RoleDesc',
		                anchor:'50%'
	
	                }, 
	                
	
	                   new Ext.form.DateField({
	                	fieldLabel: '创建时间',
	                	name: 'CreateTime',
	                	anchor:'50%'   
                   })],       

 buttons: [{
            text: '提交',
            handler: function(){
                if(fsf.form.isValid()) {
                    this.disabled=true;    
                    flag = false;
                    fsf.getForm().submit({
                        
                        url: 'studentServlet?cmd=add',
                        method: 'POST',
                        waitTitle: '请稍等……', 
                        waitMsg: '正在上传数据……',  
                        success: function() {
                            Ext.Msg.alert("成功", "添加成功！");
                            this.disabled=false;
                        },
                        failure: function() {
                            Ext.Msg.alert("出错啦", "数据保存失败！");
                            this.disabled=false;
                        }
                    });
                    win.close();
                }
            }
        }, {
            text: '重置',
            handler: function() {
                fsf.form.reset();
            }
        }, {
            text: '返回',
            handler: function() {
               window.location="roleListgrid.jsp"; 
            }
        }
        
        
        
        ]

    });

    fsf.render(document.body);

   
});