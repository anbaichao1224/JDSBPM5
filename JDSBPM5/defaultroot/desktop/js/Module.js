
function winResizeHandler  (win, width, height){
    if (win.gridResizeFn) {
        win.gridResizeFn.apply(null, arguments);
    }
} ;

var ssoid ="";

/**
 *
 * @param {Object} config
 */
Ext.app.Module = function(config){
    Ext.apply(this, config);
	
    this.xtype = 'Module';
    this.win;
    Ext.app.Module.superclass.constructor.call(this);
      this.getXtype = function(){
        return this.xtype;
    };
	if (this.gbkUrl ){
			if (this.gbkUrl.indexOf('/desktop/UrlEcode.jsp?url=') ==-1) {
		        this.gbkUrl = '/desktop/UrlEcode.jsp?url='+this.gbkUrl;
		}	
		}else{
			this.gbkUrl =this.url;
	 }
	 
	 
	 this.markActive=function(){
	 	//JDS.alert(this);
	 }
	
	
    this.getMenu = function(){
        var menuCfg = {
            style: {
                height: 28
            },
		    icon:this.icon,
            moduleId: this.id ? this.id : this.moduleId,
            text:this.parentText ?  Ext.util.Format.ellipsis(this.parentText+'('+this.text+')',14): this.text,
            iconCls: this.iconCls
        }
        createWindow = function(){
            JDSDesk.getModule(this.moduleId).createWindow();
        }
        Ext.apply(menuCfg, {
            handler: createWindow
        });
        return menuCfg;
    };
	
	
	
    this.creatMdoulePanel = function(cfg){
       ModuleManager.getModulePanel.call(this,cfg);
    };
    

    this.winCfg = {
        width: 850,
        height: 620,
        shim: false,
        animCollapse: false,
        border: false,
        constrainHeader: true,
        layout: 'fit',
        listeners: {
            resize: function(){
                if (this.resizeHandler) {
                    this.resizeHandler.apply(this, arguments);
                }
                else {
                    winResizeHandler.apply(this, arguments);//2009-02加
                }
            }
        }
    };
    
    
    
    this.openWin = function(cfg){
		
        var dispwin;
        try {
            var evalJs = function(o,scope){
                try {
                    Ext.namespace("EVAL");
                    eval(o);
                    var returnCfg = EVAL.getPanel();
                    returnCfg = Ext.apply( returnCfg,cfg)
                    dispwin = getCurDesktop().createWindow(returnCfg);
	                   winTarget = new JDS.dd.JDSDropTarget(dispwin.getEl(), {
	                        ddGroup: 'DesktopShortcuts',
	                        notifyOver: function(dd, e, data){
	                            return this.dropNotAllowed;
	                        }
	                    });
                    
                    if (dispwin.module && dispwin.module.icon) {
                        dispwin.setTitle('<table><tr><td><img style="width:16px;height:16px" src="' + dispwin.module.icon + '"></td><td>' + dispwin.title+'</td></tr></table>');
                    }
                    dispwin.show();
					if (EVAL.callBack){
						EVAL.callBack(dispwin,cfg.module);
					}
                }catch (e) {
					 scope.openByIframe(scope);
                }
            }
            JDS.ajax.Connection.LoadJsonFromUrlByDeskTop(this.url, evalJs, this.params,this);
        } 
        catch (e) {
			  openByIframe(cfg);
        }
        return dispwin;
    }
   
   	
	this.openByIframe=function (cfg){
		var iframeid=Ext.id();
		
			
		var wincfg = Ext.applyIf(cfg.winCfg, {
			title:this.icon? '<table><tr><td><img style="width:16px;height:16px" src="' + this.icon + '"></td><td>' + this.text+'</td></tr></table>':this.text,
			text:this.text,
			x:200,
			y:200,
			height:600,
			width:800,
			module: this
		});
	//	alert("creatWin('"+this.ssourl+"','"+this.id+"','"+this.text+"',"+wincfg.x+","+wincfg.y+","+wincfg.width+","+wincfg.height+")");
		
	
//		
		var p = createIframePanel({
			url: this.url,
			height:cfg.winCfg.height,
			width:cfg.winCfg.width,
			
			title: '',
			icon: this.icon
		});
	
		var desktop = getCurDesktop();
		//JDS.alert(wincfg);
		
		var window = desktop.createWindow(wincfg);
		//JDS.alert(window);
		//alert(window.id);
		// sendNSCommand("ognl","creatWin('"+this.ssourl+"','"+window.id+"','"+this.text+"',"+wincfg.x+","+wincfg.y+","+wincfg.width+","+wincfg.height+")");	
	     window.on("resize" , function(obj, w, h){
			   	p.setSize(w-20,h-35);
			   }
			);
		window.add(p);
		window.show();
	}
   
   
    this.init();
    
}

Ext.extend(Ext.app.Module, Ext.util.Observable, {
    init: Ext.emptyFn
});


/**
 * class localModule
 *
 * @param {Object} config
 */
Ext.app.DefaultModule = function(config){
    Ext.apply(this, config);
    
    Ext.app.DefaultModule.superclass.constructor.call(this);
    this.xtype = 'DefaultModule';
	this.fullTitle=Ext.util.Format.ellipsis(this.url,60);
    this.createWindow = function(){
        if (this.icon) {
            iconCls = null;
        }
        cfg = this.winCfg;
        cfg = Ext.applyIf(cfg, {
            title: this.text,
            module: this
        });
	  
		JDS.ieExplorer.openWin(cfg);
    }
    this.handler = this.createWindow;
    this.url;
    this.params;
    this.init();

}
Ext.extend(Ext.app.DefaultModule, Ext.app.Module, {});


/**
 * class localModule
 *
 * @param {Object} config
 */
Ext.app.LocalModule = function(config){
    Ext.apply(this, config);
    
    Ext.app.LocalModule.superclass.constructor.call(this);
    this.xtype = 'LocalModule';
    this.init();
    
}
Ext.extend(Ext.app.LocalModule, Ext.app.Module, {});







/**
 * class 
 *
 * @param {Object} UrlModule
 */
Ext.app.UrlModule = function(config){
    Ext.apply(this, config);
 	Ext.app.UrlModule.superclass.constructor.call(this);
   if (this.params){
   		var str = typeof(this.params)=='string' ? this.params+'&Extuuid='+Ext.id() : Ext.urlEncode(this.params)+'&Extuuid='+Ext.id();
		this.url = this.url.indexOf('?') > -1 ? this.url + '&' + str : this.url + '?' + str
	}else{
		this.params={}
	}

    
	this.createWindow = function(){
		
		

		var cfg = this.winCfg;		
		cfg = Ext.apply(cfg, {
			title:this.icon? '<table><tr><td><img style="width:16px;height:16px" src="' + this.icon + '"></td><td>' + this.text+'</td></tr></table>':this.text,
			module: this,
			width:this.params.width?this.params.width:cfg.width,
			height:this.params.height?this.params.height:(cfg.height<Ext.getBody().getHeight()-50?cfg.height:Ext.getBody().getHeight()-50),
			y:0
		});
		
		var p = createIframePanel({
			url: this.url,
			title: '',
			icon: this.icon
		});
		//alert(cfg.height+" "+this.params.height);
		var desktop = getCurDesktop();
		var window = desktop.createWindow(cfg);
		window.add(p);
		window.show();
	}
    this.xtype = 'UrlModule';
    this.init();
    
}
Ext.extend(Ext.app.UrlModule, Ext.app.Module, {});




/**
 *
 * class SystemModule
 * @param {Object} config
 */
Ext.app.SystemModule = function(config){
    Ext.apply(this, config);
    Ext.app.SystemModule.superclass.constructor.call(this);
    this.xtype = 'SystemModule';

	this.parentText='';
    this.fullTitle=Ext.util.Format.ellipsis(this.url,60)+"("+this.text+")";
    
    
    this.createWindow = function(){
     
    var uuid = this.uuid;
    	var model = JDSDesk.openerModules.find(function(o){
    		return o.uuid==uuid;
    	});
    	if(model != null){
    		var manager = JDSDesk.getDesktop().getManager();
    		var win  = manager.getBy(function(win){
    			if(win.module){
    				return win.module.uuid==uuid;
    			}
    		});
    	//	alert(win[0]);
    		if(win[0]!=undefined){
    		win[0].close();
    		}
    		
    		
    	//	JDSDesk.getDesktop().markActive(win[0]);
    	//	return;
    		//var win  = desktop.getWindow(model.id);
    		//win.show();
    	}
	
	//	window.status="javaEval#alert('11111')"
		if (this.url.indexOf("demoInsert.action?")>-1){
			var parms=Ext.urlDecode(this.url.substring(this.url.indexOf('?')+1,this.url.length));
			newProcess(parms.processDefId);
			return;
		}else if(this.url.indexOf("newPublication.action?")>-1){
			var parms=this.url.substring(this.url.indexOf('?')+1,this.url.length);
			newParamsProcess(parms);
			return;
		}
		 config.winCfg  = Ext.apply(this.winCfg,config.winCfg);
  	      config.winCfg.width = Ext.getBody().getWidth();
		 config.winCfg.height = Ext.getBody().getHeight()-30;
		 config.winCfg.x = 0;
		 config.winCfg.y = 0;
		
		if(this.url.indexOf("http://")>-1)	{
		
		this.openByIframe(config);
			//this.getSSOUrl(config);		
			return;
		}
		
		if (this.url.indexOf("open=newwindow")>-1)
		{
			if( this.url.indexOf("http://") < 0)
			
			{
				window.open(this.url);
				return;
			}
		}	
      var iconCls = this.iconCls.indexOf("-shortcut") == -1 ? this.iconCls : this.iconCls.replace("-shortcut", "");
	        if (this.icon) {
	            iconCls = null;
	        }
	        var cfg  = Ext.apply(this.winCfg,config.winCfg);
  	        cfg = Ext.applyIf(cfg, {
	            iconCls: iconCls,
	            title: this.title?this.title:this.text,
				width:850,
				height:700,
	            module: this
	        });
			

	      this.win = this.openWin(cfg);
    }

	
	
	this.markActive=function(){

	 }
    this.getSSOUrl = function(cfg){
    
	    try{
	    function evalJs(returnHtml)
	    {
		   	Ext.apply(this,Ext.decode(returnHtml));
		//   	this.url=returnHtml.replace(/[\r\n]/g, "");
			ssoid = this.ssoid; 
			if(this.ssoState !='' && this.ssoState =='1') //需要单点登录
			{
				if(this.url.indexOf(this.riseServer)==0)
				{
				   sendNSCommand("ognl","SSOLogin('"+this.moduleUrl+"','"+this.id+"')");
				}else if(this.ssourl.indexOf("http://218.26.4.116:3000")>-1) //wl
				{
					var fag = "'"+this.ssourl+"','"+this.moduleUrl+"','"+this.script+"','SSOFromUrl'";
				//	alert("wl==="+fag);
				   	sendNSCommand("ognl","WLLogin("+fag+")");
				}else if(this.ssourl.indexOf("http://218.26.4.116/htbp/")>-1) //ht
				{
					var fag = "'"+this.ssourl+"','"+this.moduleUrl+"','"+this.script+"','SSOFromUrl'";
				//	alert("ht==="+fag);
				   	sendNSCommand("ognl","HTLogin("+fag+")"); 
				}else if(this.ssourl.indexOf("http://218.26.4.116:8080")>-1) //pj				
				{
					var fag = "'"+this.ssourl+"','"+this.moduleUrl+"','"+this.script+"','SSOFromUrl'";
				//	alert("pj==="+fag);
				   	sendNSCommand("ognl","PJLogin("+fag+")");
				} else
				{
					var fag = "'"+this.ssourl+"','"+this.errorurl+"','SSOFromUrl'";
				   	sendNSCommand("ognl","WCMLogin("+fag+")");
				}
			}else
			{
				if (this.url.indexOf("open=newwindow")>-1)
				{
					window.open(this.url);
				}else
				{
					this.openByIframe(cfg);
				}
			}
				return;
	    }
		
		}catch( e)
			{
				window.open(this.url);
			}	
		 JDS.ajax.Connection.LoadJsonFromUrl( '/sso.action',evalJs,'moduleId='+this.uuid+'&personId='+personid,this);	
    }
    
    
  
    
    this.handler = this.createWindow;
    this.url;
    this.params;
    this.init();
}

Ext.extend(Ext.app.SystemModule, Ext.app.Module, {});

  SSOFromUrl = function(){  //登录后败后，弹出注册窗口
	SSoManager.getSsoPanel(ssoid);	
    }

/**
 *
 * class UserDefModule
 * this.module is by user creat
 * @param {Object} config
 */
Ext.app.UserDefModule = function(config){
    Ext.apply(this, config);
    Ext.app.UserDefModule.superclass.constructor.call(this);
    this.xtype = 'UserDefModule';
    this.init();
	
    
	this.fullTitle=Ext.util.Format.ellipsis(this.url,60)+"("+this.text+")";

    this.createWindow = function(){
        var iconCls = this.iconCls.indexOf("-shortcut") == -1 ? this.iconCls : this.iconCls.replace("-shortcut", "");
        if (this.icon) {
            iconCls = null;
        }
        cfg = this.winCfg;
        cfg = Ext.apply(cfg, {
            iconCls: iconCls,
			width:950,
			height:700,
            title: this.text,
			moduleId:this.id,
            module: this
        });
        win = this.openWin(cfg);
    }
	
    
	this.openByIframe=function (cfg){	
	
	if (this.params){
   		var str = typeof(this.params)=='string' ? this.params+'&Extuuid='+Ext.id() : Ext.urlEncode(this.params)+'&Extuuid='+Ext.id();
		this.url = this.url.indexOf('?') > -1 ? this.url + '&' + str : this.url + '?' + str
	}
		if (JDS.ieExplorer.allOpenWin.getCount()>0 ){
			var win;
			JDS.ieExplorer.allOpenWin.eachKey(function(key){
				if (JDSDesk.getDesktop().getWindow(key)){
                     win=JDSDesk.getDesktop().getWindow(key);
					 return;
				}
			});
			if (win){
				win.show();
					 Ext.apply(cfg,{win:win});
			         JDS.ieExplorer.addPanel(cfg); 
			}else{
				JDS.ieExplorer.openWin(cfg);
			}
	
		}else{
		 	JDS.ieExplorer.openWin(cfg);
		}
	}
	
    this.handler = this.createWindow;
}


Ext.extend(Ext.app.UserDefModule, Ext.app.Module, {});



