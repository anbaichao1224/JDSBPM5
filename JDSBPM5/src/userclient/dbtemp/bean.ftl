package ${package}.db.${tempbeanid};
import net.itjds.usm2.db.*;
import net.itjds.usm2.*;
import java.util.List;
<#list tableList as item>		  
import ${package}.db.${item.otherTable.className?lower_case}.*;
import ${package}.db.${item.otherTable.className?lower_case}.inner.*;
import ${package}.db.${item.otherTable.className?lower_case}.proxy.*;
</#list>

/**
 * <p>
 * Title: USM
 * </p>
 * <p>
 * Description: ${table.cnname}接口
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: www.justdos.net
 * </p>
 * 
 * @author usm2
 * @version 1.0
 */
	public interface ${table.className} extends UsmProxy{
	
	 <#list table.colList as col>
	 /**
	 * 获取${col.cnname}
	 * 
	 * @return ${col.cnname}
	 */
	public ${col.fieldtype} get${col.className}();
	public void set${col.className}(${col.fieldtype} ${col.fieldName});	
    </#list>
    

	<#list tableList as item>		  
	 <#if item.otherTable.className!=table.className>
	 
	  <#if item.refTable.className==item.otherTable.className>
	
	/**
	 * 获取${item.otherTable.cnname}列表
	 * 
	 * @return ${item.otherTable.cnname}列表
	 */
    public ${item.otherTable.className} get${item.otherTable.className}() ;
	    </#if> 
	 <#if item.refTable.className!=item.otherTable.className>
	 /**
	  * 获取${item.otherTable.cnname}代理对象
	  * 
	  * @return ${item.otherTable.cnname}代理对象
	  */
	 public USMListProxy get${item.otherTable.className}s();
    /**
	 * 将${item.otherTable.cnname}添加到当前对象
	 * 并保存当前结果
	 * @return 结果信息
	 */
	  public boolean add${item.otherTable.className}(${item.otherTable.className} ${item.otherTable.fieldName}) ; 
	 /**
	  * 更改关联关系将${item.otherTable.className}移入${table.className}
	  */
	  public boolean move${item.otherTable.className}In(List<String> ${item.otherTable.pkName}s) ; 
	  /**
	   * 删除${item.otherTable.className}与${table.className}关联关系
	   */
	  public boolean move${item.otherTable.className}Out(List<String> ${item.otherTable.pkName}s) ;  
	 
	  </#if> 
	 
	  </#if> 
	  
	  
	  
	</#list>
	

	 
	 <#list tableList as item>
	 <#if item.otherTable.className==table.className>
	 /**
	 * 获取下级${item.otherTable.cnname}代理对象
	 * 
	 * @return 下级${item.otherTable.cnname}代理对象
	 */
	  public USMListProxy getChild${table.className}() ;
	  public ${table.className}Proxy getParent${table.className}();
	 </#if> 
     </#list>
		
	
  }
	