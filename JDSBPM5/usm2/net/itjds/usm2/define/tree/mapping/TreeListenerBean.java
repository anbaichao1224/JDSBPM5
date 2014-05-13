package net.itjds.usm2.define.tree.mapping;

import net.itjds.usm2.define.enums.ElementEvent;
import net.itjds.usm2.define.mapping.ExtBean;




public class TreeListenerBean implements ExtBean{

	private static final long serialVersionUID = 1L;
	
	public ElementEvent eventname;
	public String function;
	public String id;

	
	public String getFunction() {
		return function;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public ElementEvent getEventname() {
		return eventname;
	}

	public void setEventname(ElementEvent eventname) {
		this.eventname = eventname;
	}

	public Class getClazz() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setClazz(Class clazz) {
		// TODO Auto-generated method stub
		
	}


}
