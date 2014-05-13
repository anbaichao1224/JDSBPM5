package net.itjds.usm2.define.enums;

public enum ElementPanelLayout {
	accordion("accordion"), border("border"), card("card"),column("column"),fit("fit"),table("table");
	private String type;

	public String getType() {
		return type;
	}

	ElementPanelLayout(String type) {
		this.type = type;
	}
}
