package net.itjds.esb.consol.busmaager.viewmanage;


import net.itjds.usm2.define.annotation.ButtonDefine;
import net.itjds.usm2.define.enums.ElementButton;
import net.itjds.usm2.define.enums.ElementFieldType;
import net.itjds.usm2.define.grid.annotation.ColumnDefine;
import net.itjds.usm2.define.grid.annotation.GridPanelDefine;
import net.itjds.usm2.define.grid.annotation.SearchItemDefine;
import net.itjds.usm2.define.mapping.PanelBean;



@GridPanelDefine(
		title = "视图信息",
		
		buttons = {@ButtonDefine(text=ElementButton.rebuild,iconCls="mobility",hidden = false,handler = "query()")},
			

			fieldsIndex={
			"uuid",//UUID
			"viewname",//文件名称
			"viewpath"//文件路径
			
		}		
)
public class ViewGrid {
	
 	private PanelBean  bean;
 	public ViewGrid(PanelBean bean){
 	      this.bean=bean;
 	}
 	
 	
 	
 	public ViewGrid(){
 	    
 	}
  
	
 	@ColumnDefine(
 				mapping = "uuid",
 				dataIndex = "uuid",
 				header = "ID", 
 				type = ElementFieldType.TextField,
 				hidden = false,		
 				search = @SearchItemDefine(fieldLabel="UUID",xtype=ElementFieldType.TextField,hidden=true)
 	)	
 	public java.lang.String getUuid(){
		return bean.getPath();
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
		return bean.getTitle();
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