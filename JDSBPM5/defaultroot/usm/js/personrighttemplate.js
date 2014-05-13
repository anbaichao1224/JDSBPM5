Ext.BLANK_IMAGE_URL='/usm/resources/images/default/s.gif';
Ext.onReady(function(){
    Ext.QuickTips.init();
    var fp = new Ext.FormPanel({
        frame: true,
        split:true,
        collapsible: true,
        labelWidth: 110,
        width: 785,
        renderTo:'form-ct',
        bodyStyle: 'padding:0 0px 0;',
        
        items: str,
         tbar:['名  称：', new Ext.form.TextField({   
        		id:'findvalue',
                width:175, 
                name: 'findvalue',
                allowBlank:true 
            }), {
                    id : 'newModelButton',    
                    icon: "/usm/img/search.png",   
                    text:'查  询',
                    cls : "x-btn-text-icon",   
                    handler :function(){
        	            var fv = Ext.get("findvalue").dom.value;
                    	if(fv!=""){
                    		
                    		window.location.href("/usm/rightTemplate.do?template="+ttemplate+"&sysid="+tsysid+"&nodeid="+tnodeid+"&fv="+fv);
                    	//findIt(Ext.get("findvalue").dom.value);
                        }
                    }  
            }
           ],
        buttons: [{
            text: '保  存',
            handler: function(){
              fp.getForm().submit({url:'/usm/rightTemplateSave.do',method:'POST',waitMsg:'正在保存数据...',success:function(form,action){ 
					Ext.Msg.alert('操作','操作成功');
				
				}, failure:function(form,action){ 
					Ext.MessageBox.alert('错误', '操作失败!'); 
				}
	            });
            }
        },{
            text: '取 消',
            handler: function(){
            	Ext.getCmp("10156").setChecked=false;
            }
        }]
    });
    
    

});


