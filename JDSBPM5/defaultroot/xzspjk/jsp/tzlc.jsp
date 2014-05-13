	<%@ page contentType="text/html; charset=UTF-8"%>
	<%@ page
		import="java.util.Map,
		com.opensymphony.xwork2.ActionContext,
		java.util.List,
		net.itjds.bpm.data.FormClassBean,
		net.itjds.fdt.dao.fawen.FdtOaWsxtspDAO,
		com.opensymphony.xwork2.util.OgnlValueStack"
		
	%>
	<%@ taglib uri="/struts-tags" prefix="ww"%>

	<%
		OgnlValueStack stack = (OgnlValueStack) request.getAttribute("struts.valueStack");
		FormClassBean currForm=(FormClassBean)stack.findValue("currForm");
		String jspRunTimeUrl = currForm.getJspUrl();
		
	%>
	<input type="hidden" name="" value="y" id="ldyj<ww:property value="activityInstId"/>"/>
	<input type="hidden" name="" value="<ww:property value="yibanli"/>" id="yibanli<ww:property value="activityInstId"/>">
    <form path="<%=java.net.URLEncoder.encode(currForm.getPath())%>"  formname="<%=currForm.getName()%>" formId="<%=currForm.getId()%>" >											
		<jsp:include page='<%=jspRunTimeUrl%>' flush="" />	
	</form>		
	
	<script type="text/javascript">
		Ext.onReady(function(){
			<%FdtOaWsxtspDAO fdtOaWsxtspDAO = (FdtOaWsxtspDAO)request.getAttribute("fdtOaWsxtspDAO"); %>
			document.getElementsByName("$Fdtnmwsxtsp.fdtOaWsxtspDAO.appname").item(0).value='<%=fdtOaWsxtspDAO.getAppname()%>';
			document.getElementsByName("$Fdtnmwsxtsp.fdtOaWsxtspDAO.appdate").item(0).value='<%=fdtOaWsxtspDAO.getAppdate()%>';
			document.getElementsByName("$Fdtnmwsxtsp.fdtOaWsxtspDAO.email").item(0).value='<%=fdtOaWsxtspDAO.getEmail()%>';
			document.getElementsByName("$Fdtnmwsxtsp.fdtOaWsxtspDAO.apporg").item(0).value='<%=fdtOaWsxtspDAO.getApporg()%>';
			document.getElementsByName("$Fdtnmwsxtsp.fdtOaWsxtspDAO.cardid").item(0).value='<%=fdtOaWsxtspDAO.getCardid()%>';
			document.getElementsByName("$Fdtnmwsxtsp.fdtOaWsxtspDAO.mobilephone").item(0).value='<%=fdtOaWsxtspDAO.getMobilephone()%>';
			document.getElementsByName("$Fdtnmwsxtsp.fdtOaWsxtspDAO.phone").item(0).value='<%=fdtOaWsxtspDAO.getPhone()%>';
			document.getElementsByName("$Fdtnmwsxtsp.fdtOaWsxtspDAO.address").item(0).value='<%=fdtOaWsxtspDAO.getAddress()%>';
			document.getElementsByName("$Fdtnmwsxtsp.fdtOaWsxtspDAO.name").item(0).value='<%=fdtOaWsxtspDAO.getName()%>';
			document.getElementsByName("$Fdtnmwsxtsp.fdtOaWsxtspDAO.bsnum").item(0).value='<%=fdtOaWsxtspDAO.getBsnum()%>';
			document.getElementsByName("$Fdtnmwsxtsp.fdtOaWsxtspDAO.xmmc").item(0).value='<%=fdtOaWsxtspDAO.getXmmc()%>';
			document.getElementsByName("$Fdtnmwsxtsp.fdtOaWsxtspDAO.department").item(0).value='<%=fdtOaWsxtspDAO.getDepartment()%>';
			document.getElementsByName("$Fdtnmwsxtsp.fdtOaWsxtspDAO.status").item(0).value='<%=fdtOaWsxtspDAO.getStatus()%>';
			<%
				if(fdtOaWsxtspDAO.getStatus() != null&& fdtOaWsxtspDAO.getStatus().length() > 0){
					int s = Integer.parseInt(fdtOaWsxtspDAO.getStatus());
					String[] statuses = {"在办","办结","作废","补交挂起","特别程序","暂存","已归档"};
					%>
					document.getElementsByName("$Fdtnmwsxtsp.fdtOaWsxtspDAO.status").item(0).value='<%=statuses[s]%>';
					<%
				}
			%>
		});
		
	</script>
		   