package net.itjds.usm2.define.tabpanel.mapping;

import net.itjds.usm2.define.mapping.PanelBean;



public class TabPanelBean extends PanelBean {
private String xtype;
	private String namespace;

	

	private int activeTab;

	private boolean enableTabScroll;


	public int getActiveTab() {
		return activeTab;
	}
	
	
	public void setActiveTab(int activeTab) {
		this.activeTab = activeTab;
	}

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
	
}
