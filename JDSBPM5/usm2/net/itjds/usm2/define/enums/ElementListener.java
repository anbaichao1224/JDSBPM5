package net.itjds.usm2.define.enums;

public enum ElementListener {
	click("click"),
	beforeload("beforeload"), 
	dblclick("dblclick"), 
	contextmenu("contextmenu");

	private String type;

	public String getType() {
		return type;
	}

	ElementListener(String type) {
		this.type = type;
	}
}
