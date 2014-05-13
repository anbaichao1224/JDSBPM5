package net.itjds.usm2.define.grid.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.define.annotation.SelectItem;
import net.itjds.usm2.define.enums.ElementAlign;

import net.itjds.usm2.define.grid.mapping.GridRowDefineBean;
import net.itjds.usm2.define.grid.mapping.MultiGridHeaderDefineBean;


import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ClassMappingAnnotation(clazz=MultiGridHeaderDefineBean.class)
public @interface MultiGridHeaderDefine {
	@MethodChinaName(cname = "列名")
	String header() default "";
	
	@MethodChinaName(cname = "mapping")
	String mapping() default "";

	@MethodChinaName(cname = "列与数据记录的对应关系")
	String dataIndex() default "";

	@MethodChinaName(cname = "对齐方式")
	@SelectItem(values = { "left", "center", "right" }, texts = { "左", "中", "右" })
	ElementAlign align() default ElementAlign.center;
	
	@MethodChinaName(cname = "列合并")
	int colspan() default 1;
	
	@MethodChinaName(cname = "列名")
	String expression() default "";
	
	
	@MethodChinaName(cname = "行合并")
	int rowspan() default 1;


}
