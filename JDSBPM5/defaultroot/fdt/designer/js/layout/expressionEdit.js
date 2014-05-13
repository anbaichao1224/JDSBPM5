var currform;
var viewport;

//window.onerror=function(a,b,c,d){
//alert('ffff');
// return true;
//};
//



    //显示公式编辑窗口
    function openExpressionEditor(val,okFn)
    {
        if(!expressionEditorWin )
        {

      var trees=[];
    if(expressionTreesCfg.length==0){
      trees=[{title:"没有数据",html:"",border:false}];
    }else{
      for(var i=0;i<expressionTreesCfg.length;i++){
        var cfg=expressionTreesCfg[i];
        var tmp={};
        tmp.title=cfg.cnname;
        tmp.html='<div id="treeDiv_'+cfg.type+'" style="overflow:auto; height:100%;border:1px solid #c3daf9;"></div>'
        tmp.border=false;
        tmp.defaults={autoScroll: true};
        tmp.iconCls="nav";
        trees.push(tmp);
      }
    }
            $('expression').style.display = '';
            var editorTabs = new Ext.TabPanel({
                id:'editAreaTabs',
                deferredRender:false,
                activeTab:0,
                autoWidth:true,
                items:[
                    {
                      title: '公式编辑',
                      autoHeight:true,
                      autoWidth:true,
                      id:"expTab",
                        items:
                        [
                            Ext.get('expression')//,
                        ]
                    },
                    {
                        title: '运行结果',
                       // height:700,
					   defaults:{autoScroll: true},
                         autoWidth:true,
					    autoHeight:true,
                        html:"<div id='ssdebug' overflow:auto; height:100%;border:1px solid #c3daf9; ></div>"
                    }
                ]
            });
           var winCfg={
                    id:'expressionEditorWin',
                    title:'公式编辑',
                    layout:'border',
                   // closeAction :'hide',
                   // maximizable:true,
                    width:850,
                    height:600,
                    items:
                    [
                        {

              listeners:{
                "resize":resizeMainPanel
              },

                            region:'center',
                            split:true,
                            maxWidth:700,
                            items:editorTabs,
                            buttons:[{text:'查 询',handler:function(){
                                $('ssdebug').innerHTML = '';
                                Ext.getCmp('editAreaTabs').activate(1);
                                doSearch(true);
                              curRange=null;
                            }},
                            {text:'确 定',handler:function(){
//								 expressionEditorWin.selfobj.setValue(HtmlEdit.document.body.innerText);
//								setTmpValue(expressionEditorWin.selfobj.name,HtmlEdit.document.body.innerText);
                              if(okFn){
                                okFn(HtmlEdit.document.body.innerText);
                                expressionEditorWin.hide();
                                curRange=null;
                              }
                            }},
                            {text:'取消',handler:function(){expressionEditorWin.hide();curRange=null;}}]
                        }

                    ]
                };
          if(hasTree){
            winCfg.items.push({
                            region:'west',
                            width:250,
                            autoScroll:true,
                            collapsible: true,
                            title:'数据对象',
                            split:true,
                            layout:'accordion',
                            layoutConfig:{
                                animate:true
                            },
                            items: trees
                        });
          }
             expressionEditorWin = new Ext.Window(winCfg);
			       expressionEditorWin.on("hide",function(){
			                                            var g=Ext.getCmp("ssdebug"+"GridPanel");
			                                            if(g){
			                                                resetObjDiv(g,"ssdebug");
			                                            }
			                                          }
			                                   );

             expressionEditorWin.show();
             expressionEditorWin.maximize();
			       var Tree = Ext.tree;

           function getTree(currel,treeType){
              var tree = new Tree.TreePanel({
			        el:currel,
			        animate:true,
			        enableDD:false,
			        loader: new Tree.TreeLoader({dataUrl:"/fdt/designer/objectTree.action?txtSearch=$&crrentIndex=1&currentExpression=$&treeType="+treeType}),
			        lines: true,
			        selModel: new Ext.tree.MultiSelectionModel(),
			        containerScroll: true,
			        autoHeight:true,
			        rootVisible:false
			        });
			        var root = new Tree.AsyncTreeNode({
			        text: '对象',
			        leaf : false,
			        cls : 'folder',
			        draggable:false,
			        id:'$',
			        expression:"$"
			        });
			        tree.setRootNode(root);
			        tree.on('beforeload', function(node){
			        tree.loader.dataUrl = '/fdt/designer/objectTree.action?txtSearch='+node.id + "&treeType="+treeType+"&crrentIndex=" + new String(node.id.length) + "&currentExpression=" + node.attributes.expression;
			        });
			        tree.on('click', function(node, e)
              {
                e.stopEvent();
			                //HtmlEdit.document.body.innerText = node.attributes.expression.replace('.{**}','');

                setCurEditAllHTML(node.attributes.expression.replace('.{**}', ''));
              });

			        tree.render();
			    }
          if(hasTree){
            for(var i=0;i<expressionTreesCfg.length;i++){
              var cfg=expressionTreesCfg[i];
              var tmpTree=getTree("treeDiv_"+cfg.type,cfg.type);
            }
          }
        }
        else
        {
            var g=Ext.getCmp("ssdebug"+"GridPanel");
            if(g){
                resetObjDiv(g,"ssdebug");
            }
            Ext.getCmp('editAreaTabs').activate(0);

            expressionEditorWin.show();
            expressionEditorWin.syncSize();
        }

      if (val) {
        setTimeout(function() {
          setCurEditAllHTML(val)
        }, 1);
      }
    }

//重置指定div,销毁其中的对象
function resetObjDiv(obj,divId){
if(obj){
  if(obj.destroy){
    var div=document.getElementById(divId).cloneNode(false);
    var pDiv=document.getElementById(divId).parentNode;
    obj.destroy();
    //obj.getEl().createChild({tag:"div",id:id});
    pDiv.appendChild(div);
  }else{
    document.getElementById(divId).innerHTML="";
  }
}
}

 function resizeMainPanel(obj, w1, h1, w2, h2) {
      document.getElementById("HtmlEdit").width = w1 - 50;
      var panel = Ext.getCmp('ssdebugGridPanel');
      if (panel) {
       panel.setSize(w1-3,h1-68);
      }
    }

    //初始化操作
    Ext.onReady(extInit);
    function allInit() {
      iframeInit();
      openExpressionEditor(expStr)
   
    }


