package net.itjds.esb.consol.busmaager.viewmanage.tree;

import net.itjds.usm2.define.data.annotation.AjaxDataDefine;
import net.itjds.usm2.define.enums.ElementAlign;
import net.itjds.usm2.define.enums.ElementButton;
import net.itjds.usm2.define.enums.ElementFieldType;
import net.itjds.usm2.define.enums.ElementVtype;
import net.itjds.usm2.define.form.annotation.FormFieldDefine;
import net.itjds.usm2.define.form.annotation.FormPanelDefine;
import net.itjds.usm2.define.mapping.PanelBean;
import net.itjds.usm2.define.simplepanel.mapping.SimplePanelBean;
import net.itjds.usm2.define.tree.mapping.TreePanelBean;

@FormPanelDefine(
		labelWidth = 0,
		labelAlign = ElementAlign.left,
		//buttons = {ElementButton.save,ElementButton.abort},
		 loadData= @AjaxDataDefine(
					url="/expression.jsp?expression=$JSONForm($ViewManage.cartPanel.treePanelBean.EsbTempPanelForm)"
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
public class RoTreePanelForm{
 	
	private PanelBean bean;

	private TreePanelBean treePanelBean;
	public RoTreePanelForm(PanelBean bean){
		if(bean instanceof TreePanelBean)
		{
			this.treePanelBean=(TreePanelBean) bean;
		}
 	    
 	}
 	public RoTreePanelForm(){

 	}
 
 
	
	    @FormFieldDefine(id = "$currRoTreePanelForm.title", 
				 name = "$currRoTreePanelForm.title", 
				 fieldLabel = "窗体名称",
				 xtype = ElementFieldType.TextField,
				 vtype = ElementVtype.none, 
				 validateOnBlur = true, 
				 allowBlank = false,
				 maxLength = 64
	)
	public java.lang.String getTitle(){
	    	
	return treePanelBean.getTitle();

	}
	
	public void setTitle(java.lang.String title){
		treePanelBean.setTitle(title);
	}
    @FormFieldDefine(id = "$currRoTreePanelForm.width", 
			 name = "$currRoTreePanelForm.width", 
			 fieldLabel = "窗体宽度",
			 xtype = ElementFieldType.NumberField,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = false,
			 maxLength = 64
	)
	public java.lang.Integer getWidth(){
	   	
	return treePanelBean.getWidth();

	}
	
	public void setWidth(java.lang.Integer width){
		treePanelBean.setWidth(width);
	}

	  @FormFieldDefine(id = "$currRoTreePanelForm.height", 
				 name = "$currRoTreePanelForm.height", 
				 fieldLabel = "窗体高度",
				 xtype = ElementFieldType.NumberField,
				 vtype = ElementVtype.none, 
				 validateOnBlur = true, 
				 allowBlank = false,
				 maxLength = 64
		)
		public java.lang.Integer getHeight(){
		   	
		return treePanelBean.getHeight();
		}
		
		public void setHeight(java.lang.Integer height){
			treePanelBean.setHeight(height);
		}
	
		@FormFieldDefine(id = "$currRoTreePanelForm.bodyStyle", 
				 name = "$currRoTreePanelForm.bodyStyle", 
				 fieldLabel = "背景图片",
				 xtype = ElementFieldType.TextField,
				 vtype = ElementVtype.none, 
				 validateOnBlur = true, 
				 allowBlank = false,
				 maxLength = 64
		)
		public java.lang.String getBodyStyle(){
		   	
		return treePanelBean.getBodyStyle();
		}
		
		public void setBodyStyle(java.lang.String bodyStyle){
			treePanelBean.setBodyStyle(bodyStyle);
		}
		
	
}
