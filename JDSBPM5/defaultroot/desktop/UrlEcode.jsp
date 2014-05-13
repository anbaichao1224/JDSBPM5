<%@ page contentType="text/html; charset=GBK"%>
<meta http-equiv="content-type" content="text/html;charset=GBK">
<script language=javascript>
<%String url=(String)request.getParameter("url");
  url=new String(url.getBytes("ISO-8859-1"));
  System.out.println(url);
%>
window.location.replace('<%=url%>');
</script>




 
