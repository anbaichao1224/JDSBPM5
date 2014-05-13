
JDSPreferences = Ext.extend(Ext.app.LocalModule, {
	moduleType : 'system',
	moduleId : 'JDSPreferences',
	actions : null,
	cards : [
		'pref-win-card-1', // navigation
		'pref-win-card-2', // quickstart
		'pref-win-card-3', // color and appearance
		'pref-win-card-4', // wallpaper
		'pref-win-card-5', // autorun
		'pref-win-card-6'  // shortcuts
	],
	contentPanel : null,
	cardHistory : [
		'pref-win-card-1' // default
	],
	layout: null,
	window : null,
	
    init : function(){
        this.launcher = {
        	icon: '/desktop/widgets/mainpanel/images/control.png',
            handler : this.createWindow,
			//shortcutIconCls: 'pref-icon',
            scope: this,
            text: '控制面板',
            tooltip: '<b>控制面板</b><br />自定义桌面'
        }
		Ext.apply(this,this.launcher);
    },

    createWindow : function(){
		if (ShortcutsMove){
			return;
		}
    	var desktop = getCurDesktop();
        this.window = desktop.getWindow(this.moduleId);
        if(!this.window){
        	var winWidth = 610;
			var winHeight = 460;
			this.contentPanel = new Ext.Panel({
				activeItem: 0,
                border: false,
				id: 'pref-win-content',
				items: [
                	new JDSPreferences.NavPanel({owner: this, id: 'pref-win-card-1'}),
                	new JDSPreferences.Shortcuts({owner: this, id: 'pref-win-card-6'}),
                	new JDSPreferences.AutoRun({owner: this, id: 'pref-win-card-5'}),
                	new JDSPreferences.QuickStart({owner: this, id: 'pref-win-card-2'}),
                	new JDSPreferences.Appearance({owner: this, id: 'pref-win-card-3'}),
                	new JDSPreferences.Background({owner: this, id: 'pref-win-card-4'})
                ],
				layout: 'card',
				tbar: [{
					disabled: true,
                	handler: this.navHandler.createDelegate(this, [-1]),
                	id: 'back',
                	scope: this,
                	text: '返回'
                },{
                	disabled: true,
                	handler: this.navHandler.createDelegate(this, [1]),
                	id: 'next',
                	scope: this,
                	text: '下一个'
                }]
			});
			
            this.window = desktop.createWindow({
            	animCollapse: false,
                constrainHeader: true,
				module:this,
                id: this.moduleId,
                height: winHeight,
                iconCls: 'pref-icon',
                items: this.contentPanel,
                layout: 'fit',
                shim: false,
                taskbuttonTooltip: '<b>控制面板</b><br />自定义桌面',
                title: '控制面板',
                width: winWidth
            });
            
			this.layout = this.contentPanel.getLayout();
        }
        
        this.window.show();
    },
    
    handleButtonState : function(){
    	var cards = this.cardHistory, activeId = this.layout.activeItem.id,
    		items = this.contentPanel.getTopToolbar().items, back = items.get(0), next = items.get(1);
    	
    	for(var i = 0, len = cards.length; i < len; i++){
    		if(cards[i] === activeId){
    			if(i <= 0){
    				back.disable();
    				next.enable();
    			}else if(i >= (len-1)){
    				back.enable();
    				next.disable();
    			}else{
    				back.enable();
    				next.enable();
    			}
    			break;
    		}
    	}
    },
    
    navHandler : function(index){
    	var cards = this.cardHistory,
    		activeId = this.layout.activeItem.id,
    		nextId;
    	
    	for(var i = 0, len = cards.length; i < len; i++){
    		if(cards[i] === activeId){
    			nextId = cards[i+index];
    			break;
    		}
    	}
    	
    	this.layout.setActiveItem(nextId);
    	this.handleButtonState();
    },
    
    save : function(params){    	
    	var desktop = getCurDesktop();
	//	this.buttons[0].disable();
		desktop.saveDesk();
		
//    	var notifyWin = desktop.showNotification({
//			html: '正在保存'
//			, title: '请等候'
//		});
//	    var callback = params.callback || null;
//		var callbackScope = params.callbackScope || this;
//		var task=params.task||null;
//
//		params.moduleId = this.moduleId;
//	
//	    Ext.Ajax.request({
//			url: 'saveStyles.action?personid='+personid,
//				params: params,
//			success: function(o){
//				if(o && o.responseText && Ext.decode(o.responseText).success){
//					saveComplete(' 完成', '保存成功');
//				}else{
//					saveComplete('失败', '无法联系到服务器保存失败');
//				}
//			},
//			failure: function(){
//				saveComplete('失败', '无法联系到服务器保存失败');
//			},
//			scope: this
//		});
//		
//		function saveComplete(title, msg){
//			notifyWin.setIconClass('x-icon-done');
//			notifyWin.setTitle(title);
//			notifyWin.setMessage(msg);
//			desktop.hideNotification(notifyWin);
//			if(callback){
//				callback.call(callbackScope);
//			}
//		}
	},
    
    viewCard : function(card){
		this.layout.setActiveItem(card);
	    if(this.cardHistory.length > 1){
	    	this.cardHistory.pop();
	    }
	    this.cardHistory.push(card);
	    this.handleButtonState();
	}
});



JDSPreferences.NavPanel = function(config){
	this.owner = config.owner;
	
	JDSPreferences.NavPanel.superclass.constructor.call(this, {
		autoScroll: true,
		bodyStyle: 'padding:15px',
		border: false,
		html: '<ul id="pref-nav-panel"> \
				<li> \
					<img src="'+Ext.BLANK_IMAGE_URL+'" class="icon-pref-autorun"/> \
					<a id="viewShortcuts" href="#">桌面图标</a><br /> \
					<span>选择快捷方式放置到桌面</span> \
				</li> \
				<li> \
					<img src="'+Ext.BLANK_IMAGE_URL+'" class="icon-pref-autorun"/> \
					<a id="viewAutoRun" href="#">登录自运行</a><br /> \
					<span>登录后自动运行</span> \
				</li> \
				<li> \
					<img src="'+Ext.BLANK_IMAGE_URL+'" class="icon-pref-quickstart"/> \
					<a id="viewQuickstart" href="#">快捷工具栏</a><br /> \
					<span>将快捷方式放入快捷工具栏</span> \
				</li> \
				<li> \
					<img src="'+Ext.BLANK_IMAGE_URL+'" class="icon-pref-appearance"/> \
					<a id="viewAppearance" href="#">更改主题</a><br /> \
					<span>更改主题</span> \
				</li> \
				<li> \
					<img src="'+Ext.BLANK_IMAGE_URL+'" class="icon-pref-wallpaper"/> \
					<a id="viewWallpapers" href="#">更换桌面背景</a><br /> \
					<span>更换桌面背景</span> \
				</li> \
			</ul>',
		id: config.id
	});
	
	this.actions = {
		'viewShortcuts' : function(owner){
			owner.viewCard('pref-win-card-6');
		},
		
		'viewAutoRun' : function(owner){
			owner.viewCard('pref-win-card-5');
		},
		
		'viewQuickstart' : function(owner){
	   		owner.viewCard('pref-win-card-2');
	   	},
	   	
	   	'viewAppearance' : function(owner){
	   		owner.viewCard('pref-win-card-3');
	   	},
	   	
	   	'viewWallpapers' : function(owner){
	   		owner.viewCard('pref-win-card-4');
	   	}
	};
};

Ext.extend(JDSPreferences.NavPanel, Ext.Panel, {
	afterRender : function(){
		this.body.on({
			'mousedown': {
				fn: this.doAction,
				scope: this,
				delegate: 'a'
			},
			'click': {
				fn: Ext.emptyFn,
				scope: null,
				delegate: 'a',
				preventDefault: true
			}
		});
		
		JDSPreferences.NavPanel.superclass.afterRender.call(this); // do sizing calcs last
	},
	
	doAction : function(e, t){
    	e.stopEvent();
    	this.actions[t.id](this.owner);  // pass owner for scope
    }
});



JDSPreferences.AutoRun = function(config){
	this.owner = config.owner;
		this.app = JDSDesk;
	var ms = this.app.startMenuCfg,
		ids = getCurDesktop().config.launchers.autorun,
		nodes = expandNodes(ms, ids);
				
	JDSPreferences.AutoRun.superclass.constructor.call(this, {
		autoScroll: true,
		bodyStyle: 'padding:10px',
		border: false,
		buttons: [{
			handler: onSave,
			scope: this,
			text:  '保存'
		},{
			handler: onClose,
			scope: this,
			text: '关闭'
		}],
		cls: 'pref-card pref-check-tree',
		id: config.id,
		lines: false,
		listeners: {
			'checkchange': {
				fn: onCheckChange,
				scope: this
			}
		},
		loader: new Ext.tree.TreeLoader(),
		rootVisible: false,
		root: new Ext.tree.AsyncTreeNode({
			text: '登录后运行',
			children: nodes
		}),
		title: '登录后运行'
	});
	
	new Ext.tree.TreeSorter(this, {dir: "asc"});
			
	function expandNodes(ms, ids){
		var nodes = [];
		for(var i = 0, len = ms.length; i < len; i++){
			var o = ms[i].launcher ? ms[i].launcher : ms[i];
			if(o.menu){
				 nodes.push({
					leaf: false,
					text: o.text || o.menuText,
					children: expandNodes(o.menu, ids)
				}); 
			}else if(!o.menu){
				nodes.push({
		           	checked: isChecked(ms[i].moduleId? ms[i].moduleId : ms[i].id, ids) ? true : false,
		           	iconCls: ms[i].launcher? ms[i].launcher.iconCls:ms[i].iconCls,
		           	id: ms[i].moduleId? ms[i].moduleId : ms[i].id,
		           	leaf: true,
		           	selected: true,
		           	text: o.text || o.menuText
				});
			}
		}
		
		return nodes;
	}
	
	function isChecked(id, ids){
		for(var i = 0, len = ids.length; i < len; i++){
			if(id == ids[i]){
				return true;
			}
		}
	}

	function onCheckChange(node, checked){
		if(node.leaf && node.id){
    		if(checked){
				getCurDesktop().addAutoRun(node.id, true);
    		}else{
				getCurDesktop().removeAutoRun(node.id, true);
    		}
    	}
    	node.ownerTree.selModel.select(node);
    }
    
    function onClose(){
		this.owner.window.close();
	}
	
    function onSave(){
    	this.buttons[0].disable();
		
    	this.owner.save({
    	
    		callback: function(){
    			this.buttons[0].enable();
    		}
    		, callbackScope: this
    		, task: 'save'
    		, what: 'autorun'
    		, ids: Ext.encode(getCurDesktop().config.launchers.autorun)
		
    	});
    }
};

Ext.extend(JDSPreferences.AutoRun, Ext.tree.TreePanel);



JDSPreferences.Shortcuts = function(config){
	this.owner = config.owner;
	this.app = JDSDesk;
	var ms = this.app.startMenuCfg,
		ids = getCurDesktop().config.launchers.shortcut,
		nodes = expandNodes(ms, ids);
	
	JDSPreferences.Shortcuts.superclass.constructor.call(this, {
		autoScroll: true,
		bodyStyle: 'padding:10px',
		border: false,
		buttons: [{
			handler: onSave,
			scope: this,
			text:  '保存'
		},{
			handler: onClose,
			scope: this,
			text: '关闭'
		}],
		cls: 'pref-card pref-check-tree',
		id: config.id,
		lines: false,
		listeners: {
			'checkchange': {
				fn: onCheckChange,
				scope: this
			}
		},
		loader: new Ext.tree.TreeLoader(),
		rootVisible: false,
		root: new Ext.tree.AsyncTreeNode({
			text: '桌面图标',
			children: nodes
		}),
		title: '桌面图标'
	});
	
	new Ext.tree.TreeSorter(this, {dir: "asc"});
			
	function expandNodes(ms, ids){
		var nodes = [];
	
		for(var i = 0, len = ms.length; i < len; i++){
			var o = ms[i].launcher ? ms[i].launcher : ms[i];
			if(o.menu ){
				 nodes.push({
					leaf: false,
					selected: true,
					//checked: isChecked(ms[i].moduleId? ms[i].moduleId : ms[i].id, ids) ? true : false,
		           	iconCls: ms[i].launcher? ms[i].launcher.iconCls:ms[i].iconCls,
		           	id: ms[i].moduleId? ms[i].moduleId : ms[i].id,
					text: o.text || o.menuText,
					children: expandNodes(o.menu, ids)
				}); 
			}else if(!o.menu){
				nodes.push({
		           	checked: isChecked(ms[i].moduleId? ms[i].moduleId : ms[i].id, ids) ? true : false,
		           	iconCls: ms[i].launcher? ms[i].launcher.iconCls:ms[i].iconCls,
		           	id: ms[i].moduleId? ms[i].moduleId : ms[i].id,
		           	leaf: true,
		           	selected: true,
					
		           	text: o.text || o.menuText
				});
			}
		}
		
		return nodes;
	}
	
	function isChecked(id, ids){
		for(var i = 0, len = ids.length; i < len; i++){
			if(id == ids[i]){
				return true;
			}
		}
	}

	function onCheckChange(node, checked){
		
		if(node.leaf && node.id){
    		if(checked){
				
				getCurDesktop().addShortcut(node.id, true);
    		}else{
				getCurDesktop().removeShortcut(node.id, true);
    		}
    	}
    	node.ownerTree.selModel.select(node);
    }
    
    function onClose(){
		
		this.owner.window.close();
	}
	
    function onSave(){
    	this.buttons[0].disable();
    	this.owner.save({
    		callback: function(){
    			this.buttons[0].enable();
    		}
    		, callbackScope: this
    		, task: 'save'
    		, what: 'shortcut'
    		, ids: Ext.encode(getCurDesktop().config.launchers.shortcut)
    	});
    }
};

Ext.extend(JDSPreferences.Shortcuts, Ext.tree.TreePanel);



JDSPreferences.QuickStart = function(config){
	this.owner = config.owner;
		this.app = JDSDesk;
	var ms = this.app.startMenuCfg,
		ids = getCurDesktop().config.launchers.quickstart,
		nodes = expandNodes(ms, ids);
				
    JDSPreferences.QuickStart.superclass.constructor.call(this, {
    	autoScroll: true,
		bodyStyle: 'padding:10px',
		border: false,
		buttons: [{
			handler: onSave,
			scope: this,
			text:  '保存'
		},{
			handler: onClose,
			scope: this,
			text: '关闭'
		}],
		cls: 'pref-card pref-check-tree',
		id: config.id,
		lines: false,
		listeners: {
			'checkchange': {
				fn: onCheckChange,
				scope: this
			}
		},
		loader: new Ext.tree.TreeLoader(),
		rootVisible: false,
		root: new Ext.tree.AsyncTreeNode({
			text: 'Quick Start Apps',
			children: nodes
		}),
		title: '快捷工具栏设定'
    });
    
    new Ext.tree.TreeSorter(this, {dir: "asc"});
			
	function expandNodes(ms, ids){
		var nodes = [];
		
		for(var i = 0, len = ms.length; i < len; i++){
			var o = ms[i].launcher ? ms[i].launcher : ms[i];
				if(o.menu ){
				
				 nodes.push({
					leaf: false,
					text: o.text || o.menuText,
					children: expandNodes(o.menu, ids)
				}); 
			}else if(!o.menu){
				nodes.push({
		           	checked: isChecked(ms[i].moduleId? ms[i].moduleId : ms[i].id, ids) ? true : false,
		           	iconCls: ms[i].launcher? ms[i].launcher.iconCls:ms[i].iconCls,
		           	id: ms[i].moduleId? ms[i].moduleId : ms[i].id,
		           	leaf: true,
		           	selected: true,
		           	text: o.text || o.menuText
				});
			}
		}
		
		return nodes;
	}
	
	function isChecked(id, ids){
		for(var i = 0, len = ids.length; i < len; i++){
			if(id == ids[i]){
				return true;
			}
		}
	}
	
	function onCheckChange(node, checked){
		if(node.leaf && node.id){
    		if(checked){
				getCurDesktop().addQuickStartButton(node.id, true);
    		}else{
				getCurDesktop().removeQuickStartButton(node.id, true);
    		}
    	}
    	node.ownerTree.selModel.select(node);
    }
    
    function onClose(){
		this.owner.window.close();
	}
	
    function onSave(){
    	this.buttons[0].disable();
    	this.owner.save({
    		callback: function(){
    			this.buttons[0].enable();
    		}
    		, callbackScope: this
    		, task: 'save'
    		, what: 'quickstart'
    		, ids: Ext.encode(getCurDesktop().config.launchers.quickstart)
    	});
    }
};

Ext.extend(JDSPreferences.QuickStart, Ext.tree.TreePanel);



JDSPreferences.Appearance = function(config){
	this.owner = config.owner;
	this.app = this.owner.app;
	
	var desktop =  getCurDesktop();
	
	var store = new Ext.data.JsonStore({
		baseParams: {
			moduleId: this.owner.moduleId,

			task: 'load',
			what: 'themes'
		},
		fields: ['id', 'name', 'pathtothumbnail', 'pathtofile'],
		id: 'id',
		root: 'images',
		url: context+'themes.action'
	});
	
	this.store = store;
	
	store.on('load', function(store, records){
		if(records){
			defaults.setTitle('桌面主题 (' + records.length + ')');
			
			var id = getCurDesktop().config.styles.theme.id;
			if(id){
				view.select('theme-'+id);
			}
		}				
	}, this);
	
	var tpl = new Ext.XTemplate(
		'<tpl for=".">',
			'<div class="pref-view-thumb-wrap" id="theme-{id}">',
				'<div class="pref-view-thumb"><img src="{pathtothumbnail}" title="{name}" /></div>',
			'<span>{shortName}</span></div>',
		'</tpl>',
		'<div class="x-clear"></div>'
	);
	

	var view = new Ext.DataView({
		autoHeight:true,
		cls: 'pref-thumnail-view',
		emptyText: '没有可供替换的主题',
		itemSelector:'div.pref-view-thumb-wrap',
		loadingText: '正在读取。。。',
		singleSelect: true,
		overClass:'x-view-over',
		prepareData: function(data){
			data.shortName = Ext.util.Format.ellipsis(data.name, 17);
			return data;
		},
		store: store,
		tpl: tpl
	});
	view.on('selectionchange', onSelectionChange, this);
	
	var defaults = new Ext.Panel({
		animCollapse: false,
		baseCls:'collapse-group',
		border: false,
		cls: 'pref-thumbnail-viewer',
		collapsible: true,
		hideCollapseTool: true,
		id: 'pref-theme-view',
		items: view,
		
		titleCollapse: true
	});
	
	var themes = new Ext.Panel({
		autoScroll: true,
		bodyStyle: 'padding:10px',
		border: true,
		cls: 'pref-card-subpanel',
		id: 'themes',
		items: defaults,
		margins: '10 15 0 15',
		region: 'center'
	});
	
	var checkBox = new Ext.form.Checkbox({
		checked:  getCurDesktop().config.styles.transparency ? true : false,
		x: 250,
		y: 15
	});
	checkBox.on('check', toggleTransparency, this);
	
	var formPanel = new Ext.FormPanel({
		border: false,
		height: 70,
		items: [
		    checkBox,
			{x: 15, y: 15, xtype: 'label', text: '工具栏透明处理'}
			
		],
		layout: 'absolute',
		 split: false,
		region: 'south'
	});
	
	JDSPreferences.Appearance.superclass.constructor.call(this, {
		border: false,
		buttons: [{
			handler: onSave,
			scope: this,
			text:  '保存'
			},{
			handler: onClose,
			scope: this,
			text: '关闭'
		}],
		cls: 'pref-card',
		id: config.id,
		items: [
			themes,
			formPanel
		],
		layout: 'border'
	});
	
	function onClose(){
		this.owner.window.close();
	}
	
	function onSave(){
		var c = getCurDesktop().config.styles;
		
		this.buttons[0].disable();
    	this.owner.save({
    		callback: function(){
    			this.buttons[0].enable();
    		}
    		, callbackScope: this
    		, task: 'save'
    		, what: 'appearance'
    		, backgroundcolor: c.backgroundcolor
    		, fontcolor: c.fontcolor
    		, theme: c.theme.id
    		, transparency: c.transparency
    		, wallpaper: c.wallpaper.id
    		, wallpaperposition: c.wallpaperposition
    	});
	}
	
	function onSelectionChange(view, sel){
		if(sel.length > 0){
			var cId = getCurDesktop().config.styles.theme.id,
				r = view.getRecord(sel[0]),
				d = r.data;
			
			if(parseInt(cId) !== parseInt(r.id)){
				if(r && r.id && d.name && d.pathtofile){
					desktop.setTheme({
						id: r.id,
						name: d.name,
						pathtofile: d.pathtofile
					});
				}
			}
		}
	}
	
	function toggleTransparency(field, checked){
		
		desktop.setTransparency(checked);
	}
};

Ext.extend(JDSPreferences.Appearance, Ext.Panel, {
	afterRender : function(){
		JDSPreferences.Appearance.superclass.afterRender.call(this);
		
		this.on('show', this.loadStore, this, {single: true});
	},
	
	loadStore : function(){
		this.store.load();
	}
});



JDSPreferences.Background = function(config){
	this.owner = config.owner;
	this.app = this.owner.app;
	
	var desktop = getCurDesktop();
	
	var store = new Ext.data.JsonStore({
		baseParams: {
			moduleId: this.owner.moduleId,
			task: 'load',
			what: 'wallpapers'
		},
		fields: ['id', 'name', 'pathtothumbnail', 'pathtofile'],
		id: 'id',
		root: 'images',
		url: context+'wallpapers.action'
	});
	
	this.store = store;
	
	store.on('load', function(store, records){
		if(records){
			defaults.setTitle('默认壁纸(' + records.length + ')');
			
			var id = getCurDesktop().config.styles.wallpaper.id;
			
			if(id){
				view.select('wallpaper-'+id);
			}
		}				
	}, this);

	var tpl = new Ext.XTemplate(
		'<tpl for=".">',
			'<div class="pref-view-thumb-wrap" id="wallpaper-{id}">',
				'<div class="pref-view-thumb"><img src="{pathtothumbnail}" title="{name}" /></div>',
			'<span>{shortName}</span></div>',
		'</tpl>',
		'<div class="x-clear"></div>'
	);

	var view = new Ext.DataView({
		autoHeight:true,
		cls: 'pref-thumnail-view',
		emptyText: '没有可供选择的壁纸',
		itemSelector:'div.pref-view-thumb-wrap',
		loadingText: '正在读取。。。',
		singleSelect: true,
		overClass:'x-view-over',
		prepareData: function(data){
			data.shortName = Ext.util.Format.ellipsis(data.name, 17);
			return data;
		},
		store: store,
		tpl: tpl
	});
	view.on('selectionchange', onSelectionChange, this);
	
	var defaults = new Ext.Panel({
		animCollapse: false,
		baseCls:'collapse-group',
		border: false,
		cls: 'pref-thumbnail-viewer',
		collapsible: true,
		hideCollapseTool: true,
		id: 'pref-wallpaper-view',
		items: view,
	
		titleCollapse: true
	});
	
	var wallpapers = new Ext.Panel({
		autoScroll: true,
		bodyStyle: 'padding:10px',
		border: true,
		cls: 'pref-card-subpanel',
		id: 'wallpapers',
		items: defaults,
		margins: '10 15 0 15',
		region: 'center'
	});
	
	var wpp = getCurDesktop().config.styles.wallpaperposition;
	var tileRadio = createRadio('tile', wpp == 'tile' ? true : false, 90, 40);
	var centerRadio = createRadio('center', wpp == 'center' ? true : false, 200, 40);
	
	var position = new Ext.FormPanel({
		border: false,
		height: 140,
		id: 'position',
		items: [{
				border: false,
				items: {border: false, html:'拉伸'},
				x: 15,
				y: 15
			},{
				border: false,
				items: {border: false, html:'平铺'},
				x: 125,
				y: 15
			},{
				border: false,
				items: {border: false, html: '<img class="bg-pos-tile" src="'+Ext.BLANK_IMAGE_URL+'" width="64" height="44" border="0" alt="" />'},
				x: 15,
				y: 40
			},
				tileRadio,
			{
				border: false,
				items: {border: false, html: '<img class="bg-pos-center" src="'+Ext.BLANK_IMAGE_URL+'" width="64" height="44" border="0" alt="" />'},
				x: 125,
				y: 40
			},
				centerRadio,
			{
				border: false,
				items: {border: false, html:'选择背景颜色'},
				x: 245,
				y: 15
			},{
				border: false,
				items: new Ext.ColorPalette({
					listeners: {
						'select': {
							fn: onColorSelect
							, scope: this
						}
					}
				}),
				/* items: new Ext.Button({
					handler: onChangeBgColor,
					scope: this,
					text: 'Background color'
				}), */
				x: 245,
				y: 40
			},{
				border: false,
				items: {border: false, html:'选择字体颜色'},
				x: 425,
				y: 15
			},{
				border: false,
				items: new Ext.ColorPalette({
					listeners: {
						'select': {
							fn: onFontColorSelect
							, scope: this
						}
					}
				}),
				x: 425,
				y: 40
				
		}],
		layout: 'absolute',
		region: 'south',
		split: false
	});

	JDSPreferences.Background.superclass.constructor.call(this, {
		border: false,
		buttons: [{
			handler: onSave,
			scope: this,
			text:  '保存'
			},{
			handler: onClose,
			scope: this,
			text: '关闭'
		}],
		cls: 'pref-card',
		id: config.id,
		items: [
			wallpapers,
			position
		],
		layout: 'border'
	
	});
	
	function createRadio(value, checked, x, y){
			
		if(value){
			
			radio = new Ext.form.Radio({
				name: 'position',
				inputValue: value,
				checked: checked,
				x: x,
				y: y
			});
			
			radio.on('check', togglePosition, radio);
			
			return radio;
		}
	}
    
    /* function onChangeBgColor(){
    	var dialog = Ext.getCmp('qo-color-picker');
    	
    	if(!dialog){
	    	dialog = new Ext.ux.ColorDialog({
				title: 'Background Color',
				closable: true,
				height:232,
				id: 'qo-color-picker',
				modal: false,
				shadow: true,
				width: 362,
				x: desktop.getWinX(362),
				y: desktop.getWinY(232)
			});
		}
		
		//dialog.on('pickcolor', onPickColor, this);
    	dialog.show();
    } */
    
	function onClose(){
		this.owner.window.close();
	}
	
	function onColorSelect(p, hex){
		desktop.setBackgroundColor(hex);
	}
	
	function onFontColorSelect(p, hex){
		desktop.setFontColor(hex);
	}
	
	function onSave(){
		var c = getCurDesktop().config.styles;
		
		this.buttons[0].disable();
    	this.owner.save({
    		callback: function(){
    			this.buttons[0].enable();
    		}
    		, callbackScope: this
    		, task: 'save'
    		, what: 'appearance'
    		, backgroundcolor: c.backgroundcolor
    		, fontcolor: c.fontcolor
    		, theme: c.theme.id
    		, transparency: c.transparency
    		, wallpaper: c.wallpaper.id
    		, wallpaperposition: c.wallpaperposition
    	});
	}
	
	function onSelectionChange(view, sel){
		if(sel.length > 0){
			var cId = getCurDesktop().config.styles.wallpaper.id,
				r = view.getRecord(sel[0]),
				d = r.data;
			
			if(parseInt(cId) !== parseInt(r.id)){
				if(r && r.id && d.name && d.pathtofile){
	
					desktop.setWallpaper({
						id: r.id,
						name: d.name,
						pathtofile: context+d.pathtofile
					});
				}
			}
		}
	}
	
	function togglePosition(field, checked){
		if(checked === true){
			
			desktop.setWallpaperPosition(field.inputValue);
			
		}
	}
};

Ext.extend(JDSPreferences.Background, Ext.Panel, {
	afterRender : function(){
		JDSPreferences.Background.superclass.afterRender.call(this);
		
		this.on('show', this.loadStore, this, {single: true});
	},
	
	loadStore : function(){
		this.store.load();
	}
});



/* 
 * Will ensure that the checkchange event is fired on 
 * node double click
 */
Ext.override(Ext.tree.TreeNodeUI, {
	toggleCheck : function(value){		
        var cb = this.checkbox;
        if(cb){
            cb.checked = (value === undefined ? !cb.checked : value);
            this.fireEvent('checkchange', this.node, cb.checked);
        }
    }
});

