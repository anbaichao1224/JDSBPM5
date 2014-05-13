<%
String contextpath = request.getContextPath() + "/";
%>
<%@ page language="java"  pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>USM应用资源管理系统</title>
<style type="text/css">
</style>
	<script type="text/javascript">
		var context = "/";
	</script>
	<script type="text/javascript" src="<%=path%>js/extAll.js"></script>
    <script type="text/javascript">
  /* Ext.onReady(function () {  
    getMsg();  
});  
function getMsg() {  
    Ext.Ajax.request({
    	url:"/usm/ajaxGetMsg.action", 
    	callback:function (options, success, response) {  
        if (success) {  
            //Ext.DomHelper.append(Ext.get("main"), response.responseText, true); 
            alert('成功'); 
        }  
        getMsg();  
    }});  
}  
function putMsg() {  
    Ext.Ajax.request({url:"/usm/ajaxPuttMsg.action", params:{message:document.getElementById("message").value}});  
}*/
	loadMsg();
　　function loadMsg(){
　　　　　　Ext.Ajax.request({ 
　　　　　　　　url: '/usm/ajaxGetMsg.action', 
　　　　　　　　success: function(){
					alert('成功');
				}, 
　　　　　　　　method: 'POST'
　　　　　}); 
	//setTimeout(loadMsg, 1000 * 6);

　　　　}　　 
    </script>
</head>
<body topmargin="0" leftmargin="0" background="/usm/images/bj3.jpg">
 <div id="main"></div><!-- 显示聊天记录的区域 -->  
        username:  
        <input type="text" name="username" />  
        <br>  
        message:  
        <input type="text" id="message">  
        <br>  
        <input type="button" value="submit" onclick="putMsg()">  


</body>
</html>
