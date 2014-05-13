package net.itjds.esb.consol.busmaager.viewmanage.esb;

import java.util.ArrayList;
import java.util.List;

import net.itjds.bpm.data.xmlproxy.manager.EsbBeanFactory;
import net.itjds.bpm.data.xmlproxy.manager.ExpressionTempBean;
import net.itjds.bpm.data.xmlproxy.manager.MapDAOTempBean;
import net.itjds.bpm.data.xmlproxy.manager.MappingBUSBean;
import net.itjds.bpm.data.xmlproxy.manager.USMTempBean;
import net.itjds.common.dbutil.StringUtil;
import net.itjds.usm2.db.util.EsbUtil;

import net.itjds.usm2.define.mapping.PanelBean;
import net.itjds.usm2.define.tree.StaticTreeNode;
import net.itjds.usm2.define.tree.annotation.TreeNodeDefine;
import net.itjds.esb.consol.busmaager.viewmanage.PanelTreeNode;


public class BusBeanTreeNode extends StaticTreeNode {
	private USMTempBean usmtempBean;
	private MapDAOTempBean mapdaoBean;
	private MappingBUSBean mappingBusBean;
	private ExpressionTempBean expressionBean;
	public BusBeanTreeNode(USMTempBean bean){
  		 this.usmtempBean=bean;
 	     this.setText(bean.getName());
   	     this.setId(bean.getId());
   	     this.setPanelName("$ViewManage.cartPanel.viewTabPanel");
   	     this.setLeaf(false);
  	}
	public BusBeanTreeNode(MapDAOTempBean bean){
 		 this.mapdaoBean=bean;
	     this.setText(bean.getName());
  	     this.setId(bean.getId());
  	     this.setPanelName("$ViewManage.cartPanel.viewTabPanel");
  	     this.setLeaf(false);
 	}
	public BusBeanTreeNode(MappingBUSBean bean){
 		 this.mappingBusBean=bean;
	     this.setText(bean.getName());
  	     this.setId(bean.getId());
  	     this.setPanelName("$ViewManage.cartPanel.viewTabPanel");
  	     this.setLeaf(false);
 	}
 	public BusBeanTreeNode(ExpressionTempBean bean){
 		 this.expressionBean=bean;
	     this.setText(bean.getName());
  	     this.setId(bean.getId());
  	     this.setPanelName("$ViewManage.cartPanel.viewTabPanel");
  	     this.setLeaf(false);
 	}
 
	@TreeNodeDefine()
 	public List<PanelTreeNode> childUsmEsbBean(){
 		List listBean=new ArrayList();
 		
 		
 		if (usmtempBean!=null){
 			String tableName=StringUtil.formatJavaName(usmtempBean.getMaintablename().toLowerCase(),true);
			PanelBean bean = EsbUtil.getCurrPanelBean(tableName
					+ "Viewport");
			bean.setName("访问（列表）主界面("+tableName+"Viewport)");
			bean.setPath("$"+bean.getEsbkey());
			listBean.add(bean);

 			PanelBean formPanel = EsbUtil.getCurrPanelBean("curr"+tableName+"PopForm");
  			if (formPanel!=null){
  				formPanel.setName("数据编辑界面(curr"+tableName+"PopForm)");
  				formPanel.setPath("$"+formPanel.getEsbkey());
 				listBean.add(formPanel);
 			}
  		
 		}
 		return listBean;
 	}
	@TreeNodeDefine()
 	public List<BusBeanTreeNode> childServiceBean(){
 		List listBean=new ArrayList();
 		EsbBeanFactory factory=EsbBeanFactory.newInstance();
 		if (usmtempBean!=null){
 			String tableName=StringUtil.formatJavaName(usmtempBean.getMaintablename().toLowerCase(),true);
 			ExpressionTempBean bean=(ExpressionTempBean) factory.getIdMap().get(tableName);
 			
 			bean.setName("DAO映射("+tableName+")");
            ExpressionTempBean usmServiceBean=(ExpressionTempBean) factory.getIdMap().get(tableName+"UsmService");
 			
            usmServiceBean.setName("用户服务("+tableName+"UsmService)");
 			listBean.add(bean);
			listBean.add(usmServiceBean);	
 		}
 		return listBean;
 	}
	
	@TreeNodeDefine()
 	public List<PanelTreeNode> childMapdaoEsbBean(){
 		List listBean=new ArrayList();
 		if (mapdaoBean!=null){
 			
 		}
 		return listBean;
 	}
	@TreeNodeDefine()
 	public List<PanelTreeNode> childMappingEsbBean(){
 		List listBean=new ArrayList();
 	
 		return listBean;
 	}
	@TreeNodeDefine()
 	public List<PanelTreeNode> childExpressionEsbBean(){
 		List listBean=new ArrayList();
 	
 		return listBean;
 	}
 
 
 
}
