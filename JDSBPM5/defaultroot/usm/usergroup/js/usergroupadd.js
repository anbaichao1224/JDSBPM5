/*
 * 用户组信息列表
 */
Ext.onReady(function(){

    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';
    var bd = Ext.getBody();
    var simple = new Ext.FormPanel({
        labelWidth: 95, // label settings here cascade unless overridden
        labelAlign: 'right',
        height:145,
        buttonAlign:'center',
        bodyStyle:'padding:5px 5px 0',
		anchor:'100%',  
		frame:true,  
        items: [{
            layout:'column',
            border:false,
            items:[{
               columnWidth:.6,            
               layout: 'form',            
               border:false,            
               items:[
                     
	                {
	               	       id:'grpname',
			               xtype:'textfield',                
			               fieldLabel: '* 用户组名称',                
			               name: 'usergroup.grpname',  
			               allowBlank:false,
			               blankText:'用户组名称不能为空',  
			               anchor:'100%'
		                }
	                ]
              }]

        }],

 buttons: [{
            text: '保   存',
            handler: function(){
            	var grpname=Ext.get('grpname').dom.value;
            	if(grpname=='')
            	{
            		Ext.MessageBox.alert('用户组名称','用户组不能为空');
            	}
                if(simple.form.isValid()) {
                    this.disabled=true;    
                    flag = false;
                    simple.getForm().submit({
                        
                        url: '/usm/userGroupSave.do?sysid='+sysid+'',
                        method: 'POST',
                        waitTitle: '请稍等……', 
                        waitMsg: '正在上传数据……',  
                        success: function() {
                            Ext.Msg.alert("成功", "添加成功！",showusergroupadd);
                            this.disabled=false;
                        },
                        failure: function() {
                            Ext.Msg.alert("出错啦", "数据保存失败！");
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
        }, {
            text: '退  出',
            handler: function() {
            	window.parent.location.replace('/usm/usergroup/usergroupqueryListgrid.jsp?sysid='+sysid+'&type='+type+'');
              // window.location="usergroupListgrid.jsp"; 
            }
        }
        ]

    });
    simple.render(document.body);
    function showusergroupadd()
    {
    	window.location="usergroupadd.jsp?sysid="+sysid+"";
    }

});