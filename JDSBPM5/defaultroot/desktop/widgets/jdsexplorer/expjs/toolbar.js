
//工具条

JDS.exp.toolbar.initToolbar = function (el) {
  var items = [];
  var ts = JDS.exp.toolbar.titles;
  var is=JDS.exp.toolbar.items;
  for (var i = 0,c = ts.length; i < c; i++) {
    items.push(JDS.exp.mainMenu.getMenuItem(is,ts[i]));
  }
  var tb = new Ext.Toolbar({
    renderTo : el,
    autoShow:true,
    items:items
  });
  return tb;
}
JDS.exp.toolbar.initUriToolbar=function(el){
  var items = [];
  var ts = JDS.exp.toolbar.uriTitles;
  var is=JDS.exp.toolbar.uriItems;
  for (var i = 0,c = ts.length; i < c; i++) {
    items.push(JDS.exp.mainMenu.getMenuItem(is,ts[i]));
  }
  var tb = new Ext.Toolbar({
    renderTo : el,
    autoShow:true,
    items:items
  });

  var store = new Ext.data.SimpleStore({
	        fields :  ['abbr', 'state'],
	        data  : [
		        ['test1', 'test'],
		        ['test1', 'test2']
	   		]
	    });
 var box=new Ext.form.ComboBox({
          store:store,
          displayField : 'state',
   valueField :"abbr",
          mode :  'local',
	        triggerAction :  'all',
	        emptyText : '我的应用',
	        selectOnFocus : true,
	        width  :  800,
	        minListWidth : 60
	    });
  box.on("beforequery",function(e){
    var b=e.combo;
    var dirs=b.bar.win.getDirTree();
    var kv=[];
    for(var i=0;i<dirs.length;i++){
      var t={};
      t.abbr=dirs[i].id;
      t.state=dirs[i].text;
      var r=new Ext.data.Record(t);
      kv.push(r);
    }
    b.store.removeAll();
    b.store.add(kv);
    b.store.commitChanges();
    return true;
  });
  box.on("collapse", function(b) {
    var p= b.getValue();
    if(p){
      b.bar.win.openPath(p);
    }
  });

  box.bar=tb;
  tb.addField(box);
  tb.box=box;
  return tb;
}