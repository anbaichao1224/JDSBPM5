	<%@ page contentType="text/html; charset=UTF-8"%>
	<%@ page
		import="java.util.Map,
		com.opensymphony.xwork2.ActionContext,
		java.util.List,
		java.text.SimpleDateFormat,
		net.itjds.bpm.data.FormClassBean,
		net.kzxd.dj.bean.FdtOaDj,
		com.opensymphony.xwork2.util.OgnlValueStack,
	   java.util.regex.Matcher,
       java.util.regex.Pattern"

		
	%>
	<%@ taglib uri="/struts-tags" prefix="ww"%>

	<%
		OgnlValueStack stack = (OgnlValueStack) request.getAttribute("struts.valueStack");
		FormClassBean currForm=(FormClassBean)stack.findValue("currForm");
		String jspRunTimeUrl = currForm.getJspUrl();
		//String Docbt=Docbt.replaceAll("\r\n","<br/>");
	
	%>
	<input type="hidden" name="" value="y" id="ldyj<ww:property value="activityInstId"/>"/>
	<input type="hidden" name="" value="y" id="ldyjtemb"/>
	<input type="hidden" name="" value="<ww:property value="yibanli"/>" id="yibanli<ww:property value="activityInstId"/>">
    <form path="<%=java.net.URLEncoder.encode(currForm.getPath())%>"  formname="<%=currForm.getName()%>" formId="<%=currForm.getId()%>" >											
		<jsp:include page='<%=jspRunTimeUrl%>' flush="" />	
	</form>		
	
	<script type="text/javascript">
	
			
			<%
			FdtOaDj fod = (FdtOaDj)request.getAttribute("fod");
			String reg ="[\n-\r]";
	        Pattern p = Pattern.compile(reg);
		    String Docbt=fod.getDocbt();
		    Matcher m1 = p.matcher(Docbt);
		    Docbt = m1.replaceAll(""); 
		    Docbt = Docbt.replace("'", "’");
		    Docbt=Docbt.replace("\"", "“");
		    Docbt = Docbt.replace("\"", "”");
         
			String xmlname = "";
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			String rdate = sf.format(fod.getRdate());
			%>
		//	String Docbt=Docbt.replaceAll("\r\n","<br/>");
			Ext.onReady(function(){
			document.getElementsByName("$Fdtgwg.fdtOaGwgDAO.ywdw").item(0).value='<%=fod.getDepartment()%>';
			document.getElementsByName("$Fdtgwg.fdtOaGwgDAO.wjbt").item(0).value='<%=Docbt%>';
			document.getElementsByName("$Fdtgwg.fdtOaGwgDAO.swrq").item(0).value='<%=rdate%>';
			document.getElementsByName("$Fdtgwg.fdtOaGwgDAO.ywbh").item(0).value='<%=fod.getLwbh()%>';
			document.getElementsByName("$Fdtgwg.fdtOaGwgDAO.swbh").item(0).value='<%=fod.getSn()%>';
		    document.getElementsByName("$Fdtgwg.fdtOaGwgDAO.ffzh").item(0).value='<%=fod.getLdbh()%>';
		    document.getElementsByName("$Fdtgwg.fdtOaGwgDAO.jjcd").item(0).value='<%=fod.getEmergency()%>';

		});
		
		
	</script>
		   