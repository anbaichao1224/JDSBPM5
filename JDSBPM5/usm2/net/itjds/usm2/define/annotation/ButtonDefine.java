package net.itjds.usm2.define.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.define.data.annotation.AjaxDataDefine;
import net.itjds.usm2.define.enums.ElementButton;
import net.itjds.usm2.define.mapping.ButtonBean;


import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;

@Retention(RetentionPolicy.RUNTIME)
@ClassMappingAnnotation(clazz=ButtonBean.class)
public @interface ButtonDefine {
	
	@MethodChinaName(cname = "��ť��ʾ����")
	ElementButton text() default ElementButton.none;
	
	@MethodChinaName(cname = "��ť��ʾ����")
	String otherText() default "";
	
	@MethodChinaName(cname = "��ťͼ��")
	String iconCls() default "";
	
	@MethodChinaName(cname = "�Ƿ���ʾ")
	boolean hidden() default true;
	
	@MethodChinaName(cname = "����")
	String handler() default "";
	
	@MethodChinaName(cname = "Զ�̲���")
	AjaxDataDefine ajax() default @AjaxDataDefine(url = "");
	
}
