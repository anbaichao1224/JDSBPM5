Ext.namespace("Exp", "Exp.exps");
Ext.namespace("JDS.exp");

JDS.exp.openExp = function (cfg, id) {
  var exp = null;
  id=cfg.id||id;
  if (id) {
    exp = Exp.exps[id];
  }
  if (exp) {
    exp.show();
  } else {
    exp = new JDS.exp.Explorer(cfg);
    Exp.exps[exp.getId()]=exp;
  }
}

//资源管理器类
JDS.exp.Explorer = function(cfg) {
  this.cfg = cfg;
  this.extId ="win_"+ Ext.id();
  this.id=cfg.id||this.extId;
  this.boxId=this.extId+"_north";
  this.mainAreaId=this.extId+"-itemsArea";
  this.flags={};
  this.win;
  this.mainMenu;
  this.toolbar;
  this.URIbar;
  this.rightMenus = {};
  this.tree;
  this.mainPanel;
  this.html = new String(Ext.fly('north_area').dom.innerHTML);
  this.history;
  this.init();
  this.openWin();
}
JDS.exp.Explorer.prototype.getId = function() {
  return this.extId;
}
JDS.exp.Explorer.prototype.goBack = function() {
  this.history.go(-1);
}
JDS.exp.Explorer.prototype.goForward = function() {
  this.history.go(1);
}
JDS.exp.Explorer.prototype.show = function() {
  this.win.show();
};
JDS.exp.Explorer.prototype.close = function() {
  this.win.close();
};
JDS.exp.Explorer.prototype.init = function() {
  Ext.fly('explorer').insertHtml('beforeEnd', "<div id='" + this.boxId + "'>" + this.html + "</div><div id=" + this.mainAreaId + "></div><div id='" + this.extId + "-loading_mask'></div>");

  this.history=new JDS.exp.History(this);

  var el = Ext.fly(this.boxId);
  var menu = JDS.exp.mainMenu.initMenu(el.child('.menuEl'));
  menu.win = this;
  this.mainMenu = menu;
  var tb = JDS.exp.toolbar.initToolbar(el.child('.toolbarEl'));
  tb.win = this;
  this.toolbar = tb;
  var utb = JDS.exp.toolbar.initUriToolbar(el.child('.left_buttonsEl'));
  utb.win = this;
  this.URIbar = utb;
  var tree = new JDS.exp.tree.destinationTreePanel({withView:true});
  tree.win = this;
  this.tree = tree;

  var mainPanel = new JDS.exp.mainPanel.MainPanel(this);
  //mainPanel.win = this;
  this.mainPanel = mainPanel;

  this.state = {}
  this.state.restore = this.state.update = function() {
  }
}

JDS.exp.Explorer.prototype.openWin = function() {
  var cfg = this.cfg;
  Ext.apply(cfg, {
    closable: true,
    plain: true,
    layout: 'border',
    title:cfg.module.text,
    items : [
      this.tree
      , this.state
      ,
      new Ext.BoxComponent({ // raw
        region : 'north',
        style:"background-color:white",
        el :  this.boxId,
        height : 65
      })
      ,
      new Ext.Panel({
        region : 'center',
        border : true,
        layout : 'column',
        height : 800,
        items : [
          this.mainPanel.itemsMainPanel
        ]
      })
    ]
  })
  this.win = getCurDesktop().createWindow(cfg);
  this.win.on("activate",function(w){
    JDS.exp.setCurrentWin(w.exp);
    JDS.exp.status.initToolbarStatus(w.exp.toolbar);
  });
  this.win.on("deactivate",function(w){
    var ww=JDS.exp.getCurrentWin();
    if(ww==w.exp){
      JDS.exp.setCurrentWin(null);
    }
  });
  this.win.on("close",JDS.exp.closeHandler);
  this.win.on("beforeclose",this.beforecloseHandler);
  this.win.exp=this;
  this.win.show();

  var maps=JDS.exp.keyMap.bindWinKey(this.win);
  this.win.keyMap = maps;

  var root = this.tree.getRootNode();
  var currentPath=cfg.currentPath||root.id;
//  JDS.exp.actions.exec("changeCurrentPath", [this,currentPath]);
  this.openPath(currentPath);
}

/*------------------------------------------------------------------------------------*/

JDS.exp.Explorer.prototype.beforecloseHandler=function(){
  this.keyMap.disable();
}

//打开指定的路径  historyFlag为 true时,不记录到历史列表
JDS.exp.Explorer.prototype.openPath = function (path, currentView, historyFlag) {
  JDS.setExpFlag(this, "currentPath", path);
  if (currentView) {
    JDS.setExpFlag(this, "currentView", currentView);
  } else {
    currentView = JDS.getExpFlag(this, "currentView");
  }
  if(!currentView){
    currentView="icon";
    JDS.setExpFlag(this,"currentView",currentView);
  }
      //alert(_currentPath);
  this.state.update('<img width="23" style="margin-top : 2px;" ' +
                   ' src="/"/> 寻找项目中...');
  var currentPath = path;
  var like = 'formTemp';
  var index = currentPath.indexOf(like);
  var strTmp = currentPath.substring(index + like.length + 1);
  var tmpPath = currentPath.replace("root", "\\");
  var userId = personid;
//  if (userId) {
//    var tmpUserName = currUserName;// = getUserNameById(userId);
//    tmpPath = tmpPath.replace(userId, tmpUserName);
//  }
  tmpPath=this.tree.getTextById(tmpPath);
  this.URIbar.box.setRawValue(tmpPath);
    //var currentView="icon";
  switch (currentView) {
    case 'thumb' :
      this.mainPanel.view.changeView('thumb');
      break;
    case 'icon' :
      this.mainPanel.view.changeView('icon');
      break;
    case 'list' :
      this.mainPanel.view.changeView('list');
      break;
    case 'detail' :
      this.mainPanel.grid.changeView();
      break;
    default :
      this.mainPanel.view.changeView('icon');
      break;
  }

  this.mainPanel.ds.load({
    params : {
      dir : currentPath,
      personId: personid
    }

  });

    //同步树节点状态
  var node = this.tree.getNodeById(path);
  if (node) {
    this.tree.expandTo(path);//展开后才可以选择
    node.select();
  }

  if (historyFlag !== true) {
    this.history.add(path);
  }
  if(this.isRoot(path)){
    JDS.exp.disableToolbarBtn(this.toolbar,"btnParentFolder");
  }else{
    JDS.exp.enableToolbarBtn(this.toolbar,"btnParentFolder");
  }
}

//是否是根路径
JDS.exp.Explorer.prototype.isRoot=function(path){
  return path==this.tree.getRootNode().id;
}

//获取根路径
JDS.exp.Explorer.prototype.getRootPath=function(){
  return this.tree.getRootNode().id;
}

//向上一级
JDS.exp.Explorer.prototype.goParentFolder=function(){
  var p=this.getCurrentPath();
  if(!this.isRoot(p)){
    p=this.getParentPath(p);
    this.openPath(p);
  }
}

//取得上一级的路径,如果
JDS.exp.Explorer.prototype.getParentPath=function(path){
  var p= path.replace(/\\[^\\]+$/,"");
  if(p==""){
    p=this.getRootPath();
  }
  return p;
}

JDS.exp.Explorer.prototype.getNameByAllPath=function(path){
  return path.replace(/.*(\\[^\\]+)$/,"$1");
}

  // 刷新当前窗口
JDS.exp.Explorer.prototype.refresh=function(cleanHistory){
  var path=this.getCurrentPath();
  var flag=true;
  if(cleanHistory===true){
    this.history.clean();
    flag=false;
  }
  this.openPath(path,null,flag);


  var node=this.tree.getNodeById(path);
  if(node){
    node.reload();
  }
}

//刷新指定路径
JDS.exp.Explorer.prototype.refreshPath = function(path, cleanHistory) {
  if (path == this.getCurrentPath()) {
    var flag = true;
    if (cleanHistory === true) {
      this.history.clean();
      flag = false;
    }
    this.openPath(path, null, flag);
  }
  var node=this.tree.getNodeById(path);
  if(node){
    node.reload();
  }
}

JDS.exp.Explorer.prototype.getCurrentObj=function(){
  return JDS.getExpFlag(this,"currentSelected");
}
JDS.exp.Explorer.prototype.getCurrentPath=function(){
  return JDS.getExpFlag(this, "currentPath");
}

//当前路径不应该直接set
//JDS.exp.Explorer.prototype.setCurrentPath=function(path){
//  JDS.setExpFlag(this, "currentPath",path);
//}

//取得窗口全局变量
JDS.exp.Explorer.prototype.getFlag=function(key){
  return JDS.getExpFlag(this, key);
}

JDS.exp.Explorer.prototype.setFlag=function(key,value){
  JDS.setExpFlag(this, key,value);
}

//copy 指定目标到指定位置
JDS.exp.Explorer.prototype.copyTo=function(src,target){
  var msg="";
  if(!src instanceof Array){
    src=[src];
  }

  var dir = this.tree.getNodeById(target);
  var ns=dir.childNodes ;

  for(var i=0;i<src.length;i++){
    var source=src[i];
    if(source==target){
      msg="不能粘贴到自身";
    }else  if(target.indexOf(source)==0){
      msg="不能粘贴到子目录";
    }else if(this.getParentPath(source)==target){
      msg="已经在当前目录了";
    }
    if(msg){
      alert(msg);
      return false;
    }

    //判断有没有重名
    var name = this.getNameByAllPath(source);
    var flag=false;
    for(var j=0;j<ns.length;j++){
      var id=ns[j].id;
      var reg=new RegExp(name+"$");
      if(reg.test(id)){
        flag=true;
        break;
      }
    }
    if(flag){
      if(!confirm("有重名的文件,是否覆盖?")){
        return;
      }
      break;
    }
  }

  var fn=function(){
    JDS.exp.refreshAllWin(target);
  };
  JDS.ajax.exp.Connection.copyto(source,target,fn);
  //JDS.exp.refreshAllWin(target);
}
  //将剪切板中的内容粘贴到指定路径
JDS.exp.Explorer.prototype.pasteTo=function(path){
  var val=JDS.exp.getClip();
  if(JDS.getAllFlag("cut")===true){
    JDS.setAllFlag("cut",false);
    return this.moveTo(val,path);
  }else{
    return this.copyTo(val,path);
  }
}

//删除指定目标,noConfirm 是否提示删除
JDS.exp.Explorer.prototype.remove=function(path,noConfirm){
  if (noConfirm || confirm("真的要删除")) {
    //todo 在这加删除的代码
    JDS.ajax.exp.Connection.remove(path);

    var n = this.tree.getNodeById(path);
    if (n) {
      n.remove();
    }
    JDS.exp.refreshAllWin(this.getParentPath(path));
  }
}

//移动指定目标到指定位置
JDS.exp.Explorer.prototype.moveTo=function(source,target){
  alert("move")
  if(this.copyTo(source,target)!==false){
    this.remove(source,true);
  }
}


//取得当前路径到根目录的路径树
JDS.exp.Explorer.prototype.getDirTree=function (){
  var p=this.getCurrentPath();
//  var root=p.replace(/^(\\[^\\]+).*$/,"$1");
  var dirs=p.split("\\");
  //alert(dirs)
  var rootPaths=this.tree.getRootNode().childNodes;
  var rtn=[];
  for(var i=0;i<rootPaths.length;i++){
    var tmp={};
    tmp.id=rootPaths[i].id;
    tmp.text=rootPaths[i].text;
    rtn.push(tmp);

    if(tmp.id=="\\"+dirs[1]){
      var tmpPath="\\"+dirs[1];
      var tmpBlank="--";
      for(var j=2;j<dirs.length;j++){
        var t=tmpPath+"\\"+dirs[j];
        tmp={};
        tmp.id=t;
        var n=this.tree.getNodeById(t);
        if(n){
          tmp.text = tmpBlank + n.text;
          tmpBlank += "--";
          tmpPath = t;
          rtn.push(tmp);
        }
      }
    }
  }
  return rtn;
}

JDS.exp.Explorer.prototype.selectAll=function(){
  this.mainPanel.view.selectAllNodes();
}
JDS.exp.Explorer.prototype.clearSelected=function(){
  this.mainPanel.view.clearSelections();
}

//开始重命名
JDS.exp.Explorer.prototype.startRename=function(){
  var view=this.mainPanel.view;
  var node=view.getSelectedNodes()[0];
  if (node) {
    var el=new Ext.Element(node).child(".iconTitle", true);
    var editor=view.LabelEditor;
    editor.startEdit(el);
    var record=view.getRecord(node);
    editor.activeRecord = record;
  }
}

//保存重命名的值
JDS.exp.Explorer.prototype.saveName=function(data,name){
  //alert("save---"+data.path+"==="+name);
  JDS.ajax.exp.Connection.renameFile(data,name);
  //todo 
}

//按名字排序当前目录
JDS.exp.Explorer.prototype.sortByName=function(){
  var view=this.mainPanel.view;
  var store=view.store;
  var obj=store.getSortState();
  store.sort("name");
}
JDS.exp.Explorer.prototype.sortByType=function(){
  var view=this.mainPanel.view;
  var store=view.store;
  store.sort("menu");
}
JDS.exp.Explorer.prototype.sortBySize=function(){
  var view=this.mainPanel.view;
  var store=view.store;
  store.sort("size");
}
