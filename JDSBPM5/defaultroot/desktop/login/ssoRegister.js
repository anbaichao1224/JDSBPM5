
/**
 * 
 * @param {Object} config
 */
SSoManager={
	
	getSsoPanel:function (ssoid){
    
		 var ssofp = new Ext.FormPanel({
            url: context+'ssoRegister.action',
            frame: true,
            labelAlign: 'right',
            labelWidth: 85,
            width: 360,
            items: [new Ext.form.FieldSet({
                title: '系统注册',
                autoHeight: true,
                defaultType: 'textfield',
                items: [{
                    fieldLabel: '登录名',
                    allowBlank: false,
                    name: 'ssoname',
                    id: Ext.id(),
                    value: this.text,
                    width: 230
                }, {
                    fieldLabel: '密码',
                    //vtype: "url",
                     inputType : 'password',
                    name: 'ssopassword',
                    id: Ext.id(),
                    value:  this.url,
                    width: 230
                }]
            })]
        });
       
	   var handler=function(){
	            if (ssofp.form.isValid()) {
	                var urlstr = context+'ssoRegister.action';
	                ssofp.form.submit({
	                    url: urlstr,
	                    params:{ssoid:ssoid},
	                    success: function(form, action){
	                     Ext.Msg.alert('提示', '注册成功，请重新点击该模块!');
	                            this.win.close();
	                    },
	                    failure: function(form, action){
	                        Ext.Msg.alert('提示', '保存失败!');
	                    },
	                    waitMsg: '正在保存数据，稍后...',
	                    scope: this
	                
	                }, this);
	                
	            }
	            else {
	                Ext.Msg.alert('信息', '请填写完成再提交!');
	            }
          }
	   
        ssofp.addButton('保 存',handler, this)
        
        ssofp.addButton('取 消', function(){
            this.win.close();
        }, this);
        
       
        this.win = new Ext.Window({
            title: '单点登录注册',
            xtype: 'window',
            modal: 'true',
            width: 377,
            //  height : 180, 
            collapsible: true,//是否可以折叠 
            closable: true,//是否可以关闭 
            maximizable: true,//是否可以最大化 
            closeAction: 'hide',
            plain: true,
            items: [ssofp]
        })
        this.win.show();
        
	}
	
	
}
		
