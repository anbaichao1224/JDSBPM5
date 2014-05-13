package net.itjds.esb.consol.busmaager.viewmanage.grid;

import java.util.ArrayList;
import java.util.List;

import net.itjds.usm2.define.mapping.FieldBean;
import net.itjds.usm2.define.mapping.PanelBean;
import net.itjds.usm2.define.tabpanel.annotation.TabItemsDefine;
import net.itjds.usm2.define.tabpanel.annotation.TabPanelDefine;




@TabPanelDefine(height = 500, width = 300,activeTab = 0,xtype = "TabPanel",servicekey="RoView")

public class GridPanelTabPanel {
	

	private PanelBean bean;

	
	public GridPanelTabPanel(PanelBean tempBean){
		this.bean=tempBean;
 
 		
	}
	
	

	@TabItemsDefine(title = "视图信息",html = "视图列表")
	public RoGridPanelForm getEsbTempPanelForm() {
	
 		
 		return new RoGridPanelForm(bean);
		
	}
	
	

	@TabItemsDefine(title = "字段列表",html = "字段列表")
	public List<RoGridFieldGrid> getFieldChildList() {
		List listBean=new ArrayList();
		 
 	
 			if (bean!=null){
 				List items=bean.getItems();
 		 	 	
 	 	 		for(int k=0;k<items.size();k++){
 	 	 			if(items.get(k)instanceof FieldBean){
 	 	 				FieldBean item=(FieldBean) items.get(k);
 	 	 				RoGridFieldGrid treePanel=new RoGridFieldGrid(item);
 	 	 	 			listBean.add(treePanel);
 	 	 			}
 	 	 			
 	 	 		}
 			}
 			
 	
 		
 		return listBean;
		
	}

	
}
