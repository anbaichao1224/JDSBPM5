<%@ page language="java" errorPage="/error.jsp" pageEncoding="utf8" %>
<html>
	<head>
		<title>demo</title>
        <link rel="stylesheet" type="text/css" href="/usm/resources/css/ext-all.css" />
        <script type="text/javascript" src="/usm/js/ext-base.js"></script>
  		<script type="text/javascript" src="/usm/js/ext-all.js"></script>
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
            div{text-align:left}
        </style>
      
        <!--<script language="javascript" src="../scripts/ext/js/locale/ext-lang-zh_CN.js"></script>        -->
        <script language="javascript" src="../../common/js/tree.js"></script>
        <script language="javascript" src="../../common/js/layout.js"></script>
	</head>

	<body>
        <div id="leftpanel">
        </div>
        <div id="wookbook">
        </div>
    </body>
</html>

<script type="text/javascript">
    Ext.onReady(extInit);
    Ext.onReady(initLayout);
    Ext.onReady(genTree);

    function genTree()
    {
        if(TreeData.tree)
        {
            resetObjDiv(TreeData.tree,"usmtree");
            TreeData.tree = null;
        }
        var layoutDataUrl;
        constructLayoutTree(layoutDataUrl);
        //var obj = Ext.getCmp('usmtreePanel');
        //obj.expand();
    }
    function extInit()
    {
        Ext.QuickTips.init();
    }
</script>
