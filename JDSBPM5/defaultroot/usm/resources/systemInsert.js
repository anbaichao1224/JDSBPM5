	/*
	 *
	 */
	Ext.onReady(function(){
    Ext.QuickTips.init();
    // turn on validation errors beside the field globally
    Ext.form.Field.prototype.msgTarget = 'side';
    var bd = Ext.getBody();
    /*
     * ================  Simple form  =======================
     */ 
    var simple = new Ext.FormPanel({
        labelWidth: 75, // label settings here cascade unless overridde
        fileUpload: true,
        frame: true,
        title: '子系统注册信息',
        bodyStyle:'padding:5px 5px 0',
        width: '95%',
        height:'95%',
        defaults: {width: '400'},
        defaultType: 'textfield',

        items: [{
                fieldLabel: '系统中文名',
                	id:'cnname',
                    name: 'system.syscnname',
                    allowBlank:false,
		            blankText:'系统中文名称不能为空'
                },{
                fieldLabel: '系统英文名',
                	id:'enname',
                    name: 'system.sysenname',
                    allowBlank:false,
		            blankText:'系统英文名称不能为空',
		            listeners : {
				     'change':function(){
				     	var enname=document.getElementById("enname").value;				     	
				     	Ext.Ajax.request({ 
						method:'get', 
						url:"/usm/systemisEnname.do?n="+Math.random()+"&enname="+enname, 
						success: function(response, options) {
		                     var responseArray = Ext.util.JSON.decode(response.responseText); 
		                           if(responseArray.success==true){
		                           	document.getElementById("enname").value="";
		                        	Ext.Msg.alert('提示','已存在系统英文名称');
		                           }
		                    }
						}); 	
				    }
				    }
                },{fieldLabel: '系统说明',
                	id:'desc',
                    name: 'system.sysdesc'
                }
                ,{
                    fieldLabel: '系统链接',
                    id:'url',
                    name: 'system.url',
                    value:'/usm/systemInfo.do'
                }
          		 ,{
            xtype: 'fileuploadfield',
            id: 'form-file',
            emptyText: '请选择图片',
            fieldLabel: '系统图片',
            name: 'file',
            buttonCfg: {
                text: '打开图片',
                iconCls: 'upload-icon'
            }
        },
                  new Ext.form.Checkbox({fieldLabel:'系统用户',      
                  	name:'guestenable',
	                checked:false,   
	                boxLabel:'是否允许Guest用户'      
	               })
               ],

        buttons: [{
            text: '确定',
            handler: function(){
            if(simple.getForm().isValid()) { 
	            simple.getForm().submit({url:'/usm/systemSave.do',method:'post',waitMsg:'Saving Data...',success:function(form,action){ 
				Ext.Msg.alert('操作','系统添加成功');
				}, failure:function(form,action){ 
				Ext.Msg.alert('操作','系统添加失败');
				}
	            });
            }else{
            	Ext.Msg.alert('验证','非法输入');
            }
        }
        },{
            text: '重置',
            handler:function(){ 
			simple.getForm().reset(); 
			} 
	 	},{
	 		text: '关闭',
	 		handler:function(){
	 		
	 			//Ext.get('topic-grid').dom.src="/usm/systemAll.do";
	 			window.location='/usm/systemAll.do';
	 		}
	 	}]
    });
    simple.render(document.body);
    //simple.center();
});