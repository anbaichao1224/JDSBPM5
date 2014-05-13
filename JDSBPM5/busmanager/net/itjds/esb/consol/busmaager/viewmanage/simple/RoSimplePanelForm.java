package net.itjds.esb.consol.busmaager.viewmanage.simple;

import net.itjds.usm2.define.data.annotation.AjaxDataDefine;
import net.itjds.usm2.define.enums.ElementAlign;
import net.itjds.usm2.define.enums.ElementButton;
import net.itjds.usm2.define.enums.ElementFieldType;
import net.itjds.usm2.define.enums.ElementVtype;
import net.itjds.usm2.define.form.annotation.FormFieldDefine;
import net.itjds.usm2.define.form.annotation.FormPanelDefine;
import net.itjds.usm2.define.mapping.PanelBean;
import net.itjds.usm2.define.simplepanel.mapping.SimplePanelBean;

@FormPanelDefine(
		labelWidth = 0,
		labelAlign = ElementAlign.left,
		buttons = {ElementButton.save,ElementButton.abort},
		 loadData= @AjaxDataDefine(
					url="/expression.jsp?expression=$JSONForm($ViewManage.cartPanel.panelTabPanel.EsbTempPanelForm)"
			),
		
			updateData= @AjaxDataDefine(
					url="update.action?expression=$UpdateCurrForm"
			),
		fieldsIndex={
				"title",//标题
				"width",//宽度
				"height",//高度
				"bodyStyle"//背景图片
				
		}
	
)
public class RoSimplePanelForm{
 	
	private PanelBean bean;

	private SimplePanelBean simplePanelBean;
	public RoSimplePanelForm(PanelBean bean){
		if(simplePanelBean instanceof PanelBean)
		{
			this.simplePanelBean=(SimplePanelBean) bean;
		}
 	    
 	}
 	public RoSimplePanelForm(){

 	}
 
 
	
	    @FormFieldDefine(id = "$currRoSimplePanelForm.title", 
				 name = "$currRoSimplePanelForm.title", 
				 fieldLabel = "窗体名称",
				 xtype = ElementFieldType.TextField,
				 vtype = ElementVtype.none, 
				 validateOnBlur = true, 
				 allowBlank = false,
				 maxLength = 64
	)
	public java.lang.String getTitle(){
	    	
	return simplePanelBean.getTitle();
	}
	
	public void setTitle(java.lang.String title){
		simplePanelBean.setTitle(title);
	}
    @FormFieldDefine(id = "$currRoSimplePanelForm.width", 
			 name = "$currRoSimplePanelForm.width", 
			 fieldLabel = "窗体宽度",
			 xtype = ElementFieldType.NumberField,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = false,
			 maxLength = 64
	)
	public java.lang.Integer getWidth(){
	   	
	return simplePanelBean.getWidth();
	}
	
	public void setWidth(java.lang.Integer width){
		simplePanelBean.setWidth(width);
	}

	  @FormFieldDefine(id = "$currRoSimplePanelForm.height", 
				 name = "$currRoSimplePanelForm.height", 
				 fieldLabel = "窗体高度",
				 xtype = ElementFieldType.NumberField,
				 vtype = ElementVtype.none, 
				 validateOnBlur = true, 
				 allowBlank = false,
				 maxLength = 64
		)
		public java.lang.Integer getHeight(){
		   	
		return simplePanelBean.getHeight();
		}
		
		public void setHeight(java.lang.Integer height){
			simplePanelBean.setHeight(height);
		}
	
		@FormFieldDefine(id = "$currRoSimplePanelForm.bodyStyle", 
				 name = "$currRoSimplePanelForm.bodyStyle", 
				 fieldLabel = "背景图片",
				 xtype = ElementFieldType.NumberField,
				 vtype = ElementVtype.none, 
				 validateOnBlur = true, 
				 allowBlank = false,
				 maxLength = 64
		)
		public java.lang.String getBodyStyle(){
		   	
		return simplePanelBean.getBodyStyle();
		}
		
		public void setBodyStyle(java.lang.String bodyStyle){
			simplePanelBean.setBodyStyle(bodyStyle);
		}
	
	
}
