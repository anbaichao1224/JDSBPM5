package net.itjds.esb.consol.busmaager.viewmanage.esb;

import net.itjds.bpm.data.xmlproxy.manager.EsbBean;
import net.itjds.usm2.define.data.annotation.AjaxDataDefine;
import net.itjds.usm2.define.enums.ElementAlign;
import net.itjds.usm2.define.enums.ElementFieldType;
import net.itjds.usm2.define.enums.ElementVtype;
import net.itjds.usm2.define.form.annotation.FormFieldDefine;
import net.itjds.usm2.define.form.annotation.FormPanelDefine;


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
			"id",//
			"cnname",//
			"esbtype",//
			"path",//
			"formClassManager",
			"expressionTemManager",//
			"desc"//
				
		}
	
)
public class EsbBeanFormPanel{
	
	private String id;
	private String cnname;
	private String type;
	private String esbtype;
	private String desc;
	private String path;	
	private String expressionTemManager;
    private String formClassManager;
	private EsbBean esbBean;
	
 	public EsbBeanFormPanel(EsbBean esbBean){
 		this.esbBean=esbBean;

 	}
 	 @FormFieldDefine(id = "$currRoFormPanelForm.id",                                        
			 name = "$currRoFormPanelForm.id", 
			 fieldLabel = "注册ID",
			 xtype = ElementFieldType.TextField,
			 model = "local",
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = false,
			 maxLength = 64
)
 	public String getId() {
		return esbBean.getId();
	}
	public void setId(String id) {
		this.id = id;
	}
  @FormFieldDefine(id = "$currRoFormPanelForm.cnname",                                        
				 name = "$currRoFormPanelForm.cnname", 
				 fieldLabel = "注册名称",
				 xtype = ElementFieldType.TextField,
				 model = "local",
				 vtype = ElementVtype.none, 
				 validateOnBlur = true, 
				 allowBlank = false,
				 maxLength = 64
    )
	public String getCnname() {
		return esbBean.getCnname();
	}
	public void setCnname(String cnname) {
		this.cnname = cnname;
	}
	
	@FormFieldDefine(id = "$currRoFormPanelForm.id",                                        
			 name = "$currRoFormPanelForm.id", 
			 fieldLabel = "总线类型",
			 xtype = ElementFieldType.TextField,
			 model = "local",
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = false,
			 maxLength = 64
   )
	public String getEsbtype() {
		return esbBean.getEsbtype();
	}
	public void setEsbtype(String esbtype) {
		this.esbtype = esbtype;
	}
	@FormFieldDefine(id = "$currRoFormPanelForm.path",                                        
			 name = "$currRoFormPanelForm.path", 
			 fieldLabel = "资源路径",
			 xtype = ElementFieldType.TextArea,
			 model = "local",
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = false,
			 maxLength = 64
  )
	public String getPath() {
		return esbBean.getPath();
	}
	public void setPath(String path) {
		this.path = path;
	}
	@FormFieldDefine(id = "$currRoFormPanelForm.path",                                        
			 name = "$currRoFormPanelForm.path", 
			 fieldLabel = "资源类型",
			 xtype = ElementFieldType.TextField,
			 model = "local",
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
		
			 maxLength = 64
  )
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	@FormFieldDefine(id = "$currRoFormPanelForm.expressionTemManager",                                        
			 name = "$currRoFormPanelForm.expressionTemManager", 
			 fieldLabel = "装载管理器",
			 xtype = ElementFieldType.TextField,
			 model = "local",
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
		
			 maxLength = 64
   )
	public String getExpressionTemManager() {
		return esbBean.getExpressionTemManager();
	}
	public void setExpressionTemManager(String expressionTemManager) {
		this.expressionTemManager = expressionTemManager;
	}
	@FormFieldDefine(id = "$currRoFormPanelForm.formClassManager",                                        
			 name = "$currRoFormPanelForm.formClassManager", 
			 fieldLabel = "表单转换适配器",
			 xtype = ElementFieldType.TextField,
			 model = "local",
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
		
			 maxLength = 64
  )
	public String getFormClassManager() {
		return esbBean.getFormClassManager();
	}
	public void setFormClassManager(String formClassManager) {
		this.formClassManager = formClassManager;
	}
	
	
	@FormFieldDefine(id = "$currRoFormPanelForm.desc",                                        
			 name = "$currRoFormPanelForm.desc", 
			 fieldLabel = "资源描述",
			 xtype = ElementFieldType.TextField,
			 model = "local",
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			
			 maxLength = 64
  )
	public String getDesc() {
		return esbBean.getDesc();
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
