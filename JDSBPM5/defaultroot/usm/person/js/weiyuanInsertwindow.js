Ext.onReady(function(){

    Ext.QuickTips.init();

    // turn on validation errors beside the field globally
    Ext.form.Field.prototype.msgTarget = 'side';

   

    var fsf = new Ext.FormPanel({
        labelWidth: 90, 
		labelAlign: 'right',  
		fileUpload: true,  
		title: '',    
		buttonAlign:'center',    
		bodyStyle:'padding:5px 5px 0',   
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
            width:600,
            checkboxName:'jiben',
            //defaults: {width: 350},
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
          			   // validator:CheckUserName,//指定验证器  
                       invalidText:'用户账号已经被注册！' ,
                       anchor:'82%'      
	                   },
               		  {                
		               id:'cnname',
		               xtype:'textfield',                
		               fieldLabel: '姓名',                
		               name: 'personinfo.cnname',
		               allowBlank:false,//不允许为空
		               blankText:'中文名不能为空',//错误提示内容  
		               checkboxToggle:true,
		               regex: /^[\u4E00-\u9FA5]+$/, 
          			   regexText: '只能输入汉字' ,		                              
		               anchor:'82%'
	                   },{ 
	                   id:'enname',               
		               xtype:'textfield',                
		               fieldLabel: '英文名字',                
		               name: 'personinfo.enname',
		               allowBlank:false,//不允许为空
		               blankText:'英文名不能为空',//错误提示内容  
		               regex:/^[a-zA-Z]+$/, 
          			   regexText: '只能输入英文字母',                
		               anchor:'82%'
	                   },
	                     {
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
	                    //hiddenName:&apos;hyear&apos;,//提交到后台的input的name，重要
	                     //readOnly : true,//是否只读 
	                    
	                    store: new Ext.data.SimpleStore({   ////填充数据
	                        fields: ['accountstat'],
	                        data: [['临时账号'], ['普通账号(连续120天不登陆，即失效（被禁止）)'],['永久']]
	                    }),	 
	                    triggerAction:'all',                    
	                    displayField:'accountstat',
	                    emptyText:'选择账号状态……',
	                    mode: 'local',   //数据模式，local代表本地数据 
	                    selectOnFocus:true,
	                    anchor:'82%'
	                   }),{ 
	                                  
		                id:'accountttl',
		                xtype:'textfield',                
		                fieldLabel: '账号有效期(天)',                
		                name: 'personaccount.accountttl',  
		                regex:/^[0-9]+$/, 
           			    regexText: '只能输入数字',             
		                anchor:'82%'
	                  } ,
	                  new Ext.form.Hidden({   //hidden   
                	  name:'orgperson.orgid',
                	  value:orgid
           		      }),
                     {
                        xtype:'textfield',
	                    fieldLabel: '密码问题',
	                    name: 'personaccount.passquestion',
	                    anchor:'82%'
                      }, {
                        xtype:'textfield',
	                    fieldLabel: '问题答案',
	                    name: 'personaccount.passanswer',
	                   anchor:'82%'
                      },new Ext.form.ComboBox({
                      	id:'sex',
	                    fieldLabel: '性别',
	                    name: 'personinfo.sex',  
	                    store: new Ext.data.SimpleStore({   ////填充数据
	                        fields: ['sex'],
	                        data: [['男'],['女']]
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
                        fieldLabel: '出生年月',
                        format:'Y-m-d',// h:i:s',
                        name: 'personinfo.birthday',
                        anchor:'100%'
                        }), {                
		               xtype:'textfield',                
		               fieldLabel: '职位名称',                
		               name: 'personinfo.job',                
		               anchor:'100%'
	                   },{                
		               xtype:'textfield',                
		               fieldLabel: '党派团体职务',                
		               name: 'personinfo.duty',                
		               anchor:'100%'
	                   },
	                   {                
		               xtype:'textfield',                
		               fieldLabel: '类型',                
		               name: 'personinfo.type',
		                regex:/^[0-9]+$/, 
          			   regexText: '只能输入数字',                  
		               anchor:'100%'
	                   },
	                   new Ext.form.ComboBox({
	                   	id:'marry',
	                    fieldLabel: '婚否',
	                    name: 'personinfo.marry',  
	                    store: new Ext.data.SimpleStore({   ////填充数据
	                        fields: ['marry'],
	                        data: [['保密'], ['已婚'],['未婚'],['离异']]
	                    }),	   
	                    triggerAction:'all',          
	                    displayField:'marry',
	                    emptyText:'选择婚否状态……',
	                    mode: 'local',   //数据模式，local代表本地数据  
	                    selectOnFocus:true,
	                    anchor:'100%'
	                   }),
	                    new Ext.form.ComboBox({
	                    id:'politicalstat',
	                    fieldLabel: '政治面貌',
	                    name: 'personinfo.politicalstat',  
	                    store: new Ext.data.SimpleStore({   ////填充数据
	                        fields: ['politicalstat'],
	                        data: [['群众'], ['团员'],['党员'],['其它']]
	                    }),	 
	                    triggerAction:'all',                    
	                    displayField:'politicalstat',
	                    emptyText:'选择政治面貌状态……',
	                    mode: 'local',   //数据模式，local代表本地数据 
	                    selectOnFocus:true,
	                    anchor:'100%'
	                   }),new Ext.form.ComboBox({
	                   	id:'lastedulevel',
                    	fieldLabel: '文化程度',
                    	name: 'personinfo.lastedulevel',
                   		store: new Ext.data.SimpleStore({
                        fields: ['lastedulevel'],
                        data: [['无'],['小学'], ['初中'],['高中(中专、技校)'],['大专'],['本科'],['研究生'],['博士']]
                       }),
                        triggerAction:'all',   
	                    displayField:'lastedulevel',
	                    emptyText:'选择最高学历……',
	                    mode: 'local',
	                    selectOnFocus:true,
	                     anchor:'100%'
                      }),
                       
                        new Ext.form.ComboBox({
                        id:'lastdegree',
                    	fieldLabel: '最高学位',
                    	name: 'personinfo.lastdegree',
                   		store: new Ext.data.SimpleStore({
                        fields: ['lastdegree'],
                        data: [['无学位'],['学士'], ['硕士'],['博士(后)']]
                       }),
                        triggerAction:'all',   
	                    displayField:'lastdegree',
	                    emptyText:'选择最高学位……',
	                    mode: 'local',
	                    selectOnFocus:true,
	                     anchor:'100%'
                      }),{                
		               xtype: 'fileuploadfield',
		               id: 'form-file',
		               emptyText: '请选择相片',             
		               fieldLabel: '相片',                
		               name: 'file',
		               anchor:'100%', 
			           buttonCfg: {
			                text: '浏览',
			                iconCls: 'upload-icon'
			            }
	                   }
                        ]

            }]

        },
        { 
        	xtype:"fieldset",
//checkboxToggle:true,//关键参数，其他和以前的一样
          checkboxName:"other",
          title:"联系信息",
          
          collapsed: true,
           width:600,
           height:100,
            //xtype:'fieldset',
            collapsible: true,
            checkboxToggle:false,//关键参数，其他和以前的一样            
            //title:'选项信息',
            autoHeight:true,
           // defaults: {width: 400},
            layout:'column',
            border:true,
            labelSeparator:'：',
            items:[{  
               columnWidth:.5,            
               layout: 'form',            
               border:false,            
               items: [{
	                    xtype:'textfield',                
		                fieldLabel: '房间号:',                
		                name: 'personinfo.househao',          
		                anchor:'82%'
	                   },
	                  {
	                    xtype:'textfield',                
		                fieldLabel: '家庭电话',                
		                name: 'personinfo.hometel',  
		                regex:/^((0[1-9]{3})?(0[12][0-9])?[-])?\d{6,8}$/,    
		                regexText:'请输入正确的家庭电话号码',
		                anchor:'82%'
	                   },{
	                    xtype:'textfield',
	                    fieldLabel: '通信地址邮编',
	                    name: 'personinfo.homefax',
	                     regex:/^([0-9]{6})$/,   
		                regexText:'请输入正确的邮编',
	                    anchor:'82%'
                      },{
                        xtype:'textfield',                
		                fieldLabel: '单位电话',                
		                name: 'personinfo.officetel', 
		                regex:/^((0[1-9]{3})?(0[12][0-9])?[-])?\d{6,8}$/,    
		                regexText:'请输入正确的电话号码',               
		                anchor:'82%'
	                    
	                   },{
                        xtype:'textfield',
	                    fieldLabel: '办公传真号',
	                    name: 'personinfo.officefax',
	                    regex:/^((0[1-9]{3})?(0[12][0-9])?[-])?\d{6,8}$/,    
		                regexText:'请输入正确的办公传真号',
	                     anchor:'82%'
                      },
	                   {
                        id:'mobile',
                        xtype:'textfield',
	                    fieldLabel: '手机',
	                    name: 'personinfo.mobile',
	                    regex:/(^0?[1][35][0-9]{9}$)/,
	                    regexText:'请输入正确的手机号',
	                    anchor:'82%'
                      },{
                        id:'email1',
                        xtype:'textfield',
	                    fieldLabel: '邮箱',
	                    name: 'personinfo.email1',	                   
	                    vtype:'email',
	                    anchor:'82%'
                      }]
                    },{
                		columnWidth:.4,
                        layout: 'form',
                        border:false, 
                     items: [
                      
                      {
                        xtype:'textfield',
	                    fieldLabel: '住址邮编',
	                    name: 'personinfo.zip',	                    
	                    regex:/^([0-9]{6})$/,
	                    regexText:'请输入正确的邮编',
	                     anchor:'100%'
                      },
                       
                     {
                        xtype:'textfield',
	                    fieldLabel: '单位邮编',
	                    name: 'personinfo.nativeplace',
	                     anchor:'100%'
                      },{
                        xtype:'textfield',
	                    fieldLabel: '民族',
	                    name: 'personinfo.nation',
	                    anchor:'100%'
                      },{
                        xtype:'textfield',
	                    fieldLabel: '国籍',
	                    name: 'personinfo.country',
	                     anchor:'100%'
                      },{
                        xtype:'textfield',
	                    fieldLabel: '通信地址',
	                    name: 'personinfo.city',
	                     anchor:'100%'
                      }
	                 ]

            }]
        }
        /**{
xtype:"fieldset",
checkboxToggle:true,//关键参数，其他和以前的一样
checkboxName:"dfdf",
title:"选填信息",
defaultType:'textfield',
width:330,
autoHeight:true,//使自适应展开排版
items:[{
    fieldLabel:"UserName",
    name:"user",
    anchor:"95%"//330px-labelWidth剩下的宽度的95%，留下5%作为后面提到的验证错误提示
  },
  {
    fieldLabel:"PassWord",
    inputType:"password",//密码文本
    name:"pass",
    anchor:"95%"
  }]
}**/
        ,{
        
         xtype:"fieldset",
         //checkboxToggle:true,//关键参数，其他和以前的一样
          checkboxName:"otherIfno",
          title:"其它信息",
          collapsed: true,
           width:600,

            //xtype:'fieldset',
            collapsible: true,
            checkboxToggle:false,//关键参数，其他和以前的一样            
           // title:'选项信息',
            autoHeight:true,           
            //layout:'column',
            border:true,
            labelSeparator:'：',
            items:[
              
                     {
                        xtype:'textfield',
	                    fieldLabel: '其他信息',
	                    name: 'personinfo.otherinfo',
	                    anchor:'90%'
                      }
            		,
            		{  
                              
		               xtype:'textfield',                
		               fieldLabel: '家庭地址',                
		               name: 'personinfo.homeadd',                
		               anchor:'90%'
	               },{          
		               xtype:'textfield',                
		               fieldLabel: '单位地址',                
		               name: 'personinfo.connectaddr',                
		               anchor:'90%'
	               },{          
		               xtype:'textfield',                
		               fieldLabel: '工作单位',                
		               name: 'personinfo.officeadd',                
		               anchor:'90%'
	               },{          
		               xtype:'textfield',                
		               fieldLabel: '毕业院校',                
		               name: 'personinfo.school',                
		               anchor:'90%'
	               }]
            
 
        
        
        
        }
        
        ],

 buttons: [{
            text: '提交',
            handler: function(){
                var cnname=Ext.get('cnname').dom.value;
                var enname=Ext.get('enname').dom.value;
                var email1=Ext.get('email1').dom.value;
                var accountttl=Ext.get('accountttl').dom.value;
                var mobile=Ext.get('mobile').dom.value;
                  if(enname=='')
                  {
                      Ext.MessageBox.alert('英文名','英文名不能为空' );
                  }
                  if(cnname=='')
                  {
                      Ext.MessageBox.alert('中文名','中文名不能为空' );
                  } 
                  
                  if(test_email(email1)==false)
                  {
                      Ext.MessageBox.alert('邮箱','邮箱格式不正确' );
                      
                  }
                if(fsf.form.isValid()) {
                    this.disabled=true;    
                    flag = false;
                  if(KeyIsNumber(accountttl)==false)
                  {
                  	  Ext.MessageBox.alert('账号有效期','不充许输入字符' );
                  }
                  if(ismobile(mobile)==true)
                  {
                      Ext.MessageBox.alert('手机号','请输入规范的手机号' );
                  }     
                    fsf.getForm().submit({
                        url: '../personSave.do',
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
    
    				//验证邮箱
                   function test_email(strEmail) { 
  						var myReg = /^[_a-z0-9]+@([_a-z0-9]+\.)+[a-z0-9]{2,3}$/; 
  							if(myReg.test(strEmail) || strEmail=="") return true; 
  								return false; 
  					}
  					
					//手机号码; 
				    function ismobile(mobile)
						{
						    var boole=true;
						    if(mobile.length=="")
						    {
						        boole = true;
						    }else
						    {
							    var myreg = /^(((13[0-9]{1})|159)+\d{8})$/;
							    if(!myreg.test(mobile))
							    {
							        boole = false;
							    }
						    }
						    return boole;
						}
				    
				     //只能输入数字
  					function KeyIsNumber(KeyCode)
					{
					    //假如输入的字符是在0-9之间，或是backspace、DEL键
					    
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
						 window.location="weiyuanInsertwindow.jsp?orgid="+orgid;
					}
					Ext.get('accountstat').dom.value="临时账号";
					Ext.get('sex').dom.value="保密";
					Ext.get('marry').dom.value="保密";
					Ext.get('politicalstat').dom.value="群众";
					Ext.get('lastdegree').dom.value="无学位";
					Ext.get('lastedulevel').dom.value="无";
					Ext.get('accountttl').dom.value="30";
});
