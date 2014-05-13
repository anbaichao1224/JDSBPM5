<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>

<%
String contextpath = request.getContextPath() + "/";
String urlContext=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextpath;
%>


<html>

	<head>
		<title>表单数据绑定工具</title>


		<link rel="STYLESHEET" type="text/css"
			href="<%=contextpath%>fdt/designer/css/Context.css">

		<link rel="STYLESHEET" type="text/css"
			href="<%=contextpath%>fdt/designer/css/search.css">
		<link rel="stylesheet" type="text/css"
			href="<%=contextpath%>js/ext/resources/css/menus.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
		<link rel='stylesheet' type='text/css' href='<%=contextpath%>fdt/designer/css/dhtmlXMenu_xp.css'>
        <link rel='stylesheet' type='text/css' href='<%=contextpath%>fdt/designer/css/dhtmlXToolbar.css'>
        <style type="text/css">

        </style>

        <SCRIPT LANGUAGE="JavaScript" type="text/javascript">
            _js_prefix='<%=contextpath%>js/';
            context='<%=contextpath%>';
            var _urlContext="<%=urlContext%>";

        </SCRIPT>
        <script  src="<%=contextpath%>js/prototype.js" type="text/javascript"></script>
		<script type="text/javascript" src="<%=contextpath%>js/extAll.js"></script>
	      <script language="javascript" src="<%=contextpath%>js/dhtmlXAll.js"></script>
        <script language="javascript" src="<%=contextpath%>fdt/designer/js/menuimpl/menuImplement.js"></script>
        <script language="javascript" src="<%=contextpath%>fdt/designer/js/data/layouttree.js"></script>
        <script language="javascript" src="<%=contextpath%>fdt/designer/js/ajax/dhtmlXGrid_ajaxsearch.js"></script>
        <script language="javascript" src="<%=contextpath%>fdt/designer/js/ajax/indexAjax.js"></script>
        <script language="javascript" src="<%=contextpath%>fdt/designer/js/itemarr/itemarr.js"></script>
        <script language="javascript" src="<%=contextpath%>fdt/designer/js/layout/layoutAll.js"></script>
        <script language="javascript" src="<%=contextpath%>fdt/designer/js/ajax/ajaxJSONPar.js"></script>

	</head>

	<body onload="allInit()" >

        <div id="north"></div>
        <div id="tableTool"/>
        <div id="expressionToolMenu" />
        <div id="dd" style="POSITION: relative;"></div>
        <div id="attsPanel" style="POSITION: relative;display:none;">
         <div id="div2"></div>
        </div>
        <div id="center1"></div>
        <div id="center2"></div>

        <!--<div id="expressionEditorWinPanle"></div>-->
        <table height="350" width="100%" id="expression" border="0" align="left" style="display:none;">
            <tr>
                <td width=30 align="right" valign="top">
                    <div id="linenum"
                        style="POSITION: relative;left:0;text-align:right;align:right;valign:top;font-size:12pt;font-weight:bolder;color:#0000ff;margin-top:2;line-height:16px;width:100%;height:100%;overflow:hidden">
                        <script language="javascript">
                            var tmpLineStr="";
                            for(var i=1;i<=200;i++)
                            {
                                tmpLineStr+=i+"<br>";
                            }
                            document.write(tmpLineStr);
                        </script>
                    </div>
                </td>
                <td valign="top" id="iframetd">
                   <iframe  onblur="" onload="addScroll()"
                        name="HtmlEdit" id="HtmlEdit" MARGINHEIGHT="1" MARGINWIDTH="1"
                        width="100%" height="350" frameborder="0"
                        style="border:1px solid gray">
                    </iframe>
                </td>
            </tr>
        </table>

        <INPUT id="activityInstId" type="hidden"  name="activityInstId" value="<ww:property value="activityInstId"/>">
        <INPUT id="formId" type="hidden"  name="formId" value="<ww:property value="formId"/>">

        <INPUT id=serverAddr type=hidden value="" name=serverAddr>
        <INPUT id=fileName type=hidden value="<ww:property value="fileName"/>" name=fileName>
        <INPUT id="activityDefId" type="hidden" value="<ww:property value="activityDefId"/>&processDefId=<ww:property value="processDefId"/>" name="activityDefId">
  </body>
</html>

		<script type="text/javascript">

    //公式编辑窗口层
    var expressionEditorWin;
    var hasTree=<ww:property value="hasTree" default="true"/>;

    var expressionTreesCfg=[
      <ww:iterator value="esbType" status="routs">
      <ww:if test="value.type!='map'">
        {
        cnname:'<ww:property value="value.cnname"/>',
        type:'<ww:property value="key"/>'
        } <ww:if test="!#routs.last">,</ww:if>
      </ww:if>
      </ww:iterator>
    ];


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
                        height:700,
                      //width:800,
                      autoWidth:true,
                      id:"expTab",
                        items:
                        [
                            Ext.get('expression')//,
                        ]
                    },
                    {
                        title: '运行结果',
                        height:700,
                      autoWidth:true,
                        html:"<div id='ssdebug' style='border:0;width:650;text-align:left'></div>"
                    }
                ]
            });
           var winCfg={
                    id:'expressionEditorWin',
                    title:'公式编辑',
                    layout:'border',
                    closeAction :'hide',
                    maximizable:true,
                   // plain:true,
                    width:850,
                    height:600,
                    //minWidth:850,
                    //maxWidth:900,
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
//          if(true){
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

		var expStr="<ww:property value="expStr"/>";
    var okFn=function(val){
      alert(val);
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

          <%--Ext.onReady(<ww:property value="(buswin==null || buswin=='') ? 'reportPanelInit' : 'openExpressionEditor'"/>);--%>

    function allInit() {
      iframeInit();
     // openExpressionEditor(okFn);
      <ww:property value="(buswin==null || buswin=='') ? 'reportPanelInit()' : 'openExpressionEditor(expStr)'"/>
    }

        </script>