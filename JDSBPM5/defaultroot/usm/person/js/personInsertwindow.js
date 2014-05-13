Ext.onReady(function(){


    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';
    var fsf = new Ext.FormPanel({
        labelWidth: 90, 
		labelAlign: 'right',  
		fileUpload: true,  
		title: '',    
		buttonAlign:'center',    
		bodyStyle:'padding:0px 0px 0',   
		anchor:'100%',  
		frame:true,    
        items: [{
            collapsible: true,
            checkboxToggle:false,
            collapsed: true,
            xtype:'fieldset',
            title:'基本信息',
            autoHeight:true,
            collapsed:false,
            width:650,
            checkboxName:'jiben',
            layout:'column',
            border:true,
            labelSeparator:'：',
            fileUpload:true,
            items:[{  
               columnWidth:.5,            
               layout: 'form',            
               border:false,            
               items: [
                      {                
		               id:'userid',
		               xtype:'textfield',                
		               fieldLabel: '用户账号',                
		               name: 'personaccount.userid',
		               allowBlank:false,//不允许为空
		               blankText:'用户账号不能为空',//错误提示内容  
		               regex:/^[a-zA-Z]+$/, 
          			   regexText: '只能输入英文字母', 
                       invalidText:'用户账号已经被注册！' ,
                       anchor:'82%'      
	                   },
               		  {                
		               id:'cnname',
		               xtype:'textfield',                
		               fieldLabel: '中文名字',                
		               name: 'personinfo.cnname',
		               allowBlank:false,//不允许为空
		               blankText:'中文名不能为空',//错误提示内容  
		               checkboxToggle:true,	                              
		               anchor:'82%'
	                   },{ 
	                   id:'enname',               
		               xtype:'textfield',                
		               fieldLabel: '英文名字',                
		               name: 'personinfo.enname',
		               allowBlank:false,//不允许为空
		               blankText:'英文名不能为空',//错误提示内容                
		               anchor:'82%'
	                   },{
                                        fieldLabel: '密码',
                                        xtype:'textfield',
                                        id: 'new_pass',
                                        name: 'new_pass',
                                        inputType: 'password',
                                        allowBlank: false,
                                        blankText: '密码不能为空',
                                        regex:/^[A-Za-z]{1}([A-Za-z0-9]|[._]){5,19}$/,
                                        regexText:'密码由6-20位的字母、数字、下划线、点“.”组成并且首字符为字母',
                                        anchor:'82%'
                                    
                                    }, {
                                        fieldLabel: '重复密码',
                                        xtype:'textfield',
                                        id: 'new_pass_rp',
                                        name: 'new_pass_rp',
                                        inputType: 'password',
                                        allowBlank: false,
                                        invalidText:'两次密码不一致！',                                          
                                        validator: function(){//这里自定义函数验证密码是否一致
                                            if (
                                            fsf.form.findField("new_pass").getValue() == fsf.form.findField("new_pass_rp").getValue()) 
                                                return true;
                                            else 
                                                return false;
                                            
                                        },
                                        blankText: '密码不能为空',
                                         anchor:'82%'
                                    
                                    }
	                   ,          
		                new Ext.form.ComboBox({
	                    fieldLabel: '账号状态',
	                    id:'accountstat',
	                    name: 'personaccount.accountstat',
	                    store: new Ext.data.SimpleStore({   ////填充数据
	                        fields: ['accountstat'],
	                        data: [['永久'],['禁用']]
	                    }),	 
	                    triggerAction:'all',                    
	                    displayField:'accountstat',
	                    emptyText:'选择账号状态……',
	                    mode: 'local',   //数据模式，local代表本地数据 
	                    selectOnFocus:true,
	                    anchor:'82%'
	                   })/*,{ 
	                                  
		                id:'accountttl',
		                xtype:'textfield',                
		                fieldLabel: '有效期(天)',                
		                name: 'personaccount.accountttl',  
		                regex:/^[0-9]+$/, 
           			    regexText: '只能输入数字',             
		                anchor:'82%'
	                  }*/ ,
	                  new Ext.form.Hidden({   //hidden   
                	  name:'orgperson.orgid',
                	  value:orgid
           		      })
	                  ,new Ext.form.ComboBox({
                      	id:'sex',
	                    fieldLabel: '性别',
	                    name: 'personinfo.sex',  
	                    store: new Ext.data.SimpleStore({   ////填充数据
	                        fields: ['sex'],
	                        data: [['保密'], ['男'],['女']]
	                    }),	                     
	                    displayField:'sex',
	                    triggerAction:'all',
	                    emptyText:'选择性别状态……',
	                    mode: 'local',   //数据模式，local代表本地数据  
	                    selectOnFocus:true,
	                    anchor:'82%'
	                   })
           		      ]
                    },{
                		columnWidth:.4,
                        layout: 'form',
                        border:false, 
                       items: [
	                   new Ext.form.DateField({
	                    xtype:'textfield',
                        fieldLabel: '出生日期',
                        format:'Y-m-d',// h:i:s',
                        name: 'personinfo.birthday',
                        anchor:'100%'
                        }), {                
		               xtype:'textfield',                
		               fieldLabel: '工作',                
		               name: 'personinfo.job',                
		               anchor:'100%'
	                   },{                
		               xtype:'textfield',                
		               fieldLabel: '职务',                
		               name: 'personinfo.duty',                
		               anchor:'100%'
	                   },{
	                        id:'officetel',
	                        xtype:'textfield',
		                    fieldLabel: '座机电话',
		                    name: 'personinfo.officetel',
		                    anchor:'100%'
	                    },{
	                        id:'pager',
	                        xtype:'textfield',
		                    fieldLabel: '话机IP',
		                    name: 'personinfo.pager',
		                    regexText:'请输入正确的IP地址',
		                    anchor:'100%'
	                    },{
	                        id:'mobile',
	                        xtype:'textfield',
		                    fieldLabel: '手机',
		                    name: 'personinfo.mobile',
		                    regex:/(^0?[1][35][0-9]{9}$)/,
		                    regexText:'请输入正确的手机号',
		                    anchor:'100%'
	                    }/*,{                
		               xtype: 'fileuploadfield',
		               id: 'form-file',
		               emptyText: '请选择相片',             
		               fieldLabel: '相片',                
		               name: 'file',
		               anchor:'100%', 
			           buttonCfg: {
			                text: '浏览',
			                iconCls: 'upload-icon'
			           }}
,{
	                        id:'rtxaccount',
	                        xtype:'textfield',
		                    fieldLabel: 'rtx账号',
		                    name: 'personaccount.rtxaccount',
		                    anchor:'100%'
	                    }*/
                       ]
            }]
        }
        ],
        buttons: [{
            text: '保   存',
            handler: function(){
                var cnname=Ext.get('cnname').dom.value;
                var enname=Ext.get('enname').dom.value;
              //  var accountttl=Ext.get('accountttl').dom.value;
                var mobile=Ext.get('mobile').dom.value;
                var userid=Ext.get('userid').dom.value;
                
                if(enname=='')
               	{
                    Ext.MessageBox.alert('英文名','英文名不能为空' );
                }
                if(cnname=='')
                {
                    Ext.MessageBox.alert('中文名','中文名不能为空' );
                } 
                
                //判断登录账号是否重复
                Ext.Ajax.request({
					url : '/usm/personFlag.do',
					params : {
						userid : userid
					},
					success : function(resp, opts) {
	
                              if(fsf.form.isValid()) {
                    this.disabled=true;    
                    flag = false;
	              /*  if(KeyIsNumber(accountttl)==false)
	                {
	                  	Ext.MessageBox.alert('账号有效期','不充许输入字符' );
	                }*/
	  
	                fsf.getForm().submit({
	                    url: '/usm/personSave.do',
	                    method: 'POST',
	                    waitTitle: '请稍等……', 
	                    waitMsg: '正在上传数据……',  
	                    success: function() {
	                        Ext.Msg.alert("成功", "操作成功！",showchildrenPerson);
	                        this.disabled=false;
	                    },
	                    failure: function() {
	                        Ext.Msg.alert("出错啦", "数据保存失败！");
	                        this.disabled=false;
	                    }
	                });
	              
	            }
					},
					failure : function(resp, opts) {
						Ext.Msg.alert('信息', '保存信息不完整或输入人员账号重复!');
					}
				});
                
               
            }
        }, {
            text: '取  消',
            handler: function() {
                fsf.form.reset();
            }
        }
        ]

    });

    fsf.render(document.body);
	//只能输入数字
    function KeyIsNumber(KeyCode)
    {					    
		if(((KeyCode>47)&&(KeyCode<58))||(KeyCode==8)||(KeyCode==46))
		{
			return true;
		}
		else
		{
			return false;
		}
	}	
	function showchildrenPerson()
	{
		window.location="personInsertwindow.jsp?orgid="+orgid;
	}
	Ext.get('accountstat').dom.value="永久";
	Ext.get('sex').dom.value="保密";
	//Ext.get('accountttl').dom.value="30";
});
