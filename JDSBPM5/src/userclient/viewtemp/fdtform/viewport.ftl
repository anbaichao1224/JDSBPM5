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
		expressionArr="${table.className}Viewport(R(\"where\"))",
		desc="${usmbean.name} by DAOTools ",
		dataType="context"
		)
		

public class ${table.className}Viewport {
	
	private ${table.className}CartPanel cartPanel;
	private String where ;

	public ${table.className}Viewport(String where){
		this.where=where;
	}
	

	
	@PanelItemDefine( region = "center",title = "${usmbean.name}")
	public ${table.className}CartPanel get${table.className}CartPanel() {
	   if (cartPanel==null){
			cartPanel=new ${table.className}CartPanel(where);
		}
		return cartPanel;
	}
}