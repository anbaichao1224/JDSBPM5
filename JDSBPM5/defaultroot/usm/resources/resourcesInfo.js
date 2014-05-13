Ext.onReady(function(){

    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';
    var bd = Ext.getBody();
   
    var textfieldWidth = 500;
    var simple2 =new Ext.FormPanel({
        labelWidth: 110, // label settings here cascade unless overridden
        frame:true,
        fileUpload: true,
        id: 'simpleattributes',
        bodyStyle:'padding:5px',
        width: 380,
        height:399,
        defaults: {width: 220},
        defaultType: 'textfield',
        items: [{
	            xtype: 'fileuploadfield',
	            id: 'form-file1',
	            emptyText: '请选择图片',
	            fieldLabel: '桌面快捷图片',
	            name: 'file1',
	            buttonCfg: {
	                text: '打开',
	                iconCls: 'upload-icon'
	            }
        		},{
	            xtype: 'fileuploadfield',
	            id: 'form-file2',
	            emptyText: '请选择图片',
	            fieldLabel: '桌面窗口图片(小)',
	            name: 'file2',
	            buttonCfg: {
	                text: '打开',
	                iconCls: 'upload-icon'
	            }
        		},{
        			xtype:'numberfield',
        			allowNegative:true,
        			allowBlank:false,
                    fieldLabel: '窗口高度',
                    name: 'moduleproperties.propertieswindowheight',
                    value:propertieswindowheight
                },{
                	xtype:'numberfield',
        			allowNegative:true,
        			allowBlank:false,
                    fieldLabel: '窗口宽度',
                    name: 'moduleproperties.propertieswindowwidth',
                    value:propertieswindowwidth
                },{
                	xtype:'numberfield',
        			allowNegative:true,
        			allowBlank:false,
                    fieldLabel: '窗口位置X',
                    name: 'moduleproperties.propertieswindowx',
                    value:propertieswindowx
                },{
                	xtype:'numberfield',
        			allowNegative:true,
        			allowBlank:false,
                    fieldLabel: '窗口位置Y',
                    name: 'moduleproperties.propertieswindowy',
                    value:propertieswindowy
                },
                new Ext.form.Checkbox({fieldLabel:'窗口最大化',   
	                name:'moduleproperties.propertieswindowmax',   
	                checked:propertieswindowmax}),
	            new Ext.form.Checkbox({fieldLabel:'窗口最小化',   
	                name:'moduleproperties.propertieswindowmin',   
	                checked:propertieswindowmin}),
	           	new Ext.form.Checkbox({fieldLabel:'窗口关闭',   
	                name:'moduleproperties.propertieswindowclose',   
	                checked:propertieswindowclose})
          		,new Ext.form.Hidden({
                	name:'moduleproperties.moduleid',
                	value:moduleid
                }),new Ext.form.Hidden({
                	name:'moduleproperties.uuid',
                	value:uuid
                })
               ],
                 buttons: [{
	            text: '保  存',
	            handler: function(){
	            	 if(Ext.getCmp("simpleattributes").getForm().isValid()) {
			            Ext.getCmp("simpleattributes").getForm().submit({url:'/usm/modulepropertiesSave.do',method:'GET',waitMsg:'正在保存数据...',success:function(form,action){ 
						Ext.MessageBox.alert('提示', '添加成功'); ; 
						}, failure:function(form,action){ 
						Ext.MessageBox.alert('错误', '添加失败'); 
						}
			            });
	            	 }else{
	            	 	Ext.MessageBox.alert('错误', '验证失败'); 
	            	 }
	            
		        }
		        },{
	            text: '取  消',
	            handler:function(){ 
				Ext.getCmp("simpleattributes").getForm().reset(); 
				} 
				}]
       });
    //---------------------------------------------------------------
    var store = new Ext.data.Store({
        
        proxy: new Ext.data.HttpProxy({   
            url: '/usm/getSsoInfo.do?limit=100&start=0'   // 从此页获取数据
        }),
         reader: new Ext.data.JsonReader({
            root: 'data',
            id: 'ssoid',
            fields: ['uuid','sso_name']
        })
    
    });
    store.load();
    var combo = new Ext.form.ComboBox({
 	   width:300,
 	   height:50,
 	   hiddenName:'sso_uuid',
 	   editable: false,
 	   triggerAction:'all',
 	   emptyText:'请选择SSO系统..',
 	   allowBlank: true,
 	   handleHeight:5,
 	   listWidth :270,
 	   maxHeight:100,
 	   fieldLabel: '所属系统',
 	   readOnly:true,
 	   //mode: 'local',
 	   valueField:'uuid',
 	   displayField:'sso_name',
 	   store:store
    });
    store.on('load', function() {   
       combo.setValue(sso_uuid);   
    }); 
	var simple = new Ext.FormPanel(
			{
				labelWidth :75, 
				fileUpload :true,
				frame :true,
				bodyStyle :'padding:0px 0px 0',

				align:'center',
				defaults : {
					width :230
				},
				defaultType :'textfield',
				items : [
						{
							fieldLabel :'中文名称',
							name :'module.cnname',
							allowBlank :false,
							width:textfieldWidth,
							value:cnname,
							blankText :'名称不能为空'
						},
						{
							fieldLabel :'英文名称',
							id :'enname',
							name :'module.enname',
							allowBlank :false,
							value:enname,
							width:textfieldWidth,
							blankText :'名称不能为空'
						}, {
							fieldLabel :'功能链接',
							name :'module.funcurl',
							allowBlank :false,
							value:funcurl,
							width:textfieldWidth,
							blankText :'名称不能为空'
						}, {
							fieldLabel :'导航链接',
							name :'module.navurl',
							allowBlank :false,
							value:navurl,
							width:textfieldWidth,
							blankText :'名称不能为空'
						}, {
							xtype :'fileuploadfield',
							id :'form-file',
							width:textfieldWidth,
							emptyText :'请选择图片',
							fieldLabel :'显示图片',
							name :'file',
							buttonCfg: {
				                text: '<img src="'+icon+'" width=16 height=16 border=0/>'
				            }
						}
//						, {
//							fieldLabel :'排序',
//							width:textfieldWidth,
//							value:serialindex,
//							name :'order'
//						}
						, {
							xtype :'textarea',
							fieldLabel :'表达式代码',
							width:textfieldWidth,
							name :'company'
						}, {
							xtype :'textarea',
							fieldLabel :'描       述',
							width:textfieldWidth,
							value:memo,
							name :'module.memo'
							
						}
						,new Ext.form.Hidden({
		                	name:'module.moduleid',
		                	value:moduleid
		                })
//						,new Ext.form.Checkbox( {
//							fieldLabel :'导航条',
//							name :'leftframeenabled',
//							checked:checked1,
//							boxLabel :'左导航条可用设置'
//						})
						, new Ext.form.Checkbox( {
							fieldLabel :'禁用模块',
							name :'enabled',
							checked:checked2,
							boxLabel:'勾选将导致子系统所有用户（包括管理员）无法使用该模块'
						}), new Ext.form.Checkbox( {
							fieldLabel :'权限控制',
							name :'needright',
							checked:checked3,
							boxLabel :'是否需要权限控制'
						})
//						, new Ext.form.Checkbox( {
//							fieldLabel :'管理',
//							name :'adminflag',
//							checked:checked4,
//							boxLabel :'是否可管理'
//						})
						,{
					        
					          xtype:"fieldset",
					          checkboxName:"otherIfno",
					          title:"代理登录",
					          collapsed: true,
					          width:610,
					          collapsible: true,
					          checkboxToggle:false,//关键参数，其他和以前的一样            
					          autoHeight:true,           
					          border:true,
					          labelSeparator:'：',
					          items:[
					             new Ext.form.Checkbox( {
									fieldLabel :'代理登录',
									name :'ifsso',
									checked :checked5,
									boxLabel :'是否需要代理登录'
								}),combo]
						},{
				            xtype:'fieldset',
				            width:textfieldWidth+100,
				            checkboxToggle:true,
				            title: '更多属性...',
				            autoHeight:true,
				            defaultType: 'textfield',
				            collapsed: true,
			                items :[{
			        			xtype:'numberfield',
			                    fieldLabel: '窗口高度',
			                    name: 'moduleproperties.propertieswindowheight',
			                    value:propertieswindowheight
			                },{
			                	xtype:'numberfield',
			                    fieldLabel: '窗口宽度',
			                    name: 'moduleproperties.propertieswindowwidth',
			                    value:propertieswindowwidth
			                },{
			                	xtype:'numberfield',
			                    fieldLabel: '窗口位置X',
			                    name: 'moduleproperties.propertieswindowx',
			                    value:propertieswindowx
			                },{
			                	xtype:'numberfield',
			                    fieldLabel: '窗口位置Y',
			                    name: 'moduleproperties.propertieswindowy',
			                    value:propertieswindowy
			                },new Ext.form.Checkbox({fieldLabel:'窗口最大化',   
				                name:'moduleproperties.propertieswindowmax',   
				                checked:propertieswindowmax})
			                ,new Ext.form.Checkbox({fieldLabel:'窗口最小化',   
				                name:'moduleproperties.propertieswindowmin',   
				                checked:propertieswindowmin})
			                ,new Ext.form.Checkbox({fieldLabel:'窗口关闭',   
				                name:'moduleproperties.propertieswindowclose',   
				                checked:propertieswindowclose})
			          		,new Ext.form.Hidden({
			                	name:'moduleproperties.moduleid',
			                	value:moduleid
			                })
			                ,new Ext.form.Hidden({
			                	name:'moduleproperties.uuid',
			                	value:uuid
			                })
			               ]
			        }],
				buttonAlign :'center',
				buttons: [
				            {
					            text: '修  改',
					            handler:function(){
					            if(simple.form.isValid()){
					               this.disabled=true;
					               simple.form.submit({
				                       url : '/usm/moduleUpdate.do',
					                   success : function(from, action) {
					                       Ext.example.msg('保存成功', '修改模块信息成功！');
					                   },
					                   failure : function(form, action) {
					                       Ext.example.msg('保存失败', '修改模块信息失败！');
					                   },
				                       waitMsg : '正在保存数据，稍后...'
				                  });
					           }else{
					        	   Ext.Msg.alert('信息', '请填写完成再提交!');  
				               }
				            } 
					        },{
					            text: '删  除',
					            handler:function(){ 
					             Ext.MessageBox.confirm('消息', '确认删除吗？',moduleRemove);
								} 
							}
					 ]
				     });
	 simple.render(document.body);
	 /**
	  * 模块删除
	  */
	 function moduleRemove(btn){
		 if(btn=='yes'){
			 simple.getForm().submit(
			 {
			   url:'/usm/moduleRemove.do',
			   method:'POST',
			   waitMsg:'正在保存数据...',
			   success:function(form,action){ 
			     Ext.Msg.alert('操作','数据删除成功');		
			   }, 
			   failure:function(form,action){ 
			     Ext.MessageBox.alert('错误', '删除资源失败!'); 
			   }
		     });  
        }
	 }

});