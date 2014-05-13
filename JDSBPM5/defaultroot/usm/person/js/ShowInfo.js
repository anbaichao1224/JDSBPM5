Ext.onReady(function(){

    Ext.QuickTips.init();
    var imgPanel = { 
	xtype:'panel', 
	html: "<img id='img'/>",
	border:false
	}; 
     


    // turn on validation errors beside the field globally
    Ext.form.Field.prototype.msgTarget = 'side';
   
   var fsf = new Ext.FormPanel({
        labelWidth: 95, 
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
            items:[                  
                   {       
                columnWidth:.2,            
                layout: 'form',            
                border:false,
		               items: [    
		               
                {      title:'',
               		   html:'<img id="img" border="0" width="100" height="120" src="'+photo+'"/>'
	                           
	                   }
	                   ] }  ,               
            
            {  
               columnWidth:.4,            
               layout: 'form',            
               border:false,            
               items: [    
	             {                
		               xtype:'textfield',// 'fileuploadfield',
		               id: 'form-file',
		               emptyText: '请选择相片',             
		               fieldLabel: '相片',                
		               name: 'file',
		               anchor:'95%',
		               value:photo
		               //hidden:true, 
			           //buttonCfg: {
			             //   text: '浏览',
			               // iconCls: 'upload-icon'
			            //}
	                   },
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
		               fieldLabel: '姓名',                
		               name: 'personinfo.cnname',
		               allowBlank:false,//不允许为空
		               blankText:'姓名不能为空',//错误提示内容  
		               checkboxToggle:true,
		               regex: /^[\u4E00-\u9FA5]+$/, 
          			   regexText: '只能输入汉字' ,		                              
		               anchor:'95%',readOnly:true,
		               value:cnname,
		              // validator:CheckUserName,//指定验证器  
                       invalidText:'用户名已经被注册！'  
	                   },{                
		               id:'job',
		               xtype:'textfield',                
		               fieldLabel: '部门',                
		               name: 'personinfo.job',
		               value: job,   readOnly:true,            
		               anchor:'95%'
	                   },
	                   {
                        id:'nativeplace',
                        xtype:'textfield',
	                    fieldLabel: '籍贯',
	                    name: 'personinfo.nativeplace',
	                    value: nativeplace,readOnly:true,
	                    anchor:'95%'
                      },
                      
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
	                    selectOnFocus:true,readOnly:true,
	                    anchor:'95%'
	                   }),
                      
                      new Ext.form.ComboBox({
                    	fieldLabel: '最高学历',
                    	name: 'personinfo.lastedulevel',
                   		store: new Ext.data.SimpleStore({
                        fields: ['lastedulevel'],
                        data: [['无'],['小学'], ['初中'],['高中(中专、技校)'],['大专'],['本科'],['研究生']]
                       }),
	                    displayField:'lastedulevel',
	                    emptyText:'选择最高学历……',
	                    mode: 'local',	                    
	                    triggerAction:'all',
	                    value: lastedulevel,
	                    selectOnFocus:true,readOnly:true,
	                    anchor:'95%'
                      }),
                      {          
		               id:'school', 
		               xtype:'textfield',                
		               fieldLabel: '毕业院校',                
		               name: 'personinfo.school',
		               value: school, readOnly:true,                 
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
	                    selectOnFocus:true,readOnly:true,
	                    anchor:'95%'
	                   }),
	                   new Ext.form.DateField({
	                    id:'birthday',
	                    xtype:'textfield',
                        fieldLabel: '出生日期',
                        format:'Y-m-d h:i:s',
                        name: 'personinfo.birthday',
                        value:birthday, 
                        readOnly:true,   
                        anchor:'95%'
                        }), { 
	                   id:'enname',               
		               xtype:'textfield',                
		               fieldLabel: '系统用户名',                
		               name: 'personinfo.enname',
		               allowBlank:false,//不允许为空
		               blankText:'系统用户名不能为空',//错误提示内容   
		               regex:/^[a-zA-Z]+$/, 
          			   regexText: '只能输入英文字母', 
          			   value:enname,   readOnly:true,            
		               anchor:'95%'
	                   },
	                   {                
		               id:'duty',
		               xtype:'textfield',                
		               fieldLabel: '职务',                
		               name: 'personinfo.duty',
		                value: duty, readOnly:true,                   
		              anchor:'95%'
	                   },
	                  {
                        id:'nation',
                        xtype:'textfield',
	                    fieldLabel: '民族',
	                    name: 'personinfo.nation',
	                    value: nation,readOnly:true,
	                    anchor:'95%'
                      },
	                   new Ext.form.ComboBox({
	                    fieldLabel: '婚否',
	                    name: 'personinfo.marry',  
	                    store: new Ext.data.SimpleStore({   ////填充数据
	                        fields: ['marry'],
	                        data: [['保密'], ['是'],['否']]
	                    }),	                     
	                    displayField:'marry',
	                    triggerAction:'all',
	                    emptyText:'选择婚否状态……',
	                    mode: 'local',   //数据模式，local代表本地数据  
	                    value: marry, 
	                    triggerAction:'all',
	                    selectOnFocus:true,readOnly:true,
	                    anchor:'95%'
	                   }),
	                    
	                   
                        new Ext.form.ComboBox({
                    	fieldLabel: '最高学位',
                    	name: 'personinfo.lastdegree',
                   		store: new Ext.data.SimpleStore({
                        fields: ['lastdegree'],
                        data: [['无学位'],['学士'], ['硕士']]
                       }),
	                    displayField:'lastdegree',
	                    triggerAction:'all',
	                    emptyText:'选择最高学位……',
	                    triggerAction:'all',
	                    mode: 'local',
	                    value: lastdegree,
	                    triggerAction:'all',
	                    selectOnFocus:true,readOnly:true,
	                    anchor:'95%'
                      }) 
	                   ]

            }]

        },
        { 
           xtype:"fieldset",
          checkboxName:"other",
          title:"联系信息",
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
		                value: househao,  readOnly:true,           
		                anchor:'82%'
	                   },{
                        id:'officetel',
                        xtype:'textfield',                
		                fieldLabel: '办公室电话',
		                regex:/^((0[1-9]{3})?(0[12][0-9])?[-])?\d{6,8}$/,    
		                regexText:'请输入正确的办公室电话号码',                 
		                name: 'personinfo.officetel',  
		                value: officetel,   readOnly:true,            
		                anchor:'82%'
	                    
	                   },{          
		               id:'officeadd', 
		               xtype:'textfield',                
		               fieldLabel: '办公地址',                
		               name: 'personinfo.officeadd', 
		                value: officeadd,  readOnly:true,              
		               anchor:'95%'
	               },{  
                       id:'homeadd',       
		               xtype:'textfield',                
		               fieldLabel: '家庭住址',                
		               name: 'personinfo.homeadd',
		               value: homeadd,  readOnly:true,              
		               anchor:'95%'
	               },
                       {
                        id:'email1',
                        xtype:'textfield',
	                    fieldLabel: '电子邮箱',
	                    name: 'personinfo.email1',
	                    value: email1,readOnly:true,
	                    anchor:'82%'
                      }
	               
	               ]
                    },{
                		columnWidth:.4,
                        layout: 'form',
                        border:false, 
                     items: [
                      
                     {
                        id:'mobile',
                        xtype:'textfield',
	                    fieldLabel: '手机',
	                    name: 'personinfo.mobile',
	                    regex:/(^0?[1][35][0-9]{9}$)/,
	                    regexText:'请输入正确的手机号',
	                    value: mobile, readOnly:true, 
	                    anchor:'82%'
                      },{
                        id:'officefax',
                        xtype:'textfield',
	                    fieldLabel: '办公传真号',
	                    regex:/^((0[1-9]{3})?(0[12][0-9])?[-])?\d{6,8}$/,    
		                regexText:'请输入正确的办公传真号',
	                    name: 'personinfo.officefax',
		                value: officefax, readOnly:true, 
	                    anchor:'82%'
                      },
                      {
                        id:'zip',
                        xtype:'textfield',
	                    fieldLabel: '办公邮编',
	                    name: 'personinfo.zip',
	                    regex:/^([0-9]{6})$/,
	                    regexText:'请输入正确的邮编',
	                    value: zip,readOnly:true,
	                    anchor:'100%'
                      },
                      {
                        id:'city',
                        xtype:'textfield',
	                    fieldLabel: '家庭邮编',
	                    name: 'personinfo.city',
	                    value: city,readOnly:true,
	                    anchor:'100%'
                      }
                           
                    
	                 ]

            }]
        }
        
        ]

 

    });
    
     fsf.render(document.body);
});
