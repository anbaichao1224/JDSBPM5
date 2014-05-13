<%@ page contentType="text/html;charset=utf-8"%>
<!-- 翻页 begin -->
<tr>
  <td>
    <div align="center">
      <table width="580" border="0" cellspacing="0" cellpadding="0" align=center>
        <tr>
          <td height="5" colspan="2"> </td>
        </tr>
        <tr align="right">
          <td width="300" height="25"  valign="bottom">
 <%
    if(rollPage.hasPrevious())
	{
%>
	       <img src="<%=contextPath%>/bpm/admin/images/rollpage/rollpage_first_page.gif" width="53" height="17" onclick="goPage('1')" style="cursor:hand">
           <img src="<%=contextPath%>/bpm/admin/images/rollpage/rollpage_pre_page.gif" width="53" height="17" onclick="goPage('<%=rollPage.getCurPage()-1%>')" style="cursor:hand">
<%
	}
    else
    {
%>
		   <img src="<%=contextPath%>/bpm/admin/images/rollpage/rollpage_first_page.gif" width="53" height="17">
           <img src="<%=contextPath%>/bpm/admin/images/rollpage/rollpage_pre_page.gif" width="53" height="17">
<%
    }
%>
<%
    if(rollPage.hasNext())
	{
%>
			<img src="<%=contextPath%>/bpm/admin/images/rollpage/rollpage_next_page.gif" width="53" height="17" onclick="goPage('<%=rollPage.getCurPage()+1%>')" style="cursor:hand">
            <img src="<%=contextPath%>/bpm/admin/images/rollpage/rollpage_last_page.gif" width="53" height="17" onclick="goPage('<%=rollPage.getPageCount()%>')" style="cursor:hand">
<%
	}
    else
	{
%>
			<img src="<%=contextPath%>/bpm/admin/images/rollpage/rollpage_next_page.gif" width="53" height="17">
            <img src="<%=contextPath%>/bpm/admin/images/rollpage/rollpage_last_page.gif" width="53" height="17">
<%
	}
%>
		  </td>
          <td width="220" height="25" align="right">
		    <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr valign="bottom">
                <td width="20%" align="right">到第</td>
                <td width="15%"> <input id="goPageNo" type="text" class="inputtxt" size="4"></td>
                <td width="5%" align="center">页</td>
                <td width="10%" align="center"><img src="<%=contextPath%>/bpm/admin/images/rollpage/rollpage_go.gif" width="29" height="18" onclick="goPage(document.all.goPageNo.value)" style="cursor:hand"></td>
                <td width="50%" align="center">第<%=rollPage.getCurPage()%>页 共<%=rollPage.getCounts()%>条</td>
              </tr>
            </table>
	      </td>
        </tr>
	   </table>
	 </div>
   </td>
 </tr>
 <!-- 翻页 end -->