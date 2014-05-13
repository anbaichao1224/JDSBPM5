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
	@MethodChinaName(cname = "����")
	String header() default "";
	
	@MethodChinaName(cname = "mapping")
	String mapping() default "";

	@MethodChinaName(cname = "�������ݼ�¼�Ķ�Ӧ��ϵ")
	String dataIndex() default "";

	@MethodChinaName(cname = "���뷽ʽ")
	@SelectItem(values = { "left", "center", "right" }, texts = { "��", "��", "��" })
	ElementAlign align() default ElementAlign.center;
	
	@MethodChinaName(cname = "�кϲ�")
	int colspan() default 1;
	
	@MethodChinaName(cname = "����")
	String expression() default "";
	
	
	@MethodChinaName(cname = "�кϲ�")
	int rowspan() default 1;


}
