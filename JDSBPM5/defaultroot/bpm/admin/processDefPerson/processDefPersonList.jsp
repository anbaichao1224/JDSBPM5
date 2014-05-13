<%
/**
 *    $RCSfile: processDefPersonList.jsp,v $
 *    $Author: liyang $
 *    $Revision: 1.2 $
 *    $Date: 2011/08/03 09:39:18 $
 */
%>

<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="../include/jsp/global.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="net.itjds.common.web.RollPage" %>
<%@ page import="net.itjds.bpm.engine.BPMConstants" %>
<%@ page import="net.itjds.common.org.base.OrgManager" %>
<%@ page import="net.itjds.common.org.base.OrgManagerFactory" %>
<%@ page import="net.itjds.common.org.base.Person" %>

<%
	String order = request.getParameter("order");
    RollPage rollPage = new RollPage(request);
	String orderByStr = "";
	if("1".equals(order)) {
		orderByStr = " ORDER BY PERSON_ID";
	}
	else if("2".equals(order)) {
		orderByStr = " ORDER BY APPNAME";
	}
	else {
		orderByStr = " ORDER BY PERSON_ID";
	}
	
    String querySQL = "SELECT PERSON_ID, APPNAME, PERSON_SELECT_FORMULA FROM ADMIN_BPMSYS_PERSON WHERE MODULE_NAME='BPD' "+orderByStr;
    ResultSet rs = rollPage.rollAction(BPMConstants.CONFIG_KEY, querySQL, PERPAGESIZE);
    if(rs == null) {
        rollPage.close();
        response.sendRedirect("error.jsp");
        return;
    }
    else {
        try {
            OrgManager orgManager = OrgManagerFactory.getInstance().getOrgManager();
%>

<HTML>
<HEAD>
<TITLE> BPM Engine Admin </TITLE>
<link href="../include/css/style.css" rel="stylesheet" type="text/css">
<SCRIPT LANGUAGE="JavaScript" src="../include/js/jcommon.js"></SCRIPT>
</HEAD>

<BODY   LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0> 

<TABLE width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
  <tr> 
    <td height="8" bgcolor="E3E8F8"> </td>
  </tr>
  <tr> 
    <!-- 列表 begin -->
    <td height="500" align="center" valign="top"> <TABLE width="100%" border=0 align=center cellspacing="3">
        <tr height="21" valign="bottom"> 
          <td width="500" class="lin3"><font color="FB4303">■ 当前位置 》流程定义人员列表</font></td> 
        </tr> 
      </table> 
      <br> 
      
        <TABLE width="100%" border=0 cellPadding=3 cellSpacing=0 class=tr>
        <form name="frmList" method="post">
        <input type="hidden" name="formids" value="">
          <TR align=center bgColor=#E3E8F8>
            <td width="10%" height="21" class="td" bgcolor="E3E8F8">编号</td> 
            <td width="20%" height="21" bgcolor="E3E8F8" class="td"><a href="processDefPersonList.jsp?order=1">人员名称</a></td>
            <td width="20%" height="21" bgcolor="E3E8F8" class="td"><a href="processDefPersonList.jsp?order=2">应用</a></td>
            <td width="30%" bgcolor="E3E8F8" class="td">人员选择公式</td>
            <td width="20%" bgcolor="E3E8F8" class="td">功能</td>
          </tr> 
        <%
          int count=0;
          while(rs.next() && count < PERPAGESIZE) {
            try {
                String personID = rs.getString(1);
                String appName = rs.getString(2);
				String formula = rs.getString(3);
                Person person = orgManager.getPersonByID(rs.getString(1));
				count++;
        %>
          <tr height="21"> 
            <td class="td" align="center"><%=count %></td> 
            <td class="td" align="center"><%=person.getName() %></td> 
            <td align="center" class="td"><%=appName %></td>
            <td align="center" class="td"><%=formula==null?"&nbsp;":formula%></td>
            <td align="center" class="td"><a href="modProcessDefPerson.jsp?PERSON_ID=<%=personID %>&APPNAME=<%=appName %>" class="A_1">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="delProcessDefPerson.jsp?PERSON_ID=<%=personID %>&APPNAME=<%=appName %>" class="A_1">删除</a></td>
          </tr> 
        <%
            }catch(Exception e) {
                logger.error("", e);
            }
          }
          if(count < PERPAGESIZE){	//如果数据行数比本页行数少，补空白行
            int blankline = PERPAGESIZE-count;
		    for(int i=0; i<blankline; i++) {
		  
        %>
        <tr height="21"> 
		  <td class="td">&nbsp;</td>
		  <td class="td">&nbsp;</td>
		  <td class="td">&nbsp;</td>
	      <td class="td">&nbsp;</td>
	      <td class="td">&nbsp;</td>
        </tr>
        <%
	        }
	      }
        %>
          </form>
        </TABLE> 
      <!-- 翻页 begin --> 
      <table width="440" border="0" cellspacing="0" cellpadding="0" align=right> 
        <tr> 
          <td height="5" colspan="2"> </td> 
        </tr> 
        <tr align="right"> 
          <td width="300" height="25"  valign="bottom"> 
            <%@ include file="../include/jsp/rollbar.jsp" %>
          </td> 
          <td width="140" height="25" align="right">
            <table width="100%" border="0" cellspacing="0" cellpadding="0"> 
              <tr valign="bottom"> 
                <td width="50%" align="center">&nbsp;第 <%= rollPage.getCurPage() %>/<%= rollPage.getPageCount() %> 页 共 <%= rollPage.getCounts() %> 条</td> 
              </tr> 
            </table>
          </td> 
        </tr> 
      </table> 
      <!-- 翻页 end --> 
      <!-- 操作 begin --> 
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0"> 
        <TR> 
           <TD align=center height="35">&nbsp;</TD>
        </TR>
        <tr align="center"> 
          <td height="50" width="100%">
            <input name="Submit223" type="button" class="inputbutton" style="cursor:hand;" value="新 建" onclick="window.location='newProcessDefPerson.jsp'"> 
          </td> 
        </tr> 
      </table> 
      <!-- 操作 end --> 
    </td> 
    <!-- 列表 end --> 
  </tr> 
</table> 
<%@ include file="../include/jsp/bottom.jsp" %>
</BODY>
</HTML>
<%
        }
        catch(Exception e) {
            logger.error("", e);
            response.sendRedirect("../error.jsp");
        }
		finally {
			rollPage.close();
		}
    }
%>