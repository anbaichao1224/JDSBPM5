/**
 * 布局<client>
 */
package net.itjds.esb.consol.busmaager.viewmanage;


import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.manager.EsbBean;
import net.itjds.bpm.data.xmlproxy.manager.EsbBeanFactory;
import net.itjds.bpm.data.xmlproxy.manager.ExpressionTempBean;
import net.itjds.bpm.data.xmlproxy.manager.USMTempBean;
import net.itjds.usm2.define.annotation.PanelItemDefine;
import net.itjds.usm2.define.annotation.ViewPortDefine;
import net.itjds.usm2.define.mapping.FieldBean;
import net.itjds.usm2.define.mapping.PanelBean;


@ViewPortDefine(title = "总线管理主界面", layout = "border",topNodeId="$ViewManage")

@EsbBeanAnnotation(id="ViewManage",
		name="视图管理",
		expressionArr="ViewManage($currBusBean(),GetCurrPanelBean(R(\"esbkey\"),R(\"uuid\")))",
		desc="视图管理 by DAOTools ",
		dataType="context"
		)
	

public class ViewManage {

	private EsbBeanFactory factory;
	private Object nodeBean;
	private PanelBean panelBean;


	public ViewManage(Object nodeBean,PanelBean panelBean){
		
		 this.nodeBean=nodeBean;
 		 this.panelBean=panelBean;
 		 this.factory=EsbBeanFactory.newInstance();
	}
	

	
	@PanelItemDefine(height = 600, width = 800, region = "center",title = "视图列表")
	public CartPanel getCartPanel() {
		return new CartPanel(factory,nodeBean,panelBean);
	}


	@PanelItemDefine(height = 300, width = 250, region = "west",title = "生成视图树")
	public ViewTreePanel getViewTreePanel() {
		return new ViewTreePanel(factory);
	}
	

}
