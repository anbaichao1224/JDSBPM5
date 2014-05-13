package net.itjds.usm2.define.tree.mapping;

import net.itjds.usm2.define.mapping.ExtBean;


public class TreeLoaderBean implements ExtBean{


	private String baseAttrs;
	
	private String id;


	private String dataUrl;

	public String getDataUrl() {
		return dataUrl;
	}

	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}

	public String getBaseAttrs() {
		return baseAttrs;
	}

	public void setBaseAttrs(String baseAttrs) {
		this.baseAttrs = baseAttrs;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Class getClazz() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setClazz(Class clazz) {
		// TODO Auto-generated method stub
		
	}

}
