<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="net.itjds.bpm.admin.Constants" %>
<%@ page import="net.itjds.bpm.engine.query.*" %>
<%@ page import="net.itjds.common.org.base.Person" %>
<%@ page import="net.itjds.bpm.engine.*"%>
<%@ page import="net.itjds.bpm.client.*"%>
<%@ page import="net.itjds.bpm.engine.config.CApplication"%>
<%@ page import="java.util.*"%>

<link href="../css/style.css" rel="stylesheet" type="text/css">
<SCRIPT LANGUAGE="JavaScript" src="../include/js/jcommon.js"></SCRIPT>
<!--
<table cellpadding="0" cellspacing="0"  bgcolor="A8DDFB" height="450">
  <tr>
    <td width="180" align="center" valign="top" bgcolor="F5F5F5" class="linedot2">
      <table width="165" border="0" cellspacing="0" cellpadding="0">
        <tr> 
          <td width="165" align="center">&nbsp;</td>
        </tr>
        <tr> 
          <td align="center">
            <table width="150" border="0" cellspacing="0" cellpadding="0">
              <tr align="center"> 
                <td width="153"><strong><font color="#006699">pross manage</font></strong></td>
              </tr>
           </table>
         </td>
       </tr>
     </table>
     <br>
<%
	boolean isFirst = false;
	List systemCodeList = (List) session.getAttribute("psd_systemCodeList");
	if (systemCodeList == null) {
		isFirst = true;
		systemCodeList = new ArrayList();
	}
	
	String personId = (String)session.getAttribute("personId");
	
	
	
	
	
	
	
	
	Map map = WorkflowServer.getApplications(); 
	for(Iterator it=map.values().iterator(); it.hasNext();) {
		CApplication app = (CApplication) it.next();
		String systemCode = app.getCode();
		if (!isFirst && !systemCodeList.contains(systemCode)) {
			continue;
		}
		
		BPMSessionFactory sessionFactory = new BPMSessionFactory();
		WorkflowClientService client = sessionFactory.getClientService(request, systemCode);


		AdminService admin = WorkflowServer.getInstance().getAdminService(client);
		if (admin == null) {
			continue;
		}
		//人员判断条件
        String sql = "SELECT RT_PROCESSDEF_SUPERVISOR.PROCESSDEF_ID FROM RT_PROCESSDEF_SUPERVISOR WHERE RT_PROCESSDEF_SUPERVISOR.SUPERVISOR_ID='" + personId + "'";
        Condition userCon = new Condition(ConditionKey.PROCESSDEF_ID, Condition.IN, sql);
        
		List processDefList = admin.getProcessDefList(userCon, null, null);
		if (processDefList.size() == 0) {
			continue;
		}
		if (isFirst) {
			systemCodeList.add(systemCode);
		}
%>    

     <table width="150" border="0" cellspacing="0" cellpadding="3">
        <tr> 
          <td  width="27" height="21" align="center"><img src="/images/left_img_dot_1.gif" width="13" height="13"></td>
          <td align="center" width="138" bgcolor="#E3E8F8"> <strong><a href='/bpm/admin/psd/ProcessList.jsp?SystemCode=<%=app.getCode()%>' target='mainFrame'><%=app.getName()%> </a></strong> 
          </td>
        </tr>
        <tr valign="top"> 
          <td colspan="2">
            <table width="100%" border="0" cellpadding="3" cellspacing="0">
            <% for(int i=0, n=processDefList.size(); i<n; i++) {
            	ProcessDef processDef = (ProcessDef) processDefList.get(i);
            %>
              <tr> 
                <td align="center"><img src="/images/left_img_dot_2.gif" width="13" height="7"></td>
                <td>&nbsp;<a href='/bpm/admin/psd/ProcessList.jsp?ProcessDefId=<%=processDef.getProcessDefId()%>' target='mainFrame'><%=processDef.getName()%></a></td>
              </tr>
            <%}%>
            </table>
          </td>
        </tr>
      </table>
<%}
	session.setAttribute("psd_systemCodeList", systemCodeList);
%>
      <br>
    </td>
  </tr>
</table>
-->