Ext.onReady(function(){
var  template= {
			type:'DataView',
			id:'processDefListcfg',
			fields:['id', 'name', 'url'],
			url:'/usm/applicationTemplate.do?sysid='+sysid+'&template='+p
			,
			title:'所有列表'
		}
		
 	var ss=creatDataView(template);

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
					'<div class="pref-view-thumb"><img height="65" width="65" src="{url}" title="{name}" /></div>',
				'<span>{shortName}</span></div>',
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
				data.shortName = Ext.util.Format.ellipsis(data.name, 5);
				return data;
			},
			listeners: {
					'click': {fn:function(view,nodes,s){
		
				        if(p=='system'){
				        	//alert(s.id);
				        	window.parent.document.getElementById("views").src="/usm/resources/applicationTemplate.jsp?template=application&sysid="+s.id;
				        }else if(p=='application'){
				        	
				        	if(s.outerHTML.indexOf('folder.png')!=-1){
				        		window.parent.document.getElementById("views").src="/usm/resources/applicationTemplate.jsp?template=application&sysid="+s.id;
				        	}else{
				        		modulewin(s.id);
							}	
				        }
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

function modulewin(moduleid){
	var mo;
	if(!mo){
	mo = new Ext.Window({
			title: '模块信息',
			collapsible:true,
			width:550,
			height:480,
			id:'moduleshow',
			shim:false,
			animCollapse:false,
			constrainHeader:true, 
			maximizable: true,       
			html:'<iframe id="editapp" scrolling="yes" frameborder="0" src="/usm/moduleInfo.do?start=red&moduleid='+moduleid+'" height=100% width=100%></iframe>'
			});
			mo.show();
			}
}