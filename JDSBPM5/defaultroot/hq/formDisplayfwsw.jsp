	<%@ page contentType="text/html; charset=UTF-8"%>
	<%@ page
		import="java.util.Map,
		com.opensymphony.xwork2.ActionContext,
		java.util.List,
		java.util.Date,
		java.text.SimpleDateFormat,
		net.itjds.bpm.data.FormClassBean,
		net.kzxd.hq.bean.HqSwYewu,
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
			HqSwYewu fod = (HqSwYewu)request.getAttribute("fod");
			String reg ="[\n-\r]";
	        Pattern p = Pattern.compile(reg);
		    String Docbt=fod.getWjbt();
		    Matcher m1 = p.matcher(Docbt);
		    Docbt = m1.replaceAll(""); 
		    Docbt = Docbt.replace("'", "’");
		    Docbt=Docbt.replace("\"", "“");
		    Docbt = Docbt.replace("\"", "”");
            String Nbyj=fod.getNbyj();
             if(Nbyj == null)
            	Nbyj = "";
            Matcher m2 = p.matcher(Nbyj);
            Nbyj = m2.replaceAll(""); 
		    Nbyj = Nbyj.replace("'", "’");
		    Nbyj=Nbyj.replace("\"", "“");
		    Nbyj = Nbyj.replace("\"", "”");
            String swbh = fod.getSwbh();
            if(swbh == null)
            	swbh = "";
            String ywbh = fod.getYwbh();
            if(ywbh == null)
            	ywbh = "";
            String ywdw = fod.getYwdw();
            if(ywdw == null)
            	ywdw = "";
            String ffzh = fod.getFfzh();
            if(ffzh == null)
            	ffzh = "";
            String yf = fod.getYf();
            if(yf == null)
            	yf = "";
            String jbr = fod.getJbr();
            if(jbr == null)
            	jbr = "";
            String hdr = fod.getHdr();
            if(hdr == null)
            	hdr = "";
         
		     String rdate="";
             Date date=fod.getSwrq();
			 if(date!= null){
				       SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				        rdate = sf.format(date);
                  }
           
			%>
			Ext.onReady(function(){
			document.getElementsByName("$Fdthqsw.fdtOaHqswDAO.ywbh").item(0).value='<%=ywbh%>';
			document.getElementsByName("$Fdthqsw.fdtOaHqswDAO.ffzh").item(0).value='<%=ffzh%>';
			document.getElementsByName("$Fdthqsw.fdtOaHqswDAO.wjbt").item(0).value='<%=Docbt%>';
			document.getElementsByName("$Fdthqsw.fdtOaHqswDAO.swrq").item(0).value='<%=rdate%>';
		    document.getElementsByName("$Fdthqsw.fdtOaHqswDAO.swbh").item(0).value='<%=swbh%>';
			document.getElementsByName("$Fdthqsw.fdtOaHqswDAO.ywdw").item(0).value='<%=ywdw%>';
			document.getElementsByName("$Fdthqsw.fdtOaHqswDAO.yf").item(0).value='<%=yf%>';
			document.getElementsByName("$Fdthqsw.fdtOaHqswDAO.nbyj").item(0).value='<%=Nbyj%>';
			document.getElementsByName("$Fdthqsw.fdtOaHqswDAO.jbr").item(0).value='<%=jbr%>';
			document.getElementsByName("$Fdthqsw.fdtOaHqswDAO.hdr").item(0).value='<%=hdr%>';
			
		
			
		});
		
		
	</script>
		   