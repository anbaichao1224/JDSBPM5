<%
/**
 *    $RCSfile: rollbar.jsp,v $
 *    $Author: liyang $
 *    $Revision: 1.2 $
 *    $Date: 2011/08/03 09:39:20 $
 */
%>

<a href="<%=rollPage.getFirstLink() %>"><img src="<%=contextPath %>/bpm/admin/images/cms_first_page.gif" width="53" height="17" border="0"></a>
<a href="<%=rollPage.getPreviousLink() %>"><img src="<%=contextPath %>/bpm/admin/images/cms_pre_page.gif" width="53" height="17" border="0"></a>
<a href="<%=rollPage.getNextLink() %>"><img src="<%=contextPath %>/bpm/admin/images/cms_next_page.gif" width="53" height="17" border="0"></a>
<a href="<%=rollPage.getLastLink() %>"><img src="<%=contextPath %>/bpm/admin/images/cms_last_page.gif" width="53" height="17" border="0"></a>