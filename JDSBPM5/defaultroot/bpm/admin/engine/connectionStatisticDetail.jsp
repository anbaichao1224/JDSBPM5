<%
/**
 *    $RCSfile: connectionStatisticDetail.jsp,v $
 *    $Author: liyang $
 *    $Revision: 1.2 $
 *    $Date: 2011/08/03 09:39:21 $
 */
%>

<%@ page contentType="text/html;charset=gb2312"%>
<%@ include file="../include/jsp/global.jsp" %>
<%@ page import="net.itjds.common.util.StringUtility" %>
<%@ page import="net.itjds.common.database.ProfiledConnection" %>
<%@ page import="net.itjds.common.database.ProfiledConnectionEntry" %>

<%!
	public static String formatSQL(String sql) {
		String tmp = " "+sql.toUpperCase();
		tmp = StringUtility.replace(tmp, " SELECT ", " "+keyword("SELECT")+" ", 1);
		tmp = StringUtility.replace(tmp, " UPDATE ", " "+keyword("UPDATE")+" ", 1);
		tmp = StringUtility.replace(tmp, " INSERT INTO ", " "+keyword("INSERT INTO")+" ", 1);
		tmp = StringUtility.replace(tmp, " DELETE ", " "+keyword("DELETE")+" ", 1);
		tmp = StringUtility.replace(tmp, " VALUES ", " <br>"+keyword("VALUES")+" ", 1);
		tmp = StringUtility.replace(tmp, " FROM ", " <br>"+keyword("FROM")+" ", 1);
		tmp = StringUtility.replace(tmp, " WHERE ", " <br>"+keyword("WHERE")+" ", 1);
		tmp = StringUtility.replace(tmp, " IN ", " <br>"+keyword("IN")+" ");

		return tmp;
	}
	
	public static String keyword(String keyword) {
		return "<font color='red'>"+keyword+"</font>";
	}
%>

<%
	String type = request.getParameter("type");
	ProfiledConnectionEntry[] entries = null;
	try {
		if("SELECT".equals(type)) {
			entries = ProfiledConnection.getSortedQueries(ProfiledConnection.SELECT,true);
		}
		else if("UPDATE".equals(type)) {
			entries = ProfiledConnection.getSortedQueries(ProfiledConnection.UPDATE,true);
		}
		else if("INSERT".equals(type)) {
			entries = ProfiledConnection.getSortedQueries(ProfiledConnection.INSERT,true);
		}
		else if("DELETE".equals(type)) {
			entries = ProfiledConnection.getSortedQueries(ProfiledConnection.DELETE,true);
		}
	}
	catch(Exception e) {
		e.printStackTrace();
		response.sendRedirect("../error.jsp?errorMsg="+e.getMessage()+"&returnURI=engine/connectionStatistic.jsp");
		return;
	}
	String contextpath = request.getContextPath()+"/";
%>
<HTML>
<HEAD>
<TITLE> BPM Engine Admin </TITLE>
<link href="<%=contextpath %>bpm/admin/include/css/style.css" rel="stylesheet" type="text/css">
<SCRIPT LANGUAGE="JavaScript" src="<%=contextpath %>bpm/admin/include/js/jcommon.js"></SCRIPT>
<style type="text/css">
<!--
.style1 {font-size: 16px}
-->
</style>
</HEAD>

<BODY   LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0> 

<TABLE width="95%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF"> 
     <tr height="21" valign="bottom">
        <td width="500" height="17" class="lin3"><font color="FB4303">■ 当前位置 》监控管理 》数据库监控</font></td>
      </tr>
  <tr> 
    <td height="500" align="center" valign="top"> <TABLE width="100%" height="184" border=0 align=center cellspacing="3">
     
      <tr>
        <td height="86" align="center" valign="top">
		<table width=100% border=0 cellpadding=3 cellspacing=0 class=tr>
        <tr align=center bgcolor=#E3E8F8>
          <td width="494" height="21" bgcolor="E3E8F8" class="td">SQL</td>
          <td width="63" height="21" bgcolor="E3E8F8" class="td">执行次数<br>
            </td>
          <td width="65" bgcolor="E3E8F8" class="td">执行时间<br>（毫秒）</td>
          <td width="79" bgcolor="E3E8F8" class="td">平均执行时间<br>
            （毫秒）</td>
        </tr>
		<%
			if(entries != null) {
				for(int i=0; i < entries.length; i++) {
					ProfiledConnectionEntry entry = entries[i];
		%>
        <tr height="21">
          <td class="td" align="left"><%=formatSQL(entry.sql) %></td>
          <td align="center" class="td"><%=entry.count %></td>
          <td align="center" class="td"><%=entry.totalTime %></td>
          <td align="center" class="td"><%=entry.count==0?0:(double)(entry.totalTime*100/entry.count)/(double)100 %></td>
        </tr>
		<%
				}
			}
		%>
      </table>
	  <br>&nbsp;&nbsp;<input type="button" onClick="window.location='connectionStatistic.jsp'" name="clearCacheBtn" value="返回" class="inputbutton">
		</td>
      </tr> 
</table> 
</td> </tr>
</table>
<%@ include file="../include/jsp/bottom.jsp" %>
</BODY>
</HTML>
