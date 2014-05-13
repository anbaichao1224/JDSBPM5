package ${package}.view.${tempbeanid};

import net.itjds.common.util.DateUtility;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.usm2.define.data.annotation.AjaxDataDefine;
import net.itjds.usm2.define.data.annotation.HttpBaseParams;
import net.itjds.usm2.define.enums.*;
import net.itjds.usm2.define.form.annotation.FormFieldDefine;
import net.itjds.usm2.define.form.annotation.FormPanelDefine;
import ${package}.db.${table.className?lower_case}.*;

@FormPanelDefine(
		labelWidth = 0,
		labelAlign = ElementAlign.left,
		buttons = {ElementButton.save,ElementButton.abort},
		loadData=@AjaxDataDefine(
				url="/expression.jsp?expression=$JSONForm($curr${table.className}PopForm)"
		),
		updateData=@AjaxDataDefine(
				url="/update.action?expression=$${table.className}.add${table.className}($currFormData)"
				//url="/update.action?expression=$UpdateCurrForm($currFormData)"
		),
		fieldsIndex={
			<#list table.colList as col>
				"${col.fieldName}"<#if col_has_next>,</#if>//${col.cnname}
			 </#list>		
		}		
)

@MapdaoBeanAnnotation(id="curr${table.className}PopForm",
		name="${table.cnname}йсм╪",
		expressionArr="${table.className}PopForm($currFormData())",
		desc="${table.cnname}",
		dataType="action"
)

public class ${table.className}PopForm{
 	public ${table.className} ${table.fieldName};
 	public ${table.className}PopForm(${table.className} ${table.fieldName}){
 	      this.${table.fieldName}=(${table.className})${table.fieldName};
 	}
 
 
    <#list table.colList as col>
    <#if "${col.exttype}" != "DateField">
     @FormFieldDefine(id = "$curr${table.className}.${col.className}", 
 					 name = "$curr${table.className}.${col.className}", 
 					 fieldLabel = "${col.cnname}",
 					 xtype = ElementFieldType.${col.exttype},
 					 model = "${col.model}",
 					 <#if "${col.model}" == "remote">
 					 store = "expression.jsp?expression=$UsmData.commondata(\"${col.commoncode}\")",	
 					 </#if>	
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = ${col.dbcol.canNull?string},
 					 maxLength = ${col.length}
 	)
	public ${col.fieldtype} get${col.className}(){
		return ${table.fieldName}.get${col.className}();
	}
	
	public void set${col.className}(${col.fieldtype} ${col.fieldName}){
		 ${table.fieldName}.set${col.className}(${col.fieldName});
	}
	</#if>
	<#if "${col.exttype}" == "DateField">
	@FormFieldDefine(id = "$curr${table.className}.${col.className}", 
 					 name = "$curr${table.className}.${col.className}", 
 					 fieldLabel = "${col.cnname}",
 					 xtype = ElementFieldType.Hidden,
 					 model = "${col.model}",
 					 <#if "${col.model}" == "remote">
 					 store = "expression.jsp?expression=$UsmData.commondata(\"${col.commoncode}\")",	
 					 </#if>					 
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = ${col.dbcol.canNull?string},
 					 maxLength = ${col.length}
 	)
	public ${col.fieldtype} get${col.className}(){
		return ${table.fieldName}.get${col.className}();
	}
	
	public void set${col.className}(${col.fieldtype} ${col.fieldName}){
		 ${table.fieldName}.set${col.className}(${col.fieldName});
	}
	
	
	@FormFieldDefine(id = "$curr${table.className}.${col.className}Str", 
 					 name = "$curr${table.className}.${col.className}Str", 
 					 fieldLabel = "${col.cnname}",
 					 xtype = ElementFieldType.DateField,
 					 model = "${col.model}",
 					 <#if "${col.model}" == "remote">
 					 store = "expression.jsp?expression=$UsmData.commondata(\"${col.commoncode}\")",	
 					 </#if>					 
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = ${col.dbcol.canNull?string},
 					 maxLength = ${col.length}
 	)
	public String get${col.className}Str(){
		return DateUtility.formatDate(${table.fieldName}.get${col.className}(),"yyyy-MM-dd");
	}
	
	public void set${col.className}Str(String ${col.fieldName}Str){
		 ${table.fieldName}.set${col.className}(${col.fieldtype}.valueOf(${col.fieldName}Str));
	}	
	</#if>
	
    </#list>	
}
	