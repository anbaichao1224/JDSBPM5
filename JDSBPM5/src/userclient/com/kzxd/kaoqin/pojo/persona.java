package com.kzxd.kaoqin.pojo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "RO_PERSONACCOUNT")
public class persona {
	
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
	 public persona(){}
	 @Column(name="LASTLOGINIP")
	public String getLastloginip() {
		return lastloginip;
	}
	public void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip;
	}
	 @Column(name="PERSONID")
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String personid) {
		this.personid = personid;
	}
	 @Column(name="LASTLOGINDATE")
	public Timestamp getLastlogindate() {
		return lastlogindate;
	}
	public void setLastlogindate(Timestamp lastlogindate) {
		this.lastlogindate = lastlogindate;
	}
	 @Column(name="PASSANSWER")
	public String getPassanswer() {
		return passanswer;
	}
	public void setPassanswer(String passanswer) {
		this.passanswer = passanswer;
	}
	 @Column(name="CREATETIME")
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	 @Column(name="LOGINFAILNUM")
	public Integer getLoginfailnum() {
		return loginfailnum;
	}
	public void setLoginfailnum(Integer loginfailnum) {
		this.loginfailnum = loginfailnum;
	}
	 @Column(name="ACCOUNTTTL")
	public Integer getAccountttl() {
		return accountttl;
	}
	public void setAccountttl(Integer accountttl) {
		this.accountttl = accountttl;
	}
	 @Column(name="PASSQUESTION")
	public String getPassquestion() {
		return passquestion;
	}
	public void setPassquestion(String passquestion) {
		this.passquestion = passquestion;
	}
	 @Column(name="FLAG")
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	 @Column(name="PASSWORD")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	 @Column(name="ACCOUNTSTAT")
	public String getAccountstat() {
		return accountstat;
	}
	public void setAccountstat(String accountstat) {
		this.accountstat = accountstat;
	}
	 @Id @GeneratedValue(generator="system-uuid")
	  	@GenericGenerator(name="system-uuid", strategy="uuid")
	  	@Column(name="UUID") 
	public String getUuid() {
		return uuid;
	}
	 
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	 @Column(name="USERID")
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@Column(name="RTXACCOUNT")
	public String getRtxaccount() {
		return rtxaccount;
	}
	public void setRtxaccount(String rtxaccount) {
		this.rtxaccount = rtxaccount;
	}
	public persona(String lastloginip, String personid,
			Timestamp lastlogindate, String passanswer, Timestamp createtime,
			Integer loginfailnum, Integer accountttl, String passquestion,
			Integer flag, String password, String accountstat, String uuid,
			String userid, String rtxaccount) {
		super();
		this.lastloginip = lastloginip;
		this.personid = personid;
		this.lastlogindate = lastlogindate;
		this.passanswer = passanswer;
		this.createtime = createtime;
		this.loginfailnum = loginfailnum;
		this.accountttl = accountttl;
		this.passquestion = passquestion;
		this.flag = flag;
		this.password = password;
		this.accountstat = accountstat;
		this.uuid = uuid;
		this.userid = userid;
		this.rtxaccount = rtxaccount;
	}

}
