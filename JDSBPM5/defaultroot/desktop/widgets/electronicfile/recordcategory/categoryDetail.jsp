<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%@page import="net.itjds.j2ee.util.UUID" %>
<%
String contextpath = request.getContextPath() + "/";
String uuid = (new UUID()).toString();
 %>
<HTML>
<HEAD>

<TITLE>新增会议</TITLE>
<style type="text/css">
		<!--
body {font-family: "宋体", "Arial"; font-size: 9pt;background-color:#dfe8f6}
td {font-family: "宋体", "Arial"; font-size: 9pt} 
.check{color:#FF0000}
-->
		</style>
<script type="text/javascript">
	var context="/";
</script>
 
<script type="text/javascript" src="/js/extAll.js"></script>
<script type="text/javascript">
Ext.onReady(function(){
	var uid = '<%=request.getParameter("uuid")%>';
	new Ext.Panel({
						renderTo:Ext.getBody(),
						width:Ext.getBody().getWidth(),
						height:Ext.getBody().getHeight(),
						region:'left',
                            id:'cc',
                            html:'<iframe id="views" frameborder="0" src="/category_findById.action?rbean.uuid='+uid+'" height=100% width=100%></iframe>',
                            tbar:new Ext.Toolbar(
							{
				items : [
				         {
				        	 text : '新增子目录',
				        	 id : 'newmodelbtn',
				        	 handler : function() {
				        	 	alert("1");
				        	 	location.href="/desktop/widgets/electronicfile/recordcategory/addCategory.jsp?pid="+uid;
				        	}
				         }, {
				        	 text : '删除',
				        	 id : 'delbtn',
				        	 handler : function() {
				        	 deleteFile(Ext.getCmp('room-list'));
				        	 // setTimeout("refFileGridById()",500);
				        	 alert("删除成功");
				        	 store.reload();
				        	 }
				         } ]
			})
			})
});
</script>
</HEAD>
<BODY>

<center>
<div id="ceshi"></div>
</center>
</BODY>
 
</HTML>