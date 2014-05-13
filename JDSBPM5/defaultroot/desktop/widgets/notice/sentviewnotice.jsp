
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%@page import="net.itjds.j2ee.util.UUID"%>
<%
	String contextpath = request.getContextPath() + "/";
%>
<HTML>
	<HEAD>

		<TITLE>查看通知</TITLE>
		<style type="text/css">
<!--
body {
	font-family: "宋体", "Arial";
	font-size: 9pt;
	background-color: #dfe8f6;
}

.STYLE3 {
	font-size: 24px
}

.STYLE4 {
	font-size: 18px
}

td {
	font-family: "宋体", "Arial";
	font-size: 9pt
}
-->
</style>
		<script type="text/javascript">
	var context="/";
</script>
		<script type="text/javascript" src="/js/extAll.js"></script>
		<script type="text/javascript"
			src="<%=contextpath%>desktop/widgets/notice/js/bufanotice.js"
			defer="defer"></script>
		<script type="text/javascript"
			src="<%=contextpath%>desktop/widgets/notice/reply/reply.js"
			defer="defer"></script>
		<script type="text/javascript"
			src="<%=contextpath%>desktop/js/CreateGrid.js"></script>
		<script src="/js/JDS.js" type="text/javascript"></script>
		<script type="text/javascript">
		var processid = '<ww:property value="mettingbean.processdefid"/>';
		var bsta = '<ww:property value="mettingbean.blstatus"/>';
		var id = '<ww:property value="mettingbean.tid"/>';  
		var noticeid='<ww:property value="noticebean.sendid"/>';

     function save(){
     
       var extsendrange=document.addNotice.extrasendrange.value;
          if(extsendrange==""){
	           		alert("发送范围不能为空");
	              	return false;
	        }
			 Ext.Ajax.request({
			 	url:'noticeAction_save.action?noticeid='+noticeid,
			 	method:'post',
			 	params:{},
			 	form:'addNotice',
			 	success:function(){
			 		alert("发送成功");
			 		window.location.href="/desktop/widgets/notice/yfnoticelist.jsp";
			 	},failure:function(){
			 	   alert("发送成功");
			 	   window.location.href="/desktop/widgets/notice/yfnoticelist.jsp";
			 	}
			 })
	}
	
	function find(){
	    Ext.Ajax.request({
			 	url:'reply_find.action',
			 	method:'post',
			 	params:{},
			 	success:function(){
			 		alert("发送成功");
			 	},failure:function(){
			 	   alert("发送成功");
			 	}
			 	
			 })
	
	}

	function winClose(){
		window.location.href="/desktop/widgets/notice/yfnoticelist.jsp";
	}

     function update(){
			 Ext.Ajax.request({
			 	url:'noticeAction_update.action',
			 	method:'post',
			 	params:{},
			 	form:'addNotice',
			 	success:function(){
			 		alert("已阅毕，请在已阅列表里查看！");
			 	}
			 })
	}
	
	function showReply(){
	    window.location.href="/desktop/widgets/notice/hznotice.jsp?noticeid="+'<ww:property value="noticebean.sendid"/>';
	
	}
</script>


	</HEAD>
	<BODY>
		<center>
			<form name="addNotice" id="addNotice">
				<table width="750" height="720" align="center" cellpadding="0"
					cellspacing="0" bgcolor="#dfe8f6"
					style="border: 1px solid #99bbe8;">
					<input type="hidden" name="uuid" id="uuid"
						value='<ww:property value="noticebean.uuid"/>' />
					<input type="hidden" name="liststatus" id="liststatus"
						value='<%=request.getParameter("liststatus")%>' />
					<input type="hidden" name="tid" id="tid"
						value='<ww:property value="tid"/>' />
					<input type="hidden" name="optionnum" id="optionnum" value='1' />
					<input type="hidden" name="sms" id="sms" />
					<input type="hidden" name="flag1" id="flag1" value='0' />
					<input type="hidden" name="parentid" id="parentid"
						value='<%=request.getParameter("parentid")%>' />

					<input type="hidden" name="mettinguuid" id="mettinguuid"
						value='<%=request.getParameter("mettinguuid")%>' />
					<input type="hidden" name="adddirection" id="adddirection"
						value='2' />
					<tr>
						<td width="84" height="45">
							<div align="center">
								<span class="STYLE4">起草人</span>
							</div>
						</td>
						<td>
							<input name="createname" readOnly="true" type="text"
								id="createname"
								value='<ww:property value="noticebean.createname"/>'>
							</input>
						</td>
						<td width="100" align="center">
							<span class="STYLE4">起草单位</span>
						</td>
						<td>
							<input width="84" align="center" readOnly="true"
								name="createdept" type="text" id="createdept"
								value='<ww:property value="noticebean.createdept"/>' />
						</td>
					</tr>
					<tr>
						<td width="84" align="center" height="30">
							<span class="STYLE4">联系人</span>
						</td>
						<td>
							<input width="84" readOnly="true" name="contactperson"
								type="text" id="contactperson"
								value='<ww:property value="noticebean.contactperson"/>' />
						</td>
						<td width="84" align="center">
							<span class="STYLE4">联系电话</span>
						</td>
						<td>
							<input name="contacttle" readOnly="true" type="text"
								id="contacttle"
								value='<ww:property value="noticebean.contacttle"/>' />
						</td>
					</tr>
					<tr>
						<td width="84" align="center" height="30">
							<span class="STYLE4">签发人</span>
						</td>
						<td>
							<input name="issuer" readOnly="true" width="84" type="text"
								id="issuer" value='<ww:property value="noticebean.issuer"/>' />
						</td>
						<td width="84" align="center">
							<span class="STYLE4">签发时间</span>
						</td>
						<td align="left">
							<input name="issuerdate" readOnly="true" type="text"
								id="createdate2"
								value='<ww:property value="noticebean.issuerdate"/>' />
						</td>
					</tr>
					<tr>
						<td width="84" align="center" height="80">
							<span class="STYLE4">标 题</span>
						</td>
						<td colspan="3">
							<textarea type="text" readOnly="true" name="niticetitle"
								id="niticetitle" cols="68" rows="4">
								<ww:property value="noticebean.niticetitle" />
							</textarea>
						</td>
					</tr>

					<tr>
						<td width="84" align="center" height="80">
							<span class="STYLE4">通知内容</span>
						</td>
						<td colspan="3">
							<textarea name="noticecontent" readOnly="true" cols="68"
								rows="10" id="noticecontent">
								<ww:property value="noticebean.noticecontent" />
							</textarea>
						</td>
					</tr>

					<tr>
						<td align="center" width="84" height="60">
							<span class="STYLE4">发送范围</span>
						</td>
						<td colspan="3">
							<textarea size="79" name="sendrange" readOnly="true" height="30"
								type="text" id="sendrange" size="50" cols="68" rows="3">
								<ww:property value="noticebean.sendrange" />
							</textarea>
						</td>
					</tr>
					<tr>
						<td align="center" width="84">
							<span class="STYLE4">补发</span>
						</td>
						<td colspan="3">
							<textarea size="70" name="extrasendrange" type="text"
								id="extrasendrange" onkeydown="return false;" cols="60" rows="3"></textarea>
							<input type="hidden" name="sendpersonid" id="personid" />
							<input type="hidden" name="yfid" id="yfid"
								value='<ww:property value="yfid"/>' />
							<input name="choice" size="70" type="button" id="choice"
								value="选择" onclick="createPersonPositionWindow('5015','')" />
							&nbsp;&nbsp;
							<input type="button" name="savematter" onclick="save()"
								value="发送" />
							&nbsp;&nbsp;
						</td>
					</tr>
					<tr>
						<td height="150" align="center" class="tdClass">
							<div align="center">
								<p class="STYLE4">
									附
								</p>
								<p class="STYLE4">
									件
								</p>
							</div>
						</td>
						<td colspan="4" style="width: 50px">
							<ww:action name="sdocexchangefileinclude" executeResult="true"></ww:action>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<input type="button" name="savematter" onclick="showReply()"
								value="查看反馈列表" />
							&nbsp;&nbsp;
							<input type="button" name="tc" value="返回" onclick="winClose()" />
						</td>

					</tr>

				</table>
			</form>
		</center>
	</BODY>

</HTML>