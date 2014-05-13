Ext.onReady(function(){

    Ext.QuickTips.init();

    // turn on validation errors beside the field globally
    Ext.form.Field.prototype.msgTarget = 'side';

    var bd = Ext.getBody();

    /*
     * ================  Simple form  =======================
     */ bd.createChild({tag: 'h2', html: ''});


    var simple = new Ext.FormPanel({
        labelWidth: 80, // label settings here cascade unless overridden
        title: '添加部门信息',
        labelAlign: 'right',
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
		               fieldLabel: '组织中文名称',                
		               name: 'CnName',                
		               anchor:'70%'
	                }, 
	                {
	                    xtype:'textfield',	
	                    fieldLabel: '英文简称',	
	                    name: 'EnName',
		                anchor:'70%'
	                }, 
	                {
	
	                    xtype:'textfield',	
	                    fieldLabel: '部门层数',	
	                    name: 'Orglevel',
		                anchor:'70%'
	
	                },
	                {
                    xtype:'textfield',
                    fieldLabel: '序列号码',
                    name: 'Orglevel',
                    anchor:'70%'
		                },
		                
		                {
                    xtype:'textfield',
		                    fieldLabel: '说明',
		                    name: 'Memo',
		                    anchor:'70%'
		                },
		                
		                new Ext.form.ComboBox({
	                    fieldLabel: '状态',
	                    name: 'Status',
	                    store: new Ext.data.SimpleStore({
	                        fields: ['Status'],
	                        data: [['0--正常'],['1--禁用'], ['2--删除']]
	                    }),
	                    displayField:'Status',
	                    emptyText:'选择状态……',
	                    mode: 'local',
	                    allowBlank:false,
	                    selectOnFocus:true,
	                    anchor:'95%'
                  })
		                ]
              },{
                columnWidth:.4,
                layout: 'form',
                border:false, 
                items: [
			            
                  {
                    xtype:'textfield',
		            fieldLabel: '部门类别',
		            name: 'OrgType',
		            anchor:'70%'
		          },
		          {
                    xtype:'textfield',
		            fieldLabel: '内线',
		            name: 'InTel',
		            anchor:'70%'
		          },
		          {
                    xtype:'textfield',
		            fieldLabel: '外线',
		            name: 'InTel',
		            anchor:'70%'
		          },
		          
		           {
                    xtype:'textfield',
		            fieldLabel: '传真',
		            name: 'Fax',
		            anchor:'70%'
		          },
		           {
                    xtype:'textfield',
		            fieldLabel: '值班电话',
		            name: 'NightTel',
		            anchor:'70%'
		          }
		          ]
              }]

        }],

 buttons: [{
            text: '提交',
            handler: function(){
                if(simple.form.isValid()) {
                    this.disabled=true;    
                    flag = false;
                    simple.getForm().submit({ 
                        url: '../resourcesadd?cmd=add',
                        method: 'POST',
                        waitTitle: '请稍等……', 
                        waitMsg: '正在上传数据……',  
                        success: function() {
                            Ext.Msg.alert("成功", "操作成功！");
                            this.disabled=false;
                        },
                        failure: function() {
                            Ext.Msg.alert("出错啦", "数据提交失败！");
                            this.disabled=false;
                        }
                    });
                }
            }
        }, {
            text: '重置',
            handler: function() {
                simple.form.reset();
            }
        }, {
            text: '返回',
            handler: function() {
               window.location="departListgrid.jsp"; 
            }
        }
        
        
        
        ]

    });

    simple.render(document.body);

});