package net.itjds.userclient.usm.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.kzxd.filter.RecordSessionListener;
import com.kzxd.filter.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import net.itjds.bpm.client.BPMSessionFactory;
import net.itjds.bpm.engine.BPMSessionHandle;
import net.itjds.bpm.engine.ConnectInfo;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.bpm.webservice.WSException;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.usm.dao.RoLogDAO;
import net.itjds.userclient.usm.plugins.nds.operator.AutherUser;
import net.itjds.userclient.usm.ws.WSUtil;
import net.itjds.userclient.usm.ws.server.ServerBean;
import net.itjds.userclient.usm.ws.server.ServerBeanManager;
import net.itjds.usm.persistence.action.CommitAction;

import net.itjds.common.database.DBBeanBase;
import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;
import net.itjds.common.org.base.OrgManager;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.Person;
import net.itjds.common.org.base.PersonMsgNotFoundException;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.common.org.conf.Constants;
import net.itjds.common.org.impl.database.DbPerson;
import net.itjds.common.util.DateUtility;
import net.itjds.j2ee.dao.DAOException;
import net.itjds.j2ee.util.UUID;
import net.itjds.oa.OAConstants;

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
public class LoginMainAction extends BPMActionBase {
	protected static Log log = LogFactory.getLog(Constants.CONFIG_KEY,
			LoginMainAction.class);

	private String username;

	private String password;

	private DbPerson person;

	private String message;

	private RoLogDAO roLogDAO;

	private String subServerUrl;
	public static HashMap<String, Integer> map = new HashMap<String, Integer>();

	public LoginMainAction() {
	}

	@SuppressWarnings("static-access")
	public String execute() {
		CommitAction a = new CommitAction();
		try {
			a.execute();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {

			HttpServletRequest request = ServletActionContext.getRequest();
			OrgManager orgManager = OrgManagerFactory.getOrgManager();
			/*
			 * Set<String> keySet = map.keySet();
			 * 
			 * if (keySet.size() > 0) { Iterator<String> it =
			 * keySet.iterator();
			 * 
			 * while (it.hasNext()) { String i = it.next(); Integer value =
			 * map.get(i); System.out.println("key:" + i + "--value:" + value);
			 * if (i.equals(BPMSessionFactory.getIpAddr(request)) && value == 0) {
			 * map.put(BPMSessionFactory.getIpAddr(request), 1); break; } else
			 * if (i.equals(BPMSessionFactory.getIpAddr(request)) && value == 1) {
			 * saveLog2db("login", "用户不存在"); this.message = "该IP登陆使用最大值"; return
			 * Action.LOGIN; } else if
			 * (!i.equals(BPMSessionFactory.getIpAddr(request))) {
			 * map.put(BPMSessionFactory.getIpAddr(request), 0); } }
			 *  } else { map.put(BPMSessionFactory.getIpAddr(request), 0); }
			 */

			try {
				person = (DbPerson) orgManager.getPersonByAccount(username);
			} catch (PersonNotFoundException e) {
				saveLog2db("login", "用户不存在");
				this.message = "用户不存在";
				return Action.LOGIN;
			}
			if (username.equals("zhangsong")) {
				System.out.println(BPMSessionFactory.getIpAddr(request));
				if (BPMSessionFactory.getIpAddr(request).equals("19.177.14.19")) {
					saveLog2db("login", "用户被限制登陆IP");
					this.message = "用户被限制登陆IP";
					return Action.LOGIN;
				}

			}
			String ndssso = ServerBeanManager.getEsbBeanList().getNdssso();
			if (ndssso != null && !"".equals(ndssso) && "true".equals(ndssso)) {
				if (!AutherUser.autherUser(username, password)) // NDS验证
				{
					saveLog2db("login", "密码错误");
					return Action.LOGIN;
				}
			} else if (!password.equals(person.getPassword())) {
				saveLog2db("login", "密码错误");
				this.message = "用户密码错误";
				return Action.LOGIN;
			}

			person.setIP(BPMSessionFactory.getIpAddr(request));
			person.setStatus(person.available);
			request.setAttribute("personId", person.getID());
			ActionContext context = ActionContext.getContext();
			ServerBean serverBean = WSUtil.getSubServer(person);
			subServerUrl = serverBean.getUrl();

			context.getValueStack().setValue("personId", person.getID());
			if (serverBean.type.equals(ServerBean.TYPEOUTCLIENT)) {
				person.setClientType(Person.clienttypeoutnet);
			} else {
				person.setClientType(Person.clienttypeinnernet);
			}

			BPMSessionHandle sessionHandle = BPMSessionFactory
					.getSessionHandle(request);
			BPMSessionFactory sessionFactory = new BPMSessionFactory();
			WorkflowClientService service = sessionFactory.newClientService(
					sessionHandle, OAConstants.APPLICATION_KEY);
			service
					.connect(new ConnectInfo(person.getID(), username, password));

			HttpSession session = request.getSession(true);
			session.setAttribute("sessionHandle", sessionHandle);
			request.getSession().setAttribute("personId", person.getID());
			request.getSession().setAttribute("IP",
					BPMSessionFactory.getIpAddr(request));

			User user = new User();
			String id = String.valueOf(User.USERNO++);
			user.setId(id);
			user.setIp(BPMSessionFactory.getIpAddr(request));
			user.setName(username);
			user.setPassword(password);
			request.getSession().setAttribute(RecordSessionListener.loginFlag,
					user);
			if (subServerUrl == null) {
				try {
					WSUtil.sendMsgByPerson("login", "系统已超过最大连接数请稍候重试！", person);
				} catch (WSException e) {
					this.message = "登录过程中出现异常";
					e.printStackTrace();
				} catch (PersonNotFoundException e) {
					this.message = "登录过程中出现异常";
					e.printStackTrace();
				} catch (PersonMsgNotFoundException e) {
					this.message = "登录过程中出现异常";
					e.printStackTrace();
				}
				return Action.LOGIN;
			}

			saveLog2db("success", "登录成功");

		} catch (Exception e) {
			this.message = "登录过程中出现异常";
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public void saveLog2db(String message, String body) {
		HttpServletRequest request = ServletActionContext.getRequest();
		DBBeanBase dbbase = null;
		dbbase = new DBBeanBase("org");
		roLogDAO = new RoLogDAO();
		roLogDAO.setUuid((new UUID()).toString());
		if (person != null) {
			roLogDAO.setPersonid(person.getID());
			roLogDAO.setPersonname(person.getName());
			roLogDAO.setOrgname(person.getOrgList().get(0).getName() + "/"
					+ person.getOrgList().get(0).getParent().getName());
		}
		roLogDAO.setIp(BPMSessionFactory.getIpAddr(request));
		roLogDAO.setLogintime(DateUtility.constructTimestamp(DateUtility
				.getCurrentTime()));
		roLogDAO.setMsg(message + "||" + body);
		roLogDAO.setConnection(dbbase.getConn());
		try {
			roLogDAO.create();
		} catch (DAOException e) {
			dbbase.close();
			e.printStackTrace();
		}
		dbbase.commit();
		dbbase.close();
	}

	public DbPerson getPerson() {
		return person;
	}

	public void setPerson(DbPerson person) {
		this.person = person;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSubServerUrl() {
		return subServerUrl;
	}

	public void setSubServerUrl(String subServerUrl) {
		this.subServerUrl = subServerUrl;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
