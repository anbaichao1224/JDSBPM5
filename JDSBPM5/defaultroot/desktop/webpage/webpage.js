//生成ext的树状菜单 ,需用户控制render目标
  Ext.ns("webpage");
webpage.mainPanel = function(){
  var mainPanel=null;
  function getMainPanel(){
    if(!mainPanel){
      mainPanel=Ext.getCmp("webpage_mainWin").mainPanel;
    }
    return mainPanel;
  }
  return{
    mask:function() {
      mainPanel =getMainPanel();
      mainPanel.getEl().mask("页面加载中...");
//      mainPanel.getEl()._mask.setStyle("background-image",'url("/desktop/resources/images/download.png")');
    },unmask:function(){
      getMainPanel().getEl().unmask();
    }
  }
}();

  webpage.menutree = function() {
    var headHtml="";
    var headImgDivId="topImgDiv";
    var headHeight=102;
    headHtml+=
'<table id="Table_01" width="100%" border="0" cellpadding="0" cellspacing="0" align="center" background="/desktop/webpage/images/bj.jpg">'
+'	<tr>'
+'		<td background="/desktop/webpage/images/hstt_01.jpg" height="76">'
+'		</td>'
+'	</tr>'
+'	<tr>'
+'		<td background="/desktop/webpage/images/hstt_02.jpg" id="topImgDiv" height="24">'
+'   </td>'
+'	</tr>'
+'</table>';

    function openWindowById(url,type) {
      if(type=="panel"){
        Ext.getCmp("webpageFrameMain").hide();
        Ext.getCmp("webpagePanelMain").show();
        document.getElementById("webpageMainFrame").src="";

        var win=null;
        if(url=="vgraph"){
          win=vgraph.showGraph("webpagePanelMain");
        }else{
          win=new Ext.Panel({
            html:"测试一下"
          });
        }

        var panel=Ext.getCmp("webpagePanelMain");
        panel.add(win);
        panel.doLayout();

        win.setSize(panel.getInnerWidth(),panel.getInnerHeight());
        var is=panel.items;
        if(is){
          for(var i=0;i<is.length;i++){
            is.get(i).hide();
          }
        }

        win.show();
        return;
      }else{
webpage.mainPanel.mask();
        Ext.getCmp("webpageFrameMain").show();
        Ext.getCmp("webpagePanelMain").hide();
        document.getElementById("webpageMainFrame").src=url;
        alert("aa")
webpage.mainPanel.unmask();
      }
    }

    var createMainWin = function(treeCfg) {
      var win=Ext.getCmp("webpage_mainWin");
  if(!win){
    var cfg = {
      id:"webpage_mainWin",
            resizable:false,

      closable:false,
      plain:true,
      border:false,
      //		iconCls : "available",
      // iconCls: 'accordion',
      layout:'border',
      items:[
        {
          region:"north",
          layout:"fit",
//          split:true,
//          collapsible:true,
          height:headHeight,
          html:headHtml
        },
        {
          region:"west",
          split:true,
          id:"webpage_menuTreePanel",
          layout:"fit",
          collapsible:true,
          width:160
//          height:400

//          items:[tree]
        },{
        region:"center",
        layout:"fit",
        id:"webpage_mainPanel"

              ,items:[
//        {
//          html:"<div id='webpageMainDiv' style='width:0;height:0'></div><iframe name='webpageMainFrame' id='webpageMainFrame' border=0 frameborder=0 src='' width='100%' height='100%'></iframe>"
//        }
        {
          id:"webpagePanelMain"
        },{
          id:"webpageFrameMain",
          html:"<iframe name='webpageMainFrame' id='webpageMainFrame' border=0 frameborder=0 src='' width='100%' height='100%'></iframe>"
        }
              ]
      }
      ]
    };
    win = new Ext.Window(cfg);

    win.on("resize", function(window, w, h) {
      var p = window.findById("webpage_mainPanel");
      p.setSize(window.getInnerWidth(),window.getInnerHeight());
      if (p && p.items) {
        for (var i = 0; i < p.items.length; i++) {
          var obj = p.items.get(i);
          if (obj.resizedHandler) {
            obj.resizedHandler(w, h);
          }else{
//            obj.setSize(window.getInnerWidth(),window.getInnerHeight());
            //obj.setSize(p.getInnerWidth(),window.getInnerHeight());
            obj.setHeight(window.getInnerHeight());
          }
        }
      }
    });

    win.show();
    win.mainPanel=win.findById("webpage_mainPanel");

    var trees=createMenuTree(treeCfg);
    var panel=win.findById("webpage_menuTreePanel");
    for(var i=0;i<trees.length;i++){
      panel.add(trees[i]);
    }

    //tree.hide();
  }
  webpage.menutree.win = win;
  win.show();
  win.maximize();
//  this.showQuery();
//  win.queryGrid=grid;

  return win;
}
    var showTreeMenu=function(id){
      var panel=webpage.menutree.win.findById("webpage_menuTreePanel");
      var is=panel.items;
      for(var i=0;i<is.length;i++){
        var o=is.get(i);
//        alert(id+"==="+o.id)
        if(o.id==id){
          o.show();
        }else{
          o.hide();
        }
      }
    }
var imgBasePath="/";
    var createMenuTree = function (cfg) {
      if (!cfg) {
        alert("配置为空");
        return;
      }
      var rtn=[];
      var imgStr="";
      for(var i=0;i<cfg.length;i++){
        var o=cfg[i];
//        imgStr+="<img src='"+imgBasePath+o.id+".gif' alt='"+o.text+"' onclick='webpage.menutree.showTreeMenu(\""+o.id+"\")'>";
        imgStr+="<img src='"+o.icon+"' alt='"+o.text+"' onclick='webpage.menutree.showTreeMenu(\""+o.id+"\")'>";
        rtn.push(createTree(o));
      }
      document.getElementById(headImgDivId).innerHTML=imgStr;
      return rtn;
    }
var createTree=function(cfg){
      var tree = new Ext.tree.TreePanel({
        id:cfg.id,
        enableDD:false,
        animate:true,
//        rootVisible:false,
        lines:false,
        border:false,
        useArrows: true,
        // auto create TreeLoader
        loader:new Ext.tree.TreeLoader(),
        autoScroll:true
      });

      var root = new Ext.tree.AsyncTreeNode({
        text: cfg.text,
        draggable:false,
        id:"root_"+cfg.id,
        children: cfg.children
      });


      tree.setRootNode(root);
      tree.on("click",function(node){
        if(node.isLeaf()){
          openWindowById(node.attributes.urlStr,node.attributes.urlType);
        }else{
          node.toggle();
        }
      });
      return tree;
    }

    return {
      createMainWin:createMainWin,
      showTreeMenu:showTreeMenu
    }
  }();

