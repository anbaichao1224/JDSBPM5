package ${package}.view.${tempbeanid};

<#list tableList as item>
import ${package}.db.${item.otherTable.className?lower_case}.*;
<#if item.otherTable.className != table.className>
import ${package}.view.${item.otherTable.className?lower_case}.*;
</#if> 
</#list>
import net.itjds.usm2.view.WelcomePanel;
import net.itjds.usm2.define.cartlayoutpanel.annotation.CartItemsDefine;
import net.itjds.usm2.define.cartlayoutpanel.annotation.CartPanelDefine;

@CartPanelDefine(height = 500, width = 300,activeItem = 0,xtype = "CartPanel")
public class ${table.className}CartPanel {
	
	<#list tableList as item> 
	private ${item.otherTable.className} ${item.otherTable.className?uncap_first}; 
	</#list>
	
	
	public ${table.className}CartPanel(<#list tableList as item>${item.otherTable.className} ${item.otherTable.className?uncap_first}<#if item_has_next>,</#if></#list>){
		<#list tableList as item>
		this.${item.otherTable.className?uncap_first} = ${item.otherTable.className?uncap_first};
		</#list>
	}
	
	@CartItemsDefine(title = "»¶Ó­Ò³Ãæ",html="»¶Ó­Ò³Ãæ")
	public WelcomePanel getWelcomePanel() {
		return new WelcomePanel();
	}

	<#list tableList as item>
	@CartItemsDefine(title = "${usmbean.name}",html="${usmbean.name}")
	public ${item.otherTable.className}TabPanel get${item.otherTable.className}TabPanel(){
		return new ${item.otherTable.className}TabPanel(${item.otherTable.className?uncap_first});
	}
	
	</#list>
}
