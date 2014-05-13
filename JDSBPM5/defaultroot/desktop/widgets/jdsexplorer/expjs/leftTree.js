
Ext.namespace('JDS.exp.tree');

/**
 * @class Explorer.destinationTreePanel
 * @param {Boolean} withView true to show all TreeNodes,such as My Share,Search
 */
JDS.exp.tree.destinationTreePanel = function(config){
    this.withView = !!(config.withView);
    Ext.apply(this, config ||
    {}, {
        region: 'west',
        split: true,
        ddGroup: 'organizerDD',
        enableDD: true,
        width: this.withView ? 200 : '95%',
        height: this.withView ? '100%' : 400,
        minSize: 175,
        maxSize: 500,
        collapsible: true,
        margins: '0 0 2 0',
        cmargins: '0 0 0 0',
        rootVisible: true,
        lines: true,
        autoScroll: this.withView,
        animCollapse: false,
        animate: false,
		
        style: 'text-align:left;margin-left:5px;',
        collapseMode: 'mini',
        collapseFirst: false,
		iconCls	:'!important;',
        loader: new Ext.tree.TreeLoader({
            dataUrl: '/explorer/toJsonForTree.action',
            preloadChildren: true,
            clearOnLoad: false
        }),
        root: this.RootNode()

    });


    JDS.exp.tree.destinationTreePanel.superclass.constructor.call(this);
    this.on('beforeload', this.beforeload, this);

    this.on('click', this.beforeclick, this);

    this.on('beforenodedrop', this.beforeNodeDrop);
    this.on('nodedrop', this.nodeDrop);

    this.on("contextmenu", function(node, e) {
      node.select();
      var menuType = node.attributes["menu"];
      var callMenu = JDS.exp.getRightMenu(this.win, node, menuType,"tree");
      callMenu.showAt(e.getXY());
      e.stopEvent();
    });
};
Ext.extend(JDS.exp.tree.destinationTreePanel, Ext.tree.TreePanel, {
    RootNode: function(){
        var root = new Ext.tree.AsyncTreeNode({
            text: '资源管理目录',
            expanded: true,
            draggable: false,
		 
            allowDrop: false,
            id: 'root'
        });

        return root;
    },
    beforeNodeDrop: function(e){
        var tChilds = e.target.childNodes;

        if (e.data.nodes != null) {
            for (var i = 0; i < e.data.nodes.length; i++) {
                var id = e.data.ids[i];
                var ids = e.data.ids[i].split('\\');
                var tid = e.target.id;
                var tids = e.target.id.split('\\');
                var type = e.data.types[i];
                //1.被移动目录不能和目录不能和源目录一样
                if (ids[ids.length - 2] == tids[tids.length - 1]) {
                    alert('无法移动 ' + ids[ids.length - 1] + ' 源文件与目标文件相同!');
                    e.rawEvent.stopEvent();
                    return false;
                }
                //2.被移动目录不能移动到自己目录下
                if (id == tid) {
                    alert('被移动目录与目标目录相同');
                    e.rawEvent.stopEvent();
                    return false;
                }
                //3.被移动目录不能移动到自己的子目录,并且该结点不是文件
                if (tids.length > ids.length && type) {
                    for (var i = 0; i < ids.length; i++) {
                        if (tids[i] != ids[i]) {
                            break;
                        }
                    }
                    alert('被移动目录不能移动到它子结点下');
                    e.rawEvent.stopEvent();
                    return false;
                }
                //判断重名
                if (!this.checkSaveName(ids, tChilds)) {
                    e.rawEvent.stopEvent();
                    return false;
                }
            }

        }
        else {
            var id = e.dropNode.id;
            var ids = e.dropNode.id.split('\\');
            var tid = e.target.id;
            var tids = e.target.id.split('\\');
            if (ids[ids.length - 2] == tids[tids.length - 1]) {
                //1.被移动目录不能和目录不能和源目录一样
                alert('无法移动 ' + ids[ids.length - 1] + ' 源文件与目标文件相同!');
                e.rawEvent.stopEvent();
                return false;
            }
            //判断重名
            if (!this.checkSaveName(ids, tChilds)) {
                e.rawEvent.stopEvent();
                return false;
            }
        }

    },
    checkSaveName: function(ids, tChilds){
        for (var i = 0; i < tChilds.length; i++) {
            var tmp = tChilds[i].id.split('\\');
            if (ids[ids.length - 1] == tmp[tmp.length - 1]) {
                alert('移动的文件与目标文件夹下的文件重名');
                return false;
            }
        }
		return true;
    },
    nodeDrop: function(e){
        if (e.data.nodes != null) {

            var ids = [];//被拖动文件或文件夹的ID列表
            var pids = [];
            e.data.ids.each(function(item){
                ids.push(item);
            });
            e.data.pids.each(function(item){
                pids.push(item)
            });
            var leftParent;
            var targetNode;
            //被拖动文件或文件在树上的NODE对象，如果是null说明拖动的是文件，则不在刷新左侧树，只刷新当前目录
            var tmpNode = this.getNodeById(e.data.ids[0]);
            //if(tmpNode == null){//拖动的是文件
            //	leftParent = null;
            //	targetNode = e.target;
            //}else{//拖动的是文件夹
            //	leftParent = this.getNodeById(e.data.ids[0]).parentNode;
            //	targetNode = e.target;
            //}
            leftParent = this.getNodeById(pids[0]);
            targetNode = e.target;
            JDS.ajax.exp.Connection.Folder4tree(ids,leftParent,targetNode,this.win.getCurrentPath());
        }
        else {

            JDS.ajax.exp.Connection.moveto(e.dropNode,e.dropNode.parentNode,e.target,this.win.getCurrentPath());
        }

    },
    /**
     * @this {Ext.Tree}
     */
    beforeload: function(node){
        switch (node.id) {
            //			特殊的目录
            case "myOwnSpace":
                this.loader.baseParams = {
                    dir: node.attributes.cid,
					personId: personid
                };

                break;
            case "myDocument":
                this.loader.baseParams = {
                    dir: node.attributes.cid,
					personId: personid
                };

                break;
            //主要目录
            default:
                this.loader.baseParams = {
                    dir: node.attributes.id,
					personId: personid
                };

        }
    },

    beforeclick: function(node, e){
        var specialFolder = {
            TreeNode_Public: true,
            TreeNode_myOwnSpace: true,
            TreeNode_myShare: true,
            TreeNode_allShare: true,
            TreeNode_Bookmark: true,
            TreeNode_Search: false,
            TreeNode_Recycle: true
        };
      if (!node.isLeaf()) {
        //            node.toggle();
        if (!node.isExpanded()) {
          node.expand();
        }
      }
      ;

      var tree = node.getOwnerTree();
      var win = tree.win;
      if (this.withView) {
        JDS.exp.actions.exec("changeCurrentPath", [win,node.attributes.id])
      }
//        if (specialFolder[node.id.toString()]) {
      ////            Explorer.Manager.currentPath = node.attributes.id;
      //          JDS.setExpFlag(win, "currentPath",node.attributes.id);
      //        };
    }

  ,
  //直接展开指定的字节点
  expandTo:function(path) {
    var ps = path.split("\\");
    var p = "";
    for (var i = 0; i < ps.length; i++) {
      if (ps[i]) {
        p += "\\" + ps[i];
      }
      var n = this.getNodeById(p);
      if (n){
        n.expand();
      }
    }
    this.getNodeById(path).select();
  },

  //得到指定id的节点对应的text
  getTextById:function(id){
    var rtn="";
    var ps = id.split("\\");
    var p = "";
    for (var i = 0; i < ps.length; i++) {
      if (ps[i]) {
        p += "\\" + ps[i];
      }
      var n = this.getNodeById(p);
      if (n){
        rtn+="\\"+n.text;
      }
    }
    return rtn||"我的应用";
  }
});




