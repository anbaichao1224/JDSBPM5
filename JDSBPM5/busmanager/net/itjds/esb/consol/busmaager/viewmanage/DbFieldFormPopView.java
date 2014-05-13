package net.itjds.esb.consol.busmaager.viewmanage;


import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.common.dbutil.ColInfo;
import net.itjds.common.util.DateUtility;
import net.itjds.usm2.define.data.annotation.AjaxDataDefine;
import net.itjds.usm2.define.enums.*;
import net.itjds.usm2.define.form.annotation.FormFieldDefine;
import net.itjds.usm2.define.form.annotation.FormPanelDefine;
import net.itjds.usm2.define.form.mapping.FormFieldsBean;
import net.itjds.usm2.define.mapping.FieldBean;

@FormPanelDefine(
		labelWidth = 0,
		labelAlign = ElementAlign.left,
		buttons = {ElementButton.save,ElementButton.abort},
		loadData=@AjaxDataDefine(
				url="/expression.jsp?expression=$JSONForm($currFormData)"
		),
		updateData=@AjaxDataDefine(
				url="/update.action?expression=$UsmData.modifyColumn($currFormData)"
		),
		fieldsIndex={
			"uuid",//UUID
			"name",//字段英文名
			"cnname",//字段中文名
			"type",
			"canNull",//是否为空
			"length"//最大长度
		}		
)

@EsbBeanAnnotation(id="currDbFieldForm",
		name="编辑DbField字段视图",
		expressionArr="DbFieldFormPopView($currFormData())",
		desc="编辑DbField字段视图",
		dataType="action"
)


public class DbFieldFormPopView{
	private ColInfo formFieldsBean;
 	
 	public DbFieldFormPopView(ColInfo info){
 		this.formFieldsBean=info;
 		
 	}

 
 
	@FormFieldDefine(id = "$currDbFieldForm.uuid", 
				 name = "$currDbFieldForm.uuid", 
				 fieldLabel = "uuid",
				 xtype = ElementFieldType.Hidden,
				 vtype = ElementVtype.none, 
				 validateOnBlur = true, 
				 allowBlank = true,
				 maxLength = 64
	)
	public java.lang.String getUuid(){
		return formFieldsBean.getName();
	}
	public void setUuid(java.lang.String id)
	{
		formFieldsBean.setName(id);
	}
	@FormFieldDefine(id = "$currDbFieldForm.name", 
			 name = "$currDbFieldForm.name", 
			 fieldLabel = "字段名",
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

	@FormFieldDefine(id = "$currDbFieldForm.cnname", 
			 name = "$currDbFieldForm.cnname", 
			 fieldLabel = "字段中文名",
			 xtype = ElementFieldType.TextField,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 64
	)
	public java.lang.String getCnname(){
		return formFieldsBean.getCnname();
	}
	public void setCnname(java.lang.String cnname)
	{
		formFieldsBean.setCnname(cnname);
	}
	


	
	@FormFieldDefine(id = "$currDbFieldForm.type", 
			 name = "$currDbFieldForm.type", 
			 fieldLabel = "字段类型",
			 xtype = ElementFieldType.ComboBox,
			 model="local",
			 item=ElementItem.vtype,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 64
	)
	public java.lang.String getType(){
		return formFieldsBean.getType();
	}
	public void setType(java.lang.String type)
	{
		formFieldsBean.setType(type);
	}
	@FormFieldDefine(id = "$currDbFieldForm.canNull", 
			 name = "$currDbFieldForm.canNull", 
			 fieldLabel = "是否可以为空",
			 xtype = ElementFieldType.ComboBox,
			 model="local",
			 item=ElementItem.isyes,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 64
	)
	public java.lang.String getCanNull(){
		return formFieldsBean.isCanNull()?"true":"false";
	}
	public void setCanNull(java.lang.String readOnly)
	{
		formFieldsBean.setCanNull(Boolean.valueOf(readOnly));
	}

	
	@FormFieldDefine(id = "$currDbFieldForm.length", 
			 name = "$currDbFieldForm.length", 
			 fieldLabel = "最大长度",
			 xtype = ElementFieldType.TextField,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 64
	)
	public java.lang.Integer getLength(){
		return formFieldsBean.getLength();
	}
	public void setLength(java.lang.Integer maxLength)
	{
		formFieldsBean.setLength(maxLength);
	}


}
