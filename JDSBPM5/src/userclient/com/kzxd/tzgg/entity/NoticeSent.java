package com.kzxd.tzgg.entity;

import java.sql.Connection;
import java.util.Date;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class NoticeSent extends DAO {
	
	String uuid;
	String noticeid;
	String personid;
	String title;
	Date sendtime;
	String personname;
	Integer qxflag;
	Integer receivecount;
	Integer notreceivecount;


	
	
	public Integer getQxflag() {
		return qxflag;
	}
	public void setQxflag(Integer _qxflag) {
		firePropertyChange("qxflag", qxflag, _qxflag);
		this.qxflag = _qxflag;
	}
	public String getPersonname() {
		return personname;
	}
	public void setPersonname(String _personname) {
	firePropertyChange("personname", personname, _personname);
		this.personname = _personname;
	}
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String _personid) {
		firePropertyChange("personid", personid, _personid);
		this.personid = _personid;
	}
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
	public Date getSendtime() {
		return sendtime;
	}
	public void setSendtime(Date _sendtime) {
		firePropertyChange("sendtime", sendtime, _sendtime);
		this.sendtime = _sendtime;
	}
	
	public String getNoticeid() {
		return noticeid;
	}
	public void setNoticeid(String _noticeid) {
		firePropertyChange("noticeid", noticeid, _noticeid);
		this.noticeid = _noticeid;
	}

	public Integer getReceivecount() {
		return receivecount;
	}
	public void setReceivecount(Integer receivecount) {
		firePropertyChange("receivecount", receivecount, receivecount);
		this.receivecount = receivecount;
	}
	public Integer getNotreceivecount() {
		return notreceivecount;
	}
	public void setNotreceivecount(Integer notreceivecount) {
		firePropertyChange("notreceivecount", notreceivecount, notreceivecount);
		this.notreceivecount = notreceivecount;
	}

	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("noticeid","noticeid");
		addField("personid","personid");
		addField("title", "title");
		addField("sendtime", "sendtime");
		addField("personname","personname");
		addField("qxflag","qxflag");
		addField("receivecount","RECEIVECOUNT");
		addField("notreceivecount","NOTRECEIVECOUNT");
		addKey("UUID");
		setTableName("FDT_OA_SENT");
	}
	public NoticeSent() {
		super();
	}
	public NoticeSent(Connection connection) {
		super(connection);
	}
	

}
