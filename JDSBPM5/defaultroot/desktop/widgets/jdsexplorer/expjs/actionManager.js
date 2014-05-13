//所有的执行动作

Ext.namespace("JDS.exp.actions");

  //执行所有动作的统一调用
JDS.exp.actions.exec = function(name, args) {

  if (name) {
    var fn = JDS.exp.actions.fns[name];
    if (fn) {

      if(!args){
        args=[];
      }
      if (!args[0]) {
        args = [args];
      }
      var currentObj = JDS.exp.getCurrentObj();
      var currentWin = JDS.exp.getCurrentWin();
      //alert(currentWin)
      var curThis = [];
      var eType="";
      var pM=null;
      if(args[0]){
        pM = args[0].parentMenu;
        while (pM && pM.parentMenu) {
          pM = pM.parentMenu;
        }
        if(pM){
          eType=pM.eType;
        }
      }
      if(eType == "tree") {
        var tmp = new JDS.exp.ActionData();
        tmp.win = currentWin;
        tmp.path = pM.obj.attributes.id;
        tmp.node=pM.obj;
        tmp.eType="tree";
        curThis.push(tmp);
      } else {
        if (currentObj && currentObj.length) { //当前有选中的对象
          for (var j = 0; j < currentObj.length; j++) {
            var tmp = new JDS.exp.ActionData();
            tmp.win = currentWin;
            tmp.path = currentObj[j].data.path;
            tmp.data=currentObj[j].data;
            tmp.eType="obj";
            curThis.push(tmp);
          }
        }
      }
      if (curThis.length == 0) {
        var tmp = new JDS.exp.ActionData();
        tmp.win = currentWin;
        if(currentWin) {
          var path = JDS.getExpFlag(currentWin, "currentPath");
          var node = currentWin.tree.getNodeById(path);
          tmp.pPath = node.attributes.id;
          tmp.path = "";
          tmp.node = node;
        }
        tmp.eType = "blank"
        curThis.push(tmp);
      }
        //以当前选中对象相关数据生成的新对象数组 为被调用函数的this
      var val = fn.apply(curThis, args);
      var afterFn = JDS.exp.actions.afterFns[name];
      if (afterFn) {
        for (var i = 0; i < afterFn.length; i++) {
          try {
            afterFn[i].call(curThis, val)
          } catch(e) {
            alert(e);
          }
        }//for
      }//if(afterFn)
    }//if(fn)
  }//if(name)
}

  //添加指定动作的after fn
JDS.exp.actions.addAfterFn = function(name, fn) {
  var fns = JDS.exp.actions.afterFns[name];
  if (!fns) {
    JDS.exp.actions.afterFns[name] = [];
  }
  JDS.exp.actions.afterFns[name].push(fn);
}



//所有action的this对象
JDS.exp.ActionData = function () {
  this.win;  //当前对应的窗口对象
  this.path; //当前对应的选中对象路径,面板中的选中对象或树的选中节点
  this.id;   //id
  this.value;
  this.pPath;//当右键空白处时对应的节点路径
  this.data;
}
