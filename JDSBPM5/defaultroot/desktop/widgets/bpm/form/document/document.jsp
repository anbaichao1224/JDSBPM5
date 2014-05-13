<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<HTML>
<HEAD>

<TITLE>正文编辑</TITLE>
</HEAD>
<script type="text/javascript" src="<%=request.getContextPath()%>/desktop/widgets/bpm/form/document/js/document.js"></script>
<BODY onLoad='javascript:TANGER_OCX_OpenDoc();' onbeforeunload='javascript:winClose()'>
<center>
<FORM id="myForm" METHOD="POST" ENCTYPE="multipart/form-data" ACTION="">
<style>
BODY {	MARGIN: 0px 0px 0px;}
</style>
<table width=100% height=100% border=0 cellpadding=0 cellspacing=0 style="border:0px #9dc2db solid">
 
<tr width=100%>
<td width=100%>	
<script type="text/javascript">
	var saveAs = false;
	var cuser;
	var username = '<ww:property value="person.Name"/>';
	//alert(username);
	var personid = '<ww:property value="person.ID"/>';
	var context='<%=request.getContextPath()%>/';
	var contextfile = '<ww:property value="filePath"/>';
	//alert(contextfile);
	//var contextfile = 'http://127.0.0.1/';
	var filename = getDate()+".doc";
	var uuid = '<ww:property value="docid"/>';
	var activityInstId =  '<ww:property value="activityInstId"/>';
	if(uuid != null && uuid.length>1 )
	{
		saveAs = true; //有正文
	}

	TANGER_OCX_Load();	
</script>
	
	<script language="JScript" for=TANGER_OCX event="OnDocumentClosed()">
		
		TANGER_OCX_OnDocumentClosed();
	</script>
	<script language="JScript" for=TANGER_OCX event="OnDocumentOpened(TANGER_OCX_str,TANGER_OCX_obj)">
		TANGER_OCX_OnDocumentOpened(TANGER_OCX_str,TANGER_OCX_obj);
	</script>
	
</td>
</tr></table>	
</FORM>
</center>
</BODY>
</HTML>