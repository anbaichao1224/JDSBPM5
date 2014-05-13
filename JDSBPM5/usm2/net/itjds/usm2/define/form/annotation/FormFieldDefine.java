package net.itjds.usm2.define.form.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.define.annotation.SelectItem;
import net.itjds.usm2.define.enums.ElementFieldType;
import net.itjds.usm2.define.enums.ElementItem;
import net.itjds.usm2.define.enums.ElementVtype;
import net.itjds.usm2.define.form.mapping.FormFieldsBean;

import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ClassMappingAnnotation(clazz=FormFieldsBean.class)
public @interface FormFieldDefine {
	
	
	@MethodChinaName(cname = "�ؼ�����")
	ElementFieldType xtype() default ElementFieldType.TextField;
	
	@MethodChinaName(cname = "����У��")
	ElementVtype vtype() ;//��Ҫ����һ������У����
	
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
	int maxValue() default 1000000000;
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
	int width() default 300;
	
	
	@MethodChinaName(cname = "������������")
	//radio, text, password, file 
	String inputType() default "text";
	
	@MethodChinaName(cname = "����")
	String tableName() default "";
	
	@MethodChinaName(cname = "�ֶ���")
	String columnName() default "";
}
