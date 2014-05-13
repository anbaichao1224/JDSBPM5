package net.itjds.usm2.define.grid.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.define.annotation.SelectItem;
import net.itjds.usm2.define.enums.ElementAlign;
import net.itjds.usm2.define.enums.ElementFieldType;
import net.itjds.usm2.define.grid.mapping.ColumnBean;


import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ClassMappingAnnotation(clazz=ColumnBean.class)
public @interface ColumnDefine {
	@MethodChinaName(cname = "列名")
	String header() default "字段";

	@MethodChinaName(cname = "列与数据记录的对应关系")
	String dataIndex() default "";
	
	@MethodChinaName(cname = "映射字段")
	String mapping();

	@MethodChinaName(cname = "类型")
	ElementFieldType type() default ElementFieldType.TextField;

	@MethodChinaName(cname = "宽度")
	int width() default 80;

	@MethodChinaName(cname = "设置该字段为查询")
	SearchItemDefine search() default @SearchItemDefine();

	@MethodChinaName(cname = "是否查询条件")
	boolean searchField() default false;
	
	@MethodChinaName(cname = "是否排序")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean sortable() default true;

	@MethodChinaName(cname = "是否隐藏")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean hidden() default false;

	@MethodChinaName(cname = "提示")
	String tooltip() default "";

	@MethodChinaName(cname = "对齐方式")
	@SelectItem(values = { "left", "center", "right" }, texts = { "左", "中", "右" })
	ElementAlign align() default ElementAlign.left;

	@MethodChinaName(cname = "业务对象")
	String mapdao() default "";

	@MethodChinaName(cname = "", display = false)
	String renderer() default "";
	
	@MethodChinaName(cname = "输入栏的类型")
	//radio, text, password, file 
	String inputType() default "text";
	
	@MethodChinaName(cname = "表名")
	String tableName() default "";
	
	@MethodChinaName(cname = "列名")
	String columnName() default "";
}
