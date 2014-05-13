var extVer = new Number(Ext.version);
var jameMainwin;
function getMainWin() {
  return jameMainwin;
}


function createMainJameWin() {
	if (!jameMainwin){
		jameMainwin = new Jame.ui.MainWin(personid, currUserName);
	}
  
  jameMainwin.show();
}

var  ExtJame = Ext.extend(Ext.app.LocalModule, {
		moduleId : 'jame',
		id:'jame',
			init: function(){
				this.launcher = {
					handler: this.createWindow,
					icon: '/desktop/widgets/jame/images/jame/im48x48new.png',
					scope: this,
					//shortcutIconCls: 'jame-shortcut',
					text: '即时沟通',
					tooltip: '<b>JDS</b><br />即时沟通'
				}
				Ext.apply(this,this.launcher); 
			},
	    createWindow : function(){
			 if (ShortcutsMove){return;}
	         if(!Ext.WindowMgr.get('ClientDialog')){	
			      createMainJameWin();

					//var dio=new ExtJame.ui.ClientDialog(ExtJame.hud,ExtJame.ui.UiConfig.ClientLayout).init();
				}
	    }
	});
	
	
      function showClient(){
			if(!Ext.WindowMgr.get('ClientDialog')){	
				var dio=new ExtJame.ui.ClientDialog(ExtJame.hud,ExtJame.ui.UiConfig.ClientLayout).init();
			}
}