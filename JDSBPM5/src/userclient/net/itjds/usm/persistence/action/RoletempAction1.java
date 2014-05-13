package net.itjds.usm.persistence.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.itjds.usm.persistence.model.Duty;
import net.itjds.usm.persistence.model.Dutyright;
import net.itjds.usm.persistence.model.Dutytemp;
import net.itjds.usm.persistence.model.Level;
import net.itjds.usm.persistence.model.Levelright;
import net.itjds.usm.persistence.model.Leveltemp;
import net.itjds.usm.persistence.model.Module;
import net.itjds.usm.persistence.model.Moduletemp;
import net.itjds.usm.persistence.model.Org;
import net.itjds.usm.persistence.model.Orgright;
import net.itjds.usm.persistence.model.Orgtemp;
import net.itjds.usm.persistence.model.Person;
import net.itjds.usm.persistence.model.Persononmoduleadd;
import net.itjds.usm.persistence.model.Position;
import net.itjds.usm.persistence.model.Positionright;
import net.itjds.usm.persistence.model.Postiontemp;
import net.itjds.usm.persistence.model.Role;
import net.itjds.usm.persistence.model.Roleright;
import net.itjds.usm.persistence.model.Roletemp;
import net.itjds.usm.persistence.model.Usergroup;
import net.itjds.usm.persistence.model.Usergroupright;
import net.itjds.usm.persistence.model.Usergrouptemp;
import net.itjds.usm.persistence.service.BaseroleManager;
import net.itjds.usm.persistence.service.DutyManager;
import net.itjds.usm.persistence.service.DutyrightManager;
import net.itjds.usm.persistence.service.DutytempManager;
import net.itjds.usm.persistence.service.LevelManager;
import net.itjds.usm.persistence.service.LevelrightManager;
import net.itjds.usm.persistence.service.LeveltempManager;
import net.itjds.usm.persistence.service.ModuleManager;
import net.itjds.usm.persistence.service.ModuletempManager;
import net.itjds.usm.persistence.service.OrgManager;
import net.itjds.usm.persistence.service.OrgrightManager;
import net.itjds.usm.persistence.service.OrgtempManager;
import net.itjds.usm.persistence.service.PersonManager;
import net.itjds.usm.persistence.service.PersononmoduleaddManager;
import net.itjds.usm.persistence.service.PersononmoduledisableManager;
import net.itjds.usm.persistence.service.PositionManager;
import net.itjds.usm.persistence.service.PositionrightManager;
import net.itjds.usm.persistence.service.PostiontempManager;
import net.itjds.usm.persistence.service.RoleManager;
import net.itjds.usm.persistence.service.RolerightManager;
import net.itjds.usm.persistence.service.RoletempManager;
import net.itjds.usm.persistence.service.UsergroupManager;
import net.itjds.usm.persistence.service.UsergrouprightManager;
import net.itjds.usm.persistence.service.UsergrouptempManager;

import org.appfuse.webapp.action.BaseAction;

public class RoletempAction1 extends BaseAction {
	/**
	 * 角色
	 */
	private RoletempManager roletempManager;
	private RolerightManager rolerightManager;

	/**
	 * 岗位
	 */
	private PostiontempManager postiontempManager;
	private PositionrightManager positionrightManager;

	/*
	 * 职务
	 */
	private DutytempManager dutytempManager;
	private DutyrightManager dutyrightManager;
	private DutyManager dutyManager;

	/**
	 * 级别
	 */
	private LeveltempManager leveltempManager;
	private LevelrightManager levelrightManager;
	private LevelManager levelManager;

	/**
	 * 部门
	 */
	private OrgtempManager orgtempManager;
	private OrgrightManager orgrightManager;
	private OrgManager orgManager;

	/**
	 * 用户组
	 */
	private UsergrouptempManager usergrouptempManager;
	private UsergrouprightManager usergrouprightManager;
	private UsergroupManager usergroupManager;

	/**
	 * 末班
	 */
	private ModuletempManager moduletempManager;
	private PersononmoduleaddManager persononmoduleaddManager;
	private PersononmoduledisableManager persononmoduledisableManager;

	private ModuleManager moduleManager;

	private RoleManager roleManager;

	private BaseroleManager baseroleManager;

	private PositionManager positionManager;

	private PersonManager personManager;

	private String ruuid;

	private String type;

	private String rtypeid;

	private String duuid;
	public void roledata() {
		List<Roletemp> lists = new ArrayList<Roletemp>();
		List<Postiontemp> postionlists = new ArrayList<Postiontemp>();
		List<Dutytemp> dutylists = new ArrayList<Dutytemp>();
		List<Orgtemp> orglists = new ArrayList<Orgtemp>();
		List<Leveltemp> levellists = new ArrayList<Leveltemp>();
		List<Usergrouptemp> uglists = new ArrayList<Usergrouptemp>();
		uglists = usergrouptempManager.findAcct();
		List<Moduletemp> mlists = new ArrayList<Moduletemp>();
		mlists = moduletempManager.findAcct();
		StringBuffer json = new StringBuffer();
		lists = roletempManager.findAcct();
		postionlists = postiontempManager.findAcct();
		dutylists = dutytempManager.findAcct();
		orglists = orgtempManager.findAcct();
		levellists = leveltempManager.findAcct();
		json.append("{totalProperty : 100, root : [");
		if (lists.size() > 0) {
			for (int i = 0; i < lists.size(); i++) {
				Roletemp rt = (Roletemp) lists.get(i);
				json.append("{'uuid':'" + rt.getUuid() + "',");
				String rstr = "";
				String mstr = "";
				json.append("'typeid':'" + rt.getRoletypeid() + "',");
				Module m = new Module();
				m = moduleManager.get(rt.getModuleid());
				if (m != null) {
					mstr = m.getMemo();
				}
				json.append("'modulename':'" + mstr + "',");
				Role r = new Role();
				json.append("'roleclass':'" + rt.getRoleclass() + "',");
				r = roleManager.get(rt.getRoleid());
				if (r != null) {
					rstr = r.getCnname();
				}
				json.append("'rolename':'" + rstr + "',");
				json.append("'createtime':'" + rt.getCreatetime() + "'}");
				json.append(",");
			}
		}
		if (postionlists.size() > 0) {
			for (int i = 0; i < postionlists.size(); i++) {
				Postiontemp rt = (Postiontemp) postionlists.get(i);
				json.append("{'uuid':'" + rt.getUuid() + "',");
				String rstr = "";
				String mstr = "";
				json.append("'typeid':'" + rt.getRoletypeid() + "',");
				Module m = new Module();
				m = moduleManager.get(rt.getModuleid());
				if (m != null) {
					mstr = m.getMemo();
				}
				json.append("'modulename':'" + mstr + "',");
				json.append("'roleclass':'" + rt.getRoleclass() + "',");
				Position r = new Position();
				r = positionManager.get(rt.getRoleid());
				if (r != null) {
					rstr = r.getPositionname();
				}
				json.append("'rolename':'" + rstr + "',");
				json.append("'createtime':'" + rt.getCreatetime() + "'}");
				json.append(",");
			}
		}
		if (dutylists.size() > 0) {
			for (int i = 0; i < dutylists.size(); i++) {
				Dutytemp rt = (Dutytemp) dutylists.get(i);
				json.append("{'uuid':'" + rt.getUuid() + "',");
				String rstr = "";
				String mstr = "";
				json.append("'typeid':'" + rt.getRoletypeid() + "',");
				Module m = new Module();
				m = moduleManager.get(rt.getModuleid());
				if (m != null) {
					mstr = m.getMemo();
				}
				json.append("'modulename':'" + mstr + "',");
				json.append("'roleclass':'" + rt.getRoleclass() + "',");
				Duty r = new Duty();
				r = dutyManager.get(rt.getRoleid());
				if (r != null) {
					rstr = r.getCnname();
				}
				json.append("'rolename':'" + rstr + "',");
				json.append("'createtime':'" + rt.getCreatetime() + "'}");
				json.append(",");
			}
		}
		if (orglists.size() > 0) {
			for (int i = 0; i < orglists.size(); i++) {
				Orgtemp rt = (Orgtemp) orglists.get(i);
				json.append("{'uuid':'" + rt.getUuid() + "',");
				String rstr = "";
				String mstr = "";
				json.append("'typeid':'" + rt.getRoletypeid() + "',");
				Module m = new Module();
				m = moduleManager.get(rt.getModuleid());
				if (m != null) {
					mstr = m.getMemo();
				}
				json.append("'modulename':'" + mstr + "',");
				json.append("'roleclass':'" + rt.getRoleclass() + "',");
				Org r = new Org();
				r = orgManager.get(rt.getRoleid());
				if (r != null) {
					rstr = r.getCnname();
				}
				json.append("'rolename':'" + rstr + "',");
				json.append("'createtime':'" + rt.getCreatetime() + "'}");
				json.append(",");
			}
		}
		if (levellists.size() > 0) {
			for (int i = 0; i < levellists.size(); i++) {
				Leveltemp rt = (Leveltemp) levellists.get(i);
				json.append("{'uuid':'" + rt.getUuid() + "',");
				String rstr = "";
				String mstr = "";
				json.append("'typeid':'" + rt.getRoletypeid() + "',");
				Module m = new Module();
				m = moduleManager.get(rt.getModuleid());
				if (m != null) {
					mstr = m.getMemo();
				}
				json.append("'modulename':'" + mstr + "',");
				json.append("'roleclass':'" + rt.getRoleclass() + "',");
				Level r = new Level();
				r = levelManager.get(rt.getRoleid());
				if (r != null) {
					rstr = r.getCnname();
				}
				json.append("'rolename':'" + rstr + "',");
				json.append("'createtime':'" + rt.getCreatetime() + "'}");
				json.append(",");
			}
		}
		if (uglists.size() > 0) {
			for (int i = 0; i < uglists.size(); i++) {
				Usergrouptemp rt = (Usergrouptemp) uglists.get(i);
				json.append("{'uuid':'" + rt.getUuid() + "',");
				String rstr = "";
				String mstr = "";
				json.append("'typeid':'" + rt.getRoletypeid() + "',");
				Module m = new Module();
				m = moduleManager.get(rt.getModuleid());
				if (m != null) {
					mstr = m.getMemo();
				}
				json.append("'modulename':'" + mstr + "',");
				json.append("'roleclass':'" + rt.getRoleclass() + "',");
				Usergroup r = new Usergroup();
				r = usergroupManager.get(rt.getRoleid());
				if (r != null) {
					rstr = r.getGrpname();
				}
				json.append("'rolename':'" + rstr + "',");
				json.append("'createtime':'" + rt.getCreatetime() + "'}");
				json.append(",");
			}
		}
		if (mlists.size() > 0) {
			for (int i = 0; i < mlists.size(); i++) {
				Moduletemp rt = (Moduletemp) mlists.get(i);
				json.append("{'uuid':'" + rt.getUuid() + "',");
				String rstr = "";
				String mstr = "";
				json.append("'typeid':'" + rt.getRoletypeid() + "',");
				Module m = new Module();
				m = moduleManager.get(rt.getModuleid());
				if (m != null) {
					mstr = m.getCnname();
				}
				json.append("'modulename':'" + mstr + "',");
				json.append("'roleclass':'" + rt.getRoleclass() + "',");
				Person p = new Person();
				p = personManager.get(rt.getRoleid());
				if (p != null) {
					rstr = p.getCnname();
				}
				json.append("'rolename':'" + rstr + "',");
				json.append("'createtime':'" + rt.getCreatetime() + "'}");
				json.append(",");
			}
		}
		String str = json.substring(0, json.length() - 1);
		this.print(str + "]}");
	}
	/**
	 * 授权方法
	 */
	public void roleSave() {
		String[] rus = ruuid.split(",");
		String[] rts = rtypeid.split(",");
		for (int i = 0; i < rus.length; i++) {
			if ("RT".equals(rts[i])) {
				Roletemp r = roletempManager.get(rus[i]);
				r.setRoleacct(type);
				r.setCreatetime(new Date().toLocaleString());
				roletempManager.update(r);
				this.roleAcct(r);
			} else if ("PT".equals(rts[i])) {
				Postiontemp p = postiontempManager.get(rus[i]);
				p.setRoleacct(type);
				p.setCreatetime(new Date().toLocaleString());
				postiontempManager.update(p);
				this.postionAcct(p);
			} else if ("DT".equals(rts[i])) {
				Dutytemp p = dutytempManager.get(rus[i]);
				p.setRoleacct(type);
				p.setCreatetime(new Date().toLocaleString());
				dutytempManager.update(p);
				this.dutyAcct(p);
			} else if ("OT".equals(rts[i])) {
				Orgtemp p = orgtempManager.get(rus[i]);
				p.setRoleacct(type);
				p.setCreatetime(new Date().toLocaleString());
				orgtempManager.update(p);
				this.orgAcct(p);
			} else if ("LT".equals(rts[i])) {
				Leveltemp p = leveltempManager.get(rus[i]);
				p.setRoleacct(type);
				p.setCreatetime(new Date().toLocaleString());
				leveltempManager.update(p);
				this.levelAcct(p);
			} else if ("UT".equals(rts[i])) {
				Usergrouptemp p = usergrouptempManager.get(rus[i]);
				p.setRoleacct(type);
				p.setCreatetime(new Date().toLocaleString());
				usergrouptempManager.update(p);
				this.usergroupAcct(p);
			} else if ("MT".equals(rts[i])) {
				Moduletemp p = moduletempManager.get(rus[i]);
				p.setRoleacct(type);
				p.setCreatetime(new Date().toLocaleString());
				moduletempManager.update(p);
				this.moduleAcct(p);
			}

		}
	}

	public void roleUpdate() {
		String[] rus = ruuid.split(",");
		String[] rts = rtypeid.split(",");
		for (int i = 0; i < rus.length; i++) {
			if ("RT".equals(rts[i])) {
				Roletemp r = roletempManager.get(rus[i]);
				r.setRoleacct(type);
				r.setCreatetime(new Date().toLocaleString());
				roletempManager.update(r);
			} else if ("PT".equals(rts[i])) {
				Postiontemp p = postiontempManager.get(rus[i]);
				p.setRoleacct(type);
				p.setCreatetime(new Date().toLocaleString());
				postiontempManager.update(p);
			} else if ("DT".equals(rts[i])) {
				Dutytemp p = dutytempManager.get(rus[i]);
				p.setRoleacct(type);
				p.setCreatetime(new Date().toLocaleString());
				dutytempManager.update(p);
			} else if ("OT".equals(rts[i])) {
				Orgtemp p = orgtempManager.get(rus[i]);
				p.setRoleacct(type);
				p.setCreatetime(new Date().toLocaleString());
				orgtempManager.update(p);
			} else if ("LT".equals(rts[i])) {
				Leveltemp p = leveltempManager.get(rus[i]);
				p.setRoleacct(type);
				p.setCreatetime(new Date().toLocaleString());
				leveltempManager.update(p);
			} else if ("UT".equals(rts[i])) {
				Usergrouptemp p = usergrouptempManager.get(rus[i]);
				p.setRoleacct(type);
				p.setCreatetime(new Date().toLocaleString());
				usergrouptempManager.update(p);
				this.usergroupAcct(p);
			} else if ("MT".equals(rts[i])) {
				Moduletemp p = moduletempManager.get(rus[i]);
				p.setRoleacct(type);
				p.setCreatetime(new Date().toLocaleString());
				moduletempManager.update(p);
			}
		}
	}

	public void deleteSecure() {
		String[] dus = duuid.split(",");
		for (int i = 0; i < dus.length; i++) {
			baseroleManager.remove(dus[i]);
		}
	}

	public void roleAcct(Roletemp r) {
		List rolerightlist = new ArrayList();
		rolerightManager.getByCondition("from Roleright p where p.roleid='"
				+ r.getRoleid() + "' and p.sysid='" + r.getSysid() + "'");
		Roleright sr = new Roleright();
		for (int i = 0; i < rolerightlist.size(); i++) {
			Roleright rr = (Roleright) rolerightlist.get(i);
			rolerightManager.remove(rr.getUuid());
		}
		sr.setRoleid(r.getRoleid());
		sr.setModuleid(r.getModuleid());
		sr.setSysid(r.getSysid());
		sr.setCreatetime(new Date().toLocaleString());
		rolerightManager.save(sr);
	}

	private void postionAcct(Postiontemp p) {
		List prightlist = new ArrayList();
		positionrightManager
				.getByCondition("from Positionright p where p.positionid ='"
						+ p.getRoleid() + "' and p.sysid='" + p.getSysid()
						+ "'");
		Positionright sr = new Positionright();
		for (int i = 0; i < prightlist.size(); i++) {
			Positionright rr = (Positionright) prightlist.get(i);
			positionrightManager.remove(rr.getUuid());
		}
		sr.setPositionid(p.getRoleid());
		sr.setModuleid(p.getModuleid());
		sr.setSysid(p.getSysid());
		sr.setCreatetime(new Date().toLocaleString());
		positionrightManager.save(sr);
	}

	private void dutyAcct(Dutytemp p) {
		List drightlist = new ArrayList();
		dutyrightManager.getByCondition("from Dutyright p where p.dutyid ='"
				+ p.getRoleid() + "' and p.sysid='" + p.getSysid() + "'");
		Dutyright sr = new Dutyright();
		for (int i = 0; i < drightlist.size(); i++) {
			Dutyright rr = (Dutyright) drightlist.get(i);
			dutyrightManager.remove(rr.getUuid());
		}
		sr.setDutyid(p.getRoleid());
		sr.setModuleid(p.getModuleid());
		sr.setSysid(p.getSysid());
		sr.setCreatetime(new Date().toLocaleString());
		dutyrightManager.save(sr);

	}

	private void orgAcct(Orgtemp p) {
		List orightlist = new ArrayList();
		orgrightManager.getByCondition("from Orgright p where p.orgid ='"
				+ p.getRoleid() + "' and p.sysid='" + p.getSysid() + "'");
		Orgright sr = new Orgright();
		for (int i = 0; i < orightlist.size(); i++) {
			Orgright rr = (Orgright) orightlist.get(i);
			orgrightManager.remove(rr.getUuid());
		}
		sr.setOrgid(p.getRoleid());
		sr.setModuleid(p.getModuleid());
		sr.setSysid(p.getSysid());
		sr.setCreatetime(new Date().toLocaleString());
		orgrightManager.save(sr);
	}

	private void levelAcct(Leveltemp p) {
		List lrightlist = new ArrayList();
		levelrightManager.getByCondition("from Levelright p where p.levelid ='"
				+ p.getRoleid() + "' and p.sysid='" + p.getSysid() + "'");
		Levelright sr = new Levelright();
		for (int i = 0; i < lrightlist.size(); i++) {
			Levelright rr = (Levelright) lrightlist.get(i);
			levelrightManager.remove(rr.getUuid());
		}
		sr.setLevelid(p.getRoleid());
		sr.setModuleid(p.getModuleid());
		sr.setSysid(p.getSysid());
		sr.setCreatetime(new Date().toLocaleString());
		levelrightManager.save(sr);
	}

	private void usergroupAcct(Usergrouptemp p) {
		List ugrightlist = new ArrayList();
		usergrouprightManager
				.getByCondition("from Usergroupright p where p.grpid ='"
						+ p.getRoleid() + "' and p.sysid='" + p.getSysid()
						+ "'");
		Usergroupright sr = new Usergroupright();
		for (int i = 0; i < ugrightlist.size(); i++) {
			Usergroupright rr = (Usergroupright) ugrightlist.get(i);
			usergrouprightManager.remove(rr.getUuid());
		}
		sr.setGrpid(p.getRoleid());
		sr.setModuleid(p.getModuleid());
		sr.setSysid(p.getSysid());
		sr.setCreatetime(new Date().toLocaleString());
		usergrouprightManager.save(sr);
	}

	private void moduleAcct(Moduletemp p) {
		List mrightlist = new ArrayList();
		if ("Y".equals(p.getIsdelperson())) {
			List persononmoduleaddlist = persononmoduleaddManager
					.getByCondition("from Persononmoduleadd p where p.moduleid='"
							+ p.getModuleid()
							+ "' and p.sysid='"
							+ p.getSysid()
							+ "' and p.personid = '"
							+ p.getRoleid() + "'");
			for (int i = 0; i < persononmoduleaddlist.size(); i++) {
				Persononmoduleadd persononmoduleadd = (Persononmoduleadd) persononmoduleaddlist
						.get(i);
				persononmoduleaddManager.remove(persononmoduleadd.getUuid());
			}
		} else if ("N".equals(p.getIsdelperson())) {
			Persononmoduleadd p1 = new Persononmoduleadd();
			p1.setPersonid(p.getRoleid());
			p1.setModuleid(p.getModuleid());
			p1.setSysid(p.getSysid());
			p1.setCreatetime(new Date().toLocaleString());
			persononmoduleaddManager.save(p1);
		}
	}

	public void roletree() {
		/*
		 * List<Baserole> lists = baseroleManager.findAll(); StringBuffer sb =
		 * new StringBuffer(); sb.append("["); if (lists.size() > 0) { for (int
		 * i = 0; i < lists.size(); i++) { Baserole br = lists.get(i);
		 * sb.append("{'text':'" + br.getRoleclass() + "',");
		 * sb.append("'leaf':'true'}"); if (i != lists.size() - 1) {
		 * sb.append(","); } } } sb.append("]"); this.print(sb.toString());
		 */
	}

	

	public void roleybdata() {
		List<Roletemp> lists = new ArrayList<Roletemp>();
		List<Postiontemp> postionlists = new ArrayList<Postiontemp>();
		List<Dutytemp> dutylists = new ArrayList<Dutytemp>();
		List<Orgtemp> orglists = new ArrayList<Orgtemp>();
		List<Leveltemp> levellists = new ArrayList<Leveltemp>();
		List<Usergrouptemp> uglists = new ArrayList<Usergrouptemp>();
		uglists = usergrouptempManager.finddbAcct();
		List<Moduletemp> mlists = new ArrayList<Moduletemp>();
		mlists = moduletempManager.finddbAcct();
		StringBuffer json = new StringBuffer();
		lists = roletempManager.finddbAcct();
		postionlists = postiontempManager.findybAcct();
		dutylists = dutytempManager.finddbAcct();
		orglists = orgtempManager.finddbAcct();
		levellists = leveltempManager.finddbAcct();
		json.append("{totalProperty : 100, root : [");
		if (lists.size() > 0) {
			for (int i = 0; i < lists.size(); i++) {
				Roletemp rt = (Roletemp) lists.get(i);
				json.append("{'uuid':'" + rt.getUuid() + "',");
				String rstr = "";
				String mstr = "";
				json.append("'typeid':'" + rt.getRoletypeid() + "',");
				Module m = new Module();
				m = moduleManager.get(rt.getModuleid());
				if (m != null) {
					mstr = m.getMemo();
				}
				json.append("'modulename':'" + mstr + "',");
				Role r = new Role();
				json.append("'roleclass':'" + rt.getRoleclass() + "',");
				r = roleManager.get(rt.getRoleid());
				if (r != null) {
					rstr = r.getCnname();
				}
				json.append("'rolename':'" + rstr + "',");
				json.append("'createtime':'" + rt.getCreatetime() + "',");
				json.append("'roleteype':'" + rt.getRoleacct() + "',");
				json.append("'isdelperson':''}");
				json.append(",");
			}
		}
		if (postionlists.size() > 0) {
			for (int i = 0; i < postionlists.size(); i++) {
				Postiontemp rt = (Postiontemp) postionlists.get(i);
				json.append("{'uuid':'" + rt.getUuid() + "',");
				String rstr = "";
				String mstr = "";
				json.append("'typeid':'" + rt.getRoletypeid() + "',");
				Module m = new Module();
				m = moduleManager.get(rt.getModuleid());
				if (m != null) {
					mstr = m.getMemo();
				}
				json.append("'modulename':'" + mstr + "',");
				json.append("'roleclass':'" + rt.getRoleclass() + "',");
				Position r = new Position();
				r = positionManager.get(rt.getRoleid());
				if (r != null) {
					rstr = r.getPositionname();
				}
				json.append("'rolename':'" + rstr + "',");
				json.append("'createtime':'" + rt.getCreatetime() + "',");
				json.append("'roleteype':'" + rt.getRoleacct() + "',");
				json.append("'isdelperson':''}");
				json.append(",");
			}
		}
		if (dutylists.size() > 0) {
			for (int i = 0; i < dutylists.size(); i++) {
				Dutytemp rt = (Dutytemp) dutylists.get(i);
				json.append("{'uuid':'" + rt.getUuid() + "',");
				String rstr = "";
				String mstr = "";
				json.append("'typeid':'" + rt.getRoletypeid() + "',");
				Module m = new Module();
				m = moduleManager.get(rt.getModuleid());
				if (m != null) {
					mstr = m.getMemo();
				}
				json.append("'modulename':'" + mstr + "',");
				json.append("'roleclass':'" + rt.getRoleclass() + "',");
				Duty r = new Duty();
				r = dutyManager.get(rt.getRoleid());
				if (r != null) {
					rstr = r.getCnname();
				}
				json.append("'rolename':'" + rstr + "',");
				json.append("'createtime':'" + rt.getCreatetime() + "',");
				json.append("'roleteype':'" + rt.getRoleacct() + "',");
				json.append("'isdelperson':''}");
				json.append(",");
			}
		}
		if (orglists.size() > 0) {
			for (int i = 0; i < orglists.size(); i++) {
				Orgtemp rt = (Orgtemp) orglists.get(i);
				json.append("{'uuid':'" + rt.getUuid() + "',");
				String rstr = "";
				String mstr = "";
				json.append("'typeid':'" + rt.getRoletypeid() + "',");
				Module m = new Module();
				m = moduleManager.get(rt.getModuleid());
				if (m != null) {
					mstr = m.getMemo();
				}
				json.append("'modulename':'" + mstr + "',");
				json.append("'roleclass':'" + rt.getRoleclass() + "',");
				Org r = new Org();
				r = orgManager.get(rt.getRoleid());
				if (r != null) {
					rstr = r.getCnname();
				}
				json.append("'rolename':'" + rstr + "',");
				json.append("'createtime':'" + rt.getCreatetime() + "',");
				json.append("'roleteype':'" + rt.getRoleacct() + "',");
				json.append("'isdelperson':''}");
				json.append(",");
			}
		}
		if (levellists.size() > 0) {
			for (int i = 0; i < levellists.size(); i++) {
				Leveltemp rt = (Leveltemp) levellists.get(i);
				json.append("{'uuid':'" + rt.getUuid() + "',");
				String rstr = "";
				String mstr = "";
				json.append("'typeid':'" + rt.getRoletypeid() + "',");
				Module m = new Module();
				m = moduleManager.get(rt.getModuleid());
				if (m != null) {
					mstr = m.getMemo();
				}
				json.append("'modulename':'" + mstr + "',");
				json.append("'roleclass':'" + rt.getRoleclass() + "',");
				Level r = new Level();
				r = levelManager.get(rt.getRoleid());
				if (r != null) {
					rstr = r.getCnname();
				}
				json.append("'rolename':'" + rstr + "',");
				json.append("'createtime':'" + rt.getCreatetime() + "',");
				json.append("'roleteype':'" + rt.getRoleacct() + "',");
				json.append("'isdelperson':''}");
				json.append(",");
			}
		}
		if (uglists.size() > 0) {
			for (int i = 0; i < uglists.size(); i++) {
				Usergrouptemp rt = (Usergrouptemp) uglists.get(i);
				json.append("{'uuid':'" + rt.getUuid() + "',");
				String rstr = "";
				String mstr = "";
				json.append("'typeid':'" + rt.getRoletypeid() + "',");
				Module m = new Module();
				m = moduleManager.get(rt.getModuleid());
				if (m != null) {
					mstr = m.getMemo();
				}
				json.append("'modulename':'" + mstr + "',");
				json.append("'roleclass':'" + rt.getRoleclass() + "',");
				Usergroup r = new Usergroup();
				r = usergroupManager.get(rt.getRoleid());
				if (r != null) {
					rstr = r.getGrpname();
				}
				json.append("'rolename':'" + rstr + "',");
				json.append("'createtime':'" + rt.getCreatetime() + "',");
				json.append("'roleteype':'" + rt.getRoleacct() + "',");
				json.append("'isdelperson':''}");
				json.append(",");
			}
		}
		if (mlists.size() > 0) {
			for (int i = 0; i < mlists.size(); i++) {
				Moduletemp rt = (Moduletemp) mlists.get(i);
				json.append("{'uuid':'" + rt.getUuid() + "',");
				String rstr = "";
				String mstr = "";
				json.append("'typeid':'" + rt.getRoletypeid() + "',");
				Module m = new Module();
				m = moduleManager.get(rt.getModuleid());
				if (m != null) {
					mstr = m.getCnname();
				}
				json.append("'modulename':'" + mstr + "',");
				json.append("'roleclass':'" + rt.getRoleclass() + "',");
				Person p = new Person();
				p = personManager.get(rt.getRoleid());
				if (p != null) {
					rstr = p.getCnname();
				}
				json.append("'rolename':'" + rstr + "',");
				json.append("'createtime':'" + rt.getCreatetime() + "',");
				json.append("'roleteype':'" + rt.getRoleacct() + "',");
				json.append("'isdelperson':'" + rt.getIsdelperson() + "'}");
				json.append(",");
			}
		}
		String str = json.substring(0, json.length() - 1);
		this.print(str + "]}");
	}

	public void print(String str) {
		super.getResponse().setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		try {
			out = super.getResponse().getWriter();
			out.println(str);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
		}
	}

	public RoletempManager getRoletempManager() {
		return roletempManager;
	}

	public void setRoletempManager(RoletempManager roletempManager) {
		this.roletempManager = roletempManager;
	}

	public ModuleManager getModuleManager() {
		return moduleManager;
	}

	public void setModuleManager(ModuleManager moduleManager) {
		this.moduleManager = moduleManager;
	}

	public RoleManager getRoleManager() {
		return roleManager;
	}

	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}

	public PersonManager getPersonManager() {
		return personManager;
	}

	public void setPersonManager(PersonManager personManager) {
		this.personManager = personManager;
	}

	public PostiontempManager getPostiontempManager() {
		return postiontempManager;
	}

	public void setPostiontempManager(PostiontempManager postiontempManager) {
		this.postiontempManager = postiontempManager;
	}

	public PositionManager getPositionManager() {
		return positionManager;
	}

	public void setPositionManager(PositionManager positionManager) {
		this.positionManager = positionManager;
	}

	public BaseroleManager getBaseroleManager() {
		return baseroleManager;
	}

	public void setBaseroleManager(BaseroleManager baseroleManager) {
		this.baseroleManager = baseroleManager;
	}

	public String getRuuid() {
		return ruuid;
	}

	public void setRuuid(String ruuid) {
		this.ruuid = ruuid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRtypeid() {
		return rtypeid;
	}

	public void setRtypeid(String rtypeid) {
		this.rtypeid = rtypeid;
	}

	public RolerightManager getRolerightManager() {
		return rolerightManager;
	}

	public void setRolerightManager(RolerightManager rolerightManager) {
		this.rolerightManager = rolerightManager;
	}

	public PositionrightManager getPositionrightManager() {
		return positionrightManager;
	}

	public void setPositionrightManager(
			PositionrightManager positionrightManager) {
		this.positionrightManager = positionrightManager;
	}

	public DutytempManager getDutytempManager() {
		return dutytempManager;
	}

	public void setDutytempManager(DutytempManager dutytempManager) {
		this.dutytempManager = dutytempManager;
	}

	public DutyrightManager getDutyrightManager() {
		return dutyrightManager;
	}

	public void setDutyrightManager(DutyrightManager dutyrightManager) {
		this.dutyrightManager = dutyrightManager;
	}

	public DutyManager getDutyManager() {
		return dutyManager;
	}

	public void setDutyManager(DutyManager dutyManager) {
		this.dutyManager = dutyManager;
	}

	public LeveltempManager getLeveltempManager() {
		return leveltempManager;
	}

	public void setLeveltempManager(LeveltempManager leveltempManager) {
		this.leveltempManager = leveltempManager;
	}

	public LevelrightManager getLevelrightManager() {
		return levelrightManager;
	}

	public void setLevelrightManager(LevelrightManager levelrightManager) {
		this.levelrightManager = levelrightManager;
	}

	public LevelManager getLevelManager() {
		return levelManager;
	}

	public void setLevelManager(LevelManager levelManager) {
		this.levelManager = levelManager;
	}

	public OrgtempManager getOrgtempManager() {
		return orgtempManager;
	}

	public void setOrgtempManager(OrgtempManager orgtempManager) {
		this.orgtempManager = orgtempManager;
	}

	public OrgrightManager getOrgrightManager() {
		return orgrightManager;
	}

	public void setOrgrightManager(OrgrightManager orgrightManager) {
		this.orgrightManager = orgrightManager;
	}

	public OrgManager getOrgManager() {
		return orgManager;
	}

	public void setOrgManager(OrgManager orgManager) {
		this.orgManager = orgManager;
	}

	public UsergrouptempManager getUsergrouptempManager() {
		return usergrouptempManager;
	}

	public void setUsergrouptempManager(
			UsergrouptempManager usergrouptempManager) {
		this.usergrouptempManager = usergrouptempManager;
	}

	public UsergrouprightManager getUsergrouprightManager() {
		return usergrouprightManager;
	}

	public void setUsergrouprightManager(
			UsergrouprightManager usergrouprightManager) {
		this.usergrouprightManager = usergrouprightManager;
	}

	public UsergroupManager getUsergroupManager() {
		return usergroupManager;
	}

	public void setUsergroupManager(UsergroupManager usergroupManager) {
		this.usergroupManager = usergroupManager;
	}

	public ModuletempManager getModuletempManager() {
		return moduletempManager;
	}

	public void setModuletempManager(ModuletempManager moduletempManager) {
		this.moduletempManager = moduletempManager;
	}

	public PersononmoduleaddManager getPersononmoduleaddManager() {
		return persononmoduleaddManager;
	}

	public void setPersononmoduleaddManager(
			PersononmoduleaddManager persononmoduleaddManager) {
		this.persononmoduleaddManager = persononmoduleaddManager;
	}

	public PersononmoduledisableManager getPersononmoduledisableManager() {
		return persononmoduledisableManager;
	}

	public void setPersononmoduledisableManager(
			PersononmoduledisableManager persononmoduledisableManager) {
		this.persononmoduledisableManager = persononmoduledisableManager;
	}

	public String getDuuid() {
		return duuid;
	}

	public void setDuuid(String duuid) {
		this.duuid = duuid;
	}

}
