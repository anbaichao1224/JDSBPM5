
Ext.namespace('JDS.exp');
JDS.exp.view = function(config){

    var tpl = new JDS.exp.view.Viewer({view:this}, this.templateHTML);
    var label = new Ext.DataView.LabelEditor({
      dataIndex: 'name',
      ignoreNoChange:true
    });
    Ext.apply(this, config ||
    {}, {
        tpl: tpl,
       style: "background-color:white;height:600px;",
        isDrag: false,
        border: false,
		
        autoScroll: true,
        autoHeight: false,
        multiSelect: true,
        itemSelector: 'div.JDSICON-wrap',
        emptyText: '',
        plugins: [new Ext.DataView.DragSelector(),label],
        prepareData: function(data){
            // data.shortName = Ext.util.Format.ellipsis(data.name, 15);
            //data.sizeString = Ext.util.Format.fileSize(data.size);
            // data.dateString = data.DateLastModified.format("m/d/Y g:i a");
            return data;
        }
    });
  this.LabelEditor = label;
    JDS.exp.view.superclass.constructor.call(this);
    
    var dragZone = new ImageDragZone(this, {
        containerScroll: true,
        ddGroup: 'organizerDD'
    });
    
    var viewTarget = new JDS.dd.JDSDropTarget(this.getEl(), {
      ddGroup: 'organizerDD',
      overClass: 'dd-over',
      notifyOver: function(dd, e, data) {
        var flag = true;
        if (data.isDrops == null)
          return false;
        data.isDrops.each(function(item) {
          if (item == 'false') {
            flag = false;
          }
        });
        if (flag)
          return this.dropAllowed;
        else
          return this.dropNotAllowed;
      },
        notifyDrop: function(dd, e, data){
            var target = e.getTarget(this.itemSelector);

            //从folderEx到folderEx
            if (data.ids != null) {
                if (target.path == null)
                    return false;
                var ids = data.ids;
                var pids = data.pids;
                if (ids != null) {
                    //验证目标文件夹不是选中的文件夹
                    for (var i = 0; i < ids.length; i++) {
                        if (ids[i] == target.path)
                            return false;
                    }
                    if (target.fileORfolder == 'false') {
                        return false;
                    }
                }

                //1.刷新树目录中目标结点，
                //2.同一目录下的结点，移动需要刷新目标结点的父结点(也就是它们的父级)
//                var treeTNode = App.westTree.getNodeById(target.path);
//                var treePNode = App.westTree.getNodeById(pids[0]);

              var tree=JDS.exp.getCurrentWin().tree;
                var treeTNode =tree.getNodeById(target.path);
                var treePNode = tree.getNodeById(pids[0]);
                var childsByTarget = treeTNode.childNodes;
                for (var i = 0; i < ids.length; i++) {
                    var tmps = ids[0].split('\\');

                    if (!tree.checkSaveName(tmps, childsByTarget)) {
                        return false;
                    }
                }

                //3.FolderExplorer刷新当前目录
//                var folderEx = Explorer.Manager;
                    JDS.ajax.exp.Connection.folder4folder(ids, treePNode, treeTNode,JDS.exp.getCurrentWin().getCurrentPath());
                return true;
            }
            else {
                //从树拖到exploerFx
//                var folderEx = Explorer.Manager;

                var tree = JDS.exp.getCurrentWin().tree;
//                var curPath = folderEx.getCurrentPath();
                var curPath =JDS.exp.getCurrentWin().getCurrentPath();

                var dropId = dd.dragData.ddel['ext:tree-node-id'];
                var targetId = e.getTarget(this.itemSelector).path;

                if (targetId == null) {
                    targetId = curPath;
                }


                var dropNode = tree.getNodeById(dropId);
                var targNode = tree.getNodeById(targetId);
                var parNode = dropNode.parentNode;
                var curNode = tree.getNodeById(curPath);

                var dropIds = dropId.split('\\');

                //验证
                //4.目标结点应为文件夹
                if (targNode == null) {
                    alert('目标结点不是目录');
                    return false;
                }
                //1.拖动结点你父目录不能是当前目录
                if (parNode.id == curPath && targetId == null) {
                    alert(parNode.id + '\n' + curPath)
                    return false;
                }
                //2.拖动结点不能与目标地址相同
                if (dropNode.id == targNode.id) {
                    alert('拖动结点与目标相同');
                    return false;
                }

                //3.不能拖动到该结点的子结点下
                if (curPath.indexOf(dropId) != -1) {
                    alert('父结点不能拖到子结点中')
                    return false;
                }


                //5.目标结点下不不能有重名文件
                if (!tree.checkSaveName(dropIds, targNode.childNodes)) {
                    return false;
                }
                JDS.ajax.exp.Connection.tree4folder(dropNode, parNode, targNode, curPath);
            }

            return true;
        }
    });
    
    //双击进入目录或打开文件
	/**
	 * 
	 * @param {Object} vw
	 * @param {Object} index
	 * @param {Object} node
	 * @param {Object} e
	 */
    this.on("dblclick", function(vw, index, node, e) {
      /**
       * item为explorer数据结构模板
       * item.fileORfolder判断是文件还是文件夹
       */
      var item = vw.store.getAt(index).data;

      var menuType=item.menu;
      if(menuType){
        var fn=JDS.exp.actions.fns[menuType+"_open"];
        if(fn){
          JDS.exp.actions.exec(menuType+"_open",[]);
          return;
        }
      }
      if (item.fileORfolder && item.path) {
        JDS.exp.actions.exec("changeCurrentPath", [this.panel.win,item.path])
      }

//      if (item.fileORfolder && item.path) {
//        JDS.exp.actions.exec("changeCurrentPath", [this.panel.win,item.path])
//      }
//      else {
//        //打开所有在explorer的文件
//
//        /**
//         * 为段方法为打开程序下模块
//         */
//        var uuid = item.uuid;
//        if (uuid) {//此if说明uuid不为空，此结点为programNode结点
//          var module = JDSDesk.getModule(uuid);
//          module.createWindow();
//        }
//      }
    }, this);
    
    
    this.on("selectionchange", function(vw, selected_nodes){
        try {
          JDS.exp.setCurrentObj(this,vw.getSelectedRecords());
            var count = selected_nodes.length;
            this.state.update(count > 1 ? '已经选中了' + count + '个对象' : function(){
                return vw.store.getAt(vw.getSelectedIndexes()).data.name;
            }());
        } 
        catch (e) {
            //this.restore();
        }
    }, this.panel.win);
	/**
	 * right_click event for selected node
	 * @param {Object} view
	 * @param {Object} index
	 * @param {Object} node
	 * @param {Object} e
	 */
    this.on('contextmenu', function(view, index, node, e){
		
      var node = e.getTarget().parentNode;
      if(node ==null){
        return ;
      }
      var n=e.getTarget().parentNode.parentNode
      if(!view.isSelected(n)){
        view.select(n);
      }
      var menuType = node.menu;

      var callMenu = JDS.exp.getRightMenu(view.panel.win, node, menuType);
      callMenu.showAt(e.getXY());
      e.stopEvent();
    },this);
	/**
	 * right_click event for the floder panel
	 * @param {Object} e
	 */
	 this.el.on('contextmenu', function(e){
      var id = e.getTarget().parentNode.path;
      if(id == null){
        this.clearSelections();
        var win=this.panel.win;
        var path=JDS.getExpFlag(win, "currentPath");
        var node=win.tree.getNodeById(path)
        
        var menuType=node.attributes["menu"];
        if(menuType){
          menuType+="_b";
          var menus=JDS.exp.getRightMenu(win,this,menuType,"_b");
          menus.showAt(e.getXY());
        }
        e.stopEvent();
      }
    },this);
    
};

Ext.extend(JDS.exp.view, Ext.DataView, {
    templateHTML: 'icon',
    templateFunctions: {
        fileORfolder_icon: function(path){			
            var f = path.split('.');
            var icon = f.pop();
            return f.length > 1 && (new RegExp('html|gif|png|jpg|jpge', 'ig')).test(icon) ? icon + '_icon' : 'folder_icon';
        },

//		 fileORfolder_icon: function(path){			
//            var f = path.split('.');
//            var icon = f.pop();
//            return f.length > 1 && (new RegExp('html|gif|png|jpg|jpge', 'ig')).test(icon) ? icon + '_icon' : 'folder_icon';
//        },
        loadThumb: function(path){
            var _f = path.split('.');//获取扩展名
            return _f.length > 1 && (new RegExp('gif|png|jpg|jpge', 'ig')).test(_f.pop()) ? '' : '';
        },
        fileORfolder: function(fileORfolder){
            return fileORfolder ? 'IamFolder' : '';
        },
        typeIcon_output: function(filename){
            var _f = filename.split('.');
            if (_f && _f.length > 1) {
                return '' +
                _f.pop() +
                '.gif);';
            }
            else {
                return '';
            }
        },
        fileORfolder_small_icon: function(path){
            var f = path.split('.');
            var icon = f.pop();
            return f.length > 1 && (new RegExp('html|gif|png|jpg|jpge', 'ig')).test(icon) ? icon + '_small_icon' : 'folder_small_icon';
        },

      //中英文 字符串长度等处理
      binLen:function (str){
        var count=0;
        for(var i=0,c=str.length;i<c;i++){
          var n=str.charCodeAt(i);
          if(n<256){
            count++;
          }else{
            count+=2;
          }
          //alert(str.charAt(i)+"=="+count);
        }
        return count;
      },
      //bin子串
      binSubstr:function (str,s,e){
        var count=0;
        var rtn="";
        for(var i=0,n=str.length;i<n;i++){
          if(count>=s){
            if(e==undefined){
              rtn=str.substring(i);
              break;
            }else{
              if(count>=e){
                break;
              }else{
                rtn+=str.charAt(i);
              }
            }
          }

          var n=str.charCodeAt(i);
          if(n<256){
            count++;
          }else{
            count+=2;
          }
        }
        return rtn;
      },

      binFormatStr:function (str,len){
//         var count=0;
//         var rtn="";
//         var n=this.binLen(str);
//         if(n>len){
//           rtn=this.binSubstr(str,0,len);
//           rtn+="<br>";
//           if(n-len>len){
//            rtn+=this.binSubstr(str,len,len+len-3)+"...";
//          }else{
//            rtn+=this.binSubstr(str,len);
//          }
//          return rtn;
//        }else{
//          return str;
//        }
        var n=this.binLen(str);
        if(n>len*2){
          return this.binSubstr(str,0,20)+"...";
        }
        return str;
      },
        trimlongFilename: function(v){
         return this.binFormatStr(v.trim(),12);
        }
    },
    selectAllNodes: function(){
        var lastNode_index = this.store.getCount();
        if (lastNode_index) {
            for (var i = 0; i < lastNode_index; ++i) {
                if (i != lastNode_index-1) {
                    this.select(i, true, true);// it can be Grid
                }
                else {
                    this.select(i, true, false);//firing of the selectionchang event
                }
            }
        }
    },
    
    changeView: function(_viewAS){
        var tpl = new JDS.exp.view.Viewer(null, _viewAS);
        Ext.apply(tpl, this.templateFunctions);
        
        this.tpl = tpl;

        if (!this.panel.grid.hidden) {
            this.panel.grid.hide();
            this.show();
        }
    }
});
/**
 * @class JDS.exp.view.Viewer
 * @param {Object} config
 * @param {String} viewType
 */
JDS.exp.view.Viewer = function(config, viewType){
    var markup = this.viewAS[viewType];
    JDS.exp.view.Viewer.superclass.constructor.call(this, markup);
}

Ext.extend(JDS.exp.view.Viewer, Ext.XTemplate, {
    viewAS: {
        //缩略图方式
        thumb: '<tpl for=".">' +
        '<div class="thumb-wrap item perviewThumb_outter {fileORfolder:this.fileORfolder}">' +
        '<div class="{fileORfolder:this.fileORfolder_icon} perviewThumb"' +
        ' style="{name:this.typeIcon_output}">' +
        //put image element here if that is a image type, else return ""
        '{path:this.loadThumb}' +
        '</div>' +
        '<div class="iconTitle">{name:this.trimlongFilename}</div>' +
        '</div>' +
        '</tpl>',
        //图标方式
        icon: '<tpl for=".">' +
        '<div class="JDSICON-wrap" >' +
		  '<div class="{uuid}" path={path} name={name} menu={menu} fileORfolder={fileORfolder} isDelete={isDelete} pid={pid} uuid={uuid} >'+
		'  <img src="{icon}" ></img></div>' +
        '<div class="iconTitle"  >{name:this.trimlongFilename}</div>' +
      '</div>' +
        '</tpl>' +
        '<div class="x-clear"></div>',
        //列表方式
        list: '<tpl for=".">' +
        '<div style="width:120px;margin:2px;" class="thumb-wrap item {fileORfolder:this.fileORfolder}">' +
	   '<div class="{uuid}" path={path} name={name} menu={menu}  isDelete={isDelete}  fileORfolder={fileORfolder} pid={pid}  uuid={uuid}>'+
		'  <img src="{icon}" sytle="width:12px;height:12px;"></img></div>' +
        '<span class="title_list">{name}</span>' +
        '</div>' +
        '</tpl>',
        //详细(grid)
        detail: '<div class="item" id="item_{index}">' +
        '<div class="{fileORfolder:this.fileORfolder_icon}"></div>' +
        '<div class="iconTitle">{name:this.trimlongFilename}</div>' +
        '</div>'
    }
});

/**
 * @class JDS.exp.view.Viewer.GridStyle
 * @extends Ext.grid.EditorGridPanel
 */
JDS.exp.view.Viewer.GridStyle = function(config){
    var colModel = new Ext.grid.ColumnModel([{
        header: "Name",
        locked: false,
        sortable: true,
        dataIndex: 'name',
        width: 300,
        align: 'left',
        renderer: function(v, cell, record){
            return record.data.fileORfolder ? '<div class="grid_view_icon_16">' + v + '</div>' : '<div class="grid_view_icon_16 ' + (record.data.type).replace(' ', '_') + '">' + v + '</div>';
        },
        editor: new Ext.grid.GridEditor(new Ext.form.TextField({
            allowBlank: false,
            blankText: 'You must input a filename.'
        }))
    }, {
        header: "Size",
        width: 80,
        sortable: true,
        dataIndex: 'size',
        align: 'right'
        //			,
        //			renderer:function(v,cell,record){
        //				return record.data.fileORfolder ? null:v+'kb';
        //			}
    }, {
        header: "Type",
        width: 70,
        sortable: true,
        dataIndex: 'type'
    }, {
        header: "Last Modified",
        width: 150,
        sortable: true,
        dataIndex: 'DateLastModified'
        //			,
        //		 	renderer:function (value){
        //		        return value?value.dateFormat(' Y-m-d:A'):null;
        //				}
    }]);
    Ext.apply(this, config ||
    {}, {
        cm: colModel,
        border: false,
        autoHeight: false,
        autoScroll: true,
        enableDragDrop: true,
        autoSizeColumns: true,
        enableRowHeightSync: true,
        ddGroup: 'organizerDD'
    });
    JDS.exp.view.Viewer.GridStyle.superclass.constructor.call(this);
}

Ext.extend(JDS.exp.view.Viewer.GridStyle, Ext.grid.EditorGridPanel, {
    changeView: function(){
//        Explorer.Manager.view.hide();
      this.panel.view.hide();
        if (!this.rendered) {
            this.render(this.panel.id+'gridViewHolder');
            this.panel.itemsMainPanel.insert(1, this);
            this.show();
            this.el.child('div.x-grid3').setStyle({
                width: '100%',
                overflow: 'auto'
            });
        }
        else {
            this.show();
        }
    }
});




Ext.DataView.LabelEditor = function(cfg, field){
    Ext.DataView.LabelEditor.superclass.constructor.call(this, field ||
    new Ext.form.TextArea({
        allowBlank: false,
        growMin: 55,
        growMax: 100,
        grow: true,
        selectOnFocus: true
    }), cfg);
}

Ext.extend(Ext.DataView.LabelEditor, Ext.Editor, {
    alignment: "tl-tl",
    cls: "x-icon-editor",
    shim: false,
    completeOnEnter: true,
    cancelOnEsc: true,
    labelSelector: 'iconTitle',
    init: function(view){
        this.view = view;
        view.on('render', this.initEditor, this);
        this.on('complete', this.onSave, this);
        
    },
    
    initEditor: function(){
        this.view.el.on('click', this.onMouseDown, this);
    },
    
    onMouseDown: function(e, target){
    	
        if (!e.ctrlKey && !e.shiftKey) {
		
            var target = e.getTarget('.iconTitle');
            if (target) {
//                var item = this.view.findItemFromChild(target);
//                var record = this.view.store.getAt(this.view.indexOf(item));
////                this.startEdit(target, record.data[this.dataIndex]);
//              window.testObj=target;
//                this.startEdit(target);
//                this.activeRecord = record;
              this.view.panel.win.startRename();
                e.stopEvent();
            }
            
        }
        else {
            e.preventDefault();
        }
    },
    
    onSave: function(ed, value,oldvalue){
      try{
        this.activeRecord.set(this.dataIndex, value);
        this.view.panel.win.saveName(this.activeRecord.data,value);
      }catch(e){
        //忽略错误
      }
    }
});





Ext.DataView.DragSelector = function(cfg){
    cfg = cfg ||
    {};
    var view, regions, proxy, tracker;
    var rs, bodyRegion, dragRegion = new Ext.lib.Region(0, 0, 0, 0);
    var dragSafe = cfg.dragSafe === true;
    
    this.init = function(dataView){
        view = dataView;
        view.on('render', onRender);
    };
    
    function fillRegions(){
        rs = [];
        view.all.each(function(el){
            rs[rs.length] = el.getRegion();
        });
        bodyRegion = view.el.getRegion();
    }
    
    function cancelClick(){
        return false;
    }
    
    function onBeforeStart(e){
        return !dragSafe || e.target == view.el.dom;
    }
    
    function onStart(e){
        view.on('containerclick', cancelClick, view, {
            single: true
        });
        if (view.isDrag) {
            return;
        }
        
        if (!proxy) {
            proxy = view.el.createChild({
                cls: 'x-view-selector'
            });
        }
        else {
            proxy.setDisplayed('block');
        }
        fillRegions();
        view.clearSelections();
        
    }
    
    function onDrag(e){
    
        if (view.isDrag) {
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
        dragRegion.right = x + w;
        dragRegion.bottom = y + h;
        
        dragRegion.constrainTo(bodyRegion);
        
        try {
            proxy.setRegion(dragRegion, false);
        } 
        catch (e) {
            proxy = view.el.createChild({
                cls: 'x-view-selector'
            });
        }
        
        
        for (var i = 0, len = rs.length; i < len; i++) {
            var r = rs[i], sel = dragRegion.intersect(r);
            if (sel && !r.selected) {
                r.selected = true;
                view.select(i, true);
            }
            else 
                if (!sel && r.selected) {
                    r.selected = false;
                    view.deselect(i);
                }
        }
        
    }
    
    function onEnd(e){
    
        if (proxy) {
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
 * Create a DragZone instance for our JsonView
 */
var ImageDragZone = function(view, config){
    this.view = view;
    ImageDragZone.superclass.constructor.call(this, view.getEl(), config);
};
Ext.extend(ImageDragZone, Ext.dd.DragZone, {

    onInitDrag: function(x, y){
        this.proxy.update(this.dragData.ddel.cloneNode(true));
        this.onStartDrag(x, y);

        return false;
    },
    getDragData: function(e){
        //************************************获得数据****************************
        var target = e.getTarget('.JDSICON-wrap');
        if (target) {
            var view = this.view;
            if (!view.isSelected(target)) {
                //view.onClick(e);
              if(e.ctrlKey){
                //view.select(target,true);
              }else{
                view.select(target,false);
              }
              return false;
            }
            var selNodes = view.getSelectedNodes();
            var selectedRecords = view.getSelectedRecords();
            var ids = [];
            var pids = [];
            var types = [];
            var isDrops = [];
            selectedRecords.each(function(item){
                pids.push(item.get("pid"));
                ids.push(item.get("path"));
                types.push(item.get("fileORfolder"));
                isDrops.push(item.get("allowDrop"));
            });

            var dragData = {
              nodes  :  selNodes,
              ids    :  ids,
              pids  :  pids,
              isDrops  :  isDrops,
              types  :  types
            };
          if (selNodes.length == 1) {
            dragData.ddel = target;
            dragData.single = true;
          }
          else {
            var div = document.createElement('div'); // create the multi element drag "ghost"
            div.className = 'multi-proxy';


            for (var i = 0, len = selNodes.length; i < len; i++) {
                var cloneNode = selNodes[i].firstChild.cloneNode(true);
                cloneNode.style.display = 'inline';
                div.appendChild(cloneNode); // image nodes only

                //   div.appendChild(selNodes[i].firstChild.firstChild.cloneNode(true)); // image nodes only
                if ((i + 1) % 3 == 0) {
                  div.appendChild(document.createElement('br'));
                }
              }
              var count = document.createElement('div'); // selected image count
              count.innerHTML = i + ' file selected';
              div.appendChild(count);

              dragData.ddel = div;

              dragData.multi = true;
            }
            return dragData;
        }
        return false;
    },

    onBeforeDrag: function(data, e){
        this.view.isDrag = true;
        return true;
    },
    onMouseUp: function(data, e){
        this.view.isDrag = false;
        return true;
    },
    getTreeNode: function(){
        var treeNodes = [];
        var nodeData = this.view.getRecords(this.dragData.nodes);
        for (var i = 0, len = nodeData.length; i < len; i++) {
            var data = nodeData[i].data;
            treeNodes.push(new Ext.tree.TreeNode({
                text: data.name,
                icon: '../view/' + data.url,
                data: data,
                leaf: true,
                cls: 'image-node'
            }));
        }
        return treeNodes;
    },
    afterRepair: function(){
        for (var i = 0, len = this.dragData.nodes.length; i < len; i++) {
            Ext.fly(this.dragData.nodes[i]).frame('#8db2e3', 1);
        }
        this.dragging = false;
    },


    getRepairXY: function(e){
        if (!this.dragData.multi) {
            var xy = Ext.Element.fly(this.dragData.ddel).getXY();
            xy[0] += 3;
            xy[1] += 3;
            return xy;
        }
        return false;
    }
});


