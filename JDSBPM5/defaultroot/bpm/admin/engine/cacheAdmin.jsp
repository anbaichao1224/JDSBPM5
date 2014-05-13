
<%
/**
 *    $RCSfile: cacheAdmin.jsp,v $
 *    $Author: liyang $
 *    $Revision: 1.2 $
 *    $Date: 2011/08/03 09:39:21 $
 */
%>

<%@ page contentType="text/html;charset=gb2312"%>
<%@ include file="../include/jsp/global.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="net.itjds.common.CommonConfig" %>
<%@ page import="net.itjds.bpm.engine.WorkflowServer" %>
<%@ page import="net.itjds.bpm.engine.BPMConstants" %>
<%@ page import="net.itjds.common.cache.Cache" %>
<%@ page import="net.itjds.common.cache.CacheManager" %>
<%@ page import="net.itjds.common.cache.CacheManagerFactory" %>

<%
	boolean cacheEnabled = false;
	Map cacheMap = null;
	int cacheSize=0;

	String contextpath = request.getContextPath()+"/";
%>

<HTML>
<HEAD>
<TITLE> BPM Engine Admin </TITLE>

<link href="<%=contextpath %>bpm/admin/include/css/style.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.style1 {font-size: 16px}
-->
</style>
</HEAD>

<BODY   LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0> 
  <form name="frm" method="post" action="cacheAdmin_do.jsp">
<TABLE width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF"> 
  <tr> 
    <td height="8" bgcolor="E3E8F8"> </td> 
  </tr> 
  <tr> 
    <td height="500" align="left" valign="top"> 
	<TABLE width="100%" height="184" border=0 align=center cellspacing="3">
     
      <tr height="21" valign="bottom">
        <td width="500" height="17" class="lin3"><font color="FB4303">■ 当前位置 》监控管理 》缓存管理</font></td>
      </tr>
      <tr>
      
      
      <tr><td>     <input type="hidden" name="action" value="clearCache">
      
      &nbsp;&nbsp;<input type="submit" name="clearCacheBtn" value="清空" class="inputbutton">
        &nbsp;&nbsp;<input type="button" name="loadWorkflowBtn" value="重载工作流缓存" onClick="window.location='cacheAdmin_do.jsp?action=loadWorkflow'"  class="inputbutton">
	  &nbsp;&nbsp;<input type="button" name="dumpCacheBtn" onClick="window.location='cacheAdmin_do.jsp?action=dumpCache'" value="Dump缓存" class="inputbutton"><br><br>
      </td></tr>
        <%
	 Map<String,CacheManager> cacheManagerMap=CacheManagerFactory.getInstance().getCacheManagerMap();
     Iterator<String> it=cacheManagerMap.keySet().iterator();
  
     for(;it.hasNext();){
        int subCacheSize=0;
      String key=it.next();
       CacheManager subCacheManager=cacheManagerMap.get(key);
      
	     if (true) {
			cacheMap = subCacheManager.getAllCache();
    %>    
     
      <tr>
        <td height="86" align="left" valign="top"><br>
  
      <table width=724 border=0 cellpadding=3 cellspacing=0 class=tr>
        <tr align=center bgcolor=#E3E8F8>
          <td height="21" colspan="2" bgcolor="E3E8F8" class="td">编号</td>
          <td width="211" height="21" bgcolor="E3E8F8" class="td">缓存名称</td>
          <td width="75" height="21" bgcolor="E3E8F8" class="td">缓存容量<br>
            （kb）</td>
          <td width="74" bgcolor="E3E8F8" class="td">缓存周期<br>
            （分）</td>
          <td width="68" bgcolor="E3E8F8" class="td">当前缓存<br>
            （kb）</td>
          <td width="69" bgcolor="E3E8F8" class="td">击中<br>
            （次）</td>
          <td width="74" bgcolor="E3E8F8" class="td">未击中<br>
            （次）</td>
          <td width="59" bgcolor="E3E8F8" class="td">击中率<br>
            （%）</td>
          </tr>
        <%
          int count=0;
          Iterator cacheNameIte = cacheMap.keySet().iterator();
          while (cacheNameIte.hasNext()) {
			count++;
			String cacheName = (String)cacheNameIte.next();
			Cache cache = (Cache)cacheMap.get(cacheName);
			long hits = cache.getCacheHits();
			long misses = cache.getCacheMisses();
			long total = hits + misses;
			long hitPercentage = 100;
			
			if(total != 0) {
				hitPercentage = hits*100 / total;
			}
			cacheSize=cache.getCacheSize()+cacheSize;
			subCacheSize=cache.getCacheSize()+subCacheSize;
        %>
        <tr height="21">
          <td width="18" class="td" align="center"><%=count %></td>
          <td width="21" class="td" align="center"><input type="checkbox" name="cacheName" value="<%=cacheName %>"></td>
          <td class="td" align="left"><%=cacheName %></td>
          <td align="center" class="td"><%=cache.getMaxCacheSize() / 1024 %></td>
          <td align="center" class="td"><%=cache.getMaxLifetime() / net.itjds.common.util.Constants.MINUTE %></td>
          <td align="center" class="td"><%=cache.getCacheSize() / 1024 %></td>
          <td align="center" class="td"><%=hits %></td>
          <td align="center" class="td"><%=misses %></td>
          <td align="center" class="td"><%=hitPercentage %></td>
          </tr>
        <%
	     	}
	  
     
        %>
          
      </table>
      
       <tr height="21" valign="bottom">
        <td width="500" height="17" class="lin3"><font color="FB4303">缓存【<%=key%>】使用共计<%=subCacheSize/1024%>kb</font></td>
      </tr>
    

	</td>
      </tr> 
      <%
	}
	else {
%>
	缓存已停用
<%
	}
	}
%>	
      
      
  <tr><td>缓存共计使用<%=cacheSize/1024%>kb</td></tr>
</table></td> </tr>
</table>
 </form>
<%@ include file="../include/jsp/bottom.jsp" %>
</BODY>
</HTML>
