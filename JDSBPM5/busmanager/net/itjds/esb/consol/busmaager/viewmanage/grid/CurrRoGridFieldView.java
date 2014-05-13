package net.itjds.esb.consol.busmaager.viewmanage.grid;


import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.common.util.DateUtility;
import net.itjds.usm2.define.data.annotation.AjaxDataDefine;
import net.itjds.usm2.define.enums.*;
import net.itjds.usm2.define.form.annotation.FormFieldDefine;
import net.itjds.usm2.define.form.annotation.FormPanelDefine;
import net.itjds.usm2.define.form.mapping.FormFieldsBean;
import net.itjds.usm2.define.grid.mapping.ColumnBean;
import net.itjds.usm2.define.mapping.FieldBean;

@FormPanelDefine(
		labelWidth = 0,
		labelAlign = ElementAlign.left,
		buttons = {ElementButton.save,ElementButton.abort},
		loadData=@AjaxDataDefine(
				url="/expression.jsp?expression=$JSONForm($currRoGridFieldView)"
		),
		updateData=@AjaxDataDefine(
				url="/update.action?expression=$RoOrg.addRoPerson($currFormData)"
		),
		fieldsIndex={
			"uuid",//UUID
			"name",//字段英文名
			"cnname",//字段中文名
			"width",//控件宽度
			"sortable",//是否排序
			"hidden",//是否隐藏
			
			"viewpath"//
			
		}		
)

@MapdaoBeanAnnotation(id="currRoGridFieldView",
		name="获取当前列表（COL）对象数据",
		expressionArr="CurrRoGridFieldView($currFieldBean())",
		desc="获取当前列表（COL）对象数据",
		dataType="action"
)


public class CurrRoGridFieldView{
	
 	private ColumnBean columnBean;
 	public CurrRoGridFieldView(FieldBean bean){
 	    this.columnBean=(ColumnBean)bean;
 	}
 
 
	@FormFieldDefine(id = "$currRoGridField.uuid", 
				 name = "$currRoGridField.uuid", 
				 fieldLabel = "uuid",
				 xtype = ElementFieldType.Hidden,
				 vtype = ElementVtype.none, 
				 validateOnBlur = true, 
				 allowBlank = true,
				 maxLength = 64
	)
	public java.lang.String getUuid(){
		return columnBean.getId();
	}
	public void setUuid(java.lang.String id)
	{
		columnBean.setId(id);
	}
	@FormFieldDefine(id = "$currRoGridField.name", 
			 name = "$currRoGridField.name", 
			 fieldLabel = "列标识",
			 xtype = ElementFieldType.TextField,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 64
	)
	public java.lang.String getName(){
		return columnBean.getName();
	}
	public void setName(java.lang.String name)
	{
		columnBean.setName(name);
	}

	@FormFieldDefine(id = "$currRoGridField.cnname", 
			 name = "$currRoGridField.cnname", 
			 fieldLabel = "列标题",
			 xtype = ElementFieldType.TextField,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 64
	)
	public java.lang.String getCnname(){
		return columnBean.getHeader();
	}
	public void setCnname(java.lang.String header)
	{
		columnBean.setHeader(header);
	}
	
	
	@FormFieldDefine(id = "$currRoGridField.width", 
			 name = "$currRoGridField.width", 
			 fieldLabel = "列宽",
			 xtype = ElementFieldType.TextField,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 64
	)
	public java.lang.Integer getWidth(){
		return columnBean.getWidth();
	}
	public void setWidth(java.lang.Integer width)
	{
		columnBean.setWidth(width);
	}
	@FormFieldDefine(id = "$currRoGridField.sortable", 
			 name = "$currRoGridField.sortable", 
			 fieldLabel = "是否排序",
			 xtype = ElementFieldType.ComboBox,
			 model="local",
			 item=ElementItem.isyes,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 64
	)
	public java.lang.String getSortable(){
		return columnBean.getSortable().toString();
	}
	public void setSortable(java.lang.String sortable)
	{
		columnBean.setSortable(Boolean.valueOf(sortable));
	}
	
	
	@FormFieldDefine(id = "$currRoGridField.hidden", 
			 name = "$currRoGridField.hidden", 
			 fieldLabel = "是否隐藏",
			 xtype = ElementFieldType.ComboBox,
			 model="local",
			 item=ElementItem.isyes,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 64
	)
	public java.lang.String getHidden(){
		return columnBean.getHidden().toString();
	}
	public void setHidden(java.lang.String hidden)
	{
		columnBean.setHidden(Boolean.valueOf(hidden));
	}
	
	
	
	

	@FormFieldDefine(id = "$currRoGridField.viewpath", 
			 name = "$currRoGridField.viewpath", 
			 fieldLabel = "表达式",
			 xtype = ElementFieldType.TextArea,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 200
	)
	public java.lang.String getViewpath(){
		return columnBean.getPath();
	}
	public void setViewpath(java.lang.String path)
	{
		columnBean.setPath(path);
	}
	

}
