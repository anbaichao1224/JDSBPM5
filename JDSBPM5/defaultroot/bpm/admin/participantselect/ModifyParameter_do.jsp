<%
/**
 *    $RCSfile: ModifyParameter_do.jsp,v $
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
		String isNew = request.getParameter("isNew");
		
        String name = request.getParameter("ParameterName");
        String code = request.getParameter("ParameterCode");
        String type = request.getParameter("ParameterType");
        String desc = request.getParameter("ParameterDesc");
        DbParticipantSelect participant = DbParticipantSelectManager.getInstance().loadByKey(ParticipantSelect_ID);
        DbExpressionParameter parameter = null;
        if("true".equalsIgnoreCase(isNew)) {
            parameter = new DbExpressionParameter();
            parameter.setParameterId(parameterId);
            parameter.setParameterName(name);
            parameter.setParameterCode(code);
            parameter.setParameterType(type);
            parameter.setParameterDesc(desc);
            participant.addParameter(parameter);
        } else {
           parameter = participant.getParameter(parameterId);
           if(parameter == null ) {
%>
<SCRIPT LANGUAGE="JavaScript">
alert("此参数不存在！");
window.parent._close();
</SCRIPT>
<%
               return; 
           }
           parameter.setParameterId(parameterId);
           parameter.setParameterName(name);
           parameter.setParameterCode(code);
           parameter.setParameterType(type);
           parameter.setParameterDesc(desc);
           participant.setParameter(parameter);
        }
        DbParticipantSelectManager.getInstance().save(participant);
        name = StringUtility.escapeJSSpecial(name);
        code = StringUtility.escapeJSSpecial(code);
        desc = StringUtility.escapeJSSpecial(desc);
        type = StringUtility.filterNull(type);
%>
<SCRIPT LANGUAGE="JavaScript">
<!--
var _parent = window.parent;
_parent.returnResult("<%=isNew%>", "<%=parameterId%>", "<%=name%>", "<%=code%>", "<%=DM.convertDM("ExpressionParameterType", type)%>", "<%=desc%>");
//-->
</SCRIPT>
</HEAD>

<%
	}
	catch(Exception e) {
		logger.error("", e);
%>
<SCRIPT LANGUAGE="JavaScript">
alert("保存失败！");
window.parent._close();
</SCRIPT>
<%
	}
%>