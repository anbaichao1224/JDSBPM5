<%
/**
 *    $RCSfile: connectionStatistic_do.jsp,v $
 *    $Author: liyang $
 *    $Revision: 1.2 $
 *    $Date: 2011/08/03 09:39:21 $
 */
%>

<%@ page contentType="text/html;charset=gb2312"%>
<%@ include file="../include/jsp/global.jsp" %>
<%@ page import="net.itjds.bpm.engine.BPMConstants" %>
<%@ page import="net.itjds.common.database.ProfiledConnection" %>

<%
String subsystemId=request.getParameter("subsystemId");
	if (subsystemId==null){
	   subsystemId="bpm";
	}
	String action = request.getParameter("action");
	if("reset".equals(action)) {
		ProfiledConnection.resetStatistics();
	}

	response.sendRedirect("connectionStatistic.jsp");
%>
