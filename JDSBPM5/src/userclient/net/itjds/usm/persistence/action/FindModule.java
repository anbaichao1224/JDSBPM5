package net.itjds.usm.persistence.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.itjds.common.database.DBBeanBase;

import org.appfuse.webapp.action.BaseAction;

import com.kzxd.db.action.OaBean;

public class FindModule extends BaseAction {
	private String personId;
	public List<Bean> Cnnamelist = new ArrayList<Bean>();
	public List<Bean> publiclist = new ArrayList<Bean>();
	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String execute() {
		List<Bean> roleList = this.FindRoleId(personId);
		for (int i = 0; i < roleList.size(); i++) {
			String roleId = roleList.get(i).getRoleid();
			List<Bean> RoleName = this.FindRoleCnname(roleId);
			for (int j = 0; j < RoleName.size(); j++) {
				String Cnname = RoleName.get(j).getCnname();
				Bean bean = new Bean();
				bean.setCnname(Cnname);
				Cnnamelist.add(bean);
			}
			/*List<Bean> ModList = this.FindModId(roleId);
			for (int j = 0; j < ModList.size(); j++) {
				String ModId = ModList.get(j).getModuleid();
				String Cnname = this.FindCnname(ModId);
				Bean bean = new Bean();
				bean.setCnname(Cnname);
				Cnnamelist.add(bean);
			}*/
		}
		//publiclist = this.FindPublicList();
		publiclist = this.FindAllRoleCnname();
		return SUCCESS;

	}

	public List<Bean> FindRoleId(String personId) {
		List<Bean> beanlist = new ArrayList<Bean>();
		Statement stmt = null;
		StringBuffer sql = null;
		DBBeanBase dbbase = new DBBeanBase("org");
		Connection conn = dbbase.getConn();
		try {
			stmt = conn.createStatement();
			sql = new StringBuffer();
			sql.append("select * from ro_personrole t where t.personid ='");
			sql.append(personId);
			sql.append("'");
			ResultSet rs = stmt.executeQuery(sql.toString());
			if (rs != null ) {
				while (rs.next()) {
				Bean bean = new Bean();
				bean.setRoleid(rs.getString("roleid"));
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
	public List<Bean> FindAllRoleCnname() {
		List<Bean> beanlist = new ArrayList<Bean>();
		Statement stmt = null;
		StringBuffer sql = null;
		DBBeanBase dbbase = new DBBeanBase("org");
		Connection conn = dbbase.getConn();
		try {
			stmt = conn.createStatement();
			sql = new StringBuffer();
			sql.append("select * from ro_role t ");
			ResultSet rs = stmt.executeQuery(sql.toString());
			if (rs != null ) {
				while (rs.next()) {
				Bean bean = new Bean();
				bean.setCnname(rs.getString("cnname"));
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
	public List<Bean> FindRoleCnname(String roleId) {
		List<Bean> beanlist = new ArrayList<Bean>();
		Statement stmt = null;
		StringBuffer sql = null;
		DBBeanBase dbbase = new DBBeanBase("org");
		Connection conn = dbbase.getConn();
		try {
			stmt = conn.createStatement();
			sql = new StringBuffer();
			sql.append("select * from ro_role t where t.roleid ='");
			sql.append(roleId);
			sql.append("'");
			ResultSet rs = stmt.executeQuery(sql.toString());
			if (rs != null ) {
				while (rs.next()) {
				Bean bean = new Bean();
				bean.setCnname(rs.getString("cnname"));
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
	public List<Bean> FindPublicList() {
		List<Bean> PublicList = new ArrayList<Bean>();
		Statement stmt = null;
		StringBuffer sql = null;
		DBBeanBase dbbase = new DBBeanBase("org");
		Connection conn = dbbase.getConn();
		try {
			stmt = conn.createStatement();
			sql = new StringBuffer();
			sql.append("select * from ro_module t where t.needright =");
			sql.append(0);
			ResultSet rs = stmt.executeQuery(sql.toString());
			if (rs != null ) {
				while (rs.next()) {
				Bean bean = new Bean();
				bean.setCnname(rs.getString("cnname"));
				PublicList.add(bean);
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
		return PublicList;

	}
	public List<Bean> FindModId(String roleId) {
		List<Bean> beanlist = new ArrayList<Bean>();
		Statement stmt = null;
		StringBuffer sql = null;
		DBBeanBase dbbase = new DBBeanBase("org");
		Connection conn = dbbase.getConn();
		try {
			stmt = conn.createStatement();
			sql = new StringBuffer();
			sql.append("select * from ro_roleright t where t.roleid = '");
			sql.append(roleId);
			sql.append("'");
			ResultSet rs = stmt.executeQuery(sql.toString());
			if (rs != null ) {
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
	public String FindCnname(String ModId) {
		String Cnname="";
		Statement stmt = null;
		StringBuffer sql = null;
		DBBeanBase dbbase = new DBBeanBase("org");
		Connection conn = dbbase.getConn();
		try {
			stmt = conn.createStatement();
			sql = new StringBuffer();
			sql.append("select * from ro_module t where t.moduleid = '");
			sql.append(ModId);
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
}
