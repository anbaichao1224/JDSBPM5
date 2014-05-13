package net.itjds.usm2.define.enums;

public enum ElementDataType {
	string("string"),
	Int("int"),
	Float("float"), 
	bool("boolean"), 
	date("date"),
	auto("auto");
	
	private String type;

	public String getType() {
		return type;
	}
	ElementDataType(String type){
		this.type = type;
	}

}
