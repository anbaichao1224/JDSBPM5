//窗口快捷键
Ext.namespace("JDS.exp.keyMap");


JDS.exp.keyMap.bindWinKey = function (win) {
  var keys = [
    {
    key: "c",
    ctrl:true,
    fn: function() {
      JDS.exp.actions.exec("copy");
    }
  }, {
    key: "v",
    ctrl:true,
    fn: function() {

      JDS.exp.actions.exec("paste");
    }
  },{
    key: "x",
    ctrl:true,
    fn: function() {
      JDS.exp.actions.exec("cut");
    }
  },{
    key: "a",
    ctrl:true,
    fn: function() {
      JDS.exp.actions.exec("selectAll");
    }
  }
  ];
  var map = new Ext.KeyMap(win.getEl(), keys);
  return map;
}

