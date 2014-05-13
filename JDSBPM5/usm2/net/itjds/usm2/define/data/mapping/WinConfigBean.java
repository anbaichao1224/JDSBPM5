package net.itjds.usm2.define.data.mapping;

import net.itjds.usm2.define.mapping.ExtBean;



public class WinConfigBean implements ExtBean{

	String title ;
	int width;
	int height;
	boolean maximizable;
	public String getId() {
		return null;
	}
	public void setId(String id) {
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public boolean getMaximizable() {
		return maximizable;
	}
	public void setMaximizable(boolean maximizable) {
		this.maximizable = maximizable;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public Class getClazz() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setClazz(Class clazz) {
		// TODO Auto-generated method stub
		
	}
	
	
}
