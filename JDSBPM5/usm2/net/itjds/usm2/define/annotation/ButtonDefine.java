package net.itjds.usm2.define.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.define.data.annotation.AjaxDataDefine;
import net.itjds.usm2.define.enums.ElementButton;
import net.itjds.usm2.define.mapping.ButtonBean;


import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;

@Retention(RetentionPolicy.RUNTIME)
@ClassMappingAnnotation(clazz=ButtonBean.class)
public @interface ButtonDefine {
	
	@MethodChinaName(cname = "按钮显示文字")
	ElementButton text() default ElementButton.none;
	
	@MethodChinaName(cname = "按钮显示文字")
	String otherText() default "";
	
	@MethodChinaName(cname = "按钮图标")
	String iconCls() default "";
	
	@MethodChinaName(cname = "是否显示")
	boolean hidden() default true;
	
	@MethodChinaName(cname = "动作")
	String handler() default "";
	
	@MethodChinaName(cname = "远程操作")
	AjaxDataDefine ajax() default @AjaxDataDefine(url = "");
	
}
