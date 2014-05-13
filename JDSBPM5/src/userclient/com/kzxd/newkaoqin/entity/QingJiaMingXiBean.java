package com.kzxd.newkaoqin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

public class QingJiaMingXiBean {

	private String uuid;
	private String ck;
	private String xm;
	private Date qingjiariqi;
	private String qjlx;
	private String personid;
		
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCk() {
		return ck;
	}
	public void setCk(String ck) {
		this.ck = ck;
	}

	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}

	public Date getQingjiariqi() {
		return qingjiariqi;
	}
	public void setQingjiariqi(Date qingjiariqi) {
		this.qingjiariqi = qingjiariqi;
	}
	public String getQjlx() {
		return qjlx;
	}
	public void setQjlx(String qjlx) {
		this.qjlx = qjlx;
	}
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String personid) {
		this.personid = personid;
	}
	
}
