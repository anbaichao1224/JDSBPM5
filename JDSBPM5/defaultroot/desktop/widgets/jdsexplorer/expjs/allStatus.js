//所有的状态相关

Ext.namespace("JDS.exp.status");//全局状态相关
Ext.namespace( "Exp.exps");//所有打开的 exp对象



JDS.exp.status.flags={};//所有全局 状态变量
JDS.exp.Clipboard={};//剪贴板

//激活窗口时,初始化工具条按钮状态
JDS.exp.status.initToolbarStatus=function(bar){
  if(JDS.exp.canPaste()){
    JDS.exp.enableToolbarBtn(bar,"btnFile_paste");
  }else{
    JDS.exp.disableToolbarBtn(bar,"btnFile_paste");
  }
}

//菜单显示前执行,现执行的是菜单的状态设置方法
JDS.exp.status.checkMenu=function(menu){
  var is=menu.items;
  for(var i=0;i<is.length;i++){
    JDS.exp.status.checkMenuItem(is.get(i));
  }
}

//执行菜单项的状态设置方法
JDS.exp.status.checkMenuItem=function (item){
  var txt=item.text;
  if (txt) {  //如果不是分隔符
    var key = item["statusFnName"] || item.id
    if (key) {
      var sFn = JDS.exp.mainMenu.statusFn[key];
      if (sFn) {
//        sFn.apply(item, []);

        if(sFn.apply(item,[])===false){
          item.disable();
        }else{
          item.enable();
        }
      }
    }
  }
}

JDS.exp.status.setAllFlag=function(key,value){
  JDS.exp.status.flags[key]=value;
}
JDS.exp.status.getAllFlag=function(key,defaultValue){
  var v=JDS.exp.status.flags[key];
  if(v==undefined){
    v=defaultValue;
  }
  return v;
}

JDS.exp.status.setExpFlag=function(obj,key,value){
  var o=obj;
  if(typeof o!="object"){
    o=Exp.exps[o];
  }
  var flags=o.flags;
  if(!flags){
    o.flags = {};
  }
  o.flags[key]=value;
}
JDS.exp.status.getExpFlag=function(obj,key,defaultValue){
  var o=obj;
  if(typeof o!="object"){
    o=Exp.exps[o];
  }
  var v=(o.flags?o.flags[key]:undefined);
  if(v==undefined){
    v=defaultValue;
  }
  return v;
}

//设置所有exp相关的全局 状态值
function JDS.setAllFlag(key,value){
  JDS.exp.status.setAllFlag(key,value);
}

//获取exp相关的全局 状态值
function JDS.getAllFlag(key,defaultValue){
  return JDS.exp.status.getAllFlag(key,defaultValue);
}

//设置窗口范围的状态值 obj为窗口对象或窗口的id
function JDS.setExpFlag(obj,key,value){
  JDS.exp.status.setExpFlag(obj,key,value);
}

//获取窗口范围的状态值 obj为窗口对象或窗口的id
function JDS.getExpFlag(obj,key,defaultValue){
  return JDS.exp.status.getExpFlag(obj,key, defaultValue);
}

//删除全局状态
function JDS.removeAllFlag(key){
  try{
    delete JDS.exp.status.flags[key];
  }catch(e){
    JDS.exp.status.flags[key]=undefined;
  }
}

//取当前选中对象
JDS.exp.getCurrentObj=function(win){
  if(!win){
    win=JDS.exp.getCurrentWin();
  }
  return JDS.getExpFlag(win,"currentSelected");
}

JDS.exp.setCurrentObj=function(win,obj){
  JDS.setAllFlag("currentWin",win);
  JDS.setExpFlag(win,"currentSelected",obj);

  var bar=JDS.exp.getCurrentWin().toolbar;
  if(JDS.exp.hasSelectedObj()){
    JDS.exp.enableToolbarBtn(bar,"btnFile_cut");
    JDS.exp.enableToolbarBtn(bar,"btnFile_copy");
    JDS.exp.enableToolbarBtn(bar,"btnFile_copyto");
    JDS.exp.enableToolbarBtn(bar,"btn_clearSelected");
    JDS.exp.enableToolbarBtn(bar,"btnFile_attrib");
    JDS.exp.enableToolbarBtn(bar,"btnFile_delete");
    JDS.exp.enableToolbarBtn(bar,"btnFile_moveto");
  }else{
    JDS.exp.disableToolbarBtn(bar,"btnFile_cut");
    JDS.exp.disableToolbarBtn(bar,"btnFile_copy");
    JDS.exp.disableToolbarBtn(bar,"btnFile_copyto");
    JDS.exp.disableToolbarBtn(bar,"btn_clearSelected");
    JDS.exp.disableToolbarBtn(bar,"btnFile_attrib");
    JDS.exp.disableToolbarBtn(bar,"btnFile_delete");
    JDS.exp.disableToolbarBtn(bar,"btnFile_moveto");
  }
}

JDS.exp.hasSelectedObj=function(){
  var o=JDS.exp.getCurrentObj();
  var rtn=false;
  if(!o||o.length==0){
    rtn=false;
  }else{
    rtn=true;
  }
  return rtn;
}
//取当前活动窗口
JDS.exp.getCurrentWin=function(){
  return JDS.getAllFlag("currentWin");
}

JDS.exp.setCurrentWin=function(win){
  JDS.setAllFlag("currentWin",win);
}

//窗口关闭时的处理
JDS.exp.closeHandler=function(win){
  delete Exp.exps[win.exp.getId()];
}


JDS.exp.disableToolbarBtn=function(bar,id){
  var items=bar.items;
  for(var i=0;i<items.length;i++){
    var b = items.get(i);
    if (b.id == id) {
      b.disable();
    }
  }
}
JDS.exp.enableToolbarBtn=function(bar,id){
  var items=bar.items;
  for(var i=0;i<items.length;i++){
    var b = items.get(i);
    if (b.id == id) {
      b.enable();
    }
  }
}

//刷新当前窗口
JDS.exp.refreshCurrentWin=function(flag){
  var win=JDS.exp.getCurrentWin();
  if(win){
    win.refresh(flag);
  }
}

//刷新所有窗口
JDS.exp.refreshAllWin=function(path,flag){
  for(var k in Exp.exps){
    var w=Exp.exps[k];
    if(w){
      w.refreshPath(path,flag);
    }
  }
}

//剪贴板
JDS.exp.setClip=function (val){
  JDS.exp.Clipboard.value = val;

  var bar=JDS.exp.getCurrentWin().toolbar;
  if(JDS.exp.canPaste()){
    JDS.exp.enableToolbarBtn(bar,"btnFile_paste");
  }else{
    JDS.exp.disableToolbarBtn(bar,"btnFile_paste");
  }
}
JDS.exp.getClip=function (){
  return JDS.exp.Clipboard.value||null;
}

//清空剪贴板
JDS.exp.clearClip=function(){
  delete JDS.exp.Clipboard.value;
}

JDS.exp.canPaste=function(){
  return JDS.exp.Clipboard.value?true:false;
}

//取得选择目标路径对话框
JDS.exp.getChooser = function(cfg, newFlag) {
  var w = JDS.getAllFlag("Chooser");
  if (!w || newFlag) {
    w = new JDS.exp.destinationChooser(cfg);
    JDS.setAllFlag("Chooser", w);
  }
  return w;
},

  /*---------------------------------------------*/
Ext.namespace("JDS.exp.mainMenu");
//菜单显示前的状态处理函数
JDS.exp.mainMenu.statusFn = {
  open:function(){
      var pM=this.parentMenu;
      while(pM.parentMenu){
        pM=pM.parentMenu;
      }
      if(pM.obj&&pM.obj.isSelected){
        if(pM.obj.isSelected()){
          return true;
        }
      }

      if(JDS.exp.getCurrentObj()&&JDS.exp.getCurrentObj().length){
        return true;
      }else{
        return false;
      }
    },
  copy:function() {
    if (JDS.exp.hasSelectedObj()) {
      return true;
    } else {
      return false;
    }
  },
  paste:function(){
      if(JDS.exp.canPaste()){
        return true;
      }else{
        return false;
      }
  },
  goBack:function(){
    return (JDS.exp.getCurrentWin().history.hasHistory());
  },
  goForward:function(){
    return (JDS.exp.getCurrentWin().history.hasForward());
  },
  goParentFolder:function(){
    var win=JDS.exp.getCurrentWin();
    return !win.isRoot(win.getCurrentPath());
  }
}