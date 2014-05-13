package ${package}.view.${tempbeanid};

import java.util.List;
import net.itjds.usm2.UsmProxy;
import ${package}.db.${table.className?lower_case}.*;
<#list tableList as item>
<#if item.otherTable.className != table.className>
import ${package}.${item.otherTable.className?lower_case}.*;
</#if>
</#list>
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
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
				dataUrl = "/expression.jsp?expression=$JSONTree($select${table.className}PopTree)"
		),
		treeNodeBean=@TreeNodeDefine(text="滕州市",id="{00000000-0000-0000-FFFF-000000000001}"),
		buttons = {@ButtonDefine()}, 
		updateData = @AjaxDataDefine(url = "expression.jsp?expression=$TreeService.movePerson()")
)

@MapdaoBeanAnnotation(id="select${table.className}PopTree",
		name="${table.cnname}",
		expressionArr="${table.className}PopTreePanel($currFromData())",
		desc="${table.cnname}",
		dataType="action"
)


public class ${table.className}PopTreePanel extends CheckTreeNode {
	
	public ${table.className} ${table.className?uncap_first};

 	public ${table.className}PopTreePanel(${table.className} ${table.className?uncap_first}){
 	     this.${table.className?uncap_first} = (${table.className})${table.className?uncap_first};
 	     this.setText(${table.className?uncap_first}.getCnname());
   	     this.setId(${table.className?uncap_first}.getPkValue());
   	     this.setPanelName("${table.className?uncap_first}TabPanel");
   	     this.setLeaf(false);
  	}
 	public ${table.className}PopTreePanel(){
 	}

	 <#list tableList as item>
	 <#if item.otherTable.className==table.className>
	 /**
	 * 获取下级${item.otherTable.cnname}代理对象
	 * 
	 * @return 下级${item.otherTable.cnname}代理对象
	 */
	 @TreeNodeDefine()
	  public List<${table.className}PopTreePanel> child${table.className}(){
	  	return ${table.className?uncap_first}.getChild${item.otherTable.className}();
	  }
	 </#if> 
     </#list>
 
 	 <#list tableList as item>
	 <#if item.otherTable.className != table.className>
	 <#if item.refTable.className!=item.otherTable.className>
	 /**
	 * 获取下级${item.otherTable.cnname}代理对象
	 * 
	 * @return 下级${item.otherTable.cnname}代理对象
	 */
	 @TreeNodeDefine()
	 public List<${table.className}TreePanel> ${item.otherTable.className?uncap_first}s(){
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
     public ${table.className}TreePanel child${item.otherTable.className}(){
	 	return ${table.className?uncap_first}.get${item.otherTable.className}();
	 }
	</#if> 
     </#list>

}
