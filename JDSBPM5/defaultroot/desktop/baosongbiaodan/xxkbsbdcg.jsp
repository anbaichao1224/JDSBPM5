<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
	String path = request.getContextPath();
	String contextpath = request.getContextPath() + "/";
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'xxkbsbd.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<SCRIPT LANGUAGE="JavaScript" type="text/javascript">
	var context='<%=contextpath%>';
	</script>
		<script type="text/javascript" src="<%=path%>js/extAll.js"></script>
		<script type="text/javascript" src="<%=path%>js/extAll.js"></script>
		<script type="text/javascript"
			src="<%=contextpath%>desktop/js/CreateGrid.js"></script>
		<script type="text/javascript">
  Ext.onReady(function(){
  var kssj = new Ext.form.DateField({
		applyTo:'sbsj',
		fieldLabel:'日期',
		emptyText:'请选择',
		format:'Y-m-d',
		value:'<ww:property value="bdBean.sbsj"/>'	
	});



  });
  Ext.QuickTips.init();
  
  function getFormQueryString(afrmID)    //frmID是表单的ID号，请在表单form中先命名一个ID号
{ 
	Ext.Ajax.request({
		
		url: "savebsbdAction.do",
//		params:queryString,
		form:'form1',
		method: "post",
		success: function(resp,opts){
		alert("success");
			var respText = Ext.util.JSON.decode(resp.responseText);
										
			
		}
	});

//			alert("go this");
		  
}
  </script>
		</script>
		<style type="text/css">
<!--
.STYLE1 {
	font-size: 18px;
	font-weight: bold;
}

.STYLE2 {
	font-size: 36px;
	font-weight: bold;
}
-->
</style>
	</head>

	<body>
		<form action="" name="form1" id="form1">
			<table width="982" border="1" cellpadding="0" cellspacing="0"
				bordercolor="#FFFFFF">
				<tr>
					<td height="114" colspan="4"
						style="border-left-color: #000000; border-top-color: #000000; border-right-color: #000000"
						align="center">
						<span class="STYLE2">信息报送表</span>
					</td>
				</tr>
				<tr>
					<td height="60" width="250"
						style="border-left-color: #000000; border-top-color: #000000">
						<span class="STYLE1">上报单位</span>
					<td height="60" width="250"
						style="border-left-color: #000000; border-top-color: #000000">
						<input type="text" name="dw"
							value='<ww:property value="bdBean.dw"/>' />
					</td>
					<td height="60" width="250"
						style="border-left-color: #000000; border-top-color: #000000">
						<span class="STYLE1">报送人</span>
					</td>
					<td height="60" width="250"
						style="border-left-color: #000000; border-top-color: #000000; border-right-color: #000000">
						<input type="text" name="djr"
							value='<ww:property value="bdBean.djr"/>' />
					</td>
				</tr>
				<tr>
					<td height="60"
						style="border-left-color: #000000; border-top-color: #000000">
						<span class="STYLE1">标题：</span>
					</td>
					<td colspan="3"
						style="border-left-color: #000000; border-top-color: #000000; border-right-color: #000000">
						<input type="text" name="bt"
							value='<ww:property value="bdBean.bt"/>'>
					</td>
				</tr>
				<tr>
					<td height="60"
						style="border-left-color: #000000; border-top-color: #000000">
						<span class="STYLE1">上报时间</span>
					</td>
					<td style="border-left-color: #000000; border-top-color: #000000">
						<input type="text" name="sbsj" id="sbsj"
							value='<ww:property value="bdBean.sbsj"/>' />
					</td>
					<td style="border-top-color: #000000">
						<input type="hidden" name="sxxxid" id="sxxxid"
							value='<ww:property value="bdBean.uuid"/>' />
					</td>
					<td style="border-top-color: #000000; border-right-color: #000000"" >
						<input type="hidden" name="uuid"
							value="<ww:property value="bdBean.uuid"/>" />
						<input type="hidden" id="sendorsave" name="sendorsave"
							value="save">
					</td>
				</tr>
				<tr>
					<td
					style="border-left-color: #000000; border-top-color: #000000; border-bottom-color: #000000">
					<span class="STYLE1">附件：</span>
				</td>
					<td colspan="3"
					style="border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-bottom-color: #000000">
						<ww:action name="mfileinclude" executeResult="true"></ww:action>
					</td>
				</tr>
			</table>


		</form>
	</body>
</html>
