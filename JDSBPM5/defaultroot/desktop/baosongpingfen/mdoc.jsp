<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%@ page import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack,com.opensymphony.xwork2.ActionContext,net.itjds.fdt.define.designer.metadata.bean.DocumentBean"%>
<%
	String path = request.getContextPath();
	DocumentBean docbean = (DocumentBean) ActionContext.getContext().getValueStack().findValue("$docInject.getDocBean()");
	ActionContext.getContext().getValueStack().setValue("$currActivityInst","asdfasdfasdfasdfasdfasdf");
	String updatedoc = docbean.getDocbasic().getOpen(); 
	
%>
	<script type="text/javascript">
	function openFormWin(url,winid,title,params,filenum,updatedoc){
			//var fn = Ext.get('iframeup').dom.contentWindow.document.getElementById("fn").value;
			var fn = -1;

		if(updatedoc=="false"){
			if(!(fn > 0)){
				   	var _width = 300;
					var _height = 400;
	
					var documentmodule = {
				        title: "选择模版",
				        width: _width,
				        height: _height,
				        shim: true,
				        animCollapse: false,
				        border: false,
				        constrainHeader: true,
				        layout: 'fit',
				        html:"<iframe id='dociframe' name='dociframe' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='documentmodel.action?currUserOrgName=" + currUserOrgName + "'></iframe>",
				        buttons: [{ text: "确定", handler: function() { 
				       	Ext.get('iframeup').dom.contentWindow.document.getElementById("fn").value = 1;
				       	var content=Ext.get('dociframe').dom.contentWindow.document.getElementsByName("modelname");
				       	for(var i = 0; i < content.length; i++){
					       	if(content[i].checked==true){
					       		var cv = content[i].value;
						       	params = params + "&docuuid=" + cv;
					       	}
				       	}
				       	params = params + "&filenum=" + filenum;
						if(cv == null){
							alert("请选择相应的模板!");
						}else{
							Ext.Ajax.request({
							url: url,
							method:"post",
							params:params,// parameters 改为params
							success: function(o) {// 将 onComplete改为success
								o=o.responseText;
								// JDS.alert(o);
								Ext.namespace("EVAL");
								eval(o);
						   
							var defaultCfg = {
							        title: "window",
							        width: 740,
							        height: 480,
							        shim: true,
							        animCollapse: false,
							        border: false,
							        constrainHeader: true,
							        layout: 'fit'
							    };
				    			var returnCfg = EVAL.getPanel();
								returnCfg = Ext.apply(defaultCfg,returnCfg);
								var dispwin1 = new Ext.Window(returnCfg);
								 dispwin1.show();
								 dispwin.onEsc();
							  	},
							scope: this
						});
						}
				       	
	       		}},       
				    				  			    	
	       			{ text: "取消", handler: function() {
						dispwin.onEsc();	       			
	       			}}]
	    };
	

		  var dispwin = new Ext.Window(documentmodule);
		  
		      dispwin.show();
				    
			}else{
				Ext.Ajax.request({
							url: url,
							method:"post",
							params:params,// parameters 改为params
							success: function(o) {// 将 onComplete改为success
								o=o.responseText;
								// JDS.alert(o);
								Ext.namespace("EVAL");
								eval(o);
								
							var defaultCfg = {
							        title: "window",
							        width: 740,
							        height: 480,
							        shim: true,
							        animCollapse: false,
							        border: false,
							        constrainHeader: true,
							        layout: 'fit'
							    };
				    			var returnCfg = EVAL.getPanel();
								returnCfg = Ext.apply(defaultCfg,returnCfg);
								var dispwin1 = new Ext.Window(returnCfg);
								 dispwin1.show();
							  	},
							scope: this
						});
			
				}
			 }else{
				 Ext.Ajax.request({
						url: url,
						method:"post",
						params:params,// parameters 改为params
						success: function(o) {// 将 onComplete改为success
							o=o.responseText;
							// JDS.alert(o);
							Ext.namespace("EVAL");
							eval(o);
							
						var defaultCfg = {
						        title: "window",
						        width: 740,
						        height: 480,
						        shim: true,
						        animCollapse: false,
						        border: false,
						        constrainHeader: true,
						        layout: 'fit'
						    };
			    			var returnCfg = EVAL.getPanel();
							returnCfg = Ext.apply(defaultCfg,returnCfg);
							var dispwin1 = new Ext.Window(returnCfg);
							 dispwin1.show();
						  	},
						scope: this
					}); 
				 
			 }
	
	

 }
 </script>
<ww:if test="disabled == null || disabled=='false'">
	<ww:if test="disname == null ||  disname">

	<ww:if test="cnname != null && cnname != 'null'">
			<ww:property value="cnname" />
		</ww:if>
	</ww:if>
	<iframe id="iframeup" width="520" scrolling="no" frameborder=0 height="44" src="desktop/widgets/bpm/form/document/updocument.jsp?processInstId='<ww:property value="processInst.processInstId"/>'&activityInstId='<ww:property value="activityInstId"/>'"></iframe>
	<a id="ahrefs" href=javascript:openFormWin('<%=path%>/selfdocumentAction.action','','','activityInstHistoryId=<ww:property value="activityInstHistoryId"/>&activityInstId=<ww:property value="activityInstId"/>&docid=<ww:property value="docid"/>&formID=<ww:property value="formID"/>','<ww:property value="filenum"/>','<%=updatedoc%>');>
	<script type="text/javascript">
		var sxxxid = document.getElementById("sxxxid").value;
		alert(sxxxid);
		var strUrl = "desktop/widgets/bpm/form/document/updocument.jsp?processInstId='"+sxxxid+"'&activityInstId='"+sxxxid+"'";
		//iframeup.Attributes.Add("src",strNewUrl); 
		document.getElementById("iframeup").src = strUrl;
		var ss=document.getElementById("ahrefs");
		ss.href="javascript:openFormWin('<%=path%>/mbsdocAction.action','','','activityInstHistoryId="+sxxxid+"&activityInstId="+sxxxid+"&docid=<ww:property value='docid'/>&formID="+sxxxid+"','<ww:property value='filenum'/>','<%=updatedoc%>');";
	</script>
			
		<ww:if test="disicon == null || disicon">
			<ww:if test="filenum > 0">
				<ww:if test="iconD != null && iconD != 'null'">
					<img id='docimg' src='<%=path%><ww:property value="iconD"/>'
				</ww:if>
				<ww:else>
					<img id='docimg'
						src='<%=path%>/desktop/widgets/bpm/form/document/img/newdoc1.jpg'
						title='在线编辑'
				</ww:else>
			</ww:if>
			<ww:else>
				<ww:if test="iconN != null && iconN != 'null'">
					<img id='docimg' src='<%=path%><ww:property value="iconN"/>'
				</ww:if>
				<ww:else>
					<img id='docimg1' alt='<www:property value="formID"/>'
						src='<%=path%>/desktop/widgets/bpm/form/document/img/word.bmp'
						title='创建正文'
				</ww:else>
			</ww:else>
			<ww:if test="iconW != null && iconW != ''"> 
		width="<ww:property value="iconW" />" 
		</ww:if>

			<ww:if test="iconH != null && iconH != ''"> 
	height="<ww:property value="iconH" />"
	</ww:if>
	
	></img>
		</ww:if>
</ww:if>
</a>