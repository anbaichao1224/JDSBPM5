package ${package}.db.${tempbeanid}.impl;

import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;

import java.util.Map;
import java.util.List;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;

import net.itjds.usm2.*;
import net.itjds.usm2.constants.*;
import ${package}.db.${tempbeanid}.inner.*;
import ${package}.db.${tempbeanid}.database.*;
import ${package}.db.${tempbeanid}.proxy.*;
import net.itjds.usm2.db.*;
import net.itjds.usm2.db.util.*;
import ${package}.db.${tempbeanid}.*;
<#list tableList as item>		  

import ${package}.db.${item.refTable.className?lower_case}.inner.*;


import ${package}.db.${item.otherTable.className?lower_case}.*;
import ${package}.db.${item.otherTable.className?lower_case}.inner.*;
import ${package}.db.${item.otherTable.className?lower_case}.impl.*;
import ${package}.db.${item.otherTable.className?lower_case}.proxy.*;
import ${package}.db.${item.otherTable.className?lower_case}.database.*;
</#list>
/**
 * <p>
 * Title: USM
 * </p>
 * <p>
 * Description: ${table.tableName}
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
	@MapdaoBeanAnnotation(id="${table.className}UsmService",
		name="${table.fieldName}",
		expressionArr="${table.className}UsmServiceImpl()",
		desc="${table.cnname} by DAOTools ",
		dataType="action"
		)	
    public class ${table.className}UsmServiceImpl  implements UsmService{

    private static final Log log = LogFactory.getLog(USMConstants.CONFIG_KEY,
			${table.className}UsmServiceImpl.class);
			
	private static Class defaultProxyClass= ${table.className}Proxy.class;
		
    private static EI${table.className}Manager  singleton = new Db${table.className}Manager() ;
    
    <#list tableList as item>
    private static EI${item.refTable.className}Manager ${item.refTable.fieldName}Manager = EI${item.refTable.className}Manager.getInstance();
    </#list>
	
	
	/**
	 * 级联删除
	 * 删除的关联表[
	 <#list tableList as item>
	 *	${item.refTable.cnname}-${item.refTable.tableName}
	 </#list>]
	 *级联删除实体表[
	 <#list tableList as item>
	 *	${item.otherTable.cnname}-${item.otherTable.tableName}
	 </#list>]
	 * @return 删除的记录数
	 */
	public int delete(UsmProxy pObject){
		int result = 0;
		try {
			
			result += singleton.delete((EI${table.className})pObject.getDAO());
			
			${table.className} ${table.fieldName} = (${table.className})pObject;
		<#list tableList as item>
	    <#if item.otherTable.className!=table.className>
	        <#if item.refTable.className!=item.otherTable.className>
	        
	        //删除关系表${item.refTable.cnname}-${item.refTable.tableName}
	        result +=  ${item.refTable.fieldName}Manager.deleteByWhere("where ${table.pkName}='"+${table.fieldName}.get${table.pkClassName}()+"'");
			
			//删除级联数据${item.otherTable.cnname}-${item.otherTable.tableName}
			${item.otherTable.className}UsmServiceImpl ${item.otherTable.fieldName}UsmService=(${item.otherTable.className}UsmServiceImpl) EsbUtil.parExpression("$${item.otherTable.className}UsmService");
			USMListProxy ${item.otherTable.className}s = ${table.fieldName}.get${item.otherTable.className}s();
			for(int k=0;k<${item.otherTable.className}s.size();k++){
				${item.otherTable.className} ${item.otherTable.className?uncap_first}=(${item.otherTable.className}) ${item.otherTable.className}s.get(k,${item.otherTable.className}Proxy.class);
				result += ${item.otherTable.fieldName}UsmService.delete(${item.otherTable.className?uncap_first});
			}	
			//删除扩展表
			</#if>
			 <#if item.refTable.className==item.otherTable.className>	
			  EI${item.otherTable.className}Manager ${item.otherTable.fieldName}Manager = EI${item.otherTable.className}Manager.getInstance();
			 ${item.otherTable.className} ${item.otherTable.fieldName} = ((${table.className})${table.fieldName}).get${item.otherTable.className}();
			 ${item.otherTable.fieldName}Manager.delete((EI${item.otherTable.className})${item.otherTable.fieldName}.getDAO());
			 </#if>	
		</#if>		
		  <#if item.otherTable.className==table.className>
	      	${item.otherTable.className}UsmServiceImpl ${item.otherTable.fieldName}UsmService=(${item.otherTable.className}UsmServiceImpl) EsbUtil.parExpression("$${item.otherTable.className}UsmService");
			USMListProxy ${item.otherTable.className}s = ${table.className?uncap_first}.getChild${item.otherTable.className}();
			for(int k=0;k<${item.otherTable.className}s.size();k++){
				${item.refTable.className} child${item.otherTable.className?uncap_first}=(${item.otherTable.className}) ${item.otherTable.className}s.get(k,${item.otherTable.className}Proxy.class);
				result += ${item.otherTable.fieldName}UsmService.delete(child${item.otherTable.className?uncap_first});
			}			
		</#if>		
	  
		</#list>
		
		
			
		}catch(Exception e){
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
	
	/**
	 * 根据主键ID级联删除
	 * 
	 * @return 删除的记录数
	 * @throws USMException 
	 */
	public int deleteByKey(String uuid) throws USMException {
			return delete(getUsmProxyByKey(uuid));
	}
	
	/**
	 * 根据where条件级联删除
	 * 
	 * @return 删除的记录数
	 * @throws USMException 
	 */
	public int deleteByWhere(String where) throws USMException {
		   USMListProxy list=(USMListProxy) this.getUsmWhere(where);
	   int f=0;
		for(int k=0;k<list.size();k++){
			 ${table.className}Proxy proxy=(${table.className}Proxy) list.get(k, defaultProxyClass);
			f=f+delete(proxy);
		}
		return f;
	}
	
	/**
	 * 根据主键ID获取默认代理对象
	 *
	 * @param pkValue 主键值
	 * @return 代理对象
	 * @throws USMException 
	 */
	public ${table.className}Proxy getUsmProxyByKey(String pkValue) throws USMException{
	  EI${table.className}  ei${table.className}= singleton.loadByKey(pkValue);
		if (ei${table.className}==null){
		  ei${table.className}=singleton.create${table.className}(pkValue);
		}
		return new ${table.className}Proxy(ei${table.className});
	}

	/**
	 * 根据主键ID获取默认代理对象
	 *
	 * @param pkValue 主键值
	 * @param clazz 指定的视图代理对象类型
	 * @return 指定的视图代理对象类型
	 * @throws USMException 
	 */
	public Object getUsmByKey(String pkValue,Class clazz)throws USMException {
	    ${table.className}Proxy ${table.fieldName}Proxy=getUsmProxyByKey(pkValue);
		if (clazz==null){
		   clazz=EsbUtil.getCurrClass();
		}
		return ClassUtil.getUsmViewInstance(clazz, ${table.fieldName}Proxy);

	}
	
	/**
	 * 根据主键ID获取servicekey中默认代理对象
	 *
	 * @param pkValue 主键值
	 * @return servicekey中默认代理对象
	 * @throws USMException 
	 */
	public Object getUsmByKey(String pkValue)throws USMException {
		return getUsmByKey(pkValue, null);
	}
	
	
	/**
	 * 取得所有数据
	 * @return 取得所有数据记录集
	 */
    public  List loadAll() throws USMException{
      List usms  = singleton.loadAll();
	  return new USMListProxy(usms,this);
    }
	
	/**
	 * 根据where条件获取servicekey中默认代理对象记录集
	 *
	 * @param where where条件
	 * @return servicekey中默认代理对象记录集
	 * @throws USMException 
	 */
	public USMListProxy getUsmWhere(String where) throws USMException{
	   List usms  = singleton.loadByWhere(where);
	  return new USMListProxy(usms,this);
	}
	
	/**
	 * 根据where条件分页获取servicekey中默认代理对象记录集
	 *
	 * @param where where条件
	 * @param start start开始
	 * @param limit limit步长
	 * @return servicekey中默认代理对象记录集
	 * @throws USMException 
	 */
	public USMListProxy getUsmWhere(String where,long start, long limit) throws USMException{
	   List usms  =singleton.loadByWhere(where,start,limit);
	
		return new USMListProxy(usms,this);
	}

	/**
	 * 保存默认${table.cnname}代理对象
	 * 对1:N关系实行级联保存
	 * @param pObject 默认${table.cnname}代理对象
	 * 级联保存
	<#list tableList as item>
	 <#if item.otherTable.className!=table.className>
	 <#if item.refTable.className==item.otherTable.className>
	 *	${item.refTable.cnname}-${item.refTable.tableName}
	 </#if>	
	</#if>	
	</#list>	
	 * @throws USMException 
	 */
	public void save(UsmProxy ${table.fieldName}) throws USMException {
		EI${table.className} ei${table.className} =singleton.save((EI${table.className})${table.fieldName}.getDAO());
		<#list tableList as item>
	       <#if item.otherTable.className!=table.className>
			 <#if item.refTable.className==item.otherTable.className>	
			 EI${item.otherTable.className}Manager ${item.otherTable.fieldName}Manager = EI${item.otherTable.className}Manager.getInstance();
			 ${item.otherTable.className}UsmServiceImpl ${item.otherTable.fieldName}UsmService=(${item.otherTable.className}UsmServiceImpl) EsbUtil.parExpression("$${item.otherTable.className}UsmService");
			 ${item.otherTable.className} ${item.otherTable.fieldName} = ((${table.className})${table.fieldName}).get${item.otherTable.className}();
			 ${item.otherTable.fieldName}Manager.save((EI${item.otherTable.className})${item.otherTable.fieldName}.getDAO());
			</#if>	
		</#if>		
		</#list>
		
	}
	
	/**
	 * 保存默认${table.cnname}代理对象记录集
	 *
	 * @param pObject 默认${table.cnname}代理对象记录集
	 * @throws USMException 
	 */
	public List save(List<UsmProxy> pObjects) throws USMException {
		EI${table.className}[] usms=new EI${table.className}[pObjects.size()];
		for(int k=0;k<pObjects.size();k++){
		   ${table.className}Proxy proxy=(${table.className}Proxy)pObjects.get(k);
		   usms[k]=(EI${table.className})proxy.getDAO();
		}
	
		singleton.save(usms);
		return pObjects;
	}
	
		
    /**
	 * 创建一个默认的${table.cnname}代理对象
	 *
	 * @param 默认的${table.cnname}代理对象
	 * @throws USMException 
	 */
	public ${table.className}Proxy create() throws USMException {
		EI${table.className} ei${table.className}=singleton.create${table.className}();
	    return 	new ${table.className}Proxy(ei${table.className});
	}
	public Class getDefaultProxyClass() {
		return defaultProxyClass;
	}
	
	
	
}
		
