package net.itjds.usm2.define.tree;

import java.util.List;






public abstract class TreeNode {
	public String id;
	public int index=0;
	public String path;
	public String text;
	public Boolean leaf;
	public String cls;
	public String panelName;
	public Boolean expanded=false;
	
	
	


	
	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getLeaf() {
	
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public Boolean getExpanded() {
		return expanded;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

	public String getPanelName() {
		return panelName;
	}

	public void setPanelName(String panelName) {
		this.panelName = panelName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}




}
