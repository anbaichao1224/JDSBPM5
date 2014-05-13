<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
String contextpath = request.getContextPath() + "/";
%>

 /**
  * 
  * @param {Object} cfg
  * cfg={
  * id:'test',（必选）
  * url:'demo.action',（必选）
  * fields:['id', 'name', 'pathtothumbnail', 'pathtofile'],（可选）
  * typ:new Ext.XTemplate()(可选)
  * title:（必选）
  * 
  * }
  */ 
  
 function creatDataView(cfg){

	var store = new Ext.data.JsonStore({
	    url: cfg.url,
	    root: 'images',
	   	fields: cfg.fields?cfg.fields:['id', 'name', 'pathtothumbnail']
	});
	store.load();
	
	var defalutTpl = new Ext.XTemplate(
		'<tpl for=".">',
			'<div class="JDSICON-wrap" id="theme-{id}">',
				'<div><img src="{pathtothumbnail}" title="{name}" /></div>',
			'<div class="iconTitle"  >{shortName}</div></div>',
		'</tpl>',
		'<div class="x-clear"></div>'
	);
	
	
		
	var view = new Ext.DataView({
			autoHeight:true,
			emptyText: '没有',
			
			  itemSelector: 'div.JDSICON-wrap',
			loadingText: '正在读取。。。',
			singleSelect: true,
			prepareData: function(data){
				data.shortName = Ext.util.Format.ellipsis(data.name, 17);
				return data;
			},
			store: store,
			tpl: cfg.tpl?cfg.tpl:defalutTpl
		});
	view.on('selectionchange', onSelectionChange, this);
	
	
	function onSelectionChange(view, sel){		
		var	r = view.getRecord(sel[0]),
		d = r.data;
			newProcess(d.id);
	}
	
	
	
	
	
	var defaults = new Ext.Panel({
		animCollapse: false,
		baseCls:'collapse-group',
		border: false,
		  style: "background-color:white;height:600px;",
		//cls: 'pref-thumbnail-viewer',
		collapsible: true,
		hideCollapseTool: true,
		items: view,
		title: '双击启动流程',
		titleCollapse: true
	});
	
	
	
	var themes = new Ext.Panel({
		autoScroll: true,
		bodyStyle: 'padding:10px',
		border: true,
		cls: 'pref-card-subpanel',
	
		items: defaults,
		margins: '10 15 0 15',
		region: 'center'
	});
	


	var panel = new Ext.Panel({
	
	        autoScroll: true,
			bodyStyle: 'padding:10px',
			border: true,
			cls: 'pref-card',
			margins: '10 15 0 15',
			region: 'center',
	    items: [
				themes
			]
	});

	return panel;
}	  
  


function EVAL.getPanel(){
	 var  processDefList= {
			type:'DataView',
			id:Ext.id(),
			fields:['id', 'name', 'pathtothumbnail', 'pathtofile'],
			url:context+'processDefListTmp.action',
			title:'选择要启动的流程'
		}
	var win=creatDataView(processDefList)
	var panel={
	title:'启动流程',
	 items:[win]
	}
	
	return panel;
 }

 
