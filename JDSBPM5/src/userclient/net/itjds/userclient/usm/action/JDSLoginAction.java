package net.itjds.userclient.usm.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import ws.itjds.net.MsgWebServiceProxy;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import net.itjds.bpm.client.BPMSessionFactory;
import net.itjds.bpm.webservice.WSException;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.usm.dao.RoLogDAO;
import net.itjds.userclient.usm.plugins.nds.operator.AutherUser;

import net.itjds.userclient.usm.ws.WSUtil;
import net.itjds.userclient.usm.ws.server.ServerBean;
import net.itjds.userclient.usm.ws.server.ServerBeanManager;

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
import net.itjds.common.org.impl.database.DbSSOPerson;
import net.itjds.common.org.service.WSMsgFactory;
import net.itjds.common.util.DateUtility;
import net.itjds.j2ee.dao.DAOException;
import net.itjds.j2ee.util.UUID;

/**
 * <p>Title: BPM工作流管理系统</p>
 * <p>Description: 演示程序应用登陆LoginAction</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: 基督山BPM</p>
 * @author wenzhang
 * @version 2.1
 */
public class JDSLoginAction extends BPMActionBase {
	protected static Log log = LogFactory.getLog(Constants.CONFIG_KEY,
			JDSLoginAction.class);

	private String username;

	private String password;

	private DbPerson person;

	private String message;

	private String version;
	
	private RoLogDAO roLogDAO;

	private String subServerUrl;
	

	public JDSLoginAction() {
	}

	public String execute() {
 
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			OrgManager orgManager = OrgManagerFactory.getOrgManager();
			String ssoUrl = "";
			try {
				person = (DbPerson) orgManager.getPersonByAccount(username);
			} catch (PersonNotFoundException e) {
			
				this.message="用户不存在";
				saveLog2db("login", "用户不存在");
				return Action.LOGIN;
			}
			if(password == null || password.trim().equals(""))
			{
				this.message="密码不能为空";
				saveLog2db("login", "密码不能为空");
				return Action.LOGIN;
			}
			
			if (person.getIP()!=null && !person.getIP().equals(BPMSessionFactory.getIpAddr(request))) {
					try{
						WSUtil.sendMsgByPerson("login", "您已在其他机器登录", person);
						this.message="您已在其他机器登录";
						saveLog2db("login", "用户重复登陆");
						person.setIP(null);
						ActionContext.getContext().getSession().clear();
						WSUtil.synchronizationServerLogout(person.getID(),person.getClientType());
						WSUtil.clearUser(person.getID());
					}catch(Exception e){
						WSUtil.sendMsgByCurrIP("login", "您已在其他机器登录请先退出再试");
						this.message="您已在其他机器登录";
						person.setIP(null);
						ActionContext.getContext().getSession().clear();
						WSUtil.synchronizationServerLogout(person.getID(),person.getClientType());
						WSUtil.clearUser(person.getID());
					}
					
					//WSUtil.sendMsgByCurrIP("success", "正在验证您的IP请稍候...");
					WSMsgFactory.getServiceClientMap().remove(person.getID());
				}
			
			
	
		
		
			boolean saveDbMsg=true;
			if (person.getIP()!=null && person.getIP().equals(BPMSessionFactory.getIpAddr(request))){
				saveDbMsg=false;
			}
			person.setIP(BPMSessionFactory.getIpAddr(request));
			person.setStatus(person.available);
			request.setAttribute("personId", person.getID());
			ActionContext context = ActionContext.getContext();
			ServerBean serverBean=WSUtil.getSubServer(person);
			if (serverBean==null){
				this.message="系统已超过最大连接数请稍候重试！";

				return Action.LOGIN;
			}
			subServerUrl = ssoUrl+serverBean.getUrl();
			
			context.getValueStack().setValue("personId", person.getID());
			  if (serverBean.type.equals(ServerBean.TYPEOUTCLIENT)){
	    	   person.setClientType(Person.clienttypeoutnet);
			}else{
				 person.setClientType(Person.clienttypeinnernet);
			}
			
		//	request.getSession().setAttribute("version"+person.getID(), version);
			request.getSession().setAttribute("personId", person.getID());
			WSUtil.synchronizationServerLogin(person.getID(), person.getIP(),person.getClientType());
			//WSUtil.sendMsgByPerson("success", "登录成功", person);
			if (saveDbMsg){
				saveLog2db("success", "登录成功");
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public void saveLog2db(String message, String body) {
		HttpServletRequest request = ServletActionContext.getRequest();
		DBBeanBase dbbase = null;
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
		
		try {
		
			dbbase = new DBBeanBase("org");
			roLogDAO.setConnection(dbbase.getConn());
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}


}
