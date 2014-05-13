package com.kzxd.newkaoqin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="KZXD_GZRDY")
public class GzrdyBean {

	private String uuid;
	private Date datefrom;
	private Date dateto;
	private String rztype;
	private String rzcontent;
	
	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	@Column(name="uuid")
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	@Column(name="datefrom")
	public Date getDatefrom() {
		return datefrom;
	}
	public void setDatefrom(Date datefrom) {
		this.datefrom = datefrom;
	}
	@Column(name="dateto")
	public Date getDateto() {
		return dateto;
	}
	public void setDateto(Date dateto) {
		this.dateto = dateto;
	}
	@Column(name="rztype")
	public String getRztype() {
		return rztype;
	}
	public void setRztype(String rztype) {
		this.rztype = rztype;
	}
	@Column(name="rzcontent")
	public String getRzcontent() {
		return rzcontent;
	}
	public void setRzcontent(String rzcontent) {
		this.rzcontent = rzcontent;
	}
	
}
