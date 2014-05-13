<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,
	com.opensymphony.xwork2.ActionContext,
	java.util.List,net.itjds.bpm.data.FormClassBean,
	com.opensymphony.xwork2.util.OgnlValueStack"
	%>
<jsp:directive.page import="java.util.UUID"/>
<%@ taglib uri="/struts-tags" prefix="ww"%>




<%
String uuid=UUID.randomUUID().toString();
String actionurl = request.getParameter("action");
request.getParameterMap().put("fileinclude","fdtfileinclude");
request.setAttribute("fileinclude","fdtfileinclude");
ActionContext.getContext().put("fileinclude","fdtfileinclude");
String contextpath = request.getContextPath() + "/";
%>

	<link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/menus.css" />
	<link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <SCRIPT LANGUAGE="JavaScript" type="text/javascript">
	var context='<%=contextpath%>';

    </SCRIPT>

     		
		<script type="text/javascript" src="<%=contextpath%>js/extAll.js"></script>
	    <script  src="<%=contextpath%>js/JDS.js" type="text/javascript"></script>
	     <script  src="<%=contextpath%>desktop/js/CreateGrid.js" type="text/javascript"></script>
	  	<script  src="<%=contextpath%>desktop/widgets/bpm/form/display/js/Form.js" type="text/javascript"></script>

	  	<script  src="<%=contextpath%>desktop/js/CreateGrid.js"></script>
	  	
	
	
<html>

<script type="text/javascript">
var uuid='<ww:property value="$R('uuid')"/>';
if (uuid==''){
uuid='<%=uuid%>';
}
var rootPath = "http://${pageContext.request.localAddr}:${pageContext.request.localPort}";
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
	
	
	
	
	
function save () {

	        module.baseField.add('uuid',uuid);
	
		    module.winel.mask('正在处理数据...');
			var str=module.getValues();
			var evalJs = function(o){
				module.winel.unmask();
				Ext.namespace("EVAL");
					alert(o);
				//eval(o);
			
			}
			
			JDS.ajax.Connection.LoadJsonFromUrl(context+'updateUsmData.action', evalJs,str);
		
	}
</script>

<object ID="fileLoad"
	classid="clsid:54081CE9-8B69-4E41-BF1A-E8520EBB58CF"
	CODEBASE="/BSIDownUpLoad.ocx#Version=1,1,2,3" width="0" height="0">
</object>

								
<jsp:include page='<%=actionurl%>' flush="" />	
      <table border="1" cellspacing="0" bordercolorlight="#c4d4eb" bordercolordark="#ffffff" cellpadding="0" width="980" align="center">
          <tr class="form_text">
          <td class="form_text" height="36" colspan="4" align="center"><input id="button" class="btn" type="button" name="button" onclick="save();" value="保存" / pid=component_input_13> 
          &nbsp;<input id="clean" class="btn" type="button" onclick="javascript:window.close();" name="clean" value="关闭" / pid=component_input_15> 
         </td>
         </tr>
         </table>
</form>



<script>
	

 Ext.BLANK_IMAGE_URL=context+'js/ext/resources/images/default/s.gif';
  Ext.form.Field.prototype.msgTarget = 'side';
  Ext.QuickTips.init();
  //加载数据配置页面后的回调函数
function callFormFn(){

	module.baseField.add('activityInstId','<ww:property value="activityInstId"/>');
	module.baseField.add('activityInstHistoryId','<ww:property value="activityInstHistoryId"/>');
	module.baseField.add('uuid',uuid);
	
	
	var form = new JDS.bpm.Form(Ext.getDoc().child("form"),module,false);

	module.allForm.add('formId',form);
	module.winel=Ext.getBody();
}


callFormFn();	


try{
          
showAttachWin();

}catch(e){
JDS.alert(e);
}
	
	</script>
	</html>