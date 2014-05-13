<%@ page contentType="text/html; charset=gb2312"%>
<%@ page import="net.itjds.bpm.engine.*"%>
<%@ page import="net.itjds.bpm.client.*"%>
<%@ page import="net.itjds.oa.*"%>
<%@ page import="java.util.*"%>
<%@ page import="net.itjds.common.database.*"%>

<%
        String queryString = request.getQueryString();
		String[] processInstId = request.getParameterValues("processInstId");
		if (processInstId == null) {
			processInstId = new String[0];
		}
		
		ProcessInst processInst = null; 
		ActivityInst activityInst = null;
		ConnectionManager connMgr = ConnectionManagerFactory.getInstance().getConnectionManager("bpm");
		WorkflowClientService client = null;
		AdminService admin = null;
		BPMSessionFactory sessionFactory = new BPMSessionFactory();
		try {
			int nResult;
			for(int i=0; i<processInstId.length; i++) {
				String tempStr = processInstId[i];
				StringTokenizer st = new StringTokenizer(tempStr, "|");
				String systemCode = null;
				String Id = null;
				if (st.hasMoreTokens()) {
					Id = st.nextToken();
				}
				if (st.hasMoreTokens()) {
					systemCode = st.nextToken();
				 	client = sessionFactory.getClientService(request, systemCode);
					admin = sessionFactory.getAdminService(client);
                    admin.beginTransaction();
					if (admin == null) {
						request.setAttribute("errorMsg", "AdminService not configged!");
						request.getRequestDispatcher("/bpm/admin/error.jsp").forward(request, response);
						return;
					}
				} else {
					continue;
				}

				processInst = admin.getProcessInst(Id);
				
				List activityInstList = processInst.getActivityInstList();
				for(int j=0; j<activityInstList.size(); j++) {
					activityInst = (ActivityInst) activityInstList.get(j);
				}
				admin.deleteProcessInst(processInst.getProcessInstId(), null);
				admin.commitTransaction();
			}
		} catch (Exception e) {
            e.printStackTrace();
			if (admin != null) {
				admin.rollbackTransaction();
			}
			request.setAttribute("Throwable", e);
			request.getRequestDispatcher("/bpm/admin/error.jsp").forward(request, response);
			return;
		} finally {
		}
		
		response.sendRedirect("/bpm/admin/psd/ProcessList.jsp?" + queryString);
%>