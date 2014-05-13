Ext.BLANK_IMAGE_URL='/usm/resources/images/default/s.gif';
Ext.onReady(function(){
    Ext.QuickTips.init();
    
    var fp = new Ext.FormPanel({
        frame: true,
        split:true,
        collapsible: true,
        labelWidth: 110,
        width:820,
        height:660,
        renderTo:'form-ct',
        bodyStyle: 'padding:0 10px 0;',
        items: str,
         /*tbar:['名称：', new Ext.form.TextField({   
        		id:'findvalue',
                width:175, 
                name: 'findvalue',
emptyText:'输入"空格"后点击查询按钮',
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
			            }
                    }  
            }
           ],*/
        buttons: [{
            text: '保存',
            handler: function(){
              fp.getForm().submit({url:'/usm/rightTemplateSave.do',method:'POST',waitMsg:'正在保存数据...',success:function(form,action){ 
					Ext.Msg.alert('操作','操作成功');
				
				}, failure:function(form,action){ 
					Ext.MessageBox.alert('错误', '操作失败!'); 
				}
	            });
            }
        },{
            text: '取消',
            handler: function(){
                fp.getForm().reset();
            }
        }]
    });

});


