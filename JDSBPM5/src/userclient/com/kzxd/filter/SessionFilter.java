package com.kzxd.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;
import net.itjds.common.org.base.OrgManager;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.common.org.impl.database.DbPerson;

public class SessionFilter implements Filter {

	protected static Log log = LogFactory.getLog("oa", SessionFilter.class);

	public void destroy() {

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession session = request.getSession();
		String path = request.getRequestURI().toString();

		String png = "";
		if (path.length() > 0 && path != null) { // --截取文件名
			int i = path.lastIndexOf(".");
			if (i > -1 && i < path.length()) {
				png = path.substring(i + 1); // --扩展名
			}
		}
		String js = "";
		if (path.length() > 0 && path != null) { // --截取文件名
			int i = path.lastIndexOf(".");
			if (i > -1 && i < path.length()) {
				js = path.substring(i + 1); // --扩展名
			}
		}
		String jpg = "";
		if (path.length() > 0 && path != null) { // --截取文件名
			int i = path.lastIndexOf(".");
			if (i > -1 && i < path.length()) {
				jpg = path.substring(i + 1); // --扩展名
			}
		}
		String css = "";
		if (path.length() > 0 && path != null) { // --截取文件名
			int i = path.lastIndexOf(".");
			if (i > -1 && i < path.length()) {
				css = path.substring(i + 1); // --扩展名
			}
		}
		String doo = "";
		if (path.length() > 0 && path != null) { // --截取文件名
			int i = path.lastIndexOf(".");
			if (i > -1 && i < path.length()) {
				doo = path.substring(i + 1); // --扩展名
			}
		}
		String gif = "";
		if (path.length() > 0 && path != null) { // --截取文件名
			int i = path.lastIndexOf(".");
			if (i > -1 && i < path.length()) {
				gif = path.substring(i + 1); // --扩展名
			}
		}
		String bmp = "";
		if (path.length() > 0 && path != null) { // --截取文件名
			int i = path.lastIndexOf(".");
			if (i > -1 && i < path.length()) {
				bmp = path.substring(i + 1); // --扩展名
			}
		}
		String jsp = "";
		if (path.length() > 0 && path != null) { // --截取文件名
			int i = path.lastIndexOf(".");
			if (i > -1 && i < path.length()) {
				jsp = path.substring(i + 1); // --扩展名
			}
		}
		String ico = "";
		if (path.length() > 0 && path != null) { // --截取文件名
			int i = path.lastIndexOf(".");
			if (i > -1 && i < path.length()) {
				ico = path.substring(i + 1); // --扩展名
			}
		}
		String action = "";
		if (path.length() > 0 && path != null) { // --截取文件名
			int i = path.lastIndexOf(".");
			if (i > -1 && i < path.length()) {
				action = path.substring(i + 1); // --扩展名
			}
		}
		String doc = "";
		if (path.length() > 0 && path != null) { // --截取文件名
			int i = path.lastIndexOf(".");
			if (i > -1 && i < path.length()) {
				doc = path.substring(i + 1); // --扩展名
			}
		}
		log.debug("path: " + path);
		if (!path.equals("/login.jsp") && !path.equals("/login.action")
				&& !path.equals("/selfsave.action")
				&& !path.equals("/_vti_bin/shtml.exe/_vti_rpc")
				&& !path.equals("/_vti_inf.html")
				&& !path.equals("/gwtjAction.action")
				&& !path.equals("/usm/RoleModel.action")
				&& !path.equals("/usm/commitCache.action")
				&& !action.equals("action")
				&& !path.equals("/bpmDataProcessBind.action")
				&& !path.equals("/updateSeting.action")
				&& !path.equals("/bpmView.action")
				&& !path.equals("favicon.ico")
				&& !path.equals("/gwjhAction_findReceivelist.action")
				&& !path.equals("/probe/")
				&& !path.equals("/SwdjAction.action")
				&& !path.equals("/phoneloginAction_Login.action")
				&& !path.equals("/SelectPersonJSDEFBF.action")
				&& !path.equals("/services/OADataBaseService")
				&& !doo.equals("do")
				&& !doc.equals("doc")
				&& !gif.equals("gif")
				&& !gif.equals("bmp")
				&& !gif.equals("jsp")
				&& !path.equals("/usm/info.jsp")
				&& !path.equals("/usm/logon.action")
				&& !path.equals("/usm/images/loginbj.png")
				&& !path.equals("/usm/") && !jpg.equals("jpg")
				&& !path.equals("/usmlogin.jsp")
				&& !path.equals("/usmlogon.action") && !path.equals("/")
				&& !path.equals("/demoInsert.action")
				&& !path.equals("/sublogin.action") && !png.equals("png")
				&& !js.equals("js") && !css.equals("css")
				&& !path.equals("/services/EngineWebService")
				&& !path.equals("/services/ApplicationWebService")
				&& !path.equals("/services/RightWebService")
				&& !path.equals("/services/MapDAOWebService")
				&& !path.equals("/explorer/main.action")) {
			if (!checkIP(request, session)) {
				response.setContentType("text/html;   charset=UTF-8 ");
				PrintWriter out = response.getWriter();
				String strURL = " <script language=javascript>";
				strURL += "alert('连接超时或账号在其他地方登陆，请重新登录账号！');";
				strURL += "window.open( ' " + request.getContextPath()
						+ "/login.jsp " + " ', '_parent') ";
				strURL += " </script> ";
				out.println(strURL);
			} else {
				arg2.doFilter(request, response);
			}
		} else {

			arg2.doFilter(request, response);
		}
	}

	private boolean checkIP(HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("personId") == null)
			return false;
		String personId = (String) session.getAttribute("personId");
		DbPerson person = null;
		OrgManager orgManager = OrgManagerFactory.getOrgManager();
		try {
			person = (DbPerson) orgManager.getPersonByID(personId);
		} catch (PersonNotFoundException e) {
			log.info("用户不存在:" + personId);
			return false;
		}

		if (person.getIP() == null) {
			log.info("用户没有登录:" + personId);
			return false;
		}
		return true;
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
