package net.itjds.esb.consol.busmaager.viewmanage.esb;

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
			"jspsrcpath",//
			"javabeansrcpath",//
			"javabeanclasspath",//
			"reload",//
			"compile"//
				
		}
	
)
public class EsbTypeFormPanel{
	private String jspsrcpath;

	private String javabeansrcpath;
	private String javabeanclasspath;
	private String reload;
	private String compile;
	
	private FormPanelBean formPanelBean;
	public EsbTypeFormPanel(PanelBean bean){
		if(bean instanceof PanelBean)
		{
			this.formPanelBean=(FormPanelBean) bean;
		}
 	      
 	}
 	public EsbTypeFormPanel(){

 	}
 
 
	
	
    @FormFieldDefine(id = "$currRoFormPanelForm.title",                                        
					 name = "$currRoFormPanelForm.title", 
					 fieldLabel = "´°¿Ú±êÌâ",
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
	
	
	public String getCompile() {
		return compile;
	}
	public void setCompile(String compile) {
		this.compile = compile;
	}
	public String getJavabeanclasspath() {
		return javabeanclasspath;
	}
	public void setJavabeanclasspath(String javabeanclasspath) {
		this.javabeanclasspath = javabeanclasspath;
	}
	public String getJavabeansrcpath() {
		return javabeansrcpath;
	}
	public void setJavabeansrcpath(String javabeansrcpath) {
		this.javabeansrcpath = javabeansrcpath;
	}
	public String getJspsrcpath() {
		return jspsrcpath;
	}
	public void setJspsrcpath(String jspsrcpath) {
		this.jspsrcpath = jspsrcpath;
	}

	public String getReload() {
		return reload;
	}
	public void setReload(String reload) {
		this.reload = reload;
	}
	
	
	
}
