package ${package}.view.${tempbeanid};

import net.itjds.common.util.DateUtility;
import net.itjds.usm2.define.data.annotation.AjaxDataDefine;
import net.itjds.usm2.define.data.annotation.HttpBaseParams;
import net.itjds.usm2.define.enums.*;
import net.itjds.usm2.define.annotation.ButtonDefine;
import net.itjds.usm2.define.grid.annotation.ColumnDefine;
import net.itjds.usm2.define.form.annotation.AddFormDataDefine;
import net.itjds.usm2.define.grid.annotation.GridPanelDefine;
import net.itjds.usm2.define.grid.annotation.SearchItemDefine;
import net.itjds.usm2.db.UsmService;
import ${package}.db.${table.className?lower_case}.*;
import com.opensymphony.xwork2.ActionContext;

@GridPanelDefine(
		title = "${table.cnname}",
		buttons = {@ButtonDefine(text=ElementButton.query,iconCls="zoom",hidden = false,handler = "query()"),
				   @ButtonDefine(text=ElementButton.add,iconCls="add",hidden = false,handler = "window.open(context+'fdt/display/formshow.jsp?action=${table.fieldName?lower_case}.html.jsp')"),

				   @ButtonDefine(text=ElementButton.editstyle,iconCls="serialindex",hidden = false,handler = "window.open(context+'fdt/formeditor/edit.action?fileName=*${table.fieldName?lower_case}.html')"),
				   @ButtonDefine(text=ElementButton.bindfield,iconCls="edit",hidden = false,handler = "window.open(context+'fdt/designer/fdtDesigner.action?fileName=*${table.fieldName?lower_case}.html')"),
					
				   @ButtonDefine(text=ElementButton.delete,iconCls="remove",hidden = false,handler = "remove()")},
		editRowData=@AjaxDataDefine(
				url="/expression.jsp?expression=$currFormPanel&viewId=curr${table.className}PopForm"
		),
		addRowData=@AjaxDataDefine(
				url="/expression.jsp?expression=$currFormPanel&viewId=curr${table.className}PopForm"
		),
		fieldsIndex={
		
			<#list table.colList as col>
			<#if col.exttype != "None">
				<#if "${col.exttype}" == "DateField">
				"${col.fieldName}Str",//${col.cnname}×Ö·û´®
				</#if>
				<#if "${col.exttype}" != "DateField">
				"${col.fieldName}"<#if col_has_next>,</#if>//${col.cnname}
				</#if>
			
			</#if>			
		    </#list>
		}		
)
public class ${table.className}Grid {
	UsmService service;
 	private ${table.className} ${table.fieldName};
 	public ${table.className}Grid(${table.className} ${table.fieldName}){
 	      this.${table.fieldName}=(${table.className})${table.fieldName};
 	      this.service = (UsmService) ActionContext.getContext().getValueStack().findValue("$${table.className}UsmService");
 	}
 	
 	public ${table.className}Grid(){
 	    
 	}
 
 
   <#list table.colList as col>
   <#if "${col.exttype}" != "DateField">
    @ColumnDefine(
			mapping = "${col.className?uncap_first}",
			dataIndex = "${col.className?uncap_first}",
			header = "${col.cnname}", 
			type = ElementFieldType.${col.exttype},
			tableName = "${table.tableName}",
 			columnName = "${col.dbcol.name}",			
			<#if "${col.exttype}" == "Hidden">
 			hidden = true,	
 			</#if>
			search = @SearchItemDefine(fieldLabel="${col.cnname}",id="${col.dbcol.name}",xtype=ElementFieldType.${col.exttype},hidden=true)
	)
	public ${col.fieldtype} get${col.className}(){
		return ${table.fieldName}.get${col.className}();
	}
	</#if>
	<#if "${col.exttype}" == "DateField">
	
    @ColumnDefine(
			mapping = "${col.className?uncap_first}Str",
			dataIndex = "${col.className?uncap_first}Str",
			header = "${col.cnname}", 
			type = ElementFieldType.TextField,			
			<#if "${col.exttype}" == "Hidden">
 			hidden = true,	
 			</#if>
 			tableName = "${table.tableName}",
 			columnName = "${col.dbcol.name}"
	)
	public String get${col.className}Str(){
		return DateUtility.formatDate(${table.fieldName}.get${col.className}(),"yyyy-MM-dd");
	}
	</#if>
    </#list>	
}