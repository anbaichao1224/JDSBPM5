package net.itjds.esb.consol.busmaager.viewmanage.cart;

import net.itjds.usm2.define.cartlayoutpanel.mapping.CartPanelBean;
import net.itjds.usm2.define.data.annotation.AjaxDataDefine;
import net.itjds.usm2.define.enums.ElementAlign;
import net.itjds.usm2.define.enums.ElementButton;
import net.itjds.usm2.define.enums.ElementFieldType;
import net.itjds.usm2.define.enums.ElementVtype;
import net.itjds.usm2.define.form.annotation.FormFieldDefine;
import net.itjds.usm2.define.form.annotation.FormPanelDefine;
import net.itjds.usm2.define.mapping.PanelBean;

@FormPanelDefine(
		labelWidth = 0,
		labelAlign = ElementAlign.left,
		buttons = {ElementButton.save,ElementButton.abort},
		 loadData= @AjaxDataDefine(
					url="/expression.jsp?expression=$JSONForm($ViewManage.cartPanel.cartPanelBean.EsbTempPanelForm)"
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
public class CartPanelForm{
 	
	private PanelBean bean;

	private CartPanelBean cartPanelBean;
	public CartPanelForm(PanelBean bean){
		if(bean instanceof PanelBean)
		{
			this.cartPanelBean=(CartPanelBean) bean;
		}
 	    
 	}
 	public CartPanelForm(){

 	}
 
 
	
	    @FormFieldDefine(id = "$currRoCartPanelForm.title", 
				 name = "$currRoCartPanelForm.title", 
				 fieldLabel = "窗体名称",
				 xtype = ElementFieldType.TextField,
				 vtype = ElementVtype.none, 
				 validateOnBlur = true, 
				 allowBlank = false,
				 maxLength = 64
	)
	public java.lang.String getTitle(){
	    	
	return cartPanelBean.getTitle();
	}
	
	public void setTitle(java.lang.String title){
		cartPanelBean.setTitle(title);
	}
    @FormFieldDefine(id = "$currRoCartPanelForm.width", 
			 name = "$currRoCartPanelForm.width", 
			 fieldLabel = "窗体宽度",
			 xtype = ElementFieldType.NumberField,
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = false,
			 maxLength = 64
	)
	public java.lang.Integer getWidth(){
	   	
	return cartPanelBean.getWidth();
	}
	
	public void setWidth(java.lang.Integer width){
		cartPanelBean.setWidth(width);
	}

	  @FormFieldDefine(id = "$currRoCartPanelForm.height", 
				 name = "$currRoCartPanelForm.height", 
				 fieldLabel = "窗体高度",
				 xtype = ElementFieldType.NumberField,
				 vtype = ElementVtype.none, 
				 validateOnBlur = true, 
				 allowBlank = false,
				 maxLength = 64
		)
		public java.lang.Integer getHeight(){
		   	
		return cartPanelBean.getHeight();
		}
		
		public void setHeight(java.lang.Integer height){
			cartPanelBean.setHeight(height);
		}
	
	
	
}
