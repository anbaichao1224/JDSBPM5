package com.kzxd.newkaoqin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="FDT_OA_KAOQIN")

public class KaoQinBean {
	
	private String uuid;
	private String ck;
	private Date rq;
	private String czlx;
	private String sjlx;
	private Date czsj;
	private String zt;
	private Integer sjdx;
	private String bz;
	private String personid;
	
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
	@Column(name="rq")
	public Date getRq() {
		return rq;
	}
	public void setRq(Date rq) {
		this.rq = rq;
	}
	@Column(name="czlx")
	public String getCzlx() {
		return czlx;
	}
	public void setCzlx(String czlx) {
		this.czlx = czlx;
	}
	@Column(name="sjlx")
	public String getSjlx() {
		return sjlx;
	}
	public void setSjlx(String sjlx) {
		this.sjlx = sjlx;
	}
	@Column(name="czsj")
	public Date getCzsj() {
		return czsj;
	}
	public void setCzsj(Date czsj) {
		this.czsj = czsj;
	}
	@Column(name="zt")
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	@Column(name="sjdx")
	public Integer getSjdx() {
		return sjdx;
	}
	public void setSjdx(Integer sjdx) {
		this.sjdx = sjdx;
	}
	@Column(name="bz")
	public String getBz() {
		if(bz==null){
			bz="";
		}			
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	@Column(name="personid")
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String personid) {
		this.personid = personid;
	}	

}
