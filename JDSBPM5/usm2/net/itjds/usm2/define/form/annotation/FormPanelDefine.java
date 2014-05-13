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
	
	@MethodChinaName(cname = "设置Buttons按钮的对齐方式")
	@SelectItem(values={"left","center","right"},texts={"左","中","右"})
	ElementAlign buttonAlign() default ElementAlign.center;
	
	Class panelBean()default FormPanelBean.class;
	
	@MethodChinaName(cname="按钮类型")
	ElementButton[] buttons() default ElementButton.none;
	
	@MethodChinaName(cname = "是否显示表单页脚")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean footer() default false;
	
	@MethodChinaName(cname = "是否创建表单标题栏")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean header() default false;
	
	@MethodChinaName(cname = "在标题栏是否显示title")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean headerAsText() default true;
	@MethodChinaName(cname = "是否上传文件")
	boolean fileUpload() default false;
	
	
	
	@MethodChinaName(cname = "表单标签的对齐方式")
	@SelectItem(values={"left","center","right"},texts={"左","中","右"})
	ElementAlign labelAlign() default ElementAlign.left;
	
	@MethodChinaName(cname = "表单标签的宽度")
	int labelWidth();
	
	@MethodChinaName(cname = "form的宽度")
	int  width() default 800;
	int  height() default 600;
	@MethodChinaName(cname = "表单标题")
	String title() default "";
	
	@MethodChinaName(cname = "默认窗口模板")
	String ftlUrl()default "form.ftl";
	
	@MethodChinaName(cname = "form上加ITEM")
	SearchItemDefine[] searchBeans() default {} ;
	@MethodChinaName(cname = "数据来源")
	AjaxDataDefine loadData() default @AjaxDataDefine(
			url="/expression.jsp?expression=$JSONForm($currFormView)"
	); 
	@MethodChinaName(cname = "保存处理")
	AjaxDataDefine updateData() default @AjaxDataDefine(
			url="update.action?expression=$UpdateCurrForm"
	);

	@MethodChinaName(cname = "字段排序")
	String[] fieldsIndex()default{}; 
}
