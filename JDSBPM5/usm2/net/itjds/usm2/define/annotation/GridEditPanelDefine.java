package net.itjds.usm2.define.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.define.mapping.PanelBean;

import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ClassMappingAnnotation(clazz=PanelBean.class)
public @interface GridEditPanelDefine {
	@MethodChinaName(cname = "����")
	String title() default "";
	
	@MethodChinaName(cname = "���")
	double width() default 800;

	@MethodChinaName(cname = "�߶�")
	double height() default 600;	
	
	
	@MethodChinaName(cname = "Ĭ�ϴ���ģ��")
	String getFtlUrl()default "panel.ftl";

}
