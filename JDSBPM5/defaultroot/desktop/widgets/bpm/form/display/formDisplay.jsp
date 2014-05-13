<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,
	com.opensymphony.xwork2.ActionContext,
	java.util.List,net.itjds.bpm.data.FormClassBean,
	com.opensymphony.xwork2.util.OgnlValueStack"
	%>
<%@ taglib uri="/struts-tags" prefix="ww"%>


<%
String actionurl = request.getParameter("action");
String contextpath = request.getContextPath() + "/";
%>

	<style>
		.showclock{
		position: absolute;
		border: 2px;	
		left:80%; 
		top:3%;
		line-height:20px;
		font-size:9px;
		z-index:1000;
		}
		.myDayCss
		{
		position: absolute;
		border: 2px;	
		left:40%; 
		top:95%;
		line-height:20px;
		z-index:1000;
		}
		</style>
		<link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/menus.css" />
		<link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />

		
			
	<SCRIPT LANGUAGE="JavaScript" type="text/javascript">
	window.onerror=function(a,b,c,d){
    return true;
     };

	var context='<%=contextpath%>';
	var personid='<ww:property value="$currPerson.ID"/>';
	var currUserName='<ww:property value="$currPerson.name"/>';
	var currUserOrgName='<ww:property value="person.orgs[0]"/>';
				var MSG_UNLOAD="您的文章内容还没有进行保存！";
var UnloadConfirm = {};
UnloadConfirm.set = function(confirm_msg){

window.onbeforeunload = function(event){
alert('formclose');
event = event || window.event;
event.returnValue = confirm_msg;
}
}

UnloadConfirm.clear = function(){
window.onbeforeunload = function(){};
}
UnloadConfirm.set(MSG_UNLOAD); //绑定事件
    </SCRIPT>
    
    
     		
		<script type="text/javascript" src="<%=contextpath%>js/extAll.js"></script>
	    <script  src="<%=contextpath%>js/JDS.js" type="text/javascript"></script>
	  	<script  src="<%=contextpath%>desktop/widgets/bpm/form/display/js/Form.js" type="text/javascript"></script>
	<object id="WebBrowser" classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0 width=0></object>
	
<html>

<script type="text/javascript">


function doPrint(){ 

document.all.prient.style.display='none';
document.all.WebBrowser.ExecWB(6,6)
}  
function viewPrint(){ 

document.all.prient.style.display='none';
document.all.WebBrowser.ExecWB(7,1)

}
</script>

<div id=prient align="right" >
<a id="printbutton" onclick="viewPrint();" style="cursor:hand">【打印预览】</a>
<a id="printbutton" onclick="doPrint();" style="cursor:hand">【直接打印】</a>
</div>
	<div id=prientdoc overflow=auto scroll=auto >
	<ww:action  name="<%=actionurl%>" executeResult="true"/>  
	</div>
	<script>
	
	
 Ext.BLANK_IMAGE_URL=context+'js/ext/resources/images/default/s.gif';
  Ext.form.Field.prototype.msgTarget = 'side';
  Ext.QuickTips.init();
  //加载数据配置页面后的回调函数
function callFormFn(){
	var module={
		activityInstId:'<ww:property value="activityInstId"/>',
		activityInstHistoryId:'<ww:property value="activityInstHistoryId"/>',
		baseField:new Ext.util.MixedCollection()
	}
	
	module.baseField.add('activityInstId','<ww:property value="activityInstId"/>');
	module.baseField.add('activityInstHistoryId','<ww:property value="activityInstHistoryId"/>');
	var form = new JDS.bpm.Form(Ext.getDoc().child("form"),module,true);
	
}


callFormFn();	
	
	</script>
	</html>