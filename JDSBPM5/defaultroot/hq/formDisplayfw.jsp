	<%@ page contentType="text/html; charset=UTF-8"%>
	<%@ page
		import="java.util.Map,
		com.opensymphony.xwork2.ActionContext,
		java.util.List,
		java.text.SimpleDateFormat,
		java.util.Date,
		net.itjds.bpm.data.FormClassBean,
		net.kzxd.hq.bean.HqYewu,
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
			HqYewu fod = (HqYewu)request.getAttribute("fod");
			 System.out.println("-------交办理对应的数据uuid--------"+fod.getNbyj());
			String reg ="[\n-\r]";
	        Pattern p = Pattern.compile(reg);
		    String Docbt=fod.getBt();
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
            String wjbh = fod.getWjbh();
            if(wjbh == null)
            	wjbh = "";
             String hgr = fod.getHgr();
            if(hgr == null)
            	hgr = "";
            String hgdw = fod.getHgdw();
            if(hgdw == null)
            	hgdw = "";
            String gkfs = fod.getGkfs();
            if(gkfs == null)
            	gkfs = "";
            String cs = fod.getCs();
            if(cs == null)
            	cs = "";
            String zs = fod.getZs();
            if(zs == null)
            	zs = "";
            String yffs = fod.getYffs();
            if(yffs == null)
            	yffs = "";
            String xd = fod.getXd();
            if(xd == null)
            	xd = "";
            String dz = fod.getDz();
            if(dz == null)
            	dz = "";
            String bz = fod.getBz();
            if(bz == null)
            	bz = "";
            String bwxh = fod.getBwxh();
            if(bwxh == null)
            	bwxh = "";
            String Wjlx = fod.getWjlx();
            if(Wjlx == null)
            	Wjlx = "";
            	
             String rdate="";
             Date date=fod.getNgsj();
			 if(date!= null){
				       SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				        rdate = sf.format(date);
                  }
                   
                String rdate1="";
             Date date1=fod.getHgsj();
			 if(date1!= null){
				       SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				        rdate1 = sf.format(date1);
                  }
                String rdate2="";
             Date date2=fod.getYfsj();
			 if(date2!= null){
				       SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				        rdate2 = sf.format(date2);
                  }
			%>
		//	String Docbt=Docbt.replaceAll("\r\n","<br/>");
			Ext.onReady(function(){
			document.getElementsByName("$Fdthqgwg.fdtOaHqgwgDAO.ngdw").item(0).value='<%=fod.getNgdw()%>';
			document.getElementsByName("$Fdthqgwg.fdtOaHqgwgDAO.ngr").item(0).value='<%=fod.getNgr()%>';
			document.getElementsByName("$Fdthqgwg.fdtOaHqgwgDAO.bt").item(0).value='<%=Docbt%>';
			document.getElementsByName("$Fdthqgwg.fdtOaHqgwgDAO.ngsj").item(0).value='<%=rdate%>';
			document.getElementsByName("$Fdthqgwg.fdtOaHqgwgDAO.bh").item(0).value='<%=wjbh%>';
		    document.getElementsByName("$Fdthqgwg.fdtOaHqgwgDAO.wjlx").item(0).value='<%=Wjlx%>';
			document.getElementsByName("$Fdthqgwg.fdtOaHqgwgDAO.nbyj").item(0).value='<%=Nbyj%>';
			document.getElementsByName("$Fdthqgwg.fdtOaHqgwgDAO.hgr").item(0).value='<%=hgr%>';
			document.getElementsByName("$Fdthqgwg.fdtOaHqgwgDAO.hgdw").item(0).value='<%=hgdw%>';
			document.getElementsByName("$Fdthqgwg.fdtOaHqgwgDAO.gkfs").item(0).value='<%=gkfs%>';
			document.getElementsByName("$Fdthqgwg.fdtOaHqgwgDAO.cs").item(0).value='<%=cs%>';
			document.getElementsByName("$Fdthqgwg.fdtOaHqgwgDAO.zs").item(0).value='<%=zs%>';
			document.getElementsByName("$Fdthqgwg.fdtOaHqgwgDAO.yffs").item(0).value='<%=yffs%>';
			document.getElementsByName("$Fdthqgwg.fdtOaHqgwgDAO.xd").item(0).value='<%=xd%>';
			document.getElementsByName("$Fdthqgwg.fdtOaHqgwgDAO.dz").item(0).value='<%=dz%>';
			document.getElementsByName("$Fdthqgwg.fdtOaHqgwgDAO.bz").item(0).value='<%=bz%>';
			document.getElementsByName("$Fdthqgwg.fdtOaHqgwgDAO.bwxh").item(0).value='<%=bwxh%>';
			
			document.getElementsByName("$Fdthqgwg.fdtOaHqgwgDAO.hgsj").item(0).value='<%=rdate1%>';
			document.getElementsByName("$Fdthqgwg.fdtOaHqgwgDAO.yfsj").item(0).value='<%=rdate2%>';
		
			
		});
		
		
	</script>
		   