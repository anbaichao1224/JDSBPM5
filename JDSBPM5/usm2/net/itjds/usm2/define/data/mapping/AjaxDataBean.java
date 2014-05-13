package net.itjds.usm2.define.data.mapping;

import net.itjds.usm2.define.mapping.ExtBean;



public class AjaxDataBean implements ExtBean{

	String url ;
	Object[] params;
	String viewId;
	String expression;
	WinConfigBean winConfig;
	
	public WinConfigBean getWinConfig() {
		return winConfig;
	}
	public void setWinConfig(WinConfigBean winConfig) {
		this.winConfig = winConfig;
	}
	public String getId() {
		return null;
	}
	public void setId(String id) {
		}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Object[] getParams() {
		return params;
	}
	public void setParams(Object[] params) {
		this.params = params;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public String getViewId() {
		return viewId;
	}
	public void setViewId(String viewId) {
		this.viewId = viewId;
	}
	public Class getClazz() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setClazz(Class clazz) {
		// TODO Auto-generated method stub
		
	}

}
