package com.kzxd.nbyj.dao;

import java.sql.Connection;
import java.util.Date;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class NbyjDAO extends DAO{
	 
	private String uuid;
	private String nbyj;
	private String personname;
	private String org;
	private Date createdate;
	private String  processinst_id;
	private String activityinst_id;
	private String hj;
	public String getHj() {
		return hj;
	}
	public void setHj(String _hj) {
		firePropertyChange("hj", hj, _hj);
		this.hj = _hj;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		firePropertyChange("uuid", uuid, _uuid);
		this.uuid = _uuid;
	}
	public String getNbyj() {
		return nbyj;
	}
	public void setNbyj(String _nbyj) {
		firePropertyChange("nbyj", nbyj, _nbyj);
		this.nbyj = _nbyj;
	}
	public String getPersonname() {
		return personname;
	}
	public void setPersonname(String _personname) {
		firePropertyChange("personname", personname, _personname);
		this.personname = _personname;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String _org) {
		firePropertyChange("org", org, _org);
		this.org = _org;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date _createdate) {
		firePropertyChange("createdate", createdate, _createdate);
		this.createdate = _createdate;
	}
	public String getProcessinst_id() {
		return processinst_id;
	}
	public void setProcessinst_id(String _processinst_id) {
		firePropertyChange("processinst_id", processinst_id, _processinst_id);
		this.processinst_id = _processinst_id;
	}
	public String getActivityinst_id() {
		return activityinst_id;
	}
	public void setActivityinst_id(String _activityinst_id) {
		firePropertyChange("activityinst_id", activityinst_id, _activityinst_id);
		this.activityinst_id = _activityinst_id;
	}
	
	protected void setupFields() throws DAOException {
		
		
		addField("uuid", "UUID");
		addField("hj","HJ");
		addField("nbyj","NBYJ");
		addField("org","ORG");
		addField("personname", "PERSONNAME");
		addField("createdate", "CREATEDATE");
		addField("processinst_id", "PROCESSINST_ID");
		addField("activityinst_id", "ACTIVITYINST_ID");
		addKey("UUID");
		
		setTableName("FDT_OA_NBYJ");
	}
	
	public NbyjDAO() {
		super();
	}
	public NbyjDAO(Connection connection) {
		super(connection);
	}

}
