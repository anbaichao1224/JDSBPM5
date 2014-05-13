<%
    out.println(session.getAttribute("FileUpload.Progress."+request.getParameter("sessionId").toString().trim()));
    System.out.println(session.getAttribute("FileUpload.Progress."+request.getParameter("sessionId").toString().trim()));
%>
