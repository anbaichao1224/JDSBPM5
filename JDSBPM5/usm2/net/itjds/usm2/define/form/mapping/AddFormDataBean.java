package net.itjds.usm2.define.form.mapping;

import net.itjds.usm2.define.mapping.ExtBean;



public class AddFormDataBean implements ExtBean{

	String panelClazz ;
	Object[] initparams;
	
	
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}
	
	
	public String getPanelClazz() {
		return panelClazz;
	}
	public void setPanelClazz(String panelClazz) {
		this.panelClazz = panelClazz;
	}
	public Object[] getInitparams() {
		return initparams;
	}
	public void setInitparams(Object[] initparams) {
		this.initparams = initparams;
	}
	public Class getClazz() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setClazz(Class clazz) {
		// TODO Auto-generated method stub
		
	}
	
}
