package com.kzxd.xzsp.util;

import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class XzspjkTempBF extends DAO{

	String uuid;
	String xmlstr;
	String xmlstr2;
	String bsnum;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		firePropertyChange("uuid", uuid,_uuid);
		this.uuid = _uuid;
	}
	public String getXmlstr() {
		return xmlstr;
	}
	public void setXmlstr(String _xmlstr) {
		firePropertyChange("xmlstr",xmlstr,_xmlstr);
		this.xmlstr = _xmlstr;
	}
	
	public String getXmlstr2() {
		return xmlstr2;
	}
	public void setXmlstr2(String _xmlstr2) {
		firePropertyChange("xmlstr2",xmlstr2,_xmlstr2);
		this.xmlstr2 = _xmlstr2;
	}
	
	public String getBsnum() {
		return bsnum;
	}
	public void setBsnum(String _bsnum) {
		firePropertyChange("bsnum",bsnum,_bsnum);
		this.bsnum = _bsnum;
	}
	protected void setupFields() throws DAOException {
		
		addField("uuid", "UUID");
		addField("xmlstr","XMLSTR");
		addField("xmlstr2","XMLSTR2");
		addField("bsnum","BSNUM");
		addKey("UUID");
		setTableName("XZSPJK_LSBBF");
	}
	
	public XzspjkTempBF() {
		super();
	}

	public XzspjkTempBF(Connection connection) {
		super(connection);
	}
}
