<%
/**
 *    $RCSfile: logout.jsp,v $
 *    $Author: liyang $
 *    $Revision: 1.2 $
 *    $Date: 2011/08/03 09:39:04 $
 */
%>

<%
    session.invalidate();
    response.sendRedirect("login.jsp");
%>