<%
/**
 *    $RCSfile: delProcessDefPerson.jsp,v $
 *    $Author: liyang $
 *    $Revision: 1.2 $
 *    $Date: 2011/08/03 09:39:18 $
 */
%>

<%@ page contentType="text/html;charset=gb2312"%>
<%@ include file="../include/jsp/global.jsp" %>
<%@ page import="net.itjds.bpm.engine.BPMConstants" %>
<%@ page import="net.itjds.common.database.DBAgent" %>

<%
    String PERSON_ID = request.getParameter("PERSON_ID");
    String APPNAME = request.getParameter("APPNAME");
    DBAgent dba = new DBAgent(BPMConstants.CONFIG_KEY);
    String deleteSQL = "DELETE FROM ADMIN_BPMSYS_PERSON WHERE PERSON_ID='"+PERSON_ID+"' AND APPNAME='"+APPNAME+"' AND MODULE_NAME='BPD'";
    int state = dba.execute(deleteSQL);
    dba.close();
    if(state == -1) {
        response.sendRedirect("../error.jsp");
        return;
    }
    else {
        response.sendRedirect("processDefPersonList.jsp");
    }
%>