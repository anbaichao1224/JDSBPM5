<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%@page import="net.itjds.j2ee.util.UUID"%>
<%@ page
		import= "java.util.Date"
		import ="java.text.SimpleDateFormat"
%>
<%
	String contextpath = request.getContextPath() + "/";
	String uuid = request.getParameter("uuid");
	String sendid = request.getParameter("id");
	String status = request.getParameter("status");
	
	String signDate="";
	Date date1=(Date)request.getAttribute("from");
	System.out.println(date1+"date111");
	if(date1!=null){
	  SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  signDate = sfd.format(date1);
	
	
	}
%>
<HTML>
	<HEAD>
<title>未收公文详细</title>
<style type="text/css">
<!--
body {
	font-family: "normal tahoma,arial,helvetica,sans-serif";
	font-size: 14px;
	background-color: #dfe8f6
}

td {
	font-family: "normal tahoma,arial,helvetica,sans-serif";
	font-size: 14px
}
-->
</style>
		<script type="text/javascript">
	var context="/";
</script>

		<script type="text/javascript" src="/js/extAll.js"></script>
		<script type="text/javascript"
			src="/desktop/widgets/gwjh/js/bufadeparttree.js" defer="defer"></script>
		<script type="text/javascript"
			src="<%=contextpath%>desktop/js/CreateGrid.js"></script>
		<script src="/js/JDS.js" type="text/javascript"></script>


	</HEAD>
	<BODY>
<script type="text/javascript">
	var processid = '<ww:property value="mettingbean.processdefid"/>';
	var bsta = '<ww:property value="mettingbean.blstatus"/>';
	var id = '<ww:property value="mettingbean.tid"/>';  


</script>
		<center>
			<div id="form2"></div>
			<form name="addSend" id="addSend">
				<table width="800" height="227" align="center" bgcolor="#dfe8f6"
					style="border: 1px solid #99bbe8; line-height: 30px;">
					<input type="hidden" name="parentid" id="parentid"
						value='<%=request.getParameter("parentid")%>' />
					<input type="hidden" name="tid" id="tid" value='<%=sendid%>' />
					<input type="hidden" name="optionnum" id="optionnum" value='1' />
					<input type="hidden" name="sms" id="sms" />
					<input type="hidden" name="flag1" id="flag1" value='0'/>
					<input type="hidden" name="mettinguuid" id="mettinguuid"
						value='<%=request.getParameter("mettinguuid")%>' />
					<input type="hidden" name="adddirection" id="adddirection"
						value='2' />
					<input type="hidden" name="liststatus" id="liststatus"
						value='<%=request.getParameter("liststatus")%>' />
					<tr>
						<td valign="top" align="left">
							附&nbsp;&nbsp;件:&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td>
							<ww:action name="sdocexchangefileinclude" executeResult="true"></ww:action>
						</td>
					</tr>
					<!--  <tr>
						<td colspan="4" align="center">
							<input type="button" name="savematter" onclick="addHz()"
								value="回复" />
							&nbsp;&nbsp;

							<input type="button" id="back" name="back" value="拒签"
								onclick="bback()" />
							&nbsp;&nbsp;

							<input type="button" name="tt" value="签收" onclick="goback()" />
						</td>

					</tr>-->
				</table>
			</form>
		</center>
	</BODY>
	<script type="text/javascript">
 var uuid = '<%=uuid%>';
var sendid='<%=sendid%>';
var status = '<%=status%>';
var signTime='<%=signDate%>';
function getInceptid(){
  return uuid;
}

function getSendid(){
  return sendid;
}
Ext.onReady(function(){
      
		var addForm = new Ext.form.FormPanel({
			labelAlign : 'left',
			labelWidth : 80,
			width:800,
			frame : true,
			url : '',
			items : [ {
			    layout:'column',
			    items:[{
			       columnWidth:.5,
			       layout:'form',
			       defaultType:'textfield',
			       items:[ 
			       {fieldLabel:'密   级',readOnly:true,width:180,name:'sendbean.sendMj',value:'<ww:property value="dao.sendMj"/>'},
			        {fieldLabel:'发文单位',readOnly:true,width:180,name:'sendbean.senddept',value:'<ww:property value="dao.senddept"/>'},
			       {fieldLabel:'发电编号',width:180,readOnly:true,name:'sendbean.sendType',value:'<ww:property value="dao.sendType"/>'},
			       {fieldLabel:'签发人',readOnly:true,width:180,name:'sendbean.sendSigner',value:'<ww:property value="dao.sendSigner"/>'},
			       {fieldLabel:'联 系 人',readOnly:true,width:180,name:'sendbean.sendLxr',value:'<ww:property value="dao.sendLxr"/>'}
			       
			      
			       
			
			       ]
			    },{
			       columnWidth:.5,
			       layout:'form',
			       defaultType:'textfield',
			       items:[  
			         {fieldLabel:'等   级',readOnly:true,width:180,name:'sendbean.sendJjcd',value:'<ww:property value="dao.sendJjcd"/>'},
			          {fieldLabel:'文   号',readOnly:true,width:180,name:'sendbean.sendWh',value:'<ww:property value="dao.sendWh"/>'},
			          {fieldLabel:'页   数',readOnly:true,width:180,name:'sendbean.sendPages',value:'<ww:property value="dao.sendPages"/>'},
			          {fieldLabel:'签发时间',readOnly:true,width:180,name:'sendbean.sendSignTime',value:signTime},
			           {fieldLabel:'联系电话',readOnly:true,width:180,name:'sendbean.sendLxdh',value:'<ww:property value="dao.sendLxdh"/>'}
			         // {fieldLabel:'发文人',readOnly:true,name:'sendbean.sendor',width:180,value:'<ww:property value="dao.sendor"/>'},
			
			         
			        // {fieldLabel:'总份数',readOnly:true,width:180,name:'sendbean.sendCopy',value:'<ww:property value="dao.sendCopy"/>'},
			        
			       
			      ]}]
			},{
			  width:570,
			  height:40,
			  xtype:'textarea',
			  fieldLabel:'标题',
			  readOnly:true,
			  name:'sendbean.sendTitle',
			  value:'<ww:property value="dao.sendTitle"/>'
			}
			//,{ width:570, height:50,xtype:'textarea',fieldLabel:'副标题',readOnly:true,name:'sendbean.sendTitle1',value:'<ww:property value="dao.sendTitle1"/>'}
			//,{width:570, height:40,xtype:'textarea',fieldLabel:'主题词',readOnly:true,name:'sendbean.sendKeyWord',value:'<ww:property value="dao.sendKeyWord"/>'}
			,{
			  width:570,
			  height:40,
			  xtype:'textarea',
			  fieldLabel:'内  容',
			  readOnly:true,
			  name:'sendbean.sendScope',
			  value:'<ww:property value="dao.sendScope"/>'
			}]
	
		});
		
		addForm.render('form2');
		  var backflag=<ww:property value="backflag"/>;
		  if(backflag==1){
		    document.all.back.disabled='true';
		  }
	
});

     function save(){
			 Ext.Ajax.request({
			 	url:'gwjhAction_extrasave.action',
			 	method:'post',
			 	params:{
			 	'uuid':sendid
			 	},
			 	form:'addSend',
			 	success:function(){
			 		alert("发送成功");
			 	},failure:function(){
			 	   alert("发送失败");
			 	}
			 })
	}
	
	function goback(){
	  var status = <%=status%>;
	  
	  if(status==0){
		   window.location.href="/desktop/widgets/gwjh/unreceivelist.jsp";
	   }else{
	   		window.location.href="/desktop/widgets/gwjh/receivelist.jsp";
	   }
	
	}
	
	function bback(){
	  var inceptid='<%=uuid%>';
      var _width = 900;
		var _height = Ext.getBody().getHeight()-40;
		var addwin = new Ext.Window({
					title : '拒签理由',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					y:0,
					html:"<iframe id='djiframe' name='djiframe' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='gwjhAction_addbackpage.action?uuid="+ getInceptid()+"&id="+getSendid()+"'></iframe>",
					tbar:[
						{
							id : 'djsave',
							icon : "/usm/img/save.gif",
							text : '确定',
							cls : "x-btn-text-icon",
							handler : function(){
						  		  var fn = Ext.get('djiframe').dom.contentWindow.document.getElementById("addBack");
									fn.submit();
									alert("拒签成功");
									addwin.close();
						      
						}
						},{  
							id : 'djreset',
							icon : "/usm/img/depart.gif",
							text : '重  置',
							cls : "x-btn-text-icon",
							handler : function(){
								var fn = Ext.get('djiframe').dom.contentWindow.document.getElementById("addBack");
								fn.reset();
							}
						}
					]
			});
			addwin.show();
	 
	   
	}
	
	function winClose(){
		window.close();
	}
	
	function addHz(){
		var _width = 900;
		var _height = Ext.getBody().getHeight()-40;
		var addwin = new Ext.Window({
					title : '添加回复信息',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					autoScroll: true,
					minHeight : 200,
					y:0,
					html:"<iframe id='djiframe' name='djiframe' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='gwjhAction_addpage.action?uuid="+ getInceptid()+"&id="+getSendid()+"'></iframe>",
					tbar:[
						{
							id : 'djsave',
							icon : "/usm/img/save.gif",
							text : '回复',
							cls : "x-btn-text-icon",
							handler : function(){
								var fn = Ext.get('djiframe').dom.contentWindow.document.getElementById("addHz");
								fn.submit();
								alert("回复成功");
								addwin.close();
							}
						},{
							id : 'djreset',
							icon : "/usm/img/depart.gif",
							text : '重  置',
							cls : "x-btn-text-icon",
							handler : function(){
								var fn = Ext.get('djiframe').dom.contentWindow.document.getElementById("addHz");
								fn.reset();
							}
						}
					]
			});
			addwin.show();
  }
	
 </script>
</HTML>