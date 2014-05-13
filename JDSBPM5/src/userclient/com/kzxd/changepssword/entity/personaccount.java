package com.kzxd.changepssword.entity;

import java.sql.Connection;
import java.sql.Timestamp;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;


public  class personaccount extends DAO{
	  
	

	private String lastloginip;
	 private String personid;
	 private Timestamp lastlogindate;
	 private String passanswer;
	 private Timestamp createtime;
	 private Integer loginfailnum;
	 private Integer accountttl;
	 private String passquestion;
	 private Integer flag;
	 private String password;
	 private String accountstat;
	 private String uuid;
	 private String userid;
	 private String rtxaccount;
	 protected void setupFields() throws DAOException {
			addField("lastloginip", "LASTLOGINIP");
			addField("personid","PERSONID");
			addField("lastlogindate","LASTLOGINDATE");
			addField("passanswer", "PASSANSWER");
			addField("createtime","CREATETIME");
			addField("loginfailnum","LOGINFAILNUM");
			addField("accountttl", "ACCOUNTTTL");
			addField("passquestion","PASSQUESTION");
			addField("flag","FLAG");
			addField("uuid", "UUID");
			addField("password","PASSWORD");
			addField("accountstat","ACCOUNTSTAT");
			addField("userid","USERID");
			addField("rtxaccount","RTXACCOUNT");
			addKey("UUID");
			setTableName("RO_PERSONACCOUNT");
		}

	 
	 
	public  personaccount() {
		super();
		
	}
	public personaccount(Connection connection) {
		super(connection);
		
	}
	public String getLastloginip() {
		return lastloginip;
	}
	public void setLastloginip(String _lastloginip) {
		firePropertyChange(" lastloginip",lastloginip,_lastloginip);
		this.lastloginip =  _lastloginip;
	}
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String _personid) {
		firePropertyChange(" personid",personid,_personid);
		this.personid = _personid;
	}
	public Timestamp getLastlogindate() {
		return lastlogindate;
	}
	public void setLastlogindate(Timestamp _lastlogindate) {
		
		firePropertyChange(" lastlogindate",lastlogindate,_lastlogindate);
		this.lastlogindate = _lastlogindate;
	}
	public String getPassanswer() {
		return passanswer;
	}
	public void setPassanswer(String _passanswer) {
		
		firePropertyChange(" passanswer",passanswer,_passanswer);
		this.passanswer = _passanswer;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp _createtime) {
		
		firePropertyChange(" createtime", createtime,_createtime);
		this.createtime = _createtime;
	}
	public Integer getLoginfailnum() {
		return loginfailnum;
	}
	public void setLoginfailnum(Integer _loginfailnum) {
		firePropertyChange(" loginfailnum",   loginfailnum, _loginfailnum);
		this.loginfailnum = _loginfailnum;
	}
	public Integer getAccountttl() {
		return accountttl;
	}
	public void setAccountttl(Integer _accountttl) {
		firePropertyChange("accountttl",  accountttl,_accountttl);
		this.accountttl = _accountttl;
	}
	public String getPassquestion() {
		return passquestion;
	}
	public void setPassquestion(String _passquestion) {
		firePropertyChange("passquestion",  passquestion,_passquestion);
		this.passquestion = _passquestion;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer _flag) {
		firePropertyChange(" flag",  flag, _flag);
		this.flag = _flag;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String _password) {
		firePropertyChange("password", password,_password);
		
		this.password = _password;
	}
	public String getAccountstat() {
		return accountstat;
	}
	public void setAccountstat(String _accountstat) {
		firePropertyChange("accountstat", accountstat, _accountstat);
		this.accountstat = _accountstat;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		firePropertyChange("uuid", uuid, _uuid);
		uuid = _uuid;
	}

	public String getUserid() {
		return userid;
	}
	public void setUserid(String _userid) {
		firePropertyChange("userid", userid, _userid);
		this.userid = _userid;
	}
	public String getRtxaccount() {
		return rtxaccount;
	}
	public void setRtxaccount(String _rtxaccount) {
		firePropertyChange("rtxaccount", rtxaccount, _rtxaccount);
		this.rtxaccount = _rtxaccount;
	}

}
