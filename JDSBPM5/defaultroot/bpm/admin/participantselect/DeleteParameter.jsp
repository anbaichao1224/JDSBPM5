<%
/**
 *    $RCSfile: DeleteParameter.jsp,v $
 *    $Author: liyang $
 *    $Revision: 1.2 $
 *    $Date: 2011/08/03 09:38:58 $
 */
%>

<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../include/jsp/global.jsp" %>
<%@ page import="net.itjds.common.util.StringUtility" %>
<%@ page import="net.itjds.common.dm.DM" %>
<%@ page import="net.itjds.bpm.engine.database.right.*" %>

<%
	try {
	    String ParticipantSelect_ID = request.getParameter("ParticipantSelect_ID");
	    String parameterId = request.getParameter("ParameterId");

        DbParticipantSelect participant = DbParticipantSelectManager.getInstance().loadByKey(ParticipantSelect_ID);
        DbExpressionParameter parameter = null;
        parameter = participant.getParameter(parameterId);
        participant.removeParameter(parameter);
        DbParticipantSelectManager.getInstance().save(participant);
%>
<SCRIPT LANGUAGE="JavaScript">
<!--
var _parent = window.parent;
_parent.delParameterRow("<%=parameterId%>");
//-->
</SCRIPT>
</HEAD>

<%
	}
	catch(Exception e) {
		logger.error("", e);
%>
<SCRIPT LANGUAGE="JavaScript">
alert("删除失败！");
</SCRIPT>
<%
	}
%>