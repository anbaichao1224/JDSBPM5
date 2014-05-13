package net.itjds.usm2.define.mapping;

import net.itjds.usm2.define.data.mapping.AjaxDataBean;
import net.itjds.usm2.define.enums.ElementButton;

public class ButtonBean implements ExtBean {


	private ElementButton text;

	private String iconCls;
	
	private boolean hidden;
	
	private String handler;
	
	private String otherText;
	
	private AjaxDataBean ajax;

	public String getOtherText() {
		return otherText;
	}

	public void setOtherText(String otherText) {
		this.otherText = otherText;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public ElementButton getText() {
		return text;
	}

	public void setText(ElementButton text) {
		this.text = text;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}

	public AjaxDataBean getAjax() {
		return ajax;
	}

	public void setAjax(AjaxDataBean ajax) {
		this.ajax = ajax;
	}

	public Class getClazz() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setClazz(Class clazz) {
		// TODO Auto-generated method stub
		
	}


}
