<%@ page contentType="text/html; charset=utf-8"%>
<jsp:directive.page import="mtgoa.action.PublicJson"/>
<%PublicJson obj=new PublicJson(); 

%>
<html>
<head>
	<title>全区刊物浏览</title>

    <style type="text/css">
    #all-demos {
        width:100%;
        height:100%;
    }
    #all-demos dd {
        float:left;
        width:80px;
        height:20px;
        margin:5px 5px 5px 10px;
        cursor:pointer;
        zoom:1;
    }
    #all-demos dd img {
        width:80px;
        height:20px;
        margin:5px 0 0 5px;
        float:left;
    }

    #all-demos dd div {
        float:left;
        width:130px;
        margin-left:10px;
    }

    #all-demos dd h4 {
        font-family:tahoma,arial,san-serif;
        color:#555;
        font-size:11px;
        font-weight:bold;
    }
    #all-demos dd p {
        color:#777;
    }
    #all-demos dd.over {
        background: #F5FDE3 url(shared/extjs/images/sample-over.gif) no-repeat;
    }
    #loading-mask{
        position:absolute;
        left:0;
        top:0;
        width:100%;
        height:100%;
        z-index:20000;
        background-color:white;
    }
    #loading{
        position:absolute;
        left:45%;
        top:40%;
        padding:2px;
        z-index:20001;
        height:auto;
    }
    #loading a {
        color:#225588;
    }
    #loading .loading-indicator{
        background:white;
        color:#444;
        font:bold 13px tahoma,arial,helvetica;
        padding:10px;
        margin:0;
        height:auto;
    }
    #loading-msg {
        font: normal 10px arial,tahoma,sans-serif;
    }
    
    #all-demos .x-panel-body {
        background-color:#fff;
        border:1px solid;
        border-color:#fafafa #fafafa #fafafa #fafafa;
    }
    #sample-ct {
        border:1px solid;
        border-color:#dadada #ebebeb #ebebeb #dadada;
        padding:2px;
    }

    #all-demos h2 {
        border-bottom: 2px solid #99bbe8;
        cursor:pointer;
        padding-top:6px;
    }
    #all-demos h2 div {
        background:transparent url(/usm/resources/images/default/grid/group-expand-sprite.gif) no-repeat 3px -47px;
        padding:4px 4px 4px 17px;
        color:#3764a0;
        font:bold 11px tahoma, arial, helvetica, sans-serif;
    }
    #all-demos .collapsed h2 div {
        background-position: 3px 3px;
    }
    #all-demos .collapsed dl {
        display:none;
    }
    .x-window {
        text-align:left;
    }
    </style>
</head>
<body>
<div id="loading-mask" style=""></div>
<div id="loading">
    <div class="loading-indicator"><img src="/desktop/resources/images/loading2.gif" width="32" height="32" style="margin-right:8px;float:left;vertical-align:top;"/>全区刊物 - <br /><span id="loading-msg">加载样式中...</span></div>
</div>

<div id="viewport">

<%
  String contextpath = request.getContextPath() + "/";
  String urlContext = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextpath;
%>


<div id="bd">
    <div class="left-column">
	   <!-- <h3>Ext JS 2.2 Samples</h3>-->

      
	
<link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css"/>
<script type="text/javascript" src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="<%=contextpath%>js/ext/ext-all-debug.js"></script>
<script type="text/javascript" src="<%=contextpath%>js/ext/locale/ext-lang-zh_CN.js"></script>
  <script type="text/javascript">document.getElementById('loading-msg').innerHTML = '页面初试化之中......';</script>
		<script type="text/javascript">
		var catalog=<%=obj.returnJson()%>
		alert(catalog);
		</script>

		
     
        <script type="text/javascript" src="samples.js"></script>

        <div id="all-demos">
        </div>

    </div>



    <div style="clear:both"></div>
</div><!-- end bd -->



</div><!-- end viewport -->






</body>
</html>


