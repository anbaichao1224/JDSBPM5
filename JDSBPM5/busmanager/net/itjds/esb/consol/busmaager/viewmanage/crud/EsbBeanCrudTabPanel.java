package net.itjds.esb.consol.busmaager.viewmanage.crud;

import java.util.ArrayList;
import java.util.List;

import net.itjds.bpm.data.xmlproxy.manager.BaseTableTempBean;
import net.itjds.bpm.data.xmlproxy.manager.EsbBean;
import net.itjds.bpm.data.xmlproxy.manager.EsbBeanFactory;
import net.itjds.bpm.data.xmlproxy.manager.USMTempBean;

import net.itjds.usm2.define.tabpanel.annotation.TabItemsDefine;
import net.itjds.usm2.define.tabpanel.annotation.TabPanelDefine;
import net.itjds.esb.consol.busmaager.viewmanage.esb.BusBeanGrid;
import net.itjds.esb.consol.busmaager.viewmanage.esb.EsbBeanFormPanel;

@TabPanelDefine(height = 500, width = 300, activeTab = 0, xtype = "TabPanel", servicekey = "RoView")
public class EsbBeanCrudTabPanel {

	
	private EsbBean esbBean;
	private EsbBeanFormPanel esbBeanFormView;
	
	public EsbBeanCrudTabPanel(Object esbBean) {
		this.esbBean=(EsbBean) esbBean;
		
	}

	@TabItemsDefine(title = "��Դ��Ϣ", html = "��Դ��Ϣ")
	public EsbBeanFormPanel getEsbBeanFormView() {
		if (esbBeanFormView==null){
			esbBeanFormView=new EsbBeanFormPanel(esbBean);
		}
		return esbBeanFormView;
	}
	
	@TabItemsDefine(title = "ģ���б�", html = "ģ���б�")
	public List<BusBeanGrid> getEsbBeanGridView() {
		List listBean=new ArrayList();
 		if (esbBean!=null){
 			EsbBeanFactory factory=EsbBeanFactory.newInstance();
 			listBean=factory.getEsbBeanListByKey(esbBean.getId());
 		}
 		return listBean;
	}
	
	@TabItemsDefine(title = "ģ����Ϣ", html = "ģ����Ϣ")
	public List<EsbTempGrid> getTempView() {
		List listBean=new ArrayList();
 		if (esbBean!=null && (esbBean.getType().equals("CRUD"))){
 			EsbBeanFactory factory=EsbBeanFactory.newInstance();
 			listBean=factory.getEsbBeanListByKey(esbBean.getId());
 		}
 		return listBean;
	}


	public void setEsbBeanFormView(EsbBeanFormPanel esbBeanFormView) {
		this.esbBeanFormView = esbBeanFormView;
	}




}
