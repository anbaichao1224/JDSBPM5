<%
/**
 *    $RCSfile: NewParticipantSelect_do.jsp,v $
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
<%@ page import="net.itjds.common.util.UUIDGenerator" %>
<%@ page import="net.itjds.bpm.engine.database.right.*" %>

<%
	try {
	    String ParticipantSelect_ID = UUIDGenerator.genUUID();
        String name = request.getParameter("SelectName");
		String desc = request.getParameter("SelectDesc");
		String formula = request.getParameter("FORMULA");

        DbParticipantSelect participant = DbParticipantSelectManager.getInstance().createParticipantSelect();
		participant.setParticipantSelectId(ParticipantSelect_ID);
        participant.setSelectName(name);
        participant.setSelectDesc(desc);
        participant.setFormula(formula);
        DbParticipantSelectManager.getInstance().save(participant);
        response.sendRedirect("ModifyParticipantSelect.jsp?ParticipantSelect_ID=" + ParticipantSelect_ID);
        return;
	}
	catch(Exception e) {
		logger.error("", e);
		response.sendRedirect("../error.jsp");
	}
%>