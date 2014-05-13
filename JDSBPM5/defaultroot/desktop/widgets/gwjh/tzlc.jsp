	<%@ page contentType="text/html; charset=UTF-8"%>
	<%@ page
		import="java.util.Map,
		com.opensymphony.xwork2.ActionContext,
		java.util.List,
		java.text.SimpleDateFormat,
		net.itjds.bpm.data.FormClassBean,
		net.itjds.fdt.dao.fawen.FdtOaNmswblDAO,
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
	
			<%
			FdtOaNmswblDAO dao = (FdtOaNmswblDAO)request.getAttribute("dao");
			String date1="";
			if(dao.getSwrq()!=null) {
				System.out.println(dao.getSwrq()+"");
				String rq=dao.getSwrq().toString();
			     date1 =rq.substring(0,10);
			     SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
			     sfd.parse(date1);
				 System.out.println(date1);
			 }
			%>
			Ext.onReady(function(){
	
			document.getElementsByName("$Fdtnmswbl.fdtOaNmswblDAO.swbh").item(0).value='<%=dao.getSwbh()%>';
			document.getElementsByName("$Fdtnmswbl.fdtOaNmswblDAO.wjbt").item(0).value='<%=dao.getWjbt()%>';
			document.getElementsByName("$Fdtnmswbl.fdtOaNmswblDAO.swrq").item(0).value='<%=date1%>';
			document.getElementsByName("$Fdtnmswbl.fdtOaNmswblDAO.mj").item(0).value='<%=dao.getMj()%>';

			
		});
		
		
	</script>
		   