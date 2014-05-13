package com.kzxd.rcap.entity;

import java.sql.Connection;
import java.util.Date;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

/**
 * 
 * @author tanrui
 * KZXD_GZRZ表对应的实体类
 * 
 */

public class gzrz extends DAO{
    	
	String uuid;       //UUID
	Date dateFrom;     //日志起始时间
	Date dateTo;       //日志截止时间
	String rzType;     //日志类型
	String rzContent;  //日志内容
	String personid;    //人名ID
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String _uuid) {
		firePropertyChange("uuid", uuid, _uuid);
		uuid = _uuid;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date _dateFrom) {
		firePropertyChange("dateFrom", dateFrom, _dateFrom);
		dateFrom = _dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date _dateTo) {
		firePropertyChange("dateTo", dateTo, _dateTo);
		dateTo = _dateTo;
	}

	public String getRzType() {
		return rzType;
	}

	public void setRzType(String _rzType) {
		firePropertyChange("rzType", rzType, _rzType);
		rzType = _rzType;
	}

	public String getRzContent() {
		return rzContent;
	}

	public void setRzContent(String _rzContent) {
		firePropertyChange("rzContent", rzContent, _rzContent);
		rzContent = _rzContent;
	}
	
	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String _personid) {
		firePropertyChange("personid", personid, _personid);
		personid = _personid;
	}

	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("dateFrom","DateFrom");
		addField("dateTo","DateTo");
		addField("rzType","RzType");
		addField("rzContent","RzContent");
		addField("personid", "personid");
		addKey("UUID");
		setTableName("KZXD_GZRZ");
	}

	public gzrz() {
		super();
		
	}

	public gzrz(Connection connection) {
		super(connection);
		
	}
	
	
	
}
