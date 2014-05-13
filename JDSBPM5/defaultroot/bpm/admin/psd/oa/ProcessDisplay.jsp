<%@ page contentType="text/html;charset=gb2312"%>
<%@ include file="/bpm/admin/include/jsp/global.jsp" %>
<%@ page import="net.itjds.oa.*"%>
<%@ page import="net.itjds.common.dm.DM"%>
<%@ page import="net.itjds.common.util.DateUtility"%>
<%@ page import="net.itjds.bpm.client.ActivityInst"%>
<%@ page import="java.io.PrintWriter"%>

<%
try {
	String activityInstId = request.getParameter("ActivityInstId");
	try {
		int nResult;
		nResult = OAUtil.performAdminDisplay(request, activityInstId);
		if(nResult == -1) {
			request.setAttribute("errorMsg", "error !");
			request.getRequestDispatcher("/bpm/admin/error.jsp").forward(request, response);
			return;
		}
		if (nResult == -2) {
			request.setAttribute("errorMsg", "Prcocess completed!");
			request.getRequestDispatcher("/bpm/admin/error.jsp").forward(request, response);
			return;
		}
	} catch (Exception e) {
		request.setAttribute("errorMsg", e.getMessage());
		request.setAttribute("Throwable", e);
		request.getRequestDispatcher("/bpm/admin/error.jsp").forward(request, response);
		return;
	}
	
	ActivityInst activityInst = (ActivityInst) request.getAttribute("BPM_activityInst");
	ProcessInst processInst = (ProcessInst) request.getAttribute("BPM_processInst");

%> 

<HTML>
<HEAD>
<TITLE>表单设计工具</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312"> 
<meta http-equiv='expires' content='0'>
<meta http-equiv="Pragma" content="no-cache">
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
<link href="<%=contextPath%>/bpm/admin/include/css/style.css" rel="stylesheet" type="text/css">
<SCRIPT LANGUAGE="JavaScript" src="<%=contextPath%>/bpm/admin/include/js/jcommon.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
<!--
function save() {
    var sForm = document.frm;
    sForm.submit();
}

function BPM_onRouteTo() {
//    alert("BPM_onRouteTo");
}
 
function BPM_checkRouteToValid() {
//    alert("BPM_checkRouteToValid");
    return true;
}

function BPM_doRouteTo() {
    var sForm = document.frm;  
    sForm.submit();
}

function BPM_doRouteBack() {
	var sForm = document.frm;
	sForm.submit();
}

function BPM_doSuperviseRouteTo() {
	var sForm = document.frm;
	sForm.submit();
}

function BPM_doSuperviseRouteBack() {
	var sForm = document.frm;
	sForm.submit();
}
function BPM_doChangePerformer() {
	var sForm = document.frm;
	sForm.submit();
}
//-->
</SCRIPT>
</HEAD>
<BODY     leftmargin=0 topmargin=0 marginwidth=0 marginheight=0> 

<TABLE width="100%" height="80%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
  <tr valign="top">
    <td> 
   
      <table border=0 height=10 align="center"> 
      
      </table>
      <form method="post" name="frm" action="/bpm/admin/psd/oa/ProcessUpdate.jsp">
      <!-- 引入BPM流程控制部分(必须嵌入在<form>内) -->
      <%@ include file="/bpm/admin/common/BPMControllor.jsp" %>
      <!-- 引入BPM流程控制部分(必须嵌入在<form>内) -->
      <input type=hidden name="ACTIVITY_ID" value="<%=activityInstId%>">
      <table border=1 align="center" width='100%' cellspacing=0 cellpadding=0 bordercolorlight='#000000' bordercolordark='#FFFFFF'>
        <tr height="21"> 
          <td width="25%">&nbsp;活动名称： </td>
          <td>&nbsp;<%=activityInst.getActivityDef().getName()%> </td>
        </tr>
        <tr height="21">
          <td>&nbsp;活动描述： </td>
          <td>&nbsp;<%=activityInst.getActivityDef().getDescription()%> </td>
        </tr>
        <tr height="21">
          <td>&nbsp;到达时间： </td>
          <td>&nbsp;<%=DateUtility.formatDate(activityInst.getArrivedTime(), "yyy-MM-dd HH:mm:ss")%> </td>
        </tr>
        <tr height="21"> 
          <td>&nbsp;时间限制： </td>
          <td>&nbsp;<%=DateUtility.formatDate(activityInst.getLimitTime(), "yyy-MM-dd HH:mm:ss")%> </td>
        </tr>
        <tr height="21">
          <td>&nbsp;开始办理时间： </td>
          <td>&nbsp;<%=DateUtility.formatDate(activityInst.getStartTime(), "yyy-MM-dd HH:mm:ss")%> </td>
        </tr> 
        <tr height="21">
          <td>&nbsp;接受方式： </td>
          <td>&nbsp;<%=DM.convertDM("ActivityInst_RECEIVEMETHOD", activityInst.getReceiveMethod())%> </td>
        </tr>
        <tr height="21"> 
          <td>&nbsp;办理方式： </td>
          <td>&nbsp;<%=DM.convertDM("ActivityInst_DEALMETHOD", activityInst.getDealMethod())%> </td>
        </tr>
        <tr height="21"> 
          <td>&nbsp;运行状况： </td>
          <td>&nbsp;<%=DM.convertDM("ActivityInst_STATE", activityInst.getState())%> </td>
        </tr>
      </table>
      </form>
      <table border=0 height=10> 
        <tr>
          <td></td>
        </tr>
      </table>
      <table border=0 align="center">
        <tr>
		  <td><input type="button" value="更换办理人" onClick="BPM_changePerformer()">&nbsp;</td>
		  <td>&nbsp;<input type="button" value=" 提 交 " onclick="BPM_superviseRouteTo();">&nbsp;&nbsp;</td>
          <td>&nbsp;<input  type="button" value=" 退 回 " onclick="BPM_superviseRouteBack();">&nbsp;</td>
          <td>&nbsp;<input type="button" value="查看流程日志" onclick="BPM_showRouteLog();">&nbsp;</td>
           <td>&nbsp;<input  type="button" value=" 返  回 " onClick="window.location='/bpm/admin/psd/ProcessList.jsp'" >&nbsp;</td>
        
        </tr>
      </table>
    </td>
  </tr> 
</table>

<TABLE width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
  <tr> 
    <td height="1" align="center" bgcolor="#adbad6"> </td>
  </tr>
  <tr> 
    <td height="40" align="center" bgcolor="#E3E8F8">&nbsp;</td>
  </tr>
</table>
<!-- Tail begin --> 
</BODY>
</HTML>
<!-- Tail end -->
<%
}catch(Exception e) {
	e.printStackTrace(new PrintWriter(out));
}
%>
