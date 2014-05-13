package net.itjds.usm2.define.enums;

public enum ElementAlign {
	center("center"), left("left"), right("right");

	private String type;

	public String getType() {
		return type;
	}

	ElementAlign(String type) {
		this.type = type;
	}
}
