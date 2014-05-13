package com.kzxd.tjbb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="FDT_OA_CKSXML")

public class CKSXMLBean {
	
	private String uuid;
	private String fwck;
	private String cksx;
	private String sfns;
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
	@Column(name="fwck")
	public String getFwck() {
		return fwck;
	}
	public void setFwck(String fwck) {
		this.fwck = fwck;
	}
	@Column(name="cksx")
	public String getCksx() {
		return cksx;
	}
	public void setCksx(String cksx) {
		this.cksx = cksx;
	}
	@Column(name="sfns")
	public String getSfns() {
		if(sfns==null){
			sfns="";
		}
		return sfns;
	}
	public void setSfns(String sfns) {
		this.sfns = sfns;
	}
	@Column(name="personid")
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String personid) {
		this.personid = personid;
	}

}
