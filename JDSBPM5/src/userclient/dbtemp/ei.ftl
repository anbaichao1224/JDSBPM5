package ${package}.db.${tempbeanid}.inner;
import net.itjds.usm2.*;
import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.j2ee.dao.MethodExpressionChinaName;

/**
 * <p>
 * Title: USM
 * </p>
 * <p>
 * Description: ${table.tableName}�ӿ�
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
public interface EI${table.className} extends Usm{
	<#list table.colList as col>
	/**
	 * ��ȡ${col.cnname}
	 * 
	 * @return ${col.cnname}
	 */
	@MethodChinaName(cname = "��ȡ${col.cnname}")
	public ${col.fieldtype} get${col.className}();
	public void set${col.className}(${col.fieldtype}  newVal);
	</#list>

}
	