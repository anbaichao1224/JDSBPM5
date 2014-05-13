Ext.ns("JDS");

/**
 * 外部引用格式
 * JDS.create.createView（cfg)
 * 此方法接受一个json格式的配置参数，返回一个Viewport对象
 * cfg 格式
 *
 *
 * {
 *   imgCfg:[{},{}...],//图片数据 或 一个返回此数据的url
 *   treeCfg:{ //左侧的treepanel的参数
 *     title:"",
 *     ...
 *   }
 * }
 * 其中imgCfg数组子项的格式为:
 * {
 * src:"",
 * alt:"",
 * width:"",
 * height:"",
 * treeUrl:""， //点击图片时，左边要显示的树数据url
 * text:"",     //图片下的文字
 * textStyle:"" //图片下的文字的css样式串
 * isDefault:true  or false ////页面加载后,默认显示的树等
 * defaultPanelUrl:"" //图片切换时的默认显示panel配置url,此url应该和对应的tree node中的panelUrl一致
 * ...
 * }
 *
 * 使用例子
 */  
/*
 function init(){
      var imgs=[
        {
          src:"/usm/images/index.png",
          width:260, height:69
        },
        {src:"/usm/images/01.gif",
          alt:"应用资源管理",
          text:"应用资源管理",
           treeUrl:"/usm/systemLeftMenus.do?menuid=system",
          isDefault:true,
           defaultPanelUrl:"expression.jsp?expression=$usmMain&esbkey=OrgView&ftl=panel.ftl"
        },
        {src:"/usm/images/02.gif",
          alt:"通讯录管理",
          text:"通讯录",
          textStyle:"font-size:10px;",
          treeUrl:"usm/systemLeftMenus.do?menuid=usm",
          defaultPanelUrl:""
        }
      ];
      var allCfg={
        imgCfg:imgs,
        treeCfg:{
          title:"aaa"
        }
      }
      JDS.create.createView(allCfg);
    }

*/
 function init(){
 	JDS.create.createView({imgCfg:'/index_top.html'});
 }
 
 
 
Ext.QuickTips.init();
Ext.form.Field.prototype.msgTarget = 'side';

var sm = new Ext.grid.CheckboxSelectionModel({handleMouseDown:Ext.emptyFn});
var rowNum = new Ext.grid.RowNumberer({
				header : '序号',
				width : 32,
				align : 'center'
			});

/**
 * 创建布局
 */
var viewport;

var USMContext={
   esbkey:''
}
var childPanelMap=new Ext.util.MixedCollection(); 
var viewPanelMap=new Ext.util.MixedCollection(); 
 
 
var getChildPanel=function(esbkey,path,parentPath,uuid,callbackevent){
    var childpanel;
    var url="/expression.jsp";
    var params="expression=$usmMain&esbkey="+esbkey+'&path='+path;
    var parentPanel= childPanelMap.get(parentPath);
    if (parentPanel && parentPanel.uuid){
       uuid=parentPanel.uuid;
    }
    var callback=function(o){
      try{
        var ChildPanel=new Function(o);
        var childpane= ChildPanel({uuid:uuid,renderTo:path,parentPath:parentPath});
        if (parentPath==USMContext.esbkey){
             viewPanelMap.add(path,childpane);
             childpane.setHeight(viewport.getSize().height);
         }else{
          var parentPanel=childPanelMap.get(parentPath);
          childpane.setSize({width:parentPanel.getSize().width,height:parentPanel.getSize().height-20});
        }
        childPanelMap.add(path,childpane);
        }catch(e){
          alert(path);
          JDS.alert(e);
        }
       if (callbackevent){
		  callbackevent.call(parentPanel,childpane);
		}
    }
    JDS.ajax.Connection.LoadJsonFromUrl(url,callback,params);
    
}
 


JDS.create = function() {

  var leftTreeId="forum-tree";
  var allDefault={};

  function errorFn() {
    alert("请求出错");
  }
 
  function showPanelByUrl(purl,node){
   //    purl='/usm2/main.jsp?esbkey=OrgView'
   
  	   var cp = Ext.getCmp("vcPanel");
	    var callback=function(txt){
			try{
			var ChildPanel=new Function(txt);
			var p= ChildPanel({});
			 viewport=p;
			}catch(e){
			JDS.alert(e);
			}
		      cp.remove(
		          cp.findBy(function() {
		            return true;
		          })[0]
		          , true);
		      cp.add(p);
		      p.setSize(cp.getInnerWidth(), cp.getInnerHeight());
		      cp.doLayout();
			  cp.getEl().unmask();
		    }
			cp.getEl().mask("正在装载数据。。。"); 
	   JDS.ajax.Connection.LoadJsonFromUrl(purl,callback);
  }

  function leftTreeClick(node) {
    var purl = node.panelUrl || node.attributes.panelUrl;
    if (!purl) {
      if(node.isLeaf()){
        alert("没有对应panel的url");
//        okFn.apply(node, []);
      }
      return;
    }
    showPanelByUrl(purl,node);
  }
  function showLeftTree(url,id,dUrl) {
    var tree = Ext.getCmp(id);
    var loader = tree.getLoader();
    loader.url = url;
    tree.root.reload();
    tree.root.expand(false, true);
    if(dUrl){
      showPanelByUrl(dUrl);
    }
  }

  function getHeaderHtmlStr(imgCfg){
    var str="";
    for (var i = 0; i < imgCfg.length; i++) {
      var im = imgCfg[i];
      var treeUrl = im.treeUrl || "";
      var txt = im.text || "";
      var txtStyle = im.textStyle || "";
      var dUrl=im.defaultPanelUrl||"";
      var isDef=im.isDefault===true;
      delete im.treeUrl;
      delete im.text;
      delete im.textStyle;
      delete im.defaultPanelUrl;
      delete im.isDefault;
      if(isDef){
        allDefault.treeUrl=treeUrl;
        allDefault.leftTreeId=leftTreeId;
        allDefault.dUrl=dUrl;
      }
      str += "<div style='text-align: center;margin: 0px;float: left;display:block;left:0;top:0;'";
      if (treeUrl) {
        str += " style='cursor:pointer' onclick=\"JDS.create.showLeftTree('" + treeUrl + "','"+leftTreeId+"','"+dUrl+"')\"";
      }
      str+="><img ";
      for (var k in im) {
        str += k + "='" + im[k] + "' ";
      }
      str += ">";
      if (txt) {
        str += "<br><span style='" + txtStyle + "'>" + txt + "</span>";
      }
      str += "</div>";
    }
    var divStr = '<div id="headImgDiv" style="text-align: left;margin: 0px;left:0;top:0;">'
        + str + "</div>";
    return divStr;
  }
  /**
   *
   * @param allCfg
   *
   * 格式
   * {
   * imgCfg:[{},{}...]
   * 数组子项的格式为:
   * {
   *  src:"",
   * alt:"",
   * width:"",
   * height:"",
   * treeUrl:""
   * ...
   * },
   * treeCfg:{
   *  title:""...
   * }
   */

  function createView(allCfg) {
    allDefault={};
    var imgCfg = allCfg.imgCfg;
    var divStr="";
    if(typeof(imgCfg)=="string"){

    }else{
//      divStr=getHeaderHtmlStr(imgCfg);
    }

    var tCfg = Ext.apply({}, allCfg.treeCfg || {}, {
      id:leftTreeId,
      bodyStyle:'background:url(/usm2/images/tree.jpg) repeat;',
      region:'west',
      title:'资源管理中心',
      split:true,
      width: 225,
      minSize: 175,
      maxSize: 400,
      collapsible: true,
      margins:'0 0 0 5',
      animCollapse:false,
      animate: true,
      collapseMode:'mini',

      loader: new Ext.tree.TreeLoader({
        url: ''
      }),
      rootVisible:false,
      lines:false,
      autoScroll:true,
      root: new Ext.tree.AsyncTreeNode({
        id:'root',
        draggable:false,
        text: '功能操作'
      })
    });

    var tree = new Ext.tree.TreePanel(tCfg);
    tree.on("click", leftTreeClick);
    tree.on("beforeload", function(n) {
      return !!(tree.loader.url);
    })

    var cfg = {
      id:'mainviewport',
      layout:'border',
      items:[
        new Ext.Panel({
          region:'north',
          frame:true,
          id:"vheadPanel",
          html:divStr,
      listeners:{
        render:function(obj){
          if(divStr){
            if(allDefault.treeUrl){
              showLeftTree(allDefault.treeUrl, allDefault.leftTreeId, allDefault.dUrl);
            }
          }else{
            Ext.Ajax.request({
              url: imgCfg,
              success:function(rs) {
                var cfg = Ext.decode(rs.responseText);
//            var cfg=imgCfg;
                var tmpStr = getHeaderHtmlStr(cfg);
                obj.body.dom.innerHTML = tmpStr;
                if (allDefault.treeUrl) {
                  showLeftTree(allDefault.treeUrl, allDefault.leftTreeId, allDefault.dUrl);
                }
              },
              failure: errorFn
            });
          }
        }
      },
          height:90
        }),
        tree,
        new Ext.Panel({
          region:'center',
          id:'vcPanel',
          listeners:{
            resize:function(cp) {
              var p = cp.findBy(function() {
                return true;
              })[0];
              if (p) {
                p.setSize(cp.getInnerWidth(), cp.getInnerHeight());
              }
            }
          },
          items:[
            new Ext.Panel(
//            allCfg.defaultPanelCfg||
            {
              border:false
            })
          ]

        })
      ]
    };
    return  new Ext.Viewport(cfg);;
  }


  //------------------------------
  /**
   * 根据传入的panel配置生成对应的window
   * @param cfg
   */
  function createWinByPanelCfg(cfg){
    var wCfg=null;
    var pCfg=null;
    if(cfg.winCfg){
      wCfg=cfg.winCfg;
      pCfg=cfg.panelCfg;
    }else{
      wCfg={
        title:cfg.title,
        width:cfg.width,
        height:cfg.height
      }
      delete cfg.title;
      delete cfg.width;
      delete cfg.height;
      pCfg=cfg;
    }
    var p=new Ext.Panel(pCfg);
    wCfg.items=[p]
//    wCfg.minimizable=true;
    wCfg.maximizable=true;
    var win=new Ext.Window(wCfg);
    win.on("resize",function(w){
      p.setSize(w.getInnerWidth(),w.getInnerHeight());
    })
    win.on("show",function(w){
      var wh=w.getPosition();
      if(wh[0]<0||wh[1]<0){
        win.maximize();
        var tw=win.getSize().width-80;
        var th=win.getSize().height-80;
        win.restore();
        win.setSize(tw,th);
        win.center();
      }
    });
    win.show();
  }
  return {
    createView:createView,
    showLeftTree:showLeftTree,
    createWinByPanelCfg:createWinByPanelCfg
  }
}();



function creatPanelByClass(esbkey,map){
       var str= 'esbkey='+esbkey;
	   var url="expression.jsp?expression=$usmMain";
	    var callback=function(o){
	           Ext.namespace("EVAL");
	              eval(o);
	          var config=EVAL.getConfig();
			  var panel=EVAL.getPanel(); 
	          var  winconfig={
			  	width:config.width+10,
				height:config.height,
				maximizable:true,
				items:[panel]
			};
			   try{
				   var win = new Ext.Window(winconfig);
				    win.on("resize",function(w){
				      panel.setSize(w.getInnerWidth(),w.getInnerHeight());
				    })
				    win.on("show",function(w){
				      var wh=w.getPosition();
				      if(wh[1]<0){
					  	var width=win.getSize().width;
				        win.maximize();
				        var tw=width;
				        var th=win.getSize().height-80;
				        win.restore();
				        win.setSize(width,th);
				        win.center();
				      }
					   
					  
				    });
				    win.show();
					}catch(e){
						JDS.alert(e);	
					}
				
					
			map.eachKey(function(key){
				panel.setValue(key,map.get(key));
			});	
	         }
			 
			 
	   JDS.ajax.Connection.LoadJsonFromUrl(url,callback,str,this)
}
