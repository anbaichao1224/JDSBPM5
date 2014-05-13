<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" %>

<%
String menu = request.getAttribute("menuid").toString();
String sysid = (String)request.getAttribute("sysid");

if(menu.equals("system")){
out.println("[");
out.println("{'id':1,expanded:true,'text':'应用资源管理','cls':'forum','iconCls':'icon-forum','children':");
out.println("[{'id':11,'text':'应用资源列表','icon':'/usm/img/grid.png',url:'/usm/systemAll.do','leaf':true},");
out.println("{'id':12,'text':'应用资源注册','icon':'/usm/img/plugin.gif',url:'resources/systemInsert.jsp','leaf':true}]},");	
out.println("{'id':2,expanded:true,'text':'模块资源管理','cls':'forum','iconCls':'icon-forum','children':");
out.println("[{'id':21,'text':'模块资源列表','icon':'/usm/img/grid.png',url:'/usm/moduleAll.do','leaf':true},");
out.println("{'id':22,'text':'模块资源注册','icon':'/usm/img/plugin.gif',url:'resources/resourcesInsert.jsp','leaf':true}]},");
out.println("{'id':3,expanded:true,'text':'组织身份管理','cls':'forum','iconCls':'icon-forum','children':");
out.println("[{'id':23,'text':'人员与部门管理','icon':'/usm/img/server.gif',url:'/usm/person-index.jsp','leaf':true},");
out.println("{'id':24,'text':'组织管理','icon':'/usm/img/cache.gif',url:'/usm/depart-index.jsp','leaf':true}]},");	
out.println("{'id':4,expanded:true,'text':'角色管理','cls':'forum','iconCls':'icon-forum','children':");
out.println("[{'id':25,'text':'角色管理','icon':'/usm/img/role.gif',url:'/usm/role-index.jsp?sysid="+16416+"&type=manager','leaf':true}]},");
out.println("{'id':5,expanded:true,'text':'应用菜单管理','cls':'forum','iconCls':'icon-forum','children':");
out.println("[{'id':26,'text':'应用菜单','icon':'/usm/img/application.png',url:'/usm/resources/applicationManager.jsp?sysid="+16416+"','leaf':true}");
out.println("]},");
out.println("{'id':6,expanded:true,'text':'权限管理','cls':'forum','iconCls':'icon-forum','children':");
out.println("[{'id':36,'text':'角色授权','icon':'/usm/img/role.gif',url:'/usm/role-index.jsp?sysid="+16416+"&type=right','leaf':true}]}");
out.println("]");	


}else if(menu.equals("usm")){
out.println("[");
out.println("{'id':1,expanded:true,'text':'人员管理','cls':'forum','iconCls':'icon-forum','children':");
//out.println("[{'id':110,'text':'应用系统','icon':'/usm/img/grid.png',url:'/usm/systemAll.do','leaf':true},");
out.println("[{'id':11,'text':'人员与部门管理','icon':'/usm/img/server.gif',url:'/usm/person-index.jsp','leaf':true}]},");
out.println("{'id':2,expanded:true,'text':'操作日志','cls':'forum','iconCls':'icon-forum','children':");
out.println("[{'id':122,'text':'日志查看','icon':'/usm/img/server.gif',url:'/usm/logindex.jsp','leaf':true}]");
//out.println("{'id':12,'text':'组织管理','icon':'/usm/img/cache.gif',url:'/usm/depart-index.jsp','leaf':true}]");	
out.println("}]");	
}else if(menu.equals("right")){
out.println("[");
out.println("{'id':1,expanded:true,'text':'角色管理','cls':'forum','iconCls':'icon-forum','children':");
//out.println("[{'id':11,'text':'岗位管理','icon':'/usm/img/position.gif',url:'/usm/position-index.jsp?sysid="+sysid+"&type=manager','leaf':true},");
//out.println("{'id':12,'text':'职务管理','icon':'/usm/img/duty.gif',url:'/usm/duty-index.jsp?sysid="+sysid+"&type=manager','leaf':true},");
out.println("[{'id':13,'text':'创建角色','icon':'/usm/img/role.gif',url:'/usm/role-index.jsp?sysid="+sysid+"&type=manager','leaf':true}]},");
//out.println("{'id':14,'text':'用户组管理','icon':'/usm/img/usergroup.gif',url:'/usm/usergroup-index.jsp?sysid="+sysid+"&type=manager','leaf':true}]},");	
//}else if(menu.equals("module"))
//out.println("{'id':2,expanded:true,'text':'应用菜单管理','cls':'forum','iconCls':'icon-forum','children':");
//out.println("[{'id':21,'text':'应用菜单','icon':'/usm/img/application.png',url:'/usm/resources/applicationManager.jsp?sysid="+sysid+"','leaf':true}");
//out.println("]},");
//}else if(menu.equals("module"))
out.println("{'id':3,expanded:true,'text':'权限管理','cls':'forum','iconCls':'icon-forum','children':");
//out.println("[{'id':31,'text':'模块授权','icon':'/usm/img/module.gif',url:'/usm/moduleright-index.jsp?sysid="+sysid+"','leaf':true},");
//out.println("{'id':32,'text':'人员授权','icon':'/usm/img/server.gif',url:'/usm/personright-index.jsp?sysid="+sysid+"','leaf':true},");
//out.println("{'id':33,'text':'部门授权','icon':'/usm/img/cache.gif',url:'/usm/departright-index.jsp?sysid="+sysid+"','leaf':true},");
//out.println("{'id':34,'text':'职务授权','icon':'/usm/img/duty.gif',url:'/usm/duty-index.jsp?sysid="+sysid+"&type=right','leaf':true},");
//out.println("{'id':35,'text':'岗位授权','icon':'/usm/img/position.gif',url:'/usm/position-index.jsp?sysid="+sysid+"&type=right','leaf':true},");
out.println("[{'id':36,'text':'角色授权','icon':'/usm/img/role.gif',url:'/usm/role-index1.jsp?sysid="+sysid+"&type=right','leaf':true}]},");
out.println("{'id':4,expanded:true,'text':'人员管理','cls':'forum','iconCls':'icon-forum','children':");
out.println("[{'id':37,'text':'权限查看','icon':'/usm/img/role.gif',url:'/usm/person-index1.jsp','leaf':true}]}");
//out.println("{'id':37,'text':'用户组授权','icon':'/usm/img/usergroup.gif',url:'/usm/usergroup-index.jsp?sysid="+sysid+"&type=right','leaf':true}]}");
out.println("]");
	
}else if(menu.equals("sso")){
out.println("[");
out.println("{'id':4,expanded:true,'text':'单点登录管理','cls':'forum','iconCls':'icon-forum','children':");
out.println("[{'id':41,'text':'单点登录列表','icon':'/usm/img/module.gif',url:'/usm/sso/sso-index.jsp?sysid="+sysid+"','leaf':true}]");
//out.println("{'id':42,'text':'单点登录注册','icon':'/usm/img/105(2).gif',url:'/usm/personright-index.jsp?sysid="+sysid+"','leaf':true}]");
out.println("}]");
}else if(menu.equals("report")){
out.println("[");
out.println("{'id':4,expanded:true,'text':'报表管理','cls':'forum','iconCls':'icon-forum','children':");
out.println("[{'id':41,'text':'报表列表','icon':'/usm/img/module.gif',url:'/usm/report/report-index.jsp?sysid="+sysid+"','leaf':true}]");
//out.println("{'id':42,'text':'单点登录注册','icon':'/usm/img/105(2).gif',url:'/usm/personright-index.jsp?sysid="+sysid+"','leaf':true}]");
out.println("}]");
}else if(menu.equals("chart")){
out.println("[");
out.println("{'id':4,expanded:true,'text':'图表管理','cls':'forum','iconCls':'icon-forum','children':");
out.println("[{'id':41,'text':'图表列表','icon':'/usm/img/module.gif',url:'/usm/chart/chart-index.jsp?sysid="+sysid+"','leaf':true}]");
//out.println("{'id':42,'text':'单点登录注册','icon':'/usm/img/105(2).gif',url:'/usm/personright-index.jsp?sysid="+sysid+"','leaf':true}]");
out.println("}]");
}else if(menu.equals("monitor")){
out.println("[");
out.println("{'id':4,expanded:true,'text':'运行监控','cls':'forum','iconCls':'icon-forum','children':");
out.println("[{'id':41,'text':'服务器监控','icon':'/usm/img/module.gif',url:'/usm/monitor/monitor-index.jsp?sysid="+sysid+"','leaf':true},");
out.println("{'id':42,'text':'数据库监控','icon':'/usm/img/sort.gif',url:'/bpm/admin/engine/connectionStatistic.jsp','leaf':true},");
out.println("{'id':43,'text':'流程监控','icon':'/usm/img/chakan.jpg',url:'/bpm/admin/psd/ProcessList.jsp','leaf':true},");
out.println("{'id':44,'text':'系统缓存监控','icon':'/usm/img/cache.gif',url:'/bpm/admin/engine/cacheAdmin.jsp?sysid="+sysid+"','leaf':true}]");
out.println("}]");
}else if(menu.equals("systemtool")){
out.println("[");
out.println("{'id':4,expanded:true,'text':'系统工具','cls':'forum','iconCls':'icon-forum','children':");
out.println("[{'id':51,'text':'数据库表管理','icon':'/usm/img/module.gif',url:'/fdt/mapdao/tableShow.jsp','leaf':true},");
out.println("{'id':52,'text':'总线注册资源管理','icon':'/usm/img/sort.gif',url:'/main.jsp?esbkey=ViewManage','leaf':true},");
out.println("{'id':53,'text':'权限公式模板管理','icon':'/usm/img/chakan.jpg',url:'/bpm/admin/participantselect/ParticipantSelectList.jsp','leaf':true},");
out.println("{'id':54,'text':'流程定义人员管理','icon':'/usm/img/cache.gif',url:'/bpm/admin/processDefPerson/processDefPersonList.jsp','leaf':true},");
out.println("{'id':56,'text':'流程服务管理','icon':'/usm/img/bpmserver.gif',url:'/bpm/admin/engine/engineAdmin.jsp','leaf':true}]");

out.println("}]");

}
%>