<%
/**
 *    $RCSfile: newFdtPerson_do.jsp,v $
 *    $Author: liyang $
 *    $Revision: 1.2 $
 *    $Date: 2011/08/03 09:39:04 $
 */
%>

<%@ page contentType="text/html;charset=gb2312"%>
<%@ include file="../include/jsp/global.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="net.itjds.bpm.engine.BPMConstants" %>
<%@ page import="net.itjds.common.database.DBAgent" %>

<%
    String PERSON_ID = request.getParameter("PERSON_ID");
    String APPNAME = request.getParameter("APPNAME");
    String PERSON_SELECT_FORMULA = request.getParameter("PERSON_SELECT_FORMULA");
    DBAgent dba = new DBAgent(BPMConstants.CONFIG_KEY);
    String querySQL = "SELECT PERSON_ID FROM ADMIN_BPMSYS_PERSON WHERE PERSON_ID='"+PERSON_ID+"' AND APPNAME='"+APPNAME+"' AND MODULE_NAME='FDT'";
    int state = dba.execute(querySQL);
    if(state == -1) {
        response.sendRedirect("../error.jsp");
        dba.close();
        return;
    }
    if(dba.getRows() != 0) {
        response.sendRedirect("../error.jsp?errorMsg="+URLEncoder.encode("����ʧ�ܣ��Ѿ�������Ա��Ӧ�õ�������Ϣ���ݣ�����������ݽ����޸Ĳ�����")+"&returnURI=processDefPerson/processDefPersonList.jsp");
        dba.close();
        return;
    }
    String insertSQL = "INSERT INTO ADMIN_BPMSYS_PERSON (PERSON_ID, APPNAME, PERSON_SELECT_FORMULA, MODULE_NAME) VALUES ('"+PERSON_ID+"','"+APPNAME+"','"+PERSON_SELECT_FORMULA+"', 'FDT')";
    state = dba.execute(insertSQL);
    dba.close();
    if(state == -1) {
        response.sendRedirect("../error.jsp");
    }
    else {
        response.sendRedirect("fdtPersonList.jsp");
    }
%>