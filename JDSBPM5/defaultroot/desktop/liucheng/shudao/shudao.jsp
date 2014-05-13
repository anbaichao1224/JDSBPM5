<%@ page contentType="text/html;charset=gb2312"%>
<%@ include file="/bpm/admin/include/jsp/global.jsp" %>
<%@ page import="net.itjds.oa.*"%>
<%@ page import="net.itjds.common.dm.DM"%>
<%@ page import="net.itjds.common.util.DateUtility"%>
<%@ page import="net.itjds.bpm.client.ActivityInst"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.List,net.itjds.worklist.list.support.rules.*,net.itjds.bpm.engine.*,net.itjds.common.org.base.*"%>

<%
String path = request.getContextPath();

String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";

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
<script type="text/javascript" src="<%=path%>js/extAll.js"></script>	
<script type="text/javascript">

function save() {
    var sForm = document.frm;
    sForm.submit();
}

function closeshudao(){
 //alert("go close");
 window.top.shudaoclose();
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
function BPM_doChangePerformer(aid) {
	var sForm = document.frm;
	//修改指定活动实例办理人  zhongqun 2011-12-16 修改
	document.getElementById("ACTIVITY_ID").value=aid;
	//end
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
      <form method="post" name="frm" action="/desktop/liucheng/shudao/upshudao.jsp">
      <!-- 引入BPM流程控制部分(必须嵌入在<form>内) -->
      <%@ include file="/bpm/admin/common/BPMControllor.jsp" %>
      <!-- 引入BPM流程控制部分(必须嵌入在<form>内) -->
      <input type=hidden id="ACTIVITY_ID" name="ACTIVITY_ID" value="<%=activityInstId%>">
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
      
      <table border=1 align="center" width='100%' cellspacing=0 cellpadding=0 bordercolorlight='#000000' bordercolordark='#FFFFFF'>
      		<tr>
      			
      			<td>当前办理人</td>
      			<td>操作</td>
      			
      		</tr>
      		<%ProcessInst processInstdetail = null;
      		ActivityInst activityInstdetail = activityInst;
      		processInstdetail = activityInstdetail.getProcessInst();
      		List<ActivityInst> alist = processInstdetail.getActivityInstList();
      		for(int i=0;i<alist.size();i++){%>
      			
      			<tr>
      			<%
      			WorkflowClientService client = OAUtil.getClient(request);
      			AdminService admin = OAUtil.getAdmin(client);
      			List persons = (List) admin.getActivityInstRightAttribute(alist.get(i).getActivityInstId(), OARightConstants.ACTIVITYINST_RIGHT_ATT_PERFORMER, null); %>
    			<td><%=((Person)persons.get(0)).getName() %></td>
    			<td><input type="button" value="更换办理人" onClick="BPM_changePerformer('<%=alist.get(i).getActivityInstId() %>')">&nbsp;</td>
      		</tr>
      		<%}
      		%>
      		
      </table>
      
      <table border=0 height=10> 
        <tr>
          <td></td>
        </tr>
      </table>
      <table border=0 align="center">
        <tr>
		  <td><!-- <input type="button" value="更换办理人" onClick="BPM_changePerformer()">&nbsp;</td> -->
		  <!-- <td>&nbsp;<input type="button" value=" 提 交 " onclick="BPM_superviseRouteTo();">&nbsp;&nbsp;</td> -->
		  <td> <input type="button" value="关闭" onclick="closeshudao();"/></td>
          <!--<td>&nbsp;<input  type="button" value=" 退 回 " onclick="BPM_superviseRouteBack();">&nbsp;</td>-->
          <td>&nbsp;<input type="button" value="查看流程日志" onclick="BPM_showRouteLog();">&nbsp;</td>
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
