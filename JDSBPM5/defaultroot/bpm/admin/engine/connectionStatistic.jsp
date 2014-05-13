<%
/**
 *    $RCSfile: connectionStatistic.jsp,v $
 *    $Author: liyang $
 *    $Revision: 1.2 $
 *    $Date: 2011/08/03 09:39:21 $
 */
%>

<%@ page contentType="text/html;charset=gb2312"%>
<%@ page import="net.itjds.bpm.engine.BPMConstants" %>
<%@ page import="net.itjds.common.util.NumberUtility" %>
<%@ page import="net.itjds.common.database.ConnectionManager" %>
<%@ page import="net.itjds.common.CommonConfig" %>
<%@ page import="net.itjds.common.database.ProfiledConnection" %>
<%@ page import="net.itjds.common.database.ConnectionManagerFactory" %>

<%
	String contextpath = request.getContextPath()+"/";
    ConnectionManager cm = ConnectionManagerFactory.getInstance().getConnectionManager("org");
	  cm.setProfilingEnabled(true);
	  String[] systems= CommonConfig.getValues("subsystemid");
	for(int k=0;k<systems.length;k++){
	    ConnectionManager subcm = ConnectionManagerFactory.getInstance().getConnectionManager(systems[k]);
	    subcm.setProfilingEnabled(true);
	}
	boolean isProfileEnabled = true;
%>
<HTML>
<HEAD>
<TITLE> BPM Engine Admin </TITLE>

<link href="<%=contextpath %>bpm/admin/include/css/style.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.style1 {font-size: 16px}
-->
</style>
</HEAD>

<BODY   LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0> 
<TABLE width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF"> 

  <tr> 
    <td height="500" align="center" valign="top"> <TABLE width="100%" height="184" border=0 align=center cellspacing="3">
<tr height="21" valign="bottom">
        <td width="500" height="17" class="lin3"><font color="FB4303">�� ��ǰλ�� ����ع��� �����ݿ���</font></td>
      </tr>
      	<tr><td>
			&nbsp;&nbsp;<input type="button" name="dumpCacheBtn" onClick="window.location='connectionStatistic_do.jsp?action=reset'" value="����ͳ������" class="inputbutton"><br><br>
		</td></tr>
      <tr>
        <td height="20" align="left" valign="top">
		<%
			if(isProfileEnabled) {
		%>
			<table width=100% border=0 cellpadding=3 cellspacing=0 class=tr>
        <tr align=center bgcolor=#E3E8F8>
          <td width="64" height="21" bgcolor="E3E8F8" class="td">����</td>
          <td width="115" height="21" bgcolor="E3E8F8" class="td">ִ�д���<br>
            </td>
          <td width="103" bgcolor="E3E8F8" class="td">ִ�д���/��</td>
          <td width="185" bgcolor="E3E8F8" class="td">ƽ��ִ��ʱ��<br>�����룩</td>
          <td width="116" bgcolor="E3E8F8" class="td">�ܹ�ִ��ʱ��<br>�����룩</td>
          <td width="117" bgcolor="E3E8F8" class="td">����</td>
        </tr>
        <tr height="26">
          <td class="td" align="left"><div align="center">��ѯ</div></td>
          <td align="center" class="td"><%=ProfiledConnection.getQueryCount(ProfiledConnection.SELECT)%></td>
          <td align="center" class="td"><%=NumberUtility.formatNumber(ProfiledConnection.getQueriesPerSecond(ProfiledConnection.SELECT), 1)%></td>
          <td align="center" class="td"><%=NumberUtility.formatNumber(ProfiledConnection.getAverageQueryTime(ProfiledConnection.SELECT), 1)%></td>
          <td align="center" class="td"><%=ProfiledConnection.getTotalQueryTime(ProfiledConnection.SELECT)%></td>
          <td align="center" class="td"><a href="connectionStatisticDetail.jsp?type=SELECT" class="A_1">�鿴��ϸ</a></td>
        </tr>
		<tr height="21">
          <td class="td" align="left"><div align="center">����</div></td>
          <td align="center" class="td"><%=ProfiledConnection.getQueryCount(ProfiledConnection.UPDATE)%></td>
          <td align="center" class="td"><%=NumberUtility.formatNumber(ProfiledConnection.getQueriesPerSecond(ProfiledConnection.UPDATE), 1)%></td>
          <td align="center" class="td"><%=NumberUtility.formatNumber(ProfiledConnection.getAverageQueryTime(ProfiledConnection.UPDATE), 1)%></td>
          <td align="center" class="td"><%=ProfiledConnection.getTotalQueryTime(ProfiledConnection.UPDATE)%></td>
          <td align="center" class="td"><a href="connectionStatisticDetail.jsp?type=UPDATE" class="A_1">�鿴��ϸ</a></td>
		</tr>
		<tr height="21">
          <td class="td" align="left"><div align="center">����</div></td>
          <td align="center" class="td"><%=ProfiledConnection.getQueryCount(ProfiledConnection.INSERT)%></td>
          <td align="center" class="td"><%=NumberUtility.formatNumber(ProfiledConnection.getQueriesPerSecond(ProfiledConnection.INSERT), 1)%></td>
          <td align="center" class="td"><%=NumberUtility.formatNumber(ProfiledConnection.getAverageQueryTime(ProfiledConnection.INSERT), 1)%></td>
          <td align="center" class="td"><%=ProfiledConnection.getTotalQueryTime(ProfiledConnection.INSERT)%></td>
          <td align="center" class="td"><a href="connectionStatisticDetail.jsp?type=INSERT" class="A_1">�鿴��ϸ</a></td>
		</tr>
		<tr height="21">
          <td class="td" align="left"><div align="center">ɾ��</div></td>
          <td align="center" class="td"><%=ProfiledConnection.getQueryCount(ProfiledConnection.DELETE)%></td>
          <td align="center" class="td"><%=NumberUtility.formatNumber(ProfiledConnection.getQueriesPerSecond(ProfiledConnection.DELETE), 1)%></td>
          <td align="center" class="td"><%=NumberUtility.formatNumber(ProfiledConnection.getAverageQueryTime(ProfiledConnection.DELETE), 1)%></td>
          <td align="center" class="td"><%=ProfiledConnection.getTotalQueryTime(ProfiledConnection.DELETE)%></td>
          <td align="center" class="td"><a href="connectionStatisticDetail.jsp?type=DELETE" class="A_1">�鿴��ϸ</a></td>
          
		</tr>
		
      </table>
      
      
		<%
			}
			else {
		%>
		&nbsp;&nbsp;���ݿ�����ͳ�ƹ����ѱ�����
		<%
			}
		%>
		</td>
	
      </tr> 
</table> 

</td> </tr>
</table>

      
</BODY>
</HTML>
