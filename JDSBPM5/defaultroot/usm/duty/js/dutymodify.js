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
               		   id:'dutyid',      
		               xtype:'textfield',                
		               fieldLabel: '* 职务编号',                
		               name: 'duty.dutyid',     
		               readOnly:true, 
		               value:dutyid,           
		               anchor:'65%'
	                },{      
               		  
		               id:'cnname',
		               xtype:'textfield',                
		               fieldLabel: '* 职务名称',                
		               name: 'duty.cnname',    
		               allowBlank:false,
		               blankText:'职务名称不能为空',
		               value:cnname,           
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
	                }
	                ,
	                {      
               		   id:'dutydesc',      
		               xtype:'textfield',                
		               fieldLabel: '显示顺序',                
		               name: 'duty.dutydesc',
		               readOnly:true,  
		               value:dutydesc,           
		               anchor:'65%'
	                },{
	               
	               xtype:'textfield',	
	               fieldLabel: '子系统ID',	
	               name: 'duty.sysid',
	               value:sysid,
	               readOnly:true,
		           anchor:'65%'
	            }
                   ],       
  buttons: [{
            text: '保存',
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
                        
                        url: '/usm/dutyModify.do?dutyid='+dutyid,
                        method: 'POST',
                        waitTitle: '请稍等……', 
                        waitMsg: '正在上传数据……',  
                        success: function() {
                            Ext.Msg.alert("成功", "修改成功！");
                             window.location="getdutyDetailInfo.do?dutyid="+dutyid; 
                            this.disabled=false;
                        },
                        failure: function() {
                            Ext.Msg.alert("出错啦", "数据修改失败！");
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