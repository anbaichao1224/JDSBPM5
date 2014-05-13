//聊天窗口js


Ext.namespace("Jame.ui");
Jame.ui.chatWin={};
Jame.ui.noShowMsg={};
var iconDir = context + 'desktop/widgets/jame/images/jame/';
Jame.ui.getEditorCfg=function(nodeId){

  //增加htmleditor 的按钮
  function addTbBtn(editor) {
    if(!editor.getToolbar().addedFace) {//2.2下会执行2次,加判断
      editor.getToolbar().add('-', Jame.custBtn.getFaceCfg(editor));
      editor.getToolbar().render();
      editor.getToolbar().addedFace = true;
//    editor.disableItems(true);
    }
  }
  var cfg= {
    xtype:'htmleditor',
    fieldLabel:'',
    name:'body',
    allowBlank:false,
    height:180,
    width:510,
    enableLists:false,
    id:(nodeId) + '_editor',
    winId:nodeId,
    hidden :true,
    listeners:{
//      activate :function(editor){
//        var win=Ext.getCmp(nodeId+"_win");
//        alert(win)
//        if(win){
//          win.setActive(true);
//        }
//      },
      initialize :function(editor) {
        editor.setVisible(true);
        addTbBtn(editor);//增加htmleditor 的按钮

        var iframe = editor.getEditorBody();
        if (iframe) {
          var winId=editor.winId;
          iframe.attachEvent("onkeydown", function(ev) {
            if (ev.ctrlKey && ev.keyCode == 13) {
              var win=Ext.WindowMgr.get(winId+"_chatWin")||Ext.WindowMgr.get(winId+"_groupChatWin");
              Jame.ui.ChatDialog.sendMessage({win:win});
              ev.keyCode=0;
            }
          });

          iframe.attachEvent("onfocus", function(ev) {
            var win=Ext.WindowMgr.get(winId+"_chatWin")||Ext.WindowMgr.get(winId+"_groupChatWin");
            if(win){
              Ext.WindowMgr.bringToFront(win);
            }
          });
        }
      }
    },
    fontFamilies:[
      'Arial',
      'Courier New',
      'Tahoma',
      'Times New Roman',
      'Verdana',
      '黑体',
      '宋体',
      '楷体_GB2312',
      '华文行楷',
      '隶书'
    ],
    buttonTips : {
      bold : {
        title: '粗 体1 (Ctrl+B)',
        text: '设置选中文字为粗体',
        cls: 'x-html-editor-tip'
      },
      italic : {
        title: '斜体 (Ctrl+I)',
        text: '设置选中文字为斜体.',
        cls: 'x-html-editor-tip'
      },
      underline : {
        title: '下划线 (Ctrl+U)',
        text: '给选中文字加下划线.',
        cls: 'x-html-editor-tip'
      },
      increasefontsize : {
        title: '加大',
        text: '增大字号',
        cls: 'x-html-editor-tip'
      },
      decreasefontsize : {
        title: '减小',
        text: '减小字号',
        cls: 'x-html-editor-tip'
      },
      backcolor : {
        title: '背景色',
        text: '设置选中文字的背景色',
        cls: 'x-html-editor-tip'
      },
      forecolor : {
        title: '前景色',
        text: '设置选中文字的颜色',
        cls: 'x-html-editor-tip'
      },
      justifyleft : {
        title: '左对齐',
        text: '左对齐',
        cls: 'x-html-editor-tip'
      },
      justifycenter : {
        title: '居中',
        text: '居中对齐',
        cls: 'x-html-editor-tip'
      },
      justifyright : {
        title: '右对齐',
        text: '右对齐',
        cls: 'x-html-editor-tip'
      },
      createlink : {
        title: '超链接',
        text: '给选中的文字加超链接',
        cls: 'x-html-editor-tip'
      },
      sourceedit : {
        title: '编辑源码',
        text: '切换是否是编辑源代码.',
        cls: 'x-html-editor-tip'
      }
    },
    border:false
  };
  return cfg;
}

Jame.ui.ChatDialog = (function(){
  var tbarHandler=function(btn){
    var id=btn.id;
    var toId=btn.win.toId;
    if (id.indexOf("sendFile") >= 0) {//传文件
    //  alert("选择文件传给:"+toId);
	  Jame.backend.Connection.LoadJsonFromUrl("jame/showSendDio.action", function() {}, ({toPersonId:toId,personId:personid}));
    }else if(id.indexOf("chatHistory")>=0){//显示聊天记录
          Jame.ui.MainWin.prototype.showChatHistory(toId);
    }
  };

  var addTbarEvent=function (win){
    var tbar=win.getTopToolbar();
    var is;
    if (extVer >= 3) {
      is = tbar.items.get(0).items;
    } else {
      is = tbar.items;
    }

    for(var i=0;i<is.length;i++){
      var btn=is.get(i);
      btn.win=win;
      btn.on("click",tbarHandler);
    }
  };

    var getTopBtns = function(id) {
      var cfg = [
   /*     {
		  text:"发送文件",
		  cls:'x-btn-icon',
		  //icon:iconDir+"vcard.png",
          id:"sendFile_" + id
        },  */
        {
		  text:"聊天记录",
		  cls:'x-btn-icon',
		  //icon:iconDir+"comments.png",
          id:"chatHistory_" + id
        }
      ];
      return cfg;
    };

  //多人聊天窗口,暂时没实现
  var createGroupDialog=function(node){
  	alert(node.text);
    var nodeId=node.attributes? node.attributes["id"]:node.id;

    var personList=Jame.tree.getList(node);
//    personList.setRootNode(node);

    var cfg={
//      title:"及时消息",
      id:nodeId+"_groupChatWin",
      width:700,
      height:510,
//      closeAction:"hide",
      miniWidth:700,
      collapsible:true,
      closable:true,
      layout:'border',
      iconCls : '',
      border:true,
      bodyBorder:true,

      headerCfg :{
        html:"<div style='padding-top:3px;font-size:14px'><img src='"+Jame.ui.getGroupImg(nodeId)+"' width=40 height=40 style='vertical-align:top;'>"
            +"与<span style='font-size:16px;font-weight:bold;'>"+node.text
            +"</span>成员对话中</div>"
      },
      headerStyle:"line-height:80px",

      //主功能按钮
//      tbar:[{
////        xtype: 'buttongroup',
//        //columns: 3,
//        autoWidth:true,
//        frame:false,
//        border:false,
//        items: getTopBtns(nodeId)
//      }
//      ],
      tbar:getTopBtns(nodeId),
      items:[
        {
          region:"center",
          miniWidth:500,
          id:nodeId+"_main",
          layout:'border',
          iconCls : '',
          items:[{                      //显示当前所有聊天信息
            region:'center',
            id:nodeId+"_all",

            border:0,
            height:150,
            xtype:'panel',
            autoScroll : true,
            border:false,
            layout:'fit',
            bodyStyle:'padding:10px;'
          },{                         //聊天输入框的父容器
            region:'south',
            minHeight:150,
            split:true,
            border:false,
            hideLabels:true,
            height:180,
            listeners:{
              resize:function(obj, w, h) {
                if (w && h) {
                  var e = obj.getComponent(0);
                  e.setSize(w, h - 40);
                }
              }
            },
            items:[
              Jame.ui.getEditorCfg(nodeId)   //输入框
            ],
            buttons:[
//              {text:"关闭",
//                handler:function(){
//                  var win=Ext.getCmp(nodeId+"_win");
//                  if(win){
//                    win.close();
//                  }
//                }
//              },
              {
              text:"发送",
              id:nodeId+'_sendBtn'
            }
            ]
          }]
        },{//右边形象展示等
        region: 'east',
        split: true,
        title:'参与人员',
        width: 150,
//        collapsible: true,
        margins:'0 0 3 3',
        cmargins:'3 3 3 3',
//        layout:"border",
        items:[
            personList
        ]
      }
      ]
		};

    var win = new Ext.Window(cfg);
//    extDialog.setTitle("");
    win.show();
    win.msgPanel=win.findById(nodeId+"_all");
    win.msgTxt=win.findById(nodeId + '_editor');
    win.sendBtn=Ext.getCmp(nodeId+'_sendBtn');
    win.sendBtn.win=win;
    win.toId = nodeId;
    win.isGroup=true;

    addTbarEvent(win);

    win.sendBtn.on("click",sendMessage);
  };

  var createDialog = function(node){
  	
//    var nodeId=node.attributes? node.attributes["personId"]:node.id;
    var nodeId=node.attributes? node.attributes["id"]:node.id;
	if (nodeId==personid){
		return;
	}

    var cfg={
//      title:"及时消息",
      id:nodeId+"_chatWin",
      width:500,
      height:400,
//      closeAction:"hide",
      miniWidth:300,
      collapsible:true,
      closable:true,
      layout:'border',
      iconCls : '',
      border:true,
      bodyBorder:true,

      headerCfg :{
        html:"<div style='padding-top:3px;font-size:14px'><img src='"+Jame.ui.getPersonImg(nodeId)+"' width=40 height=40 style='vertical-align:top;'>"
            +"与<span style='font-size:16px;font-weight:bold;'>"+node.text
            +"</span>对话中</div>"
      },
      headerStyle:"line-height:80px",

      //主功能按钮
//      tbar:[{
////        xtype: 'buttongroup',
//        //columns: 3,
//        autoWidth:true,
//        frame:false,
//        border:false,
//        items: getTopBtns(nodeId)
//      }
//      ],

      tbar:getTopBtns(nodeId),
      items:[
        {
          region:"center",
          miniWidth:500,
          id:nodeId+"_main",
          layout:'border',
          iconCls : '',
          items:[{                      //显示当前所有聊天信息
            region:'center',
            id:nodeId+"_all",

            border:0,
            height:150,
            xtype:'panel',
            autoScroll : true,
            border:false,
            layout:'fit',
            bodyStyle:'padding:10px;'
          },{                         //聊天输入框的父容器
            region:'south',
            minHeight:150,
            split:true,
            border:false,
            hideLabels:true,
            height:180,
            listeners:{
              resize:function(obj, w, h) {
                if (w && h) {
                  var e = obj.getComponent(0);
                  e.setSize(w, h - 40);
                }
              }
            },
            items:[
              Jame.ui.getEditorCfg(nodeId)   //输入框
            ],
            buttons:[
//              {text:"关闭",
//                handler:function(){
//                  var win=Ext.getCmp(nodeId+"_win");
//                  if(win){
//                    win.close();
//                  }
//                }
//              },
              {
              text:"发送(Ctrl+Enter)",
              id:nodeId+'_sendBtn'
            }
            ]
          }]
        }
     /*   
        ,{//右边形象展示等
        region: 'east',
        split: true,
        //title:'adsaf',
        width: 150,
        collapsible: true,
        margins:'0 0 3 3',
        cmargins:'3 3 3 3',
        layout:"border",
        items:[
          {
            region: 'north',
            id:"rightTopMsg_"+nodeId,
            split: true,
            height:250

          },{
          region: 'center',
          split: true
        }
        ]
      } */
      ]
		};
    
    var win = new Ext.Window(cfg);
//    extDialog.setTitle("");
    win.show();

    win.msgPanel=win.findById(nodeId+"_all");
    win.msgTxt=win.findById(nodeId + '_editor');
    win.sendBtn=Ext.getCmp(nodeId+'_sendBtn');
    win.sendBtn.win=win;
    win.toId = nodeId;
    win.isGroup=false;
win.getMsgPanel=function(){
  if(win.msgPanel){
    return win.msgPanel;
  }else{
    return win.findById(nodeId+"_all");
  }
}
    addTbarEvent(win);
    
    win.sendBtn.on("click",sendMessage);
    Jame.ui.chatWin[nodeId]=win;
  };

  //开始发送消息
  var sendMessage = function(obj) {
    var win = obj.win || obj;
    if (win) {
      var msg = win.msgTxt.getValue();
      win.msgTxt.reset();

if(msg.length>500){
  alert("消息太长,请分多次发送");
  return;
}
       msg=msg.replace(new RegExp("(<img.+?)(src=['|\"])http://.+?" + context, "gi"), "$1$2" + context);
      var ms = [];
      var tmp = {};
      tmp.to = win.toId;
      tmp.title = currUserName;
      tmp.msg = msg;
      ms.push(tmp);
      updateMessages(ms, 'blue', win.isGroup);

      var url;
      if (win.isGroup) {
        url = Jame.backend.url.sendGroupMessage;
      } else {
        url = Jame.backend.url.sendmessage;

      }
      msg=msg.replace(/(\'|\")/g,"\\$1");
      msg=msg.replace(/\n|\r/g,"<br>");
      Jame.backend.Connection.LoadJsonFromUrl(url, function() {}, ({body:msg,to:win.toId,personId:personid}));
    }
  }

  //将消息显示在聊天窗口中
  var updateMessages = function(messages, color, isGroup,msgStatus) {
//    if(msgStatus) {
//      var toId = messages[0].to;
//      if (!(Ext.getCmp(toId+"_chatWin")||Ext.getCmp(toId+"_groupChatWin"))) {
////      if(!Jame.ui.chatWin[toId]){
//
//        var ms = Jame.ui.noShowMsg[toId];
//        if (!ms) {
//          ms = [];
//        }
//        ms.push(messages[0]);
//        Jame.ui.noShowMsg[toId] = ms;
//
//        getMainWin().setMsgStatus(toId, true);
//        return;
//      }
//    }

    for (var i = 0; i < messages.length; i++) {
      var win=null;
      if (isGroup) {
        win=Jame.ui.ChatDialog.showGroupChatWin({id:messages[i]["to"],text:messages[i]["title"],treeType:messages[i]["treeType"]});
      } else {
        win=Jame.ui.ChatDialog.showChatWin({id:messages[i]["to"],text:messages[i]["title"]});
      }
//          var panel = Ext.getCmp(messages[i]["to"]+"_main").getComponent(0);

      var panel = win.msgPanel;
//      var panel = win.getMsgPanel();
color=color||"blue"
      var d = new Date();
      var ts = d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds();
      var btext = "<b style='color:" + color + ";'>[" + messages[i]["title"] + " " + ts + "] </b>" + messages[i]["msg"] + "<br/>";
//      btext = btext.replace(new RegExp("(<img.+?)(src=['|\"])http://.+?" + context, "gi"), "$1$2" + context);

      panel.body.insertHtml("beforeEnd", btext);
      panel.body.scroll("bottom", 1000, true)
    }
  };

  var chatMsg = function(title,html) {
    title=title||"提示";
    if(!html){
      html=title;
      title="提示";
    }
    Ext.Msg.alert(title,html);
  };

  return{
    //打开或显示单人聊天窗口
    showChatWin:function(node){
		
//      var id=node.attributes? node.attributes["personId"]:node.id;
      var id=node.attributes? node.attributes["id"]:node.id;
      var win=Ext.getCmp(id+"_chatWin");
//      var win=Jame.ui.chatWin[id];
      if(!win){
        createDialog(node);
        win=Jame.ui.chatWin[id];
      } else{
        win.show();
      }
      if(Jame.ui.noShowMsg[id]){
        getMainWin().setMsgStatus(id,false);
        var ms=Jame.ui.noShowMsg[id];
        Jame.ui.noShowMsg[id]=null;
        updateMessages(ms);
      }
      return win;
    },

    //打开或显示多人聊天窗口
    showGroupChatWin:function(node){
		
      var id=node.id;
      var win=Ext.getCmp(id+"_groupChatWin");
      if(!win){
          createGroupDialog(node);
      }
      else{
        win.show();
      }
      return win;
    },

    //开始发送消息,参数{win:要发出聊天信息的窗口对象}
    sendMessage:sendMessage,

    //将消息显示在聊天窗口中,参数 msg:信息数组,color:颜色,isGroup:是不是多人聊天
    updateMessages:updateMessages,
    showPrivateMsgById:function(personId, msgbody, title) {
      var messages = [{to:personId,msg:msgbody,title:title}];
      updateMessages(messages, 'red');
    },
    chatMsg:chatMsg

  }
})();

function showPrivateMsgById(personId, msgbody, title){
	
  if(!getMainWin()){
    createMainJameWin();
  }
  var messages = [{to:personId,msg:msgbody,title:title}];
  Jame.ui.ChatDialog.updateMessages(messages,"red",false,true);
}

Ext.ns("Jame.ui.chat");
/**
 * 发送和接收文件的js处理
 */
Jame.ui.chat.file=function(){
  function getSimpleName(path){
    return path.replace(/.*?([^\/\\]+)$/,"$1");
  }

  return {
    sendFiles:{},
    recFiles:{},
    savePaths:{},
    /**
     * 外壳选择要发送的文件或文件夹后调用,显示要发送的信息,同时提交到后台
     * @param toId    要发送给的人(接收方)的id,在调用选择框时传入的
     * @param fileId  发送文件的唯一标识,外壳生成
     * @param name    文件或文件夹名
     * @param isDir   是否是文件夹,在次只用来区别提示信息
     */
    beforeSend:function(toId,fileId,name,isDir){
      var msg="准备发送文件"+(isDir?"夹":"")+"["+name+"]";
      Jame.ui.ChatDialog.updateMessages([{to:toId,msg:msg,title:""}],"red");

      var url=Jame.backend.url.chatFileBeforeSend;
      Jame.backend.Connection.LoadJsonFromUrl(url, function() {}, ({toPersonId:toId,fileName:name,fileId:fileId,personId:personid}));
    },

    /**
     * 对方接收或拒绝后显示提示信息
     * @param toId    接收方id
     * @param fileId  文件id,暂未用
     * @param name    文件名
     * @param flag    flase:拒绝,true:接收
     */
    startSend:function(toId,fileId,name,flag){
      var msg=null;
      if(flag){
        msg="开始发送["+name+"]";
        Jame.ui.chat.file.sendFiles[fileId]=name;
      }else{
        msg="对方拒绝接收["+name+"]";
      }
      Jame.ui.ChatDialog.updateMessages([{to:toId,msg:msg,title:""}],"red");
    },

    //上面是发送方用的方法
    //下面是接收方法用的方法

    /**
     * 提示是否接收发送来的文件
     * @param formId          发送人id
     * @param fileId          文件id
     * @param name            文件名
     * @param formPersonName  发送人名字
     */
    beforeReceive:function(formId,fileId,name,formPersonName){
      var msg="";
      msg=" 给您传送文件:["+name+"].<a href='#' onclick='Jame.ui.chat.file.beforeStartReceive(\""+formId+"\",\""+fileId+"\",\""+name+"\");return false;'>接收</a>"
        +",<a href='#' onclick='Jame.ui.chat.file.startReceive(\""+formId+"\",\""+fileId+"\",\""+name+"\",false);return false;'>拒绝</a>";

      Jame.ui.ChatDialog.updateMessages([{to:formId,msg:msg,title:formPersonName}],"red");
    },

    /**
     * 调用外壳,打开保存位置选择对话框
     * @param formId
     * @param fileId
     * @param name
     */
    beforeStartReceive:function(formId,fileId,name){
		
       Jame.backend.Connection.LoadJsonFromUrl("jame/showReceiveDio.action", function() {}, ({fileId:fileId,toPersonId:formId,personId:personid,fileName:name}));
    },

    /**
     * 点击接收或拒绝后的提示信息处理,同时通知后台
     * @param formId
     * @param fileId
     * @param name
     * @param flag
     * @param savePath 保存路径
     */
    startReceive:function(formId,fileId,name,flag,savePath){
      if(!Jame.ui.chat.file.recFiles[fileId]){
        Jame.ui.chat.file.recFiles[fileId] = {flag:flag};
        var msg = "";
        if (flag) {
          msg = "您同意接收文件[" + name + "],开始传送";
          Jame.ui.chat.file.recFiles[fileId]=name;
          Jame.ui.chat.file.savePaths[fileId]=savePath;
          Jame.ui.chat.file.updateFileSendPro(formId,fileId,name,0);
        } else {
          msg = "您拒绝接收文件[" + name + "]";
        }
        Jame.ui.ChatDialog.updateMessages([{to:formId,msg:msg,title:""}], "red");

        var url=Jame.backend.url.chatFileBeforeRec;
        Jame.backend.Connection.LoadJsonFromUrl(url, function() {}, ({toPersonId:formId,fileName:name,fileId:fileId,flag:flag,personId:personid}));
      }
    },
    openFile:function(fileId){
      alert(fileId);
    },

    //下面是发送,接收方共用的方法

    /**
     * 更新文件传送进度条,到100%时提示发送完成
     * @param toId    接收方id
     * @param fileId  文件的唯一标识
     * @param name    文件名
     * @param num     进度 大于0,小于等于1
     */
    updateFileSendPro:function(personId,fileId,name,num){
		
      name=name||Jame.ui.chat.file.sendFiles[fileId]||Jame.ui.chat.file.recFiles[fileId]||"";
      if(num<1&&num>0){
        num=Math.round(num*10000)/10000;
      }
      var proId="filePro_"+fileId+"_"+personId;
      var pro=Ext.getCmp(proId);
      if(!pro){
        var parent = Ext.getCmp("rightTopMsg_" + personId);
        pro = new Ext.ProgressBar({
          text:'传送文件' + name,
          id:proId
//          ,
//          cls:'left-align'
        });
        parent.add(pro);
        parent.doLayout();
      }
	 

      pro.updateProgress(num,"["+Ext.util.Format.ellipsis(name,10)+"]已传送"+(num*100)+"%");
      if(num>=1){
        pro.destroy();
        var str="["+name+"]传送完成";
        if(Jame.ui.chat.file.recFiles[fileId]){
//          <a href='#' onclick='Jame.ui.chat.file.startReceive(\""+formId+"\",\""+fileId+"\",\""+name+"\",false);return false;
          str+="<a href='#' onclick='window.open(\""+Jame.ui. chat.file.savePaths[fileId]+"\")'>保存位置:["+Jame.ui.chat.file.savePaths[fileId]+"]</a>"
        }
        Jame.ui.ChatDialog.updateMessages([{to:personId,msg:str,title:""}],"red");
      }
    },

    /**
     * 从发送方更新传送进度,频繁交服务器是否有问题
     * @param fileId
     * @param sendId
     * @param k
     */
    beforUpdateProcess:function(fileId, sendId, k) {
	  
      Jame.ui.chat.file.updateFileSendPro(sendId,fileId,null,k);
        var url=Jame.backend.url.chatFileUpdatePro;
       Jame.backend.Connection.LoadJsonFromUrl(url, function() {}, ({toPersonId:sendId,proNum:k,fileId:fileId,personId:personid}));
    }
  }
}();