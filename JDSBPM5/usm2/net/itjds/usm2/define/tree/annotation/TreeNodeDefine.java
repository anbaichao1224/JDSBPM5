package net.itjds.usm2.define.tree.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.define.annotation.SelectItem;
import net.itjds.usm2.define.tree.mapping.TreeNodeBean;

import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ClassMappingAnnotation(clazz=TreeNodeBean.class)
public @interface TreeNodeDefine {
	
	@MethodChinaName(cname = "�����Ƿ�����ǰ�ڵ�����ӽڵ�")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean allowChildren() default true;
	
	@MethodChinaName(cname = "��ѯ����")
	String where() default "";
	
	@MethodChinaName(cname = "")
	String servicekey() default "";
	
	@MethodChinaName(cname = "���õ�ǰ�ڵ��ѡ��״̬")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean checked() default true;
	
	@MethodChinaName(cname = "��ӵ��ڵ����ʽ")
	String cls() default "";
	
	@MethodChinaName(cname = "�ڵ��Ƿ����")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean disabled() default false;
	
	@MethodChinaName(cname = "���õ������ӽڵ�ʱ�Ƿ���ʾ�Ӽ�ͼ��")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean expandable() default false;
	
	@MethodChinaName(cname = "�����Ƿ�չ���ڵ�")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean expanded() default false;
	
	@MethodChinaName(cname = "���ýڵ����������")
	String href() default "#";
	
	@MethodChinaName(cname = "���ýڵ�����ӵ�Ŀ����")
	String hrefTarget() default "";
	
	@MethodChinaName(cname = "���ýڵ�ͼ���Ӧ��·��")
	String icon() default "";
	
	@MethodChinaName(cname = "���ýڵ�ͼ�����ʽ")
	String iconCls() default "";
	
	@MethodChinaName(cname = "���ýڵ��ϵ���ʾ��Ϣ")
	String qtip() default "";
	
	@MethodChinaName(cname = "�����Ƿ�ͨ��������ʽչ���ڵ�")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean singleClickExpand() default true;
	
	@MethodChinaName(cname = "���ýڵ��ϵ��ı���Ϣ")
	String text() default "";
	
	@MethodChinaName(cname = "ID")
	String id() default "";
	@MethodChinaName(cname = "panelName")
	String panelName() default "";
	
	@MethodChinaName(cname = "URL��������")
	String parameterName() default "";
}
