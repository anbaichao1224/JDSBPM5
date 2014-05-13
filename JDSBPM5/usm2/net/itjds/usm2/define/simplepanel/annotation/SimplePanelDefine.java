package net.itjds.usm2.define.simplepanel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.define.enums.ElementPanelLayout;
import net.itjds.usm2.define.simplepanel.mapping.SimplePanelBean;

import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ClassMappingAnnotation(clazz=SimplePanelBean.class)
public @interface SimplePanelDefine {
	@MethodChinaName(cname = "����")
	String title() default "";
	
	@MethodChinaName(cname = "���")
	int width() default 800;

	@MethodChinaName(cname = "�߶�")
	int height() default 600;	
	
	@MethodChinaName(cname = "�߶�")
	String html() default "";	
	
	@MethodChinaName(cname = "���ַ�ʽ")
	ElementPanelLayout layout() default ElementPanelLayout.fit;	
	
	@MethodChinaName(cname = "λ��")
	String  region();
	
	@MethodChinaName(cname = "Ĭ�ϴ���ģ��")
	String ftlUrl()default "simplepanel.ftl";
	@MethodChinaName(cname = "��ʽ")
	String bodyStyle() default "";	
	
}
