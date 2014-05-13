package ${package}.fdtview.${tempbeanid};
import net.itjds.j2ee.dao.MethodChinaName;
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
				url="/update.action?expression=$UpdateCurrForm($currFormData)"
		),
		fieldsIndex={
			<#list table.colList as col>
			<#if col.exttype != "None">
				<#if "${col.exttype}" == "DateField">
				"${col.fieldName}Str",//${col.cnname}×Ö·û´®
				</#if>
				"${col.fieldName}"<#if col_has_next>,</#if>//${col.cnname}
			
			</#if>			
		    </#list>		
		}		
)

@MapdaoBeanAnnotation(id="curr${table.className}PopForm",
		name="${table.cnname}ÊÓÍ¼",
		expressionArr="${table.className}PopForm($${table.className}())",
		desc="${table.cnname}",
		dataType="context"
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
 	@MethodChinaName(cname="${col.cnname}")
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
 	@MethodChinaName(cname="${col.cnname}")
	public java.util.Date  get${col.className}(){
		return new java.util.Date (${table.fieldName}.get${col.className}().getTime());
	}
	
	public void set${col.className}(java.util.Date  ${col.fieldName}){
		 ${table.fieldName}.set${col.className}(new ${col.fieldtype}(${col.fieldName}.getTime()));
	}
	
	
	</#if>
	
    </#list>	
}
	