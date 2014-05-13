package net.itjds.esb.consol.busmaager.viewmanage.object;

import net.itjds.usm2.define.enums.ElementFieldType;
import net.itjds.usm2.define.enums.ElementItem;
import net.itjds.usm2.define.enums.ElementVtype;
import net.itjds.usm2.define.mapping.FieldBean;



public class RoFormField extends RoFormPanel{

	private String xtype;

	private String vtype;

	private String name;

	private String cnname;
	private String id;

	private String value;

	private String readOnly;

	private String validateOnBlur;

	private int validationDelay;

	private String disabled;

	private String fieldLabel;

	private String hideLabel;

	private String hidden;

	private String labelSeparator;
	
	private String allowBlank;
	
	private int  maxLength;

    private String store;
   
    private String boxLabel;
    
    private String column;
    
    private int maxValue;
    
    private String  item;
    
    private String model;

    private String src;
    
    private String regex;
    
    private String inputType;
    
    private String regexText;
    
	private String comboxkey;
	public String getComboxkey() {
		return comboxkey;
	}



	public void setComboxkey(String comboxkey) {
		this.comboxkey = comboxkey;
	}

	public String getRegexText() {
		return regexText;
	}

	public void setRegexText(String regexText) {
		this.regexText = regexText;
	}


	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}


	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getBoxLabel() {
		return boxLabel;
	}

	public void setBoxLabel(String boxLabel) {
		this.boxLabel = boxLabel;
	}

	public String getStore() {
	return store;
}

public void setStore(String store) {
	this.store = store;
}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}



	public String getFieldLabel() {
		return fieldLabel;
	}

	public void setFieldLabel(String fieldLabel) {
		this.fieldLabel = fieldLabel;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabelSeparator() {
		return labelSeparator;
	}

	public void setLabelSeparator(String labelSeparator) {
		this.labelSeparator = labelSeparator;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public long getValidationDelay() {
		return validationDelay;
	}

	public void setValidationDelay(int validationDelay) {
		this.validationDelay = validationDelay;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	

	public String getXtype() {
		return xtype;
	}

	public void setXtype(String xtype) {
		this.xtype = xtype;
	}

	public String getVtype() {
		return vtype;
	}

	public void setVtype(String vtype) {
		this.vtype = vtype;
	}

	public String getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(String readOnly) {
		this.readOnly = readOnly;
	}

	public String getValidateOnBlur() {
		return validateOnBlur;
	}

	public void setValidateOnBlur(String validateOnBlur) {
		this.validateOnBlur = validateOnBlur;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getHideLabel() {
		return hideLabel;
	}

	public void setHideLabel(String hideLabel) {
		this.hideLabel = hideLabel;
	}

	public String getHidden() {
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public String getAllowBlank() {
		return allowBlank;
	}

	public void setAllowBlank(String allowBlank) {
		this.allowBlank = allowBlank;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	public String getCnname() {
		return cnname;
	}

	public void setCnname(String cnname) {
		this.cnname = cnname;
	}


}
