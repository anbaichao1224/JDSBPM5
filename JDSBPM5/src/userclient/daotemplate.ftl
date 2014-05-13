package ${package}.${tempbean.flowType?lower_case};

import java.sql.Timestamp;
import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.annotation.DBField;
import net.itjds.j2ee.dao.annotation.DBTable;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.common.mapdao.MapDAO;
import net.itjds.j2ee.dao.MethodChinaName;

    
	@DBTable(tableName = "${table.tableName}", primaryKey="${table.pkName}",cname="${table.cnname}" <#if tempbean.schema??>,schema="${tempbean.schema}" </#if>)
	public class ${table.className}DAO extends DAO {
	
		public ${table.className}DAO () {
		   super();
	    }
	    public ${table.className}DAO (Connection conn) {
		   super(conn);
	    }
		<#list table.colList as col>
		
		@DBField(dbFieldName = "${col.dbcol.name}",length = ${col.length},dbType="${col.dbcol.type}"<#if !col.dbcol.canNull>,isNull=false</#if>)
		private ${col.fieldtype} ${col.fieldName};
	   </#list>
   
	   	<#list table.colList as col>
		@MethodChinaName(cname="${col.cnname}")
		public ${col.fieldtype} get${col.className}(){
		   return ${col.fieldName}; 
		}
		public void set${col.className}(${col.fieldtype}  newVal){
			this.${col.fieldName} = newVal;
			
		}
	    </#list>


	}
	