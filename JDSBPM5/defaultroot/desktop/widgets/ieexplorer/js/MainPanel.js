
JDS.IeExplorer= function(){

	
	this.allOpenWin=new Ext.util.MixedCollection(); 
	
	this.html=new String(Ext.fly('ienorth_area').dom.innerHTML);
	this.extId;
	
	IeExplorer.Actions = new IeExplorer.Actions();
	
	
	this.init=function(){
		IeExplorer.toolbar.init(Ext.fly(this.extId?this.extId:'ienorth_area'));
		this.URIbar=IeExplorer.toolbar.URIbar;
	}
	
	
	winClose=function(){
//         JDS.alert(this.tab);
//		if (this.tab.items.getCount()==1){
//			this.tab.win.close();
//		}
	}
	
	
	this.addPanel=function(cfg){
		
		this.URIbar.setValue(cfg.module.fullTitle);
		IeExplorer.toolbar.currPath=cfg.module.id;
		
			if (cfg.win.tabPanel.findById(cfg.moduleId+"-tabwin")){
		      }else{
			  	cfg.win.tabPanel.add({
				closable : true,
				tab:cfg.win.tabPanel,
				listeners: {beforedestroy: winClose},
				id:cfg.moduleId+"-tabwin",
				iconCls:"x-icon-ie",
				title:cfg.module.fullTitle,
				html: '<div style="width:100%;height:100%" >'
						+'<iframe id='+this.extId+cfg.module.id+'write isload="false"  src="/desktop/widgets/mainpanel/write.html" style="width:100%;height:100%"></iframe>'
						+'<iframe id='+this.extId+cfg.module.id+' isload="false" onload="writeLoad(\''+this.extId+cfg.module.id+'\',\''+cfg.module.gbkUrl+'\')"  src="'+cfg.module.gbkUrl+'" style="width:100%;height:100%;display:none"></iframe>'
			});
		}
		cfg.win.tabPanel.activate(cfg.moduleId+"-tabwin");	
		Ext.get(this.extId+cfg.module.id).dom.src=cfg.module.url;
	};

	
	this.openWin=function(cfg){
			this.extId=Ext.id();
			Ext.fly('ieexplorer').insertHtml('beforeEnd',"<div id="+this.extId+">"+this.html+"</div>");
			this.init();
			IeExplorer.toolbar.currPath=cfg.module.id;
		    this.URIbar.setValue(cfg.module.url);
			var iframId=Ext.id();
			
		    var tabPanel = new Ext.TabPanel({
		 		region: 'center',
		 	    maskDisabled:false,
		 		activeTab: 0,
				margins:'0 5 5 0',
		        resizeTabs:true,
		        tabWidth:200,
		        minTabWidth: 120,
		        enableTabScroll: true,
		 		defaults: {
		 			autoScroll: true
		 		},
			 items: [
					{
						id:cfg.moduleId+"-tabwin",
						 closable : true,
						tab:this,
						listeners: {beforedestroy: winClose},
						iconCls:"x-icon-ie",
						title:cfg.module.fullTitle,
		               html: '<div style="width:100%;height:100%" >'
						+'<iframe id='+this.extId+cfg.module.id+'write isload="false"  src="/desktop/widgets/mainpanel/write.html" style="width:100%;height:100%"></iframe>'
						+'<iframe id='+this.extId+cfg.module.id+' isload="false" onload="writeLoad(\''+this.extId+cfg.module.id+'\',\''+cfg.module.gbkUrl+'\')"  src="'+cfg.module.gbkUrl+'" style="width:100%;height:100%;display:none"></iframe>'
						//html: '<div style="width:100%;height:100%" >'+appletStr+'</iframe>'
					}
					]

			 	});
			Ext.apply(cfg, {
				closable: true,
				plain: true,
				id:this.extId+'-win',
				tabPanel:tabPanel,
				layout: 'border',
				title:cfg.module.fullTitle,
				items: [new Ext.BoxComponent({ 
			            region : 'north',
						style:"background-color:white",
			            el : this.extId?this.extId:'ienorth_area',
			            height : 40
			        }),tabPanel]
			})
		var win = getCurDesktop().createWindow(cfg);
		tabPanel.parentWin=win;
		this.allOpenWin.add(this.extId+'-win',win);
		win.show();
		
	}
			
		return  this;

}



function writeLoad(iframId,src){
		Ext.get(iframId+'write').dom.style.display="none";
		Ext.get(iframId).dom.style.display="";

};


