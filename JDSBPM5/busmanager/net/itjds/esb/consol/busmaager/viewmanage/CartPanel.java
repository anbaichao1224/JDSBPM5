package net.itjds.esb.consol.busmaager.viewmanage;

import net.itjds.bpm.data.xmlproxy.manager.EsbBean;
import net.itjds.bpm.data.xmlproxy.manager.EsbBeanFactory;
import net.itjds.bpm.data.xmlproxy.manager.ExpressionTempBean;
import net.itjds.usm2.define.cartlayoutpanel.annotation.CartItemsDefine;
import net.itjds.usm2.define.cartlayoutpanel.annotation.CartPanelDefine;
import net.itjds.usm2.define.mapping.PanelBean;
import net.itjds.esb.consol.busmaager.viewmanage.cart.CartPanelTabPanel;
import net.itjds.esb.consol.busmaager.viewmanage.esb.EsbBeanFormPanel;
import net.itjds.esb.consol.busmaager.viewmanage.esb.EsbBeanTabPanel;
import net.itjds.esb.consol.busmaager.viewmanage.form.FormPanelTabPanel;
import net.itjds.esb.consol.busmaager.viewmanage.grid.GridPanelTabPanel;
import net.itjds.esb.consol.busmaager.viewmanage.simple.SimplePanelTabPanel;
import net.itjds.esb.consol.busmaager.viewmanage.tab.TabPanelTabPanel;
import net.itjds.esb.consol.busmaager.viewmanage.tree.TreePanelTabPanel;


@CartPanelDefine(height = 500, width = 800,activeItem = 0,xtype = "CartPanel")
public class CartPanel {
	

	private EsbBeanFactory factory;
	private Object nodeBean;
	private PanelBean panelBean;

	
	
	public CartPanel(EsbBeanFactory factory,Object nodeBean,PanelBean panelBean){
		this.factory=factory;
		this.nodeBean=nodeBean;
 		 this.panelBean=panelBean;
 		
	}
	


	@CartItemsDefine(title = "��ӭҳ��",html="��ӭҳ��")
	public WelcomePanel getWelcomePanel() {
		return new WelcomePanel();
	}
	

	@CartItemsDefine(title = "��ͼ��Ϣ")
	public ViewTabPanel getViewTabPanel() {
		return new ViewTabPanel(nodeBean);
	}
	
	@CartItemsDefine(title = "cartPanel��Ϣ")
	public CartPanelTabPanel getCartPanelBean() {
		return new CartPanelTabPanel(panelBean);
	}
	@CartItemsDefine(title = "simplePanel��Ϣ")
	public SimplePanelTabPanel getSimplePanelBean() {
		return new SimplePanelTabPanel(panelBean);
	}
	@CartItemsDefine(title = "tabPanel��Ϣ")
	public TabPanelTabPanel getTabPanelBean() {
		return new TabPanelTabPanel(panelBean);
	}
	@CartItemsDefine(title = "treePanel��Ϣ")
	public TreePanelTabPanel getTreePanelBean() {
		return new TreePanelTabPanel(panelBean);
	}
	@CartItemsDefine(title = "from������Ϣ")
	public FormPanelTabPanel getFormPanelBean() {
		return new FormPanelTabPanel(panelBean);
	}
	@CartItemsDefine(title = "gridPanel��Ϣ")
	public GridPanelTabPanel getGridPanelBean() {
		return new GridPanelTabPanel(panelBean);
	}
	@CartItemsDefine(title = "gridPanel��Ϣ")
	public EsbBeanTabPanel getEsbFormPanelBean() {
		return new EsbBeanTabPanel(nodeBean);
	}
	@CartItemsDefine(title = "����ͼ")
	public ViewTabPanel getViewPortBean() {
		return new ViewTabPanel(nodeBean);
	}

}
