package com.kzxd.tzgg.entity;

import java.sql.Connection;
import java.util.Date;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class UnreadReceiveNotice extends DAO{
	String uuid;
	String title;
	String sendperson;
	Date sendtime;
	String sendpersondept;
	String noticeid;
	String personid;
	String personname;
	String persondept;
	Integer qxflag;
	Integer signReceive;
	Date inceptTime;
	
	public Integer getQxflag() {
		return qxflag;
	}
	public void setQxflag(Integer _qxflag) {
		firePropertyChange("qxflag", qxflag, _qxflag);
		this.qxflag = _qxflag;
	}
	
	
	
	public String getPersondept() {
		return persondept;
	}
	public void setPersondept(String _persondept) {
		firePropertyChange("persondept", persondept, _persondept);
		this.persondept = _persondept;
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
	public String getSendperson() {
		return sendperson;
	}
	public void setSendperson(String _sendperson) {
		firePropertyChange("sendperson", sendperson, _sendperson);
		this.sendperson = _sendperson;
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
	
	
	
	public String getSendpersondept() {
		return sendpersondept;
	}
	public void setSendpersondept(String _sendpersondept) {
		firePropertyChange("sendpersondept", sendpersondept, _sendpersondept);
		this.sendpersondept = _sendpersondept;
	}
	
	
	public Integer getSignReceive() {
		return signReceive;
	}
	public void setSignReceive(Integer _signReceive) {
		firePropertyChange("signReceive", signReceive, _signReceive);
		this.signReceive = _signReceive;
	}
	
	
	public Date getInceptTime() {
		return inceptTime;
	}
	public void setInceptTime(Date _inceptTime) {
		firePropertyChange("inceptTime", inceptTime, _inceptTime);
		this.inceptTime = _inceptTime;
	}
	
	
	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("noticeid","noticeid");
		addField("personid","personid");
		addField("personname","personname");
		addField("title", "title");
		addField("sendtime", "sendtime");
		addField("sendperson","sendperson");
		addField("sendpersondept","sendpersondept");
		addField("persondept","persondept");
		addField("qxflag","qxflag");
		addField("signReceive","sign_receive");
		addField("inceptTime","incepttime");
		addKey("UUID");
		setTableName("FDT_OA_UNREADNOTICE");
	}
	public UnreadReceiveNotice() {
		super();
	}
	public UnreadReceiveNotice(Connection connection) {
		super(connection);
	}
	
	
	

}
