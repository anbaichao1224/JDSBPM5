package ${package}.db.${tempbeanid}.impl;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.usm2.db.*;
import ${package}.db.${tempbeanid}.*;
import ${package}.*;
import net.itjds.usm2.*;
import ${package}.db.${tempbeanid}.database.*;
import ${package}.db.${tempbeanid}.proxy.*;
import ${package}.db.${tempbeanid}.inner.*;
import java.util.List;

@MapdaoBeanAnnotation(id="${tempbean.id}",
		name="${tempbean.name}",
		expressionArr="${tempbean.id}ServiceImpl()",
		desc="${tempbean.desc} by DAOTools ",
		<#if tempbean.flowType??>
		flowType="${tempbean.flowType}",
		</#if>
		dataType="${tempbean.dataType}"
		)
public class ${tempbean.id}ServiceImpl  implements ${tempbean.id}Service{
    <#list tableList as table>
    EI${table.className}Manager ei${table.className}Manager=Db${table.className}Manager.getInstance();
    </#list> 

	 <#list tableList as table>
	 
	 public ${table.className} get${table.className}(String pkName) {
		EI${table.className} ei${table.className}=null;
		try {
			ei${table.className} = ei${table.className}Manager.loadByKey(pkName);
		} catch (USMException e) {
			e.printStackTrace();
		}
		return new ${table.className}Proxy(ei${table.className});
	}
	
	public List<${table.className}> get${table.className}ByWhere(String where){
	
		List<EI${table.className}> ei${table.className}s =null;
		
		try {
			ei${table.className}s = ei${table.className}Manager.loadByWhere(where);
		} catch (USMException e) {
			e.printStackTrace();
		}
		return new USMListProxy(ei${table.className}s,${table.className}Proxy.class);
	}
	

	</#list> 

}