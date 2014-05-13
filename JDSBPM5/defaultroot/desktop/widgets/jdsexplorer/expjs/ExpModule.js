

var ExpModule = Ext.extend(Ext.app.LocalModule, {
       moduleId : 'testExp',
		id:'testExp',
	    init : function(){
		this.launcher = {
					handler: this.createWindow,
					icon: '/desktop/widgets/jdsexplorer/resouces/images/classy_icons.png',
					scope: this,
					text: '资源管理器',
					tooltip: '<b>JDS</b><br />资源管理器'
				}
				Ext.apply(this,this.launcher); 
	    },
	createWindow: function(){
	  var cfg = this.winCfg;

        cfg = Ext.apply(cfg, {
		   width:950,
			height:700,
            title: this.text,
			moduleId:this.id,
            module: this
        });

      JDS.exp.openExp(cfg);
	}
})

