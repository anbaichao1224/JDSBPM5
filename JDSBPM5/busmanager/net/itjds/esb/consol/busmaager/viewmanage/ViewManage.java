/**
 * ����<client>
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


@ViewPortDefine(title = "���߹���������", layout = "border",topNodeId="$ViewManage")

@EsbBeanAnnotation(id="ViewManage",
		name="��ͼ����",
		expressionArr="ViewManage($currBusBean(),GetCurrPanelBean(R(\"esbkey\"),R(\"uuid\")))",
		desc="��ͼ���� by DAOTools ",
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
	

	
	@PanelItemDefine(height = 600, width = 800, region = "center",title = "��ͼ�б�")
	public CartPanel getCartPanel() {
		return new CartPanel(factory,nodeBean,panelBean);
	}


	@PanelItemDefine(height = 300, width = 250, region = "west",title = "������ͼ��")
	public ViewTreePanel getViewTreePanel() {
		return new ViewTreePanel(factory);
	}
	

}
