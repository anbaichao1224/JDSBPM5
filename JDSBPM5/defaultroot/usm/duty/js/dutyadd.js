Ext.onReady(function(){

    Ext.QuickTips.init();

    // turn on validation errors beside the field globally
    Ext.form.Field.prototype.msgTarget = 'side';

    

    var fsf = new Ext.FormPanel({
		labelAlign: 'right',    
		title: '',    
		buttonAlign:'center',    
		bodyStyle:'padding:8px',    
		anchor:'100%',  
		frame:true,    
        items: [ 
	                {
	                    id:'cnname',
	                    xtype:'textfield',	
	                    fieldLabel: '* 角色名称',	
	                    name: 'duty.cnname',
		                anchor:'65%',
		                allowBlank:false,		               		               
		                blankText:'角色名称不能为空'
	
	                }, 
	                 {
	                    xtype:'textfield',	
	                    fieldLabel: '标识',	
	                    name: 'duty.adminflag',
		                anchor:'65%'
	
	                },
	                {
                        xtype:'textfield',	
	                    fieldLabel: '排序',	
	                    name: 'duty.dutydesc',
		                anchor:'65%'
                   
                   }
                  ],       

 buttons: [{
            text: '提交',
            handler: function(){
            	
            	var cnname = Ext.get('cnname').dom.value;
            	if(cnname=='')
            	{
            		Ext.MessageBox.alert('角色名称','角色名称不能为空');
            	}
            	
                if(fsf.form.isValid()) {
                    this.disabled=true;    
                    flag = false;
                    fsf.getForm().submit({
                        
                        url: '/usm/dutySave.do',
                        method: 'POST',
                        waitTitle: '请稍等……', 
                        waitMsg: '正在上传数据……',  
                        success: function() {
                            Ext.Msg.alert("成功", "操作成功！");
                            this.disabled=false;
                        },
                        failure: function() {
                            Ext.Msg.alert("出错啦", "数据保存失败！");
                            this.disabled=false;
                        }
                    });
                    
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
            	window.parent.location.replace('/usm/duty/dutyqueryListgrid.jsp');
             //  window.location="roleListgrid.jsp"; 
            }
        }
        
        
        
        ]

    });

    fsf.render(document.body);

   
});