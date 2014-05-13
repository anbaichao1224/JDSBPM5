<%
/**
 *    $RCSfile: ParticipantSelectList.jsp,v $
 *    $Author: liyang $
 *    $Revision: 1.2 $
 *    $Date: 2011/08/03 09:38:58 $
 */
%>

<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../include/jsp/global.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="net.itjds.common.web.ColRollPage" %>
<%@ page import="net.itjds.common.util.StringUtility" %>

<%@ page import="net.itjds.bpm.engine.database.right.*" %>

<%
    try {
	String order = request.getParameter("order");
    ColRollPage rollPage = new ColRollPage(request);
	String orderByStr = "";
	orderByStr = " ORDER BY SELECTNAME";

    DbParticipantSelectManager participantMgr = DbParticipantSelectManager.getInstance();
    List participantList = participantMgr.loadByWhere(orderByStr);
//    DbParticipantSelect select = (DbParticipantSelect) participantList.get(0);
	Iterator participantIterator = rollPage.rollAction(participantList, PERPAGESIZE);
%>

<HTML>
<HEAD>
<TITLE> BPM Engine Admin </TITLE>
<link href="../include/css/style.css" rel="stylesheet" type="text/css">
<SCRIPT LANGUAGE="JavaScript" src="../include/js/jcommon.js"></SCRIPT>
</HEAD>
<SCRIPT LANGUAGE="JavaScript">
<!--
function delParticipant(ParticipantSelect_ID) {
   var ret = confirm("您是否确定要删除此公式？");
   if(ret == true) {
	 
      window.location.href = "./DeleteParticipantSelect.jsp?ParticipantSelect_ID=" + ParticipantSelect_ID;
   }
}
//-->
</SCRIPT>
<BODY   LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0> 

<TABLE width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
  <tr> 
    <td height="8" bgcolor="E3E8F8"> </td>
  </tr>
  <tr> 
    <!-- 列表 begin -->
    <td height="500" align="center" valign="top"> <TABLE width="100%" border=0 align=center cellspacing="3">
        <tr height="21" valign="bottom"> 
          <td width="500" class="lin3"><font color="FB4303">■ 当前位置 》公式模版管理 </font></td> 
        </tr> 
      </table> 
      <br> 
      
        <TABLE width="100%" border=0 cellPadding=3 cellSpacing=0 class=tr>
        <form name="frmList" method="post">
        <input type="hidden" name="formids" value="">
          <tr align=center bgColor=#E3E8F8>
            <td height="21" width="5%" class="td" bgcolor="E3E8F8">编号</td> 
            <td width="24%" height="21" bgcolor="E3E8F8" class="td">公式名称</td>
            <td width="24%" height="21" bgcolor="E3E8F8" class="td">公式描述</td>
            <td width="35%" bgcolor="E3E8F8" class="td">公式</td>
            <td width="12%" bgcolor="E3E8F8" class="td">功能</td>
          </tr> 
        <% 
          int count=0;
          while(participantIterator.hasNext() && count < PERPAGESIZE) {
          try {
                DbParticipantSelect participant = (DbParticipantSelect) participantIterator.next();
                String id = participant.getParticipantSelectId();
                String name = StringUtility.escapeHTMLSpecial(participant.getSelectName());
				String desc = StringUtility.escapeHTMLSpecial(participant.getSelectDesc());
				String formula = StringUtility.escapeHTMLSpecial(participant.getFormula());
				formula = StringUtility.replace(formula, "<br>", "\n");
				String formulaDisplay = formula;
				//列表显示的时候忽略换行
				int newLineIndex = formula.indexOf("\n");
  				if(newLineIndex != -1 ) {
  				   formulaDisplay = formula.substring(0, newLineIndex) + "...";
  				}
  				if(formulaDisplay.length() > 30) {
  				    formulaDisplay = formulaDisplay.substring(0, 30) + "...";
  				}
				count++;
        %>
          <tr height="22"> 
            <td class="td" align="center"><%=count + (rollPage.getCurPage()-1)*PERPAGESIZE %></td> 
            <td class="td" align="left">&nbsp;<%=name %></td> 
            <td align="left" class="td">&nbsp;<%=desc %></td>
            <td align="left" class="td" title="<%=formula%>">&nbsp;<%=formulaDisplay%></td>
            <td align="center" class="td"><a href="ModifyParticipantSelect.jsp?ParticipantSelect_ID=<%=id %>" class="A_1">编辑</a>&nbsp;&nbsp;<a href="javascript:delParticipant('<%=id %>');" class="A_1">删除</a></td>
          </tr> 
        <%
            }catch(Exception e) {
            	//e.printStackTrace();
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
      <table width="750" border="0" cellspacing="0" cellpadding="0" > 
        <tr> 
          <td height="5" colspan="2"> </td> 
        </tr> 
        <tr align="right"> 
          <td height="25"  valign="bottom"> 
          </td> 
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
           <TD align=center height="20">&nbsp;</TD>
        </TR>
        <tr align="center"> 
          <td height="50" width="100%">
            <input name="Submit223" type="button" class="inputbutton" style="cursor:hand;" value="新 建" onclick="window.location='NewParticipantSelect.jsp'"> 
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
        catch(Exception ee) {
        	//ee.printStackTrace();
            logger.error("", ee);
            response.sendRedirect("../error.jsp");
        }

%>