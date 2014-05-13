package ${package}.view.${tempbeanid};

import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import ${package}.db.${table.className?lower_case}.*;
<#list tableList as item>
<#if item.otherTable.className != table.className>
import ${package}.db.${item.otherTable.className?lower_case}.*;
</#if>
</#list>
import net.itjds.usm2.define.annotation.PanelItemDefine;
import net.itjds.usm2.define.annotation.ViewPortDefine;

@ViewPortDefine(title = "${usmbean.name}", layout = "border")

@MapdaoBeanAnnotation(id="${table.className}Viewport",
		name="${usmbean.name}",
		expressionArr="${table.className}Viewport(<#list tableList as item>$${item.otherTable.className}()<#if item_has_next>,</#if></#list>)",
		desc="${usmbean.name} by DAOTools ",
		dataType="action"
		)
		

public class ${table.className}Viewport {
	
	private ${table.className} ${table.className?uncap_first};
	<#list tableList as item>
	<#if item.otherTable.className != table.className>
	private ${item.otherTable.className} ${item.otherTable.className?uncap_first}; 
	</#if>
	</#list>

	public ${table.className}Viewport(<#list tableList as item>${item.otherTable.className} ${item.otherTable.className?uncap_first}<#if item_has_next>,</#if></#list>){
		<#list tableList as item>
		this.${item.otherTable.className?uncap_first} = ${item.otherTable.className?uncap_first};
		</#list>
	}
	

	@PanelItemDefine(height = 300, width = 250, region = "west",title = "${usmbean.name}")
	public ${table.className}TreePanel get${table.className}TreePanel() {
		return new ${table.className}TreePanel(${table.className?uncap_first});
	}
	
	
	@PanelItemDefine(height = 300, width = 300, region = "center",title = "${usmbean.name}")
	public ${table.className}CartPanel get${table.className}CartPanel() {
		return new ${table.className}CartPanel(<#list tableList as item>${item.otherTable.className?uncap_first}<#if item_has_next>,</#if></#list>);
	}
}