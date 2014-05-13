package net.itjds.usm2.define.simplepanel.mapping;




import net.itjds.usm2.define.enums.ElementPanelLayout;

import net.itjds.usm2.define.mapping.PanelBean;


public class SimplePanelBean extends PanelBean {
  
	
	public String html;
	
	public String bodyStyle;
	public ElementPanelLayout layout;
	
	
	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public ElementPanelLayout getLayout() {
		return layout;
	}

	public void setLayout(ElementPanelLayout layout) {
		this.layout = layout;
	}

	public String getBodyStyle() {
		return bodyStyle;
	}

	public void setBodyStyle(String bodyStyle) {
		this.bodyStyle = bodyStyle;
	}

	
	

}
