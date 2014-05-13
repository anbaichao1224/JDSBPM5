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
	 * ����ɾ��
	 * ɾ���Ĺ�����[
	 <#list tableList as item>
	 *	${item.refTable.cnname}-${item.refTable.tableName}
	 </#list>]
	 *����ɾ��ʵ���[
	 <#list tableList as item>
	 *	${item.otherTable.cnname}-${item.otherTable.tableName}
	 </#list>]
	 * @return ɾ���ļ�¼��
	 */
	public int delete(UsmProxy pObject){
		int result = 0;
		try {
			
			result += singleton.delete((EI${table.className})pObject.getDAO());
			
			${table.className} ${table.fieldName} = (${table.className})pObject;
		<#list tableList as item>
	    <#if item.otherTable.className!=table.className>
	        <#if item.refTable.className!=item.otherTable.className>
	        
	        //ɾ����ϵ��${item.refTable.cnname}-${item.refTable.tableName}
	        result +=  ${item.refTable.fieldName}Manager.deleteByWhere("where ${table.pkName}='"+${table.fieldName}.get${table.pkClassName}()+"'");
			
			//ɾ����������${item.otherTable.cnname}-${item.otherTable.tableName}
			${item.otherTable.className}UsmServiceImpl ${item.otherTable.fieldName}UsmService=(${item.otherTable.className}UsmServiceImpl) EsbUtil.parExpression("$${item.otherTable.className}UsmService");
			USMListProxy ${item.otherTable.className}s = ${table.fieldName}.get${item.otherTable.className}s();
			for(int k=0;k<${item.otherTable.className}s.size();k++){
				${item.otherTable.className} ${item.otherTable.className?uncap_first}=(${item.otherTable.className}) ${item.otherTable.className}s.get(k,${item.otherTable.className}Proxy.class);
				result += ${item.otherTable.fieldName}UsmService.delete(${item.otherTable.className?uncap_first});
			}	
			//ɾ����չ��
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
	 * ��������ID����ɾ��
	 * 
	 * @return ɾ���ļ�¼��
	 * @throws USMException 
	 */
	public int deleteByKey(String uuid) throws USMException {
			return delete(getUsmProxyByKey(uuid));
	}
	
	/**
	 * ����where��������ɾ��
	 * 
	 * @return ɾ���ļ�¼��
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
	 * ��������ID��ȡĬ�ϴ������
	 *
	 * @param pkValue ����ֵ
	 * @return �������
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
	 * ��������ID��ȡĬ�ϴ������
	 *
	 * @param pkValue ����ֵ
	 * @param clazz ָ������ͼ�����������
	 * @return ָ������ͼ�����������
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
	 * ��������ID��ȡservicekey��Ĭ�ϴ������
	 *
	 * @param pkValue ����ֵ
	 * @return servicekey��Ĭ�ϴ������
	 * @throws USMException 
	 */
	public Object getUsmByKey(String pkValue)throws USMException {
		return getUsmByKey(pkValue, null);
	}
	
	
	/**
	 * ȡ����������
	 * @return ȡ���������ݼ�¼��
	 */
    public  List loadAll() throws USMException{
      List usms  = singleton.loadAll();
	  return new USMListProxy(usms,this);
    }
	
	/**
	 * ����where������ȡservicekey��Ĭ�ϴ�������¼��
	 *
	 * @param where where����
	 * @return servicekey��Ĭ�ϴ�������¼��
	 * @throws USMException 
	 */
	public USMListProxy getUsmWhere(String where) throws USMException{
	   List usms  = singleton.loadByWhere(where);
	  return new USMListProxy(usms,this);
	}
	
	/**
	 * ����where������ҳ��ȡservicekey��Ĭ�ϴ�������¼��
	 *
	 * @param where where����
	 * @param start start��ʼ
	 * @param limit limit����
	 * @return servicekey��Ĭ�ϴ�������¼��
	 * @throws USMException 
	 */
	public USMListProxy getUsmWhere(String where,long start, long limit) throws USMException{
	   List usms  =singleton.loadByWhere(where,start,limit);
	
		return new USMListProxy(usms,this);
	}

	/**
	 * ����Ĭ��${table.cnname}�������
	 * ��1:N��ϵʵ�м�������
	 * @param pObject Ĭ��${table.cnname}�������
	 * ��������
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
	 * ����Ĭ��${table.cnname}��������¼��
	 *
	 * @param pObject Ĭ��${table.cnname}��������¼��
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
	 * ����һ��Ĭ�ϵ�${table.cnname}�������
	 *
	 * @param Ĭ�ϵ�${table.cnname}�������
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
		
