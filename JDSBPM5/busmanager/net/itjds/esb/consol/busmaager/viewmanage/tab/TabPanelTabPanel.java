package net.itjds.esb.consol.busmaager.viewmanage.tab;

import java.util.ArrayList;
import java.util.List;

import net.itjds.usm2.define.mapping.FieldBean;
import net.itjds.usm2.define.mapping.PanelBean;
import net.itjds.usm2.define.tabpanel.annotation.TabItemsDefine;
import net.itjds.usm2.define.tabpanel.annotation.TabPanelDefine;
import net.itjds.esb.consol.busmaager.viewmanage.ViewGrid;




@TabPanelDefine(height = 500, width = 300,activeTab = 0,xtype = "TabPanel",servicekey="RoView")

public class TabPanelTabPanel {
	

	private PanelBean bean;

	
	public TabPanelTabPanel(PanelBean tempBean){
		this.bean=tempBean;
 
 		
	}
	

	@TabItemsDefine(title = "视图列表",html = "视图列表")
	public List<ViewGrid> getViewChildList() {
		List listBean=new ArrayList();
		 
 	
 			if (bean!=null){
 				List items=bean.getItems();
 		 	 	
 	 	 		for(int k=0;k<items.size();k++){
 	 	 			PanelBean item=(PanelBean) items.get(k);
 	 	 			ViewGrid treePanel=new ViewGrid(item);
 	 	 			listBean.add(treePanel);
 	 	 		}
 			}
 			
 	
 		
 		return listBean;
		
	}
	

	@TabItemsDefine(title = "视图信息",html = "视图列表")
	public RoTabPanelForm getEsbTempPanelForm() {
	
 		
 		return new RoTabPanelForm(bean);
		
	}
	
	

	
}
