<%@ page import="net.itjds.bpm.client.*"%>
<%
    ActivityInst BPM_activityInst = (ActivityInst) request.getAttribute("BPM_activityInst");
    ProcessInst BPM_processInst = (ProcessInst) request.getAttribute("BPM_processInst");
    String BPM_contextPath = request.getContextPath();
%>
<SCRIPT LANGUAGE="JavaScript">
<!--
<%if(BPM_activityInst != null) { //显示活动实例
%>

function BPM_routeToEnd(activityDefId) {
    document.all.BPM_UpdateType.value="routetoend";
	document.all.BPM_RouteToActivityDefIds.value=activityDefId;
	
	if((typeof BPM_doRouteTo) == "function") 
	{
		BPM_doRouteTo();
		return true;
	}
}

function parseReturn(ret) {
    if(ret != null )
    {
		var k, activityDefIds="", allPerformers="", allReaders=""; performTypes="";
		for (k=0; k<ret.length; k++) {
			var ret0 = ret[k];
			//performers
			var performers = ret0.performers;
			var strPerformer=",";
			for(var i=0; i<performers.length; i++) {
				strPerformer = strPerformer + performers[i].id + ","
			}
			//readers
			var readers = ret0.readers;
			var strReader=",";
			for(var i=0; i<readers.length; i++) {
				strReader = strReader + readers[i].id + ","
			}
			if (k != ret.length-1) {
				activityDefIds = activityDefIds + ret0.activityDefId + ",";
				performTypes = performTypes + ret0.performType + ",";
				allPerformers = allPerformers + strPerformer + ";";
				allReaders = allReaders + strReader + ";";
			} else {
				activityDefIds = activityDefIds + ret0.activityDefId;
				performTypes = performTypes + ret0.performType;
				allPerformers = allPerformers + strPerformer;
				allReaders = allReaders + strReader;
			}
		}
		if (k >1) {
			document.all.BPM_UpdateType.value="multirouteto";
		} else {
			document.all.BPM_UpdateType.value="routeto";
		}
		document.all.BPM_RouteToActivityDefIds.value=activityDefIds;
		document.all.BPM_PerformTypes.value=performTypes;
		document.all.BPM_Performers.value = allPerformers; 
		document.all.BPM_Readers.value = allReaders; 
		return true;
    } 
    return false;
}

function BPM_routeTo(routeId) {
    //发出即将路由消息
  	if( (typeof BPM_onRouteTo) == "function" ) BPM_onRouteTo(routeId);
	if( (typeof BPM_checkRouteToValid) == "function") 
	{
		if(BPM_checkRouteToValid() == false)
		{
			return false;
		}
	}

    var _url = "<%=BPM_contextPath%>/bpm/admin/common/SelectPerformer?routeDefId=" + routeId + "&activityInstId=<%=BPM_activityInst.getActivityInstId()%>";
    var ret = window.showModalDialog(_url,null,"dialogHeight: 350px; dialogWidth: 565px; dialogTop: px; dialogLeft: px; edge: Raised; center: Yes; help: No; resizable: No; status: No;");
	
	if (!parseReturn(ret)) {
		return false;
	}
	
	if((typeof BPM_doRouteTo) == "function") 
	{
		BPM_doRouteTo();
		return true;
	}
	return false;
} 

function BPM_superviseRouteTo() {
    //发出即将路由消息
  	if( (typeof BPM_onSuperviseRouteTo) == "function" ) BPM_onSuperviseRouteTo();
	if( (typeof BPM_checkSuperviseRouteToValid) == "function") 
	{
		if(BPM_checkSuperviseRouteToValid() == false)
		{
			return false;
		}
	}

    var _url = "<%=BPM_contextPath%>/bpm/admin/common/SuperviseSelectPerformer.jsp?activityInstId=<%=BPM_activityInst.getActivityInstId()%>";
    var ret = window.showModalDialog(_url,null,"dialogHeight: 350px; dialogWidth: 585px; dialogTop: px; dialogLeft: px; edge: Raised; center: Yes; help: No; resizable: No; status: No;");

	if (!parseReturn(ret)) {
		return false;
	}
	document.all.BPM_Admin.value = "true";
	
	if((typeof BPM_doSuperviseRouteTo) == "function") 
	{
		BPM_doSuperviseRouteTo();
		return true;
	}
	return false;
} 

function BPM_multiRouteTo() {
    //发出即将路由消息
  	if( (typeof BPM_onMultiRouteTo) == "function" ) BPM_onMultiRouteTo();
	if( (typeof BPM_checkMultiRouteToValid) == "function") 
	{
		if(BPM_checkMultiRouteToValid() == false)
		{
			return false;
		}
	}

    var _url = "<%=BPM_contextPath%>/bpm/admin/common/MultiRouteSelectPerformer?activityInstId=<%=BPM_activityInst.getActivityInstId()%>";
    var ret = window.showModalDialog(_url,null,"dialogHeight: 350px; dialogWidth: 585px; dialogTop: px; dialogLeft: px; edge: Raised; center: Yes; help: No; resizable: No; status: No;");

	if (!parseReturn(ret)) {
		return false;
	}

	if((typeof BPM_doMultiRouteTo) == "function") 
	{
		BPM_doMultiRouteTo();
		return true;
	}
	if((typeof BPM_doRouteTo) == "function") 
	{
		BPM_doRouteTo();
		return true;
	}
	
	return false;
}

function BPM_showRouteLog() {
	var _url = "<%=BPM_contextPath%>/bpm/admin/common/RouteLog?ActivityInstId=<%=BPM_activityInst.getActivityInstId()%>";
	window.open(_url, "_blank", "width=620,height=440,left=20,top=10,toolbar=0,location=0,directories=0,resizable=1,scrollbars=1");
	return true;
}

function BPM_RouteBack() {
    //发出即将路由消息
  	if( (typeof BPM_onRouteBack) == "function" ) BPM_onRouteBack(routeId);
	if( (typeof BPM_checkRouteBackValid) == "function") 
	{
		if(BPM_checkRouteBackValid() == false)
		{
			return false;
		}
	}

    var _url = "<%=BPM_contextPath%>/bpm/admin/common/SelectBackRoute?activityInstId=<%=BPM_activityInst.getActivityInstId()%>";
    var ret = window.showModalDialog(_url,null,"dialogHeight: 350px; dialogWidth: 565px; dialogTop: px; dialogLeft: px; edge: Raised; center: Yes; help: No; resizable: No; status: No;");
    if(ret != null )
    {
		document.all.BPM_BackToActivityInstHistoryId.value = ret;
	    document.all.BPM_UpdateType.value="routeback";

		if((typeof BPM_doRouteBack) == "function") 
		{
		    BPM_doRouteBack();
			return true;
		}
		return false;
	}
	return false;
}


function BPM_superviseRouteBack() {
    //发出即将路由消息
  	if( (typeof BPM_onSuperviseRouteBack) == "function" ) BPM_onSuperviseRouteBack(routeId);
	if( (typeof BPM_checkSuperviseRouteBackValid) == "function") 
	{
		if(BPM_checkSuperviseRouteBackValid() == false)
		{
			return false;
		}
	}

    var _url = "<%=BPM_contextPath%>/bpm/admin/common/SelectBackRoute?activityInstId=<%=BPM_activityInst.getActivityInstId()%>&Admin=true";
    var ret = window.showModalDialog(_url,null,"dialogHeight: 350px; dialogWidth: 565px; dialogTop: px; dialogLeft: px; edge: Raised; center: Yes; help: No; resizable: No; status: No;");
    if(ret != null )
    {
		document.all.BPM_BackToActivityInstHistoryId.value = ret;
	    document.all.BPM_UpdateType.value="routeback";
		document.all.BPM_Admin.value = "true";

		if((typeof BPM_doSuperviseRouteBack) == "function") 
		{
		    BPM_doSuperviseRouteBack();
			return true;
		}
		return false;
	}
	return false;
}

//新增活动实例id 参数，更改指定活动实例办理人
function BPM_changePerformer(aid) {
    //发出即将路由消息
  	if( (typeof BPM_onChangePerformer) == "function" ) BPM_onChangePerformer(routeId);
	if( (typeof BPM_checkChangePerformerValid) == "function") 
	{
		if(BPM_checkChangePerformer() == false)
		{
			return false;
		}
	}

    var _url = "<%=BPM_contextPath%>/bpm/admin/common/ChangePerformer.jsp?activityInstId="+aid+"&Admin=true";
    var ret = window.showModalDialog(_url,null,"dialogHeight: 350px; dialogWidth: 500px; dialogTop: px; dialogLeft: px; edge: Raised; center: Yes; help: No; resizable: No; status: No;");
	if (!parseReturn(ret)) {
		return false;
	}

	document.all.BPM_UpdateType.value="changeperformer";
	document.all.BPM_Admin.value = "true";

	if((typeof BPM_doChangePerformer) == "function") 
	{
		BPM_doChangePerformer(aid);//为此方法也新增一个参数
		return true;
	}
	return false;
}
<%} //这些方法必须有活动实例才能运行
%>

<%if(BPM_activityInst == null && BPM_processInst != null) {%> 
function BPM_showRouteLog() {
	var _url = "<%=BPM_contextPath%>/bpm/admin/common/RouteLog?ProcessInstId=<%=BPM_processInst.getProcessInstId()%>";
	window.open(_url, "_blank", "width=620,height=440,left=20,top=10,toolbar=0,location=0,directories=0,resizable=1,scrollbars=1");
	return true;
}

<%} //这些方法必须有流程实例才能运行
%>

//-->
</SCRIPT>
<span id="BPM_control_field" style="display:none">
	<input type="hidden" name="BPM_UpdateType" value="saveonly">
	<input type="hidden" name="BPM_PerformTypes">
	<input type="hidden" name="BPM_Admin">
	<!--退回到活动实例历史的标识-->
	<input type="hidden" name="BPM_BackToActivityInstHistoryId">
	<input type="hidden" name="BPM_RouteToActivityDefIds">
	<input type="hidden" name="BPM_Performers">
	<input type="hidden" name="BPM_Readers">
</span>
