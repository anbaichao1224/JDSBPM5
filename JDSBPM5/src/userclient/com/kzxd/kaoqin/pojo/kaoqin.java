package com.kzxd.kaoqin.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@SuppressWarnings("unused")

@Entity
@Table(name = "RO_KAOQIN")

public class kaoqin {
	
      private String uuid;   //uuid
      private int  isCd;  //是否迟到
      private int  isZt;    //是否早退
      private Date qdTime;     //签到时间
      private Date qtTime;     //签退时间
      private String username;   //用户名
      private String personid; 
      
      
      
      
      
    @Id @GeneratedValue(generator="system-uuid")
  	@GenericGenerator(name="system-uuid", strategy="uuid")
  	@Column(name="UUID")  
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	@Column(name="ISCD")
	public int getIsCd() {
		return isCd;
	}
	
	public void setIsCd(int isCd) {
		this.isCd = isCd;
	}
	
	@Column(name="ISZT")
	public int getIsZt() {
		return isZt;
	}
	public void setIsZt(int isZt) {
		this.isZt = isZt;
	}
	
	@Column(name="QDTIME",length=32)
	public Date getQdTime() {
		return qdTime;
	}
	public void setQdTime(Date qdTime) {
		this.qdTime = qdTime;
	}
	@Column(name="QTTIME ",length=32)
	public Date getQtTime() {
		return qtTime;
	}
	public void setQtTime(Date qtTime) {
		this.qtTime = qtTime;
	}
	@Column(name="USERNAME",length=32)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public kaoqin(){}
	
	@Column(name="PERSONID",length=32)
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String personid) {
		this.personid = personid;
	}
	public kaoqin(String uuid, int isCd, int isZt, Date qdTime, Date qtTime,
			String username, String personid) {
		super();
		this.uuid = uuid;
		this.isCd = isCd;
		this.isZt = isZt;
		this.qdTime = qdTime;
		this.qtTime = qtTime;
		this.username = username;
		this.personid = personid;
	}
      
}
