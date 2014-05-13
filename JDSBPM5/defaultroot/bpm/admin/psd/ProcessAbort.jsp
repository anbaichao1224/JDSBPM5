<%@ page contentType="text/html; charset=gb2312"%>
<%@ page import="net.itjds.bpm.engine.*"%>
<%@ page import="net.itjds.bpm.client.*"%>
<%@ page import="java.util.*"%>

<%
		String[] processInstId = request.getParameterValues("processInstId");
		if (processInstId == null) {
			response.sendRedirect("/bpm/admin/psd/ProcessList.jsp");
			return;
		}
		ProcessInst processInst = null; 
		ActivityInst activityInst = null;
		WorkflowClientService client = null;
		AdminService admin = null;
		BPMSessionFactory sessionFactory = new BPMSessionFactory();
		try {
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
					if (admin == null) {
						request.setAttribute("errorMsg", "AdminService not configged!");
						request.getRequestDispatcher("/bpm/admin/error.jsp").forward(request, response);
						return;
					}
				} else {
					continue;
				}
				processInst = admin.getProcessInst(Id);
				admin.beginTransaction();
				admin.abortProcessInst(Id, null);
				admin.commitTransaction();
			}
		} catch (Exception e) {
			if (admin != null) {
				admin.rollbackTransaction();
			}
			request.setAttribute("Throwable", e);
			request.getRequestDispatcher("/bpm/admin/error.jsp").forward(request, response);
			return;
		} finally {
		}
		
		response.sendRedirect("/bpm/admin/psd/ProcessList.jsp");
%>