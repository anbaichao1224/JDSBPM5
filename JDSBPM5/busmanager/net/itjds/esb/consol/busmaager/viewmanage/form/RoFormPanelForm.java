package net.itjds.esb.consol.busmaager.viewmanage.form;

import net.itjds.usm2.define.data.annotation.AjaxDataDefine;
import net.itjds.usm2.define.enums.ElementAlign;
import net.itjds.usm2.define.enums.ElementButton;
import net.itjds.usm2.define.enums.ElementFieldType;
import net.itjds.usm2.define.enums.ElementItem;
import net.itjds.usm2.define.enums.ElementVtype;
import net.itjds.usm2.define.form.annotation.FormFieldDefine;
import net.itjds.usm2.define.form.annotation.FormPanelDefine;
import net.itjds.usm2.define.form.mapping.FormPanelBean;
import net.itjds.usm2.define.mapping.PanelBean;


@FormPanelDefine(
		labelWidth = 0,
		labelAlign = ElementAlign.left,
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
			"labelWidth",//��ǩ���
			"labelAlign",//��ǩ���뷽ʽ
			"buttonAlign"//��ť���뷽ʽ
				
		}
	
)
public class RoFormPanelForm{
	private FormPanelBean formPanelBean;
	public RoFormPanelForm(PanelBean bean){
		if(bean instanceof PanelBean)
		{
			this.formPanelBean=(FormPanelBean) bean;
		}
 	      
 	}
 	public RoFormPanelForm(){

 	}
 
 
	
	
    @FormFieldDefine(id = "$currRoFormPanelForm.title",                                        
					 name = "$currRoFormPanelForm.title", 
					 fieldLabel = "���ڱ���",
					 xtype = ElementFieldType.TextField,
					 model = "local",
					 vtype = ElementVtype.none, 
					 validateOnBlur = true, 
					 allowBlank = false,
					 maxLength = 64
	)
	public java.lang.String getTitle(){
		return formPanelBean.getTitle();
	}
	
	public void setTitle(java.lang.String title){
		formPanelBean.setTitle(title);
	}
	
	
	
    @FormFieldDefine(id = "$currRoFormPanelForm.width", 
					 name = "$currRoFormPanelForm.width", 
					 fieldLabel = "���ڿ��",
					 xtype = ElementFieldType.NumberField,
					 model = "local",
					 vtype = ElementVtype.none, 
					 validateOnBlur = true, 
					 allowBlank = true,
					 maxLength = 64
	)
	public java.lang.Integer getWidth(){
		return formPanelBean.getWidth();
	}
	
	public void setWidth(java.lang.Integer width){
		formPanelBean.setWidth(width);
	}
	
	
    @FormFieldDefine(id = "$currRoFormPanelForm.height", 
					 name = "$currRoFormPanelForm.height", 
					 fieldLabel = "���ڸ߶�",
					 xtype = ElementFieldType.NumberField,
					 model = "local",
					 vtype = ElementVtype.none, 
					 validateOnBlur = true, 
					 allowBlank = true,
					 maxLength = 800
	)
	public java.lang.Integer getHeight(){
		return formPanelBean.getHeight();
	}
	
	public void setHeight(java.lang.Integer height){
		formPanelBean.setHeight(height);
	}
	
	  @FormFieldDefine(id = "$currRoFormPanelForm.labelWidth", 
				 name = "$currRoFormPanelForm.labelWidth", 
				 fieldLabel = "��ǩ���",
				 xtype = ElementFieldType.NumberField,
				 model = "local",
				 vtype = ElementVtype.none, 
				 validateOnBlur = true, 
				 allowBlank = true,
				 maxLength = 60
	)
	public java.lang.Double getLabelWidth(){
		  
		return formPanelBean.getLabelWidth();
	}
	
	public void setLabelWidth(java.lang.Double labelWidth){
		formPanelBean.setLabelWidth(labelWidth);
	}
//	
	@FormFieldDefine(id = "$currRoFormPanelForm.labelAlign", 
			 name = "$currRoFormPanelForm.labelAlign", 
			 fieldLabel = "��ǩ���뷽ʽ",
			 xtype = ElementFieldType.ComboBox,
			 model = "local",
			 item=ElementItem.align,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 80
	)
	public java.lang.String getLabelAlign(){
		return null;
	}
	
	public void setLabelAlign(java.lang.String labelAlign){
		//formPanelBean.setLabelAlign(labelAlign);
	}
	
	@FormFieldDefine(id = "$currRoFormPanelForm.buttonAlign", 
			 name = "$currRoFormPanelForm.buttonAlign", 
			 fieldLabel = "��ť���뷽ʽ",
			 xtype = ElementFieldType.ComboBox,
			 model = "local",
			 item=ElementItem.align,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = true,
			 maxLength = 800
	)
	public java.lang.String getButtonAlign(){
	return null;
	}
	
	public void setButtonAlign(java.lang.String buttonAlign){
	//formPanelBean.setButtonAlign(buttonAlign);
	}
	
	
	
}
