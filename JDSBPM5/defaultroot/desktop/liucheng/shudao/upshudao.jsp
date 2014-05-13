<%@ page contentType="text/html; charset=gb2312"%>
<%@ page import="net.itjds.bpm.engine.*"%>
<%@ page import="net.itjds.oa.*"%>

<%
	    String ACTIVITY_ID = request.getParameter("ACTIVITY_ID");
		
		WorkflowClientService client = null;
		AdminService admin = null;
		try {
			int nResult ;
			client = OAUtil.getClient(request);
			admin = OAUtil.getAdmin(client);
			if (admin == null) {
				request.setAttribute("errorMsg", "AdminService 没有配置！");
				request.getRequestDispatcher("/bpm/admin/error.jsp").forward(request, response);
				return;
			}
			admin.beginTransaction();
			nResult = OAUtil.performUpdate(request, ACTIVITY_ID);
			if(nResult == -1) {
				admin.rollbackTransaction();
				request.setAttribute("errorMsg", "更新失败！");
				request.getRequestDispatcher("/bpm/admin/error.jsp").forward(request, response);
				return;
			}
			admin.commitTransaction();
		} catch (Exception e) {
			admin.rollbackTransaction();
			request.setAttribute("errorMsg", e.getMessage());
			request.setAttribute("Throwable", e);
			request.getRequestDispatcher("/bpm/admin/error.jsp").forward(request, response);
			return;
		}
		
		response.sendRedirect("/desktop/liucheng/shudao/shudao.jsp?ActivityInstId=" + ACTIVITY_ID);
%>