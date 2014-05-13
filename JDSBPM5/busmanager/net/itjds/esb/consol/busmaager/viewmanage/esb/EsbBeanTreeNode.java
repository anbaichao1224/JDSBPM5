package net.itjds.esb.consol.busmaager.viewmanage.esb;

import java.util.ArrayList;
import java.util.List;

import net.itjds.bpm.data.xmlproxy.manager.EsbBean;
import net.itjds.bpm.data.xmlproxy.manager.EsbBeanFactory;
import net.itjds.bpm.data.xmlproxy.manager.USMTempBean;
import net.itjds.common.dbutil.StringUtil;
import net.itjds.usm2.db.util.EsbUtil;
import net.itjds.usm2.define.annotation.ButtonDefine;
import net.itjds.usm2.define.data.annotation.AjaxDataDefine;
import net.itjds.usm2.define.enums.*;
import net.itjds.usm2.define.mapping.PanelBean;
import net.itjds.usm2.define.tree.StaticTreeNode;
import net.itjds.usm2.define.tree.TreeNode;
import net.itjds.usm2.define.tree.annotation.TreeListener;
import net.itjds.usm2.define.tree.annotation.TreeLoaderDefine;
import net.itjds.usm2.define.tree.annotation.TreeNodeDefine;
import net.itjds.usm2.define.tree.annotation.TreePanelDefine;


public class EsbBeanTreeNode extends StaticTreeNode {
	

 
	private EsbBean tempBean;
	public EsbBeanTreeNode(EsbBean bean){
  		 this.tempBean=bean;
 	     this.setText(bean.getCnname()+"("+bean.getId()+")");
   	     this.setId(bean.getId());
   	     this.setPanelName("$ViewManage.cartPanel.esbFormPanelBean");
   	     this.setLeaf(false);
  	}
	
	
 	public EsbBeanTreeNode(){
 	}
 
	@TreeNodeDefine()
 	public List<BusBeanTreeNode> childBusBeanBean(){
 		List listBean=new ArrayList();
 		if (tempBean!=null){
 			EsbBeanFactory factory=EsbBeanFactory.newInstance();
 			listBean=factory.getEsbBeanListByKey(tempBean.getId());
 		}
 		return listBean;
 	}
 
}
