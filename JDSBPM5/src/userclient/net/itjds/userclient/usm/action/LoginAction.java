package net.itjds.userclient.usm.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.itjds.bpm.client.BPMSessionFactory;
import net.itjds.common.CommonConfig;
import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;
import net.itjds.common.org.base.OrgManager;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.Person;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.common.org.conf.Constants;
import net.itjds.common.org.impl.database.DbPerson;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.usm.ws.server.ClientVersion;
import net.itjds.usm.desktop.DesktopManager;
import net.itjds.usm.desktop.bean.DesktopBean;
import net.itjds.usm.desktop.bean.StylesBean;
import net.itjds.usm.persistence.service.PersonaccountManager;

import org.apache.struts2.ServletActionContext;

import com.kzxd.filter.SessionFilter;
import com.kzxd.filter.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

/**
 * <p>
 * Title: BPM工作流管理系统
 * </p>
 * <p>
 * Description: 演示程序应用登陆LoginAction
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: 基督山BPM
 * </p>
 * 
 * @author wenzhang
 * @version 2.1
 */
public class LoginAction extends BPMActionBase {
	protected static Log log = LogFactory.getLog(Constants.CONFIG_KEY,
			LoginAction.class);

	private DbPerson person;
	private String message;
	private DesktopManager obj;
	private String personId;
	private String fileServer;
	private String isDelegate;
	private String updateVersion;
	private String version;
	private List<Delegate> delegateList;

	public DbPerson getPerson() {
		return person;
	}

	public void setPerson(DbPerson person) {
		this.person = person;
	}

	/**
	 * 委托内部类
	 * 
	 * @author 闫晓光
	 * 
	 */
	private class Delegate {
		private DbPerson person;
		private String url;

		public DbPerson getPerson() {
			return person;
		}

		public void setPerson(DbPerson person) {
			this.person = person;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
	}

	public String execute() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();

			String personId1 = (String) ServletActionContext.getRequest()
					.getSession().getAttribute("personId");

			if (personId1 == null) {
				this.message = "连接超时，请重新登录账号！";
				return Action.LOGIN;
			}
			OrgManager orgManager = OrgManagerFactory.getOrgManager();
			try {
				person = (DbPerson) orgManager.getPersonByID(personId1);
			} catch (PersonNotFoundException e) {
				return Action.LOGIN;
			}

			person.setIP(BPMSessionFactory.getIpAddr(request));
			person.setStatus(person.available);
			request.setAttribute("personId", person.getID());
			request.getSession().setAttribute("personId", person.getID());
			ActionContext context = ActionContext.getContext();
			context.getValueStack().setValue("personId", person.getID());
			person.setClientType(Person.clienttypeinnernet);
			String account = person.getAccount();
			request.setAttribute("account", account);
			this.fileServer = CommonConfig.getValue("bpm.fileServer");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public DesktopBean getDesktopBean() {
		DesktopBean desktopBean = obj.getDesktopBean();
		return desktopBean;
	}

	public StylesBean getStylesBean() {
		StylesBean stylesBean = obj.getStylesBean();
		return stylesBean;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	/**
	 * @return the fileServer
	 */
	public String getFileServer() {
		return fileServer;
	}

	/**
	 * @param fileServer
	 *            the fileServer to set
	 */
	public void setFileServer(String fileServer) {
		this.fileServer = fileServer;
	}

	public String getIsDelegate() {
		return isDelegate;
	}

	public void setIsDelegate(String isDelegate) {
		this.isDelegate = isDelegate;
	}

	public List<Delegate> getDelegateList() {
		return delegateList;
	}

	public void setDelegateList(List<Delegate> delegateList) {
		this.delegateList = delegateList;
	}

	public String getUpdateVersion() {
		updateVersion = "false";
		if (version != null
				&& !ClientVersion.getClientVersion().equals(version))
			updateVersion = "true";
		return updateVersion;
	}

	public void setUpdateVersion(String updateVersion) {
		this.updateVersion = updateVersion;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	private PersonaccountManager personaccountManager;

	public PersonaccountManager getPersonaccountManager() {
		return personaccountManager;
	}

	public void setPersonaccountManager(
			PersonaccountManager personaccountManager) {
		this.personaccountManager = personaccountManager;
	}

}
