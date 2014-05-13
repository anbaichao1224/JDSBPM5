Ext.onReady(function(){

    Ext.QuickTips.init();

    // turn on validation errors beside the field globally
    Ext.form.Field.prototype.msgTarget = 'side';

    

    var simple = new Ext.FormPanel({
		labelAlign: 'right',    
		title: '部门信息添加窗口',    
		buttonAlign:'right',    
		bodyStyle:'padding:8px',    
		anchor:'100%',  
		frame:true,    
       items: [{
            layout:'column',
            border:false,
            labelSeparator:'=======',
            items:[{
               columnWidth:.5,            
               layout: 'form',            
               border:false,            
               items:[
                   {                
		               xtype:'textfield',                
		               fieldLabel: '*岗位名称',                
		               name: 'position.positionname',                
		               anchor:'100%'		               
	                }, 
	                {	
	                   xtype:'textfield',                
		               fieldLabel: '*岗位说明',                
		               name: 'position.positiondesc',                
		               anchor:'100%'
	                	                    
	                 }],
	                 buttons: [{
            text: '添加',
           handler: function(){
            simple.getForm().submit({url:'/usm/postionSave.do',method:'POST',waitMsg:'正在保存信息......',success:function(form,action){ 
            alert("success"); 
          }, failure:function(form,action){ 
             alert("failure"); 
         }
            });
            }
        }, {
            text: '重置',
            handler: function() {
                simple.form.reset();
            }
        }, {
            text: '返回',
            handler: function() {
                window.location="positionqueryListgrid.jsp?sysid='"+sysid+"'&type='"+type+"'";  
            }
        }    ]
	               
              }]
       }]
                 
       

    });

    simple.render(document.body);  
});