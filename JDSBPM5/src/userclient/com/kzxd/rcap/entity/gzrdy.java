package com.kzxd.rcap.entity;

import java.sql.Connection;
import java.util.Date;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

/**
 * 
 * @author tanrui
 * KZXD_GZRDY表对应的实体类
 * 
 */

public class gzrdy extends DAO{
    	
	String uuid;       //UUID
	Date dateFrom;     //起始时间
	Date dateTo;       //截止时间
	String rzType;     //类型
	String rzContent;  //内容
	
	
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
	
	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("dateFrom","DateFrom");
		addField("dateTo","DateTo");
		addField("rzType","RzType");
		addField("rzContent","RzContent");
		addKey("UUID");
		setTableName("KZXD_GZRDY");
	}

	public gzrdy() {
		super();
	}

	public gzrdy(Connection connection) {
		super(connection);
	}
	
}
