package net.itjds.usm2.define.mapping;



public class ViewPortBean extends PanelBean{

	private String layout;
	private String topNodeId;
	


	public String getFtlUrl() {
		if (this.ftlUrl==null){
			ftlUrl="main.ftl";
		}
		return ftlUrl;
	}


	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}


	public String getTopNodeId() {
		return topNodeId;
	}


	public void setTopNodeId(String topNodeId) {
		this.topNodeId = topNodeId;
	}
	

	
}
