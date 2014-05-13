/*
 * Ext JS Library 2.0.1
 * Copyright(c) 2006-2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */

Ext.onReady(function(){

    Ext.QuickTips.init();

    // turn on validation errors beside the field globally
    Ext.form.Field.prototype.msgTarget = 'side';

    var bd = Ext.getBody();

    /*
     * ================  Simple form  =======================
     */ bd.createChild({tag: 'h2', html: ''});


   var simple = new Ext.FormPanel({
		labelAlign: 'right',    
		title: '',    
		buttonAlign:'center',    
		bodyStyle:'padding:8px',    
		anchor:'100%',  
		frame:true,    
       items: [ 
	                 {
						id:'grpid',
	                    xtype:'textfield',	
	                    fieldLabel: '* 用户组编号',	
	                    name: 'usergroup.grpid',
	                    value:grpid,
	                    allowBlank:false,
	                    blankText:'用户组编号不能为空',
	                    readOnly:true,
		                anchor:'65%'
	
	                },{
						id:'grpname',
	                    xtype:'textfield',	
	                    fieldLabel: '* 用户组名称',	
	                    name: 'usergroup.grpname',
	                    value:grpname,
	                    allowBlank:false,
	                    blankText:'用户组名称不能为空',
		                anchor:'65%'
	
	                },{
						id:'createtime',
	                    xtype:'textfield',	
	                    fieldLabel: '创建时间',	
	                    name: 'usergroup.createtime',
	                    readOnly:true,
	                    value:createtime,
	                  
		                anchor:'65%'
	
	                },
	                {
                        id:'grpdesc',
                        xtype:'textfield',	
	                    fieldLabel: '排序',
	                    value:grpdesc,	
	                    name: 'grpdesc',
	                    readOnly:true,
		                anchor:'65%'
                   
                   }
            ],      
 buttons: [{
            text: '修改',
            handler: function(){
                if(simple.form.isValid()) {
                    this.disabled=true;    
                    flag = false;
                    simple.getForm().submit({ 
                        url: '/usm/userGroupModify.do?grpid='+grpid,
                        method: 'POST',
                        waitTitle: '请稍等……', 
                        waitMsg: '正在上传数据……',  
                        success: function() {
                            Ext.Msg.alert("成功", "操作成功！");
                             window.location="getuserGroupDetailInfo.do?grpid="+grpid; 
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
            text: '重置',
            handler: function() {
                simple.form.reset();
            }
        }    
        ]
    });

    simple.render(document.body);

});