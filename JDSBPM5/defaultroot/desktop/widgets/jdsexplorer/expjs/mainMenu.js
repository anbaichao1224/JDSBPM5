//主菜单

Ext.namespace("JDS.exp.mainMenu");


//在指定集合中,通过id获取菜单项及其子菜单
JDS.exp.mainMenu.getMenuItem = function(is,id) {
  if(id=="-"){
    return "-";
  }
  var item = Ext.apply({}, is[id]);
  if (item.menu) {
    var ms = item.menu;
    item.menu = [];
    item.id = item.id+"_"+Ext.id();
    for (var j = 0,n = ms.length; j < n; j++) {
      item.menu.push(JDS.exp.mainMenu.getMenuItem(is,ms[j]));
    }
  }
  var actionName=item["cmdName"]||id;
  if (actionName) {
    item.handler = function () {
      JDS.exp.actions.exec(actionName, arguments);
    };
  }
  var sName=item["statusFnName"]||id;
  if(sName){
    item["statusFnName"]=sName;
  }
  return item;
}

/**
 * 初始化主菜单到指定的el对象
 * @param el
 */
JDS.exp.mainMenu.initMenu = function (el) {
  var items = [];
  var ts = JDS.exp.mainMenu.titles;
  var is=JDS.exp.mainMenu.items;
  for (var i = 0,c = ts.length; i < c; i++) {
    var item=JDS.exp.mainMenu.getMenuItem(is,ts[i]);
    items.push(item);
  }
  var menu = new Ext.Toolbar({
    id:Ext.id(),
    renderTo : el,
    autoShow:true,
    items:items
  });
  var ms=menu.items;
  for(var i=0;i<ms.length;i++){
    JDS.exp.mainMenu.addMenuEvent(ms.get(i).menu);
  }
  //menu.show(el, "bl-bl");
  return menu;
}

//增加菜单的显示前的处理函数的调用
JDS.exp.mainMenu.addMenuEvent=function(menu){
  menu.on("beforeshow",JDS.exp.status.checkMenu);
  var is=menu.items;
  for(var i=0;i<is.length;i++){
    var m=is.get(i).menu;
    if(m){
      JDS.exp.mainMenu.addMenuEvent(m);
    }
  }
}

function al(obj,flag){
  var str="";
if(typeof obj =="array"){
  for(var i=0;i<obj.length;i++){
    str+=getObjStr(obj[i],flag)+",";
  }
}else{
str=getObjStr(obj,flag);
}
  alert(str)
}

function getObjStr(obj,flag){
var str="";
for(var k in obj){
  if(flag){
    if(typeof(obj[k])=="function"){
      str+=k+"=fnfn"+",";
    }else{

  str+=k+"="+obj[k]+","
    }
  }else{
  str+=k+"="+obj[k]+","
    }
}
return str;
}