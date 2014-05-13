package net.itjds.esb.consol.busmaager.viewmanage.form;

import java.util.ArrayList;
import java.util.List;

import net.itjds.usm2.define.mapping.FieldBean;
import net.itjds.usm2.define.mapping.PanelBean;
import net.itjds.usm2.define.tabpanel.annotation.TabItemsDefine;
import net.itjds.usm2.define.tabpanel.annotation.TabPanelDefine;




@TabPanelDefine(height = 500, width = 300,activeTab = 0,xtype = "TabPanel",servicekey="RoView")

public class FormPanelTabPanel {
	

	private PanelBean bean;

	
	public FormPanelTabPanel(PanelBean tempBean){
		this.bean=tempBean;
 
 		
	}
	

	

	@TabItemsDefine(title = "视图信息",html = "视图列表")
	public RoFormPanelForm getEsbTempPanelForm() {
 		return new RoFormPanelForm(bean);
		
	}
	
	

	@TabItemsDefine(title = "字段列表",html = "字段列表")
	public List<RoFormFieldGrid> getFieldChildList() {
		List listBean=new ArrayList();
 			if (bean!=null){
 				listBean=bean.getItems();
 		 	 
 			}
 			
 	
 		
 		return listBean;
		
	}

	
}
