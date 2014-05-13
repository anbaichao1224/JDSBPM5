/*
 * 模块授权
 */
Ext.BLANK_IMAGE_URL = '/usm/resources/images/default/s.gif';
Ext.onReady( function() {

			Ext.QuickTips.init();
			var viewport = new Ext.Viewport(
					{
						layout :'border',
						items : [

								new Ext.tree.TreePanel(
										{
											id :'forum-tree',
											region :'west',
											title :'模块管理中心',
											split :true,
											width :225,
											minSize :175,
											maxSize :400,
											collapsible :true,
											margins :'0 0 0 5',
											animCollapse :false,
											animate :true,
											collapseMode :'mini',
											loader :new Ext.tree.TreeLoader(
													{
														url :'/usm/applicationtreeJson.do?sysid=' + sysid + ''
													}),
											rootVisible :true,
											lines :true,
											autoScroll :true,
											root :new Ext.tree.AsyncTreeNode( {
												id :'root',
												draggable :false,
												text :'所有列表'
											})
										}),
								        new Ext.Panel(
										{
											region :'center',
											id :'topic-grid',
											html :'<iframe name="views" id="views" scrolling="yes" frameborder="0" src="/usm/info.jsp" height=100% width=100%></iframe>'
										}) ]
					});

			var tree = Ext.getCmp('forum-tree');
			tree.on('beforeload',function(node){
			     if(node.attributes.treetype=='module'){
			    	 tree.loader.dataUrl = '/usm/applicationtreeJson.do?sysid='+sysid+'&fid='+node.id;
			     }  
			    });  
			var sm = tree.getSelectionModel();
			sm.on('beforeselect', function(sm, node) {
				return node.isLeaf();
			});
			tree.on('click', function(node) {
						if (node.id == 'root') {
							left_tree_refresh();
						} else {
							if (node.attributes.treetype == 'fathernodes') {

						} else if (node.attributes.type == 'modulenode') {
							
							Ext.get("views").dom.src = '/usm/rightTemplate.do?template=moduleright&sysid='
									+ sysid + '&nodeid=' + node.id;
						} else {

						}

					}

				}	);

		});

// 刷新树
function left_tree_refresh() {
	var tree = Ext.getCmp('forum-tree');
	tree.body.mask('加载中...', 'x-mask-loading');
	tree.loader.dataUrl = '/usm/applicationtreeJson.do?sysid='+sysid
	tree.root.reload();
	tree.root.collapse(true, true);
	setTimeout( function() {
		tree.body.unmask();
		tree.root.expand(false, false);
	}, 100);
};

// 生成节点的右键菜单
function treeCtxMenu(node, e) {
	var menu = new Ext.menu.Menu();
	// alert(node.id);
	var addApp = {
		id :"aMenu",
		text :"菜单授权",
		node :node,
		handler : function(item) {
	}
	};

	var expandAll = {
		id :"expMenu",
		text :"展开所有",
		node :node,
		handler : function(item) {
			item.node.expand(true);
		}
	};
	var collapseAll = {
		id :"collMenu",
		text :"折叠所有",
		node :node,
		handler : function(item) {
			item.node.collapse(true);
		}
	};

	if (node.attributes.treetype != 'fathernodes'
			&& node.attributes.type != 'modulenode') {
		menu.add(addApp);
	}

	if (!node.isLeaf()) {
		if (node.childNodes && node.childNodes.length > 0) {
			if (menu.items.length > 0) {
				menu.add("-");
			}
			menu.add(expandAll);
			menu.add(collapseAll);
		}
	}
	if (menu.items.getCount() > 0) {
		menu.showAt(e.getXY());
	}
	return;
}