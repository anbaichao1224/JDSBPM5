package net.itjds.esb.consol.busmaager.viewmanage.grid;



import net.itjds.usm2.define.annotation.ButtonDefine;
import net.itjds.usm2.define.data.annotation.AjaxDataDefine;
import net.itjds.usm2.define.data.annotation.WinConfigDefine;
import net.itjds.usm2.define.enums.ElementButton;
import net.itjds.usm2.define.enums.ElementFieldType;
import net.itjds.usm2.define.grid.annotation.ColumnDefine;
import net.itjds.usm2.define.grid.annotation.GridPanelDefine;
import net.itjds.usm2.define.grid.annotation.GroupGridPanelDefine;
import net.itjds.usm2.define.grid.annotation.SearchItemDefine;
import net.itjds.usm2.define.grid.mapping.ColumnBean;
import net.itjds.usm2.define.mapping.FieldBean;



@GroupGridPanelDefine(
		title = "��ͼ��Ϣ",
		
		editRowData=@AjaxDataDefine(
				expression="$currFormPanel",
				viewId="currRoGridFieldView",
				winConfig=@WinConfigDefine(width=500,height=350,title="�޸��ֶ���Ϣ")
		),
		
		buttons = { @ButtonDefine(text=ElementButton.query,iconCls="zoom",hidden = false,handler = "query()"),
					@ButtonDefine(text=ElementButton.rebuild,iconCls="mobility",hidden = false,handler = "rebuild()"),
					@ButtonDefine(text=ElementButton.serialindex,iconCls="serialindex",hidden = false,handler = "serialindex()"),
					@ButtonDefine(text=ElementButton.createfield,iconCls="add",hidden = false,
							 ajax= @AjaxDataDefine(
			        					expression="$currFormPanel",
			        					viewId="currRoGridFieldView",
			        					winConfig=@WinConfigDefine(width=500,height=350,title="�����ֶ�")
			        			)
							),
							 @ButtonDefine(text=ElementButton.delete,iconCls="remove",hidden = false,handler = "remove()")
				         	   
        },
			

			fieldsIndex={
			"uuid",//UUID
			"name",//�ֶ�Ӣ����
			"cnname",//�ֶ�������
			"width",//�ؼ����
			"sortable",//�Ƿ�����
			"hidden",//�Ƿ�����
		
			"viewpath"
			
		}, 
		groupField = "hidden"		
)
public class RoGridFieldGrid {
	
 	private FieldBean  bean;
 	private ColumnBean columnBean;
 	public RoGridFieldGrid(FieldBean bean){
 	      if(bean instanceof FieldBean)
 	      {
 	    	  this.bean=bean;
 	    	  this.columnBean=(ColumnBean)bean;
 	      }
 	}
 	
 	
 	
 	public RoGridFieldGrid(){
 	    
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
		return columnBean.getId();
	}



	@ColumnDefine(
			mapping = "name",
			dataIndex = "name",
			header = "��ӳ��", 
			type = ElementFieldType.TextField
			)
	public java.lang.String getName(){
		return columnBean.getName();
	}
	@ColumnDefine(
			mapping = "cnname",
			dataIndex = "cnname",
			header = "�б���", 
			type = ElementFieldType.TextField,			
			search = @SearchItemDefine(fieldLabel="����",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.String getCnname(){
		return columnBean.getHeader();
	}
	
	@ColumnDefine(
			mapping = "width",
			dataIndex = "width",
			header = "�п�",
			type = ElementFieldType.TextField,			
			search = @SearchItemDefine(fieldLabel="����",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.Integer getWidth(){
		return columnBean.getWidth();
	}
	
	@ColumnDefine(
			mapping = "hidden",
			dataIndex = "hidden",
			header = "�Ƿ���ʾ",
			type = ElementFieldType.TextField,			
			search = @SearchItemDefine(fieldLabel="����",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.String getHidden(){
		return columnBean.getHidden().toString();
	}
	
	@ColumnDefine(
			mapping = "sortable",
			dataIndex = "sortable",
			header = "�Ƿ��������",
			type = ElementFieldType.TextField
			)
	public java.lang.String getSortable(){
		return columnBean.getSortable().toString();
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
		return bean.getPath();
	}
}