package net.itjds.usm2.define.data.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.define.data.mapping.HttpBaseParamsBean;

import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ClassMappingAnnotation(clazz=HttpBaseParamsBean.class)
public @interface HttpBaseParams {
	@MethodChinaName(cname = "参数名称")
	String name() ;
	@MethodChinaName(cname = "参数值")
	String value() default "";

}
