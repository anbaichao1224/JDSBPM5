<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="java.util.*" %>
<%@ page import="net.itjds.bpm.engine.*" %>
<%@ page import="net.itjds.common.util.*" %>
<%@ page import="net.itjds.bpm.client.*"%>
<%@ page import="net.itjds.common.org.base.*"%>
<%@ page import="net.itjds.oa.*"%>


<%
try {
    response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);

	List historyList = (List) request.getAttribute("historyList");
    WorkflowClientService client = OAUtil.getClient(request);
    ActivityInst activityInst = (ActivityInst) request.getAttribute("activityInst");
    ProcessInst processInst = (ProcessInst) request.getAttribute("processInst");
    String curActId=activityInst.getActivityDefId();
   
%>
<html>
<head>
<title>流程跟踪日志</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv='expires' content='0'>
<meta http-equiv="Pragma" content="no-cache">
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">

<link rel="stylesheet" href="/bpm/oa/include/css/style.css" type="text/css">

<!-- JSScript脚本 begin -->
<SCRIPT LANGUAGE="JavaScript">

}
</SCRIPT>
<!-- JSScript脚本 end -->
</head>
<body onload="handlMov()" bgcolor="#FCFCFC" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<br>
<table width="600" align="center" border="0" cellpadding="0" cellspacing="0" bgcolor="#F6F6F6" bordercolorlight="#FFFFFF" bordercolordark="#FFFFFF">
  <tr height="158">
    <td width="100%" valign="top">
    <table width="98%" border="0" align="center" cellspacing="0" cellpadding="0">
      <tr bgcolor="A8DDFB"> 
        <td width="5%" height="30">&nbsp;</td>
        <td width="95%" height="30"><font color="#006699"><strong>流程跟踪日志</strong></font></td>
      </tr>
    </table>
	  <table align="center" width="98%" border="1" cellpadding="0" cellspacing="0" bgcolor="#EFEFEF" bordercolorlight="#FFFFFF" bordercolordark="#FFFFFF">
	    <tr align="center" valign="top">
		  <td>
            <table width="100%" border="1" align="center" cellpadding="0" cellspacing="0"  bordercolorlight="#ffffff" bordercolordark="#0E9CE3" bgcolor="#FFFFFF">
		     <tr align="center" bgcolor="A8DDFB" height="25"> 
			   <td width="5%">序号</td>
			   <td width="13%">办理人</td>
			   <td width="27%">所属部门</td>
			   <td width="25%">办理步骤</td>
			   <td width="15%">开始时间</td>
			   <td width="15%">结束时间</td>
			 </tr>
<%
    int i=0;
    String bgColor = "#FFFFFF";
    for(; i<historyList.size(); i++ ) {
        if(i%2 == 1) {
            bgColor = "#FFFFFF";
        } else  {
            bgColor = "#FFFFFF";
        }
    
        ActivityInstHistory history = (ActivityInstHistory) historyList.get(i);
        String historyId = history.getActivityHistoryId();
        List performers = (List) client.getActivityInstHistoryRightAttribute(historyId, OARightConstants.ACTIVITYINSTHISTORY_RIGHT_ATT_PERFORMER, null);
        
        ActivityDef activityDef = client.getActivityDef(history.getActivityDefId());
%>
             <tr bgcolor=<%=bgColor%> height="22">
			   <td width="5%" align="center"><%=(i+1)%></td>
			   <td width="40%" colspan="2">
                 <table width="100%" height="100%" border=0 cellpadding=2 cellspacing=0>
<%
        for(int j=0; j<performers.size(); j++ ) {
            Person p = (Person) performers.get(j);
            Org[] orgs = p.getOrgs();
%>
                   <tr>
                     <td width="33%" style="BORDER-RIGHT: #0E9CE3 1px solid;">&nbsp;<%=p.getName()%></td>
                     <td width="66%">&nbsp;
                     <% for(int k=0; k<orgs.length; k++) { %>
                     <%=(k==0 ? "" : "<br>")%>
                     <%=orgs[k].getName()%>
                     <% } %>
                     </td>
                   </tr>
<%
        }
		String stepName = activityDef.getName();
		if (ActivityInst.RECEIVEMETHOD_BACK.equals(history.getReceiveMethod())) {
			stepName += "（退回）";
		}
%>
                 </table>
               </td>
			   <td width="25%">&nbsp;<%=stepName%></td>
			   <td width="15%" align="center"><%=DateUtility.formatDate(history.getArrivedTime(), "yyyy-MM-dd HH:mm:ss")%></td>
			   <td width="15%" align="center"><%=DateUtility.formatDate(history.getEndTime(), "yyyy-MM-dd HH:mm:ss")%></td>
             </tr>
<%}%>
<%
    if(i%2 == 1) {
        bgColor = "#FFFFFF";
    } else  {
        bgColor = "#FFFFFF";
    }
    if(activityInst != null) {
	//显示当前活动办理人
    List currentPerformers = (List) client.getActivityInstRightAttribute(activityInst.getActivityInstId(), OARightConstants.ACTIVITYINST_RIGHT_ATT_PERFORMER, null);
    ActivityDef currentActivityDef = activityInst.getActivityDef();
 
%>
             <tr bgcolor=<%=bgColor%> height="22">
			   <td width="5%" align="center"><%=(i+1)%></td>
			   <td width="40%" colspan="2">
                 <table width="100%" height="100%" border=0 cellpadding=2 cellspacing=0 >
<%
        for(int j=0; j<currentPerformers.size(); j++ ) {
            Person p = (Person) currentPerformers.get(j);
            Org[] orgs = p.getOrgs();
%>
                   <tr>
                     <td width="33%" style="BORDER-RIGHT: #0E9CE3 1px solid;">&nbsp;<%=p.getName()%></td>
                     <td width="66%">&nbsp;
                     <% for(int k=0; k<orgs.length; k++) { %> <%=(k==0 ? "" : "<br>")%> <%=orgs[k].getName()%> <% } %>
                     </td>
                   </tr>
<%
        }
		String stepName = currentActivityDef.getName();
		if (ActivityInst.RECEIVEMETHOD_BACK.equals(activityInst.getReceiveMethod())) {
			stepName += "（退回）";
		}
%>
                 </table>
               </td>
			   <td width="25%">&nbsp;<%=stepName%></td>
			   <td width="15%" align="center"><%=DateUtility.formatDate(activityInst.getArrivedTime(), "yyyy-MM-dd HH:mm:ss")%></td>
			   <td width="15%" align="center"><%="正在办理"%></td>
             </tr>
<%
     } else {
%>
             <tr bgcolor=<%=bgColor%> height="22">
			   <td width="40%" align="center" colspan="6">
                 办理完成
               </td>
             </tr>

<%
     }
     for(i++ ; i< 5; i++ ) {
        if(i%2 == 1) {
            bgColor = "#FFFFFF";
        } else  {
            bgColor = "#FFFFFF";
        }
 
%>
             <tr bgcolor=<%=bgColor%> height="40">
			   <td width="5%" align="center"><%=(i+1)%></td>
			   <td width="13%">&nbsp;</td>
			   <td width="27%">&nbsp;</td>
			   <td width="25%">&nbsp;</td>
			   <td width="15%" align="center">&nbsp;</td>
			   <td width="15%" align="center">&nbsp;</td>
             </tr>
<%
    }
%>
		   </table>
		  </td>
        </tr>
	  </table>

	  <table align="center" width="95%" border="0" cellpadding="0" cellspacing="0">
	    <tr>
		  <td align="center" valign="middle" height="35">
			<input type="button" value=" 关 闭 " class="inputbutton" onClick="window.close();">&nbsp;&nbsp;
		  </td>
		</tr>
	  </table>
    </td>
  </tr>
</table>
</body>
</html>
<%
  }
  catch(Exception e) {
	e.printStackTrace();
	response.sendRedirect("/bpm/oa/error.jsp");
  }
%>


