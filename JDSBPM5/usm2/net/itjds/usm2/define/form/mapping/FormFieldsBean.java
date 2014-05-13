package net.itjds.usm2.define.form.mapping;

import net.itjds.usm2.define.enums.ElementFieldType;
import net.itjds.usm2.define.enums.ElementItem;
import net.itjds.usm2.define.enums.ElementVtype;
import net.itjds.usm2.define.mapping.FieldBean;



public class FormFieldsBean extends FieldBean{

	private ElementFieldType xtype;

	private ElementVtype vtype;

	private String name;

	private String id;

	private String value;

	private Boolean readOnly;

	private Boolean validateOnBlur;

	private int validationDelay;

	private Boolean disabled;

	private String fieldLabel;

	private Boolean hideLabel;

	private Boolean hidden;

	private String labelSeparator;
	
	private Boolean allowBlank;
	
	private int  maxLength;

    private String store;
   
    private String boxLabel;
    
    private String column;
    
    private int maxValue;
    
    private ElementItem  item;
    
    private String model;

    private String src;
    
    private String regex;
    
    private String inputType;
    
    private String regexText;
    
    private String tableName;
    
    private String columnName;
    
	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
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

	public ElementItem getItem() {
		return item;
	}

	public void setItem(ElementItem item) {
		this.item = item;
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

	public Boolean getAllowBlank() {
		return allowBlank;
	}

	public void setAllowBlank(Boolean allowBlank) {
		this.allowBlank = allowBlank;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public String getFieldLabel() {
		return fieldLabel;
	}

	public void setFieldLabel(String fieldLabel) {
		this.fieldLabel = fieldLabel;
	}

	public Boolean getHideLabel() {
		return hideLabel;
	}

	public void setHideLabel(Boolean hideLabel) {
		this.hideLabel = hideLabel;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
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

	public Boolean getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}

	public Boolean getValidateOnBlur() {
		return validateOnBlur;
	}

	public void setValidateOnBlur(Boolean validateOnBlur) {
		this.validateOnBlur = validateOnBlur;
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

	public ElementVtype getVtype() {
		return vtype;
	}

	public void setVtype(ElementVtype vtype) {
		this.vtype = vtype;
	}

	public ElementFieldType getXtype() {
		return xtype;
	}

	public void setXtype(ElementFieldType xtype) {
		this.xtype = xtype;
	}

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}


}
