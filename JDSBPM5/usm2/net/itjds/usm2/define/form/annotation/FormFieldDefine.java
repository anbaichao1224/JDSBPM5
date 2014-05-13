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
	
	
	@MethodChinaName(cname = "控件类型")
	ElementFieldType xtype() default ElementFieldType.TextField;
	
	@MethodChinaName(cname = "数据校验")
	ElementVtype vtype() ;//需要定义一个数据校验类
	
	@MethodChinaName(cname = "数据提交到后台的名称")
	String name() default "";
	
	@MethodChinaName(cname = "控件的唯一标示")
	String id() default "";
	
	@MethodChinaName(cname = "控件的初始化值")
	String value() default ""; 
	
	@MethodChinaName(cname = "是否只读")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean readOnly() default false;
	
	@MethodChinaName(cname = "控件在失去焦点时是否被验证")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean validateOnBlur() default true;
	
	@MethodChinaName(cname = "验证的延时时间(毫秒)")
	int validationDelay() default 250;
	
	@MethodChinaName(cname = "控件是否不可用")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean disabled() default false;
	
	@MethodChinaName(cname = "字段对应的标签说明")
	String fieldLabel() default "";
	
	@MethodChinaName(cname = "是否隐藏标签说明")
	boolean hideLabel() default false;
	
	@MethodChinaName(cname = "隐藏组件时是否隐藏组件所在的容器")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean hidden() default true;
	
	@MethodChinaName(cname = "字段标签与字段之间的分隔符")
	String labelSeparator() default ":";
	
	@MethodChinaName(cname = "是否允许为空")
	boolean allowBlank() default true;
	
	@MethodChinaName(cname = "输入最长度")
	int  maxLength() default 200;
	@MethodChinaName(cname = "store数据")
	String store() default "";
	@MethodChinaName(cname = "单选标签")
	String boxLabel() default "";
	@MethodChinaName(cname = "列布局")
//column值，column1为第一列，column2为第二列，
	String column() default "test";
	@MethodChinaName(cname = "输入最大值")
	int maxValue() default 1000000000;
	@MethodChinaName(cname = "")
	ElementItem item() default ElementItem.none ;
	
	@MethodChinaName(cname = "数据模型")
	//remote,local;
	String model() default "";
	
	@MethodChinaName(cname = "图片地址")
	String src() default "/usm/images/jds.jpg";
	
	@MethodChinaName(cname = "正则验证")
	String regex() default "none";
	
	@MethodChinaName(cname = "正则验证错误提示信息")
	String regexText() default "不符合填写规则";
	
	@MethodChinaName(cname = "控件宽度")
	int width() default 300;
	
	
	@MethodChinaName(cname = "输入栏的类型")
	//radio, text, password, file 
	String inputType() default "text";
	
	@MethodChinaName(cname = "表名")
	String tableName() default "";
	
	@MethodChinaName(cname = "字段名")
	String columnName() default "";
}
