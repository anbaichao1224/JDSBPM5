<%@ page contentType="text/html; charset=gb2312"%>
<%@ page import="java.util.*"%>
<%@ page import="net.itjds.common.util.DateUtility"%>
<%@ page import="net.itjds.bpm.client.*"%>
<%@ page import="net.itjds.bpm.engine.WorkflowClientService"%>
<%@ page import="net.itjds.oa.OAUtil"%>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
	
try {
	List activityInstHistorys = (List)request.getAttribute("activityInstHistorys");
	int historyCount = activityInstHistorys.size();
	
	WorkflowClientService client = OAUtil.getClient(request);
%>
<SCRIPT LANGUAGE="JavaScript">
<!--
function selectHistory(os, historyId) {
	document.all.historyId.value = historyId;

	if (typeof op != "undefined") {
		op.style.backgroundColor = bg;
		op.style.border = "none";
	}
	bg = os.style.backgroundColor;
	os.style.backgroundColor = "ccddee";
	os.style.border = "0px solid #666666";
	op = os;
}

function _ok()
{
	if (document.all.historyId.value == "") {
		alert("��ѡ���˻ؽڵ㣡");
		return;
	}
	window.returnValue = document.all.historyId.value;
	window.close();
}

//-->
</SCRIPT>

<input type="hidden" name="historyId">

<table width="100%" height="200" align="center" border="0" cellspacing="0" cellpadding="0">
  <tr valign="top">
    <td width="100%" align="center" valign="top">
	  <table width="90%">
	    <tr>
		  <td>��ѡ��·�ɡ�</td>
	    </tr> 
	  </table> 
	  <table width="90%" height="10" border="0" cellspacing="0" cellpadding="0">
  	    <tr valign="top"> 
		  <td>
		    <table width="100%">
			  <tr height="21">
			    <td align="center" width="24%">�ڵ�����</td>
			    <td align="center" width="36%">��ʼʱ��</td>
			    <td align="center" width="36%">����ʱ��</td>
			  </tr>
			</table>
		  </td>
	    </tr>
<% for(int ii=0; ii<historyCount;ii++){
		ActivityInstHistory history = (ActivityInstHistory)activityInstHistorys.get(ii);
		if(history == null) {
			continue;
		}
		String historyId = history.getActivityHistoryId();
		String startTime = DateUtility.formatDate(history.getArrivedTime(), "yyyy-MM-dd HH:mm:ss");
		String endTime = DateUtility.formatDate(history.getEndTime(), "yyyy-MM-dd HH:mm:ss");
		
		ActivityDef activityDef = client.getActivityDef(history.getActivityDefId());
		if (activityDef == null) {
			continue;
		}
		String activityDefName = activityDef.getName();
%>
  	    <tr valign="top"> 
		  <td>
		    <table width="100%">
			  <tr height="21" onclick='selectHistory(this, "<%=historyId%>")' id="history<%=ii%>">
			    <td align="center" width="24%"><%=activityDefName%></td>
			    <td align="center" width="36%"><%=startTime%></td>
			    <td align="center" width="36%"><%=endTime%></td>
			  </tr>
			</table>
		  </td>
	    </tr>
<%
	if (ii==0) {
%>
<SCRIPT LANGUAGE="JavaScript">
<!--
	selectHistory(eval("document.all.history0"), "<%=historyId%>");
//-->
</SCRIPT>
<%	}%>
<%}//end of for%>
<%}catch(Exception e) {
	e.printStackTrace();
	response.sendRedirect("/bpm/oa/error.jsp");
}
%>
	  </table>
	</td>
  </tr>
</table>

<table align="center" width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
	<td width="100%">
	  <table width="100%">
		<tr height="5"><td></td></tr>
        <tr height="40">
          <td align="center" width="100%">
            <input type=button value=' ȷ �� ' class="inputbutton" onclick="_ok();">&nbsp;&nbsp;&nbsp;&nbsp;
            <input type=button value=' �� �� ' class="inputbutton" onclick="window.close();">
          </td>
        </tr>
      </table>
	</td>
  </tr>
</table>
