package com.kzxd.newkaoqin.entity;

import java.sql.Connection;
import java.util.Date;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class QingJiaMingXiDAO extends DAO {

	String uuid;
	String ck;
	String xm;
	Date qingjiariqi;
	String qjlx;
	String personid;
	
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		firePropertyChange("uuid", uuid,_uuid);
		this.uuid = _uuid;
	}
	public String getCk() {
		return ck;
	}
	public void setCk(String _ck) {
		firePropertyChange("ck", ck,_ck);
		this.ck = _ck;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String _xm) {
		firePropertyChange("xm", xm,_xm);
		this.xm = _xm;
	}
	public Date getQingjiariqi() {
		return qingjiariqi;
	}
	public void setQingjiariqi(Date _qingjiariqi) {
		firePropertyChange("qingjiariqi", qingjiariqi,_qingjiariqi);
		this.qingjiariqi = _qingjiariqi;
	}
	public String getQjlx() {
		return qjlx;
	}
	public void setQjlx(String _qjlx) {
		firePropertyChange("qjlx", qjlx,_qjlx);
		this.qjlx = _qjlx;
	}
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String _personid) {
		firePropertyChange("personid", personid,_personid);
		this.personid = _personid;
	}
	protected void setupFields() throws DAOException {
		
		addField("uuid", "UUID");
		addField("ck","CK");
		addField("xm","XM");
		addField("qingjiariqi","QINGJIARIQI");
		addField("qjlx","QJLX");
		addField("personid","PERSONID");
		addKey("UUID");
		setTableName("FDT_OA_QINGJIAMINGXI");
	}
	public QingJiaMingXiDAO() {
		super();
	}

	public QingJiaMingXiDAO(Connection connection) {
		super(connection);
	}
	
}
