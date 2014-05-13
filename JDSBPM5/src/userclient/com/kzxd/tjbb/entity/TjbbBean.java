package com.kzxd.tjbb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="FDT_OA_BJRBB")

public class TjbbBean {
	
	private String uuid;
	private String tjdw;
	private String sbdw;
	private String sbsx;
	private String zxjs;
	private String sljs;
	private String xmbllj;
	private String cnsj;
	private Date bjsj;
	private String zxsx;
	private Date sbsj;
	private String bjzt;
	private String personid;

	
	@Column(name="tjdw")	
	public String getTjdw() {
		return tjdw;
	}
	public void setTjdw(String tjdw) {
		this.tjdw = tjdw;
	}
	
	@Column(name="sbdw")	
	public String getSbdw() {
		return sbdw;
	}
	public void setSbdw(String sbdw) {
		this.sbdw = sbdw;
	}
	
	@Column(name="sbsx")
	public String getSbsx() {
		return sbsx;
	}
	public void setSbsx(String sbsx) {
		this.sbsx = sbsx;
	}
	
	@Column(name="zxjs")
	public String getZxjs() {
		return zxjs;
	}
	public void setZxjs(String zxjs) {
		this.zxjs = zxjs;
	}
	
	@Column(name="sljs")
	public String getSljs() {
		return sljs;
	}
	public void setSljs(String sljs) {
		this.sljs = sljs;
	}
	
	@Column(name="xmbllj")
	public String getXmbllj() {
		return xmbllj;
	}
	public void setXmbllj(String xmbllj) {
		this.xmbllj = xmbllj;
	}
	
	@Column(name="cnsj")
	public String getCnsj() {
		return cnsj;
	}
	public void setCnsj(String cnsj) {
		this.cnsj = cnsj;
	}
	@Column(name="bjsj")
	public Date getBjsj() {
		return bjsj;
	}
	public void setBjsj(Date bjsj) {
		this.bjsj = bjsj;
	}
	@Column(name="zxsx")
	public String getZxsx() {
		return zxsx;
	}
	public void setZxsx(String zxsx) {
		this.zxsx = zxsx;
	}
	@Column(name="sbsj")
	public Date getSbsj() {
		return sbsj;
	}
	public void setSbsj(Date sbsj) {
		this.sbsj = sbsj;
	}
	@Column(name="bjzt")
	public String getBjzt() {
		return bjzt;
	}
	public void setBjzt(String bjzt) {
		this.bjzt = bjzt;
	}
	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	@Column(name="uuid")
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	@Column(name="personid")
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String personid) {
		this.personid = personid;
	}

}
