package com.kzxd.xzsp.util;

import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class Application1 extends DAO {
	private static final long serialVersionUID = 1L;

	private String uuid;
	private String appname;  		
	private String apporg;	 			
	private String cardid;		   		
	private String appdate;				
	private String mobilephone;    		
	private String phone;				
	private String email;				
	private String address;	
	private String authenticateid;
	

	public String getAuthenticateid() {
		return authenticateid;
	}
	public void setAuthenticateid(String _authenticateid) {
		firePropertyChange("authenticateid",authenticateid,_authenticateid);
		this.authenticateid = _authenticateid;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		firePropertyChange("uuid",uuid,_uuid);
		this.uuid = _uuid;
	}
	public String getAppname() {
		return appname;
	}
	public void setAppname(String _appname) {
		firePropertyChange("appname",appname,_appname);
		this.appname = _appname;
	}
	public String getApporg() {
		return apporg;
	}
	public void setApporg(String _apporg) {
		firePropertyChange("apporg",apporg,_apporg);
		this.apporg = _apporg;
	}
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String _cardid) {
		firePropertyChange("cardid",cardid,_cardid);
		this.cardid = _cardid;
	}
	public String getAppdate() {
		return appdate;
	}
	public void setAppdate(String _appdate) {
		firePropertyChange("appdate",appdate,_appdate);
		this.appdate = _appdate;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String _mobilephone) {
		firePropertyChange("mobilephone",mobilephone,_mobilephone);
		this.mobilephone = _mobilephone;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String _phone) {
		firePropertyChange("phone",phone,_phone);
		this.phone = _phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String _email) {
		firePropertyChange("email",email,_email);
		this.email = _email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String _address) {
		firePropertyChange("address",address,_address);
		this.address = _address;
	}

	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("appname","APPNAME");
		addField("apporg","APPORG");
		addField("cardid","CARDID");
		addField("appdate","APPDATE");
		addField("mobilephone","MOBILEPHONE");
		addField("phone","PHONE");
		addField("email","EMAIL");
		addField("address","ADDRESS");
		addField("authenticateid","AUTHENTICATEID");
		setTableName("XZSPJK_APPLICATION");
		addKey("UUID");
	}
	
	public Application1(){
		super();
	}
	
	public Application1(Connection conn){
		super(conn);
	}
	
}
