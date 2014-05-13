<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String personid = request.getParameter("personid");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'rcap.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="/desktop/widgets/schedule/dhtmlxScheduler/dhtmlxscheduler.css" type="text/css" media="screen" title="no title" charset="utf-8"/>
<script src="/desktop/widgets/schedule/dhtmlxScheduler/selfdhtmlxscheduler.js" type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" href="/desktop/widgets/schedule/dhtmlxScheduler/dhtmlxscheduler.css" type="text/css" charset="utf-8">
<script src="/desktop/widgets/schedule/dhtmlxScheduler/locale_cn1.js" type="text/javascript" charset="utf-8"></script>
<script src="/desktop/widgets/schedule/dhtmlxScheduler/ext/dhtmlxscheduler_agenda_view.js"></script>
<link rel="stylesheet" href="/desktop/widgets/schedule/dhtmlxScheduler/ext/dhtmlxscheduler_ext.css" type="text/css">
<script src="/desktop/widgets/schedule/dhtmlxScheduler/locale_recurring_cn.js" type="text/javascript" charset="utf-8"></script>

<script src="/desktop/widgets/schedule/dhtmlxToolbar/dhtmlxcommon.js"></script>
<script src="/desktop/widgets/schedule/dhtmlxToolbar/dhtmlxtoolbar.js"></script>
<link rel="stylesheet" type="text/css" href="/desktop/widgets/schedule/dhtmlxToolbar/skins/dhtmlxtoolbar_dhx_skyblue.css"></link>
<script type="text/javascript" src="/desktop/widgets/schedule/js/dateday.js"></script>
<script type="text/javascript" src="/desktop/widgets/schedule/js/prototype.js"></script>
<style type="text/css" media="screen">
		html, body{
			margin:0px;
			padding:0px;
			height:100%;
			overflow:hidden;
		}	
		
		/*event in day or week view*/
	.dhx_cal_event.past_event div{
		background-color:#66FFFF !important; 
		color:#000000 !important;
		font-size:12pt;
	}
    /*multi-day event in month view*/
	.dhx_cal_event_line.past_event{
		background-color:#66FFFF !important; 
		color:#000000 !important;
		font-size:12pt;
		height:17px;
	}
    /*event with fixed time, in month view*/
	.dhx_cal_event_clear.past_event{
		color:purple !important;
	}
	</style>
<script language="JavaScript">
	var toolbar;
	function schedulerInit(){
		//var oday1 = "2011-10-14 15:04:09";
		var oday1 = formatDate(new Date());
			scheduler.config.xml_date="%Y-%m-%d %H:%i";
			scheduler.config.multi_day = true;
			scheduler.config.edit_on_create = true;
			scheduler.config.details_on_create = true;
			scheduler.config.details_on_dblclick = true;
			//scheduler.config.readonly = true;
			//scheduler.attachEvent("onEventAdded", addOrUpdate);
			//scheduler.attachEvent("onEventChanged", addOrUpdate);
			//scheduler.attachEvent("onBeforeEventDelete", del);
			scheduler.attachEvent("onBeforeViewChange", beforeViewChange);
			//scheduler.attachEvent("onEventLoading", eventLoad);
			scheduler.config.start_on_monday = true;
			scheduler.config.scroll_hour = 8;
			//scheduler.config.show_loading = true;
			scheduler.config.time_step  = 1;
			scheduler.config.hour_size_px = 50;
			var year = oday1.substr(0,4);
			var month = oday1.substr(5,2);
			var day = oday1.substr(8,2);
            
			scheduler.init('scheduler_here',new Date(parseInt(year), parseInt(month)-1, parseInt(day)), "week");
			
			scheduler.templates.event_class=function(start,end,event){
			    if (start < (new Date())) //if date in past
			          return "past_event"; //then set special css class for it
			}
			
			scheduler.config.lightbox.sections=[	
					{name:"rztype", height:25, type:"textarea", map_to:"type", focus:false},
					{ name:"description", height:200, map_to:"text", type:"textarea" , focus:true},
					
					{ name:"time", height:72, type:"time", map_to:"auto"}	
				]
				
	}
	
	function toolBarInit(){
		toolbar = new dhtmlXToolbarObject("toolbarObj"); 
		toolbar.setIconsPath("/desktop/widgets/schedule/dhtmlxToolbar/icon/");
		//toolbar.attachEvent("onClick", toolBarClick);
		//toolbar.attachEvent("onXLE", toolBarXLE);
		toolbar.loadXML("/desktop/widgets/schedule/pages/gzrz/gzrz.xml?etc="+new Date().getTime());
	}
	function toolBarXLE(){
		toolbar.disableItem("from");
		toolbar.disableItem("to");
		toolbar.setValue("from", "2010-05-19 14:36");
	}
	function toolBarClick(id){
		alert(id);
	}
	
	function init() {
			
		schedulerInit();
	//	toolBarInit();	
			
	}
	
	function eventLoad(event_object){
		if (event_object.end_date < (new Date()))
			event_object.readonly=true;
	}
	 
	function addOrUpdate(event_id,event_object){
	
		/*var ss = formatDate(event_object.start_date);
		
		var se = formatDate(event_object.end_date);
		
		new Ajax.Request('/rcapAction.action', {
			 method: 'post',
			 parameters: {eventId: event_object.id, from: ss, to: se, content: event_object.text,rzType: event_object.type},
			 onSuccess: function(){
			 	alert("保存失败");
			 },
			 onFailure: function(){
			 	alert("保存成功");
			 }
			 });*/

		
	}
	
	function del(event_id,event_object){
		var retvalue = true;
		new Ajax.Request('/rcapAction_delete.action', {
			 method: 'post',
			 parameters: {eventId: event_object.id},
			 onSuccess: function(){
			 	alert("删除失败");
			 	retvalue=true;
			 },
			 onFailure: function(){
			 	alert("删除成功");
			 	retvalue=false;
			 }
			 });
		//alert(retvalue);
		return retvalue;
			 
	}
	
	function beforeViewChange(old_mode, old_date, mode , date){
	    //alert('<%=personid%>');
		var date1 = new Date();
		var datestr = formatDate(date);
		var mode1;
		if(mode == null || mode == 'undefined')
			mode1 = old_mode;
		else{
			mode1 = mode;
		}
		var url = "/rcapAction_find.action?date=" + date1.toLocaleString() + "&queryModel=" + mode1 + "&oday=" + datestr+"&personid=<%=personid%>";
		scheduler.load(url);
		return true;  
	}
	
	//function (event_id,event_object)
	
	function formatDate(dateObj){
		var result;
		var year;
		var month;
		var date;
		var hours;
		var minutes;
		var seconds;
		//alert(dateObj);
		if (Prototype.Browser.IE) {
			year = dateObj.getYear();
		} else {
			year = dateObj.getYear() + 1900;
		}
		month = dateObj.getMonth() + 1;
		// 添加开始
	    if(month<10){
          month="0"+month;
          
        }
        //  添加结束
		date = dateObj.getDate();
		hours = dateObj.getHours();
		minutes = dateObj.getMinutes();
		seconds = dateObj.getSeconds();
		result = year + "-" + month + "-" + date + " " +  hours + ":" + minutes + ":" + seconds;
		
		return result;
	}
</script>

  </head>
  
  <body onload="init();">
    <div id="scheduler_here" class="dhx_cal_container" style='width:100%; height:100%;'>
		<div class="dhx_cal_navline">
			<div class="dhx_cal_prev_button">&nbsp;</div>
			<div class="dhx_cal_next_button">&nbsp;</div>
			<div class="dhx_cal_today_button"></div>
			<div class="dhx_cal_date"></div>
			<div class="dhx_cal_tab" name="day_tab" style="right:204px;"></div>
			<div class="dhx_cal_tab" name="week_tab" style="right:140px;"></div>
			<div class="dhx_cal_tab" name="month_tab" style="right:76px;"></div>
		</div>
		<div class="dhx_cal_header">
		</div>
		<div class="dhx_cal_data">
		</div>		
	</div>
  </body>
</html>
