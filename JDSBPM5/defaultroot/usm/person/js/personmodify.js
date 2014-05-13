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
        items: [{
            layout:'column',
            border:false,
            labelSeparator:'：',
            items:[
            {
               columnWidth:.5,            
               layout: 'form',            
               border:false,            
               items: 
               [
	               {                
		               xtype:'textfield',                
		               fieldLabel: '中文名',                
		               name: 'CnName',                
		               anchor:'82%'
	                }, 
	                {
	
	                    xtype:'textfield',	
	                    fieldLabel: '英文名',	
	                    name: 'EnName',
		                anchor:'82%'
	
	                },  
	                new Ext.form.ComboBox({
                    fieldLabel: '账号状态',
                    name: 'AccountStat',
                    store: new Ext.data.SimpleStore({
                        fields: ['AccountStat'],
                        data: [['0-正常'], ['1-锁定'],['2-一般管理员禁止']]
                    }),
                    displayField:'AccountStat',
                    emptyText:'选择账号状态……',
                    mode: 'local',
                    allowBlank:false,
                    selectOnFocus:true,
                    anchor:'82%'
                  }),  
	                {
	
	                    xtype:'textfield',	
	                    fieldLabel: '密码',	
	                    name: 'Password',
		                anchor:'82%'
	
	                },
	                {
	
	                    xtype:'textfield',	
	                    fieldLabel: '失败登陆次数',	
	                    name: 'LoginFailNum',
		                anchor:'82%'
	
	                },  
	                {
	
	                    xtype:'textfield',	
	                    fieldLabel: '最近登录IP',	
	                    name: 'LastLoginIp',
		                anchor:'82%'
	
	                },
	                
	                new Ext.form.DateField({
	                fieldLabel: '最近登录日期',
	                name: 'LastLoginDate'
            		}),
	                {
	                    xtype:'textfield',	
	                    fieldLabel: '密码问题',	
	                    name: 'PassQuestion',
		                anchor:'82%'
	
	                },
	                {
	                    xtype:'textfield',	
	                    fieldLabel: '密码答案',	
	                    name: 'PassAnswer',
		                anchor:'82%'
	
	                },
	                new Ext.form.ComboBox({
                    fieldLabel: '账号类型',
                    name: 'TtlFlag',
                    store: new Ext.data.SimpleStore({
                        fields: ['TtlFlag'],
                        data: [['1--临时账号'], ['2--普通账号(连续120天不登陆，即失效（被禁止）)'],['3--永久']]
                    }),
                    displayField:'TtlFlag',
                    emptyText:'选择账号类型……',
                    mode: 'local',
                    allowBlank:false,
                    selectOnFocus:true,
                    anchor:'82%'
                  }),  
	                {
	                    xtype:'textfield',	
	                    fieldLabel: '账号有效期限',	
	                    name: 'AccountTtl',
		                anchor:'82%'
	
	                },   
	                new Ext.form.DateField({
	                fieldLabel: '创建时间',
	                name: 'CreateTime'
            		}),
            		{
            		xtype:'textfield',
                    fieldLabel: '证件号',
                    name: 'IDNum',
                    anchor:'60%'
            		},
            		new Ext.form.ComboBox({
                    fieldLabel: '证件类型',
                    name: 'IDType',
                    store: new Ext.data.SimpleStore({
                        fields: ['IDType'],
                        data: [['0--身份证'], ['1--军官证'],['2--护照'],['3--其它']]
                    }),
                    displayField:'IDType',
                    emptyText:'选择证件类型……',
                    mode: 'local',
                    allowBlank:false,
                    selectOnFocus:true,
                    anchor:'82%'
                  }), 
                  new Ext.form.ComboBox({
                    fieldLabel: '性别',
                    name: 'Sex',
                    store: new Ext.data.SimpleStore({
                        fields: ['Sex'],
                        data: [['0--保密'],['1--男'], ['2--女']]
                    }),
                    displayField:'Sex',
                    emptyText:'选择性别……',
                    mode: 'local',
                    allowBlank:false,
                    selectOnFocus:true,
                    anchor:'82%'
                  }),  
                   new Ext.form.ComboBox({
                    fieldLabel: '婚烟状况',
                    name: 'Marry',
                    store: new Ext.data.SimpleStore({
                        fields: ['Marry'],
                        data: [['0--保密'], ['1--已婚'],['2--未婚'],['3--离异']]
                    }),
                    displayField:'Marry',
                    emptyText:'选择婚烟状况……',
                    mode: 'local',
                    allowBlank:false,
                    selectOnFocus:true,
                    anchor:'82%'
                  })                           
                
                  
                ]
            },
            {
                columnWidth:.4,
                layout: 'form',
                border:false, 
                items: [
                
                 new Ext.form.ComboBox({
                    fieldLabel: '政治面貌',
                    name: 'PoliticalStat',
                    store: new Ext.data.SimpleStore({
                        fields: ['PoliticalStat'],
                        data: [['1--群众'], ['2--团员'],['3--党员'],['4--其它']]
                    }),
                    displayField:'PoliticalStat',
                    emptyText:'选择政治面貌……',
                    mode: 'local',
                    allowBlank:false,
                    selectOnFocus:true,
                    anchor:'95%'
                  }),    
                  {
                    xtype:'textfield',
                    fieldLabel: '家庭电话',
                    name: 'HomeTel',
                    anchor:'95%'
                },
                 {
                    xtype:'textfield',
                    fieldLabel: '办公电话',
                    name: 'OfficeTel',
                    anchor:'95%'
                },
                 {
                    xtype:'textfield',
                    fieldLabel: '家庭传真',
                    name: 'HomeFax',
                    anchor:'95%'
                },
                 {
                    xtype:'textfield',
                    fieldLabel: '办公传真',
                    name: 'OfficeFax',
                    anchor:'95%'
                },
                 {
                    xtype:'textfield',
                    fieldLabel: '电子邮件1',
                    name: 'Email1',
                    anchor:'95%'
                },
                 {
                    xtype:'textfield',
                    fieldLabel: '电子邮件2',
                    name: 'Email2',
                    anchor:'95%'
                },
                 {
                    xtype:'textfield',
                    fieldLabel: '国家',
                    name: 'Country',
                    anchor:'95%'
                },
                {
                    xtype:'textfield',
                    fieldLabel: '省份',
                    name: 'Nation',
                    anchor:'95%'
                },
                {
                    xtype:'textfield',
                    fieldLabel: '城市',
                    name: 'City',
                    anchor:'95%'
                },
                {
                    xtype:'textfield',
                    fieldLabel: '联系地址',
                    name: 'ConnectAddr',
                    anchor:'95%'
                },
                {
                    xtype:'textfield',
                    fieldLabel: '邮编',
                    name: 'Zip',
                    anchor:'95%'
                },
                new Ext.form.ComboBox({
                    fieldLabel: '最高学历',
                    name: 'LastEduLevel',
                    store: new Ext.data.SimpleStore({
                        fields: ['LastEduLevel'],
                        data: [['0--无'],['1--小学'], ['2--初中'],['3--高中(中专、技校)'],['4--大专'],['5--本科'],['6--研究生'],['7--博士']]
                    }),
                    displayField:'LastEduLevel',
                    emptyText:'选择性别……',
                    mode: 'local',
                    allowBlank:false,
                    selectOnFocus:true,
                    anchor:'95%'
                  }),               
                
                new Ext.form.ComboBox({
                    fieldLabel: '最高学位',
                    name: 'LastDegree',
                    store: new Ext.data.SimpleStore({
                        fields: ['LastEduLevel'],
                        data: [['0--无学位'],['1--学士'], ['2--硕士'],['3--博士(后)']]
                    }),
                    displayField:'LastEduLevel',
                    emptyText:'选择性别……',
                    mode: 'local',
                    allowBlank:false,
                    selectOnFocus:true,
                    anchor:'95%'
                  }) ,
                  {
                    xtype:'textfield',
                    fieldLabel: '其他信息',
                    name: 'OtherInfo',
                    anchor:'95%'
                  
                  }
                ]

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
               window.location="personListgrid.jsp"; 
            }
        }
        
        
        
        ]

    });

    fsf.render(document.body);

   
});
