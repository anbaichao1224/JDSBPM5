package net.itjds.esb.consol.busmaager.viewmanage.form;


import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.usm2.define.data.annotation.AjaxDataDefine;
import net.itjds.usm2.define.enums.*;
import net.itjds.usm2.define.form.annotation.FormFieldDefine;
import net.itjds.usm2.define.form.annotation.FormPanelDefine;
import net.itjds.usm2.define.form.mapping.FormFieldsBean;

@FormPanelDefine(
		labelWidth = 0,
		labelAlign = ElementAlign.left,
		buttons = {ElementButton.save,ElementButton.abort},
		loadData=@AjaxDataDefine(
				url="/expression.jsp?expression=$JSONForm($currFieldBean)"
		),
		updateData=@AjaxDataDefine(
				url="/update.action?expression=$UsmData.modifyColumn($currFormData)"
		),
		fieldsIndex={
			"uuid",//UUID
			"name",//字段英文名
			"cnname",//字段中文名
			"fieldLabel",//控件标签
			"width",//控件宽度
			"xtype",//控件类型
			"comboxkey",//下拉框键值
			"vtype",//验证类型
			"readOnly",//是否只读
			"allowBlank",//是否为空
			"maxLength",//最大长度
			"maxValue",//最大值
			"viewname",//文件名称
			"viewpath"//文件路径
			
		}		
)

@EsbBeanAnnotation(id="currFormFieldView",
		name="编辑列表（col）列字段",
		expressionArr="TableColView($currFormData())",
		desc="编辑用户FormField字段视图",
		dataType="action"
)


public class TableColView{
	private FormFieldsBean formFieldsBean;
 	
 	public TableColView(FormFieldsBean bean){
 		if(bean == null){
	    	 this.formFieldsBean = new FormFieldsBean();
	    }else{
	    	 this.formFieldsBean=bean;	
	    }
 		
 	}

 
 
	@FormFieldDefine(id = "$currRoFormField.uuid", 
				 name = "$currRoFormField.uuid", 
				 fieldLabel = "uuid",
				 xtype = ElementFieldType.Hidden,
				 vtype = ElementVtype.none, 
				 validateOnBlur = true, 
				 allowBlank = true,
				 maxLength = 64
	)
	public java.lang.String getUuid(){
		return formFieldsBean.getId();
	}
	public void setUuid(java.lang.String id)
	{
		formFieldsBean.setId(id);
	}
	@FormFieldDefine(id = "$currRoFormField.name", 
			 name = "$currRoFormField.name", 
			 fieldLabel = "字段英文名",
			 xtype = ElementFieldType.TextField,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 64
	)
	public java.lang.String getName(){
		return formFieldsBean.getName();
	}
	public void setName(java.lang.String name)
	{
		formFieldsBean.setName(name);
	}

	@FormFieldDefine(id = "$currRoFormField.cnname", 
			 name = "$currRoFormField.cnname", 
			 fieldLabel = "字段中文名",
			 xtype = ElementFieldType.TextField,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 64
	)
	public java.lang.String getCnname(){
		return formFieldsBean.getFieldLabel();
	}
	public void setCnname(java.lang.String fieldLabel)
	{
		formFieldsBean.setFieldLabel(fieldLabel);
	}
	
	@FormFieldDefine(id = "$currRoFormField.fieldLabel", 
			 name = "$currRoFormField.fieldLabel", 
			 fieldLabel = "控件标签",
			 xtype = ElementFieldType.TextField,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 64
	)
	public java.lang.String getFieldLabel(){
		return formFieldsBean.getFieldLabel();
	}
	public void setFieldLabel(java.lang.String fieldLabel)
	{
		formFieldsBean.setFieldLabel(fieldLabel);
	}
	
	@FormFieldDefine(id = "$currRoFormField.width", 
			 name = "$currRoFormField.width", 
			 fieldLabel = "控件宽度",
			 xtype = ElementFieldType.TextField,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 64
	)
	public java.lang.Integer getWidth(){
		return formFieldsBean.getWidth();
	}
	public void setWidth(java.lang.Integer width)
	{
		formFieldsBean.setWidth(width);
	}
	@FormFieldDefine(id = "$currRoFormField.xtype", 
			 name = "$currRoFormField.xtype", 
			 fieldLabel = "控件类型",
			 xtype = ElementFieldType.ComboBox,
			 model="local",
			 item=ElementItem.xtype,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 64
	)
	public java.lang.String getXtype(){
		return formFieldsBean.getXtype().getType();
	}
	public void setXtype(java.lang.String xtype)
	{
//		formFieldsBean.setXtype(xtype);
	}
	
	@FormFieldDefine(id = "$currRoFormField.comboxkey", 
			 name = "$currRoFormField.comboxkey", 
			 fieldLabel = "下拉框键值",
			 xtype = ElementFieldType.TextField,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 64
	)
	public java.lang.String getComboxkey(){
		return null;
	}
	public void setComboxkey(java.lang.String xtype)
	{
		
	}
	
	
	@FormFieldDefine(id = "$currRoFormField.vtype", 
			 name = "$currRoFormField.vtype", 
			 fieldLabel = "验证类型",
			 xtype = ElementFieldType.ComboBox,
			 model="local",
			 item=ElementItem.vtype,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 64
	)
	public java.lang.String getVtype(){
		return formFieldsBean.getVtype().getType();
	}
	public void setVtype(java.lang.String vtype)
	{
		//formFieldsBean.setVtype()(Boolean.valueOf(readOnly));
	}
	@FormFieldDefine(id = "$currRoFormField.readOnly", 
			 name = "$currRoFormField.readOnly", 
			 fieldLabel = "是否只读",
			 xtype = ElementFieldType.ComboBox,
			 model="local",
			 item=ElementItem.isyes,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 64
	)
	public java.lang.String getReadOnly(){
		return formFieldsBean.getReadOnly().toString();
	}
	public void setReadOnly(java.lang.String readOnly)
	{
		formFieldsBean.setReadOnly(Boolean.valueOf(readOnly));
	}

	@FormFieldDefine(id = "$currRoFormField.allowBlank", 
			 name = "$currRoFormField.allowBlank", 
			 fieldLabel = "是否为空",
			 xtype = ElementFieldType.ComboBox,
			 model="local",
			 item=ElementItem.isyes,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 64
	)
	public java.lang.String getAllowBlank(){
		return formFieldsBean.getAllowBlank().toString();
	}
	public void setAllowBlank(java.lang.String allowBlank)
	{
		formFieldsBean.setAllowBlank(Boolean.valueOf(allowBlank));
	}
	
	@FormFieldDefine(id = "$currRoFormField.maxLength", 
			 name = "$currRoFormField.maxLength", 
			 fieldLabel = "最大长度",
			 xtype = ElementFieldType.TextField,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 64
	)
	public java.lang.Integer getMaxLength(){
		return formFieldsBean.getMaxLength();
	}
	public void setMaxLength(java.lang.Integer maxLength)
	{
		formFieldsBean.setMaxLength(maxLength);
	}

	@FormFieldDefine(id = "$currRoFormField.maxValue", 
			 name = "$currRoFormField.maxValue", 
			 fieldLabel = "最大值",
			 xtype = ElementFieldType.TextField,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 64
	)
	public java.lang.Integer getMaxValue(){
		return formFieldsBean.getMaxValue();
	}
	public void setMaxValue(java.lang.Integer maxValue)
	{
		formFieldsBean.setMaxValue(maxValue);
	}
	
	
	@FormFieldDefine(id = "$currRoFormField.viewname", 
			 name = "$currRoFormField.viewname", 
			 fieldLabel = "文件名称",
			 xtype = ElementFieldType.TextField,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 64
	)
	public java.lang.String getViewname(){
		return formFieldsBean.getTitle();
	}
	public void setViewname(java.lang.String title)
	{
		formFieldsBean.setTitle(title);
	}

	@FormFieldDefine(id = "$currRoFormField.viewpath", 
			 name = "$currRoFormField.viewpath", 
			 fieldLabel = "文件路径",
			 xtype = ElementFieldType.TextField,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 64
	)
	public java.lang.String getViewpath(){
		return formFieldsBean.getPath();
	}
	public void setViewpath(java.lang.String path)
	{
		formFieldsBean.setPath(path);
	}
	

}
