<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
    <title><fmt:message key="menu.tools.title"/></title>
    <meta name="heading" content="<fmt:message key='menu.tools.title'/>"/>
    <meta name="menu" content="ToolsMenu"/>
    <link rel="STYLESHEET" type="text/css" href="<c:url value='/engine/css/dhtmlXMenu.css'/>">
    <link rel="STYLESHEET" type="text/css" href="<c:url value='/engine/css/Context.css'/>">
    <link rel="STYLESHEET" type="text/css" href="<c:url value='/engine/css/dhtmlXGrid.css'/>">
    <link rel="STYLESHEET" type="text/css" href="<c:url value='/engine/css/search.css'/>">    
    <script src="<c:url value='/engine/js/dhtmlXCommon.js'/>"></script>
    <script src="<c:url value='/engine/js/dhtmlXGrid.js'/>"></script>
    <script src="<c:url value='/engine/js/dhtmlXGridCell.js'/>"></script>
    <script src="<c:url value='/engine/js/dhtmlXGrid_drag.js'/>"></script> 
    <script src="<c:url value='/engine/js/dhtmlXGrid_search.js'/>"></script>
    <script src="<c:url value='/engine/menujs/dhtmlXProtobar.js'/>"></script>
    <script src="<c:url value='/engine/menujs/dhtmlXMenuBar.js'/>"></script>
    <script src="<c:url value='/engine/menujs/dhtmlXMenuBar_cp.js'/>"></script>
    <script src="<c:url value='/engine/menujs/dhtmlXCommon.js'/>"></script>
    <script src="<c:url value='/engine/js/dhtmlXGrid_menu.js'/>"></script>
    <script src="<c:url value='/engine/js/dhtmlXGrid_search.js'/>"></script>
    <script src="<c:url value='/engine/js/dhtmlXGrid_ajaxsearch.js'/>"></script>    
    <script src="<c:url value='/engine/js/prototype.js'/>"></script>
    <script src="<c:url value='/engine/js/indexAjax.js'/>"></script>
    <script src="<c:url value='/engine/js/dhtmlXTreeGrid.js'/>"></script>
</head>
<body onclick="document.getElementById('search_suggest').style.display = 'none';">
<input type="hidden" name="serverAddr" value="<%=request.getContextPath()%>"/>

<table id="editor" height="320">
    <tr height="320">
    	<td width=30 align="right" valign="top">
			<div id="linenum" style="POSITION: relative;left:0;align:right;valign:top;font-size:12pt;font-weight:bolder;color:#0000ff;margin-top:2;line-height:16px;width:100%;height:100%;overflow:hidden">
            <script language="javascript">
  			var tmpLineStr="";
              for(var i=1;i<=200;i++){
                tmpLineStr+=i+"<br>";
              }
              document.write(tmpLineStr);
            </script>
			</div>
        
		</td>
		<td valign="top">
            <iframe onfocus="//addScroll()" onblur=""   onload="addScroll()" name="HtmlEdit" id="HtmlEdit" MARGINHEIGHT="1" MARGINWIDTH="1" width="700" height="320" frameborder="0" style="border:1px solid gray">
			</iframe>
        </td>
		<td><input type="button" class="inputbutton" onClick="doSearch();" value="<fmt:message key="button.search"/>" style="cursor:'hand';"><br><input type="button" class="inputbutton" onClick="doReturn();" value="<fmt:message key="button.useexpression"/>" style="cursor:'hand';"></td>
		
			
    </tr>
</table>
<div class="txt" id="dd"></div>
<script language="javascript">
     function doReturn() {
         var text = getAllText();
         window.opener.document.all.item("tdVO.theoremExpression").value = text;
         window.close();
     }

     function init() {
         var text= window.opener.document.all.item("tdVO.theoremExpression").value;
         HtmlEdit.document.body.innerText = text;
         hightColor();
     }
     
    function doSearch(reload) {
	    var r = HtmlEdit.document.selection.createRange();
	    var str=r.text;
	    if(!str){
	      str=HtmlEdit.document.body.innerText;
	    }
	   
        $('search_suggest').style.display = 'none';
 
        loadAjaxSearch(str,reload);
    }
</script>
</body>

