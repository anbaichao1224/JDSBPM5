package net.itjds.esb.consol.busmaager.viewmanage.crud;

import net.itjds.bpm.data.xmlproxy.manager.ExpressionTempFtlBean;
import net.itjds.usm2.define.annotation.ButtonDefine;
import net.itjds.usm2.define.enums.ElementButton;
import net.itjds.usm2.define.enums.ElementFieldType;
import net.itjds.usm2.define.grid.annotation.ColumnDefine;
import net.itjds.usm2.define.grid.annotation.GridPanelDefine;
import net.itjds.usm2.define.grid.annotation.GroupGridPanelDefine;
import net.itjds.usm2.define.grid.annotation.SearchItemDefine;



@GroupGridPanelDefine(
		title = "模板信息",
		
		groupField="type",
	
		
		buttons = {@ButtonDefine(text=ElementButton.rebuild,iconCls="mobility",hidden = false,handler = "query()")},
			fieldsIndex={
			"uuid",//UUID
			"name",//名称
			"type",//类型
			"classname",//类型
			"ftlpath"//文件路径
		}	
		
		
)
public class EsbTempGrid {
	
 	private ExpressionTempFtlBean  ftlBean;
 	public EsbTempGrid(ExpressionTempFtlBean ftlBean){
 	      this.ftlBean=ftlBean;
 	}
 	
 	
 	
 	public EsbTempGrid(){
 	    
 	}
  
	
 	@ColumnDefine(
 				mapping = "uuid",
 				dataIndex = "uuid",
 				header = "ID", 
 				type = ElementFieldType.TextField,
 				hidden = true,		
 				search = @SearchItemDefine(fieldLabel="UUID",xtype=ElementFieldType.TextField,hidden=true)
 	)	
 	public java.lang.String getUuid(){
		return ftlBean.getFtlpath();
	}
 
   
  
   
    @ColumnDefine(
			mapping = "name",
			dataIndex = "name",
			header = "模板名称", 
			width=200,
			type = ElementFieldType.TextField
	)
	public java.lang.String getName(){
		return ftlBean.getName();
	}
    
    @ColumnDefine(
			mapping = "ftlpath",
			dataIndex = "ftlpath",
			width=400,
			header = "路径", 
			type = ElementFieldType.TextField
	)
	public java.lang.String getFtlpath(){
		return ftlBean.getFtlpath();
	}
    @ColumnDefine(
			mapping = "classname",
			dataIndex = "classname",
			width=400,
			header = "JAVA类名", 
			type = ElementFieldType.TextField
	)
	public java.lang.String getClassname(){
		return ftlBean.getClassname();
	}
    @ColumnDefine(
			mapping = "type",
			dataIndex = "type",
			width=100,
			header = "模板类型", 
			type = ElementFieldType.TextField
	)
	public java.lang.String getType(){
		return ftlBean.getType();
	}
}