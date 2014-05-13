package net.itjds.usm2.define.grid.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.define.annotation.SelectItem;
import net.itjds.usm2.define.enums.ElementAlign;
import net.itjds.usm2.define.enums.ElementFieldType;
import net.itjds.usm2.define.grid.mapping.ColumnBean;


import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ClassMappingAnnotation(clazz=ColumnBean.class)
public @interface ColumnDefine {
	@MethodChinaName(cname = "����")
	String header() default "�ֶ�";

	@MethodChinaName(cname = "�������ݼ�¼�Ķ�Ӧ��ϵ")
	String dataIndex() default "";
	
	@MethodChinaName(cname = "ӳ���ֶ�")
	String mapping();

	@MethodChinaName(cname = "����")
	ElementFieldType type() default ElementFieldType.TextField;

	@MethodChinaName(cname = "���")
	int width() default 80;

	@MethodChinaName(cname = "���ø��ֶ�Ϊ��ѯ")
	SearchItemDefine search() default @SearchItemDefine();

	@MethodChinaName(cname = "�Ƿ��ѯ����")
	boolean searchField() default false;
	
	@MethodChinaName(cname = "�Ƿ�����")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean sortable() default true;

	@MethodChinaName(cname = "�Ƿ�����")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean hidden() default false;

	@MethodChinaName(cname = "��ʾ")
	String tooltip() default "";

	@MethodChinaName(cname = "���뷽ʽ")
	@SelectItem(values = { "left", "center", "right" }, texts = { "��", "��", "��" })
	ElementAlign align() default ElementAlign.left;

	@MethodChinaName(cname = "ҵ�����")
	String mapdao() default "";

	@MethodChinaName(cname = "", display = false)
	String renderer() default "";
	
	@MethodChinaName(cname = "������������")
	//radio, text, password, file 
	String inputType() default "text";
	
	@MethodChinaName(cname = "����")
	String tableName() default "";
	
	@MethodChinaName(cname = "����")
	String columnName() default "";
}
