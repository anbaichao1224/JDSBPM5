<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2010-1-21
  Time: 17:59:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="ww" %>
<%
  String contextpath = request.getContextPath() + "/";
%>
<html>
<head><title>Simple jsp page</title>

  <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css"/>
  <script type="text/javascript" src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
  <script type="text/javascript" src="<%=contextpath%>js/ext/ext-all-debug.js"></script>
  <script type="text/javascript" src="<%=contextpath%>js/ext/locale/ext-lang-zh_CN.js"></script>

  <style type="text/css">
    .maskbigimg{
      background-image:url( "/desktop/resources/images/download.png" );
    }
  </style>

  <script type="text/javascript">
    var mxBasePath = '<%=contextpath %>desktop/widgets/bpm/viewroute/graph/resources/';
  </script>
  <script type="text/javascript" src="<%=contextpath %>desktop/widgets/bpm/viewroute/graph/resources/js/mxclient.js"></script>
  <script type="text/javascript" src="<%=contextpath %>desktop/widgets/bpm/viewroute/graph/js/graph.js"></script>

  <script type="text/javascript" src="<%=contextpath%>hstc/js/webpage.js"></script>
  <script type="text/javascript" src="<%=contextpath%>hstc/js/hstcgraph.js"></script>
  <script type="text/javascript">
    <%--var treeData =<ww:property value="jsonStr" escape="false"/>;--%>

    function extInit() {
      Ext.BLANK_IMAGE_URL = '/js/ext/resources/images/default/s.gif';
      Ext.form.Field.prototype.msgTarget = 'side';
      Ext.QuickTips.init();
    }

    function initPage() {
      extInit();

//      alert("init")
     var  treeData=[
        {id:"abc",text:"根一",children:[
          {id:"abc_1",text:"子一",leaf:true,urlStr:"vgraph"},
          {id:"abc_2",text:"子2",leaf:true}
        ]},{
        id:"abc2",text:"根2",children:[
        {id:"abc2_1",text:"子21",leaf:true},
        {id:"abc2_2",text:"子22",leaf:true}
        ]

        }
      ];

      //加vdc管理 的菜单
      treeData.push(
      {id:"vdcRoot",text:"vdc管理",children:[
        {id:"vdc_1",text:"vdc管理1",leaf:true,urlStr:"vgraph",urlType:"panel"}
      ]   }
          );
      var win=webpage.menutree.createMainWin(treeData);
      
//      var tree = webpage.menutree.createMenuTree(treeData);
//      var win = new Ext.Window({
//        width:400,
//        height:500,
//        title:"asdk",
//        items:[
//          {html:'aaasdkfl'}
//        ]
//      });
//      alert("aaaa")
//      win.show();
//      alert('bbbbb')
    }
    Ext.onReady(initPage);
  </script>

</head>
<body>
</body>
</html>