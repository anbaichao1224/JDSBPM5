<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'schedule.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" href="/desktop/widgets/schedule/dhtmlxScheduler/dhtmlxscheduler.css" type="text/css" media="screen" title="no title" charset="utf-8"/>
<script src="/desktop/widgets/schedule/dhtmlxScheduler/dhtmlxscheduler.js" type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" href="/desktop/widgets/schedule/dhtmlxScheduler/dhtmlxscheduler.css" type="text/css" charset="utf-8">
<script src="/desktop/widgets/schedule/dhtmlxScheduler/locale_cngzrz.js" type="text/javascript" charset="utf-8"></script>
<script src="/desktop/widgets/schedule/dhtmlxScheduler/ext/dhtmlxscheduler_agenda_view.js"></script>
<link rel="stylesheet" href="/desktop/widgets/schedule/dhtmlxScheduler/ext/dhtmlxscheduler_ext.css" type="text/css">
<script src="/desktop/widgets/schedule/dhtmlxScheduler/locale_recurring_cn.js" type="text/javascript" charset="utf-8"></script>

<script src="/desktop/widgets/schedule/dhtmlxToolbar/dhtmlxcommon.js"></script>
<script src="/desktop/widgets/schedule/dhtmlxToolbar/dhtmlxtoolbar.js"></script>
<link rel="stylesheet" type="text/css" href="/desktop/widgets/schedule/dhtmlxToolbar/skins/dhtmlxtoolbar_dhx_skyblue.css"></link>
<script type="text/javascript" src="/desktop/widgets/schedule/js/dateday.js"></script>
<script type="text/javascript" src="/desktop/widgets/schedule/js/prototype.js"></script>
<script type="text/javascript" src="/desktop/widgets/schedule/js/exportExcel.js"></script>
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
	}
    /*multi-day event in month view*/
	.dhx_cal_event_line.past_event{
		background-color:#66FFFF !important; 
		color:#000000 !important;
	}
    /*event with fixed time, in month view*/
	.dhx_cal_event_clear.past_event{
		color:purple !important;
	}
	</style>
<script language="JavaScript">
	var toolbar;
	function schedulerInit(){
		//var oday1 = "2011-01-1 15:04:09";
		var oday1 = formatDate(new Date());
		   
			scheduler.config.xml_date="%Y-%m-%d %H:%i";
			scheduler.config.multi_day = true;
			scheduler.config.edit_on_create = true;
			scheduler.config.details_on_create = true;
			scheduler.config.details_on_dblclick = true;
			scheduler.attachEvent("onEventAdded", addOrUpdate);
			scheduler.attachEvent("onEventChanged", addOrUpdate);
			scheduler.attachEvent("onBeforeEventDelete", del);
			scheduler.attachEvent("onBeforeViewChange", beforeViewChange);
			//scheduler.attachEvent("onEventLoading", eventLoad);
			scheduler.config.start_on_monday = true;
			scheduler.config.scroll_hour = 8;
			scheduler.config.show_loading = true;
			scheduler.config.time_step  = 1;
			scheduler.config.hour_size_px = 50;
			var year = oday1.substr(0,4);
			var month = oday1.substr(5,2);
			var day = oday1.substr(8,2);
       
			scheduler.init('scheduler_here',new Date(parseInt(year), parseInt(month)-1, parseInt(day)), "day");
			
			scheduler.templates.event_class=function(start,end,event){
			    if (start < (new Date())) //if date in past
			          return "past_event"; //then set special css class for it
			}
			
			scheduler.config.lightbox.sections=[	
					
					{ name:"description", height:200, map_to:"text", type:"textarea" , focus:true},
					
					{ name:"time", height:72, type:"time", map_to:"auto"}	
				]
				
	}
	
	function toolBarInit(){
		toolbar = new dhtmlXToolbarObject("toolbarObj"); 
		toolbar.setIconsPath("/desktop/widgets/schedule/dhtmlxToolbar/icon/");
		toolbar.attachEvent("onClick", toolBarClick);
		toolbar.attachEvent("onXLE", toolBarXLE);
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
	
		
		var ss = formatDate(event_object.start_date);
		
		var se = formatDate(event_object.end_date);
		
		new Ajax.Request('/gzrzAction.action', {
			 method: 'post',
			 parameters: {eventId: event_object.id, from: ss, to: se, content: event_object.text,rzType: event_object.type},
			 onSuccess: function(){
			 	alert("保存失败");
			 },
			 onFailure: function(){
			 	alert("保存成功");
			 }
			 });

		
	}
	
	function del(event_id,event_object){
		var retvalue = true;
		new Ajax.Request('/gzrzAction_delete.action', {
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
		var date1 = new Date();
		var datestr = formatDate(date);
		var mode1;
		if(mode == null || mode == 'undefined')
			mode1 = old_mode;
		else{
			mode1 = mode;
		}
		var url = "/gzrzAction_find.action?date=" + date1.toLocaleString() + "&queryModel=" + mode1 + "&oday=" + datestr;
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
	
	function exportExcel(){
	    alert(1);
	    //var date1 = new Date();
		//var datestr = formatDate(date);
		//var mode1;
		//var url = "/gzrzAction_find.action?date=" + date1.toLocaleString() + "&queryModel=" + mode1 + "&oday=" + datestr;
		//var url = "/exportExcelAction.action";
		//scheduler.load(url);
	    //downloadViewData();
		//return true;
			
		new Ajax.Request('/exportExcelAction.action', {
			 method: 'post',
			 //parameters: {eventId: event_object.id, from: ss, to: se, content: event_object.text,rzType: event_object.type},
			 onSuccess: function(){
			 	alert("导出成功");
			 },
			 onFailure: function(){
			 	alert("导出失败");
			 }
			 });
	}
	
	function downloadViewData(){   
       alert(2);
        try {   
           var xls = new ActiveXObject("Excel.Application");   
        } catch (e) {   
           alert("要打印该表，您必须安装Excel电子表格软件，同时浏览器须使用“ActiveX 控件”，您的浏览器须允许执行控件。 请点击【帮助】了解浏览器设置方法！");   
           return "";   
        }   
        var cm = gzrz.getColumnModel();   
        var colCount = cm.getColumnCount();   
     
        xls.visible = true; // 设置excel为可见   
   		var xlBook = xls.Workbooks.Add;   
   		var xlSheet = xlBook.Worksheets(1);   
        var temp_obj = [];   
   // 只下载没有隐藏的列(isHidden()为true表示隐藏,其他都为显示)   
   // 临时数组,存放所有当前显示列的下标   
   		for (i = 2; i < colCount; i++) {   
	    	if (cm.isHidden(i) == true) {   
	    	} else {   
	    		 temp_obj.push(i);   
	    	}   
  		 }   
   		for (i = 1; i <= temp_obj.length; i++) {   
	    	// 显示列的列标题   
	   	 //alert(cm.getColumnHeader(temp_obj[i - 1]));   
	    	xlSheet.Cells(1, i).Value = cm.getColumnHeader(temp_obj[i - 1]);   
  		 }   
  		 var store = grid.getStore();   
  		 var recordCount = store.getCount();   
  		 var view = grid.getView();   
   		for (i = 1; i <= recordCount; i++) {   
	    	for (j = 1; j <= temp_obj.length; j++) {   
	    	 // EXCEL数据从第二行开始,故row = i + 1;   
	    	 xlSheet.Cells(i + 1, j).Value = view.getCell(i - 1, temp_obj[j   
	         - 1]).innerText;   
	   		 }   
   		}   
   		xlSheet.Columns.AutoFit;   
   		xls.ActiveWindow.Zoom = 75   
   		xls.UserControl = true; // 很重要,不能省略,不然会出问题 意思是excel交由用户控制   
  		xls = null;   
   		xlBook = null;   
   		xlSheet = null;   
	}   
</script>
   
  </head>
  
  <body onload="init();">
    <!--  
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" borderColorDark="#ffffff" borderColorLight="#999999">
	<tr >
		<td colspan="2" class="tdb" align="center"><input type="button" class="button" value="导出" onClick="exportExcel()" /></td>
	</tr>
   </table>
   -->
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
