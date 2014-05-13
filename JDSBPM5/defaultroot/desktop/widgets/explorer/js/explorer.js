
Ext.namespace("JDS.explorer");
Explorer={};
App={};
JDSExplorer = Ext.extend(Ext.app.LocalModule, {
		moduleType : 'system',
		moduleId : 'JDSExplorer',
		id:'JDSExplorer',
	    module : this,
		
		init : function(){
			this.launcher = {
	            handler : this.createWindow,
	            iconCls:'jame',
	            scope: this,
	            shortcutIconCls: 'jame-shortcut',
	            text: '浏览器',
	            tooltip: '<b>JDS</b><br />浏览器'
			}
			Ext.apply(this,this.launcher); 
		},
		
	    createWindow : function(){
			var config={
				expressionToolMenu:Ext.id(),
				tableTool:Ext.id(),
				JDSExplorerId:Ext.id()
				
			}
		  dispwin = getCurDesktop().createWindow({
		  title:"资源管理器",
          width:800,
		
          height:600,
		  id :Ext.id(),
		  layout:'border',
		  items:[
		         { 
	                    region:'north',
					
	                    items:[
						{
			                      region:'north',
							      id:config.expressionToolMenu+'El',
								 //tbar:Explorer.Actions,
								  html:'<div id="'+config.expressionToolMenu+'"></div>',
								  height:23
							},
	                      {
			                      region:'center',
							      id:config.tableTool+'El',
								  html:'<div id="'+config.tableTool+'"></div>',
								  height:23
						  },
							{
			                     region:'south',
							     id:Ext.id(),
								 autoLoad: {url: context + "desktop/widgets/explorer/ieBar.action", params: "rodom="+Ext.id()},
								 height:23
							}
	                    ] ,
	                    height:75
	                }
					,
					
	                {
	                    region:'center',
	                    collapsible: true,
	                    autoScroll:true,
	                    split:true,
	                    layout:'border',
	                    items:
	                    [
							{
			                      region:'center',
							      id:'JDSExplorerEl',
							      closable:true,
							      autoScroll :true,
								  html:"<div id="+config.JDSExplorerId+"></div>"
							},
							{
			                    region:'west',
			                    split:true,
			                    width: 200,
			                    minSize: 175,
			                    maxSize: 200,
			                    collapsible: true,
			                    margins:'0 0 0 0',
			                    id:Ext.id(),
			                    autoLoad: {url: context + "desktop/widgets/explorer/leftPanel.action",  params: "rodom="+Ext.id(),callback:LeftPanel.initLeftPanel},
			                    iconCls:'nav'
		                   }
	                    ]
	                }
	             ]

			 }); 
			
		  dispwin.show();

		 var shortcuts = new Ext.ux.EShortcuts({
	    	renderTo: config.JDSExplorerId,
			taskbarEl:Ext.get(config.JDSExplorerId)
	    });
		
				
		initToolBar();
		//initMenubar();
		function initToolBar(){
			atableTool=new dhtmlXToolbarObject(config.tableTool,'100%',16,"");
		    atableTool.setOnClickHandler(toolbarclickeventhander);
		    atableTool.loadXML(context+"desktop/widgets/explorer/config/_toolbarutf8.xml");
		    atableTool.showBar();
		}
		function initMenubar(){
			var gfxpath = context+'explorer/codebase/imgs/toolbar/';
			aexpressionToolMenu=new dhtmlXMenuBarObject(config.expressionToolMenu,'100%',16,"",null);
			aexpressionToolMenu.setZIndex(9999);
			aexpressionToolMenu.setGfxPath(gfxpath);
			aexpressionToolMenu.loadXML(context+"explorer/codebase/config/_menuutf8.xml");
			aexpressionToolMenu.setOnClickHandler(menuclickeventhander);
			aexpressionToolMenu.showBar();
		  
		}
		
	    }
	});
		





/**
 * 菜单和工具栏事件处理
 */
function toolbarclickeventhander(itemId, itemValue) {
	initMenubar();
//	var a = new Act();
//	var selectId = myTree.getSelectedItemId();
//
//	if (itemId == "folderUp") {
//		a.folderUp();
//
//	} else if (itemId == "upload") {
//
//		a.upload(selectId);
//
//	}else{
//		eval('a.'+itemId+'(itemId,itemValue)');
//	}

}

function menuclickeventhander(itemId, itemValue) {

	var a = new Act();
	var selectId = myTree.getSelectedItemId();

	if (itemId == "folderUp") {
		//如果选中对象是文件的话，可以编辑。
		a.folderUp();

	} else if (itemId == "upload") {

		a.upload(selectId);

	}

}