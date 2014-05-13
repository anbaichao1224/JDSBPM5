<%
/**
 *    $RCSfile: ModifyParameter.jsp,v $
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
<%@ page import="net.itjds.common.util.UUIDGenerator" %>
<%@ page import="net.itjds.common.dm.DM" %>

<%@ page import="net.itjds.bpm.engine.database.right.*" %>

<%
	try {
	    String ParticipantSelect_ID = request.getParameter("ParticipantSelect_ID");
	    String parameterId = request.getParameter("ParameterId");
	    boolean failFlag = false;
	    boolean newFlag = false;
		if(ParticipantSelect_ID == null || ParticipantSelect_ID.equals("") ) {
    		failFlag = true;
		}
		if(parameterId == null || parameterId.equals("") ) {
    		newFlag = true;
		}
        DbParticipantSelect participant = DbParticipantSelectManager.getInstance().loadByKey(ParticipantSelect_ID);
        if(participant == null ) {
    		failFlag = true;
        }
        DbExpressionParameter parameter = null;
        String id = "";
        String name = "";
        String code = "";
        String type = "";
        String desc = "";
        if(newFlag == false ) {
	        parameter = participant.getParameter(parameterId);
	        if(parameter == null ) {
	    		failFlag = true;
	        }
	        id = parameter.getParameterId();
	        name = parameter.getParameterName();
	        code = parameter.getParameterCode();
	        type = StringUtility.filterNull(parameter.getParameterType());
	        desc = parameter.getParameterDesc();
        } else {
            id = UUIDGenerator.genUUID();
        }
%>

<HTML>
<HEAD>
<TITLE> 公式参数编辑 </TITLE>
<link href="../include/css/style.css" rel="stylesheet" type="text/css">
<SCRIPT LANGUAGE="JavaScript" src="../include/js/jcommon.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
<!--
function _check() {
    var sForm = document.frm;
    if(sForm.ParameterName.value == "" ) {
       alert("参数名不能为空！");
       return false;
    }
    if(sForm.ParameterCode.value == "" ) {
       alert("变量名不能为空！");
       return false;
    }
	return true;
}

function _close() {
    window.returnValue = null;
    window.close();
}

function returnResult(isnew , id, name, code, type, desc) {
   var ret = new Object;
   ret.isnew = isnew;
   ret.id = id;
   ret.name = name;
   ret.code = code;
   ret.type = type;
   ret.desc = desc;
   window.returnValue = ret;
   window.close();
}

//-->
</SCRIPT>
</HEAD>

<BODY BGCOLOR=#FFFFFF LEFTMARGIN=3 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0> 
<iframe name="frame_<%=id%>" style="display:none"></iframe>
<form name="frm" method="post" action="ModifyParameter_do.jsp" onSubmit="return _check();" target="frame_<%=id%>">
<input type="hidden" name="ParticipantSelect_ID" value="<%=ParticipantSelect_ID%>">
<input type="hidden" name="ParameterId" value="<%=id%>">
<input type="hidden" name="isNew" value="<%=newFlag%>">
<table width="400" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF"> 
  <tr> 
    <td height="8" bgcolor="#FFFFFF"> </td> 
  </tr> 
  <tr> 
    <td height="200" align="center" valign="top">
      <table width=395 border=0 align=center cellspacing="3">
        <tr>
          <td>
            <table width="100%" border="0" align="center" cellpadding="2" cellspacing="0" class="tr">
              <tr >
                <td height=21 colspan=2 bgcolor=#E3E8F8 class=td>■<font color=#ffffff>&nbsp;</font>参数详细信息</td>
              </tr>
              <tr>
                <td width="100%" height="23" align="center" class="td">
                  <table width="100%" border="0" cellspacing="0" cellpadding="3">
                    <tr>
                      <td width="20%" height="23" align="right">参数名：</td>
                      <td width="80%" height="23"><input type="text" class="inputtxt" name="ParameterName" value="<%=name %>" maxlength="32" size="40"></td>
                    </tr>
                    <tr>
                      <td width="20%" height="23" align="right">变量名：</td>
                      <td width="80%" height="23"><input type="text" class="inputtxt" name="ParameterCode" value="<%=code %>" maxlength="50" size="40"></td>
                    </tr>
                    <tr>
                      <td width="20%" height="23" align="right">参数类型：</td>
                      <td width="30%" height="23">
                        <select name="ParameterType" style="width:250">
                          <%=DM.getSelectOption("ExpressionParameterType", type, 0)%>
                        </select>
                      </td>
                    </tr>
                    <tr>
                      <td width="20%" height="23" align="right">参数描述：</td>
                      <td width="30%" height="23"><input type="text" class="inputtxt" name="ParameterDesc" value="<%=desc %>" maxlength="100" size="40"></td>
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
                  <input name="cancle" type="button" class="inputbutton" onClick="_close();" style="cursor:hand;" value="关 闭">
                </td>
              </tr>
            </table>
          </td> 
        </tr> 
      </table> 
    </td>
  </tr>
</table>

</BODY>
</HTML>
<%
	}
	catch(Exception e) {
		logger.error("", e);
		response.sendRedirect("../error.jsp");
	}
%>