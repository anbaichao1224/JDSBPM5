<%@ page contentType="text/html; charset=GBK"%>
<meta http-equiv="content-type" content="text/html;charset=GBK">
<script language=javascript>
<%String dir=(String)request.getParameter("dir");
	dir=java.net.URLEncoder.encode(dir);
  System.out.println(dir);
%>
window.open('/<%=dir%>');
</script>




 
