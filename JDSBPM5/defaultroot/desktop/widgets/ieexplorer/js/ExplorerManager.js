
Ext.namespace('Explorer');
/**
 * A singleton for management of Web Shell Navigator
 */
Explorer.Manager = function(){
	/**
	 * @type {String}当前路径，当前查看方式
	 */
	var currentPath,
		currentView;

	/**
	 * 创建右键菜单 create them after DOM on Ready
	 * @type {Ext.menu.Menu}
	 */
	var NoSelctionrightClickMenu,
		HasSelctionrightClickMenu,
		TreeHasSelctionrightClickMenu;



	/**
	 * @this {Ext.DateView}
	 * @type {Event Handler}
	 */
	function rightClick_Handler(dataview, index, node, e){
		e.preventDefault();
		if(this.getSelectionCount()>0){
			//选区有内容的
			HasSelctionrightClickMenu.showAt(e.getXY());
		}else{
			//选区没有内容的
			NoSelctionrightClickMenu.showAt(e.getXY());
		}
	}
	
  function TreeRightClick_Handler(dataview, index, node, e){
		e.preventDefault();
		TreeHasSelctionrightClickMenu.showAt(e.getXY());	
	}
	
	return {
		init : function(extId){
			var itemsAreaEl = Ext.get(extId+'-itemsArea'),
        	 	hide = itemsAreaEl.unmask.createDelegate(itemsAreaEl);
			var url = '/explorer/toJsonForTree.action';
        	this.ds = new JDS.store(null, itemsAreaEl, hide);
			this.destinationChooser = new Explorer.destinationChooser();

			this.view = new JDS.view({
				renderTo: Ext.get(extId+'-itemsArea').createChild({tag : 'div',cls : 'viewClass'}),
				store : this.ds
			});
		
			this.grid = new JDS.view.Viewer.GridStyle({
				ds : this.ds
			});

			this.itemsMainPanel = new Ext.Panel({
			
				columnWidth: 1,
				border : true,
				autoScroll : true,
				items : Explorer.Manager.view,
				html : {
					tag :'div',
				
					id : "gridViewHolder"
				}
			});
			NoSelctionrightClickMenu =
				App.toolbar.createView_NoSelction_RightClick_Menu.call(App.Actions);
			HasSelctionrightClickMenu =
				App.toolbar.createView_hasSelction_RightClick_Menu.call(App.Actions);
			TreeHasSelctionrightClickMenu=App.toolbar.createTree_hasSelction_RightClick_Menu.call(App.Actions)
			//this.view.on('contextmenu', rightClick_Handler, this.view);
		},
		RootNodes  :  {
			myOwnSpace : '我的应用'
		},
		currentView : currentView,
		// Equivalent to the following get/set, but rather 'public attribute' looks like
		getView : function(){
			return currentView;
		},
		setView : function(view){
			currentView = view;
		},
		currentPath : currentPath,
		getCurrentPath : function(){
			return currentPath;
		},
		setPath : function(path){
			currentPath = path;
		},

		/**
		 * Select all items.
		 */
		selectAll : function(){
			if(currentView!='detail'){
				this.view.selectAllNodes();
			}else{
				//grid goes here
			}
		},
		clearSelections : function(){
			if(currentView!='detail'){
				this.view.clearSelections();
			}else{
				//grid goes here
			}
		}
		
		,
		/**
		 * down load the file from Server.
		 */
		open : function(item){

			Ext.Ajax.request({
				url : '',
				params : {
					'do' : 'open',
					path : item.path
				},
				success : function(){
					
				},
				failure : function(){alert('err')}
			});
		},
		/**
		 * @param {String} _currentPath
		 */
		render : function(_currentPath){
			//alert(_currentPath);
			App.state.update('<img width="23" style="margin-top : 2px;" ' +
					' src="/"/> 寻找项目中...');
			currentPath = _currentPath;
			var like = 'formTemp';
			var index = currentPath.indexOf(like);
			var strTmp = currentPath.substring(index+like.length+1);
//    App.toolbar.URIbar.setValue(
//				(/TreeNode/ig).test(currentPath)?
//				this.RootNodes[currentPath.split('_').pop()] : strTmp);

      var tmpPath=currentPath.replace("root","");
      var userId="";
      if(/(\{[^}]{36}\})/.test(currentPath)){
        userId=RegExp.$1;
      }
      if(userId){
        var tmpUserName=currUserName;// = getUserNameById(userId);
        tmpPath=currentPath.replace(userId,tmpUserName);
      }
      App.toolbar.URIbar.setValue(tmpPath);
      switch(currentView){
				case 'thumb' :
					this.view.changeView('thumb');
				break;
				case 'icon' :
					this.view.changeView('icon');
				break;
				case 'list' :
					this.view.changeView('list');
				break;
				case 'detail' :
					this.grid.changeView();
				break;
				default :
					this.view.changeView('icon');
				break;
			}

			this.ds.load({
				params : {
					dir : currentPath,
					personId: personid
				}

			});
				
		},
		/**
		 * Refresh view with current path info
		 */
		refresh : function(){
			if(!currentPath)throw new Error("当前目录为空！");
			this.render(currentPath);
		},
		/**
		 * @static
		 */
		fileNameParser : function(targets){
			if(targets.length >3){
				return targets.length;
			}else{
				var str = ['<table>'];
				Ext.each(targets, function(i){
					this.push('<tr><td>'+i.name+'</td></tr>');
				}, str);
				str.push('</table>')
				return str.join('');
			}
		},
		/**
		 * 获取当前选中的item(s)
		 * @argument {Fucntion} fn Callback
		 * @argument {Object} scope
		 * @this {this}
		 */
		getSelected : function(fn, scope){
			var selectedItems = [],
				dataStore = this.ds;

			if(!this.view.getSelectionCount()){
				App.state.alert("你没有选择任何记录");
				return false;
			}

			Ext.each(this.view.getSelectedIndexes(), function(i){
				this.push(dataStore.getAt(i).data);
			}, selectedItems);

			fn.call(scope||this, selectedItems);
		},
		
		getSelectedItems : function(fn, scope){
			var selectedItems = [],
				dataStore = this.ds;

			if(!this.view.getSelectionCount()){
				App.state.alert("你没有选择任何记录");
				return false;
			}

			Ext.each(this.view.getSelectedIndexes(), function(i){
				selectedItems.push(dataStore.getAt(i).data);
			});

			return selectedItems;
		},
		
		getViewSelected : function(fn, scope){
			var selectedItems = [],
				dataStore = this.ds;

			if(!this.view.getSelectionCount()){
				App.state.alert("你没有选择任何记录");
				return false;
			}

			Ext.each(this.view.getSelectedIndexes(), function(i){
				this.push(dataStore.getAt(i).data);
			}, selectedItems);

			return selectedItems;
		}
	};
}();




