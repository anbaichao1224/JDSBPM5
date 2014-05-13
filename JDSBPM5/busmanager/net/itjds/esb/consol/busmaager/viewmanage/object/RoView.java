package net.itjds.esb.consol.busmaager.viewmanage.object;

import java.util.Date;

public class RoView {

	private String id;
	
	private String viewname;
	
	private String viewpath;
	
	private Date createtime;
	
	private Date updatetime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getViewname() {
		return viewname;
	}

	public void setViewname(String viewname) {
		this.viewname = viewname;
	}

	public String getViewpath() {
		return viewpath;
	}

	public void setViewpath(String viewpath) {
		this.viewpath = viewpath;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	
}
