package net.itjds.usm2.define.grid.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.define.annotation.ButtonDefine;
import net.itjds.usm2.define.annotation.SelectItem;
import net.itjds.usm2.define.data.annotation.AjaxDataDefine;
import net.itjds.usm2.define.grid.mapping.GridPanelBean;

import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ClassMappingAnnotation(clazz = GridPanelBean.class)
public @interface GroupGridPanelDefine {
	@MethodChinaName(cname = "������")
	String title() default "";
	
	@MethodChinaName(cname = "�����ʽ")
	String bodyStyle() default "";
	
	@MethodChinaName(cname = "��������")
	AjaxDataDefine saveSort() default  @AjaxDataDefine(url="") ;
	
	@MethodChinaName(cname = "��������")
	AjaxDataDefine rebuild() default  @AjaxDataDefine(url="") ;
	
	@MethodChinaName(cname = "������ť")
	ButtonDefine[] buttons() default {};
	
	@MethodChinaName(cname = "�Զ��������δ�ÿռ����")
	String autoExpandColumn() default "";

	@MethodChinaName(cname = "�Զ������е������")
	int autoExpandMax() default 1000;

	@MethodChinaName(cname = "�Զ������е���С���")
	int autoExpandMin() default 50;

	@MethodChinaName(cname = "���ñ�����ģʽ")
	String cm() default "";

	@MethodChinaName(cname = "���ñ�����ģʽ")
	String colModel() default "";

	@MethodChinaName(cname = "���ñ��Ŀ��")
	double width() default 800;

	@MethodChinaName(cname = "���ñ��ĸ߶�")
	double height() default 600;

	@MethodChinaName(cname = "�Ƿ��ֹ���ѡ��")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean disableSelection() default false;

	@MethodChinaName(cname = "�Ƿ�����ͨ�������е������Ĳ˵�������")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean enableColumnHide() default true;

	@MethodChinaName(cname = "�Ƿ������Ϸű����")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean enableColumnMove() default true;

	@MethodChinaName(cname = "�Ƿ�����ı����п�")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean enableColumnResize() default true;

	@MethodChinaName(cname = "�Ƿ������Ϸű���е���")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean enalbeDragDrop() default false;

	@MethodChinaName(cname = "�Ƿ�������ʾ��ͷ�е������Ĳ˵�")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean enableHdMenu() default true;

	@MethodChinaName(cname = "�Ƿ�ͬ���и�")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean enableRowHeightSync() default true;

	@MethodChinaName(cname = "�������߶�")
	int maxHeight() default 50;

	@MethodChinaName(cname = "����е���С���")
	int minColumnWidth() default 25;

	@MethodChinaName(cname = "����ѡ��ģʽ")
	String sm() default "";// ����

	@MethodChinaName(cname = "����ѡ��ģʽ")
	String selModel() default "";

	@MethodChinaName(cname = "�������ݼ�")
	String store() default "store";
	
	
	 String groupField();

	@MethodChinaName(cname = "��ѯ����")
	SearchItemDefine[] searchBeans() default {};

	@MethodChinaName(cname = "����Ƿ���л�ɫ")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean stripeRows() default false;

	@MethodChinaName(cname = "�Ƿ������ʾ���������")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean trackMouseOver() default true;

	@MethodChinaName(cname = "Ĭ�ϴ���ģ��")
	String ftlUrl() default "groupgrid.ftl";
	
	AjaxDataDefine loadData() default @AjaxDataDefine(
			url="expression.jsp?expression=$JSONGroupGrid"
	); 
	
	AjaxDataDefine editRowData() default @AjaxDataDefine(
			url="expression.jsp?expression=$currFormPanel"
	); 
	AjaxDataDefine addRowData() default @AjaxDataDefine(
			url="expression.jsp?expression=$currFormPanel"
	); 
	AjaxDataDefine deleteRowData() default @AjaxDataDefine(
			url="expression.jsp?expression=$deleteGridRow"
	); 
	
	

	@MethodChinaName(cname = "�ֶ�����")
	String[] fieldsIndex()default{}; 
	@MethodChinaName(cname = "ÿҳ��ʾ����")
	int pageSize()default 20; 

}
