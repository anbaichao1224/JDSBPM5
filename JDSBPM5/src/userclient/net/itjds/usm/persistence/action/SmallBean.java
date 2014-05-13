package net.itjds.usm.persistence.action;

import java.util.Date;

public class SmallBean {
	private String roleid;
	private String moduleid;
	private String Cnname;
	private String uuid;
	private String msm;
	private Date createdate;
	private String person;
	public String getModuleid() {
		return moduleid;
	}

	public String getCnname() {
		return Cnname;
	}

	public void setCnname(String cnname) {
		Cnname = cnname;
	}

	public void setModuleid(String moduleid) {
		this.moduleid = moduleid;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getMsm() {
		return msm;
	}

	public void setMsm(String msm) {
		this.msm = msm;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}
}
