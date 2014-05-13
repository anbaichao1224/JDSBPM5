package com.kzxd.db.action;

import java.sql.Connection;
import java.util.Date;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

@SuppressWarnings("serial")
public class OaDAO  extends DAO {
	private String uuid;
	private String username;
	private String password;
	private String dw;
	private String deptid;
	private Integer dworder;
	private String msm;
	private Date createdate;
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String _deptid) {
		firePropertyChange("deptid", deptid, _deptid);
		this.deptid = _deptid;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		firePropertyChange("uuid", uuid, _uuid);
		this.uuid = _uuid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String _username) {
		firePropertyChange("username", username, _username);
		this.username = _username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String _password) {
		firePropertyChange("password", password, _password);
		this.password = _password;
	}
	public String getDw() {
		return dw;
	}
	public void setDw(String _dw) {
		firePropertyChange("dw", dw, _dw);
		this.dw = _dw;
	}
	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("dw", "DW");
		addField("dworder", "DWORDER");
		addField("username", "USERNAME");
		addField("password", "PASSWORD");
		addField("deptid", "DEPTID");
		setTableName("OA");
		addKey("UUID");
	}

	public OaDAO(Connection conn) {
		super(conn);
	}

	public OaDAO() {
		super();
	}
	public Integer getDworder() {
		return dworder;
	}
	public void setDworder(Integer _dworder) {
		firePropertyChange("dworder", dworder, _dworder);
		this.dworder = _dworder;
	}
	public String getMsm() {
		return msm;
	}
	public void setMsm(String msm) {
		this.msm = msm;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	
}
