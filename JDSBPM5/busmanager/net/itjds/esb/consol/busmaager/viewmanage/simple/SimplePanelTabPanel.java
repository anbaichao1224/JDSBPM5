package net.itjds.esb.consol.busmaager.viewmanage.simple;


import net.itjds.usm2.define.mapping.PanelBean;
import net.itjds.usm2.define.tabpanel.annotation.TabItemsDefine;
import net.itjds.usm2.define.tabpanel.annotation.TabPanelDefine;




@TabPanelDefine(height = 500, width = 300,activeTab = 0,xtype = "TabPanel",servicekey="RoView")

public class SimplePanelTabPanel {
	

	private PanelBean bean;

	
	public SimplePanelTabPanel(PanelBean tempBean){
		this.bean=tempBean;
 
 		
	}
	


	@TabItemsDefine(title = "视图信息",html = "视图信息")
	public RoSimplePanelForm getEsbTempPanelForm() {
	
 		
 		return new RoSimplePanelForm(bean);
		
	}
	
	

	
}
