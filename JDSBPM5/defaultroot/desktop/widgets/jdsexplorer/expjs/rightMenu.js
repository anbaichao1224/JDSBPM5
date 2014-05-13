//主菜单

Ext.namespace("JDS.exp.rightMenu");

//已生成的右键菜单的缓存
JDS.exp.rightMenu.menus={};

/**
 * 取指定窗口的指定类型的 右键菜单
 * 此菜单是全局唯一的,上下文取决于传入的参数
 * @param win 对应的exp对象
 * @param obj 菜单对应的元素
 * @param type 菜单类型
 * @param eType 菜单的目标类型,即 哪上面生成的右键菜单,保留使用
 */
JDS.exp.getRightMenu=function(win,obj,type,eType){
  var menu=JDS.exp.rightMenu.menus[type];
  if(!menu){
    menu=JDS.exp.rightMenu.createRightMenu(type);
    JDS.exp.rightMenu.menus[type]=menu;
  }
  menu.win = win;
  menu.obj = obj;
  menu.eType=eType||"";
  return menu;  
}

//生成指定类型的新的右键菜单
JDS.exp.rightMenu.createRightMenu = function(type) {
  var items = [];
  var ts = JDS.exp.rightMenu.groups[type];
  var is = JDS.exp.mainMenu.items; //右键菜单和主菜单用相同的 item
  if(ts){
    for (var i = 0,c = ts.length; i < c; i++) {
      var item = JDS.exp.mainMenu.getMenuItem(is, ts[i]);
      items.push(item);
    }
  }
  var menu = new Ext.menu.Menu({
    id:"rightMenu" + type,//+Ext.id()
    //    autoShow:true,
    items:items
  });
  JDS.exp.mainMenu.addMenuEvent(menu);
  return menu;
}

//右键菜单的组合类型
JDS.exp.rightMenu.groups = {
  "default":["createnew","open","-","cut","copy","paste","copyto"],
  default_b:["createnew","open","-","sortBy","cut","copy","paste","copyto"]

};


