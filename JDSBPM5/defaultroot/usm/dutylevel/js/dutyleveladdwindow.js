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
	                    fieldLabel: '* 职级名称',	
	                    name: 'level.cnname',
	                    allowBlank:false,
	                    blankText:'职级名称不能为空',
		                anchor:'65%'
	
	                }        
	                ],  

 buttons: [{
            text: '提交',
            handler: function(){
            	var cnname=Ext.get('cnname').dom.value;
            	if(cnname=='')
            	{
            		Ext.MessageBox.alert('职级名称','职级名称不能为空');
            	}
                if(fsf.form.isValid()) {
                    this.disabled=true;    
                    flag = false;
                    fsf.getForm().submit({
                        
                        url: '/usm/levelSave.do?sysid='+sysid+'',
                        method: 'POST',
                        waitTitle: '请稍等……', 
                        waitMsg: '正在上传数据……',  
                        success: function() {
                            Ext.Msg.alert("成功", "操作成功！",showduty);
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
             window.parent.location.replace('/usm/dutylevel/dutylevelqueryListgrid.jsp?sysid='+sysid+'&type='+type+'');
            }
        }
        ]

    });

    fsf.render(document.body);
   function showduty()
   {
   	window.location="dutyleveladdwindow.jsp?sysid="+sysid+"";
   }
   
});