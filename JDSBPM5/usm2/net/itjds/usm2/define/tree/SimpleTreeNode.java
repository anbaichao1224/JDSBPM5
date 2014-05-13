package net.itjds.usm2.define.tree;

import java.util.List;






public  class SimpleTreeNode extends StaticTreeNode{

	
	public List<TreeNode> children;
	public List<TreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	
}
