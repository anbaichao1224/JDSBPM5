<%
/**
 *    $RCSfile: DeleteParticipantSelect.jsp,v $
 *    $Author: liyang $
 *    $Revision: 1.2 $
 *    $Date: 2011/08/03 09:38:58 $
 */
%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../include/jsp/global.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="net.itjds.common.util.StringUtility" %>
<%@ page import="net.itjds.common.dm.DM" %>
<%@ page import="net.itjds.bpm.engine.database.right.*" %>

<%
	try {
	    String ParticipantSelect_ID = request.getParameter("ParticipantSelect_ID");
        String id = request.getParameter("ParticipantSelect_ID");

        DbParticipantSelect participant = DbParticipantSelectManager.getInstance().loadByKey(ParticipantSelect_ID);
        if(participant == null ) {
            response.sendRedirect("../error.jsp?errorMsg="+URLEncoder.encode("错误的参数！")+"&returnURI=./participantselect/ParticipantSelectList.jsp");
            return;
        }
        DbParticipantSelectManager.getInstance().deleteByKey(ParticipantSelect_ID);
        response.sendRedirect("ParticipantSelectList.jsp");
        return;
	}
	catch(Exception e) {
		logger.error("", e);
		response.sendRedirect("../error.jsp");
	}
%>