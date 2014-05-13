
Ext.namespace("Ext.ux","Ext.Desktop");
Ext.app.App = function(cfg){
    Ext.apply(this, cfg);
			
	//所有模块
	this.allModules=new Ext.util.MixedCollection(); 
	//本地模块
	this.localModules=new Ext.util.MixedCollection();
	//用户自定义模块
	this.userDefMoudles=new Ext.util.MixedCollection();
	//系统模块
	this.systemModules=new Ext.util.MixedCollection();
	//当前打开的正在运行的MODULE
	this.openerModules=new Ext.util.MixedCollection();
	

	
    this.addEvents({
        'ready' : true,
        'beforeunload' : true
    });	
	
	
//	
//	 var evalJs = function(o){
//				 Ext.apply(this,Ext.decode(o));
//				  this.desktop = new Ext.Desktop(this);
//				  this.desktop.config = this.desktopConfig;
//				 this.initStyles(this.desktopConfig.styles);
//			
//   };
//     JDS.ajax.Connection.LoadJsonFromUrl("/stylesAction.action",evalJs,$H({personId:personid}),this);	

//startwin();

	Ext.onReady(this.load, this);
	
 
};

Ext.extend(Ext.app.App, Ext.util.Observable, {
    isReady : false,
    modules : null,
	url:"/desktopAction.action?VPNUrl="+clientUrl,
	params:$H({personId:personid}),
	render:function(){
		
		    this.moduleCfg.systemModules.each(function(item){
			var systemModule=new Ext.app.SystemModule(item);
			
			this.allModules.add(systemModule.id,systemModule);
			this.systemModules.add(systemModule.id,systemModule);
		},this);
		
		this.moduleCfg.userDefModules.each(function(item){
			var userDefMoudle=new Ext.app.UserDefModule(item);
			this.allModules.add(userDefMoudle.id,userDefMoudle);
			this.userDefMoudles.add(userDefMoudle.id,userDefMoudle);
		},this);
		
		this.getLocalModules().each(function(item){
			this.allModules.add(item.moduleId,item);
			this.localModules.add(item.moduleId,item);
		},this);
		this.openerwin.each(function(key){
				var module=this.allModules.get(key);	
				if (module){
					this.openerModules.add(key,module);
				}
		},this);
	

		 this.initApp();
	},
	
     load:function (){
	 	JDS.ajax.Connection.load(this);
	 },
  
    initApp : function(){
		// prevent backspace (history -1) shortcut
		var map = new Ext.KeyMap(document, [
		{
			key: Ext.EventObject.BACKSPACE,			//stopEvent: true,
			fn: function(key, e){
				var t = e.target.tagName;
			
				if(t != "INPUT" && t != "TEXTAREA"){
					e.stopEvent();
				}
			}
		}]);
		
	
   	    this.startConfig = this.startConfig || this.getStartConfig();	
		this.initStartMenuCfg();
       this.desktop = new Ext.Desktop(this);
	     
		this.startMenu = this.desktop.taskbar.startMenu;

        this.initDesktopConfig(this.desktopConfig);
		
	    this.init();
        Ext.EventManager.on(window, 'beforeunload', this.onUnload, this);
		this.fireEvent('ready', this);
        this.isReady = true;
    },

	getModules : Ext.emptyFn,
    getStartConfig : Ext.emptyFn,
    getLogoutButtonConfig : Ext.emptyFn,
	getLocalModules:[],
	getDesktopConfig : Ext.emptyFn,
    init : Ext.emptyFn,

   
    initDesktopConfig : function(l){
    	if(!l){
			l=this.desktopConfig;
		}
		
			l.contextmenu = l.contextmenu || [];
			l.startmenutool = l.startmenutool || [];
			l.quickstart = l.launchers.quickstart || [];
			l.shortcut = l.launchers.shortcut || [];
			l.styles = l.styles || [];
			l.autorun = l.launchers.autorun || [];
			if(l.autorun.toString().indexOf('jryw')<0)  //宣武国土项目添加
			{
				l.autorun.push('jryw');
			}
		//	if(l.autorun.toString().indexOf('looknotice1')<0)
		//	{
		//		l.autorun.push('looknotice1');
		//	} 
		//	JDS.alert(l.autorun); 
			this.desktop.config = l;
		    this.initQuickStart(l.quickstart);
	        this.initShortcuts(l.shortcut);
	        this.initStyles(l.styles);
			
			if (!this.isReady ){
				this.initContextMenu(l.contextmenu);
				this.initStartMenu(l.startmenu);
		        this.initToolModule(l.startmenutool);
		        this.initLogoutButton();
				this.initAutoRun(l.autorun);
			}
	  
    },
    
    initAutoRun : function(mIds){
    	if(mIds){
    		for(var i = 0, len = mIds.length; i < len; i++){
	            var m = this.getModule(mIds[i]);
	            if(m){
	            	m.autorun = true;	
					try{
						m.createWindow();
					}catch(e){
						//_createDesktopWinById(mIds[i],{x:100,y:100});
					}
	            	
	            }
			}
		}
    },

    initContextMenu : function(mIds){
    	if(mIds){
    		for(var i = 0, len = mIds.length; i < len; i++){
    			this.desktop.addContextMenuItem(mIds[i]);
	        }
    	}
    },
    
    initLogoutButton : function(){
    	var config = this.getLogoutButtonConfig();
    	this.desktop.taskbar.startMenu.addTool(config);
    },

    initShortcuts : function(mIds){
		if(mIds){
			for(var i = 0, len = mIds.length; i < len; i++){
	            this.desktop.addShortcut(mIds[i], false);
	        }
		}
//	this.desktop.addShortcut('JDSExplorer',false);
//		this.userDefMoudles.each(function(item){
//		    if (item.path=='DESK'){
//				this.desktop.addShortcut(item.id,true);
//			}
//		},this)
		
    },
   initStartMenuCfg : function(mIds){	
	 init=function(cfg){
			this.createWindow;
			if (cfg.menu){
			 	this.createWindow=function(){return false;}
				    cfg.menu.each(function(item){
					   Ext.apply(item,{ parentText : cfg.text});
					  init(item);
				    })
			 }else{	
			 if ( JDSDesk.getModule(cfg.id)){
			 	
			 	  JDSDesk.getModule(cfg.id).parentText=cfg.parentText;
			 	  this.createWindow=function (){
						 JDSDesk.getModule(this.id).createWindow();	
			      }
				  this.icon=JDSDesk.getModule(cfg.id).icon;
			   }  
			 }
			
		  Ext.apply(cfg,{ handler : this.createWindow,icon:this.icon});
		};
		
		this.startMenuCfg.each(function (item){
			   init(item)

		},this)
	 	
	 },
    initStartMenu : function(mIds){	
	
	//本地模块
	/*	 this.localModules.each(function (item,index,count){
	      	var menu=item.getMenu()
			this.startMenu.add(menu);
		},this);
	
			this.startMenu.addSeparator(); */
		
		
	//系统菜单	
       this.startMenuCfg.each(function (item){
		     this.startMenu.add(item);
		},this);
    
   	 this.startMenu.addSeparator();
   	 
   	 //当前打开的模块
		var subOpeners=this.openerModules.getRange(0,3);
		subOpeners.each(function(module,index,count){
			this.startMenu.add(module.getMenu());
		},this)
		
	//	this.startMenu.addSeparator();
			
//	国土配置
 /*      this.startMenuCfg.each(function (item){
			
		     this.startMenu.add(item);
		},this)
    	this.startMenu.addSeparator();
		 this.localModules.each(function (item,index,count){
	      	var menu=item.getMenu()
			this.startMenu.add(menu);
		},this);
		*/
    },
	
   initToolModule : function(mIds){		
		if(mIds){	        
	        for(var i = 0, iLen = mIds.length; i < iLen; i++){
			  var m = this.getModule(mIds[i]);
			  	if (m.launcher) {
					menu.addTool(m.launcher);
				}
	        }
		}
	},

	initQuickStart : function(mIds){
		if(mIds){
			for(var i = 0, len = mIds.length; i < len; i++){
	            this.desktop.addQuickStartButton(mIds[i], false);
	        }
			
		}
	//	 this.desktop.addQuickStartButton('jame', false);
    },
    
    initStyles : function(s){
			
    	this.desktop.setBackgroundColor(s.backgroundcolor);
		
    	this.desktop.setFontColor(s.fontcolor);
    	this.desktop.setTheme({
    		id: s.theme.id,
    		name: s.theme.name,
    		pathtofile:s.theme.pathtofile
    	});
		
    	this.desktop.setTransparency(s.transparency);
    	this.desktop.setWallpaper({
    		id: s.wallpaperid,
    		name: s.wallpapername,
    		pathtofile: s.wallpaperfile
    	});
	
    	this.desktop.setWallpaperPosition(s.wallpaperposition);
    },
  
    getModule : function(v){
		return this.allModules.get(v);
    },
	
	

    onReady : function(fn, scope){
        if(!this.isReady){
            this.on('ready', fn, scope);
        }else{
            fn.call(scope, this);
        }
    },

    getDesktop : function(){
        return this.desktop;
    },
    
    getModuleById : function(v){
		return this.allModules.find(function (o){
				return o.uuid==v;
		});
    },

    onUnload : function(e){
        if(this.fireEvent('beforeunload', this) === false){
            e.stopEvent();
        }
    }
	
	
});
