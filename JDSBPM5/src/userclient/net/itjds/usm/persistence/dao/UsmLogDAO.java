package net.itjds.usm.persistence.dao;

import java.sql.Connection;
import java.util.Date;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;


public class UsmLogDAO extends DAO{
	private String uuid;
	private String msm;
	private Date createdate;
	private String person;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		firePropertyChange("uuid",uuid,_uuid);
		uuid = _uuid;
	}
	
	public String getMsm() {
		return msm;
	}
	public void setMsm(String _msm) {
		firePropertyChange("msm",msm,_msm);
		this.msm = _msm;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date _createdate) {
		firePropertyChange("createdate",createdate,_createdate);
		this.createdate = _createdate;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String _person) {
		firePropertyChange("person",person,_person);
		this.person = _person;
	}
	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("createdate","CREATEDATE");
		addField("person","PERSON");
		addField("msm","MSM");
		addKey("UUID");
		setTableName("USMLOG");
	}
	
	public UsmLogDAO(){
		super();
	}
	
	public UsmLogDAO(Connection conn){
		super(conn);
	}
	

}
