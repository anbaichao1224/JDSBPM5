package net.itjds.usm2.define.form.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.define.annotation.SelectItem;
import net.itjds.usm2.define.data.annotation.AjaxDataDefine;
import net.itjds.usm2.define.enums.ElementAlign;
import net.itjds.usm2.define.enums.ElementButton;
import net.itjds.usm2.define.form.mapping.FormPanelBean;
import net.itjds.usm2.define.grid.annotation.SearchItemDefine;


import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ClassMappingAnnotation(clazz=FormPanelBean.class)
public @interface FormPanelDefine {
	
	@MethodChinaName(cname = "����Buttons��ť�Ķ��뷽ʽ")
	@SelectItem(values={"left","center","right"},texts={"��","��","��"})
	ElementAlign buttonAlign() default ElementAlign.center;
	
	Class panelBean()default FormPanelBean.class;
	
	@MethodChinaName(cname="��ť����")
	ElementButton[] buttons() default ElementButton.none;
	
	@MethodChinaName(cname = "�Ƿ���ʾ��ҳ��")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean footer() default false;
	
	@MethodChinaName(cname = "�Ƿ񴴽���������")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean header() default false;
	
	@MethodChinaName(cname = "�ڱ������Ƿ���ʾtitle")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean headerAsText() default true;
	@MethodChinaName(cname = "�Ƿ��ϴ��ļ�")
	boolean fileUpload() default false;
	
	
	
	@MethodChinaName(cname = "����ǩ�Ķ��뷽ʽ")
	@SelectItem(values={"left","center","right"},texts={"��","��","��"})
	ElementAlign labelAlign() default ElementAlign.left;
	
	@MethodChinaName(cname = "����ǩ�Ŀ��")
	int labelWidth();
	
	@MethodChinaName(cname = "form�Ŀ��")
	int  width() default 800;
	int  height() default 600;
	@MethodChinaName(cname = "������")
	String title() default "";
	
	@MethodChinaName(cname = "Ĭ�ϴ���ģ��")
	String ftlUrl()default "form.ftl";
	
	@MethodChinaName(cname = "form�ϼ�ITEM")
	SearchItemDefine[] searchBeans() default {} ;
	@MethodChinaName(cname = "������Դ")
	AjaxDataDefine loadData() default @AjaxDataDefine(
			url="/expression.jsp?expression=$JSONForm($currFormView)"
	); 
	@MethodChinaName(cname = "���洦��")
	AjaxDataDefine updateData() default @AjaxDataDefine(
			url="update.action?expression=$UpdateCurrForm"
	);

	@MethodChinaName(cname = "�ֶ�����")
	String[] fieldsIndex()default{}; 
}
