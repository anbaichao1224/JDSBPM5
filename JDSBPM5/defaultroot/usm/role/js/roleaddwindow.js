Ext.onReady(function(){

    Ext.QuickTips.init();
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
	                    name: 'role.cnname',
		                anchor:'65%',
		                allowBlank:false,		               		               
		                blankText:'角色名称不能为空'
	
	                }
                  ],       

 buttons: [{
            text: '保  存',
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
                        
                        url: '/usm/roleSave.do?sysid='+sysid+'&type='+type+'',
                        method: 'POST',
                        waitTitle: '请稍等……', 
                        waitMsg: '正在上传数据……',  
                        success: function() {
                            Ext.Msg.alert("成功", "操作成功！",showrole);
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
            text: '取  消',
            handler: function() {
                fsf.form.reset();
            }
        }, {
            text: '退  出',
            handler: function() {
            	window.parent.location.replace('/usm/role/rolequeryListgrid.jsp?sysid='+sysid+'&type='+type+'');
            }
        }

        ]

    });
    function showrole()
    {
    	window.location="roleaddwindow.jsp?sysid="+sysid+"";    	
    }
    fsf.render(document.body);
});