package net.itjds.usm2.define.tabpanel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.define.annotation.SelectItem;
import net.itjds.usm2.define.tabpanel.mapping.TabPanelBean;

import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ClassMappingAnnotation(clazz=TabPanelBean.class)
public @interface TabPanelDefine {
	@MethodChinaName(cname = "����")
	String xtype() default "";
	
	@MethodChinaName(cname = "���")
	int width() default 800;

	@MethodChinaName(cname = "�߶�")
	int height() default 600;
	
	@MethodChinaName(cname = "����Ĭ�ϼ���ҳ")
	int activeTab() default 0;
	
	@MethodChinaName(cname = "��ǩ����ʱ�Զ����ֹ�����ť")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean enableTabScroll() default true;
	
	@MethodChinaName(cname = "Ĭ�ϴ���ģ��")
	String ftlUrl()default "tab.ftl";
	@MethodChinaName(cname = "����ֵ")
	String servicekey();	
	@MethodChinaName(cname = "�ֶ�����")
	String[] fieldsIndex()default{}; 
}
