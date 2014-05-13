<%@ page contentType="text/html;charset=gb2312" language="java"%>
<%@include file="../../shared/inc/App_Variable.jsp"%>
<html>
<head>
<META NAME="Robots" CONTENT="noindex">
<TITLE>TreeView Content Bar</TITLE>
<LINK REL="stylesheet" TYPE="text/css" HREF="<%=SYS_BASE_PATH%>treeview/shared/css/treeview.jsp">
</head>
<script language="javascript">

function selectstart()
  {
  window.event.cancelBubble = true;
  window.event.returnValue = false;
  return false;
  }

</script>

<SCRIPT LANGUAGE="javascript"><!--

function GoToURL(strX)

{
	if (strX == "" || strX == null || strX == "undefined")
	{	return;	}
	window.top.location.href = strX;

}

//--></SCRIPT>

<body onselectstart="selectstart();" topmargin="0" leftmargin="0" marginheight="0" marginwidth="0" bgcolor="#6699CC">

<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" WIDTH="100%" BGCOLOR="#6699CC">
  <tr>
    <td align="left" VALIGN="top" width="50%" NOWRAP>
    <div id="showtoc" name="showtoc" style="display:none;">
    <table onclick="top.dtbar.showtoc();" align="left" border=0 cellpadding=0 cellspacing=0 title="Show TOC" style="cursor:hand">
      <tr>
        <td height="20"><img width="5" height="20" src="<%=SYS_BASE_PATH%>treeview/shared/graphics/1pix.gif"></td>
        <td height="20" valign="middle"><img title="Show TOC" border="0" src="<%=SYS_BASE_PATH%>treeview/shared/graphics/showtoc.gif" /></td>
        <td height="20"><img width="5" height="20" src="<%=SYS_BASE_PATH%>treeview/shared/graphics/1pix.gif"></td>
        <td height="20" valign="middle" nowrap><font face="verdana" size="1" color="#FFFFFF">show toc</font></td>
      </tr>
    </table>
    </div>
    </td>
    <td align="right" width="50%" valign="top" nowrap>

      <table align="right" border="0" cellpadding="0" cellspacing="0">
        <tr>
         
          <td height="20"><img width="10" height="20" src="<%=SYS_BASE_PATH%>treeview/shared/graphics/1pix.gif"></td>
        </tr>
      </table>

    </td>
  </tr>
</table>
</body>
</html>