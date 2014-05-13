<%
/**
 *    $RCSfile: global.jsp,v $
 *    $Author: liyang $
 *    $Revision: 1.2 $
 *    $Date: 2011/08/03 09:39:20 $
 */
%>

<%@ page import="net.itjds.bpm.admin.Constants" %>
<%@ page import="net.itjds.common.logging.LogFactory" %>
<%@ page import="net.itjds.common.logging.Log" %>
<%@ page import="net.itjds.bpm.engine.BPMConstants" %>
<%!
    private static final Log logger = LogFactory.getLog(BPMConstants.CONFIG_KEY, "BPM_ADMIN_JSP");
    private static final int PERPAGESIZE = 15;
	private static final int REFRESHINTERVAL = 20;
%>
<%

    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);

 
    String adminRole = (String)session.getAttribute(Constants.ADMIN_ROLE_KEY);
    String fdtRole = (String)session.getAttribute(Constants.FDT_ROLE_KEY);
    String pdtRole = (String)session.getAttribute(Constants.BPD_ROLE_KEY);
    if(adminRole == null) adminRole = "";
    if(fdtRole == null) fdtRole = "";
    if(pdtRole == null) pdtRole = "";
/*
  if(!adminRole.equals(Constants.ADMIN_ROLE_SUPERADMIN) && !fdtRole.equals(Constants.ADMIN_ROLE_FDT) && !pdtRole.equals(Constants.ADMIN_ROLE_BPD)) {
        response.sendRedirect("/bpm/admin/login.jsp");
       return;
   }
    */
%>
<%
    String contextPath = request.getContextPath();
%>