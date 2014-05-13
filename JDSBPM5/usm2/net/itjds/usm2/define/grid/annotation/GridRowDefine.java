package net.itjds.usm2.define.grid.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


import net.itjds.j2ee.dao.MethodChinaName;


import net.itjds.usm2.define.grid.mapping.GridRowDefineBean;


import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ClassMappingAnnotation(clazz=GridRowDefineBean.class)
public @interface GridRowDefine {
	@MethodChinaName(cname = "行高")
	int height() default 20;


	@MethodChinaName(cname = "行")
	MultiGridHeaderDefine[] rows() default{};
	
	@MethodChinaName(cname = "是否合计行")
	String  isSumRow() default "false";



}
