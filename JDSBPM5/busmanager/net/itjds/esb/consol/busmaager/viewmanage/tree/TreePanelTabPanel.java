package net.itjds.esb.consol.busmaager.viewmanage.tree;


import net.itjds.usm2.define.mapping.PanelBean;
import net.itjds.usm2.define.tabpanel.annotation.TabItemsDefine;
import net.itjds.usm2.define.tabpanel.annotation.TabPanelDefine;




@TabPanelDefine(height = 500, width = 300,activeTab = 0,xtype = "TabPanel",servicekey="RoView")

public class TreePanelTabPanel {
	

	private PanelBean bean;

	
	public TreePanelTabPanel(PanelBean tempBean){
		this.bean=tempBean;
 
 		
	}
	



	@TabItemsDefine(title = "视图信息",html = "视图列表")
	public RoTreePanelForm getEsbTempPanelForm() {
	
 		
 		return new RoTreePanelForm(bean);
		
	}
	
	

	
}
