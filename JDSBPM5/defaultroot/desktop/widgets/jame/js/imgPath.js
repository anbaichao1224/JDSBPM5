//图片

Ext.namespace("Jame.ui");
Ext.namespace("Jame.menu");

var iconDir = context + 'desktop/widgets/jame/images/jame/';

/**
* 打开组聊天窗口时,显示的组图片,id为组id
 */
Jame.ui.getGroupImg=function(id){
  return iconDir+'FastIcon_Users/user_group.png';
}

/**
* 打开聊天窗口时,显示的聊天人员图片,id为人员id
 */
Jame.ui.getPersonImg=function(id){
  return iconDir+'FastIcon_Users/user.png';
}

// 用户的签名
Jame.ui.userTitle="";


Jame.ui.groupImg={//有消息时 闪动提示用  12*12 px
  sys_1:iconDir+"group_go.png", //系统组静态图片
  sys_2:iconDir+"comments.png", //系统组动态图片 应该用不到
  cust_1:iconDir+"group_go.png",    //自定义组静态图片
  cust_2:iconDir+"comments.png" ,    //自定义组动态图片
  work_1:iconDir+"group_go.png"     //我的工作组静态图片
}

Jame.ui.mainWinImg={
  searchImg:iconDir+"search.gif",//主窗口 搜索按钮 18*18 px
  userImg:iconDir+"FastIcon_Users/chat.png"   //用户头像
}

//人员不同状态对应的图标
Jame.menu.statusImg={
  available:iconDir+"icon_available.png",
  away:iconDir+"icon_away.png",
  dnd:iconDir+"icon_dnd.png",
  unavailable:iconDir+"icon_unavailable.png"

  ,
  msgStatus:iconDir+"comments.png" // 来消息时人员的闪动图标
}
