package com.kzxd.changepssword.entity;

import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class Changepssword extends DAO{
	
	
	
	
	public Changepssword(String uuid, String usename, String newpssword,
			String oldpssword) {
		super();
		this.uuid = uuid;
		this.usename = usename;
		this.newpssword = newpssword;
		this.oldpssword = oldpssword;
	}


	public Changepssword() {
		super();
	
	}


	public Changepssword(Connection connection) {
		
	}


	String uuid;  // UUID
	String usename;     //用户名
	String newpssword; 
	String oldpssword; //新密码
	
	


	

	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("usename","USEID");
		addField("oldpssword","PASSWORD");
		addKey("UUID");
		setTableName("RO_PERSONACCOUNT");
	}


	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		firePropertyChange("uuid", uuid, _uuid);
		uuid = _uuid;
	}

	public String getUsename() {
		return usename;
	}


	public void setUsename(String _usename) {
		firePropertyChange("usename", usename,_usename);
		this.usename = _usename;
	}


	public String getNewpssword() {
		return newpssword;
	}


	public void setNewpssword(String newpssword) {
	    
		this.newpssword = newpssword;
	}


	public String getOldpssword() {
		return oldpssword;
	}


	public void setOldpssword(String _oldpssword) {
		firePropertyChange("password",oldpssword ,_oldpssword);
		this.oldpssword = _oldpssword;
	}


	
	
}	
