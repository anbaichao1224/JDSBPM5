package net.itjds.usm.persistence.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.OrgManager;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.Person;
import net.itjds.j2ee.util.UUID;
import net.itjds.usm.persistence.model.Appcatalogmodule;
import net.itjds.usm.persistence.model.Application;
import net.itjds.usm.persistence.model.Dutyright;
import net.itjds.usm.persistence.model.Dutytemp;
import net.itjds.usm.persistence.model.Levelright;
import net.itjds.usm.persistence.model.Leveltemp;
import net.itjds.usm.persistence.model.Module;
import net.itjds.usm.persistence.model.Moduletemp;
import net.itjds.usm.persistence.model.Orgright;
import net.itjds.usm.persistence.model.Orgtemp;
import net.itjds.usm.persistence.model.Persononmoduleadd;
import net.itjds.usm.persistence.model.Persononmoduledisable;
import net.itjds.usm.persistence.model.Personright;
import net.itjds.usm.persistence.model.Persontemp;
import net.itjds.usm.persistence.model.Positionright;
import net.itjds.usm.persistence.model.Postiontemp;
import net.itjds.usm.persistence.model.Roleright;
import net.itjds.usm.persistence.model.Roletemp;
import net.itjds.usm.persistence.model.System;
import net.itjds.usm.persistence.model.Usergroupright;
import net.itjds.usm.persistence.model.Usergrouptemp;
import net.itjds.usm.persistence.service.AppcatalogmoduleManager;
import net.itjds.usm.persistence.service.ApplicationManager;
import net.itjds.usm.persistence.service.DutyrightManager;
import net.itjds.usm.persistence.service.DutytempManager;
import net.itjds.usm.persistence.service.LevelrightManager;
import net.itjds.usm.persistence.service.LeveltempManager;
import net.itjds.usm.persistence.service.ModuleManager;
import net.itjds.usm.persistence.service.ModuletempManager;
import net.itjds.usm.persistence.service.OrgrightManager;
import net.itjds.usm.persistence.service.OrgtempManager;
import net.itjds.usm.persistence.service.PersononmoduleaddManager;
import net.itjds.usm.persistence.service.PersononmoduledisableManager;
import net.itjds.usm.persistence.service.PersonrightManager;
import net.itjds.usm.persistence.service.PersontempManager;
import net.itjds.usm.persistence.service.PositionrightManager;
import net.itjds.usm.persistence.service.PostiontempManager;
import net.itjds.usm.persistence.service.RolerightManager;
import net.itjds.usm.persistence.service.RoletempManager;
import net.itjds.usm.persistence.service.SystemManager;
import net.itjds.usm.persistence.service.UsergrouprightManager;
import net.itjds.usm.persistence.service.UsergrouptempManager;
import net.itjds.usm.util.CacheUtil;

import org.appfuse.webapp.action.BaseAction;

public class RoleModel extends BaseAction {

	private ApplicationManager applicationManager;
	private AppcatalogmoduleManager appcatalogmoduleManager;
	private SystemManager systemManager;
	private ModuleManager moduleManager;
	private PositionrightManager positionrightManager;
	private DutyrightManager dutyrightManager;
	private LevelrightManager levelrightManager;
	private LeveltempManager leveltempManager;
	private RolerightManager rolerightManager;
	private UsergrouprightManager usergrouprightManager;
	private UsergrouptempManager usergrouptempManager;
	private PersonrightManager personrightManager;
	private OrgrightManager orgrightManager;
	private OrgtempManager orgtempManager;
	private PersononmoduleaddManager persononmoduleaddManager;
	private PersontempManager persontempManager;
	private PersononmoduledisableManager persononmoduledisableManager;
	private RoletempManager roletempManager;
	private DutytempManager dutytempManager;
	private PostiontempManager postiontempManager;
	private ModuletempManager moduletempManager;

	private Persononmoduleadd persononmoduleadd = new Persononmoduleadd();
	private Persononmoduledisable persononmoduledisable = new Persononmoduledisable();
	private Application application = new Application();
	private Module module = new Module();
	private System system = new System();
	private Appcatalogmodule appcatalogmodule = new Appcatalogmodule();
	private Positionright positionright = new Positionright();
	private Postiontemp postiontemp = new Postiontemp();
	private Dutyright dutyright = new Dutyright();
	private Dutytemp dutytemp = new Dutytemp();
	private Roleright roleright = new Roleright();
	private Roletemp roletemp = new Roletemp();
	private Levelright levelright = new Levelright();
	private Leveltemp leveltemp = new Leveltemp();
	private Usergroupright usergroupright = new Usergroupright();
	private Usergrouptemp usergrouptemp = new Usergrouptemp();
	private Personright personright = new Personright();
	private Orgright orgright = new Orgright();
	private Orgtemp orgtemp = new Orgtemp();
	private Persontemp persontemp = new Persontemp();
	private Moduletemp moduletemp = new Moduletemp();

	private List listall = new ArrayList();
	private List<System> list = new ArrayList<System>(0);
	private List<Appcatalogmodule> appcatalogmodulelist = new ArrayList<Appcatalogmodule>(
			0);
	private List<Application> applicationlist = new ArrayList<Application>(0);
	private List<Module> modulelist = new ArrayList<Module>(0);
	private List<Positionright> positionlist = new ArrayList<Positionright>(0);
	private List<Postiontemp> positionrightlist = new ArrayList<Postiontemp>(0);
	private List<Roleright> rolerightlist = new ArrayList<Roleright>(0);
	private List<Dutyright> dutylist = new ArrayList<Dutyright>(0);
	private List<Dutytemp> dlists = new ArrayList<Dutytemp>(0);
	private List<Levelright> levelrightlist = new ArrayList<Levelright>(0);
	private List<Leveltemp> leveltemplist = new ArrayList<Leveltemp>(0);
	private List<Orgright> orglist = new ArrayList<Orgright>(0);
	private List<Orgtemp> orgtemplists = new ArrayList<Orgtemp>(0);
	private List<Persontemp> persontemplist = new ArrayList<Persontemp>(0);
	private List<Moduletemp> moduletemplist = new ArrayList<Moduletemp>(0);
	private List<Usergroupright> usergrouprightlist = new ArrayList<Usergroupright>(
			0);
	private List<Usergrouptemp> usergrouptemplist = new ArrayList<Usergrouptemp>(
			0);
	private List<Persononmoduleadd> persononmoduleaddlist = new ArrayList<Persononmoduleadd>(
			0);
	private List<Persononmoduledisable> persononmoduledisablelist = new ArrayList<Persononmoduledisable>(
			0);

	List<net.itjds.common.org.base.Module> listright = new ArrayList<net.itjds.common.org.base.Module>(
			0);
	List<net.itjds.common.org.base.Module> listright2 = new ArrayList<net.itjds.common.org.base.Module>(
			0);
	List<net.itjds.common.org.base.Module> listrightadd = new ArrayList<net.itjds.common.org.base.Module>(
			0);
	List<net.itjds.common.org.base.Module> listrightdisable = new ArrayList<net.itjds.common.org.base.Module>(
			0);
	List<net.itjds.common.org.base.Person> personlistright = new ArrayList<net.itjds.common.org.base.Person>(
			0);
	List<net.itjds.common.org.base.Person> personlistright2 = new ArrayList<net.itjds.common.org.base.Person>(
			0);
	List<net.itjds.common.org.base.Person> personlistrightadd = new ArrayList<net.itjds.common.org.base.Person>(
			0);
	List<net.itjds.common.org.base.Person> personlistrightdisable = new ArrayList<net.itjds.common.org.base.Person>(
			0);

	public List publiclist = new ArrayList();
	private String json;
	private String enabled;
	private String haschild;
	private String uuid;
	private String txtCheckValue;
	private String CheckValue;

	private String checkboxs;
	public String template;
	public String nodeid;
	private String moduleid;
	private String appcatalogid;
	private String parentappcatalogid;

	private String sysid;
	private String fv;
	private String fid;
	private String roleid;
	private File file;
	private String fileFileName;
	private String fileContentType;

	public String getFv() {
		if ("null".equals(fv) || fv == null) {
			fv = "";
		}
		return fv;
	}

	public void setFv(String fv) {
		this.fv = fv;
	}

	public String getCheckValue() {
		return CheckValue;
	}

	public void setCheckValue(String checkValue) {
		CheckValue = checkValue;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getSysid() {
		return sysid;
	}

	public void setSysid(String sysid) {
		this.sysid = sysid;
	}

	private void copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src));
				out = new BufferedOutputStream(new FileOutputStream(dst));
				// system.setIcon(Hibernate.createBlob(in));
				byte[] buffer = new byte[16 * 1024];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String clienttest() {
		List<net.itjds.common.org.base.Module> listrightadd = new ArrayList<net.itjds.common.org.base.Module>(
				0);
		return SUCCESS;
	}

	public String appcatalogmoduleInfo() {
		module = moduleManager.get(moduleid);
		return SUCCESS;
	}

	public String appcatalogmoduleSaveSort() {
		String arr[] = txtCheckValue.split(",");
		for (int i = 0; i < arr.length; i++) {
			appcatalogmodule = appcatalogmoduleManager.get(arr[i]);
			appcatalogmodule.setSerialindex(i + 1);
			appcatalogmoduleManager.save(appcatalogmodule);
			module = moduleManager.get(appcatalogmodule.getModuleid());
			module.setSerialindex(i + 1);
			moduleManager.save(module);
		}

		return SUCCESS;
	}

	public String appcatalogmoduleGrid() {
		try {
			appcatalogmodulelist = appcatalogmoduleManager
					.getByCondition("from Appcatalogmodule a where a.appcatalogid='"
							+ appcatalogid + "'");
			String str = "";
			json = "{totalCount:" + appcatalogmodulelist.size() + ",data:[";
			for (int i = 0; i < appcatalogmodulelist.size(); i++) {
				appcatalogmodule = (Appcatalogmodule) appcatalogmodulelist
						.get(i);
				module = moduleManager.get(appcatalogmodule.getModuleid());
				str += "{uuid:'" + appcatalogmodule.getUuid() + "',cnname:'"
						+ module.getCnname() + "',serialindex:'"
						+ appcatalogmodule.getSerialindex() + "',displayname:'"
						+ appcatalogmodule.getDisplayname() + "'},";
			}
			if (!"".equals(str)) {
				str = str.substring(0, str.length() - 1);
			}
			json += str.replaceAll("null", "");
			json += "]}";
			return SUCCESS;
		} catch (Exception ex) {
			return ERROR;
		}
	}

	public String appcatalogmoduleRemove() {
		if ("application".equals(txtCheckValue)) {
			appcatalogmoduleManager.remove(uuid);
		} else {
			String arr[] = txtCheckValue.split(",");
			for (int i = 0; i < arr.length; i++) {
				appcatalogmoduleManager.remove(arr[i]);
			}
		}
		return SUCCESS;
	}

	public String appcatalogmoduleSave() {
		String arr[] = txtCheckValue.split(",");
		for (int i = 0; i < arr.length; i++) {
			Appcatalogmodule m = new Appcatalogmodule();
			m.setModuleid(arr[i]);
			m.setAppcatalogid(appcatalogid);
			module = moduleManager.get(arr[i]);
			m.setDisplayname(module.getCnname());
			appcatalogmoduleManager.save(m);
		}
		CacheUtil.clearCache(appcatalogid);
		CacheUtil.clearCache(module.getModuleid());
		return SUCCESS;
	}

	/*
	 * 保存模块权限
	 */
	public String rightTemplateSave() throws Exception {
		system = systemManager.get(sysid);
		String ename = system.getSysenname();

		String arr[] = new String[] {};
		if (txtCheckValue != null) {
			arr = txtCheckValue.split(",");
		}
		String arrRight[] = new String[] {};
		if (CheckValue != null) {
			arrRight = CheckValue.split(",");
		}
		String arrDisable[] = new String[] {};
		if (checkboxs != null) {
			arrDisable = checkboxs.split(",");
		}

		for (int i = 0; i < arr.length; i++) {
			CacheUtil.clearCache(arr[i].trim());
		}
		for (int i = 0; i < arrRight.length; i++) {
			CacheUtil.clearCache(arrRight[i].trim());
		}
		for (int i = 0; i < arrDisable.length; i++) {
			CacheUtil.clearCache(arrDisable[i].trim());
		}
		CacheUtil.clearCache(nodeid);
		if ("position".equals(template)) {// 对岗位授权

			positionrightlist = postiontempManager
					.getByCondition("from Postiontemp p where p.roleid='"
							+ nodeid + "' and p.sysid='" + sysid + "'");
			for (int i = 0; i < positionrightlist.size(); i++) {
				postiontemp = (Postiontemp) positionrightlist.get(i);
				postiontempManager.remove(postiontemp.getUuid());
			}
			for (int i = 0; i < arrRight.length; i++) {
				Postiontemp p = new Postiontemp();
				p.setRoleid(nodeid);
				p.setModuleid(arrRight[i].trim());
				p.setSysid(sysid);
				p.setRoletypeid("PT");
				p.setRoleacct("N");
				p.setRoleclass("岗位授权申请");
				p.setCreatetime(new Date().toLocaleString());
				postiontempManager.save(p);
			}
			for (int i = 0; i < arr.length; i++) {
				if (!postiontempManager.exists(arr[i].trim())) {
					Postiontemp p1 = new Postiontemp();
					p1.setRoleid(nodeid);
					p1.setModuleid(arr[i].trim());
					p1.setSysid(sysid);
					p1.setRoletypeid("PT");
					p1.setRoleacct("N");
					p1.setRoleclass("岗位授权申请");
					p1.setCreatetime(new Date().toLocaleString());
					postiontempManager.save(p1);
				}
			}
			/*
			 * positionrightlist = positionrightManager .getByCondition("from
			 * Positionright p where p.positionid='" + nodeid + "' and
			 * p.sysid='" + sysid + "'"); for (int i = 0; i <
			 * positionrightlist.size(); i++) { positionright = (Positionright)
			 * positionrightlist.get(i);
			 * positionrightManager.remove(positionright.getUuid()); } for (int
			 * i = 0; i < arrRight.length; i++) { Positionright p = new
			 * Positionright(); p.setPositionid(nodeid);
			 * p.setModuleid(arrRight[i].trim()); p.setSysid(sysid);
			 * p.setCreatetime(new Date().toLocaleString());
			 * positionrightManager.save(p); } for (int i = 0; i < arr.length;
			 * i++) { if (!positionrightManager.exists(arr[i].trim())) {
			 * Positionright p = new Positionright(); p.setPositionid(nodeid);
			 * p.setModuleid(arr[i].trim()); p.setSysid(sysid);
			 * p.setCreatetime(new Date().toLocaleString());
			 * positionrightManager.save(p); } }
			 */

		} else if ("role".equals(template)) {// 对角色授权
			/*
			 * rolerightlist = roletempManager .getByCondition("from Roletemp p
			 * where p.roleid='" + nodeid + "' and p.sysid='" + sysid + "'");
			 * for (int i = 0; i < rolerightlist.size(); i++) { roletemp =
			 * (Roletemp) rolerightlist.get(i);
			 * roletempManager.remove(roletemp.getUuid()); } for (int i = 0; i <
			 * arrRight.length; i++) { Roletemp p = new Roletemp();
			 * p.setRoleid(nodeid); p.setModuleid(arrRight[i].trim());
			 * p.setSysid(sysid); p.setRoletypeid("RT"); p.setRoleacct("N");
			 * p.setRoleclass("角色授权申请"); p.setCreatetime(new
			 * Date().toLocaleString()); roletempManager.save(p); } for (int i =
			 * 0; i < arr.length; i++) { if
			 * (!rolerightManager.exists(arr[i].trim())) { Roletemp p1 = new
			 * Roletemp(); p1.setRoleid(nodeid); p1.setModuleid(arr[i].trim());
			 * p1.setSysid(sysid); p1.setRoletypeid("RT"); p1.setRoleacct("N");
			 * p1.setRoleclass("角色授权申请"); p1.setCreatetime(new
			 * Date().toLocaleString()); CacheUtil.clearCache(arr[i].trim());
			 * roletempManager.save(p1); } }
			 */

			rolerightlist = rolerightManager
					.getByCondition("from Roleright p where p.roleid='"
							+ nodeid + "' and p.sysid='" + sysid + "'");
			for (int i = 0; i < rolerightlist.size(); i++) {
				roleright = (Roleright) rolerightlist.get(i);
				rolerightManager.remove(roleright.getUuid());
			}
			for (int i = 0; i < arrRight.length; i++) {
				Roleright p = new Roleright();
				p.setRoleid(nodeid);
				p.setModuleid(arrRight[i].trim());
				p.setSysid(sysid);
				p.setCreatetime(new Date().toLocaleString());

				rolerightManager.save(p);
			}
			for (int i = 0; i < arr.length; i++) {
				if (!rolerightManager.exists(arr[i].trim())) {
					Roleright p = new Roleright();
					p.setRoleid(nodeid);
					p.setModuleid(arr[i].trim());
					p.setSysid(sysid);
					p.setCreatetime(new Date().toLocaleString());
					CacheUtil.clearCache(arr[i].trim());
					rolerightManager.save(p);
				}
			}

		} else if ("duty".equals(template)) {// 职位
			dlists = dutytempManager
					.getByCondition("from Dutytemp p where p.roleid='" + nodeid
							+ "' and p.sysid='" + sysid + "'");
			for (int i = 0; i < dlists.size(); i++) {
				dutytemp = (Dutytemp) dlists.get(i);
				dutytempManager.remove(dutytemp.getUuid());
			}
			for (int i = 0; i < arrRight.length; i++) {
				Dutytemp p = new Dutytemp();
				p.setRoleid(nodeid);
				p.setModuleid(arrRight[i].trim());
				p.setSysid(sysid);
				p.setRoletypeid("DT");
				p.setRoleacct("N");
				p.setRoleclass("职务授权申请");
				p.setCreatetime(new Date().toLocaleString());
				dutytempManager.save(p);
			}
			for (int i = 0; i < arr.length; i++) {
				if (!dutytempManager.exists(arr[i].trim())) {
					Dutytemp p1 = new Dutytemp();
					p1.setRoleid(nodeid);
					p1.setModuleid(arr[i].trim());
					p1.setSysid(sysid);
					p1.setRoletypeid("DT");
					p1.setRoleacct("N");
					p1.setRoleclass("职务授权申请");
					p1.setCreatetime(new Date().toLocaleString());
					CacheUtil.clearCache(arr[i].trim());
					dutytempManager.save(p1);
				}
			}
			/*
			 * dutylist = dutyrightManager .getByCondition("from Dutyright p
			 * where p.dutyid='" + nodeid + "' and p.sysid='" + sysid + "'");
			 * for (int i = 0; i < dutylist.size(); i++) { dutyright =
			 * (Dutyright) dutylist.get(i);
			 * dutyrightManager.remove(dutyright.getUuid()); } for (int i = 0; i <
			 * arrRight.length; i++) { Dutyright p = new Dutyright();
			 * p.setDutyid(nodeid); p.setModuleid(arrRight[i].trim());
			 * p.setSysid(sysid); p.setCreatetime(new Date().toLocaleString());
			 * CacheUtil.clearCache(arrRight[i].trim());
			 * dutyrightManager.save(p); } for (int i = 0; i < arr.length; i++) {
			 * if (!dutyrightManager.exists(arr[i].trim())) { Dutyright p = new
			 * Dutyright(); p.setDutyid(nodeid); p.setModuleid(arr[i].trim());
			 * p.setSysid(sysid); p.setCreatetime(new Date().toLocaleString());
			 * 
			 * dutyrightManager.save(p); } }
			 */
		} else if ("org".equals(template)) {// 对部门授权
			orgtemplists = orgtempManager
					.getByCondition("from Orgtemp p where p.roleid='" + nodeid
							+ "' and p.sysid='" + sysid + "'");
			for (int i = 0; i < orgtemplists.size(); i++) {
				orgtemp = (Orgtemp) orgtemplists.get(i);
				orgtempManager.remove(orgtemp.getUuid());
			}
			for (int i = 0; i < arrRight.length; i++) {
				Orgtemp p = new Orgtemp();
				p.setRoleid(nodeid);
				p.setModuleid(arrRight[i].trim());
				p.setSysid(sysid);
				p.setRoletypeid("OT");
				p.setRoleacct("N");
				p.setRoleclass("部门授权申请");
				p.setCreatetime(new Date().toLocaleString());

				orgtempManager.save(p);
			}
			for (int i = 0; i < arr.length; i++) {
				if (!orgrightManager.exists(arr[i].trim())) {
					Orgtemp p = new Orgtemp();
					p.setRoleid(nodeid);
					p.setModuleid(arr[i].trim());
					p.setSysid(sysid);
					p.setRoletypeid("OT");
					p.setRoleacct("N");
					p.setRoleclass("部门授权申请");
					p.setCreatetime(new Date().toLocaleString());
					orgtempManager.save(p);
				}

			}

			/*
			 * orglist = orgrightManager .getByCondition("from Orgright p where
			 * p.orgid='" + nodeid + "' and p.sysid='" + sysid + "'"); for (int
			 * i = 0; i < orglist.size(); i++) { orgright = (Orgright)
			 * orglist.get(i); orgrightManager.remove(orgright.getUuid()); } for
			 * (int i = 0; i < arrRight.length; i++) { Orgright p = new
			 * Orgright(); p.setOrgid(nodeid);
			 * p.setModuleid(arrRight[i].trim()); p.setSysid(sysid);
			 * p.setCreatetime(new Date().toLocaleString());
			 * 
			 * orgrightManager.save(p); } for (int i = 0; i < arr.length; i++) {
			 * if (!orgrightManager.exists(arr[i].trim())) { Orgright p = new
			 * Orgright(); p.setOrgid(nodeid); p.setModuleid(arr[i].trim());
			 * p.setSysid(sysid); p.setCreatetime(new Date().toLocaleString());
			 * orgrightManager.save(p); } }
			 */

		} else if ("level".equals(template)) {// 对级别授权

			leveltemplist = leveltempManager
					.getByCondition("from Leveltemp p where p.roleid='"
							+ nodeid + "' and p.sysid='" + sysid + "'");
			for (int i = 0; i < leveltemplist.size(); i++) {
				leveltemp = (Leveltemp) leveltemplist.get(i);
				leveltempManager.remove(leveltemp.getUuid());
			}
			for (int i = 0; i < arrRight.length; i++) {
				Leveltemp p = new Leveltemp();
				p.setRoleid(nodeid);
				p.setModuleid(arrRight[i].trim());
				p.setSysid(sysid);
				p.setRoletypeid("LT");
				p.setRoleacct("N");
				p.setRoleclass("级别授权申请");
				p.setCreatetime(new Date().toLocaleString());
				leveltempManager.save(p);
			}
			for (int i = 0; i < arr.length; i++) {
				if (!leveltempManager.exists(arr[i].trim())) {
					Leveltemp p = new Leveltemp();
					p.setRoleid(nodeid);
					p.setModuleid(arr[i].trim());
					p.setSysid(sysid);
					p.setRoletypeid("LT");
					p.setRoleacct("N");
					p.setRoleclass("级别授权申请");
					p.setCreatetime(new Date().toLocaleString());
					leveltempManager.save(p);
				}
			}

			/*
			 * levelrightlist = levelrightManager .getByCondition("from
			 * Levelright p where p.levelid='" + nodeid + "' and p.sysid='" +
			 * sysid + "'"); for (int i = 0; i < levelrightlist.size(); i++) {
			 * levelright = (Levelright) levelrightlist.get(i);
			 * levelrightManager.remove(levelright.getUuid()); } for (int i = 0;
			 * i < arrRight.length; i++) { Levelright p = new Levelright();
			 * p.setLevelid(nodeid); p.setModuleid(arrRight[i].trim());
			 * p.setSysid(sysid); p.setCreatetime(new Date().toLocaleString());
			 * levelrightManager.save(p); } for (int i = 0; i < arr.length; i++) {
			 * if (!levelrightManager.exists(arr[i].trim())) { Levelright p =
			 * new Levelright(); p.setLevelid(nodeid);
			 * p.setModuleid(arr[i].trim()); p.setSysid(sysid);
			 * p.setCreatetime(new Date().toLocaleString());
			 * levelrightManager.save(p); } }
			 */
		} else if ("usergroup".equals(template)) {// 对用户组授权
			usergrouptemplist = usergrouptempManager
					.getByCondition("from Usergrouptemp p where p.roleid='"
							+ nodeid + "' and p.sysid='" + sysid + "'");
			for (int i = 0; i < usergrouptemplist.size(); i++) {
				usergrouptemp = (Usergrouptemp) usergrouptemplist.get(i);
				usergrouptempManager.remove(usergrouptemp.getUuid());
			}
			for (int i = 0; i < arrRight.length; i++) {
				Usergrouptemp p = new Usergrouptemp();
				p.setRoleid(nodeid);
				p.setModuleid(arrRight[i].trim());
				p.setSysid(sysid);
				p.setRoletypeid("UT");
				p.setRoleacct("N");
				p.setRoleclass("用户组授权申请");
				p.setCreatetime(new Date().toLocaleString());
				usergrouptempManager.save(p);
			}
			for (int i = 0; i < arr.length; i++) {
				if (!usergrouptempManager.exists(arr[i].trim())) {
					Usergrouptemp p = new Usergrouptemp();
					p.setRoleid(nodeid);
					p.setModuleid(arr[i].trim());
					p.setSysid(sysid);
					p.setRoletypeid("UT");
					p.setRoleacct("N");
					p.setRoleclass("用户组授权申请");
					p.setCreatetime(new Date().toLocaleString());
					usergrouptempManager.save(p);
				}
			}
		} else if ("personright".equals(template)) {// 对人员授权
			for (int i = 0; i < arr.length; i++) {
				if (!persononmoduleaddManager.exists(arr[i].trim())) {
					Persononmoduleadd p = new Persononmoduleadd();
					p.setPersonid(nodeid);
					p.setModuleid(arr[i].trim());
					p.setSysid(sysid);
					p.setCreatetime(new Date().toLocaleString());
					persononmoduleaddManager.save(p);
				}
			}

			persononmoduledisablelist = persononmoduledisableManager
					.getByCondition("from Persononmoduledisable p where p.personid='"
							+ nodeid + "' and p.sysid='" + sysid + "'");
			for (int i = 0; i < persononmoduledisablelist.size(); i++) {
				persononmoduledisable = (Persononmoduledisable) persononmoduledisablelist
						.get(i);
				persononmoduledisableManager.remove(persononmoduledisable
						.getUuid());
			}
			listright = OrgManagerFactory.getOrgManager().getPersonByID(nodeid)
					.getAllRightModule();
			if (checkboxs == null) {
				for (int m = 0; m < listright.size(); m++) {
					net.itjds.common.org.base.Module mo = (net.itjds.common.org.base.Module) listright
							.get(m);
					Persononmoduledisable p = new Persononmoduledisable();
					p.setPersonid(nodeid);
					p.setModuleid(mo.getID());
					p.setSysid(sysid);
					p.setCreatetime(new Date().toLocaleString());
					persononmoduledisableManager.save(p);
				}
			} else {

				for (int m = 0; m < listright.size(); m++) {
					net.itjds.common.org.base.Module mo = listright.get(m);
					if (checkboxs.lastIndexOf(mo.getID()) == -1) {
						Persononmoduledisable p = new Persononmoduledisable();
						p.setPersonid(nodeid);
						p.setModuleid(mo.getID());
						p.setSysid(sysid);
						p.setCreatetime(new Date().toLocaleString());
						persononmoduledisableManager.save(p);
					}
				}

			}
			/*
			 * for (int i = 0; i < arr.length; i++) { if
			 * (!persontempManager.exists(arr[i].trim())) { Persontemp p = new
			 * Persontemp(); p.setRoleid(nodeid); p.setModuleid(arr[i].trim());
			 * p.setSysid(sysid); p.setRoletypeid("ET"); p.setRoleacct("N");
			 * p.setRoleclass("人员授权申请"); p.setCreatetime(new
			 * Date().toLocaleString()); persontempManager.save(p); } }
			 * 
			 * persontemplist = persontempManager .getByCondition("from
			 * Persontemp p where p.roleid='" + nodeid + "' and p.sysid='" +
			 * sysid + "'"); for (int i = 0; i < persontemplist.size(); i++) {
			 * persontemp = (Persontemp) persontemplist.get(i);
			 * persontempManager.remove(persontemp.getUuid()); } listright =
			 * OrgManagerFactory.getOrgManager().getPersonByID(nodeid)
			 * .getAllRightModule(); if (checkboxs == null) { for (int m = 0; m <
			 * listright.size(); m++) { net.itjds.common.org.base.Module mo =
			 * (net.itjds.common.org.base.Module) listright .get(m); Persontemp
			 * p = new Persontemp(); p.setRoleid(nodeid);
			 * p.setModuleid(mo.getID()); p.setSysid(sysid);
			 * p.setRoletypeid("ET"); p.setRoleacct("N");
			 * p.setRoleclass("人员授权申请"); p.setCreatetime(new
			 * Date().toLocaleString()); persontempManager.save(p); } } else {
			 * 
			 * for (int m = 0; m < listright.size(); m++) {
			 * net.itjds.common.org.base.Module mo = listright.get(m); if
			 * (checkboxs.lastIndexOf(mo.getID()) == -1) { Persontemp p = new
			 * Persontemp(); p.setRoleid(nodeid); p.setModuleid(mo.getID());
			 * p.setSysid(sysid); p.setRoletypeid("ET"); p.setRoleacct("N");
			 * p.setRoleclass("人员授权申请"); p.setCreatetime(new
			 * Date().toLocaleString()); persontempManager.save(p); } } }
			 */
			// 同步缓存
			// DbSubSystemCacheManager.getInstance("org").personCache.clear();
		} else if ("moduleright".equals(template)) {// 对模块授权
			appcatalogmodule = appcatalogmoduleManager.get(nodeid);
			moduletemplist = moduletempManager
					.getByCondition("from Moduletemp p where p.moduleid='"
							+ appcatalogmodule.getModuleid()
							+ "' and p.sysid='" + sysid
							+ "' and p.isdelperson = 'Y'");
			for (int i = 0; i < moduletemplist.size(); i++) {
				moduletemp = (Moduletemp) moduletemplist.get(i);
				moduletempManager.remove(moduletemp.getUuid());
			}
			personlistright = OrgManagerFactory.getOrgManager().getModuleByID(
					appcatalogmodule.getModuleid()).getAllRightPerson();
			if (checkboxs == null) {
				for (int m = 0; m < personlistright.size(); m++) {
					Person po = (Person) personlistright.get(m);
					/*
					 * persononmoduleaddlist =
					 * persononmoduleaddManager.getByCondition("from
					 * Persononmoduleadd p where p.moduleid='" +
					 * appcatalogmodule.getModuleid() + "' and p.sysid='" +
					 * sysid + "' and p.personid = '" + po.getID() + "'");
					 * for(int i = 0; i < persononmoduleaddlist.size(); i++){
					 * persononmoduleadd = (Persononmoduleadd)
					 * persononmoduleaddlist .get(i);
					 * persononmoduleaddManager.remove(persononmoduleadd.getUuid()); }
					 */
					Moduletemp p = new Moduletemp();
					p.setRoleid(po.getID());
					p.setModuleid(appcatalogmodule.getModuleid());
					p.setSysid(sysid);
					p.setIsdelperson("Y");
					p.setRoletypeid("MT");
					p.setRoleacct("N");
					p.setRoleclass("模块授权申请");
					CacheUtil.clearCache(appcatalogmodule.getModuleid());
					CacheUtil.clearCache(po.getID());
					p.setCreatetime(new Date().toLocaleString());
					moduletempManager.save(p);
					CacheUtil.clearCache(appcatalogmodule.getModuleid());
					CacheUtil.clearCache(po.getID());
				}

			} else {

				for (int m = 0; m < personlistright.size(); m++) {
					net.itjds.common.org.base.Person po = personlistright
							.get(m);
					if (checkboxs.lastIndexOf(po.getID()) == -1) {
						/*
						 * persononmoduleaddlist =
						 * persononmoduleaddManager.getByCondition("from
						 * Persononmoduleadd p where p.moduleid='" +
						 * appcatalogmodule.getModuleid() + "' and p.sysid='" +
						 * sysid + "' and p.personid = '" + po.getID() + "'");
						 * for(int i = 0; i < persononmoduleaddlist.size();
						 * i++){ persononmoduleadd = (Persononmoduleadd)
						 * persononmoduleaddlist .get(i);
						 * persononmoduleaddManager.remove(persononmoduleadd.getUuid()); }
						 */
						Moduletemp p = new Moduletemp();
						p.setRoleid(po.getID());
						p.setModuleid(appcatalogmodule.getModuleid());
						p.setSysid(sysid);
						p.setIsdelperson("Y");
						p.setRoletypeid("MT");
						p.setRoleacct("N");
						p.setRoleclass("模块授权申请");
						CacheUtil.clearCache(appcatalogmodule.getModuleid());
						CacheUtil.clearCache(po.getID());
						p.setCreatetime(new Date().toLocaleString());
						moduletempManager.save(p);
					}
					CacheUtil.clearCache(appcatalogmodule.getModuleid());
					CacheUtil.clearCache(po.getID());
				}

			}

		} else if ("moduleaddright".equals(template)) {

			appcatalogmodule = appcatalogmoduleManager.get(nodeid);
			for (int i = 0; i < arr.length; i++) {
				persononmoduleaddlist = persononmoduleaddManager
						.getByCondition("from Persononmoduleadd p where p.moduleid='"
								+ appcatalogmodule.getModuleid()
								+ "' and p.sysid='"
								+ sysid
								+ "' and p.personid = '" + arr[i].trim() + "'");
				if (persononmoduleaddlist.size() > 0) {
					continue;
				}
				moduletemplist = moduletempManager
						.getByCondition("from Moduletemp p where p.moduleid='"
								+ appcatalogmodule.getModuleid()
								+ "' and p.sysid='" + sysid
								+ "' and p.isdelperson = 'N' and p.roleid = '"
								+ arr[i].trim() + "'");
				for (int j = 0; j < moduletemplist.size(); j++) {
					moduletemp = (Moduletemp) moduletemplist.get(j);
					moduletempManager.remove(moduletemp.getUuid());
				}
				Moduletemp p = new Moduletemp();
				p.setRoleid(arr[i].trim());
				p.setModuleid(appcatalogmodule.getModuleid());
				p.setSysid(sysid);
				p.setIsdelperson("N");
				p.setRoletypeid("MT");
				p.setRoleacct("N");
				p.setRoleclass("模块授权申请");
				CacheUtil.clearCache(appcatalogmodule.getModuleid());
				CacheUtil.clearCache(arr[i].trim());
				p.setCreatetime(new Date().toLocaleString());
				moduletempManager.save(p);
			}
			/*
			 * appcatalogmodule = appcatalogmoduleManager.get(nodeid); for (int
			 * i = 0; i < arr.length; i++) { Persononmoduleadd p = new
			 * Persononmoduleadd(); p.setPersonid(arr[i].trim());
			 * p.setModuleid(appcatalogmodule.getModuleid()); p.setSysid(sysid);
			 * CacheUtil.clearCache(appcatalogmodule.getModuleid());
			 * CacheUtil.clearCache(arr[i].trim()); p.setCreatetime(new
			 * Date().toLocaleString()); persononmoduleaddManager.save(p); }
			 */
			// 同步缓存
			// DbSubSystemCacheManager.getInstance("org").reloadCache();
			// DbSubSystemCacheManager.getInstance("org").moduleCache.clear();
			// personCache.remove(nodeid);
		}
		// 刷新权限缓存
		return SUCCESS;
	}

	/**
	 * 取出各种情况的权限
	 * 
	 * @return
	 */
	public String rightTemplate() {

		// if (fv == null) {
		fv = "";
		// } else {
		try {
			fv = new String(fv.getBytes("ISO8859-1"), "UTF-8");
			modulelist = moduleManager
					.getBySQL("select a.* from ro_Module a,ro_Sysmodule b where a.moduleid=b.moduleid and a.needright=1 and b.sysid='"
							+ sysid + "' and a.memo like '%" + fv + "%'");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// }

		try {
			// modulelist=moduleManager.getByCondition("select a from Module
			// a,Sysmodule b where a.moduleid=b.moduleid and a.needright=1 and
			// b.sysid='"+sysid+"'");
			if ("position".equals(template)) {
				// publiclist=positionrightManager.getByCondition("from
				// Positionright p where p.positionid='"+ nodeid +"' and
				// p.sysid='"+sysid+"'");
				publiclist = moduleManager
						.getBySQL("select a.* from ro_Module a,ro_Sysmodule b where a.moduleid=b.moduleid and a.needright=1 and b.sysid='"
								+ sysid
								+ "' and a.moduleid in (select p.moduleid from ro_Positionright p where p.positionid='"
								+ nodeid + "' and p.sysid='" + sysid + "')");
			} else if ("role".equals(template)) {
				// publiclist=rolerightManager.getByCondition("from Roleright p
				// where p.roleid='"+ nodeid +"' and p.sysid='"+sysid+"'");
				publiclist = moduleManager
						.getBySQL("select a.* from ro_Module a,ro_Sysmodule b where a.moduleid=b.moduleid and a.needright=1 and b.sysid='"
								+ sysid
								+ "' and a.moduleid in (select p.moduleid from ro_Roleright p where p.roleid='"
								+ nodeid + "' and p.sysid='" + sysid + "')");
			} else if ("duty".equals(template)) {
				// publiclist=dutyrightManager.getByCondition("from Dutyright p
				// where p.dutyid='"+ nodeid +"' and p.sysid='"+sysid+"'");
				publiclist = moduleManager
						.getBySQL("select a.* from ro_Module a,ro_Sysmodule b where a.moduleid=b.moduleid and a.needright=1 and b.sysid='"
								+ sysid
								+ "' and a.moduleid in (select p.moduleid from ro_Dutyright p where p.dutyid='"
								+ nodeid + "' and p.sysid='" + sysid + "')");
			} else if ("level".equals(template)) {
				// publiclist=levelrightManager.getByCondition("from Levelright
				// p where p.levelid='"+ nodeid +"' and p.sysid='"+sysid+"'");
				publiclist = moduleManager
						.getBySQL("select a.* from ro_Module a,ro_Sysmodule b where a.moduleid=b.moduleid and a.needright=1 and b.sysid='"
								+ sysid
								+ "' and a.moduleid in (select p.moduleid from ro_Levelright p where p.levelid='"
								+ nodeid + "' and p.sysid='" + sysid + "')");
			} else if ("usergroup".equals(template)) {
				// publiclist=usergrouprightManager.getByCondition("from
				// Usergroupright p where p.grpid='"+ nodeid +"' and
				// p.sysid='"+sysid+"'");
				publiclist = moduleManager
						.getBySQL("select a.* from ro_Module a,ro_Sysmodule b where a.moduleid=b.moduleid and a.needright=1 and b.sysid='"
								+ sysid
								+ "' and a.moduleid in (select p.moduleid from ro_Usergroupright p where p.grpid='"
								+ nodeid + "' and p.sysid='" + sysid + "')");
			} else if ("person".equals(template)) {
				// 取已分配权限模块
				// publiclist=personrightManager.getByCondition("from
				// Personright p where p.personid='"+ nodeid +"' and
				// p.sysid='"+sysid+"'");
				publiclist = moduleManager
						.getBySQL("select a.* from ro_Module a,ro_Sysmodule b where a.moduleid=b.moduleid and a.needright=1 and b.sysid='"
								+ sysid
								+ "' and a.moduleid in (select p.moduleid from ro_Usergroupright p where p.grpid='"
								+ nodeid + "' and p.sysid='" + sysid + "')");
			} else if ("org".equals(template)) {
				// 取已分配权限模块
				publiclist = moduleManager
						.getBySQL("select a.* from ro_Module a,ro_Sysmodule b where a.moduleid=b.moduleid and a.needright=1 and b.sysid='"
								+ sysid
								+ "' and a.moduleid in (select p.moduleid from ro_Orgright p where p.orgid='"
								+ nodeid + "' and p.sysid='" + sysid + "')");
			} else if ("personright".equals(template)) {
				// 同步缓存
				// DbSubSystemCacheManager.getInstance("org").personCache.clear();
				listright = OrgManagerFactory.getOrgManager().getPersonByID(
						nodeid).getAllRightModule();// 取所有权限
				java.lang.System.out.println("size is e:" + listright.size());
				listrightdisable = OrgManagerFactory.getOrgManager()
						.getPersonByID(nodeid).getDisableModuleList();// 取禁用权限

				return INPUT;
			} else if ("moduleright".equals(template)) {
				appcatalogmodule = appcatalogmoduleManager.get(nodeid);
				// 同步缓存
				personlistright = OrgManagerFactory.getOrgManager()
						.getModuleByID(appcatalogmodule.getModuleid())
						.getAllRightPerson();
				OrgManager ogm = OrgManagerFactory.getOrgManager();
				personlistrightdisable = ogm.getModuleByID(
						appcatalogmodule.getModuleid()).getDisablePersonList();
				int t = personlistrightdisable.size();
				return LOGIN;
			}
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
			return ERROR;
		}
	}

	public String applicationTemplate() {
		if ("system".equals(template)) {
			applicationlist = applicationManager
					.getByCondition("from Application a where a.parentappcatalogid='"
							+ sysid + "' order by serialindex ");
			String str = "";
			str = "{images: [";
			for (int i = 0; i < applicationlist.size(); i++) {
				Application app = new Application();
				app = applicationlist.get(i);
				str += "{id:'" + app.getAppcatalogid() + "', name:'"
						+ app.getCnname() + "',url:'/usm/img/folder.png'},";
			}
			if (applicationlist.size() != 0) {
				str = str.substring(0, str.length() - 1);
			}
			str += "]}";
			json = str;
		} else if ("application".equals(template)) {
			applicationlist = applicationManager
					.getByCondition("from Application a where a.parentappcatalogid='"
							+ sysid + "'");
			appcatalogmodulelist = appcatalogmoduleManager
					.getByCondition("from Appcatalogmodule ap where ap.appcatalogid='"
							+ sysid + "'");

			String str = "";
			str = "{images: [";
			for (int i = 0; i < applicationlist.size(); i++) {
				Application app = new Application();
				app = applicationlist.get(i);
				str += "{id:'" + app.getAppcatalogid() + "', name:'"
						+ app.getCnname() + "',url:'/usm/img/folder.png'},";
			}

			for (int i = 0; i < appcatalogmodulelist.size(); i++) {
				Appcatalogmodule app = new Appcatalogmodule();
				app = appcatalogmodulelist.get(i);
				str += "{id:'" + app.getModuleid() + "', name:'"
						+ app.getDisplayname()
						+ "',url:'/usm/img/document.png'},";
			}

			if (applicationlist.size() != 0 || appcatalogmodulelist.size() != 0) {
				str = str.substring(0, str.length() - 1);
			}
			str += "]}";
			json = str;
		}
		return SUCCESS;
	}

	public String applicationSaveSort() {
		String arr[] = txtCheckValue.split(",");
		for (int i = 0; i < arr.length; i++) {
			application = applicationManager.get(arr[i]);
			application.setSerialindex(i + 10001);
			applicationManager.save(application);
			CacheUtil.clearCache(arr[i]);
		}

		return SUCCESS;
	}

	public String applicationSort() {
		try {
			applicationlist = applicationManager
					.getByCondition("from Application a where a.parentappcatalogid='"
							+ parentappcatalogid + "'");
			String str = "";
			json = "{totalCount:" + applicationlist.size() + ",data:[";
			for (int i = 0; i < applicationlist.size(); i++) {
				application = (Application) applicationlist.get(i);
				str += "{uuid:'" + application.getUuid() + "',appcatalogid:'"
						+ application.getAppcatalogid() + "',cnname:'"
						+ application.getCnname() + "',serialinde:'"
						+ application.getSerialindex() + "',memo:'"
						+ application.getMemo() + "'},";
			}
			if (!"".equals(str)) {
				str = str.substring(0, str.length() - 1);
			}
			json += str.replaceAll("null", "");
			json += "]}";
			return SUCCESS;
		} catch (Exception ex) {
			return ERROR;
		}
	}

	public String applicationGrid() {
		try {
			applicationlist = applicationManager
					.getByCondition("from Application a where a.parentappcatalogid='"
							+ appcatalogid + "' order by a.serialindex asc");
			String str = "";
			json = "{totalCount:" + applicationlist.size() + ",data:[";
			for (int i = 0; i < applicationlist.size(); i++) {
				application = (Application) applicationlist.get(i);
				str += "{uuid:'" + application.getUuid() + "',appcatalogid:'"
						+ application.getAppcatalogid() + "',cnname:'"
						+ application.getCnname() + "',memo:'"
						+ application.getMemo() + "'},";
				// str+=getApplicationRootId(application.getAppcatalogid())+",";
			}
			if (!"".equals(str)) {
				str = str.substring(0, str.length() - 1);
			}
			json += str;
			json += "]}";
			return SUCCESS;
		} catch (Exception ex) {
			return ERROR;
		}
	}

	public List<Bean> FindRoleModuleid(String roleId) {
		List<Bean> beanlist = new ArrayList<Bean>();
		Statement stmt = null;
		StringBuffer sql = null;
		DBBeanBase dbbase = new DBBeanBase("org");
		Connection conn = dbbase.getConn();
		try {
			stmt = conn.createStatement();
			sql = new StringBuffer();
			sql.append("select * from ro_roleright t where t.roleid ='");
			sql.append(roleId);
			sql.append("'");
			ResultSet rs = stmt.executeQuery(sql.toString());
			if (rs != null) {
				while (rs.next()) {
					Bean bean = new Bean();
					bean.setModuleid(rs.getString("moduleid"));
					beanlist.add(bean);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
			dbbase.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

			}
		}
		return beanlist;

	}

	public String applicationtreeJson() {
		StringBuffer sys = new StringBuffer();
		try {
			// 如果是根节点，取根下的系统
			if (fid == null) {
				if (sysid == null) {
					list = systemManager
							.getByCondition("from System sys order by sys.serialindex asc");
				} else {
					system = systemManager.get(sysid);
					list.add(system);
				}

				for (int i = 0; i < list.size(); i++) {

					System system = (System) list.get(i);
					String sql = "select p from Application p where p.parentappcatalogid='"
							+ system.getSysid()
							+ "' order by p.serialindex asc ";
					applicationlist = applicationManager.getByCondition(sql);

					int app = applicationlist.size();
					if (app != 0) {
						sys
								.append("{id:'")
								.append(system.getSysid())
								.append("',text:'")
								.append(system.getSyscnname())
								.append(
										"',cls:'forum-ct',treetype:'fathernodes',url:'")
								.append(system.getUrl())
								.append(
										"',iconCls:'forum-parent',expanded:false,children:[");
						String appmodule = appcatalogmoduleList(system
								.getSysid());
						if (!"".equals(appmodule)) {
							sys.append(appmodule);
						}
					} else {
						String appmodule = appcatalogmoduleList(system
								.getSysid());
						if (!"".equals(appmodule)) {
							appmodule = appmodule.substring(0, appmodule
									.length() - 1);
						}
						sys
								.append("{id:'")
								.append(system.getSysid())
								.append("',text:'")
								.append(system.getSyscnname())
								.append(
										"',cls:'forum-ct',treetype:'fathernodes',url:'")
								.append(system.getUrl())
								.append(
										"',iconCls:'forum-parent',expanded:false,children:[")
								.append(appmodule).append("]},");

					}
					if (app != 0) {
						String str = "";
						for (Iterator it = applicationlist.iterator(); it
								.hasNext();) {

							Application application = (Application) it.next();
							try {

								str += getApplicationRootId(application
										.getAppcatalogid())
										+ ",";

							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						sys.append(str.substring(0, str.length() - 1)).append(
								"]},");
						// sys.append("]},");

					}

				}
			} else {// 如果是应用目录的最末级，则取该应用目录下的模块
				List applist = new ArrayList();
				applist = appcatalogmoduleManager
						.getByCondition("from Appcatalogmodule m where m.appcatalogid='"
								+ fid + "'");
				if (applist.size() != 0) {
					String rtn = appcatalogmoduleList(fid);
					sys.append(rtn);

				}
			}
			if (!"".equals(sys)) {
				this.json = sys.toString();
				if (sys.toString().endsWith(",")) {
					this.json = sys.toString().substring(0, sys.length() - 1);
				}
				// .substring(0,sys.length()-1);

			}
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 取应用信息
	 * 
	 * @param rootId
	 * @return
	 * @throws Exception
	 */
	public String getApplicationRootId(String rootId) throws Exception {

		StringBuffer result = new StringBuffer();
		String hql = "from Application f where f.appcatalogid='" + rootId
				+ "' order by f.serialindex asc";
		// 取当前目录的信息
		List list = applicationManager.getByCondition(hql);
		String strText = "";
		String strLink = "";
		if (list.size() != 0) {
			strText = ((Application) list.get(0)).getCnname();
			strLink = ((Application) list.get(0)).getSysid();
		}
		// 取该目录下的信息
		String hql2 = "from Application f where f.parentappcatalogid='"
				+ rootId + "' order by f.serialindex asc";
		List list2 = applicationManager.getByCondition(hql2);

		if (list2.size() != 0) {
				result.append("{").append("id:'").append(rootId).append("',")
						.append("treetype:'module',").append("text:'").append(
								strText).append("',").append("sysid:'");
				result.append(strLink).append("',").append("children:[");
				for (int i = 0; i < list2.size(); i++) {
					Application app = new Application();
					app = (Application) list2.get(i);
					String intChildId = app.getAppcatalogid();
					result.append(getApplicationRootId(intChildId));
					if (i < list2.size() - 1) {
						result.append(",");
					}
				}
				String json = appcatalogmoduleList(rootId);
				if (json.length() > 0) {
					result.append("," + json.substring(0, json.length() - 1));
				}
				result.append("]");
				result.append("}");
		} else {
			result.append("{").append("id:'").append(rootId).append("',")
					.append("text:'").append(strText).append("',").append(
							"sysid:'");
			result.append(strLink).append("'").append(",treetype:'module'}");

		}

		return result.toString();
	}

	/**
	 * 取某一目录下的模块
	 * 
	 * @param rootId
	 * @return
	 */
	public String appcatalogmoduleList(String rootId) {

		StringBuffer str = new StringBuffer();
		List list = new ArrayList();
		list = appcatalogmoduleManager
				.getByCondition("from Appcatalogmodule m where m.appcatalogid='"
						+ rootId + "' order by m.serialindex asc");
		for (int i = 0; i < list.size(); i++) {
			appcatalogmodule = (Appcatalogmodule) list.get(i);
			Module m = new Module();

			try {
				m = moduleManager.get(appcatalogmodule.getModuleid());
				str.append("{");
				str.append("id:'").append(appcatalogmodule.getUuid()).append(
						"',");
				str.append("icon: '/usm/img/personicon.jpg',");
				List<Bean> modulelist = this.FindRoleModuleid(roleid);
				int flag=0;
				for (int j = 0; j < modulelist.size(); j++){
					String moduleid = modulelist.get(j).getModuleid();
					if(moduleid.equals(appcatalogmodule.getModuleid())){
						str.append("text:'").append(m.getCnname()+"[已授权]").append("',");
						flag=1;
						break;
					}
				}
				if(flag==0){
					str.append("text:'").append(m.getCnname()).append("',");
				}
				str.append("uuid:'").append(appcatalogmodule.getUuid()).append(
						"',");
				str.append("leaf: true,");
				str.append("type:'modulenode'");
				str.append("},");

			} catch (Exception e) {
				continue;
			}

		}
		return str.toString();
	}

	public String appSave() {

		if (!"".equals(fileFileName)) {
			String targetDirectory = this.getRequest().getRealPath(
					"/desktop/widgets/jdsexplorer/resouces/images");
			java.io.File target = new java.io.File(targetDirectory,
					fileFileName);
			copy(file, target);
			application.setIcon("/desktop/widgets/jdsexplorer/resouces/images/"
					+ fileFileName);
		}

		// Date date=new Date();
		// String sss=String.valueOf(date.getTime());
		// String id=sss.substring(sss.length()-4,sss.length());
		String uuid = new UUID().toString();
		application.setAppcatalogid(uuid);
		if (enabled != null) {
			application.setEnabled(1);
		} else {
			application.setEnabled(0);
		}

		if (haschild != null) {
			application.setHaschild(1);
		} else {
			application.setHaschild(0);
		}

		try {
			applicationManager.save(application);
			return SUCCESS;
		} catch (Exception ex) {
			java.lang.System.out.println(ex.getLocalizedMessage());
			return ERROR;
		}

	}

	public String appremove() {
		try {
			applicationManager.remove(application.getAppcatalogid());
			List<Appcatalogmodule> applist = new ArrayList<Appcatalogmodule>();
			applist = appcatalogmoduleManager
					.getByCondition("from Appcatalogmodule f where f.appcatalogid='"
							+ application.getAppcatalogid() + "'");
			for (int i = 0; i < applist.size(); i++) {
				Appcatalogmodule mo = (Appcatalogmodule) applist.get(i);
				appcatalogmoduleManager.remove(mo.getUuid());
			}
			CacheUtil.clearCache(application.getAppcatalogid());
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
			return ERROR;
		}

	}

	/**
	 * 编码是否有效
	 * 
	 * @param text
	 * @return
	 */
	public boolean Utf8codeCheck(String text) {
		String sign = "";
		if (text.startsWith("%e"))
			for (int i = 0, p = 0; p != -1; i++) {
				p = text.indexOf("%", p);
				if (p != -1)
					p++;
				sign += p;
			}
		return sign.equals("147-1");
	}

	public String appupdate() {
		try {
			Application app = new Application();
			app = applicationManager.get(application.getAppcatalogid());
			app.setCnname(application.getCnname());
			app.setEnname(application.getEnname());
			app.setNavurl(application.getNavurl());
			app.setMemo(application.getMemo());
			if (enabled != null) {
				app.setEnabled(1);
			} else {
				app.setEnabled(0);
			}

			if (haschild != null) {
				app.setHaschild(1);
			} else {
				app.setHaschild(0);
			}
			if (!"".equals(fileFileName)) {
				String targetDirectory = this.getRequest().getRealPath(
						"/desktop/widgets/jdsexplorer/resouces/images");
				java.io.File target = new java.io.File(targetDirectory,
						fileFileName);
				copy(file, target);
				app.setIcon("/desktop/widgets/jdsexplorer/resouces/images/"
						+ fileFileName);
			}
			CacheUtil.clearCache(application.getAppcatalogid());
			applicationManager.save(app);
			return SUCCESS;
		} catch (Exception ex) {
			java.lang.System.out.println(ex.getLocalizedMessage());
			return ERROR;
		}
	}

	public String applicationInfo() {
		application = applicationManager.get(appcatalogid);
		return SUCCESS;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public ApplicationManager getApplicationManager() {
		return applicationManager;
	}

	public void setApplicationManager(ApplicationManager applicationManager) {
		this.applicationManager = applicationManager;
	}

	public List<System> getList() {
		return list;
	}

	public void setList(List<System> list) {
		this.list = list;
	}

	public System getSystem() {
		return system;
	}

	public void setSystem(System system) {
		this.system = system;
	}

	public SystemManager getSystemManager() {
		return systemManager;
	}

	public void setSystemManager(SystemManager systemManager) {
		this.systemManager = systemManager;
	}

	public List<Application> getApplicationlist() {
		return applicationlist;
	}

	public void setApplicationlist(List<Application> applicationlist) {
		this.applicationlist = applicationlist;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getAppcatalogid() {
		return appcatalogid;
	}

	public void setAppcatalogid(String appcatalogid) {
		this.appcatalogid = appcatalogid;
	}

	public Appcatalogmodule getAppcatalogmodule() {
		return appcatalogmodule;
	}

	public void setAppcatalogmodule(Appcatalogmodule appcatalogmodule) {
		this.appcatalogmodule = appcatalogmodule;
	}

	public List<Appcatalogmodule> getAppcatalogmodulelist() {
		return appcatalogmodulelist;
	}

	public void setAppcatalogmodulelist(
			List<Appcatalogmodule> appcatalogmodulelist) {
		this.appcatalogmodulelist = appcatalogmodulelist;
	}

	public AppcatalogmoduleManager getAppcatalogmoduleManager() {
		return appcatalogmoduleManager;
	}

	public void setAppcatalogmoduleManager(
			AppcatalogmoduleManager appcatalogmoduleManager) {
		this.appcatalogmoduleManager = appcatalogmoduleManager;
	}

	public String getTxtCheckValue() {
		return txtCheckValue;
	}

	public void setTxtCheckValue(String txtCheckValue) {
		this.txtCheckValue = txtCheckValue;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
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

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getHaschild() {
		return haschild;
	}

	public void setHaschild(String haschild) {
		this.haschild = haschild;
	}

	public String getParentappcatalogid() {
		return parentappcatalogid;
	}

	public void setParentappcatalogid(String parentappcatalogid) {
		this.parentappcatalogid = parentappcatalogid;
	}

	public String getModuleid() {
		return moduleid;
	}

	public void setModuleid(String moduleid) {
		this.moduleid = moduleid;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getNodeid() {
		return nodeid;
	}

	public void setNodeid(String nodeid) {
		this.nodeid = nodeid;
	}

	public List<Postiontemp> getPositionrightlist() {
		return positionrightlist;
	}

	public void setPositionrightlist(List<Postiontemp> positionrightlist) {
		this.positionrightlist = positionrightlist;
	}

	public PositionrightManager getPositionrightManager() {
		return positionrightManager;
	}

	public void setPositionrightManager(
			PositionrightManager positionrightManager) {
		this.positionrightManager = positionrightManager;
	}

	public Positionright getPositionright() {
		return positionright;
	}

	public void setPositionright(Positionright positionright) {
		this.positionright = positionright;
	}

	public List getPubliclist() {
		return publiclist;
	}

	public void setPubliclist(List publiclist) {
		this.publiclist = publiclist;
	}

	public Dutyright getDutyright() {
		return dutyright;
	}

	public void setDutyright(Dutyright dutyright) {
		this.dutyright = dutyright;
	}

	public DutyrightManager getDutyrightManager() {
		return dutyrightManager;
	}

	public void setDutyrightManager(DutyrightManager dutyrightManager) {
		this.dutyrightManager = dutyrightManager;
	}

	public Levelright getLevelright() {
		return levelright;
	}

	public void setLevelright(Levelright levelright) {
		this.levelright = levelright;
	}

	public LevelrightManager getLevelrightManager() {
		return levelrightManager;
	}

	public void setLevelrightManager(LevelrightManager levelrightManager) {
		this.levelrightManager = levelrightManager;
	}

	public Roleright getRoleright() {
		return roleright;
	}

	public void setRoleright(Roleright roleright) {
		this.roleright = roleright;
	}

	public RolerightManager getRolerightManager() {
		return rolerightManager;
	}

	public void setRolerightManager(RolerightManager rolerightManager) {
		this.rolerightManager = rolerightManager;
	}

	public UsergrouprightManager getUsergrouprightManager() {
		return usergrouprightManager;
	}

	public void setUsergrouprightManager(
			UsergrouprightManager usergrouprightManager) {
		this.usergrouprightManager = usergrouprightManager;
	}

	public Usergroupright getUsergroupright() {
		return usergroupright;
	}

	public void setUsergroupright(Usergroupright usergroupright) {
		this.usergroupright = usergroupright;
	}

	public Orgright getOrgright() {
		return orgright;
	}

	public void setOrgright(Orgright orgright) {
		this.orgright = orgright;
	}

	public OrgrightManager getOrgrightManager() {
		return orgrightManager;
	}

	public void setOrgrightManager(OrgrightManager orgrightManager) {
		this.orgrightManager = orgrightManager;
	}

	public Personright getPersonright() {
		return personright;
	}

	public void setPersonright(Personright personright) {
		this.personright = personright;
	}

	public PersonrightManager getPersonrightManager() {
		return personrightManager;
	}

	public void setPersonrightManager(PersonrightManager personrightManager) {
		this.personrightManager = personrightManager;
	}

	public Persononmoduleadd getPersononmoduleadd() {
		return persononmoduleadd;
	}

	public void setPersononmoduleadd(Persononmoduleadd persononmoduleadd) {
		this.persononmoduleadd = persononmoduleadd;
	}

	public PersononmoduleaddManager getPersononmoduleaddManager() {
		return persononmoduleaddManager;
	}

	public void setPersononmoduleaddManager(
			PersononmoduleaddManager persononmoduleaddManager) {
		this.persononmoduleaddManager = persononmoduleaddManager;
	}

	public Persononmoduledisable getPersononmoduledisable() {
		return persononmoduledisable;
	}

	public void setPersononmoduledisable(
			Persononmoduledisable persononmoduledisable) {
		this.persononmoduledisable = persononmoduledisable;
	}

	public PersononmoduledisableManager getPersononmoduledisableManager() {
		return persononmoduledisableManager;
	}

	public void setPersononmoduledisableManager(
			PersononmoduledisableManager persononmoduledisableManager) {
		this.persononmoduledisableManager = persononmoduledisableManager;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public List<Dutyright> getDutylist() {
		return dutylist;
	}

	public void setDutylist(List<Dutyright> dutylist) {
		this.dutylist = dutylist;
	}

	public List<Levelright> getLevelrightlist() {
		return levelrightlist;
	}

	public void setLevelrightlist(List<Levelright> levelrightlist) {
		this.levelrightlist = levelrightlist;
	}

	public List<Roleright> getRolerightlist() {
		return rolerightlist;
	}

	public void setRolerightlist(List<Roleright> rolerightlist) {
		this.rolerightlist = rolerightlist;
	}

	public List<Usergroupright> getUsergrouprightlist() {
		return usergrouprightlist;
	}

	public void setUsergrouprightlist(List<Usergroupright> usergrouprightlist) {
		this.usergrouprightlist = usergrouprightlist;
	}

	public List getListall() {
		return listall;
	}

	public void setListall(List listall) {
		this.listall = listall;
	}

	public List<Persononmoduleadd> getPersononmoduleaddlist() {
		return persononmoduleaddlist;
	}

	public void setPersononmoduleaddlist(
			List<Persononmoduleadd> persononmoduleaddlist) {
		this.persononmoduleaddlist = persononmoduleaddlist;
	}

	public List<Persononmoduledisable> getPersononmoduledisablelist() {
		return persononmoduledisablelist;
	}

	public void setPersononmoduledisablelist(
			List<Persononmoduledisable> persononmoduledisablelist) {
		this.persononmoduledisablelist = persononmoduledisablelist;
	}

	public String getCheckboxs() {
		return checkboxs;
	}

	public void setCheckboxs(String checkboxs) {
		this.checkboxs = checkboxs;
	}

	public List<net.itjds.common.org.base.Module> getListright() {
		return listright;
	}

	public void setListright(List<net.itjds.common.org.base.Module> listright) {
		this.listright = listright;
	}

	public List<net.itjds.common.org.base.Module> getListright2() {
		return listright2;
	}

	public void setListright2(List<net.itjds.common.org.base.Module> listright2) {
		this.listright2 = listright2;
	}

	public List<net.itjds.common.org.base.Module> getListrightadd() {
		return listrightadd;
	}

	public void setListrightadd(
			List<net.itjds.common.org.base.Module> listrightadd) {
		this.listrightadd = listrightadd;
	}

	public List<net.itjds.common.org.base.Module> getListrightdisable() {
		return listrightdisable;
	}

	public void setListrightdisable(
			List<net.itjds.common.org.base.Module> listrightdisable) {
		this.listrightdisable = listrightdisable;
	}

	public List<net.itjds.common.org.base.Person> getPersonlistright() {
		return personlistright;
	}

	public void setPersonlistright(
			List<net.itjds.common.org.base.Person> personlistright) {
		this.personlistright = personlistright;
	}

	public List<net.itjds.common.org.base.Person> getPersonlistright2() {
		return personlistright2;
	}

	public void setPersonlistright2(
			List<net.itjds.common.org.base.Person> personlistright2) {
		this.personlistright2 = personlistright2;
	}

	public List<net.itjds.common.org.base.Person> getPersonlistrightadd() {
		return personlistrightadd;
	}

	public void setPersonlistrightadd(
			List<net.itjds.common.org.base.Person> personlistrightadd) {
		this.personlistrightadd = personlistrightadd;
	}

	public List<net.itjds.common.org.base.Person> getPersonlistrightdisable() {
		return personlistrightdisable;
	}

	public void setPersonlistrightdisable(
			List<net.itjds.common.org.base.Person> personlistrightdisable) {
		this.personlistrightdisable = personlistrightdisable;
	}

	public List<Orgright> getOrglist() {
		return orglist;
	}

	public void setOrglist(List<Orgright> orglist) {
		this.orglist = orglist;
	}

	public RoletempManager getRoletempManager() {
		return roletempManager;
	}

	public void setRoletempManager(RoletempManager roletempManager) {
		this.roletempManager = roletempManager;
	}

	public PostiontempManager getPostiontempManager() {
		return postiontempManager;
	}

	public void setPostiontempManager(PostiontempManager postiontempManager) {
		this.postiontempManager = postiontempManager;
	}

	public DutytempManager getDutytempManager() {
		return dutytempManager;
	}

	public void setDutytempManager(DutytempManager dutytempManager) {
		this.dutytempManager = dutytempManager;
	}

	public OrgtempManager getOrgtempManager() {
		return orgtempManager;
	}

	public void setOrgtempManager(OrgtempManager orgtempManager) {
		this.orgtempManager = orgtempManager;
	}

	public LeveltempManager getLeveltempManager() {
		return leveltempManager;
	}

	public void setLeveltempManager(LeveltempManager leveltempManager) {
		this.leveltempManager = leveltempManager;
	}

	public UsergrouptempManager getUsergrouptempManager() {
		return usergrouptempManager;
	}

	public void setUsergrouptempManager(
			UsergrouptempManager usergrouptempManager) {
		this.usergrouptempManager = usergrouptempManager;
	}

	public PersontempManager getPersontempManager() {
		return persontempManager;
	}

	public void setPersontempManager(PersontempManager persontempManager) {
		this.persontempManager = persontempManager;
	}

	public ModuletempManager getModuletempManager() {
		return moduletempManager;
	}

	public void setModuletempManager(ModuletempManager moduletempManager) {
		this.moduletempManager = moduletempManager;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

}
