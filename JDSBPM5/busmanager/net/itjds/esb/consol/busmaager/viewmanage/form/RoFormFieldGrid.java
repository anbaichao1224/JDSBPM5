package net.itjds.esb.consol.busmaager.viewmanage.form;

import net.itjds.usm2.define.annotation.ButtonDefine;
import net.itjds.usm2.define.data.annotation.AjaxDataDefine;
import net.itjds.usm2.define.data.annotation.WinConfigDefine;
import net.itjds.usm2.define.enums.ElementButton;
import net.itjds.usm2.define.enums.ElementFieldType;
import net.itjds.usm2.define.form.mapping.FormFieldsBean;
import net.itjds.usm2.define.grid.annotation.ColumnDefine;
import net.itjds.usm2.define.grid.annotation.GridPanelDefine;
import net.itjds.usm2.define.grid.annotation.SearchItemDefine;
import net.itjds.usm2.define.mapping.FieldBean;



@GridPanelDefine(
		title = "视图信息",
		
			
		editRowData=@AjaxDataDefine(
				expression="$currFormPanel",
				viewId="currRoFormFieldView",
				winConfig=@WinConfigDefine(width=500,height=600,title="修改字段信息")
		),
		
		buttons = { @ButtonDefine(text=ElementButton.query,iconCls="zoom",hidden = false,handler = "query()"),
					@ButtonDefine(text=ElementButton.rebuild,iconCls="mobility",hidden = false,handler = "rebuild()"),
					@ButtonDefine(text=ElementButton.serialindex,iconCls="serialindex",hidden = false,handler = "serialindex()"),
					@ButtonDefine(text=ElementButton.createfield,iconCls="add",hidden = false,
							 ajax= @AjaxDataDefine(
			        					expression="$currFormPanel",
			        					viewId="currRoFormFieldView",
			        					winConfig=@WinConfigDefine(width=500,height=600,title="新增字段")
			        			)
							),
							 @ButtonDefine(text=ElementButton.delete,iconCls="remove",hidden = false,handler = "remove()")
				         	   
        },
        rebuild = 	 @AjaxDataDefine(
				url="/update.action?expression=$UsmData.rebuild()"
		),
			fieldsIndex={
			"uuid",//UUID
			"name",//字段英文名
			"cnname",//字段中文名
			"fieldLabel",//控件标签
			"width",//控件宽度
			"xtype",//控件类型
			"comboxkey",//下拉框键值
			"vtype",//验证类型
			"readOnly",//是否只读
			"allowBlank",//是否为空
			"maxLength",//最大长度
			"maxValue",//最大值
			"viewname",//文件名称
			"viewpath"//文件路径
			
		}		
)
public class RoFormFieldGrid {

 	private FormFieldsBean formFieldsBean;
 	public RoFormFieldGrid(FieldBean bean){
 	      if(bean instanceof FieldBean)
 	      {
 	    	  this.formFieldsBean=(FormFieldsBean)bean;
 	      }
 	}
 
 	 	@ColumnDefine(
				mapping = "uuid",
				dataIndex = "uuid",
				header = "UUID", 
				type = ElementFieldType.TextField,
				hidden = true,		
				search = @SearchItemDefine(fieldLabel="UUID",xtype=ElementFieldType.Hidden,hidden=true)
	)	
	public java.lang.String getUuid(){
		return formFieldsBean.getId();
	}



	@ColumnDefine(
			mapping = "name",
			dataIndex = "name",
			header = "字段英文名", 
			type = ElementFieldType.TextField,			
			search = @SearchItemDefine(fieldLabel="岗位",xtype=ElementFieldType.Hidden,hidden=true)
	)
	public java.lang.String getName(){
		return formFieldsBean.getName();
	}
	@ColumnDefine(
			mapping = "cnname",
			dataIndex = "cnname",
			header = "字段中文名", 
			type = ElementFieldType.TextField
			)
	public java.lang.String getCnname(){
		return formFieldsBean.getFieldLabel();
	}
	@ColumnDefine(
			mapping = "fieldLabel",
			dataIndex = "fieldLabel",
			header = "控件标签",		
			type = ElementFieldType.Hidden,	
			hidden=true,
			search = @SearchItemDefine(fieldLabel="描述",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.String getFieldLabel(){
		return formFieldsBean.getFieldLabel();
	}
	@ColumnDefine(
			mapping = "width",
			dataIndex = "width",
			header = "显示宽度",
			type = ElementFieldType.TextField,			
			search = @SearchItemDefine(fieldLabel="描述",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.Integer getWidth(){
		return formFieldsBean.getWidth();
	}
	@ColumnDefine(
			mapping = "xtype",
			dataIndex = "xtype",
			header = "显示类型",
			type = ElementFieldType.TextField,			
			search = @SearchItemDefine(fieldLabel="描述",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.String getXtype(){
		return formFieldsBean.getXtype().getType();
	}
	 	@ColumnDefine(
				mapping = "comboxkey",
				dataIndex = "comboxkey",
				header = "下拉框键值", 
				type = ElementFieldType.TextField,
				search = @SearchItemDefine(fieldLabel="UUID",xtype=ElementFieldType.Hidden,hidden=true)
	)	
	public java.lang.String getComboxkey(){
		return null;
	}
	
	@ColumnDefine(
			mapping = "vtype",
			dataIndex = "vtype",
			header = "验证类型",
			type = ElementFieldType.TextField,			
			search = @SearchItemDefine(fieldLabel="描述",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.String getVtype(){
		return null;
	}
	@ColumnDefine(
			mapping = "readOnly",
			dataIndex = "readOnly",
			header = "是否只读",
			type = ElementFieldType.TextField,			
			search = @SearchItemDefine(fieldLabel="描述",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.String getReadOnly(){
		return null;
	}
	@ColumnDefine(
			mapping = "allowBlank",
			dataIndex = "allowBlank",
			header = "是否为空",
			type = ElementFieldType.TextField,			
			search = @SearchItemDefine(fieldLabel="描述",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.String getAllowBlank(){
		return null;
	}
	@ColumnDefine(
			mapping = "maxLength",
			dataIndex = "maxLength",
			header = "最大长度",
			type = ElementFieldType.TextField,			
			search = @SearchItemDefine(fieldLabel="描述",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.Integer getMaxLength(){
		return formFieldsBean.getMaxLength();
	}
		@ColumnDefine(
				mapping = "maxValue",
				dataIndex = "maxValue",
				header = "最大值", 
				type = ElementFieldType.TextField,	
				search = @SearchItemDefine(fieldLabel="UUID",xtype=ElementFieldType.Hidden,hidden=true)
	)	
	public java.lang.Integer getMaxValue(){
		return formFieldsBean.getMaxValue();
	}
  
   
    @ColumnDefine(
			mapping = "viewname",
			dataIndex = "viewname",
			header = "总线名称", 
			width=200,
			type = ElementFieldType.TextField,			
			search = @SearchItemDefine(fieldLabel="总线名称",xtype=ElementFieldType.TextField,hidden=true)
	)
	public java.lang.String getViewname(){
		return formFieldsBean.getTitle();
	}
    
    @ColumnDefine(
			mapping = "viewpath",
			dataIndex = "viewpath",
			width=400,
			header = "表达式", 
			type = ElementFieldType.TextField,			
			search = @SearchItemDefine(fieldLabel="表达式",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.String getViewpath(){
		return formFieldsBean.getPath();
	}
}