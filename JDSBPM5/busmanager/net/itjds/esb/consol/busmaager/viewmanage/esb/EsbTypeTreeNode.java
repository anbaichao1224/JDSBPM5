package net.itjds.esb.consol.busmaager.viewmanage.esb;

import java.util.List;

import net.itjds.bpm.data.xmlproxy.manager.EsbBean;

import net.itjds.usm2.define.tree.StaticTreeNode;

import net.itjds.usm2.define.tree.annotation.TreeNodeDefine;



public class EsbTypeTreeNode extends StaticTreeNode {
	

 
	private List beans;
	public EsbTypeTreeNode(List<EsbBean> beans){
  		 this.beans=beans;
 	   
   	     this.setLeaf(false);
  	}
	
	
 	public EsbTypeTreeNode(){
 	}
 
	@TreeNodeDefine()
 	public List<EsbBeanTreeNode> childBusBeanBean(){
 		
 		return beans;
 	}


	public List getBeans() {
		return beans;
	}


	public void setBeans(List beans) {
		this.beans = beans;
	}
 
}
