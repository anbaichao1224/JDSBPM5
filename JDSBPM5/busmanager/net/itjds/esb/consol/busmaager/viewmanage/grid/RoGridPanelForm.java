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
			"title",//���ڱ���
			"width",//���ڿ��
			"height",//���ڸ߶�
			"maxHeight",//�������߶�
			"minColumnWidth",//����е���С���
			"autoExpandMax",//�Զ������е������
			"autoExpandMin",//�Զ������е���С���
			"disableSelection",//�Ƿ��ֹ���ѡ��
			"enableColumnMove",//�Ƿ������Ϸű����
			"enableColumnResize",//�Ƿ�����ı����п�
			"enalbeDragDrop",//�Ƿ������Ϸű���е���
			"pageSize"//ÿҳ��ʾ����
				
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
					 fieldLabel = "���ڱ���",
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
					 fieldLabel = "���ڿ��",
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
					 fieldLabel = "���ڸ߶�",
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
				 fieldLabel = "������߶�",
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
			 fieldLabel = "����е���С���",
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
			 fieldLabel = "�Զ������е������",
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
			 fieldLabel = "�Զ������е���С���",
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
			 fieldLabel = "�Ƿ��ֹ���ѡ��",
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
			 fieldLabel = "�Ƿ������Ϸű����",
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
			 fieldLabel = "�Ƿ�����ı����п�",
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
			 fieldLabel = "�Ƿ������Ϸű���е���",
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
			 fieldLabel = "ÿҳ��ʾ����",
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
