
Ext.Desktop = function(app){
	
	this.taskbar = new Ext.ux.TaskBar(app);
	var taskbar = this.taskbar;
	this.el = Ext.get('x-desktop');
	var desktopEl = this.el;
	this.contextmenu=DesktopAction.getBodyContextmenu(this);
	
    var taskbarEl = Ext.get('ux-taskbar');
   
    this.shortcuts = new Ext.ux.Shortcuts({
    	renderTo: 'x-desktop',
    	taskbarEl: taskbarEl
    });
	

    this.el.on('contextmenu', function(e){
	  var target = e.getTarget();
		if (target.id=='x-desktop'){
			e.stopEvent();
        if(!this.contextmenu.el){
            this.contextmenu.render();
        }
			
        var xy = e.getXY();
        xy[1] = xy[1];
		this.contextmenu.showAt(xy);
		}
    }, this);
		
	this.config = {};
	this.initialConfig = null;

    var windows = new Ext.WindowGroup();
    var activeWindow;
		
    function minimizeWin(win){
        win.minimized = true;
        try{
		sendNSCommand("ognl","minimize('"+win.id+"')");
		}catch(e){}
        win.hide();
    }
	function maximizeWin(win){
        win.minimized = false;
      try{
		sendNSCommand("ognl","maximize('"+win.id+"')");	
		  	}catch(e){
		  	win.maximize()
		  	}
       // win.maximize();
    }

    function markActive(win){
		
        if(activeWindow && activeWindow != win){
		    markInactive(activeWindow);
        }else{
			beforeshow(win);
		}
	
		if (win.module){
			win.module.markActive();
		}
        taskbar.setActiveButton(win.taskButton);
        activeWindow = win;
        Ext.fly(win.taskButton.el).addClass('active-win');
	
        win.minimized = false;
        try{
		sendNSCommand("ognl","markActive('"+win.id+"')");	
			}catch(e){
		
		  	}
    }
	
	

    function markInactive(win){
        if(win == activeWindow){
            activeWindow = null;
            Ext.fly(win.taskButton.el).removeClass('active-win');
            try{
			sendNSCommand("ognl","markInactive('"+win.id+"')");	
				}catch(e){
		  	
		  	}
        }
    }

    function removeWin(win){
    	taskbar.taskButtonPanel.remove(win.taskButton);
        layout();
    }
	
	function saveWin(win){
	    var module= win.module;
		var modulePropertys="";
	   var launchers=this.config;
		  var launchers=this.config.launchers;
		  var styles=this.config.styles;
			if (module){
				module.winCfg.x=win.x;
				module.winCfg.y=win.y;
				module.winCfg.width=win.getFrameWidth()+win.getInnerWidth() ;
				module.winCfg.height=win.getFrameHeight()+win.getInnerHeight();
				modulePropertys=modulePropertys+module.id+":"+win.x+":"+win.y+":"+module.winCfg.width +":"+module.winCfg.height;
			}
			
			 var params=$H(
		  {
			'modulePropertys':modulePropertys,
			personId:personid
			});
			
	    JDS.ajax.Connection.LoadJsonFromUrl(context+"updateSeting.action",null,params);
    }
	
	function beforeshow(win){
	    var module= win.module;
		if (module && module.xtype!='FormModule'){
			module.win=this;
			if (module.id){
				if (app.openerModules.containsKey(module.id)){
					app.openerModules.removeKey(module.id);
				}
				app.openerModules.insert(0,module.id,module);
			}
		}
    }

    function layout(){
    	desktopEl.setHeight(Ext.lib.Dom.getViewHeight() - taskbarEl.getHeight());
    }
    Ext.EventManager.onWindowResize(layout);




    this.saveDesk=function (saveAll){
	 var modulePropertys="";
	 var launchers=this.config.launchers;
     var styles=this.config.styles;
	 var opener=[];
	if (saveAll){
		   var count=JDSDesk.getDesktop().taskbar.taskButtonPanel.items.length;
			launchers.autorun=[];
			for(var i=0;i<count;i++){
				var win=JDSDesk.getDesktop().taskbar.taskButtonPanel.items[i].win;
				var win=JDSDesk.getDesktop().getWindow(win.id);
				var moduleId=win.module.id;
				if (moduleId && win.module.xtype!='FormModule'){
					  var w = win.getFrameWidth()+win.getInnerWidth();
					  var h = win.getFrameHeight()+win.getInnerHeight();
					  modulePropertys=modulePropertys+moduleId+":"+win.x+":"+win.y+":"+w +":"+h;
					if (launchers.autorun.indexOf(moduleId)==-1){
						launchers.autorun.push(moduleId);
					}
				}
				if(count-1!=i){
					modulePropertys=modulePropertys+","; 
		 	    }
		    }
			app.openerModules.eachKey(function(key){
					 if (opener.length<7){ 
							opener.push(key);
					    }
			       }
		      )
			
		}
	
	   var params=$H(
		{
			'modulePropertys':modulePropertys,
			'desktop.shortcut':Ext.encode(launchers.shortcut),
			'desktop.autorun':Ext.encode(launchers.autorun),
			'desktop.quickstart':Ext.encode(launchers.quickstart),
            'desktop.openerwin':Ext.encode(opener),
			'desktop.backGroundColor':styles.backgroundcolor,
			'desktop.wallPaperPosition':styles.wallpaperposition,
			'desktop.fontColor':styles.fontcolor,
			'desktop.themeId':styles.theme.id,
			'desktop.wallPaperId':styles.wallpaper.id,
			personId:personid
			});
			
			
	    JDS.ajax.Connection.LoadJsonFromUrlByDeskTop(context+"updateSeting.action",null,params);
    }

    this.layout = layout;
	this.saveWin = saveWin;
	
	this.markActive=markActive;
	
   this.createIframeWindow = function(config, cls){
    	var win = new (cls||Ext.Window)(
            Ext.applyIf(config||{}, {
                manager: windows,
                minimizable: true,
                maximizable: true
            })
        );
        win.on({
        	'activate': {
        		fn: function(){ 
        		try{
						 sendNSCommand("ognl","markActive('"+window.id+"'");	
					}catch(e){}
	
				}
        	},
        	'deactivate': {
        		fn: markIframeInactive
        	},
        	'minimize': {
        		fn: minimizeIframeWin
				
        	},

        	'close': {
        		fn: removeWin
        	}
        });
      
        layout();
        return win;
		win.render(desktopEl);
        win.taskButton = taskbar.taskButtonPanel.add(win);
	}
	
    this.createWindow = function(config, cls){
	
    	var win = new (cls||Ext.Window)(
            Ext.applyIf(config||{}, {
                manager: windows,
                minimizable: true,
                maximizable: true
            })
        );

        win.render(desktopEl);
        win.taskButton = taskbar.taskButtonPanel.add(win);
        win.cmenu = new Ext.menu.Menu({
            items: [

            ]
        });
        //win.animateTarget = win.taskButton.el;
        win.on({
        	'activate': {
        		fn: markActive
        	},
        	'deactivate': {
        		fn: markInactive
        	},
        	'minimize': {
        		fn: minimizeWin
				
        	},
			'maximize': {
        		fn: maximizeWin
				
        	},
			'beforeclose':{
				fn: saveWin,
				scope:this
			},
        	'close': {
        		fn: removeWin
        	}
        });
      
        layout();
        return win;
    };

    this.getManager = function(){
        return windows;
    };

    this.getWindow = function(id){
        return windows.get(id);
    };
    
    this.getViewHeight = function(){
    	return (Ext.lib.Dom.getViewHeight()-taskbarEl.getHeight());
    };
    
    this.getViewWidth = function(){
    	return Ext.lib.Dom.getViewWidth();
    };
    
    this.getWinWidth = function(){
		var width = Ext.lib.Dom.getViewWidth();
		return width < 200 ? 200 : width;
	};
		
	this.getWinHeight = function(){
		var height = (Ext.lib.Dom.getViewHeight()-taskbarEl.getHeight());
		return height < 100 ? 100 : height;
	};
		
	this.getWinX = function(width){
		return (Ext.lib.Dom.getViewWidth() - width) / 2
	};
		
	this.getWinY = function(height){
		return (Ext.lib.Dom.getViewHeight()-taskbarEl.getHeight() - height) / 2;
	};
	
	this.setBackgroundColor = function(hex){
		
		if(hex){
			Ext.get(document.body).setStyle('background-color', '#'+hex);
			this.config.styles.backgroundcolor = hex;
		}
		
	};
	
	this.setFontColor = function(hex){
		if(hex){
			Ext.util.CSS.updateRule('.ux-shortcut-btn-text', 'color', '#'+hex);
			this.config.styles.fontcolor = hex;
		}
	};
	
	this.setTheme = function(o){
		if(o && o.id && o.name && o.pathtofile){
			Ext.util.CSS.swapStyleSheet('theme', o.pathtofile);
			this.config.styles.theme = o;
		}
	};
	
	this.setTransparency = function(b){
		
		if(String(b) != ""){
			if(b){
				 taskbarEl.addClass("transparent");
			}else{
				taskbarEl.removeClass("transparent");
			}
			this.config.styles.transparency = b
		}
	};
	
	this.setWallpaper = function(o){
		if(o && o.id && o.name && o.pathtofile){
			
			var notifyWin = this.showNotification({
				html: '正在装载壁纸'
				, title: '请稍候'
			});
			
			var wp = new Image();
			wp.src = o.pathtofile;
			
			var task = new Ext.util.DelayedTask(verify, this);
			task.delay(200);
			
			this.config.styles.wallpaper = o;
		}
		
		function verify(){
			if(wp.complete){
				task.cancel();
				notifyWin.setIconClass('x-icon-done');
				notifyWin.setTitle('装载完成');
				notifyWin.setMessage('正在更新请稍候。。。');
				this.hideNotification(notifyWin);
				
				document.body.background = wp.src;
			}else{
				task.delay(200);
			}
		}
	};
	
	this.setWallpaperPosition = function(pos){
		if(pos){
			if(pos === "center"){
				var b = Ext.get(document.body);
				b.removeClass('wallpaper-tile');
				b.addClass('wallpaper-center');
			}else if(pos === "tile"){
				var b = Ext.get(document.body);
				b.removeClass('wallpaper-center');
				b.addClass('wallpaper-tile');
			}			
			this.config.styles.wallpaperposition = pos;
		}
	};
	
	this.showNotification = function(config){
		var win = new Ext.ux.Notification(Ext.apply({
			animateTarget: taskbarEl
			, autoDestroy: true
			, hideDelay: 5000
			, html: ''
			, iconCls: 'x-icon-waiting'
			, title: ''
		}, config));
		win.show();

		return win;
	};
	
	this.hideNotification = function(win, delay){
		if(win){
			(function(){ win.animHide(); }).defer(delay || 3000);
		}
	};
	
	this.addAutoRun = function(id){
		var m = app.getModule(id),
			c = this.config.launchers.autorun;
			
		if(m && !m.autorun){
			m.autorun = true;
			c.push(id);
		}
	};
	
	this.removeAutoRun = function(id){
		var m = app.getModule(id),
			c = this.config.launchers.autorun;
			
		if(m && m.autorun){
			var i = 0;
				
			while(i < c.length){
				if(c[i] == id){
					c.splice(i, 1);
				}else{
					i++;
				}
			}
			
			m.autorun = null;
		}
	};

	this.addShortcut = function(id,updateConfig,module,index){
		if(!module){
				module = app.getModule(id);
		}
		
		if(module && !module.shortcut){
			
			module.shortcut = this.shortcuts.addShortcut({
				handler: module.handler,
				moduleId:id?id:moduleId,
				iconCls: module.iconCls?module.iconCls+"-shortcut": '',
				scope: module,
				text: module.text,
				index:index
			});
			
		  if (module.icon){
		  	module.shortcut.el.child('img:first').applyStyles(
				{
				  filter:"progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+module.icon+"', sizingMethod='scale')"
				}
			)	
		  }
				
			if(updateConfig){
	    	    var tIndex=getIndexOfArr(id,this.config.launchers.shortcut);
				if (tIndex>0){
					 shortcuts.splice(tIndex,1);
					 this.config.launchers.shortcut.splice(index,0,id);
				}else{
					this.config.launchers.shortcut.splice(index?index:this.config.launchers.shortcut.length,0,id);
				}
			}
		}
		
	};
	
	
	this.removeUserDefModule = function(ids){
		 fnbtn=function(btn){
		 	if (btn!='yes'){
				return;
			}
			var userModuleStr=[];
			ids.each(function(item,index){
			 	var module = JDSDesk.getModule(item);
				
				
				if (module.path){
					module.path="RECYCLER";
					userModuleStr.push("moduleIdList["+index+"]="+item);
				}
				JDSDesk.getDesktop().removeShortcut(item,true);		
				JDSDesk.getDesktop().saveDesk();	
			});
			userModuleStr.push("personId="+personid);	
			userModuleStr.push("path="+"RECYCLER");	
		
			
		    var url=context+'deletModules.action';
			var evalJs=function(o){};         
		 	JDS.ajax.Connection.LoadJsonFromUrlByDeskTop(url,evalJs,userModuleStr.join("&"));
		} 
		  Ext.MessageBox.show({
					           title:"删除文件",
					           msg:"确实要将（"+ids.length+"）放入回收站吗？",
					           width:300,
					           buttons : {           
					           yes : "是",
					           no : "否"
					        },
					           fn: fnbtn,
							   scope:this,
					           value:ids,
					          // animEl:document.body,
					           icon: Ext.MessageBox.QUESTION
					       });
         
		
	};

	this.removeShortcut = function(id, updateConfig){
		var m = app.getModule(id);
		
		if(m && m.shortcut){
			this.shortcuts.removeShortcut(m.shortcut);
			m.shortcut = null;
			
			if(updateConfig){
				var sc = this.config.launchers.shortcut,
					i = 0;
				while(i < sc.length){
					if(sc[i] == id){
						sc.splice(i, 1);
					}else{
						i++;
					}
				}
			}
		}
	};

	this.addQuickStartButton = function(id, updateConfig,index){
    	var m = app.getModule(id);
		
		
		
		if(m && !m.quickStartButton){
				m.quickStartButton = this.taskbar.quickStartPanel.add({
				handler: m.handler,
				//iconCls: m.iconCls,
				icon:m.icon,
				scope: m,
				text: m.text,
				moduleId:m.id,
				index:index,
				tooltip: m.tooltip || m.text
			});
			if (m.icon){
		  	  m.quickStartButton.el.child('button:first').applyStyles(
				{
				  filter:"progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+m.icon+"', sizingMethod='scale')"
				}
			  )	
		     }
			if(updateConfig){
				this.config.launchers.quickstart.push(id);
			}
		}
    };
    
    this.removeQuickStartButton = function(id, updateConfig){
    	var m = app.getModule(id);
    	
		if(m && m.quickStartButton){
			this.taskbar.quickStartPanel.remove(m.quickStartButton);
			m.quickStartButton = null;
			
			if(updateConfig){
				var qs = this.config.launchers.quickstart,
					i = 0;
				while(i < qs.length){
					if(qs[i] == id){
						qs.splice(i, 1);
					}else{
						i++;
					}
				}
			}
		}
    };

    layout();
    
    this.cmenu = new Ext.menu.Menu();
    
    Ext.getBody().on('contextmenu', function(e){
    	if(e.target.id === desktopEl.id){
	    	e.stopEvent();
			if(!this.cmenu.el){
				this.cmenu.render();
			}
			var xy = e.getXY();
			xy[1] -= this.cmenu.el.getHeight();
			this.cmenu.showAt(xy);
		}else if (e.within('ux-taskbar') ||e.within('Bar')){
			e.stopEvent();
		}
	}, this);
	

};



Ext.ux.NotificationMgr = {
    positions: []
};

Ext.ux.Notification = Ext.extend(Ext.Window, {
	initComponent : function(){
		Ext.apply(this, {
			iconCls: this.iconCls || 'x-icon-information'
			, width: 200
			, autoHeight: true
			, closable: true
			, plain: false
			, draggable: false
			, bodyStyle: 'text-align:left;padding:10px;'
			, resizable: false
		});
		if(this.autoDestroy){
			this.task = new Ext.util.DelayedTask(this.close, this);
		}else{
			this.closable = true;
		}
		Ext.ux.Notification.superclass.initComponent.call(this);
    }

	, setMessage : function(msg){
		this.body.update(msg);
	}
	
	, setTitle : function(title, iconCls){
        Ext.ux.Notification.superclass.setTitle.call(this, title, iconCls||this.iconCls);
    }

	, onRender : function(ct, position) {
		Ext.ux.Notification.superclass.onRender.call(this, ct, position);
	}

	, onDestroy : function(){
		Ext.ux.NotificationMgr.positions.remove(this.pos);
		Ext.ux.Notification.superclass.onDestroy.call(this);
		officeMsg={};
	}

	, afterShow : function(){
		Ext.ux.Notification.superclass.afterShow.call(this);
		this.on('move', function(){
			Ext.ux.NotificationMgr.positions.remove(this.pos);
			if(this.autoDestroy){
				this.task.cancel();
			}
		}, this);
		if(this.autoDestroy){
			this.task.delay(this.hideDelay || 5000);
		}
	}

	, animShow : function(){
		this.pos = 0;
		while(Ext.ux.NotificationMgr.positions.indexOf(this.pos)>-1){
			this.pos++;
		}
		Ext.ux.NotificationMgr.positions.push(this.pos);
		this.setSize(200,100);
		this.el.alignTo(this.animateTarget || document, "br-tr", [ -1, -1-((this.getSize().height+10)*this.pos) ]);
		this.el.slideIn('b', {
			duration: .7
			, callback: this.afterShow
			, scope: this
		});
	}

	, animHide : function(){
		Ext.ux.NotificationMgr.positions.remove(this.pos);
		this.el.ghost("b", {
			duration: 1
			, remove: true
		});
	}
});

