Ext.onReady(function(){

    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';
    var bd = Ext.getBody();
    var p=new Ext.Panel({
    height:100,
    html:"<font size=2px>"+desc+"</font>"
    });
   var simple = new Ext.FormPanel({
        labelWidth: 75, // label settings here cascade unless overridden
        frame:true,
        bodyStyle:'padding:5px 5px 0',
        width: 790,
        defaults: {width: 230},
        items: [p,{
            checkboxToggle:true,
            xtype:'fieldset',
            title:'基本配置',
            autoHeight:true,
            collapsed:true,
            width:600,
            checkboxName:'jiben',
            items:[new Ext.form.Hidden({
            name: 'bean.id',
            value: nodeid
            }),
             {
                fieldLabel: '名称',
                    name: 'bean.name',
                    xtype:'textfield',
                    width:200,
                    value:name
                },{fieldLabel: 'URL',
                    name: 'bean.url',
                    xtype:'textfield',
                    width:200,
                    value:url
                }, new Ext.form.ComboBox({
                		id:'type',
	                    fieldLabel: '类型',
	                    name: 'type', 
	                    hiddenName:'bean.type', 
	                    store: new Ext.data.SimpleStore({   ////填充数据
	                        fields: ['value','text'],
	                        data: [['main','main'],['sub','sub']]
	                    }),	      
	                    valueField:'value',               
	                    displayField:'text',
	                    forceSelection: true,
	                    triggerAction:'all',
	                    emptyText:'请选择类型……',
	                    mode: 'local',   //数据模式，local代表本地数据  
	                    triggerAction:'all',
	                    selectOnFocus:true,
	                    width:140
	                   }),{
                    fieldLabel: '最大连接',
                    name: 'bean.maxconnection',
                    xtype:'textfield',
                    value:50,
                    width:200,
                    value:maxconnection
                },{
                    fieldLabel: '最小连接',
                    xtype:'textfield',
                    width:200,
                    value:50,
                    name: 'bean.minconnection',
                    value:minconnection
                },
            	{
                    fieldLabel: '连接超时',
                    xtype:'textfield',
                    width:200,
                    value:200,
                    name: 'bean.timeout',
                    value:timeout
                },{
	                xtype:'textarea',
                    fieldLabel: '描述',
                    name: 'bean.desc',
                    width:200,
                    value:desc
                },
                new Ext.form.ComboBox({
                		id:'status',
	                    fieldLabel: '状态',
	                    name: 'status', 
	                    hiddenName:'bean.status', 
	                    store: new Ext.data.SimpleStore({   ////填充数据
	                        fields: ['value','text'],
	                        data: [['STOP','停止'],['RUN','运行']]
	                    }),	      
	                    valueField:'value',               
	                    displayField:'text',
	                    forceSelection: true,
	                    triggerAction:'all',
	                    emptyText:'请选择状态……',
	                    mode: 'local',   //数据模式，local代表本地数据  
	                    triggerAction:'all',
	                    selectOnFocus:true,
	                    width:140
	                   }),
                new Ext.form.ComboBox({
                		id:'userexpression',
	                    fieldLabel: '表达式',
	                    name: 'userexpression', 
	                    hiddenName:'bean.userexpression', 
	                    store: new Ext.data.SimpleStore({   ////填充数据
	                        fields: ['value','text'],
	                        data: [['true','true'],['false','false']]
	                    }),	      
	                    valueField:'value',               
	                    displayField:'text',
	                    forceSelection: true,
	                    triggerAction:'all',
	                    emptyText:'是否支持表达式……',
	                    mode: 'local',   //数据模式，local代表本地数据  
	                    triggerAction:'all',
	                    selectOnFocus:true,
	                    width:140
	                   })
            ]}
               ],

        buttons: [{
            text: '修改',
            handler: function(){
            simple.getForm().submit({url:'updateComputer.action',method:'POST',waitMsg:'正在保存数据...',success:function(form,action){ 
				Ext.Msg.alert('操作','数据修改成功');
				
			}, failure:function(form,action){ 
				Ext.MessageBox.alert('错误', '修改失败!'); 
			}
            });
	        }
	        },{
            text: '删除',
            handler:function(){ 
             Ext.MessageBox.confirm('消息', '确认删除吗？',moduleRemove);
			} 
			}
	 ]
	 
    });

    var tabpan= new Ext.TabPanel({        
                    autoTabs:true,
                    region: 'center',
            		 plain:true,
                    margins:'3 3 3 0', 
                  //  defaults:{autoScroll:true},        
					items:[
						{	
						id:'read',
						height:500,
						title:'控制信息',
						items:simple
						},{
						id:'module',
						height:500,
						title:'应用模块',
						html:"<iframe name='allmodule' id='allmodule' scrolling='yes' frameborder='0' src='allmodule.jsp?nodeid="+nodeid+"' height=100% width=100%></iframe>"
						},{
						id:'info',
						height:500,
						title:'所有用户',
						html:"<iframe name='allperson' id='allperson' scrolling='yes' frameborder='0' src='allperson.jsp?nodeid="+nodeid+"' height=100% width=100%></iframe>"
						}
					]  ,        
                       tbar : [{
                id:'new',
                text : '创建服务器',
                tooltip : '创建服务器',
                iconCls : 'add',
                handler: function (){
                           window.location="insertcomputer.jsp"; 
                           
                         }
            },{
                id:'start',
                text : '启动服务器',
                tooltip : '启动服务器',
                iconCls : 'add',
                handler: function (){
                           window.location="computerInfo.jsp?nodeid="+nodeid+"&action=RUN"; 
                            this.disabled=false;
                         }
            },{
            	id:'stop',
                text : '停止服务器',
                tooltip : '停止服务器',
                iconCls : 'remove',
                handler: function(){
                window.location="computerInfo.jsp?nodeid="+nodeid+"&action=STOP"; 
                            this.disabled=false;
                }
            }],
            		activeTab:0,
                    border:false
                });
    
 
 var pan = new Ext.Panel({
        title: '服务器详细信息',
        collapsible:true,
        width:780,
        height:545,
        id:'tabExtPersonformwin',
        shim:false,
        animCollapse:false,
        constrainHeader:true, 
        items:tabpan
        });
	 pan.render(document.body);
	 
	 function moduleRemove(btn){
	 if(btn=='yes'){
	 simple.getForm().submit({url:'/usm/usm/moduleRemove.do',method:'POST',waitMsg:'正在保存数据...',success:function(form,action){ 
				Ext.Msg.alert('操作','数据删除成功');
				
			}, failure:function(form,action){ 
				Ext.MessageBox.alert('错误', '删除资源失败!'); 
			}
            }); 
            }
	 }
	 if(status=='RUN'){
	 	Ext.getCmp('start').disable();
	 }else{
	 	Ext.getCmp('stop').disable();
	 }
	Ext.getCmp('type').setValue(type);
	Ext.getCmp('userexpression').setValue(userexpression);
	Ext.getCmp('status').setValue(status);
});