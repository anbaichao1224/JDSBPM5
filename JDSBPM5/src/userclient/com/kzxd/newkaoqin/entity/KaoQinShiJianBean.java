package com.kzxd.newkaoqin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="FDT_OA_KQSJSD")
public class KaoQinShiJianBean {
	
	private String uuid;
	private Date ksrq;
	private Date jsrq;
	private Date swqdsj;
	private Date swqtsj;
	private Date xwqdsj;
	private Date xwqtsj;
	
	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	@Column(name="uuid")
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	@Column(name="ksrq")
	public Date getKsrq() {
		return ksrq;
	}
	public void setKsrq(Date ksrq) {
		this.ksrq = ksrq;
	}
	@Column(name="jsrq")
	public Date getJsrq() {
		return jsrq;
	}
	public void setJsrq(Date jsrq) {
		this.jsrq = jsrq;
	}
	@Column(name="swqdsj")
	public Date getSwqdsj() {
		return swqdsj;
	}
	public void setSwqdsj(Date swqdsj) {
		this.swqdsj = swqdsj;
	}
	@Column(name="swqtsj")
	public Date getSwqtsj() {
		return swqtsj;
	}
	public void setSwqtsj(Date swqtsj) {
		this.swqtsj = swqtsj;
	}
	@Column(name="xwqdsj")
	public Date getXwqdsj() {
		return xwqdsj;
	}
	public void setXwqdsj(Date xwqdsj) {
		this.xwqdsj = xwqdsj;
	}
	@Column(name="xwqtsj")
	public Date getXwqtsj() {
		return xwqtsj;
	}
	public void setXwqtsj(Date xwqtsj) {
		this.xwqtsj = xwqtsj;
	}

}
