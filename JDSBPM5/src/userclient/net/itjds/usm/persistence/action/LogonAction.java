package net.itjds.usm.persistence.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.itjds.bpm.admin.AdminConfig;
import net.itjds.bpm.client.BPMSessionFactory;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.BPMSessionHandle;
import net.itjds.bpm.engine.ConnectInfo;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.common.org.conf.OrgConfig;
import net.itjds.j2ee.util.UUID;
import net.itjds.oa.OAConstants;
import net.itjds.usm.persistence.dao.UsmLogDAO;
import net.itjds.usm.persistence.model.Module;
import net.itjds.usm.persistence.model.Person;
import net.itjds.usm.persistence.model.Personaccount;
import net.itjds.usm.persistence.model.Personextaccount;
import net.itjds.usm.persistence.model.Personsecure;
import net.itjds.usm.persistence.model.Sysmodule;
import net.itjds.usm.persistence.model.System;
import net.itjds.usm.persistence.service.ModuleManager;
import net.itjds.usm.persistence.service.PersonManager;
import net.itjds.usm.persistence.service.PersonaccountManager;
import net.itjds.usm.persistence.service.PersonextaccountManager;
import net.itjds.usm.persistence.service.PersonsecureManager;
import net.itjds.usm.persistence.service.SysmoduleManager;
import net.itjds.usm.persistence.service.SystemManager;
import net.itjds.usm.persistence.service.UsmLog;

import org.apache.struts2.ServletActionContext;
import org.appfuse.webapp.action.BaseAction;

public class LogonAction extends BaseAction {
	private SystemManager systemManager;
	private SysmoduleManager sysmoduleManager;
	private ModuleManager moduleManager;
	private PersonManager personManager;
	private PersonaccountManager personaccountManager;
	private PersonextaccountManager personextaccountManager;
	private PersonsecureManager personsecureManager;

	private Personextaccount personextaccount = new Personextaccount();
	private Personaccount personaccount = new Personaccount();
	private Person person = new Person();
	private System system = new System();

	private List<Person> personlist = new ArrayList<Person>(0);
	private List<Personaccount> personaccountlist = new ArrayList<Personaccount>(
			0);
	private List<Personsecure> personsecurelist = new ArrayList<Personsecure>(0);
	private List<Personextaccount> personextaccountlist = new ArrayList<Personextaccount>(
			0);
	private List<System> list = new ArrayList<System>(0);
	private List<Sysmodule> sysmodulelist = new ArrayList<Sysmodule>(0);
	private List<Module> modulelist = new ArrayList<Module>(0);

	private String username;
	private String password;

	private String sysid;
	private String sysenname;

	boolean perrole;
	boolean perposition;
	boolean perduty;
	boolean perlevel;
	boolean perusergroup;

	public String getSysid() {
		return sysid;
	}

	public void setSysid(String sysid) {
		this.sysid = sysid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String logon() {
		list = systemManager.getAll();
		sysmodulelist = sysmoduleManager
				.getBySQL("select a.sysid,b.moduleid,b.cnname,b.funcurl from ro_sysmodule a,ro_module b where a.moduleid=b.moduleid");
		personaccountlist = personaccountManager
				.getByCondition("from Personaccount p where p.userid='"
						+ username + "' and p.password='" + password + "'");

		if (username.equals(AdminConfig.getValue("SuperAdmin.username"))
				&& password.equals(AdminConfig.getValue("SuperAdmin.password"))) {
			try {
				HttpServletRequest request = ServletActionContext.getRequest();
				BPMSessionHandle sessionHandle = BPMSessionFactory
						.getSessionHandle(request);
				BPMSessionFactory sessionFactory = new BPMSessionFactory();
				WorkflowClientService service = sessionFactory
						.newClientService(sessionHandle,
								OAConstants.APPLICATION_KEY);
				service.connect(new ConnectInfo("webmaster", username,
								password));
				HttpSession session = request.getSession(true);
				session.setAttribute("sessionHandle", sessionHandle);
				session.setAttribute("personId", AdminConfig
						.getValue("SuperAdmin.username"));

			} catch (BPMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "webmaster";
		} else if (personaccountlist.size() > 0) {
			String returnstr = "";
			person = personManager.get(((Personaccount) personaccountlist
					.get(0)).getPersonid());
			personextaccountlist = personextaccountManager
					.getByCondition("from Personextaccount ap where ap.adminid='"
							+ person.getPersonid() + "'");
			personsecurelist = personsecureManager
					.getByCondition("from Personsecure ap where ap.adminid='"
							+ person.getPersonid() + "'");
			if (personextaccountlist.size() > 0) {
				sysid = ((Personextaccount) personextaccountlist.get(0))
						.getSysid();
				returnstr = SUCCESS;
			}else {
				try {
					sysid = ((Personsecure) personsecurelist.get(0)).getSysid();
				} catch (Exception e) {
					return ERROR;
					// TODO: handle exception
				}
				returnstr = "secure";
			}
			system = systemManager.get(sysid);
			this.sysenname = system.getSysenname();
			this.getRequest().getSession().setAttribute("subSystemId",
					sysenname);
			perrole = OrgConfig.getInstance(sysenname).isSupportPersonDuty();
			perposition = OrgConfig.getInstance(sysenname)
					.isSupportPersonPosition();
			perduty = OrgConfig.getInstance(sysenname).isSupportPersonDuty();
			perlevel = OrgConfig.getInstance(sysenname).isSupportPersonLevel();
			perusergroup = OrgConfig.getInstance(sysenname)
					.isSupportPersonGroup();
			SmallBean bean = new SmallBean();
			bean.setMsm(person.getCnname()+"||µÇÂ½ÏµÍ³");
			bean.setCreatedate(new Date());
			bean.setUuid((new UUID()).toString());
			bean.setPerson(person.getCnname());
			UsmLog usm = new UsmLog();
			usm.save(bean);
			return returnstr;
		} else if (username.equals("usm")) {
			// this.getRequest().getSession().setAttribute("subSystemId",
			// "org");
			return CANCEL;
		} else {
			return ERROR;
		}
	}

	public SystemManager getSystemManager() {
		return systemManager;
	}

	public void setSystemManager(SystemManager systemManager) {
		this.systemManager = systemManager;
	}

	public List<System> getList() {
		return list;
	}

	public void setList(List<System> list) {
		this.list = list;
	}

	public SysmoduleManager getSysmoduleManager() {
		return sysmoduleManager;
	}

	public void setSysmoduleManager(SysmoduleManager sysmoduleManager) {
		this.sysmoduleManager = sysmoduleManager;
	}

	public List<Module> getModulelist() {
		return modulelist;
	}

	public void setModulelist(List<Module> modulelist) {
		this.modulelist = modulelist;
	}

	public ModuleManager getModuleManager() {
		return moduleManager;
	}

	public void setModuleManager(ModuleManager moduleManager) {
		this.moduleManager = moduleManager;
	}

	public List<Sysmodule> getSysmodulelist() {
		return sysmodulelist;
	}

	public void setSysmodulelist(List<Sysmodule> sysmodulelist) {
		this.sysmodulelist = sysmodulelist;
	}

	public List<Person> getPersonlist() {
		return personlist;
	}

	public void setPersonlist(List<Person> personlist) {
		this.personlist = personlist;
	}

	public PersonManager getPersonManager() {
		return personManager;
	}

	public void setPersonManager(PersonManager personManager) {
		this.personManager = personManager;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Personextaccount getPersonextaccount() {
		return personextaccount;
	}

	public void setPersonextaccount(Personextaccount personextaccount) {
		this.personextaccount = personextaccount;
	}

	public List<Personextaccount> getPersonextaccountlist() {
		return personextaccountlist;
	}

	public void setPersonextaccountlist(
			List<Personextaccount> personextaccountlist) {
		this.personextaccountlist = personextaccountlist;
	}

	public PersonextaccountManager getPersonextaccountManager() {
		return personextaccountManager;
	}

	public void setPersonextaccountManager(
			PersonextaccountManager personextaccountManager) {
		this.personextaccountManager = personextaccountManager;
	}

	public Personaccount getPersonaccount() {
		return personaccount;
	}

	public void setPersonaccount(Personaccount personaccount) {
		this.personaccount = personaccount;
	}

	public List<Personaccount> getPersonaccountlist() {
		return personaccountlist;
	}

	public void setPersonaccountlist(List<Personaccount> personaccountlist) {
		this.personaccountlist = personaccountlist;
	}

	public PersonaccountManager getPersonaccountManager() {
		return personaccountManager;
	}

	public void setPersonaccountManager(
			PersonaccountManager personaccountManager) {
		this.personaccountManager = personaccountManager;
	}

	public boolean isPerduty() {
		return perduty;
	}

	public void setPerduty(boolean perduty) {
		this.perduty = perduty;
	}

	public boolean isPerlevel() {
		return perlevel;
	}

	public void setPerlevel(boolean perlevel) {
		this.perlevel = perlevel;
	}

	public boolean isPerposition() {
		return perposition;
	}

	public void setPerposition(boolean perposition) {
		this.perposition = perposition;
	}

	public boolean isPerrole() {
		return perrole;
	}

	public void setPerrole(boolean perrole) {
		this.perrole = perrole;
	}

	public boolean isPerusergroup() {
		return perusergroup;
	}

	public void setPerusergroup(boolean perusergroup) {
		this.perusergroup = perusergroup;
	}

	public System getSystem() {
		return system;
	}

	public void setSystem(System system) {
		this.system = system;
	}

	public String getSysenname() {
		return sysenname;
	}

	public void setSysenname(String sysenname) {
		this.sysenname = sysenname;
	}

	public List<Personsecure> getPersonsecurelist() {
		return personsecurelist;
	}

	public void setPersonsecurelist(List<Personsecure> personsecurelist) {
		this.personsecurelist = personsecurelist;
	}

	public PersonsecureManager getPersonsecureManager() {
		return personsecureManager;
	}

	public void setPersonsecureManager(PersonsecureManager personsecureManager) {
		this.personsecureManager = personsecureManager;
	}

}
