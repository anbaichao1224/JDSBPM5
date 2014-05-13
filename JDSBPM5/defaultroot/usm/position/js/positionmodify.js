Ext.onReady(function(){

    Ext.QuickTips.init();


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
	               fieldLabel: '* 岗位编号',	
	               name: 'position.positionid',
	               value:positionid,
	               readOnly:true,     	                              
		           anchor:'65%'
	            },
       			{
	              
	               xtype:'textfield',	
	               fieldLabel: '* 岗位名称',	
	               name: 'position.positionname',
	               value:positionname,
	               allowBlank:false,//不允许为空
		           blankText:'岗位名称不能为空',//错误提示内容  
		           checkboxToggle:true,
		           	
		           anchor:'65%'
	            },{
	               
	               xtype:'textfield',	
	               fieldLabel: '排序',	
	               name: 'position.positiondesc',
	               value:positiondesc,
	               readOnly:true,
		           anchor:'65%'
	            },{
	               
	               xtype:'textfield',	
	               fieldLabel: '子系统ID',	
	               name: 'position.sysid',
	               value:sysid,
	               readOnly:true,
		           anchor:'65%'
	            }
                   ],       
         buttons: [{
            text: '保存',
            handler: function(){
            
                if(fsf.form.isValid()) {
                    this.disabled=true;    
                    flag = false;
                    fsf.getForm().submit({
                        url: '/usm/posititionModify.do?positionid='+positionid,
                        method: 'POST',
                        waitTitle: '请稍等……', 
                        waitMsg: '正在上传数据……',  
                        success: function() {
                            Ext.Msg.alert("成功", "操作成功！");
                             window.location="getPositionDetailInfo.do?positionid="+positionid; 
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