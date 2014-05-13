package com.kzxd.nbyj.entity;

import java.util.Date;

public class NbyjBean {
	private String uuid;
	private String nbyj;
	private String personname;
	private String org;
	private Date createdate;
	private String  processinst_id;
	private String activityinst_id;
	private String hj;
	public String getHj() {
		return hj;
	}
	public void setHj(String hj) {
		this.hj = hj;
	}
	public String getProcessinst_id() {
		return processinst_id;
	}
	public void setProcessinst_id(String processinst_id) {
		this.processinst_id = processinst_id;
	}
	public String getActivityinst_id() {
		return activityinst_id;
	}
	public void setActivityinst_id(String activityinst_id) {
		this.activityinst_id = activityinst_id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getNbyj() {
		return nbyj;
	}
	public void setNbyj(String nbyj) {
		this.nbyj = nbyj;
	}
	public String getPersonname() {
		return personname;
	}
	public void setPersonname(String personname) {
		this.personname = personname;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
}
