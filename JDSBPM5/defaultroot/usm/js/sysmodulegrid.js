Ext.onReady(function(){
    Ext.QuickTips.init();
    var fp = new Ext.FormPanel({
        frame: true,
        title:'',
        labelWidth: 110,
        renderTo:'form-ct',
        bodyStyle: 'padding:0 10px 0;',
        items: str,
        tbar:['名称：', new Ext.form.TextField({   
        		id:'findvalue',
                width:175, 
                name: 'findvalue',
                allowBlank:true 
            }), {
                    id : 'newModelButton',    
                    icon : "/usm/img/search.png",   
                    cls : "x-btn-text-icon",   
                    handler :function(){
                    	if(Ext.get("findvalue").dom.value!="")
                    	findIt(Ext.get("findvalue").dom.value);
                    }     
            },{
            	 id : 'save',    
                 icon : "/usm/img/save.gif",   
                 cls : "x-btn-text-icon", 
                 text:'保 存',
                 handler: function(){
	                fp.getForm().submit({url:'/usm/sysmoduleAllRemove.do',method:'POST',waitMsg:'正在保存数据...',success:function(form,action){ 
	  					Ext.Msg.alert('操作','操作成功');
	  				
	  				}, failure:function(form,action){ 
	  					Ext.MessageBox.alert('错误', '操作失败!'); 
	  				}
	  	            });
                 }
            },{
	           	 id : 'cancel',    
	             icon : "/usm/img/cancel.gif",   
	             cls : "x-btn-text-icon", 
	             text:'取 消',
	             handler: function(){
	                fp.getForm().reset();
	            }
            }
           ]

    });
    
});


