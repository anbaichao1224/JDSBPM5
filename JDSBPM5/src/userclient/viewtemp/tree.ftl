package ${package}.view.${tempbeanid};

import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import java.util.List;
import net.itjds.usm2.UsmProxy;
import ${package}.db.${table.className?lower_case}.*;
<#list tableList as item>
<#if item.otherTable.className != table.className>
import ${package}.db.${item.otherTable.className?lower_case}.*;
import ${package}.view.${item.otherTable.className?lower_case}.*;
</#if>
</#list>
import net.itjds.usm2.define.annotation.ButtonDefine;
import net.itjds.usm2.define.data.annotation.AjaxDataDefine;
import net.itjds.usm2.define.enums.*;
import net.itjds.usm2.define.tree.CheckTreeNode;
import net.itjds.usm2.define.tree.annotation.TreeListener;
import net.itjds.usm2.define.tree.annotation.TreeLoaderDefine;
import net.itjds.usm2.define.tree.annotation.TreeNodeDefine;
import net.itjds.usm2.define.tree.annotation.TreePanelDefine;

@TreePanelDefine(
		height = 0,
		width = 300,
		title = "${usmbean.name}", 
		checkModel = ElementCheckModel.single,
		rootVisible = true,
		listeners={
		@TreeListener(
				eventname=ElementEvent.click,
				    function="function(node,e){}"
				)
			},
		treeLoaderBean = @TreeLoaderDefine(
				dataUrl = "/expression.jsp?expression=$JSONTree"
		),
		treeNodeBean=@TreeNodeDefine(text="${usmbean.name}",id="{00000000-0000-0000-FFFF-000000000001}")
)



public class ${table.className}TreePanel extends CheckTreeNode {
	
	public ${table.className} ${table.className?uncap_first};

 	public ${table.className}TreePanel(${table.className} ${table.className?uncap_first}){
 	     this.${table.className?uncap_first} = (${table.className})${table.className?uncap_first};
 	     <#list table.colList as col>
			<#if "${col.dbcol.name}" == "cnname">
 	          this.setText(${table.className?uncap_first}.getCnname());
 	     </#if>
 	     </#list>
   	     this.setId(${table.className?uncap_first}.getPkValue());
   	     this.setPanelName("${table.className?uncap_first}TabPanel");
	   	 <#list tableList as item>
		 <#if item.otherTable.className != table.className>
		 <#if item.refTable.className != item.otherTable.className>
   	     this.setLeaf(false);
   	     </#if>   	    
		 </#if>		 
	     </#list>
  	}
 	public ${table.className}TreePanel(){
 	}

	 <#list tableList as item>
	 <#if item.otherTable.className==table.className>
	 /**
	 * 获取下级${item.otherTable.cnname}代理对象
	 * 
	 * @return 下级${item.otherTable.cnname}代理对象
	 */
	  @TreeNodeDefine()
	  public List<${table.className}TreePanel> child${table.className}(){
	  	return ${table.className?uncap_first}.getChild${item.otherTable.className}();
	  }
	 </#if> 
     </#list>
 
 	 <#list tableList as item>
	 <#if item.otherTable.className != table.className>
	 <#if item.refTable.className != item.otherTable.className>

	 /**
	 * 获取下级${item.otherTable.cnname}代理对象
	 * 
	 * @return 下级${item.otherTable.cnname}代理对象
	 */
	 @TreeNodeDefine()
	 public List<${item.otherTable.className}TreePanel> ${item.otherTable.className?uncap_first}s(){
	 	return ${table.className?uncap_first}.get${item.otherTable.className}s();
	 }
	</#if>
	<#if item.refTable.className==item.otherTable.className>
	/**
	 * 获取${item.otherTable.cnname}列表
	 * 
	 * @return ${item.otherTable.cnname}列表
	 */
	 @TreeNodeDefine()
     public ${item.otherTable.className}TreePanel child${item.otherTable.className}(){
	 	return ${table.className?uncap_first}.get${item.otherTable.className}();
	 }
	</#if> 
	</#if>
    </#list>

}
