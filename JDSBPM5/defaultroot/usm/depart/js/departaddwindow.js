Ext.onReady(function(){

    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';
    var bd = Ext.getBody();
    bd.createChild({tag: 'h2', html: ''});
    var simple = new Ext.FormPanel({
        labelWidth: 80, // label settings here cascade unless overridden
        labelAlign: 'right',
        buttonAlign:'center',    
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
               items:[ 
	                {                
		               id:'cnname',
		               xtype:'textfield',             
		               fieldLabel: '* 部门名称',                
		               name: 'depart.cnname',            
		               allowBlank:false,//不允许为空
		               blankText:'部门名称不能为空',//错误提示内容  
		               checkboxToggle:true,
		               regex: /^[\u4E00-\u9FA5]+$/, 
          			   regexText: '只能输入汉字' ,		                              
		               anchor:'85%',
                       invalidText:'部门名称已经被注册！'  
	                },
	                {
	                    id:'enname',
	                    xtype:'textfield',
	                    fieldLabel: '* 英文名称',	
	                    name: 'depart.enname',
	                    allowBlank:false,
	                    blankText:'英文名称不能为空',
	                    
		               regex:/^[a-zA-Z]+$/, 
          			   regexText: '只能输入英文字母',
	                   
		              anchor:'85%'
	                },
	                  new Ext.form.ComboBox({
	                  	id:'orgtype',
	                    fieldLabel: '部门类型',
	                    name: 'depart.orgtype',
	                    store: new Ext.data.SimpleStore({
	                        fields: ['orgtype','text'],
	                        data: [['普通部门'],['实用部门']]
	                    }),
	                    displayField:'orgtype',
	                    emptyText:'选择部门类型……',
	                    mode: 'local',
	                    triggerAction: 'all',	
	                    readOnly: true,
	                    allowBlank:true,
	                    selectOnFocus:true,
	                    anchor:'85%'
                     }), 
	                
	              
	                new Ext.form.Hidden({   //hidden   
                	name:'depart.parentorgid',
                	value:parentorgid
           		    }),
		            new Ext.form.ComboBox({
		            	id:'status',
	                    fieldLabel: '状态',
	                    name: 'depart.status',
	                    store: new Ext.data.SimpleStore({
	                        fields: ['status','cstatus'],
	                        data: [['正常'],['禁用'], ['删除']]
	                    }),
	                    displayField:'status',
	                    emptyText:'选择状态……',
	                    mode: 'local',
	                    allowBlank:true,
	                    selectOnFocus:true,
	                    readOnly:true,
   						typeAhead: true,
   						triggerAction: 'all',	                    
	                    anchor:'85%'
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
		            anchor:'100%'
		            } ,
                    
		            {
                    xtype :'textfield',
		            fieldLabel: '外线电话',
		            name: 'depart.outtel',
		            anchor:'100%'
		            }
	             ,
		            {
                    xtype:'textfield',
		            fieldLabel: '传真',
		            name: 'depart.fax',
		             anchor:'100%'
		          },
		           {
                    id:'nighttel',
                    xtype:'textfield',
		            fieldLabel: '夜间值班电话',
		            name: 'depart.nighttel',
		            anchor:'100%'
		          }		           
		          ]
              }]

        },{
        	            xtype:'textarea',
	                    fieldLabel: '备注',	                    
	                    name: 'depart.memo',	          
	                    anchor:'89%'
		           
         }],
 buttons: [{
            text: '保  存',
            handler: function(){
            	var cnname = Ext.get('cnname').dom.value;
            	var enname =Ext.get('enname').dom.value;
            	var intel =Ext.get('intel').dom.value;
            	var nighttel =Ext.get('nighttel').dom.value;
            	
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
                        url: '../orgSave.do?orglevel='+orglevel+'',
                        method: 'post',
                        waitTitle: '请稍等……', 
                        waitMsg: '正在上传数据……',  
                        success: function() {
                            Ext.Msg.alert("成功", "操作成功！",childerdepartListgrid);
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
	function childerdepartListgrid()
    {
		 window.location="departaddwindow.jsp?parentorgid="+parentorgid;
	}
    Ext.get('orgtype').dom.value="普通部门";
    Ext.get('status').dom.value="正常";

});