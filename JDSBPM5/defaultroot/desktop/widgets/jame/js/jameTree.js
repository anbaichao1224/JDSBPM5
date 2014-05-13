//人员树
Ext.namespace("Jame.tree");

//某一组的人员列表
Jame.tree.getList = function(node,params) {
  var url = Jame.backend.url.allTree;
  var type="list";
  var tree = new Ext.tree.TreePanel({
    enableDD:false,
    animate:true,
    rootVisible:false,
    lines:false,
    //      ddGroup: "buddys",
    border:false,
    useArrows: true,
    // auto create TreeLoader
    dataUrl: url,
    type:type,
    autoScroll:true,
    root: {
      nodeType: 'async',
      text: node.text,
      draggable: false,
      id:node.id
    }
  });

//  tree.scType = type;//树是系统组还是自定义组

  var tmp = {};
  if (params) {
    tmp = Ext.apply(tmp, params);
  }
  tmp.type = type;
  tree.on('beforeload', function(node) {
    //      tree.loader.dataUrl = url + '&childOrgId=' + node.id+"&type="+type;

    tmp.childOrgId = node.id;
    tree.loader.dataUrl = url;
    tree.loader.requestMethod = "POST";
    tree.loader.baseParams = tmp;
  });

  tree.on("contextmenu", function(node, e) {
    var menu;
    node.select();
    var t = node.getOwnerTree();
    menu = Jame.menu.getListPersonMenu(personid);
    menu.showAt(e.getXY());
    menu.node = node;
    e.stopPropagation();
    return false;
  });
  return tree;
}


//人员树
Jame.tree.getTree = function(personid, type, params) {
  //是否是选择人员时的树
  var isSelectTree = params && params.canCheck == "true";

  var url = Jame.backend.url.allTree;
  var tree = new Ext.tree.TreePanel({
   // enableDD:type=="cust",
    animate:true,
    rootVisible:false,
    lines:false,
    //ddGroup: "buddys",
    border:false,
    useArrows: true,
    // auto create TreeLoader
    dataUrl: url,
    type:type,
    split:false,
    autoScroll:true,

//    containerScroll :true,
//width:200,
//    height:200,
    root: {
      nodeType: 'async',
      text: 'root',
      draggable: false,

      id:"root_"+type + personid
      //        id:"toproot"
    }
  });

  if (type == "cust") {
    new Ext.tree.TreeSorter(tree, {
      folderSort: true,
      //        dir: "desc",
      sortType: function(node) {
        if (node.attributes.type == "SYSTEM")return '\0';
        return node.text;
      }
    });
  }

  tree.scType = type;//树是系统组还是自定义组

  var tmp = {};
  if (params) {
    tmp = Ext.apply(tmp, params);
  }
  tmp.type = type;
  tree.on('beforeload', function(node) {
    //      tree.loader.dataUrl = url + '&childOrgId=' + node.id+"&type="+type;
    tmp.childOrgId = node.id;
    tree.loader.dataUrl = url;
    tree.loader.requestMethod = "POST";
    tree.loader.baseParams = tmp;
  });
//    tree.on("render",function(){
  //      tree.expand();
  //    });

  if (!isSelectTree||type=="search") {
    if(type!="his"){
    tree.on("contextmenu", function(node, e) {
      var menu;
      node.select();
      var t = node.getOwnerTree();
//      alert(t.jt.isGroup(node))
      if (t.jt.isGroup(node)) {
	  	
        if (t.scType == "sys") {
          menu = Jame.menu.getSysGroupMenu(personid + "group");
        } else if (t.scType == "cust"){
          menu = Jame.menu.getCustGroupMenu(personid + "group");
        } else if (t.scType == "office"){
          menu = Jame.menu.getSysGroupMenu(personid + "group");
        }
        menu.showAt(e.getXY());
      } else {
        if (t.scType == "sys"||t.scType=="cust2") {
          menu = Jame.menu.getSysPersonMenu(personid + "person");
        } else if (t.scType == "cust"){
          menu = Jame.menu.getCustPersonMenu(personid + "person");
        }else if (t.scType == "search"){
          menu = Jame.menu.getSearchPersonMenu(personid + "person");
        } else if (t.scType == "office"){
          menu = Jame.menu.getSysPersonMenu(personid + "group");
        }
        menu.showAt(e.getXY());
      }
      menu.node = node;
      e.stopPropagation();
      return false;
    });
    }

    if(type=="his"){
      tree.on("dblclick", function(node, e) {        
        var t = node.getOwnerTree();
        if (!t.jt.isGroup(node)) {
        getMainWin().showChatHistory(node.id);
        }
      });
    }else{
      tree.on("dblclick", function(node, e) {
        var t = node.getOwnerTree();
        if (!t.jt.isGroup(node)) {
          getMainWin().showChatWin(node);
        }
      });
    }
    tree.on("beforemovenode", function(t, node, oldParent, newParent, index) {
      if (t.scType != "sys") {
        var rId = t.getRootNode().id;
        if(node.parentNode.id==rId){
          return false;
        }
        var pId = newParent.id;
        if (rId == pId) {
          alert("人员必须属于某个分组");
          return false;
        }
        return true;
      } else {
        return false;
      }
    });
    tree.on("movenode", function(t, node, oldParent, newParent, index) {
      Jame.backend.Connection.switchUserGroup(node.id,newParent.id, oldParent.id);
    });
  }
  return tree;
}

Jame.tree.JameTree = function(personid, type, parent, params) {
  this.tree = Jame.tree.getTree(personid, type, params);
  this.tree.jt = this;
  parent.add(this.tree);
  this.tree.parentPanel=parent;

  this.tree.doLayout();
  parent.doLayout();
}

Jame.tree.JameTree.prototype.refresh = function(node) {
  var tree=this.tree;
  tree.getLoader().load(this.tree.getRootNode());
  setTimeout(function(){tree.expandAll();},1000);
}

  //判断节点是否是分组
Jame.tree.JameTree.prototype.isGroup = function(node) {
  //临时判断用 todo
  return node.leaf == false;

//  var type = node.attributes.type;
  //      if (type == "group"){
  //        return true;
  //      }
  //  return false;
}
  //获取人员树中的分组名和id,因系统组不允许更改,所以只有自定义组才能用到
Jame.tree.JameTree.prototype.getRootGroups = function() {
  var rtn = [];
  var cs = this.tree.root.childNodes;
  for (var i = 0; i < cs.length; i++) {
//    var tmp = {};
//    tmp.id = cs[i].id;
//    tmp.text = cs[i].text;
//    rtn.push(tmp);
    rtn.push(cs[i]);
  }
  return rtn;
}

//根据id取分组,自定义组用
Jame.tree.JameTree.prototype.getGroupById = function(id) {
  var cs = this.tree.root.childNodes;
  for (var i = 0; i < cs.length; i++) {
    if (cs[i].id == id) {
      return cs[i];
    }
  }
  return null;
}


//根据name取分组,自定义组用
Jame.tree.JameTree.prototype.getGroupByName = function(name) {
  var cs = this.tree.root.childNodes;
  for (var i = 0; i < cs.length; i++) {
    if (cs[i].text == text) {
      return cs[i];
    }
  }
  return null;
}

//根据名字查找人员 ,只能查找已展开的节点中的人员
//tree.root.childNodes[0].expand()
Jame.tree.JameTree.prototype.findByName = function(name) {
  var rtn = [];
  var pat = new RegExp("^.*" + name + ".*$", "i");

  var cs = this.tree.root.childNodes;
  for (var i = 0,n = cs.length; i < n; i++) {
    var cc = cs[i].childNodes;
    for (var j = 0; j < cc.length; j++) {
      var str = cc[j].text;
      alert(cc.length + "==" + cs.length + "==" + i)
      if (pat.test(str)) {
        rtn.push(cc[j]);
      }
    }
  }
  return rtn;
}


//加新组,自定义组用
Jame.tree.JameTree.prototype.addGroup = function() {
  var id="group_" + Ext.id();
  var group = new Ext.tree.TreeNode({ //group adden
    text:"新组",
    iconCls:"display:none!important;",
    expanded:false,
    //    type:"custGroup",
    id:id,
    expandable:true,
    allowDrag:false,
    leaf:false
  });
  this.tree.root.appendChild(group);
  this.renameGroup(group,true);
  return group;
}

//删除组或人员
//type为 group或 persion
Jame.tree.JameTree.prototype.removeNode = function(node) {
  //
}

//当前组是否已经有某人了
Jame.tree.JameTree.prototype.containsPerson = function(p) {
  if (typeof(p) != 'string') {
    p = p.id;
  }
  var node = this.tree.getNodeById(p);
  return node ? true : false;
}

//重命名组,自定义组用到
Jame.tree.JameTree.prototype.renameGroup = function(node,isNew) {
  var treeEditor = new Ext.tree.TreeEditor(node.getOwnerTree(), {
    allowBlank:false,
    blankText:'新组',
    selectOnFocus:true
  });
 
	treeEditor.on("complete",function(oldname,newname){
      if(isNew){
        Jame.backend.Connection.addGroup(node,newname);
      }else{
        Jame.backend.Connection.renameGroup(node,newname);
      }
    },this);
	
   treeEditor.completeEdit();
		if(node.attributes.editable !== false){
			treeEditor.editNode = node;
			treeEditor.startEdit(node.ui.textNode, node.text);
        }
 
  
}

//人员添加到指定id的组 原人员不动,用于从系统组加到自定义组 及 选择添加到组的人员等
Jame.tree.JameTree.prototype.addToID = function(node, parentId, msg) {
  var p=this.tree.getNodeById(parentId);
  if(this.addToNode(node,p,msg)){
    Jame.backend.Connection.addBuddy(node.id,parentId);
  }
}
Jame.tree.JameTree.prototype.addToNode = function(node, parent, msg) {
  if (this.containsPerson(node)) {
    if (msg) {
      alert("已经存在此人员:" + node.text);
    }
    return false;
  }
  var cfg = {};
  cfg.id = node.id;
  cfg.text = node.text;
  cfg.icon = node.icon;
  cfg.qtip = node.qtip;
  Ext.apply(cfg, node.attributes);
  cfg.checked = undefined;
  
  var n = new Ext.tree.TreeNode(cfg);

  this.addToGroup(n, parent);
  return true;
}

Jame.tree.JameTree.prototype.addAllToID = function(nodes, id) {
  var tmp = [];
  var ids=[];
  var np = this.tree.getNodeById(id);
  for (var i = 0; i < nodes.length; i++) {
    if (!this.addToNode(nodes[i], np)) {
      tmp.push(nodes[i].text);
    }else{
      ids.push(nodes[i].id);
    }
  }

  if(ids.length>0){
    Jame.backend.Connection.addBuddy(ids.join(","),id);
  }
  if (tmp.length > 0) {
    alert("下列人员已存在:\n" + tmp.join(","));
  }
}

//人员移动到 指定id的组
Jame.tree.JameTree.prototype.moveToID = function(node, id) {
  var np = this.tree.getNodeById(id);
  //todo
  this.addToGroup(node, np);
}

//人员移动到指定组,此方法只做前台处理,不应在此加提交到后台的代码
Jame.tree.JameTree.prototype.addToGroup = function(node, newParent) {
  newParent.appendChild(node);
}

//改变好友的当前显示状态
Jame.tree.JameTree.prototype.changePersonStatus = function(id, status) {
  var node = this.tree.getNodeById(id);
  if (node) {
    status=status||node.attributes.oldStatus;
    if (status) {
      node.attributes.oldStatus = node.attributes.status;
      node.attributes.status = status;
      var ui = node.getUI();
      var path = Jame.menu.statusImg[status];
      if (path) {
        ui.getIconEl().src = path;
      }
    }
  }
  //todo 加上下线提示等
}

//取默认组 默认组不能删除
Jame.tree.JameTree.prototype.getDefaultGroup = function() {
  var cs = this.tree.root.childNodes;
  for (var i = 0; i < cs.length; i++) {
    if (cs[i].attributes.type == "SYSTEM") {
      return cs[i];
    }
  }
  return null;
}

Jame.tree.JameTree.prototype.removePerson = function(node) {
  Jame.backend.Connection.removeBuddy(node);
}
Jame.tree.JameTree.prototype.removeGroup = function(node) {
  Jame.backend.Connection.removeGroup(node);

//  if (confirm("确认要删除此组吗?")) {
//    var n = this.getDefaultGroup();
//    var ns = node.childNodes;
//    if (n && ns) {
//      for (var i = 0,c = ns.length; i < c; i++) {
//        n.appendChild(ns[0]);
//      }
//    }
//    this.removeNode(node);
    //删除组时, 把其原所属人员 移到默认组 todo 更改删除等信息发送到后台
//  }
}

//目前只有自定义组用到
Jame.tree.JameTree.prototype.getPersonByStatus=function(status){
  var ns=this.getRootGroups();
  var rtn=[];
  for(var i=0;i<ns.length;i++){
    var ps=ns[i].childNodes;
    for(var j=0;j<ps.length;j++){
      if(ps[j].attributes.status ==status){
        rtn.push(ps[j]);
      }
    }
  }
  return rtn;
}

Jame.tree.JameTree.prototype.getEmptyGroup=function(){
  var ns=this.getRootGroups();
  var rtn=[];
  for(var i=0;i<ns.length;i++){
    if(!ns[i].childNodes||!ns[i].childNodes.length){
      rtn.push(ns[i]);
    }
  }
  return rtn;
}
