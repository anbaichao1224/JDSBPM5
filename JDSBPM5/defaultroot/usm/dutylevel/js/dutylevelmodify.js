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
	                    id:'levelid',
	                    xtype:'textfield',	
	                    fieldLabel: '* 职级编号',	
	                    name: 'level.levelid',
	                    value:levelid,
	                    readOnly:true,  
		                anchor:'65%'
	
	                },{
	                    id:'cnname',
	                    xtype:'textfield',	
	                    fieldLabel: '* 职级名称',	
	                    name: 'level.cnname',
	                    value:cnname,
	                     allowBlank:false,//不允许为空
		           		blankText:'职级名称不能为空',//错误提示内容  
		           		checkboxToggle:true,
		           		
		                anchor:'65%'
	
	                },{
	                    id:'createtime',
	                    xtype:'textfield',	
	                    fieldLabel: '创建时间',	
	                    name: 'level.createtime',
	                    format:'Y-m-d h:i:s',
	                    value:createtime,
	                    readOnly:true, 
		                anchor:'65%'
	
	                },{
	                    id:'leveldesc',
	                    xtype:'textfield',	
	                    fieldLabel: '职级排序',	
	                    name: 'level.leveldesc',
	                    value:leveldesc,
	                    readOnly:true,  
		                anchor:'65%'
	
	                }
	               	  ],  

 buttons: [{
            text: '修改',
            handler: function(){
                if(fsf.form.isValid()) {
                
                    this.disabled=true;    
                    flag = false;
                    fsf.getForm().submit({
                        url: '/usm/dutyLevelModify.do?levelid='+levelid,
                        method: 'POST',
                        waitTitle: '请稍等……', 
                        waitMsg: '正在上传数据……',  
                        success: function() {
                            Ext.Msg.alert("成功", "修改成功！");
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
        }
        ]

    });

    fsf.render(document.body);

   
});