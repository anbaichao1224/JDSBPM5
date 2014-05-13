<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww" %>


<%
	String BPM_contextPath = request.getContextPath()+"/";

%>
<SCRIPT LANGUAGE="JavaScript">


function BPM_routeToEnd(activityDefId) {
   $F('BPM_UpdateType')="routetoend";
   $F('BPM_RouteToActivityDefIds')=activityDefId;	
	if((typeof BPM_doRouteTo) == "function") 
	{
		BPM_doRouteTo();
		return true;
	}
}


function BPM_routeTo(routeId) {
    var _url = "<%=BPM_contextPath%>oa/common/SelectPerformer?routeDefId=" + routeId + "&activityInstId=<ww:property  value="demoActivityBean.fristActivityInstIner.activityInst.activityInstId" />";
	var str="routeDefId=" + routeId + "&activityInstId=<ww:property  value="demoActivityBean.fristActivityInstIner.activityInst.activityInstId"/>";  
    var perFormAjax = getMyAjax('selectPersonAjax', "<%=BPM_contextPath%>SelectPerformerAction.action", str);
   return true;
} 


function BPM_showRouteLog() {
var str="ActivityInstId=<ww:property  value="demoActivityBean.fristActivityInstIner.activityInst.activityInstId" />";
var myAjax = getMyAjax('selectPersonAjax', "<%=BPM_contextPath%>routelog.action", str);
mainWin.style.display="";
if (mainWin.contentDom.innerHTML!=''){
_R(mainWin);
mainWin=initWin();

};
mainWin.contentDom.innerHTML=$('selectPersonAjax').innerHTML;
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

    var _url = "<%=BPM_contextPath%>oa/common/SelectBackRoute?activityInstId=<ww:property  value="demoActivityBean.fristActivityInstIner.activityInst.activityInstId" />";
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

 function routReturn() {  

		$('BPM_UpdateType').value='routeto';		
		var preformArry = preformtree.getAllChecked().split(",");
		var preform=new Array();
		var performType=new Array();
		var readArry = readtree.getAllChecked().split(",");
		var read=new Array();
		 
			var toActivityIds=new Array();	
		for(var i=0;i<preformArry.length;i++){	
		 if (preformArry[i].indexOf('org')!=0){
		   performType[performType.length]=$('BPM_PerformTypes').value;	
		   preform[preform.length]=preformArry[i];
		   toActivityIds[toActivityIds.length]=$('BPM_RouteToActivityDefIds').value;	
		 }			
		}
		
		$('BPM_Performers').value=preform.join(";");
		for(var i=0;i<readArry.length;i++){	
		  if (readArry[i].indexOf('org')!=0){
		    read[read.length]=readArry[i];
		   }			
		}
		 
		$F('BPM_Readers').value=read.join(";");	
		$('BPM_PerformTypes').value=performType.join(',')
	
		$('BPM_RouteToActivityDefIds').value=toActivityIds.join(',')
		
	 var sForm = document.frm;  
    sForm.submit();
    return true;
}


  function () { 
  
  }
 


//-->
</SCRIPT>
    <input type="hidden" name="allFieldRightStr" value='<ww:property value="allFieldRightStr"/>'>
	<input type="hidden" name="activityInstId" value='<ww:property value="activityInstId"/>'>
	<input type="hidden" name="webworkOAUtil.BPM_UpdateType"  id="BPM_UpdateType">
	<input type="hidden" name="webworkOAUtil.BPM_PerformTypes" id="BPM_PerformTypes">
	<input type="hidden" name="webworkOAUtil.BPM_Admin" id="BPM_Admin" >
	<!--退回到活动实例历史的标识-->
	<input type="hidden" name="webworkOAUtil.BPM_BackToActivityInstHistoryId" id="BPM_BackToActivityInstHistoryId">
	<input type="hidden" name="webworkOAUtil.BPM_RouteToActivityDefIds" id="BPM_RouteToActivityDefIds">
	<input type="hidden" name="webworkOAUtil.BPM_Performers" id="BPM_Performers">
	<input type="hidden" name="webworkOAUtil.BPM_Readers" id="BPM_Readers">
<!--
</span>
-->
