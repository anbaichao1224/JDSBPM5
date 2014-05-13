Ext.onReady(function(){

    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';
    var simple = new Ext.FormPanel({
        labelWidth: 80, // label settings here cascade unless overridden
        title: '',
        labelAlign: 'right',
        buttonAlign:'center',   
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
               items:[ 
//                    {  
//                       id:'orgid',      
//		               xtype:'textfield',             
//		               fieldLabel: '部门编号',                
//		               name: 'depart.orgid',
//		               readOnly:true, 
//		               hidden:true,
//		               value:orgid, 
//		               anchor:'70%'
//	                },
                    {  
                       id:'cnname',      
		               xtype:'textfield',             
		               fieldLabel: '部门名称',                
		               name: 'depart.cnname',
		               value:cnname,  
		               allowBlank:false,//不允许为空
		               blankText:'部门名称不能为空',//错误提示内容  
		               checkboxToggle:true,
		               regex: /^[\u4E00-\u9FA5]+$/, 
          			   regexText: '只能输入汉字' ,		                              
		               anchor:'70%',
                       invalidText:'部门名称已经被注册！'  
	                },{
	                   id:'enname',
	                   xtype:'textfield',
	                   fieldLabel: '英文名称',	
	                   name: 'depart.enname',
	                   allowBlank:false,
	                   blankText:'英文名称不能为空', 
		               regex:/^[a-zA-Z]+$/, 
          			   regexText: '只能输入英文字母',
	                   value:enname,  
		               anchor:'70%'
	                },{
	                    xtype:'textfield',
	                    fieldLabel: '部门级别',	
	                    name: 'depart.orglevel',
	                    value:orglevel,
	                    regex:/^([0-9]{1,6})$/,
	                    regexText:'请输入正确的部门级别',
		                anchor:'70%'
	
	                },new Ext.form.ComboBox({
	                    fieldLabel: '状态',
	                    name: 'depart.status',
	                    value:status,
	                    store: new Ext.data.SimpleStore({
	                        fields: ['status'],
	                        data: [['正常'],['禁用'], ['删除']]
	                    }),
	                    displayField:'status',
	                    triggerAction:'all',
	                    emptyText:'选择状态……',
	                    mode: 'local',
	                   
	                    selectOnFocus:true,
	                    anchor:'85%'
                  })
//	                ,{
//						
//	                    xtype:'textfield',
//	                    fieldLabel: '父级部门ID',	
//	                    name: 'depart.parentorgid',
//	                    value:parentorgid,
//	                    readOnly:true, 
//		                anchor:'70%'
//	                }
	                ,new Ext.form.ComboBox({
	                    fieldLabel: '部门类型',
	                    name: 'depart.orgtype',
	                    value:orgtype,
	                    store: new Ext.data.SimpleStore({
	                        fields: ['orgtype'],
	                        data: [['普通部门'],['实用部门']]
	                    }),
	                    displayField:'orgtype',
	                    emptyText:'选择部门类型……',
	                    triggerAction:'all',
	                    mode: 'local',
	                    selectOnFocus:true,
	                    anchor:'70%'
                  }), 
                  new Ext.form.Hidden({   //hidden   
                	name:'depart.parentorgid'
           		   })
                  
                  ]
              },{
                columnWidth:.4,
                layout: 'form',
                border:false, 
                items: [
		                
		             {
                    id:'intel',
                    xtype:'textfield',
		            fieldLabel: '内线电话',
		            name: 'depart.intel',
		            value:intel,
		            anchor:'85%'
		          },
		          {
                    id:'outtel',
                    xtype :'textfield',
		            fieldLabel: '外线电话',
		            name: 'depart.outtel',
		            value:outtel,
		            anchor:'85%'
		          },{
                    xtype:'textfield',
		            fieldLabel: '传真',
		            name: 'depart.fax',
		            value:fax,
		            anchor:'85%'
		          } ,{
                    xtype:'textfield',
		            fieldLabel: '夜间值班电话',
		            name: 'depart.nighttel',
		            value:nighttel,
		            anchor:'85%'
		          }, {
	                    xtype:'textfield',
	                    fieldLabel: '序号',
	                    name: 'depart.serialindex',
	                    value:serialindex,
	                    readOnly:true,
	                    anchor:'85%'
	                   
		            }
		           ]
              }]

        },  {
	                    xtype:'textarea',
	                    fieldLabel: '备注',
	                    name: 'depart.memo',
	                   value:memo,
	                    anchor:'83%'
	                    
		            }],

 buttons: [{
            text: '修  改',
            handler: function(){
            	var cnname = Ext.get('cnname').dom.value;
            	var enname =Ext.get('enname').dom.value;
            	
            	if(enname=='')
            	{
            		Ext.MessageBox.alert('英文名','英文名不能为空');
            	}
            	if(cnname=='')
            	{
            		Ext.MessageBox.alert('部门名称','部门名称不能为空');
            	}
                if(simple.form.isValid()) {
                    this.disabled=true;    
                    flag = false;
                    simple.getForm().submit({ 
                        url: '/usm/departModify.do?orgid='+orgid,
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
            text: '取  消',
            handler: function() {
                simple.form.reset();
            }
        }    
        ]

    });

    simple.render(document.body);

});