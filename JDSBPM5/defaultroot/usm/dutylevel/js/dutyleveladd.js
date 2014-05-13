Ext.onReady(function(){

    Ext.QuickTips.init();

    // turn on validation errors beside the field globally
    Ext.form.Field.prototype.msgTarget = 'side';

    

    var fsf = new Ext.FormPanel({
		labelAlign: 'right',    
		title: '新增职级信息',    
		buttonAlign:'right',    
		bodyStyle:'padding:8px',    
		anchor:'100%',  
		frame:true,    
       items: [{
            layout:'column',
            border:false,
            labelSeparator:'：',
            items:[{
               columnWidth:.5,            
               layout: 'form',            
               border:false,            
               items:[{                
		               xtype:'textfield',                
		               fieldLabel: '职级名称',                
		               name: 'CnName',                
		               anchor:'50%'
	                }, 
	                {	                    
	                    xtype:'textfield',	
	                    fieldLabel: '级别',	
	                    name: 'LevelGrade',
		                anchor:'50%'
	                }, 
	                {
	
	                    xtype:'textfield',	
	                    fieldLabel: '说明',	
	                    name: 'LevelDesc',
		                anchor:'50%'
	
	                }]
              },{
                columnWidth:.4,
                layout: 'form',
                border:false, 
                items: [{
                        xtype:'textfield',	
	                    fieldLabel: '排序号',	
	                    name: 'LevelTabIndex',
		                anchor:'50%'
	
		                }, 
		                {
						id:'sysid',
	                    xtype:'textfield',	
	                    fieldLabel: '子系统名称',	
	                    name: 'level.sysid',
		                anchor:'65%'
	                 }
	                ,
		                new Ext.form.DateField({
	                	fieldLabel: '创建时间',
	                	name: 'CreateTime',
	                	anchor:'50%'
            		})]
              }]

        }],

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
                            Ext.Msg.alert("成功", "操作成功！");
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
               window.location="dutylevelListgrid.jsp"; 
            }
        }
        ]

    });

    fsf.render(document.body);

   
});