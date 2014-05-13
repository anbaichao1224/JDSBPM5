package net.itjds.usm2.define.data.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.define.data.mapping.WinConfigBean;

import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ClassMappingAnnotation(clazz=WinConfigBean.class)
public @interface WinConfigDefine {
	@MethodChinaName(cname = "����")
	String title() default "" ;
	@MethodChinaName(cname = "���")
	int width() default 800;
	@MethodChinaName(cname = "�߶�")
	int height() default 600;
	@MethodChinaName(cname = "����")
	String servicekey() default "";
	@MethodChinaName(cname = "�Ƿ�����С��")
	boolean maximizable() default true;
}
