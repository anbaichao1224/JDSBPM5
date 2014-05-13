package net.itjds.usm.persistence.action;

public class Bean {
	private String roleid;
	private String moduleid;
	private String Cnname;
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
}
