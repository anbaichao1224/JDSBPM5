<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>

<% 
    rtx.RTXSvrApi rtxApi = new rtx.RTXSvrApi(); 
    //String account = (String)session.getAttribute("loginName_");//����ͨ��һ��session������õ�ǰ�û���½���Ƶ�
    //String account = (String)request.getParameter("user");
    String ip = rtxApi.getServerIP(); 
    //String key= rtxApi.getSessionKey(account); //���GetSessionKey�ķ�������RTX SDK����JAVA����RTXSvrApi.java������ 
%> 
<html> 
<script language="JavaScript" >
function RtxSycn(){ 
	var accounts = '<ww:property value="performPersonIdList"/>';
	var keys = '<ww:property value="keystr"/>';
	var accountary = new Array();
	var keyary = new Array();
	accountary = accounts.split(",");
	keyary = keys.split(",");
	alert(accountary.length);
	for(var i=0;i<accountary.length;i++){
		 try{ 
         
        var account = accountary[i];
        var key = keyary[i];
        alert(key);
        
        var ip="<%=ip%>"; 

       var RTXCRoot = RTXAX.GetObject("KernalRoot");    //�ͻ���SDK 
  
        RTXCRoot.LoginSessionKey(ip,8100,account,key); 
       }catch(e){ 
        //alert(e.description+" RTXδ�ܳɹ���½�������Ի������Ա��ϵ��"); 
       } 
	}
   window.close();
} 
</script> 
<body> 
    <OBJECT id="RTXAX" data="data:application/x-oleobject;base64,fajuXg4WLUqEJ7bDM/7aTQADAAAaAAAAGgAAAA==" classid="clsid:5EEEA87D-160E-4A2D-8427-B6C333FEDA4D" VIEWASTEXT></OBJECT> 
    <script>RtxSycn();</script>
</body> 
</html> 