

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
	                    fieldLabel: '* 职务名称',	
	                    name: 'duty.cnname',
	                    allowBlank:false,
	                    blankText:'职务名称不能为空',
		                anchor:'75%'
	
	                }],  

 buttons: [{
            text: '保  存',
            handler: function(){
            var cnname=Ext.get('cnname').dom.value;
            	if(cnname=='')
            	{
            		Ext.MessageBox.alert('职务名称','职务名称不能为空');
            	}
                if(fsf.form.isValid()) {
                    this.disabled=true;    
                    flag = false;
                    fsf.getForm().submit({
                        
                        url: '/usm/dutySave.do?sysid='+sysid+'',
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
            text: '取  消',
            handler: function() {
                fsf.form.reset();
            }
        }, {
            text: '返  回',
            handler: function() {
            window.parent.location.replace('/usm/duty/dutyqueryListgrid.jsp?sysid='+sysid+'&type='+type+'');
            }
        }
        ]

    });

    fsf.render(document.body);
	function showduty()
	{
		window.location="dutyaddwindow.jsp?sysid="+sysid+"";
	}

})



