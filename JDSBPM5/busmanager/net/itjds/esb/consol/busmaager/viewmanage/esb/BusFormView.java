package net.itjds.esb.consol.busmaager.viewmanage.esb;

import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.manager.BaseTableTempBean;
import net.itjds.bpm.data.xmlproxy.manager.ExpressionTempBean;
import net.itjds.bpm.data.xmlproxy.manager.MapDAOTempBean;
import net.itjds.bpm.data.xmlproxy.manager.USMTempBean;
import net.itjds.usm2.define.data.annotation.AjaxDataDefine;
import net.itjds.usm2.define.enums.ElementAlign;
import net.itjds.usm2.define.enums.ElementButton;
import net.itjds.usm2.define.enums.ElementFieldType;
import net.itjds.usm2.define.enums.ElementVtype;
import net.itjds.usm2.define.form.annotation.FormFieldDefine;
import net.itjds.usm2.define.form.annotation.FormPanelDefine;


@EsbBeanAnnotation(id="currBusFormView",
		name="�༭Bus��ͼ",
		expressionArr="BusFormView($currUSMTempBean())",
		desc="�༭Bus��ͼ",
		dataType="action"
)
@FormPanelDefine(
		labelWidth = 0,
		labelAlign = ElementAlign.left,
		buttons = {ElementButton.save,ElementButton.abort},
		 loadData= @AjaxDataDefine(
				url="/expression.jsp?expression=$JSONForm($currBusFormView)"
		),
	
		updateData= @AjaxDataDefine(
				url="update.action?expression=$UpdateCurrForm"
		),
		fieldsIndex={
			"title",
			
			"mainTableName",
			"esbkey",
			"schema",
			"expressionArr",
			"dataType",
			"desc"
		}
	
)

public class BusFormView{
 	
 	private ExpressionTempBean bean;
 	
 	public String mainTableName;
 	public String packageName;
 	public String esbkey;
 	public String schema;
 	public String expressionArr;
	public String dataType;
	public String desc;


	
 	public BusFormView(ExpressionTempBean bean){
	      this.bean=bean;
	}
    @FormFieldDefine(id = "$currRoFormPanel.title", 
					 name = "$currRoFormPanel.title", 
					 fieldLabel = "��������",
					 xtype = ElementFieldType.TextField,
					 model = "local",
					 vtype = ElementVtype.none, 
					 validateOnBlur = true, 
					 allowBlank = false,
					 maxLength = 64
	)
	public java.lang.String getTitle(){
		return bean.getName();
	}
	
	public void setTitle(java.lang.String title){
		bean.setName(title);
	}
	@FormFieldDefine(id = "$currRoFormPanel.mainTableName", 
				 name = "$currRoFormPanel.mainTableName", 
				 fieldLabel = "��Ӧ����",
				 xtype = ElementFieldType.TextField,
				 model = "local",
				 vtype = ElementVtype.none, 
				
				
				 maxLength = 64
   )
	public String getMainTableName() {
		if (bean != null) {
			if   (bean instanceof USMTempBean) {
				mainTableName=((USMTempBean)bean).getMaintablename();
			}else if (bean instanceof MapDAOTempBean){
				mainTableName=((MapDAOTempBean)bean).getTableNames().get(0);
			}
		}
		return mainTableName;
	}
	public void setMainTableName(String mainTableName) {
		this.mainTableName = mainTableName;
	}
	@FormFieldDefine(id = "$currRoFormPanel.esbkey", 
			 name = "$currRoFormPanel.esbkey", 
			 fieldLabel = "����ע���ʶ",
			 xtype = ElementFieldType.TextField,
			 model = "local",
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = false,
			 maxLength = 64
   )
	public String getEsbkey() {
		return bean.getId();
	}
	public void setEsbkey(String esbkey) {
		this.esbkey = esbkey;
	}
	@FormFieldDefine(id = "$currRoFormPanel.expressionArr", 
			 name = "$currRoFormPanel.expressionArr", 
			 fieldLabel = "���ط���",
			 xtype = ElementFieldType.TextArea,
			 model = "local",
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = false,
			 maxLength = 64
   )
	public String getExpressionArr() {
		return bean.getExpressionArr();
	}
	public void setExpressionArr(String expressionArr) {
		this.expressionArr = expressionArr;
	}
	@FormFieldDefine(id = "$currRoFormPanel.dataType", 
			 name = "$currRoFormPanel.dataType", 
			 fieldLabel = "��������",
			 xtype = ElementFieldType.TextField,
			 model = "local",
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = false,
			 maxLength = 64
    )
 	public String getDataType() {
		return bean.getDataType();
	}
   public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	@FormFieldDefine(id = "$currRoFormPanel.packageName", 
			 name = "$currRoFormPanel.packageName", 
			 fieldLabel = "��·��",
			 xtype = ElementFieldType.TextField,
			 model = "local",
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = false,
			 maxLength = 64
 )
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	
	@FormFieldDefine(id = "$currRoFormPanel.expressionArr", 
			 name = "$currRoFormPanel.expressionArr", 
			 fieldLabel = "���ݿ��ʶ",
			 xtype = ElementFieldType.TextField,
			 model = "local",
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
		
			 maxLength = 64
     )
	public String getSchema() {
	
		 if (bean instanceof BaseTableTempBean) {
			 BaseTableTempBean tableBean = (BaseTableTempBean) bean;
			 schema=tableBean.getSchema();
		}
		return schema;
	}
	public void setSchema(String schema) {
		this.schema = schema;
	}
	@FormFieldDefine(id = "$currRoFormPanel.desc", 
			 name = "$currRoFormPanel.desc", 
			 fieldLabel = "����",
			 xtype = ElementFieldType.TextField,
			 model = "local",
			 vtype = ElementVtype.none, 
			 validateOnBlur = true, 
			 allowBlank = false,
			 maxLength = 64
   )
	public String getDesc() {
		return bean.getDesc();
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

}
