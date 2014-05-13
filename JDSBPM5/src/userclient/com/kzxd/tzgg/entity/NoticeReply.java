package com.kzxd.tzgg.entity;

import java.sql.Connection;
import java.util.Date;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

/**
 * 
 * @author tanrui
 * FDT_OA_NOTICE表对应的实体类
 * 
 */

public class NoticeReply extends DAO{
	
	String uuid;       //UUID
	String noticeid;
	String personid;
	String personname;
	String content;
	String replyDate;
    String readnoticeid;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		firePropertyChange("uuid", uuid, _uuid);
		uuid = _uuid;
	}
	
	public String getPersonname() {
		return personname;
	}
	public void setPersonname(String _personname) {
		firePropertyChange("personname", personname, _personname);
		this.personname = _personname;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String _content) {
		firePropertyChange("content", content, _content);
		this.content = _content;
	}
	
	
	public String getReadnoticeid() {
		return readnoticeid;
	}
	public void setReadnoticeid(String _readnoticeid) {
		firePropertyChange("readnoticeid", readnoticeid, _readnoticeid);
		this.readnoticeid = _readnoticeid;
	}
	public String getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(String _replyDate) {
		firePropertyChange("replyDate", replyDate, _replyDate);
		this.replyDate = _replyDate;
	}
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String _personid) {
		firePropertyChange("personid", personid, _personid);
		personid = _personid;
	}
	
	public String getNoticeid() {
		return noticeid;
	}
	public void setNoticeid(String _noticeid) {
		firePropertyChange("noticeid", noticeid, _noticeid);
		this.noticeid = _noticeid;
	}
	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("noticeid", "noticeid");
		addField("personname","personname");
		addField("personid", "personid");
		addField("content", "content");
		addField("replyDate", "replydate");
		addField("readnoticeid", "readnoticeid");
		addKey("UUID");
		setTableName("FDT_OA_NOTICEREPLY");
	}
	public NoticeReply() {
		super();
	}
	public NoticeReply(Connection connection) {
		super(connection);
	}
	
}
