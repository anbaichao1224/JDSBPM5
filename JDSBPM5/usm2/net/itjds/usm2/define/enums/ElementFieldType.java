package net.itjds.usm2.define.enums;

public enum ElementFieldType {
	Checkbox("checkbox"),
	ComboBox("combo"),
	DateField("datefield"),
	Hidden("hidden"),
	HTMLEditor("htmleditor"),
	NumberField("numberfield"),
	Radio("radio"),
	TextArea("textarea"),
	TextField("textfield"),
	TimeField("timefield"),
	TriggerField("triggerfield"),
	FileuploadField("fileuploadfield"),
    Panel("panel"),
    Multiselect("Multiselect"),
    Field("field"),
    FieldSet("fieldset"),
	None("none");

	private String type;

	public String getType() {
		return type;
	}
	ElementFieldType(String type){
		this.type = type;
	}
	
}
