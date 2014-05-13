package net.itjds.esb.consol.busmaager.viewmanage.tab;

import net.itjds.usm2.define.data.annotation.AjaxDataDefine;
import net.itjds.usm2.define.enums.ElementAlign;
import net.itjds.usm2.define.enums.ElementButton;
import net.itjds.usm2.define.enums.ElementFieldType;
import net.itjds.usm2.define.enums.ElementVtype;
import net.itjds.usm2.define.form.annotation.FormFieldDefine;
import net.itjds.usm2.define.form.annotation.FormPanelDefine;
import net.itjds.usm2.define.mapping.PanelBean;
import net.itjds.usm2.define.tabpanel.mapping.TabPanelBean;

@FormPanelDefine(
		labelWidth = 0,
		labelAlign = ElementAlign.left,
		buttons = {ElementButton.save,ElementButton.abort},
		 loadData= @AjaxDataDefine(
					url="/expression.jsp?expression=$JSONForm($ViewManage.cartPanel.tabPanelBean.EsbTempPanelForm)"
			),
		
			updateData= @AjaxDataDefine(
					url="update.action?expression=$UpdateCurrForm"
			),
		fieldsIndex={
				"title",//标题
				"width",//宽度
				"height"//高度
				
				
		}
	
)
public class RoTabPanelForm{
 	
	private PanelBean bean;

	private TabPanelBean tabPanelBean;
	public RoTabPanelForm(PanelBean bean){
		if(bean instanceof PanelBean)
		{
			this.tabPanelBean=(TabPanelBean) bean;
		}
 	    
 	}
 	public RoTabPanelForm(){

 	}
 
 
	
	    @FormFieldDefine(id = "$currRoTabPanelForm.title", 
				 name = "$currRoTabPanelForm.title", 
				 fieldLabel = "窗体名称",
				 xtype = ElementFieldType.TextField,
				 vtype = ElementVtype.none, 
				 validateOnBlur = true, 
				 allowBlank = false,
				 maxLength = 64
	)
	public java.lang.String getTitle(){
	    	
	return tabPanelBean.getTitle();
	}
	
	public void setTitle(java.lang.String title){
		tabPanelBean.setTitle(title);
	}
    @FormFieldDefine(id = "$currRoTabPanelForm.width", 
			 name = "$currRoTabPanelForm.width", 
			 fieldLabel = "窗体宽度",
			 xtype = ElementFieldType.NumberField,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = false,
			 maxLength = 64
	)
	public java.lang.Integer getWidth(){
	   	
	return tabPanelBean.getWidth();
	}
	
	public void setWidth(java.lang.Integer width){
		tabPanelBean.setWidth(width);
	}

	  @FormFieldDefine(id = "$currRoTabPanelForm.height", 
				 name = "$currRoTabPanelForm.height", 
				 fieldLabel = "窗体高度",
				 xtype = ElementFieldType.NumberField,
				 vtype = ElementVtype.none, 
				 validateOnBlur = true, 
				 allowBlank = false,
				 maxLength = 64
		)
		public java.lang.Integer getHeight(){
		   	
		return tabPanelBean.getHeight();
		}
		
		public void setHeight(java.lang.Integer height){
			tabPanelBean.setHeight(height);
		}
	
	
	
}
