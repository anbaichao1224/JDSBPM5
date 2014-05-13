<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://www.opensymphony.com/oscache" prefix="cache" %>
<!--     
  content:登陆后首页    
-->

<%
String contextpath = request.getContextPath() + "/";
%>
<html>
<head>
	<SCRIPT LANGUAGE="JavaScript" type="text/javascript">
		var context='<%=contextpath%>';
	    var username = '<s:property value="username"/>';
	</SCRIPT>
    <title>共性办公应用系统后台管理 USM</title>
     <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
     <link rel="stylesheet" type="text/css" href="<%=contextpath%>usm/css/menus.css" />
     <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
     <script src='<%=contextpath%>js/ext/ext-all.js'></script>
     <script src='<%=contextpath%>js/prototype.js'></script>
   <script type="text/javascript" src="<%=contextpath%>usm/resources/examples.js"></script>
     <script type="text/javascript" src="<%=contextpath%>usm/js/resources-index.js" defer="defer"></script>
	  <script type="text/javascript" src="<%=contextpath%>usm/js/displayAction1.js" defer="defer"></script>
	 <script src='<%=contextpath%>js/JDS.js'></script>
  


</head>



<body> 
<div id="header">
    <div style="float:left;margin:5px;" class="x-small-editor">
		<div id="container">
		    <div id="toolbar">
			    <div id="c" style="text-align: left;
									margin: 0px;
									float: left;
									display:inline;
									left:0;
									top:0;">
				    
					 <img src="<%=contextpath%>usm/images/index2.png" alt="应用资源管理" width=260 height=69/>
					 <s:if test="username=='webmaster'">
			     	 <a href="#"onclick="javascript:tree_refreshSystem('<%=contextpath%>usm/systemLeftMenus.do?menuid=system')">
                     <img src="<%=contextpath%>usm/images/01.png" alt="应用资源管理" onclick="" onmouseover="this.style.cursor='pointer'" onmouseout="this.style.cursor='normal'"  border="0"/></a>
					  <a href="#"onclick="javascript:tree_refreshSystem('<%=contextpath%>usm/systemLeftMenus.do?menuid=systemtool')">
                     <img src="<%=contextpath%>usm/images/02.jpg" alt="系统管理" onclick="" onmouseover="this.style.cursor='pointer'" onmouseout="this.style.cursor='normal'"  border="0"/></a>
				
					 </s:if>
					 <a href="javascript:tree_refreshSystem('<%=contextpath%>usm/systemLeftMenus.do?menuid=usm');">
					 <img src="<%=contextpath%>usm/images/02.png" alt="组织身份管理" onclick="" onmouseover="this.style.cursor='pointer'" onmouseout="this.style.cursor='normal'"  border="0"/></a>
					 <s:if test="username!='webmaster'">
					 <a href="javascript:tree_refreshSystem('<%=contextpath%>usm/systemLeftMenus.do?menuid=right&sysid=<s:property value="sysid"/>');">
					 <img src="<%=contextpath%>usm/images/03.png" alt="权限管理" onmouseover="this.style.cursor='pointer'" onmouseout="this.style.cursor='normal'"  border="0"/></a>
					 </s:if>
					 
					 <!--  
					 <a href="javascript:tree_refreshSystem('<%=contextpath%>usm/systemLeftMenus.do?menuid=report&sysid=<s:property value="sysid"/>');">
					 <img src="<%=contextpath%>usm/images/07.png" alt="报表管理" onmouseover="this.style.cursor='pointer'" onmouseout="this.style.cursor='normal'"  border="0"/></a>
					 <a href="javascript:tree_refreshSystem('<%=contextpath%>usm/systemLeftMenus.do?menuid=chart&sysid=<s:property value="sysid"/>');">
					 <img src="<%=contextpath%>usm/images/08.png" alt="图表管理" onmouseover="this.style.cursor='pointer'" onmouseout="this.style.cursor='normal'"  border="0"/></a>
                     <a href="javascript:tree_refreshSystem('<%=contextpath%>usm/systemLeftMenus.do?menuid=sso&sysid=<s:property value="sysid"/>');">
					 <img src="<%=contextpath%>usm/images/06.png" alt="SSO管理" onmouseover="this.style.cursor='pointer'" onmouseout="this.style.cursor='normal'"  border="0"/></a>
                    <a href="javascript:tree_refreshSystem('<%=contextpath%>usm/systemLeftMenus.do?menuid=monitor');">
					 <img src="<%=contextpath%>usm/images/10.png" alt="监控管理" onmouseover="this.style.cursor='pointer'" onmouseout="this.style.cursor='normal'"  border="0"/></a>
                   
                     <s:if test="username!='webmaster'">
                     <a href="javascript:refreshCache();">
					 <img src="<%=contextpath%>usm/images/04.png" alt="缓存同步" onmouseover="this.style.cursor='pointer'" onmouseout="this.style.cursor='normal'"  border="0"/></a>
                     </s:if>
                     -->
					 <a href="/usmlogin.jsp">
					 <img src="<%=contextpath%>usm/images/09.png" alt="重新登入" onmouseover="this.style.cursor='pointer'" onmouseout="this.style.cursor='normal'"  border="0"/></a>

				</div>
		    </div>
	    </div>	
  </div>
</div>
</body>
<script>
function refreshCache(){

	var evalJs = function(o){
	    Ext.Msg.updateText('缓存同步成功请刷新页面！');
    }
    Ext.Msg.show({
	    title:'缓存同步',
	      msg:'刷新当前系统缓存',
	  buttons: Ext.Msg.OK,
			   fn: function(){
			     Ext.Msg.alert('缓存同步', '正在提交请稍候......');
			   	 JDS.ajax.Connection.LoadJsonFromUrl( '/usm/commitCache.action', evalJs, 'subSystemId=');
			   },
	   		  icon: Ext.MessageBox.QUESTION
   });

}
</script>
</html>
