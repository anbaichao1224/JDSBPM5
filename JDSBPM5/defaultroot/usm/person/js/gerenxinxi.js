Ext.onReady(function(){

   Ext.QuickTips.init();
   Ext.form.Field.prototype.msgTarget = 'side';
   var fsf = new Ext.Panel({ 
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
            items:[                  
     		{  
               columnWidth:.4,            
               layout: 'form',            
               border:false,            
               items: [
               		   {       
               		   id:'personid',     
		               xtype:'textfield',                
		               fieldLabel: '人员编号',                
		               name: 'personinfo.personid',
		               value:personid,
		               readOnly:true,                 
		               anchor:'95%'
	                   },{       
               		   id:'cnname',     
		               xtype:'textfield',                
		               fieldLabel: '中文名字',                
		               name: 'personinfo.cnname',
		               allowBlank:false,//不允许为空
		               blankText:'中文名不能为空',//错误提示内容  
		               checkboxToggle:true,
		               regex: /^[\u4E00-\u9FA5]+$/, 
          			   regexText: '只能输入汉字' ,		                              
		               anchor:'95%',
		               value:cnname,
		              // validator:CheckUserName,//指定验证器  
                       invalidText:'用户名已经被注册！'  
	                   },{ 
	                   id:'enname',               
		               xtype:'textfield',                
		               fieldLabel: '英文名字',                
		               name: 'personinfo.enname',
		               allowBlank:false,//不允许为空
		               blankText:'英文名不能为空',//错误提示内容   
		               regex:/^[a-zA-Z]+$/, 
          			   regexText: '只能输入英文字母', 
          			   value:enname,               
		               anchor:'95%'
	                   },
	                   {
	                    id:'password',
	                    xtype:'textfield',	
	                    fieldLabel: '密码',	
	                    name: 'personaccount.password',
	                    inputType:'password',
	                    value: password,
		                anchor:'95%'
	                   },
	                   new Ext.form.DateField({
	                    id:'birthday',
	                    xtype:'textfield',
                        fieldLabel: '出生日期',
                        format:'Y-m-d h:i:s',
                        name: 'personinfo.birthday',
                        value:birthday,    
                        anchor:'95%'
                        }), {                
		               id:'job',
		               xtype:'textfield',                
		               fieldLabel: '工作',                
		               name: 'personinfo.job',
		               value: job,               
		               anchor:'95%'
	                   }
	                   ]
                    },{
                		columnWidth:.4,
                        layout: 'form',
                        border:false, 
                     items: [
                     
	                  new Ext.form.ComboBox({
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
	                    value:sex,    
	                    selectOnFocus:true,
	                    anchor:'95%'
	                   }),{                
		               id:'duty',
		               xtype:'textfield',                
		               fieldLabel: '职务',                
		               name: 'personinfo.duty',
		                value: duty,                    
		              anchor:'95%'
	                   },
	                  
	                   new Ext.form.ComboBox({
	                    fieldLabel: '婚否',
	                    name: 'personinfo.marry',  
	                    store: new Ext.data.SimpleStore({   ////填充数据
	                        fields: ['marry'],
	                        data: [['保密'], ['已婚'],['未婚'],['离异']]
	                    }),	                     
	                    displayField:'marry',
	                    triggerAction:'all',
	                    emptyText:'选择婚否状态……',
	                    mode: 'local',   //数据模式，local代表本地数据  
	                    value: marry, 
	                    triggerAction:'all',
	                    selectOnFocus:true,
	                    anchor:'95%'
	                   }),
	                    new Ext.form.ComboBox({
	                    fieldLabel: '政治面貌',
	                    name: 'personinfo.politicalstat',  
	                    store: new Ext.data.SimpleStore({   ////填充数据
	                        fields: ['politicalstat'],
	                        data: [['群众'], ['团员'],['党员'],['其它']]
	                    }),	    
	                    triggerAction:'all',                 
	                    displayField:'politicalstat',
	                    triggerAction:'all',
	                    emptyText:'选择政治面貌状态……',
	                    mode: 'local',   //数据模式，local代表本地数据  
	                     value: politicalstat, 
	                    selectOnFocus:true,
	                    anchor:'95%'
	                   }),new Ext.form.ComboBox({
                    	fieldLabel: '最高学历',
                    	name: 'personinfo.lastedulevel',
                   		store: new Ext.data.SimpleStore({
                        fields: ['lastedulevel'],
                        data: [['无'],['小学'], ['初中'],['高中(中专、技校)'],['大专'],['本科'],['研究生'],['博士']]
                       }),
	                    displayField:'lastedulevel',
	                    emptyText:'选择最高学历……',
	                    mode: 'local',	                    
	                    triggerAction:'all',
	                    value: lastedulevel,
	                    selectOnFocus:true,
	                    anchor:'95%'
                      }),
                        new Ext.form.ComboBox({
                    	fieldLabel: '最高学位',
                    	name: 'personinfo.lastdegree',
                   		store: new Ext.data.SimpleStore({
                        fields: ['lastdegree'],
                        data: [['无学位'],['学士'], ['硕士'],['博士(后)']]
                       }),
	                    displayField:'lastdegree',
	                    triggerAction:'all',
	                    emptyText:'选择最高学位……',
	                    triggerAction:'all',
	                    mode: 'local',
	                    value: lastdegree,
	                    triggerAction:'all',
	                    selectOnFocus:true,
	                    anchor:'95%'
                      }) 
	                   ]

            }]

        },
        { 
           xtype:"fieldset",
          checkboxName:"other",
          title:"联系信息",
          collapsed: true,
           width:600,
           height:100,
            collapsible: true,
            checkboxToggle:false,//关键参数，其他和以前的一样            
            autoHeight:true,
            layout:'column',
            border:true,
            labelSeparator:'：',
            items:[{  
               columnWidth:.5,            
               layout: 'form',            
               border:false,           
               items: [{
	                    id:'househao',
	                    xtype:'textfield',                
		                fieldLabel: '房间号',                
		                name: 'personinfo.househao',
		                value: househao,             
		                anchor:'82%'
	                   },{
	                    id:'hometel',
	                    xtype:'textfield',                
		                fieldLabel: '家庭电话',                
		                name: 'personinfo.hometel',
		                 regex:/^((0[1-9]{3})?(0[12][0-9])?[-])?\d{6,8}$/,    
		                regexText:'请输入正确的家庭电话号码',    
		                value: hometel,             
		                anchor:'82%'
	                   },
	                  {
	                    id:'homefax',
	                    xtype:'textfield',
	                    fieldLabel: '家庭传真',
	                    regex:/^((0[1-9]{3})?(0[12][0-9])?[-])?\d{6,8}$/,    
		                regexText:'请输入正确的家庭传真号码',
	                    name: 'personinfo.homefax',
	                     value: homefax,      
	                    anchor:'82%'
                      },{
                        id:'officetel',
                        xtype:'textfield',                
		                fieldLabel: '办公室电话',
		                regex:/^((0[1-9]{3})?(0[12][0-9])?[-])?\d{6,8}$/,    
		                regexText:'请输入正确的办公室电话号码',                 
		                name: 'personinfo.officetel',  
		                value: officetel,               
		                anchor:'82%'
	                    
	                   },{
                        id:'officefax',
                        xtype:'textfield',
	                    fieldLabel: '办公传真号',
	                    regex:/^((0[1-9]{3})?(0[12][0-9])?[-])?\d{6,8}$/,    
		                regexText:'请输入正确的办公传真号',
	                    name: 'personinfo.officefax',
		                value: officefax,  
	                    anchor:'82%'
                      },{
                        id:'mobile',
                        xtype:'textfield',
	                    fieldLabel: '手机',
	                    name: 'personinfo.mobile',
	                    regex:/(^0?[1][35][0-9]{9}$)/,
	                    regexText:'请输入正确的手机号',
	                    value: mobile,  
	                    anchor:'82%'
                      },
                       {
                        id:'email1',
                        xtype:'textfield',
	                    fieldLabel: '邮箱',
	                    name: 'personinfo.email1',
	                    value: email1,
	                    anchor:'82%'
                      }]
                    },{
                		columnWidth:.4,
                        layout: 'form',
                        border:false, 
                     items: [
                      
                     {
                        id:'nativeplace',
                        xtype:'textfield',
	                    fieldLabel: '祖籍',
	                    name: 'personinfo.nativeplace',
	                    value: nativeplace,
	                    anchor:'100%'
                      },{
                        id:'nation',
                        xtype:'textfield',
	                    fieldLabel: '民族',
	                    name: 'personinfo.nation',
	                    value: nation,
	                    anchor:'100%'
                      },{
                        id:'country',
                        xtype:'textfield',
	                    fieldLabel: '国籍',
	                    value: country,
	                    name: 'personinfo.country',
	                    anchor:'100%'
                      },{
                        id:'city',
                        xtype:'textfield',
	                    fieldLabel: '城市',
	                    name: 'personinfo.city',
	                    value: city,
	                   anchor:'100%'
                      },
                      {
                        id:'zip',
                        xtype:'textfield',
	                    fieldLabel: '邮编',
	                    name: 'personinfo.zip',
	                    regex:/^([0-9]{6})$/,
	                    regexText:'请输入正确的邮编',
	                    value: zip,
	                    anchor:'100%'
                      }
	                 ]
            }]
        }
//          ,{
//          xtype:"fieldset",
//          checkboxName:"otherIfno",
//          title:"其它信息",
//          collapsed: true,
//          width:600,
//          collapsible: true,
//          checkboxToggle:false,//关键参数，其他和以前的一样            
//          autoHeight:true,           
//          border:true,
//          labelSeparator:'：',
//          items:[
//            		{  
//                       id:'homeadd',       
//		               xtype:'textfield',                
//		               fieldLabel: '家庭住址',                
//		               name: 'personinfo.homeadd',
//		               value: homeadd,                
//		               anchor:'95%'
//	               },{          
//		               id:'connectaddr',   
//		               xtype:'textfield',                
//		               fieldLabel: '联系地址',                
//		               name: 'personinfo.connectaddr', 
//		               value: connectaddr,                  
//		               anchor:'95%'
//	               },{          
//		               id:'officeadd', 
//		               xtype:'textfield',                
//		               fieldLabel: '办公地址',                
//		               name: 'personinfo.officeadd', 
//		                value: officeadd,                
//		               anchor:'95%'
//	               },{          
//		               id:'school', 
//		               xtype:'textfield',                
//		               fieldLabel: '毕业院校',                
//		               name: 'personinfo.school',
//		               value: officeadd,                  
//		              anchor:'95%'
//	               }]
//        }
        ],

 buttons: [{
            text: '修改',
            handler: function(){
             
                if(fsf.form.isValid()) {
                    this.disabled=true;    
                    flag = false;
                    fsf.getForm().submit({ 
                        url: '/usm/person/gerenxinxixiougai.do?personid='+personid,
                        method: 'POST',
                        waitTitle: '请稍等……', 
                        waitMsg: '正在上传数据……',  
                        success: function() {
                            Ext.Msg.alert("成功", "操作成功！");
                            window.location="gerenxinxi.do?personid="+personid; 
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
