package net.itjds.fdt.define.designer.metadata.datatype;

import net.itjds.fdt.define.designer.metadata.annotation.Attribute;
import net.itjds.fdt.define.designer.metadata.referencetype.ChildType;
import net.itjds.fdt.define.designer.metadata.referencetype.DataType;
import net.itjds.fdt.define.designer.metadata.referencetype.EditType;
import net.itjds.fdt.define.designer.metadata.referencetype.Level;

/**
 * @author arnold
 * @version $Id: TextareaType.java,v 1.1 2011/06/09 14:43:40 administrator Exp $
 * @since 2007-12-13
 */
public class TextareaType extends BaseFiledType {

	@Attribute(name = "vType", caption = "校验", tip = "对输入内容进行校验", editType = EditType.Selector, childType = ChildType.VType, dataType = { DataType.Select }, level = { Level.Normal })
	private String vType;

	@Attribute(name = "allowBlank", caption = "是否可为空", tip = "是否允许为空", editType = EditType.Selector, childType = ChildType.Choose, dataType = { DataType.Select }, level = { Level.Normal })
	private String allowBlank;

	@Attribute(name = "minLength", caption = "最小长度", tip = "所输入的最小长度", editType = EditType.TextEditor, childType = ChildType.Null, dataType = { DataType.NumberType }, level = { Level.Normal })
	private String minLength = "0";

	@Attribute(name = "maxLength", caption = "最大长度", tip = "所输入的最大长度", editType = EditType.TextEditor, childType = ChildType.Null, dataType = { DataType.NumberType }, level = { Level.Normal })
	private String maxLength = "1000";

	public String getAllowBlank() {
		return allowBlank;
	}

	public void setAllowBlank(String allowBlank) {
		this.allowBlank = allowBlank;
	}

	public String getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}

	public String getMinLength() {
		return minLength;
	}

	public void setMinLength(String minLength) {
		this.minLength = minLength;
	}

	public String getVType() {
		return vType;
	}

	public void setVType(String type) {
		vType = type;
	}

	public Class getSubClass() {
		return this.getClass();
	}
}
