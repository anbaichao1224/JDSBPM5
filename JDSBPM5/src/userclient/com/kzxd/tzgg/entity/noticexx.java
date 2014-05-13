package com.kzxd.tzgg.entity;

import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

/**
 * 
 * @author tanrui
 * FDT_OA_NOTICEXX表对应的实体类
 * 
 */

public class noticexx extends DAO{
	
	String uuid;       //UUID
	String noticeid;     //通知表ID
	String sendpersonid;   //接收人ID
	String sendpersonname;

	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		firePropertyChange("uuid", uuid, _uuid);
		uuid = _uuid;
	}
	public String getNoticeid() {
		return noticeid;
	}
	public void setNoticeid(String _noticeid) {
		firePropertyChange("noticeid", noticeid, _noticeid);
		noticeid = _noticeid;
	}
	public String getSendpersonid() {
		return sendpersonid;
	}
	public void setSendpersonid(String _sendpersonid) {
		firePropertyChange("sendpersonid", sendpersonid, _sendpersonid);
		sendpersonid = _sendpersonid;
	}
	
	public String getSendpersonname() {
		return sendpersonname;
	}
	public void setSendpersonname(String _sendpersonname) {
		firePropertyChange("sendpersonname", sendpersonname, _sendpersonname);
		this.sendpersonname = _sendpersonname;
	}
	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("noticeid","noticeid");
		addField("sendpersonid", "sendpersonid");
		addField("sendpersonname", "sendpersonname");
		addKey("UUID");
		setTableName("FDT_OA_NOTICEXX");
	}
	public noticexx() {
		super();
	}
	public noticexx(Connection connection) {
		super(connection);
	}
	
}
