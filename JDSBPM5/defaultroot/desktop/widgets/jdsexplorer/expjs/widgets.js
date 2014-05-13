
Ext.namespace('JDS.exp');
Ext.namespace('Exp');

/**
 * @class Explorer.store
 * @extends Ext.data.JsonStore
 * @param {Ext.Element} itemsAreaEl
 * @param {Function} hide
 */
JDS.exp.store = function(_config, itemsAreaEl, hide){
	var config = Ext.apply({
		    url : '/explorer/toJsonForExplorer.action',
		    root : 'folder',
		    fields : [
			    {name : 'fileORfolder', type :  'bool'},
			    {name : 'type', type :  'string', mapping :  'type'},
			    {name : 'size', type :  'string', mapping :  'size'},
			    {name : 'name', type :  'string', mapping :  'name'},
			    {name : 'path', type :  'string', mapping :  'path'},
				{name : 'pid' , type :  'string', mapping :  'pid'},
				{name : 'icon' , type :  'string', mapping :  'icon'},
				{name : 'menu' , type :  'string', mapping :  'menu'},
				{name : 'uuid' , type :  'string', mapping :  'uuid'},
				{name : 'url' , type :  'string', mapping :  'url'},
				{name : 'isDelete', type :  'string', mapping :  'isDelete'},
				{name : 'allowDrop' , type :  'string', mapping :  'allowDrop'},
			    {name : 'DateLastModified',type : 'string',mapping : 'DateLastModified'}
		    ]
		}, _config);
	JDS.exp.store.superclass.constructor.call(this, config);
	this.on("beforeload", this.loadMask, itemsAreaEl);
	this.on("load", this.afterEveryLoad.createSequence(hide), this);
	this.on('loadexception', this.loadexception);
}
Ext.extend(JDS.exp.store, Ext.data.JsonStore, {

	loadexception: function(_ds, o, arg, e){
		alert(JDS.util.String.debug(arg));

	}

	,afterEveryLoad:function (){
		var Count = this.getCount();
//		if(Count){
//			App.state.update(Count+'个对象');
//		}else{
//			App.state.restore();
//		}
	}

	,loadMask : function(){
		this.mask('数据交互中...', 'x-mask-loading');
	}
});

Exp.attribDialog = function(filename){
  var store = new Ext.data.JsonStore({
	    url: '/explorer/getAttrib.action',
	    baseParams: {
		 	'do' : 'getAttrib',
		 	nodeId : filename
		},
	    root : 'attrib',
	    autoLoad : true,
	    fields: [
			{name : 'fileName' ,type : 'string',mapping : 'fileName'},
			{name : 'type' ,type : 'string',mapping : 'type'},
	        {name : 'size', type: 'float' , mapping : 'size'},
	        {name : 'modiftime', type:'date', mapping : 'modiftime'}
	    ]
	});
	//store.load();
	var html =
	    '<tpl for=".">'+
		'<div class="attrib_Panel">'+
			'<div class="lable">名称：</div>' +
			'<input type="text" ' +
			'value="{fileName}" size="42" />'+
			'<div id="hz_line"></div>'+
			'<ul>'+
				'<li><div class="lable">类型：</div>{type}</li>'+
				'<li><div class="lable">位置：</div>{path}</li>'+
				'<li><div class="lable">大小：</div>{size}</li>'+
				'<li><div class="lable">占用空间：</div>{size}</li>'+
				'<li><div class="lable">包含</div></li>'+
			'</ul>'+
			'<div id="hz_line"></div>'+
			'<ul>'+
				'<li><div class="lable">创建时间：</div>{modiftime}</li>'+
				'<li><div class="lable">修改时间：</div>{modiftime}</li>'+
				'<li><div class="lable">访问时间：</div>{modiftime}</li>'+
			'</ul>'+
			'<div id="hz_line"></div>'+
			'<ul>'+
				'<li>'+
					'<div class="lable">属性：</div>'+
					'<div class="left">'+
						'<input id="attrib_isReadOnly" type="checkbox" /><label for="attrib_isReadOnly">只读(R)</label><br />'+
						'<input id="attrib_isHidden" type="checkbox" /><label for="attrib_isHidden">隐藏(H)</label>'+
					'</div>'+
				'</li>'+
			'</ul>'+
		'</div>'+
		'</tpl>';

	Exp.attribDialog.superclass.constructor.call(this, {
		shim : false,
		modal : false,
		shadow : false,
		autoTabs : false,
		resizable : false,
		width : 388,
		height : 443,
		closeAction : 'close',
		items:[
			new Ext.DataView({
		        store : store,
		        tpl : new Ext.XTemplate(html),
		        autoHeight : true,
		        multiSelect : true,
		        overClass : 'x-view-over',
		        itemSelector : 'div.thumb-wrap',
		        emptyText : 'No images to display'
    		})
		]
		//---------Buttons----------
	//	buttons:[Ext.ux.Helper.commonBtns.btnHide]
//			new Ext.Button({
//				text:'关闭',
//				handler:function(){this.hide();},
//				scope:this
//			})

	});
};
Ext.extend(Exp.attribDialog, Ext.Window, {
});

/**
 * @class DestinationChooser
 * @extends Ext.Window
 * Create a dialog used as Destination Chooser
 * @param {Object} config configuartion options
 */
JDS.exp.destinationChooser = function(config){
	this.destTree = new JDS.exp.tree.destinationTreePanel({
			withView:false
		});
	Ext.apply(this, config||{}, {
		closeAction: 'hide',
		autoScroll : true,
		shadow : true,
		shim  : false,
		modal : true,
		width : 326,
		height: 508,
		style : 'text-align:center;',
		items : [
		//-------Hint Text----------
			{
				tag:'div', id:'dC_HintInfo', style:'text-align:left;margin:10px;'
			},
			//---------Tree----------
			this.destTree
		],
		//---------Buttons----------
		buttons : [
//			new Ext.Button({
//				text : '新建文件夹',
//				handler : this.hide,
//				scope : this,
//				style : 'margin-right:65px;text-align:left;'
//			}),
			new Ext.Button(),
			new Ext.Button({
				text:'关闭',
				handler:function(){this.hide();},
				scope:this
			})
		]
	});
	JDS.exp.destinationChooser.superclass.constructor.call(this);
};
Ext.extend(JDS.exp.destinationChooser, Ext.Window, {
  /**
	 * @return {Object} the selected node
	 */
	getDestination:function(){
		return (this.destTree.getSelectionModel().getSelectedNode());
	},
	/**
	 * @type {String} dlgHintText The template Text for User's Hint
	 */
	dlgHintText:'选择目标路径，然后单击 确定 按钮 放弃请点击 关闭。',
	/**
	 * show the destinationChooser
	 * @cfg {String} btnText
	 * @cfg {String} dlgTitle
	 * @cfg {String} dlgHint
	 * @cfg {Function} fn the handler of botton
	 * @cfg {Object} scope fn's scope
	 */
	show:function(config){
		this.constructor.superclass.show.call(this);
		this.getEl().child('div[id="dC_HintInfo"]').update(
			String.format(this.dlgHintText,config.dlgHint,config.dlgTitle)
		);
    this.destTree.getSelectionModel().clearSelections();
    this.buttons[0].setText(config.btnText);
		this.targets=config.targets;
		var _self = this; //hack 'this'
		this.buttons[0].setHandler(
			function(){
        var n=_self.getDestination();
        if(!n){
          alert("请选择目标路径");
          return false;
        }

        _self.hide();
        config.fn(n.id);
      },
			config.scope||this
		);
		this.setTitle(config.dlgTitle);
		//path to scroll
		this.destTree.getEl().child('.x-panel-body').setStyle('overflow','auto');
	}
});

JDS.exp.ProgressBox = function() {
  var updatePro=function(progressBar,url,params,msg,fn){
    var fn=function(val){
      var n=Math.round(val*100);
      progressBar.updateProgress(val,n+"%"+ msg);
      
      if(n>=100){
        clearInterval(progressBar.win.inter);
        progressBar.win.close();
        if(fn){
          fn.apply(null);
        }
      }
    }
    //todo  在此取后台处理进度
    //JDS.ajax.Connection.LoadJsonFromUrl(url, fn, $H(params));

    //test 用
    var n=window.testNum;
    if(!n){
      n=0;
      window.testNum=n;
    }
    window.testNum+=0.1;
    fn(n);
    if(window.testNum>1.1)window.testNum=0;
  };
  
  return {
    showNew: function(cfg) {
      var dlg = new Ext.Window({
        id:Ext.id(),
        title:cfg.title || "进度",
        modal :cfg.modal || false,
        width:340,
        height:100
      });
      dlg.render(document.body);
      dlg.getEl().addClass('x-window-dlg');

      var bodyEl = dlg.body.createChild({
        html:'<div>' + cfg.text + '</div>'//'<div class="ext-mb-icon"></div><div class="ext-mb-content">adsa<span class="ext-mb-text"></span><br>'
      });
      var progressBar = new Ext.ProgressBar({
        renderTo:bodyEl
      });
      progressBar.setWidth(300);
      progressBar.updateProgress(0,cfg.msg);
      dlg.inter = setInterval(function() {
        var args=[progressBar,cfg.url,cfg.params,cfg.msg,cfg.fn];
        updatePro.apply(dlg,args);
      }, 1000);

      progressBar.win = dlg;


      dlg.tools.close.setDisplayed(false);

      dlg.on("close",function(){
        if(dlg.inter){
          clearInterval(dlg.inter);
        }
      });
      dlg.show();
      return dlg;
    }
  }
}();
