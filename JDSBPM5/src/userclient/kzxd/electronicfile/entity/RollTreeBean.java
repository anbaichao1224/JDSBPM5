package kzxd.electronicfile.entity;

import java.util.ArrayList;
import java.util.List;

import net.itjds.usm.orgtree.ExtNodeBean;

public class RollTreeBean {
	private String id;
	private String name;
	private List<RollTreeBean> Children = new ArrayList<RollTreeBean>();
	//private String parentid;
	private boolean leaf;
	
	//°¸¾ítree
	private String rollnum;
	private String yearnum;
	private String timelimit;
	private String status;
	private String uiProvider;
	private String icon;
	//private boolean checked;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<RollTreeBean> getChildren() {
		return Children;
	}
	public void setChildren(List<RollTreeBean> children) {
		Children = children;
	}
	/*public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}*/
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public String getRollnum() {
		return rollnum;
	}
	public void setRollnum(String rollnum) {
		this.rollnum = rollnum;
	}
	public String getYearnum() {
		return yearnum;
	}
	public void setYearnum(String yearnum) {
		this.yearnum = yearnum;
	}
	public String getTimelimit() {
		return timelimit;
	}
	public void setTimelimit(String timelimit) {
		this.timelimit = timelimit;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUiProvider() {
		return "col";
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
}
