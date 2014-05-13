package com.kzxd.tzgg.entity;

import java.sql.Connection;
import java.util.Date;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

/**
 * 
 * @author tanrui
 * FDT_OA_NOTICE���Ӧ��ʵ����
 * 
 */

public class notice extends DAO{
	
	String uuid;       //UUID
	String contactperson;     //��ϵ��
	String niticetitle;   //֪ͨ����
	String noticecontent; //֪ͨ����
	Date senddate;       //��������
	String createdept;   //�����˲���
	String createname;   //����������
	String contacttle;  //��ϵ�˵绰
	String issuer;     //ǩ����
	Date issuerdate ;  //ǩ��ʱ��
	String sendrange;  //���ͷ�Χ
	String attachment;    //����
	String personid;   //��ǰ��ID
	String status;   //0����δ�� 1��������
    String sendid;
	String activityInstId;
    
	public String getStatus() {
		return status;
	}
	public void setStatus(String _status) {
		firePropertyChange("status", status, _status);
		status = _status;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		firePropertyChange("uuid", uuid, _uuid);
		uuid = _uuid;
	}
	public String getContactperson() {
		return contactperson;
	}
	public void setContactperson(String _contactperson) {
		firePropertyChange("contactperson", contactperson, _contactperson);
		contactperson = _contactperson;
	}
	public String getNiticetitle() {
		return niticetitle;
	}
	public void setNiticetitle(String _niticetitle) {
		firePropertyChange("niticetitle", niticetitle, _niticetitle);
		niticetitle = _niticetitle;
	}
	public String getNoticecontent() {
		return noticecontent;
	}
	public void setNoticecontent(String _noticecontent) {
		firePropertyChange("noticecontent", noticecontent, _noticecontent);
		noticecontent = _noticecontent;
	}
	public Date getSenddate() {
		return senddate;
	}
	public void setSenddate(Date _senddate) {
		firePropertyChange("senddate", senddate, _senddate);
		senddate = _senddate;
	}
	public String getCreatedept() {
		return createdept;
	}
	public void setCreatedept(String _createdept) {
		firePropertyChange("createdept", createdept, _createdept);
		createdept = _createdept;
	}
	public String getCreatename() {
		return createname;
	}
	public void setCreatename(String _createname) {
		firePropertyChange("createname", createname, _createname);
		createname = _createname;
	}
	public String getContacttle() {
		return contacttle;
	}
	public void setContacttle(String _contacttle) {
		firePropertyChange("contacttle", contacttle, _contacttle);
		contacttle = _contacttle;
	}
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String _issuer) {
		firePropertyChange("issuer", issuer, _issuer);
		issuer = _issuer;
	}
	public Date getIssuerdate() {
		return issuerdate;
	}
	public void setIssuerdate(Date _issuerdate) {
		firePropertyChange("issuerdate", issuerdate, _issuerdate);
		issuerdate = _issuerdate;
	}
	public String getSendrange() {
		return sendrange;
	}
	public void setSendrange(String _sendrange) {
		firePropertyChange("sendrange", sendrange, _sendrange);
		sendrange = _sendrange;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String _attachment) {
		firePropertyChange("attachment", attachment, _attachment);
		attachment = _attachment;
	}
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String _personid) {
		firePropertyChange("personid", personid, _personid);
		personid = _personid;
	}
	
	public String getSendid() {
		return sendid;
	}
	public void setSendid(String _sendid) {
		firePropertyChange("sendid", sendid, _sendid);
		this.sendid = _sendid;
	}
	
	public String getActivityInstId() {
		return activityInstId;
	}
	public void setActivityInstId(String _activityInstId) {
		firePropertyChange("activityInstId", activityInstId, _activityInstId);
		this.activityInstId = _activityInstId;
	}
	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("contactperson","contactperson");
		addField("niticetitle","niticetitle");
		addField("noticecontent","noticecontent");
		addField("senddate","senddate");
		addField("createdept","createdept");
		addField("createname","createname");
		addField("contacttle","contacttle");
		addField("issuer","issuer");
		addField("issuerdate","issuerdate");
		addField("sendrange","sendrange");
		addField("attachment","attachment");
		addField("personid", "personid");
		addField("status", "status");
		addField("sendid","sendid");
		addField("activityInstId","activityinstid");
		addKey("UUID");
		setTableName("FDT_OA_NOTICE");
	}
	public notice() {
		super();
	}
	public notice(Connection connection) {
		super(connection);
	}
	
}
