package net.itjds.usm2.define.tree.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.define.annotation.ButtonDefine;
import net.itjds.usm2.define.annotation.SelectItem;
import net.itjds.usm2.define.data.annotation.AjaxDataDefine;
import net.itjds.usm2.define.enums.ElementButton;
import net.itjds.usm2.define.enums.ElementCheckModel;
import net.itjds.usm2.define.tree.mapping.TreePanelBean;


import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ClassMappingAnnotation(clazz=TreePanelBean.class)
public @interface TreePanelDefine {
	@MethodChinaName(cname = "����")
	String xtype() default "";
	
	@MethodChinaName(cname = "��������")
	String name() default "";
	
	@MethodChinaName(cname = "����Ч��")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean animate() default false;

	@MethodChinaName(cname = "����")
	String title() default "";

	@MethodChinaName(cname = "��ʾ����")
	String el() default "";

	@MethodChinaName(cname = "��ʽ")
	String bodyStyle() default "";

	@MethodChinaName(cname = "���")
	int width() default 0;

	@MethodChinaName(cname = "�߶�")
	int height() default 0;

	@MethodChinaName(cname = "ָʾ��")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean lines() default true;

	@MethodChinaName(cname = "�߿�")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean border() default false;

	@MethodChinaName(cname = "��ѡģʽ")
	@SelectItem(values = { "multiple", "single", "cascade" }, texts = { "��ѡ","��ѡ", "������ѡ" })
	ElementCheckModel checkModel() default ElementCheckModel.single;

	@MethodChinaName(cname = "�ڵ�ɹ�ѡ")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean onlyLeafCheckable() default false;

	@MethodChinaName(cname = "��ʾ���ڵ�")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean rootVisible() default false;

	@MethodChinaName(cname = "�Զ���ʾ������")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean autoScroll() default true;

	@MethodChinaName(cname = "�ڵ������")
	String loader() default "loader";

	@MethodChinaName(cname = "���ڵ�")
	String root() default "root";
	@MethodChinaName(cname = "���ݼ���")
	TreeLoaderDefine treeLoaderBean();
	@MethodChinaName(cname = "���ݽڵ�")
	TreeNodeDefine treeNodeBean();
	
	TreeListener[] listeners();
	@MethodChinaName(cname = "Ĭ�ϴ���ģ��")
	String ftlUrl()default "tree.ftl";
	
	@MethodChinaName(cname = "������ť")
	ButtonDefine[] buttons() default {};
	
	@MethodChinaName(cname = "�޸Ĳ���")
	AjaxDataDefine updateData()default @AjaxDataDefine(url="");
	
	@MethodChinaName(cname = "ɾ������")
	AjaxDataDefine deleteData()default @AjaxDataDefine(url="");
}
