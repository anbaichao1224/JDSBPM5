//执行的命令等

Ext.namespace("Jame.action");
var iconDir = context + 'desktop/widgets/newjame/images/jame/';

Jame.action.addGroup = function(node) {
  //  if (node.type == "cust") {
  Ext.getCmp("group_cust").expand();
  var n = getMainWin().custTree.addGroup();
//  }
}

Jame.action.changeStatus = function(b, e) {
  getMainWin().setUserStatus(b.text, b.status);
}

//打开人员组织树,选择人员加到指定组中
Jame.action.selectPersonTo = function(parent) {
  var tree = Jame.tree.getTree("personid", "sys", {canCheck:"true"});
  var win = new Ext.Window({
    title: '选择添加人员',
    //         collapsible:true,
    width:330,
    height:450,
    modal:true,
    id:'selectTreeWin',
    shim:false,
    animCollapse:false,
    constrainHeader:true,
    maximizable: true,
    layout:"fit",
    items: tree,
    buttons: [{
      text:'确定',
      handler: function() {
        var ns = tree.getChecked();
        if (ns && ns.length > 0) {
          getMainWin().custTree.addAllToID(ns, parent.id);
        }
        win.close();
      }
    },{
      text: '取消',
      handler: function() {
        win.close();
      }

    }]
  });
  win.show();
}

//显示详细信息
Jame.action.showInfo = function(node) {
  //alert(node.text + "\n" + (node.message || node.attributes.message || ""));
  var url="/getPersonInfo.action";
  url+="?personid="+node.id;
  var cfg={
    title:node.text+" 的信息",
    resizable:false,
    width:380,
    height:370,
    html:"<iframe src='"+url+"' width='100%' height='100%'></iframe>"
  };
  new Ext.Window(cfg).show();
}


Jame.action.showOffline = function(flag) {
  var ns = getMainWin().custTree.getPersonByStatus("unavailable");
  for (var i = 0; i < ns.length; i++) {
    if (flag) {
      ns[i].getUI().show();
    } else {
      ns[i].getUI().hide();
    }
  }
  getMainWin().showOffline = flag;
}

Jame.action.showEmptyGroup = function(flag) {
  var ns = getMainWin().custTree.getEmptyGroup();
  for (var i = 0; i < ns.length; i++) {
    if (flag) {
      ns[i].getUI().show();
    } else {
      ns[i].getUI().hide();
    }
  }
  getMainWin().showEmptyGroup = flag;
}

Jame.action.showChatHistory = function(id) {
  var win = Ext.WindowMgr.get("jameMsgHistory");
  if (!win) {
    var pId = personid;

    var store = new Ext.data.JsonStore({
      root: 'rows',
      totalProperty: 'count',
      fields: [
        'msg',"from","to","date","status","type"
      ],

      proxy: new Ext.data.HttpProxy({
        url: Jame.backend.url.hisMsg + "?personId=" + personid
//        url:Jame.backend.url.hisMsg+ "?personId=" + personid+"&msgPerson="+id
      })
    });

     var typeStore = new Ext.data.SimpleStore({
        fields: ['key', 'text'],
        data : [
          ["","所有消息"],
          ["SYSTEM","系统消息"],
          ["OFFICE","公务消息"],
          ["PRIVATE","个人消息"]
        ]
    });
    var typeCom = new Ext.form.ComboBox({
      store: typeStore,
      displayField:'text',
      valueField :"key",
      typeAhead: true,
      mode: 'local',
      triggerAction: 'all',
      editable:false,
      width:80,
      value:"",
      name:"type_" + id,
      selectOnFocus:true
    });
    var tBar=new Ext.Toolbar([
      {text:"发送时间从:"},
      {xtype:"datefield",
//        fieldLabel :"abd",
//        format:"Y-m-d",
        name:"startTime_"+id
      },{
      text:"到"
      },{
      xtype:"datefield",
//      format:"Y-m-d",
        name:"endTime_"+id
    },{
      text:"类型:"
    },typeCom,{
      text:"内容包括:"
    },{
      xtype:"textfield",
      name:"content_"+id
    },"-",{
      text:"清空",
      handler:function(){
        var is=tBar.items;
        for(var i=0;i<is.length;i++){
          var it=is.get(i);
          if(it.setValue){
            it.setValue("");
          }
        }
      }
    },{
    text:"查询",
      handler:function(){
        var curId=getMainWin().hisTree.tree.getSelectionModel().getSelectedNode().id;
        Jame.action.showChatHistory(curId);
      }
    }
    ]);



    var pagingBar = new Ext.PagingToolbar({
      pageSize: 25,
      store: store,
      displayInfo: true,
      displayMsg: '记录 {0} - {1} of {2}',
      emptyMsg: "没有记录"

    });


    var grid = new Ext.grid.GridPanel({
      store: store,
      columns: [
        {id:'type', header: "类型", width: 40, sortable: true, dataIndex: 'type'},
        {id:'status', header: "状态", width: 40, sortable: true, dataIndex: 'status'},
        {id:'from', header: "发送人", width: 40, sortable: true, dataIndex: 'from'},
        {id:'to', header: "接收人", width: 40, sortable: true, dataIndex: 'to'},
        {id:'date', header: "时间", width: 80, sortable: true, dataIndex: 'date'},
        {id:'msg', header: "记录",  sortable: true, dataIndex: 'msg'}
      ],
      viewConfig: {
        forceFit: true
      },
      sm: new Ext.grid.RowSelectionModel({singleSelect:true}),
          width:686,
      height:476,
      frame:true,
      //    title:'Framed with Checkbox Selection and Horizontal Scrolling',
      iconCls:'icon-grid',
      tbar:tBar,
      bbar:pagingBar
    });
    var winCfg = {
      title:"聊天记录",
      id:"jameMsgHistory",
      width:860,
      height:500,
      miniWidth:800,
      miniHeight:500,
      resizable:false,

      //closable:false,
      plain:true,
      border:false,
      //		iconCls : "available",
      // iconCls: 'accordion',
      layout:'border',
      items:[
        {
          region:"west",
          id:"msgHisTree",
          layout:"fit",
          width:160,
          height:400

        },{
        region:"center",
        items:[
          grid
        ]

        //       html:"skdjfdsklfjdsfjks"
      }
      ]
    };
    win = new Ext.Window(winCfg);
    win.show();
    win.grid = grid;
    win.queryBar = tBar;

    var p = Ext.getCmp('msgHisTree');
    getMainWin().hisTree = new Jame.tree.JameTree("msgHisTree" + pId, "his", p);
  }
  win.toId=id;
  win.show();
  getMainWin().hisTree.tree.getRootNode().expand();
  if(id){
    var node = getMainWin().hisTree.tree.getNodeById(id);
    if (node) {
      node.select();
    }
    var store = win.grid.getStore();

    store.removeAll();
    store.proxy.getConnection().extraParams = null;
    var p = {
      msgPerson:id,
      start:0,
      limit:25
    };

    var is = win.queryBar.items;
    var len = is.length;

    for (var i = 0; i < len; i++) {
      var it = is.get(i);
      if (it.name && it.getValue()) {
        var v = it.getValue();
        if ((typeof v) == "object") {
          v = v.format("Y-m-d");
        }
        p[it.name.substring(0, it.name.indexOf("_"))] = v;
      }
    }

    store.load({params :p});
    
    store.proxy.getConnection().extraParams = p;

  }
}