package com.kzxd.newkaoqin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="FDT_OA_QINGJIAMINGXI")
public class QingJiaMingxiBean2 {

	private String uuid;
	private String ck;
	private String xm;
	private Date qingjiariqi;
	private String qjlx;
	
	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	@Column(name="uuid")	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	@Column(name="ck")
	public String getCk() {
		return ck;
	}
	public void setCk(String ck) {
		this.ck = ck;
	}
	@Column(name="xm")
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	@Column(name="qingjiariqi")
	public Date getQingjiariqi() {
		return qingjiariqi;
	}
	public void setQingjiariqi(Date qingjiariqi) {
		this.qingjiariqi = qingjiariqi;
	}
	@Column(name="qjlx")
	public String getQjlx() {
		return qjlx;
	}
	public void setQjlx(String qjlx) {
		this.qjlx = qjlx;
	}
	
}
