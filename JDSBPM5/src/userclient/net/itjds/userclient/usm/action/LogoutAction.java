package net.itjds.userclient.usm.action;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.itjds.bpm.client.BPMSessionFactory;
import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;
import net.itjds.common.org.base.OrgManager;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.PersonMsgNotFoundException;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.common.org.conf.Constants;
import net.itjds.common.org.impl.database.DbPerson;
import net.itjds.common.org.service.WSMsgFactory;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.usm.ws.WSUtil;

import org.apache.struts2.ServletActionContext;

import ws.itjds.net.MsgWebServiceProxy;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import java.util.List;

/*
 * <p>Description: 演示程序应用登陆LoginAction</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: 基督山BPM</p>
 * @author wenzhang
 * @version 2.0
 */
public class LogoutAction extends BPMActionBase {
	protected static Log log = LogFactory.getLog(Constants.CONFIG_KEY,
			LogoutAction.class);
	private String personId;

	private DbPerson person;

	private String event;

	public DbPerson getPerson() {
		return person;
	}

	public void setPerson(DbPerson person) {
		this.person = person;
	}

	// public void sendMsg(String success,String msg)throws WSException{
	// HttpServletRequest request=ServletActionContext.getRequest();
	// MsgWebServiceProxy client=new
	// MsgWebServiceProxy(this.getIpAddr(request));
	// MsgWebService service= client.getMsgWebService();
	// try{
	// service.loginReturn(success, msg);
	// }catch(Exception e){
	// throw new WSException("IP=["+this.getIpAddr(request)+"]非法登录");
	// }
	//	 	  
	// }

	public String execute() throws PersonMsgNotFoundException {
		OrgManager orgManager = OrgManagerFactory.getOrgManager();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		try {
			person = (DbPerson) orgManager.getPersonByID(personId);
			person.setIP(null);
			person.setStatus(person.unavailable);
			// person.commit();

			MsgWebServiceProxy client = WSMsgFactory.getClientByPersonId(person
					.getID());
			ActionContext.getContext().getValueStack().set("personId", null);
			ActionContext.getContext().getSession().remove("personId");
			ActionContext.getContext().getSession().clear();
			request.getSession().removeAttribute("personId");
			if (event.equals("reboot")) {
				client.getMsgWebService().loginReturn("login", "重新登陆");
			} else {
				client.getMsgWebService().loginReturn("shutdown", "关闭");
			}
			// WSUtil.synchronizationServerLogout(personId,person.getClientType());
			WSUtil.clearUser(personId);
			request.getSession().invalidate();
			List<String> userList = null;
			ServletContext application = request.getSession()
					.getServletContext();
			if (application.getAttribute("userList") != null) {
				userList = (List<String>) application.getAttribute("userList");
				userList.remove(person.getID());
				application.setAttribute("userList", userList);
			}
		} catch (PersonNotFoundException e) {
			return Action.SUCCESS;
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

}
