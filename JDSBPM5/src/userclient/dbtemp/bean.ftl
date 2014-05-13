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
 * Description: ${table.cnname}�ӿ�
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
	 * ��ȡ${col.cnname}
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
	 * ��ȡ${item.otherTable.cnname}�б�
	 * 
	 * @return ${item.otherTable.cnname}�б�
	 */
    public ${item.otherTable.className} get${item.otherTable.className}() ;
	    </#if> 
	 <#if item.refTable.className!=item.otherTable.className>
	 /**
	  * ��ȡ${item.otherTable.cnname}�������
	  * 
	  * @return ${item.otherTable.cnname}�������
	  */
	 public USMListProxy get${item.otherTable.className}s();
    /**
	 * ��${item.otherTable.cnname}��ӵ���ǰ����
	 * �����浱ǰ���
	 * @return �����Ϣ
	 */
	  public boolean add${item.otherTable.className}(${item.otherTable.className} ${item.otherTable.fieldName}) ; 
	 /**
	  * ���Ĺ�����ϵ��${item.otherTable.className}����${table.className}
	  */
	  public boolean move${item.otherTable.className}In(List<String> ${item.otherTable.pkName}s) ; 
	  /**
	   * ɾ��${item.otherTable.className}��${table.className}������ϵ
	   */
	  public boolean move${item.otherTable.className}Out(List<String> ${item.otherTable.pkName}s) ;  
	 
	  </#if> 
	 
	  </#if> 
	  
	  
	  
	</#list>
	

	 
	 <#list tableList as item>
	 <#if item.otherTable.className==table.className>
	 /**
	 * ��ȡ�¼�${item.otherTable.cnname}�������
	 * 
	 * @return �¼�${item.otherTable.cnname}�������
	 */
	  public USMListProxy getChild${table.className}() ;
	  public ${table.className}Proxy getParent${table.className}();
	 </#if> 
     </#list>
		
	
  }
	