package net.itjds.usm.persistence.action;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import net.itjds.common.cache.Cache;
import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.OrgManager;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.PersonRole;
import net.itjds.common.org.impl.database.DbOrgManagerImpl;
import net.itjds.common.org.impl.database.DbPerson;
import net.itjds.common.org.impl.database.DbPersonRole;
import net.itjds.common.org.impl.database.DbSubSystemCacheManager;
import net.itjds.j2ee.util.UUID;
import net.itjds.usm.persistence.model.Grprole;
import net.itjds.usm.persistence.model.Orgrole;
import net.itjds.usm.persistence.model.Personrole;
import net.itjds.usm.persistence.model.Positionrole;
import net.itjds.usm.persistence.model.Role;
import net.itjds.usm.persistence.service.GrproleManager;
import net.itjds.usm.persistence.service.OrgroleManager;
import net.itjds.usm.persistence.service.PersonroleManager;
import net.itjds.usm.persistence.service.PositionroleManager;
import net.itjds.usm.persistence.service.RoleManager;
import net.itjds.usm.persistence.service.UsmLog;

import org.appfuse.webapp.action.BaseAction;

public class RoleAction extends BaseAction {
	RoleManager roleManager;
	Role role = new Role();
	String json;
	String roleid;
	String roleidjsonData;
	private String start;
	private String limit;
	String sysid;
	String name;
	String txtCheckValue;
	List<Role> roleList = new ArrayList(0);

	Personrole personrole = new Personrole();
	PersonroleManager personroleManager;
	Positionrole positionrole = new Positionrole();
	PositionroleManager positionroleManager;
	Grprole grprole = new Grprole();
	GrproleManager grproleManager;
	Orgrole orgrole = new Orgrole();
	OrgroleManager orgroleManager;

	public String getTxtCheckValue() {
		return this.txtCheckValue;
	}

	public void setTxtCheckValue(String txtCheckValue) {
		this.txtCheckValue = txtCheckValue;
	}

	public Grprole getGrprole() {
		return this.grprole;
	}

	public void setGrprole(Grprole grprole) {
		this.grprole = grprole;
	}

	public GrproleManager getGrproleManager() {
		return this.grproleManager;
	}

	public void setGrproleManager(GrproleManager grproleManager) {
		this.grproleManager = grproleManager;
	}

	public Positionrole getPositionrole() {
		return this.positionrole;
	}

	public void setPositionrole(Positionrole positionrole) {
		this.positionrole = positionrole;
	}

	public PositionroleManager getPositionroleManager() {
		return this.positionroleManager;
	}

	public void setPositionroleManager(PositionroleManager positionroleManager) {
		this.positionroleManager = positionroleManager;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public RoleManager getRoleManager() {
		return this.roleManager;
	}

	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}

	public String roleSave() {
		UUID uuid = new UUID();
		this.role.setRoleid(String.valueOf(uuid));
		this.role.setCreatetime(new Timestamp(System.currentTimeMillis()));
		this.role.setSysid(this.sysid);
		this.roleList = this.roleManager
				.getByCondition("from Role  order by  roledesc desc");
		if (this.roleList.size() != 0) {
			Role r = (Role) this.roleList.get(0);
			this.role.setRoledesc(Integer
					.valueOf(r.getRoledesc().intValue() + 1));
		} else {
			this.role.setRoledesc(Integer.valueOf(1));
		}
		this.roleManager.save(this.role);
		DbOrgManagerImpl orgManager = (DbOrgManagerImpl) OrgManagerFactory
				.getOrgManager();
		orgManager.cacheManager.getPersonRoleCache().clear();
		SmallBean bean = new SmallBean();
		bean.setMsm("增加角色：" + this.role.getCnname());
		bean.setCreatedate(new Date());
		bean.setUuid((new UUID()).toString());
		UsmLog usm = new UsmLog();
		usm.save(bean);
		return "success";
	}

	public String roleDataIds() {
		String rid = "";
		String strData = "";

		DbOrgManagerImpl orgManager = (DbOrgManagerImpl) OrgManagerFactory
				.getOrgManager();
		PersonRole[] personRoleObjs = orgManager.getPersonRoles();
		if (personRoleObjs.length == 0) {
			return "error";
		}

		for (int i = 0; i < personRoleObjs.length; i++) {
			DbPersonRole dbPersonRole = (DbPersonRole) personRoleObjs[i];
			rid = dbPersonRole.getID();
			if (personRoleObjs.length == i + 1)
				strData = strData + "roleid='" + rid + "'";
			else {
				strData = strData + "roleid='" + rid + "' or ";
			}
		}

		return strData;
	}

	public String getRoleInfo() {
		try {
			String sql = "";
			String str = "";
			if (("".equals(this.name)) || (this.name == null)) {
				sql = "select * from ro_Role  where sysid in (select sysid from ro_Role where "
						+ roleDataIds() + ") ";
				this.roleList = this.roleManager.getBySQL(sql);
			} else {
				sql = "select * from ro_Role  where cnname like '%" + this.name
						+ "%' and sysid in (select sysid from ro_Role where "
						+ roleDataIds() + ")";
				this.roleList = this.roleManager.getBySQL(sql);
			}

			int count = Integer.parseInt(this.start)
					+ Integer.parseInt(this.limit);
			if (count > this.roleList.size())
				this.roleList = this.roleList.subList(Integer
						.parseInt(this.start), this.roleList.size());
			else {
				this.roleList = this.roleList.subList(Integer
						.parseInt(this.start), Integer.parseInt(this.start)
						+ Integer.parseInt(this.limit));
			}

			this.json = ("{totalCount:" + this.roleManager.getBySQL(sql).size() + ",data:[");
			for (Iterator it = this.roleList.iterator(); it.hasNext();) {
				Object[] obj = (Object[]) it.next();

				str = str + "{roleid:'" + obj[1] + "',cnname:'" + obj[3]
						+ "',adminflag:'" + obj[4] + "'," + "createtime:'"
						+ obj[5] + "'," + "roledesc:'" + obj[6] + "'},";
			}

			if (!"".equals(str)) {
				str = str.substring(0, str.length() - 1);
			}
			this.json += str;
			this.json += "]}";
			return "success";
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		return "error";
	}

	public String FindCnname(String roleid) {
		String Cnname = "";
		Statement stmt = null;
		StringBuffer sql = null;
		DBBeanBase dbbase = new DBBeanBase("org");
		Connection conn = dbbase.getConn();
		try {
			stmt = conn.createStatement();
			sql = new StringBuffer();
			sql.append("select * from ro_role t where t.roleid = '");
			sql.append(roleid);
			sql.append("'");
			ResultSet rs = stmt.executeQuery(sql.toString());
			if (rs != null && rs.next()) {
				Cnname = rs.getString("cnname");
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
		return Cnname;

	}

	public String rolePointDel() {
		DbOrgManagerImpl orgManager = (DbOrgManagerImpl) OrgManagerFactory
				.getOrgManager();

		String value = this.roleidjsonData;
		String[] names = value.split("\\,");
		for (int i = 0; i < names.length; i++) {
			SmallBean bean = new SmallBean();
			String cnname = this.FindCnname(names[i]);
			bean.setMsm("删除角色：" + cnname);
			bean.setCreatedate(new Date());
			bean.setUuid((new UUID()).toString());
			UsmLog usm = new UsmLog();
			usm.save(bean);
			Role role = (Role) this.roleManager.get(names[i]);
			orgManager.cacheManager.getPersonRoleCache().remove(
					role.getRoleid());
			this.roleManager.remove(names[i]);

		}

		return "success";
	}

	public String roleModify() {
		try {
			Role rol = (Role) this.roleManager.get(this.roleid);
			rol.setCnname(this.role.getCnname());
			DbPersonRole dbPersonRole = (DbPersonRole) OrgManagerFactory
					.getOrgManager().getPersonRoleByID(
							this.personrole.getRoleid());
			dbPersonRole.setName(this.role.getCnname());
			this.roleManager.save(rol);
			return "success";
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		return "error";
	}

	public String roletreeJson() {
		try {
			System.out.println(this.sysid);
			String res = "";

			DbOrgManagerImpl orgManager = (DbOrgManagerImpl) OrgManagerFactory
					.getOrgManager();

			PersonRole[] personRoleObjs = orgManager.getPersonRoles();
			if (personRoleObjs.length == 0) {
				return "error";
			}

			for (int i = 0; i < personRoleObjs.length; i++) {
				DbPersonRole dbPersonRole = (DbPersonRole) personRoleObjs[i];
				res = res
						+ "{id:'"
						+ dbPersonRole.getID()
						+ "',text:'"
						+ dbPersonRole.getName()
						+ "',cls:'forum',treetype:'fathernodes',iconCls:'icon-forum',leaf:true},";
			}

			if (!"".equals(res)) {
				this.json = res.substring(0, res.length() - 1);
			}
			return "success";
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		return "error";
	}

	public String roleInfo() {
		this.role = ((Role) this.roleManager.get(this.roleid));
		return "success";
	}

	public String getroleDetailInfo() {
		this.role = ((Role) this.roleManager.get(this.roleid));
		return "success";
	}

	public String getRoleNameDetailInfo() {
		this.role = ((Role) this.roleManager.get(this.roleid));
		return "success";
	}

	public String addPersonRoleSave() {
		try {
			String personid = this.roleidjsonData;
			String[] personids = personid.split("\\,");
			for (int i = 0; i < personids.length; i++) {
				this.personrole = new Personrole();
				this.personrole.setPersonid(personids[i]);
				this.personrole.setRoleid(this.roleid);
				List list = this.personroleManager
						.getBySQL(" select * from ro_personRole where personid='"
								+ personids[i]
								+ "' and roleid='"
								+ this.roleid
								+ "'");
				if (list.size() == 0) {
					this.personroleManager.save(this.personrole);
				}

				DbPerson dbperson = (DbPerson) OrgManagerFactory
						.getOrgManager().getPersonByID(
								this.personrole.getPersonid());
				dbperson.addRole(this.roleid);
				DbPersonRole dbPersonRole = (DbPersonRole) OrgManagerFactory
						.getOrgManager().getPersonRoleByID(this.roleid);
				dbPersonRole.addPerson(this.personrole.getPersonid());
				SmallBean bean = new SmallBean();
					bean.setMsm("在" + this.FindCnname(this.roleid)+"角色添加人员："+dbperson.getName());
					bean.setCreatedate(new Date());
					bean.setUuid((new UUID()).toString());
					UsmLog usm = new UsmLog();
					usm.save(bean);
			}

			return "success";
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		return "error";
	}

	public String lookOverPersonRoleInfo() {
		String sql = "";
		try {
			if (("".equals(this.name)) || (this.name == null)) {
				sql = "select ro_personrole.uuid, ro_person.personid, ro_person.cnname,ro_person.enname, ro_role.cnname rcnname,ro_personrole.indextype indextype from ro_person, ro_personrole,ro_role  where ro_person.personid = ro_personrole.personid and ro_role.roleid = ro_personrole.roleid   and ro_personrole.roleid='"
						+ this.roleid + "' order by ro_personrole.indextype";
				this.roleList = this.roleManager.getBySQL(sql);
			} else {
				sql = "select ro_personrole.uuid, ro_person.personid, ro_person.cnname,ro_person.enname, ro_role.cnname rcnname from ro_person, ro_personrole,ro_role,ro_personrole.indextype indextype  where ro_person.personid = ro_personrole.personid and ro_role.roleid = ro_personrole.roleid   and ro_personrole.roleid='"
						+ this.roleid
						+ "' and ro_person.cnname like  '%"
						+ this.name + "%' order by ro_personrole.indextype";
				this.roleList = this.roleManager.getBySQL(sql);
			}

			int count = Integer.parseInt(this.start)
					+ Integer.parseInt(this.limit);
			if (count > this.roleList.size())
				this.roleList = this.roleList.subList(Integer
						.parseInt(this.start), this.roleList.size());
			else {
				this.roleList = this.roleList.subList(Integer
						.parseInt(this.start), Integer.parseInt(this.start)
						+ Integer.parseInt(this.limit));
			}

			String str = "";
			this.json = ("{totalCount:" + this.roleManager.getBySQL(sql).size() + ",data:[");
			for (Iterator iter = this.roleList.iterator(); iter.hasNext();) {
				Object[] obj = (Object[]) iter.next();
				str = str + "{uuid:'" + obj[0] + "',personid:'" + obj[1]
						+ "',cnname:'" + obj[2] + "',enname:'" + obj[3]
						+ "',rcnname:'" + obj[4] + "',indextype:'" + obj[5]
						+ "'},";
			}
			if (!"".equals(str)) {
				str = str.substring(0, str.length() - 1);
			}
			this.json += str.replaceAll("null", "");
			this.json += "]}";
			return "success";
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		return "error";
	}

	public String positionRoleDel() {
		try {
			String value = this.roleidjsonData;
			String[] names = value.split("\\,");
			for (int i = 0; i < names.length; i++) {
				Positionrole personrole = (Positionrole) this.positionroleManager
						.get(names[i]);

				this.positionroleManager.remove(names[i]);
			}

			return "success";
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return "error";
	}

	public String rolePersonSaveSort() {
		String[] arr = this.txtCheckValue.split(",");
		for (int i = 0; i < arr.length; i++) {
			this.personrole = ((Personrole) this.personroleManager.get(arr[i]));
			this.personrole.setIndextype(Integer.valueOf(i + 10001));

			this.personroleManager.save(this.personrole);
		}
		return "success";
	}

	public String addPositionRoleSave() {
		try {
			String positionid = this.roleidjsonData;
			String[] positionids = positionid.split("\\,");
			for (int i = 0; i < positionids.length; i++) {
				this.positionrole = new Positionrole();
				this.positionrole.setPositionid(positionids[i]);
				this.positionrole.setRoleid(this.roleid);

				this.positionroleManager.save(this.positionrole);
			}

			return "success";
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		return "error";
	}

	public String lookOverPositionRoleInfo() {
		String sql = "";
		try {
			if (("".equals(this.name)) || (this.name == null)) {
				sql = "select ro_positionrole.uuid, ro_position.positionid, ro_position.positionname,  ro_role.cnname rcnname from ro_position, ro_positionrole,ro_role   where ro_position.positionid = ro_positionrole.positionid and ro_role.roleid = ro_positionrole.roleid and ro_positionrole.roleid='"
						+ this.roleid + "'";
				this.roleList = this.roleManager.getBySQL(sql);
			} else {
				sql = "select ro_positionrole.uuid, ro_position.positionid, ro_position.positionname,  ro_role.cnname rcnname from ro_position, ro_positionrole,ro_role   where ro_position.positionid = ro_positionrole.positionid and ro_role.roleid = ro_positionrole.roleid and ro_positionrole.roleid='"
						+ this.roleid
						+ "' and ro_position.positionname like '%"
						+ this.name
						+ "%'";
				this.roleList = this.roleManager.getBySQL(sql);
			}

			int count = Integer.parseInt(this.start)
					+ Integer.parseInt(this.limit);
			if (count > this.roleList.size())
				this.roleList = this.roleList.subList(Integer
						.parseInt(this.start), this.roleList.size());
			else {
				this.roleList = this.roleList.subList(Integer
						.parseInt(this.start), Integer.parseInt(this.start)
						+ Integer.parseInt(this.limit));
			}
			String str = "";
			this.json = ("{totalCount:" + this.roleManager.getBySQL(sql).size() + ",data:[");
			for (Iterator iter = this.roleList.iterator(); iter.hasNext();) {
				Object[] obj = (Object[]) iter.next();
				str = str + "{uuid:'" + obj[0] + "',positionid:'" + obj[1]
						+ "',cnname:'" + obj[2] + "',rcnname:'" + obj[3]
						+ "'},";
			}
			if (!"".equals(str)) {
				str = str.substring(0, str.length() - 1);
			}
			this.json += str;
			this.json += "]}";
			return "success";
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		return "error";
	}

	public String userGroupRoleDel() {
		try {
			String value = this.roleidjsonData;
			String[] names = value.split("\\,");
			for (int i = 0; i < names.length; i++) {
				Grprole grprole = (Grprole) this.grproleManager.get(names[i]);

				this.grproleManager.remove(names[i]);
			}

			return "success";
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return "error";
	}

	public String getRolePositionNameDetailInfo() {
		this.role = ((Role) this.roleManager.get(this.roleid));
		return "success";
	}

	public String getRoleUserGroupNameDetailInfo() {
		this.role = ((Role) this.roleManager.get(this.roleid));
		return "success";
	}

	public String lookOverUserGroupRoleInfo() {
		String sql = "";
		try {
			if (("".equals(this.name)) || (this.name == null)) {
				sql = "select ro_grprole.uuid, ro_usergroup.grpid, ro_usergroup.grpname, ro_role.cnname rcnname from ro_usergroup, ro_grprole,ro_role  where ro_usergroup.grpid = ro_grprole.grpid and ro_role.roleid = ro_grprole.roleid   and ro_grprole.roleid='"
						+ this.roleid + "'";
				this.roleList = this.roleManager.getBySQL(sql);
			} else {
				sql = "select ro_grprole.uuid, ro_usergroup.grpid, ro_usergroup.grpname, ro_role.cnname rcnname from ro_usergroup, ro_grprole,ro_role  where ro_usergroup.grpid = ro_grprole.grpid and ro_role.roleid = ro_grprole.roleid   and ro_grprole.roleid='"
						+ this.roleid
						+ "' and ro_usergroup.grpname like '%"
						+ this.name + "%'";
				this.roleList = this.roleManager.getBySQL(sql);
			}

			int count = Integer.parseInt(this.start)
					+ Integer.parseInt(this.limit);
			if (count > this.roleList.size())
				this.roleList = this.roleList.subList(Integer
						.parseInt(this.start), this.roleList.size());
			else {
				this.roleList = this.roleList.subList(Integer
						.parseInt(this.start), Integer.parseInt(this.start)
						+ Integer.parseInt(this.limit));
			}
			String str = "";
			this.json = ("{totalCount:" + this.roleManager.getBySQL(sql).size() + ",data:[");
			for (Iterator iter = this.roleList.iterator(); iter.hasNext();) {
				Object[] obj = (Object[]) iter.next();
				str = str + "{uuid:'" + obj[0] + "',grpid:'" + obj[1]
						+ "',grpname:'" + obj[2] + "',rcnname:'" + obj[3]
						+ "'},";
			}
			if (!"".equals(str)) {
				str = str.substring(0, str.length() - 1);
			}
			this.json += str;
			this.json += "]}";
			return "success";
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		return "error";
	}

	public String addUserGroupRoleSave() {
		try {
			String grpid = this.roleidjsonData;
			String[] grpids = grpid.split("\\,");
			for (int i = 0; i < grpids.length; i++) {
				this.grprole = new Grprole();
				this.grprole.setGrpid(grpids[i]);
				this.grprole.setRoleid(this.roleid);
				this.grprole.setCreatetime(new Timestamp(System
						.currentTimeMillis()));

				this.grproleManager.save(this.grprole);
			}
			return "success";
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		return "error";
	}

	public String getRoleOrgNameDetailInfo() {
		this.role = ((Role) this.roleManager.get(this.roleid));
		return "success";
	}

	public String addOrgRoleSave() {
		try {
			String orgid = this.roleidjsonData;
			String[] orgids = orgid.split("\\,");
			for (int i = 0; i < orgids.length; i++) {
				this.orgrole = new Orgrole();
				this.orgrole.setOrgid(orgids[i]);
				this.orgrole.setRoleid(this.roleid);

				this.orgrole.setCreatetime(new Timestamp(System
						.currentTimeMillis()));
				this.orgroleManager.save(this.orgrole);
			}
			return "success";
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		return "error";
	}

	public String lookOverOrgRoleInfo() {
		String sql = "";
		try {
			if (("".equals(this.name)) || (this.name == null)) {
				sql = "select ro_orgrole.uuid, ro_org.orgid, ro_org.cnname, ro_role.cnname rcnname from ro_org, ro_orgrole,ro_role  where ro_org.orgid = ro_orgrole.orgid and ro_role.roleid = ro_orgrole.roleid   and ro_orgrole.roleid='"
						+ this.roleid + "'";
				this.roleList = this.roleManager.getBySQL(sql);
			} else {
				sql = "select ro_orgrole.uuid, ro_org.orgid, ro_org.cnname, ro_role.cnname rcnname from ro_org, ro_orgrole,ro_role  where ro_org.orgid = ro_orgrole.orgid and ro_role.roleid = ro_orgrole.roleid   and ro_orgrole.roleid='"
						+ this.roleid
						+ "' and ro_org.cnname like  '%"
						+ this.name + "%'";
				this.roleList = this.roleManager.getBySQL(sql);
			}

			int count = Integer.parseInt(this.start)
					+ Integer.parseInt(this.limit);
			if (count > this.roleList.size())
				this.roleList = this.roleList.subList(Integer
						.parseInt(this.start), this.roleList.size());
			else {
				this.roleList = this.roleList.subList(Integer
						.parseInt(this.start), Integer.parseInt(this.start)
						+ Integer.parseInt(this.limit));
			}

			String str = "";
			this.json = ("{totalCount:" + this.roleManager.getBySQL(sql).size() + ",data:[");
			for (Iterator iter = this.roleList.iterator(); iter.hasNext();) {
				Object[] obj = (Object[]) iter.next();
				str = str + "{uuid:'" + obj[0] + "',orgid:'" + obj[1]
						+ "',cnname:'" + obj[2] + "',rcnname:'" + obj[3]
						+ "'},";
			}
			if (!"".equals(str)) {
				str = str.substring(0, str.length() - 1);
			}
			this.json += str;
			this.json += "]}";
			return "success";
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		return "error";
	}

	public String orgRoleDel() {
		try {
			String value = this.roleidjsonData;
			String[] names = value.split("\\,");
			for (int i = 0; i < names.length; i++) {
				Orgrole orgrole = (Orgrole) this.orgroleManager.get(names[i]);

				this.orgroleManager.remove(names[i]);
			}

			return "success";
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return "error";
	}

	public String personRoleDel() {
		try {
			String value = this.roleidjsonData;
			String[] names = value.split("\\,");
			for (int i = 0; i < names.length; i++) {
				Personrole personrole = (Personrole) this.personroleManager
						.get(names[i]);

				DbPerson dbperson = (DbPerson) OrgManagerFactory
						.getOrgManager()
						.getPersonByID(personrole.getPersonid());
				dbperson.getRoleIdList().remove(personrole.getRoleid());
				DbPersonRole dbPersonRole = (DbPersonRole) OrgManagerFactory
						.getOrgManager().getPersonRoleByID(
								personrole.getRoleid());
				dbPersonRole.getPersonList().remove(personrole.getPersonid());
				SmallBean bean = new SmallBean();
				bean.setMsm("在" + this.FindCnname(personrole.getRoleid())+"角色删除人员："+dbperson.getName());
				bean.setCreatedate(new Date());
				bean.setUuid((new UUID()).toString());
				UsmLog usm = new UsmLog();
				usm.save(bean);
				this.personroleManager.remove(names[i]);
			}

			return "success";
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return "error";
	}

	public String getJson() {
		return this.json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public List<Role> getRoleList() {
		return this.roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public String getRoleid() {
		return this.roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getRoleidjsonData() {
		return this.roleidjsonData;
	}

	public void setRoleidjsonData(String roleidjsonData) {
		this.roleidjsonData = roleidjsonData;
	}

	public Personrole getPersonrole() {
		return this.personrole;
	}

	public void setPersonrole(Personrole personrole) {
		this.personrole = personrole;
	}

	public PersonroleManager getPersonroleManager() {
		return this.personroleManager;
	}

	public void setPersonroleManager(PersonroleManager personroleManager) {
		this.personroleManager = personroleManager;
	}

	public Orgrole getOrgrole() {
		return this.orgrole;
	}

	public void setOrgrole(Orgrole orgrole) {
		this.orgrole = orgrole;
	}

	public OrgroleManager getOrgroleManager() {
		return this.orgroleManager;
	}

	public void setOrgroleManager(OrgroleManager orgroleManager) {
		this.orgroleManager = orgroleManager;
	}

	public String getLimit() {
		return this.limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getStart() {
		return this.start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSysid() {
		return this.sysid;
	}

	public void setSysid(String sysid) {
		this.sysid = sysid;
	}
}