package net.itjds.esb.consol.busmaager.viewmanage;


import java.sql.Timestamp;

import net.itjds.bpm.data.xmlproxy.manager.USMTempBean;
import net.itjds.common.dbutil.ColInfo;
import net.itjds.common.dbutil.DAOColProxy;
import net.itjds.usm2.define.annotation.ButtonDefine;
import net.itjds.usm2.define.data.annotation.AjaxDataDefine;
import net.itjds.usm2.define.data.annotation.WinConfigDefine;
import net.itjds.usm2.define.enums.ElementButton;
import net.itjds.usm2.define.enums.ElementFieldType;
import net.itjds.usm2.define.grid.annotation.ColumnDefine;
import net.itjds.usm2.define.grid.annotation.GridPanelDefine;
import net.itjds.usm2.define.grid.annotation.SearchItemDefine;
import net.itjds.usm2.define.mapping.PanelBean;



@GridPanelDefine(
		title = "���ݿ���Ϣ",
		
		buttons = { @ButtonDefine(text=ElementButton.query,iconCls="zoom",hidden = false,handler = "query()"),
				@ButtonDefine(text=ElementButton.rebuild,iconCls="mobility",hidden = false,handler = "rebuild()"),
				@ButtonDefine(text=ElementButton.serialindex,iconCls="serialindex",hidden = false,handler = "serialindex()"),
				@ButtonDefine(text=ElementButton.createfield,iconCls="add",hidden = false,
						 ajax= @AjaxDataDefine(
		        					expression="$currFormPanel",
		        					viewId="currDbFieldForm",
		        					winConfig=@WinConfigDefine(width=500,height=300,title="�����ֶ�")
		        			)
						),
						 @ButtonDefine(text=ElementButton.delete,iconCls="remove",hidden = false,handler = "remove()")
			         	   
    },
	
	editRowData=@AjaxDataDefine(
			expression="$currFormPanel",
			viewId="currDbFieldForm",
			winConfig=@WinConfigDefine(width=500,height=300,title="�޸��ֶ���Ϣ")
	),
    rebuild = 	 @AjaxDataDefine(
			url="/update.action?expression=$UsmData.rebuild()"
	),
			fieldsIndex={
			"uuid",//UUID
			"cnname",//ע��
			"name",//�ֶ�����
			"type",
			"length",
			"canNull"
			
		}		
)
public class DbFieldGrid {
	
 	private ColInfo bean;
 	public DbFieldGrid(ColInfo info){
 		this.bean=info;
 	}
 	
 	
 	
 	public DbFieldGrid(){
 	    
 	}
  
	
 	@ColumnDefine(
 				mapping = "uuid",
 				dataIndex = "uuid",
 				header = "ID", 
 				type = ElementFieldType.Hidden,
 				hidden = true,		
 				search = @SearchItemDefine(fieldLabel="UUID",xtype=ElementFieldType.TextField,hidden=true)
 	)	
 	public java.lang.String getUuid(){
		return bean.getFieldname();
	}
 
   
  
   
    @ColumnDefine(
			mapping = "cnname",
			dataIndex = "cnname",
			header = "��������", 
			width=200,
			type = ElementFieldType.TextField
	)
	public java.lang.String getCnname(){
		return bean.getCnname();
	}
    
    @ColumnDefine(
			mapping = "name",
			dataIndex = "name",
			width=100,
			header = "�ֶ�����", 
			type = ElementFieldType.TextField
	)
	public java.lang.String getName(){
		return bean.getName();
	}
    @ColumnDefine(
			mapping = "type",
			dataIndex = "type",
			width=100,
			header = "�ֶ�����", 
			type = ElementFieldType.TextField
	)
	public int getType(){
		return bean.getDataType();
	}
    @ColumnDefine(
			mapping = "length",
			dataIndex = "length",
			width=100,
			header = "�ֶγ���", 
			type = ElementFieldType.TextField
	)
	public int getLength(){
		return bean.getLength();
	}
    @ColumnDefine(
			mapping = "canNull",
			dataIndex = "canNull",
			width=150,
			header = "�Ƿ�����Ϊ��", 
			type = ElementFieldType.TextField
	)
	public String getCanNull(){
		return bean.isCanNull()?"��":"��";
	}
}