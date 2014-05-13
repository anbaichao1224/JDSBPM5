package net.itjds.usm2.define.enums;

public enum ElementCheckModel {
	multiple("multiple"), 
	single("single"), 
	cascade("cascade");
	
	private String type;

	public String getType() {
		return type;
	}

	ElementCheckModel(String type) {
		this.type = type;
	}
}
