package kzxd.electronicfile.entity;

import java.util.ArrayList;
import java.util.List;

import net.itjds.usm.orgtree.ExtNodeBean;

public class CategoryTreeBean {
	private String id;
	private String name;
	private String text;
	//private CategoryTreeBean parentbean;
	private List<CategoryTreeBean> Children = new ArrayList<CategoryTreeBean>();
	//private String parentid;
	private boolean leaf;
	
	
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<CategoryTreeBean> getChildren() {
		return Children;
	}
	public void setChildren(List<CategoryTreeBean> children) {
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
	
}
