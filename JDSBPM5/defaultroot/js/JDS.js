var porcess=0;
var JDSDesk;
var JDS={
	ajax:{
	},
	dd:{
		
	},
	bpm:{
	},
	util:{
		
	},
	desktop:{
		widgets:{
			
		}
	}
}

	
function Hash(object) {
    
  this.object=object;
  this.toQueryString=function(){
      
    return Ext.urlEncode(this.object);
  };
};
function $H(object) {
  return new Hash(object);
};
Array.prototype.each=function(iterator,scope) {
  Ext.each(this,iterator,scope);
 }


/**
 * 
 * @param {Object} msg
 */
JDS.Msg={
	
	alertMsg:function (msg){
	 Ext.MessageBox.show({
	           title: '提示',
	           msg: msg,
	           width:300,
	           buttons:  {           
	           yes : "确定"
	        },
	           animEl: currGrid,
	           icon: Ext.MessageBox.INFO
	       });
	},
	
	warningMsg:function (msg){
	 Ext.MessageBox.show({
	           title:"系统警告",
	           width:300,
	           msg: msg,
	           buttons:  {           
	           yes : "确定"
	           },
	          // animEl:currGrid,
	          icon: Ext.MessageBox.WARNING
	       });
	 },
	 
	errorMsg:function (msg){
	 Ext.MessageBox.show({
	           title: "系统错误",
	            width:300,
	           msg: msg,
	           buttons:  {           
	           yes : "确定"
	           },
	           animEl:currGrid,
	           icon: Ext.MessageBox.ERROR     });
	 },
	 
  doOk:function (){
    Ext.MessageBox.hide();
   return true;
   }


}

function getIndexOfArr(value,arr){
	for (var i=0; i<arr.length; i++) {
		if (value==arr[i]){
				return i;
			}
	};
	return -1;
		
	};
	
	
	

/**
 * Create a DragZone instance for our JsonView
 */
JDS.dd.DragZone = function(view, config){
    this.view = view;
    JDS.dd.DragZone.superclass.constructor.call(this, view.el, config);
};
Ext.extend(JDS.dd.DragZone, Ext.dd.DragZone, {
	
    getDragData : function(e){
        var target = e.getTarget('.ux-shortcut-btn');
        if(target){
            var view = this.view;
			var btn=view.getBtnByDom(target);
            if(!btn.selected){
                view.select(btn);
            }
            var selectedItems = view.getSelectedItems();
            var dragData = {
				selectedItems:selectedItems
            };
			
            if(selectedItems.length == 1){
                dragData.ddel = target;
                dragData.single = true;
				var div = document.createElement('div'); // create the multi element drag "ghost"
				div.innerHTML = btn.getEl().dom.innerHTML;
		        div.className = btn.getEl().dom.className;
		        div.style.width = btn.getEl().getWidth() + "px";
			   	dragData.ddel = div;
	         }else{
                var div = document.createElement('div'); // create the multi element drag "ghost"
                div.className = 'multi-proxy';
                for(var i = 0, len = selectedItems.length; i < len; i++){
					var cloneNode=selectedItems[i].getEl().dom.cloneNode(true);
					cloneNode.style.display='inline';
				
					 div.appendChild(cloneNode); // image nodes only
                    if((i+1) % 3 == 0){
                        div.appendChild(document.createElement('br'));
                    }
                }
                var count = document.createElement('div'); // selected image count
                count.style.color="#ffffff";
			    count.innerHTML = '('+i+') 文件 被选中';
                div.appendChild(count);
                dragData.ddel = div;
                dragData.multi = true;
            }
            return dragData;
        }
        return false;
    },
	

	
	autoOffset : function(x, y) {
		this.setDelta(24, this.view.getSelectedItems().length*20+30 );
    },    
	onBeforeDrag :function( data,e){
		data.startX=e.getXY()[0];
		data.startY=e.getXY()[1];
		this.view.isDrag=true;
	    return true;
	} ,
	onMouseUp:function( data,e){
		this.view.isDrag=false;
	    return true;
	} ,

      afterRepair:function(){
	    for(var i = 0, len = this.dragData.selectedItems.length; i < len; i++){
            Ext.fly(this.dragData.selectedItems[i].getEl().dom).frame('#8db2e3', 1);
        }
        this.dragging = false;    
		
    },
    
   
    getRepairXY : function(e){
        return false;
    },
	endDrag:function(e){
		this.view.clearSelections();
		 return false;
	}
});

	
	
	
	
JDS.dd.DragSelector=function(cfg){
    cfg = cfg || {};
    var view, regions, proxy, tracker;
    var rs, bodyRegion, dragRegion = new Ext.lib.Region(0,0,0,0);
    var dragSafe = cfg.dragSafe === true;

    this.init = function(dataView){
        view = dataView;
        onRender(view);
    };

    function fillRegions(){
        rs = [];
        view.items.each(function(item){
            rs[rs.length] = item.getEl().getRegion();
        });
        bodyRegion = view.el.getRegion();
    }

    function cancelClick(e,view,config){
		
		    return false;
    }

    function onBeforeStart(e){
        return !dragSafe || e.target == view.el.dom;
    }

    function onStart(e){
	    view.on('containerclick', cancelClick, view, {single:true}); 
		if (view.isDrag){
			return;
		}
     
        if(!proxy){
            proxy = view.el.createChild({cls:'x-view-selector'});
        }else{
            proxy.setDisplayed('block');
        }
        fillRegions();
        view.clearSelections();
    }

    function onDrag(e){
		if (view.isDrag){
			return;
		}
        var startXY = tracker.startXY;
        var xy = tracker.getXY();

        var x = Math.min(startXY[0], xy[0]);
        var y = Math.min(startXY[1], xy[1]);
        var w = Math.abs(startXY[0] - xy[0]);
        var h = Math.abs(startXY[1] - xy[1]);

        dragRegion.left = x;
        dragRegion.top = y;
        dragRegion.right = x+w;
        dragRegion.bottom = y+h;

        dragRegion.constrainTo(bodyRegion);
        proxy.setRegion(dragRegion);

        for(var i = 0, len = rs.length; i < len; i++){
            var r = rs[i], sel = dragRegion.intersect(r);
            if(sel && !r.selected){
                r.selected = true;
                view.selectByIndex(i, true);
            }else if(!sel && r.selected){
                r.selected = false;
                view.deselectByIndex(i);
            }
        }
    }

    function onEnd(e){
        if(proxy){
            proxy.setDisplayed(false);
        }
    }

    function onRender(view){
        tracker = new Ext.dd.DragTracker({
            onBeforeStart: onBeforeStart,
            onStart: onStart,
            onDrag: onDrag,
            onEnd: onEnd
        });
		
       tracker.initEl(view.el);
    }
};

	
	
/**
 * 
 * @param {Object} el
 * @param {Object} config
 */ 
JDS.dd.JDSDragZone = function(el, config) {
    config = config || {};
     moduleId:Ext.id();
    Ext.applyIf(config, {
		centerFrame: true,
        ddel: document.createElement('div')
    });
    JDS.dd.JDSDragZone.superclass.constructor.call(this, el, config);
};
/**
 * 
 * @param {Object} e
 */ 
Ext.extend(JDS.dd.JDSDragZone, Ext.dd.DragZone, {
	centerFrame: true,
    getDragData: function(e) {
            var target = Ext.get(e.getTarget());
	       return {ddel:this.ddel,	moduleId:this.moduleId, item:target.up('',1)};
		
    },
    onInitDrag: function(e) {
        this.ddel.innerHTML = this.dragData.item.dom.innerHTML;
        this.ddel.className = this.dragData.item.dom.className;
        this.ddel.style.width = this.dragData.item.getWidth() + "px";
        this.proxy.update(this.ddel);
    },

    getRepairXY: function(e, data) {
        return data.item.getXY();
    },
	autoOffset : function(x, y) {
        this.setDelta(this.dragData.item.getWidth()/2, this.dragData.item.getHeight()/2);
    }    
    
});

/**
 * 
 * @param {Object} el
 * @param {Object} config
 */
JDS.dd.JDSDropTarget = function(el, config) {
    JDS.dd.JDSDropTarget.superclass.constructor.call(this, el, config);
};
Ext.extend(JDS.dd.JDSDropTarget, Ext.dd.DropTarget, {
   
});

JDS.alert=function(o){
	alert(JDS.util.String.debug(o));
}

/**
 * 
 * @param {Object} s
 * @param {Object} oldkey
 * @param {Object} newkey
 */
JDS.util.String={	
    replaceAll:function (s,oldkey,newkey){
			var ss=s.replace(oldkey,newkey);
		if (ss.indexOf(oldkey)>-1){
			ss=JDS.util.String.replaceAll(ss,oldkey,newkey);
		}
	
		return ss;
	},
	
	 debug : function(o){
            if(!o){
                return "";
            }
			if (typeof(o)=='string'){
				return o;
			}
            var buf = [];
            for(var key in o){
                var ov = o[key], k = key;
                var type = typeof ov;
                if(type == 'undefined'){
                    buf.push(k, "=\n");
                }else if(type != "function" && type != "object"){
                    buf.push(k, "=", ov, "\n");
                }else if(Ext.isArray(ov)){
                    if (ov.length) {
	                    for(var i = 0, len = ov.length; i < len; i++) {
	                        buf.push(k, "=", ov[i] === undefined ? '' : ov[i], "\n");
	                    }
	                } else {
	                    buf.push(k, "=\n");
	                }
                }
            }
            buf.pop();
            return buf.join("");
        }
	
}






//	调用示例
//  addbuddy = context+"jame/addPerson.action",//后台相应地址

//	addGroup : function(f,a){
//		var url = addbuddy;
//		var form=f.ownerCt.form;	

//		var evalJs = function(o){//定义回调方法
//			   Ext.namespace("EVAL");
//               eval(o);
//			   EVAL.addGroup();
//			}
//			JDS.ajax.Connection.LoadJsonFromUrl(url,evalJs,$H({name:form.findField('name').getValue()}));
//	}
//	
//var JDS={
//	ajax:{
//	},
//	bpm:{
//		
//	}
//}	


    


JDS.ajax.Connection={
	
	/**
	 * Ajax 简便调用链接方法
	 * scope源对象中必须包括
	 * [url,params]
	 * 需要回调函数render（）
	 * @param {Object} scope
	 */
	load:function(scope){
	
		 var evalJs = function(o){
			 
				  Ext.apply(this,Ext.decode(o));
				
				  this.render();  
		};
	
	   JDS.ajax.Connection.LoadJsonFromUrl(scope.url,evalJs,scope.params,scope);	
	},
	
	/**
	 * Ajax调用链接参考注释示例
	 * @param {Object} _url//后台相应地址
	 * @param {Object} callback//定义回调方法
	 * @param {Object} args//POST 参数
	 * @param {Object} args//POST 反调THIS指针
	 */
	LoadJsonFromUrl : function(_url,callback,args,scope){

			if(!args && !(typeof(args)=='string') ){
					args = new Hash();
			}
			
		Ext.Ajax.request({
			url: _url,
			params : typeof(args)=='string' ? args+'&Extuuid='+Ext.id() : args.toQueryString()+'&Extuuid='+Ext.id(),
			success: function(o){
			
				if(o && o.responseText ){
						if (callback){
							callback.call(scope,o.responseText);
						}
						
					}
			},
			failure: function(){
				Ext.Msg.show({
				 		title:'网络连接超时',
				 		msg: '网络连接超时，请重新登录账号！',
				 		buttons: Ext.MessageBox.OK
				 	//	fn:function(){sendNSCommand("ognl","reLogin('网络连接失败，请重新登录!')");}
						//icon:Ext.MessageBox.ERROR
					});
			},
			scope: scope
		});
			
		
			
		},
		
	
	
		
	/**
	 * 在桌面环境下调用AJAX方法 集成了saveComplete提示！
	 * @param {Object} _url
	 * @param {Object} callback
	 * @param {Object} args
	 * @param {Object} scope 
	 */
	LoadJsonFromUrlByDeskTop : function(_url,callback,args,scope){
	
		if (JDSDesk){
			var	desktop=getCurDesktop();
	
	    	/*var notifyWin = desktop.showNotification({
				html: '正在处理'
				, title: '请等候'
			});*/
		}
	
			if(!args && !(typeof(args)=='string') ){
					args = new Hash();
			}
		
		Ext.Ajax.request({
	
			url:_url,
			params : typeof(args)=='string' ? args : args.toQueryString(),
			complete: function(o) {
					if(o && o.responseText){
						saveComplete(' 完成', '操作成功',o.responseText,scope);
					}else{
						saveComplete(' 完成', '操作成功','',scope);
					}
			  	},
		    success: function(o){
				
			 if(o && o.responseText){
					saveComplete(' 完成', '操作成功',o.responseText,scope);
				}else{
					saveComplete('失败', '无法联系到服务器保存失败',o.responseText,scope);
				}
			},
			failure: function(o){
				saveComplete('失败', '无法联系到服务器保存失败',o.responseText,scope);
			},
			scope: scope
		});
		
		function saveComplete(title, msg,callbackScope,scope){
		
			/*try{
			notifyWin.setIconClass('x-icon-done');
			notifyWin.setTitle(title);
			notifyWin.setMessage(msg);
			desktop.hideNotification(notifyWin);
			}catch(e){
				
			}*/
			if(callback){	
				callback(callbackScope,scope);
			}
		}	
	}

}






Ext.override(Ext.form.Checkbox, {
	onRender: function(ct, position){
		Ext.form.Checkbox.superclass.onRender.call(this, ct, position);
		if(this.inputValue !== undefined){
			this.el.dom.value = this.inputValue;
		}
		//this.el.addClass('x-hidden');
		this.innerWrap = this.el.wrap({
			//tabIndex: this.tabIndex,
			cls: this.baseCls+'-wrap-inner'
		});
		this.wrap = this.innerWrap.wrap({cls: this.baseCls+'-wrap'});
		this.imageEl = this.innerWrap.createChild({
			tag: 'img',
			src: Ext.BLANK_IMAGE_URL,
			cls: this.baseCls
		});
		if(this.boxLabel){
			this.labelEl = this.innerWrap.createChild({
				tag: 'label',
				htmlFor: this.el.id,
				cls: 'x-form-cb-label',
				html: this.boxLabel
			});
		}
		//this.imageEl = this.innerWrap.createChild({
			//tag: 'img',
			//src: Ext.BLANK_IMAGE_URL,
			//cls: this.baseCls
		//}, this.el);
		if(this.checked){
			this.setValue(true);
		}else{
			this.checked = this.el.dom.checked;
		}
		this.originalValue = this.checked;
	},
	afterRender: function(){
		Ext.form.Checkbox.superclass.afterRender.call(this);
		//this.wrap[this.checked ? 'addClass' : 'removeClass'](this.checkedCls);
		this.imageEl[this.checked ? 'addClass' : 'removeClass'](this.checkedCls);
	},
	initCheckEvents: function(){
		//this.innerWrap.removeAllListeners();
		this.innerWrap.addClassOnOver(this.overCls);
		this.innerWrap.addClassOnClick(this.mouseDownCls);
		this.innerWrap.on('click', this.onClick, this);
		//this.innerWrap.on('keyup', this.onKeyUp, this);
	},
	onFocus: function(e) {
		Ext.form.Checkbox.superclass.onFocus.call(this, e);
		//this.el.addClass(this.focusCls);
		this.innerWrap.addClass(this.focusCls);
	},
	onBlur: function(e) {
		Ext.form.Checkbox.superclass.onBlur.call(this, e);
		//this.el.removeClass(this.focusCls);
		this.innerWrap.removeClass(this.focusCls);
	},
	onClick: function(e){
		if (e.getTarget().htmlFor != this.el.dom.id) {
			if (e.getTarget() !== this.el.dom) {
				this.el.focus();
			}
			if (!this.disabled && !this.readOnly) {
				this.toggleValue();
			}
		}
		//e.stopEvent();
	},
	onEnable: Ext.form.Checkbox.superclass.onEnable,
	onDisable: Ext.form.Checkbox.superclass.onDisable,
	onKeyUp: undefined,
	setValue: function(v) {
		var checked = this.checked;
		this.checked = (v === true || v === 'true' || v == '1' || String(v).toLowerCase() == 'on');
		if(this.rendered){
			this.el.dom.checked = this.checked;
			this.el.dom.defaultChecked = this.checked;
			//this.wrap[this.checked ? 'addClass' : 'removeClass'](this.checkedCls);
			this.imageEl[this.checked ? 'addClass' : 'removeClass'](this.checkedCls);
		}
		if(checked != this.checked){
			this.fireEvent("check", this, this.checked);
			if(this.handler){
				this.handler.call(this.scope || this, this, this.checked);
			}
		}
	},
	getResizeEl: function() {
		//if(!this.resizeEl){
			//this.resizeEl = Ext.isSafari ? this.wrap : (this.wrap.up('.x-form-element', 5) || this.wrap);
		//}
		//return this.resizeEl;
		return this.wrap;
	}
});
Ext.override(Ext.form.Radio, {
	checkedCls: 'x-form-radio-checked'
});









