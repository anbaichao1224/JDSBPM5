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

	<link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/menus.css" />
	<link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <SCRIPT LANGUAGE="JavaScript" type="text/javascript">
	var context='<%=contextpath%>';
	var personid='<ww:property value="$currPerson.ID"/>';
	var currUserName='<ww:property value="$currPerson.name"/>';

    </SCRIPT>
    
    
     		
		<script type="text/javascript" src="<%=contextpath%>js/extAll.js"></script>
	    <script  src="<%=contextpath%>js/JDS.js" type="text/javascript"></script>
	  	<script  src="<%=contextpath%>desktop/widgets/bpm/form/display/js/Form.js" type="text/javascript"></script>
	  	<script  src="<%=contextpath%>desktop/widgets/bpm/form/display/js/Form.js" type="text/javascript"></script>
	  	<script  src="<%=contextpath%>desktop/js/CreateGrid.js"></script>
	  	
	
	
<html>
<div id=prient align="right" >
<a id="printbutton" onclick="save();" style="cursor:hand">【保存】</a>

</div>
<script type="text/javascript">
var module={
		activityInstId:'<ww:property value="activityInstId"/>',
		activityInstHistoryId:'<ww:property value="activityInstHistoryId"/>',
		allForm:new Ext.util.MixedCollection(),
		baseField:new Ext.util.MixedCollection(),
		getValues:function(){
		var str="";
		 this.allForm.each(function(f){
			 str=f.extForm.getValues(true)+"&"+str;
		  })
		   this.baseField.eachKey(function(f){
			  str=str+"&"+encodeURIComponent(f)+'='+encodeURIComponent(this.baseField.get(f))
		},this)
		  
	
		return str;  
	   }
	}
	
	function routeto(){
	
	//到达的活动ID
	  module.baseField.add('toActivityDefIds',toActivityInstId);
	//读者
		module.baseField.add('readPersonIdList','');
	//当前活动ID	
		module.baseField.add('startActivityInstId',module.baseField.get('activityInstId'));
		
		module.baseField.add('bpmUserClientUtil.BPM_UpdateType','routeto');
	//办理列表	
		module.baseField.add('performPersonIdList','');
		
		var str=module.getValues();
		module.winel.mask('正在处理数据...');
			var evalJs = function(o){
				module.winel.unmask();
				Ext.namespace("EVAL");
				eval(o);
			}
			JDS.ajax.Connection.LoadJsonFromUrl(context+'demoupdate.action', evalJs,str);
}	
	
	
function save () {
		    module.baseField.add('bpmUserClientUtil.BPM_UpdateType','saveonly');
		     module.winel.mask('正在处理数据...');
			var str=module.getValues();
			var evalJs = function(o){
				module.winel.unmask();
				Ext.namespace("EVAL");
				eval(o);
			}
			JDS.ajax.Connection.LoadJsonFromUrl(context+'demoupdate.action', evalJs,str);
		
	}
</script>
	<ww:action  name="<%=actionurl%>" executeResult="true"/>  
<script>
	
	
 Ext.BLANK_IMAGE_URL=context+'js/ext/resources/images/default/s.gif';
  Ext.form.Field.prototype.msgTarget = 'side';
  Ext.QuickTips.init();
  //加载数据配置页面后的回调函数
function callFormFn(){
	module.baseField.add('activityInstId','<ww:property value="activityInstId"/>');
	module.baseField.add('activityInstHistoryId','<ww:property value="activityInstHistoryId"/>');
	var form = new JDS.bpm.Form(Ext.getDoc().child("form"),module,false);
	module.allForm.add('formId',form);
	module.winel=Ext.getBody();
}


callFormFn();	
	
	</script>
	</html>