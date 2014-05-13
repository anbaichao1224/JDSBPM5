

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
	                    xtype:'textfield',	
	                    fieldLabel: '* 岗位名称',	
	                    name: 'position.positionname',
		                anchor:'90%'
	
	                }],  

 buttons: [{
            text: '保  存',
            handler: function(){
                if(fsf.form.isValid()) {
                    this.disabled=true;    
                    flag = false;
                  
                    fsf.getForm().submit({
                        
                        url: '/usm/postionSave.do?sysid='+sysid+'&type='+type,
                        method: 'POST',
                        waitTitle: '请稍等……', 
                        waitMsg: '正在上传数据……',  
                        success: function() {
                            Ext.Msg.alert("成功", "操作成功！",showposition);
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
             window.parent.location.replace("/usm/position/positionqueryListgrid.jsp?sysid="+sysid+"&type="+type+"");
            }
        }
        ]

    });

    fsf.render(document.body);
    function showposition()
    {
    	 window.location="positionaddwindow.jsp?sysid="+sysid+"";
    }
	

})



