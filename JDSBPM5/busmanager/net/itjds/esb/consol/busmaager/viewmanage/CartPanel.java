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
	


	@CartItemsDefine(title = "欢迎页面",html="欢迎页面")
	public WelcomePanel getWelcomePanel() {
		return new WelcomePanel();
	}
	

	@CartItemsDefine(title = "视图信息")
	public ViewTabPanel getViewTabPanel() {
		return new ViewTabPanel(nodeBean);
	}
	
	@CartItemsDefine(title = "cartPanel信息")
	public CartPanelTabPanel getCartPanelBean() {
		return new CartPanelTabPanel(panelBean);
	}
	@CartItemsDefine(title = "simplePanel信息")
	public SimplePanelTabPanel getSimplePanelBean() {
		return new SimplePanelTabPanel(panelBean);
	}
	@CartItemsDefine(title = "tabPanel信息")
	public TabPanelTabPanel getTabPanelBean() {
		return new TabPanelTabPanel(panelBean);
	}
	@CartItemsDefine(title = "treePanel信息")
	public TreePanelTabPanel getTreePanelBean() {
		return new TreePanelTabPanel(panelBean);
	}
	@CartItemsDefine(title = "from窗体信息")
	public FormPanelTabPanel getFormPanelBean() {
		return new FormPanelTabPanel(panelBean);
	}
	@CartItemsDefine(title = "gridPanel信息")
	public GridPanelTabPanel getGridPanelBean() {
		return new GridPanelTabPanel(panelBean);
	}
	@CartItemsDefine(title = "gridPanel信息")
	public EsbBeanTabPanel getEsbFormPanelBean() {
		return new EsbBeanTabPanel(nodeBean);
	}
	@CartItemsDefine(title = "主视图")
	public ViewTabPanel getViewPortBean() {
		return new ViewTabPanel(nodeBean);
	}

}
