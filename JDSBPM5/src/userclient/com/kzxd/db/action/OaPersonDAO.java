package com.kzxd.db.action;

import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

@SuppressWarnings("serial")
public class OaPersonDAO  extends DAO {
	private String uuid;
	private String username;
	private String password;
	private String user;
	private String flag;
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
	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("user", "USER");
		addField("flag", "FLAG");
		addField("username", "USERNAME");
		addField("password", "PASSWORD");
		setTableName("OA_person");
		addKey("UUID");
	}

	public OaPersonDAO(Connection conn) {
		super(conn);
	}

	public OaPersonDAO() {
		super();
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
