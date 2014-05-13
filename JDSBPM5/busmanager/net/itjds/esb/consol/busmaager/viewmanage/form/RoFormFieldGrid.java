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
		title = "��ͼ��Ϣ",
		
			
		editRowData=@AjaxDataDefine(
				expression="$currFormPanel",
				viewId="currRoFormFieldView",
				winConfig=@WinConfigDefine(width=500,height=600,title="�޸��ֶ���Ϣ")
		),
		
		buttons = { @ButtonDefine(text=ElementButton.query,iconCls="zoom",hidden = false,handler = "query()"),
					@ButtonDefine(text=ElementButton.rebuild,iconCls="mobility",hidden = false,handler = "rebuild()"),
					@ButtonDefine(text=ElementButton.serialindex,iconCls="serialindex",hidden = false,handler = "serialindex()"),
					@ButtonDefine(text=ElementButton.createfield,iconCls="add",hidden = false,
							 ajax= @AjaxDataDefine(
			        					expression="$currFormPanel",
			        					viewId="currRoFormFieldView",
			        					winConfig=@WinConfigDefine(width=500,height=600,title="�����ֶ�")
			        			)
							),
							 @ButtonDefine(text=ElementButton.delete,iconCls="remove",hidden = false,handler = "remove()")
				         	   
        },
        rebuild = 	 @AjaxDataDefine(
				url="/update.action?expression=$UsmData.rebuild()"
		),
			fieldsIndex={
			"uuid",//UUID
			"name",//�ֶ�Ӣ����
			"cnname",//�ֶ�������
			"fieldLabel",//�ؼ���ǩ
			"width",//�ؼ����
			"xtype",//�ؼ�����
			"comboxkey",//�������ֵ
			"vtype",//��֤����
			"readOnly",//�Ƿ�ֻ��
			"allowBlank",//�Ƿ�Ϊ��
			"maxLength",//��󳤶�
			"maxValue",//���ֵ
			"viewname",//�ļ�����
			"viewpath"//�ļ�·��
			
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
			header = "�ֶ�Ӣ����", 
			type = ElementFieldType.TextField,			
			search = @SearchItemDefine(fieldLabel="��λ",xtype=ElementFieldType.Hidden,hidden=true)
	)
	public java.lang.String getName(){
		return formFieldsBean.getName();
	}
	@ColumnDefine(
			mapping = "cnname",
			dataIndex = "cnname",
			header = "�ֶ�������", 
			type = ElementFieldType.TextField
			)
	public java.lang.String getCnname(){
		return formFieldsBean.getFieldLabel();
	}
	@ColumnDefine(
			mapping = "fieldLabel",
			dataIndex = "fieldLabel",
			header = "�ؼ���ǩ",		
			type = ElementFieldType.Hidden,	
			hidden=true,
			search = @SearchItemDefine(fieldLabel="����",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.String getFieldLabel(){
		return formFieldsBean.getFieldLabel();
	}
	@ColumnDefine(
			mapping = "width",
			dataIndex = "width",
			header = "��ʾ���",
			type = ElementFieldType.TextField,			
			search = @SearchItemDefine(fieldLabel="����",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.Integer getWidth(){
		return formFieldsBean.getWidth();
	}
	@ColumnDefine(
			mapping = "xtype",
			dataIndex = "xtype",
			header = "��ʾ����",
			type = ElementFieldType.TextField,			
			search = @SearchItemDefine(fieldLabel="����",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.String getXtype(){
		return formFieldsBean.getXtype().getType();
	}
	 	@ColumnDefine(
				mapping = "comboxkey",
				dataIndex = "comboxkey",
				header = "�������ֵ", 
				type = ElementFieldType.TextField,
				search = @SearchItemDefine(fieldLabel="UUID",xtype=ElementFieldType.Hidden,hidden=true)
	)	
	public java.lang.String getComboxkey(){
		return null;
	}
	
	@ColumnDefine(
			mapping = "vtype",
			dataIndex = "vtype",
			header = "��֤����",
			type = ElementFieldType.TextField,			
			search = @SearchItemDefine(fieldLabel="����",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.String getVtype(){
		return null;
	}
	@ColumnDefine(
			mapping = "readOnly",
			dataIndex = "readOnly",
			header = "�Ƿ�ֻ��",
			type = ElementFieldType.TextField,			
			search = @SearchItemDefine(fieldLabel="����",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.String getReadOnly(){
		return null;
	}
	@ColumnDefine(
			mapping = "allowBlank",
			dataIndex = "allowBlank",
			header = "�Ƿ�Ϊ��",
			type = ElementFieldType.TextField,			
			search = @SearchItemDefine(fieldLabel="����",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.String getAllowBlank(){
		return null;
	}
	@ColumnDefine(
			mapping = "maxLength",
			dataIndex = "maxLength",
			header = "��󳤶�",
			type = ElementFieldType.TextField,			
			search = @SearchItemDefine(fieldLabel="����",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.Integer getMaxLength(){
		return formFieldsBean.getMaxLength();
	}
		@ColumnDefine(
				mapping = "maxValue",
				dataIndex = "maxValue",
				header = "���ֵ", 
				type = ElementFieldType.TextField,	
				search = @SearchItemDefine(fieldLabel="UUID",xtype=ElementFieldType.Hidden,hidden=true)
	)	
	public java.lang.Integer getMaxValue(){
		return formFieldsBean.getMaxValue();
	}
  
   
    @ColumnDefine(
			mapping = "viewname",
			dataIndex = "viewname",
			header = "��������", 
			width=200,
			type = ElementFieldType.TextField,			
			search = @SearchItemDefine(fieldLabel="��������",xtype=ElementFieldType.TextField,hidden=true)
	)
	public java.lang.String getViewname(){
		return formFieldsBean.getTitle();
	}
    
    @ColumnDefine(
			mapping = "viewpath",
			dataIndex = "viewpath",
			width=400,
			header = "���ʽ", 
			type = ElementFieldType.TextField,			
			search = @SearchItemDefine(fieldLabel="���ʽ",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.String getViewpath(){
		return formFieldsBean.getPath();
	}
}