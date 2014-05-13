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
		title = "视图信息",
		
		editRowData=@AjaxDataDefine(
				expression="$currFormPanel",
				viewId="currRoGridFieldView",
				winConfig=@WinConfigDefine(width=500,height=350,title="修改字段信息")
		),
		
		buttons = { @ButtonDefine(text=ElementButton.query,iconCls="zoom",hidden = false,handler = "query()"),
					@ButtonDefine(text=ElementButton.rebuild,iconCls="mobility",hidden = false,handler = "rebuild()"),
					@ButtonDefine(text=ElementButton.serialindex,iconCls="serialindex",hidden = false,handler = "serialindex()"),
					@ButtonDefine(text=ElementButton.createfield,iconCls="add",hidden = false,
							 ajax= @AjaxDataDefine(
			        					expression="$currFormPanel",
			        					viewId="currRoGridFieldView",
			        					winConfig=@WinConfigDefine(width=500,height=350,title="新增字段")
			        			)
							),
							 @ButtonDefine(text=ElementButton.delete,iconCls="remove",hidden = false,handler = "remove()")
				         	   
        },
			

			fieldsIndex={
			"uuid",//UUID
			"name",//字段英文名
			"cnname",//字段中文名
			"width",//控件宽度
			"sortable",//是否排序
			"hidden",//是否隐藏
		
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
			header = "列映射", 
			type = ElementFieldType.TextField
			)
	public java.lang.String getName(){
		return columnBean.getName();
	}
	@ColumnDefine(
			mapping = "cnname",
			dataIndex = "cnname",
			header = "列标题", 
			type = ElementFieldType.TextField,			
			search = @SearchItemDefine(fieldLabel="描述",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.String getCnname(){
		return columnBean.getHeader();
	}
	
	@ColumnDefine(
			mapping = "width",
			dataIndex = "width",
			header = "列宽",
			type = ElementFieldType.TextField,			
			search = @SearchItemDefine(fieldLabel="描述",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.Integer getWidth(){
		return columnBean.getWidth();
	}
	
	@ColumnDefine(
			mapping = "hidden",
			dataIndex = "hidden",
			header = "是否显示",
			type = ElementFieldType.TextField,			
			search = @SearchItemDefine(fieldLabel="描述",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.String getHidden(){
		return columnBean.getHidden().toString();
	}
	
	@ColumnDefine(
			mapping = "sortable",
			dataIndex = "sortable",
			header = "是否可以排序",
			type = ElementFieldType.TextField
			)
	public java.lang.String getSortable(){
		return columnBean.getSortable().toString();
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
		return bean.getPath();
	}
}