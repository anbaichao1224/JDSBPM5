<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="ww" %>

<%



String contextpath = request.getContextPath() + "/";
%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>资源管理器</title>
        <script>
    	var context="/";
    	var initdir='<ww:property value="initDir"/>';
    	var initPath='<ww:property value="initPath"/>';
    	var activityDefId='<ww:property value="activityDefId"/>';
    	var flowType='<ww:property value="$R('flowType')"/>';
    	var esbKeyList='<ww:property value="$R('esbKeyList')"/>';
    	var cellMenu;
        </script>
        
        
        <script id="script_prototype" src="<%=contextpath%>js/prototype.js" type="text/javascript"></script>
		<script id="script_yui-utilities" src="<%=contextpath%>js/ext/adapter/yui/yui-utilities.js" type="text/javascript"></script>
		<script id="script_ext-yui-adapter" src="<%=contextpath%>js/ext/adapter/yui/ext-yui-adapter.js" type="text/javascript"></script>
		     <script id="script_ext-all" src="<%=contextpath%>js/ext/ext-all.js" type="text/javascript"></script>
		<script language="javascript" src="<%=contextpath%>js/ext/locale/ext-lang-zh_CN.js"></script>
            <script language="javascript" src="<%=contextpath%>js/JDS.js"></script>
              
        <script src="codebase/dhtmlxcommon.js" type="text/javascript"> </script>
        <script src="codebase/dhtmlxgrid.js" type="text/javascript"> </script>
        <script src="codebase/dhtmlxgridcell.js" type="text/javascript"></script>
        <script src="codebase/dhtmlxlayout.js" type="text/javascript"></script>
        <script src="codebase/dhtmlXTreeAll.js" type="text/javascript"> </script>
         <script src="codebase/dhtmlXProtobar.js"></script>
         <script src="codebase/dhtmlXMenuBar.js"></script>
         <script src="codebase/dhtmlXMenuBar_cp.js"></script>
        <script src="codebase/dhtmlxtoolbar.js" type="text/javascript"></script>
        <script src="codebase/dhtmlxwindows.js" type="text/javascript"></script>
        <script src="codebase/dhtmlxwindows_wmn.js" type="text/javascript"></script>
         <script src="codebase/dhtmlxwindows_wtb.js" type="text/javascript"></script>
        <script src="codebase/dhtmlxfolders.js" type="text/javascript"></script>
        <script src="codebase/dhtmlxfolders_drag.js" type="text/javascript"> </script>
         <script src="codebase/folderdisplay.js" type="text/javascript"> </script>         
         
         
 	<link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
        <link rel='stylesheet' type='text/css' href='codebase/skins/dhtmlXMenu_xp.css'></link>
        <link rel="STYLESHEET" type="text/css" href="codebase/skins/dhtmlXToolbar.css">
         <link rel="STYLESHEET" type="text/css" href="codebase/skins/Context.css">
        <link rel="STYLESHEET" type="text/css" href="codebase/skins/dhtmlxlayout_dhx_blue.css">
        <link rel="STYLESHEET" type="text/css" href="codebase/skins/dhtmlxfolders.css">

        <link rel="STYLESHEET" type="text/css" href="codebase/skins/dhtmlxgrid_dhx_blue.css">
        <link rel="STYLESHEET" type="text/css" href="codebase/skins/dhtmlXTree.css">
        <link rel="STYLESHEET" type="text/css" href="codebase/skins/dhtmlxwindows.css">
        <link rel="STYLESHEET" type="text/css" href="codebase/skins/dhtmlxwindows_dhx_blue.css">
	
    </head>
    <script>
    	context='<%=contextpath%>';
    	
	
    //鼠标移动到图片上
	function over(the,imgsrc){
		the.className="img_over";
		the.src='images/'+imgsrc+'_over.gif';
	}
	//鼠标移出图片上
	function out(the,imgsrc){
		the.className="img";
		the.src='images/'+imgsrc+'.gif';
	}
	//鼠标在图片上按下
	function down(the,imgsrc){
		the.className="img_down";
		//the.src='images/'+imgsrc+'_down.gif';
	}
	function over_(the){the.className="img_over";}
	function out_(the){the.className="img";}
	function down_(the){the.className="img_down";}
	      
            
    </script>
    <body onload="doOnLoad()" style="width:100%; height:100%; margin:0px; overflow:hidden;">
       
    <TABLE id=iebar	style="font:12px Arial,sans-serif;background-color : #F3F3F3;border-bottom : solid 1px #F3F3F3;border-left : solid 1px #F3F3F3;border-right : solid 1px #FFFFFF;border-top : solid 1px #ffffff;" CELLPADDING="0" CELLSPACING="0" BORDER="0" width="100%">
	  <TR valign="middle" height=22>
		<TD width=2></TD>
		<TD width=3><img src="images/toolbar.gif"></TD>
		<TD width=4></TD>
		<TD width=46><nobr>地址(<u>D</u>)</TD>
		<TD width=2></TD>
		<TD>
		  <div style="border:2 inset white;width:100%; height:22px;background-color:#FFFFFF;font:12px Arial,sans-serif;">
		  <TABLE CELLPADDING="1" CELLSPACING="0" BORDER="0" width="100%" height="100%">
		  <TR>
		  	<TD width=16><img src="images/ie.gif"></TD>
		  	<TD id=URL><a style="font:12px Arial,sans-serif;">http://<%=request.getServerName()%>:<%=request.getServerPort()%></a></TD>
		  </TR>
		  </TABLE>
		  </div>
		</TD>
		<TD width=2></TD>
		<TD width=49><img src="images/goto.gif" class="img" onmousedown="down(this,'goto');" onmouseout="out(this,'goto');" onmouseover="over(this,'goto');" onmouseup="over(this,'goto');" border=1></TD>
		<TD width=4></TD>
	  </TR>
	</TABLE>

<div id="pageContent" style="display:'none'">
<div id="dhtmlgoodies_xpPane">
	<div class="dhtmlgoodies_panel">
		<div>
			创建文件夹<br>
			上传文件<br>
			刷新目录<br>
		</div>	
	</div>
	<div class="dhtmlgoodies_panel">
		<div>
			<!-- Start content of pane -->
			我的程序<br>
			流程文件<br>
			表单文件<br>
			报表文件<br>
			人员基础信息<br>
			字典表文件<br>
            <!-- End content -->
		</div>		
	</div>
	<div class="dhtmlgoodies_panel">
		<div>
			<!-- Start content of pane -->
			<b>表单定义</b><br>
			文件夹<br><br>
			修改时间: 2009年3月26日14:20				
			<!-- End content -->
		</div>		
	</div>
</div>


        
    </body>
</html>