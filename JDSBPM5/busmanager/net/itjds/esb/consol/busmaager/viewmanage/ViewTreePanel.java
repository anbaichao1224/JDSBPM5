package net.itjds.esb.consol.busmaager.viewmanage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.itjds.bpm.data.xmlproxy.manager.EsbBean;
import net.itjds.bpm.data.xmlproxy.manager.EsbBeanFactory;

import net.itjds.usm2.define.annotation.ButtonDefine;
import net.itjds.usm2.define.data.annotation.AjaxDataDefine;
import net.itjds.usm2.define.enums.*;

import net.itjds.usm2.define.tree.StaticTreeNode;
import net.itjds.usm2.define.tree.annotation.TreeListener;
import net.itjds.usm2.define.tree.annotation.TreeLoaderDefine;
import net.itjds.usm2.define.tree.annotation.TreeNodeDefine;
import net.itjds.usm2.define.tree.annotation.TreePanelDefine;
import net.itjds.esb.consol.busmaager.viewmanage.esb.EsbTypeTreeNode;

@TreePanelDefine(
		height = 500,
		width = 300,
		title = "视图列表", 
		checkModel = ElementCheckModel.single,
		loader = "loader",		
		xtype = "TreePanel", 
		rootVisible = true,
		listeners={
		@TreeListener(
				eventname=ElementEvent.click,
				    function="function(node,e){}"
				)
			},
		treeLoaderBean = @TreeLoaderDefine(
				dataUrl = "expression.jsp?expression=$JSONTree"
		),
		treeNodeBean=@TreeNodeDefine(text="所有应用",id="null",where="",expanded=true),
		buttons = {@ButtonDefine()}, 
		updateData = @AjaxDataDefine(url = "")
)



public class ViewTreePanel extends StaticTreeNode {
	
	
	private EsbBeanFactory factory;
	
 	public ViewTreePanel(EsbBeanFactory factory){
 	
 	
 		 this.factory=EsbBeanFactory.newInstance();
 		 this.factory=factory;
 	 	 this.setPanelName("$ViewManage.cartPanel.viewTabPanel");
   	     this.setLeaf(false);
   	   
   	     this.setExpanded(true);
  	}
 
 
 
 	@TreeNodeDefine()
 	public List<EsbTypeTreeNode> childPanel(){
 		List listBean=new ArrayList();
 		List userEsbNodeList =new ArrayList();
 		List systemEsbNodeList =new ArrayList();
 		EsbBeanFactory factory=EsbBeanFactory.newInstance();
 		Map<String,EsbBean> esbBeanMap=factory.getEsbBeanListBean().getEsbBeanMap();
 		Iterator<String> it=esbBeanMap.keySet().iterator();
 		
 		for(;it.hasNext();){
 			String key= it.next();
 			EsbBean bean=esbBeanMap.get(key);
 			if (bean.getEsbtype()!=null && bean.getEsbtype().equals("user")){
 				userEsbNodeList.add(bean);
 			}else if (bean.getEsbtype()!=null&&bean.getEsbtype().equals("system")){
 				systemEsbNodeList.add(bean);
 			}
 		}
 		EsbTypeTreeNode userEsbNode=new  EsbTypeTreeNode(userEsbNodeList);
 		userEsbNode.setText("用户资源");
 		userEsbNode.setId("userEsbNode");
 		EsbTypeTreeNode sysEsbNode=new  EsbTypeTreeNode(systemEsbNodeList);
 		sysEsbNode.setText("系统资源");
 		sysEsbNode.setId("systemEsbNode");
 		
 		listBean.add(userEsbNode);
 		listBean.add(sysEsbNode);
  		return listBean;
 	}
	

}
