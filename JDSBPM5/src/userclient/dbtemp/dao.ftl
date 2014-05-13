package ${package}.db.${tempbeanid}.database;

import java.io.Serializable;

import net.itjds.common.cache.CacheSizes;
import net.itjds.common.cache.Cacheable;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.j2ee.dao.annotation.DBField;
import net.itjds.j2ee.dao.annotation.DBTable;
import ${package}.*;
import net.itjds.usm2.constants.USMConstants;

import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;
import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.*;
import ${package}.db.${tempbeanid}.*;
import ${package}.db.${tempbeanid}.inner.*;


	@MapdaoBeanAnnotation(
		id = "${table.className}", 
		name = "获取${table.cnname}数据库映射对象", 
		expressionArr = "GetCurrData(\"${table.className}\",R(\"${table.className}.${table.pkFieldName}\"))", 
		desc = "获取${table.cnname}数据库映射对象", 
		dataType = "action"
    )	
   
	@DBTable(tableName = "${table.tableName}", primaryKey="${table.pkName}",cname="${table.cnname}" <#if usmbean.schema??>,schema="${usmbean.schema}" </#if>)
	public class ${table.className}DAO implements EI${table.className} , Cacheable, Serializable{
	
	   private static final Log log = LogFactory.getLog(USMConstants.CONFIG_KEY,
			${table.className}DAO.class);
		<#list table.colList as col>
		@DBField(dbFieldName = "${col.dbcol.name}",length = ${col.length},dbType="${col.dbcol.type}"<#if !col.dbcol.canNull>,isNull=false</#if>)
		private ${col.fieldtype} ${col.fieldName};
		private boolean ${col.fieldName}_is_modified = false;
		private boolean ${col.fieldName}_is_initialized = false;		
		
	   </#list>
	   	private boolean _isNew = true;
	   
	
		public boolean isNew() {
			return _isNew;
		}
	
		public void setIsNew(boolean isNew) {
			this._isNew = isNew;
		}
		   
   
	   	<#list table.colList as col>
	   	
	   	public boolean is${col.className}Modified() {
			return ${col.fieldName}_is_modified;
		}
	   	
		public boolean is${col.className}Initialized() {
			return ${col.fieldName}_is_initialized;
		}
	   	
	   	@MethodChinaName(cname="${col.cnname}")
		public ${col.fieldtype} get${col.className}(){
		   return ${col.fieldName}; 
		}
		public void set${col.className}(${col.fieldtype}  newVal){
			if ((newVal != null && newVal.equals(this.${col.fieldName}) == true)
					|| (newVal == null && this.${col.fieldName} == null))
				return;
			this.${col.fieldName} = newVal;
			${col.fieldName}_is_modified = true;
			${col.fieldName}_is_initialized = true;
		}
	    </#list>
	   
	    public boolean isModified() {
	      return
	   <#list table.colList as col>
	      ${col.fieldName}_is_modified||
	      <#if !col_has_next>${col.fieldName}_is_modified;</#if>
	   </#list>
	   }
	   
	   public void resetIsModified() {
	   <#list table.colList as col>
		 ${col.fieldName}_is_modified = false;
		 </#list>
	   }
	   
	   public void copy(${table.className}DAO bean) {
		<#list table.colList as col>
			 set${col.className}(bean.get${col.className}());
			 </#list>
		}
		
	 public int getCachedSize() {
		int size = 0;
	<#list table.colList as col>
		size += CacheSizes.sizeOfObject(${col.fieldName});
	</#list>
	
		return size;
	}
	
	public String toString() {
	return "\n[${table.className}] "
	<#list table.colList as col>
				+ "\n - ${table.tableName}.${col.dbUpName} = "
				+ (${col.fieldName}_is_initialized ? ("["
						+ ${col.fieldName}.toString() + "]") : "not initialized")
				+ ""
	</#list>	;		
	}
							
}

