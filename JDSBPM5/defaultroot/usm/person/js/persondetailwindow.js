Ext.onReady(function(){

    Ext.QuickTips.init();
    if (icon==""){
    	icon='/usm/upload/no.jpg';
    }else{
    	icon='/'+icon;
    }	

    var imgPanel = { 
	xtype:'panel', 
	html: "<img id='img'/>",
	border:false
	}; 
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
            width:660,
            checkboxName:'jiben',
            layout:'column',
            border:true,
            labelSeparator:'：',
            items:[                  
                {       
                columnWidth:.2,            
                layout: 'form',            
                border:false,
		        items: [           
                {      
                title:'',
                html:'<img id="img" border="2" width="120" height="160" src="/'+icon+'"/>'        
	            }
	            ]},               
                {  
                columnWidth:.4,            
                layout: 'form',            
                border:false,            
                items: [
               		   {       
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
readOnly : true,
		               value:cnname
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
readOnly : true,            
		               anchor:'95%'
	                   },
	                   {
	                    id:'password',
	                    xtype:'textfield',	
	                    fieldLabel: '密码',	
	                    name: 'personaccount.password',
	                    inputType:'password',
	                    value: password,
readOnly : true,
		                anchor:'95%'
	                   },          
		                {
	                    id:'accountstat',
xtype:'textfield',	
	                    fieldLabel: '账号状态',
	                    name: 'personaccount.accountstat',
readOnly : true,	                   
	                 inputType:'accountstat',    
	                    value:accountstat,
	                    anchor:'95%'
	                   },{
	                        id:'officetel',
	                        xtype:'textfield',
		                    fieldLabel: '座机电话',
		                    name: 'personinfo.officetel',
		                    value: officetel, 
readOnly : true,
		                    anchor:'95%'
	                  }
	                  ]
                    },{
                		columnWidth:.4,
                        layout: 'form',
                        border:false, 
                     items: [
                     
	                  {
	                    fieldLabel: '性别',
	                    name: 'personinfo.sex',  
readOnly : true,
xtype:'textfield',	                    	                     
	    inputType:'sex',                    
	                    value:sex,    
	                   
	                    anchor:'95%'
	                   },
	                  {
	                    id:'birthday',
	                    xtype:'textfield',
readOnly : true,
                        fieldLabel: '出生日期',
     xtype:'textfield',	                    	                     
	    inputType:'birthday',                   
                        name: 'personinfo.birthday',
                        value:birthday,    
                        anchor:'95%'
                        }, {                
		               id:'job',
		               xtype:'textfield',                
		               fieldLabel: '工作',                
		               name: 'personinfo.job',
readOnly : true,
		               value: job,               
		               anchor:'95%'
	                   },{                
		               id:'duty',
		               xtype:'textfield',                
		               fieldLabel: '职务',                
		               name: 'personinfo.duty',
readOnly : true,
		                value: duty,                    
		               anchor:'95%'
	                   },{
	                        id:'mobile',
	                        xtype:'textfield',
		                    fieldLabel: '手机',
		                    name: 'personinfo.mobile',
readOnly : true,
		                    value: mobile, 
		                    anchor:'95%'
	                   },{
		                        id:'pager',
		                        xtype:'textfield',
			                    fieldLabel: '话机IP',
			                    name: 'personinfo.pager',
			                    value: pager, 
readOnly : true,
			                    anchor:'95%'
		               }
	                  ]
            }]

        }

        ]
    });
     fsf.render(document.body);
});
