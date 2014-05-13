package com.kzxd.filter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;
import net.itjds.common.org.conf.Constants;
import net.itjds.userclient.usm.action.LoginMainAction;

public class LoginSessionListener implements HttpSessionListener {

	protected static Log log =
		LogFactory.getLog(Constants.CONFIG_KEY,
				LoginSessionListener.class);
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		log.debug("sessionDestroyed");
		 HttpSession session = arg0.getSession();
		 String IP = (String)session.getAttribute("IP");
		 HashMap<String, Integer>  map = LoginMainAction.map;
			Set<String> keySet = map.keySet();

			if (keySet.size() > 0) {
				Iterator<String> it = keySet.iterator();

				while (it.hasNext()) {
					String i = it.next();
					if (i.equals(IP)) {
							System.out.println("remove  key:" + i);
							map.remove(i);
					} 
				}

			} 
		
	}

}
