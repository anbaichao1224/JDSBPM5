package net.itjds.usm2.define.cartlayoutpanel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.define.annotation.SelectItem;
import net.itjds.usm2.define.cartlayoutpanel.mapping.CartPanelBean;

import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ClassMappingAnnotation(clazz=CartPanelBean.class)
public @interface CartPanelDefine {
	@MethodChinaName(cname = "����")
	String xtype() default "cart";
	
	@MethodChinaName(cname = "���")
	double width() default 800;

	@MethodChinaName(cname = "�߶�")
	double height() default 600;
	
	@MethodChinaName(cname = "����Ĭ�ϼ���ҳ")
	int activeItem() default 0;
	
	@MethodChinaName(cname = "��ǩ����ʱ�Զ����ֹ�����ť")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean enableTabScroll() default true;
	
	@MethodChinaName(cname = "�ֶ�����")
	String[] fieldsIndex()default{}; 
	@MethodChinaName(cname = "Ĭ�ϴ���ģ��")
	String ftlUrl()default "cart.ftl";
}
