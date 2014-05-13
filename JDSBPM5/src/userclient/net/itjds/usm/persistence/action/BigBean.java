package net.itjds.usm.persistence.action;

import java.util.List;

public class BigBean {
	private String roleid;
	private String moduleid;
	private String Cnname;
	private Boolean checked;
	private int size;
	private List<SmallBean> smbean;
	public String getModuleid() {
		return moduleid;
	}

	

	public String getCnname() {
		return Cnname;
	}

	public List<SmallBean> getSmbean() {
		return smbean;
	}



	public void setSmbean(List<SmallBean> smbean) {
		this.smbean = smbean;
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



	public int getSize() {
		return size;
	}



	public void setSize(int size) {
		this.size = size;
	}



	public Boolean getChecked() {
		return checked;
	}



	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
}
