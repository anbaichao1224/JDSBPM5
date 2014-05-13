<%
/**
 *    $RCSfile: engineAdmin_do.jsp,v $
 *    $Author: liyang $
 *    $Revision: 1.2 $
 *    $Date: 2011/08/03 09:39:21 $
 */
%>

<%@ page contentType="text/html;charset=gb2312"%>
<%@ include file="../include/jsp/global.jsp" %>
<%@ page import="net.itjds.bpm.engine.WorkflowServer" %>

<%
	String action = request.getParameter("action");
	if("stop".equals(action)) {
		WorkflowServer.stop();
	}
	else if("restart".equals(action)) {
		WorkflowServer.restart();
	}
	else if("start".equals(action)) {
		WorkflowServer.start();
	}
	response.sendRedirect("engineAdmin.jsp");
%>