
<jsp:directive.page import="com.opensymphony.xwork2.ActionContext" />
<jsp:directive.page import="net.itjds.bpm.engine.WorkflowClientService" />
<jsp:directive.page import="net.itjds.bpm.client.ActivityInst" />
<jsp:directive.page import="net.itjds.bpm.client.BPMSessionFactory" />
<jsp:directive.page import="net.itjds.bpm.engine.ConnectInfo" />
<jsp:directive.page import="net.itjds.bpm.engine.BPMSessionHandle" />
<jsp:directive.page import="net.itjds.bpm.engine.BPMException" />
<jsp:directive.page import="net.itjds.bpm.data.mapdao.MapDAODataMap" />
<jsp:directive.page import="net.itjds.common.mapdao.MapDAO" />
<jsp:directive.page import="net.itjds.bpm.client.ProcessInst" />
<jsp:directive.page import="net.itjds.bpm.client.ActivityInstHistory" />
<%
	 /**
	 *    $RCSfile: cacheAdmin_do.jsp,v $
	 *    $Author: liyang $
	 *    $Revision: 1.2 $
	 *    $Date: 2011/08/03 09:39:21 $
	 */
%>

<%@ page contentType="text/html;charset=gb2312"%>
<%@ include file="../include/jsp/global.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="net.itjds.bpm.engine.WorkflowServer"%>
<%@ page import="net.itjds.bpm.engine.BPMConstants"%>
<%@ page import="net.itjds.common.cache.Cache"%>
<%@ page import="net.itjds.common.cache.CacheManager"%>
<%@ page import="net.itjds.common.cache.CacheManagerFactory"%>

<%
	boolean cacheEnabled = true;
	Map cacheMap = new HashMap();

	
	Map<String,CacheManager> cacheManagerMap=CacheManagerFactory.getInstance().getCacheManagerMap();
     Iterator<String> cacheit=cacheManagerMap.keySet().iterator();
  
     for(;cacheit.hasNext();){
      
        String key=cacheit.next();
        CacheManager subCacheManager=cacheManagerMap.get(key);
         cacheMap.putAll(subCacheManager.getAllCache());
     }
      
	
	

	String action = request.getParameter("action");
	if ("clearCache".equals(action) && cacheEnabled) {
		String[] cacheNames = request.getParameterValues("cacheName");
		if (cacheNames != null) {
			for (int i = 0; i < cacheNames.length; i++) {
		Cache cache = (Cache) cacheMap.get(cacheNames[i]);
		if (cache != null) {
			cache.clear();
		}
			}
		}
	} else if ("dumpCache".equals(action) && cacheEnabled) {
		WorkflowServer.getInstance().dumpCache();
	} else if ("loadWorkflow".equals(action)) {

		BPMSessionHandle sessionHandle = BPMSessionFactory
		.getSessionHandle(request);
		BPMSessionFactory sessionFactory = new BPMSessionFactory();
		WorkflowClientService client = sessionFactory.newClientService(
		sessionHandle, "oa");
		client.connect(new ConnectInfo("04", "oaadmin", "aaaaaa"));
		session.setAttribute("sessionHandle", sessionHandle);

		try {

			List<ActivityInst> acList = client.getActivityInstList(
			null, null, null);
			for (int k = 0; k < acList.size(); k++) {
		ActivityInst inst = acList.get(k);
		ProcessInst pinst = inst.getProcessInst();
		List<ActivityInstHistory> hislist = client
				.getActivityInstHistoryListByProcessInst(pinst
				.getProcessInstId(), null);
		for (int h = 0; h < hislist.size(); h++) {
			ActivityInstHistory his = hislist.get(h);

		}

			}
		} catch (BPMException e) {
			e.printStackTrace();
		}

	}

	response.sendRedirect("cacheAdmin.jsp");
%>
