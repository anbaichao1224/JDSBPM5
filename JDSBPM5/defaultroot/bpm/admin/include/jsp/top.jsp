<%
/**
 *    $RCSfile: top.jsp,v $
 *    $Author: liyang $
 *    $Revision: 1.2 $
 *    $Date: 2011/08/03 09:39:20 $
 */
%>

<%
/**
 *    JRE Check Script
 */
%>
<%@ page contentType="text/html;charset=gb2312"%>
<SCRIPT LANGUAGE="Javascript">
    var javawsInstalled = 0;
    isIE = "false";
    
    if (navigator && navigator.mimeTypes) {
        isIE = "true";
    }

</SCRIPT>
<SCRIPT LANGUAGE="VBScript">
    on error resume next
    If isIE = "true" Then
        If Not(IsObject(CreateObject("JavaWebStart.IsInstalled"))) Then
            javawsInstalled = 0
        Else
            javawsInstalled = 1
        End If
    End If
</SCRIPT>
<SCRIPT LANGUAGE="Javascript">
    function _checkJRE() {
        if(javawsInstalled){
            window.location = '<%=contextPath %>/bpm/admin/pdt/PDTStarter.jsp';
        }else{
            if(confirm("����Ҫ��װһ���������֧�����̶��幤�ߵ����С�\nҪ�������ظ������")) {
                window.location='<%=contextPath %>/bpm/admin/pdt/j2re-1_4_2_03-windows-i586-p.exe';
            }
        }
    }
</SCRIPT>

<TABLE WIDTH=770 BORDER=0 align="center" CELLPADDING=0 CELLSPACING=0 background="<%=contextPath %>/bpm/admin/images/welcome_2.gif" bgcolor="#FFFFFF"> 
  <TR> 
    <TD> <IMG SRC="<%=contextPath %>/bpm/admin/images/PDT_Title.jpg"  ALT=""></TD> 
  </TR> 
</TABLE> 
<table width="770" border="0" align="center" cellpadding="0" cellspacing="0" background="<%=contextPath %>/bpm/admin/images/fdt_index_2.gif"> 
  <tr> 
    <td height="21" align="left">&nbsp;&nbsp;
    <a href="<%=contextPath %>/bpm/admin/welcome.jsp" class="A_Title">��ҳ</a>&nbsp;&nbsp;
	<!--
    <a href="<%=contextPath %>/bpm/fdt/jsp/database/oriDatabaseExportFrame.jsp" class="A_Title">���ݿ⵼��</a>&nbsp;&nbsp;
	<a href="<%=contextPath %>/bpm/fdt/jsp/database/assignDatabaseTableFrame.jsp" class="A_Title">ҵ������</a>&nbsp;&nbsp;
	<a href="<%=contextPath %>/bpm/fdt/jsp/database/businessDatabaseTableZHENFrame.jsp"
	class="A_Title">��Ӣ�Ķ���</a>&nbsp;&nbsp;
	-->
	<%if(adminRole.equals(Constants.ADMIN_ROLE_SUPERADMIN)) {%>
    <a href="<%=contextPath %>/bpm/admin/engine/engineAdmin.jsp" class="A_Title">�������</a>&nbsp;&nbsp;
    <a href="<%=contextPath %>/bpm/admin/processDefPerson/processDefPersonList.jsp" class="A_Title">���̶�����Ա</a>&nbsp;&nbsp;
    <a href="<%=contextPath %>/bpm/admin/fdtPerson/fdtPersonList.jsp" class="A_Title">�������Ա</a>&nbsp;&nbsp;
    <a href="/bpm/admin/participantselect/ParticipantSelectList.jsp" class="A_Title">��ʽģ�����</a>&nbsp;&nbsp;
	
    <%}%>
    <%if(pdtRole.equals(Constants.ADMIN_ROLE_BPD)) {%>
	<!--
    <a href="javascript: _checkJRE();" class="A_Title">���̶��幤��</a>&nbsp;&nbsp;
	-->
    <%}%>
    <%if(fdtRole.equals(Constants.ADMIN_ROLE_FDT)) {%>
    <a href="<%=contextPath %>/servlet/form/FormListServlet" class="A_Title">����ƹ���</a>&nbsp;&nbsp;
    <%}%>
	<%if(!adminRole.equals(Constants.ADMIN_ROLE_SUPERADMIN)) {%>
    <a href="/bpm/admin/psd/ProcessList.jsp" class="A_Title">���̼���赼</a>&nbsp;&nbsp;
	<%}%>
    </td>
    <td width="50" align="right"><a href="<%=contextPath %>/bpm/admin/logout.jsp" class="A_Title">ע��</a>&nbsp;&nbsp;&nbsp;</td>
  </tr> 
</table>