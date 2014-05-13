package net.itjds.usm2.define.grid.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.define.annotation.SelectItem;
import net.itjds.usm2.define.enums.ElementAlign;
import net.itjds.usm2.define.enums.ElementFieldType;
import net.itjds.usm2.define.enums.ElementItem;
import net.itjds.usm2.define.enums.ElementVtype;
import net.itjds.usm2.define.grid.mapping.SearchItemBean;

import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;

@Retention(RetentionPolicy.RUNTIME)
@ClassMappingAnnotation(clazz=SearchItemBean.class)
public @interface SearchItemDefine {

	@MethodChinaName(cname = "�ؼ�����")
	ElementFieldType xtype() default ElementFieldType.TextField;
	
	@MethodChinaName(cname = "����У��")
	ElementVtype vtype()default  ElementVtype.none;//��Ҫ����һ������У����
	
	@MethodChinaName(cname = "�����ύ����̨������")
	String name() default "";
	
	@MethodChinaName(cname = "�ؼ���Ψһ��ʾ")
	String id() default "";
	
	@MethodChinaName(cname = "�ؼ��ĳ�ʼ��ֵ")
	String value() default ""; 
	
	@MethodChinaName(cname = "�Ƿ�ֻ��")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean readOnly() default false;
	
	@MethodChinaName(cname = "�ؼ���ʧȥ����ʱ�Ƿ���֤")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean validateOnBlur() default true;
	
	@MethodChinaName(cname = "��֤����ʱʱ��(����)")
	int validationDelay() default 250;
	
	@MethodChinaName(cname = "�ؼ��Ƿ񲻿���")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean disabled() default false;
	
	@MethodChinaName(cname = "�ֶζ�Ӧ�ı�ǩ˵��")
	String fieldLabel() default "";
	
	@MethodChinaName(cname = "�Ƿ����ر�ǩ˵��")
	boolean hideLabel() default false;
	
	@MethodChinaName(cname = "�������ʱ�Ƿ�����������ڵ�����")
	@SelectItem(values = { "true", "false" }, texts = { "��", "��" })
	boolean hidden() default true;
	
	@MethodChinaName(cname = "�ֶα�ǩ���ֶ�֮��ķָ���")
	String labelSeparator() default ":";
	
	@MethodChinaName(cname = "�Ƿ�����Ϊ��")
	boolean allowBlank() default true;
	
	@MethodChinaName(cname = "�������")
	int  maxLength() default 200;
	@MethodChinaName(cname = "store����")
	String store() default "";
	@MethodChinaName(cname = "��ѡ��ǩ")
	String boxLabel() default "";
	@MethodChinaName(cname = "�в���")
//columnֵ��column1Ϊ��һ�У�column2Ϊ�ڶ��У�
	String column() default "test";
	@MethodChinaName(cname = "�������ֵ")
	int maxValue() default 1000;
	@MethodChinaName(cname = "������Сֵ")
	int minLength() default 0;
	@MethodChinaName(cname = "")
	ElementItem item() default ElementItem.none ;
	
	@MethodChinaName(cname = "����ģ��")
	//remote,local;
	String model() default "";
	
	@MethodChinaName(cname = "ͼƬ��ַ")
	String src() default "/usm/images/jds.jpg";
	
	@MethodChinaName(cname = "������֤")
	String regex() default "none";
	
	@MethodChinaName(cname = "������֤������ʾ��Ϣ")
	String regexText() default "��������д����";
	
	@MethodChinaName(cname = "�ؼ����")
	int width() default 120;
	
	@MethodChinaName(cname = "�п��")
	String columnWidth() default "0.25";

	
	
	@MethodChinaName(cname = "������������")
	//radio, text, password, file 
	String inputType() default "text";
	
	@MethodChinaName(cname = "����")
	String tableName() default "";
	
	@MethodChinaName(cname = "�ֶ���")
	String columnName() default "";
	@MethodChinaName(cname = "�ж��뷽ʽ")
	ElementAlign labelAlign() default ElementAlign.right;
	@MethodChinaName(cname = "�п��")
	int labelWidth() default 0;
	
	@MethodChinaName(cname = "���ʽ")
	String experssion() default "";
}
