package com.kzxd.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class RecordSessionListener implements HttpSessionAttributeListener,
		HttpSessionListener {
	private static List<SessionAndUser> sessions;
	public static String loginFlag = "loginUser";

	static {
		if (sessions == null) {
			sessions = Collections
					.synchronizedList(new ArrayList<SessionAndUser>());
		}
	}

	public void attributeAdded(HttpSessionBindingEvent e) {
		HttpSession session = e.getSession();
		String attrName = e.getName();
		Object o = e.getValue();
		// 登录
		if (attrName.equals(loginFlag)) {
			User nowUser = (User) o;
			User sUser = (User) session.getAttribute(loginFlag);
			System.out.println("nowUser:" + nowUser + " sUser:"
					+ sUser.getName());
			// 遍历所有session
			for (int i = sessions.size() - 1; i >= 0; i--) {
				SessionAndUser tem = sessions.get(i);
				if (tem.getUserID().equals(nowUser.getName())) {
					System.out.println("Add:invalidate 1=!" + tem.getSid());
					System.out.println("Add:invalidate 2=!" + nowUser.getId());
					tem.getSession().invalidate();// 自动调用remove
					break;
				}

			}

			SessionAndUser sau = new SessionAndUser();
			sau.setUserID(nowUser.getName());
			sau.setSession(session);
			sau.setSid(session.getId());
			sau.setIp(nowUser.getIp());
			sessions.add(sau);

			for (int i = 0; i < sessions.size(); i++) {
				SessionAndUser tem = sessions.get(i);
			}
		}
	}

	public void sessionCreated(HttpSessionEvent e) {
	}

	public void sessionDestroyed(HttpSessionEvent e) {
	}

	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub

	}

}