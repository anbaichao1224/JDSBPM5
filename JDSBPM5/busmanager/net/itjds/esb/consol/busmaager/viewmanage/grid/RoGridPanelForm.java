package net.itjds.esb.consol.busmaager.viewmanage.grid;

import net.itjds.usm2.define.data.annotation.AjaxDataDefine;
import net.itjds.usm2.define.enums.ElementAlign;
import net.itjds.usm2.define.enums.ElementButton;
import net.itjds.usm2.define.enums.ElementFieldType;
import net.itjds.usm2.define.enums.ElementItem;
import net.itjds.usm2.define.enums.ElementVtype;
import net.itjds.usm2.define.form.annotation.FormFieldDefine;
import net.itjds.usm2.define.form.annotation.FormPanelDefine;
import net.itjds.usm2.define.form.mapping.FormPanelBean;
import net.itjds.usm2.define.grid.mapping.GridPanelBean;
import net.itjds.usm2.define.mapping.PanelBean;

@FormPanelDefine(
		labelWidth = 150,
		labelAlign = ElementAlign.left,
		buttons = {ElementButton.save,ElementButton.abort},
		 loadData= @AjaxDataDefine(
					url="/expression.jsp?expression=$JSONForm"
			),
		
			updateData= @AjaxDataDefine(
					url="update.action?expression=$UpdateCurrForm"
			),
		fieldsIndex={
			"title",//窗口标题
			"width",//窗口宽度
			"height",//窗口高度
			"maxHeight",//表格的最大高度
			"minColumnWidth",//表格列的最小宽度
			"autoExpandMax",//自动扩充列的最大宽度
			"autoExpandMin",//自动扩充列的最小宽度
			"disableSelection",//是否禁止表格选择
			"enableColumnMove",//是否允许拖放表格列
			"enableColumnResize",//是否允许改变表格列宽
			"enalbeDragDrop",//是否允许拖放表格中的行
			"pageSize"//每页显示条数
				
		}
	
)
public class RoGridPanelForm{
 	
	private PanelBean bean;

	private GridPanelBean gridPanelBean;
	public RoGridPanelForm(PanelBean bean){
	
			this.gridPanelBean=(GridPanelBean) bean;

 	      
 	}
 	public RoGridPanelForm(){

 	}
 
 
	
	
    @FormFieldDefine(id = "$currRoGridPanelForm.title", 
					 name = "$currRoGridPanelForm.title", 
					 fieldLabel = "窗口标题",
					 xtype = ElementFieldType.TextField,
					 model = "local",
					 vtype = ElementVtype.none, 
					 validateOnBlur = true, 
					 allowBlank = false,
					 maxLength = 64
	)
	public java.lang.String getTitle(){
		return gridPanelBean.getTitle();
	}
	
	public void setTitle(java.lang.String title){
		gridPanelBean.setTitle(title);
	}
	
	
	
    @FormFieldDefine(id = "$currRoGridPanelForm.width", 
					 name = "$currRoGridPanelForm.width", 
					 fieldLabel = "窗口宽度",
					 xtype = ElementFieldType.NumberField,
					 model = "local",
					 vtype = ElementVtype.none, 
					 validateOnBlur = true, 
					 allowBlank = true,
					 maxLength = 64
	)
	public java.lang.Integer getWidth(){
		return gridPanelBean.getWidth();
	}
	
	public void setWidth(java.lang.Integer width){
		gridPanelBean.setWidth(width);
	}
	
	
    @FormFieldDefine(id = "$currRoGridPanelForm.height", 
					 name = "$currRoGridPanelForm.height", 
					 fieldLabel = "窗口高度",
					 xtype = ElementFieldType.NumberField,
					 model = "local",
					 vtype = ElementVtype.none, 
					 validateOnBlur = true, 
					 allowBlank = true,
					 maxLength = 800
	)
	public java.lang.Integer getHeight(){
		return gridPanelBean.getHeight();
	}
	
	public void setHeight(java.lang.Integer height){
		gridPanelBean.setHeight(height);
	}
	
	  @FormFieldDefine(id = "$currRoGridPanelForm.maxHeight", 
				 name = "$currRoGridPanelForm.maxHeight", 
				 fieldLabel = "表格最大高度",
				 xtype = ElementFieldType.NumberField,
				 model = "local",
				 vtype = ElementVtype.none, 
				 validateOnBlur = true, 
				 allowBlank = true,
				 maxLength = 60
	)
	public java.lang.Integer getMaxHeight(){
		  
		return gridPanelBean.getMaxHeight();
	}
	
	public void setMaxHeight(java.lang.Integer maxHeight){
		gridPanelBean.setMaxHeight(maxHeight);
	}
//	
	@FormFieldDefine(id = "$currRoGridPanelForm.minColumnWidth", 
			 name = "$currRoGridPanelForm.minColumnWidth", 
			 fieldLabel = "表格列的最小宽度",
			 xtype = ElementFieldType.NumberField,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 80
	)
	public java.lang.Integer getMinColumnWidth(){
		return gridPanelBean.getMinColumnWidth();
	}
	
	public void setMinColumnWidth(java.lang.Integer minColumnWidth){
		gridPanelBean.setMinColumnWidth(minColumnWidth);
	}
	
	@FormFieldDefine(id = "$currRoGridPanelForm.autoExpandMax", 
			 name = "$currRoGridPanelForm.autoExpandMax", 
			 fieldLabel = "自动扩充列的最大宽度",
			 xtype = ElementFieldType.NumberField,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 80
	)
	public java.lang.Integer getAutoExpandMax(){
		return gridPanelBean.getAutoExpandMax();
	}
	
	public void setAutoExpandMax(java.lang.Integer autoExpandMax){
		gridPanelBean.setAutoExpandMax(autoExpandMax);
	}
	
	
	@FormFieldDefine(id = "$currRoGridPanelForm.autoExpandMin", 
			 name = "$currRoGridPanelForm.autoExpandMin", 
			 fieldLabel = "自动扩充列的最小宽度",
			 xtype = ElementFieldType.NumberField,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 80
	)
	public java.lang.Integer getAutoExpandMin(){
		return gridPanelBean.getAutoExpandMin();
	}
	
	public void setAutoExpandMin(java.lang.Integer autoExpandMin){
		gridPanelBean.setAutoExpandMin(autoExpandMin);
	}
	
	@FormFieldDefine(id = "$currRoGridPanelForm.disableSelection", 
			 name = "$currRoGridPanelForm.disableSelection", 
			 fieldLabel = "是否禁止表格选择",
			 xtype = ElementFieldType.ComboBox,
			 model = "local",
			 item=ElementItem.isyes,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 64
	)
	public java.lang.String getDisableSelection(){
	return null;
	}
	
	public void setDisableSelection(java.lang.String disableSelection){
		
	}
	@FormFieldDefine(id = "$currRoGridPanelForm.enableColumnMove", 
			 name = "$currRoGridPanelForm.enableColumnMove", 
			 fieldLabel = "是否允许拖放表格列",
			 xtype = ElementFieldType.ComboBox,
			 model = "local",
			 item=ElementItem.isyes,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 64
	)
	public java.lang.String getEnableColumnMove(){
	return null;
	}
	
	public void setEnableColumnMove(java.lang.String enableColumnMove){
		
	}
	
	@FormFieldDefine(id = "$currRoGridPanelForm.enableColumnResize", 
			 name = "$currRoGridPanelForm.enableColumnResize", 
			 fieldLabel = "是否允许改变表格列宽",
			 xtype = ElementFieldType.ComboBox,
			 model = "local",
			 item=ElementItem.isyes,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 64
	)
	public java.lang.String getEnableColumnResize(){
	return null;
	}
	
	public void setEnableColumnResize(java.lang.String enableColumnResize){
		
	}
	
	
	@FormFieldDefine(id = "$currRoGridPanelForm.enalbeDragDrop", 
			 name = "$currRoGridPanelForm.enalbeDragDrop", 
			 fieldLabel = "是否允许拖放表格中的行",
			 xtype = ElementFieldType.ComboBox,
			 model = "local",
			 item=ElementItem.isyes,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 64
	)
	public java.lang.String getEnalbeDragDrop(){
	return null;
	}
	
	public void setEnalbeDragDrop(java.lang.String enalbeDragDrop){
		
	}
	
	
	@FormFieldDefine(id = "$currRoGridPanelForm.pageSize", 
			 name = "$currRoGridPanelForm.pageSize", 
			 fieldLabel = "每页显示条数",
			 xtype = ElementFieldType.NumberField,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 64
	)
	public java.lang.Integer getPageSize(){
	return gridPanelBean.getPageSize();
	}
	
	public void setPageSize(java.lang.Integer pageSize){
		gridPanelBean.setPageSize(pageSize);
	}
	
	
}
