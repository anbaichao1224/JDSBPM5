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
	  SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
	  signDate = sfd.format(date1);
	  System.out.println(signDate+"signDate");
	
	}
%>
<HTML>
	<HEAD>

		<TITLE></TITLE>
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
			src="/desktop/widgets/nbgwjh/js/bufadeparttree.js" defer="defer"></script>
		<script type="text/javascript"
			src="<%=contextpath%>desktop/js/CreateGrid.js"></script>
		<script src="/js/JDS.js" type="text/javascript"></script>
		<script type="text/javascript">
			var processid = '<ww:property value="mettingbean.processdefid"/>';
			var bsta = '<ww:property value="mettingbean.blstatus"/>';
			var id = '<ww:property value="mettingbean.tid"/>';  
		</script>

	</HEAD>
	<BODY>

		<center>
			<div id="form2"></div>
			<form name="addSend" id="addSend">
				<table width="750" height="227" align="center" bgcolor="#dfe8f6"
					style="border: 1px solid #99bbe8; line-height: 30px;">
					<input type="hidden" name="parentid" id="parentid"
						value='<%=request.getParameter("parentid")%>' />
					<input type="hidden" name="tid" id="tid" value='<%=sendid%>' />
					<input type="hidden" name="optionnum" id="optionnum" value='1' />

					<input type="hidden" name="mettinguuid" id="mettinguuid"
						value='<%=request.getParameter("mettinguuid")%>' />
					<input type="hidden" name="adddirection" id="adddirection"
						value='2' />
					<input type="hidden" name="liststatus" id="liststatus"
						value='<%=request.getParameter("liststatus")%>' />

					<tr>
						<td align="left" width="184">
							接收单位:&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td colspan="3">
							<textarea size="70" name="sandrange" readOnly ="true"
								 type="text"
								id="sandrange" onkeydown="return false;" cols="71" rows="3"><ww:property value="sandrange"/></textarea>
							
						</td>
					</tr>

					<tr>
						<td valign="top" align="left">
							附&nbsp;&nbsp;件:&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td>
							<ww:action name="sdocexchangefileinclude" executeResult="true"></ww:action>
						</td>
					</tr>
                    	<tr>
					      <td align="center" colspan="2">
					        <input type="button" name="tc" value="返回" onclick="goback()" />
					   </td>
					</tr>
				</table>
			</form>
		</center>
	</BODY>
	<script type="text/javascript">
 var uuid = '<%=uuid%>';
var sendid='<%=sendid%>';
var signTime='<%=signDate%>';
function getInceptid(){
  return uuid;
}
Ext.onReady(function(){
   
		var addForm = new Ext.form.FormPanel({
			labelAlign : 'left',
			labelWidth : 80,
			width:750,
			frame : true,
			url : '',
			items : [ {
			    layout:'column',
			    items:[{
			       columnWidth:.5,
			       layout:'form',
			       defaultType:'textfield',
			       items:[ 
			       {fieldLabel:'发文人',readOnly:true,name:'sendbean.sendor',height:40,width:180,value:'<ww:property value="dao.sendor"/>'}
			
			       ]
			    },{
			       columnWidth:.5,
			       layout:'form',
			       defaultType:'textfield',
			       items:[  
			         {fieldLabel:'发文单位',readOnly:true,height:40,width:180,name:'sendbean.senddept',value:'<ww:property value="dao.senddept"/>'}
			       
			      ]}]
			},{
			  width:550,
			  height:100,
			  xtype:'textarea',
			  fieldLabel:'标题',
			  readOnly:true,
			  name:'sendbean.sendTitle',
			  value:'<ww:property value="dao.sendTitle"/>'
			},{
			  width:550,
			  height:150,
			  xtype:'textarea',
			  fieldLabel:'备注',
			  readOnly:true,
			 name:'sendbean.sendTitle1',
			  value:'<ww:property value="dao.sendTitle1"/>'
			}]
	
		});
		
		addForm.render('form2');
	
});
	function goback(){
	    var status='<%=status%>';
	    if(status==0){
	      window.location.href="/desktop/widgets/nbgwjh/cancellist.jsp";
	   }else{
	    window.location.href="/desktop/widgets/nbgwjh/backlist.jsp";
	   }
	   
	
	}

	
	
	
 </script>
</HTML>