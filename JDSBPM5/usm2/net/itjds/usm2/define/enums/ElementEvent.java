package net.itjds.usm2.define.enums;

public enum ElementEvent {
	click("click"),
	beforeload("beforeload"), 
	dblclick("dblclick"), 
	contextmenu("contextmenu"),
	check("check");

	private String type;

	public String getType() {
		return type;
	}

	ElementEvent(String type) {
		this.type = type;
	}
}
