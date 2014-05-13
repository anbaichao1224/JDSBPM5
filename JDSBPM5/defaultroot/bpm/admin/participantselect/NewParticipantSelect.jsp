<%
/**
 *    $RCSfile: NewParticipantSelect.jsp,v $
 *    $Author: liyang $
 *    $Revision: 1.2 $
 *    $Date: 2011/08/03 09:38:58 $
 */
%>

<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../include/jsp/global.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="net.itjds.common.web.ColRollPage" %>
<%@ page import="net.itjds.common.util.StringUtility" %>
<%@ page import="net.itjds.common.dm.DM" %>

<%@ page import="net.itjds.bpm.engine.database.right.*" %>

<%

	try {
%>

<HTML>
<HEAD>
<TITLE> BPM Engine Admin </TITLE>
<link href="../include/css/style.css" rel="stylesheet" type="text/css">
<SCRIPT LANGUAGE="JavaScript" src="../include/js/jcommon.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
<!--
function _check() {
    var sForm = document.frm;
    if(sForm.SelectName.value == "") {
        alert("公式名称不能为空！");
        return false;
    }
    if(sForm.FORMULA.value == "") {
        alert("公式内容不能为空！");
        return false;
    }
    
	return true;
}

//-->
</SCRIPT>
</HEAD>
<BODY   LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0> 
<form name="frm" method="post" action="NewParticipantSelect_do.jsp" onSubmit="return _check();">

<TABLE width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF"> 
  <tr> 
    <td height="8" bgcolor="E3E8F8"> </td> 
  </tr> 
  <tr> 
    <td height="500" align="center" valign="top">
      <TABLE width="100%" border=0 align=center cellspacing="3">
        <tr height="21" valign="bottom">
          <td width="500" class="lin3"><font color="FB4303">■ 当前位置 》 公式模版管理 》 新建公式</font></td>
        </tr>
        <tr>
          <td height="8"> </td>
        </tr>
        <tr>
          <td>
            <table width="100%" border="0" align="center" cellpadding="2" cellspacing="0" class="tr">
              <tr >
                <td height=21 colspan=2 bgcolor=#E3E8F8 class=td>■<font color=#ffffff>&nbsp;</font>详细信息</td>
              </tr>
              <tr>
                <td width="100%" height="23" align="center" class="td">
                  <table width="100%" border="0" cellspacing="0" cellpadding="3">
                    <tr>
                      <td width="20%" height="23" align="right">公式名称：</td>
                      <td width="80%" height="23"><input type="text" class="inputtxt" name="SelectName" value="" maxlength="50" size="70"></td>
                    </tr>
				    <tr>
                      <td height="23" align="right">公式描述：</td>
                      <td height="23"><input type="text" class="inputtxt" name="SelectDesc" value="" maxlength="100" size="70"></td>
                    </tr>
                    <tr>
                      <td height="23" align="right">公式：</td>
                      <td height="23"><textarea name="FORMULA" class="inputtxt" rows="10" cols="80"></textarea></td>
                    </tr>
                  </table>
                </td>
              </tr>
            </table>
            <br>
            <table width="191" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr> 
                <td height="25" align="center"> 
                  <input name="save" type="submit" class="inputbutton" style="cursor:hand;" value="保 存">
                  <input name="cancle" type="button" class="inputbutton" onClick="window.location='ParticipantSelectList.jsp'" style="cursor:hand;" value="返 回">
                </td>
              </tr>
            </table>
          </td> 
        </tr> 
      </table> 
    </td>
  </tr>
</table>
<%@ include file="../include/jsp/bottom.jsp" %>

</BODY>
</HTML>
<%
	}
	catch(Exception e) {
		logger.error("", e);
		response.sendRedirect("../error.jsp");
	}
%>