<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%@page import="net.itjds.j2ee.util.UUID" %>
<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String uuid = (new UUID()).toString();
	String inceptid = request.getParameter("uuid");
	String sendid = request.getParameter("id");
%>
<html>
	<head>
		<base href="<%=basePath%>"></base>
		<script>var context='<%=path%>';</script>
<style type="text/css">
<!--
.STYLE3 {font-size: 36px}
.STYLE4 {font-size: 20px}
-->
</style>

		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />

		<script type="text/javascript" src="<%=path%>js/extAll.js"></script>
		<script type="text/javascript" src="<%=path%>/js/ext/TabCloseMenu.js"></script>
		<script type="text/javascript"
			src="<%=path%>desktop/js/CreateGrid.js"></script>
		<script src="/js/JDS.js" type="text/javascript"></script>
		<script type="text/javascript">
			
		</script>
	</head>
	<body>
		<form id="addHz" action="gwjhAction_addHz.action" method="post">
		<!-- <input type="hidden" name="tid" id="tid" value='<ww:property value="sxxxid"></ww:property>' />-->
		<!--  <input type="hidden" name="xmlmodel" id="xmlmodel" value='<ww:property value="xmlmodel"></ww:property>' />-->
		<!--<input type="hidden" name="UUID" id="UUID" value='<ww:property value="sxxxid"></ww:property>' />-->
		
		<input type="hidden" name="parentid" id="parentid" value='<%=request.getParameter("parentid") %>'/>
		
		<input type="hidden" name="tid" id="tid" value='<%=uuid %>'/><input type="hidden" name="optionnum" id="optionnum" value='1'/>
		
		<input type="hidden" name="mettinguuid" id="mettinguuid" value='<%=request.getParameter("mettinguuid") %>'/>
		<input type="hidden" name="adddirection" id="adddirection" value='2'/>
		<input type="hidden" name="liststatus" id="liststatus" value='<%=request.getParameter("liststatus")%>'/>
		<input type="hidden" name="uuid" id="uuid" value='<%=inceptid%>' />
		<input type="hidden" name="id" id="id" value='<%=sendid%>' />
			<table align="center" width="722" height="491" border="1"
				cellpadding="0" cellspacing="0">
				<tr>
					<td height="104" colspan="6">
						<div align="center" class="STYLE3">
							 回执信息
						</div>
					</td>
				</tr>
				<tr>
					<td width="58" height="46">
						<div align="center">
							<span class="STYLE4">回复人</span>
						</div>
					</td>
					<td width="249" align="center">
						<input name="replybean.replyPerson" type="text"  value='<ww:property value="$currPerson.name"/>'></input>
					</td>
					<td width="68">
						<div align="center">
							<span class="STYLE4">回复单位</span>
						</div>
					</td>
					<td width="99" align="center">
						<input name="replybean.replyDept" type="text"  value='<ww:property value="$currPerson.orgList[0].name"/>'></input>
					</td>
					
				</tr>
				<tr>
					<td height="57">
						<div align="center">
							<span class="STYLE4">回复内容</span>
						</div>
					</td>
					<td colspan="5" align="center">
						<textarea name="replybean.replyContent" cols="75" rows="5"></textarea>
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
					<td colspan="5" style="width:50px" class="tdClass">
						
						<ww:action name="docexchangefileinclude" executeResult="true"></ww:action>
						
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>