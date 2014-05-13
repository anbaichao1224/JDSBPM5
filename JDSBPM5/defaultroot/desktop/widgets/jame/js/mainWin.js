//主窗口js

Ext.namespace("Jame.ui");
Ext.namespace("Jame.factory");
//Ext.namespace("Jame.backend.url");
Jame.ui.getMainCfg = function(id) {
  var tmpId = id || Ext.id();
  var cfg = {

    id:"jameMain" + tmpId,
    width:260,
    height:500,
//    miniWidth:288,
    miniHeight:500,
    collapsible:true,
    x:820,
    y:100,
    //closable:false,
    plain:true,
    border:false,
    layout:'border',

    headerCfg :{
      html:"<table border=0 ><tr><td rowspan=2 width='41' id='userImg"+tmpId+"'></td><td id='userTitle"+tmpId+"'></td><td id='status"+tmpId+"'></td></tr>"
            +"<tr><td colspan=2 id='userMsg"+tmpId+"'></td></tr></table>"
    },
    headerStyle:"line-height:80px",
    tbar: Jame.menu.mainWinTbar,
    bbar:Jame.menu.mainWinBbar,
    items:[
      {
        region:"north",
        height:22,
//        items:[
////          {xtype:"form",
////            items:[
//              {xtype:"textfield",
//                hideLabel:true,
//                width:"90%",
//                name:"searchTxt"
//              }
//            ]
////          }]
        html:"<input type=text name='searchTxt"+tmpId+"' style='width:90%'>&nbsp;" +
             "<img src='"+Jame.ui.mainWinImg.searchImg+"' width=18 height=18 onclick='getMainWin().searchUser()' style='cursor:pointer'>"
      },
      {
      region:'center',
      minHeight:150,
      id:'buddy_panel_main'+id,
      border:false,
      defaults:{autoScroll:true},
      layout:"accordion",

        hideCollapseTool:true,
        titleCollapse:true,
        collapseFirst:true,
        activeOnTop: true,
//        activeItem:"group_cust",
listeners:{
  resize:function(p) {
    var obj = p.getLayout().activeItem ;
    if (obj) {
      var is = obj.items;
      var w = p.getInnerWidth();
      var h = obj.getInnerHeight();
      if (is && h > 10) {
        var c = is.get(0);
        c.setSize(w, h);
      }
    }
  }
},
      items:[
       {
        title:"<img id='cust2GrpImg' src='"+Jame.ui.groupImg.cust_1+"' width=16 height=16>&nbsp;&nbsp;&nbsp;<b>本部门</b>",
        id:"group_cust2"
        ,
          listeners:{
            expand :function(p){
              var w=p.getInnerWidth();
              var h=p.getInnerHeight();
              var is=p.items;
              if(is&&h>10){
              var c=is.get(0);
                setTimeout(function(){
                  c.setSize(w,p.getInnerHeight())
                },100);
              }
            }
//            collapse:function(){
//              if(Ext.getCmp("group_sys").collapsed){
//                Ext.getCmp("group_mywork").expand();
//              }
//            }
//            ,
//              expand :function() {
//                alert('expand');
//              }

          }
        }, {
          title:"<img id='sysGrpImg' src='"+Jame.ui.groupImg.sys_1+"' width=16 height=16>&nbsp;&nbsp;&nbsp;<b>组织机构</b>",
          id:'group_sys',
          autoScroll:false,
        autoWidth :false,
          listeners:{
            expand :function(p){
              var w=p.getInnerWidth();
              var h=p.getInnerHeight();
              var is=p.items;
              if(is&&h>10){
              var c=is.get(0);
                setTimeout(function(){
                  c.setSize(w,p.getInnerHeight())
                },100);
              }
            }
//            collapse:function(){
//              if(Ext.getCmp("group_mywork").collapsed){
//                Ext.getCmp("group_cust").expand();
//              }
//            }
          }
        },{
        title:"<img id='custGrpImg' src='"+Jame.ui.groupImg.cust_1+"' width=16 height=16>&nbsp;&nbsp;&nbsp;<b>自定义组</b>",
        id:"group_cust"
        ,
          listeners:{
//            collapse:function(){
//              if(Ext.getCmp("group_sys").collapsed){
//                Ext.getCmp("group_mywork").expand();
//              }
//            }
//            ,
//              expand :function() {
//                alert('expand');
//              }
            
          }
        }
  /*      ,{
          title:"<img id='officeGrpImg' src='"+Jame.ui.groupImg.work_1+"' width=16 height=16>&nbsp;&nbsp;&nbsp;<b>工作组</b>",
          id:"group_office",
          listeners:{
//            collapse:function(){
//              if(Ext.getCmp("group_cust").collapsed){
//                Ext.getCmp("group_sys").expand();
//              }
//            }
          }
        } */
      ]
    }
    ]
  };
  return cfg;
}
//----------------------------------------------------------------------------------------

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

Jame.ui.MainWin = function(id, name) {
  this.id=id;
  this.userName=name;
  this.cwins={};
  var cfg = Jame.ui.getMainCfg(this.id);
  this.win = new Ext.Window(cfg);
  this.win.show();
  this.titleTxt = document.getElementById("userTitle"+this.id);
  this.titleImg=document.getElementById("userImg"+this.id);
  this.titleMsg=document.getElementById("userMsg"+this.id);
  
  var imgPath =Jame.ui.mainWinImg.userImg;//用户头像
  this.setUserTitle(imgPath,name,"");
  this.statusBtn=this.initStatus();

  this.showOffline=true;
  this.showEmptyGroup=true;

  var p1 = Ext.getCmp('group_sys');
  p1.getEl().on("contextmenu", function(e, html, obj) {
    var menu = Jame.menu.getSysPanelMenu(id);
    menu.showAt(e.getXY());
    e.stopEvent();
  })

  this.sysTree=new Jame.tree.JameTree(id,"sys",p1);

  var p2 = Ext.getCmp('group_cust');
  p2.getEl().on("contextmenu", function(e, html, obj) {
    var menu = Jame.menu.getCustPanelMenu(id);
    menu.showAt(e.getXY());
    e.stopEvent();
  })
  

  this.custTree = new Jame.tree.JameTree(id, "cust", p2);
  this.custTree.tree.on("show", function(t) {
    t.expandAll();
  });
  this.custTree.tree.show();

 /*   var p3 = Ext.getCmp('group_office');
    p3.getEl().on("contextmenu", function(e, html, obj) {
     var menu = Jame.menu.getSysPanelMenu(id);
    menu.showAt(e.getXY());
    e.stopEvent();
  })
  this.officeTree=new Jame.tree.JameTree(id,"office",p3); */

  var p4=Ext.getCmp("group_cust2");
  p4.getEl().on("contextmenu", function(e, html, obj) {
    e.stopEvent();
  })
  this.cust2Tree=new Jame.tree.JameTree(id,"cust2",p4);


  this.treePanel=Ext.getCmp("buddy_panel_main"+this.id);

  this.win.on("close",function(){
    jameMainwin=null;
    var ws=Jame.ui.chatWin;
    for(var k in ws){
      if(ws[k]){
        ws[k].close();
      }
    }
  });

}

Jame.ui.MainWin.prototype.initStatus = function() {

  var b = new Ext.Button(Jame.menu.userStatusMenu);
  b.render("status" + this.id);
//  renderTo:"status" + this.id,
  return b;
}


Jame.ui.MainWin.prototype.setUserStatus=function(text,status){

    //todo 状态发送到后台
  this.statusBtn.setText(text);
//  this.statusBtn.setTooltip(text);
}
Jame.ui.MainWin.prototype.setUserTitle=function(imgPath,title,msg){
  if(this.titleTxt) {
    if (imgPath)
      this.titleImg.innerHTML = "<img src='" + imgPath + "' width=40 height=40 >";
    if (title)
      this.titleTxt.innerHTML = "<div style='font-size:14px;font-weight:bold;' onclick='//getMainWin().showUserInfo()'>" + Ext.util.Format.ellipsis(title || "", 6) + "</div>";
    if (msg){
//      this.titleMsg.innerHTML = "<div style='font-size:12px;cursor:pointer' msg='" + msg + "' onclick='Jame.ui.MainWin.prototype.startEditTitle(this)'>" + Ext.util.Format.ellipsis(msg || "", 8) + "</div>";

      this.titleMsg.innerHTML="";
      var btn=new Ext.Button({
        text: Ext.util.Format.ellipsis(msg || "", 10),
        msg:msg,
        cls:"x-btn-text",
        handler:this.startEditTitle
      });
      btn.render(this.titleMsg);
    }
  }
}
Jame.ui.MainWin.prototype.startEditTitle=function(obj){
//  obj.el.dom.outerHTML=("<input style='font-size:12px' type=text value='"+obj.msg+"'>")


  var txtF = new Ext.form.TextField({
    value:obj.msg,
    selectOnFocus:true
  });
  txtF.on("blur", function(t) {
    getMainWin().updateTitle(t.getValue());
  });
//  var txtEl = Ext.DomHelper.createDom({tag:"div"});
//  obj.el.replaceWith(txtEl)

  var node=obj.el.dom.parentNode;
  node.innerHTML="";

  txtF.render(node);
  txtF.selectText();
  txtF.focus();
}

Jame.ui.MainWin.prototype.updateTitle=function(s){
  //todo 签名改变发到后台
  this.setUserTitle(null,null,s);
}

Jame.ui.MainWin.prototype.showUserInfo=function(){
  alert(this.id+"=="+this.userName)
}
Jame.ui.MainWin.prototype.show=function(){
  this.win.show();
}

Jame.ui.MainWin.prototype.showChatWin=function(node){
  var node=node||{id:"abcddd",text:"哈"};
  Jame.ui.ChatDialog.showChatWin(node);

}

Jame.ui.MainWin.prototype.showGroupChatWin=function(node){
  Jame.ui.ChatDialog.showGroupChatWin(node);
}

Jame.ui.MainWin.prototype.searchUser=function(){
  var str=document.getElementById("searchTxt"+this.id).value;
  if(str&&str.trim()!=""){
    this.showSearchRS(str);
  }else{
    alert("请输入要查找的用户名");
  }
}

Jame.ui.MainWin.prototype.showSearchRS = function(str) {
  var dy = 100;
  var h = this.win.getSize().height - dy;
//  var id="rsWin"+this.id;;
  var cfg = {
    //    id:id,
    x:this.win.x,
    y:this.win.y + dy,
    width:this.win.getSize().width,
    height:h,
    items:[
      {id:'searchRsDiv',
        layout:"fit"
        ,
        height:h - 60
      }
    ]
  };
  if (this.rsWin) {
    this.rsTree.tree.destroy();
    this.rsWin.close();
  }
  this.rsWin = new Ext.Window(cfg);

  this.rsWin.show();
  var p = Ext.getCmp('searchRsDiv');

  this.rsTree = new Jame.tree.JameTree("searchRsTree" + this.id, "search", p,
  {
    searchStr:str,
    curPanel:this.getCurPanel().id,
    canCheck:true
  }
      );
}

Jame.ui.MainWin.prototype.getCurPanel = function() {
  return this.treePanel.getLayout().activeItem;
}

//应该只用到cust的
Jame.ui.MainWin.prototype.changeGroupImg = function(grp,idx) {
  if(grp=="sys"){
    var obj=document.getElementById("sysGrpImg");
    obj.src=Jame.ui.groupImg["sys_"+idx];
  }else if(grp=="cust"){
    var obj=document.getElementById("custGrpImg");
    obj.src=Jame.ui.groupImg["cust_"+idx];
  }
}

//闪动或取消 消息提示
Jame.ui.MainWin.prototype.setMsgStatus=function(id,flag){
  if(flag){
  	
    this.changeGroupImg("cust","2");
    this.custTree.changePersonStatus(id,"msgStatus")
  }else{
    this.changeGroupImg("cust","1");
    this.custTree.changePersonStatus(id)
  }
}

//显示聊天记录
Jame.ui.MainWin.prototype.showChatHistory=function(id){
  Jame.action.showChatHistory(id);
}
