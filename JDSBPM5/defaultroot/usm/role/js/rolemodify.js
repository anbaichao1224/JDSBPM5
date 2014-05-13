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
        items: [ {
	      			    
	                    xtype:'textfield',	
	                    fieldLabel: '* 角色编号',	
	                    name: 'role.roleid',
	                    allowBlank:false,
	                    blankText:'角色编号不能为空',
		                anchor:'65%',
		                readOnly:true, 
		                value:roleid
	
	                },{
	      			    
	                    xtype:'textfield',	
	                    fieldLabel: '* 角色名称',	
	                    name: 'role.cnname',
	                    allowBlank:false,
	                    blankText:'角色名称不能为空',
		                anchor:'65%',
		                value:cnname
	
	                },
	                {
	      			    
	                    xtype:'textfield',	
	                    fieldLabel: '创建时间',	
	                    name: 'role.createtime',
	                    format:'Y-m-d h:i:s',
	                    readOnly:true, 
		                anchor:'65%',
		                value:createtime
	
	                },
	                {
                        
                        xtype:'textfield',	
	                    fieldLabel: '排序',	
	                    name: 'role.roledesc',
		                anchor:'65%',
	                    readOnly:true, 
		                value:roledesc
                   
                   }],       

 buttons: [{
            text: '保存',
            handler: function(){
                if(fsf.form.isValid()) {
                    this.disabled=true;    
                    flag = false;
                    	
                  // alert(roleid);
                    fsf.getForm().submit({
                        
                        url: '/usm/roleModify.do?roleid='+roleid,
                        method: 'POST',
                        waitTitle: '请稍等……', 
                        waitMsg: '正在上传数据……',  
                        success: function() {
                            Ext.Msg.alert("成功", "操作成功！");
                             window.location="getroleDetailInfo.do?roleid="+roleid; 
                            this.disabled=false;
                        },
                        failure: function() {
                            Ext.Msg.alert("成功啦", "数据修改成功！");
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