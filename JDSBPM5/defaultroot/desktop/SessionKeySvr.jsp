<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>

<% 
    rtx.RTXSvrApi rtxApi = new rtx.RTXSvrApi(); 
    //String account = (String)session.getAttribute("loginName_");//我是通过一个session类来获得当前用户登陆名称的
    //String account = (String)request.getParameter("user");
    String ip = rtxApi.getServerIP(); 
    //String key= rtxApi.getSessionKey(account); //这个GetSessionKey的方法就在RTX SDK下面JAVA例子RTXSvrApi.java里面有 
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

       var RTXCRoot = RTXAX.GetObject("KernalRoot");    //客户端SDK 
  
        RTXCRoot.LoginSessionKey(ip,8100,account,key); 
       }catch(e){ 
        //alert(e.description+" RTX未能成功登陆，请重试或与管理员联系！"); 
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