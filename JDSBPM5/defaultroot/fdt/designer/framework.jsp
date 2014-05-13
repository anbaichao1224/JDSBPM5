<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
String contextpath = request.getContextPath() + "/";
%>

<html>

	<head>
		<title>表单设计器</title>

        <link rel="stylesheet" type="text/css" href="../scripts/ext/resources/css/ext-all.css" />
        <link rel="STYLESHEET" type="text/css" href="../css/designer/default.css">
        
        <SCRIPT LANGUAGE="JavaScript" type="text/javascript">
            _js_prefix='<%=contextpath%>js/';
            context='<%=contextpath%>';
        </SCRIPT>

        <script language="javascript" src="../scripts/ext/js/ext-base.js"></script>
        <script language="javascript" src="../scripts/ext/js/ext-all-debug.js"></script>
        <script language="javascript" src="../scripts/designer/js/dhtmlXAll.js"></script>
        <script language="javascript" src="../scripts/designer/js/report/index_ajax.js"></script>
        <style type="text/css">
            html, body {
                font:normal 12px verdana;
                margin:0;
                padding:0;
                border:0 none;
                overflow:hidden;
                height:100%;
            }
            p {
                margin:5px;
            }
            .settings {
                background-image:url(../shared/icons/fam/folder_wrench.png);
            }
            .nav {
                background-image:url(../shared/icons/fam/folder_go.png);
            }
        </style>
		<script type="text/javascript">
        Ext.onReady(function(){

        atableTool=new dhtmlXToolbarObject(document.getElementById('tableTool'),'100%',16,"表单设计器");
        atableTool.setOnClickHandler(onButtonClick);
        atableTool.loadXML("<%=contextpath%>designer/formdesigner/menuconfig/_toolbarutf8.xml")
        $('tableTool').style.height='0px';
        atableTool.showBar();

        aexpressionToolMenu=new dhtmlXMenuBarObject(document.getElementById('expressionToolMenu'),'100%',16,"表单设计器");
        aexpressionToolMenu.setGfxPath("designer/imgs/formDef/");
	    aexpressionToolMenu.loadXML("<%=contextpath%>designer/formdesigner/menuconfig/_menuutf8.xml")
	    aexpressionToolMenu.showBar();

        <%--aexpressionToolToolBar=new dhtmlXToolbarObject(document.getElementById('expressionToolToolBar'),'100%',16,"表单设计器");--%>
        <%--aexpressionToolToolBar.setOnClickHandler(onButtonClick);--%>
        <%--aexpressionToolToolBar.loadXML("<%=contextpath%>designer/formdesigner/menuconfig/_toolbarutf8.xml")--%>
        <%--$('expressionToolToolBar').style.height='0px';--%>
        <%--aexpressionToolToolBar.showBar();--%>

    //var forms=[
             <%--<ww:iterator value="#activityInstId=activityInstId,activityInst.activityDef.allDataFormDef" status="routs">--%>
//			{
//                title: '<wws:property value="name"/>',
//                listeners: {activate: handleActivate},
//                id:'<wws:property value="id"/>',
//                autoLoad: {url: '/jdsbpm/BPMClientFormDisplayAction.action', params: 'formID=<wws:property value="id"/>&activityInstId=<wws:property value="#activityInstId"/>'}
//            }
         <%--<ww:if test="!#routs.last">,</ww:if>--%>
            <%--</ww:iterator>--%>

        //];

        Ext.QuickTips.init();
        var tabs2 = new Ext.TabPanel({
        renderTo: document.body,
        activeTab: 0,
        width:790,
        height:400,
        plain:true,
        defaults:{autoScroll: true}//,
        //items:forms
    });
    var expressionEditor = new Ext.TabPanel({
    region:'center',
    deferredRender:false,
    activeTab:0,
    items:[
    {
        contentEl:'center1',
        title: '公式编辑',
        //closable:true,
        autoScroll:true,
        items:Ext.get('expression')
    },
    {
        contentEl:'center2',
        title: '运行结果',
        autoScroll:true//,
        //items:tabs2
    }
    ]
})

    function handleActivate(tab){
       // alert(tab.title + ' was activated.');
    }


            Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
//            var subViewport = new Ext.Viewport({
//
//            });
            var viewport = new Ext.Viewport({
            layout:'border',
            items:[
                new Ext.BoxComponent({ // raw
                    region:'north',
                    items:[
                        Ext.get('expressionToolMenu') //,
                        //Ext.get('expressionToolToolBar')
                    ] ,
                    el: 'north',
                    height:25
                }),
                {
                    region:'south',
                    split:true,
                    collapsible: true,
                    //title:'属性编辑',
                    autoScroll:true,
                    layout:'border',
                    items:[
                        {
                            width: 200,
                            minSize: 175,
                            maxSize: 400,
                            split:true,
                            region:'west',
                            title:'数据对象',
                            collapsible: true,
                            html:'<div id="processorTree" style="overflow:auto; height:100%;width:250px;border:1px solid #c3daf9;"></div>'
                        },
                        {
                            width: 0,
                            region:'east',
                            html:"<div><p>east</p></div>"
                        },

                        {
                            height: 24,
                            region:'north',
                            items:Ext.get('expressionToolDiv')
                        },
                        {
                            height: 400,
                            minSize: 400,
                            maxSize: 600,
                            region:'center',
                            contentEl:'center1',
                            //title: '公式编辑',
                            //closable:true,
                            autoScroll:true,

                            //items:Ext.get('expression')
                            items:expressionEditor
                        }
                    ],
                    margins:'0 0 0 0'
                },
                {
                    region:'west',
                    id:'west-panel',
                    title:'表单列表',
                    split:true,
                    width: 200,
                    minSize: 175,
                    maxSize: 400,
                    collapsible: true,
                    margins:'0 0 0 0',
                    layout:'accordion',
                    layoutConfig:{
                        animate:true
                    }
//                    ,
//                    items: [{
//                        contentEl: 'west',
//                        title:'已有表单',
//                        html:'<div><p>file tree</p></div>',
//                        border:false,
//                        iconCls:'nav'
//                    }]
                },
                new Ext.TabPanel({
                    region:'center',
                    deferredRender:false,
                    activeTab:0,
                    items:[
                        {
                        contentEl:'center2',
                        title: '表单视图',
                        autoScroll:true,
                        items:tabs2
                       // autoLoad: {url: '/jdsbpm/BPMClientFormDisplayAction.action', params: 'formID=<wws:property value="id"/>&activityInstId=<wws:property value="activityInstId"/>'}
                    }
//                    ,
//                    {
//                        contentEl:'center1',
//                        title: '公式编辑',
//                        //closable:true,
//                        autoScroll:true,
//                        items:Ext.get('expression')
//                    }
                    ]
                })
             ]
        });
        var Tree = Ext.tree;
        var tree = new Tree.TreePanel({
        el:"processorTree",
        //width:100%,
        //height;100%,
        animate:true,
        enableDD:false,
        loader: new Tree.TreeLoader({dataUrl:"objectTree.action?txtSearch=$&crrentIndex=1&currentExpression=$"}),
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
        tree.loader.dataUrl = 'objectTree.action?txtSearch='+node.id + "&crrentIndex=" + new String(node.id.length) + "&currentExpression=" + node.attributes.expression;
        });
        tree.on('click',function(node,e)
        {
                e.stopEvent();
                //HtmlEdit.document.body.innerText = node.id.substr(0,node.id.length - 1);
                HtmlEdit.document.body.innerText = node.attributes.expression.replace('.{**}','');
//                if(node.attributes.description)
//                {
//                    Ext.MessageBox.show({title:'您选择了',
//                        msg:String.format("description:{0}",node.attributes.description)
//                    });
//                }
                //HtmlEdit.innerHTML = node.id.substr(0,node.id.length - 1);
            });

        tree.render();
        //root.expand();



    });




	</script>
	</head>

	<body onclick="document.getElementById('search_suggest').style.display = 'none';">
		<script type="text/javascript" src="../examples.js"></script>
		<!-- EXAMPLES -->
		<!--<div id="west"></div>-->
		<div id="expressionToolToolBar" />
			<div id="expressionToolMenu" />

				<div id="center2"></div>
				<div id="center1"></div>
				<div id="expressionToolDiv">
					<div>
						<div id="tableTool"/></div>
						<%--<div id="dd" style="POSITION: relative"></div>--%>
					</div>
					<div id="north"></div>
					<div class="txt" id="selectPersonAjax" style="display:none"></div>
					<div class="txt" id="routelog" style="display:none"></div>
					<table height="400" id="expression">
						<tr>
							<td width=30 align="right" valign="top">
								<div id="linenum"
									style="POSITION: relative;left:0;align:right;valign:top;font-size:12pt;font-weight:bolder;color:#0000ff;margin-top:2;line-height:16px;width:100%;height:100%;overflow:hidden">
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
							<td valign="top">
								<iframe onfocus="//addScroll()" onblur="" onload="addScroll()"
									name="HtmlEdit" id="HtmlEdit" MARGINHEIGHT="1" MARGINWIDTH="1"
									width="790" height="400" frameborder="0"
									style="border:1px solid gray">
								</iframe>
							</td>
							<%--<td>--%>
								<%--<input type="button" class="inputbutton" onClick="doSearch();"--%>
									<%--value="查     询" style="cursor:'hand';">--%>
								<%--<br>--%>
							<%--</td>--%>


						</tr>
					</table>

	</body>
	<script language="javascript">
    function doSearch(reload) {
	    var r = HtmlEdit.document.selection.createRange();
	    var str=r.text;
	    if(!str){
	      str=HtmlEdit.document.body.innerText;
	    }
        $('search_suggest').style.display = 'none';
       // str=str+"&activityInstId="+$('activityInstId').value;
       // alert(str);
        loadAjaxSearch(str,reload);
    }
</script>
</html>
