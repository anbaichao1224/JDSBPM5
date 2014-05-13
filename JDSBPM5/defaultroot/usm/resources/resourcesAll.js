Ext.onReady( function() {
	var modulelist = {
		type :'DataView',
		id :'modulelist',
		fields : [ 'id', 'name', 'url' ],
		url :'/usm/moduleGridview.do',
		title :'&#x6240;&#x6709;&#x6A21;&#x5757;&#x5217;&#x8868;'
	}
	var ss = creatDataView(modulelist);
	ss.render(document.body);
});

function creatDataView(cfg) {

	var store = new Ext.data.JsonStore( {
		url :cfg.url,
		root :'images',
		fields :cfg.fields ? cfg.fields : [ 'id', 'name', 'url' ]
	});
	store.load();

	var defalutTpl = new Ext.XTemplate(
			'<tpl for=".">',
			'<div class="JDSICON-wrap" id="{id}">',
			'<div ><img height="60" width="75" src="{url}" title="{name}" style="cursor:hand" /></div>',
			'<div style="width:75px"><span>{shortName}</span></div></div>',
			'</tpl>', '<div class="x-clear"></div>')

	var view = new Ext.DataView( {
		emptyText :'没有',
		itemSelector :'div.JDSICON-wrap',
		loadingText :'正在读取。。。',
		singleSelect :true,
		prepareData : function(data) {
			data.shortName = Ext.util.Format.ellipsis(data.name, 17);
			return data;
		},
		listeners : {
			'click' : {
				fn : function(view, nodes, m) {
					window.parent.createModuleWindow(m.id);
				}
			}

		},

		store :store,
		tpl :cfg.tpl ? cfg.tpl : defalutTpl
	});

	var defaults = new Ext.Panel( {
		animCollapse :true,
		baseCls :'collapse-group',
		cls :'pref-thumbnail-viewer',
		border :false,
		collapsible :true,
		hideCollapseTool :false,
		id :'pref-theme-view',
		items :view,
		title :cfg.title,
		titleCollapse :true
	});

	var themes = new Ext.Panel( {
		autoScroll :true,
		bodyStyle :'padding:10px',
		border :true,
		cls :'pref-card-subpanel',
		id :'themes',
		items :defaults,
		margins :'10 15 0 15',
		tbar : [ '&#x6A21;&#x5757;&#x540D;&#x79F0;&#xFF1A;',
				new Ext.form.TextField( {
					id :'findvalue',
					width :175,
					name :'findvalue',
					allowBlank :true
				}), {
					id :'newModelButton',
					icon :"/usm/img/search.png",
					cls :"x-btn-text-icon",
					handler : function() {
						if (Ext.get("findvalue").dom.value != "")
							findIt(Ext.get("findvalue").dom.value);
					}
				} ],
		keys : [ {
			key :Ext.EventObject.ENTER,
			fn : function() {
				if (Ext.get("findvalue").dom.value != "")
					findIt(Ext.get("findvalue").dom.value);

			},
			scope :this
		} ],
		region :'center'
	});

	var panel = new Ext.Panel( {
		autoScroll :true,
		border :true,
		cls :'pref-card',
		id :cfg.id,
		margins :'10 15 0 15',
		region :'center',

		items : [ themes ]
	});

	return panel;
}
