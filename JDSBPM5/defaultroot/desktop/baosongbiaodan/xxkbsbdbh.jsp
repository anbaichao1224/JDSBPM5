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
		<script type="text/javascript"
			src="<%=contextpath%>desktop/js/CreateGrid.js"></script>
		<script src="/js/JDS.js" type="text/javascript"></script>
		
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
  </script>
		<script type="text/javascript">
  function xiugai(biaohao,yingyongt,uuid,name){
	  
   Ext.Ajax.request({
		url: "updatetjAction.do",
		params: {biaohao:biaohao,
		         uuid:uuid,
		         yingyongt:yingyongt},
		method: "post",
		success: function(resp,opts){
			
			var respText = Ext.util.JSON.decode(resp.responseText);
			
			if(yingyongt == 0){
				//document.getElementById(biaohao).innerHTML = "<input type='button' value='测试插入'";							
				//document.getElementById(biaohao).innerHTML = "<input type ='button' value='"+name+" 取消评分' onclick = xiugai('"+biaohao+"','1','"+uuid+"','"+name+"');>";
				 document.getElementById(biaohao).innerHTML ="<EM unselectable='on'>"
				    + "<BUTTON class='x-btn-text sendMenu1' qtip='<b>操作提示</b><br/>取消&nbsp;"+name+"' onclick=xiugai('"+biaohao+"','1','"+uuid+"','"+name+"');>"
						+name+"&nbsp;&nbsp;取消评分"
				    +"</BUTTON>"
				+"</EM>"
			}else{
				 document.getElementById(biaohao).innerHTML ="<EM unselectable='on'>"
				    + "<BUTTON class='x-btn-text sendMenu1' qtip='<b>操作提示</b><br/>"+name+"' onclick=xiugai('"+biaohao+"','0','"+uuid+"','"+name+"');>"
						+name+"&nbsp;&nbsp;评分"
				    +"</BUTTON>"
				+"</EM>"
			}
			
			alert("操作成功");
		}
	});
  }
  function xxktuihui(){
  		Ext.Ajax.request({
  			url:"xxktuihuiAction.do",
  			params:{uuid:Ext.getDom("uuid").value},
  			method:"post",
  			success:function(resp,opts){
  				alert("删除成功");
  			}
  		});
  }
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
		<LINK rel=STYLESHEET type=text/css
			href="/desktop/resources/themes/xtheme-vista/css/xtheme-vista.css">
		<LINK rel=STYLESHEET type=text/css
			href="/desktop/resources/css/desktop.css">
	</head>

	<body>
		<!--   <input type="button" onclick="xxktuihui()"/> -->
		<DIV  class="x-panel-tbar x-panel-tbar-noheader">
			<DIV  class="x-toolbar x-small-editor">
				<TABLE cellSpacing=0>
					<TBODY>
						<TR>
							<ww:iterator value="gzListBean" status="rowstatus">
								<TD>
									<TABLE style="WIDTH: auto" class="x-btn-wrap x-btn x-btn-text-icon" border=0 cellSpacing=0 cellPadding=0>
										<TBODY>
											<TR>
											  <TD class=x-btn-left>
												<I>&nbsp;</I>
											  </TD>
											   <TD class=x-btn-center><ww:property value="rowstatus.count"/>
												<ww:if test=" 0 == yingyongt ">
													<div id='<ww:property value="biaohao"/>'>
														 <EM unselectable="on">
															<BUTTON class="x-btn-text sendMenu1"
																qtip="<b>操作提示</b><br/><ww:property value="name" />"
																onclick='xiugai("<ww:property value="biaohao"/>","<ww:property value="yingyongt"/>","<ww:property value="uuid"/>","<ww:property value="name"/>");'>
																<ww:property value="name" />
																&nbsp;&nbsp;评分
															</BUTTON>
														</EM>
													</div>
												</ww:if>
												<ww:else>
													<div id='<ww:property value="biaohao"/>'>
													     <EM unselectable="on">
															<BUTTON class="x-btn-text sendMenu1"
																qtip="<b>操作提示</b><br/>取消&nbsp;<ww:property value="name" />"
																onclick='xiugai("<ww:property value="biaohao"/>","<ww:property value="yingyongt"/>","<ww:property value="uuid"/>","<ww:property value="name"/>");'>
																<ww:property value="name" />
																&nbsp;&nbsp;取消评分
															</BUTTON>
														</EM>
													</div>
												</ww:else>
												
								             </TD>
											<TD class=x-btn-right>
												<I>&nbsp;</I>
											</TD>
										</TR>
					               </TBODY>
				                 </TABLE>
				                 
				                 <ww:if test="#rowstatus.count % 7 == 0">
									</td></tr><tr>
								</ww:if>
				                <ww:if test ="#rowstatus.count >= gzListBean.size">
      							     <td></tr>
      						    </ww:if>
				            </ww:iterator>
						
				</TBODY>
				</TABLE>
			</DIV>
		</DIV>
		<br />
		<br />
		<br />
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
				</td>
				<td height="60" width="250"
					style="border-left-color: #000000; border-top-color: #000000">
					<input type="text" name="dw" readonly="readonly"
						value='<ww:property value="bdBean.dw"/>' />
				</td>
				<td height="60" width="250"
					style="border-left-color: #000000; border-top-color: #000000">
					<span class="STYLE1">报送人</span>
				</td>
				<td height="60" width="250"
					style="border-left-color: #000000; border-top-color: #000000; border-right-color: #000000">
					<input type="text" name="djr" readonly="readonly"
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
					<input type="text" name="bt" readonly="readonly"
						value='<ww:property value="bdBean.bt"/>'>
				</td>
			</tr>
			<tr>
				<td height="60"
					style="border-left-color: #000000; border-top-color: #000000">
					<span class="STYLE1">上报时间</span>
				</td>
				<td style="border-left-color: #000000; border-top-color: #000000">
					<input type="text" name="sbsj" id="sbsj" readonly="readonly"
						value='<ww:property value="bdBean.sbsj"/>' />
				</td>
				<td style="border-top-color: #000000">
					&nbsp;
				</td>
				<td style="border-top-color: #000000; border-right-color: #000000"" >
					&nbsp;
				</td>
				<input type="hidden" name="uuid" id="uuid" value="<ww:property value="bdBean.uuid"/>" />
				<input type="hidden" name="sxxxid" id="sxxxid" value="<ww:property value="bdBean.uuid"/>" />
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
	</body>
</html>
