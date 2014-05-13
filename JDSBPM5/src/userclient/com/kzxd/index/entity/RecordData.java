package com.kzxd.index.entity;

import java.sql.Connection;
import java.util.Date;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class RecordData extends DAO {
	String uuid;
	String title;
	String content;
	String dkeyword;
	Date createdate;
	String personid;
	int status;
	String rollid;
	String senddept;
	String senddeptid;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		firePropertyChange("uuid", uuid, _uuid);
		this.uuid = _uuid;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String _title) {
		firePropertyChange("title", title, _title);
		this.title = _title;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date _createdate) {
		firePropertyChange("createdate", createdate, _createdate);
		this.createdate = _createdate;
	}
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String _personid) {
		firePropertyChange("personid", personid, _personid);
		this.personid = _personid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRollid() {
		return rollid;
	}
	public void setRollid(String _rollid) {
		firePropertyChange("rollid", rollid, _rollid);
		this.rollid = _rollid;
	}
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String _content) {
		firePropertyChange("content", content, _content);
		this.content = _content;
	}
	
	public String getSenddept() {
		return senddept;
	}
	public void setSenddept(String _senddept) {
		firePropertyChange("senddept", senddept, _senddept);
		this.senddept = _senddept;
	}
	public String getSenddeptid() {
		return senddeptid;
	}
	public void setSenddeptid(String _senddeptid) {
		firePropertyChange("senddeptid", senddeptid, _senddeptid);
		this.senddeptid = _senddeptid;
	}
	
	public String getDkeyword() {
		return dkeyword;
	}
	public void setDkeyword(String _dkeyword) {
		firePropertyChange("dkeyword", dkeyword, _dkeyword);
		this.dkeyword = _dkeyword;
	}
	@Override
	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("createdate", "CREATEDATE");
		addField("personid", "PERSONID");
		addField("title", "TITLE");
		addField("rollid", "ROLLID");
		addField("content", "CONTENT");
		addField("senddept", "SENDDEPT");
		addField("senddeptid", "SENDDEPTID");
		addField("dkeyword", "DKEYWORD");
		setTableName("FDT_OA_RECORDDATA");
		addKey("UUID");
	}
	public RecordData() {
		super();
	}
	public RecordData(Connection connection) {
		super(connection);
	}
		
	
}
