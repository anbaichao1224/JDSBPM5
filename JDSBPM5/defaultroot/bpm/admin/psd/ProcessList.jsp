<%@ page contentType="text/html;charset=utf-8"%>

<%@ page import="java.text.*"%>
<%@ page import="net.itjds.common.web.CollectionRollPage"%>
<%@ page import="net.itjds.common.dm.DM"%>
<%@ page import="net.itjds.common.util.DateUtility"%>
<%@ page import="net.itjds.common.util.StringUtility"%>
<%@ page import="net.itjds.bpm.engine.query.*"%>
<jsp:directive.page import="java.util.List"/>
<jsp:directive.page import="java.util.ArrayList"/>
<jsp:directive.page import="net.itjds.bpm.client.BPMSessionFactory"/>
<jsp:directive.page import="java.util.Iterator"/>
<jsp:directive.page import="net.itjds.bpm.engine.WorkflowClientService"/>
<jsp:directive.page import="net.itjds.bpm.engine.AdminService"/>
<jsp:directive.page import="net.itjds.bpm.engine.WorkflowServer"/>
<jsp:directive.page import="net.itjds.bpm.client.ProcessInst"/>
<jsp:directive.page import="net.itjds.common.org.base.Person"/>
<jsp:directive.page import="net.itjds.bpm.engine.OARightConstants"/>
<jsp:directive.page import="net.itjds.bpm.client.ActivityInst"/>
<jsp:directive.page import="net.itjds.bpm.admin.AdminConfig"/>
 <%@ include file="/bpm/admin/include/jsp/global.jsp" %>
<%
	String contextpath = request.getContextPath()+"/";
	int pageSize = 10;
	String selectedSystemCode = request.getParameter("SystemCode");
		String fileTitle = request.getParameter("fileTitle");
	String selectedProcessDefId = request.getParameter("ProcessDefId");

	String selectedProcessName = StringUtility.filterNull(request.getParameter("ProcessName"));
	String selectedProcessRunStatus = request.getParameter("ProcessRunStatus");
	
	String timeStart = StringUtility.filterNull(request.getParameter("TimeStart"));
	String timeEnd = StringUtility.filterNull(request.getParameter("TimeEnd"));

    String queryString = null;
    if(selectedSystemCode!=null && !selectedSystemCode.equals("")) {
        if(queryString == null) {
            queryString = "?";
        } else {
            queryString += "&";
        }
        queryString += "SystemCode=" + selectedSystemCode;
    }
    
    
    
	
    if(selectedProcessDefId!=null && !selectedProcessDefId.equals("")) {
        if(queryString == null) {
            queryString = "?";
        } else {
            queryString += "&";
        }
        queryString += "ProcessDefId=" + selectedProcessDefId;
    }
    if(queryString == null) {
        queryString = "";
    }

%>




<HTML>
<HEAD>
<TITLE>流程监控与疏导</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"> 
<meta http-equiv='expires' content='0'>
<meta http-equiv="Pragma" content="no-cache">
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
<link href="<%=contextpath %>bpm/admin/include/css/style.css" rel="stylesheet" type="text/css">
<SCRIPT LANGUAGE="JavaScript" src="<%=contextpath %>bpm/admin/include/js/jcommon.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
<!--
function _query()
{
	if (!_check()) {
		return false;
	}
	var sForm = document.frmQuery;
	sForm.action = "/bpm/admin/psd/ProcessList.jsp<%=queryString%>";
	sForm.submit();
	return true;
}

function _del() {
    var sForm = document.frm;
    var ids = sForm.processInstId;
    if(ids == null ) {
        alert("请选择要删除的流程！");
        return;
    }
    if(ids.length == null) {
        ids = new Array(ids);
    }
    var bDelFlag = false;
    for(var i=0; i<ids.length; i++) {
        if(ids[i].checked == true) {
            bDelFlag = true;
            break;
        }
    }
    if(bDelFlag == false) {
        alert("请选择要删除的流程！");
        return;
    }

    var result = confirm("您确定要删除这些流程吗？");
    if(result == true) {
    	sForm.action = "/bpm/admin/psd/ProcessDelete.jsp<%=queryString%>";
        sForm.submit();
    }
}

function _abort() {
    var sForm = document.frm;
    var ids = sForm.processInstId;
    if(ids == null ) {
        alert("请选择要中止的流程！");
        return;
    }
    if(ids.length == null) {
        ids = new Array(ids);
    }
    var bDelFlag = false;
    for(var i=0; i<ids.length; i++) {
        if(ids[i].checked == true) {
            bDelFlag = true;
            break;
        }
    }
    if(bDelFlag == false) {
        alert("请选择要中止的流程！");
        return;
    }

    var result = confirm("您确定要中止这些流程吗？");

    if(result == true) {
        sForm.action = "/bpm/admin/psd/ProcessAbort.jsp<%=queryString%>";
        sForm.submit();
    }
}

function goPage(page)
{
	var sForm = document.frm;
	if (Trim(page)!="") { 
		if(!is_int(page)) { 
			alert("页码必须为整数！");
			return false;
		}
	} else {
		return false;
	}
	sForm.page.value = page;
	sForm.action = "/bpm/admin/psd/ProcessList.jsp<%=queryString%>";
	sForm.submit();
}

function _check()
{
	var sForm = document.frmQuery;
	if (Trim(sForm.TimeStart.value)!="" && !is_Date(sForm.TimeStart.value, 0)) {
		alert("请正确填写开始时间（yyyy-MM-dd格式）!");
		sForm.TimeStart.select();
		sForm.TimeStart.focus();
		return false;
	}

	if (Trim(sForm.TimeEnd.value)!="" && !is_Date(sForm.TimeEnd.value, 0)) {
		alert("请正确填写结束时间（yyyy-MM-dd格式）!");
		sForm.TimeEnd.select();
		sForm.TimeEnd.focus();
		return false;
	}

	if (Trim(sForm.TimeStart.value)!="" && Trim(sForm.TimeEnd.value)!="" && !_compareTwoDate(sForm.TimeStart, sForm.TimeEnd)) {
		alert("开始时间不应该比结束时间大！");
		sForm.TimeEnd.select();
		sForm.TimeEnd.focus();
		return false;
	}
	return true;
}
//-->
</SCRIPT>
</HEAD>
<BODY   leftmargin=0 topmargin=0 marginwidth=0 marginheight=0> 

<script src="<%=contextPath%>/bpm/admin/include/js/calendar.js"></script>
<TABLE width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
  <tr> 
    <td height="8" bgcolor="E3E8F8" colspan=3> </td>
  </tr>
  <tr> 
    <td align="left" valign="top" bgcolor="F5F5F5"> 

    </td>
<%
	boolean isFirst = false;
	List systemCodeList = (List) session.getAttribute("psd_systemCodeList");
	if (systemCodeList == null) {
		isFirst = true;
		systemCodeList = new ArrayList();
		
	}
	if (systemCodeList.isEmpty()){
	systemCodeList.add("oa");
	}
	
	
	String personId = (String)session.getAttribute("personId");
	
	BPMSessionFactory sessionFactory = new BPMSessionFactory();
	String processDefId = request.getParameter("processDefId");
	List processInstList = new ArrayList();
	for (Iterator it = systemCodeList.iterator(); it.hasNext();) {
        String systemCode = (String) it.next();
		if (selectedSystemCode != null && !selectedSystemCode.equals(systemCode)) {
			continue;
		}

		
		WorkflowClientService client = sessionFactory.getClientService(request, systemCode);

		AdminService admin = WorkflowServer.getInstance().getAdminService(client);
		if (admin == null) {
			continue;
		}

		

		//人员判断条件
		String sql ="";
		if(!personId.equals(AdminConfig.getValue("SuperAdmin.username"))){
		      sql = "SELECT RT_PROCESSDEF_SUPERVISOR.PROCESSDEF_ID FROM RT_PROCESSDEF_SUPERVISOR WHERE RT_PROCESSDEF_SUPERVISOR.SUPERVISOR_ID='" + personId + "'";
		}else{
		   sql = "SELECT RT_PROCESSDEF_SUPERVISOR.PROCESSDEF_ID FROM RT_PROCESSDEF_SUPERVISOR";
		};
		System.out.println(sql);
         Condition userCon = new Condition(ConditionKey.PROCESSINST_PROCESSDEF_ID, Condition.IN, sql);
		if (selectedProcessDefId != null) {
			Condition defCon = new Condition(ConditionKey.PROCESSINST_PROCESSDEF_ID, Condition.EQUALS, selectedProcessDefId);
			userCon.addCondition(defCon, Condition.JOIN_AND);
		}
		if (selectedProcessRunStatus != null && !selectedProcessRunStatus.equals("-1")) {
			Condition runStatusCon = new Condition(ConditionKey.PROCESSINST_RUNSTATUS, Condition.EQUALS, selectedProcessRunStatus);
			userCon.addCondition(runStatusCon, Condition.JOIN_AND);
		}
		if (!selectedProcessName.equals("")) {
			Condition nameCon = new Condition(ConditionKey.PROCESSINST_PROCESSDEF_ID, Condition.IN, "(SELECT PROCESSDEF_ID FROM BPM_PROCESSDEF WHERE DEFNAME LIKE '%"+selectedProcessName+"%')");
			userCon.addCondition(nameCon, Condition.JOIN_AND);
		}
		if (!timeStart.equals("")) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				long time = df.parse(timeStart).getTime();
				Condition timeCon = new Condition(ConditionKey.PROCESSINST_STARTTIME, Condition.GREATER_THAN, new Long(time));
				userCon.addCondition(timeCon, Condition.JOIN_AND);
			} catch(ParseException e) {}
		}
		if (!timeEnd.equals("")) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				long time = df.parse(timeEnd).getTime();
				Condition timeCon = new Condition(ConditionKey.PROCESSINST_STARTTIME, Condition.LESS_THAN, new Long(time));
				userCon.addCondition(timeCon, Condition.JOIN_AND);
			} catch(ParseException e) {}
		}

		//加入状态条件
		List stateList = new ArrayList();
		stateList.add(ProcessInst.STATE_RUNNING);
		stateList.add(ProcessInst.STATE_SUSPENDED);
		Condition stateCon = new Condition(ConditionKey.PROCESSINST_STATE, Condition.IN, stateList);
		userCon.addCondition(stateCon, Condition.JOIN_AND);
        //安拟稿时间排序
        
        	//标题处理
		if (fileTitle != null && fileTitle.trim().length()>0) {
			String whereSql = " WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='FILETITLE' AND PROPVALUE like '%" + fileTitle + "%' ) ";
			String inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
			Condition processC = new Condition(ConditionKey.PROCESSINST_ID,
					Condition.IN, inSql + whereSql);
			userCon.addCondition(processC, Condition.JOIN_AND);
		}
        
        Order order = new Order(ConditionKey.PROCESSINST_STARTTIME, false);
        userCon.addOrderBy(order);
        //modify by lxl 2004-3-16 不能支持toArray()方法，所以不能用addAll方法
        //processInstList.addAll(admin.getProcessInstList(userCon, null, null));
        List tmpProcessInstList = admin.getProcessInstList(userCon, null, null);
        for(int j=0; j<tmpProcessInstList.size(); j++) {
            processInstList.add(tmpProcessInstList.get(j));
        }
    }
	CollectionRollPage rollPage = new CollectionRollPage(request);
    Iterator processInstIterator = rollPage.rollAction(processInstList, pageSize);
%>
	<!-- 信息列表 begin --> 
    <td height="100%" align="center" valign="top">
	  <table width="98%" border=0 align=center cellspacing="3">
	
      </table> 
      <table width="98%" border=0 align=center cellpadding=3 cellspacing=0 bgcolor="#F9F9F9" class=tr>
        <tr>
          <td height=21 colspan=2 bgcolor=#E3E8F8 class=td>■<font color=#ffffff>&nbsp;</font>查询条件</td>
        </tr> 
        <tr> 
          <form method="post" name="frmQuery" action=""> 
            <td class=td align=center height=21>
			  <table width="99%" border="0" cellspacing="0" cellpadding="0"> 
               <tr> 
                  <td width="12%" height=23 align="right">流程名称：</td> 
                  <td width="12%" height=23>
				    <input name="ProcessName" type="text" class="inputtxt" size="16" value="<%=selectedProcessName%>">
				  </td> 
				     <td width="12%" height=23 align="right">文件标题:</td> 
				     <td width="12%" height=23>
				    <input name="fileTitle" type="text" class="inputtxt" size="16" value="<%=fileTitle==null?"":fileTitle%>">
				  </td> 
                  <td width="18%" height="23" align="right">运行状况：</td> 
                  <td width="32%" height="23">
				    <select name="ProcessRunStatus">
				    <%=DM.getSelectOption("ProcessInst_RUNSTATUS", selectedProcessRunStatus, 1)%>
                
                    </select>
				 </td> 
                </tr> 
                <tr> 
                  <td width="18%" height=23 align="right">拟稿时间：</td> 
                  <td colspan="3">
					<input type="text" name="TimeStart" id="TimeStart" size=16 class="inputtxt" value="<%=timeStart%>">
					<img src="/bpm/admin/images/calendar.gif" align="top" border="0" onclick="document.all.TimeStart.value=showCalendar(document.all.TimeStart.value)"> 到
					<input type="text" name="TimeEnd" ID="TimeEnd" size=16 class="inputtxt" value="<%=timeEnd%>">
					<img src="/bpm/admin/images/calendar.gif" align="top" border="0" onclick="document.all.TimeEnd.value=showCalendar(document.all.TimeEnd.value)">
                  </td>
                </tr> 
              </table>
			</td> 
            <td width="103" height="21" align="center" class="td"> 
			  <table width="98" border="0" cellspacing="0" cellpadding="0"> 
                <tr> 
                  <td height="25" align="center"> <input name="Submit22" type="button" class="inputbutton" style="cursor:hand;" onclick="_query();" value="查 询"> </td> 
                </tr> 
              </table>
			</td> 
          </form> 
        </tr> 
      </table> 
      <br> 
      <!--列表 begin-->
	  <table width="98%" border=0 cellpadding=3 cellspacing=0 class=tr>
		<form name="frm" method="post">
		  <!-- 隐藏域  -->
		  <input type="hidden" name="page" value="1">
		  <!-- 隐藏域  -->
		  <tr align=center bgcolor=#e3e8f8>
			<td height="21" colspan="2" width="7%" class="td" bgcolor="E3E8F8">序号</td> 
				 <td height="21"class="td" width="10%" bgcolor="E3E8F8">流程名称</td>
			<td height="21"class="td" width="15%" bgcolor="E3E8F8">标题</td> 
			<td height="21"class="td" width="8%" bgcolor="E3E8F8">流程启动人</td>
		
              <td height="21"class="td" width="10%" bgcolor="E3E8F8">起草时间</td>
         
			<td height="21"class="td" width="5%" bgcolor="E3E8F8">运行状况</td>
			<td height="21"class="td" width="10%" bgcolor="E3E8F8">办理步骤</td> 
			<td height="21"class="td" width="10%" bgcolor="E3E8F8">当前待办/办理人</td> 
				<td height="21"class="td" width="8%" bgcolor="E3E8F8">办理状态</td> 
			<td height="21"class="td" width="5%" bgcolor="E3E8F8">操作</td> 
		  </tr> 
<%
int i = 0;
int rollPageSize = rollPage.getPerPageSize();
for (; i < rollPageSize; i++) {
	if(!processInstIterator.hasNext()) {
		break;
	}
	ProcessInst pi = (ProcessInst) processInstIterator.next();
	String systemCode = pi.getProcessDef().getSystemCode();
	Person starter = (Person) pi.getRightAttribute(OARightConstants.PROCESS_INSTANCE_STARTER);
	String starterName = "";
	if(starter != null ) {
		starterName = starter.getName();
	}

	List activityInstList = (List) pi.getActivityInstList();
	ActivityInst ai = null;
	int count = 0;
	if (activityInstList!=null && activityInstList.size() != 0) {
		for(int j=0, n=activityInstList.size(); j<n; j++) {
			WorkflowClientService client = sessionFactory.getClientService(request, systemCode);
			ai = (ActivityInst)activityInstList.get(j);
		   List<Person> personList=(List<Person>) client.getActivityInstRightAttribute(ai.getActivityInstId(), OARightConstants.ACTIVITYINST_RIGHT_ATT_PERFORMER, null);
			 Person person=null;
			if (personList.size()==0){
				personList=(List<Person>) client.getActivityInstRightAttribute(ai.getActivityInstId(), OARightConstants.ACTIVITYINST_RIGHT_ATT_READER, null);
				if(personList != null && personList.size() > 0){
					 person=personList.get(0);
				}
			}else{
			person=personList.get(0);
			}
			
			
			
			count++;
			try{
%>
		  <tr height="21"> 
		  <%if (count == 1) {%>
			<td class=td id=td_process<%=i%> width="4%">
			  <input type="checkbox" name="processInstId" value="<%=pi.getProcessInstId() + "|" + systemCode%>">
			</td>
			<td class=td id=td_process<%=i%>><%=(i + 1) %></td>
			<td class=td id=td_process<%=i%>>&nbsp;<%=pi.getProcessDef().getName()%></td>
			<td class=td id=td_process<%=i%>>&nbsp;<%=pi.getAttribute("fileTitle")%></td>
			<td class=td id=td_process<%=i%>>&nbsp;<%=starterName%></td>
			
			<td class=td id=td_process<%=i%>>&nbsp;<%=DateUtility.formatDate(pi.getStartTime(), "yyy-MM-dd HH:mm:ss") %></td>
			<td class=td id=td_process<%=i%>>&nbsp;<%=DM.convertDM("ProcessInst_RUNSTATUS", pi.getRunStatus()) %></td>
		  <%}%>
			<td class=td>&nbsp;<%=ai.getActivityDef().getName() %></td>
			<td class=td>&nbsp;<%=person==null?"":person.getName()%></td>
			<td class=td>&nbsp;<%=DM.convertDM("ActivityInst_STATE", ai.getState()) %></td>
			<td class=td align="center">
				&nbsp;<input type="button" class="inputbutton" value="疏导" onClick="document.location='/bpm/admin/psd/<%=systemCode%>/ProcessDisplay.jsp?ActivityInstId=<%=ai.getActivityInstId() %>'">&nbsp;
			</td>
		  </tr> 
			
<%			} catch (Exception e) {}
		} // end of for j
		if (count >0) {
%>
		<script>
			var os = document.all.td_process<%=i%>;
			for (var i=0; i<os.length ;i++) {
				os[i].rowSpan = <%=count%>;
			}
		</script>
<%					
		}
	}
  }
%>
<%
for(; i<rollPageSize; i++)
{
%>
	      <tr height="21"> 
		    <td class="td">&nbsp;</td>
		    <td class="td">&nbsp;</td>
		    <td class="td">&nbsp;</td>
		    <td class="td">&nbsp;</td>
  		    <td class="td">&nbsp;</td>
		    <td class="td">&nbsp;</td>
		    <td class="td">&nbsp;</td>
		    <td class="td">&nbsp;</td>
		    <td class="td">&nbsp;</td>
		     <td class="td">&nbsp;</td>
		    <td class="td">&nbsp;</td>
		  </tr>
<%}%>
	  </table> 
	  <!--翻页按钮-->
	  <table width="98%" border=0 cellpadding=3 cellspacing=0>
		  <%@ include file="/bpm/admin/include/jsp/rollPage.jsp"%>
	  </table>
      <!-- 操作 begin --> 
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0"> 
        <tr> 
           <td align=center height="15">&nbsp;</td>
        </tr>
        <tr align="center"> 
          <td height="40" width="100%">
            <input type="button" class="inputbutton" value="中 止" onClick="_abort()">&nbsp;&nbsp;
            <input type="button" class="inputbutton" value="删 除" onclick="_del()">
          </td> 
        </tr> 
      </table> 
      <!-- 操作 end --> 
    </td> 
    <!-- 信息列表 end --> 
  </tr> 
  </form>
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
