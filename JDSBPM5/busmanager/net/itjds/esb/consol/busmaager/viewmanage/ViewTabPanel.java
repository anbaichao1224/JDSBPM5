package net.itjds.esb.consol.busmaager.viewmanage;

import java.util.ArrayList;
import java.util.List;

import net.itjds.bpm.data.xmlproxy.manager.ExpressionTempBean;
import net.itjds.bpm.data.xmlproxy.manager.MapDAOTempBean;
import net.itjds.bpm.data.xmlproxy.manager.USMTempBean;

import net.itjds.usm2.define.tabpanel.annotation.TabItemsDefine;
import net.itjds.usm2.define.tabpanel.annotation.TabPanelDefine;
import net.itjds.esb.consol.busmaager.viewmanage.esb.BusFormView;

@TabPanelDefine(height = 500, width = 300, activeTab = 0, xtype = "TabPanel", servicekey = "RoView")
public class ViewTabPanel {

	private ExpressionTempBean tempBean;

	public ViewTabPanel(Object tempBean) {
		this.tempBean = (ExpressionTempBean) tempBean;
	}
	

	@TabItemsDefine(title = "基本信息", html = "基本信息")
	public BusFormView getEsbTempPanelForm() {

		return new BusFormView(tempBean);

	}

//	@TabItemsDefine(title = "EXT布局", html = "EXT布局")
//	public List<RoViewGrid> getViewChildList() {
//		List listBean = new ArrayList();
//
//		if (tempBean != null) {
//			String tableName=StringUtil.formatJavaName(tempBean.getMaintablename().toLowerCase(),true);
//			PanelBean bean = EsbUtil.getCurrPanelBean(tableName
//					+ "Viewport");
//			listBean=bean.getItems();
//		}
//		return listBean;
//	}
	
	@TabItemsDefine(title = "字段信息", html = "字段信息")
	public List<DbFieldGrid> getDbInfo() {
		List listBean = new ArrayList();
		if (tempBean != null) {
			if   (tempBean instanceof USMTempBean) {
				listBean=((USMTempBean)tempBean).getMaintable().getColList();
			}else if (tempBean instanceof MapDAOTempBean){
				listBean=((MapDAOTempBean)tempBean).getTables().get(0).getColList();
			}
		}
		return listBean;
	}



}
