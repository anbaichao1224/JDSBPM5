
/**
 * 
 * @param {Object} config
 */
ModuleManager={
	showModulePanel:function(moduleId){
		var module=JDSDesk.getModule(moduleId);
		 var cfg={
				callBack:function(o){
					var userDefMoudle=new Ext.app.UserDefModule(Ext.decode(o).data);	
					if 	(JDSDesk.allModules.containsKey(userDefMoudle.id)){
						var currModule=JDSDesk.getModule(userDefMoudle.id);
						
						if (currModule.shortcut){
							userDefMoudle.shortcut=currModule.shortcut;
							userDefMoudle.shortcut.setText(userDefMoudle.text);
							if (userDefMoudle.icon){
							  	module.shortcut.el.child('img:first').setStyle(
									{
									  filter:"progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+userDefMoudle.icon+"', sizingMethod='scale')"
									}
								)	
							  }
						}
					   	JDSDesk.allModules.replace(userDefMoudle.id,userDefMoudle);
		                JDSDesk.userDefMoudles.replace(userDefMoudle.id,userDefMoudle);
					}else{
						JDSDesk.allModules.add(userDefMoudle.id,userDefMoudle);
		                JDSDesk.userDefMoudles.add(userDefMoudle.id,userDefMoudle);
					    JDSDesk.getDesktop().addShortcut(userDefMoudle.id);	
					}
					
					this.win.close();
				}
			}

		module.creatMdoulePanel(cfg);
	},
	creatMdoulePanel:function(name,uri){ 
		var module=new Ext.app.UserDefModule(
			{text:name==''?'新建':name,
			url:uri==''?'http://':uri,
			path:'DESK'}
		);
	   var cfg={
			callBack:function(o){
				   var userDefMoudle=new Ext.app.UserDefModule(Ext.decode(o).data);		
					JDSDesk.allModules.add(userDefMoudle.id,userDefMoudle);
	                JDSDesk.userDefMoudles.add(userDefMoudle.id,userDefMoudle);
				    JDSDesk.getDesktop().addShortcut(userDefMoudle.id,true);	
					JDSDesk.getDesktop().saveDesk();
					//userDefMoudles.createWindow();
					this.win.close();
			}
		}
		module.creatMdoulePanel(cfg);
	},
	getModulePanel:function (cfg){
	    var detTemplate = new Ext.XTemplate('<div class="details">', '<tpl for=".">', '<table>', '<tr>', '<td width="30%" align="center"><div border=1><img src="{url}"></div></td>', '<td width="68%">', '<div class="details-info">', '图标名称:', '<span>{name}</span><br>', '尺寸:', '<span>{sizeString}</span><br>', '最后修改时间:', '<span>{dateString}</span></div>', '</td>', '</tr>', '</table>', '</tpl>', '</div>');
	    detTemplate.compile();	
			   //建立图片panel
	    var iconDetail = new Ext.Panel({
	    
	        split: true,
	        width: 360,
	        //height:100,
	        minWidth: 150,
	        maxWidth: 250
	    
	    });
		this.iconselect = function(data){
	 
	        Ext.getCmp('module.icon').setValue(data.url);
	        detTemplate.overwrite(iconDetail.body, data);
	        
	    };
    
		 var fs = new Ext.FormPanel({
            url: 'addModule.action',
            frame: true,
            labelAlign: 'right',
            labelWidth: 85,
            width: 360,
            items: [new Ext.form.FieldSet({
                title: '快捷方式配置',
                autoHeight: true,
                defaultType: 'textfield',
                items: [{
                    fieldLabel: '名称',
                    allowBlank: false,
                    name: 'module.name',
                    id: Ext.id(),
                    disabled: this.xtype == 'UserDefModule' ? false : true,
                    value: this.text,
                    width: 230
                }, {
                    fieldLabel: '地址 URL',
                    //vtype: "url",
                    name: 'module.url',
                    id: Ext.id(),
                    disabled: this.xtype == 'UserDefModule' ? false : true,
                    value:  this.url,
                    width: 230
                }, {
                    fieldLabel: '文件类型',
                    name: 'module.fielType',
                    id: Ext.id(),
                    disabled: true,
                    value: this.xtype == 'UserDefModule' ? '用户自定义模块' : '系统模块',
                    width: 230
                }, {
                    fieldLabel: '创建时间',
                    name: 'createTime',
                    id: Ext.id(),
                    disabled: true,
                    value: this.creatTime,
                    width: 230
                }, {
                    fieldLabel: '修改时间',
                    name: 'lastModified',
                    id: Ext.id(),
                    disabled: true,
                    value: this.lastModified,
                    width: 230
                }, {
                    xtype: 'hidden',
                    hidden: true,
                    name: 'module.icon',
                    id: 'module.icon',
					value:this.icon,
                    width: 180
                
                }, {
                    xtype: 'hidden',
                    hidden: true,
                    name: 'personId',
                    id: Ext.id(),
                    value: personid
                }, {
                    xtype: 'hidden',
                    hidden: true,
                    name: 'module.path',
                    value: this.path,
                    id: Ext.id()
                }, {
                    xtype: 'hidden',
                    hidden: true,
                    name: 'moduleId',
                    value:  this.id,
                    id: Ext.id()
                }]
            })]
        });
        fs.add(iconDetail);
        fs.addButton('选择图标', function(){
            //  
            var chooser;
            if (!chooser) {
                chooser = new ImageChooser({
                    url: 'getIcons.action',
                    width: 515,
                    height: 350
                });
            }
            chooser.show(this.iconselect);
        }, this);
        fs.addButton({
            text: '保 存',
            disabled: this.xtype == 'UserDefModule' ? false : true
        }, function(){
            if (fs.form.isValid()) {
                fs.form.submit({
                    url: 'addModule.action',
                    success: function(form, action){
                        if (cfg.callBack) {
                            cfg.callBack.call(this, action.response.responseText)
                        }
                        else {
                            this.win.close();
                        }
                    },
                    failure: function(form, action){
                        Ext.Msg.alert('信息', '设置失败!');
                    },
                    waitMsg: '正在保存数据，稍后...',
                    scope: this
                
                }, this);
                
            }
            else {
                Ext.Msg.alert('信息', '请填写完成再提交!');
            }
        }, this);
        
        fs.addButton('取 消', function(){
            this.win.close();
        }, this);
        
        //构造新建快捷方式的窗体
        this.win = new Ext.Window({
            title: '新建快捷方式',
            xtype: 'window',
            modal: 'true',
            width: 377,
            //  height : 180, 
            collapsible: true,//是否可以折叠 
            closable: true,//是否可以关闭 
            maximizable: true,//是否可以最大化 
            closeAction: 'hide',
            plain: true,
            items: [fs]
        })
        this.win.show();
        
	}
	
	
}
		
