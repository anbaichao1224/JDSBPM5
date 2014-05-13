package com.kzxd.rcap.entity;

import java.sql.Connection;
import java.util.Date;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

/**
 * 
 * @author tanrui
 * KZXD_RCAP���Ӧ��ʵ����
 * 
 */

public class rcap extends DAO{
    	
	String uuid;       //UUID
	Date dateFrom;     //�ճ���ʼʱ��
	Date dateTo;       //�ճ̽�ֹʱ��
	String rzTitle;     //�ճ̱���
	String rzContent;  //�ճ�����
	String personid;   //����id
	
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

	public String getRzTitle() {
		return rzTitle;
	}

	public void setRzTitle(String _rzTitle) {
		firePropertyChange("rzTitle", rzTitle, _rzTitle);
		rzTitle = _rzTitle;
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
		addField("rzTitle","RcTitle");
		addField("rzContent","RzContent");
		addField("personid", "personid");
		addKey("UUID");
		setTableName("KZXD_RCAP");
	}

	public rcap() {
		super();
	}

	public rcap(Connection connection) {
		super(connection);
	}
	
}
