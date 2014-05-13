<%@ page contentType="text/html;charset=gb2312" language="java"%>
<%@include file="../../shared/inc/App_Variable.jsp"%>

<script language="javascript">

var strColumns_Current = "180,*";

function movenext()
  {
  top.deeptree.movenext();
  }

function moveprevious()
  {
  top.deeptree.moveprevious();
  }

function hidetoc()
  {
  strColumns_Current = top.mainframeset.cols
  
  top.mainframeset.cols = "1,*";
  top.contentbar.document.all("showtoc").style.display = "block";
  }

function showtoc()
  {
  top.mainframeset.cols = strColumns_Current;
  top.contentbar.document.all("showtoc").style.display = "none";
  }
  
function synctoc()
{
    top.location = top.location.pathname + "?url=" + top.content.location.pathname + top.content.location.search + top.content.location.hash;
}

function displaybutton()
  {
  document.all.showtoc.style.display = "block";
  }

function mouseover(item)
  {
  switch (item)
    {
    case "moveprevious" :
      window.status = "Move up to the previous node in the tree [SHIFT + TAB]";
      document.all.imgMovePrevious.src = "<%=SYS_BASE_PATH%>treeview/shared/graphics/moveprevious2.gif";
      break;

    case "movenext" :
      window.status = "Move down to the next node in the tree [TAB]";
      document.all.imgMoveNext.src = "<%=SYS_BASE_PATH%>treeview/shared/graphics/movenext2.gif";
      break;

    case "synctoc" :
      window.status = "Synchronize the tree with the current page";
      document.all.imgSyncToc.src = "<%=SYS_BASE_PATH%>treeview/shared/graphics/synctoc2.gif"
      break;

    case "hidetoc" :
      window.status = "Hide TOC";
      document.all.imgHideToc.src = "<%=SYS_BASE_PATH%>treeview/shared/graphics/hidetoc2.gif"
      break;
    }

  }

function mouseout(item)
  {
  switch (item)
    {
    case "moveprevious" :
      window.status = "";
      document.all.imgMovePrevious.src = "<%=SYS_BASE_PATH%>treeview/shared/graphics/moveprevious1.gif";
      break;

    case "movenext" :
      window.status = "";
      document.all.imgMoveNext.src = "<%=SYS_BASE_PATH%>treeview/shared/graphics/movenext1.gif";
      break;

    case "synctoc" :
      window.status = "";
      document.all.imgSyncToc.src = "<%=SYS_BASE_PATH%>treeview/shared/graphics/synctoc1.gif"
      break;

    case "hidetoc" :
      window.status = "";
      document.all.imgHideToc.src = "<%=SYS_BASE_PATH%>treeview/shared/graphics/hidetoc1.gif"
      break;
    }
  }

function selectstart()
  {
  window.event.cancelBubble = true;
  window.event.returnValue = false;
  return false;
  }

</script>

<html>

<head>
<META NAME="Robots" CONTENT="noindex">
<title>TreeView Controlbar</title>
<link rel="stylesheet" type="text/css" href="<%=SYS_BASE_PATH%>treeview/shared/css/ie.jsp">
</head>

<body onselectstart="selectstart();" topmargin="0" leftmargin="0" marginheight="0" marginwidth="0" bgcolor="buttonface">
<FIELDSET>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
 <tr>
  <td align="left">
	<table border="0" cellpadding="0" cellspacing="0">
	  <tr>
		<td height="20" width="4"><img src="<%=SYS_BASE_PATH%>treeview/shared/graphics/1pix.gif" width="4"></td>
		<td height="20"><img id="imgMoveNext" style="cursor:hand" onmouseover="mouseover('movenext');" onmouseout="mouseout('movenext')" onclick="movenext();" title="Move down to the next node in the tree [TAB]" src="<%=SYS_BASE_PATH%>treeview/shared/graphics/movenext1.gif" border="0" /></td>
		<td height="20" width="2"><img src="<%=SYS_BASE_PATH%>treeview/shared/graphics/1pix.gif" width="2"></td>
		<td height="20"><img id="imgMovePrevious" style="cursor:hand" onmouseover="mouseover('moveprevious');" onmouseout="mouseout('moveprevious')" onclick="moveprevious();" title="Move up to the previous node in the tree [SHIFT + TAB]" src="<%=SYS_BASE_PATH%>treeview/shared/graphics/moveprevious1.gif" border="0" /></td>
		<td height="20" width="8"><img src="<%=SYS_BASE_PATH%>treeview/shared/graphics/1pix.gif" width="8"></td>
	  </tr>
	</table>
  </td>
  <td align="right">
	<table border="0" cellpadding="0" cellspacing="0">
	  <tr>
		<td height="20"><img id="imgSyncToc" style="cursor:hand" onclick="synctoc();" onmouseover="mouseover('synctoc');" onmouseout="mouseout('synctoc');" src="<%=SYS_BASE_PATH%>treeview/shared/graphics/synctoc1.gif" title="Synchronize the TOC with the current page" /></td>
		<td height="20" width="10"><img src="<%=SYS_BASE_PATH%>treeview/shared/graphics/1pix.gif" width="10" height="20"></td>
		<td height="20"><img id="imgHideToc" style="cursor:hand" onclick="hidetoc();" onmouseover="mouseover('hidetoc');" onmouseout="mouseout('hidetoc');" src="<%=SYS_BASE_PATH%>treeview/shared/graphics/hidetoc1.gif" title="Hide TOC" /></td>
		<td height="20" width="4"><img src="<%=SYS_BASE_PATH%>treeview/shared/graphics/1pix.gif" width="4" height="20"></td>
	  </tr>
	</table>
  </td>
 </tr>
</table>
</FIELDSET>

</body>
</html>
