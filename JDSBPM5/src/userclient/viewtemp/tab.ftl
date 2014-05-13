package ${package}.view.${tempbeanid};

import java.util.List;
import ${package}.db.${table.className?lower_case}.*;
<#list tableList as item>
import ${package}.db.${item.otherTable.className?lower_case}.*;
<#if item.otherTable.className != table.className>
import ${package}.view.${item.otherTable.className?lower_case}.*;
</#if> 
</#list>
import net.itjds.usm2.define.tabpanel.annotation.TabItemsDefine;
import net.itjds.usm2.define.tabpanel.annotation.TabPanelDefine;

@TabPanelDefine(height = 500, width = 300,activeTab = 0,xtype = "TabPanel",servicekey="${table.className}")
public class ${table.className}TabPanel {
	private ${table.className} ${table.className?uncap_first};
		
	public ${table.className}TabPanel(${table.className} ${table.className?uncap_first}){
		this.${table.className?uncap_first} = ${table.className?uncap_first};
	}

	@TabItemsDefine(title = "基本信息",html = "基本信息")
	public ${table.className}Form get${table.className}InfoBean() {
		return new ${table.className}Form(${table.className?uncap_first});
	}
	
	 <#list tableList as item>
	 <#if item.otherTable.className==table.className>
	 /**
	 * 获取下级${item.otherTable.cnname}代理对象
	 * 
	 * @return 下级${item.otherTable.cnname}代理对象
	 */
	@TabItemsDefine(title = "${item.otherTable.cnname}",html = "")
	public List<${item.otherTable.className}Grid> get${item.otherTable.className}ChildList() {
		List list=${table.className?uncap_first}.getChild${table.className}();
		return list;
	}
	 </#if> 
     </#list>
 
 	 <#list tableList as item>
	 <#if item.otherTable.className != table.className>
	 /**
	 * 获取下级${item.otherTable.cnname}代理对象
	 * 
	 * @return 下级${item.otherTable.cnname}代理对象
	 */
	 @TabItemsDefine(title = "${item.otherTable.cnname}",html = "")
	 public List<${item.otherTable.className}Grid> get${item.otherTable.className}s(){
	 	return ${table.className?uncap_first}.get${item.otherTable.className}s();
	 }
	 </#if> 
     </#list>
	
}
