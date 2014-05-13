package net.itjds.usm2.define.cartlayoutpanel.mapping;

import net.itjds.usm2.define.mapping.PanelBean;



public class CartPanelBean extends PanelBean {
    private String xtype;
	private String namespace;

	private int activeItem;

	private boolean enableTabScroll;
	

	public boolean isEnableTabScroll() {
		return enableTabScroll;
	}

	public void setEnableTabScroll(boolean enableTabScroll) {
		this.enableTabScroll = enableTabScroll;
	}
	
	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getXtype() {
		return xtype;
	}

	public void setXtype(String xtype) {
		this.xtype = xtype;
	}

	public int getActiveItem() {
		return activeItem;
	}

	public void setActiveItem(int activeItem) {
		this.activeItem = activeItem;
	}
	
}
