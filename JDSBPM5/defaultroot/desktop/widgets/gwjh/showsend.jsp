<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>

<%@ page import="java.util.Date" import="java.text.SimpleDateFormat"%>
<%
	String contextpath = request.getContextPath() + "/";
	String sendid = request.getParameter("uuid");
	String signDate = "";
	Date date1 = (Date) request.getAttribute("from");
	if (date1 != null) {
		SimpleDateFormat sfd = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		signDate = sfd.format(date1);

	}
%>
<HTML>
	<HEAD>
		<title>已发公文 点击标题查看详细 补发</title>
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
					<input type="hidden" name="flag1" id="flag1" value='0' />
					<input type="hidden" name="mettinguuid" id="mettinguuid"
						value='<%=request.getParameter("mettinguuid")%>' />
					<input type="hidden" name="adddirection" id="adddirection"
						value='2' />
					<input type="hidden" name="liststatus" id="liststatus"
						value='<%=request.getParameter("liststatus")%>' />

					<tr>
						<td align="left" width="184">
							已发单位:&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td colspan="3">
							<textarea size="70" name="deptnames" readOnly="true" type="text"
								id="deptnames" onkeydown="return false;" cols="65" rows="3">
								<ww:property value="sandrange" />
							</textarea>
							<input type="hidden" name="sentid" id="sentid"
								value='<ww:property value="sandrange"/>' />
						</td>
					</tr>
					<tr>
						<td align="left" width="184">
							补 发:&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td colspan="3">
							<textarea size="70" name="bufadeptnames" type="text"
								id="bufadeptnames" onkeydown="return false;" cols="65" rows="3"></textarea>
							<input type="hidden" name="id" id="id" />
							<input type="hidden" name="yfid" id="yfid"
								value='<ww:property value="yfid"/>' />
							<input name="choice" size="70" type="button" id="choice"
								value="选择" onclick="createPersonPositionWindow('5015','')" />
							&nbsp;&nbsp;
							<input type="button" onclick="save()" value="发送" />
					</tr>
					<tr>
						<td valign="top" align="left">
							附&nbsp;&nbsp;件:&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td>
							<ww:action name="sdocexchangefileinclude" executeResult="true"></ww:action>
						</td>
					</tr>


				</table>
			</form>
		</center>
	</BODY>
	<script type="text/javascript">
  var sendid = '<%=sendid%>';
  var signTime='<%=signDate%>';
Ext.onReady(function(){

		var addForm = new Ext.form.FormPanel({
			labelAlign : 'left',
			labelWidth : 80,
			width:800,
			x:5,
			y:5,
			frame : true,
			items : [ {
			    layout:'column',
			    items:[{
			       columnWidth:.5,
			       layout:'form',
			       defaultType:'textfield',
			       items:[ 
			       {fieldLabel:'密    级',readOnly:true,width:180,name:'sendbean.sendMj',value:'<ww:property value="dao.sendMj"/>'},
			       {fieldLabel:'发文单位',readOnly:true,width:180,name:'sendbean.senddept',value:'<ww:property value="dao.senddept"/>'},
			       {fieldLabel:'发电编号',width:180,readOnly:true,name:'sendbean.sendType',value:'<ww:property value="dao.sendType"/>'},
			       {fieldLabel:'签 发 人',readOnly:true,width:180,name:'sendbean.sendSigner',value:'<ww:property value="dao.sendSigner"/>'},
			       {fieldLabel:'联 系 人',readOnly:true,width:180,name:'sendbean.sendLxr',value:'<ww:property value="dao.sendLxr"/>'}
			     
			      
			       
			
			       ]
			    },{
			       columnWidth:.5,
			       layout:'form',
			       defaultType:'textfield',
			       items:[  
			         {fieldLabel:'等    级',readOnly:true,width:180,name:'sendbean.sendJjcd',value:'<ww:property value="dao.sendJjcd"/>'},
			          {fieldLabel:'文    号',readOnly:true,width:180,name:'sendbean.sendWh',value:'<ww:property value="dao.sendWh"/>'},
			           {fieldLabel:'页    数',readOnly:true,width:180,name:'sendbean.sendPages',value:'<ww:property value="dao.sendPages"/>'},
			          {fieldLabel:'签发时间',readOnly:true,width:180,name:'sendbean.sendSignTime',value:signTime},
			        {fieldLabel:'联系电话',readOnly:true,width:180,name:'sendbean.sendLxdh',value:'<ww:property value="dao.sendLxdh"/>'}
			         
			       
			      ]}]
			},{
			  width:570,
			  height:40,
			  xtype:'textarea',
			  fieldLabel:'标   题',
			  readOnly:true,
			  name:'sendbean.sendTitle',
			  value:'<ww:property value="dao.sendTitle"/>'
			}
			,{
			  width:570,
			  height:40,
			  xtype:'textarea',
			  fieldLabel:'内  容',
			  readOnly:true,
			  grow :true,
			  name:'sendbean.sendType',
			  value:'<ww:property value="dao.sendScope"/>'
			}]
	
		});
		
		addForm.render('form2');
           
});
		
     function save(){
     Ext.MessageBox.wait("准备发送", "正在准备数据请稍候...", {
											width : 300
										});
			 Ext.Ajax.request({
			 	url:'gwjhAction_extrasave.action',
			 	method:'post',
			 	params:{
			 	'uuid':sendid
			 	},
			 	form:'addSend',
			 	success:function(){
			 		Ext.Msg.alert('提示','发送成功');
			 	},failure:function(){
			 	   Ext.Msg.alert('提示','发送失败');
			 	}
			 })
	}
	
	function goback(){
	   window.location.href="/desktop/widgets/gwjh/hassentlist.jsp";
	   
	
	}
	
	function winClose(){
		window.close();
	}
  </script>
</HTML>