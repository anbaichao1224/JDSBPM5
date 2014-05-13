package net.itjds.usm2.define.tree;

import java.util.List;






public abstract class StaticTreeNode extends TreeNode{

	
	public List<TreeNode> children;
	public List<TreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	
}
