package net.itjds.fdt.define.designer.metadata.datatype;

import net.itjds.fdt.define.designer.metadata.annotation.Attribute;
import net.itjds.fdt.define.designer.metadata.referencetype.ChildType;
import net.itjds.fdt.define.designer.metadata.referencetype.EditType;

public class DateType extends BaseFiledType
{
  private String pattern;

  @Attribute(name="allowBlank ", caption="是否允许为空", tip="是否允许为空", editType=EditType.Selector, childType=ChildType.Choose, dataType={net.itjds.fdt.define.designer.metadata.referencetype.DataType.Select}, level={net.itjds.fdt.define.designer.metadata.referencetype.Level.Normal})
  private String allowBlank;

  @Attribute(name="blankText", caption="验证为空提示", tip="验证空信息后所提示的信息内容", editType=EditType.TextEditor, childType=ChildType.Null, dataType={net.itjds.fdt.define.designer.metadata.referencetype.DataType.TextType}, level={net.itjds.fdt.define.designer.metadata.referencetype.Level.Normal})
  private String blankText;

  @Attribute(name="maxValue", caption="最大值", tip="可选日期最大值", editType=EditType.Selector, childType=ChildType.Null, dataType={net.itjds.fdt.define.designer.metadata.referencetype.DataType.DateType}, level={net.itjds.fdt.define.designer.metadata.referencetype.Level.Normal})
  private String maxValue;

  @Attribute(name="maxLengthText ", caption="超出最大值提示", tip="超出最大值提示", editType=EditType.TextEditor, childType=ChildType.Null, dataType={net.itjds.fdt.define.designer.metadata.referencetype.DataType.Suspend}, level={net.itjds.fdt.define.designer.metadata.referencetype.Level.Normal})
  private String maxLengthText;

  @Attribute(name="minValue", caption="最小值", tip="可选日期最小值", editType=EditType.Selector, childType=ChildType.Null, dataType={net.itjds.fdt.define.designer.metadata.referencetype.DataType.DateType}, level={net.itjds.fdt.define.designer.metadata.referencetype.Level.Normal})
  private String minValue;

  @Attribute(name="minLengthText", caption="低于最小值提示", tip="文本超短提示信息", editType=EditType.TextEditor, childType=ChildType.Null, dataType={net.itjds.fdt.define.designer.metadata.referencetype.DataType.Suspend}, level={net.itjds.fdt.define.designer.metadata.referencetype.Level.Normal})
  private String minLengthText;

  @Attribute(name="format", caption="格式化方式", tip="展示样式格式", editType=EditType.Selector, childType=ChildType.Null, dataType={net.itjds.fdt.define.designer.metadata.referencetype.DataType.FormatDate}, level={net.itjds.fdt.define.designer.metadata.referencetype.Level.Normal})
  private String format = "Y-m-d";

  public String getAllowBlank() {
    return this.allowBlank;
  }

  public void setAllowBlank(String allowBlank) {
    this.allowBlank = allowBlank;
  }

  public String getMaxLengthText() {
    return this.maxLengthText;
  }

  public void setMaxLengthText(String maxLengthText) {
    this.maxLengthText = maxLengthText;
  }

  public String getMinLengthText() {
    return this.minLengthText;
  }

  public void setMinLengthText(String minLengthText) {
    this.minLengthText = minLengthText;
  }

  public String getFormat() {
    return this.format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  public String getMaxValue() {
    return this.maxValue;
  }

  public void setMaxValue(String maxValue) {
    this.maxValue = maxValue;
  }

  public String getMinValue() {
    return this.minValue;
  }

  public void setMinValue(String minValue) {
    this.minValue = minValue;
  }

  public String getBlankText() {
    return this.blankText;
  }

  public void setBlankText(String blankText) {
    this.blankText = blankText;
  }
}