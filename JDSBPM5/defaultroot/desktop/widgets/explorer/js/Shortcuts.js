
Ext.namespace("JDS.explorer");

Ext.ux.EShortcuts = function(config){
	var desktopEl = Ext.get(config.renderTo)
	    , path=config.path
		, taskbarEl = config.taskbarEl
		, btnHeight = 74
		, btnWidth = 64
		, btnPadding = 15
		, col = null
		, row = null
		, items = [];
	
	initColRow();

	
	function initColRow(){
		col = {index: 1, x: btnPadding};
		row = {index: 1, y: btnPadding};
	}
	
	function isOverflow(y){
		if(y > (Ext.lib.Dom.getViewWidth() - taskbarEl.getWidth())){
			return true;
		}
		return false;
	}
	


	function newShortcut(config){
		var div = desktopEl.createChild({tag:'div', cls: 'ux-shortcut-item'}),
			btn = new Ext.ux.EShortcutButton(Ext.apply(config, {
			text: Ext.util.Format.ellipsis(config.text, 16)
		}), div);
		return btn;
	}
	this.addShortcut = function(config){
		btn=newShortcut(config);
		this.setXY(btn.container);
		return btn;
	};
	
	
	this.moveShortcut= function(sId,tId){
	   var shortcut = JDSDesk.getModule(sId).shortcut;
	   
	   if (!shortcut){
		 addShortcut(sId,true,JDSDesk.getModule(sId))
	   }
		this.handleUpdate();
	}
	
	this.removeShortcut = function(b){
		
		var d = document.getElementById(b.container.id);
		b.destroy();
		d.parentNode.removeChild(d);
		var s = [];
		for(var i = 0, len = items.length; i < len; i++){
			if(items[i] != b){
				s.push(items[i]);
			}
		}
		items = s;

		this.handleUpdate();
	}
	
	this.handleUpdate = function(){
		initColRow();
		for(var i = 0, len = items.length; i < len; i++){
			this.setXY(items[i].container);
		}
	}
	
	this.setXY = function(item){
		var bottom = row.x + btnHeight,
			overflow = isOverflow(row.x + btnHeight);
		if(overflow && bottom > (btnHeight + btnPadding)){
			col = {
				index: col.index++
				, x: col.x + btnWidth + btnPadding
			};
			row = {
				index: 1
				, y: btnPadding
			};
		}
		
		item.setXY([
			col.x
			, row.y
		]);
		
		row.index++;
		row.y = row.y + btnHeight + btnPadding;
	};
	
	Ext.EventManager.onWindowResize(this.handleUpdate, this, {delay:500});
};

  




/**
 * @class Ext.ux.ShortcutButton
 * @extends Ext.Button
 */
Ext.ux.EShortcutButton = function(config, el){
	
    Ext.ux.EShortcutButton.superclass.constructor.call(this, Ext.apply(config, {
        renderTo: el,
		eShortcuts:config.eShortcuts,
		moduleId:config.moduleId,
		template: new Ext.Template(
			'<div class="ux-shortcut-btn"><div>',
				'<img src="'+Ext.BLANK_IMAGE_URL+'" />',
				'<div class="ux-eshortcut-btn-text">{0}</div>',
			'</div></div>')
    }));
    
};

Ext.extend(Ext.ux.EShortcutButton, Ext.Button, {

	buttonSelector : 'div:first',
    onRender : function(){
        Ext.ux.EShortcutButton.superclass.onRender.apply(this, arguments);
        this.cmenu = new Ext.menu.Menu({
            moduleId:this.moduleId,
			eShortcuts:this.eShortcuts,
			items: [{
				moduleId:this.moduleId,
                id: 'open',
                text: '打开',
                handler: function(e){
					openWinById(this.moduleId);
				},
                scope: this.win
            }, '-', {
				moduleId:this.moduleId,
				eShortcuts:this.eShortcuts,
                id: 'remove',
                iconCls: 'remove',
                text: '删除',
                handler: function(e){
					var m = JDSDesk.getModule(this.moduleId);
					if (m && m.shortcut) {
						this.eShortcuts.removeShortcut(m.shortcut);
					}
				},
                scope: this.win
            }]
        });
		
		shortcutdragZone = new JDS.dd.JDSDragZone(this.el, {
                ddGroup: 'DesktopShortcuts',
				moduleId:this.moduleId,
                scroll: false
            });
			
		 shortcutTarget = new JDS.dd.JDSDropTarget(this.el, {
                ddGroup: 'DesktopShortcuts',
                overClass: 'dd-over',
				moduleId:this.moduleId,
				eShortcuts:this.eShortcuts,
				notifyDrop: function(dd, e, data) {
				    this.eShortcuts.moveShortcut(data.moduleId,this.moduleId);
			        return true;
			    }
            });
	 

        this.el.on('contextmenu', function(e){
        	e.stopEvent();
            if(!this.cmenu.el){
                this.cmenu.render();
            }
            var xy = e.getXY();
            xy[1] -= this.cmenu.el.getHeight()-50;
            this.cmenu.showAt(xy);
        }, this);
    }, 
    
    initButtonEl : function(btn, btnEl){
    	Ext.ux.EShortcutButton.superclass.initButtonEl.apply(this, arguments);
    	btn.removeClass("x-btn");
    	if(this.iconCls){
            if(!this.cls){
                btn.removeClass(this.text ? 'x-btn-text-icon' : 'x-btn-icon');
            }
        }
    },
    
    autoWidth : function(){
    	// do nothing
    },
	
	/**
     * Sets this shortcut button's text
     * @param {String} text The button text
     */
    setText : function(text){
        this.text = text;
        if(this.el){
        	this.el.child("div.ux-eshortcut-btn-text").update(text);
        }
    }
});



