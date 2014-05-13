package ${package}.db.${tempbeanid}.proxy;

import net.itjds.usm2.*;
import ${package}.*;
import ${package}.db.${tempbeanid}.*;
import ${package}.db.${tempbeanid}.proxy.*;
import ${package}.db.${tempbeanid}.inner.*;
import net.itjds.usm2.db.*;
import java.util.List;
import java.util.HashSet;
import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.db.util.EsbUtil;

<#list tableList as item>		  
import ${package}.db.${item.refTable.className?lower_case}.inner.*;
import ${package}.db.${item.otherTable.className?lower_case}.*;
import ${package}.db.${item.otherTable.className?lower_case}.impl.*;
import ${package}.db.${item.otherTable.className?lower_case}.proxy.*;
import ${package}.db.${item.otherTable.className?lower_case}.inner.*;

</#list>
	/**
	 * <p>
	 * Title: USM
	 * </p>
	 * <p>
	 * Description: ${table.cnname}代理类
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
	public class ${table.className}Proxy implements ${table.className}{
	 	
	 	EI${table.className} ei${table.className};
	 	
	 	<#list tableList as item>
	 	
	   <#if item.refTable.className!=item.otherTable.className>
	 	private USMListProxy ${item.otherTable.className?uncap_first}s;
	    boolean ${item.otherTable.className?uncap_first}s_is_initialized=false;
	     </#if>
	     
	      <#if item.otherTable.className==table.className>
	 	private USMListProxy ${item.otherTable.className?uncap_first}s;
	    boolean ${item.otherTable.className?uncap_first}s_is_initialized=false;
	     </#if>
	    </#list>
	 	
	 	/**
		 * 获取${table.cnname}代理对象
		 * 
		 * @return ${table.cnname}代理对象
		 */
	 	public  ${table.className}Proxy(EI${table.className} ei${table.className}){
	 	   this.ei${table.className}=ei${table.className};
	 	}
	 	
	 
	 	/**
		 * 获取${table.cnname}对象
		 * 
		 * @return ${table.cnname}对象
		 */
	 	public Usm getDAO() {
			return ei${table.className};
		}
		
		public void setDAO(Usm dao) {
		    this.ei${table.className}=(EI${table.className}) dao;
			
		};
	 	
	 	<#list table.colList as col>
	 	/**
		 * 获取${col.cnname}
		 * 
		 * @return ${col.cnname}
		 */
		@MethodChinaName(cname="${col.cnname}")
		public ${col.fieldtype} get${col.className}(){
		  return  this.ei${table.className}.get${col.className}();
		};
		public void set${col.className}(${col.fieldtype} ${col.fieldName}){
		      ei${table.className}.set${col.className}(${col.fieldName});
	    }
	    </#list>
	    
	  
		
		
		 <#list tableList as item>
		 
		 
	    <#if item.otherTable.className!=table.className >
	         <#if item.refTable.className!=item.otherTable.className>
	    /**
		 * 获取${item.otherTable.cnname}列表
		 * 
		 * @return ${item.otherTable.cnname}列表
		 */
	    public USMListProxy get${item.otherTable.className}s() {   
	    	if (!${item.otherTable.className?uncap_first}s_is_initialized){
	    	  ${item.otherTable.className}UsmServiceImpl service=(${item.otherTable.className}UsmServiceImpl) EsbUtil.parExpression("$${item.otherTable.className}UsmService");
	    	 try {
				 ${item.otherTable.className?uncap_first}s=	service.getUsmWhere("where ${item.otherTable.pkName} in(select ${item.otherTable.pkName} from ${item.refTable.tableName} where ${table.pkName}='"+this.get${table.pkClassName}()+"')");
			} catch (USMException e) {
				e.printStackTrace();
			}
	    	 
	    	}
	        return ${item.otherTable.className?uncap_first}s;   
	    }  
	    
	     /**
		 * 将${item.otherTable.cnname}添加到当前对象
		 * 并保存当前结果
		 * @return 结果信息
		 */
	  public boolean add${item.otherTable.className}(${item.otherTable.className} ${item.otherTable.fieldName}) {   
	    
	    EI${item.refTable.className}Manager ${item.refTable.fieldName}Manager = EI${item.refTable.className}Manager.getInstance();
	     ${item.otherTable.className}UsmServiceImpl ${item.otherTable.fieldName}service=(${item.otherTable.className}UsmServiceImpl) EsbUtil.parExpression("$${item.otherTable.className}UsmService");
	     try {
	      List refList= ${item.refTable.fieldName}Manager.loadByWhere("where  ${item.otherTable.pkName}='"+ ${item.otherTable.fieldName}.get${item.otherTable.pkClassName}()+"' and ${table.pkName}='"+this.get${table.pkClassName}()+"'");
	      if (refList.size()==0){
	          EI${item.refTable.className} ei${item.refTable.className} = ${item.refTable.fieldName}Manager.create${item.refTable.className}();
	          ei${item.refTable.className}.set${item.otherTable.pkClassName}(${item.otherTable.fieldName}.get${item.otherTable.pkClassName}());
	          ei${item.refTable.className}.set${table.pkClassName}(this.get${table.pkClassName}());
	         ${item.refTable.fieldName}Manager.save(ei${item.refTable.className});
	      }
			  ${item.otherTable.fieldName}service.save(${item.otherTable.fieldName});
			 
			} catch (USMException e) {
				e.printStackTrace();
				  return false;
			}
			return true;
		}  
		
	/**
	 * 更改关联关系将${item.otherTable.className}移入${table.className}
	 */
	  public boolean move${item.otherTable.className}In(List<String> ${item.otherTable.pkName?lower_case}s) {    
	    EI${item.refTable.className}Manager ${item.refTable.fieldName}Manager = EI${item.refTable.className}Manager.getInstance();
        for(int k=0;k<${item.otherTable.pkName?lower_case}s.size();k++){
		   String ${item.otherTable.pkName?lower_case}=${item.otherTable.pkName?lower_case}s.get(k);
			try {
				EI${item.refTable.className} ${item.refTable.fieldName}=(EI${item.refTable.className}) ${item.refTable.fieldName}Manager.loadByWhere("where  ${item.otherTable.pkName}='"+${item.otherTable.pkName?lower_case}+"'").get(0);
				${item.refTable.fieldName}.set${table.pkClassName}(this.get${table.pkClassName}());
				${item.refTable.fieldName}Manager.save(${item.refTable.fieldName});
			} catch (USMException e1) {
				e1.printStackTrace();
			    return false;
			}
		}
		return true;
	  }
	  
	  /**
	   * 删除${item.otherTable.className}与${table.className}关联关系
	   */
	  public boolean move${item.otherTable.className}Out(List<String> ${item.otherTable.pkName?lower_case}s) {    
	    EI${item.refTable.className}Manager ${item.refTable.fieldName}Manager = EI${item.refTable.className}Manager.getInstance();
        for(int k=0;k<${item.otherTable.pkName?lower_case}s.size();k++){
		   String ${item.otherTable.pkName?lower_case}=${item.otherTable.pkName?lower_case}s.get(k);
			try {
				${item.refTable.fieldName}Manager.deleteByWhere("where  ${item.otherTable.pkName}='"+ ${item.otherTable.pkName?lower_case}+"' and ${table.pkName}='"+this.get${table.pkClassName}()+"'");
			} catch (USMException e1) {
				e1.printStackTrace();
			    return false;
			}
		}
		return true;
	  }
	  
	         </#if>
	    
	    <#if item.refTable.className==item.otherTable.className>
		/**
		 * 获取${item.otherTable.cnname} 如果为空则创建一个新的对象返回
		 * 
		 * @return ${item.otherTable.cnname}
		 */
	    public ${item.otherTable.className} get${item.otherTable.className}() {   
	       ${item.otherTable.className} ${item.otherTable.fieldName} =null;
	    	  ${item.otherTable.className}UsmServiceImpl service=(${item.otherTable.className}UsmServiceImpl) EsbUtil.parExpression("$${item.otherTable.className}UsmService");
	    	 try {
				USMListProxy ${item.otherTable.className?uncap_first}s=	service.getUsmWhere("where ${table.pkName}='"+this.get${table.pkClassName}()+"'");
				if (${item.otherTable.className?uncap_first}s.size()>0){
				${item.otherTable.fieldName} =(${item.otherTable.className} )${item.otherTable.className?uncap_first}s.get(0,${item.otherTable.className}Proxy.class);
				}else{
				${item.otherTable.fieldName}=service.create();
				${item.otherTable.fieldName}.set${item.otherTable.pkClassName}(this.get${table.pkClassName}());
				}
				
			} catch (USMException e) {
				e.printStackTrace();
			}
			
	        return ${item.otherTable.fieldName};   
	    } 
	   
	    
		    </#if> 
	    
	    </#if> 
	
		</#list>
		
		
		   <#list tableList as item>
	    <#if item.otherTable.className==table.className>
	    
	    /**
		 * 添加${item.otherTable.cnname}到列表
		 * 
		 * @return 添加${item.otherTable.cnname}到列表
		 */
	    public boolean add${table.className}(${table.className} child${table.className}) {   
	               
	     ${item.otherTable.className}UsmServiceImpl service=(${item.otherTable.className}UsmServiceImpl) EsbUtil.parExpression("$${item.otherTable.className}UsmService");
	     child${table.className}.setParentid(this.get${table.pkClassName}());
	         try {
			   service.save(child${table.className});
				} catch (USMException e) {
					e.printStackTrace();
				return false;  
		    }
		    return true;
	    }  
	    
	    
	    
		/**
		 * 获取下级${item.otherTable.cnname}列表
		 * 
		 * @return 下级${item.otherTable.cnname}列表
		 */
	    public USMListProxy getChild${table.className}() {   
	    	if (!${item.otherTable.className?uncap_first}s_is_initialized){
	    	  ${item.otherTable.className}UsmServiceImpl service=(${item.otherTable.className}UsmServiceImpl) EsbUtil.parExpression("$${item.otherTable.className}UsmService");
		    	try {
					 ${item.otherTable.className?uncap_first}s=service.getUsmWhere("where PARENTID='"+this.get${table.pkClassName}()+"'");
				} catch (USMException e) {
					e.printStackTrace();
				}

	    	}
	        return ${item.otherTable.className?uncap_first}s;   
	    }  
	    
	    /**
		 * 获取父级${item.otherTable.cnname}列表
		 * 
		 * @return 父级${item.otherTable.cnname}列表
		 */
	     public ${table.className}Proxy getParent${table.className}() {   
	         ${item.otherTable.className}UsmServiceImpl service=(${item.otherTable.className}UsmServiceImpl) EsbUtil.parExpression("$${item.otherTable.className}UsmService");
	    	${table.className}Proxy ${item.otherTable.className?uncap_first}=null;
	    	try {
	    	  ${item.otherTable.className?uncap_first}=(${table.className}Proxy)service.getUsmProxyByKey(this.getParentid());
	    	} catch (USMException e) {
					e.printStackTrace();
			}
	    	 return ${item.otherTable.className?uncap_first};  
	     }
	
	    </#if> 	
		</#list>
		
		/**
		 * 获取主键值
		 * 
		 * @return 主键值
		 */
		 public String getPkValue(){
	        return get${table.pkClassName}();
		 }
		 
		 /**
		 * 获取总线中注册的servicekey值
		 * 
		 * @return 总线中注册的servicekey值
		 */
		 public String getServiceKey(){
		    return "${table.className}";
		 }
	
  }
	