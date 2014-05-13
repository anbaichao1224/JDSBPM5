Ext.onReady(function(){ 
var  processDefList= {
			type:'DataView',
			id:'processDefListcfg',
			fields:['id', 'name', 'url'],
			url:'/usm/systemGridview.do',
			title:'应用资源列表'
		}
 	var ss=creatDataView(processDefList);

   ss.render(document.body);
   });
   

    
 function creatDataView(cfg){

	var store = new Ext.data.JsonStore({
	    url: cfg.url,
	    root: 'images',
	   	fields: cfg.fields?cfg.fields:['id', 'name', 'url']
	});
	store.load();

	var defalutTpl = new Ext.XTemplate(
			'<tpl for=".">',
				'<div class="pref-view-thumb-wrap" id="{id}">',
					'<div class="pref-view-thumb"><img height="120" width="140" src="{url}" title="{name}" /></div>',
				'<span align=center>{shortName}</span></div>',
			'</tpl>',
			'<div class="x-clear"></div>'
		)
	
	
	var view = new Ext.DataView({
			autoHeight:true,
			cls: 'pref-thumnail-view',
			emptyText: '没有',
			itemSelector:'div.pref-view-thumb-wrap',
			loadingText: '正在读取。。。',
			singleSelect: true,
			overClass:'x-view-over',
			prepareData: function(data){
				data.shortName = Ext.util.Format.ellipsis(data.name, 17);
				return data;
			},
			listeners: {
					'click': {fn:function(view,nodes,s){
						window.parent.createSystemWindow(s.id);
				       // createWindow(s.id);
				    }}
					
				    },

			store: store,
			tpl: cfg.tpl?cfg.tpl:defalutTpl
		});

	
	
	
	
	var defaults = new Ext.Panel({
		animCollapse: true,
		baseCls:'collapse-group',
		cls:'pref-thumbnail-viewer',
		border: false,
		collapsible: true,
		hideCollapseTool: false,
		id: 'pref-theme-view',
		items: view,
		title: cfg.title,
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
	


	var panel = new Ext.Panel({
	        autoScroll: true,
			bodyStyle: 'padding:10px',
			border: true,
			cls: 'pref-card',
			id: cfg.id,
			margins: '10 15 0 15',
			region: 'center',
	    items: [
				themes
			]
	});

	return panel;
}	  

