package ${package}.db.${tempbeanid};
import java.util.List;

public interface ${tempbean.id}Service {
   
	<#list tableList as table>
	public ${table.className} get${table.className}(String pkName) ;
	public List<${table.className}> get${table.className}ByWhere(String where);
	</#list> 


}