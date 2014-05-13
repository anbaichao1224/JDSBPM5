
Ext.namespace("Ext.ux");
var ShortcutsMove=false;
Ext.BLANK_IMAGE_URL = context+'js/ext/resources/images/default/s.gif';
Ext.ux.Shortcuts = function(config){
	  Ext.apply(this, config);
	  Ext.ux.Shortcuts.superclass.constructor.call(this);
	var desktopEl = Ext.get(config.renderTo)
		, taskbarEl = config.taskbarEl
		, btnHeight = 66
		, btnWidth = 56
		, btnPadding = 15
		, col = null
		, row = null
		, selectedItems=[];
	this.el=desktopEl;
	this.items = [];
	this.isDrag=false;
	initColRow();
	
	/**
	 * add event
	 */
    this.addEvents(
        'containerclick"'
     )

	/**
	 *  public
	 *return selectedItems
	 */
	this.getSelectedItems=function(){
		return selectedItems;
	};
	
	/**
	 * 
	 * @param {Object} index
	 */
	this.selectByIndex=function (index){
		 var btn = this.items[index];
		this.select(btn);
	}
	
	/**
	 *  public
	 * @param {Object} dom
	 */
	this.getBtnByDom=function(dom){
		for(var i = 0, len = this.items.length; i < len; i++){
			if (this.items[i].getEl().dom==dom ){
				return this.items[i];
			}
		}
		return false;
	}
	
		/**
	 * public
	 * @param {Object} id
	 */
	this.getBtnById =function (id){
		for(var i = 0, len = this.items.length; i < len; i++){
			if(this.items[i].moduleId == id){
				return this.items[i];
			}
		}
		return false;
	}
	
	/**
	 * public
	 * @param {Object} btn
	 */
	this.select=function(btn){
		   btn.getEl().addClass('selected');
		   selectedItems.push(btn);
		   btn.selected=true;
	};
	
	/**
	 *  public
	 */
	this.clearSelections =function(){
		for(var i = 0, len = selectedItems.length; i < len; i++){
			var btn=selectedItems[i];
			btn.getEl().removeClass('selected');
		    btn.selected=false;
		}
		this.isDrag=false;
		selectedItems.length = 0;
	};
	
	/**
	 * 
	 * @param {Object} index
	 */
	this.deselectByIndex=function (index){
		var btn=this.items[index];
		deselect(btn);
	}
	
	/**
	 * public
	 * @param {Object} config
	 */
	this.addShortcut = function(config){
		
		btn=newShortcut(config);
		var index=config.index;
		if (index && index>0){
			this.items.splice(index,0,btn);
		}else{
			this.items.push(btn);
		}
		this.setXY(btn.container);
		
		
		
		
		return btn;
	};
	
	
	
	/**
	 * public
	 * @param {Object} sId
	 * @param {Object} tId
	 */
	this.moveShortcut= function(sId,tId){
	   var shortcuts=JDSDesk.getDesktop().config.launchers.shortcut;
	   var sIndex=getIndexOfArr(sId,shortcuts);
	   var shortcut = JDSDesk.getModule(sId).shortcut;
	   
	   if (!shortcut){
	   	var tIndex=getIndexOfArr(tId,shortcuts);
		 JDSDesk.getDesktop().addShortcut(sId,true,JDSDesk.getModule(sId),tIndex)
	   }else{
	   	 this.items.splice(sIndex,1);
		 shortcuts.splice(sIndex,1);
		  
		var tIndex=getIndexOfArr(tId,shortcuts);
		shortcuts.splice(tIndex,0,sId);
		this.items.splice(tIndex,0,shortcut);
	   }
	  
		this.handleUpdate();
	}
	
	
	/**
	 * public
	 * @param {Object} b
	 */
	this.removeShortcut = function(b){
		var d = document.getElementById(b.container.id);
		b.destroy();
		d.parentNode.removeChild(d);
		
		var s = [];
		for(var i = 0, len = this.items.length; i < len; i++){
			if(this.items[i] != b){
				s.push(this.items[i]);
			}
		}
		this.items = s;

		this.handleUpdate();
	}
	
	/**
	 * public
	 */
	this.handleUpdate = function(){
		initColRow();
		for(var i = 0, len = this.items.length; i < len; i++){
			this.setXY(this.items[i].container);
		}
	}
	
	/**
	 * public
	 * @param {Object} item
	 */
	this.setXY = function(item){
		var bottom = row.y + btnHeight,
			overflow = isOverflow(row.y + btnHeight);
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
	
	/**
	 * public
	 */
	this.reLoad = function(){
		initColRow();
		for(var i = 0, len = this.items.length; i < len; i++){
			this.setXY(this.items[i].container);
		}
	}

	
	/**
	 * private
	 * @param {Object} btn
	 */
	function deselect(btn){ 
		for(var i = 0, len = selectedItems.length; i < len; i++){
			if(selectedItems[i] == btn){
				selectedItems.remove(selectedItems[i]);
			}
		}
		   btn.getEl().removeClass('selected');
		   btn.selected=false;
	};
	
	/**
	 * private
	 */
	function initColRow(){
		col = {index: 1, x: btnPadding};
		row = {index: 1, y: btnPadding};
	}
	
	/**
	 * private
	 * @param {Object} y
	 */
	function isOverflow(y){
		if(y > (Ext.lib.Dom.getViewHeight() - taskbarEl.getHeight())){
			return true;
		}
		return false;
	}
	
	

	/**
	 * private
	 * @param {Object} config
	 */
	function newShortcut(config){
		var div = desktopEl.createChild({tag:'div', cls: 'ux-shortcut-item'}),
		
		btn = new Ext.ux.ShortcutButton(Ext.apply(config, {
			text: Ext.util.Format.ellipsis(config.text, 16)
		}), div);
		
		if (config.scope){
			btn.setHandler(config.handler,config.scope)
		}
		
		return btn;
	}
	
//  会引起页面元素无法选中,暂时去掉! BY lwz 	
//    var dragSelector =new JDS.dd.DragSelector();
//	dragSelector.init(this);
//	
//	
	
	var dragZone = new JDS.dd.DragZone(this, {containerScroll:true,
        ddGroup: 'DesktopShortcuts',scroll: false});
	var viewTarget = new JDS.dd.JDSDropTarget(this.el, {
        ddGroup: 'DesktopShortcuts',
        overClass: 'dd-over',
		moduleId:this.moduleId,
		notifyDrop: function(dd, e, data) {
			if (data.moduleId){
				var btn=JDSDesk.getDesktop().shortcuts.getBtnById(data.moduleId);
				if (btn==null){
					JDSDesk.getDesktop().addShortcut(data.moduleId,true);
					btn=getBtnById(data.moduleId);
				}
				   btn.container.setXY(e.getXY());
		    }else if(data.selectedItems && data.selectedItems.length>0 ){
				for(var k=0;data.selectedItems.length>k;k++){
					var btn=selectedItems[k];
					if (btn==null){
						JDSDesk.getDesktop().addShortcut(data.moduleId,true);
						btn=this.getBtnById(data.moduleId);
					}
					btn.container.setXY([
					 btn.container.getXY()[0]+e.getXY()[0]-data.startX,
					 btn.container.getXY()[1]+e.getXY()[1]-data.startY,  
					]);
				}
				
			}
	        return true;
	    }
       });
	


	
	
	this.el.on('click',function(e){
        var item = e.getTarget('ux-shortcut-btn', this.el);
        if(item){
            var btn=this.getBtnByDom(item);
			this.select(btn);
        }else{
            if(this.fireEvent("containerclick", this, e) !== false){
                this.clearSelections();
            }
        }
	},this);
  	Ext.EventManager.onWindowResize(this.handleUpdate, this, {delay:500});
};


/**
 * extend
 */
Ext.extend(Ext.ux.Shortcuts, Ext.util.Observable, {
    init : Ext.emptyFn
});




/**
 * @class Ext.ux.ShortcutButton
 * @extends Ext.Button
 */
Ext.ux.ShortcutButton = function(config, el){
    Ext.ux.ShortcutButton.superclass.constructor.call(this, Ext.apply(config, {
        renderTo: el,
		moduleId:config.moduleId,
		selected:false,
		template: new Ext.Template(
			'<div class="ux-shortcut-btn "><div><div class='+config.moduleId+'-icons">',
				'<img src="'+Ext.BLANK_IMAGE_URL+'" /><div>',
				'<div class="ux-shortcut-btn-text">{0}</div>',
			'</div></div>')
    }));
    
};



Ext.extend(Ext.ux.ShortcutButton, Ext.Button, {
	buttonSelector : 'div:first',
	
    onRender : function(){
        Ext.ux.ShortcutButton.superclass.onRender.apply(this, arguments);
		this.cmenu=DesktopAction.getShortcutContextMenu(this);
		 shortcutTarget = new JDS.dd.JDSDropTarget(this.el, {
            ddGroup: 'DesktopShortcuts',
            overClass: 'dd-over',
			moduleId:this.moduleId,
			notifyDrop: function(dd, e, data) {
				if (data.moduleId){
					   JDSDesk.getDesktop().shortcuts.moveShortcut(data.moduleId,this.moduleId);
			    }else if(data.selectedItems && data.selectedItems.length>0 ){
						for (var k = 0; data.selectedItems.length > k; k++) {
							var btn = data.selectedItems[k];
							var tId=this.moduleId;
							if (k>0){
							 tId=data.selectedItems[k-1].moduleId;
							}
							JDSDesk.getDesktop().shortcuts.moveShortcut(data.selectedItems[k].moduleId, tId);
						}
				}
			 
		        return true;
		    }
	 });
			
			
		   this.el.on('mouseover',function(e){
			    //Ext.fly(this.getEl().dom).frame('#8db2e3', 1,{ stopFx: true});
				return false;
			},this);
	        this.el.on('contextmenu', function(e){
        	e.stopEvent();
            if(!this.cmenu.el){
                this.cmenu.render();
            }
            var xy = e.getXY();
            this.cmenu.showAt(xy);
        }, this);
		
		
    }, 
    
    initButtonEl : function(btn, btnEl){
    	Ext.ux.ShortcutButton.superclass.initButtonEl.apply(this, arguments);
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
        	this.el.child("div.ux-shortcut-btn-text").update(text);
        }
    }
});



