//菜单等

Ext.namespace("Jame.menu");
var iconDir = context + 'desktop/widgets/jame/images/jame/';

//静态配置

//显示状态的配置
Jame.menu.userStatusMenu = {
  text:"我在线上",
  tooltip:'在线状态',
  menu:[
    { 
	  text:'我在线上',
	  icon:iconDir+"icon_available.png",
      status:"available",
      handler:Jame.action.changeStatus
    },
    { 
	  text:'离开',
	  icon:iconDir+"icon_away.png",
      status:"away",
      handler:Jame.action.changeStatus
    },
    {
      text:'忙碌',
	  icon:iconDir+"icon_dnd.png",
      status:"dnd",
      handler:Jame.action.changeStatus
    },
    { 
	  text:'离线',
	  icon:iconDir+"icon_unavailable.png",
      status:"unavailable",
      handler:Jame.action.changeStatus
    }
  ]
};

Jame.menu.tb_sz={
  id:'szMenu',
  items:[
    {
      text:"系统设置",
	  icon:iconDir + 'cog_edit.png',
      tooltip:"系统设置",
      menu:[
        {text:"创建组",
        handler:Jame.action.addGroup
        },
        {text:"显示离线人员",
          checked:true,
          checkHandler:function(item, checked) {
            Jame.action.showOffline(checked);
          }
        },
        {text:"显示空组",
          checked :true,
          checkHandler:function(item, checked) {
            Jame.action.showEmptyGroup(checked)
          }
        }
      ]
    },{
      text:"消息设置",
	  icon:iconDir + 'comments.png',
      tooltip:"消息设置",
      menu:[
        {text:"聊天日志",
        handler:function(){
          getMainWin().showChatHistory()
        }
        }
      ]
    },{
      text:"退出",
	  icon:iconDir + 'cancel.png',
      tooltip:"退出",
      handler:function(){
        getMainWin().win.close();
      }
    }
  ]
};

//主面板工具条
Jame.menu.mainWinTbar = [
  {xtype: 'tbseparator'},
  {
    text: '设置',
    tooltip:'设置',
    menu: Jame.menu.tb_sz
  },
  "-",{
  text:"刷新",
  tooltip:"刷新自定义组",
  handler:function(){
    getMainWin().custTree.refresh();
  }
}
];
Jame.menu.mainWinBbar = [

  {
    text:"版本信息",
    //icon: iconDir + 'information.png',
    tooltip:{text:'即时通讯2.0'}

  }
]


//个人信息设定
Jame.menu.tb_persionmenu = {
  id: 'ToolMenu',
  items: [{
    text: '修改密码',
    icon: iconDir + 'cog_edit.png',
    disabled:false

  },{
    text: '个人信息',
    icon: iconDir + 'vcard_edit.png',
    disabled:true
//    ,
//    handler:Jame.factory.showVCard
  }]
};

//组设定等
Jame.menu.tb_groupmenu = {
  id:"BuddysMenu",
  items:[{
    text: '发送消息',
    icon: iconDir + 'message-pending.png',
    disabled:true
//    ,
//    handler:Jame.factory.sendMessage
  },{
    text: '回复信息',
    disabled:true,
    icon: iconDir + 'comments.png'
//    ,
//    handler:Jame.factory.enterChat
  },{
    text: '是否显示离线人员',
    checked:true,
    id:'showOffline'
//    ,
//    checkHandler:Jame.factory.showOffline
  },{
    text: '是否显示空组',
    checked:true
//    ,
//    checkHandler:Jame.factory.showEmpty
  },{
    text: '添加组',
    icon: iconDir + 'group_add.png'
//    ,
//    handler:Jame.factory.addGroup
  }]
};

////////////////////////////////////
//直接返回菜单对象

//系统组面板的右键菜单
Jame.menu.getSysPanelMenu = function(id) {
//  return testMenu2;
  return new Ext.menu.Menu({});
}

//自定义组面板的系统菜单
Jame.menu.getCustPanelMenu = function(id) {
  var menu = Ext.getCmp(id + "_menu");
  if (!menu) {
    var cfg = {
      id: id + "_menu",
      items:[
        {text:"创建组",
          type:"cust",
          handler:Jame.action.addGroup
        }
      ]
    };

    menu = new Ext.menu.Menu(cfg);
  }
  return menu;
}

//系统组人员右键菜单
Jame.menu.getSysPersonMenu = function(id) {
  var menu = Ext.getCmp(id + "_sysmenu");
  if (!menu) {
    var cfg = {
      id: id + "_sysmenu",
      items:[{
          text:"发送即时消息",
          handler:function(){
            getMainWin().showChatWin(menu.node);
          },
          icon: iconDir + 'message-pending.png'
        },
        {text:" 查看详细信息",
          handler:function(){
            Jame.action.showInfo(menu.node);
          }
        },
        {
          text:"添加到组",
          menu:[],
          listeners:{
            activate:function(item) {
              Jame.menu.showCustGroupMenu(item);
              if (!item.menu.hasListener("itemclick")) {
                item.menu.on("itemclick", function(it) {
                  getMainWin().custTree.addToID(menu.node, it.groupId,true);
                });
              }
            }
          }
        }
//        ,
//        {text:"发送短信"},
//        {text:"发送审批件"},
//        {text:"发送催办"},
      ]
    };

    menu = new Ext.menu.Menu(cfg);
  }
  return menu;
}

//自定义组人员右键菜单
Jame.menu.getCustPersonMenu = function(id) {
  var menu = Ext.getCmp(id + "_custmenu");
  if (!menu) {
    var cfg = {
      id: id + "_custmenu",
      items:[

//        {   text: '发送消息',
//          				handler: function(){getMainWin().showChatWin(menu.node); },
//          icon: iconDir + 'message-pending.png'
//        },
//        {   text: '删除',
//          //				handler: ExtJame.backend.Connection.removeBuddy ,
//
//          icon:iconDir + "user_delete.png"
//        },{
//          text:"移动到",
//          menu:[],
//          listeners:{
//            activate:function(item){
//              item.menu.removeAll(true);
//              item.menu.add(Jame.menu.getCustGroupList());
//              if(!item.menu.hasListener("itemclick")){
//                item.menu.on("itemclick",function(it){
//                  getMainWin().custTree.moveToID(menu.node,it.groupId)
//                });
//              }
//            }
//          }
//          }


        {text:"发送即时消息",
          handler: function() {
            getMainWin().showChatWin(menu.node);
          },
          icon: iconDir + 'message-pending.png'
        },
//        {text:"发送短信"},
//        {text:"发送审批件"},
//        {text:"发送催办"},
        {text:"转移到组",
          menu:[],
          listeners:{
            activate:function(item) {
              Jame.menu.showCustGroupMenu(item);
              if (!item.menu.hasListener("itemclick")) {
                item.menu.on("itemclick", function(it) {
                  getMainWin().custTree.moveToID(menu.node, it.groupId)
                });
              }
            }
          }
        },
//        {text:"拷贝到组"},
        {text:"查看详细信息",
		icon: iconDir + 'icon_xa.png',
          handler:function() {
            Jame.action.showInfo(menu.node);
          }
        },
        {text:"查看聊天记录",
		icon: iconDir + 'comments.png',
          handler:function(){
            getMainWin().showChatHistory(menu.node.id);
          }
        },
        {text:"删除联系人" ,
          handler:function() {
//            if(confirm("确认要删除好友:"+menu.node.text+"?")){
              menu.node.getOwnerTree().jt.removePerson(menu.node);
//            }
          },
          icon:iconDir + "user_delete.png"
        }
//        {text:"排序"},
//        ,{
//          text:"测试",
//          handler:function(){
//            getMainWin().custTree.changePersonStatus(menu.node.id,"dnd");
//          }
//        }

      ]
    };

    menu = new Ext.menu.Menu(cfg);
  }
  return menu;
}


//搜索结果人员右键菜单
Jame.menu.getSearchPersonMenu = function(id) {
  var menu = Ext.getCmp(id + "_searchmenu");
  if (!menu) {
    var cfg = {
      id: id + "_custmenu",
      items:[
        {text:"发送即时消息",
          handler: function() {
            getMainWin().showChatWin(menu.node);
          },
          icon: iconDir + 'message-pending.png'
        },
        {
          text:"添加到组",
          menu:[],
          listeners:{
            activate:function(item) {
              Jame.menu.showCustGroupMenu(item);
              if (!item.menu.hasListener("itemclick")) {
                item.menu.on("itemclick", function(it) {
                  getMainWin().custTree.addToID(menu.node, it.groupId,true);
                });
              }
            }
          }
        }
//		,
//
//        {text:"查看详细信息",
//          handler:function() {
//            Jame.action.showInfo(menu.node);
//          }
//        }
      ]
    };

    menu = new Ext.menu.Menu(cfg);
  }
  return menu;
}


//系统组的右键菜单
Jame.menu.getSysGroupMenu = function(id) {
  var menu = Ext.getCmp(id + "_sysmenu");
  if (!menu) {
    var cfg = {
      id: id + "_sysmenu",
      items: [
//          {
//            text:"向部门发消息",
//            handler:function(){
//              getMainWin().showGroupChatWin(menu.node);
//            },
//            icon:iconDir + 'message-pending.png'
//          },
//          {
//            text:"查看部门信息",
//            handler:function(){
//              Jame.action.showInfo(menu.node);
//            }
//          },
          {
            text:"转移到自定义组",
            menu:[],
          listeners:{
            activate:function(item) {
              Jame.menu.showCustGroupMenu(item);
              
              if (!item.menu.hasListener("itemclick")) {
                item.menu.on("itemclick", function(it) {
                  var ns=menu.node.childNodes;
                  if(ns&&ns.length>0){
                    if(ns[0].leaf!=true){
                      alert("请选择基层部门");
                      return ;
                    }
                    getMainWin().custTree.addAllToID(ns, it.groupId)
                  }
                });
              }
            }
          }
          }
      ]
    };

    menu = new Ext.menu.Menu(cfg);
  }
  return menu;
}

//自定义组右键菜单
Jame.menu.getCustGroupMenu = function(id) {
  var menu = Ext.getCmp(id + "_custmenu");
  if (!menu) {
    var cfg = {
      id: id + "_custmenu",
      items: [
        {text:"向组发送消息",
          handler:function(){
            getMainWin().showGroupChatWin(menu.node);
          },
          icon:iconDir + 'message-pending.png'
        },
        {text:"添加人员",
          handler:function(){
            Jame.action.selectPersonTo(menu.node);
          },
          icon:iconDir + 'user_add.png'
        },
        {text:"重命名",
          handler:function(){
            menu.node.getOwnerTree().jt.renameGroup(menu.node);
          },
          icon:iconDir + "group_edit.png"
        },
        {text:"删除组",
          handler:function(){
            menu.node.getOwnerTree().jt.removeGroup(menu.node);
          },
          icon:iconDir + "group_delete.png"
        },
        {text:"创建组",
        handler:Jame.action.addGroup
        }
//        ,
//        {text:"排序"}
      ]
    };

    menu = new Ext.menu.Menu(cfg);
  }
  return menu;
}

Jame.menu.getListPersonMenu=function(id){
  var menu = Ext.getCmp(id + "_listMenu");
  if (!menu) {
    var cfg = {
      id: id + "_custmenu",
      items: [
        {text:"发送即时消息",
          handler:function(){
            getMainWin().showChatWin(menu.node);
          },
          icon:iconDir + 'message-pending.png'
        }
//		,
//        {text:"查看详细信息",
//          handler:function(){
//            Jame.action.showInfo(menu.node);
//          }
//        }
      ]
    };

    menu = new Ext.menu.Menu(cfg);
  }
  return menu;
}


Jame.menu.getCustGroupList=function(){
//  var gs=getMainWin().custTree.getCustGroup();
  var gs=getMainWin().custTree.getRootGroups();
  var menu=[];
  for(var i=0;i<gs.length;i++){
    var tmp={};
    tmp.text=gs[i].text;
    tmp.groupId=gs[i].id;
    menu.push(tmp);
  }
  return menu;
}


Jame.menu.showCustGroupMenu = function(item) {
  var gs=Jame.menu.getCustGroupList();
  item.menu.removeAll(true);

//  item.menu.add(gs);
  for(var i=0;i<gs.length;i++){
    item.menu.add(gs[i]);
  }
}