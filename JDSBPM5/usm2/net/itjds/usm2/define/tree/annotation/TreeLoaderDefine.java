package net.itjds.usm2.define.tree.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.define.annotation.SelectItem;
import net.itjds.usm2.define.tree.mapping.TreeLoaderBean;

import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ClassMappingAnnotation(clazz=TreeLoaderBean.class)
public @interface TreeLoaderDefine {
	@MethodChinaName(cname = "TreeLoaderΨһ����")
	String name() default "";
	
	@MethodChinaName(cname = "�����ӽڵ�Ļ������Զ���")
	String baseAttrs() default "{uiProvider: Ext.tree.TreeCheckNodeUI}";
	
	@MethodChinaName(cname = "���û������������")
	String baseParams() default "";
	
	@MethodChinaName(cname = "��ȡ�ӽڵ��URL��ַ")
	String dataUrl() default "";
	
	@MethodChinaName(cname = "��ȡ�ӽڵ��URL��ַ")
	String url() default "";
	
	@MethodChinaName(cname = "���ü���ǰ�Ƿ��Ƴ��Ѵ��ڵ��ӽڵ�")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean clearOnload() default true;
	
	@MethodChinaName(cname = "�����ڵ�һ�μ����ӽڵ���Ƿ�ݹ���������ӽڵ�")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean preloadChildren() default false;
	
	@MethodChinaName(cname = "��������ʽ")
	String requestMethod() default "POST";
	
	@MethodChinaName(cname = "���ü����������ӽڵ��UIʵ����")
	String uiProviders() default "";
}
