package ${package}.${tempbean.flowType?lower_case};

import java.sql.Timestamp;
import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.annotation.DBField;
import net.itjds.j2ee.dao.annotation.DBTable;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.common.mapdao.MapDAO;
import net.itjds.j2ee.dao.MethodChinaName;


@MapdaoBeanAnnotation(id="${tempbean.id}",
		name="${tempbean.name}",
		expressionArr="GetSampleMapdao(request,\"${tempbean.id}\")",
		desc="${tempbean.name} by DAOTools ",
		<#if tempbean.flowType??>
		flowType="${tempbean.flowType}",
		</#if>
		dataType="action"
		)
public class ${tempbean.id}MapDAO extends MapDAO {

<#list tableList as table>
	private ${table.className}DAO ${table.fieldName}DAO;

	@MethodChinaName(cname="${table.cnname}")
	public ${table.className}DAO get${table.className}DAO() {
		return ${table.fieldName}DAO; 
	}

	public void set${table.className}DAO (${table.className}DAO ${table.fieldName}DAO) {
		this.${table.fieldName}DAO = ${table.fieldName}DAO;
	}
	</#list> 
}