<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%   String contextpath = request.getContextPath() ; response.setContentType("text/html;charset=utf-8");

%>
<html>
<head>
<title></title>

<script type="text/javascript">
	var context="/";
</script>
 
<script type="text/javascript" src="/js/extAll.js"></script>
<script type="text/javascript"><!--
	Ext.onReady(function(){
		var tabs = new Ext.TabPanel({        
					renderTo: 'tabs',         
					width:450,        
					activeTab: 0,     
					frame:true,        
					defaults:{autoHeight: true},        
					items:[            
						{contentEl:'script', title: '文件'},            
						{contentEl:'markup', title: '案卷'}         
					]     
				}); 
		var fileform = new Ext.FormPanel({
			renderTo:'script',
			frame:true,
			labelAlign: 'right',
			labelWidth:60,
			bodyStyle:'padding:1px',
			anchor:'100%', 
			width:500,
			items:[
				{
					layout:'column',
					labelSeparator:'：', 
					border:false,
					items:[{
						layout:'form',
						columnWidth:.4, 
						border:false,
						items:[
							{
								xtype:'textfield',
								fieldLabel:'标题',
								name:'title',
								id:'title',
								//width:200,
						 		anchor:'95%'
							}, 
							new Ext.form.ComboBox({
                    			fieldLabel: '密级',
                    			name: 'miji',
                    			store: new Ext.data.SimpleStore({
                        				fields: ['disval','valval'],
                        				data:[['一般','一般'],['秘密','秘密'],['机密','机密'],['绝密','绝密']]
                        				//data: [['一般-一般'], ['秘密-秘密'],['机密-机密'],['绝密-绝密']]
                   				 }),
                   				 displayField:'disval',
                   				 valueField:'valval',
                    			mode: 'local',
                    			selectOnFocus:true,
                    			anchor:'95%'
                  			})
							,new Ext.form.DateField({
	                				fieldLabel: '归档日期',
	               					 name: 'createdate',
	               					 format:'Y-m-d'
            				})
						]
					},{
						layout:'form',
					columnWidth:.5, 
					border:false,
					items:[{
						xtype:'textfield',
						fieldLabel:'运转号',
						name:'yzh',
						id:'yzh',
						//width:120,
						anchor:'95%'
					},
					new Ext.form.ComboBox({
                    			fieldLabel: '紧急程度',
                    			name: 'jjcd',
                    			store: new Ext.data.SimpleStore({
                        				fields: ['disval','valval'],
                        				data: [['一般','一般'], ['平急','平急'],['加急','加急'],['特急','特急'],['特提','特提']]
                        				//data: [['一般-一般'], ['平急-平急'],['加急-加急'],['特急-特急'],['特提-特提']]
                   				 }),
                   				 displayField:'disval',
                   				 valueField:'valval',
                    			mode: 'local',
                    			selectOnFocus:true,
                    			anchor:'95%'
                  	})
                  	]	
				}]	
				
			}],
			buttons:
			[{
			text: '提交',
            handler: function(){
                if(fileform.form.isValid()) {
                    this.disabled=true;    
                    flag = false;
                    fileform.getForm().submit({
                        
                        url: 'search_searchlist.action?seartype=2',
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
			}]
		});
		
		var rollform = new Ext.FormPanel({
			renderTo:'markup',
			frame:true,
			labelAlign: 'right',
			labelWidth:60,
			bodyStyle:'padding:1px',
			anchor:'100%', 
			width:500,
			items:[{
				layout:'column',
				labelSeparator:'：', 
				border:false,
				items:[{
					layout:'form',
					ColumnWidth:.5,
					border:false,
					items:[{
						xtype:'textfield',
								fieldLabel:'案卷标题',
								name:'title',
								id:'title',
								//width:200,
						 		anchor:'95%'
					},{
						xtype:'textfield',
								fieldLabel:'目录号',
								name:'miji',
								id:'miji',
								//width:200,
						 		anchor:'95%'
					}]
				},{
					layout:'form',
					ColumnWidth:.4,
					border:false,
					items:[{
						xtype:'textfield',
								fieldLabel:'年度',
								name:'jjcd',
								id:'jjcd',
								//width:200,
						 		anchor:'95%'
					}]
				}]
			}],
			buttons:[{
				text: '提交',
            	handler: function(){
                if(rollform.form.isValid()) {
                    this.disabled=true;    
                    flag = false;
                    rollform.getForm().submit({
                        
                        url: 'search_searchlist.action?seartype=2',
                        method: 'POST',
                        waitTitle: '请稍等……', 
                        waitMsg: '正在上传数据……',  
                        success: function() {
                           //Ext.Msg.alert("成功", "操作成功！");
                            //this.disabled=false;
                          	parent.Ext.getCmp('tabss').close(); 
                        },
                        failure: function() {
                            Ext.Msg.alert("出错啦", "数据保存失败！");
                            this.disabled=false;
                        }
                    });
                   
                }
            }
			}]
			
		});
	});
--></script>
</head>
<body>
<div id="tabs">      
	<div id="script" class="x-hide-display">             
      
	</div>        
	<div id="markup" class="x-hide-display"> 
           bbb     
	</div>    
</div> 
</body>
</html>

 