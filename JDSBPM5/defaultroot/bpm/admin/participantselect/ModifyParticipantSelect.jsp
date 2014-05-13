<%
/**
 *    $RCSfile: ModifyParticipantSelect.jsp,v $
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
<%@ page import="net.itjds.bpm.engine.BPMExpressionParserManager" %>

<%
	try {
	    String ParticipantSelect_ID = request.getParameter("ParticipantSelect_ID");
		if(ParticipantSelect_ID == null || ParticipantSelect_ID.equals("") ) {
            response.sendRedirect("../error.jsp?errorMsg="+URLEncoder.encode("错误的参数！")+"&returnURI=./participantselect/ParticipantSelectList.jsp");
            return;
		}
        DbParticipantSelect participant = DbParticipantSelectManager.getInstance().loadByKey(ParticipantSelect_ID);
        if(participant == null ) {
            response.sendRedirect("../error.jsp?errorMsg="+URLEncoder.encode("数据库内未找到此记录！")+"&returnURI=./participantselect/ParticipantSelectList.jsp");
            return;
        }
        String id = participant.getParticipantSelectId();
        String name = StringUtility.escapeHTMLSpecial(participant.getSelectName());
		String desc = StringUtility.escapeHTMLSpecial(participant.getSelectDesc());
		String formula = StringUtility.filterNull(participant.getFormula());
		List parameterList = participant.getParameterList();
		String verify = request.getParameter("verify");
		if(verify == null || !verify.equals("yes")) {
		    verify = "no";
		}
        List parameterCodeList = new ArrayList();
        for(int i=0; i<parameterList.size(); i++) {
            DbExpressionParameter parameter = (DbExpressionParameter) parameterList.get(i);
            parameterCodeList.add(parameter.getParameterCode());
        }
        String errorInfo = BPMExpressionParserManager.verifyExpression(formula, parameterCodeList);

%>

<HTML>
<HEAD>
<TITLE> BPM Engine Admin </TITLE>
<link href="../include/css/style.css" rel="stylesheet" type="text/css">
<SCRIPT LANGUAGE="JavaScript" src="../include/js/jcommon.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
<!--

function verifyFormula() {
    var sForm = document.frm;
	sForm.verify.value = "yes";
    sForm.submit();
}
 
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
    if(sForm.FORMULA.value.length > 500) {
        alert("公式长度不能超过500个字符！");
        return false;
    }
	return true;
}

function modifyParameter(parameterId) {
   var _url = "ModifyParameter.jsp?ParticipantSelect_ID=<%=ParticipantSelect_ID%>&ParameterId=" + parameterId;
   var ret = window.showModalDialog(_url, parameterId, "dialogWidth:420px;dialogHeight:240px;status:0;");
   if(ret != null) {
       var id = ret.id;
       var name = ret.name;
       var code = ret.code;
       var type = ret.type;
       var desc = ret.desc;
       var _row = document.all("parameterId_"+ id, 0);
       if(_row != null) {
           _row.cells[1].innerText = " " + name;
           _row.cells[2].innerText = " " + code;
           _row.cells[3].innerText = " " + type;
           _row.cells[4].innerText = " " + desc;
       }
   }
}

function delParameter(parameterId) {
   var ret = confirm("您是否确定要删除此参数？");
   if(ret == true) {
      var delFrame = window.frames("delframe_<%=ParticipantSelect_ID%>");
	  alert(parameterId);
      delFrame.location.href = "./DeleteParameter.jsp?ParticipantSelect_ID=<%=ParticipantSelect_ID%>&ParameterId=" + parameterId;
   }
}

function delParameterRow(parameterId) {
   var _table = document.all("parameterTable", 0);
   var _row = document.all("parameterId_"+ parameterId, 0);
   var _backRow = _row.nextSibling;
   while(_backRow != null) {
       var _num = _backRow.cells[0].innerText;
       _num--;
       _backRow.cells[0].innerText = _num;
       _backRow = _backRow.nextSibling;
   }
   _table.firstChild.removeChild(_row);
   parameterCount--;
//   if(_table.rows.length
}

var parameterCount = <%=parameterList.size()%>;

function newParameter() {
   var _url = "ModifyParameter.jsp?ParticipantSelect_ID=<%=ParticipantSelect_ID%>";
   var ret = window.showModalDialog(_url, null, "dialogWidth:420px;dialogHeight:240px;status:0;");
   if(ret != null) {
       var id = ret.id;
       var name = ret.name;
       var code = ret.code;
       var type = ret.type;
       var desc = ret.desc;
       var _table = document.all("parameterTable", 0);
       var _row = document.all("parameterId_no", 0);
       if(_row != null){
           _row.removeNode();
           _row = _table.insertRow();
       } else  {
           _row = _table.insertRow();
       }
       _row.id = "parameterId_" + id;
       _row.height = "23";
       var num_cell = _row.insertCell();
       num_cell.className = "td";
       num_cell.align = "center";
       num_cell.innerText = "" + (++parameterCount);
       
       var name_cell = _row.insertCell();
       name_cell.className = "td";
       name_cell.innerText = " " + name;
       
       var code_cell = _row.insertCell();
       code_cell.className = "td";
       code_cell.innerText = " " + code;       
       
       var type_cell = _row.insertCell();
       type_cell.className = "td";
       type_cell.align = "center";
       type_cell.innerText = " " + type;       
       
       var desc_cell = _row.insertCell();
       desc_cell.className = "td";
       desc_cell.innerText = " " + desc;       
       
       var op_cell = _row.insertCell();
       op_cell.className = "td";
       op_cell.align = "center";
       op_cell.innerHTML = "<a href=\"javascript:modifyParameter('" + id + "')\">编辑<a>&nbsp;&nbsp;&nbsp;<a href=\"javascript:delParameter('" + id + "')\">删除</a>";
   }
    
}
//-->
</SCRIPT>
</HEAD>
<iframe name="delframe_<%=ParticipantSelect_ID%>" style="display:none"></iframe>
<BODY   LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0> 
<form name="frm" method="post" action="ModifyParticipantSelect_do.jsp" onSubmit="return _check();">
<input type="hidden" name="ParticipantSelect_ID" value="<%=ParticipantSelect_ID%>">
<input type="hidden" name="verify" value="no">

<TABLE width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF"> 
  <tr> 
    <td height="8" bgcolor="E3E8F8"> </td> 
  </tr> 
  <tr> 
    <td height="500" align="center" valign="top">
      <TABLE width="100%" border=0 align=center cellspacing="3">
        <tr height="21" valign="bottom">
          <td width="500" class="lin3"><font color="FB4303">■ 当前位置 》 公式模版管理 》 编辑公式</font></td>
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
                      <td width="80%" height="23"><input type="text" class="inputtxt" name="SelectName" value="<%=name %>" maxlength="50" size="70"></td>
                    </tr>
				    <tr>
                      <td height="23" align="right">公式描述：</td>
                      <td height="23"><input type="text" class="inputtxt" name="SelectDesc" value="<%=desc %>" maxlength="100" size="70"></td>
                    </tr>
                    <tr>
                      <td height="23" align="right">公式：</td>
                      <td height="23"><textarea name="FORMULA" class="inputtxt" rows="10" cols="80"><%=formula%></textarea></td>
                    </tr>
                    <%if(verify.equals("yes")) {%>
                    <tr>
                      <td height="23" align="right"></td>
                      <td height="23"><font color="red">
                      <%if(errorInfo.equals("OK")) { 
                          out.println("此公式验证成功，无语法错误！");
                        } else {
                          out.println("发现语法错误：" + errorInfo);
                        }
                      %></font>
                      </td>
                    </tr>
                    <% } %>
                  </table>
                </td>
              </tr>
            </table>
            <table width="100%" border="0" align="center" cellpadding="2" cellspacing="0" class="tr">
              <tr >
                <td height=21 colspan=2 bgcolor=#E3E8F8 class=td>■<font color=#ffffff>&nbsp;</font>公式参数信息  &nbsp;&nbsp;&nbsp;<input type=button value="新建参数" class="inputtxt" onclick="newParameter();"></td>
              </tr>
              <tr>
                <td width="100%" height="23" align="center" class="td">
                  <table width="100%" border="1" cellspacing="0" cellpadding="3" class=tr id="parameterTable">
                    <tr>
                      <td width="5%" height="23" align="center" class=td>序号</td>
                      <td width="20%" height="23" align="center" class=td>参数名</td>
                      <td width="20%" height="23" align="center" class=td>变量名</td>
                      <td width="20%" height="23" align="center" class=td>参数类型</td>
                      <td width="20%" height="23" align="center" class=td>参数描述</td>
                      <td width="15%" height="23" align="center" class=td>操作</td>
                    </tr>
<%
    int i=0;
    for( ; i<parameterList.size(); i++) {
        DbExpressionParameter parameter = (DbExpressionParameter) parameterList.get(i);
        String parameterId = parameter.getParameterId();
        String parameterCode = StringUtility.escapeHTMLSpecial(parameter.getParameterCode());
        String parameterName = StringUtility.escapeHTMLSpecial(parameter.getParameterName());
        String parameterDesc = StringUtility.escapeHTMLSpecial(parameter.getParameterDesc());
        String parameterType = StringUtility.filterNull(parameter.getParameterType());
        String parameterTypeDisplay = DM.convertDM("ExpressionParameterType", parameterType);
%>
                   <tr id='parameterId_<%=parameterId%>'>
                      <td width="5%" height="23" align="center" class=td><%=i+1%></td>
                      <td width="20%" height="23" align="left" class=td>&nbsp;<%=parameterName%></td>
                      <td width="20%" height="23" align="left" class=td>&nbsp;<%=parameterCode%></td>
                      <td width="20%" height="23" align="center" class=td>&nbsp;<%=parameterTypeDisplay%></td>
                      <td width="20%" height="23" align="left" class=td>&nbsp;<%=parameterDesc%></td>
                      <td width="15%" height="23" align="center" class=td>
                        <a href="javascript:modifyParameter('<%=parameterId%>')">编辑<a>&nbsp;&nbsp;&nbsp;<a href="javascript:delParameter('<%=parameterId%>')">删除</a>
                      </td>
                    </tr>
<%  }
    if(i == 0 ) { %>
                   <tr>
                      <td width="100%" height="23" colspan="6" align="center" class=td id="parameterId_no">无参数</td>
                    </tr>
<%    } %>
                  </table>
                </td>
              </tr>
            </table>
            <br>
            <table width="191" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr> 
                <td height="25" align="center"> 
                  <input name="save" type="button" class="inputbutton" style="cursor:hand;" value="验证公式" onclick="verifyFormula();">
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